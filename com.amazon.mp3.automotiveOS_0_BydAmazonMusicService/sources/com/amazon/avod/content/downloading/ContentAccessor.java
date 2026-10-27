package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.LocalContentState;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentAccessor extends LocalContentState {
    void disableStream(@Nonnull StreamType streamType);

    void dispose();

    void enableStream(@Nonnull StreamType streamType);

    int getAverageVideoBandwidthBps();

    @Nonnull
    ContentUrlSelector getContentUrlSelector();

    long getFirstAvailableTimeInNanos(@Nullable StreamType streamType);

    @Nullable
    FragmentStreamRequestResult getFragmentStream(SmoothStreamingURI smoothStreamingURI) throws ContentException;

    long getLastAvailableTimeInNanos(@Nullable StreamType streamType);

    void notifyFragmentStreamCorrupt(SmoothStreamingURI smoothStreamingURI);

    void notifyLivePointUpdated(long j);

    void onContentContextChanged();

    void releaseFragment(@Nonnull FragmentStreamRequestResult fragmentStreamRequestResult);

    void restrictToQuality(@Nonnull QualityLevel qualityLevel, @Nonnull long j);

    void seek(long j);

    void start(ContentSessionContext contentSessionContext) throws ContentException;

    void stop(boolean z);
}
