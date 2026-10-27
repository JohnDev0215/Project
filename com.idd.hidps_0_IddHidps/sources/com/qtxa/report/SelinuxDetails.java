package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class SelinuxDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 8;
    public String det;

    public String toString() {
        return "{det : " + this.det + "}";
    }
}
