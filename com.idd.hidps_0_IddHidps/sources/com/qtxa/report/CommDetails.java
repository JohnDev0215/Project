package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class CommDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 11;
    public String det;

    public String toString() {
        return "{det : " + this.det + "}";
    }
}
