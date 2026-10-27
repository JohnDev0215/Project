package com.qtxa.report;

import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class HardwareDetails implements JniReportMessage.ReportDetails {
    public static final int REPORT_FLAG = 10;
    public float cpu;
    public long disk_total;
    public long disk_used;
    public long io_read;
    public long io_write;
    public long ram_total;
    public long ram_used;
    public String top = "[{\"appname\",\"com.qt\"},\"appname\",\"com.qt\"}]";
    public String idps = "[{\"appname\",\"com.qt\"},\"appname\",\"com.qt\"}]";

    public String toString() {
        return "{ cpu : " + this.cpu + "; disk_total : " + this.disk_total + "; disk_used : " + this.disk_used + "; io_read : " + this.io_read + "; io_write : " + this.io_write + "; ram_total : " + this.ram_total + "; ram_used : " + this.ram_used + "; top : " + this.top + "; idps : " + this.idps + " }";
    }
}
