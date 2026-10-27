package com.amazon.music.media.playback;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.config.PlaybackPersistenceProvider;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.media.playback.impl.IOnPlaybackCommandListener;
import com.amazon.music.media.playback.impl.MediaSessionController;
import com.amazon.music.media.playback.impl.NullPlaybackProvider;
import com.amazon.music.media.playback.impl.PlaybackControllerImpl;
import com.amazon.music.media.playback.impl.PlaybackService;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class Playback {
    private static volatile Playback playback;
    private AudioFocusManager audioFocusManager;
    private volatile MediaSessionController mediaSessionController;
    private String mricToken;
    private volatile PlaybackConfig playbackConfig;
    private static final Logger logger = LoggerFactory.getLogger(Playback.class.getSimpleName());
    private static final Object instanceLock = new Object();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Map<ControlSource, PlaybackControllerImpl> controllers = new HashMap();
    private final ListenerSet<IOnPlaybackCommandListener> commandListeners = new ListenerSet<>(IOnPlaybackCommandListener.class, ListenerSet.DispatchStrategy.SYNCHRONOUS, ListenerSet.DuplicateStrategy.ALLOW_DUPLICATES);
    private volatile PlaybackProvider playbackProvider = NullPlaybackProvider.INSTANCE;
    private PlaybackProvider.Connection connection = this.playbackProvider.openConnection(null);

    public static Playback getInstance() {
        if (playback == null) {
            synchronized (instanceLock) {
                if (playback == null) {
                    playback = new Playback();
                }
            }
        }
        return playback;
    }

    public void init(@NonNull final PlaybackConfig playbackConfig) {
        this.lock.writeLock().lock();
        try {
            if (this.playbackConfig != null) {
                return;
            }
            this.playbackConfig = (PlaybackConfig) Validate.notNull(playbackConfig);
            Context context = playbackConfig.getContext();
            AndroidUtils.init(context);
            this.audioFocusManager = new AudioFocusManager(context);
            if (!playbackConfig.isUnitTestMode()) {
                PlaybackController playbackController = getPlaybackController(ControlSource.EXTERNAL);
                this.mediaSessionController = new MediaSessionController(context, playbackController);
                playbackController.addOnPlayStateChangedListener(new OnPlayStateChangedListener() { // from class: com.amazon.music.media.playback.Playback.1
                    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
                    public void onPlayStateChanged() {
                        PlaybackService.startIfNeeded(playbackConfig);
                    }
                });
                AndroidUtils.addForegroundListener(new AndroidUtils.ForegroundListener() { // from class: com.amazon.music.media.playback.Playback.2
                    @Override // com.amazon.music.media.playback.util.AndroidUtils.ForegroundListener
                    public void onApplicationInForegroundChanged(boolean z) {
                        PlaybackService.startIfNeeded(playbackConfig);
                    }
                });
            }
            PlaybackPersistenceProvider playbackPersistenceProvider = playbackConfig.getPlaybackPersistenceProvider();
            setPlaybackProvider(playbackConfig.getDefaultPlaybackProvider(this.mricToken));
            if (playbackPersistenceProvider != null) {
                ControlSource controlSource = ControlSource.NONE;
                playbackPersistenceProvider.setPlaybackController(getPlaybackController(controlSource));
                if (this.playbackProvider instanceof SequencerPlaybackProvider) {
                    ((SequencerPlaybackProvider) this.playbackProvider).setSequencer(new PlaceholderSequencer(), controlSource);
                    playbackPersistenceProvider.restore();
                }
            }
            playbackConfig.initializePrimeVideoPlayer();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public PlaybackConfig getPlaybackConfig() {
        return this.playbackConfig;
    }

    public void addOnPlaybackCommandListener(OnPlaybackCommandListener onPlaybackCommandListener) {
        this.commandListeners.add(onPlaybackCommandListener);
    }

    public void setMediaButtonListener(OnMediaButtonListener onMediaButtonListener) {
        if (this.mediaSessionController != null) {
            this.mediaSessionController.setMediaButtonListener(onMediaButtonListener);
        }
    }

    public void setPlaybackProvider(final PlaybackProvider playbackProvider) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.Playback.3
            @Override // java.lang.Runnable
            public void run() {
                Playback.this.lock.writeLock().lock();
                try {
                    Playback.logger.info("setPlaybackProvider(" + playbackProvider + ")");
                    PlaybackProvider playbackProvider2 = playbackProvider;
                    if (playbackProvider2 == null) {
                        playbackProvider2 = NullPlaybackProvider.INSTANCE;
                    }
                    if (Playback.this.playbackProvider == playbackProvider2) {
                        return;
                    }
                    Playback.logger.info("setPlaybackProvider, calling close() -> shutdown(false)");
                    Playback.this.connection.close();
                    Playback.logger.info("closed " + Playback.this.connection);
                    Playback.this.playbackProvider = playbackProvider2;
                    PlaybackPersistenceProvider playbackPersistenceProvider = Playback.this.playbackConfig.getPlaybackPersistenceProvider();
                    Playback playback2 = Playback.this;
                    playback2.connection = playbackProvider2.openConnection(playback2);
                    Playback.logger.info("opened: " + Playback.this.connection);
                    Iterator it = Playback.this.controllers.values().iterator();
                    while (it.hasNext()) {
                        ((PlaybackControllerImpl) it.next()).setConnection(Playback.this.connection);
                    }
                    Playback.logger.info("controller connections set.");
                    if (playbackPersistenceProvider != null) {
                        Playback.logger.info("setting persistenceProvider's PlaybackProvider");
                        playbackPersistenceProvider.setPlaybackProvider(playbackProvider2);
                    }
                    Playback.logger.info("setPlaybackProvider returns");
                } finally {
                    Playback.this.lock.writeLock().unlock();
                }
            }
        });
    }

    public PlaybackProvider getPlaybackProvider() {
        return this.playbackProvider;
    }

    public PlaybackController getPlaybackController(ControlSource controlSource) {
        this.lock.readLock().lock();
        if (controlSource == null) {
            try {
                controlSource = ControlSource.NONE;
            } catch (Throwable th) {
                this.lock.readLock().unlock();
                throw th;
            }
        }
        PlaybackControllerImpl playbackControllerImpl = this.controllers.get(controlSource);
        if (playbackControllerImpl == null) {
            PlaybackControllerImpl playbackControllerImpl2 = new PlaybackControllerImpl(this, this.commandListeners.proxy(), controlSource, this.connection, this.lock.readLock());
            this.controllers.put(controlSource, playbackControllerImpl2);
            playbackControllerImpl = playbackControllerImpl2;
        }
        this.lock.readLock().unlock();
        return playbackControllerImpl;
    }

    public MediaSessionCompat getMediaSession() {
        if (this.mediaSessionController == null) {
            return null;
        }
        return this.mediaSessionController.getMediaSession();
    }

    public AudioFocusManager getAudioFocusManager() {
        return this.audioFocusManager;
    }

    @WorkerThread
    public void clearData() {
        getPlaybackController(ControlSource.APP_UI).stop(ChangeReason.NEW_SOURCE);
        this.playbackConfig.getLocalPlayer(this.mricToken).clearData();
    }

    public void setMricToken(@Nullable String str) {
        this.mricToken = str;
    }
}
