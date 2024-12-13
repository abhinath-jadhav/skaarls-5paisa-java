
import com.skaarl.fivepaisa.core.api.FivePaisaRestClient;
import com.skaarl.fivepaisa.core.api.FivePaisaWssClient;
import com.skaarl.fivepaisa.core.api.WebSocketObservable;
import com.skaarl.fivepaisa.core.config.FivePaisaConfig;
import com.skaarl.fivepaisa.core.config.FivePaisaProperties;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;


import okhttp3.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class Test {

    public static void main(String[] args) {
        FivePaisaConfig config = getConfig();

        FivePaisaProperties properties = new FivePaisaProperties("52656869", 24869, "","1234");
       // eg 12345678

        FivePaisaRestClient apis = new FivePaisaRestClient(config, properties);
        try {
//            String response = apis.getTotpSession(properties.getClientCode(),
//                    "321996", "181195");
//
//            System.out.println("\n Response >> " + response);
//
//            System.out.println(" \n ************* OrderRequest  ************* \n");
           wssCall();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void wssCall(){
        FivePaisaWssClient client = new FivePaisaWssClient(new WebSocketObservable(), "public");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Method", "MarketFeedV3");
        jsonObject.put("Operation","Subscribe");
        jsonObject.put("ClientCode","52656869");

        JSONObject marketFeedData1 = new JSONObject();
        marketFeedData1.put("Exch", "N");
        marketFeedData1.put("ExchType", "C");
        marketFeedData1.put("ScripCode", 3405);

        JSONObject marketFeedData2 = new JSONObject();
        marketFeedData2.put("Exch", "N");
        marketFeedData2.put("ExchType", "C");
        marketFeedData2.put("ScripCode", 395);

        jsonObject.put("MarketFeedData", List.of(marketFeedData1, marketFeedData2));
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjUyNjU2ODY5Iiwicm9sZSI6IjI0ODY5IiwiU3RhdGUiOiIiLCJSZWRpcmVjdFNlcnZlciI6IkIiLCJuYmYiOjE3MzM5MTAzMjIsImV4cCI6MTczMzk0MTc5OSwiaWF0IjoxNzMzOTEwMzIyfQ.8yj3STzOsahLyXEpo1N7til1MjlM3sKQAB9YpWkR5XI";
        try {
            client.wssfeed(jsonObject,"", jwt, "52656869");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static FivePaisaConfig getConfig() {
        FivePaisaConfig config = new FivePaisaConfig();
        config.setAppName("5P52656869");// eg 5P12345678
        config.setAppVer("1.0");
        config.setOsName("WEB");
        config.setEncryptKey("mzFkXZxeTcOJW8VClDSPv3Gmi5SKGF1W"); // eg. ABCDEFGHIJKLMNOPQURSTUVWXY
        config.setKey("3Xm7Cmp1xR6SVmya4kDCAeccOwgSGwV5");// eg ABCDEFGHIJKLMNOPQURSTUVWXYZANCDEF
        config.setUserId("rkBRs1WdhFR");
        config.setPassword("pJwzJBIppWK");// eg. ABCDEFGHIJK
        config.setLoginId("52656869");// eg 12345678
        return config;
    }

    public static void getOrderBook(FivePaisaConfig config, FivePaisaProperties properties, FivePaisaRestClient apis) throws Exception {
        System.out.println(" \n ************* OrderBookV2  ************* \n");
        JSONObject obj3 = new JSONObject();
        obj3.put("ClientCode", properties.getClientCode());
        Response response = apis.orderBookV2(obj3);
        System.out.println("\n Response >> " + response.body().string());
    }

    public static void orderRequest(FivePaisaConfig config, FivePaisaProperties properties, FivePaisaRestClient apis) throws Exception {
        JSONObject obj3 = new JSONObject();
        obj3.put("ClientCode", properties.getClientCode());
        obj3.put("OrderFor", "P");// P - New Order placed, M - Modify, C- Cancel
        obj3.put("Exchange", "N");// N- NSE, B-BSE, M - MCX
        obj3.put("ExchangeType", "D");// D - Derivative, C - Cash, U - Currency
        obj3.put("ScripCode", 35019);
        obj3.put("Price", 0);// 0 - For market order
        obj3.put("OrderID", 2);
        obj3.put("OrderType", "BUY");// BUY/SELL
        obj3.put("Qty", 50);// For NSE FUT Qty=Lotsize*Lot ie for 1lot Qty=50
        obj3.put("OrderDateTime", "/Date(1563857357612)/");// Current time
        obj3.put("AtMarket", true);
        obj3.put("RemoteOrderID", "s0002201907231019172");
        obj3.put("ExchOrderID", 0);// 0 - for new order, Actual value for modify and cancel
        obj3.put("DisQty", 0);// Disclosed quantity
        obj3.put("IsStopLossOrder", false);
        obj3.put("StopLossPrice", 0);// 0 - For at market stoploss price
        obj3.put("IsVTD", false);
        obj3.put("IOCOrder", false);
        obj3.put("IsIntraday", false);
        obj3.put("PublicIP", "192.168.84.215");
        obj3.put("AHPlaced", "N");// Y - For After hour orders
        obj3.put("ValidTillDate", "/Date(1600248018615)/");
        obj3.put("iOrderValidity", 0);
        obj3.put("OrderRequesterCode", properties.getClientCode());
        obj3.put("TradedQty", 0);
        obj3.put("AppSource", properties.getAppSource());
        Response response = apis.placeOrderRequest(obj3);
        String resp = response.body().string();
        System.out.println("\n Response =========>> " + resp);
    }

}
