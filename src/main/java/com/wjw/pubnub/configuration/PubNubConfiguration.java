package com.wjw.pubnub.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

@Configuration
@ConfigurationProperties(prefix = "spring.data.pubnub")
public class PubNubConfiguration {

    private String publishKey;
    private String subscribeKey;
    private String secretKey;

    public String getPublishKey() {
        return publishKey;
    }

    public void setPublishKey(String publishKey) {
        this.publishKey = publishKey;
    }

    public String getSubscribeKey() {
        return subscribeKey;
    }

    public void setSubscribeKey(String subscribeKey) {
        this.subscribeKey = subscribeKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Bean(name = "pubnub")
    public PubNub getPubNub() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey(publishKey);
        pnConfiguration.setSubscribeKey(subscribeKey);
        pnConfiguration.setSecure(true);
        pnConfiguration.setSecretKey(secretKey);
        PubNub pubNub = new PubNub(pnConfiguration);
        return pubNub;
    }

}
