package org.chromium.chrome.browser.history;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class HistoryDeletionInfo {
    public final long a;

    public HistoryDeletionInfo(long j) {
        this.a = j;
    }

    @CalledByNative
    public static HistoryDeletionInfo create(long j) {
        return new HistoryDeletionInfo(j);
    }
}
