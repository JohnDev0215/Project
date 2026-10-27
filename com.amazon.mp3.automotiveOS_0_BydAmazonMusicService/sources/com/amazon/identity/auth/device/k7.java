package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.firebase.perf.FirebasePerformance;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class k7 extends f7 {
    public static final /* synthetic */ int f = 0;
    public final String d;
    public final v8 e;

    /* JADX INFO: compiled from: DCP */
    public static class a extends k7 {
        public final String g;
        public final String h;
        public final String i;
        public final String j;
        public final String k;

        public a(v8 v8Var, Context context, String str, String str2, String str3, String str4, String str5, String str6) {
            super(context, v8Var, str);
            this.g = str2;
            this.h = str3;
            this.i = str4;
            this.j = str5;
            this.k = str6;
        }

        @Override // com.amazon.identity.auth.device.f7
        public AuthenticationMethod b() {
            return null;
        }

        @Override // com.amazon.identity.auth.device.f7
        public JSONObject b(y5 y5Var) throws JSONException {
            v8 v8Var = this.e;
            String str = this.g;
            String str2 = this.h;
            String str3 = this.i;
            String str4 = this.j;
            String str5 = this.k;
            v8Var.getClass();
            JSONObject jSONObject = new JSONObject();
            v8Var.a(jSONObject);
            jSONObject.put("source_token_type", AccountConstants.KEY_AUTHORIZATION_CODE);
            jSONObject.put("source_token", str);
            jSONObject.put("requested_token_type", "access_token");
            jSONObject.put(AccountConstants.KEY_CODE_VERIFIER, str2);
            jSONObject.put("code_algorithm", str3);
            jSONObject.put(AccountConstants.KEY_CLIENT_ID, str4);
            jSONObject.put(AccountConstants.KEY_CLIENT_DOMAIN, str5);
            jSONObject.put("app_name", v8Var.f635a.getPackageName());
            jSONObject.putOpt("map_version", v8Var.c.a());
            return jSONObject;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class c extends k7 {
        public final String g;
        public final Bundle h;

        public c(v8 v8Var, Context context, String str, String str2, Bundle bundle) {
            super(context, v8Var, str);
            this.g = str2;
            this.h = bundle;
        }

        @Override // com.amazon.identity.auth.device.f7
        public AuthenticationMethod b() {
            return null;
        }

        @Override // com.amazon.identity.auth.device.f7
        public JSONObject b(y5 y5Var) throws JSONException {
            v8 v8Var = this.e;
            String str = this.g;
            String str2 = this.d;
            JSONObject jSONObjectA = v8Var.a("refresh_token", str, "delegated_access_token", y5Var);
            jSONObjectA.put("directed_id", str2);
            jSONObjectA.toString();
            ga.a("PandaOAuthExchangeRequestHelper");
            return jSONObjectA;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
        @Override // com.amazon.identity.auth.device.k7, com.amazon.identity.auth.device.f7
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String d() {
            /*
                r3 = this;
                android.os.Bundle r0 = r3.h
                if (r0 == 0) goto L15
                java.lang.String r0 = com.amazon.identity.auth.device.q9.a(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L15
                com.amazon.identity.auth.device.env.EnvironmentUtils r1 = com.amazon.identity.auth.device.env.EnvironmentUtils.c
                java.lang.String r0 = r1.e(r0)
                goto L16
            L15:
                r0 = 0
            L16:
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 == 0) goto L24
                com.amazon.identity.auth.device.t5 r0 = r3.f311a
                java.lang.String r1 = r3.d
                java.lang.String r0 = com.amazon.identity.auth.device.q9.a(r0, r1)
            L24:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Delegated token exchange endpoint: "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "PandaTokenExchangeApiCall"
                java.lang.String r2 = com.amazon.identity.auth.device.ga.a(r2)
                android.util.Log.i(r2, r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "PandaDelegateTokenExchange:"
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                r2 = 0
                java.lang.String[] r2 = new java.lang.String[r2]
                com.amazon.identity.auth.device.yd.b(r1, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.k7.c.d():java.lang.String");
        }
    }

    public k7(Context context, v8 v8Var, String str) {
        super(t5.a(context));
        this.d = str;
        this.e = v8Var;
    }

    @Override // com.amazon.identity.auth.device.f7
    public Map<String, String> a() {
        return super.a();
    }

    @Override // com.amazon.identity.auth.device.f7
    public String d() {
        return q9.a(this.f311a, this.d);
    }

    @Override // com.amazon.identity.auth.device.f7
    public String e() {
        return FirebasePerformance.HttpMethod.POST;
    }

    @Override // com.amazon.identity.auth.device.f7
    public String f() {
        return q9.b(this.f311a, this.d);
    }

    @Override // com.amazon.identity.auth.device.f7
    public String h() {
        return "/auth/token";
    }

    @Override // com.amazon.identity.auth.device.f7
    public String a(JSONObject jSONObject) {
        return da.a(jSONObject, "error_index", (String) null);
    }

    /* JADX INFO: compiled from: DCP */
    public static class b extends k7 {
        public final String g;

        public b(v8 v8Var, Context context, String str, String str2) {
            super(context, v8Var, str);
            this.g = str2;
        }

        @Override // com.amazon.identity.auth.device.f7
        public AuthenticationMethod b() {
            AuthenticationMethodFactory authenticationMethodFactory = new AuthenticationMethodFactory(this.f311a, this.d);
            authenticationMethodFactory.setPackageName(this.g);
            return authenticationMethodFactory.newAuthenticationMethod(AuthenticationType.ADPAuthenticator);
        }

        @Override // com.amazon.identity.auth.device.f7
        public JSONObject b(y5 y5Var) throws JSONException {
            return this.e.a("dms_token", "source_token", "refresh_token", y5Var);
        }
    }
}
