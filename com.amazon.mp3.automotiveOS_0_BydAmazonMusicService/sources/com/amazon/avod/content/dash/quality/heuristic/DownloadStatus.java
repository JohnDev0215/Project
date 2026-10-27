package com.amazon.avod.content.dash.quality.heuristic;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadStatus {
    Success(0),
    Timeout(1),
    Failed(2),
    NoMoreSpace(3),
    PartSuccess(4),
    EndOfStream(5),
    Existing(6),
    Cancelled(7),
    MaxDownloadStatus(8);

    private final int mStatusCode;

    DownloadStatus(int i) {
        this.mStatusCode = i;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }
}
