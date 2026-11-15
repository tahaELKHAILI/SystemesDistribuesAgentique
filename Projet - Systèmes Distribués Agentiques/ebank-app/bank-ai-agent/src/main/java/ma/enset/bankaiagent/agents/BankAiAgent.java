package ma.enset.bankaiagent.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

@Component
public class BankAiAgent {

    private ChatClient chatClient;

    public BankAiAgent(ChatClient.Builder chatClient, ToolCallbackProvider tools){
        this.chatClient = chatClient
                .defaultSystem("""
                        You are an assistant who supposed to answer user questions about clients, bank accounts
                        and bank transactions based on the provided context.
                        If no context is provided say I don't know.
                        if some values like balance, amount are provided, make sure they are of type double and not
                        String.
                        """)
                .defaultTools(tools)
                .build();
    }

    public String chat(String query){
        return chatClient.prompt(query).call().content();
    }
}
