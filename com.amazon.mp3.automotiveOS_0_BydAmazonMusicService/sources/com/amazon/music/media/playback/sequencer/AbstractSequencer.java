package com.amazon.music.media.playback.sequencer;

import android.net.Uri;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.sequencer.AbstractSequencer.SequencerInfo;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.MetricsTimer;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractSequencer<INFO extends SequencerInfo> implements Sequencer {
    protected final INFO info;
    private final BasicRatingsProvider ratingsProvider;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    protected final Object modificationLock = new Object();
    private final Map<String, MetricsTimer> metricsTimers = new HashMap();
    private final ListenerSet<SequencerListener> listeners = new ListenerSet<>(SequencerListener.class, ListenerSet.DispatchStrategy.SYNCHRONOUS, ListenerSet.DuplicateStrategy.ALLOW_DUPLICATES);

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchCurrentMediaItem() {
        return false;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchPreviousMediaItems() {
        return false;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchUpcomingMediaItems() {
        return false;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchCurrentMediaItem() {
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchPreviousMediaItems(int i) {
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchUpcomingMediaItems(int i) {
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public abstract /* synthetic */ int getCurrentIndex();

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getMediaItem(int i) {
        return null;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public abstract /* synthetic */ int getNumMediaItems();

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void goToBeginning(ChangeReason changeReason) {
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingCurrentMediaItem() {
        return false;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingPreviousMediaItems() {
        return false;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingUpcomingMediaItems() {
        return false;
    }

    protected abstract INFO newSequencerInfo(INFO info);

    protected abstract MediaItem skip(int i, boolean z, ChangeReason changeReason);

    public static class SequencerInfo {
        Uri artistImageUri;
        boolean canShuffle;
        boolean canSkipNext;
        boolean canSkipPrevious;
        boolean canSkipToIndex;
        MediaCollectionInfo collectionInfo;
        Uri imageUri;
        boolean live;
        RepeatMode repeatMode;
        EnumSet<RepeatMode> repeatModes;
        boolean shuffled;
        int upcomingPreviewSize;

        public SequencerInfo() {
            initValues();
        }

        public String toString() {
            return "SequencerInfo{\n " + toInfoString() + "\n}";
        }

        protected String toInfoString() {
            return "collectionInfo=" + this.collectionInfo + ",\n imageUri=" + this.imageUri + ",\n artistImageUri=" + this.artistImageUri + ",\n repeatMode=" + this.repeatMode + ",\n shuffled=" + this.shuffled + ",\n canShuffle=" + this.canShuffle + ",\n repeatModes=" + this.repeatModes + ",\n live=" + this.live + ",\n upcomingPreviewSize=" + this.upcomingPreviewSize + ",\n canSkipNext=" + this.canSkipNext + ",\n canSkipPrevious=" + this.canSkipPrevious + ",\n canSkipToIndex=" + this.canSkipToIndex;
        }

        private void initValues() {
            this.collectionInfo = null;
            this.imageUri = null;
            this.artistImageUri = null;
            this.repeatMode = RepeatMode.REPEAT_NONE;
            this.shuffled = false;
            this.canShuffle = false;
            this.repeatModes = RepeatMode.REPEAT_NONE_ONLY;
            this.live = false;
            this.upcomingPreviewSize = -1;
            this.canSkipNext = true;
            this.canSkipPrevious = true;
            this.canSkipToIndex = false;
        }

        public SequencerInfo copyFrom(SequencerInfo sequencerInfo) {
            if (sequencerInfo == null) {
                initValues();
                return this;
            }
            this.collectionInfo = sequencerInfo.collectionInfo;
            this.imageUri = sequencerInfo.imageUri;
            this.artistImageUri = sequencerInfo.artistImageUri;
            this.repeatMode = sequencerInfo.repeatMode;
            this.shuffled = sequencerInfo.shuffled;
            this.canShuffle = sequencerInfo.canShuffle;
            this.repeatModes = sequencerInfo.repeatModes;
            this.live = sequencerInfo.live;
            this.upcomingPreviewSize = sequencerInfo.upcomingPreviewSize;
            this.canSkipNext = sequencerInfo.canSkipNext;
            this.canSkipPrevious = sequencerInfo.canSkipPrevious;
            this.canSkipToIndex = sequencerInfo.canSkipToIndex;
            return this;
        }

        public MediaCollectionInfo getMediaCollectionInfo() {
            return this.collectionInfo;
        }

        public boolean isCanShuffle() {
            return this.canShuffle;
        }

        public int getUpcomingPreviewSize() {
            return this.upcomingPreviewSize;
        }

        public SequencerInfo setMediaCollectionInfo(MediaCollectionInfo mediaCollectionInfo) {
            this.collectionInfo = mediaCollectionInfo;
            return this;
        }

        public SequencerInfo setImageUri(Uri uri) {
            this.imageUri = uri;
            return this;
        }

        public SequencerInfo setShuffled(boolean z) {
            this.shuffled = z;
            return this;
        }

        public SequencerInfo setCanShuffle(boolean z) {
            this.canShuffle = z;
            return this;
        }

        public SequencerInfo setRepeatModes(EnumSet<RepeatMode> enumSet) {
            if (enumSet == null) {
                StrictMode.crashIfStrict("repeatModes cannot be null");
                return this;
            }
            this.repeatModes = enumSet;
            return this;
        }

        public void setUpcomingPreviewSize(int i) {
            this.upcomingPreviewSize = i;
        }

        public void setCanSkipNext(boolean z) {
            this.canSkipNext = z;
        }

        public void setCanSkipPrevious(boolean z) {
            this.canSkipPrevious = z;
        }
    }

    protected AbstractSequencer(INFO info, boolean z) {
        getMetricsTimer("TIME_SINCE_CREATION").start();
        this.info = (INFO) newSequencerInfo(info);
        this.ratingsProvider = new BasicRatingsProvider(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final INFO copySequencerInfo() {
        return (INFO) newSequencerInfo(this.info);
    }

    public final void setSequencerInfo(final INFO info) {
        final long jNow = Clock.now();
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.sequencer.AbstractSequencer.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (AbstractSequencer.this.modificationLock) {
                    long jNow2 = Clock.now() - jNow;
                    INFO info2 = AbstractSequencer.this.info;
                    MediaCollectionInfo mediaCollectionInfo = info2.collectionInfo;
                    info2.copyFrom(info);
                    AbstractSequencer.this.notifySequencerInfoChanged();
                    AbstractSequencer abstractSequencer = AbstractSequencer.this;
                    MediaCollectionInfo mediaCollectionInfo2 = abstractSequencer.info.collectionInfo;
                    if (mediaCollectionInfo != mediaCollectionInfo2) {
                        Logger logger = abstractSequencer.logger;
                        Object[] objArr = new Object[2];
                        objArr[0] = mediaCollectionInfo == null ? "null" : mediaCollectionInfo.getName();
                        objArr[1] = mediaCollectionInfo2 == null ? "null" : mediaCollectionInfo2.getName();
                        logger.info(String.format("Collection info has changed, oldCollectionInfo: %s, newCollectionInfo: %s", objArr));
                        ((SequencerListener) AbstractSequencer.this.listeners.proxy()).onMediaCollectionInfoChanged(AbstractSequencer.this.getMediaCollectionInfo());
                    }
                    MetricsTimer metricsTimer = AbstractSequencer.this.getMetricsTimer("TIME_SINCE_READY");
                    if (!metricsTimer.isRunning() && AbstractSequencer.this.getCurrentMediaItem() != null) {
                        metricsTimer.start();
                        metricsTimer.addElapsedTimeMillis(jNow2);
                    }
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final void notifySequencerInfoChanged() {
        this.listeners.proxy().onSequencerInfoChanged(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyErrorFetchingMediaItems(PlaybackException playbackException) {
        this.listeners.proxy().onErrorFetchingMediaItems(this, playbackException);
    }

    protected final void notifyMetadataChanged(MediaItem mediaItem) {
        this.listeners.proxy().onMetadataChanged(mediaItem);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaCollectionInfo getMediaCollectionInfo() {
        return this.info.collectionInfo;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem peek(int i) {
        return skip(i, true, null);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final MediaItem skip(int i, ChangeReason changeReason) {
        return skip(i, false, changeReason);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipNext() {
        return this.info.canSkipNext;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipPrevious() {
        return this.info.canSkipPrevious;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipToIndex() {
        return this.info.canSkipToIndex;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int indexOf(MediaItem mediaItem) {
        if (!isIndexBased()) {
            return -1;
        }
        Iterator<MediaItemId> it = mediaItem.getMediaItemIds().iterator();
        while (it.hasNext()) {
            int iIndexOf = indexOf(it.next());
            if (iIndexOf >= 0) {
                return iIndexOf;
            }
        }
        return -1;
    }

    public int indexOf(MediaItemId mediaItemId) {
        if (!isIndexBased()) {
            return -1;
        }
        synchronized (this.modificationLock) {
            int numMediaItems = getNumMediaItems();
            for (int i = 0; i < numMediaItems; i++) {
                MediaItem mediaItem = getMediaItem(i);
                if (mediaItem != null && mediaItemId.equals(mediaItem.getMediaItemId(mediaItemId.getType()))) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final int getUpcomingPreviewSize() {
        return this.info.upcomingPreviewSize;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final void addSequencerListener(SequencerListener sequencerListener) {
        this.listeners.add(sequencerListener);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final void removeSequencerListener(SequencerListener sequencerListener) {
        this.listeners.remove(sequencerListener);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean canShuffle() {
        return this.info.canShuffle;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean isShuffled() {
        return this.info.shuffled;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final void setShuffled(boolean z, boolean z2) {
        if (this.info.shuffled == z || !canShuffle()) {
            return;
        }
        this.info.shuffled = z;
        onShuffledChanged(z2);
    }

    protected void onShuffledChanged(boolean z) {
        notifySequencerInfoChanged();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final RepeatMode getRepeatMode() {
        return this.info.repeatMode;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final void setRepeatMode(RepeatMode repeatMode) {
        INFO info = this.info;
        if (info.repeatMode == repeatMode || !info.repeatModes.contains(repeatMode)) {
            return;
        }
        this.info.repeatMode = repeatMode;
        if (isAfterEnd()) {
            skip(0, ChangeReason.REPEAT_MODE);
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final EnumSet<RepeatMode> getAvailableRepeatModes() {
        return this.info.repeatModes;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean isLive() {
        return this.info.live;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final BasicRatingsProvider getRatingsProvider() {
        return this.ratingsProvider;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MetricsTimer getMetricsTimer(String str) {
        MetricsTimer metricsTimer;
        synchronized (this.metricsTimers) {
            metricsTimer = this.metricsTimers.get(str);
            if (metricsTimer == null) {
                metricsTimer = new MetricsTimer(str);
                this.metricsTimers.put(str, metricsTimer);
            }
        }
        return metricsTimer;
    }
}
