package org.common.chatbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.common.chatbot.chatclient.SimpleChatClient;
import org.common.chatbot.chatclient.impl.TelegramChatClient;
import org.common.chatbot.engine.impl.Engine;
import org.common.chatbot.engine.SimpleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
//
//    private static SimpleEngine chatBot = new Engine();
//
//    public static void main( String[] args )
//    {
//        TelegramBot bot = new TelegramBot("983716540:AAFGMtb1bmKJ8tNljKXctsumTQ4XYj607zo");
//
//        bot.setUpdatesListener(updates -> {
//
//            updates.stream().forEach(x-> {
//                System.out.println(x.message().text());
//              String res =  chatBot.respondToMessage(x.message().text());
//
//                long chatId = x.message().chat().id();
//                SendResponse response = bot.execute(new SendMessage(chatId, res));
//
//            }  );
//            return UpdatesListener.CONFIRMED_UPDATES_ALL;
//        });
//
//
//    }
//


   private static SimpleChatClient chatClient;

   @Autowired
   @Qualifier("TelegramChatClient")
   public void setSomeThing(SimpleChatClient client){
       chatClient = client;
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        SimpleEngine chatBot = new Engine();
        //SimpleChatClient chatClient = new TelegramChatClient();
        chatClient.build(chatBot);
        chatClient.start();

    }

}
