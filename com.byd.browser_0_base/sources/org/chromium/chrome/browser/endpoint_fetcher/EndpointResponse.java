package org.chromium.chrome.browser.endpoint_fetcher;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class EndpointResponse {
    public final String a;

    public EndpointResponse(String str) {
        this.a = str;
    }

    @CalledByNative
    public static EndpointResponse createEndpointResponse(String str) {
        return new EndpointResponse(str);
    }
}
