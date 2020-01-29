package org.common.chatbot.engine.impl;

import java.io.File;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import org.common.chatbot.engine.SimpleEngine;
import org.springframework.stereotype.Service;

@Service
public class Engine implements SimpleEngine {

    private static final boolean TRACE_MODE = false;
    static String botName = "AyushVipul_Bot";
    String resourcesPath;
    Bot bot;
    Chat chatSession;
    String textLine = "";

    public Engine(String resourcesPath, Bot bot, Chat chatSession, String textLine) {
        this.resourcesPath = resourcesPath;
        this.bot = bot;
        this.chatSession = chatSession;
        this.textLine = textLine;
    }

    public  Engine() {

        resourcesPath = getResourcesPath();
        MagicBooleans.trace_mode = TRACE_MODE;
        bot = new Bot("super", resourcesPath);

        bot.brain.nodeStats();
        textLine = "";

    }

    @Override
    public String  respondToMessage(String message) {
        Chat chatSession = new Chat(bot);
        System.out.println("message:"+message);
        textLine =message;
        String response = "Sorry I did not Understand";
        try{
        if ((textLine == null) || (textLine.length() < 1))
            textLine = MagicStrings.null_input;
        if (textLine.equals("stop")) {
            System.exit(0);
        } else if (textLine.equals("quit")) {
            bot.writeQuit();
            System.exit(0);
        } else {
            String request = textLine;
            if (MagicBooleans.trace_mode)
                System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
             response = chatSession.multisentenceRespond(request);
            while (response.contains("&lt;"))
                response = response.replace("&lt;", "<");
            while (response.contains("&gt;"))
                response = response.replace("&gt;", ">");
            System.out.println("Robot : " + response);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
