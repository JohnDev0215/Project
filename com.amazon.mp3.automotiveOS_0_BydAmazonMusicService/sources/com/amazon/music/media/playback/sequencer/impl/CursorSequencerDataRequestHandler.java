package com.amazon.music.media.playback.sequencer.impl;

import android.database.Cursor;
import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CursorSequencerDataRequestHandler extends AbstractSequencerDataRequestHandler {
    private Cursor cursor;

    public abstract Cursor createCursor(boolean z) throws PlaybackException;

    public CursorSequencerDataRequestHandler(AsyncSequencer asyncSequencer, boolean z) {
        super(asyncSequencer);
        setAutoShuffleEnabled(z);
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public void invalidate() {
        if (this.cursor != null) {
            this.logger.info("close -> shutdown(false) called, invalidate()");
            this.cursor.close();
            this.cursor = null;
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public int getUnshuffledIndex() {
        return this.cursor.getPosition();
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public void moveToUnshuffledIndex(int i) {
        this.cursor.moveToPosition(i);
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public void moveToShuffledIndex(int i) {
        if (isAutoShuffleEnabled()) {
            super.moveToShuffledIndex(i);
        } else {
            this.cursor.moveToPosition(i);
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public boolean isPreparedToCreateItems() {
        return this.cursor != null;
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public void prepareToCreateItems() throws PlaybackException {
        if (this.cursor == null) {
            this.cursor = createCursor(this.sequenceData.shuffled);
        }
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public int getAvailableItemCount() {
        Cursor cursor = this.cursor;
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
    public int getTotalItemCount() {
        Cursor cursor = this.cursor;
        if (cursor == null) {
            return -1;
        }
        return cursor.getCount();
    }
}
