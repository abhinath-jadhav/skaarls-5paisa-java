package com.skaarl.fivepaisa.core.config;

import lombok.Getter;

@Getter
public class FivePaisaProperties {

	public static final int threadTime = 500;

	private final String requestCodeLoginv2 = "5PLoginV2";
	private final String requestCodeLoginv3 = "5PLoginV3";
	private final String requestCodeLoginv4 = "5PLoginV4";
	private final String requestCodeLoginCheck = "5PLoginCheck";

	private final String requestCodeSmoOrderReq = "SMOOrderRequest";

	private final String requestCodeModifySmoOrder = "5PSModMOOrd";

	private final String holding = "5PMarginV3";

	private final String marketFeed = "5PMF";

	private final String orderRequest = "5POrdReq";

	private final String orderStatus = "5POrdStatus";

	private final String tradeInformation = "5PTrdInfo";

	private final String margin = "5PMarginV3";

	private final String orderBook = "5POrdBkV2";

	private final String netPositionNetWise = "5PNPNWV1";
	private final String clientCode;//  = "Your client code";
	private final int AppSource; // = 11033; // Your App source;

	private final String remoteIpAddress; // = "192.0.0.0";
	private final String machineID;// = "039377 ";

	public FivePaisaProperties(String clientCode, int appSource, String remoteIpAddress, String machineID) {
		this.clientCode = clientCode;
		this.AppSource = appSource;
		this.remoteIpAddress = remoteIpAddress;
		this.machineID = machineID;
	}
}
