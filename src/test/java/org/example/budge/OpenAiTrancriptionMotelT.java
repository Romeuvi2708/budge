package org.example.budge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiTrancriptionMotelT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @ParameterizedTest
    // procura o arquivo de audio e sua palavra-chave
    @CsvSource({
            ""
    })
    public void shouldContentKeywordsWhenAudio(String fileName, String expectedKeyword ){
        var recording = new ClassPathResource("Audio/" + fileName);

        var response = openAiAudioTranscriptionModel.call(recording);

        assertThat(response).contains(expectedKeyword);
        System.out.println(response);
    }
}
