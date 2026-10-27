package org.chromium.chrome.browser.historyreport;

import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class HistoryReportJniBridge {
    public HistoryReportJniBridge() {
        new AtomicBoolean();
    }

    @CalledByNative
    public static DeltaFileEntry[] createDeltaFileEntriesArray(int i) {
        return new DeltaFileEntry[i];
    }

    @CalledByNative
    public static UsageReport[] createUsageReportsArray(int i) {
        return new UsageReport[i];
    }

    @CalledByNative
    public static void setDeltaFileEntry(DeltaFileEntry[] deltaFileEntryArr, int i, long j, String str, String str2, String str3, int i2, String str4, String str5) {
        deltaFileEntryArr[i] = new DeltaFileEntry(j, str, str2, str3, str4);
    }

    @CalledByNative
    public static void setUsageReport(UsageReport[] usageReportArr, int i, String str, String str2, long j, boolean z) {
        usageReportArr[i] = new UsageReport();
    }

    @CalledByNative
    public final void onDataChanged() {
        throw null;
    }

    @CalledByNative
    public final void onDataCleared() {
        throw null;
    }

    @CalledByNative
    public final void startReportingTask() {
        throw null;
    }

    @CalledByNative
    public final void stopReportingTask() {
        throw null;
    }
}
