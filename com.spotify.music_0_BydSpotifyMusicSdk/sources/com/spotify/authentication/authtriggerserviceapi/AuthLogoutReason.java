package com.spotify.authentication.authtriggerserviceapi;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AuthLogoutReason {
    public static final AuthLogoutReason c;
    public static final AuthLogoutReason e;
    public static final AuthLogoutReason k;
    public static final /* synthetic */ AuthLogoutReason[] l;

    /* JADX INFO: Fake field, exist only in values array */
    AuthLogoutReason EF0;

    static {
        AuthLogoutReason authLogoutReason = new AuthLogoutReason("UNDEFINED", 0);
        AuthLogoutReason authLogoutReason2 = new AuthLogoutReason("ATTEMPTING_AUTOLOGIN", 1);
        AuthLogoutReason authLogoutReason3 = new AuthLogoutReason("NOT_LOGGED_IN", 2);
        AuthLogoutReason authLogoutReason4 = new AuthLogoutReason("USER_INITIATED", 3);
        c = authLogoutReason4;
        AuthLogoutReason authLogoutReason5 = new AuthLogoutReason("FORCED_LOGOUT", 4);
        e = authLogoutReason5;
        AuthLogoutReason authLogoutReason6 = new AuthLogoutReason("REMOTE_LOGOUT", 5);
        k = authLogoutReason6;
        l = new AuthLogoutReason[]{authLogoutReason, authLogoutReason2, authLogoutReason3, authLogoutReason4, authLogoutReason5, authLogoutReason6, new AuthLogoutReason("BOOTSTRAP_FAILURE", 6), new AuthLogoutReason("APP_SHUTDOWN", 7), new AuthLogoutReason("ABROAD_SINCE", 8), new AuthLogoutReason("ACCOUNT_SWITCHING", 9), new AuthLogoutReason("ADDING_ACCOUNT_SWITCHING_ACCOUNT", 10)};
    }

    public static AuthLogoutReason valueOf(String str) {
        return (AuthLogoutReason) Enum.valueOf(AuthLogoutReason.class, str);
    }

    public static AuthLogoutReason[] values() {
        return (AuthLogoutReason[]) l.clone();
    }
}
