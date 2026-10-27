package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class kb extends RetryLogic {
    public static final String e = "com.amazon.identity.auth.device.kb";
    public jb b;
    public int c = 0;
    public Context d;

    public kb(Context context, jb jbVar) {
        this.b = jbVar;
        this.d = context;
    }

    @Override // com.amazon.identity.auth.device.framework.RetryLogic
    public RetryLogic.a a(HttpURLConnection httpURLConnection, int i, y5 y5Var) {
        this.c++;
        URL url = ((k5) httpURLConnection).b.getURL();
        try {
            de deVarC = y5Var.c(e.d(url));
            int responseCode = ((k5) httpURLConnection).b.getResponseCode();
            deVarC.e();
            jb jbVar = this.b;
            deVarC.a(e.a(url, responseCode, jbVar != null ? jbVar.a(httpURLConnection) : null));
            deVarC.c();
            if (RetryLogic.a(responseCode)) {
                if (cd.b(url) != null) {
                    return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.BackoffError);
                }
                ga.b(e, "Got response code %d. Retrying", Integer.valueOf(responseCode));
                return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.ServerInternalError);
            }
            int i2 = this.c;
            if (i2 > 0) {
                y5Var.a(e.b(url), 1.0d / ((double) i2));
            }
            if (i > 0) {
                String str = e.d(url) + ":SuccessAfterRetry";
                ga.a(e, y5Var, str, str);
            }
            return new RetryLogic.a();
        } catch (IOException e2) {
            if (!e.f(this.d)) {
                this.c--;
            }
            String str2 = e;
            Log.e(ga.a(str2), "IOException : ", e2);
            String strC = e.c(url);
            ga.a(str2, y5Var, strC, strC);
            String strA = e.a(url, e2, this.d);
            ga.a(str2, y5Var, strA, strA);
            return new RetryLogic.a(e2);
        }
    }

    public void c(HttpURLConnection httpURLConnection) {
        if (this.c <= 0 || !EnvironmentUtils.c.f(httpURLConnection.getURL().getHost())) {
            return;
        }
        httpURLConnection.addRequestProperty("x-amzn-identity-retry-attempt", String.valueOf(this.c));
    }
}
