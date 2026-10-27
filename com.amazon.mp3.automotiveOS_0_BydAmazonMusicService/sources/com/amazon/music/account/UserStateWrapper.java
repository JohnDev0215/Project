package com.amazon.music.account;

import com.amazon.stratus.IsAccountValidResponse;
import com.amazon.stratus.RetrievePreferencesResponse;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponse;

/* JADX INFO: loaded from: classes4.dex */
class UserStateWrapper {
    private final Boolean invokeAccountWorkflow;
    private final User user;

    public UserStateWrapper(AccountServiceResponseParser accountServiceResponseParser, IsAccountValidResponse isAccountValidResponse, RetrievePreferencesResponse retrievePreferencesResponse, RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferencesResponse, String str) {
        if (isAccountValidResponse != null) {
            this.user = accountServiceResponseParser.parseUser(isAccountValidResponse, retrievePreferencesResponse, retrievePrivacyCompliancePreferencesResponse, str);
            this.invokeAccountWorkflow = isAccountValidResponse.isShouldInvokeWebview();
        } else {
            this.user = null;
            this.invokeAccountWorkflow = Boolean.FALSE;
        }
    }

    public User getUser() {
        return this.user;
    }

    public boolean shouldInvokeAccountWorkflow() {
        return Boolean.TRUE.equals(this.invokeAccountWorkflow);
    }
}
