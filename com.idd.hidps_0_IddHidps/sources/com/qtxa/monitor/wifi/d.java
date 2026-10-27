package com.qtxa.monitor.wifi;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f96a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f97b;
    private boolean c;
    private boolean d;
    private boolean e;
    private String f;
    private long g;

    public d(int i, boolean z, boolean z2, String str, long j, String str2, boolean z3) {
        this.f96a = i;
        this.d = z;
        this.e = z2;
        this.f = str;
        this.g = j;
        this.f97b = str2;
        this.c = z3;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mWifiState", this.f96a);
            jSONObject.put("mSsid", this.f97b);
            jSONObject.put("isPwdNeeded", this.c);
            jSONObject.put("mApOpened", this.d);
            jSONObject.put("mAPConnected", this.e);
            jSONObject.put("mAPConnMac", this.f);
            jSONObject.put("mEventTime", this.g);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
