package com.amazon.music.media.playback.ratings;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public interface RatingsProvider {

    public interface OnRatingsChangedListener {
        void onAllRatingsChanged();

        void onRatingsChanged(@NonNull MediaItem mediaItem, int i, int i2);
    }

    void addOnRatingsChangedListener(OnRatingsChangedListener onRatingsChangedListener);

    boolean canRateMediaItems();

    void removeOnRatingsChangedListener(OnRatingsChangedListener onRatingsChangedListener);
}
