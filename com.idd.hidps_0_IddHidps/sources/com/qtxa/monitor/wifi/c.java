package com.qtxa.monitor.wifi;

import com.qtxa.report.DefaultDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends DefaultDetails {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f94a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f95b;
    public String c;
    public long d;
    public long e;
    public String f;
    public SimpleDateFormat g;

    public c(String str, boolean z, String str2, long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss", Locale.CHINA);
        this.g = simpleDateFormat;
        this.f94a = str;
        this.f95b = z;
        this.c = str2;
        this.d = j;
        this.f = simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(this.d))));
        this.e = j2;
    }

    @Override // com.qtxa.report.DefaultDetails
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f94a);
            jSONObject.put("ispwd", this.f95b);
            jSONObject.put("result", this.c);
            jSONObject.put("stime", this.f);
            jSONObject.put("time", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
