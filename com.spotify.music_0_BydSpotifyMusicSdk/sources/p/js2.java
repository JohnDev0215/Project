package p;

import com.spotify.automotive.auth.LoginException;
import com.spotify.automotive.ubi.LoginUbiLogger$ErrorInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class js2 {
    public static final LoginUbiLogger$ErrorInfo a(LoginException.LoginError loginError) {
        v03.j(loginError, "error");
        int iOrdinal = loginError.ordinal();
        return iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? LoginUbiLogger$ErrorInfo.UNKNOWN : LoginUbiLogger$ErrorInfo.TRAVEL_RESTRICTION : LoginUbiLogger$ErrorInfo.GENERAL : LoginUbiLogger$ErrorInfo.BAD_CREDENTIALS : LoginUbiLogger$ErrorInfo.API_RATE_LIMITED;
    }
}
