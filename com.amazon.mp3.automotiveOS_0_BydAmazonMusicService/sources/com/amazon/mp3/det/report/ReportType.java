package com.amazon.mp3.det.report;

/* JADX INFO: loaded from: classes3.dex */
public enum ReportType {
    CUSTOMER_SUPPORT("BinaryFile"),
    UNCAUGHT_EXCEPTION("CrashReport"),
    CAUGHT_EXCEPTION("CrashReport");

    private final String mContentName;

    public String getContentName() {
        return this.mContentName;
    }

    ReportType(String contentName) {
        this.mContentName = contentName;
    }
}
