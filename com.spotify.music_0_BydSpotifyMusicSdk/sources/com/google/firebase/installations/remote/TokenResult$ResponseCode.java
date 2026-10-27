package com.google.firebase.installations.remote;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class TokenResult$ResponseCode {
    public static final TokenResult$ResponseCode c;
    public static final TokenResult$ResponseCode e;
    public static final TokenResult$ResponseCode k;
    public static final /* synthetic */ TokenResult$ResponseCode[] l;

    static {
        TokenResult$ResponseCode tokenResult$ResponseCode = new TokenResult$ResponseCode("OK", 0);
        c = tokenResult$ResponseCode;
        TokenResult$ResponseCode tokenResult$ResponseCode2 = new TokenResult$ResponseCode("BAD_CONFIG", 1);
        e = tokenResult$ResponseCode2;
        TokenResult$ResponseCode tokenResult$ResponseCode3 = new TokenResult$ResponseCode("AUTH_ERROR", 2);
        k = tokenResult$ResponseCode3;
        l = new TokenResult$ResponseCode[]{tokenResult$ResponseCode, tokenResult$ResponseCode2, tokenResult$ResponseCode3};
    }

    public static TokenResult$ResponseCode valueOf(String str) {
        return (TokenResult$ResponseCode) Enum.valueOf(TokenResult$ResponseCode.class, str);
    }

    public static TokenResult$ResponseCode[] values() {
        return (TokenResult$ResponseCode[]) l.clone();
    }
}
