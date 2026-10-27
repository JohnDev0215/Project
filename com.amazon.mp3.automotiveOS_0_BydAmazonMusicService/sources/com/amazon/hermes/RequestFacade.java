package com.amazon.hermes;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes2.dex */
public final class RequestFacade<B> {
    private final B body;
    private final Map<String, String> headers = new HashMap();
    private final URL url;

    public RequestFacade(URL url, B b) {
        this.url = url;
        this.body = b;
    }

    public URL getUrl() {
        return this.url;
    }

    public B getBody() {
        return this.body;
    }

    public void addHeaders(Map<String, String> map) {
        this.headers.putAll(map);
    }

    public RequestFacade addHeaders(@Nonnull String str, @Nonnull String str2) {
        this.headers.put((String) Validate.notNull(str), (String) Validate.notNull(str2));
        return this;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
