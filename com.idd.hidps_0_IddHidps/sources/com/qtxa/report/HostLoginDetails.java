package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class HostLoginDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 28;
    public static String det;
    public static boolean remote;
    public static String result;
    public static long stime;
    public static long time;
    public static String user;

    public String toString() {
        return "{user : " + user + ", remote : " + remote + ", result : " + result + ", det : " + det + ", stime : " + stime + ", time : " + time + "}";
    }
}
