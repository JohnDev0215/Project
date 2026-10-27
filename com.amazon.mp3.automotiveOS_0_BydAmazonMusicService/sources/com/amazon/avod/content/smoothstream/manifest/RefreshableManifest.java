package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshableManifest implements Manifest {
    private final long mAvailabilityStartTimeMillis;
    private final ContentManagementEventBus mContentManagementEventBus;
    private final boolean mIsHD;
    private final boolean mIsHdr;
    private Manifest mLatestManifest;
    private final ReadWriteLock mLock = new ReentrantReadWriteLock();
    private final TimeSpan mMinUpdatePeriod;
    private final int mNumStreams;
    private final List<RefreshableStreamIndex> mRefreshableStreamIndices;
    private final TimeSpan mSuggestedFromMargin;

    @Nonnull
    public static RefreshableManifest newManifest(@Nonnull Manifest manifest, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        StreamIndex refreshableStreamIndex;
        Preconditions.checkNotNull(manifest, "originalManifest");
        Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(manifest.getNumStreams());
        for (StreamIndex streamIndex : manifest.getStreams()) {
            if (streamIndex.getType() == StreamType.IMAGE) {
                refreshableStreamIndex = new RefreshableImageStreamIndex(streamIndex);
            } else {
                refreshableStreamIndex = new RefreshableStreamIndex(streamIndex);
            }
            arrayListNewArrayListWithCapacity.add(refreshableStreamIndex);
        }
        return new RefreshableManifest(manifest, arrayListNewArrayListWithCapacity, contentManagementEventBus);
    }

    private RefreshableManifest(@Nonnull Manifest manifest, @Nonnull List<RefreshableStreamIndex> list, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        this.mRefreshableStreamIndices = (List) Preconditions.checkNotNull(list, "refreshableStreamIndices");
        Manifest manifest2 = (Manifest) Preconditions.checkNotNull(manifest, "originalManifest");
        this.mLatestManifest = manifest2;
        this.mNumStreams = manifest2.getNumStreams();
        this.mIsHD = this.mLatestManifest.isHD();
        this.mIsHdr = this.mLatestManifest.isHdr();
        this.mAvailabilityStartTimeMillis = this.mLatestManifest.getAvailabilityStartTimeMillis();
        this.mSuggestedFromMargin = this.mLatestManifest.getSuggestedFrontMargin();
        this.mMinUpdatePeriod = this.mLatestManifest.getMinimumUpdatePeriod();
        this.mContentManagementEventBus = contentManagementEventBus;
    }

    public void refresh(@Nonnull Manifest manifest, @Nullable RefreshableManifestValidator refreshableManifestValidator, @Nonnull ContentUrl contentUrl) throws ContentException {
        refresh(manifest, refreshableManifestValidator, false, contentUrl);
    }

    public void refresh(@Nonnull Manifest manifest, @Nullable RefreshableManifestValidator refreshableManifestValidator, boolean z, @Nonnull ContentUrl contentUrl) throws ContentException {
        Preconditions.checkNotNull(manifest, "manifest");
        Preconditions.checkNotNull(contentUrl, "currentContentUrl");
        this.mLock.writeLock().lock();
        Manifest manifest2 = this.mLatestManifest;
        if (refreshableManifestValidator != null && z) {
            try {
                if (manifest2.isDynamic()) {
                    refreshableManifestValidator.validate(this.mLatestManifest, manifest, contentUrl);
                }
            } finally {
                if (this.mLatestManifest != manifest2) {
                    manifest2.release();
                } else {
                    manifest.release();
                }
                this.mLock.writeLock().unlock();
            }
        }
        triggerNewVideoPeriodEventsBasedOnManifestRefresh(this.mLatestManifest, manifest, this.mContentManagementEventBus);
        this.mLatestManifest = manifest;
        for (int i = 0; i < this.mLatestManifest.getNumStreams() && i < this.mRefreshableStreamIndices.size(); i++) {
            this.mRefreshableStreamIndices.get(i).refresh(this.mLatestManifest.getStreams().get(i));
        }
    }

    private static void triggerNewVideoPeriodEventsBasedOnManifestRefresh(@Nonnull Manifest manifest, @Nonnull Manifest manifest2, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        if (manifest.getManifestHandle() == 0 || manifest.getVideoStream() == null || manifest2.getVideoStream() == null) {
            return;
        }
        triggerNewVideoPeriodEventsBasedOnManifestRefresh(manifest.getVideoStream(), manifest2.getVideoStream(), contentManagementEventBus);
    }

    protected static void triggerNewVideoPeriodEventsBasedOnManifestRefresh(@Nonnull StreamIndex streamIndex, @Nonnull StreamIndex streamIndex2, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        Preconditions.checkNotNull(streamIndex, "oldIndex");
        Preconditions.checkNotNull(streamIndex2, "newIndex");
        Preconditions.checkNotNull(streamIndex.getType(), "oldIndex.getType()");
        Preconditions.checkNotNull(streamIndex2.getType(), "newIndex.getType()");
        StreamType type = streamIndex.getType();
        StreamType streamType = StreamType.VIDEO;
        Preconditions.checkState(type.equals(streamType), "we only test on video indexes");
        Preconditions.checkState(streamIndex2.getType().equals(streamType), "we only test on video indexes");
        TreeSet treeSet = new TreeSet(RefreshableManifestValidator.getPeriodIds(streamIndex));
        TreeSet treeSet2 = new TreeSet(RefreshableManifestValidator.getPeriodIds(streamIndex2));
        if (streamIndex.getType() != null && streamIndex2.getType() != null) {
            Preconditions.checkState(streamIndex.getType().equals(streamIndex2.getType()));
        }
        if (streamIndex.getLanguage() != null && streamIndex2.getLanguage() != null) {
            Preconditions.checkState(streamIndex.getLanguage().equals(streamIndex2.getLanguage()));
        }
        TreeSet<String> treeSetFindNewPeriodEventsBasedOnManifestRefresh = findNewPeriodEventsBasedOnManifestRefresh(treeSet, treeSet2);
        if (treeSetFindNewPeriodEventsBasedOnManifestRefresh.isEmpty()) {
            return;
        }
        contentManagementEventBus.postEvent(new ManifestRefreshFoundNewPeriodEvent(treeSetFindNewPeriodEventsBasedOnManifestRefresh.first(), streamIndex2.getType(), streamIndex2.getLanguage()));
    }

    private static TreeSet<String> findNewPeriodEventsBasedOnManifestRefresh(@Nullable TreeSet<String> treeSet, @Nullable TreeSet<String> treeSet2) {
        if (treeSet == null) {
            treeSet = new TreeSet<>();
        }
        if (treeSet2 == null) {
            treeSet2 = new TreeSet<>();
        }
        TreeSet<String> treeSet3 = new TreeSet<>((SortedSet<String>) treeSet2);
        treeSet3.removeAll(treeSet);
        return treeSet3;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public TimeSpan getDuration() {
        readLock();
        try {
            return this.mLatestManifest.getDuration();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public int getNumStreams() {
        return this.mNumStreams;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public List<StreamIndex> getStreams() {
        readLock();
        try {
            return this.mRefreshableStreamIndices;
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public StreamIndex getVideoStream() {
        readLock();
        try {
            for (RefreshableStreamIndex refreshableStreamIndex : this.mRefreshableStreamIndices) {
                if (refreshableStreamIndex.isVideo()) {
                    return refreshableStreamIndex;
                }
            }
            return null;
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public StreamIndex getAudioStream(@Nonnull String str, @Nullable String str2) {
        readLock();
        try {
            Preconditions.checkNotNull(str, "fourCC");
            int bitrate = Integer.MAX_VALUE;
            int bitrate2 = Integer.MIN_VALUE;
            int i = -1;
            int i2 = -1;
            for (int i3 = 0; i3 < getNumStreams(); i3++) {
                RefreshableStreamIndex refreshableStreamIndex = this.mRefreshableStreamIndices.get(i3);
                if (refreshableStreamIndex.getType() == StreamType.AUDIO) {
                    QualityLevel[] sortedQualityLevels = refreshableStreamIndex.getSortedQualityLevels(0);
                    if (str.equalsIgnoreCase(sortedQualityLevels[0].getFourCC()) && (str2 == null || str2.equalsIgnoreCase(refreshableStreamIndex.getLanguage()))) {
                        QualityLevel qualityLevel = sortedQualityLevels[0];
                        QualityLevel qualityLevel2 = sortedQualityLevels[sortedQualityLevels.length - 1];
                        if (qualityLevel.getBitrate() < bitrate) {
                            bitrate = qualityLevel.getBitrate();
                            i2 = i3;
                        }
                        if (qualityLevel2.getBitrate() > bitrate2) {
                            bitrate2 = qualityLevel2.getBitrate();
                            i = i3;
                        }
                    }
                }
            }
            if (SmoothStreamingPlaybackConfig.INSTANCE.shouldSelectLowerAudioBitrates()) {
                i = i2;
            }
            if (i < 0 && str2 != null) {
                return getAudioStream(str, null);
            }
            return i >= 0 ? this.mRefreshableStreamIndices.get(i) : null;
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public StreamIndex getSubtitleStream() {
        readLock();
        try {
            for (RefreshableStreamIndex refreshableStreamIndex : this.mRefreshableStreamIndices) {
                if (refreshableStreamIndex.getType() == StreamType.SUBTITLES) {
                    return refreshableStreamIndex;
                }
            }
            return null;
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public StreamIndex getImageStream() {
        readLock();
        try {
            for (RefreshableStreamIndex refreshableStreamIndex : this.mRefreshableStreamIndices) {
                if (refreshableStreamIndex.getType() == StreamType.IMAGE) {
                    return refreshableStreamIndex;
                }
            }
            return null;
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isHD() {
        return this.mIsHD;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isHdr() {
        return this.mIsHdr;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isEncrypted() {
        readLock();
        try {
            return this.mLatestManifest.isEncrypted();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public ProtectionHeader getProtectionHeader() {
        readLock();
        try {
            return this.mLatestManifest.getProtectionHeader();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public void release() {
        readLock();
        try {
            this.mLatestManifest.release();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public TimeSpan getTimeshiftBufferDuration() {
        readLock();
        try {
            return this.mLatestManifest.getTimeshiftBufferDuration();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public Set<String> getAvailableAudioLanguages(String str) {
        readLock();
        try {
            return this.mLatestManifest.getAvailableAudioLanguages(str);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public Set<String> getAvailableAudioLanguagesAcrossAudioStreams() {
        readLock();
        try {
            return this.mLatestManifest.getAvailableAudioLanguagesAcrossAudioStreams();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public long getAvailabilityStartTimeMillis() {
        return this.mAvailabilityStartTimeMillis;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getManifestStartTime() {
        readLock();
        try {
            return this.mLatestManifest.getManifestStartTime();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getManifestEndTime() {
        readLock();
        try {
            return this.mLatestManifest.getManifestEndTime();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getSuggestedFrontMargin() {
        return this.mSuggestedFromMargin;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getMinimumUpdatePeriod() {
        return this.mMinUpdatePeriod;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public List<StreamIndex> getSupportedAudioStreams(@Nullable String str, @Nonnull List<String> list) {
        readLock();
        try {
            Preconditions.checkNotNull(list, "supportedCodecs");
            ImmutableList.Builder builder = ImmutableList.builder();
            for (RefreshableStreamIndex refreshableStreamIndex : this.mRefreshableStreamIndices) {
                if (refreshableStreamIndex.getType() == StreamType.AUDIO) {
                    QualityLevel[] sortedQualityLevels = refreshableStreamIndex.getSortedQualityLevels(0);
                    if (sortedQualityLevels.length > 0 && list.contains(sortedQualityLevels[0].getFourCC()) && (str == null || str.equalsIgnoreCase(refreshableStreamIndex.getLanguage()))) {
                        builder.add(refreshableStreamIndex);
                    }
                }
            }
            ImmutableList immutableListBuild = builder.build();
            return (!immutableListBuild.isEmpty() || str == null) ? immutableListBuild : getSupportedAudioStreams(null, list);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public long getManifestHandle() {
        readLock();
        try {
            return this.mLatestManifest.getManifestHandle();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isDynamic() {
        readLock();
        try {
            return this.mLatestManifest.isDynamic();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean hasMultiPeriodSupport() {
        readLock();
        try {
            return this.mLatestManifest.hasMultiPeriodSupport();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public Boolean isPatternTemplateManifest() {
        readLock();
        try {
            return this.mLatestManifest.isPatternTemplateManifest();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public QualityLevel getHighestVideoQualityLevel() {
        return this.mLatestManifest.getHighestVideoQualityLevel();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean hasStitchedAds() {
        return this.mLatestManifest.hasStitchedAds();
    }

    private void readLock() {
        this.mLock.readLock().lock();
    }

    private void unlock() {
        this.mLock.readLock().unlock();
    }
}
