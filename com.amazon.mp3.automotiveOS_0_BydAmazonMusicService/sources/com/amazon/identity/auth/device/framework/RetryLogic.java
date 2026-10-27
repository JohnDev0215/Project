package com.amazon.identity.auth.device.framework;

import android.util.Log;
import com.amazon.identity.auth.device.bd;
import com.amazon.identity.auth.device.cd;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.kcpsdk.common.BackoffException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class RetryLogic {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f330a = "RetryLogic";

    /* JADX INFO: compiled from: DCP */
    public enum RetryErrorMessageFromServerSide {
        InvalidJSON("Backend service returns invalid JSON"),
        ServerInternalError(String.format(Locale.ENGLISH, "Backend service returns error code %d to %d", 500, 599)),
        BackoffError("Request is within backoff interval");

        private String mReason;

        RetryErrorMessageFromServerSide(String str) {
            this.mReason = str;
        }

        public String getReason() {
            return this.mReason;
        }
    }

    public static void a(URL url) throws BackoffException {
        bd bdVarB = cd.b(url);
        if (bdVarB != null) {
            long jCurrentTimeMillis = bdVarB.c - System.currentTimeMillis();
            long j = bd.h;
            if (jCurrentTimeMillis > j) {
                Log.i(ga.a("BackoffInfo"), "System clock is set to past, correcting backoff info...");
                cd.a(bdVarB.f248a);
                jCurrentTimeMillis = j;
            }
            if (jCurrentTimeMillis > 0) {
                bd bdVarB2 = cd.b(url);
                String str = url.getHost() + url.getPath();
                String str2 = f330a;
                Locale locale = Locale.ENGLISH;
                String.format(locale, "Host is %s not available and MAP is applying backoff", str);
                ga.a(str2);
                yd.b("BackoffException:" + str, new String[0]);
                if (bdVarB2 == null) {
                    throw new BackoffException(-1, String.format(locale, "MAP run in to a rare race condition during backoff interval, this call is backed off but %s server is back to available after this point.", url.getHost()), bdVarB);
                }
                throw new BackoffException(-1, String.format(locale, "Service %s is unavailable and MAP is applying backoff, please retry after %d ms.", url.getHost(), Long.valueOf(bdVarB2.c - System.currentTimeMillis())), bdVarB);
            }
        }
    }

    public static boolean a(int i) {
        return i >= 500 && i <= 599;
    }

    public static int b(HttpURLConnection httpURLConnection) throws IOException {
        a(httpURLConnection.getURL());
        int responseCode = httpURLConnection.getResponseCode();
        URL url = httpURLConnection.getURL();
        if (a(responseCode)) {
            Log.e(ga.a(cd.h), String.format(Locale.ENGLISH, "MAP received %d response from server, so updating the backoff info", Integer.valueOf(responseCode)));
            cd.a(url);
        } else {
            HashMap<String, bd> map = cd.g;
            synchronized (map) {
                map.remove(cd.c(url));
            }
        }
        return responseCode;
    }

    public abstract a a(HttpURLConnection httpURLConnection, int i, y5 y5Var);

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RetryErrorMessageFromServerSide f331a;
        public IOException b;
        public boolean c = true;

        public a(RetryErrorMessageFromServerSide retryErrorMessageFromServerSide) {
            this.f331a = retryErrorMessageFromServerSide;
        }

        public a(IOException iOException) {
            this.b = iOException;
        }

        public a() {
        }
    }

    public static void a(HttpURLConnection httpURLConnection) {
        int i;
        bd bdVarB = cd.b(httpURLConnection.getURL());
        if (bdVarB != null && (i = bdVarB.e) > 0 && EnvironmentUtils.c.f(httpURLConnection.getURL().getHost())) {
            httpURLConnection.addRequestProperty("x-amzn-identity-retry-attempt", Integer.toString(i));
        }
    }
}
