package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class UpgradeDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 30;
    public static String det;
    public static String dver;
    public static String pver;
    public static String result;
    public static long time;

    public String toString() {
        return "{pver : " + pver + ", dver : " + dver + ", result : " + result + ", time : " + time + ", det : " + det + "}";
    }
}
