package com.spotify.connectivity.httpwebgate;

import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.HttpUrl;
import okhttp3.Request;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class WebgateHelper {
    public static final Companion Companion = new Companion(null);
    public static final String DEFAULT_WEBGATE_HOST = "spclient.wg.spotify.com";
    public static final String DEFAULT_WEBGATE_PROTOCOL = "https";
    public static final String EXPERIMENTAL_WEBGATE_HOST = "exp.wg.spotify.com";
    public static final String IMAGE_UPLOAD_HOST = "image-upload.spotify.com";
    public static final String INTERNAL_WEBGATE_HOST = "wgint.spotify.net";
    public static final String WEB_API_HOST = "api.spotify.com";
    private String customHost;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final boolean isWebgateHost(String str) {
        return v03.b(DEFAULT_WEBGATE_HOST, str) || v03.b(EXPERIMENTAL_WEBGATE_HOST, str) || v03.b(INTERNAL_WEBGATE_HOST, str) || v03.b(IMAGE_UPLOAD_HOST, str) || v03.b(WEB_API_HOST, str);
    }

    public final boolean hasNoAuthTag(Request request) {
        v03.j(request, "request");
        return Object.class.cast(request.e.get(Object.class)) instanceof NoAuthTag;
    }

    public final boolean isWebgateRequest(Request request) {
        v03.j(request, "request");
        HttpUrl httpUrl = request.a;
        if (!httpUrl.j) {
            return false;
        }
        if (isWebgateHost(httpUrl.d)) {
            return true;
        }
        String str = this.customHost;
        if (str != null) {
            return ng2.b0(str, httpUrl.d, true);
        }
        return false;
    }

    public final void setWebgateHost(String str) {
        v03.j(str, "customHost");
        this.customHost = str;
    }
}
