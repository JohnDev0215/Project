package com.spotify.automotive.auth.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import p.c71;
import p.f60;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LoginCredentials {
    public static final Companion f = new Companion(0);
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginCredentialsAuthenticationType {
    }

    @z61(name = "authenticationType")
    public static /* synthetic */ void getAuthenticationType$annotations() {
    }

    @z61(name = "blob")
    public static /* synthetic */ void getBlob$annotations() {
    }

    @z61(name = "password")
    public static /* synthetic */ void getPassword$annotations() {
    }

    @z61(name = "refreshToken")
    public static /* synthetic */ void getRefreshToken$annotations() {
    }

    @z61(name = "username")
    public static /* synthetic */ void getUsername$annotations() {
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoginCredentials)) {
            return false;
        }
        LoginCredentials loginCredentials = (LoginCredentials) obj;
        return f60.p(this.a, loginCredentials.a) && f60.p(this.b, loginCredentials.b) && f60.p(this.c, loginCredentials.c) && f60.p(this.d, loginCredentials.d) && f60.p(this.e, loginCredentials.e);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c, this.d, this.e});
    }
}
