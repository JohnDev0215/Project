package com.dee.app.contacts.interfaces.models.apis.getpnvurl;

import com.dee.app.contacts.interfaces.models.data.enums.ClientConfig;
import com.dee.app.contacts.interfaces.models.data.enums.UiConfig;

/* JADX INFO: loaded from: classes5.dex */
public class GetPhoneNumberVerificationURLBody {
    private String actorAccessToken;
    private String actorId;
    private String clientConfig;
    private String redirectUrl;
    private String uiConfig;
    private String refreshTokenType = "ProfileLevel";
    private String policyHandle = "AMAZON_CONTACTS_CID_PID_POLICY_HANDLE";
    private String signInDomain = "www.amazon.com";

    public GetPhoneNumberVerificationURLBody(String str, String str2, String str3) {
        this.actorAccessToken = str;
        this.actorId = str2;
        this.redirectUrl = str3;
    }

    public void setUiConfig(UiConfig uiConfig) {
        this.uiConfig = uiConfig.getValue();
    }

    public void setClientConfig(ClientConfig clientConfig) {
        this.clientConfig = clientConfig.getValue();
    }
}
