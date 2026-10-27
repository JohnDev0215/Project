package com.qtxa.report;

import com.qtxa.report.JniReportMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CrashDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 9;
    public String pkgname = "";
    public String appname = "";
    public String appver = "";
    public int appstat = 0;
    public String crash = "";
    public int tag = 0;
    public String stag = "";
    public String crash_info = "";
    public String shebei = "";
    public int count = 1;
    public List<String> time_str = new ArrayList();
    public List<Long> time_long = new ArrayList();
    public long[] time = {0};

    public String getStringDate() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n{ ");
        sb.append("pkgname : ");
        sb.append(this.pkgname);
        sb.append(";\n Build: ");
        sb.append(this.shebei);
        sb.append(";\n tag : ");
        sb.append(this.tag + ":" + this.stag);
        sb.append(";\n crash_time : ");
        sb.append(Arrays.toString(this.time));
        sb.append(";\n count : ");
        sb.append(this.count);
        sb.append(";\n appname : ");
        sb.append(this.appname);
        sb.append(";\n appver : ");
        sb.append(this.appver);
        sb.append(";\n appstat : ");
        sb.append(this.appstat);
        sb.append(";\n crash_info : ");
        sb.append(this.crash);
        sb.append("}");
        return sb.toString();
    }
}
