package com.spotify.automotive.auth;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class LoginState {
    public static final LoginState c;
    public static final LoginState e;
    public static final LoginState k;
    public static final LoginState l;
    public static final /* synthetic */ LoginState[] m;

    static {
        LoginState loginState = new LoginState("IN_PROGRESS", 0);
        c = loginState;
        LoginState loginState2 = new LoginState("LOGGED_IN", 1);
        e = loginState2;
        LoginState loginState3 = new LoginState("LOGGED_OUT", 2);
        k = loginState3;
        LoginState loginState4 = new LoginState("APPLICATION_DEPRECATED", 3);
        l = loginState4;
        m = new LoginState[]{loginState, loginState2, loginState3, loginState4};
    }

    public static LoginState valueOf(String str) {
        return (LoginState) Enum.valueOf(LoginState.class, str);
    }

    public static LoginState[] values() {
        return (LoginState[]) m.clone();
    }
}
