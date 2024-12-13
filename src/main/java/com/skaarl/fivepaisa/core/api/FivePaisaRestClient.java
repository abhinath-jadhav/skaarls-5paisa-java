package com.skaarl.fivepaisa.core.api;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skaarl.fivepaisa.core.config.FivePaisaConfig;
import com.skaarl.fivepaisa.core.service.ApiCalls;
import com.skaarl.fivepaisa.core.config.FivePaisaProperties;

import okhttp3.Response;

public class FivePaisaRestClient {

	private String holding = "V2/Holding";
	private String marketFeed = "MarketFeed";
	private String placeOrderRequest = "PlaceOrderRequest";
	private String modifyOrderRequest = "ModifyOrderRequest";
	private String cancelOrderRequest = "CancelOrderRequest";
	private String orderStatus = "V2/OrderStatus";
	private String tradeInformation = "TradeInformation";
	private String margin = "V4/Margin";
	private String orderBook = "V3/OrderBook";
	private String netPositionNetWise = "V2/NetPositionNetWise";
	private String smoOrderRequest = "SMOOrderRequest";
	private String modifySmoOrder = "5PSModMOOrd";
	private String loginCheck = "LoginCheck";
	private String totpLogin = "TOTPLogin";
	private String getAccessToken = "GetAccessToken";

	JSONParser parser = new JSONParser();
	ApiCalls ac = new ApiCalls();
	FivePaisaProperties pr;
	FivePaisaConfig config;

	// public RestClient(AppConfig config) {
	// this(config, new Properties());
	// }

	public FivePaisaRestClient(FivePaisaConfig config, FivePaisaProperties properties) {
		this.config = config;
		this.pr = properties;
	}

	public String loginCheck(JSONObject requestBody) throws IOException, ParseException {
		return ac.callCheckLogin(requestBody, loginCheck, pr.getRequestCodeLoginCheck(), config);
	}

	public Response smoOrderRequest(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, smoOrderRequest, pr.getRequestCodeSmoOrderReq(), config);
	}

	public Response modifySmoOrder(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, modifySmoOrder, pr.getRequestCodeModifySmoOrder(), config);
	}

	public String getTotpSession(String clientCode, String totp, String pin) throws IOException, ParseException {
		return ac.getTotpSession(clientCode, totp, pin, totpLogin, getAccessToken, config, pr);
	}

	public Response placeOrderRequest(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, placeOrderRequest, pr.getOrderRequest(), config);
	}

	public Response modifyOrderRequest(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, modifyOrderRequest, pr.getOrderRequest(), config);
	}

	public Response cancelOrderRequest(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, cancelOrderRequest, pr.getOrderRequest(), config);
	}

	public Response holdingV2(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, holding, pr.getHolding(), config);
	}

	public Response marketFeed(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies((requestBody), marketFeed, pr.getMarketFeed(), config);
	}

	public Response orderStatus(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, orderStatus, pr.getOrderStatus(), config);
	}

	public Response tradeInformation(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, tradeInformation, pr.getTradeInformation(), config);
	}

	public Response marginV3(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, margin, pr.getMargin(), config);
	}

	public Response orderBookV2(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, orderBook, pr.getOrderBook(), config);
	}

	public Response netPositionNetWiseV1(JSONObject requestBody) throws IOException, ParseException {
		return ac.callWithCookies(requestBody, netPositionNetWise, pr.getNetPositionNetWise(), config);
	}

}
