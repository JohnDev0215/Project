package com.amazon.avod.content.downloading.progressive;

import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class ProgressiveChunkInfo implements Serializable {
    private final int mChunkIndex;
    private String mDownloadTime;
    private boolean mDownloaded;

    public boolean isDownloaded() {
        return this.mDownloaded;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.mChunkIndex);
        objArr[1] = this.mDownloaded ? "true" : "false";
        objArr[2] = this.mDownloadTime;
        return String.format(locale, "Chunk %d downloaded: %s at %s", objArr);
    }
}
