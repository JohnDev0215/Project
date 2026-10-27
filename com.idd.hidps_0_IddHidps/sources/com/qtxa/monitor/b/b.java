package com.qtxa.monitor.b;

import com.qtxa.report.DefaultDetails;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends DefaultDetails {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f74a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f75b;

    public b(String str, String str2) {
        this.f74a = str;
        this.f75b = str2;
    }

    @Override // com.qtxa.report.DefaultDetails
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", this.f74a);
            jSONObject.put("det", this.f75b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
