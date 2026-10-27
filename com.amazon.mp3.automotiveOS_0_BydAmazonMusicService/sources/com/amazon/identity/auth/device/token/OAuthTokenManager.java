package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.c8;
import com.amazon.identity.auth.device.d7;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.e2;
import com.amazon.identity.auth.device.f7;
import com.amazon.identity.auth.device.f8;
import com.amazon.identity.auth.device.fa;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.ha;
import com.amazon.identity.auth.device.i;
import com.amazon.identity.auth.device.j9;
import com.amazon.identity.auth.device.k7;
import com.amazon.identity.auth.device.me;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.p0;
import com.amazon.identity.auth.device.qa;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.t8;
import com.amazon.identity.auth.device.u8;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.v8;
import com.amazon.identity.auth.device.w5;
import com.amazon.identity.auth.device.w8;
import com.amazon.identity.auth.device.wa;
import com.amazon.identity.auth.device.x8;
import com.amazon.identity.auth.device.xa;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z9;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class OAuthTokenManager {
    public static final long k;
    public static final long l;
    public static final Set<AuthTokenExchangeType> m;
    public static final Set<String> n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f593a;
    public final w5 b;
    public final c8 c;
    public final MAPAccountManager d;
    public final p0 e;
    public final x8 f;
    public final ha g;
    public final v8 h;
    public final w8 i;
    public final j9 j;

    /* JADX INFO: compiled from: DCP */
    public enum AuthTokenExchangeType {
        DMSTokenToOauthTokenExchange("exchangeDMSCredentialsForOAuthTokenFailure"),
        OauthRefreshToAccessExchange("refreshNormalOAuthTokenFailure"),
        OauthRefreshToCookieExchange("fetchCookiesFromServerFailure"),
        OauthRefreshToDelegationAccessExchange("refreshDelegatedOAuthTokenFailure"),
        AuthorizationCodeToOAuthAccessTokenExchange("authorizationCodeToAccessTokenFailure");

        public final String mFailureMetric;

        AuthTokenExchangeType(String str) {
            this.mFailureMetric = str;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f594a;
        public final int b;
        public final String c;
        public final String d;
        public final String e;

        public a(String str, int i, String str2, String str3, String str4) {
            this.f594a = str;
            this.b = i;
            this.d = str3;
            this.c = str2;
            this.e = str4;
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        k = wa.c(1L, timeUnit);
        l = wa.c(1L, timeUnit);
        m = EnumSet.allOf(AuthTokenExchangeType.class);
        n = new HashSet(Arrays.asList("A4ZP7ZC4PI6TO", "A1Z88NGR2BK6A2", "A15996VY63BQ2D", "A1XWJRHALS1REP"));
    }

    public OAuthTokenManager(Context context) {
        this(context, (w5) t5.a(context).getSystemService("dcp_system"), new c8(context), new qa(), new MAPAccountManager(context), new p0(), new v8(t5.a(context), new qa()), new ha(context), new x8(t5.a(context), new c8(context)), new w8(context));
    }

    public static void a(OAuthTokenManager oAuthTokenManager, f8 f8Var, String str, boolean z) {
        oAuthTokenManager.getClass();
        f8Var.a(a("account_transfer_key", str), Boolean.valueOf(z));
        String strA = a("timestamp_key", str);
        oAuthTokenManager.b.getClass();
        f8Var.a(strA, System.currentTimeMillis());
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02b3  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(java.lang.String r24, com.amazon.identity.auth.device.fa r25, android.os.Bundle r26, com.amazon.identity.auth.device.y5 r27) throws com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException {
        /*
            Method dump skipped, instruction units count: 743
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.b(java.lang.String, com.amazon.identity.auth.device.fa, android.os.Bundle, com.amazon.identity.auth.device.y5):java.lang.String");
    }

    public final boolean c(String str, fa faVar, Bundle bundle, y5 y5Var) {
        String strF;
        String str2 = faVar.b;
        synchronized (this.j) {
            strF = this.c.f(str, TokenKeys.getAccessTokenKeyForPackage(str2));
        }
        if (strF != null) {
            return d(str, faVar, bundle, y5Var);
        }
        return true;
    }

    public boolean d(String str, fa faVar, Bundle bundle, y5 y5Var) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH)) {
            return a(str, faVar, bundle);
        }
        Log.i(ga.a("OAuthTokenManager"), "Force refresh the OAuth access token.");
        me.a aVar = (me.a) yd.a();
        aVar.f438a = "FORCE_REFRESH_OAUTH";
        aVar.a().a();
        y5Var.b("FORCE_REFRESH_OAUTH");
        return true;
    }

    public final boolean e(String str, String str2) {
        String str3 = "/" + str2;
        if (!str.endsWith(e.a((String) null, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token") + str3)) {
            if (!str.endsWith(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT) + str3)) {
                if (!str.endsWith(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT) + str3)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: DCP */
    public static final class OAuthTokenManagerException extends Exception implements d7.a {
        private static final long serialVersionUID = -7354549861193710767L;
        private d7 mAccountRecoverContext;
        private AuthEndpointErrorParser.a mAuthEndpointError;
        private final MAPError mError;
        private final String mErrorMessage;
        private final int mLegacyErrorCode;
        private final String mLegacyErrorMessage;
        private final boolean mShouldClearAuthCookies;

        public OAuthTokenManagerException(MAPError mAPError, String str) {
            this(mAPError, str, MAPAccountManager.RegistrationError.LEGACY_ERROR_CODE_NOT_SUPPORTED_ERROR.value(), "Legacy MAP error code is not supported. Please refer com.amazon.identity.auth.device.api.MAPError");
        }

        @Override // com.amazon.identity.auth.device.d7.a
        public d7 a() {
            return this.mAccountRecoverContext;
        }

        @Override // com.amazon.identity.auth.device.d7.a
        @Deprecated
        public String b() {
            return this.mLegacyErrorMessage;
        }

        @Override // com.amazon.identity.auth.device.d7.a
        @Deprecated
        public int c() {
            return this.mLegacyErrorCode;
        }

        public AuthEndpointErrorParser.a d() {
            return this.mAuthEndpointError;
        }

        public MAPError e() {
            return this.mError;
        }

        public String f() {
            return this.mErrorMessage;
        }

        public boolean g() {
            return this.mShouldClearAuthCookies;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAccountRecoverContext = null;
            this.mAuthEndpointError = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, Throwable th) {
            super(th.getMessage(), th);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = th.getMessage();
            this.mAccountRecoverContext = null;
            this.mAuthEndpointError = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2, AuthEndpointErrorParser.a aVar) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAuthEndpointError = aVar;
            this.mAccountRecoverContext = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2, AuthEndpointErrorParser.a aVar, boolean z) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAuthEndpointError = aVar;
            this.mAccountRecoverContext = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = z;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2, AuthEndpointErrorParser.a aVar, d7 d7Var) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAuthEndpointError = aVar;
            this.mAccountRecoverContext = d7Var;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            com.amazon.identity.auth.device.j9 r0 = r7.j
            monitor-enter(r0)
            java.lang.String r1 = "OAuthTokenManager"
            com.amazon.identity.auth.device.ga.a(r1)     // Catch: java.lang.Throwable -> L6a
            com.amazon.identity.auth.device.c8 r1 = r7.c     // Catch: java.lang.Throwable -> L6a
            com.amazon.identity.auth.device.u7 r1 = r1.b     // Catch: java.lang.Throwable -> L6a
            java.util.Set r1 = r1.d(r8)     // Catch: java.lang.Throwable -> L6a
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L6a
        L14:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L6a
            if (r2 == 0) goto L66
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L6a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
            r3.<init>()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a
            r3.append(r9)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L6a
            boolean r4 = r7.e(r2, r9)     // Catch: java.lang.Throwable -> L6a
            if (r4 != 0) goto L56
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
            r4.<init>()     // Catch: java.lang.Throwable -> L6a
            r5 = 0
            java.lang.String r6 = "com.amazon.dcp.sso.token.oauth.amazon.actor.refresh_token"
            java.lang.String r5 = com.amazon.identity.auth.device.e.a(r5, r6)     // Catch: java.lang.Throwable -> L6a
            r4.append(r5)     // Catch: java.lang.Throwable -> L6a
            r4.append(r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L6a
            boolean r3 = r2.endsWith(r3)     // Catch: java.lang.Throwable -> L6a
            if (r3 == 0) goto L54
            goto L56
        L54:
            r3 = 0
            goto L57
        L56:
            r3 = 1
        L57:
            if (r3 == 0) goto L14
            java.lang.String r3 = "OAuthTokenManager"
            com.amazon.identity.auth.device.ga.a(r3)     // Catch: java.lang.Throwable -> L6a
            com.amazon.identity.auth.device.c8 r3 = r7.c     // Catch: java.lang.Throwable -> L6a
            com.amazon.identity.auth.device.u7 r3 = r3.b     // Catch: java.lang.Throwable -> L6a
            r3.a(r8, r2)     // Catch: java.lang.Throwable -> L6a
            goto L14
        L66:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6a
            return
        L68:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6a
            throw r8
        L6a:
            r8 = move-exception
            goto L68
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.c(java.lang.String, java.lang.String):void");
    }

    public OAuthTokenManager(Context context, w5 w5Var, c8 c8Var, qa qaVar, MAPAccountManager mAPAccountManager, p0 p0Var, v8 v8Var, ha haVar, x8 x8Var, w8 w8Var) {
        t5 t5VarA = t5.a(context);
        this.f593a = t5VarA;
        this.b = w5Var;
        this.c = c8Var;
        this.d = mAPAccountManager;
        this.e = p0Var;
        this.f = x8Var;
        t5VarA.b();
        this.h = v8Var;
        this.g = haVar;
        this.i = w8Var;
        this.j = j9.a();
    }

    public final String d(String str, String str2) {
        String strF;
        synchronized (this.j) {
            strF = this.c.f(str, e.a(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
        }
        return strF;
    }

    public a b(String str, String str2, String str3, y5 y5Var) throws JSONException, OAuthTokenManagerException, IOException, ParseException {
        HttpURLConnection httpURLConnectionB = null;
        try {
            de deVarA = yd.a("OAuthTokenManager", "refreshNormalOAuthToken");
            httpURLConnectionB = this.h.b(str3, str, str2, y5Var);
            int iB = RetryLogic.b(httpURLConnectionB);
            Log.i(ga.a("OAuthTokenManager"), "Response received from OAuth refresh to access exchange end-point");
            ha haVar = this.g;
            haVar.getClass();
            synchronized (ha.class) {
                if (ha.f) {
                    haVar.f370a.a("map_version_recorded_server", "20220722N");
                    ha.f = false;
                }
            }
            JSONObject jSONObjectA = da.a(httpURLConnectionB);
            deVarA.c();
            if (!this.h.a(Integer.valueOf(iB)) && jSONObjectA != null) {
                a aVarD = this.h.d(jSONObjectA);
                yd.a("refreshNormalOAuthTokenSuccess", new String[0]);
                httpURLConnectionB.disconnect();
                return aVarD;
            }
            Object[] objArr = new Object[1];
            objArr[0] = jSONObjectA != null ? jSONObjectA.toString() : "Null Json Response";
            ga.a("OAuthTokenManager", "Error Response: %s", objArr);
            throw a(str, (String) null, this.h.c(jSONObjectA), Integer.valueOf(iB), AuthTokenExchangeType.OauthRefreshToAccessExchange);
        } catch (Throwable th) {
            if (httpURLConnectionB != null) {
                httpURLConnectionB.disconnect();
            }
            throw th;
        }
    }

    public String b(String str, String str2, y5 y5Var) throws OAuthTokenManagerException {
        String strF;
        try {
            String strC = this.c.c(str, e.a(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
            if (strC == null) {
                return a(str, str2, false, y5Var);
            }
            a aVarB = b(str, str2, strC, y5Var);
            synchronized (this.j) {
                String strC2 = this.c.c(str, e.a(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
                if (TextUtils.equals(strC2, strC)) {
                    Log.i(ga.a("OAuthTokenManager"), "Refresh token is not changed, store the exchanged token.");
                    a(str, str2, aVarB);
                    return aVarB.f594a;
                }
                Log.i(ga.a("OAuthTokenManager"), "Refresh token has been changed, try read from the database.");
                yd.b("MAP_CID_ATNR_Changed_TokenExchange", new String[0]);
                synchronized (this.j) {
                    strF = this.c.f(str, TokenKeys.getAccessTokenKeyForPackage(str2));
                }
                if (!TextUtils.isEmpty(strF)) {
                    yd.b("MAP_CID_ATNR_Changed_TokenExchange_ReturnCached", new String[0]);
                    Log.i(ga.a("OAuthTokenManager"), "Local database access token is not empty, return it.");
                    return strF;
                }
                yd.b("MAP_CID_ATNR_Changed_TokenExchange_Refresh", new String[0]);
                Log.i(ga.a("OAuthTokenManager"), "Local database access token is empty, refresh it.");
                return b(str, str2, strC2, y5Var).f594a;
            }
        } catch (IOException e) {
            yd.a("refreshNormalOAuthTokenFailure:IOException", new String[0]);
            yd.b("NetworkError10:OAuthTokenManager", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), 3, e);
        } catch (ParseException e2) {
            yd.a("refreshNormalOAuthTokenFailure:ParseException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e2.getMessage()), 5, e2.getMessage());
        } catch (JSONException e3) {
            yd.a("refreshNormalOAuthTokenFailure:JSONException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e3.getMessage()), 5, e3.getMessage());
        }
    }

    public final String b(String str, String str2, String str3, Bundle bundle, y5 y5Var) throws OAuthTokenManagerException {
        try {
            de deVarA = yd.a("OAuthTokenManager", "refreshDelegatedOAuthToken");
            f7.a aVarC = new k7.c(new v8(t5.a(this.f593a), new qa()), this.f593a, str, str2, bundle).c(y5Var);
            deVarA.c();
            aVarC.a();
            JSONObject jSONObject = aVarC.f312a;
            Integer num = aVarC.b;
            Log.i(ga.a("OAuthTokenManager"), "Response received for exchange delegate account token.");
            if (!this.h.a(num) && jSONObject != null) {
                yd.a("refreshDelegatedOAuthTokenPandaSuccess", new String[0]);
                a aVarD = this.h.d(jSONObject);
                a(str, str3, aVarD);
                return aVarD.f594a;
            }
            Object[] objArr = new Object[1];
            objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response";
            ga.a("OAuthTokenManager", "Error Response: %s", objArr);
            throw a(str, (String) null, this.h.c(jSONObject), num, AuthTokenExchangeType.OauthRefreshToDelegationAccessExchange);
        } catch (IOException e) {
            yd.a("refreshDelegatedOAuthTokenFailurePanda:IOException", new String[0]);
            yd.b("NetworkError11:OAuthTokenManager", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), 3, e);
        } catch (ParseException e2) {
            yd.a("refreshDelegatedOAuthTokenFailurePanda:ParseException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e2.getMessage()), 5, e2);
        } catch (JSONException e3) {
            yd.a("refreshDelegatedOAuthTokenFailurePanda:JSONException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e3.getMessage()), 5, e3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            com.amazon.identity.auth.device.j9 r0 = r5.j
            monitor-enter(r0)
            java.lang.String r1 = "OAuthTokenManager"
            com.amazon.identity.auth.device.ga.a(r1)     // Catch: java.lang.Throwable -> L5b
            com.amazon.identity.auth.device.c8 r1 = r5.c     // Catch: java.lang.Throwable -> L5b
            com.amazon.identity.auth.device.u7 r1 = r1.b     // Catch: java.lang.Throwable -> L5b
            java.util.Set r1 = r1.d(r6)     // Catch: java.lang.Throwable -> L5b
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L5b
        L14:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L5b
            if (r2 == 0) goto L57
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L5b
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L5b
            java.lang.String r3 = "com.amazon.dcp.sso.token.amazon.actor.cookies"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> L5b
            if (r3 == 0) goto L47
            boolean r3 = r2.endsWith(r7)     // Catch: java.lang.Throwable -> L5b
            if (r3 != 0) goto L45
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5b
            r3.<init>()     // Catch: java.lang.Throwable -> L5b
            r3.append(r7)     // Catch: java.lang.Throwable -> L5b
            java.lang.String r4 = "."
            r3.append(r4)     // Catch: java.lang.Throwable -> L5b
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L5b
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> L5b
            if (r3 == 0) goto L47
        L45:
            r3 = 1
            goto L48
        L47:
            r3 = 0
        L48:
            if (r3 == 0) goto L14
            java.lang.String r3 = "OAuthTokenManager"
            com.amazon.identity.auth.device.ga.a(r3)     // Catch: java.lang.Throwable -> L5b
            com.amazon.identity.auth.device.c8 r3 = r5.c     // Catch: java.lang.Throwable -> L5b
            com.amazon.identity.auth.device.u7 r3 = r3.b     // Catch: java.lang.Throwable -> L5b
            r3.a(r6, r2)     // Catch: java.lang.Throwable -> L5b
            goto L14
        L57:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5b
            return
        L59:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5b
            throw r6
        L5b:
            r6 = move-exception
            goto L59
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.b(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r1v15, types: [com.amazon.identity.auth.device.c8] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.amazon.identity.auth.device.c8] */
    /* JADX WARN: Type inference failed for: r20v0, types: [com.amazon.identity.auth.device.token.OAuthTokenManager] */
    /* JADX WARN: Type inference failed for: r2v10, types: [com.amazon.identity.auth.device.c8] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public String a(String str, String str2, fa faVar, String str3, y5 y5Var, @NonNull Bundle bundle) throws Throwable {
        int i;
        int i2;
        int i3;
        ?? r12 = str2;
        Log.i(ga.a("OAuthTokenManager"), "refreshing actor access token...");
        int i4 = 0;
        String strD = d(str, null);
        String strA = a(str, faVar.b, y5Var);
        String strE = this.c.e(str, r12, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN);
        try {
            try {
                a[] aVarArrA = a(str, strD, strA, str2, strE, faVar.b, str3, y5Var, bundle);
                j9 j9Var = this.j;
                try {
                    synchronized (j9Var) {
                        try {
                            String strA2 = a(str, faVar.b, y5Var);
                            String strD2 = d(str, null);
                            String strE2 = this.c.e(str, r12, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN);
                            if (TextUtils.equals(strE2, strE) && TextUtils.equals(strA2, strA) && TextUtils.equals(strD2, strD)) {
                                Log.i(ga.a("OAuthTokenManager"), "Actor and account refresh token is not changed, store and return it.");
                                return a(str, r12, faVar.b, aVarArrA);
                            }
                            Log.i(ga.a("OAuthTokenManager"), "Actor or account refresh token has been changed, read from database.");
                            i4 = 0;
                            try {
                                yd.b("MAP_CID_PID_ATNR_Changed_TokenExchange", new String[0]);
                                String strE3 = this.c.e(str, r12, e.a(faVar.b, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"));
                                if (!TextUtils.isEmpty(strE3)) {
                                    yd.b("MAP_CID_PID_ATNR_Changed_TokenExchange_ReturnCached", new String[0]);
                                    Log.i(ga.a("OAuthTokenManager"), "Local database actor access token is not empty, return it.");
                                    return strE3;
                                }
                                Log.i(ga.a("OAuthTokenManager"), "Local database actor access token is empty, refreshing it.");
                                yd.b("MAP_CID_PID_ATNR_Changed_TokenExchange_Refresh", new String[0]);
                                a[] aVarArrA2 = a(str, strD2, strA2, str2, strE2, faVar.b, str3, y5Var, bundle);
                                String str4 = faVar.b;
                                for (a aVar : aVarArrA2) {
                                    String str5 = aVar.e;
                                    if (!TextUtils.isEmpty(str5) && str5.equals(z9.c(this.f593a, str4))) {
                                        return aVar.f594a;
                                    }
                                }
                                ga.a("OAuthTokenManager");
                                throw new ParseException("Can not get actor token from service response", 0);
                            } catch (Throwable th) {
                                th = th;
                                r12 = j9Var;
                                try {
                                    throw th;
                                } catch (IOException e) {
                                    e = e;
                                    i3 = 1;
                                    yd.b("refreshActorTokenFailure:IOException", new String[i4]);
                                    MAPError.CommonError commonError = MAPError.CommonError.NETWORK_ERROR;
                                    Object[] objArr = new Object[i3];
                                    objArr[i4] = e.getMessage();
                                    throw new OAuthTokenManagerException(commonError, String.format("A network error occurred: %s", objArr), 3, e);
                                } catch (ParseException e2) {
                                    e = e2;
                                    i = 1;
                                    i2 = 5;
                                    yd.b("refreshActorTokenFailure:ParseException", new String[i4]);
                                    MAPError.CommonError commonError2 = MAPError.CommonError.INVALID_RESPONSE;
                                    Object[] objArr2 = new Object[i];
                                    objArr2[i4] = e.getMessage();
                                    throw new OAuthTokenManagerException(commonError2, String.format("An invalid response was received: %s", objArr2), i2, e.getMessage());
                                } catch (JSONException e3) {
                                    e = e3;
                                    yd.b("refreshActorTokenFailure:JSONException", new String[i4]);
                                    MAPError.CommonError commonError3 = MAPError.CommonError.INVALID_RESPONSE;
                                    Object[] objArr3 = new Object[1];
                                    objArr3[i4] = e.getMessage();
                                    throw new OAuthTokenManagerException(commonError3, String.format("An invalid response was received: %s", objArr3), 5, e.getMessage());
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            r12 = j9Var;
                            i4 = 0;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e4) {
                e = e4;
                i4 = 0;
            } catch (ParseException e5) {
                e = e5;
                i4 = 0;
            } catch (JSONException e6) {
                e = e6;
                i4 = 0;
            }
        } catch (IOException e7) {
            e = e7;
            i4 = 0;
            i3 = 1;
        } catch (ParseException e8) {
            e = e8;
            i4 = 0;
            i = 1;
            i2 = 5;
        } catch (JSONException e9) {
            e = e9;
            i4 = 0;
        }
    }

    public String a(String str, fa faVar, Bundle bundle, y5 y5Var) throws OAuthTokenManagerException {
        String strB;
        if (!TextUtils.isEmpty(str)) {
            if ("com.amazon.dcp.sso.token.oauth.amazon.access_token".equals(faVar.c)) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                Bundle bundle2 = bundle;
                String strA = a(str, bundle2);
                if (!TextUtils.isEmpty(strA)) {
                    if (!TextUtils.isEmpty(strA) && !TextUtils.isEmpty(str)) {
                        String str2 = faVar.b;
                        if (this.d.isAccountRegistered(strA)) {
                            if (a(bundle2, y5Var)) {
                                strB = b(str, a(strA, faVar.b, true, y5Var), faVar.b, bundle2, y5Var);
                            } else if (c(str, faVar, bundle2, y5Var)) {
                                String strD = d(strA, str2);
                                if (strD == null) {
                                    strD = a(strA, str2, true, y5Var);
                                }
                                strB = b(str, TextUtils.isEmpty(strD) ? a(strA, faVar.b, true, y5Var) : strD, faVar.b, bundle2, y5Var);
                            } else {
                                strB = null;
                            }
                        } else {
                            Log.w(ga.a("OAuthTokenManager"), "The delegatee account is already deregistered.");
                            String.format("The delegatee account %s is already deregistered.", strA);
                            MAPError.AccountError accountError = MAPError.AccountError.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED;
                            throw new OAuthTokenManagerException(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED.value(), "The delegatee account is already deregistered on this device");
                        }
                    } else {
                        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given account or delegated account is currently not valid", 8, "Given account or delegated account is currently not valid");
                    }
                } else {
                    try {
                        strB = b(str, faVar, bundle2, y5Var);
                    } catch (UnsupportedOperationException e) {
                        throw new OAuthTokenManagerException(MAPError.AccountError.CUSTOMER_NOT_FOUND, MAPError.AccountError.CUSTOMER_NOT_FOUND.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), e);
                    }
                }
                return TextUtils.isEmpty(strB) ? this.c.c(str, faVar.f315a) : strB;
            }
            String str3 = String.format("Token key %s is not a valid key", faVar.f315a);
            throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, str3, 7, str3);
        }
        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given Account is currently not valid", 8, "Given Account is currently not valid");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(android.content.Context r9, java.lang.String r10, java.lang.String r11, com.amazon.identity.auth.device.fa r12, java.lang.String r13, @androidx.annotation.NonNull android.os.Bundle r14, com.amazon.identity.auth.device.y5 r15) throws com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.a(android.content.Context, java.lang.String, java.lang.String, com.amazon.identity.auth.device.fa, java.lang.String, android.os.Bundle, com.amazon.identity.auth.device.y5):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(@androidx.annotation.NonNull java.lang.String r17, java.lang.String r18, @androidx.annotation.NonNull java.lang.String r19, @androidx.annotation.NonNull com.amazon.identity.auth.device.api.Callback r20, @androidx.annotation.NonNull com.amazon.identity.auth.device.y5 r21, android.os.Bundle r22) throws com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException {
        /*
            Method dump skipped, instruction units count: 507
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.a(java.lang.String, java.lang.String, java.lang.String, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.y5, android.os.Bundle):void");
    }

    public final void a(String str, Set<String> set) {
        for (String str2 : this.c.b.c(str)) {
            synchronized (this.j) {
                ga.a("OAuthTokenManager");
                for (String str3 : this.c.b.d(str)) {
                    if (e(str3, str2)) {
                        ga.a("OAuthTokenManager");
                        this.c.b.a(str, str3);
                    }
                }
            }
            b(str, str2);
        }
        for (String str4 : this.c.b.d(str)) {
            if (str4.startsWith("com.amazon.dcp.sso.token.amazon.cookies.")) {
                this.c.a(str, str4);
            }
        }
        for (String str5 : set) {
            HashSet hashSet = new HashSet();
            hashSet.add(TokenKeys.getAccessTokenKeyForPackage(null));
            hashSet.add(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT));
            hashSet.add(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT));
            c8 c8Var = this.c;
            c8Var.getClass();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                c8Var.b.a(str, e.a(c8Var.f264a, str5, fa.a((String) it.next()).c));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x000a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.util.List<com.amazon.identity.auth.device.w8.c> r10, java.util.Set<java.lang.String> r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.a(java.util.List, java.util.Set, java.lang.String, java.lang.String):void");
    }

    public void a(String str, String str2, String str3, @NonNull Bundle bundle, y5 y5Var) throws OAuthTokenManagerException {
        try {
            bundle.putBundle("actor_cookies_for_request", new t8(this.f593a).a(str, str2, str3, new Bundle(), y5Var));
        } catch (MAPCallbackErrorException unused) {
            throw new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch actor cookies internally for get actor token request with failure context.", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Unable to fetch actor cookies internally for get actor token request with failure context.");
        }
    }

    public void a(String str, String str2, @NonNull Bundle bundle, y5 y5Var) throws OAuthTokenManagerException {
        try {
            bundle.putBundle("account_cookies_for_request", new t8(this.f593a).a(str, str2, new Bundle(), y5Var));
        } catch (MAPCallbackErrorException unused) {
            throw new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch account cookies internally.", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Unable to fetch account cookies internally.");
        }
    }

    public String a(String str, String str2, String str3, y5 y5Var) throws Throwable {
        String strE = this.c.e(str, str2, e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN));
        if (!TextUtils.isEmpty(strE)) {
            return strE;
        }
        Log.i(ga.a("OAuthTokenManager"), "No local actor refresh token, try get one by calling getActorAccessToken.");
        a(str, str2, new fa(null, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"), (String) null, y5Var, new Bundle());
        return this.c.e(str, str2, e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN));
    }

    public final String a(String str, Bundle bundle) {
        String string = bundle.getString("com.amazon.dcp.sso.property.account.delegateeaccount");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        yd.b("GetDelegatedTokenUnnecessaryDelegatee", new String[0]);
        p0 p0Var = this.e;
        c8 c8Var = this.c;
        p0Var.getClass();
        Map<String, String> map = p0.f485a;
        String str2 = map.get(str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String strD = c8Var.d(str, "com.amazon.dcp.sso.property.account.delegateeaccount");
        if (!TextUtils.isEmpty(strD)) {
            map.put(str, strD);
        }
        return strD;
    }

    public final boolean a(Bundle bundle, y5 y5Var) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_DMS_TO_OAUTH)) {
            return false;
        }
        Log.i(ga.a("OAuthTokenManager"), "Force refresh the DMS token for OAuth token.");
        me.a aVar = (me.a) yd.a();
        aVar.f438a = "FORCE_REFRESH_DMS";
        aVar.a().a();
        y5Var.b("FORCE_REFRESH_DMS");
        return true;
    }

    public boolean a(String str, fa faVar, Bundle bundle) {
        String strC = this.c.c(str, e.a(faVar.b, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT));
        this.b.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (a(jCurrentTimeMillis, strC)) {
            Log.i(ga.a("OAuthTokenManager"), "Clock skew detected. Refreshing...");
            return true;
        }
        Long lK = e.k(this.c.c(str, e.a(faVar.b, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT)));
        if (lK == null || !a(lK, Long.valueOf(jCurrentTimeMillis), bundle)) {
            return false;
        }
        Log.i(ga.a("OAuthTokenManager"), "OAuth access token near or past expiry. Need to refresh it...");
        return true;
    }

    public final boolean a(Long l2, Long l3, Bundle bundle) {
        return (l3.longValue() + bundle.getLong(TokenKeys.Options.KEY_OAUTH_TTL_MS_LONG, 0L)) + l >= l2.longValue();
    }

    public final boolean a(long j, String str) {
        Long lK;
        return (TextUtils.isEmpty(str) || (lK = e.k(str)) == null || j >= lK.longValue()) ? false : true;
    }

    public final String a(String str, String str2, boolean z, y5 y5Var) throws OAuthTokenManagerException {
        if (str != null) {
            Log.i(ga.a("OAuthTokenManager"), "Exchange DMS token to OAuth token for package " + str2 + " due to " + y5Var.a(this.f593a));
            try {
                de deVarA = yd.a("OAuthTokenManager", "exchangeDMSCredentialsForOAuthToken");
                k7.b bVar = new k7.b(new v8(t5.a(this.f593a), new qa()), this.f593a, str, str2);
                f7.a aVarC = bVar.c(y5Var);
                Log.i(ga.a("OAuthTokenManager"), "Exchanging DMS token with exchange token endpoint: " + bVar.g().toString());
                deVarA.c();
                aVarC.a();
                Integer num = aVarC.b;
                JSONObject jSONObject = aVarC.f312a;
                Log.i(ga.a("OAuthTokenManager"), "Response received for exchange DMS to OAuth end-point");
                if (!this.h.a(num) && jSONObject != null) {
                    yd.a("exchangeDMSCredentialsForOAuthTokenSuccess", new String[0]);
                    a aVarD = this.h.d(jSONObject);
                    a(str, str2, aVarD);
                    if (z) {
                        return aVarD.c;
                    }
                    return aVarD.f594a;
                }
                Object[] objArr = new Object[1];
                objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response";
                ga.a("OAuthTokenManager", "Error Response: %s", objArr);
                throw a(str, (String) null, this.h.c(jSONObject), num, AuthTokenExchangeType.DMSTokenToOauthTokenExchange);
            } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e) {
                if (e.getAccountRecoverContextBundle() != null) {
                    throw new OAuthTokenManagerException(MAPError.CommonError.CORRUPTED_DATABASE, "MAP Database is corrupted", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP Database is corrupted", new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.InvalidToken, "RecoverAccount", "MAP client side database is corrupted.", null, null), d7.a(e.getAccountRecoverContextBundle()));
                }
                yd.a("exchangeDMSCredentialsForOAuthTokenFailure:IOException", new String[0]);
                yd.b("NetworkError8:OAuthTokenManager", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), 3, e.getMessage());
            } catch (IOException e2) {
                yd.a("exchangeDMSCredentialsForOAuthTokenFailure:IOException", new String[0]);
                yd.b("NetworkError9:OAuthTokenManager", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e2.getMessage()), 3, e2);
            } catch (ParseException e3) {
                yd.a("exchangeDMSCredentialsForOAuthTokenFailure:ParseException", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e3.getMessage()), 5, e3.getMessage());
            } catch (JSONException e4) {
                yd.a("exchangeDMSCredentialsForOAuthTokenFailure:JSONException", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e4.getMessage()), 5, e4.getMessage());
            }
        }
        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given Account is currently not valid", 8, "Given Account is currently not valid");
    }

    public a[] a(String str, String str2, String str3, String str4, String str5, String str6, String str7, y5 y5Var, @NonNull Bundle bundle) throws Throwable {
        de deVarA;
        HttpURLConnection httpURLConnectionA;
        HttpURLConnection httpURLConnection = null;
        try {
            deVarA = yd.a("OAuthTokenManager", "refreshActorToken");
            httpURLConnectionA = this.h.a(str2, str3, str5, str, str4, str6, str7, bundle, y5Var);
        } catch (Throwable th) {
            th = th;
        }
        try {
            int iB = RetryLogic.b(httpURLConnectionA);
            deVarA.c();
            Log.i(ga.a("OAuthTokenManager"), "Response received actor access token exchange");
            JSONObject jSONObjectA = da.a(httpURLConnectionA);
            if (!this.h.a(Integer.valueOf(iB)) && jSONObjectA != null) {
                a[] aVarArrB = this.h.b(jSONObjectA);
                a(str, str4, jSONObjectA);
                yd.b("refreshActorTokenSuccess", new String[0]);
                httpURLConnectionA.disconnect();
                return aVarArrB;
            }
            Object[] objArr = new Object[1];
            objArr[0] = jSONObjectA != null ? jSONObjectA.toString() : "Null Json Response";
            ga.a("OAuthTokenManager", "Error Response: %s", objArr);
            throw a(str, str4, this.h.c(jSONObjectA), Integer.valueOf(iB), AuthTokenExchangeType.OauthRefreshToAccessExchange);
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = httpURLConnectionA;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public final void a(String str, String str2, JSONObject jSONObject) {
        e2.e eVar;
        this.h.getClass();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("actor_info");
            eVar = new e2.e(jSONObject2.getString("actor_sub_type"), jSONObject2.getString("actor_entity_type"), jSONObject2.getString("actor_converted_type"));
        } catch (Exception e) {
            Log.e(ga.a("PandaOAuthExchangeRequestHelper"), "Failed to parse actor info from the response. Just return null.", e);
            eVar = null;
        }
        if (eVar != null) {
            this.c.a(str, str2, AccountConstants.KEY_ACTOR_SUBTYPE, eVar.f293a);
            this.c.a(str, str2, AccountConstants.KEY_ACTOR_ENTITYTYPE, eVar.b);
            this.c.a(str, str2, AccountConstants.KEY_ACTOR_CONVERTEDTYPE, eVar.c);
        }
    }

    public final String a(String str, String str2, y5 y5Var) throws OAuthTokenManagerException {
        if (z9.d(this.f593a, str2)) {
            return null;
        }
        String strD = d(str, str2);
        if (TextUtils.isEmpty(strD)) {
            b(str, str2, y5Var);
            strD = d(str, str2);
        }
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        Log.e(ga.a("OAuthTokenManager"), "Fail to get child device type refresh token!");
        throw new OAuthTokenManagerException(MAPError.CommonError.SERVER_ERROR, "Fail to get child device type refresh token, probably due to child device type registration failed", 1, "Unable to get child device type refresh token");
    }

    public OAuthTokenManagerException a(String str, String str2, AuthEndpointErrorParser.a aVar, Integer num, AuthTokenExchangeType authTokenExchangeType) {
        boolean zBooleanValue;
        String str3 = aVar != null ? String.format("Received Error code %s from the server. Message: %s Detail: %s Index: %s", aVar.f320a.getCode(), aVar.b, aVar.c, aVar.d) : "Invalid error response received from the token exchange endpoint";
        String str4 = authTokenExchangeType.mFailureMetric;
        String[] strArr = new String[1];
        strArr[0] = aVar == null ? "InvalidErrorResponse" : aVar.f320a.name();
        yd.a(str4, strArr);
        if (aVar == null) {
            Log.e(ga.a("OAuthTokenManager"), String.format(Locale.ENGLISH, "Received unrecognized error from the server with status code %d", num));
        } else {
            Log.e(ga.a("OAuthTokenManager"), String.format("Received error code: %s %n Message: %s %n Detail: %s %n Index: %s", aVar.f320a.getCode(), aVar.b, aVar.c, aVar.d));
            ga.a("OAuthTokenManager");
            AuthEndpointErrorParser.AuthErrorType authErrorType = aVar.f320a;
            if (authErrorType == AuthEndpointErrorParser.AuthErrorType.InvalidToken || authErrorType == AuthEndpointErrorParser.AuthErrorType.InvalidValue) {
                if (m.contains(authTokenExchangeType) ? !"com.amazon.imp".equals(this.f593a.getApplicationContext().getPackageName()) : false) {
                    try {
                        this.d.deregisterAccount(str, new s2(null)).get(5L, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        Log.e(ga.a("OAuthTokenManager"), "Exception while waiting for deregistration as the result of an invalid token to complete", e);
                    }
                    return new OAuthTokenManagerException((MAPError) MAPError.CommonError.PARSE_ERROR, String.format("A ParseError occurred: %s", str3), MAPAccountManager.RegistrationError.PARSE_ERROR.value(), str3, aVar, true);
                }
                if (!n.contains(z9.b(this.f593a))) {
                    Log.i(ga.a("OAuthTokenManager"), "This is not enterprise supported device type. Bypassing remote device transfer check.");
                    zBooleanValue = false;
                } else {
                    Log.i(ga.a("OAuthTokenManager"), "Checking hasOngoingRemoteAccountTransfer.");
                    f8 f8VarA = f8.a(this.f593a, "DMS_ATS");
                    long jA = f8VarA.a(a("timestamp_key", str));
                    String strA = a("account_transfer_key", str);
                    this.b.getClass();
                    if (System.currentTimeMillis() - jA < k) {
                        Boolean boolA = f8VarA.a(strA, false);
                        Log.i(ga.a("OAuthTokenManager"), "AccountTransfer status found in sharedPreference is " + boolA);
                        zBooleanValue = boolA.booleanValue();
                    } else {
                        g gVarB = g.b(this.f593a);
                        u8 u8Var = new u8(this, f8VarA, str);
                        Log.i(ga.a("OAuthTokenManager"), "Fetching transferred account credential before triggering account recover bundle");
                        gVarB.getClass();
                        gVarB.g.a((String) null, true, gVarB.f, (p.b) new i(gVarB, u8Var), new y5("RemoteAccountTransfer"));
                        Boolean boolA2 = f8VarA.a(strA, false);
                        Log.i(ga.a("OAuthTokenManager"), "Returning AccountTransfer status from hasOngoingRemoteAccountTransfer is " + boolA2);
                        yd.b("FetchTransferredAccountCredentials:" + (boolA2.booleanValue() ? "HasOngoingRemoteAccountTransfer" : "HasNoOngoingRemoteAccountTransfer"), new String[0]);
                        zBooleanValue = boolA2.booleanValue();
                    }
                }
                if (zBooleanValue) {
                    Log.i(ga.a("OAuthTokenManager"), "Return network error due to mismatched account info between the device and DMS side.");
                    return new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", "The account to getAccessToken with is no longer registered."), 3, "The account to getAccessToken with is no longer registered.");
                }
                return new OAuthTokenManagerException(MAPError.CommonError.PARSE_ERROR, String.format("A ParseError occurred: %s", str3), MAPAccountManager.RegistrationError.PARSE_ERROR.value(), str3, aVar, d7.a().a(str).b(authTokenExchangeType.name() + ":" + aVar.f320a.name()));
            }
            if (authErrorType == AuthEndpointErrorParser.AuthErrorType.ActorNotAssociated) {
                Log.i(ga.a("OAuthTokenManager"), "Received an ActorNotAssociatedError, expire actor tokens and cookies for actor");
                b(str, str2);
                c(str, str2);
                return new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, str3, aVar.f320a.getRegistrationError().value(), str3, aVar);
            }
            if (authErrorType == AuthEndpointErrorParser.AuthErrorType.InvalidActorToken) {
                Log.i(ga.a("OAuthTokenManager"), "Received an InvalidActorTokenError, expire actor tokens for actor");
                c(str, str2);
                return new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, str3, aVar.f320a.getRegistrationError().value(), str3, aVar);
            }
        }
        return new OAuthTokenManagerException(MAPError.CommonError.PARSE_ERROR, String.format("A ParseError occurred: %s", str3), MAPAccountManager.RegistrationError.PARSE_ERROR.value(), str3, aVar);
    }

    public final void a(String str, String str2, int i, String str3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jConvert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + jCurrentTimeMillis;
        HashMap map = new HashMap();
        map.put(TokenKeys.getAccessTokenKeyForPackage(null), str3);
        map.put(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(jConvert));
        map.put(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(jCurrentTimeMillis));
        this.c.b(str, str2, map);
    }

    public static String a(String str, String str2) {
        return str + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str2;
    }

    public final void a(String str, String str2, a aVar) {
        if (this.d.isAccountRegistered(str) || xa.a()) {
            int i = aVar.b;
            String str3 = aVar.c;
            String str4 = aVar.f594a;
            synchronized (this) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long jConvert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + jCurrentTimeMillis;
                HashMap map = new HashMap();
                if (!TextUtils.isEmpty(str3)) {
                    map.put(e.a(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), str3);
                }
                map.put(TokenKeys.getAccessTokenKeyForPackage(str2), str4);
                map.put(e.a(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(jConvert));
                map.put(e.a(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(jCurrentTimeMillis));
                this.c.b(str, map);
            }
            x8 x8Var = this.f;
            if (qe.f(x8Var.b.f382a)) {
                return;
            }
            x8Var.f660a.d(str, "3PLastRegistrationCheckTimeKey", Long.toString(System.currentTimeMillis()));
        }
    }

    public final String a(String str, String str2, String str3, a[] aVarArr) throws ParseException {
        String str4 = null;
        if (this.d.isAccountRegistered(str) || xa.a()) {
            for (a aVar : aVarArr) {
                String str5 = aVar.e;
                if (!TextUtils.isEmpty(str5)) {
                    if (str5.equals(z9.c(this.f593a, str3))) {
                        a(str, str2, str3, aVar);
                        str4 = aVar.f594a;
                    } else if (str5.equals(z9.b(this.f593a))) {
                        a(str, str2, str3, aVar);
                    } else {
                        Log.w(ga.a("OAuthTokenManager"), "The device type is not supported for the package , ignoring...");
                        yd.b("UNSUPPORTED_DEVICE_TYPE_FROM_SERVER", new String[0]);
                    }
                }
            }
            x8 x8Var = this.f;
            if (!qe.f(x8Var.b.f382a)) {
                x8Var.f660a.d(str, "3PLastRegistrationCheckTimeKey", Long.toString(System.currentTimeMillis()));
            }
        }
        if (!TextUtils.isEmpty(str4)) {
            return str4;
        }
        ga.a("OAuthTokenManager");
        throw new ParseException("Can not get actor token from service response", 0);
    }

    public final void a(String str, String str2, String str3, a aVar) throws ParseException {
        synchronized (this.j) {
            String str4 = aVar.c;
            String str5 = aVar.d;
            String str6 = aVar.f594a;
            if (!TextUtils.isEmpty(str6)) {
                int i = aVar.b;
                String str7 = aVar.e;
                long jCurrentTimeMillis = System.currentTimeMillis();
                long jConvert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + jCurrentTimeMillis;
                HashMap map = new HashMap();
                if (z9.b(this.f593a).equals(str7)) {
                    str3 = null;
                }
                if (!TextUtils.isEmpty(str4)) {
                    map.put(e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), str4);
                }
                if (!TextUtils.isEmpty(str5)) {
                    map.put(e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN), str5);
                }
                map.put(e.a(str3, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"), str6);
                map.put(e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(jConvert));
                map.put(e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(jCurrentTimeMillis));
                this.c.a(str, str2, map);
            } else {
                ga.a("OAuthTokenManager");
                throw new ParseException("No access token received for package: " + str3, 0);
            }
        }
    }

    public final void a(String str, String str2, String str3, int i, String str4) {
        synchronized (this.j) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jConvert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + jCurrentTimeMillis;
            HashMap map = new HashMap();
            map.put(e.a((String) null, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"), str4);
            map.put(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(jConvert));
            map.put(e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(jCurrentTimeMillis));
            this.c.a(str, str2, str3, map);
        }
    }
}
