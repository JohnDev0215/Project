package com.qtxa.report;

import com.qtxa.report.JniReportMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 20;
    public String devid;
    public String devname;
    public boolean ispwd;
    public String result;
    public long stime;
    public long time;

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("devid", this.devid);
            jSONObject.put("devname", this.devname);
            jSONObject.put("ispwd", this.ispwd);
            jSONObject.put("result", this.result);
            jSONObject.put("stime", this.stime);
            jSONObject.put("time", this.time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
