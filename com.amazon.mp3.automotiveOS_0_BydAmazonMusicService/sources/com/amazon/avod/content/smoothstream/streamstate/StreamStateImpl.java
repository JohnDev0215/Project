package com.amazon.avod.content.smoothstream.streamstate;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy;
import com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StreamStateImpl implements StreamDownloaderState {
    private RetentionPolicy mCachePolicy;
    private CheckpointPolicy mCheckpointPolicy;
    private final SmoothStreamingPlaybackConfig mConfig;
    private ContentSessionContext mContext;
    private CachedContentView mDownloadedChunks;
    private final SmoothStreamingContentStore mFragmentStore;
    private final boolean mIsLiveRetentionPolicyDebugLogEnabled;
    private final TimeSpan mPlayheadCorrectionThreshold;
    private SmoothStreamingURI mRequestedUri;
    private final boolean mShouldCleanOutOfWindowFragmentsBeforeDownload;
    private boolean mShouldConsumeExactAudioFragment;
    private StreamIndex mStreamIndex;
    private final boolean mUseOptimizedCleaningOutOfWindowFragments;
    private final Set<StreamStateObserver> mObservers = Sets.newHashSet();
    private Set<SmoothStreamingURI> mInitFragments = Sets.newHashSet();
    private final Object mMutex = new Object();
    private volatile SmoothStreamingURI mCachedEarliestExistingUri = null;
    private volatile SmoothStreamingURI mCachedLatestExistingUri = null;

    public StreamStateImpl(@Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mFragmentStore = (SmoothStreamingContentStore) Preconditions.checkNotNull(smoothStreamingContentStore, "fragmentStore");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mPlayheadCorrectionThreshold = smoothStreamingPlaybackConfig.getPlayheadCorrectionThreshold();
        this.mIsLiveRetentionPolicyDebugLogEnabled = smoothStreamingPlaybackConfig.isLiveRetentionPolicyDebugLogEnabled();
        this.mUseOptimizedCleaningOutOfWindowFragments = smoothStreamingPlaybackConfig.useOptimizedCleaningOutOfWindowFragments();
        this.mShouldCleanOutOfWindowFragmentsBeforeDownload = smoothStreamingPlaybackConfig.shouldCleanOutOfWindowFragmentsBeforeDownload();
    }

    public void initialize(ContentSessionContext contentSessionContext, StreamIndex streamIndex, RetentionPolicy retentionPolicy, CheckpointPolicy checkpointPolicy) {
        this.mContext = contentSessionContext;
        this.mStreamIndex = streamIndex;
        this.mCachePolicy = retentionPolicy;
        this.mCheckpointPolicy = checkpointPolicy;
        this.mShouldConsumeExactAudioFragment = this.mConfig.shouldConsumeExactAudioFragment(contentSessionContext.getSpecification().isLiveStream());
        synchronized (this.mMutex) {
            this.mDownloadedChunks = this.mCheckpointPolicy.rebuildFromCheckpoint(this.mStreamIndex.getType());
            this.mInitFragments = this.mCheckpointPolicy.rebuildInitFragmentsFromCheckpoint(this.mStreamIndex.getType());
            updatedUrisOnStateChanged();
            this.mRequestedUri = createUriForChunk(this.mStreamIndex.getChunkIndexFromNanos(this.mContext.getState().getPlayPositionInNanos()));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public void notifyLivePointUpdated(long j) {
        Preconditions.checkState(this.mContext.getSessionType() == ContentSessionType.LIVE_CACHE);
        if (this.mStreamIndex.isAudio()) {
            this.mContext.getState().setPlayPositionInNanos(j);
        }
        try {
            SmoothStreamingURI smoothStreamingURICreateUriForChunk = createUriForChunk(this.mStreamIndex.getChunkIndexFromNanos(j));
            synchronized (this.mMutex) {
                this.mRequestedUri = smoothStreamingURICreateUriForChunk;
                cleanOutOfWindowFragments();
                updatedUrisOnStateChanged();
            }
            notifyObservers(false);
            this.mContext.getState().updateConsumptionHead(this.mStreamIndex.getIndex(), smoothStreamingURICreateUriForChunk.getChunkIndex());
        } catch (IndexOutOfBoundsException e) {
            DLog.warnf("StreamStateImpl %s: failed to create the fragment uri at live point %s ns, %s", this.mStreamIndex, Long.valueOf(j), e);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState
    public SmoothStreamingURI getNextRequest() {
        SmoothStreamingURI nextURI;
        synchronized (this.mMutex) {
            if (!this.mDownloadedChunks.contains(this.mRequestedUri.getChunkIndex())) {
                nextURI = this.mRequestedUri;
            } else {
                SmoothStreamingURI latestExistingUri = getLatestExistingUri();
                if (latestExistingUri == null || (nextURI = latestExistingUri.getNextURI()) == null || !shouldDownloadUri(nextURI)) {
                    nextURI = null;
                }
            }
        }
        return nextURI;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState
    public boolean isUriDownloaded(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mMutex) {
            if (smoothStreamingURI.isInitFragment()) {
                return this.mInitFragments.contains(smoothStreamingURI);
            }
            return this.mDownloadedChunks.contains(smoothStreamingURI.getChunkIndex());
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState
    public void onSuccessfulDownload(SmoothStreamingURI smoothStreamingURI) {
        int chunkIndex = smoothStreamingURI.getChunkIndex();
        synchronized (this.mMutex) {
            if (smoothStreamingURI.isInitFragment()) {
                this.mInitFragments.add(smoothStreamingURI);
                this.mCheckpointPolicy.checkpointInitFragments(this.mStreamIndex.getType(), this.mInitFragments);
            } else if (!this.mDownloadedChunks.contains(chunkIndex)) {
                if (shouldDownloadUri(smoothStreamingURI)) {
                    if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
                        DLog.devf("StreamStateImpl %s downloading Index %s", this.mStreamIndex.getType().name(), Integer.valueOf(smoothStreamingURI.getChunkIndex()));
                    }
                    this.mDownloadedChunks.add(chunkIndex, smoothStreamingURI.getQualityLevel());
                    this.mCheckpointPolicy.checkpoint(this.mStreamIndex.getType(), this.mDownloadedChunks);
                    updatedUrisOnStateChanged();
                } else {
                    deleteFragmentByChunkIndex(smoothStreamingURI.getChunkIndex(), false);
                }
            }
        }
        notifyObservers(false);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState
    public boolean shouldDownloadUri(SmoothStreamingURI smoothStreamingURI) {
        if (smoothStreamingURI.isInitFragment()) {
            return !this.mInitFragments.contains(smoothStreamingURI);
        }
        return this.mCachePolicy.shouldRetain(smoothStreamingURI, this.mContext.getState().getPlayPositionInNanos(), this.mContext);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState
    public int getHighestObtainedQualityBitrate(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mMutex) {
            if (this.mDownloadedChunks.contains(smoothStreamingURI.getChunkIndex())) {
                try {
                    return this.mFragmentStore.getBestQualityAvailable(this.mContext, smoothStreamingURI).getQualityLevel().getBitrate();
                } catch (ContentException e) {
                    DLog.warnf("Content store and memory streamstate out of sync for fragment %s: %s", smoothStreamingURI, e);
                }
            }
            return 1;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public void registerObserver(StreamStateObserver streamStateObserver) {
        synchronized (this.mObservers) {
            this.mObservers.add(streamStateObserver);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public long getDurationAfterPositionInNanos(long j) {
        SmoothStreamingURI earliestExistingUri;
        SmoothStreamingURI latestExistingUri;
        long presentationTimeInNanos;
        long presentationTimeInNanos2;
        synchronized (this.mMutex) {
            earliestExistingUri = getEarliestExistingUri();
            latestExistingUri = getLatestExistingUri();
        }
        if (earliestExistingUri != null && latestExistingUri != null) {
            try {
                presentationTimeInNanos = earliestExistingUri.getPresentationTimeInNanos();
            } catch (IndexOutOfBoundsException e) {
                e = e;
                presentationTimeInNanos = 0;
            }
            try {
                presentationTimeInNanos2 = latestExistingUri.getPresentationTimeInNanos() + latestExistingUri.getDurationInNanos();
            } catch (IndexOutOfBoundsException e2) {
                e = e2;
                DLog.warnf("Cannot find chunk as the manifest refreshed. firstUri: %s lastUri: %s ex:%s", earliestExistingUri, latestExistingUri, e.getLocalizedMessage());
                presentationTimeInNanos2 = 0;
            }
            if (j <= presentationTimeInNanos2 && this.mPlayheadCorrectionThreshold.getTotalNanoSeconds() + j >= presentationTimeInNanos) {
                return Math.abs(j - presentationTimeInNanos2);
            }
        }
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public SmoothStreamingURI getEarliestExistingUri() {
        return this.mCachedEarliestExistingUri;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public SmoothStreamingURI getLatestExistingUri() {
        return this.mCachedLatestExistingUri;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public void onSeek(long j) {
        synchronized (this.mMutex) {
            this.mRequestedUri = createUriForChunk(this.mStreamIndex.getChunkIndexFromNanos(j));
            cleanOutOfWindowFragments();
            updatedUrisOnStateChanged();
        }
        notifyObservers(true);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    @Nullable
    public FragmentStreamRequestResult getFragment(@Nonnull SmoothStreamingURI smoothStreamingURI) throws ContentException {
        FragmentStreamRequestResult fragmentStreamRequestResultLoadFragment;
        synchronized (this.mMutex) {
            if (this.mShouldCleanOutOfWindowFragmentsBeforeDownload) {
                cleanOutOfWindowFragments();
            }
            if (!smoothStreamingURI.isInitFragment()) {
                this.mRequestedUri = smoothStreamingURI;
            }
            if ((smoothStreamingURI.isInitFragment() && this.mInitFragments.contains(smoothStreamingURI)) || this.mDownloadedChunks.contains(smoothStreamingURI.getChunkIndex())) {
                try {
                    fragmentStreamRequestResultLoadFragment = loadFragment(smoothStreamingURI);
                } catch (ContentException e) {
                    DLog.warnf("Couldn't read fragment %s from disk due to %s; throwing exception, callers should handle this exception and attempt redownload or seekover depending on connectivity", smoothStreamingURI, e);
                    throw e;
                }
            } else {
                fragmentStreamRequestResultLoadFragment = null;
            }
            if (!this.mShouldCleanOutOfWindowFragmentsBeforeDownload) {
                cleanOutOfWindowFragments();
            }
            updatedUrisOnStateChanged();
        }
        notifyObservers(false);
        return fragmentStreamRequestResultLoadFragment;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public void markAsErrored(SmoothStreamingURI smoothStreamingURI) {
        resetErroredFragment(smoothStreamingURI);
        notifyObservers(true);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public void restrictToQuality(@Nonnull QualityLevel qualityLevel, @Nonnegative long j) {
        Preconditions.checkNotNull(qualityLevel);
        Preconditions.checkArgument(j >= 0);
        restrictToQuality(qualityLevel, this.mStreamIndex.getChunkIndexFromNanos(j));
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState, com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public int getConsumptionHead() {
        return this.mContext.getState().getConsumptionHead(this.mStreamIndex.getIndex());
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamReaderState
    public void releaseFragment(FragmentStreamRequestResult fragmentStreamRequestResult) {
        this.mFragmentStore.releaseBuffer(this.mContext.getSessionType(), fragmentStreamRequestResult.getByteBuffer());
    }

    void restrictToQuality(@Nonnull QualityLevel qualityLevel, int i) {
        synchronized (this.mMutex) {
            UnmodifiableIterator<CachedContentView.EntryTuple> it = this.mDownloadedChunks.restrictToQuality(qualityLevel, i).iterator();
            while (it.hasNext()) {
                CachedContentView.EntryTuple next = it.next();
                int iIntValue = ((Integer) next.getValue().upperEndpoint()).intValue();
                for (int iIntValue2 = ((Integer) next.getValue().lowerEndpoint()).intValue(); iIntValue2 <= iIntValue; iIntValue2++) {
                    deleteFragmentByChunkIndex(iIntValue2, false);
                }
            }
            updatedUrisOnStateChanged();
        }
        notifyObservers(true);
    }

    private void notifyObservers(boolean z) {
        Preconditions.checkState(!Thread.holdsLock(this.mMutex), "Do not invoke callbacks while under the mutex, as this will lead to deadlock!");
        synchronized (this.mObservers) {
            for (StreamStateObserver streamStateObserver : this.mObservers) {
                if (z) {
                    streamStateObserver.onStreamPositionChanged();
                } else {
                    streamStateObserver.onStreamStateChanged();
                }
            }
        }
    }

    private void resetErroredFragment(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mMutex) {
            if (smoothStreamingURI.isInitFragment()) {
                this.mInitFragments.remove(smoothStreamingURI);
                this.mFragmentStore.deleteErroredFragment(this.mContext, smoothStreamingURI);
            }
            if (this.mDownloadedChunks.contains(smoothStreamingURI.getChunkIndex())) {
                deleteFragmentByChunkIndex(smoothStreamingURI.getChunkIndex(), true);
                updatedUrisOnStateChanged();
            }
        }
    }

    private void cleanOutOfWindowFragments() {
        if (this.mUseOptimizedCleaningOutOfWindowFragments) {
            if (this.mDownloadedChunks.isEmpty()) {
                return;
            }
            int max = this.mDownloadedChunks.getMax();
            int min = this.mDownloadedChunks.getMin();
            if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
                DLog.devf("StreamStateImpl %s SlidingWindow (%d - %d)", this.mStreamIndex.getType().name(), Integer.valueOf(min), Integer.valueOf(max));
            }
            for (CachedContentView.EntryTuple entryTuple : new ArrayList(this.mDownloadedChunks.getRanges())) {
                for (int iIntValue = ((Integer) entryTuple.getValue().lowerEndpoint()).intValue(); iIntValue <= ((Integer) entryTuple.getValue().upperEndpoint()).intValue(); iIntValue++) {
                    if (!shouldDownloadUri(createUriForChunk(iIntValue))) {
                        if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
                            DLog.devf("StreamStateImpl %s deleting Index %s", this.mStreamIndex.getType().name(), Integer.valueOf(iIntValue));
                        }
                        deleteFragmentByChunkIndex(iIntValue, false);
                    }
                }
            }
            return;
        }
        if (!this.mDownloadedChunks.isEmpty()) {
            int max2 = this.mDownloadedChunks.getMax();
            int min2 = this.mDownloadedChunks.getMin();
            if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
                DLog.devf("StreamStateImpl %s SlidingWindow (%d - %d)", this.mStreamIndex.getType().name(), Integer.valueOf(min2), Integer.valueOf(max2));
            }
            while (min2 <= max2 && !shouldDownloadUri(createUriForChunk(min2))) {
                if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
                    DLog.devf("StreamStateImpl %s deleting Index %s", this.mStreamIndex.getType().name(), Integer.valueOf(min2));
                }
                deleteFragmentByChunkIndex(min2, false);
                min2++;
            }
        }
        if (this.mDownloadedChunks.isEmpty()) {
            return;
        }
        int min3 = this.mDownloadedChunks.getMin();
        int max3 = this.mDownloadedChunks.getMax();
        if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
            DLog.devf("StreamStateImpl %s SlidingWindow (%d - %d)", this.mStreamIndex.getType().name(), Integer.valueOf(min3), Integer.valueOf(max3));
        }
        while (max3 >= min3 && !shouldDownloadUri(createUriForChunk(max3))) {
            if (this.mContext.getSpecification().isLiveStream() && this.mIsLiveRetentionPolicyDebugLogEnabled) {
                DLog.devf("StreamStateImpl %s deleting Index %s", this.mStreamIndex.getType().name(), Integer.valueOf(max3));
            }
            deleteFragmentByChunkIndex(max3, false);
            max3--;
        }
    }

    private void deleteFragmentByChunkIndex(int i, boolean z) {
        SmoothStreamingURI smoothStreamingURICreateUriForChunk = createUriForChunk(i);
        DLog.devf("deleting %s", smoothStreamingURICreateUriForChunk);
        this.mDownloadedChunks.remove(i);
        if (z) {
            this.mFragmentStore.deleteErroredFragment(this.mContext, smoothStreamingURICreateUriForChunk);
        } else {
            this.mFragmentStore.releaseFragment(this.mContext, smoothStreamingURICreateUriForChunk);
        }
    }

    private SmoothStreamingURI createUriForChunk(int i) {
        return new SmoothStreamingURI(this.mStreamIndex, i);
    }

    @Nonnull
    private FragmentStreamRequestResult loadFragment(@Nonnull SmoothStreamingURI smoothStreamingURI) throws ContentException {
        if (!this.mFragmentStore.isAnyFragmentAvailable(this.mContext, smoothStreamingURI)) {
            throw new ContentException(ContentException.ContentError.FILE_MISSING, String.format(Locale.US, "Content store and memory streamstate out of sync for fragment %s", smoothStreamingURI));
        }
        SmoothStreamingURI existingUri = getExistingUri(smoothStreamingURI);
        return new FragmentStreamRequestResult(smoothStreamingURI, existingUri, this.mFragmentStore.loadFragment(this.mContext, existingUri));
    }

    SmoothStreamingURI getExistingUri(SmoothStreamingURI smoothStreamingURI) throws ContentException {
        if (smoothStreamingURI.isInitFragment()) {
            return smoothStreamingURI;
        }
        if (smoothStreamingURI.isAudio() && this.mShouldConsumeExactAudioFragment) {
            Preconditions.checkNotNull(smoothStreamingURI.getQualityLevel(), "quality level cannot be null for audio");
            return smoothStreamingURI;
        }
        return this.mFragmentStore.getBestQualityAvailable(this.mContext, smoothStreamingURI);
    }

    private void updatedUrisOnStateChanged() {
        Range<Integer> containingRangeIgnoringQuality;
        Preconditions.checkState(Thread.holdsLock(this.mMutex), "Every call to this should already have the mStateMutex lock.");
        long playPositionInNanos = this.mContext.getState().getPlayPositionInNanos();
        try {
            int chunkIndexFromNanos = this.mStreamIndex.getChunkIndexFromNanos(playPositionInNanos);
            containingRangeIgnoringQuality = this.mDownloadedChunks.getContainingRangeIgnoringQuality(chunkIndexFromNanos);
            if (containingRangeIgnoringQuality == null) {
                try {
                    if (!this.mStreamIndex.isLastPlayableChunk(chunkIndexFromNanos)) {
                        int i = chunkIndexFromNanos + 1;
                        if (Math.abs(playPositionInNanos - this.mStreamIndex.getChunkTimeInNanos(i)) <= this.mPlayheadCorrectionThreshold.getTotalNanoSeconds()) {
                            containingRangeIgnoringQuality = this.mDownloadedChunks.getContainingRangeIgnoringQuality(i);
                        }
                    }
                } catch (IndexOutOfBoundsException unused) {
                    DLog.devf("PeriodView indexOutOfBounds, falling back to consumption head based chunk calculation, playhead in nanos: %d", Long.valueOf(playPositionInNanos));
                }
            }
        } catch (IndexOutOfBoundsException unused2) {
            containingRangeIgnoringQuality = null;
        }
        if (this.mContext.getSpecification().isLiveStream() && containingRangeIgnoringQuality == null) {
            containingRangeIgnoringQuality = this.mDownloadedChunks.getContainingRangeIgnoringQuality(this.mContext.getState().getConsumptionHead(this.mStreamIndex.getIndex()));
        }
        if (containingRangeIgnoringQuality != null) {
            this.mCachedEarliestExistingUri = createUriForChunk(((Integer) containingRangeIgnoringQuality.lowerEndpoint()).intValue());
            this.mCachedLatestExistingUri = createUriForChunk(((Integer) containingRangeIgnoringQuality.upperEndpoint()).intValue());
            return;
        }
        DLog.devf("Adjacent range is still null for %s stream", this.mStreamIndex.getType());
        Iterator<CachedContentView.EntryTuple> it = this.mDownloadedChunks.getRanges().iterator();
        while (it.hasNext()) {
            DLog.devf("downloaded ranges: %s ", it.next().getValue());
        }
        this.mCachedEarliestExistingUri = null;
        this.mCachedLatestExistingUri = null;
    }
}
