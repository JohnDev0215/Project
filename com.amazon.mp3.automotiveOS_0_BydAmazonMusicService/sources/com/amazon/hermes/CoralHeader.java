package com.amazon.hermes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes2.dex */
public class CoralHeader implements Header {
    private static final String PREDEFINED_KEY_ACCEPT = "Accept";
    private static final String PREDEFINED_KEY_ACCEPT_CHARSET = "Accept-Charset";
    private static final String PREDEFINED_KEY_CONNECTION = "Connection";
    private static final String PREDEFINED_KEY_CONTENT_ENCODING = "Content-Encoding";
    private static final String PREDEFINED_KEY_CONTENT_TYPE = "Content-Type";
    private static final String PREDEFINED_KEY_REQUESTID = "x-amzn-RequestId";
    private static final String PREDEFINED_KEY_TARGET = "x-amz-target";
    private ApiInfo apiInfo;
    private AuthMethod authMethod;
    private boolean compressedBody;

    @Deprecated
    public CoralHeader(ApiInfo apiInfo, AuthMethod authMethod, boolean z) {
        Validate.notNull(apiInfo, "ApiInfo cannot be null", new Object[0]);
        Validate.notNull(authMethod, "AuthMethod cannot be null", new Object[0]);
        this.apiInfo = apiInfo;
        this.authMethod = authMethod;
        this.compressedBody = z;
    }

    public CoralHeader(ApiInfo apiInfo, boolean z) {
        Validate.notNull(apiInfo, "ApiInfo cannot be null", new Object[0]);
        this.apiInfo = apiInfo;
        this.compressedBody = z;
    }

    public CoralHeader(ApiInfo apiInfo, AuthMethod authMethod) {
        this(apiInfo, authMethod, false);
    }

    public CoralHeader(ApiInfo apiInfo) {
        this(apiInfo, false);
    }

    @Override // com.amazon.hermes.Header
    public Map<String, String> getHeaders() {
        Map<String, String> predefinedHeaders = getPredefinedHeaders();
        AuthMethod authMethod = this.authMethod;
        if (authMethod != null) {
            predefinedHeaders.putAll(authMethod.getHeaders());
        }
        return predefinedHeaders;
    }

    private Map<String, String> getPredefinedHeaders() {
        HashMap map = new HashMap();
        map.put("Accept", "application/json");
        map.put(PREDEFINED_KEY_ACCEPT_CHARSET, "utf-8");
        map.put("Content-Type", "application/json; charset=UTF-8");
        map.put(PREDEFINED_KEY_CONNECTION, "keep-alive");
        if (this.compressedBody) {
            map.put(PREDEFINED_KEY_CONTENT_ENCODING, "gzip, amz-1.0");
        } else {
            map.put(PREDEFINED_KEY_CONTENT_ENCODING, "amz-1.0");
        }
        map.put(PREDEFINED_KEY_REQUESTID, UUID.randomUUID().toString());
        map.put(PREDEFINED_KEY_TARGET, getTarget());
        return map;
    }

    private String getTarget() {
        return this.apiInfo.getServiceInfo().getServicePackage() + "." + this.apiInfo.getServiceInfo().getServiceName() + "." + this.apiInfo.getApiName();
    }
}
