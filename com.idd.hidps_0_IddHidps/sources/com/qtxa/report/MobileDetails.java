package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class MobileDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 19;
    public static String det;
    public static String model;

    public String toString() {
        return "{model : " + model + ", det : " + det + "}";
    }
}
