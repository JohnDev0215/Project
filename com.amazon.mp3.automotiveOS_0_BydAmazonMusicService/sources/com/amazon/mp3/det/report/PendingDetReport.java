package com.amazon.mp3.det.report;

import com.amazon.mp3.util.FileUtil;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class PendingDetReport implements DetReport {
    private final String mReportTag;
    private final ReportType mReportType;
    private final File mSavedReport;

    public PendingDetReport(String reportTag, File savedReport) {
        this.mReportTag = reportTag;
        this.mReportType = reportTypeFromReportTag(reportTag);
        this.mSavedReport = savedReport;
    }

    @Override // com.amazon.mp3.det.report.DetReport
    public String buildReport() throws Throwable {
        String strLoadFromFile = FileUtil.loadFromFile(this.mSavedReport);
        return strLoadFromFile == null ? "" : strLoadFromFile;
    }

    @Override // com.amazon.mp3.det.report.DetReport
    public String getReportTag() {
        return this.mReportTag;
    }

    @Override // com.amazon.mp3.det.report.DetReport
    public ReportType getReportType() {
        return this.mReportType;
    }

    private static ReportType reportTypeFromReportTag(String reportTag) {
        for (ReportType reportType : ReportType.values()) {
            if (reportTag.contains(reportType.name())) {
                return reportType;
            }
        }
        return ReportType.CUSTOMER_SUPPORT;
    }
}
