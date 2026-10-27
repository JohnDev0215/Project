package com.amazon.avod.content;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum ContentSessionType {
    STREAMING("streaming", false),
    STREAMING_CONTINUOUS("streaming", false),
    DOWNLOAD("downloading", true),
    LIVE_CACHE("global_video_cache", false),
    CONTENT_CACHE("global_video_cache", false),
    PROGRESSIVE_PLAYBACK("downloading", true);

    private final String mDataPartition;
    private final boolean mIsDownload;

    ContentSessionType(@Nonnull String str, boolean z) {
        this.mDataPartition = str;
        this.mIsDownload = z;
    }

    public boolean isDownload() {
        return this.mIsDownload;
    }

    @Nonnull
    public String getDataPartition() {
        return this.mDataPartition;
    }

    public static boolean isCacheSession(@Nonnull ContentSessionType contentSessionType) {
        return contentSessionType == CONTENT_CACHE || contentSessionType == LIVE_CACHE;
    }

    public static boolean isStreamingSession(@Nonnull ContentSessionType contentSessionType) {
        return contentSessionType == STREAMING || contentSessionType == STREAMING_CONTINUOUS || contentSessionType == LIVE_CACHE;
    }

    public static boolean isDownloadSession(@Nonnull ContentSessionType contentSessionType) {
        return contentSessionType == DOWNLOAD || contentSessionType == PROGRESSIVE_PLAYBACK;
    }

    public static boolean needsImmediateDownloading(@Nonnull ContentSessionType contentSessionType) {
        return contentSessionType == STREAMING || contentSessionType == DOWNLOAD || contentSessionType == PROGRESSIVE_PLAYBACK;
    }
}
