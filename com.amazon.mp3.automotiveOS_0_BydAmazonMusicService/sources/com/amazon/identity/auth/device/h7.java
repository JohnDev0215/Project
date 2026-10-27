package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.google.firebase.perf.FirebasePerformance;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h7 extends f7 {
    public final String d;
    public final String e;
    public final String f;

    public h7(Context context, String str, String str2, String str3) {
        super(t5.a(context));
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    @Override // com.amazon.identity.auth.device.f7
    public Map<String, String> a() {
        return super.a();
    }

    @Override // com.amazon.identity.auth.device.f7
    public AuthenticationMethod b() {
        return null;
    }

    @Override // com.amazon.identity.auth.device.f7
    public JSONObject b(y5 y5Var) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.e);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("refreshToken", jSONObject);
        if (!e.g(this.f)) {
            jSONObject2.put("keyIdentifier", this.f);
        }
        return jSONObject2;
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
        return "/auth/mobile/encryptionkey";
    }

    @Override // com.amazon.identity.auth.device.f7
    public String a(JSONObject jSONObject) {
        return da.a(jSONObject, "error_index", (String) null);
    }
}
