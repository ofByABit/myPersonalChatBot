package org.common.chatbot.engine;

import org.springframework.stereotype.Service;

@Service
public interface SimpleEngine {

    public String respondToMessage(String message);
}
