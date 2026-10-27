package com.qtxa.report;

import com.qtxa.qtxadefend.w;

/* JADX INFO: loaded from: classes.dex */
public class JniReportMessage {
    public static final int BLOCK = 1;
    public static final int PASS = 3;
    public static final int WARN = 2;
    public static boolean library = false;

    public interface ReportDetails {
    }

    static {
        try {
            System.loadLibrary("iddsecuritylogjni");
            library = true;
        } catch (Throwable th) {
            library = false;
            th.printStackTrace();
        }
    }

    public static int java_reportMessage(int i, int i2, String str, int i3, ReportDetails reportDetails) {
        w.d("acd::" + i + " atttp::" + i2 + " disc::" + str + " result::" + i3 + " det::" + reportDetails.toString());
        if (library) {
            return reportMessage(i, i2, str, i3, reportDetails);
        }
        return 0;
    }

    public static native int reportMessage(int i, int i2, String str, int i3, ReportDetails reportDetails);
}
