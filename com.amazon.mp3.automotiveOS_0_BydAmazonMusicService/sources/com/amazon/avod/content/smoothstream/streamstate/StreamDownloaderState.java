package com.amazon.avod.content.smoothstream.streamstate;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface StreamDownloaderState extends StreamReaderState {
    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    /* synthetic */ int getConsumptionHead();

    int getHighestObtainedQualityBitrate(SmoothStreamingURI smoothStreamingURI);

    @Nullable
    SmoothStreamingURI getNextRequest();

    boolean isUriDownloaded(SmoothStreamingURI smoothStreamingURI);

    void onSuccessfulDownload(SmoothStreamingURI smoothStreamingURI);

    boolean shouldDownloadUri(SmoothStreamingURI smoothStreamingURI);
}
