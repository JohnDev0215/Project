package com.spotify.automotive.httpwebgategson;

import java.util.List;
import p.c71;
import p.qb2;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class TokenResponse {

    @qb2("refreshToken")
    public String a;

    @qb2("accessToken")
    public String b;

    @qb2("expiresIn")
    public int c;

    @qb2("tokenType")
    public String d;

    @qb2("scope")
    public List<String> e;

    @qb2("errorCode")
    public int f;

    @qb2("errorDescription")
    public String g;

    @z61(name = "accessToken")
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @z61(name = "errorCode")
    public static /* synthetic */ void getErrorCode$annotations() {
    }

    @z61(name = "errorDescription")
    public static /* synthetic */ void getErrorDescription$annotations() {
    }

    @z61(name = "expiresIn")
    public static /* synthetic */ void getExpiresIn$annotations() {
    }

    @z61(name = "refreshToken")
    public static /* synthetic */ void getRefreshToken$annotations() {
    }

    @z61(name = "scope")
    public static /* synthetic */ void getScope$annotations() {
    }

    @z61(name = "tokenType")
    public static /* synthetic */ void getTokenType$annotations() {
    }
}
