package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class PermissionDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 26;
    public static String appname;
    public static String det;
    public static String permission;
    public static String pkgname;

    public String toString() {
        return "{pkgname : " + pkgname + ", appname : " + appname + ", permission : " + permission + ", det : " + det + "}";
    }
}
