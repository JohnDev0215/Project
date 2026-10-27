package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.firebase.perf.FirebasePerformance;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i7 extends f7 {
    public final String d;
    public final String e;
    public final w8 f;
    public final Map<String, String> g;
    public final Map<String, String> h;
    public final Bundle i;
    public final String j;

    public i7(Context context, String str, Map<String, String> map, String str2, Map<String, String> map2, String str3, w8 w8Var, Bundle bundle) {
        super(t5.a(context));
        this.d = str;
        this.e = str2;
        this.j = str3;
        this.i = bundle;
        this.g = map;
        this.h = map2;
        this.f = w8Var;
    }

    @Override // com.amazon.identity.auth.device.f7
    public String a(JSONObject jSONObject) {
        return da.a(jSONObject, "error_index", (String) null);
    }

    @Override // com.amazon.identity.auth.device.f7
    public AuthenticationMethod b() {
        return null;
    }

    @Override // com.amazon.identity.auth.device.f7
    public JSONObject b(y5 y5Var) throws JSONException {
        w8 w8Var = this.f;
        Object obj = this.d;
        Map<String, String> map = this.g;
        String str = this.e;
        Map<String, String> map2 = this.h;
        String str2 = this.j;
        Bundle bundle = this.i;
        w8Var.getClass();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code", str2);
        if (bundle != null && bundle.getBoolean("authorizationCode")) {
            ga.a("PandaOAuthUpgradeRequestHelper");
            jSONObject2.put(AccountConstants.KEY_CODE_VERIFIER, bundle.getString(AccountConstants.KEY_CODE_VERIFIER));
            jSONObject2.put("code_algorithm", bundle.getString(AccountConstants.KEY_CODE_CHALLENGE_METHOD));
            jSONObject2.put(AccountConstants.KEY_CLIENT_DOMAIN, bundle.getString(AccountConstants.KEY_CLIENT_DOMAIN));
            jSONObject2.put(AccountConstants.KEY_CLIENT_ID, bundle.getString(AccountConstants.KEY_CLIENT_ID));
            jSONObject2.put("code_type", "authorizationCode");
        }
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            JSONObject jSONObject3 = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                String str3 = map2.get(key);
                if (!TextUtils.isEmpty(str3)) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("token", str3);
                    jSONObject3.put("actor_refresh_token", jSONObject4);
                }
            }
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("token", value);
            jSONObject3.put("account_refresh_token", jSONObject5);
            jSONObject3.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, key);
            jSONArray.put(jSONObject3);
        }
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("actor_id", str);
        }
        jSONObject.put("account_id", obj);
        jSONObject.put("upgrade_source", jSONObject2);
        jSONObject.put("device_tokens", jSONArray);
        jSONObject.put("app_name", w8Var.c.getPackageName());
        jSONObject.put("app_version", s9.b());
        jSONObject.put("map_version", w8Var.b.a());
        ga.a("PandaOAuthUpgradeRequestHelper", "Upgrade token body:" + jSONObject.toString(), new Object[0]);
        return jSONObject;
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
        return "/auth/upgradeToken";
    }
}
