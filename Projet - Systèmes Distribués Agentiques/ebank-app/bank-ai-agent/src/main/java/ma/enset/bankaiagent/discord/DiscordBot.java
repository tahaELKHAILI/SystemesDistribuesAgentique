package ma.enset.bankaiagent.discord;


import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import ma.enset.bankaiagent.agents.BankAiAgent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@DiscordController
public class DiscordBot {

    private BankAiAgent bankAiAgent;

    public DiscordBot( BankAiAgent bankAiAgent){
        this.bankAiAgent = bankAiAgent;
    }

    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        if(event.getAuthor().isBot())
            return;
        String query = event.getMessage().getContentRaw();
        String response = bankAiAgent.chat(query);
        event.getChannel().sendMessage(response).queue();
    }
}
