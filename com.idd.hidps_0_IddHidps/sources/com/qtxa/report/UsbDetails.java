package com.qtxa.report;

import com.qtxa.report.JniReportMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UsbDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 21;
    public static long stime;
    public static long time;

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("stime", stime);
            jSONObject.put("time", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
