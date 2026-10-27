package com.amazon.music.media.playback.sequencer.impl;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.access.MediaAccessPolicy;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.SequencerListener;
import com.amazon.music.media.playback.util.MetricsTimer;
import com.amazon.music.media.playback.util.StrictMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class FilteredSequencer implements Sequencer {
    private MediaAccessPolicy accessPolicy;
    private boolean foundBeginning;
    private boolean foundEnd;
    private Sequencer source;

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getCurrentIndex() {
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getMediaItem(int i) {
        return null;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getNumMediaItems() {
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getUnshuffledIndex(int i) {
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int indexOf(MediaItem mediaItem) {
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isIndexBased() {
        return false;
    }

    public FilteredSequencer(@NonNull Sequencer sequencer, @NonNull MediaAccessPolicy mediaAccessPolicy) {
        this.source = (Sequencer) Validate.notNull(sequencer);
        this.accessPolicy = (MediaAccessPolicy) Validate.notNull(mediaAccessPolicy);
    }

    public final MediaAccessPolicy getMediaAccessPolicy() {
        return this.accessPolicy;
    }

    public final void setMediaAccessPolicy(@NonNull MediaAccessPolicy mediaAccessPolicy) {
        if (this.accessPolicy != Validate.notNull(mediaAccessPolicy)) {
            this.accessPolicy = mediaAccessPolicy;
            filterChanged();
        }
    }

    protected void filterChanged() {
        this.foundBeginning = false;
        this.foundEnd = false;
        this.source.notifySequencerInfoChanged();
    }

    public boolean shouldInclude(@NonNull MediaItem mediaItem) {
        return this.accessPolicy.getMediaAccess(mediaItem.getMediaAccessInfo()).canPlay();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaCollectionInfo getMediaCollectionInfo() {
        return this.source.getMediaCollectionInfo();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isAtBeginning() {
        return this.source.isAtBeginning() || (peekNoWrap(-1) == getCurrentMediaItem() && !canFetchPreviousMediaItems());
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isAfterEnd() {
        return this.source.isAfterEnd() || (getCurrentMediaItem() == null && !canFetchCurrentMediaItem());
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
        return this.source.canSkipNext();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public ActionAvailability getSkipNextAvailability() {
        return this.source.getSkipNextAvailability();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipPrevious() {
        return this.source.canSkipPrevious();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipToIndex() {
        return this.source.canSkipToIndex();
    }

    private MediaItem peekNoWrap(int i) {
        RepeatMode repeatMode = this.source.getRepeatMode();
        this.source.setRepeatMode(RepeatMode.REPEAT_NONE);
        try {
            return skip(i, true, null);
        } finally {
            this.source.setRepeatMode(repeatMode);
        }
    }

    private MediaItem skip(int i, boolean z, ChangeReason changeReason) {
        int sourceSkips = toSourceSkips(i);
        MediaItem mediaItemPeek = z ? this.source.peek(sourceSkips) : this.source.skip(sourceSkips, changeReason);
        if (mediaItemPeek == null || !shouldInclude(mediaItemPeek)) {
            return null;
        }
        return mediaItemPeek;
    }

    public int toSourceSkips(int i) {
        boolean z = this.source.getCurrentMediaItem() != null && shouldInclude(this.source.getCurrentMediaItem());
        int i2 = i > 0 ? 1 : i < 0 ? -1 : 0;
        int i3 = (z || i <= 0) ? 0 : -1;
        int i4 = (z ? 0 : -1) + 50000;
        MediaItem mediaItem = null;
        int i5 = i;
        int i6 = 0;
        while (i5 != 0) {
            i6 += i2;
            MediaItem mediaItemPeek = this.source.peek(i6);
            if (mediaItemPeek != null && shouldInclude(mediaItemPeek)) {
                i5 -= i2;
                i4 = 50000;
                i3 = i6;
            } else {
                if (mediaItemPeek == null || i4 - 1 < 0) {
                    if (i < 0) {
                        boolean z2 = (mediaItemPeek == null && this.source.canFetchPreviousMediaItems()) ? false : true;
                        this.foundBeginning = z2;
                        return !z2 ? i3 - 1 : i3;
                    }
                    this.foundEnd = (mediaItemPeek == null && this.source.canFetchUpcomingMediaItems()) ? false : true;
                    return i3 + 1;
                }
                if (mediaItemPeek == mediaItem) {
                    if (i < 0) {
                        this.foundBeginning = true;
                        return i3;
                    }
                    this.foundEnd = true;
                    return i3 + 1;
                }
            }
            mediaItem = mediaItemPeek;
        }
        return i6;
    }

    public int fromSourceSkips(int i) {
        boolean z = true;
        int i2 = i > 0 ? 1 : i < 0 ? -1 : 0;
        boolean z2 = getCurrentMediaItem() == null;
        boolean z3 = !z2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i == 0) {
                z = z2;
                break;
            }
            i3 += i2;
            i -= i2;
            MediaItem mediaItemPeek = this.source.peek(i3);
            if (mediaItemPeek == null) {
                break;
            }
            if (shouldInclude(mediaItemPeek)) {
                i4 += i2;
                z2 = false;
                z3 = true;
            } else {
                z2 = true;
            }
        }
        return (z3 && z) ? i4 + i2 : i4;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getCurrentMediaItem() {
        MediaItem currentMediaItem = this.source.getCurrentMediaItem();
        return (currentMediaItem == null || shouldInclude(currentMediaItem)) ? currentMediaItem : skip(1, ChangeReason.MEDIA_ITEM_FILTERED);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public List<MediaItem> getUpcomingMediaItems(int i) {
        return getItems(i, 1);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public List<MediaItem> getPreviousMediaItems(int i) {
        return getItems(i, -1);
    }

    private List<MediaItem> getItems(int i, int i2) {
        ArrayList arrayList = new ArrayList(i > 0 ? i : 8);
        MediaItem currentMediaItem = getCurrentMediaItem();
        int i3 = 0;
        MediaItem mediaItem = currentMediaItem;
        int i4 = 0;
        while (i3 != i) {
            i4 += i2;
            MediaItem mediaItemPeek = peek(i4);
            if (mediaItemPeek == null || mediaItemPeek == mediaItem || mediaItemPeek == currentMediaItem) {
                break;
            }
            arrayList.add(mediaItemPeek);
            i3++;
            mediaItem = mediaItemPeek;
        }
        if (i2 < 0) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getUpcomingPreviewSize() {
        return this.source.getUpcomingPreviewSize();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchUpcomingMediaItems() {
        return !this.foundEnd && this.source.canFetchUpcomingMediaItems();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchPreviousMediaItems() {
        return !this.foundBeginning && this.source.canFetchPreviousMediaItems();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchCurrentMediaItem() {
        MediaItem currentMediaItem = this.source.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return this.source.canFetchCurrentMediaItem();
        }
        if (shouldInclude(currentMediaItem)) {
            return false;
        }
        return canFetchUpcomingMediaItems();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingUpcomingMediaItems() {
        return !this.foundEnd && this.source.isFetchingUpcomingMediaItems();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingPreviousMediaItems() {
        return !this.foundBeginning && this.source.isFetchingPreviousMediaItems();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingCurrentMediaItem() {
        MediaItem currentMediaItem = this.source.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return this.source.isFetchingCurrentMediaItem();
        }
        if (shouldInclude(currentMediaItem)) {
            return false;
        }
        return this.source.isFetchingUpcomingMediaItems();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchUpcomingMediaItems(int i) {
        this.source.fetchUpcomingMediaItems(i);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchPreviousMediaItems(int i) {
        this.source.fetchPreviousMediaItems(i);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchCurrentMediaItem() {
        MediaItem currentMediaItem = this.source.getCurrentMediaItem();
        if (currentMediaItem == null) {
            this.source.fetchCurrentMediaItem();
        } else {
            if (shouldInclude(currentMediaItem)) {
                return;
            }
            this.source.fetchUpcomingMediaItems(16);
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void addSequencerListener(SequencerListener sequencerListener) {
        this.source.addSequencerListener(sequencerListener);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void removeSequencerListener(SequencerListener sequencerListener) {
        this.source.removeSequencerListener(sequencerListener);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void notifySequencerInfoChanged() {
        this.source.notifySequencerInfoChanged();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canShuffle() {
        return this.source.canShuffle();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isShuffled() {
        return this.source.isShuffled();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void setShuffled(boolean z, boolean z2) {
        this.source.setShuffled(z, z2);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public RepeatMode getRepeatMode() {
        return this.source.getRepeatMode();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void setRepeatMode(RepeatMode repeatMode) {
        this.source.setRepeatMode(repeatMode);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public EnumSet<RepeatMode> getAvailableRepeatModes() {
        return this.source.getAvailableRepeatModes();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isLive() {
        return this.source.isLive();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public RatingsProvider getRatingsProvider() {
        return this.source.getRatingsProvider();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void setCurrentIndex(int i, ChangeReason changeReason) {
        if (i == 0) {
            this.source.goToBeginning(changeReason);
            return;
        }
        StrictMode.crashIfStrict("Index not supported for " + this);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void goToBeginning(ChangeReason changeReason) {
        this.source.goToBeginning(changeReason);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MetricsTimer getMetricsTimer(String str) {
        return this.source.getMetricsTimer(str);
    }
}
