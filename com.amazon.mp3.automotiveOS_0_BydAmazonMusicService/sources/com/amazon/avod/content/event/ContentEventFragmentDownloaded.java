package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.playback.ContentType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventFragmentDownloaded extends ContentEventBase {
    private final ContentType mContentType;
    private final DownloadStatistics mDownloadStatistics;
    private final FragmentInformationHolder mFragmentInformationHolder;
    private final Heuristics mHeuristics;
    private final int mNumberOfRetries;

    public ContentEventFragmentDownloaded(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull FragmentInformationHolder fragmentInformationHolder, @Nonnull DownloadStatistics downloadStatistics, @Nullable Heuristics heuristics, @Nonnegative int i, @Nonnull ContentType contentType) {
        super(playableContent, contentSessionType);
        this.mFragmentInformationHolder = (FragmentInformationHolder) Preconditions.checkNotNull(fragmentInformationHolder, "fragmentInformationHolder");
        this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        this.mHeuristics = heuristics;
        this.mNumberOfRetries = i;
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
    }

    public long getBandwidthBitsPerSecond() {
        return this.mDownloadStatistics.getBandwidth();
    }

    public long getAveragedBandwidthBitsPerSecond() {
        return this.mDownloadStatistics.getThroughput();
    }

    @Nullable
    public Heuristics getHeuristics() {
        return this.mHeuristics;
    }

    public int getNumberOfRetries() {
        return this.mNumberOfRetries;
    }

    public int getDownloadPercentage() {
        return ((this.mFragmentInformationHolder.getChunkIndex() + 1) * 100) / this.mFragmentInformationHolder.getNumChunks();
    }

    public long getDownloadTimeInNanos() {
        return this.mDownloadStatistics.getTotalTimeInNanos();
    }

    public long getTimeToTheFirstByteInNanos() {
        return this.mDownloadStatistics.getLatencyInNanos();
    }

    public long getFragmentSizeBytes() {
        return this.mDownloadStatistics.getBytesProcessed();
    }

    @Nonnull
    public FragmentInformationHolder getFragmentInformationHolder() {
        return this.mFragmentInformationHolder;
    }

    @Nonnull
    public ContentType getContentType() {
        return this.mContentType;
    }
}
