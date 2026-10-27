package com.amazon.identity.kcpsdk.auth;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.a7;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.gb;
import com.amazon.identity.auth.device.gc;
import com.amazon.identity.auth.device.id;
import com.amazon.identity.auth.device.jd;
import com.amazon.identity.auth.device.kd;
import com.amazon.identity.auth.device.lc;
import com.amazon.identity.auth.device.ld;
import com.amazon.identity.auth.device.oc;
import com.amazon.identity.auth.device.pc;
import com.amazon.identity.auth.device.q9;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import com.amazonaws.http.HttpHeader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class RegisterDeviceRequest extends pc {
    public static final String b0 = "com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest";
    public String A;
    public String B;
    public String C;
    public List<MAPCookie> D;
    public String E;
    public JSONObject F;
    public String G;
    public String H;
    public String I;
    public Map<String, jd> J;
    public String K;
    public String L;
    public String M;
    public String N;
    public t5 O;
    public String P;
    public String Q;
    public String R;
    public String S;
    public boolean T;
    public a U;
    public lc V;
    public String W;
    public String X;
    public String Y;
    public String Z;
    public List<String> a0;
    public String i;
    public String j;
    public String k;
    public String l;
    public CustomerAccountTokenType m;
    public Bundle n;
    public String o;
    public String p;
    public String q;
    public String r;
    public kd s;
    public boolean t;
    public boolean u;
    public DeviceAccountRole v;
    public boolean w;
    public boolean x;
    public RegisterEndpointEnum y;
    public String z;

    /* JADX INFO: compiled from: DCP */
    public enum CustomerAccountTokenType {
        AT_MAIN("ATMain"),
        ACCESS_TOKEN("AccessToken"),
        AUTH_TOKEN("AuthToken");

        private final String mValue;

        CustomerAccountTokenType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public enum DeviceAccountRole {
        UNDEFINED,
        PRIMARY,
        SECONDARY
    }

    /* JADX INFO: compiled from: DCP */
    public enum RegisterEndpointEnum {
        FIRS,
        Panda
    }

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f710a;
    }

    public RegisterDeviceRequest(t5 t5Var, Bundle bundle) {
        this(t5Var, a(t5Var, bundle));
    }

    public static lc a(t5 t5Var, Bundle bundle) {
        String str = qe.f519a;
        if (!t5Var.b().a(Feature.IsolateApplication) || bundle == null || !bundle.getBoolean(MAPAccountManager.KEY_IGNORE_NAME_FOR_ISOLATED_APP, false)) {
            return new lc(new gc());
        }
        Log.i(ga.a(b0), "Using special isolated app parser");
        return new lc(new oc());
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0497  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0103  */
    @Override // com.amazon.identity.auth.device.pc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.device.ld c() {
        /*
            Method dump skipped, instruction units count: 1301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest.c():com.amazon.identity.auth.device.ld");
    }

    public boolean d(String str) {
        boolean z;
        if (id.b(str)) {
            Log.i(ga.a(b0), "isValidPassword: returning false because a null or empty password was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            Log.e(ga.a(b0), "setPassword: login was invalid. Cannot be set.");
            return false;
        }
        if (this.l != null) {
            Log.e(ga.a(b0), "setPassword: cannot specify both a password and an auth token. Cannot be set.");
            return false;
        }
        this.k = str;
        return true;
    }

    public RegisterDeviceRequest(t5 t5Var, lc lcVar) {
        this.p = null;
        this.q = null;
        this.r = null;
        this.v = DeviceAccountRole.UNDEFINED;
        this.x = false;
        this.P = null;
        this.m = CustomerAccountTokenType.AT_MAIN;
        this.y = RegisterEndpointEnum.FIRS;
        this.J = null;
        this.O = t5Var;
        this.T = true;
        this.V = lcVar;
    }

    public final ld d() {
        ld ldVar = this.e;
        if (ldVar != null && this.y == RegisterEndpointEnum.Panda) {
            return ldVar;
        }
        CustomerAccountTokenType customerAccountTokenType = this.m;
        CustomerAccountTokenType customerAccountTokenType2 = CustomerAccountTokenType.AUTH_TOKEN;
        if (customerAccountTokenType == customerAccountTokenType2 && (id.b(this.l) || id.b(this.C))) {
            Log.e(ga.a(b0), "Customer token or ClientContext is not set. Use MAPAccountManager.KEY_AUTH_TOKEN and MAPAccountManager.KEY_AUTH_TOKEN_CONTEXT to pass them in regData.");
            return null;
        }
        ld ldVar2 = new ld();
        this.e = ldVar2;
        ldVar2.a(WebProtocol.WebProtocolHttps);
        this.e.d = EnvironmentUtils.c.e(q9.b(this.n));
        ld ldVar3 = this.e;
        ldVar3.h = HttpVerb.HttpVerbPost;
        ldVar3.b(HttpHeader.CONTENT_TYPE, "application/json");
        this.e.b("x-amzn-identity-auth-domain", EnvironmentUtils.c.c(this.n));
        ld ldVar4 = this.e;
        int i = EnvironmentUtils.c.i();
        ldVar4.getClass();
        if (i != -1) {
            ldVar4.e = Integer.toString(i);
        } else {
            ldVar4.e = null;
        }
        this.e.c("/auth/register");
        String str = this.o;
        if (str != null) {
            this.e.b("Accept-Language", str);
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.T) {
                jSONObject.put("use_global_authentication", "true");
            } else {
                jSONObject.put("use_global_authentication", "false");
            }
            if (this.u) {
                this.e.b(HttpHeader.AUTHORIZATION, "Bearer " + this.K);
            }
            if (!TextUtils.isEmpty(this.r)) {
                jSONObject.put("code", this.r);
            } else if (!TextUtils.isEmpty(this.q) && !TextUtils.isEmpty(this.p)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("public_code", this.p);
                jSONObject2.put("private_code", this.q);
                jSONObject.put("code_pair", jSONObject2);
            } else if (!TextUtils.isEmpty(this.P) && !TextUtils.isEmpty(this.Q)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("sso_code", this.P);
                jSONObject3.put("register_directedId", this.Q);
                jSONObject3.put("host_device_type", this.R);
                jSONObject3.put("host_device_serial", this.S);
                jSONObject.put("sso_data", jSONObject3);
            } else if (!TextUtils.isEmpty(this.L) && !TextUtils.isEmpty(this.N)) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("user_id", this.L);
                jSONObject4.put("password", this.N);
                jSONObject.put("user_id_password", jSONObject4);
            } else if (!TextUtils.isEmpty(this.M)) {
                if (!TextUtils.isEmpty(this.N)) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("directedId", this.M);
                    jSONObject5.put("password", this.N);
                    jSONObject.put("directedId_password", jSONObject5);
                } else {
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("directedId", this.M);
                    jSONObject.put("delegation_data", jSONObject6);
                }
            } else if (TextUtils.isEmpty(this.l) && !TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.k)) {
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("user_id", this.i);
                jSONObject7.put("password", this.k);
                jSONObject.put("user_id_password", jSONObject7);
            } else if (TextUtils.isEmpty(this.l) && !TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.k)) {
                JSONObject jSONObject8 = new JSONObject();
                jSONObject8.put("directedId", this.j);
                jSONObject8.put("password", this.k);
                jSONObject.put("directedId_password", jSONObject8);
            } else if (TextUtils.isEmpty(this.l) && !TextUtils.isEmpty(this.W)) {
                jSONObject.put(AccountConstants.KEY_AUTHORIZATION_CODE, this.W);
                jSONObject.put(AccountConstants.KEY_CODE_VERIFIER, this.X);
                jSONObject.put("code_algorithm", this.Y);
                jSONObject.put(AccountConstants.KEY_CLIENT_DOMAIN, AccountConstants.CLIENT_DOMAIN_DEVICE_LEGACY);
                jSONObject.put(AccountConstants.KEY_CLIENT_ID, this.Z);
            } else {
                CustomerAccountTokenType customerAccountTokenType3 = this.m;
                if (customerAccountTokenType3 == CustomerAccountTokenType.ACCESS_TOKEN) {
                    jSONObject.put("access_token", this.l);
                } else if (customerAccountTokenType3 == customerAccountTokenType2) {
                    JSONObject jSONObject9 = new JSONObject();
                    jSONObject9.put("atmain", this.l);
                    jSONObject9.put("client_context", this.C);
                    jSONObject9.put("max_age", 1209600);
                    jSONObject.put("auth_token", jSONObject9);
                }
            }
            JSONObject jSONObject10 = new JSONObject();
            jSONObject10.put("domain", AccountConstants.CLIENT_DOMAIN_DEVICE_LEGACY);
            jSONObject10.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, this.f496a);
            jSONObject10.put("device_serial", this.b);
            String str2 = this.z;
            if (str2 == null) {
                str2 = "defaultAppName";
            }
            jSONObject10.put("app_name", str2);
            String str3 = this.A;
            if (str3 == null) {
                str3 = "defaultAppVersion";
            }
            jSONObject10.put("app_version", str3);
            jSONObject10.put("device_model", Build.MODEL);
            jSONObject10.put("os_version", Build.FINGERPRINT);
            kd kdVar = this.s;
            jSONObject10.put("software_version", kdVar != null ? kdVar.f410a : "defaultSoftwareVersion");
            if (!qe.a(this.O)) {
                String strB = b();
                if (!TextUtils.isEmpty(strB)) {
                    jSONObject10.put("device_authentication_token", strB);
                }
                if (!TextUtils.isEmpty(this.d)) {
                    ga.a(b0);
                    jSONObject10.put("device_secret", this.d);
                }
            }
            if (!TextUtils.isEmpty(this.E)) {
                jSONObject10.put(MAPAccountManager.KEY_DEVICE_NAME, this.E);
            }
            if (!TextUtils.isEmpty(this.H)) {
                jSONObject10.put("preload_device_info", this.H);
            }
            if (this.s == null) {
                Log.e(ga.a(b0), " software_version was undefined.");
            }
            if (this.v.equals(DeviceAccountRole.PRIMARY)) {
                jSONObject10.put("register_as_primary", "true");
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("bearer");
            jSONArray.put("mac_dms");
            jSONArray.put("store_authentication_cookie");
            jSONArray.put("website_cookies");
            JSONObject jSONObject11 = new JSONObject();
            jSONObject11.put("domain", this.I);
            JSONArray jSONArray2 = new JSONArray();
            for (MAPCookie mAPCookie : this.D) {
                JSONObject jSONObject12 = new JSONObject();
                jSONObject12.put("Name", mAPCookie.c());
                jSONObject12.put("Value", mAPCookie.e());
                jSONArray2.put(jSONObject12);
            }
            jSONObject11.put("website_cookies", jSONArray2);
            JSONArray jSONArray3 = new JSONArray();
            jSONArray3.put("device_info");
            jSONArray3.put("customer_info");
            if (!e.a(this.a0)) {
                Iterator<String> it = this.a0.iterator();
                while (it.hasNext()) {
                    jSONArray3.put(it.next());
                }
            }
            JSONObject jSONObject13 = new JSONObject();
            if (this.U != null) {
                JSONObject jSONObject14 = new JSONObject();
                jSONObject14.put("country_of_residence", this.U.f710a);
                jSONObject13.put("anonymous_data", jSONObject14);
            } else if (this.u) {
                jSONObject13.put("secondary_auth_data", jSONObject);
            } else {
                jSONObject.remove("auth_token");
                jSONObject13.put("auth_data", jSONObject);
            }
            jSONObject13.put("registration_data", jSONObject10);
            jSONObject13.put("requested_token_type", jSONArray);
            jSONObject13.put("cookies", jSONObject11);
            a7.a(this.G, jSONObject13);
            JSONObject jSONObject15 = this.F;
            if (jSONObject15 != null && jSONObject15.length() > 0) {
                jSONObject13.put("device_metadata", this.F);
            }
            jSONObject13.put(MAPAccountManager.KEY_REQUESTED_EXTENSIONS, jSONArray3);
            this.e.b(jSONObject13.toString());
            String str4 = b0;
            Object[] objArr = new Object[4];
            objArr[0] = this.f496a;
            objArr[1] = Boolean.toString(this.u);
            kd kdVar2 = this.s;
            objArr[2] = kdVar2 == null ? "None" : kdVar2.f410a;
            String str5 = this.o;
            if (str5 == null) {
                str5 = "Default";
            }
            objArr[3] = str5;
            ga.c(str4, "getWebRequest: constructed a web request with:\nDevice Type: %s\nIs Secondary Account: %s\nSoftware Version: %s\nLocale: %s", objArr);
            return this.e;
        } catch (JSONException e) {
            ga.b(b0, "Error Creating Panda web requst. Error: %s", e.getMessage());
            return null;
        }
    }

    public void a(boolean z, DeviceAccountRole deviceAccountRole) {
        this.u = z;
        if (!z) {
            deviceAccountRole = DeviceAccountRole.UNDEFINED;
        }
        this.v = deviceAccountRole;
    }

    @Override // com.amazon.identity.auth.device.pc
    public JSONObject a() throws JSONException {
        JSONObject jSONObjectA = gb.a();
        if (!TextUtils.isEmpty(this.l)) {
            jSONObjectA.put("access_token", this.l);
        }
        return jSONObjectA;
    }
}
