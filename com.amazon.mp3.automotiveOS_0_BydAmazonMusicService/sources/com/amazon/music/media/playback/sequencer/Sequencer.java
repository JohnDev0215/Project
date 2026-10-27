package com.amazon.music.media.playback.sequencer;

import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.util.MetricsTimer;
import java.util.EnumSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface Sequencer {
    void addSequencerListener(SequencerListener sequencerListener);

    boolean canFetchCurrentMediaItem();

    boolean canFetchPreviousMediaItems();

    boolean canFetchUpcomingMediaItems();

    boolean canShuffle();

    boolean canSkipNext();

    boolean canSkipPrevious();

    boolean canSkipToIndex();

    void fetchCurrentMediaItem();

    void fetchPreviousMediaItems(int i);

    void fetchUpcomingMediaItems(int i);

    EnumSet<RepeatMode> getAvailableRepeatModes();

    int getCurrentIndex();

    MediaItem getCurrentMediaItem();

    MediaCollectionInfo getMediaCollectionInfo();

    MediaItem getMediaItem(int i);

    MetricsTimer getMetricsTimer(String str);

    int getNumMediaItems();

    List<MediaItem> getPreviousMediaItems(int i);

    RatingsProvider getRatingsProvider();

    RepeatMode getRepeatMode();

    ActionAvailability getSkipNextAvailability();

    int getUnshuffledIndex(int i);

    List<MediaItem> getUpcomingMediaItems(int i);

    int getUpcomingPreviewSize();

    void goToBeginning(ChangeReason changeReason);

    int indexOf(MediaItem mediaItem);

    boolean isAfterEnd();

    boolean isAtBeginning();

    boolean isFetchingCurrentMediaItem();

    boolean isFetchingPreviousMediaItems();

    boolean isFetchingUpcomingMediaItems();

    boolean isIndexBased();

    boolean isLive();

    boolean isShuffled();

    void notifySequencerInfoChanged();

    MediaItem peek(int i);

    void removeSequencerListener(SequencerListener sequencerListener);

    void setCurrentIndex(int i, ChangeReason changeReason);

    void setRepeatMode(RepeatMode repeatMode);

    void setShuffled(boolean z, boolean z2);

    MediaItem skip(int i, ChangeReason changeReason);
}
