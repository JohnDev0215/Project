package com.spotify.automotive.ubi;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class LoginUbiLogger$Target {
    public static final LoginUbiLogger$Target c;
    public static final LoginUbiLogger$Target e;
    public static final LoginUbiLogger$Target k;
    public static final LoginUbiLogger$Target l;
    public static final LoginUbiLogger$Target m;
    public static final LoginUbiLogger$Target n;
    public static final /* synthetic */ LoginUbiLogger$Target[] o;

    static {
        LoginUbiLogger$Target loginUbiLogger$Target = new LoginUbiLogger$Target("NAVIGATE_BACK", 0);
        c = loginUbiLogger$Target;
        LoginUbiLogger$Target loginUbiLogger$Target2 = new LoginUbiLogger$Target("NAVIGATE_TO_PIN_BUTTON", 1);
        e = loginUbiLogger$Target2;
        LoginUbiLogger$Target loginUbiLogger$Target3 = new LoginUbiLogger$Target("NAVIGATE_TO_PASSWORD_BUTTON", 2);
        k = loginUbiLogger$Target3;
        LoginUbiLogger$Target loginUbiLogger$Target4 = new LoginUbiLogger$Target("NEED_HELP_BUTTON", 3);
        l = loginUbiLogger$Target4;
        LoginUbiLogger$Target loginUbiLogger$Target5 = new LoginUbiLogger$Target("ERROR_RETRY_BUTTON", 4);
        m = loginUbiLogger$Target5;
        LoginUbiLogger$Target loginUbiLogger$Target6 = new LoginUbiLogger$Target("LOGIN_BUTTON", 5);
        n = loginUbiLogger$Target6;
        o = new LoginUbiLogger$Target[]{loginUbiLogger$Target, loginUbiLogger$Target2, loginUbiLogger$Target3, loginUbiLogger$Target4, loginUbiLogger$Target5, loginUbiLogger$Target6};
    }

    public static LoginUbiLogger$Target valueOf(String str) {
        return (LoginUbiLogger$Target) Enum.valueOf(LoginUbiLogger$Target.class, str);
    }

    public static LoginUbiLogger$Target[] values() {
        return (LoginUbiLogger$Target[]) o.clone();
    }
}
