package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class AppSecurityDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 7;
    public String appname;
    public String det;
    public String pkgname;

    public String toString() {
        return "{pkgname : " + this.pkgname + "; appname : " + this.appname + "; det : " + this.det + "}";
    }
}
