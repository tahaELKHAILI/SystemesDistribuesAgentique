package ma.enset.bankaiagent.web;

import ma.enset.bankaiagent.agents.BankAiAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAiAgentRestController {

    @Autowired
    private BankAiAgent bankAiAgent;

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String query){
        return bankAiAgent.chat(query);
    }
}
