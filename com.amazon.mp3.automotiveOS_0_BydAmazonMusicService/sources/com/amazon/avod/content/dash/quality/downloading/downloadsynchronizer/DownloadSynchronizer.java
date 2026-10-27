package com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public interface DownloadSynchronizer {
    void onFragmentDownloaded(SmoothStreamingURI smoothStreamingURI);

    void onSeeked(long j);

    boolean shouldDownload(SmoothStreamingURI smoothStreamingURI);
}
