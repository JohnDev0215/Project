package com.amazon.hermes;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class OauthAuthMethod implements AuthMethod {
    private String accessToken;

    public OauthAuthMethod(String str) {
        Validate.notEmpty(str, "accessToken can't be null", new Object[0]);
        this.accessToken = str;
    }

    @Override // com.amazon.hermes.AuthMethod
    public Map<String, String> getHeaders() {
        HashMap map = new HashMap();
        map.put("x-amz-access-token", this.accessToken);
        return map;
    }
}
