package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.a7;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.c8;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.dependency.PandaServiceAccessor;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.fa;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.o5;
import com.amazon.identity.auth.device.q9;
import com.amazon.identity.auth.device.qa;
import com.amazon.identity.auth.device.s9;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.w5;
import com.amazon.identity.auth.device.wa;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z9;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AtzTokenManager {
    public static final String e = "com.amazon.identity.auth.device.token.AtzTokenManager";
    public static final long f = wa.c(1, TimeUnit.MILLISECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w5 f588a;
    public final t5 b;
    public final PandaServiceAccessor c;
    public final u7 d;

    public AtzTokenManager(Context context) {
        this(context, new PandaServiceAccessor(context), new c8(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(java.lang.String r8, org.json.JSONObject r9, android.os.Bundle r10, com.amazon.identity.auth.device.fa r11) throws org.json.JSONException {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            if (r9 == 0) goto Lab
            java.lang.String r2 = r11.b
            java.lang.String r3 = "com.amazon.dcp.sso.token.oauth.amazon.atz_token.refreshed_at"
            java.lang.String r2 = com.amazon.identity.auth.device.e.a(r2, r3)
            com.amazon.identity.auth.device.u7 r3 = r7.d
            java.lang.String r2 = r3.c(r8, r2)
            if (r2 != 0) goto L22
            java.lang.String r8 = com.amazon.identity.auth.device.token.AtzTokenManager.e
            java.lang.String r8 = com.amazon.identity.auth.device.ga.a(r8)
            java.lang.String r11 = "No previous token refresh time found. Possible case of no cached token. Refreshing..."
            android.util.Log.i(r8, r11)
        L1f:
            r8 = r1
            goto L99
        L22:
            com.amazon.identity.auth.device.w5 r3 = r7.f588a
            r3.getClass()
            long r3 = java.lang.System.currentTimeMillis()
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L32
            goto L42
        L32:
            java.lang.Long r2 = com.amazon.identity.auth.device.e.k(r2)
            if (r2 == 0) goto L42
            long r5 = r2.longValue()
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 >= 0) goto L42
            r2 = r1
            goto L43
        L42:
            r2 = r0
        L43:
            if (r2 == 0) goto L51
            java.lang.String r8 = com.amazon.identity.auth.device.token.AtzTokenManager.e
            java.lang.String r8 = com.amazon.identity.auth.device.ga.a(r8)
            java.lang.String r11 = "Clock skew detected. Refreshing..."
            android.util.Log.i(r8, r11)
            goto L1f
        L51:
            com.amazon.identity.auth.device.u7 r2 = r7.d
            java.lang.String r11 = r11.b
            java.lang.String r5 = "com.amazon.dcp.sso.token.oauth.amazon.atz_token.expires_at"
            java.lang.String r11 = com.amazon.identity.auth.device.e.a(r11, r5)
            java.lang.String r8 = r2.c(r8, r11)
            java.lang.Long r8 = com.amazon.identity.auth.device.e.k(r8)
            if (r8 == 0) goto L98
            java.lang.Long r11 = java.lang.Long.valueOf(r3)
            long r2 = r11.longValue()
            java.util.concurrent.TimeUnit r11 = java.util.concurrent.TimeUnit.MILLISECONDS
            r4 = 15
            long r4 = com.amazon.identity.auth.device.wa.c(r4, r11)
            java.lang.String r11 = "com.amazon.identity.auth.device.api.TokenKeys.Options.OAuthAccessTokenTTLInMilliSec"
            long r4 = r10.getLong(r11, r4)
            long r2 = r2 + r4
            long r4 = com.amazon.identity.auth.device.token.AtzTokenManager.f
            long r2 = r2 + r4
            long r4 = r8.longValue()
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 < 0) goto L89
            r8 = r1
            goto L8a
        L89:
            r8 = r0
        L8a:
            if (r8 == 0) goto L98
            java.lang.String r8 = com.amazon.identity.auth.device.token.AtzTokenManager.e
            java.lang.String r8 = com.amazon.identity.auth.device.ga.a(r8)
            java.lang.String r11 = "Atz access token near or past expiry. Refreshing..."
            android.util.Log.i(r8, r11)
            goto L1f
        L98:
            r8 = r0
        L99:
            if (r8 != 0) goto Lab
            java.lang.String r8 = "client-id"
            java.lang.String r10 = r10.getString(r8)
            java.lang.String r8 = r9.getString(r8)
            boolean r8 = android.text.TextUtils.equals(r8, r10)
            if (r8 != 0) goto Lac
        Lab:
            r0 = r1
        Lac:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.AtzTokenManager.a(java.lang.String, org.json.JSONObject, android.os.Bundle, com.amazon.identity.auth.device.fa):boolean");
    }

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
    public final String b(String str, String str2, fa faVar, Bundle bundle, y5 y5Var) throws Throwable {
        boolean z;
        HttpURLConnection httpURLConnection;
        String strC = this.d.c(str, faVar.f315a);
        JSONObject jSONObject = strC != null ? new JSONObject(strC) : null;
        if (bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH)) {
            Log.i(ga.a(e), "Force refresh the ATZA token.");
            y5Var.b("ATZ_FORCE_REFRESH_OAUTH");
            z = true;
        } else {
            z = false;
        }
        if (!z && !a(str, jSONObject, bundle, faVar)) {
            return jSONObject.getString("token");
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList(TokenKeys.KEY_LWA_REQUESTED_SCOPES);
        t5 t5Var = this.b;
        String string = bundle.getString(TokenKeys.KEY_LWA_APPLICATION_ID);
        String string2 = bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID);
        t5 t5VarA = t5.a(t5Var);
        o5 o5Var = (o5) t5VarA.getSystemService("dcp_device_info");
        JSONArray jSONArray = new JSONArray();
        if (e.a(stringArrayList)) {
            jSONArray.put("profile:user_id");
        } else {
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        }
        PandaServiceAccessor pandaServiceAccessor = this.c;
        pandaServiceAccessor.getClass();
        try {
            try {
                URL urlA = EnvironmentUtils.c.a(q9.a(t5VarA, str), "/auth/token");
                qa qaVar = pandaServiceAccessor.f286a;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("app_name", t5VarA.getPackageName());
                jSONObject2.put("app_version", s9.b());
                jSONObject2.put("device_metadata", a7.a(z9.c(t5VarA, t5VarA.getPackageName()), o5Var.a(), y5Var));
                jSONObject2.put("source_token_type", "refresh_token");
                jSONObject2.put("source_token", str2);
                jSONObject2.put("requested_token_type", "scope_access_token");
                jSONObject2.put("scope_list", jSONArray);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(AccountConstants.KEY_CLIENT_ID, string2);
                jSONObject3.put("client_secret", string);
                jSONObject2.put("client_auth_data", jSONObject3);
                HttpURLConnection httpURLConnectionA = qaVar.a((Context) t5VarA, urlA, jSONObject2, false, (List<MAPCookie>) null, str, t5VarA.getPackageName(), y5Var);
                try {
                    int iB = RetryLogic.b(httpURLConnectionA);
                    Log.i(ga.a(PandaServiceAccessor.d), String.format("Call to %s with request-id %s ended with status %d", urlA, httpURLConnectionA.getHeaderField("X-Amzn-RequestId"), Integer.valueOf(iB)));
                    OAuthTokenManager.a aVarB = pandaServiceAccessor.b(new PandaServiceAccessor.a(iB, da.a(httpURLConnectionA), httpURLConnectionA.getHeaderFields()));
                    httpURLConnectionA.disconnect();
                    yd.a("exchangeAtnrForAtzaTokenSuccess", new String[0]);
                    String string3 = bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID);
                    String str3 = faVar.b;
                    int i = aVarB.b;
                    String str4 = aVarB.c;
                    String str5 = aVarB.f594a;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long jConvert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + jCurrentTimeMillis;
                    String strA = e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
                    String atzTokenKeyForPackage = TokenKeys.getAtzTokenKeyForPackage(str3);
                    String strA2 = e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_EXPIRES_AT);
                    String strA3 = e.a(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_REFRESHED_AT);
                    HashMap map = new HashMap();
                    if (!TextUtils.isEmpty(str4)) {
                        map.put(strA, str4);
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("token", str5);
                    jSONObject4.put(TokenKeys.KEY_LWA_CLIENT_ID, string3);
                    map.put(atzTokenKeyForPackage, jSONObject4.toString());
                    map.put(strA2, Long.toString(jConvert));
                    map.put(strA3, Long.toString(jCurrentTimeMillis));
                    this.d.b(str, map);
                    return aVarB.f594a;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnectionA;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException e2) {
                throw new RuntimeException("Should never occur, hardcoded constant.", e2);
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
    }

    public AtzTokenManager(Context context, PandaServiceAccessor pandaServiceAccessor, u7 u7Var) {
        t5 t5VarA = t5.a(context);
        this.b = t5VarA;
        this.c = pandaServiceAccessor;
        this.d = u7Var;
        this.f588a = (w5) t5VarA.getSystemService("dcp_system");
    }

    /* JADX INFO: compiled from: DCP */
    public static final class AtzTokenManagerException extends Exception {
        private static final long serialVersionUID = -7354549861193710767L;
        private final MAPError mError;
        private final String mErrorMessage;
        private final int mLegacyErrorCode;
        private final String mLegacyErrorMessage;

        public AtzTokenManagerException(MAPError mAPError, String str, int i, String str2) {
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

        public AtzTokenManagerException(MAPError mAPError, String str, int i, Throwable th) {
            super(th.getMessage(), th);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = th.getMessage();
            this.mError = mAPError;
            this.mErrorMessage = str;
        }
    }

    public String a(String str, String str2, fa faVar, Bundle bundle, y5 y5Var) throws AtzTokenManagerException {
        if (!TextUtils.isEmpty(str2)) {
            if (TextUtils.equals("com.amazon.dcp.sso.token.oauth.atz.access_token", faVar.c)) {
                if (!TextUtils.isEmpty(bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID))) {
                    try {
                        return b(str, str2, faVar, bundle, y5Var);
                    } catch (PandaServiceAccessor.PandaServiceException e2) {
                        yd.a("exchangeAtnrForAtzaTokenFailure:PandaServiceException", new String[0]);
                        throw new AtzTokenManagerException(e2.a(), e2.b(), e2.c(), e2.d());
                    } catch (IOException e3) {
                        yd.a("exchangeAtnrForAtzaTokenFailure:IOException", new String[0]);
                        yd.b("NetworkError12:AtzTokenManager", new String[0]);
                        throw new AtzTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("Network error occurred: %s", e3.getMessage()), 3, e3.getMessage());
                    } catch (UnsupportedOperationException e4) {
                        yd.a("exchangeAtnrForAtzaTokenFailure:UnsupportedOperationException", new String[0]);
                        throw new AtzTokenManagerException(MAPError.AccountError.CUSTOMER_NOT_FOUND, MAPError.AccountError.CUSTOMER_NOT_FOUND.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), e4);
                    } catch (ParseException e5) {
                        yd.a("exchangeAtnrForAtzaTokenFailure:ParseException", new String[0]);
                        throw new AtzTokenManagerException(MAPError.CommonError.PARSE_ERROR, String.format("ParseException occurred: %s", e5.getMessage()), 5, e5.getMessage());
                    } catch (JSONException e6) {
                        yd.a("exchangeAtnrForAtzaTokenFailure:JSONException", new String[0]);
                        throw new AtzTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("JSONException occurred: %s", e6.getMessage()), 5, e6.getMessage());
                    }
                }
                throw new AtzTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Client id is missing from the passed options bundle", 7, "Client id is missing from options passed.");
            }
            String str3 = String.format("Token key %s is not a valid key", faVar.f315a);
            throw new AtzTokenManagerException(MAPError.CommonError.BAD_REQUEST, str3, 7, str3);
        }
        throw new AtzTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given AtnToken is not valid", 8, "Given AtnToken is not valid");
    }
}
