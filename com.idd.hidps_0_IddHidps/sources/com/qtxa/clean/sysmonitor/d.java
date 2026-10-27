package com.qtxa.clean.sysmonitor;

import com.qtxa.qtxadefend.w;
import com.qtxa.report.HardwareDetails;
import com.qtxa.report.JniReportMessage;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class d {
    static {
        new HashMap();
    }

    public static int a(String str, String str2) {
        w.e(str, str2);
        return -1;
    }

    public static int b(String str, String str2) {
        w.e(str, str2);
        return -1;
    }

    public static int c(float f, long j, long j2, long j3, long j4, long j5, long j6, String str, String str2) {
        HardwareDetails hardwareDetails = new HardwareDetails();
        hardwareDetails.cpu = f;
        hardwareDetails.disk_total = j;
        hardwareDetails.disk_used = j2;
        hardwareDetails.io_read = j3;
        hardwareDetails.io_write = j4;
        hardwareDetails.ram_total = j5;
        hardwareDetails.ram_used = j6;
        hardwareDetails.top = str;
        hardwareDetails.idps = str2;
        JniReportMessage.java_reportMessage(1, 1200, "system monitor", 3, hardwareDetails);
        return 0;
    }
}
