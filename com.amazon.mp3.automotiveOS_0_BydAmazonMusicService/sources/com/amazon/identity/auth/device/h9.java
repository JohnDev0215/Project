package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.b9;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.AtzTokenManager;
import com.amazon.identity.auth.device.token.MobileAuthEncryptionKeyManager;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h9 implements e9 {
    public static final String k = "com.amazon.identity.auth.device.h9";
    public static h9 l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f364a;
    public final c8 b;
    public final OAuthTokenManager c;
    public final AtzTokenManager d;
    public final t8 e;
    public final MobileAuthEncryptionKeyManager f;
    public final t2 g;
    public final x8 h;
    public final ha i;
    public final b9 j;

    /* JADX INFO: compiled from: DCP */
    public class d implements b9.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f368a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Bundle c;
        public final /* synthetic */ String d;
        public final /* synthetic */ y5 e;

        public d(String str, String str2, Bundle bundle, String str3, y5 y5Var) {
            this.f368a = str;
            this.b = str2;
            this.c = bundle;
            this.d = str3;
            this.e = y5Var;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public String a() {
            return "UpgradeToken";
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public void a(Callback callback) {
            h9 h9Var = h9.this;
            String str = this.f368a;
            String str2 = this.b;
            String string = this.c.getString("key_token_type");
            String str3 = this.d;
            y5 y5Var = this.e;
            Bundle bundle = this.c;
            h9Var.getClass();
            try {
                if ("token_type_oauth_refresh_token".equals(string)) {
                    h9Var.c.a(str, str2, str3, callback, y5Var, bundle);
                } else {
                    Log.e(ga.a(h9.k), "Upgrade non OAuth refresh token is not supported.");
                    MAPErrorCallbackHelper.onError(callback, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED);
                }
            } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                MAPErrorCallbackHelper.onError(callback, e.e(), e.f());
            }
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public boolean b() {
            return true;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f369a;
        public final /* synthetic */ fa b;
        public final /* synthetic */ y5 c;

        public e(String str, fa faVar, y5 y5Var) {
            this.f369a = str;
            this.b = faVar;
            this.c = y5Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            h9 h9Var = h9.this;
            String str = this.f369a;
            String accessTokenKeyForPackage = TokenKeys.getAccessTokenKeyForPackage(this.b.b);
            Bundle bundle = new Bundle();
            h9 h9Var2 = h9.this;
            h9Var2.getClass();
            h9Var.b(str, accessTokenKeyForPackage, bundle, new i9(h9Var2), this.c);
        }
    }

    public h9(Context context) {
        t5 t5VarA = t5.a(context);
        this.f364a = t5VarA;
        c8 c8Var = new c8(t5VarA, new BackwardsCompatiableDataStorage(t5VarA));
        this.b = c8Var;
        this.c = new OAuthTokenManager(context);
        this.d = new AtzTokenManager(context);
        this.e = new t8(context);
        this.f = new MobileAuthEncryptionKeyManager(context);
        this.g = new t2();
        this.h = new x8(t5VarA, c8Var);
        this.j = b9.a();
        this.i = new ha(context);
    }

    public static synchronized h9 a(Context context) {
        if (l == null || xa.a()) {
            l = new h9(context.getApplicationContext());
        }
        return l;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        long j;
        String str3 = k;
        ga.a(str3);
        s2 s2Var = new s2(callback);
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a(str3), "Directed Id used in getToken is null or empty");
            a9.b(s2Var, MAPError.CommonError.BAD_REQUEST, "Directed Id used in getToken is null or empty", 8, "Directed Id used in getToken is null or empty");
            return s2Var;
        }
        if (TextUtils.isEmpty(str2)) {
            Log.e(ga.a(str3), "Token key used in getToken is null or empty.");
            a9.b(s2Var, MAPError.CommonError.BAD_REQUEST, "Token key used in getToken is null or empty.", 8, "Token key used in getToken is null or empty.");
            return s2Var;
        }
        b9 b9Var = this.j;
        synchronized (b9Var) {
            j = b9Var.d.get();
        }
        String strValueOf = String.valueOf(j);
        t2 t2Var = this.g;
        String str4 = String.format(Locale.ENGLISH, "%s#%s", str, str2);
        if (!TextUtils.isEmpty(strValueOf)) {
            str4 = strValueOf + "#" + str4;
        }
        Callback callbackA = t2Var.a(str4, s2Var);
        if (callbackA == null) {
            String.format("Get token for type %s is already in flight.", str2);
            ga.a(str3);
        } else {
            this.j.a(new f9(this, str, str2, bundle, y5Var), callbackA);
        }
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> c(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        ga.a(k);
        s2 s2Var = new s2(callback);
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.j.a(new b(str, str2, bundle), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        ga.a(k);
        s2 s2Var = new s2(callback);
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.j.a(new a(str, str2, bundle, y5Var), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, y5 y5Var) {
        long j;
        String str5;
        String str6 = k;
        ga.a(str6);
        s2 s2Var = new s2(callback);
        if (bundle == null) {
            new Bundle();
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a(str6), "Account Id used in getTokenForActor is null or empty");
            MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
            a9.b(s2Var, commonError, commonError.getErrorMessage(), 8, "Account Id used in getTokenForActor is null or empty");
            return s2Var;
        }
        if (TextUtils.isEmpty(str2)) {
            Log.e(ga.a(str6), "Actor Id used in getTokenForActor is null or empty");
            MAPError.CommonError commonError2 = MAPError.CommonError.BAD_REQUEST;
            a9.b(s2Var, commonError2, commonError2.getErrorMessage(), 8, "Actor Id used in getTokenForActor is null or empty");
            return s2Var;
        }
        if (TextUtils.isEmpty(str3)) {
            Log.e(ga.a(str6), "Token type used in getTokenForActor is null or empty.");
            MAPError.CommonError commonError3 = MAPError.CommonError.BAD_REQUEST;
            a9.b(s2Var, commonError3, commonError3.getErrorMessage(), 8, "Token type used in getTokenForActor is null or empty.");
            return s2Var;
        }
        b9 b9Var = this.j;
        synchronized (b9Var) {
            j = b9Var.d.get();
        }
        String strValueOf = String.valueOf(j);
        t2 t2Var = this.g;
        if (TextUtils.isEmpty(str4)) {
            str5 = String.format(Locale.ENGLISH, "%s#%s#%s", str, str2, str3);
        } else {
            str5 = String.format(Locale.ENGLISH, "%s#%s#%s#%s", str, str2, str3, "failure_context_flag");
        }
        if (!TextUtils.isEmpty(strValueOf)) {
            str5 = strValueOf + "#" + str5;
        }
        Callback callbackA = t2Var.a(str5, s2Var);
        if (callbackA == null) {
            String.format("Get actor token for type %s is already in flight.", str3);
            ga.a(str6);
        } else {
            this.j.a(new g9(this, context, str, str2, str3, str4, bundle, y5Var), callbackA);
        }
        return s2Var;
    }

    public final void b(String str, fa faVar, Bundle bundle, Callback callback, y5 y5Var) {
        try {
            a9.a(callback, this.c.a(str, faVar, bundle, y5Var), false);
        } catch (OAuthTokenManager.OAuthTokenManagerException e2) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            OAuthTokenManager oAuthTokenManager = this.c;
            String strC = oAuthTokenManager.c.c(str, faVar.f315a);
            if (oAuthTokenManager.a(str, faVar, bundle)) {
                strC = null;
            }
            String str2 = k;
            ga.b(str2, "Received an error when calling getOAuthAccessToken. ErrorCode: %d ErrorMessage: %s.", Integer.valueOf(e2.c()), e2.b());
            if (strC == null) {
                a9.a(callback, e2.e(), e2.f(), e2);
                return;
            }
            Log.w(ga.a(str2), "MAP will return cached access token in some cases. Please check if the success bundle has key KEY_ACCESS_TOKEN_FROM_CACHE.");
            yd.b("RETURN_CACHED_ATNA_TOKEN", new String[0]);
            a9.a(callback, strC, true);
        }
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, y5 y5Var) {
        ga.a(k);
        s2 s2Var = new s2(callback);
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.j.a(new c(str, str2, str3, bundle, y5Var), s2Var);
        return s2Var;
    }

    public final void b(String str, fa faVar, Callback callback, y5 y5Var) {
        try {
            OAuthTokenManager oAuthTokenManager = this.c;
            String str2 = faVar.b;
            String strD = oAuthTokenManager.d(str, str2);
            if (strD == null) {
                strD = oAuthTokenManager.a(str, str2, true, y5Var);
            }
            boolean zA = this.f.a(str, strD, y5Var);
            Bundle bundle = new Bundle();
            bundle.putBoolean(TokenKeys.Options.KEY_MOBILE_AUTH_ENCRYPTION_KEY_UPSERT, zA);
            callback.onSuccess(bundle);
        } catch (MobileAuthEncryptionKeyManager.MobileAuthEncryptionKeyManagerException e2) {
            ga.b(k, "Failed to create/update MobileAuth Encryption Key. ErrorCode: %d ErrorMessage: %s", Integer.valueOf(e2.a().getErrorCode()), e2.b());
            MAPErrorCallbackHelper.onError(callback, e2.a());
        } catch (OAuthTokenManager.OAuthTokenManagerException e3) {
            ga.b(k, "Received an error when fetching Refresh Token. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e3.c()), e3.b());
            MAPErrorCallbackHelper.onError(callback, e3.e());
        }
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, r8 r8Var, y5 y5Var) {
        String str3 = k;
        Log.i(ga.a(str3), "Upgrade token for account or actor.");
        s2 s2Var = new s2(callback);
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a(str3), "Account Id in upgradeToken is null or empty");
            MAPErrorCallbackHelper.onError(s2Var, MAPError.CommonError.BAD_REQUEST, "Account Id in upgradeToken is null or empty");
            return s2Var;
        }
        String string = bundle.getString("key_auth_code");
        if (TextUtils.isEmpty(string)) {
            Log.e(ga.a(str3), "AuthCode used in upgradeToken is null or empty");
            MAPErrorCallbackHelper.onError(s2Var, MAPError.CommonError.BAD_REQUEST, "AuthCode used in upgradeToken is null or empty");
            return s2Var;
        }
        this.j.a(new d(str, str2, bundle, string, y5Var), s2Var);
        if (r8Var != null) {
            Log.i(ga.a(str3), "Finish listener upon enqueuing.");
            r8Var.onFinish(new Bundle());
        }
        return s2Var;
    }

    public final void a(String str, fa faVar, Callback callback, y5 y5Var) {
        try {
            OAuthTokenManager oAuthTokenManager = this.c;
            String str2 = faVar.b;
            String strD = oAuthTokenManager.d(str, str2);
            if (strD == null) {
                strD = oAuthTokenManager.a(str, str2, true, y5Var);
            }
            a9.a(callback, strD, false);
        } catch (OAuthTokenManager.OAuthTokenManagerException e2) {
            ga.b(k, "Received an error when calling getOAuthRefreshToken. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e2.c()), e2.b());
            a9.a(callback, e2.e(), e2.f(), e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r15, com.amazon.identity.auth.device.fa r16, android.os.Bundle r17, com.amazon.identity.auth.device.api.Callback r18, com.amazon.identity.auth.device.y5 r19) {
        /*
            r14 = this;
            r1 = r14
            r8 = r15
            r9 = r16
            r10 = r18
            r11 = 2
            r12 = 1
            r13 = 0
            com.amazon.identity.auth.device.token.OAuthTokenManager r0 = r1.c     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            java.lang.String r2 = r9.b     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            java.lang.String r3 = r0.d(r15, r2)     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            if (r3 == 0) goto L17
            r7 = r19
            r4 = r3
            goto L1e
        L17:
            r7 = r19
            java.lang.String r0 = r0.a(r15, r2, r12, r7)     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            r4 = r0
        L1e:
            com.amazon.identity.auth.device.token.AtzTokenManager r2 = r1.d     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            r3 = r15
            r5 = r16
            r6 = r17
            r7 = r19
            java.lang.String r0 = r2.a(r3, r4, r5, r6, r7)     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            com.amazon.identity.auth.device.a9.a(r10, r0, r13)     // Catch: com.amazon.identity.auth.device.token.OAuthTokenManager.OAuthTokenManagerException -> L30 com.amazon.identity.auth.device.token.AtzTokenManager.AtzTokenManagerException -> L60
            goto Lc6
        L30:
            r0 = move-exception
            java.lang.String r2 = com.amazon.identity.auth.device.h9.k
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.lang.Object[] r4 = new java.lang.Object[r11]
            int r5 = r0.c()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r13] = r5
            java.lang.String r5 = r0.b()
            r4[r12] = r5
            java.lang.String r5 = "Received an error when calling getAtzAccessToken. ErrorCode: %d ErrorMessage: %s "
            java.lang.String r3 = java.lang.String.format(r3, r5, r4)
            java.lang.String r2 = com.amazon.identity.auth.device.ga.a(r2)
            android.util.Log.e(r2, r3)
            com.amazon.identity.auth.device.api.MAPError r2 = r0.e()
            java.lang.String r3 = r0.f()
            com.amazon.identity.auth.device.a9.a(r10, r2, r3, r0)
            goto Lc6
        L60:
            r0 = move-exception
            if (r17 != 0) goto L69
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            goto L6b
        L69:
            r2 = r17
        L6b:
            com.amazon.identity.auth.device.token.AtzTokenManager r3 = r1.d
            com.amazon.identity.auth.device.u7 r4 = r3.d
            java.lang.String r5 = r9.f315a
            java.lang.String r4 = r4.c(r15, r5)
            r5 = 0
            if (r4 == 0) goto L7e
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8d
            r6.<init>(r4)     // Catch: org.json.JSONException -> L8d
            goto L7f
        L7e:
            r6 = r5
        L7f:
            boolean r2 = r3.a(r15, r6, r2, r9)     // Catch: org.json.JSONException -> L8d
            if (r2 == 0) goto L86
            goto L8d
        L86:
            java.lang.String r2 = "token"
            java.lang.String r5 = r6.getString(r2)     // Catch: org.json.JSONException -> L8d
        L8d:
            java.lang.String r2 = com.amazon.identity.auth.device.h9.k
            java.lang.Object[] r3 = new java.lang.Object[r11]
            int r4 = r0.c()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r13] = r4
            java.lang.String r4 = r0.d()
            r3[r12] = r4
            java.lang.String r4 = "Received an error when calling getAtzAccessToken. ErrorCode: %d ErrorMessage: %s, MAP will return cached access token in some case. Please check if the success bundle has key KEY_ACCESS_TOKEN_FROM_CACHE"
            com.amazon.identity.auth.device.ga.b(r2, r4, r3)
            if (r5 == 0) goto Lb3
            java.lang.String[] r0 = new java.lang.String[r13]
            java.lang.String r2 = "RETURN_CACHED_ATZA_TOKEN"
            com.amazon.identity.auth.device.yd.b(r2, r0)
            com.amazon.identity.auth.device.a9.a(r10, r5, r12)
            goto Lc6
        Lb3:
            com.amazon.identity.auth.device.api.MAPError r2 = r0.a()
            java.lang.String r3 = r0.b()
            int r4 = r0.c()
            java.lang.String r0 = r0.d()
            com.amazon.identity.auth.device.a9.a(r10, r2, r3, r4, r0)
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.h9.a(java.lang.String, com.amazon.identity.auth.device.fa, android.os.Bundle, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.y5):void");
    }

    public final void a(Context context, String str, String str2, fa faVar, String str3, Bundle bundle, Callback callback, y5 y5Var) {
        Bundle bundle2;
        Bundle bundle3 = bundle == null ? new Bundle() : bundle;
        try {
            bundle2 = bundle3;
            try {
                String strA = this.c.a(context, str, str2, faVar, str3, bundle3, y5Var);
                OAuthTokenManager oAuthTokenManager = this.c;
                oAuthTokenManager.b.getClass();
                long jLongValue = com.amazon.identity.auth.device.e.k(oAuthTokenManager.c.e(str, str2, com.amazon.identity.auth.device.e.a(faVar.b, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT))).longValue() - System.currentTimeMillis();
                String str4 = a9.f175a;
                if (callback == null) {
                    Log.e(ga.a(a9.f175a), "Cannot callback success because no callback was given");
                } else {
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("value_key", strA);
                    bundle4.putLong(TokenKeys.Options.KEY_OAUTH_TTL_MS_LONG, jLongValue);
                    callback.onSuccess(bundle4);
                }
            } catch (OAuthTokenManager.OAuthTokenManagerException e2) {
                e = e2;
                String strF = e.f();
                if (MAPError.AccountError.AUTHENTICATION_CHALLENGED.equals(e.e()) && ld.a(strF) && context != null) {
                    bundle2.putString("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token", faVar.f315a);
                    Log.i(ga.a(k), "Opening webview to handle actor token exchange challenge.");
                    Intent intentA = ca.a(context, null, AuthChallengeHandleActivity.class.getName());
                    Bundle bundle5 = bundle2;
                    if (y5Var != null) {
                        y5Var.a(intentA);
                    }
                    if (intentA != null) {
                        bundle5.putString("account_id", str);
                        bundle5.putString("actor_id", str2);
                        bundle5.putString("challenge_url", strF);
                        try {
                            this.c.a(str, str2, bundle5.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN), bundle5, y5Var);
                            intentA.putExtras(bundle5);
                            intentA.putExtra("callback", new RemoteCallbackWrapper(callback));
                            if (!(context instanceof Activity)) {
                                intentA.addFlags(268435456);
                            }
                            context.startActivity(intentA);
                            return;
                        } catch (OAuthTokenManager.OAuthTokenManagerException e3) {
                            Log.e(ga.a(k), "Cannot get cookies before launching the challenge UI");
                            MAPErrorCallbackHelper.onError(callback, e3.e(), "Cannot get cookies before launching the challenge UI");
                            return;
                        }
                    }
                    throw new RuntimeException("No activity can handle the intent. Probably because you do not declare AuthChallengeHandleActivity in android manifest");
                }
                Log.e(ga.a(k), String.format(Locale.ENGLISH, "Received an error when calling getActorAccessToken. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e.e().getErrorCode()), e.e().getErrorMessage()));
                a9.a(callback, e.e(), e.f(), e);
            }
        } catch (OAuthTokenManager.OAuthTokenManagerException e4) {
            e = e4;
            bundle2 = bundle3;
        }
    }

    public final void a(String str, fa faVar, y5 y5Var) {
        boolean z;
        x8 x8Var = this.h;
        synchronized (x8Var) {
            if (qe.f(x8Var.b.f382a)) {
                z = false;
            } else {
                String strD = x8Var.f660a.d(str, "3PLastRegistrationCheckTimeKey");
                Long lK = strD == null ? null : com.amazon.identity.auth.device.e.k(strD);
                if (lK != null) {
                    z = System.currentTimeMillis() - lK.longValue() >= x8.c;
                }
            }
        }
        if (z) {
            Log.i(ga.a(k), "Start to do registration check.");
            va.f637a.execute(new e(str, faVar, y5Var));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class a implements b9.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f365a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Bundle c;
        public final /* synthetic */ y5 d;

        public a(String str, String str2, Bundle bundle, y5 y5Var) {
            this.f365a = str;
            this.b = str2;
            this.c = bundle;
            this.d = y5Var;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public void a(Callback callback) {
            h9 h9Var = h9.this;
            String str = this.f365a;
            String str2 = this.b;
            Bundle bundle = this.c;
            y5 y5Var = this.d;
            h9Var.getClass();
            try {
                h9Var.i.b();
                callback.onSuccess(h9Var.e.a(str, str2, bundle, y5Var));
            } catch (MAPCallbackErrorException e) {
                callback.onError(e.getErrorBundle());
            }
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public boolean b() {
            return false;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public String a() {
            return "GetCookies:" + this.b;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements b9.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f367a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ Bundle d;
        public final /* synthetic */ y5 e;

        public c(String str, String str2, String str3, Bundle bundle, y5 y5Var) {
            this.f367a = str;
            this.b = str2;
            this.c = str3;
            this.d = bundle;
            this.e = y5Var;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public void a(Callback callback) {
            h9 h9Var = h9.this;
            String str = this.f367a;
            String str2 = this.b;
            String str3 = this.c;
            Bundle bundle = this.d;
            y5 y5Var = this.e;
            h9Var.getClass();
            try {
                h9Var.i.b();
                callback.onSuccess(h9Var.e.a(str, str2, str3, bundle, y5Var));
            } catch (MAPCallbackErrorException e) {
                callback.onError(e.getErrorBundle());
            }
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public boolean b() {
            return false;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public String a() {
            return "GetActorCookies:" + this.c;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements b9.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f366a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Bundle c;

        public b(String str, String str2, Bundle bundle) {
            this.f366a = str;
            this.b = str2;
            this.c = bundle;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public void a(Callback callback) {
            h9 h9Var = h9.this;
            String str = this.f366a;
            String str2 = this.b;
            Bundle bundle = this.c;
            h9Var.getClass();
            Bundle bundle2 = new Bundle();
            f8 f8Var = new f8(h9Var.f364a, "token_storage", 0);
            StringBuilder sb = new StringBuilder();
            sb.append(CookieKeys.KEY_INVALIDATE_COOKIES);
            sb.append(h9Var.f364a.getPackageName());
            sb.append(str);
            sb.append(str2);
            if (bundle.containsKey(CookieKeys.Options.KEY_INVALIDATE_ACTOR_COOKIES)) {
                sb.append(bundle.getString(CookieKeys.Options.KEY_INVALIDATE_ACTOR_COOKIES));
            }
            ga.a(h9.k);
            boolean zA = f8Var.a(sb.toString(), Boolean.TRUE);
            bundle2.putBoolean(CookieKeys.KEY_INVALIDATE_COOKIES, zA);
            if (zA) {
                callback.onSuccess(bundle2);
            } else {
                callback.onError(bundle2);
            }
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public boolean b() {
            return false;
        }

        @Override // com.amazon.identity.auth.device.b9.d
        public String a() {
            return "invalidateCookies:" + this.b;
        }
    }
}
