package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.c;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class x2 implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f654a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Bundle d;
    public final /* synthetic */ y5 e;
    public final /* synthetic */ z2 f;

    public x2(z2 z2Var, String str, String str2, String str3, Bundle bundle, y5 y5Var) {
        this.f = z2Var;
        this.f654a = str;
        this.b = str2;
        this.c = str3;
        this.d = bundle;
        this.e = y5Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.net.HttpURLConnection] */
    @Override // com.amazon.identity.auth.device.c.b
    public Bundle a(Callback callback) throws Throwable {
        URL urlA;
        HttpsURLConnection httpsURLConnectionA;
        de deVarC;
        int iB;
        String str;
        JSONObject jSONObjectA;
        ?? r4 = 0;
        if (!this.f.d.a(this.f654a)) {
            z2.a(3, "The directedID passed in the authorizeLinkCode API is not registered on this device", callback, (Bundle) null);
            return null;
        }
        String strA = z2.a(this.f, this.f654a);
        if (TextUtils.isEmpty(strA)) {
            z2.a(1, "Could not get the auth token for the customer to authorize the link code", callback, (Bundle) null);
            return null;
        }
        z2 z2Var = this.f;
        String str2 = this.f654a;
        String str3 = this.b;
        String str4 = this.c;
        y5 y5Var = this.e;
        z2Var.getClass();
        try {
            try {
                urlA = EnvironmentUtils.c.a(EnvironmentUtils.c.e(str3), "/auth/authorize");
                try {
                    deVarC = y5Var.c(e.d(urlA));
                    httpsURLConnectionA = z2.a(urlA, str3);
                } catch (IOException e) {
                    e = e;
                    httpsURLConnectionA = null;
                } catch (JSONException unused) {
                    httpsURLConnectionA = null;
                } catch (Throwable th) {
                    th = th;
                    if (r4 != 0) {
                        r4.disconnect();
                    }
                    throw th;
                }
                try {
                    sa.a(httpsURLConnectionA.getOutputStream(), e.b(str4, strA).getBytes());
                    iB = RetryLogic.b(httpsURLConnectionA);
                    str = z2.f;
                    Log.i(ga.a(str), "The authorize public code call http response code is " + iB);
                    deVarC.e();
                    jSONObjectA = da.a(httpsURLConnectionA);
                } catch (IOException e2) {
                    e = e2;
                    Log.e(ga.a(z2.f), "IOException while making request to Panda to authorize public code:", e);
                    y5Var.b(e.c(urlA));
                    y5Var.b(e.a(urlA, e, z2Var.f695a));
                    z2.a(5, "Got an IOException while talking to the server : " + e.getMessage(), callback, (Bundle) null);
                    if (httpsURLConnectionA != null) {
                    }
                    return null;
                } catch (JSONException unused2) {
                    z2.a(4, "Could not parse the response from our service to generate the link code", callback, (Bundle) null);
                    if (httpsURLConnectionA != null) {
                    }
                    return null;
                }
            } catch (MalformedURLException e3) {
                Log.e(ga.a(z2.f), "MalformedURLException while trying to generate url to call authorize link code", e3);
                z2.a(1, "Could not construct the url to authorize the link code.", callback, (Bundle) null);
            }
            if (iB != 200) {
                b3 b3VarA = e.a(jSONObjectA);
                String str5 = "Response code : " + iB + " Error Code : " + b3VarA.f237a + " Description : " + b3VarA.b + " Error Index : " + b3VarA.c;
                deVarC.a(e.a(urlA, iB, b3VarA.f237a));
                if (b3VarA.f237a.equals("AuthorizationInPlace")) {
                    Log.i(ga.a(str), "The public code is already authorized.");
                    ((c.a.C0054a) callback).onSuccess(new Bundle());
                } else {
                    Context context = z2Var.f695a;
                    if (!TextUtils.equals(b3VarA.f237a, "CredentialError")) {
                        z2.a(5, str5, callback, (Bundle) null);
                    } else if (!qe.f(context)) {
                        Log.i(ga.a(str), "Deregistering account because while authorizing public code, Panda told us the refresh token is invalid");
                        z2Var.c.deregisterAccount(str2, new w2(z2Var, "authorizeLinkCode"));
                        z2.a(3, "Could not authorize the link code because the account credentials that MAP had were invalid. This happens if the account was deregistered from the server side.", callback, (Bundle) null);
                    } else {
                        z2Var.a(str2, "authorize the link code", "AuthorizePublicCode:CredentialError", callback);
                    }
                }
                httpsURLConnectionA.disconnect();
                return null;
            }
            deVarC.a(e.a(urlA, iB));
            ((c.a.C0054a) callback).onSuccess(new Bundle());
            deVarC.c();
            httpsURLConnectionA.disconnect();
            return null;
        } catch (Throwable th2) {
            th = th2;
            r4 = str3;
        }
    }
}
