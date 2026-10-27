package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class DefaultDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 14;
    public String details;

    public String toString() {
        return "{details : " + this.details + "}";
    }
}
