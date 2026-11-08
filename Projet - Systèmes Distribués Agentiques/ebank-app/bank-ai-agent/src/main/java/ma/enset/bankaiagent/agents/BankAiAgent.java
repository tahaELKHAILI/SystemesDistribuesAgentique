package ma.enset.bankaiagent.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

@Component
public class BankAiAgent {

    private ChatClient chatClient;

    public BankAiAgent(ChatClient.Builder chatClient, ToolCallbackProvider tools){
        this.chatClient = chatClient
                .defaultTools(tools)
                .build();
    }

    public String chat(String query){
        return chatClient.prompt(query).call().content();
    }
}
