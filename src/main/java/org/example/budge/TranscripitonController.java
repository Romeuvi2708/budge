package org.example.budge;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RequestMapping("/api")
@RestController
public class TranscripitonController {
    private final TranscriptionModel transcriptionModel;

    public TranscripitonController(TranscriptionModel transcriptionModel){
        this.transcriptionModel = transcriptionModel;
    }

    @PostMapping(value ="transcribe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void transcribe(@ResquestParam("file")MultipartFile file){
        var resource = file.getResource();
        var result = transcriptionModel.transcribe(resource);
    }

}
