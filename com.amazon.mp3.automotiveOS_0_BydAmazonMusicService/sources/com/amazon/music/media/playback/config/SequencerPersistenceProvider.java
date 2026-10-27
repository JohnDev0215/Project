package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.NoOpSequencer;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ProgressUpdateHandler;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SequencerPersistenceProvider implements PlaybackPersistenceProvider {
    private PlaybackController controller;
    private MediaItemId mediaItemId;
    private boolean newPosition;
    private boolean newSequencer;
    private final long persistPositionMillis;
    private PlaceholderSequencer placeholderSequencer;
    private long position;
    private ProgressUpdateHandler progressUpdateHandler;
    private SequencerPlaybackProvider provider;
    private boolean restored;
    private boolean restoring;
    private boolean savePending;
    private Sequencer sequencer;
    private boolean shouldRestore;
    private static final Logger logger = LoggerFactory.getLogger("PlaybackPersistenceProvider");
    private static final long MIN_PERSIST_POSITION_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private final Object lock = new Object();
    private final SequencerPlaybackProvider.MediaItemVerifier verifier = new SequencerPlaybackProvider.MediaItemVerifier() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.1
        @Override // com.amazon.music.media.playback.SequencerPlaybackProvider.MediaItemVerifier
        public boolean isCorrectMediaItem(MediaItem mediaItem) {
            return SequencerPersistenceProvider.this.isPreviousMediaItem(mediaItem);
        }
    };
    private final Runnable saveRunnable = new Runnable() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.2
        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            boolean z2;
            while (true) {
                synchronized (SequencerPersistenceProvider.this.lock) {
                    if (SequencerPersistenceProvider.this.newSequencer || SequencerPersistenceProvider.this.newPosition) {
                        z = SequencerPersistenceProvider.this.newSequencer;
                        z2 = SequencerPersistenceProvider.this.newPosition;
                        SequencerPersistenceProvider.this.newSequencer = false;
                        SequencerPersistenceProvider.this.newPosition = false;
                    } else {
                        SequencerPersistenceProvider.this.savePending = false;
                        return;
                    }
                }
                if (z) {
                    if (SequencerPersistenceProvider.this.sequencer == null) {
                        SequencerPersistenceProvider.this.onDeleteSequencerState();
                    } else {
                        SequencerPersistenceProvider sequencerPersistenceProvider = SequencerPersistenceProvider.this;
                        sequencerPersistenceProvider.onSaveSequencerState(sequencerPersistenceProvider.sequencer);
                    }
                }
                if (z2) {
                    SequencerPersistenceProvider sequencerPersistenceProvider2 = SequencerPersistenceProvider.this;
                    sequencerPersistenceProvider2.onSavePosition(sequencerPersistenceProvider2.position);
                }
            }
        }
    };
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.3
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            if (SequencerPersistenceProvider.this.getPlaybackController().getPlayStatus().willPlay()) {
                SequencerPersistenceProvider.this.restore();
            }
        }
    };
    private AndroidUtils.ForegroundListener mForegroundListener = new AndroidUtils.ForegroundListener() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.4
        @Override // com.amazon.music.media.playback.util.AndroidUtils.ForegroundListener
        public void onApplicationInForegroundChanged(boolean z) {
            SequencerPersistenceProvider.this.restore();
        }
    };
    private final long startTime = Clock.now();

    @WorkerThread
    protected abstract void onDeleteSequencerState();

    @WorkerThread
    protected abstract void onRestore();

    @WorkerThread
    protected abstract void onSavePosition(long j);

    @WorkerThread
    protected abstract void onSaveSequencerState(@NonNull Sequencer sequencer);

    public void markRestored() {
        this.restored = true;
    }

    protected SequencerPersistenceProvider(long j) {
        long j2 = MIN_PERSIST_POSITION_MILLIS;
        if (j < j2) {
            StrictMode.crashIfStrict("persistPositionMillis is less than " + j2);
            this.persistPositionMillis = j2;
        } else {
            this.persistPositionMillis = j;
        }
        AndroidUtils.addForegroundListener(this.mForegroundListener);
    }

    @Override // com.amazon.music.media.playback.config.PlaybackPersistenceProvider
    public void setPlaybackController(PlaybackController playbackController) {
        this.controller = playbackController;
        ProgressUpdateHandler progressUpdateHandler = this.progressUpdateHandler;
        if (progressUpdateHandler != null) {
            progressUpdateHandler.setEnabled(false);
            this.progressUpdateHandler = null;
        }
        if (playbackController != null) {
            new ProgressUpdateHandler(playbackController, this.persistPositionMillis, new ProgressUpdateHandler.OnPositionChangedListener() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.5
                @Override // com.amazon.music.media.playback.util.ProgressUpdateHandler.OnPositionChangedListener
                public void onPositionChanged(long j) {
                    if (SequencerPersistenceProvider.this.sequencer == null || (SequencerPersistenceProvider.this.sequencer instanceof PlaceholderSequencer) || (SequencerPersistenceProvider.this.sequencer instanceof NoOpSequencer)) {
                        return;
                    }
                    SequencerPersistenceProvider.this.savePosition(j);
                }
            }).setEnabled(true);
            playbackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        }
    }

    public PlaybackController getPlaybackController() {
        return this.controller;
    }

    @Override // com.amazon.music.media.playback.config.PlaybackPersistenceProvider
    public void setPlaybackProvider(PlaybackProvider playbackProvider) {
        SequencerPlaybackProvider sequencerPlaybackProvider = playbackProvider instanceof SequencerPlaybackProvider ? (SequencerPlaybackProvider) playbackProvider : null;
        this.provider = sequencerPlaybackProvider;
        Sequencer sequencer = sequencerPlaybackProvider != null ? sequencerPlaybackProvider.getSequencer() : null;
        if (this.sequencer == sequencer || sequencer == null || (sequencer instanceof NoOpSequencer) || (sequencer instanceof PlaceholderSequencer)) {
            return;
        }
        saveSequencerState(sequencer);
    }

    protected final void restore(final Sequencer sequencer, final MediaItemId mediaItemId, final long j) {
        PlaybackController playbackController = getPlaybackController();
        if (playbackController != null) {
            playbackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        }
        AndroidUtils.removeForegroundListener(this.mForegroundListener);
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.6
            @Override // java.lang.Runnable
            public void run() {
                SequencerPersistenceProvider.logger.debug("restore(" + sequencer + ", " + mediaItemId + ", " + j + ")");
                if (sequencer != null) {
                    SequencerPersistenceProvider.logger.debug("shuffled: " + sequencer.isShuffled() + ", repeatMode: " + sequencer.getRepeatMode() + ", index: " + sequencer.getCurrentIndex());
                }
                SequencerPersistenceProvider.this.restored = true;
                if (!SequencerPersistenceProvider.this.shouldRestore()) {
                    SequencerPersistenceProvider.this.placeholderSequencer = null;
                    return;
                }
                SequencerPersistenceProvider.this.placeholderSequencer = null;
                if (sequencer == null) {
                    SequencerPersistenceProvider.this.provider.setSequencer(new NoOpSequencer(), SequencerPersistenceProvider.this.controller.getControlSource());
                    return;
                }
                boolean zWillPlay = SequencerPersistenceProvider.this.controller.getPlayStatus().willPlay();
                SequencerPersistenceProvider.this.mediaItemId = mediaItemId;
                SequencerPersistenceProvider.this.provider.setSequencerAndPosition(sequencer, j, SequencerPersistenceProvider.this.verifier, SequencerPersistenceProvider.this.controller.getControlSource());
                if (zWillPlay) {
                    SequencerPersistenceProvider.this.controller.play(SequencerPersistenceProvider.this.startTime);
                }
            }
        });
    }

    public final boolean shouldRestore() {
        SequencerPlaybackProvider sequencerPlaybackProvider = this.provider;
        return (sequencerPlaybackProvider == null || this.placeholderSequencer == null || sequencerPlaybackProvider.getSequencer() != this.placeholderSequencer) ? false : true;
    }

    @Override // com.amazon.music.media.playback.config.PlaybackPersistenceProvider
    public final void restore() {
        Logger logger2 = logger;
        logger2.debug("restore()");
        synchronized (this.lock) {
            SequencerPlaybackProvider sequencerPlaybackProvider = this.provider;
            if (sequencerPlaybackProvider != null && !this.restored && !this.restoring) {
                this.shouldRestore = true;
                this.restoring = true;
                if (sequencerPlaybackProvider.getSequencer() instanceof PlaceholderSequencer) {
                    this.placeholderSequencer = (PlaceholderSequencer) this.provider.getSequencer();
                } else {
                    PlaceholderSequencer placeholderSequencer = new PlaceholderSequencer();
                    this.placeholderSequencer = placeholderSequencer;
                    this.provider.setSequencer(placeholderSequencer, this.controller.getControlSource());
                }
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.config.SequencerPersistenceProvider.7
                    @Override // java.lang.Runnable
                    public void run() {
                        SequencerPersistenceProvider.logger.debug("restore() in background");
                        while (true) {
                            synchronized (SequencerPersistenceProvider.this.lock) {
                                if (SequencerPersistenceProvider.this.restored || !SequencerPersistenceProvider.this.shouldRestore || SequencerPersistenceProvider.this.provider == null || SequencerPersistenceProvider.this.provider.getSequencer() != SequencerPersistenceProvider.this.placeholderSequencer) {
                                    break;
                                } else {
                                    SequencerPersistenceProvider.this.shouldRestore = false;
                                }
                            }
                            SequencerPersistenceProvider.logger.debug("calling onRestore");
                            try {
                                SequencerPersistenceProvider.this.onRestore();
                            } catch (Exception e) {
                                SequencerPersistenceProvider.logger.error("exception in onRestore()", (Throwable) e);
                                SequencerPersistenceProvider.this.restore(null, null, 0L);
                            }
                        }
                        SequencerPersistenceProvider.logger.debug("done restoring");
                        SequencerPersistenceProvider.this.restoring = false;
                    }
                });
                return;
            }
            logger2.debug("ignoring restore() - provider: " + this.provider + ", restored: " + this.restored + ", restoring: " + this.restoring);
        }
    }

    public final void saveSequencerState(Sequencer sequencer) {
        synchronized (this.lock) {
            this.sequencer = sequencer;
            this.newSequencer = true;
            if (!this.savePending) {
                this.savePending = true;
                ThreadUtils.runInBackground(this.saveRunnable);
            }
        }
    }

    public final void savePosition(long j) {
        synchronized (this.lock) {
            this.position = j;
            this.newPosition = true;
            if (!this.savePending && this.sequencer != null) {
                this.savePending = true;
                ThreadUtils.runInBackground(this.saveRunnable);
            }
        }
    }

    public boolean isPreviousMediaItem(@NonNull MediaItem mediaItem) {
        MediaItemId mediaItemId = this.mediaItemId;
        return mediaItemId != null && mediaItemId.equals(mediaItem.getMediaItemId(mediaItemId.getType()));
    }
}
