package com.amazon.music.media.playback.sequencer.impl;

import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.util.StrictMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface SequencerDataRequestHandler {
    @WorkerThread
    SequenceData handleRequest(StateRequest stateRequest);

    @MainThread
    SequenceData onSuspend(StateRequest stateRequest);

    public static class StateRequest {
        public MediaItem currentItem;
        public boolean fetchingCurrent;
        public int fetchingPreviousCount;
        public int fetchingUpcomingCount;
        public boolean invalidate;
        public boolean shuffled;

        @VisibleForTesting(otherwise = 2)
        boolean suspend;
        public int unshuffledIndex = -1;
        public int shuffledIndex = -1;

        public void copyFrom(StateRequest stateRequest) {
            this.unshuffledIndex = stateRequest.unshuffledIndex;
            this.shuffledIndex = stateRequest.shuffledIndex;
            this.shuffled = stateRequest.shuffled;
            this.currentItem = stateRequest.currentItem;
            this.fetchingCurrent = stateRequest.fetchingCurrent;
            this.fetchingUpcomingCount = stateRequest.fetchingUpcomingCount;
            this.fetchingPreviousCount = stateRequest.fetchingPreviousCount;
            this.suspend = stateRequest.suspend;
            this.invalidate = stateRequest.invalidate;
        }

        public String toString() {
            return "StateRequest{unshuffledIndex=" + this.unshuffledIndex + ", shuffledIndex=" + this.shuffledIndex + ", currentItem=" + this.currentItem + ", shuffled=" + this.shuffled + ", fetchingCurrent=" + this.fetchingCurrent + ", fetchingUpcomingCount=" + this.fetchingUpcomingCount + ", fetchingPreviousCount=" + this.fetchingPreviousCount + ", invalidate=" + this.invalidate + ", suspend=" + this.suspend + '}';
        }
    }

    public static class SequenceData {
        public PlaybackException error;
        public boolean fetchingCurrent;
        public int fetchingPreviousCount;
        public int fetchingUpcomingCount;
        public MediaCollectionInfo mediaCollectionInfo;
        public boolean partialResult;
        public boolean shuffled;
        public int startIndex;
        public final List<MediaItem> items = new ArrayList();
        public final List<Integer> otherOrder = new ArrayList();
        public int maxItems = -1;
        public int shuffledIndex = -1;
        public int unshuffledIndex = -1;

        public void clear() {
            this.items.clear();
            this.otherOrder.clear();
            this.startIndex = 0;
            this.maxItems = -1;
            this.shuffled = false;
            this.shuffledIndex = -1;
            this.unshuffledIndex = -1;
            this.fetchingCurrent = false;
            this.fetchingUpcomingCount = 0;
            this.fetchingPreviousCount = 0;
            this.mediaCollectionInfo = null;
        }

        public void validate() {
            if (this.items.size() != this.otherOrder.size()) {
                StrictMode.crashIfStrict("items.size() = " + this.items.size() + " should be the same as otherOrder.size() = " + this.otherOrder.size());
            }
        }

        public int getCurrentIndex() {
            return this.shuffled ? this.shuffledIndex : this.unshuffledIndex;
        }

        public MediaItem getMediaItem(int i) {
            int i2 = this.startIndex;
            if (i < i2 || i >= i2 + this.items.size()) {
                return null;
            }
            return this.items.get(i - this.startIndex);
        }

        public int getAltIndex(int i) {
            Integer num;
            int i2 = this.startIndex;
            if (i < i2 || i >= i2 + this.items.size() || (num = this.otherOrder.get(i - this.startIndex)) == null) {
                return -1;
            }
            return num.intValue();
        }

        public void setMediaItem(int i, int i2, MediaItem mediaItem) {
            boolean z = this.shuffled;
            int i3 = z ? i : i2;
            if (i3 < 0) {
                return;
            }
            if (z) {
                i = i2;
            }
            boolean z2 = (mediaItem == null || mediaItem == getMediaItem(i3)) ? false : true;
            if (i >= 0 && !z2) {
                z2 = i != getAltIndex(i3);
            }
            if (z2) {
                if (this.items.isEmpty()) {
                    this.items.add(mediaItem);
                    this.startIndex = i3;
                    this.otherOrder.add(Integer.valueOf(i));
                    return;
                }
                int i4 = this.startIndex;
                if (i3 >= i4 && i3 < i4 + this.items.size()) {
                    this.items.set(i3 - this.startIndex, mediaItem);
                    this.otherOrder.set(i3 - this.startIndex, Integer.valueOf(i));
                    return;
                }
                int i5 = this.startIndex;
                if (i3 < i5) {
                    int i6 = (i5 - i3) - 1;
                    if (i6 > 0) {
                        this.items.addAll(0, Collections.nCopies(i6, null));
                        this.otherOrder.addAll(0, Collections.nCopies(i6, null));
                    }
                    this.items.add(0, mediaItem);
                    this.otherOrder.add(0, Integer.valueOf(i));
                    this.startIndex = i3;
                    return;
                }
                int size = (i3 - i5) - this.items.size();
                if (size > 0) {
                    this.items.addAll(Collections.nCopies(size, null));
                    this.otherOrder.addAll(0, Collections.nCopies(size, null));
                }
                this.items.add(mediaItem);
                this.otherOrder.add(Integer.valueOf(i));
            }
        }

        public String toString() {
            return "SequenceData{items=" + Arrays.toString(this.items.toArray()) + ", otherOrder=" + this.otherOrder + ", startIndex=" + this.startIndex + ", maxItems=" + this.maxItems + ", shuffled=" + this.shuffled + ", shuffledIndex=" + this.shuffledIndex + ", unshuffledIndex=" + this.unshuffledIndex + ", fetchingCurrent=" + this.fetchingCurrent + ", fetchingUpcomingCount=" + this.fetchingUpcomingCount + ", fetchingPreviousCount=" + this.fetchingPreviousCount + ", error=" + this.error + ", mediaCollectionInfo=" + this.mediaCollectionInfo + ", partialResult=" + this.partialResult + '}';
        }
    }
}
