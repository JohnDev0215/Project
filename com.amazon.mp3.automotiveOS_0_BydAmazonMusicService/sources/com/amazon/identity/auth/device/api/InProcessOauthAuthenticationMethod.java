package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.cb;
import com.amazon.identity.auth.device.d7;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.wa;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class InProcessOauthAuthenticationMethod extends AuthenticationMethod {
    public static final long h = wa.c(2, TimeUnit.MILLISECONDS);
    public final TokenManagement f;
    public final String g;

    public InProcessOauthAuthenticationMethod(Context context, String str, String str2, AuthenticationType authenticationType) {
        super(context, str, authenticationType);
        this.f = (TokenManagement) this.b.getSystemService("dcp_token_mangement");
        this.g = str2;
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    public MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, s2 s2Var) throws IOException {
        Bundle bundleC = null;
        try {
            String scheme = uri.getScheme();
            if (scheme != null && !"https".equals(scheme.toLowerCase(Locale.US))) {
                AuthenticationMethod.a(s2Var, 3, "OAuth authentication has to be over https", null);
                Log.w(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "OAuth authentication has to be over https");
                return s2Var;
            }
            String value = this.f.getValue(this.c, TokenKeys.getAccessTokenKeyForPackage(this.g), null, h);
            if (value == null) {
                AuthenticationMethod.a(s2Var, 2, "Could not authenticate request because we could not get an access token", null);
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "Could not authenticate request because we could not get an access token");
                return s2Var;
            }
            Bundle bundle = new Bundle();
            cb.a(bundle, "x-amz-access-token", value);
            if (s2Var != null) {
                s2Var.onSuccess(bundle);
            }
            return s2Var;
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            if (errorBundle != null) {
                Bundle bundle2 = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context");
                if (bundle2 != null) {
                    bundleC = d7.a(bundle2).c();
                    ga.b("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod", "Received an error when calling getAtzAccessToken. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(errorBundle.getInt("com.amazon.dcp.sso.ErrorCode", -1)), errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                } else {
                    Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "Getting Access Token failed because of callback error. Error Bundle: " + e.c(errorBundle));
                }
            } else {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "Getting Access Token failed because of callback error. No error bundle");
            }
            AuthenticationMethod.a(s2Var, 6, "Getting Access Token failed because of callback error. Error Bundle: " + e.c(errorBundle), bundleC);
            return s2Var;
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            AuthenticationMethod.a(s2Var, 6, "Getting Access Token failed because of InterruptedException. This can happen if the caller kills the thread or asnc task that is calling MAP's api. Exception message: " + e2.getMessage(), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "Getting Access Token failed because of InterruptedException. This can happen if the caller kills the thread or asnc task that is calling MAP's api. Exception message: " + e2.getMessage(), e2);
            return s2Var;
        } catch (ExecutionException e3) {
            AuthenticationMethod.a(s2Var, 6, "Getting Access Token failed failed because of ExecutionException. This can happen when the thread or task was aborted. Exception message: " + e3.getMessage(), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "Getting Access Token failed failed because of ExecutionException. This can happen when the thread or task was aborted. Exception message: " + e3.getMessage(), e3);
            return s2Var;
        } catch (TimeoutException e4) {
            AuthenticationMethod.a(s2Var, 6, "Getting Access Token failed because of TimeoutException. This happens when the timeout passed into the future object occurs. Exception message: " + e4.getMessage(), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod"), "Getting Access Token failed because of TimeoutException. This happens when the timeout passed into the future object occurs. Exception message: " + e4.getMessage(), e4);
            return s2Var;
        }
    }
}
