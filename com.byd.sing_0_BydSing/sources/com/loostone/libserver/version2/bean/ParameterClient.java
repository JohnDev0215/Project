package com.loostone.libserver.version2.bean;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.a;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.text.d;

/* JADX INFO: loaded from: classes.dex */
public final class ParameterClient {
    private String clientId;
    private String clientSecret;
    private String grantType;

    public ParameterClient() {
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public final String getGrantType() {
        return this.grantType;
    }

    public final void setClientId(String str) {
        this.clientId = str;
    }

    public final void setClientSecret(String str) {
        this.clientSecret = str;
    }

    public final void setGrantType(String str) {
        this.grantType = str;
    }

    public /* synthetic */ ParameterClient(String str, String str2, String str3, int i, f fVar) {
        this(str, str2, (i & 4) != 0 ? new String(a.f132a.b(), d.f4111a) : str3);
    }

    public ParameterClient(String str, String str2, String str3) {
        h.d(str, "clientId");
        h.d(str2, "clientSecret");
        h.d(str3, "grantType");
        this.clientId = str;
        this.clientSecret = str2;
        this.grantType = str3;
    }
}
