package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i2 {
    public static final String f = "com.amazon.identity.auth.device.i2";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f377a;
    public String b;
    public String c;
    public y5 d;
    public HttpURLConnection e;

    public i2(String str, Bundle bundle, Set<String> set, y5 y5Var) {
        this.f377a = str;
        this.b = EnvironmentUtils.h().c(bundle);
        this.c = EnvironmentUtils.h().e(q9.b(bundle));
        this.d = y5Var;
        this.e = a(set);
    }

    public final HttpURLConnection a(Set<String> set) {
        String str;
        if (e.a(set)) {
            str = "/user/profile";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str2 : set) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append("attributes=");
                sb.append(str2);
            }
            str = "/user/profile?" + sb.toString();
        }
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) ib.a(e.a(EnvironmentUtils.c.a(this.c, str)));
                httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.GET);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty(HttpHeader.AUTHORIZATION, "Bearer " + this.f377a);
                httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, qa.b);
                httpURLConnection.setRequestProperty("x-amzn-identity-auth-domain", this.b);
                String str3 = f;
                Objects.toString(httpURLConnection.getURL());
                ga.a(str3);
                return httpURLConnection;
            } catch (IOException e) {
                ga.a(f, this.d, "IOException happens when trying to open Panda connection", "MAPUserProfileError:IOException", e);
                return null;
            }
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException(String.format("MalformedURLException when generating %s url. This should never happen.", str), e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0084 A[PHI: r1
  0x0084: PHI (r1v2 java.net.HttpURLConnection) = (r1v1 java.net.HttpURLConnection), (r1v4 java.net.HttpURLConnection) binds: [B:28:0x0071, B:33:0x0082] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject a() {
        /*
            r6 = this;
            r0 = 0
            java.net.HttpURLConnection r1 = r6.e     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            if (r1 != 0) goto Lb
            if (r1 == 0) goto La
            r1.disconnect()
        La:
            return r0
        Lb:
            com.amazon.identity.auth.device.framework.RetryLogic.a(r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.net.HttpURLConnection r1 = r6.e     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            int r1 = com.amazon.identity.auth.device.framework.RetryLogic.b(r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.lang.String r2 = com.amazon.identity.auth.device.i2.f     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            r3.<init>()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.lang.String r4 = "Response received from Panda user profile API. Response Code:"
            r3.append(r4)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            r3.append(r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r2)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            android.util.Log.i(r4, r3)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            com.amazon.identity.auth.device.framework.AuthEndpointErrorParser r3 = new com.amazon.identity.auth.device.framework.AuthEndpointErrorParser     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            boolean r1 = com.amazon.identity.auth.device.framework.AuthEndpointErrorParser.a(r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            if (r1 == 0) goto L4b
            java.lang.String r1 = "Error happens when calling Panda user profile api"
            java.lang.String r2 = com.amazon.identity.auth.device.ga.a(r2)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            android.util.Log.e(r2, r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            java.net.HttpURLConnection r1 = r6.e
            if (r1 == 0) goto L4a
            r1.disconnect()
        L4a:
            return r0
        L4b:
            java.net.HttpURLConnection r1 = r6.e     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            org.json.JSONObject r1 = com.amazon.identity.auth.device.da.a(r1)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            if (r1 == 0) goto L59
            r1.toString()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
            com.amazon.identity.auth.device.ga.a(r2)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 org.json.JSONException -> L74
        L59:
            java.net.HttpURLConnection r0 = r6.e
            if (r0 == 0) goto L60
            r0.disconnect()
        L60:
            return r1
        L61:
            r0 = move-exception
            goto L88
        L63:
            r1 = move-exception
            java.lang.String r2 = com.amazon.identity.auth.device.i2.f     // Catch: java.lang.Throwable -> L61
            com.amazon.identity.auth.device.y5 r3 = r6.d     // Catch: java.lang.Throwable -> L61
            java.lang.String r4 = "IOException happens when trying to call user profile"
            java.lang.String r5 = "MAPUserProfileError:IOException"
            com.amazon.identity.auth.device.ga.a(r2, r3, r4, r5, r1)     // Catch: java.lang.Throwable -> L61
            java.net.HttpURLConnection r1 = r6.e
            if (r1 == 0) goto L87
            goto L84
        L74:
            r1 = move-exception
            java.lang.String r2 = com.amazon.identity.auth.device.i2.f     // Catch: java.lang.Throwable -> L61
            com.amazon.identity.auth.device.y5 r3 = r6.d     // Catch: java.lang.Throwable -> L61
            java.lang.String r4 = "JSONException happens when trying to call user profile"
            java.lang.String r5 = "MAPUserProfileError:JSONException"
            com.amazon.identity.auth.device.ga.a(r2, r3, r4, r5, r1)     // Catch: java.lang.Throwable -> L61
            java.net.HttpURLConnection r1 = r6.e
            if (r1 == 0) goto L87
        L84:
            r1.disconnect()
        L87:
            return r0
        L88:
            java.net.HttpURLConnection r1 = r6.e
            if (r1 == 0) goto L8f
            r1.disconnect()
        L8f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.i2.a():org.json.JSONObject");
    }
}
