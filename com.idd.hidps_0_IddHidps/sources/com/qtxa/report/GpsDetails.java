package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class GpsDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 18;
    public static String det;
    public static String serid;
    public static String sername;

    public String toString() {
        return "{serid : " + serid + ", sername : " + sername + ", det : " + det + "}";
    }
}
