package com.qtxa.qtxadefend;

import java.nio.ByteBuffer;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f131a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f132b = true;
    boolean c = false;
    ByteBuffer d;

    k(String str) {
        this.f131a = str;
    }

    static k a(JSONObject jSONObject) throws JSONException {
        k kVar = new k(jSONObject.getString("packageName"));
        kVar.f132b = jSONObject.getBoolean("deleted");
        kVar.c = jSONObject.getBoolean("hasPermission");
        String string = jSONObject.getString("hash");
        if (!string.equals("null")) {
            kVar.d = ByteBuffer.wrap(d.b(string));
        }
        return kVar;
    }

    JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("packageName", this.f131a);
        jSONObject.put("deleted", this.f132b);
        jSONObject.put("hasPermission", this.c);
        ByteBuffer byteBuffer = this.d;
        jSONObject.put("hash", byteBuffer != null ? d.c(byteBuffer.array()) : "null");
        return jSONObject;
    }
}
