package com.spotify.automotive.auth.model;

import android.text.TextUtils;
import java.util.Map;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CoreNativeResponseException extends Exception {
    public static final Companion c = new Companion(0);
    private static final long serialVersionUID = -5053671225033614402L;
    private final Map<String, String> headers;
    private final String reason;
    private final Map<String, String> requestHeaders;
    private final int status;
    private final String uri;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreNativeResponseException(int i, String str, String str2, Map map, String str3, Map map2, byte[] bArr, String str4) {
        super("{status=" + i + ", uri=" + str + ", reason=" + str4 + ", message=\"" + str2 + "\", headers=\"" + map + "\", request={action=\"" + str3 + "\", headers=\"" + map2 + "\", body=\"" + v03.R(bArr) + "\"}}");
        v03.j(str2, "message");
        this.status = i;
        this.uri = str;
        this.headers = map;
        this.requestHeaders = map2;
        this.reason = str4;
    }

    public final Map a() {
        return this.requestHeaders;
    }

    public final int b() {
        return this.status;
    }

    public final boolean c() {
        return this.status == 401 && TextUtils.equals("ap_network_disabled", this.headers.get("message"));
    }

    public final boolean d() {
        return this.status == 401 && TextUtils.equals("auth_bad_credentials", this.headers.get("message"));
    }

    public final boolean e() {
        return this.status == 401 && TextUtils.equals("login_region_mismatch", this.headers.get("message"));
    }

    public final boolean f() {
        return this.status == 401 && TextUtils.equals("auth_network", this.headers.get("message"));
    }
}
