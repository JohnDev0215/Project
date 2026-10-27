package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.e9;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class TokenManagement {

    @FireOsSdk
    public static final int ERROR_INVALID_PARAMETER = 1;

    @FireOsSdk
    public static final String KEY_ACCESS_TOKEN_FROM_CACHE = "access_token_from_cache";

    @FireOsSdk
    public static final String KEY_ACCOUNT_RECOVER_CONTEXT_BUNDLE = "com.amazon.identity.mobi.account.recover.context";

    @FireOsSdk
    public static final String KEY_ERROR_CODE = "com.amazon.dcp.sso.ErrorCode";

    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "com.amazon.dcp.sso.ErrorMessage";
    public static final String TAG = "com.amazon.identity.auth.device.api.TokenManagement";

    @FireOsSdk
    public static final String VALUE_KEY = "value_key";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e9 f231a;
    public final Context b;

    @FireOsSdk
    public TokenManagement(Context context) {
        MAPInit.getInstance(context).initialize();
        this.b = t5.a(context);
    }

    public final synchronized e9 a() {
        if (this.f231a == null) {
            this.f231a = e.e(this.b);
        }
        return this.f231a;
    }

    @FireOsSdk
    public MAPFuture<Bundle> getCookies(String str, String str2, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("TokenManagement:GetCookies");
        return a().a(str, str2, bundle, yd.a(y5Var, y5Var.a(this.b, "Time"), callback), y5Var);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getCookiesForActor(String str, String str2, String str3, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("TokenManagement:GetCookiesForActor");
        return a().a(str, str2, str3, bundle, yd.a(y5Var, y5Var.a(this.b, "Time"), callback), y5Var);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getToken(String str, String str2, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("TokenManagement:GetToken");
        return a().b(str, str2, bundle, yd.a(y5Var, y5Var.a(this.b, "Time"), callback), y5Var);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getTokenForActor(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("TokenManagement:GetTokenForActor");
        return a().a(context, str, str2, str3, str4, bundle, yd.a(y5Var, y5Var.a(this.b, "Time"), callback), y5Var);
    }

    @FireOsSdk
    public String getValue(String str, String str2, Bundle bundle, long j) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        return getToken(str, str2, bundle, null).get(j, TimeUnit.MILLISECONDS).getString("value_key");
    }

    @FireOsSdk
    public MAPFuture<Bundle> invalidateCookies(String str, String str2, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("TokenManagement:InvalidateCookies");
        return a().c(str, str2, bundle, yd.a(y5Var, y5Var.a(this.b, "Time"), callback), y5Var);
    }

    @FireOsSdk
    public MAPFuture<Bundle> peekCookies(String str, String str2, Callback callback) {
        y5 y5Var = new y5("TokenManagement:PeekCookies");
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a(TAG), "Directed Id given was null. Cannot peek cookies for a deregistered device");
            throw new IllegalArgumentException("Directed Id given was null. Cannot peek cookies for a deregistered device");
        }
        de deVarA = y5Var.a(this.b, "Time");
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.amazon.identity.auth.device.internal.cookiekeys.options.ignorefresh", true);
        return a().a(str, str2, bundle, yd.a(y5Var, deVarA, callback), y5Var);
    }
}
