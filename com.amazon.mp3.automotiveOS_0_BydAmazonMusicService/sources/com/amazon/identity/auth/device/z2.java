package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.DefaultCallback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z2 {
    public static final String f = "z2";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f695a;
    public final TokenManagement b;
    public final MAPAccountManager c;
    public final f d;
    public final OAuthTokenManager e;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f696a;
        public final String b;
        public final long c;
        public final long d;

        public a(String str, String str2, long j, long j2) {
            this.f696a = str;
            this.b = str2;
            this.c = j - System.currentTimeMillis();
            this.d = j2;
        }
    }

    public z2(Context context) {
        this.f695a = context;
        this.b = new TokenManagement(context);
        this.c = new MAPAccountManager(context);
        this.d = e.b(context);
        this.e = new OAuthTokenManager(context);
    }

    public static void a(String str, String str2, long j, Long l, Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString(str2, str);
        bundle.putLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE, j);
        if (l != null) {
            bundle.putLong(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL, l.longValue());
        }
        callback.onSuccess(bundle);
    }

    public static String a(z2 z2Var, String str) {
        try {
            return z2Var.b.getToken(str, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, new Bundle(), new DefaultCallback()).get().getString("value_key");
        } catch (Exception e) {
            Log.e(ga.a(f), "Exception while trying to get the refresh token in the authorizeLinkCode API", e);
            return null;
        }
    }

    public static void a(int i, String str, Callback callback, Bundle bundle) {
        String str2 = f;
        Log.e(ga.a(str2), str);
        ga.a(str2);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle2.putString("com.amazon.dcp.sso.ErrorMessage", str);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        callback.onError(bundle2);
    }

    public static a a(Context context) {
        f8 f8Var = new f8(context, "cbl_storage", 0);
        String string = f8Var.f313a.getString("public_code", null);
        String string2 = f8Var.f313a.getString("private_code", null);
        long j = f8Var.f313a.getLong("expires_at", 0L);
        long j2 = f8Var.f313a.getLong("polling_interval", 0L);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || j == 0 || j2 == 0) {
            Log.i(ga.a(f), "Cannot find existing code pair in storage");
            return null;
        }
        if (j > 0) {
            if (j > System.currentTimeMillis() + 60000) {
                Log.i(ga.a(f), "Returning already existing public code");
                return new a(string, string2, j, j2);
            }
            f8Var.a();
        }
        return null;
    }

    public static HttpsURLConnection a(URL url, String str) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ib.a((HttpsURLConnection) e.a(url));
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
        httpsURLConnection.setRequestProperty(HttpHeader.ACCEPT, "application/json");
        httpsURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, "application/json");
        httpsURLConnection.setRequestProperty("Accept-Language", e.a(Locale.getDefault()));
        httpsURLConnection.setRequestProperty("x-amzn-identity-auth-domain", EnvironmentUtils.c.e(str));
        return httpsURLConnection;
    }

    public final void a(String str, String str2, String str3, Callback callback) {
        a(3, "Could not" + str2 + " because the account credentials that MAP had were invalid. This happens if the account was deregistered from the server side. Returning an account recover context to help recover the account", callback, d7.a().a(str).b(str3).c());
    }
}
