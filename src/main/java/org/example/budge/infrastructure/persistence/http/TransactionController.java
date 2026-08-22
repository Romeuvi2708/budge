package org.example.budge.infrastructure.persistence.http;

import org.example.budge.ResquestParam;
import org.example.budge.application.ListTransactionUserCase;
import org.example.budge.application.PersistTransactionUseCase;
import org.example.budge.domain.Category;
import org.example.budge.infrastructure.persistence.http.reguests.TransactionRequest;
import org.example.budge.infrastructure.persistence.http.response.TransactionResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final ListTransactionUserCase listTransactionUserCase;
    private final TranscriptionModel transcriptionModel;
    private final ChatClient chatClient;
    private final TextToSpeechModel textToSpeechModel;


    public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
                                 ListTransactionUserCase listTransactionUserCase,
                                 TranscriptionModel transcriptionModel,
                                 ChatClient.Builder builder,
                                 TextToSpeechModel textToSpeechModel,
                                 @Value("classpath:prompts/system-message.txt") Resource systemPrompt, TextToSpeechModel textToSpeechModel1
    ) throws IOException {
        this.listTransactionUserCase = listTransactionUserCase;
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.transcriptionModel = transcriptionModel;
        this.textToSpeechModel = textToSpeechModel1;
        this.chatClient = builder
                .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                .defaultTools(persistTransactionUseCase, listTransactionUserCase)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request){
          var transaction = persistTransactionUseCase.execute(request.toInput());
          return TransactionResponse.from(transaction);
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable Category category){
        return listTransactionUserCase.execute(category).stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @PostMapping(value ="/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
     ResponseEntity<Resource> transcribe(@ResquestParam("file") MultipartFile file){
        var userMessage = transcriptionModel.transcribe(file.getResource());
        var result = chatClient.prompt().user(userMessage).call().content();

        byte[] audio = textToSpeechModel.call(result);
        var resource = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body(resource);


    }

}
