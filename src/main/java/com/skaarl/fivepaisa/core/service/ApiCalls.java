package com.skaarl.fivepaisa.core.service;

import java.io.IOException;

import com.skaarl.fivepaisa.core.config.FivePaisaProperties;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skaarl.fivepaisa.core.config.FivePaisaConfig;
import com.skaarl.fivepaisa.core.util.ServerDetails;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiCalls {
	// Properties pr = new Properties();
	public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	String Fivepaisacookie;
	String jwtToken;
	String urls = "https://Openapi.5paisa.com";
	String apiUrl = "https://Openapi.5paisa.com/VendorsAPI/Service1.svc/";
	String loginCheckUrl = "https://openfeed.5paisa.com/Feeds/api/UserActivity/";
	String wssUrl = "wss://openfeed.5paisa.com/Feeds/api/chat?Value1=";
	String ASPXAUTH_Cookie;
	final String FIVE_PAISA_COOKIE = "5paisacookie";
	final String JWT_TOKEN = "JwtToken";

	JSONParser parser = new JSONParser();
	// Properties properties = new Properties();

	public String callCheckLogin(JSONObject reqbody, String suburl, String rc, FivePaisaConfig config)
			throws IOException, ParseException {

		// String cookie = readFile();
		// System.out.println("cookie:====="+cookie);
		OkHttpClient client = new OkHttpClient();
		String url = loginCheckUrl + suburl;

		JSONObject payload = new JSONObject();
		JSONObject requestHead = new JSONObject();
		requestHead = headerWss(rc, config.getUserId(), config);
		payload.put("body", reqbody);
		payload.put("head", requestHead);
		RequestBody body = RequestBody.create(JSON, payload.toJSONString());
		Request request = new Request.Builder().url(url)
				.post(body).build();

		Call call = client.newCall(request);
		Response response = call.execute();
		ASPXAUTH_Cookie = (response.headers().get("set-cookie").split(";", 2)[0]).split("=", 2)[1];

		if (!response.isSuccessful())
			throw new IOException("\n  Unexpected code " + response);

		return ASPXAUTH_Cookie;

	}

	public Response callWithCookies(JSONObject requestBody, String url, String rc, FivePaisaConfig config)
			throws IOException, ParseException {
		JSONObject body = new JSONObject();
		JSONObject OrderStatusJson = (JSONObject) requestBody;
		requestBody = ipConfig(OrderStatusJson);
		JSONObject requestHead = new JSONObject();
		requestHead = header(rc, config.getUserId(), config.getPassword(), config);
		body.put("body", requestBody);
		body.put("head", requestHead);
		Response resonse = apiCallWithCookies(url, body);
		return resonse;
	}

	public String getTotpSession(String clientCode, String totp, String pin, String totpUrl, String getAccessTokenUrl,
			FivePaisaConfig config,
			FivePaisaProperties properties)
			throws IOException, ParseException {
		JSONObject requestBody = new JSONObject();
		JSONObject headObject = new JSONObject();
		headObject.put("Key", config.getKey());

		requestBody.put("head", headObject);

		JSONObject bodyObject = ipConfig(new JSONObject());
		bodyObject.put("Email_ID", clientCode);
		bodyObject.put("TOTP", totp);
		bodyObject.put("PIN", pin);

		requestBody.put("body", bodyObject);
		Response response = apiCall(totpUrl, requestBody, config);
		String respString = response.body().string();
		JSONObject requestTokenObject = (JSONObject) JSONValue.parse(respString);
		requestTokenObject = (JSONObject) requestTokenObject.get("body");
		if ((Long) requestTokenObject.get("Status") == 0) {
			return getOauthSession(clientCode, (String) requestTokenObject.get("RequestToken"), getAccessTokenUrl,
					config,
					properties);
		} else {
			return respString;
		}
	}

	public String getOauthSession(String clientCode, String requestToken, String url, FivePaisaConfig config,
			FivePaisaProperties properties)
			throws IOException, ParseException {
		JSONObject requestBody = new JSONObject();
		JSONObject headObject = new JSONObject();
		headObject.put("Key", config.getKey());

		requestBody.put("head", headObject);

		JSONObject bodyObject = ipConfig(new JSONObject());
		bodyObject.put("RequestToken", requestToken);
		bodyObject.put("EncryKey", config.getEncryptKey());
		bodyObject.put("UserId", config.getUserId());

		requestBody.put("body", bodyObject);

		Response response = apiCall(url, requestBody, config);
		String respString = response.body().string();

		JSONObject requestTokenObject = (JSONObject) JSONValue.parse(respString);
		requestTokenObject = (JSONObject) requestTokenObject.get("body");
		if ((Long) requestTokenObject.get("Status") == 0) {
			jwtToken = (String) requestTokenObject.get("AccessToken");
		}
		return respString;
	}

	public Response apiCall(String suburl, JSONObject reqbody, FivePaisaConfig config)
			throws IOException, ParseException {
		OkHttpClient client = new OkHttpClient();
		String url = apiUrl + suburl;
		RequestBody body = RequestBody.create(reqbody.toJSONString(), JSON);

		Request request = new Request.Builder().url(url)
				.post(body).build();
		Call call = client.newCall(request);
		Response response = call.execute();

		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);
		return response;
	}

	public Response apiCallWithCookies(String suburl, JSONObject reqbody) throws IOException, ParseException {

		OkHttpClient client = new OkHttpClient();
		String url = apiUrl + suburl;
		RequestBody body = RequestBody.create(reqbody.toJSONString(), JSON);
		Request request = new Request.Builder().url(url)
				.addHeader("Authorization", "Bearer " + jwtToken)
				.post(body)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		if (!response.isSuccessful())
			throw new IOException("\n  Unexpected code " + response);
		return response;
	}

	public JSONObject header(String rc, String ui, String pw, FivePaisaConfig config) throws IOException, ParseException {
		JSONObject requestHead = new JSONObject();
		requestHead.put("requestCode", rc);
		requestHead.put("key", config.getKey());
		requestHead.put("appVer", config.getAppVer());
		requestHead.put("appName", config.getAppName());
		requestHead.put("osName", config.getOsName());
		requestHead.put("userId", ui);
		requestHead.put("password", pw);
		// System.out.println(AES.encrypt(ui)+"ENcrypted
		// Password=====:"+AES.encrypt(pw));
		return requestHead;
	}

	public JSONObject headerWss(String rc, String ui, FivePaisaConfig config) throws IOException, ParseException {
		JSONObject requestHead = new JSONObject();
		requestHead.put("requestCode", rc);
		requestHead.put("key", config.getKey());
		requestHead.put("appVer", config.getAppVer());
		requestHead.put("appName", config.getAppName());
		requestHead.put("osName", config.getOsName());
		requestHead.put("LoginId", config.getLoginId());
		// requestHead.put("password",pw);
		return requestHead;
	}

	public JSONObject ipConfig(JSONObject requestBody) throws IOException, ParseException {
		JSONObject ipDetails = ServerDetails.GetIP();
		requestBody.put("LocalIP", ipDetails.get("publicIp"));
		requestBody.put("PublicIP", ipDetails.get("privateIp"));
		return requestBody;
	}

}
