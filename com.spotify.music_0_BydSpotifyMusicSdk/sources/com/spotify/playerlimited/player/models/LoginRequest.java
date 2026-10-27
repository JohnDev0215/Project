package com.spotify.playerlimited.player.models;

import java.util.Arrays;
import p.c71;
import p.f60;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LoginRequest {
    public LoginCredentials a;
    public LoginOptions b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    @z61(name = "credentials")
    public static /* synthetic */ void getCredentials$annotations() {
    }

    @z61(name = "options")
    public static /* synthetic */ void getLoginOptions$annotations() {
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoginRequest)) {
            return false;
        }
        LoginRequest loginRequest = (LoginRequest) obj;
        return f60.p(this.a, loginRequest.a) && f60.p(this.b, loginRequest.b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b});
    }
}
