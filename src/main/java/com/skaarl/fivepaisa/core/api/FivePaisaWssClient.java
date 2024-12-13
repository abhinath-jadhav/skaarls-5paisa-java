package com.skaarl.fivepaisa.core.api;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.skaarl.fivepaisa.core.service.WssCall;

public class FivePaisaWssClient {

	private final WebSocketObservable wssObser;
	private final String publishUrl;
	final WssCall wss;

	public FivePaisaWssClient(WebSocketObservable wssObser, String publishUrl) {
		this.wssObser = wssObser;
        this.publishUrl = publishUrl;
		wss = new WssCall(publishUrl);
    }

	// Properties pr = new Properties();


	public void wssfeed(JSONObject requestBody, String cookie_ASPXAUTH, String jwtToken, String clientcode)
			throws IOException, ParseException {
		wss.wssCall(requestBody, cookie_ASPXAUTH, jwtToken, clientcode, wssObser);
	}

}
