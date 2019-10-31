package com.wjw.pubnub.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

@RestController
@RequestMapping("/pubnub")
public class PubNubController {

    @Autowired
    private PubNub pubnub;

    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendMessage(@RequestBody String message) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("content", message);
        pubnub.publish().message(data).channel("test FED channel").async(new PNCallback<PNPublishResult>() {

            @Override
            public void onResponse(PNPublishResult result, PNStatus status) {
                if (status.isError()) {
                    System.out.println(status.getErrorData().getInformation());
                }
            }
        });
    }
    
    @PostMapping(value = "/base64/decode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String base64Decode(@RequestBody String message) throws UnsupportedEncodingException {
        return URLDecoder.decode(new String(Base64.getDecoder().decode(message)), "UTF-8");
    }
    
}
