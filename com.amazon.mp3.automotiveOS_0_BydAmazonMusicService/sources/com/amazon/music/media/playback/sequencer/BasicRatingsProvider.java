package com.amazon.music.media.playback.sequencer;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.util.ListenerSet;

/* JADX INFO: loaded from: classes4.dex */
public class BasicRatingsProvider implements RatingsProvider, RatingsProvider.OnRatingsChangedListener {
    private final boolean canRate;
    private final ListenerSet<RatingsProvider.OnRatingsChangedListener> listeners = new ListenerSet<>(RatingsProvider.OnRatingsChangedListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES);

    public BasicRatingsProvider(boolean z) {
        this.canRate = z;
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider
    public void addOnRatingsChangedListener(RatingsProvider.OnRatingsChangedListener onRatingsChangedListener) {
        this.listeners.add(onRatingsChangedListener);
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider
    public void removeOnRatingsChangedListener(RatingsProvider.OnRatingsChangedListener onRatingsChangedListener) {
        this.listeners.remove(onRatingsChangedListener);
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider
    public boolean canRateMediaItems() {
        return this.canRate;
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider.OnRatingsChangedListener
    public void onRatingsChanged(@NonNull MediaItem mediaItem, int i, int i2) {
        this.listeners.proxy().onRatingsChanged(mediaItem, i, i2);
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider.OnRatingsChangedListener
    public void onAllRatingsChanged() {
        this.listeners.proxy().onAllRatingsChanged();
    }
}
