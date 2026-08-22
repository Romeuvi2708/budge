package org.example.budge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ai.openai.OpenAiChatModel;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiChatModelIT {
    @Autowired
    OpenAiApi openAiApi;

    @Test
    void should_receiveResponse(){


        OpenAiChatOptions options = OpenAiChatOptions.builder()
             .model("gpt-4o-mini")
             .temperature(0.8)
             .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
             .build();

     OpenAiChatModel chatModel =  OpenAiChatModel.builder()
              .openAiApi(openAiApi)
              .build();

      var response =  chatModel.call("gere um registro de budge,  com descrição de gasto, valor em reais");

        assertThat(response).isNotEmpty();
        System.out.println(response);
    }

}