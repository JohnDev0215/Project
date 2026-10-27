package com.spotify.playerlimited.player.models;

import com.spotify.connectivity.productstate.RxProductState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LoginResponse {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public int h;
    public long i;
    public String j;
    public long k;
    public String l;
    public int m;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginResponseType {
    }

    static {
        new Companion(0);
    }

    @z61(name = "accessToken")
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @z61(name = "canonical_phone_number")
    public static /* synthetic */ void getCanonicalPhoneNumber$annotations() {
    }

    @z61(name = "challenge_id")
    public static /* synthetic */ void getChallengeId$annotations() {
    }

    @z61(name = "code_length")
    public static /* synthetic */ void getCodeLength$annotations() {
    }

    @z61(name = "expires_in")
    public static /* synthetic */ void getExpiresIn$annotations() {
    }

    @z61(name = "identifier_token")
    public static /* synthetic */ void getIdentifierToken$annotations() {
    }

    @z61(name = "locale")
    public static /* synthetic */ void getLocale$annotations() {
    }

    @z61(name = "method")
    public static /* synthetic */ void getMethod$annotations() {
    }

    @z61(name = "retry_number")
    public static /* synthetic */ void getRetryNumber$annotations() {
    }

    @z61(name = "storedCredentials")
    public static /* synthetic */ void getStoredCredentials$annotations() {
    }

    @z61(name = "tokenType")
    public static /* synthetic */ void getTokenType$annotations() {
    }

    @z61(name = RxProductState.Keys.KEY_TYPE)
    public static /* synthetic */ void getType$annotations() {
    }

    @z61(name = "username")
    public static /* synthetic */ void getUsername$annotations() {
    }
}
