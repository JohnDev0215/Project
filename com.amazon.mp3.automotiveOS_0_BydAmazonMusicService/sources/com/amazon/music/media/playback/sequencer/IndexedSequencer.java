package com.amazon.music.media.playback.sequencer;

import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.util.StrictMode;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class IndexedSequencer extends IndexedSequencerBase {
    private final ShuffleMap shuffleMap;
    private ShuffleSizeScheme shuffleSizeScheme;
    public static final ShuffleSizeScheme SHUFFLE_SIZE_SCHEME_MAX = new ShuffleSizeScheme() { // from class: com.amazon.music.media.playback.sequencer.IndexedSequencer.1
        @Override // com.amazon.music.media.playback.sequencer.IndexedSequencer.ShuffleSizeScheme
        public int getShuffleSize(IndexedSequencer indexedSequencer) {
            return ((IndexedSequencerBase.SequencerInfo) indexedSequencer.info).getMaximumPosition();
        }
    };
    public static final ShuffleSizeScheme SHUFFLE_SIZE_SCHEME_LOADED = new ShuffleSizeScheme() { // from class: com.amazon.music.media.playback.sequencer.IndexedSequencer.2
        @Override // com.amazon.music.media.playback.sequencer.IndexedSequencer.ShuffleSizeScheme
        public int getShuffleSize(IndexedSequencer indexedSequencer) {
            return indexedSequencer.getQueueEnd();
        }
    };

    public interface ShuffleSizeScheme {
        int getShuffleSize(IndexedSequencer indexedSequencer);
    }

    protected IndexedSequencer(boolean z) {
        this(null, z);
    }

    protected IndexedSequencer(IndexedSequencerBase.SequencerInfo sequencerInfo, boolean z) {
        super(sequencerInfo, z);
        this.shuffleMap = new ShuffleMap();
        this.shuffleSizeScheme = SHUFFLE_SIZE_SCHEME_LOADED;
    }

    protected ShuffleMap getShuffleMap() {
        return this.shuffleMap;
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getMediaItem(int i) {
        if (isShuffled()) {
            if (i < ((IndexedSequencerBase.SequencerInfo) this.info).getQueueStartPosition() || i >= getQueueEnd()) {
                return null;
            }
            i = getUnshuffledIndex(i);
        }
        return super.getMediaItem(i);
    }

    public void setShuffled(List<Integer> list, int i) {
        if (canShuffle()) {
            int unshuffledIndex = getUnshuffledIndex(getCurrentIndex());
            try {
                this.shuffleMap.clear();
                this.shuffleMap.setShuffleOrder(list);
                setCurrentIndex(i, ChangeReason.SHUFFLE);
            } catch (RuntimeException e) {
                StrictMode.crashIfStrict(e);
                this.shuffleMap.clear();
                ((IndexedSequencerBase.SequencerInfo) this.info).shuffled = false;
                setCurrentIndex(unshuffledIndex, ChangeReason.SHUFFLE);
            }
            super.onShuffledChanged(true);
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    protected void onShuffledChanged(boolean z) {
        int currentIndex = getCurrentIndex();
        if (!isShuffled()) {
            updateShuffleMapSize(currentIndex + 1);
            currentIndex = this.shuffleMap.getUnshuffledIndex(currentIndex);
        }
        this.shuffleMap.clear();
        updateShuffleMapSize(0);
        if (isShuffled()) {
            if (z) {
                updateShuffleMapSize(0);
                this.shuffleMap.setUnshuffledIndex(0, currentIndex);
            }
            setCurrentIndex(0, ChangeReason.SHUFFLE);
        } else {
            if (!z) {
                currentIndex = 0;
            }
            setCurrentIndex(currentIndex, ChangeReason.SHUFFLE);
        }
        super.onShuffledChanged(z);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getUnshuffledIndex(int i) {
        if (!isShuffled()) {
            return i;
        }
        updateShuffleMapSize(i + 1);
        return this.shuffleMap.getUnshuffledIndex(i);
    }

    public int getShuffledIndex(int i) {
        if (!isShuffled()) {
            return i;
        }
        updateShuffleMapSize(i + 1);
        return this.shuffleMap.getShuffledIndex(i);
    }

    protected void updateShuffleMapSize(int i) {
        int iMax = Math.max(Math.max(Math.max(this.shuffleSizeScheme.getShuffleSize(this), 1), i), this.shuffleMap.getSize());
        if (iMax > this.shuffleMap.getSize()) {
            this.shuffleMap.setSize(iMax);
        }
    }
}
