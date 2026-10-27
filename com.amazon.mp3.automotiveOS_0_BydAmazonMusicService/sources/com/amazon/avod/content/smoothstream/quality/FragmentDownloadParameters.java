package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class FragmentDownloadParameters {
    private final long mSlowFragmentTimeoutInNanos;
    private final SmoothStreamingURI mSsUri;

    public FragmentDownloadParameters(@Nonnull SmoothStreamingURI smoothStreamingURI, long j) {
        this.mSsUri = (SmoothStreamingURI) Preconditions.checkNotNull(smoothStreamingURI);
        Preconditions.checkArgument(j >= 0);
        this.mSlowFragmentTimeoutInNanos = j;
    }

    @Nonnull
    public SmoothStreamingURI getSmoothStreamingURI() {
        return this.mSsUri;
    }

    @Nonnull
    public long getSlowFragmentTimeoutInNanos() {
        return this.mSlowFragmentTimeoutInNanos;
    }
}
