package org.common.chatbot.chatclient.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.common.chatbot.chatclient.SimpleChatClient;
import org.common.chatbot.engine.SimpleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.*;

@Component("TelegramChatClient")
public class TelegramChatClient implements SimpleChatClient {
    private TelegramBot bot;
    private  SimpleEngine botEngine;

    @Value("${bot.telegram.token}")
    private String token;

    @Autowired
    Environment env;

    @Override
    public SimpleChatClient build(SimpleEngine simpleEngine) throws Exception {
        String x = env.getProperty("bot.telegram.token");
        if(simpleEngine == null)
            throw new Exception("Bot Engine is Null");

        //this.bot = new TelegramBot("983716540:AAFGMtb1bmKJ8tNljKXctsumTQ4XYj607zo");
        this.bot = new TelegramBot(this.token);
        this.botEngine=simpleEngine;
        return this;
    }

    @Override
    public void start() throws Exception {
    if(this.botEngine == null || this.bot == null)
        throw new Exception("Class not Initialized");

    bot.setUpdatesListener(updates -> {
            updates.stream().forEach(x-> {
                System.out.println(x.message().text());
                String res =  botEngine.respondToMessage(x.message().text());
                long chatId = x.message().chat().id();
                SendResponse response = bot.execute(new SendMessage(chatId, res));
            }  );
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

}
