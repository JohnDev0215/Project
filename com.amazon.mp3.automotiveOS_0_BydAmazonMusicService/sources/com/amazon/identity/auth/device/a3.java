package com.amazon.identity.auth.device;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class a3 {
    public static final String d = "a3";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f168a;
    public y5 b;
    public o5 c;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f169a;
        public String b;
        public long c;
        public long d;
        public long e;

        public a(String str, String str2, String str3, String str4) {
            this.f169a = str;
            this.b = str2;
            this.c = Long.parseLong(str3) * 1000;
            this.d = System.currentTimeMillis() + this.c;
            this.e = Long.parseLong(str4);
        }
    }

    public a3(t5 t5Var, y5 y5Var) {
        this.f168a = t5Var;
        this.b = y5Var;
        this.c = (o5) t5Var.getSystemService("dcp_device_info");
    }

    public JSONObject a() throws JSONException {
        String packageName = this.f168a.getPackageName();
        Long lA = la.a(this.f168a, packageName);
        String string = lA != null ? Long.toString(lA.longValue()) : "defaultAppVersion";
        try {
            String strD = this.c.d();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("domain", "Device");
            if (TextUtils.isEmpty(packageName)) {
                packageName = "defaultAppName";
            }
            jSONObject.put("app_name", packageName);
            jSONObject.put("app_version", string);
            jSONObject.put("device_model", Build.MODEL);
            jSONObject.put("os_version", Integer.toString(Build.VERSION.SDK_INT));
            jSONObject.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, this.c.f());
            jSONObject.put("device_serial", strD);
            return jSONObject;
        } catch (UnsupportedOperationException e) {
            Log.e(ga.a(d), "Got an unsupported operation exception while trying to get the device serial number", e);
            return null;
        }
    }

    public void a(JSONObject jSONObject, int i, long j) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (i > 0) {
            jSONObject2.put("code_length", Integer.toString(i));
        }
        if (j > 0) {
            jSONObject2.put("code_duration", Long.toString(j / 1000));
        }
        if (jSONObject2.length() > 0) {
            jSONObject.put("code_properties", jSONObject2);
        }
    }

    public a a(JSONObject jSONObject) {
        try {
            return new a(jSONObject.getString("public_code"), jSONObject.getString("private_code"), jSONObject.getString("expires_in"), jSONObject.getString("polling_interval_in_seconds"));
        } catch (JSONException e) {
            Log.e(ga.a(d), "JSONException while parsing createCodePair response", e);
            return null;
        }
    }
}
