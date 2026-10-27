package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c3 {
    public static final String d = "c3";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f257a;
    public y5 b;
    public o5 c;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f258a;
        public long b;

        public a(String str, String str2) {
            this.f258a = str;
            this.b = Long.parseLong(str2) * 1000;
        }
    }

    public c3(t5 t5Var, y5 y5Var) {
        this.f257a = t5Var;
        this.b = y5Var;
        this.c = (o5) t5Var.getSystemService("dcp_device_info");
    }

    public String a(long j, String str, String str2) throws JSONException {
        a3 a3Var = new a3(this.f257a, this.b);
        JSONObject jSONObjectA = a3Var.a();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("access_token", str);
        jSONObject.put("actor_id", str2);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code_data", jSONObjectA);
        jSONObject2.put("auth_data", jSONObject);
        a3Var.a(jSONObject2, 0, j);
        a7.a(a7.a((Context) this.f257a, this.c.d()), jSONObject2);
        return jSONObject2.toString();
    }

    public a a(JSONObject jSONObject) {
        try {
            return new a(jSONObject.getString("code"), jSONObject.getString("expires_in"));
        } catch (JSONException e) {
            Log.e(ga.a(d), "JSONException while parsing generatePreAuthorizedCode response, probably due to server response is missing some data", e);
            return null;
        }
    }
}
