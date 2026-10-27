package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class RootCheckDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 6;
    public String det;

    public String toString() {
        return "{det : " + this.det + "}";
    }
}
