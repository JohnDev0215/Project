package com.amazon.mp3.det.report;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class CustomerServiceReport extends AbstractNewDetReport {
    public CustomerServiceReport(Context context) {
        super(context, ReportType.CUSTOMER_SUPPORT);
    }

    @Override // com.amazon.mp3.det.report.AbstractNewDetReport
    public StringBuilder createReportBody() {
        return readLogs(true);
    }
}
