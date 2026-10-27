package com.qtxa.monitor.usb;

import com.qtxa.report.DefaultDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends DefaultDetails {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SimpleDateFormat f84a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss", Locale.CHINA);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f85b;
    public String c;
    public String d;

    public b(long j, long j2) {
        if (j2 == 0) {
            this.c = "usb_connected";
        } else {
            this.c = "usb_disconnected";
        }
        this.f85b = j2;
        this.d = this.f84a.format(new Date(Long.parseLong(String.valueOf(j))));
    }

    @Override // com.qtxa.report.DefaultDetails
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", this.c);
            jSONObject.put("stime", this.d);
            jSONObject.put("time", this.f85b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
