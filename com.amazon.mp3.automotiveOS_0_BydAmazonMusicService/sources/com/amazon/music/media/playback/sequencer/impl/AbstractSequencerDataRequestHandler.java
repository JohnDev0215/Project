package com.amazon.music.media.playback.sequencer.impl;

import android.util.SparseArray;
import androidx.annotation.WorkerThread;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.sequencer.ShuffleMap;
import com.amazon.music.media.playback.sequencer.impl.SequencerDataRequestHandler;
import com.amazon.music.media.playback.util.StrictMode;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractSequencerDataRequestHandler implements SequencerDataRequestHandler {
    protected final AsyncSequencer sequencer;
    private SuspendHandler suspendHandler;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    protected final SequencerDataRequestHandler.SequenceData sequenceData = new SequencerDataRequestHandler.SequenceData();
    protected ShuffleMap shuffleMap = new ShuffleMap() { // from class: com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler.1
        @Override // com.amazon.music.media.playback.sequencer.ShuffleMap
        protected int createShuffleIndex(int i) {
            if (AbstractSequencerDataRequestHandler.this.autoShuffleEnabled) {
                return super.createShuffleIndex(i);
            }
            return -1;
        }
    };
    private final SparseArray<MediaItem> unshuffledItems = new SparseArray<>();
    private final SparseArray<MediaItem> shuffledItems = new SparseArray<>();
    private boolean autoShuffleEnabled = true;

    public interface SuspendHandler {
        void onSuspend(SequencerDataRequestHandler.StateRequest stateRequest);
    }

    @WorkerThread
    public abstract MediaItem createMediaItem() throws PlaybackException;

    public abstract int getAvailableItemCount();

    @WorkerThread
    public abstract int getTotalItemCount();

    public abstract int getUnshuffledIndex();

    public abstract void invalidate();

    public abstract boolean isPreparedToCreateItems();

    @WorkerThread
    public abstract MediaCollectionInfo loadMediaCollectionInfo() throws PlaybackException;

    public abstract void moveToUnshuffledIndex(int i);

    public abstract void prepareToCreateItems() throws PlaybackException;

    private class InsertRemoveSuspendHandler implements SuspendHandler {
        private boolean adjustIndex;
        private int insertionCount;
        private int shuffledInsertionIndex;
        private int unshuffledInsertionIndex;

        public InsertRemoveSuspendHandler(int i, int i2, int i3, boolean z) {
            this.shuffledInsertionIndex = i;
            this.unshuffledInsertionIndex = i2;
            this.insertionCount = i3;
            this.adjustIndex = z;
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler.SuspendHandler
        public void onSuspend(SequencerDataRequestHandler.StateRequest stateRequest) {
            AbstractSequencerDataRequestHandler.this.handleRequestNonBlocking(stateRequest);
            AbstractSequencerDataRequestHandler.this.invalidate();
            int i = this.insertionCount;
            if (i > 0) {
                AbstractSequencerDataRequestHandler.this.insert(this.shuffledInsertionIndex, this.unshuffledInsertionIndex, i, this.adjustIndex);
            } else {
                AbstractSequencerDataRequestHandler.this.remove(this.shuffledInsertionIndex, this.unshuffledInsertionIndex);
            }
        }
    }

    private class ReplaceSuspendHandler implements SuspendHandler {
        private final MediaItem currentItem;
        private final boolean shuffled;
        private final int unshuffledIndex;

        public ReplaceSuspendHandler(int i, boolean z, MediaItem mediaItem) {
            this.unshuffledIndex = i;
            this.shuffled = z;
            this.currentItem = mediaItem;
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler.SuspendHandler
        public void onSuspend(SequencerDataRequestHandler.StateRequest stateRequest) {
            SequencerDataRequestHandler.SequenceData sequenceData = AbstractSequencerDataRequestHandler.this.sequenceData;
            boolean z = sequenceData.shuffled;
            sequenceData.clear();
            AbstractSequencerDataRequestHandler abstractSequencerDataRequestHandler = AbstractSequencerDataRequestHandler.this;
            SequencerDataRequestHandler.SequenceData sequenceData2 = abstractSequencerDataRequestHandler.sequenceData;
            boolean z2 = this.shuffled;
            sequenceData2.shuffled = z2;
            if (z != z2) {
                abstractSequencerDataRequestHandler.onShuffledChanged(z2);
            }
            AbstractSequencerDataRequestHandler abstractSequencerDataRequestHandler2 = AbstractSequencerDataRequestHandler.this;
            SequencerDataRequestHandler.SequenceData sequenceData3 = abstractSequencerDataRequestHandler2.sequenceData;
            sequenceData3.unshuffledIndex = this.unshuffledIndex;
            sequenceData3.shuffledIndex = this.shuffled ? 0 : -1;
            sequenceData3.fetchingCurrent = stateRequest.fetchingCurrent;
            sequenceData3.fetchingUpcomingCount = stateRequest.fetchingUpcomingCount;
            sequenceData3.fetchingPreviousCount = stateRequest.fetchingPreviousCount;
            abstractSequencerDataRequestHandler2.shuffleMap.clear();
            AbstractSequencerDataRequestHandler.this.shuffledItems.clear();
            AbstractSequencerDataRequestHandler.this.unshuffledItems.clear();
            AbstractSequencerDataRequestHandler.this.invalidate();
            AbstractSequencerDataRequestHandler abstractSequencerDataRequestHandler3 = AbstractSequencerDataRequestHandler.this;
            abstractSequencerDataRequestHandler3.setMediaItem(abstractSequencerDataRequestHandler3.sequenceData.shuffledIndex, this.unshuffledIndex, this.currentItem);
            AbstractSequencerDataRequestHandler abstractSequencerDataRequestHandler4 = AbstractSequencerDataRequestHandler.this;
            abstractSequencerDataRequestHandler4.addMediaItem(abstractSequencerDataRequestHandler4.sequenceData.shuffledIndex, this.unshuffledIndex, this.currentItem);
            AbstractSequencerDataRequestHandler abstractSequencerDataRequestHandler5 = AbstractSequencerDataRequestHandler.this;
            AsyncSequencer asyncSequencer = abstractSequencerDataRequestHandler5.sequencer;
            int i = this.unshuffledIndex;
            asyncSequencer.invalidate(i != -2 ? i : -1, abstractSequencerDataRequestHandler5.sequenceData.shuffledIndex, this.shuffled, this.currentItem);
        }
    }

    private class ReplaceTrackSuspendHandler implements SuspendHandler {
        private final MediaItem newMediaItem;
        private final int unshuffledIndex;

        public ReplaceTrackSuspendHandler(int i, MediaItem mediaItem) {
            this.unshuffledIndex = i;
            this.newMediaItem = mediaItem;
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler.SuspendHandler
        public void onSuspend(SequencerDataRequestHandler.StateRequest stateRequest) {
            AbstractSequencerDataRequestHandler abstractSequencerDataRequestHandler = AbstractSequencerDataRequestHandler.this;
            SequencerDataRequestHandler.SequenceData sequenceData = abstractSequencerDataRequestHandler.sequenceData;
            sequenceData.shuffled = stateRequest.shuffled;
            sequenceData.shuffledIndex = stateRequest.shuffledIndex;
            sequenceData.unshuffledIndex = stateRequest.unshuffledIndex;
            sequenceData.fetchingCurrent = stateRequest.fetchingCurrent;
            sequenceData.fetchingUpcomingCount = stateRequest.fetchingUpcomingCount;
            sequenceData.fetchingPreviousCount = stateRequest.fetchingPreviousCount;
            int shuffledIndex = abstractSequencerDataRequestHandler.shuffleMap.getShuffledIndex(this.unshuffledIndex);
            AbstractSequencerDataRequestHandler.this.setMediaItem(shuffledIndex, this.unshuffledIndex, this.newMediaItem);
            AbstractSequencerDataRequestHandler.this.addMediaItem(shuffledIndex, this.unshuffledIndex, this.newMediaItem);
        }
    }

    private class MoveSuspendHandler implements SuspendHandler {
        private final int fromIndex;
        private final boolean shuffled;
        private final int toIndex;

        public MoveSuspendHandler(int i, int i2, boolean z) {
            this.fromIndex = i;
            this.toIndex = i2;
            this.shuffled = z;
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler.SuspendHandler
        public void onSuspend(SequencerDataRequestHandler.StateRequest stateRequest) {
            AbstractSequencerDataRequestHandler.this.handleRequestNonBlocking(stateRequest);
            AbstractSequencerDataRequestHandler.this.invalidate();
            AbstractSequencerDataRequestHandler.this.move(this.fromIndex, this.toIndex, this.shuffled);
        }
    }

    protected AbstractSequencerDataRequestHandler(AsyncSequencer asyncSequencer) {
        this.sequencer = asyncSequencer;
    }

    public void setAutoShuffleEnabled(boolean z) {
        this.autoShuffleEnabled = z;
    }

    public boolean isAutoShuffleEnabled() {
        return this.autoShuffleEnabled;
    }

    public void handleRequestNonBlocking(SequencerDataRequestHandler.StateRequest stateRequest) {
        boolean z;
        int i;
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        boolean z2 = sequenceData.shuffled;
        if (stateRequest.invalidate) {
            sequenceData.clear();
            z = true;
        } else {
            z = false;
        }
        if (stateRequest.invalidate || stateRequest.unshuffledIndex == -2 || stateRequest.shuffledIndex == -2 || (stateRequest.shuffled && !this.sequenceData.shuffled)) {
            this.shuffleMap.clear();
            this.sequenceData.items.clear();
            this.sequenceData.otherOrder.clear();
            this.unshuffledItems.clear();
            this.shuffledItems.clear();
            if (!this.autoShuffleEnabled) {
                z = true;
            }
        }
        SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
        boolean z3 = stateRequest.shuffled;
        sequenceData2.shuffled = z3;
        if (z2 != z3) {
            sequenceData2.items.clear();
            this.sequenceData.otherOrder.clear();
            onShuffledChanged(stateRequest.shuffled);
        }
        growShuffleMap(stateRequest.shuffledIndex, stateRequest.unshuffledIndex);
        int i2 = stateRequest.shuffledIndex;
        if (i2 >= 0 && (i = stateRequest.unshuffledIndex) >= 0) {
            this.shuffleMap.setUnshuffledIndex(i2, i);
            SequencerDataRequestHandler.SequenceData sequenceData3 = this.sequenceData;
            sequenceData3.shuffledIndex = stateRequest.shuffledIndex;
            sequenceData3.unshuffledIndex = stateRequest.unshuffledIndex;
        } else if (i2 >= 0) {
            SequencerDataRequestHandler.SequenceData sequenceData4 = this.sequenceData;
            sequenceData4.shuffledIndex = i2;
            sequenceData4.unshuffledIndex = this.shuffleMap.getUnshuffledIndex(i2);
        } else {
            int i3 = stateRequest.unshuffledIndex;
            if (i3 >= 0) {
                SequencerDataRequestHandler.SequenceData sequenceData5 = this.sequenceData;
                sequenceData5.unshuffledIndex = i3;
                sequenceData5.shuffledIndex = this.shuffleMap.getShuffledIndex(i3);
            } else if (stateRequest.shuffled) {
                SequencerDataRequestHandler.SequenceData sequenceData6 = this.sequenceData;
                sequenceData6.shuffledIndex = 0;
                sequenceData6.unshuffledIndex = -1;
            } else {
                SequencerDataRequestHandler.SequenceData sequenceData7 = this.sequenceData;
                sequenceData7.unshuffledIndex = 0;
                sequenceData7.shuffledIndex = -1;
            }
        }
        MediaItem mediaItem = stateRequest.currentItem;
        if (mediaItem == null) {
            SequencerDataRequestHandler.SequenceData sequenceData8 = this.sequenceData;
            mediaItem = getMediaItem(sequenceData8.shuffledIndex, sequenceData8.unshuffledIndex);
        }
        SequencerDataRequestHandler.SequenceData sequenceData9 = this.sequenceData;
        setMediaItem(sequenceData9.shuffledIndex, sequenceData9.unshuffledIndex, mediaItem);
        SequencerDataRequestHandler.SequenceData sequenceData10 = this.sequenceData;
        sequenceData10.setMediaItem(sequenceData10.shuffledIndex, sequenceData10.unshuffledIndex, mediaItem);
        SequencerDataRequestHandler.SequenceData sequenceData11 = this.sequenceData;
        sequenceData11.fetchingUpcomingCount = stateRequest.fetchingUpcomingCount;
        sequenceData11.fetchingPreviousCount = stateRequest.fetchingPreviousCount;
        sequenceData11.fetchingCurrent = stateRequest.fetchingCurrent && mediaItem == null;
        if (z) {
            invalidate();
        }
    }

    private void growShuffleMap(int i, int i2) {
        int availableItemCount = getAvailableItemCount();
        int iMax = Math.max(availableItemCount, Math.max(i, i2) + 1);
        if (iMax >= 2000 && this.shuffleMap.getErrorCause() == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("growShuffleMap(" + i + ", " + i2 + ") - availableCount: " + availableItemCount);
            illegalArgumentException.fillInStackTrace();
            this.shuffleMap.setErrorCause(illegalArgumentException);
        }
        if (this.shuffleMap.getSize() < iMax) {
            this.shuffleMap.setSize(iMax);
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.SequencerDataRequestHandler
    public SequencerDataRequestHandler.SequenceData handleRequest(SequencerDataRequestHandler.StateRequest stateRequest) {
        int i;
        handleRequestNonBlocking(stateRequest);
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        if (sequenceData.mediaCollectionInfo == null) {
            try {
                sequenceData.mediaCollectionInfo = loadMediaCollectionInfo();
            } catch (PlaybackException e) {
                SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
                sequenceData2.error = e;
                return sequenceData2;
            }
        }
        if (this.sequencer.hasUrgentRequest()) {
            SequencerDataRequestHandler.SequenceData sequenceData3 = this.sequenceData;
            sequenceData3.partialResult = true;
            return sequenceData3;
        }
        if (!isPreparedToCreateItems()) {
            try {
                prepareToCreateItems();
                if (!isPreparedToCreateItems()) {
                    SequencerDataRequestHandler.SequenceData sequenceData4 = this.sequenceData;
                    sequenceData4.partialResult = false;
                    return sequenceData4;
                }
                if (this.sequencer.hasUrgentRequest()) {
                    SequencerDataRequestHandler.SequenceData sequenceData5 = this.sequenceData;
                    sequenceData5.partialResult = true;
                    return sequenceData5;
                }
            } catch (PlaybackException e2) {
                this.sequenceData.error = e2;
                return this.sequenceData;
            }
        }
        this.sequenceData.maxItems = getTotalItemCount();
        if (this.sequenceData.maxItems > 0 && getAvailableItemCount() > 0 && this.sequenceData.maxItems > this.shuffleMap.getSize()) {
            this.shuffleMap.setSize(this.sequenceData.maxItems);
        }
        SequencerDataRequestHandler.SequenceData sequenceData6 = this.sequenceData;
        int i2 = sequenceData6.shuffledIndex;
        int i3 = sequenceData6.maxItems;
        if (i2 >= i3 || sequenceData6.unshuffledIndex >= i3) {
            if (sequenceData6.shuffled) {
                sequenceData6.shuffledIndex = 0;
                sequenceData6.unshuffledIndex = -1;
            } else {
                sequenceData6.unshuffledIndex = 0;
                sequenceData6.shuffledIndex = -1;
            }
        }
        if (i3 == 0) {
            sequenceData6.fetchingCurrent = false;
            sequenceData6.fetchingPreviousCount = 0;
            sequenceData6.fetchingUpcomingCount = 0;
        }
        if (sequenceData6.fetchingCurrent) {
            sequenceData6.fetchingCurrent = false;
            getOrCreateMediaItem(sequenceData6.shuffledIndex, sequenceData6.unshuffledIndex);
            if (this.sequencer.hasUrgentRequest()) {
                SequencerDataRequestHandler.SequenceData sequenceData7 = this.sequenceData;
                if (sequenceData7.fetchingPreviousCount <= 0 && sequenceData7.fetchingUpcomingCount <= 0) {
                    z = false;
                }
                sequenceData7.partialResult = z;
                return sequenceData7;
            }
        }
        SequencerDataRequestHandler.SequenceData sequenceData8 = this.sequenceData;
        int i4 = sequenceData8.shuffled ? sequenceData8.shuffledIndex : sequenceData8.unshuffledIndex;
        if (sequenceData8.fetchingUpcomingCount > 0) {
            int i5 = i4 + 1;
            int i6 = i5;
            while (isMediaItemInSequence(i6) && ((i = this.sequenceData.maxItems) < 0 || i6 < i)) {
                i6++;
            }
            SequencerDataRequestHandler.SequenceData sequenceData9 = this.sequenceData;
            int i7 = sequenceData9.fetchingUpcomingCount + i6;
            int i8 = sequenceData9.maxItems;
            if (i8 >= 0 && i7 >= i8) {
                i7 = i8 - 1;
                sequenceData9.fetchingUpcomingCount = Math.max(0, (i7 + 1) - i6);
            }
            while (i6 <= i7) {
                if (this.sequenceData.shuffled) {
                    getOrCreateMediaItem(i6, -1);
                } else {
                    getOrCreateMediaItem(-1, i6);
                }
                SequencerDataRequestHandler.SequenceData sequenceData10 = this.sequenceData;
                sequenceData10.error = null;
                sequenceData10.fetchingUpcomingCount--;
                if (i6 == i5 || this.sequencer.hasUrgentRequest()) {
                    SequencerDataRequestHandler.SequenceData sequenceData11 = this.sequenceData;
                    if (sequenceData11.fetchingPreviousCount <= 0 && sequenceData11.fetchingUpcomingCount <= 0) {
                        z = false;
                    }
                    sequenceData11.partialResult = z;
                    return sequenceData11;
                }
                i6++;
            }
        }
        if (this.sequenceData.fetchingPreviousCount > 0) {
            int i9 = i4 - 1;
            while (i9 > 0 && isMediaItemInSequence(i9)) {
                i9--;
            }
            int i10 = i9 + 1;
            int iMax = Math.max(0, i10 - this.sequenceData.fetchingPreviousCount);
            this.sequenceData.fetchingPreviousCount = Math.max(0, i10 - iMax);
            while (i9 >= iMax) {
                if (this.sequenceData.shuffled) {
                    getOrCreateMediaItem(i9, -1);
                } else {
                    getOrCreateMediaItem(-1, i9);
                }
                SequencerDataRequestHandler.SequenceData sequenceData12 = this.sequenceData;
                sequenceData12.error = null;
                sequenceData12.fetchingPreviousCount--;
                if (this.sequencer.hasUrgentRequest()) {
                    SequencerDataRequestHandler.SequenceData sequenceData13 = this.sequenceData;
                    sequenceData13.partialResult = sequenceData13.fetchingPreviousCount > 0;
                    return sequenceData13;
                }
                i9--;
            }
        }
        SequencerDataRequestHandler.SequenceData sequenceData14 = this.sequenceData;
        sequenceData14.partialResult = false;
        return sequenceData14;
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.SequencerDataRequestHandler
    public SequencerDataRequestHandler.SequenceData onSuspend(SequencerDataRequestHandler.StateRequest stateRequest) {
        SuspendHandler suspendHandler = this.suspendHandler;
        if (suspendHandler != null) {
            suspendHandler.onSuspend(stateRequest);
            this.suspendHandler = null;
        } else {
            handleRequestNonBlocking(stateRequest);
        }
        return this.sequenceData;
    }

    public void suspend(SuspendHandler suspendHandler) {
        this.suspendHandler = suspendHandler;
        this.sequencer.suspend();
    }

    public void suspendToReplace(int i, boolean z, MediaItem mediaItem) {
        suspend(new ReplaceSuspendHandler(i, z, mediaItem));
    }

    public void suspendToReplaceItem(int i, MediaItem mediaItem) {
        suspend(new ReplaceTrackSuspendHandler(i, mediaItem));
    }

    public void suspendToInsert(int i, int i2, int i3, boolean z) {
        suspend(new InsertRemoveSuspendHandler(i, i2, i3, z));
    }

    public void suspendToRemove(int i, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 <= 0) {
            throw new IllegalArgumentException("Illegal values - shuffleIndex: " + i + " unshuffledIndex: " + i2 + ", removalCount: " + i3);
        }
        suspend(new InsertRemoveSuspendHandler(i, i2, -i3, true));
    }

    public void suspendToMove(int i, int i2, boolean z) {
        if (i >= 0 && i2 >= 0) {
            if (i != i2) {
                suspend(new MoveSuspendHandler(i, i2, z));
            }
        } else {
            throw new IllegalArgumentException("Illegal values - fromIndex: " + i + ", toIndex: " + i2);
        }
    }

    public int getShuffledIndex() {
        if (!this.autoShuffleEnabled) {
            StrictMode.crashIfStrict(getClass().getName() + ".getShuffledIndex() must be overridden, since auto-shuffle was disabled!");
        }
        return this.shuffleMap.getShuffledIndex(getUnshuffledIndex());
    }

    public void moveToShuffledIndex(int i) {
        if (!this.autoShuffleEnabled) {
            StrictMode.crashIfStrict(getClass().getName() + ".moveToShuffledIndex(int) must be overridden, since auto-shuffle was disabled!");
        }
        moveToUnshuffledIndex(this.shuffleMap.getUnshuffledIndex(i));
    }

    public void onShuffledChanged(boolean z) {
        if (this.autoShuffleEnabled) {
            return;
        }
        StrictMode.crashIfStrict(getClass().getName() + ".onShuffledChanged(boolean) must be overridden, since auto-shuffle was disabled!");
    }

    public void setMediaItem(int i, int i2, MediaItem mediaItem) {
        if (i2 >= 0) {
            this.unshuffledItems.put(i2, mediaItem);
        }
        if (i >= 0) {
            this.shuffledItems.put(i, mediaItem);
        }
        if (i2 < 0 || i < 0) {
            return;
        }
        growShuffleMap(i, i2);
        this.shuffleMap.setUnshuffledIndex(i, i2);
    }

    public void insert(int i, int i2, int i3, boolean z) {
        int i4;
        int i5;
        int i6;
        int i7;
        int iKeyAt;
        int iKeyAt2;
        int i8;
        int i9;
        int i10;
        int i11;
        if (i < 0) {
            i = this.shuffleMap.getSize();
        }
        if (i2 < 0) {
            i2 = this.shuffleMap.getSize();
        }
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        boolean z2 = sequenceData.shuffled;
        int i12 = z2 ? i : i2;
        int i13 = z2 ? i2 : i;
        int i14 = sequenceData.startIndex;
        if (i14 >= i12) {
            sequenceData.startIndex = i14 + i3;
        } else if (!sequenceData.items.isEmpty()) {
            SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
            if (i12 < sequenceData2.startIndex + sequenceData2.items.size()) {
                SequencerDataRequestHandler.SequenceData sequenceData3 = this.sequenceData;
                int i15 = i12 - sequenceData3.startIndex;
                sequenceData3.items.addAll(i15, Collections.nCopies(i3, null));
                this.sequenceData.otherOrder.addAll(i15, Collections.nCopies(i3, null));
            }
        }
        int size = this.sequenceData.items.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            Integer num = this.sequenceData.otherOrder.get(size);
            if (num != null && num.intValue() >= i13) {
                this.sequenceData.otherOrder.set(size, Integer.valueOf(num.intValue() + i3));
            }
        }
        SequencerDataRequestHandler.SequenceData sequenceData4 = this.sequenceData;
        int i16 = sequenceData4.maxItems;
        if (i16 >= 0) {
            sequenceData4.maxItems = i16 + i3;
        }
        this.shuffleMap.onItemsInserted(i, i2, i3);
        SequencerDataRequestHandler.SequenceData sequenceData5 = this.sequenceData;
        if (sequenceData5.shuffled) {
            if (z && (i11 = sequenceData5.shuffledIndex) >= i) {
                sequenceData5.shuffledIndex = i11 + i3;
            }
            if (z && (i10 = sequenceData5.unshuffledIndex) >= i2) {
                sequenceData5.unshuffledIndex = i10 + i3;
            }
            if (!z && (i9 = sequenceData5.shuffledIndex) >= i && sequenceData5.unshuffledIndex >= 0) {
                sequenceData5.unshuffledIndex = this.shuffleMap.getUnshuffledIndex(i9);
            } else if (!z && (i8 = sequenceData5.unshuffledIndex) >= i2) {
                sequenceData5.unshuffledIndex = i8 + i3;
            }
        } else {
            if (z && (i7 = sequenceData5.unshuffledIndex) >= i2) {
                sequenceData5.unshuffledIndex = i7 + i3;
            }
            if (z && (i6 = sequenceData5.shuffledIndex) >= i) {
                sequenceData5.shuffledIndex = i6 + i3;
            }
            if (!z && (i5 = sequenceData5.unshuffledIndex) >= i2 && sequenceData5.shuffledIndex >= 0) {
                sequenceData5.shuffledIndex = this.shuffleMap.getShuffledIndex(i5);
            } else if (!z && (i4 = sequenceData5.shuffledIndex) >= i) {
                sequenceData5.shuffledIndex = i4 + i3;
            }
        }
        int size2 = this.shuffledItems.size();
        while (true) {
            size2--;
            if (size2 < 0 || (iKeyAt2 = this.shuffledItems.keyAt(size2)) < i) {
                break;
            }
            MediaItem mediaItemValueAt = this.shuffledItems.valueAt(size2);
            this.shuffledItems.removeAt(size2);
            this.shuffledItems.put(iKeyAt2 + i3, mediaItemValueAt);
        }
        int size3 = this.unshuffledItems.size();
        while (true) {
            size3--;
            if (size3 < 0 || (iKeyAt = this.unshuffledItems.keyAt(size3)) < i2) {
                return;
            }
            MediaItem mediaItemValueAt2 = this.unshuffledItems.valueAt(size3);
            this.unshuffledItems.removeAt(size3);
            this.unshuffledItems.put(iKeyAt + i3, mediaItemValueAt2);
        }
    }

    public void remove(int i, int i2) {
        int i3;
        int i4;
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        int i5 = sequenceData.maxItems;
        if (i5 == 1 && (i == 0 || i2 == 0)) {
            sequenceData.clear();
            this.shuffledItems.clear();
            this.unshuffledItems.clear();
            this.shuffleMap.clear();
            return;
        }
        if (i5 > Math.max(i, i2)) {
            this.sequenceData.maxItems--;
        } else if (this.sequenceData.maxItems >= 0) {
            this.logger.error("Attempting to remove shuffledIndex: " + i + ", unshuffledIndex: " + i + ", but maxItems is " + this.sequenceData.maxItems);
            return;
        }
        SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
        if (sequenceData2.shuffledIndex < 0 || sequenceData2.unshuffledIndex < 0) {
            this.shuffleMap.clear();
        } else {
            this.shuffleMap.onItemRemoved(i2);
        }
        int i6 = 0;
        if (i < 0) {
            this.shuffledItems.clear();
        } else {
            int i7 = 0;
            while (i7 < this.shuffledItems.size()) {
                int iKeyAt = this.shuffledItems.keyAt(i7);
                if (iKeyAt == i) {
                    this.shuffledItems.removeAt(i7);
                    i7--;
                } else if (iKeyAt > i) {
                    MediaItem mediaItemValueAt = this.shuffledItems.valueAt(i7);
                    this.shuffledItems.removeAt(i7);
                    this.shuffledItems.put(iKeyAt - 1, mediaItemValueAt);
                }
                i7++;
            }
        }
        if (i2 < 0) {
            this.unshuffledItems.clear();
        } else {
            while (i6 < this.unshuffledItems.size()) {
                int iKeyAt2 = this.unshuffledItems.keyAt(i6);
                if (iKeyAt2 == i2) {
                    this.unshuffledItems.removeAt(i6);
                    i6--;
                } else if (iKeyAt2 > i2) {
                    MediaItem mediaItemValueAt2 = this.unshuffledItems.valueAt(i6);
                    this.unshuffledItems.removeAt(i6);
                    this.unshuffledItems.put(iKeyAt2 - 1, mediaItemValueAt2);
                }
                i6++;
            }
        }
        SequencerDataRequestHandler.SequenceData sequenceData3 = this.sequenceData;
        boolean z = sequenceData3.shuffled;
        if (z && (i4 = sequenceData3.shuffledIndex) >= 0) {
            if (i4 > i) {
                sequenceData3.shuffledIndex = i4 - 1;
            }
            sequenceData3.unshuffledIndex = this.shuffleMap.getUnshuffledIndex(sequenceData3.shuffledIndex);
        } else if (!z && (i3 = sequenceData3.unshuffledIndex) >= 0) {
            if (i3 > i2) {
                sequenceData3.unshuffledIndex = i3 - 1;
            }
            sequenceData3.shuffledIndex = this.shuffleMap.getShuffledIndex(sequenceData3.unshuffledIndex);
        } else {
            sequenceData3.shuffledIndex = -1;
            sequenceData3.unshuffledIndex = -1;
        }
        SequencerDataRequestHandler.SequenceData sequenceData4 = this.sequenceData;
        boolean z2 = sequenceData4.shuffled;
        int i8 = z2 ? i : i2;
        if (z2) {
            i = i2;
        }
        if (!sequenceData4.items.isEmpty()) {
            if (i8 < 0) {
                this.sequenceData.items.clear();
                this.sequenceData.otherOrder.clear();
            } else {
                SequencerDataRequestHandler.SequenceData sequenceData5 = this.sequenceData;
                int i9 = sequenceData5.startIndex;
                if (i9 > i8) {
                    sequenceData5.startIndex = i9 - 1;
                } else {
                    int i10 = i8 - i9;
                    if (i10 < sequenceData5.items.size()) {
                        this.sequenceData.items.remove(i10);
                        this.sequenceData.otherOrder.remove(i10);
                    }
                }
            }
        }
        if (i >= 0) {
            int size = this.sequenceData.otherOrder.size();
            while (true) {
                size--;
                if (size < 0) {
                    return;
                }
                Integer num = this.sequenceData.otherOrder.get(size);
                if (num != null && num.intValue() > i) {
                    this.sequenceData.otherOrder.set(size, Integer.valueOf(num.intValue() - 1));
                }
            }
        } else {
            int size2 = this.sequenceData.otherOrder.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    this.sequenceData.otherOrder.set(size2, null);
                }
            }
        }
    }

    public void move(int i, int i2, boolean z) {
        SparseArray<MediaItem> sparseArray;
        int iKeyAt;
        int iKeyAt2;
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        int i3 = sequenceData.maxItems;
        if ((i3 >= 0 && i >= i3) || i2 >= i3 || i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Out of range - fromIndex: " + i + ", toIndex: " + i2 + ", maxItems: " + this.sequenceData.maxItems);
        }
        if (i == i2 || z != sequenceData.shuffled) {
            return;
        }
        if (z) {
            int i4 = sequenceData.shuffledIndex;
            if (i4 == i) {
                sequenceData.shuffledIndex = i2;
            } else if (i4 > i && i4 <= i2) {
                sequenceData.shuffledIndex = i4 - 1;
            } else if (i4 < i && i4 >= i2) {
                sequenceData.shuffledIndex = i4 + 1;
            }
            this.shuffleMap.onShuffledItemMoved(i, i2);
            sparseArray = this.shuffledItems;
        } else {
            int i5 = sequenceData.unshuffledIndex;
            if (i5 == i) {
                sequenceData.unshuffledIndex = i2;
            } else if (i5 > i && i5 <= i2) {
                sequenceData.unshuffledIndex = i5 - 1;
            } else if (i5 < i && i5 >= i2) {
                sequenceData.unshuffledIndex = i5 + 1;
            }
            this.shuffleMap.onUnshuffledItemMoved(i, i2);
            sparseArray = this.unshuffledItems;
        }
        MediaItem mediaItem = sparseArray.get(i);
        SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
        int i6 = sequenceData2.startIndex;
        int i7 = i - i6;
        int i8 = i2 - i6;
        int size = sequenceData2.items.size();
        Integer num = (i < 0 || i >= size) ? null : this.sequenceData.otherOrder.get(i7);
        if (i < i2) {
            for (int i9 = 0; i9 < sparseArray.size() && (iKeyAt2 = sparseArray.keyAt(i9)) <= i2; i9++) {
                if (iKeyAt2 > i) {
                    MediaItem mediaItemValueAt = sparseArray.valueAt(i9);
                    sparseArray.removeAt(i9);
                    sparseArray.put(iKeyAt2 - 1, mediaItemValueAt);
                }
            }
            while (i7 < size && i7 <= i8) {
                if (i7 < size - 1) {
                    List<MediaItem> list = this.sequenceData.items;
                    int i10 = i7 + 1;
                    list.set(i7, list.get(i10));
                    List<Integer> list2 = this.sequenceData.otherOrder;
                    list2.set(i7, list2.get(i10));
                } else {
                    this.sequenceData.items.set(i7, null);
                    this.sequenceData.otherOrder.set(i7, null);
                }
                i7++;
            }
        } else {
            int size2 = sparseArray.size();
            while (true) {
                size2--;
                if (size2 < 0 || (iKeyAt = sparseArray.keyAt(size2)) < i2) {
                    break;
                }
                if (iKeyAt < i) {
                    MediaItem mediaItemValueAt2 = sparseArray.valueAt(size2);
                    sparseArray.removeAt(size2);
                    sparseArray.put(iKeyAt + 1, mediaItemValueAt2);
                }
            }
            int iMax = Math.max(0, i8 + 1);
            int iMin = Math.min(size, i7 + 1);
            while (true) {
                iMin--;
                if (iMin < iMax) {
                    break;
                }
                if (iMin > 0) {
                    List<MediaItem> list3 = this.sequenceData.items;
                    int i11 = iMin - 1;
                    list3.set(iMin, list3.get(i11));
                    List<Integer> list4 = this.sequenceData.otherOrder;
                    list4.set(iMin, list4.get(i11));
                } else {
                    this.sequenceData.items.set(iMin, null);
                    this.sequenceData.otherOrder.set(iMin, null);
                }
            }
        }
        if (i8 >= 0 && i8 < size) {
            this.sequenceData.items.set(i8, mediaItem);
            this.sequenceData.otherOrder.set(i8, num);
        }
        if (mediaItem != null) {
            sparseArray.put(i2, mediaItem);
        }
    }

    public MediaItem getOrCreateMediaItem(int i, int i2) {
        boolean z = true;
        boolean z2 = false;
        if (i < 0 && (i = this.shuffleMap.getShuffledIndex(i2)) < 0) {
            moveToUnshuffledIndex(i2);
            i = getShuffledIndex();
            z2 = true;
        }
        if (i2 >= 0 || (i2 = this.shuffleMap.getUnshuffledIndex(i)) >= 0) {
            z = z2;
        } else {
            moveToShuffledIndex(i);
            i2 = getUnshuffledIndex();
        }
        growShuffleMap(i, i2);
        this.shuffleMap.setUnshuffledIndex(i, i2);
        MediaItem mediaItem = getMediaItem(i, i2);
        if (mediaItem == null) {
            this.logger.debug("createMediaItem - shuffledIndex: " + i + ", unshuffledIndex: " + i2);
            if (!z) {
                try {
                    if (this.sequenceData.shuffled) {
                        moveToShuffledIndex(i);
                    } else {
                        moveToUnshuffledIndex(i2);
                    }
                } catch (PlaybackException e) {
                    this.sequenceData.error = e;
                    return null;
                }
            }
            mediaItem = createMediaItem();
        }
        addMediaItem(i, i2, mediaItem);
        return mediaItem;
    }

    public void addMediaItem(int i, int i2, MediaItem mediaItem) {
        boolean z = this.sequenceData.shuffled;
        int i3 = z ? i : i2;
        if (mediaItem == null) {
            return;
        }
        int i4 = z ? i2 : i;
        if (i2 >= 0) {
            this.unshuffledItems.put(i2, mediaItem);
        }
        if (i >= 0) {
            this.shuffledItems.put(i, mediaItem);
        }
        if (this.sequenceData.items.isEmpty()) {
            SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
            sequenceData.startIndex = i3;
            sequenceData.items.add(mediaItem);
            this.sequenceData.otherOrder.add(Integer.valueOf(i4));
            return;
        }
        SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
        int i5 = sequenceData2.startIndex;
        if (i3 < i5) {
            int i6 = (i5 - i3) - 1;
            sequenceData2.items.addAll(0, Collections.nCopies(i6, null));
            this.sequenceData.otherOrder.addAll(0, Collections.nCopies(i6, null));
            SequencerDataRequestHandler.SequenceData sequenceData3 = this.sequenceData;
            sequenceData3.startIndex = i3;
            sequenceData3.items.add(0, mediaItem);
            this.sequenceData.otherOrder.add(0, Integer.valueOf(i4));
            return;
        }
        if (i3 >= i5 + sequenceData2.items.size()) {
            SequencerDataRequestHandler.SequenceData sequenceData4 = this.sequenceData;
            int size = (i3 - sequenceData4.startIndex) - sequenceData4.items.size();
            this.sequenceData.items.addAll(Collections.nCopies(size, null));
            this.sequenceData.otherOrder.addAll(Collections.nCopies(size, null));
            this.sequenceData.items.add(mediaItem);
            this.sequenceData.otherOrder.add(Integer.valueOf(i4));
            return;
        }
        SequencerDataRequestHandler.SequenceData sequenceData5 = this.sequenceData;
        sequenceData5.items.set(i3 - sequenceData5.startIndex, mediaItem);
        SequencerDataRequestHandler.SequenceData sequenceData6 = this.sequenceData;
        sequenceData6.otherOrder.set(i3 - sequenceData6.startIndex, Integer.valueOf(i4));
    }

    public MediaItem getMediaItem(int i, int i2) {
        MediaItem mediaItem;
        MediaItem mediaItem2;
        if (i2 >= 0 && (mediaItem2 = this.unshuffledItems.get(i2)) != null) {
            if (i >= 0) {
                this.shuffledItems.put(i, mediaItem2);
            }
            return mediaItem2;
        }
        if (i < 0 || (mediaItem = this.shuffledItems.get(i)) == null) {
            return null;
        }
        if (i2 >= 0) {
            this.unshuffledItems.put(i2, mediaItem);
        }
        return mediaItem;
    }

    private boolean isMediaItemInSequence(int i) {
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        int i2 = sequenceData.startIndex;
        if (i < i2 || i >= i2 + sequenceData.items.size()) {
            return false;
        }
        SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
        return sequenceData2.items.get(i - sequenceData2.startIndex) != null;
    }
}
