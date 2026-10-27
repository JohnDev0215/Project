package com.qtxa.report;

import com.qtxa.report.JniReportMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WifiDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 17;
    public static boolean ispwd;
    public static String name;
    public static String result;
    public static long stime;
    public static long time;

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", name);
            jSONObject.put("ispwd", ispwd);
            jSONObject.put("result", result);
            jSONObject.put("stime", stime);
            jSONObject.put("time", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
