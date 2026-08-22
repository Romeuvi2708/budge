package org.example.budge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiChatClientTest {
        @Autowired
    OpenAiChatModel openAiChatModel;

        @Test
        void shouldExecuteSUM(){
        var chatClient  = ChatClient.builder(openAiChatModel).defaultSystem("voce é um matematico").build();

        var response = chatClient.prompt("Some 10 mais 20").call().content();

        assertThat(response).contains("0");
            System.out.println(response);
        }

}
