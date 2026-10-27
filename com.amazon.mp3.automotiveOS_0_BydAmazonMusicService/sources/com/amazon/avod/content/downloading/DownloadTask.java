package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.LocalContentState;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadTask extends LocalContentState {
    boolean canServiceRequest(SmoothStreamingURI smoothStreamingURI);

    void cancel();

    void dispose();

    int getAverageVideoBandwidthBps();

    @Nonnull
    StreamType getStreamType();

    void notifyFragmentCorrupt(SmoothStreamingURI smoothStreamingURI);

    void notifyLivePointUpdated(long j);

    void onContentContextChanged();

    void releaseFragment(@Nonnull FragmentStreamRequestResult fragmentStreamRequestResult);

    @Nullable
    FragmentStreamRequestResult requestFragment(SmoothStreamingURI smoothStreamingURI) throws ContentException;

    void restrictToQuality(@Nonnull QualityLevel qualityLevel, @Nonnull long j);

    void seek(long j);

    void start();
}
