package com.amazon.music.media.playback.sequencer;

import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.featuregate.Feature;
import com.amazon.music.media.playback.sequencer.AbstractSequencer;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IndexedSequencerBase extends AbstractSequencer<SequencerInfo> {
    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isIndexBased() {
        return true;
    }

    public static class SequencerInfo extends AbstractSequencer.SequencerInfo {
        private boolean canLoadMore;
        private boolean canLoadPrevious;
        private int currentPosition;
        private int maximumPosition;
        private final List<MediaItem> queue = new ArrayList();
        private int queueStartPosition;

        @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer.SequencerInfo
        protected String toInfoString() {
            return "queue=" + StringUtils.toString(this.queue) + ",\n queueStartPosition=" + this.queueStartPosition + ",\n currentPosition=" + this.currentPosition + ",\n maximumPosition=" + this.maximumPosition + ",\n canLoadMore=" + this.canLoadMore + ",\n canLoadPrevious=" + this.canLoadPrevious + ",\n " + super.toInfoString();
        }

        public SequencerInfo() {
            initValues();
        }

        public SequencerInfo(SequencerInfo sequencerInfo) {
            copyFrom((AbstractSequencer.SequencerInfo) sequencerInfo);
        }

        private void initValues() {
            this.queue.clear();
            this.queueStartPosition = 0;
            this.currentPosition = 0;
            this.maximumPosition = -1;
            this.repeatModes = RepeatMode.ALL_REPEAT_MODES;
            this.canLoadMore = false;
            this.canLoadPrevious = false;
            this.canSkipNext = true;
            this.canSkipPrevious = true;
            this.canSkipToIndex = true;
        }

        @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer.SequencerInfo
        public SequencerInfo copyFrom(AbstractSequencer.SequencerInfo sequencerInfo) {
            super.copyFrom(sequencerInfo);
            if (!(sequencerInfo instanceof SequencerInfo)) {
                initValues();
                return this;
            }
            SequencerInfo sequencerInfo2 = (SequencerInfo) sequencerInfo;
            this.queue.clear();
            this.queue.addAll(sequencerInfo2.queue);
            this.queueStartPosition = sequencerInfo2.queueStartPosition;
            this.currentPosition = sequencerInfo2.currentPosition;
            this.maximumPosition = sequencerInfo2.maximumPosition;
            this.canLoadMore = sequencerInfo2.canLoadMore;
            this.canLoadPrevious = sequencerInfo2.canLoadPrevious;
            return this;
        }

        public List<MediaItem> getQueue() {
            return this.queue;
        }

        public int getQueueStartPosition() {
            return this.queueStartPosition;
        }

        public int getCurrentPosition() {
            return this.currentPosition;
        }

        public int getMaximumPosition() {
            return this.maximumPosition;
        }

        public boolean canLoadMore() {
            return this.canLoadMore;
        }

        public boolean canLoadPrevious() {
            return this.canLoadPrevious;
        }

        public SequencerInfo setQueueStartPosition(int i) {
            this.queueStartPosition = i;
            return this;
        }

        public SequencerInfo setCurrentPosition(int i) {
            if (i >= 1000000) {
                throw new OutOfMemoryError("setCurrentPosition(" + i + ")");
            }
            if (i < 0) {
                StrictMode.crashIfStrict("currentPosition cannot be negative.  Actual value: " + i);
                this.currentPosition = 0;
                return this;
            }
            this.currentPosition = i;
            return this;
        }

        public SequencerInfo setMaximumPosition(int i) {
            this.maximumPosition = i;
            return this;
        }

        public SequencerInfo setCanLoadMore(boolean z) {
            this.canLoadMore = z;
            return this;
        }

        public SequencerInfo setCanLoadPrevious(boolean z) {
            this.canLoadPrevious = z;
            return this;
        }
    }

    protected IndexedSequencerBase(boolean z) {
        this(null, z);
    }

    protected IndexedSequencerBase(SequencerInfo sequencerInfo, boolean z) {
        super(sequencerInfo, z);
    }

    protected MediaCollectionId getPlaybackSessionId() {
        return new MediaCollectionId(MediaCollectionId.Type.PLAYBACK_SESSION_ID, UUID.randomUUID().toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    public SequencerInfo newSequencerInfo(SequencerInfo sequencerInfo) {
        return new SequencerInfo(sequencerInfo);
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchPreviousMediaItems() {
        return ((SequencerInfo) this.info).currentPosition > getQueueEnd() || (((SequencerInfo) this.info).queueStartPosition > 0 && ((SequencerInfo) this.info).canLoadPrevious);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getQueueEnd() {
        return ((SequencerInfo) this.info).queueStartPosition + ((SequencerInfo) this.info).queue.size();
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getMediaItem(int i) {
        MediaItem mediaItem;
        synchronized (this.modificationLock) {
            mediaItem = (i < ((SequencerInfo) this.info).queueStartPosition || i >= getQueueEnd()) ? null : (MediaItem) ((SequencerInfo) this.info).queue.get(i - ((SequencerInfo) this.info).queueStartPosition);
        }
        return mediaItem;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean canFetchUpcomingMediaItems() {
        return ((SequencerInfo) this.info).canLoadMore;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean canFetchCurrentMediaItem() {
        if (((SequencerInfo) this.info).currentPosition >= getQueueEnd()) {
            return ((SequencerInfo) this.info).canLoadMore;
        }
        if (((SequencerInfo) this.info).currentPosition < ((SequencerInfo) this.info).queueStartPosition) {
            return ((SequencerInfo) this.info).canLoadPrevious;
        }
        return getCurrentMediaItem() == null;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean isAtBeginning() {
        return ((SequencerInfo) this.info).currentPosition == 0;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final boolean isAfterEnd() {
        return ((SequencerInfo) this.info).currentPosition >= getQueueEnd() && !canFetchCurrentMediaItem();
    }

    protected final int getSkipIndex(int i) {
        int i2 = ((SequencerInfo) this.info).currentPosition + i;
        int numMediaItems = getNumMediaItems();
        boolean z = numMediaItems < 0;
        if (z) {
            numMediaItems = getQueueEnd();
        }
        boolean z2 = getRepeatMode() == RepeatMode.REPEAT_ALL;
        if (numMediaItems == 0) {
            return 0;
        }
        if (!z && z2) {
            return ((i2 % numMediaItems) + numMediaItems) % numMediaItems;
        }
        if (i2 < 0) {
            return 0;
        }
        return i2 >= numMediaItems ? numMediaItems : i2;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    protected final MediaItem skip(int i, boolean z, ChangeReason changeReason) {
        int skipIndex = getSkipIndex(i);
        if (!z) {
            setCurrentIndex(skipIndex, changeReason);
        }
        return getMediaItem(skipIndex);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getCurrentMediaItem() {
        return getMediaItem(((SequencerInfo) this.info).currentPosition);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final List<MediaItem> getUpcomingMediaItems(int i) {
        return getSublist(i, 1);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public final List<MediaItem> getPreviousMediaItems(int i) {
        return getSublist(i, -1);
    }

    private final List<MediaItem> getSublist(int i, int i2) {
        ArrayList arrayList;
        MediaItem mediaItem;
        synchronized (this.modificationLock) {
            int i3 = 0;
            int skipIndex = getSkipIndex(0);
            arrayList = new ArrayList(i < 0 ? 8 : i);
            int i4 = skipIndex;
            while (i3 != i) {
                i3++;
                int skipIndex2 = getSkipIndex(i3 * i2);
                if (skipIndex2 == i4 || skipIndex2 == skipIndex || (mediaItem = getMediaItem(skipIndex2)) == null) {
                    break;
                }
                arrayList.add(mediaItem);
                i4 = skipIndex2;
            }
        }
        return arrayList;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public int getCurrentIndex() {
        return ((SequencerInfo) this.info).currentPosition;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void setCurrentIndex(int i, ChangeReason changeReason) {
        if (i >= 1000000) {
            throw new OutOfMemoryError("setCurrentIndex(" + i + ", " + changeReason + ")");
        }
        if (i >= 0) {
            if ((((SequencerInfo) this.info).maximumPosition < 0 || i <= ((SequencerInfo) this.info).maximumPosition) && ((SequencerInfo) this.info).currentPosition != i) {
                ((SequencerInfo) this.info).currentPosition = i;
                notifySequencerInfoChanged();
                return;
            }
            return;
        }
        StrictMode.crashIfStrict("Illegal index: " + i);
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    public int indexOf(MediaItemId mediaItemId) {
        synchronized (this.modificationLock) {
            int size = ((SequencerInfo) this.info).queue.size();
            for (int i = 0; i < size; i++) {
                MediaItem mediaItem = (MediaItem) ((SequencerInfo) this.info).queue.get(i);
                if (mediaItem != null && mediaItemId.equals(mediaItem.getMediaItemId(mediaItemId.getType()))) {
                    return i + ((SequencerInfo) this.info).queueStartPosition;
                }
            }
            return -1;
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public int getNumMediaItems() {
        if (((SequencerInfo) this.info).maximumPosition > 0) {
            return ((SequencerInfo) this.info).maximumPosition;
        }
        if (canFetchUpcomingMediaItems()) {
            return ((SequencerInfo) this.info).maximumPosition == 0 ? 1 : -1;
        }
        return getQueueEnd();
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public void goToBeginning(ChangeReason changeReason) {
        setCurrentIndex(0, changeReason);
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipNext() {
        return ActionAvailability.AVAILABLE == getSkipNextAvailability();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public ActionAvailability getSkipNextAvailability() {
        if (ObjectUtils.isAdvertisement(getCurrentMediaItem())) {
            return ActionAvailability.UNAVAILABLE;
        }
        if (getCurrentIndex() < ((SequencerInfo) this.info).getMaximumPosition() - 1 || ((SequencerInfo) this.info).getMaximumPosition() < 0) {
            return ActionAvailability.AVAILABLE;
        }
        if (getRepeatMode() != RepeatMode.REPEAT_NONE) {
            return ActionAvailability.AVAILABLE;
        }
        return ActionAvailability.UNAVAILABLE_END_OF_QUEUE;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipPrevious() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (ObjectUtils.isAdvertisement(currentMediaItem)) {
            return false;
        }
        return (getCurrentIndex() > 0 || getRepeatMode() != RepeatMode.REPEAT_NONE) && (!shouldCheckEntitySkippable() || (currentMediaItem != null && currentMediaItem.isBOP()) || (currentMediaItem != null && ObjectUtils.getIsOnDemandQueue(currentMediaItem)) || (currentMediaItem != null && currentMediaItem.isOwned()));
    }

    private boolean shouldCheckEntitySkippable() {
        return Feature.entity_skippable_check.isEnabled();
    }
}
