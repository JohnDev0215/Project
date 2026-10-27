package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class FileDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 22;
    public static String acti;
    public static String det;
    public static String path;

    public String toString() {
        return "{path : " + path + ", acti : " + acti + ", det : " + det + "}";
    }
}
