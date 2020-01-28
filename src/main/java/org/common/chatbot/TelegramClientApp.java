package org.common.chatbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.common.chatbot.engine.Engine;
import org.common.chatbot.engine.SimpleEngine;

import java.io.File;

/**
 * Hello world!
 *
 */
public class TelegramClientApp
{

    private static SimpleEngine chatBot = new Engine();

    public static void main( String[] args )
    {
        TelegramBot bot = new TelegramBot("983716540:AAFGMtb1bmKJ8tNljKXctsumTQ4XYj607zo");

        bot.setUpdatesListener(updates -> {

            updates.stream().forEach(x-> {
                System.out.println(x.message().text());
              String res =  chatBot.respondToMessage(x.message().text());

                long chatId = x.message().chat().id();
                SendResponse response = bot.execute(new SendMessage(chatId, res));

            }  );
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });


    }


}
