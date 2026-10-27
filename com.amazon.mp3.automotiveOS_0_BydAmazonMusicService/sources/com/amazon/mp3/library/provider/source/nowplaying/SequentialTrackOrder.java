package com.amazon.mp3.library.provider.source.nowplaying;

/* JADX INFO: loaded from: classes3.dex */
public class SequentialTrackOrder implements TrackOrder {
    private int mMax;
    private int mPosition;
    private boolean mRepeat;

    public SequentialTrackOrder(int startPosition, int max, boolean repeat) {
        this.mMax = max;
        this.mRepeat = repeat;
        this.mPosition = startPosition;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public void setCurrentPosition(int position) {
        this.mPosition = position;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public int getCurrentPlaybackPosition() {
        return this.mPosition;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public int getCurrentCollectionPosition() {
        return getCurrentPlaybackPosition();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public int getNextPosition() {
        int i = this.mPosition + 1;
        this.mPosition = i;
        int i2 = this.mMax;
        if (i >= i2 && this.mRepeat) {
            this.mPosition = 0;
        } else if (i >= i2) {
            this.mPosition = i2 - 1;
        }
        return this.mPosition;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public int getPreviousPosition() {
        int i = this.mPosition - 1;
        this.mPosition = i;
        if (i < 0 && this.mRepeat) {
            this.mPosition = this.mMax - 1;
        } else if (i < 0) {
            this.mPosition = 0;
        }
        return this.mPosition;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public void setRepeat(boolean repeat) {
        this.mRepeat = repeat;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public void setMax(int max) {
        this.mMax = max;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public int getMax() {
        return this.mMax;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public void reset() {
        this.mPosition = -1;
        this.mMax = -1;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackOrder
    public void onPositionChanged(int position) {
        setCurrentPosition(position);
    }
}
