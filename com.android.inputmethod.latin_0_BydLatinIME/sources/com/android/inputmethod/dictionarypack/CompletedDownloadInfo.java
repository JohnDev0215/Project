package com.android.inputmethod.dictionarypack;

/* JADX INFO: loaded from: classes2.dex */
public class CompletedDownloadInfo {
    final long mDownloadId;
    final int mStatus;
    final String mUri;

    public CompletedDownloadInfo(final String uri, final long downloadId, final int status) {
        this.mUri = uri;
        this.mDownloadId = downloadId;
        this.mStatus = status;
    }

    public boolean wasSuccessful() {
        return 8 == this.mStatus;
    }
}
