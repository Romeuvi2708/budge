package org.example.budge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ToolCallingTest {
        @Autowired
    OpenAiChatModel openAiChatModel;

        static  class MatchTools{
            @Tool(description = "soma dois numeros inteiros")
            public int sum(int a, int b){
                return a + b;
                }
            @Tool(description = "subtrai dois numeros inteiros")
            public int diff(int a, int b){
                return a - b;
            }
        }

        @Test
        void shouldExecuteSUM(){
        var chatClient  = ChatClient.builder(openAiChatModel).defaultSystem("voce é um matematico").defaultTools(new MatchTools()).build();

        var response = chatClient.prompt("Some 10 mais 20").call().content();

        assertThat(response).contains("0");
            System.out.println(response);
        }

}
