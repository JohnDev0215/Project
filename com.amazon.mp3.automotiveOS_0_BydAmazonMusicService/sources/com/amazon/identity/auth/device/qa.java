package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.ya;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class qa {
    public static final String b = "AmazonWebView/MAPClientLib/" + s9.a().c + "/Android/" + Build.VERSION.RELEASE + "/" + Build.MODEL;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AuthEndpointErrorParser f515a = new AuthEndpointErrorParser();

    public HttpURLConnection a(Context context, URL url, JSONObject jSONObject, boolean z, List<MAPCookie> list, String str, String str2, y5 y5Var) throws IOException {
        return a(context, url, "application/json", jSONObject.toString(), z, list, str, str2, y5Var);
    }

    public final HttpURLConnection a(Context context, URL url, String str, String str2, boolean z, List<MAPCookie> list, String str3, String str4, y5 y5Var) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ib.a(e.a(url, new y4(context), y5Var, context));
        if (z) {
            AuthenticationMethodFactory authenticationMethodFactory = new AuthenticationMethodFactory(context, str3);
            authenticationMethodFactory.setPackageName(str4);
            httpURLConnection = (HttpURLConnection) ib.a(AuthenticatedURLConnection.openConnection(httpURLConnection, authenticationMethodFactory.newAuthenticationMethod(AuthenticationType.ADPAuthenticator)));
        }
        httpURLConnection.setDoOutput(true);
        if (list != null && list.size() > 0) {
            for (MAPCookie mAPCookie : list) {
                httpURLConnection.addRequestProperty("Cookie", String.format("%s=%s", mAPCookie.c(), mAPCookie.e()));
            }
        }
        httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
        httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, b);
        httpURLConnection.addRequestProperty(HttpHeader.CONTENT_TYPE, str);
        httpURLConnection.addRequestProperty("x-amzn-identity-auth-domain", q9.b(t5.a(context), str3));
        Log.i(ga.a("RequestHelper"), "Starting request to endpoint " + url);
        ga.a("RequestHelper", "Request body: %s", str2);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, ParserUtil.UTF_8);
        try {
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            return httpURLConnection;
        } finally {
            sa.a(outputStream);
            sa.a(outputStreamWriter);
        }
    }

    public HttpURLConnection a(Context context, URL url, ya.b bVar, boolean z, List<MAPCookie> list, String str, String str2, y5 y5Var) throws IOException {
        return a(context, url, "application/x-www-form-urlencoded", bVar.f683a.build().getQuery(), z, list, str, null, y5Var);
    }
}
