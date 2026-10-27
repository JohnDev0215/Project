package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.cb;
import com.amazon.identity.auth.device.db;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z3;
import com.amazon.identity.auth.request.NoCredentialsException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AuthenticationMethod {

    @FireOsSdk
    public static final String KEY_AUTH_HEADERS = "auth.headers";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MAPAccountManager f198a;
    public final t5 b;
    public final String c;
    public final String d;
    public final z3 e;

    /* JADX INFO: compiled from: DCP */
    public static final class GetAuthenticationHeadersForRequestError {

        @FireOsSdk
        public static final int INVALID_ARGUMENT = 3;

        @FireOsSdk
        public static final int IPC_ERROR = 1;

        @FireOsSdk
        public static final String KEY_ERROR_CODE = "error_code_key";

        @FireOsSdk
        public static final String KEY_ERROR_MESSAGE = "error_message_key";

        @FireOsSdk
        public static final int NOT_SUPPORTED = 5;

        @FireOsSdk
        public static final int NO_CREDENTIALS = 2;

        @FireOsSdk
        public static final int PACKAGE_NOT_WHITE_LIST = 4;

        @FireOsSdk
        public static final int UNRECOGNIZED = 6;
    }

    public AuthenticationMethod(Context context, String str, AuthenticationType authenticationType) {
        this(context, str, authenticationType.getValue());
    }

    public void a() {
    }

    public void a(db dbVar) throws IOException {
        Map<String, List<String>> headers;
        byte[] body;
        b();
        de deVarB = yd.b(getClass().getSimpleName(), "getAuthenticationBundle");
        Uri uri = dbVar.getUri();
        String httpVerb = dbVar.getHttpVerb();
        if (AuthenticationType.OAuth.getValue().equals(this.d)) {
            headers = Collections.EMPTY_MAP;
            body = new byte[0];
        } else {
            headers = dbVar.getHeaders();
            body = dbVar.getBody();
        }
        try {
            MAPFuture<Bundle> authenticationBundle = getAuthenticationBundle(uri, httpVerb, headers, body, new s2(null));
            if (authenticationBundle == null) {
                throw new AuthenticatedURLConnection.AuthenticationFailureIOException("The future result is null!");
            }
            Bundle bundle = authenticationBundle.get();
            deVarB.c();
            if (bundle == null) {
                throw new AuthenticatedURLConnection.AuthenticationFailureIOException("Cannot authenticate because we received no token, the getToken call failed");
            }
            int i = cb.b;
            HashMap map = new HashMap();
            Bundle bundle2 = bundle.getBundle(KEY_AUTH_HEADERS);
            if (bundle2 != null) {
                for (String str : bundle2.keySet()) {
                    map.put(str, bundle2.getString(str));
                }
            }
            if (map.size() == 0) {
                ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod");
            } else {
                for (Map.Entry entry : map.entrySet()) {
                    if (dbVar.getHeader((String) entry.getKey()) != null) {
                        String.format("Overridding header %s because it is needed for authentication", entry.getKey());
                        ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod");
                    }
                    dbVar.setHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
            a();
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            Bundle bundle3 = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context");
            if (bundle3 != null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "Error happened when try to get authentication bundle. Account needs to be recovered.");
                throw new AuthenticatedURLConnection.AccountNeedsRecoveryException("Error happened when try to get authentication bundle", bundle3);
            }
            Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "Error happened when try to get authentication bundle, the error message is: " + e.c(errorBundle));
            throw new AuthenticatedURLConnection.AuthenticationFailureIOException("Error happened when try to get authentication bundle");
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "InterruptedException happened when try to get authentication bundle result", e2);
            throw new AuthenticatedURLConnection.AuthenticationFailureIOException("InterruptedException happened when try to get authentication bundle. " + e2.getMessage());
        } catch (ExecutionException e3) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "ExecutionException happened when try to get authentication bundle result", e3);
            throw new AuthenticatedURLConnection.AuthenticationFailureIOException("ExecutionException happened when try to get authentication bundle. " + e3.getMessage());
        }
    }

    public final void b() throws NoCredentialsException {
        String str = this.c;
        if (str == null) {
            if (AuthenticationType.OAuth.getValue().equals(this.d)) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "OAuth does not support anonymous credentials");
                throw new NoCredentialsException("OAuth does not support anonymous credentials");
            }
        } else {
            if (this.f198a.isAccountRegistered(str)) {
                return;
            }
            Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "The account that this AuthenticationMethod with is no longer registered");
            ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod", this.c, this.f198a.getAccounts());
            throw new NoCredentialsException("The account that this AuthenticationMethod with is no longer registered");
        }
    }

    public abstract MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, s2 s2Var) throws IOException;

    @FireOsSdk
    public MAPFuture<Bundle> getAuthenticationHeadersForRequest(Uri uri, String str, Map map, byte[] bArr, Callback callback) {
        s2 s2Var = new s2(callback);
        if (!a(uri, str, s2Var)) {
            return s2Var;
        }
        de deVarB = yd.b(getClass().getSimpleName(), "getAuthenticationBundle");
        try {
            try {
                getAuthenticationBundle(uri, str, map, bArr, s2Var);
            } catch (IOException unused) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), "Error happened when trying to sign the request components and get the http headers back!");
            }
            return s2Var;
        } finally {
            deVarB.c();
        }
    }

    public AuthenticationMethod(Context context, String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("Must Specify an Authentication Type");
        }
        MAPInit.getInstance(context).initialize();
        this.c = str;
        this.d = str2;
        t5 t5VarA = t5.a(context);
        this.b = t5VarA;
        this.f198a = new MAPAccountManager(t5VarA);
        this.e = t5VarA.b();
    }

    public static void a(s2 s2Var, int i, String str, Bundle bundle) {
        if (s2Var == null) {
            return;
        }
        Log.e(ga.a("com.amazon.identity.auth.device.api.AuthenticationMethod"), str);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("error_code_key", i);
        bundle2.putString("error_message_key", str);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        s2Var.onError(bundle2);
    }

    public final boolean a(Uri uri, String str, s2 s2Var) {
        try {
            b();
            if (uri == null) {
                a(s2Var, 3, "The serviceUri cannot be null, please check your parameters!", null);
                return false;
            }
            if (TextUtils.isEmpty(str)) {
                a(s2Var, 3, "The verb of the request cannot be null, please check your parameters!", null);
                return false;
            }
            if (TextUtils.isEmpty(this.d)) {
                a(s2Var, 3, "Please specify an authentication type!", null);
                return false;
            }
            AuthenticationType authenticationType = AuthenticationType.parse(this.d);
            if (AuthenticationType.ADPAuthenticator.equals(authenticationType) || AuthenticationType.OAuth.equals(authenticationType)) {
                return true;
            }
            a(s2Var, 5, "Currently MAP just support ADP and OAuh authentication type for this API. Please check your authentication type.", null);
            return false;
        } catch (NoCredentialsException unused) {
            a(s2Var, 2, "The given directedId does not exist!", null);
            return false;
        }
    }
}
