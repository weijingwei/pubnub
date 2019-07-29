package com.wjw.pubnub.listener;

import java.util.Arrays;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

@WebListener
public class PubNubListener implements ServletContextListener {

    @Autowired
    private PubNub pubnub;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getCategory() == PNStatusCategory.PNUnknownCategory) {
                    System.out.println(status.getErrorData());
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                JsonElement msg = message.getMessage();
                System.out.println(msg);
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {
                // TODO Auto-generated method stub
                
            }
        });

        pubnub.subscribe().channels(Arrays.asList("test BED channel")).execute();
    }

}
