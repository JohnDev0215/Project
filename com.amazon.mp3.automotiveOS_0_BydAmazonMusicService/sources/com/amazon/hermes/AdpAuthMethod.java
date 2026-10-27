package com.amazon.hermes;

import com.amazon.hermes.security.RequestSigner;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class AdpAuthMethod implements AuthMethod {
    private static final String POST = "POST";
    private Map<String, String> headers;

    public AdpAuthMethod(String str, String str2, URL url, Object obj) {
        this.headers = new HashMap();
        Validate.notEmpty(str, "adpToken can't be null", new Object[0]);
        Validate.notNull(str2, "privateKey can't be null", new Object[0]);
        Validate.notNull(url, "url can't be null", new Object[0]);
        Validate.notNull(obj, "request can't be null", new Object[0]);
        this.headers = RequestSigner.signRequest("POST", url, getSerializedBody(obj), str2, str);
    }

    @Override // com.amazon.hermes.AuthMethod
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    private static final String getSerializedBody(Object obj) {
        try {
            return Hermes.get().getObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
