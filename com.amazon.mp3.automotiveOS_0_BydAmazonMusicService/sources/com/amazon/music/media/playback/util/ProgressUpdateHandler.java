package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlaybackController;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ProgressUpdateHandler {
    private final OnPositionChangedListener callback;
    private volatile boolean enabled;
    private long lastPosition;
    private boolean pending;
    private final PlaybackController playbackController;
    private boolean positionChanged;
    private final long updateInterval;
    private final Object lock = new Object();
    private final OnPlayStateChangedListener listener = new OnPlayStateChangedListener() { // from class: com.amazon.music.media.playback.util.ProgressUpdateHandler.1
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            ProgressUpdateHandler.this.checkTimer(true);
        }
    };
    private final Runnable timerCallback = new Runnable() { // from class: com.amazon.music.media.playback.util.ProgressUpdateHandler.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (ProgressUpdateHandler.this.lock) {
                ProgressUpdateHandler.this.pending = false;
                if (ProgressUpdateHandler.this.enabled) {
                    long positionMillis = ProgressUpdateHandler.this.getPositionMillis();
                    boolean z = ProgressUpdateHandler.this.positionChanged;
                    ProgressUpdateHandler.this.positionChanged = false;
                    ProgressUpdateHandler.this.lastPosition = positionMillis;
                    ProgressUpdateHandler.this.checkTimer(false);
                    if (z) {
                        ProgressUpdateHandler.this.onUpdate(positionMillis);
                    }
                }
            }
        }
    };

    public interface OnPositionChangedListener {
        void onPositionChanged(long j);
    }

    public ProgressUpdateHandler(@NonNull PlaybackController playbackController, long j, OnPositionChangedListener onPositionChangedListener) {
        this.playbackController = (PlaybackController) Validate.notNull(playbackController);
        this.callback = onPositionChangedListener;
        if (j <= 0) {
            StrictMode.crashIfStrict("updateInterval should be positive, not: " + j);
            this.updateInterval = 1000L;
            return;
        }
        this.updateInterval = j;
    }

    public final void setEnabled(boolean z) {
        synchronized (this.lock) {
            if (this.enabled != z) {
                this.enabled = z;
                if (z) {
                    this.playbackController.addOnPlayStateChangedListener(this.listener);
                    checkTimer(false);
                } else {
                    this.playbackController.removeOnPlayStateChangedListener(this.listener);
                }
            }
        }
    }

    public final long getPositionMillis() {
        long positionMillis;
        synchronized (this.lock) {
            positionMillis = this.playbackController.getPositionMillis();
            if (this.lastPosition != positionMillis) {
                this.positionChanged = true;
            }
        }
        return positionMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTimer(boolean z) {
        synchronized (this.lock) {
            if (this.enabled && !this.pending) {
                if (!this.playbackController.getPlayStatus().isPositionChanging() && this.playbackController.getPlaybackSpeed() == 1.0f) {
                    if (z) {
                        getPositionMillis();
                    }
                    if (!this.positionChanged) {
                        return;
                    }
                }
                this.pending = true;
                ThreadUtils.postOnMainThreadDelayed(this.timerCallback, this.updateInterval);
            }
        }
    }

    protected void onUpdate(long j) {
        OnPositionChangedListener onPositionChangedListener = this.callback;
        if (onPositionChangedListener != null) {
            try {
                onPositionChangedListener.onPositionChanged(j);
            } catch (Exception e) {
                StrictMode.crashIfStrict("Crash in onUpdate(" + j + ")", e);
            }
        }
    }
}
