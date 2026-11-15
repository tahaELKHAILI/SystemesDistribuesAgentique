package ma.enset.bankaiagent.telegram;

import jakarta.annotation.PostConstruct;
import ma.enset.bankaiagent.agents.BankAiAgent;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.token}")
    private String telegramToken;
    private BankAiAgent bankAiAgent;

    public TelegramBot(BankAiAgent bankAiAgent){
        this.bankAiAgent = bankAiAgent;
    }
    @PostConstruct
    public void registerTelegramBot(){
        try{
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        }
        catch (TelegramApiException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpdateReceived(Update telegramRequest) {
        try{
            if(!telegramRequest.hasMessage())
                return;
            Long chatID = telegramRequest.getMessage().getChatId();
            String query = telegramRequest.getMessage().getText();

            UserMessage userMessage = UserMessage.builder()
                    .text(query)
                    .build();

            sendTypingQuestion(chatID);
            String answer = bankAiAgent.chat(query);
            sendTextMessage(chatID, answer);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getBotUsername(){
        return "BankBot";
    }

    @Override
    public String getBotToken() {
        return telegramToken;
    }

    private void sendTextMessage(Long chatId, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), text);
        execute(sendMessage);
    }

    private void sendTypingQuestion(Long chatId) throws TelegramApiException {
        SendChatAction sendChatAction = new SendChatAction();
        sendChatAction.setChatId(String.valueOf(chatId));
        sendChatAction.setAction(ActionType.TYPING);
        execute(sendChatAction);

    }
}
