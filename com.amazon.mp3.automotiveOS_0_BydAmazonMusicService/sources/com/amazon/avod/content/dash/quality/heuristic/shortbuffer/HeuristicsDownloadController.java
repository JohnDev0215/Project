package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.amazon.avod.content.dash.quality.heuristic.DownloadStatus;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
interface HeuristicsDownloadController {
    void reportDownloadProgress(long j, long j2, long j3, DownloadStatus downloadStatus, long j4);

    void reviewDownloadRequests();

    void submitDownloadRequests();
}
