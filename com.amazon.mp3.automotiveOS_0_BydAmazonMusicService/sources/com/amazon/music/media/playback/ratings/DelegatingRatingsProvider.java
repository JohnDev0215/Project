package com.amazon.music.media.playback.ratings;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.util.ListenerSet;

/* JADX INFO: loaded from: classes4.dex */
public class DelegatingRatingsProvider implements RatingsProvider {
    private static final Object lock = new Object();
    private RatingsProvider source;
    private final ListenerSet<RatingsProvider.OnRatingsChangedListener> listeners = new ListenerSet<>(RatingsProvider.OnRatingsChangedListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES);
    private RatingsProvider.OnRatingsChangedListener listener = new RatingsProvider.OnRatingsChangedListener() { // from class: com.amazon.music.media.playback.ratings.DelegatingRatingsProvider.1
        @Override // com.amazon.music.media.playback.ratings.RatingsProvider.OnRatingsChangedListener
        public void onRatingsChanged(@NonNull MediaItem mediaItem, int i, int i2) {
            ((RatingsProvider.OnRatingsChangedListener) DelegatingRatingsProvider.this.listeners.proxy()).onRatingsChanged(mediaItem, i, i2);
        }

        @Override // com.amazon.music.media.playback.ratings.RatingsProvider.OnRatingsChangedListener
        public void onAllRatingsChanged() {
            ((RatingsProvider.OnRatingsChangedListener) DelegatingRatingsProvider.this.listeners.proxy()).onAllRatingsChanged();
        }
    };

    public DelegatingRatingsProvider(RatingsProvider ratingsProvider) {
        this.source = ratingsProvider;
    }

    public void setSource(RatingsProvider ratingsProvider) {
        synchronized (lock) {
            RatingsProvider ratingsProvider2 = this.source;
            this.source = ratingsProvider;
            if (ratingsProvider2 != ratingsProvider && !this.listeners.isEmpty()) {
                if (ratingsProvider2 != null) {
                    ratingsProvider2.removeOnRatingsChangedListener(this.listener);
                }
                if (ratingsProvider != null) {
                    ratingsProvider.addOnRatingsChangedListener(this.listener);
                }
                this.listeners.proxy().onAllRatingsChanged();
            }
        }
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider
    public void addOnRatingsChangedListener(RatingsProvider.OnRatingsChangedListener onRatingsChangedListener) {
        RatingsProvider ratingsProvider;
        synchronized (lock) {
            if (this.listeners.isEmpty() && onRatingsChangedListener != null && (ratingsProvider = this.source) != null) {
                ratingsProvider.addOnRatingsChangedListener(onRatingsChangedListener);
            }
            this.listeners.add(onRatingsChangedListener);
        }
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider
    public void removeOnRatingsChangedListener(RatingsProvider.OnRatingsChangedListener onRatingsChangedListener) {
        RatingsProvider ratingsProvider;
        synchronized (lock) {
            if (!this.listeners.isEmpty()) {
                this.listeners.remove(onRatingsChangedListener);
                if (this.listeners.isEmpty() && (ratingsProvider = this.source) != null) {
                    ratingsProvider.removeOnRatingsChangedListener(onRatingsChangedListener);
                }
            }
        }
    }

    @Override // com.amazon.music.media.playback.ratings.RatingsProvider
    public boolean canRateMediaItems() {
        RatingsProvider ratingsProvider = this.source;
        return ratingsProvider != null && ratingsProvider.canRateMediaItems();
    }
}
