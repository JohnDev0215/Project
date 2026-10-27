package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.firebase.perf.FirebasePerformance;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j7 extends f7 {
    public static final String g = "com.amazon.identity.auth.device.j7";
    public final Bundle d;
    public final u7 e;
    public final o5 f;

    /* JADX WARN: Illegal instructions before constructor call */
    public j7(Bundle bundle, Context context) {
        t5 t5VarA = t5.a(context);
        super(t5VarA);
        this.d = bundle;
        this.e = t5VarA.a();
        this.f = (o5) this.f311a.getSystemService("dcp_device_info");
    }

    @Override // com.amazon.identity.auth.device.f7
    public Map<String, String> a() {
        Map<String, String> mapA = super.a();
        if (this.f311a.b().a(Feature.UseDeviceLocaleAsLanguagePreference)) {
            String strA = e.a(Locale.getDefault());
            ga.a(g);
            ((HashMap) mapA).put("Accept-Language", strA);
        }
        return mapA;
    }

    @Override // com.amazon.identity.auth.device.f7
    public AuthenticationMethod b() {
        return null;
    }

    @Override // com.amazon.identity.auth.device.f7
    public JSONObject b(y5 y5Var) throws Throwable {
        boolean z;
        boolean z2;
        boolean z3;
        fc fcVar = new fc();
        String string = this.d.getString("com.amazon.dcp.sso.property.account.acctId");
        String string2 = this.d.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME);
        String string3 = this.d.getString("password");
        String string4 = this.d.getString("com.amazon.dcp.sso.token.oauth.amazon.access_token");
        boolean z4 = this.d.getBoolean(MAPAccountManager.KEY_DISABLE_GLOBAL_SIGNIN);
        if (!TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(string4)) {
                if (id.b(string)) {
                    Log.i(ga.a(fc.n), " isValidDirectedId: returning false because a null or empty directedId was given.");
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z3) {
                    fcVar.b = string;
                } else {
                    Log.e(ga.a(fc.n), " setDirectedId: directedId was invalid. Cannot be set.");
                }
            } else if (TextUtils.isEmpty(string4)) {
                Log.e(ga.a(fc.n), "setAccessToken: access_token is not empty. Cannot be set.");
            } else {
                fcVar.k = string4;
            }
            String strC = this.e.c(string, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
            if (!TextUtils.isEmpty(strC)) {
                fcVar.d = strC.replaceAll("^\"|\"$", "");
            }
        }
        if (!TextUtils.isEmpty(string2)) {
            if (id.b(string2)) {
                Log.i(ga.a(fc.n), " isValidUserID: returning false because a null or empty user ID was given.");
                z2 = false;
            } else {
                z2 = true;
            }
            if (z2) {
                fcVar.f316a = string2;
            } else {
                Log.e(ga.a(fc.n), " setUserID: userID was invalid. Cannot be set.");
            }
        }
        if (id.b(string3)) {
            Log.i(ga.a(fc.n), " isValidPassword: returning false because a null or empty password was given.");
            z = false;
        } else {
            z = true;
        }
        if (z) {
            fcVar.c = string3;
        } else {
            Log.e(ga.a(fc.n), " setPassword: password was invalid. Cannot be set.");
        }
        o5 o5Var = this.f;
        String strD = o5Var.d();
        if (id.c(strD)) {
            fcVar.e = strD;
        } else {
            Log.e(ga.a(fc.n), " setDeviceSerialNumber: device serial number was invalid. Cannot be set.");
        }
        String strF = o5Var.f();
        if (id.d(strF)) {
            fcVar.f = strF;
        } else {
            Log.e(ga.a(fc.n), " setDeviceType: deviceType was invalid. Cannot be set.");
        }
        kd kdVarG = o5Var.g();
        if (kdVarG.a()) {
            fcVar.g = kdVarG;
        } else {
            Log.e(ga.a(fc.n), " setDeviceSoftwareVersion: device software version is invalid. Cannot be set.");
        }
        cc.a();
        fcVar.l = null;
        String strA = a7.a((Context) this.f311a, this.f.d());
        if (!TextUtils.isEmpty(strA)) {
            ga.a(fc.n);
        }
        fcVar.j = strA;
        String string5 = this.d.getString("calling_package");
        if (string5 != null) {
            fcVar.h = string5;
            Long lA = la.a(this.f311a, string5);
            if (lA != null) {
                fcVar.i = Long.toString(lA.longValue());
            }
        }
        if (z4) {
            fcVar.m = false;
        } else {
            fcVar.m = true;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject.put("auth_data", jSONObject2);
        if (fcVar.m) {
            jSONObject2.put("use_global_authentication", "true");
        } else {
            jSONObject2.put("use_global_authentication", "false");
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("password", fcVar.c);
        if (!TextUtils.isEmpty(fcVar.f316a) && !TextUtils.isEmpty(fcVar.b)) {
            throw new IllegalStateException("Invalid parameters. Cannot set both login and directedId.");
        }
        if (!TextUtils.isEmpty(fcVar.f316a)) {
            jSONObject3.put("user_id", fcVar.f316a);
            jSONObject2.put("user_id_password", jSONObject3);
        } else if (!TextUtils.isEmpty(fcVar.b)) {
            jSONObject3.put("directedId", fcVar.b);
            jSONObject2.put("directedId_password", jSONObject3);
        } else {
            if (TextUtils.isEmpty(fcVar.k)) {
                throw new IllegalStateException("Invalid parameters.");
            }
            jSONObject3.put("access_token", fcVar.k);
            jSONObject2.put("access_token_password", jSONObject3);
        }
        if (!TextUtils.isEmpty(fcVar.d)) {
            jSONObject2.put("trusted_device_token", fcVar.d);
        }
        JSONObject jSONObject4 = new JSONObject();
        jSONObject.put("signin_data", jSONObject4);
        jSONObject4.put("device_serial", fcVar.e);
        jSONObject4.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, fcVar.f);
        jSONObject4.put("domain", "Device");
        String str = fcVar.h;
        if (str == null) {
            str = "defaultAppName";
        }
        jSONObject4.put("app_name", str);
        String str2 = fcVar.i;
        if (str2 == null) {
            str2 = "defaultAppVersion";
        }
        jSONObject4.put("app_version", str2);
        jSONObject4.put("device_model", Build.MODEL);
        jSONObject4.put("os_version", Build.FINGERPRINT);
        kd kdVar = fcVar.g;
        jSONObject4.put("software_version", kdVar != null ? kdVar.f410a : "defaultSoftwareVersion");
        a7.a(fcVar.j, jSONObject);
        if (fcVar.g == null) {
            Log.e(ga.a(fc.n), " software_version was undefined.");
        }
        if (fcVar.l != null) {
            try {
                JSONObject jSONObjectA = gb.a(fcVar.f, fcVar.e, (String) null);
                JSONObject jSONObjectA2 = gb.a();
                if (!TextUtils.isEmpty(fcVar.f316a)) {
                    jSONObjectA2.put("email_hash", Base64.encodeToString(MessageDigest.getInstance("SHA256").digest(fcVar.f316a.getBytes(ParserUtil.UTF_8)), 2));
                } else if (!TextUtils.isEmpty(fcVar.b)) {
                    jSONObjectA2.put("directed_id", fcVar.b);
                }
                jSONObject4.put("device_authentication_token", fcVar.l.a("drvV2", gb.a(jSONObjectA, jSONObjectA2, (String) null)));
            } catch (Exception e) {
                Log.e(ga.a(fc.n), "Failed to sign JWT", e);
            }
        }
        return jSONObject;
    }

    @Override // com.amazon.identity.auth.device.f7
    public String d() {
        return EnvironmentUtils.c.e(q9.b(this.d));
    }

    @Override // com.amazon.identity.auth.device.f7
    public String e() {
        return FirebasePerformance.HttpMethod.POST;
    }

    @Override // com.amazon.identity.auth.device.f7
    public String f() {
        return EnvironmentUtils.c.c(this.d);
    }

    @Override // com.amazon.identity.auth.device.f7
    public String h() {
        return "/auth/signin";
    }

    @Override // com.amazon.identity.auth.device.f7
    public String a(JSONObject jSONObject) {
        return da.a(da.a(da.a(jSONObject, "response"), "error"), "index", (String) null);
    }
}
