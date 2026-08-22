package org.example.budge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiSpeechModelIT {
    @Autowired
    OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @Test
    public void shouldProduceAudioWhemTextIsProvided( ) throws IOException {
       var response = openAiAudioSpeechModel.call("O valor total ficou em 80 reais ");

       assertThat(response).hasSizeGreaterThan(1024);
        var temFile = Files.createTempFile("AUDIO_", ".mp3");
        Files.write(temFile, response);
        System.out.println(temFile.toAbsolutePath());
    }
}
