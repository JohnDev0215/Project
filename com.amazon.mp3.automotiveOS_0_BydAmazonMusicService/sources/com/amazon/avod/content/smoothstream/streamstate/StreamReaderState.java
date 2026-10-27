package com.amazon.avod.content.smoothstream.streamstate;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface StreamReaderState {
    int getConsumptionHead();

    long getDurationAfterPositionInNanos(long j);

    @Nullable
    SmoothStreamingURI getEarliestExistingUri();

    @Nullable
    FragmentStreamRequestResult getFragment(@Nonnull SmoothStreamingURI smoothStreamingURI) throws ContentException;

    @Nullable
    SmoothStreamingURI getLatestExistingUri();

    void markAsErrored(SmoothStreamingURI smoothStreamingURI);

    void notifyLivePointUpdated(long j);

    void onSeek(long j);

    void registerObserver(StreamStateObserver streamStateObserver);

    void releaseFragment(@Nonnull FragmentStreamRequestResult fragmentStreamRequestResult);

    void restrictToQuality(QualityLevel qualityLevel, long j);
}
