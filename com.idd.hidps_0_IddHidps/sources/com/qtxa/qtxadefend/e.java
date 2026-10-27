package com.qtxa.qtxadefend;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static e f119b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private v f120a;

    private e() {
    }

    private String b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "" : "error" : "alert" : "info";
    }

    public static e c() {
        if (f119b == null) {
            f119b = new e();
        }
        return f119b;
    }

    public int a(String str, String[] strArr, int i, String str2) {
        int i2 = 0;
        String str3 = "";
        if (strArr != null) {
            for (String str4 : strArr) {
                str3 = str3 + str4 + ";";
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str2);
            jSONObject.put("title", "ids_ducarsecurity_title");
            jSONObject.put("event", str);
            jSONObject.put("params", str3);
            jSONObject.put("icon", b(i));
            String string = new JSONObject(this.f120a.c(jSONObject.toString())).getString("result");
            if (string.equalsIgnoreCase("yes")) {
                i2 = 1;
            } else if (!string.equalsIgnoreCase("no")) {
                i2 = string.equalsIgnoreCase("ok") ? 2 : -1;
            }
            return i2;
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void d(v vVar, Context context) {
        this.f120a = vVar;
    }

    public void e() {
        this.f120a = null;
    }
}
