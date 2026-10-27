package com.qtxa.monitor.wifi;

import com.qtxa.report.DefaultDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends DefaultDetails {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f91a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f92b;
    public String c;
    public boolean d;
    public String e;
    public long f;
    public String g;
    public SimpleDateFormat h;

    public a(long j, String str, String str2, boolean z, String str3, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss", Locale.CHINA);
        this.h = simpleDateFormat;
        this.f91a = j;
        this.g = simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(this.f91a))));
        this.f92b = str;
        this.c = str2;
        this.d = z;
        this.e = str3;
        this.f = j2;
    }

    @Override // com.qtxa.report.DefaultDetails
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("stime", this.g);
            jSONObject.put("devid", this.f92b);
            jSONObject.put("devname", this.c);
            jSONObject.put("ispwd", this.d);
            jSONObject.put("result", this.e);
            jSONObject.put("time", this.f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
