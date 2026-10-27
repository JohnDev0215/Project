package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class y4 extends RetryLogic {
    public static final String e = "com.amazon.identity.auth.device.y4";
    public final AuthEndpointErrorParser b = new AuthEndpointErrorParser();
    public int c = 0;
    public Context d;

    public y4(Context context) {
        this.d = context;
    }

    @Override // com.amazon.identity.auth.device.framework.RetryLogic
    public RetryLogic.a a(HttpURLConnection httpURLConnection, int i, y5 y5Var) throws Throwable {
        JSONObject jSONObjectA;
        this.c++;
        URL url = ((k5) httpURLConnection).b.getURL();
        try {
            de deVarC = y5Var.c(e.d(url));
            int responseCode = ((k5) httpURLConnection).b.getResponseCode();
            deVarC.e();
            String code = null;
            try {
                jSONObjectA = da.a(httpURLConnection);
            } catch (JSONException e2) {
                String str = e.a(url, responseCode) + ":JSONException";
                ga.a(e, y5Var, str, str);
                Log.e(ga.a(e), "Got JSONException while parsing response.", e2);
                jSONObjectA = null;
            }
            AuthEndpointErrorParser authEndpointErrorParser = this.b;
            authEndpointErrorParser.getClass();
            try {
                AuthEndpointErrorParser.a aVarA = authEndpointErrorParser.a(jSONObjectA);
                if (aVarA != null) {
                    code = aVarA.f320a.getCode();
                }
            } catch (JSONException unused) {
            }
            if (TextUtils.isEmpty(code)) {
                deVarC.a(e.a(url, responseCode));
            } else {
                deVarC.a(e.a(url, responseCode, code));
            }
            deVarC.c();
            if (RetryLogic.a(responseCode)) {
                return cd.b(url) != null ? new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.BackoffError) : new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.ServerInternalError);
            }
            if (jSONObjectA == null) {
                Log.e(ga.a(e), "Malformed exchange token json response detected. Should retry if still within retry limit.");
                return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.InvalidJSON);
            }
            if (i > 0) {
                String str2 = e.d(url) + ":SuccessAfterRetry";
                ga.a(e, y5Var, str2, str2);
            }
            int i2 = this.c;
            if (i2 > 0) {
                y5Var.a(e.b(url), 1.0d / ((double) i2));
            }
            return new RetryLogic.a();
        } catch (IOException e3) {
            String str3 = e;
            Log.e(ga.a(str3), "IOException while calling exchange token endpoint. Will retry. Exception : ", e3);
            if (!e.f(this.d)) {
                this.c--;
            }
            String strC = e.c(url);
            ga.a(str3, y5Var, strC, strC);
            String strA = e.a(url, e3, this.d);
            ga.a(str3, y5Var, strA, strA);
            return new RetryLogic.a(e3);
        }
    }
}
