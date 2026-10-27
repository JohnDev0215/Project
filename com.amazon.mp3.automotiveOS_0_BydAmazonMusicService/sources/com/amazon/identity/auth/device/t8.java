package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.ya;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t8 {
    public static final long i = wa.c(1, TimeUnit.MILLISECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f579a;
    public w5 b;
    public final qa c;
    public final OAuthTokenManager d;
    public final MAPAccountManager e;
    public final s7 f;
    public final j9 g;
    public u7 h;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<MAPCookie> f580a;
        public final boolean b;

        public a(List<MAPCookie> list, boolean z) {
            this.f580a = list;
            this.b = z;
        }

        public List<MAPCookie> a() {
            if (e.a(this.f580a)) {
                Log.i(ga.a("MAPCookieManager"), "Cached cookies are empty");
            }
            return this.f580a;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<MAPCookie> f581a;
        public final List<MAPCookie> b;
        public final String c;

        public b(List<MAPCookie> list, List<MAPCookie> list2, String str) {
            this.f581a = list;
            this.b = list2;
            this.c = str;
        }
    }

    public t8(Context context) {
        t5 t5VarA = t5.a(context);
        this.f579a = t5VarA;
        this.b = (w5) t5VarA.getSystemService("dcp_system");
        this.d = new OAuthTokenManager(t5VarA);
        this.c = new qa();
        this.e = new MAPAccountManager(t5VarA);
        this.f = qe.k(((i5) t5VarA.getSystemService("sso_platform")).f382a) ? new h8(t5VarA) : new b8(t5.a(t5VarA).a());
        this.h = t5VarA.a();
        this.g = j9.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[PHI: r5
  0x002d: PHI (r5v3 java.util.List<com.amazon.identity.auth.device.token.MAPCookie>) = 
  (r5v2 java.util.List<com.amazon.identity.auth.device.token.MAPCookie>)
  (r5v5 java.util.List<com.amazon.identity.auth.device.token.MAPCookie>)
 binds: [B:11:0x002b, B:7:0x0018] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.identity.auth.device.t8.a a(java.lang.String r5, java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = "MAPCookieManager"
            r2 = 0
            if (r5 == 0) goto L22
            com.amazon.identity.auth.device.ga.a(r1)
            com.amazon.identity.auth.device.s7 r3 = r4.f
            java.util.List r5 = r3.a(r5, r6, r7)
            boolean r3 = com.amazon.identity.auth.device.e.a(r5)
            if (r3 != 0) goto L1b
            com.amazon.identity.auth.device.ga.a(r1)
            if (r8 != 0) goto L2e
            goto L2d
        L1b:
            com.amazon.identity.auth.device.s7 r5 = r4.f
            java.util.List r5 = r5.a(r0, r6, r7)
            goto L2e
        L22:
            com.amazon.identity.auth.device.ga.a(r1)
            com.amazon.identity.auth.device.s7 r5 = r4.f
            java.util.List r5 = r5.a(r0, r6, r7)
            if (r8 != 0) goto L2e
        L2d:
            r2 = 1
        L2e:
            com.amazon.identity.auth.device.t8$a r6 = new com.amazon.identity.auth.device.t8$a
            r6.<init>(r5, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.t8.a(java.lang.String, java.lang.String, java.lang.String, boolean):com.amazon.identity.auth.device.t8$a");
    }

    public final void b(List<MAPCookie> list, String str, boolean z) throws MAPCallbackErrorException {
        if (z && TextUtils.isEmpty(str)) {
            Log.e(ga.a("MAPCookieManager"), "Expected url, but did not receive one from getActorCookies request. Cannot proceed.");
            yd.b("fetchActorCookiesFromServerFailure:MissingExpectedResponseUrl", new String[0]);
            throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.SERVER_ERROR, "Expected url, but did not receive one from getActorCookies request. Cannot proceed.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected url, but did not receive one from getActorCookies request. Cannot proceed."));
        }
        if (z || !e.a(list)) {
            return;
        }
        Log.e(ga.a("MAPCookieManager"), "Expected cookies, but did not receive them from getActorCookies request");
        yd.b("fetchActorCookiesFromServerFailure:MissingExpectedCookies", new String[0]);
        throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.SERVER_ERROR, "Expected cookies, but did not receive them from getActorCookies request", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected cookies, but did not receive them from getActorCookies request"));
    }

    public final boolean c(List<MAPCookie> list) {
        if (list != null && list.size() > 0) {
            for (MAPCookie mAPCookie : list) {
                Date dateB = mAPCookie.b();
                this.b.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (dateB == null) {
                    Log.w(ga.a("MAPCookieManager"), String.format("Cookie: %s has null expiry date.", mAPCookie.c()));
                    return true;
                }
                if (jCurrentTimeMillis + i >= dateB.getTime()) {
                    Log.i(ga.a("MAPCookieManager"), String.format("Cookie: %s near expiry, refreshing", mAPCookie.c()));
                    return true;
                }
            }
        }
        return false;
    }

    public Bundle a(String str, String str2, Bundle bundle, y5 y5Var) throws Throwable {
        int i2;
        boolean z;
        String strD;
        String str3;
        Object obj;
        String str4;
        String strA;
        j9 j9Var;
        if (!TextUtils.isEmpty(str) && !this.e.isAccountRegistered(str)) {
            Log.i(ga.a("MAPCookieManager"), "Account not registered");
            throw new MAPCallbackErrorException(a9.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, "Given account is not registered or directedId is empty", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given Account is currently not registered."));
        }
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        String string = bundle2.getString(CookieKeys.Options.KEY_ASSOC_HANDLE);
        ga.a("MAPCookieManager");
        if (bundle2.getBoolean("com.amazon.identity.auth.device.internal.cookiekeys.options.ignorefresh", false)) {
            List<MAPCookie> listA = this.f.a(str, str2, string);
            if (e.a(listA)) {
                listA = new ArrayList<>();
            }
            return a(listA, str, str2, (String) null);
        }
        boolean zContainsKey = bundle2.containsKey(CookieKeys.Options.KEY_SIGN_IN_URL);
        f8 f8Var = new f8(this.f579a, "token_storage", 0);
        String str5 = CookieKeys.KEY_INVALIDATE_COOKIES + this.f579a.getPackageName() + str + str2;
        boolean zBooleanValue = f8Var.a(str5, false).booleanValue();
        ga.a("MAPCookieManager");
        boolean z2 = bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false) || zContainsKey || zBooleanValue;
        String string2 = bundle2.getString(CookieKeys.Options.KEY_SIGN_IN_URL);
        ga.a("MAPCookieManager");
        a aVarA = a(str, str2, string, z2);
        List<MAPCookie> listA2 = aVarA.a();
        boolean z3 = aVarA.b;
        ga.a("MAPCookieManager");
        boolean zC = c(listA2);
        if (!e.a(listA2) && !zC && z3) {
            ga.a("MAPCookieManager");
            return a(listA2, str, str2, (String) null);
        }
        Log.i(ga.a("MAPCookieManager"), "Fetching cookies from server due to " + y5Var.a(this.f579a) + ", for domain " + str2 + " and associate handle " + string + " with options forceRefresh=" + z2 + " and with isCookiesInvalid=" + zBooleanValue + " haveCookiesExpired=" + zC + " returnCachedCookies=" + z3);
        if (bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false)) {
            yd.a("getCookiesFromServerWithForceRefresh", new String[0]);
        }
        if (zBooleanValue) {
            yd.a("getCookiesFromServerWithCookiesInvalidated", new String[0]);
        }
        try {
            if (TextUtils.isEmpty(str)) {
                z = true;
                strD = null;
            } else {
                OAuthTokenManager oAuthTokenManager = this.d;
                strD = oAuthTokenManager.d(str, null);
                if (strD != null) {
                    z = true;
                } else {
                    z = true;
                    try {
                        strD = oAuthTokenManager.a(str, (String) null, true, y5Var);
                    } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                        e = e;
                        i2 = 1;
                        Object[] objArr = new Object[i2];
                        objArr[0] = e.b();
                        Log.e(ga.a("MAPCookieManager"), String.format("Get error when fetchAuthCookies: %s", objArr));
                        yd.a("fetchCookiesFromServerFailure:OAuthTokenManagerException", new String[0]);
                        throw new MAPCallbackErrorException(a9.a(e.e(), e.f(), e));
                    }
                }
            }
            String str6 = strD;
            List<MAPCookie> list = listA2;
            boolean z4 = z2;
            Bundle bundle3 = bundle2;
            b bVarA = a(bundle2, str, str6, null, null, str2, string, string2, list, y5Var);
            List<MAPCookie> listA3 = bVarA.f581a;
            String str7 = bVarA.c;
            a(listA3, str7, zContainsKey);
            yd.a("fetchCookiesFromServerSuccess", new String[0]);
            if (e.a(listA3)) {
                str3 = str7;
            } else {
                ga.a("MAPCookieManager");
                j9 j9Var2 = this.g;
                try {
                    synchronized (j9Var2) {
                        try {
                            if (TextUtils.isEmpty(str)) {
                                ga.a("MAPCookieManager");
                                a(str, str2, string, z4, listA3);
                            } else {
                                OAuthTokenManager oAuthTokenManager2 = this.d;
                                String strD2 = oAuthTokenManager2.d(str, null);
                                if (strD2 != null) {
                                    str4 = str6;
                                    strA = strD2;
                                } else {
                                    str4 = str6;
                                    strA = oAuthTokenManager2.a(str, (String) null, true, y5Var);
                                }
                                if (TextUtils.equals(strA, str4)) {
                                    ga.a("MAPCookieManager");
                                    a(str, str2, string, z4, listA3);
                                } else {
                                    ga.a("MAPCookieManager");
                                    yd.b("MAP_CID_ATNR_Changed_CookiesExchange", new String[0]);
                                    a aVarA2 = a(str, str2, string, false);
                                    if (aVarA2.b) {
                                        yd.b("MAP_CID_ATNR_Changed_CookiesExchange_ReturnCached", new String[0]);
                                        listA3 = aVarA2.a();
                                    } else {
                                        yd.b("MAP_CID_ATNR_Changed_CookiesExchange_Refresh", new String[0]);
                                        j9Var = j9Var2;
                                        str3 = str7;
                                        b bVarA2 = a(bundle3, str, strA, null, null, str2, string, string2, aVarA2.a(), y5Var);
                                        List<MAPCookie> list2 = bVarA2.f581a;
                                        a(list2, bVarA2.c, zContainsKey);
                                        listA3 = list2;
                                    }
                                }
                            }
                            j9Var = j9Var2;
                            str3 = str7;
                        } catch (Throwable th) {
                            th = th;
                            obj = j9Var2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obj = list;
                }
            }
            f8Var.c(str5);
            return a(listA3, str, str2, str3);
        } catch (OAuthTokenManager.OAuthTokenManagerException e2) {
            e = e2;
            i2 = 1;
        }
    }

    public a b(String str, String str2, String str3, boolean z) throws MAPCallbackErrorException {
        if (!TextUtils.isEmpty(str)) {
            List<MAPCookie> listA = this.f.a(str, str2, str3);
            boolean z2 = false;
            if (z) {
                return new a(listA, false);
            }
            if (!e.a(listA) && !c(listA)) {
                z2 = true;
            }
            return new a(listA, z2);
        }
        throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.BAD_REQUEST, "directId in getValidCookieCacheResponseFromDataStore is null!", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "directId in getValidCookieCacheResponseFromDataStore is null!"));
    }

    public static void b(List<MAPCookie> list) {
        if (e.a(list)) {
            return;
        }
        for (MAPCookie mAPCookie : list) {
            if (mAPCookie.c().equals("sid")) {
                ga.a("MAPCookieManager");
                list.remove(mAPCookie);
                return;
            }
        }
    }

    public final List<MAPCookie> b(String str, JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String strTrim = itKeys.next().trim();
            JSONArray jSONArray = jSONObject.getJSONArray(strTrim);
            if (jSONArray != null) {
                arrayList.addAll(a(str, jSONArray, strTrim));
            }
        }
        return arrayList;
    }

    public final String b(Bundle bundle, String str, String str2) {
        String strE;
        String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
        if (!TextUtils.isEmpty(string)) {
            Log.i(ga.a("MAPCookieManager"), "Using explicitly passed endpoint for cookie exchange : " + string);
            return string;
        }
        if (TextUtils.isEmpty(str)) {
            strE = EnvironmentUtils.c.e(EnvironmentUtils.c.d(str2));
        } else {
            String strD = EnvironmentUtils.c.d(str2);
            if (TextUtils.isEmpty(strD)) {
                strE = q9.a(this.f579a, str);
            } else {
                strE = EnvironmentUtils.c.e(strD);
            }
        }
        Log.i(ga.a("MAPCookieManager"), String.format("Cookies exchange panda host: %s", strE));
        return strE;
    }

    public ya.b a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) throws MAPCallbackErrorException {
        ya.b bVar = new ya.b();
        bVar.a("requested_token_type", "auth_cookies");
        bVar.a("app_name", this.f579a.getPackageName());
        bVar.a("app_version", s9.b());
        boolean z = ha.f;
        bVar.a("di.sdk.version", "20220722N");
        bVar.a("domain", str);
        Log.i(ga.a("MAPCookieManager"), String.format("Use associate handle %s to exchange cookies", str7));
        if (!TextUtils.isEmpty(str7)) {
            bVar.a("openid.assoc_handle", str7);
        }
        if (TextUtils.isEmpty(str4)) {
            if (!TextUtils.isEmpty(str2)) {
                Log.i(ga.a("MAPCookieManager"), "Requesting all cookies");
                bVar.a("source_token_type", "refresh_token");
                bVar.a("source_token", str3);
            } else {
                Log.i(ga.a("MAPCookieManager"), "Requesting just non-auth cookies");
            }
        } else {
            a(str2, str4);
            bVar.a("source_token_type", "refresh_token");
            bVar.a("source_token", str3);
            bVar.a("actor_refresh_token", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            bVar.a(ImagesContract.URL, str6);
        }
        if (!TextUtils.isEmpty(str8)) {
            bVar.a("calling_package_name", str8);
        }
        return bVar;
    }

    public final void a(List<MAPCookie> list, String str, boolean z) throws MAPCallbackErrorException {
        if (z && TextUtils.isEmpty(str)) {
            Log.e(ga.a("MAPCookieManager"), "Expected url, but did not receive one from cookies request. Cannot proceed.");
            yd.a("fetchCookiesFromServerFailure:MissingExpectedResponseUrl", new String[0]);
            throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.INVALID_RESPONSE, "Expected a URL, but did not receive one from the getCookies request", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected url, but did not receive one from getCookies request"));
        }
        if (z || !e.a(list)) {
            return;
        }
        Log.e(ga.a("MAPCookieManager"), "Expected cookies, but did not receive them from getCookies request");
        yd.a("fetchCookiesFromServerFailure:MissingExpectedCookies", new String[0]);
        throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.INVALID_RESPONSE, "Expected cookies, but did not receive them from the getCookies request", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected cookies, but did not receive them from getCookies request"));
    }

    public Bundle a(String str, String str2, String str3, Bundle bundle, y5 y5Var) throws Throwable {
        String str4;
        String str5;
        Object obj;
        boolean z;
        String strA;
        String str6;
        j9 j9Var;
        List<MAPCookie> listA;
        List<MAPCookie> listA2;
        a(str, str2);
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        String string = bundle2.getString(CookieKeys.Options.KEY_ASSOC_HANDLE);
        ga.a("MAPCookieManager");
        boolean zContainsKey = bundle2.containsKey(CookieKeys.Options.KEY_SIGN_IN_URL);
        f8 f8Var = new f8(this.f579a, "token_storage", 0);
        String str7 = CookieKeys.KEY_INVALIDATE_COOKIES + this.f579a.getPackageName() + str + str3 + str2;
        boolean zBooleanValue = f8Var.a(str7, false).booleanValue();
        ga.a("MAPCookieManager");
        boolean z2 = bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false) || zContainsKey || zBooleanValue;
        String string2 = bundle2.getString(CookieKeys.Options.KEY_SIGN_IN_URL);
        ga.a("MAPCookieManager");
        a aVarB = b(str, str3, string, z2);
        List<MAPCookie> listA3 = aVarB.a();
        boolean z3 = z2;
        a aVarA = a(str, str2, str3, string, z3);
        List<MAPCookie> listA4 = aVarA.a();
        boolean z4 = aVarB.b && aVarA.b;
        Log.i(ga.a("MAPCookieManager"), "areBothCookiesValid from cache responses: " + z4);
        if (z4) {
            List<MAPCookie> listA5 = a(listA3, listA4);
            ga.a("MAPCookieManager");
            return a(listA5, str, str3, (String) null);
        }
        Log.i(ga.a("MAPCookieManager"), "Fetching actor cookies from server due to " + y5Var.a(this.f579a) + ", for domain " + str3 + " and associate handle " + string + " with options forceRefresh=" + z3 + " and with isCookiesInvalid=" + zBooleanValue + " , should return from cache: " + z4);
        if (bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false)) {
            yd.b("getActorCookiesFromServerWithForceRefresh", new String[0]);
        }
        if (zBooleanValue) {
            yd.b("getActorCookiesFromServerWithCookiesInvalidated", new String[0]);
        }
        List<MAPCookie> listA6 = a(listA3, listA4, str3, string);
        try {
            OAuthTokenManager oAuthTokenManager = this.d;
            String strD = oAuthTokenManager.d(str, null);
            String strA2 = strD != null ? strD : oAuthTokenManager.a(str, (String) null, true, y5Var);
            String strA3 = this.d.a(str, str2, (String) null, y5Var);
            String str8 = strA2;
            Bundle bundle3 = bundle2;
            b bVarA = a(bundle2, str, strA2, str2, strA3, str3, string, string2, listA6, y5Var);
            List<MAPCookie> list = bVarA.f581a;
            List<MAPCookie> list2 = bVarA.b;
            String str9 = bVarA.c;
            b(list2, str9, zContainsKey);
            yd.b("fetchActorCookiesFromServerSuccess", new String[0]);
            if (e.a(list2) || e.a(list)) {
                str4 = str3;
                str5 = str9;
                if (!zContainsKey) {
                    throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.PARSE_ERROR, "Account cookies or actor cookies from server is empty.", MAPAccountManager.RegistrationError.PARSE_ERROR.value(), "Account cookies or actor cookies from server is empty."));
                }
            } else {
                ga.a("MAPCookieManager");
                j9 j9Var2 = this.g;
                try {
                    synchronized (j9Var2) {
                        try {
                            String strA4 = this.d.a(str, str2, (String) null, y5Var);
                            OAuthTokenManager oAuthTokenManager2 = this.d;
                            String strD2 = oAuthTokenManager2.d(str, null);
                            if (strD2 != null) {
                                str6 = strA3;
                                strA = strD2;
                                z = true;
                            } else {
                                z = true;
                                strA = oAuthTokenManager2.a(str, (String) null, true, y5Var);
                                str6 = strA3;
                            }
                            if (TextUtils.equals(str6, strA4) && TextUtils.equals(str8, strA)) {
                                Log.i(ga.a("MAPCookieManager"), "Actor or account refresh token is not changed, store it.");
                                a(str, str3, string, z3, list);
                                this.f.a(str, str2, str3, string, list2);
                                str4 = str3;
                                j9Var = j9Var2;
                                str5 = str9;
                            } else {
                                Log.i(ga.a("MAPCookieManager"), "Actor or account refresh token has been changed, try using the cached cookies");
                                yd.b("MAP_CID_PID_ATNR_Changed_CookiesExchange", new String[0]);
                                a aVarB2 = b(str, str3, string, false);
                                String str10 = strA;
                                a aVarA2 = a(str, str2, str3, string, false);
                                if (aVarB2.b && aVarA2.b) {
                                    yd.b("MAP_CID_PID_ATNR_Changed_CookiesExchange_ReturnCached", new String[0]);
                                    Log.i(ga.a("MAPCookieManager"), "Returning cached cookies refreshed by other threads");
                                    listA = aVarB2.a();
                                    listA2 = aVarA2.a();
                                    str4 = str3;
                                    j9Var = j9Var2;
                                    str5 = str9;
                                } else {
                                    yd.b("MAP_CID_PID_ATNR_Changed_CookiesExchange_Refresh", new String[0]);
                                    Log.i(ga.a("MAPCookieManager"), "Cached cookies is invalid, refresh it.");
                                    str4 = str3;
                                    j9Var = j9Var2;
                                    str5 = str9;
                                    b bVarA2 = a(bundle3, str, str10, str2, strA4, str3, string, string2, a(aVarB2.a(), aVarA2.a(), str3, string), y5Var);
                                    b(bVarA2.b, bVarA2.c, zContainsKey);
                                    listA = bVarA2.f581a;
                                    listA2 = bVarA2.b;
                                }
                                list2 = listA2;
                                list = listA;
                            }
                        } catch (Throwable th) {
                            th = th;
                            obj = j9Var2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obj = string2;
                }
            }
            f8Var.c(str7);
            return a(a(list, list2), str, str4, str5);
        } catch (OAuthTokenManager.OAuthTokenManagerException e) {
            Log.e(ga.a("MAPCookieManager"), String.format("Get error when fetchAuthCookies: %s", e.b()));
            yd.a("fetchCookiesFromServerFailure:OAuthTokenManagerException", new String[0]);
            throw new MAPCallbackErrorException(a9.a(e.e(), e.f(), e));
        }
    }

    public final List<MAPCookie> a(List<MAPCookie> list, List<MAPCookie> list2, String str, String str2) throws MAPCallbackErrorException {
        if (e.a(list) || e.a(list2)) {
            return !e.a(list) ? list : this.f.a(null, str, str2);
        }
        return a(list, list2);
    }

    public final void a(String str, String str2, String str3, boolean z, List<MAPCookie> list) {
        if (TextUtils.isEmpty(str)) {
            this.f.a((String) null, str2, str3, list);
            return;
        }
        String strA = a(list);
        if (!TextUtils.isEmpty(strA)) {
            b(list);
            if (!strA.equals(this.h.c(str, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN))) {
                ga.a("MAPCookieManager");
                this.h.c(str, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN, strA);
            }
        }
        this.f.a(str, str2, str3, list);
        if (z) {
            return;
        }
        this.f.a((String) null, str2, str3, (List<MAPCookie>) null);
        ga.a("MAPCookieManager");
    }

    public static String a(List<MAPCookie> list) {
        if (e.a(list)) {
            return null;
        }
        for (MAPCookie mAPCookie : list) {
            if (mAPCookie.c().equals("sid")) {
                String strE = mAPCookie.e();
                if (TextUtils.isEmpty(strE)) {
                    Log.e(ga.a("MAPCookieManager"), "The sid value inside the returned cookies is null or a empty string. There's a bug on server side!");
                    yd.a("EmptySidCookieValueInsideAuthCookiesFromServer", new String[0]);
                }
                return strE;
            }
        }
        return null;
    }

    public a a(String str, String str2, String str3, String str4, boolean z) throws MAPCallbackErrorException {
        a(str, str2);
        List<MAPCookie> listA = this.f.a(str, str2, str3, str4);
        boolean z2 = false;
        if (z) {
            return new a(listA, false);
        }
        if (!e.a(listA) && !c(listA)) {
            z2 = true;
        }
        return new a(listA, z2);
    }

    public final Bundle a(List<MAPCookie> list, String str, String str2, String str3) {
        if (list == null) {
            list = new ArrayList<>();
        }
        a(list, str, str2);
        Bundle bundle = new Bundle();
        bundle.putStringArray(CookieKeys.KEY_COOKIES, v9.a(list));
        if (!TextUtils.isEmpty(str3)) {
            bundle.putString(CookieKeys.KEY_RESPONSE_URL, str3);
        }
        return bundle;
    }

    public b a(Bundle bundle, String str, String str2, String str3, String str4, String str5, String str6, String str7, List<MAPCookie> list, y5 y5Var) throws MAPCallbackErrorException {
        boolean z = !TextUtils.isEmpty(str3);
        Log.i(ga.a("MAPCookieManager"), "Fetching cookies... Is is for actor : " + z);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                try {
                    de deVarA = yd.a("MAPCookieManager", z ? "fetchActorCookiesFromServer" : "fetchCookiesFromServer");
                    URL urlA = a(bundle, str5, str);
                    if (urlA != null) {
                        Log.i(ga.a("MAPCookieManager"), "Using CookieExchangeToken URL: " + urlA.toString());
                        ya.b bVarA = a(str5, str, str2, str3, str4, str7, str6, qe.f(this.f579a) ? y5Var.a(this.f579a) : null);
                        List<MAPCookie> arrayList = list == null ? new ArrayList() : list;
                        a(arrayList, str, str5);
                        HttpURLConnection httpURLConnectionA = this.c.a((Context) this.f579a, urlA, bVarA, false, arrayList, str, (String) null, y5Var);
                        int iB = RetryLogic.b(httpURLConnectionA);
                        Log.i(ga.a("MAPCookieManager"), "Headers received update request to exchange token endpoint");
                        JSONObject jSONObjectA = da.a(httpURLConnectionA);
                        deVarA.c();
                        Log.i(ga.a("MAPCookieManager"), "Response parsed for cookie request to exchange token endpoint");
                        qa qaVar = this.c;
                        Integer numValueOf = Integer.valueOf(iB);
                        qaVar.getClass();
                        if (!AuthEndpointErrorParser.a(numValueOf) && jSONObjectA != null) {
                            Log.i(ga.a("MAPCookieManager"), "Parsing getCookies or getCookiesForActor success response");
                            ga.a("MAPCookieManager");
                            b bVarA2 = a(str, jSONObjectA);
                            httpURLConnectionA.disconnect();
                            return bVarA2;
                        }
                        Object[] objArr = new Object[1];
                        objArr[0] = jSONObjectA != null ? jSONObjectA.toString() : "Null Json Response";
                        ga.a("MAPCookieManager", "Error Response: %s", objArr);
                        AuthEndpointErrorParser.a aVarB = new AuthEndpointErrorParser().b(jSONObjectA);
                        if (aVarB != null) {
                            OAuthTokenManager.OAuthTokenManagerException oAuthTokenManagerExceptionA = this.d.a(str, str3, aVarB, Integer.valueOf(iB), OAuthTokenManager.AuthTokenExchangeType.OauthRefreshToCookieExchange);
                            String str8 = String.format("Received Error code %s from the server. Message: %s. Detail: %s. Index: %s.", aVarB.f320a.getCode(), aVarB.b, aVarB.c, aVarB.d);
                            Bundle bundleA = a9.a(MAPError.CommonError.PARSE_ERROR, str8, oAuthTokenManagerExceptionA.d().f320a.getRegistrationError().value(), str8);
                            Log.e(ga.a("MAPCookieManager"), "Panda error index: " + aVarB.d);
                            ga.a("MAPCookieManager");
                            d7 d7VarA = oAuthTokenManagerExceptionA.a();
                            if (d7VarA != null) {
                                bundleA.putBundle("com.amazon.identity.mobi.account.recover.context", d7VarA.b());
                            }
                            bundleA.putBoolean(MAPError.KEY_SHOULD_CLEAR_AUTH_COOKIES, oAuthTokenManagerExceptionA.g());
                            throw new MAPCallbackErrorException(bundleA);
                        }
                        String str9 = String.format(Locale.ENGLISH, "Received unrecognized error from the server with status code %d", Integer.valueOf(iB));
                        throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.SERVER_ERROR, str9, MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), str9));
                    }
                    throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.BAD_REQUEST, "Invalid Parameter: Domain", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "Invalid Parameter: Domain"));
                } catch (IOException e) {
                    Log.e(ga.a("MAPCookieManager"), "Got IOException when fetching Cookie from server ", e);
                    yd.a("fetchCookiesFromServerFailure:IOException", new String[0]);
                    yd.b("NetworkError1:MAPCookieManager", new String[0]);
                    throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), e.getMessage()));
                }
            } catch (JSONException e2) {
                Log.e(ga.a("MAPCookieManager"), "Got JSONException while parsing response ", e2);
                yd.a("fetchCookiesFromServerFailure:JSONException", new String[0]);
                throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.PARSE_ERROR, String.format("Received a JSONException while parsing server response with message: %s", e2.getMessage()), MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), e2.getMessage()));
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public void a(List<MAPCookie> list, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strC = this.h.c(str, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
        if (!TextUtils.isEmpty(strC)) {
            if (!TextUtils.isEmpty(a(list))) {
                Log.w(ga.a("MAPCookieManager"), "Before we add the sid cookie to the cookies that will be sent out, there's already a sid cookie inside; this is a bug since we should have removed the sid cookie before we saved the cookies.");
                yd.a("SidCookieExistsInCookiesBeforeAddingOne", new String[0]);
                b(list);
            }
            if (TextUtils.isEmpty(str2)) {
                ga.a(q9.f514a);
                str2 = ".amazon.com";
            } else if (!str2.startsWith(".")) {
                if (str2.startsWith("amazon.")) {
                    str2 = "." + str2;
                } else if (str2.startsWith("www")) {
                    str2 = str2.substring(3);
                } else if (str2.contains(".amazon")) {
                    str2 = str2.substring(str2.indexOf(".amazon"));
                } else {
                    throw new RuntimeException("Input was non-empty and doesn't look like a valid url: " + str2);
                }
            }
            String str3 = str2;
            ga.a("MAPCookieManager");
            list.add(new MAPCookie("sid", strC, str3, v9.b(), "/", null, true, true));
            return;
        }
        if (TextUtils.isEmpty(a(list))) {
            return;
        }
        Log.e(ga.a("MAPCookieManager"), "There's a sid cookie in the cookies that will be sent out, but we didn't update the sid; this is a bug since we should have updated the sid and removed the sid cookie before we saved the cookies.");
        yd.a("SidCookieExistsButHaveNotBeenSavedAsAToken", new String[0]);
    }

    public final b a(String str, JSONObject jSONObject) {
        String strOptString;
        List<MAPCookie> arrayList;
        List<MAPCookie> arrayList2;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("response");
            strOptString = jSONObject2.optString("uri");
            try {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("tokens");
                try {
                    ga.a("MAPCookieManager");
                    arrayList = b(str, jSONObject3.getJSONObject("cookies"));
                } catch (JSONException unused) {
                    arrayList = new ArrayList<>();
                }
                try {
                    ga.a("MAPCookieManager");
                    arrayList2 = b(str, jSONObject3.getJSONObject("actor_cookies"));
                } catch (JSONException unused2) {
                    arrayList2 = new ArrayList<>();
                }
                return new b(arrayList, arrayList2, strOptString);
            } catch (JSONException unused3) {
                return new b(new ArrayList(), new ArrayList(), strOptString);
            }
        } catch (JSONException unused4) {
            strOptString = null;
        }
    }

    public final List<MAPCookie> a(String str, JSONArray jSONArray, String str2) throws JSONException {
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            MAPCookie mAPCookie = new MAPCookie(jSONObject.getString("Name"), jSONObject.getString("Value"), str2, str, jSONObject.getBoolean("Secure"));
            if (TextUtils.isEmpty(str2)) {
                mAPCookie.a(jSONObject.getString("Domain"));
            }
            mAPCookie.c(jSONObject.getString("Path"));
            if (jSONObject.has("Expires")) {
                mAPCookie.b(jSONObject.getString("Expires"));
            }
            mAPCookie.a(jSONObject.getBoolean("HttpOnly"));
            arrayList.add(mAPCookie);
        }
        return arrayList;
    }

    public final URL a(Bundle bundle, String str, String str2) {
        try {
            return new URL("https", b(bundle, str2, str), EnvironmentUtils.c.i(), "/ap/exchangetoken/cookies");
        } catch (MalformedURLException e) {
            Log.e(ga.a("MAPCookieManager"), "Error parsing URL", e);
            return null;
        }
    }

    public final void a(String str, String str2) throws MAPCallbackErrorException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                if (this.e.isAccountRegistered(str)) {
                    return;
                }
                Log.i(ga.a("MAPCookieManager"), "Given account is not registered");
                throw new MAPCallbackErrorException(a9.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "Given account is not registered", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given account is not registered"));
            }
            Log.e(ga.a("MAPCookieManager"), "Given actor is null");
            throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.BAD_REQUEST, "Given actor is null", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given actor is null"));
        }
        Log.e(ga.a("MAPCookieManager"), "Given account is null");
        throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.BAD_REQUEST, "Given account is null", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given account is null"));
    }

    public static List<MAPCookie> a(List<MAPCookie> list, List<MAPCookie> list2) throws MAPCallbackErrorException {
        if (!e.a(list) && !e.a(list2)) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list2);
            int size = list2.size();
            for (MAPCookie mAPCookie : list) {
                if (a(mAPCookie, list2)) {
                    size--;
                } else {
                    arrayList.add(mAPCookie);
                }
            }
            if (size != 0) {
                Log.i(ga.a("MAPCookieManager"), "Cookies number not match! Return anyway...");
                yd.b("WRONG_ACTOR_COOKIES_NUMBER_DIFF:" + size, new String[0]);
            }
            return arrayList;
        }
        yd.b("NO_COOKIES_WHEN_REPLACE_ACTOR_COOKIES", new String[0]);
        throw new MAPCallbackErrorException(a9.a(MAPError.CommonError.BAD_REQUEST, "No existing cookies, have you called getCookiesForActor before?", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "No existing cookies, have you called getCookiesForActor before?"));
    }

    public static boolean a(MAPCookie mAPCookie, List<MAPCookie> list) {
        for (MAPCookie mAPCookie2 : list) {
            if (TextUtils.equals(mAPCookie2.a(), mAPCookie.a()) && TextUtils.equals(mAPCookie2.c(), mAPCookie.c())) {
                return true;
            }
        }
        return false;
    }
}
