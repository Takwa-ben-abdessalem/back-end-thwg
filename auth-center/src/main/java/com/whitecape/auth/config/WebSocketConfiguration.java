package com.whitecape.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.whitecape.auth.handler.ChatWebSocketHandler;
import com.whitecape.auth.handler.NotifWebSocketHnadler;
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final static String CHAT_ENDPOINT = "api/events/chat";
    private final static String Notif_ENDPOINT = "api/events/notif";


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
                .setAllowedOrigins("*");
        
        webSocketHandlerRegistry.addHandler(getNotifWebSocketHandler(), Notif_ENDPOINT)
        .setAllowedOrigins("*");
    }

   
    
    
    @Bean
    public WebSocketHandler getChatWebSocketHandler(){
        return new ChatWebSocketHandler();
    }
    
    @Bean
    public WebSocketHandler getNotifWebSocketHandler(){
        return new NotifWebSocketHnadler();
    }
}
