package com.qtxa.monitor.a;

import com.qtxa.report.DefaultDetails;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends DefaultDetails {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f69b;
    public long c;
    public String d;
    public String e;

    public b(String str, String str2, long j, String str3, String str4) {
        this.f68a = str;
        this.f69b = str2;
        this.c = j;
        this.d = str3;
        this.e = str4;
    }

    @Override // com.qtxa.report.DefaultDetails
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pver", this.f68a);
            jSONObject.put("dver", this.f69b);
            jSONObject.put("time", this.c);
            jSONObject.put("result", this.d);
            jSONObject.put("det", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
