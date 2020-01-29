package org.common.chatbot.chatclient;

import org.common.chatbot.engine.SimpleEngine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface SimpleChatClient {

public SimpleChatClient build(SimpleEngine simpleEngine) throws Exception;
public void start() throws Exception;

}
