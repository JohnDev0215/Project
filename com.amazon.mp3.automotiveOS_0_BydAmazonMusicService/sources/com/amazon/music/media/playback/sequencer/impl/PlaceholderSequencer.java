package com.amazon.music.media.playback.sequencer.impl;

import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.sequencer.AbstractSequencer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PlaceholderSequencer extends AbstractSequencer<AbstractSequencer.SequencerInfo> {
    private boolean failed;

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public int getCurrentIndex() {
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getCurrentMediaItem() {
        return null;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getUnshuffledIndex(int i) {
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isAtBeginning() {
        return true;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isIndexBased() {
        return false;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public void setCurrentIndex(int i, ChangeReason changeReason) {
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    protected MediaItem skip(int i, boolean z, ChangeReason changeReason) {
        return null;
    }

    private static AbstractSequencer.SequencerInfo createSequencerInfo() {
        AbstractSequencer.SequencerInfo sequencerInfo = new AbstractSequencer.SequencerInfo();
        sequencerInfo.setCanSkipNext(false);
        sequencerInfo.setCanSkipPrevious(false);
        return sequencerInfo;
    }

    public PlaceholderSequencer() {
        super(createSequencerInfo(), false);
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isAfterEnd() {
        return this.failed;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public ActionAvailability getSkipNextAvailability() {
        return ActionAvailability.UNAVAILABLE;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public List<MediaItem> getUpcomingMediaItems(int i) {
        return Collections.emptyList();
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public List<MediaItem> getPreviousMediaItems(int i) {
        return Collections.emptyList();
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchCurrentMediaItem() {
        return !this.failed;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public int getNumMediaItems() {
        return this.failed ? 0 : -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    protected AbstractSequencer.SequencerInfo newSequencerInfo(AbstractSequencer.SequencerInfo sequencerInfo) {
        return new AbstractSequencer.SequencerInfo();
    }

    public void onFailedToLoad(PlaybackException playbackException) {
        if (this.failed) {
            return;
        }
        this.failed = true;
        notifyErrorFetchingMediaItems(playbackException);
    }
}
