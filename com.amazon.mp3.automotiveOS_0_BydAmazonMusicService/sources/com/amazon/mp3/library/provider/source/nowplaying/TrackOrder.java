package com.amazon.mp3.library.provider.source.nowplaying;

/* JADX INFO: loaded from: classes3.dex */
public interface TrackOrder {
    int getCurrentCollectionPosition();

    int getCurrentPlaybackPosition();

    int getMax();

    int getNextPosition();

    int getPreviousPosition();

    void onPositionChanged(int position);

    void reset();

    void setCurrentPosition(int position);

    void setMax(int max);

    void setRepeat(boolean repeat);
}
