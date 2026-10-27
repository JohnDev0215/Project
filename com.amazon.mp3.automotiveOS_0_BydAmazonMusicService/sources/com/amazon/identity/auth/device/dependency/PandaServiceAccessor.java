package com.amazon.identity.auth.device.dependency;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.ha;
import com.amazon.identity.auth.device.k2;
import com.amazon.identity.auth.device.m2;
import com.amazon.identity.auth.device.q9;
import com.amazon.identity.auth.device.qa;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.y5;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class PandaServiceAccessor {
    public static final String d = "com.amazon.identity.auth.device.dependency.PandaServiceAccessor";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final qa f286a = new qa();
    public final AuthEndpointErrorParser b = new AuthEndpointErrorParser();
    public final t5 c;

    /* JADX INFO: compiled from: DCP */
    public static final class PandaServiceException extends Exception {
        private final MAPError mError;
        private final String mErrorMessage;
        private final int mLegacyErrorCode;
        private final String mLegacyErrorMessage;

        public PandaServiceException(MAPError mAPError, String str, int i, String str2) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        public MAPError a() {
            return this.mError;
        }

        public String b() {
            return this.mErrorMessage;
        }

        public int c() {
            return this.mLegacyErrorCode;
        }

        public String d() {
            return this.mLegacyErrorMessage;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f287a;
        public final JSONObject b;
        public final Map<String, List<String>> c;

        public a(int i, JSONObject jSONObject, Map<String, List<String>> map) {
            this.f287a = i;
            this.b = jSONObject;
            this.c = map;
        }

        public Date a() {
            Calendar calendar = Calendar.getInstance();
            Map<String, List<String>> map = this.c;
            if (map != null && map.containsKey("Cache-Control")) {
                List<String> list = this.c.get("Cache-Control");
                if (!list.isEmpty()) {
                    String str = list.get(0);
                    if (str.contains("max-age=")) {
                        try {
                            calendar.add(13, Integer.parseInt(str.substring(str.indexOf("max-age=") + 8)));
                        } catch (NumberFormatException unused) {
                            Log.w(ga.a(PandaServiceAccessor.d), String.format("Cache-Control header has malformed value: %s", str));
                        }
                    }
                }
            }
            return calendar.getTime();
        }
    }

    public PandaServiceAccessor(Context context) {
        this.c = t5.a(context);
    }

    public a a(String str, m2 m2Var, y5 y5Var) throws JSONException, IOException, PandaServiceException {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnectionA = this.f286a.a((Context) m2Var.f391a, EnvironmentUtils.c.a(q9.a(m2Var.f391a, str), "/auth/bootstrap/sso"), m2Var.a(), false, (List<MAPCookie>) null, str, m2Var.f391a.getPackageName(), y5Var);
                a aVar = new a(RetryLogic.b(httpURLConnectionA), da.a(httpURLConnectionA), httpURLConnectionA.getHeaderFields());
                a(aVar);
                httpURLConnectionA.disconnect();
                return aVar;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Should never occur, hardcoded constant.", e);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public final OAuthTokenManager.a b(a aVar) throws JSONException, ParseException, PandaServiceException {
        a(aVar);
        t5 t5Var = this.c;
        new ha(t5Var);
        JSONObject jSONObject = aVar.b;
        if (jSONObject == null) {
            throw new ParseException("Null response", 0);
        }
        int i = jSONObject.getInt("expires_in");
        String string = jSONObject.getString(CentralTokenManagementCommunication.GetTokenForActorCommand.KEY_TOKEN_TYPE);
        String string2 = jSONObject.getString("access_token");
        String strOptString = jSONObject.optString("refresh_token", null);
        if (!"bearer".equals(string)) {
            throw new ParseException("Unexpected token type.", 0);
        }
        if (string2 != null) {
            return new OAuthTokenManager.a(string2, i, strOptString, null, null);
        }
        throw new ParseException("Incomplete response.", 0);
    }

    public a a(k2 k2Var, y5 y5Var) throws JSONException, IOException, PandaServiceException {
        HttpURLConnection httpURLConnectionA = null;
        try {
            String strE = EnvironmentUtils.c.e(".amazon.com");
            EnvironmentUtils environmentUtils = EnvironmentUtils.c;
            k2Var.getClass();
            URL urlA = environmentUtils.a(strE, "/auth/authority/signature");
            qa qaVar = this.f286a;
            t5 t5Var = k2Var.f391a;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appSignature", k2Var.c);
            jSONObject.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, k2Var.f391a.getApplicationContext().getPackageName());
            httpURLConnectionA = qaVar.a((Context) t5Var, urlA, jSONObject, false, (List<MAPCookie>) null, (String) null, k2Var.f391a.getPackageName(), y5Var);
            a aVar = new a(RetryLogic.b(httpURLConnectionA), da.a(httpURLConnectionA), httpURLConnectionA.getHeaderFields());
            a(aVar);
            httpURLConnectionA.disconnect();
            return aVar;
        } catch (Throwable th) {
            if (httpURLConnectionA != null) {
                httpURLConnectionA.disconnect();
            }
            throw th;
        }
    }

    public final void a(a aVar) throws PandaServiceException {
        String str;
        JSONObject jSONObject = aVar.b;
        int i = aVar.f287a;
        qa qaVar = this.f286a;
        Integer numValueOf = Integer.valueOf(i);
        qaVar.getClass();
        if (AuthEndpointErrorParser.a(numValueOf) || jSONObject == null) {
            String str2 = d;
            Object[] objArr = new Object[1];
            objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response from Panda Service";
            ga.a(str2, "Error Response: %s", objArr);
            AuthEndpointErrorParser.a aVarB = this.b.b(jSONObject);
            if (aVarB != null) {
                str = String.format("Received Error code %s from the server. Message: %s Detail: %s Index: %s", aVarB.f320a.getCode(), aVarB.b, aVarB.c, aVarB.d);
            } else {
                str = String.format(Locale.ENGLISH, "Received unrecognized error from the server with status code %d", Integer.valueOf(i));
            }
            throw new PandaServiceException(MAPError.CommonError.INVALID_RESPONSE, str, 5, str);
        }
    }
}
