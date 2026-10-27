package com.amazon.mp3.download.library.scanner;

import android.media.MediaScannerConnection;

/* JADX INFO: loaded from: classes3.dex */
interface IMediaScannerClient extends MediaScannerConnection.MediaScannerConnectionClient {
    Object getLockObject();

    boolean isCompleted();

    void setConnection(MediaScannerConnection connection);
}
