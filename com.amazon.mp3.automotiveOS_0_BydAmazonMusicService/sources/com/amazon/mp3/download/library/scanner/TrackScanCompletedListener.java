package com.amazon.mp3.download.library.scanner;

import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public interface TrackScanCompletedListener {
    void onScanCompleted(String filePath, Uri uri);
}
