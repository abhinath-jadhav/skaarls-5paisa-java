package com.skaarl.fivepaisa.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FivePaisaConfig {

	private String encryptKey; //  "Your encryption key";

	private String key; //  "Your user key";

	private String appVer; //  "1.0";

	private String appName; //  "Your App Name";

	private String osName; //  "WEB";

	private String loginId; //  "Your client code";

	private String userId; //  "Your userId";

	private String password; //  "Your password";

}
