package com.amazon.avod.content.event;

import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentInformationHolder {
    private final String mAbsoluteUrl;
    private final int mBitrate;
    private final int mChunkIndex;
    private final ContentUrl mContentUrl;
    private final double mDisplayAspectRatio;
    private final long mDownloadChunkSize;
    private final long mDurationInNanos;
    private final FragmentType mFragmentType;
    private final int mHeight;
    private final boolean mIsAudio;
    private final boolean mIsInitFragment;
    private final boolean mIsVideo;
    private final boolean mLastInStream;
    private final int mNumChunks;
    private final long mPresentationTimeInNanos;
    private final double mQualityScore;
    private final StreamType mStreamType;

    @Nullable
    private final Map<String, List<String>> mUnformattedHeaders;
    private final int mWidth;

    FragmentInformationHolder(ContentUrl contentUrl, long j, String str, boolean z, int i, boolean z2, boolean z3, long j2, boolean z4, long j3, int i2, int i3, @Nonnull FragmentType fragmentType, @Nonnull StreamType streamType, @Nullable Map<String, List<String>> map, int i4, int i5, double d, double d2) {
        this.mContentUrl = contentUrl;
        this.mDownloadChunkSize = j;
        this.mAbsoluteUrl = str;
        this.mIsInitFragment = z;
        this.mChunkIndex = i;
        this.mIsVideo = z2;
        this.mIsAudio = z3;
        this.mDurationInNanos = j2;
        this.mLastInStream = z4;
        this.mPresentationTimeInNanos = j3;
        this.mNumChunks = i2;
        this.mBitrate = i3;
        this.mFragmentType = (FragmentType) Preconditions.checkNotNull(fragmentType, "fragmentType");
        this.mStreamType = (StreamType) Preconditions.checkNotNull(streamType, "streamType");
        this.mUnformattedHeaders = map;
        this.mWidth = i4;
        this.mHeight = i5;
        this.mDisplayAspectRatio = d;
        this.mQualityScore = d2;
    }

    @Nonnull
    public static FragmentInformationHolder extractFragmentData(@Nonnull SmoothStreamingURI smoothStreamingURI, @Nullable ContentUrl contentUrl, @Nullable DownloadStatistics downloadStatistics) {
        int i;
        int maxHeight;
        Preconditions.checkNotNull(smoothStreamingURI, "sSURI");
        String absoluteUrl = contentUrl != null ? smoothStreamingURI.getAbsoluteUrl(contentUrl.getUrl()) : null;
        int bitrate = smoothStreamingURI.getQualityLevel() != null ? smoothStreamingURI.getQualityLevel().getBitrate() : -1;
        int numChunks = smoothStreamingURI.getStream() != null ? smoothStreamingURI.getStream().getNumChunks() : -1;
        if (smoothStreamingURI.getQualityLevel() == null || !smoothStreamingURI.isVideo()) {
            i = -1;
            maxHeight = -1;
        } else {
            VideoQualityLevel videoQualityLevel = (VideoQualityLevel) smoothStreamingURI.getQualityLevel();
            int maxWidth = videoQualityLevel.getMaxWidth();
            maxHeight = videoQualityLevel.getMaxHeight();
            i = maxWidth;
        }
        return new FragmentInformationHolder(contentUrl, smoothStreamingURI.getDownloadChunkSize(), absoluteUrl, smoothStreamingURI.isInitFragment(), smoothStreamingURI.getChunkIndex(), smoothStreamingURI.isVideo(), smoothStreamingURI.isAudio(), smoothStreamingURI.getDurationInNanos(), smoothStreamingURI.isLastInStream(), smoothStreamingURI.getPresentationTimeInNanos(), numChunks, bitrate, smoothStreamingURI.getFragmentType(), smoothStreamingURI.getStream().getType(), downloadStatistics != null ? downloadStatistics.getUnformattedHeaders() : null, i, maxHeight, smoothStreamingURI.getStream().getDisplayAspectRatio(), smoothStreamingURI.getQualityScore());
    }

    @Nullable
    public ContentUrl getContentUrl() {
        return this.mContentUrl;
    }

    public long getDownloadChunkSize() {
        return this.mDownloadChunkSize;
    }

    @Nullable
    public Map<String, List<String>> getUnformattedDownloadHeaders() {
        return this.mUnformattedHeaders;
    }

    @Nullable
    public String getAbsoluteUrl() {
        return this.mAbsoluteUrl;
    }

    public boolean isInitFragment() {
        return this.mIsInitFragment;
    }

    public int getChunkIndex() {
        return this.mChunkIndex;
    }

    public boolean isVideo() {
        return this.mIsVideo;
    }

    public boolean isAudio() {
        return this.mIsAudio;
    }

    public long getDurationInNanos() {
        return this.mDurationInNanos;
    }

    public boolean isLastInStream() {
        return this.mLastInStream;
    }

    public long getPresentationTimeInNanos() {
        return this.mPresentationTimeInNanos;
    }

    public int getNumChunks() {
        return this.mNumChunks;
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    @Nonnull
    public FragmentType getFragmentType() {
        return this.mFragmentType;
    }

    @Nonnull
    public StreamType getStreamType() {
        return this.mStreamType;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public double getDisplayAspectRatio() {
        return this.mDisplayAspectRatio;
    }

    public double getQualityScore() {
        return this.mQualityScore;
    }
}
