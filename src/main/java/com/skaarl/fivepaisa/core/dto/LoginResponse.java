package com.skaarl.fivepaisa.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @JsonProperty("body")
    private OtpRequestBody body;

    @JsonProperty("head")
    private RequestHead head;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OtpRequestBody {

        @JsonProperty("AccessToken")
        private String accessToken;

        @JsonProperty("AllowBseCash")
        private String allowBseCash;

        @JsonProperty("AllowBseDeriv")
        private String allowBseDeriv;

        @JsonProperty("AllowBseMF")
        private String allowBseMF;

        @JsonProperty("AllowMCXComm")
        private String allowMCXComm;

        @JsonProperty("AllowMcxSx")
        private String allowMcxSx;

        @JsonProperty("AllowNSECurrency")
        private String allowNSECurrency;

        @JsonProperty("AllowNSEL")
        private String allowNSEL;

        @JsonProperty("AllowNseCash")
        private String allowNseCash;

        @JsonProperty("AllowNseComm")
        private String allowNseComm;

        @JsonProperty("AllowNseDeriv")
        private String allowNseDeriv;

        @JsonProperty("AllowNseMF")
        private String allowNseMF;

        @JsonProperty("BulkOrderAllowed")
        private Integer bulkOrderAllowed;

        @JsonProperty("CleareDt")
        private String cleareDt;

        @JsonProperty("ClientCode")
        private String clientCode;

        @JsonProperty("ClientName")
        private String clientName;

        @JsonProperty("ClientType")
        private String clientType;

        @JsonProperty("CommodityEnabled")
        private String commodityEnabled;

        @JsonProperty("CustomerType")
        private String customerType;

        @JsonProperty("DPInfoAvailable")
        private String dpInfoAvailable;

        @JsonProperty("DemoTrial")
        private String demoTrial;

        @JsonProperty("DirectMFCharges")
        private Integer directMFCharges;

        @JsonProperty("IsIDBound")
        private Integer isIDBound;

        @JsonProperty("IsIDBound2")
        private Integer isIDBound2;

        @JsonProperty("IsOnlyMF")
        private String isOnlyMF;

        @JsonProperty("IsPLM")
        private Integer isPLM;

        @JsonProperty("IsPLMDefined")
        private Integer isPLMDefined;

        @JsonProperty("Message")
        private String message;

        @JsonProperty("OTPCredentialID")
        private String otpCredentialID;

        @JsonProperty("PGCharges")
        private Integer pgCharges;

        @JsonProperty("PLMsAllowed")
        private Integer plMsAllowed;

        @JsonProperty("POAStatus")
        private String poaStatus;

        @JsonProperty("PasswordChangeFlag")
        private Integer passwordChangeFlag;

        @JsonProperty("PasswordChangeMessage")
        private String passwordChangeMessage;

        @JsonProperty("ReferralBenefits")
        private Integer referralBenefits;

        @JsonProperty("RefreshToken")
        private String refreshToken;

        @JsonProperty("RunningAuthorization")
        private Integer runningAuthorization;

        @JsonProperty("Status")
        private Integer status;

        @JsonProperty("VersionChanged")
        private Integer versionChanged;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestHead {

        @JsonProperty("Status")
        private Integer status;

        @JsonProperty("StatusDescription")
        private String statusDescription;
    }
}


