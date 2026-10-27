package com.amazon.music.casting.session;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.casting.Casting;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.google.gson.Gson;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class CastingSessionManager {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CastingSessionManager.class);
    private static volatile CastingSessionManager instance;
    private Casting casting;
    private long castingVersion;
    private Context context;
    private boolean initialized;
    private CastingService service;
    private CastingSession session = new NoOpCastingSession();
    private CastingConnectionCallback connectionCallback = new CastingConnectionCallback() { // from class: com.amazon.music.casting.session.CastingSessionManager.1
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(CastingDevice castingDevice) {
            CastingSessionManager.LOG.debug("onConnected() device = " + castingDevice);
            CastingSessionManager.this.saveLastDevice(castingDevice);
            Iterator<CastingConnectionCallback> it = CastingSessionManager.this.connectionListeners.iterator();
            while (it.hasNext()) {
                it.next().onConnected(castingDevice);
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason terminationReason) {
            CastingSessionManager.LOG.debug("onDisconnected()");
            Iterator<CastingConnectionCallback> it = CastingSessionManager.this.connectionListeners.iterator();
            while (it.hasNext()) {
                it.next().onDisconnected(terminationReason);
            }
            CastingSessionManager.this.saveLastDevice(null);
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException castingException) {
            Iterator<CastingConnectionCallback> it = CastingSessionManager.this.connectionListeners.iterator();
            while (it.hasNext()) {
                it.next().onError(castingException);
            }
        }
    };
    private CastingPlaybackCallback playbackCallback = new CastingPlaybackCallback() { // from class: com.amazon.music.casting.session.CastingSessionManager.2
        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onPlay() {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onPlay();
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onPause() {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onPause();
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onStop() {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onStop();
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onSetShuffled(boolean z) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onSetShuffled(z);
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onSetLoopMode(NowPlayingThingShadow.State.LoopMode loopMode) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onSetLoopMode(loopMode);
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onSetRating(NowPlayingThingShadow.State.Rating rating) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onSetRating(rating);
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onSetAvailableFeatures(NowPlayingThingShadow.State.Transport[] transportArr) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onSetAvailableFeatures(transportArr);
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onSetAvailableLoopModes(NowPlayingThingShadow.State.LoopMode[] loopModeArr) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onSetAvailableLoopModes(loopModeArr);
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onTracksMetadataUpdated(MetadataThingShadow.Track[] trackArr) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onTracksMetadataUpdated(trackArr);
            }
        }

        @Override // com.amazon.music.casting.session.CastingPlaybackCallback
        public void onVolumeUpdated(NowPlayingThingShadow.State.ReportedNode.Volume volume) {
            Iterator<CastingPlaybackCallback> it = CastingSessionManager.this.playbackListeners.iterator();
            while (it.hasNext()) {
                it.next().onVolumeUpdated(volume);
            }
        }
    };
    protected final CopyOnWriteArraySet<CastingConnectionCallback> connectionListeners = new CopyOnWriteArraySet<>();
    protected final CopyOnWriteArraySet<CastingPlaybackCallback> playbackListeners = new CopyOnWriteArraySet<>();

    private CastingSessionManager() {
    }

    public static synchronized CastingSessionManager getInstance() {
        if (instance == null) {
            instance = new CastingSessionManager();
        }
        return instance;
    }

    public synchronized void initialize(Casting casting, long j) {
        LOG.debug("initialize()");
        Validate.notNull(casting);
        this.castingVersion = j;
        this.service = casting.getService();
        this.context = casting.getContext();
        this.casting = casting;
        this.initialized = true;
    }

    public synchronized void initialize(Casting casting) {
        initialize(casting, 1L);
    }

    public void clearSession() {
        Logger logger = LOG;
        logger.debug("clearSession()");
        Context context = this.context;
        if (context == null) {
            logger.error("Cannot clear as context is null!");
            return;
        }
        context.getSharedPreferences("com.amazon.music_CASTING", 0).edit().clear().apply();
        this.context.getSharedPreferences("com.amazonaws.android.auth", 0).edit().clear().apply();
        AmazonCastingSession.clearSession();
    }

    public synchronized void connect(CastingDevice castingDevice) {
        connect(castingDevice, CastingConnectionType.MANUAL);
    }

    public synchronized void connect(CastingDevice castingDevice, CastingConnectionType castingConnectionType) {
        Logger logger = LOG;
        logger.debug("connect() device: " + castingDevice);
        logIfNotInitialized();
        CastingSession castingSession = this.session;
        if (castingSession != null && castingSession.isConnecting()) {
            logger.warn("connect() called when already connecting, ignoring the call");
            return;
        }
        CastingSession castingSessionCreateCastingSession = createCastingSession(castingDevice.getCategory());
        this.session = castingSessionCreateCastingSession;
        castingSessionCreateCastingSession.connect(castingDevice, this.connectionCallback, this.playbackCallback, castingConnectionType);
    }

    public synchronized void restore() {
        Logger logger = LOG;
        logger.debug("restore()");
        logIfNotInitialized();
        CastingDevice lastDevice = getLastDevice();
        if (lastDevice != null) {
            CastingSession castingSession = this.session;
            if (castingSession != null && castingSession.isConnecting()) {
                logger.warn("restore() called when already restoring, ignoring the call");
            } else {
                CastingSession castingSessionCreateCastingSession = createCastingSession(lastDevice.getCategory());
                this.session = castingSessionCreateCastingSession;
                castingSessionCreateCastingSession.restore(lastDevice, this.connectionCallback, this.playbackCallback);
            }
        }
    }

    public synchronized void refresh() {
        LOG.debug("refresh()");
        logIfNotInitialized();
        this.session.refresh();
    }

    public synchronized void disconnect(TerminationReason terminationReason) {
        LOG.debug("disconnect()");
        logIfNotInitialized();
        this.session.disconnect(terminationReason);
    }

    public synchronized boolean isInitialized() {
        return this.initialized;
    }

    public boolean isConnected() {
        if (this.initialized) {
            return this.session.isConnected();
        }
        return false;
    }

    public CastingDevice getCastingDevice() {
        if (this.initialized) {
            return this.session.getCastingDevice();
        }
        return null;
    }

    public int getCastingSessionId() {
        if (this.initialized) {
            return this.session.hashCode();
        }
        return -1;
    }

    public void registerListener(CastingConnectionCallback castingConnectionCallback) {
        this.connectionListeners.add(castingConnectionCallback);
    }

    public void unregisterListener(CastingConnectionCallback castingConnectionCallback) {
        this.connectionListeners.remove(castingConnectionCallback);
    }

    public void registerListener(CastingPlaybackCallback castingPlaybackCallback) {
        this.playbackListeners.add(castingPlaybackCallback);
    }

    public CastingDevice getLastDevice() {
        if (this.context == null) {
            LOG.error("Cannot retrieve last device as context is null!");
            return null;
        }
        return (CastingDevice) new Gson().fromJson(this.context.getSharedPreferences("com.amazon.music_CASTING", 0).getString("com.amazon.music.CASTING_LAST_CONNECTED_DEVICE", ""), CastingDevice.class);
    }

    public void play() {
        LOG.debug("play()");
        logIfNotInitialized();
        this.session.play();
    }

    public void updateQueue(CreateQueueRequest createQueueRequest, CastingQueueCallback castingQueueCallback) {
        LOG.debug("updateQueue() request: " + createQueueRequest);
        logIfNotInitialized();
        this.session.updateQueue(createQueueRequest, castingQueueCallback);
    }

    public void updateQueue(TransferQueueRequest transferQueueRequest, CastingQueueCallback castingQueueCallback) {
        LOG.debug("updateQueue() request: " + transferQueueRequest);
        logIfNotInitialized();
        this.session.updateQueue(transferQueueRequest, castingQueueCallback);
    }

    public void pause() {
        LOG.debug("pause()");
        logIfNotInitialized();
        this.session.pause();
    }

    public void previous() {
        LOG.debug("previous()");
        logIfNotInitialized();
        this.session.previous();
    }

    public void next() {
        LOG.debug("next()");
        logIfNotInitialized();
        this.session.next();
    }

    public void seek(long j) {
        logIfNotInitialized();
        this.session.seek(j);
    }

    public void setLoopMode(NowPlayingThingShadow.State.LoopMode loopMode) {
        logIfNotInitialized();
        this.session.setLoopMode(loopMode);
    }

    public void setShuffled(boolean z) {
        logIfNotInitialized();
        this.session.setShuffled(z);
    }

    public void setVolume(float f) {
        logIfNotInitialized();
        this.session.setVolume(f);
    }

    public float getVolume() {
        if (this.initialized) {
            return this.session.getVolume();
        }
        return 0.0f;
    }

    public void onVolumeUp() {
        logIfNotInitialized();
        this.session.onVolumeUp();
    }

    public void onVolumeDown() {
        logIfNotInitialized();
        this.session.onVolumeDown();
    }

    public NowPlayingThingShadow.State.ReportedNode.TrackInfo getTrackInfo() {
        if (this.initialized && this.session.getNowPlayingReportedNode() != null) {
            return this.session.getNowPlayingReportedNode().getTrackInformation();
        }
        return null;
    }

    public NowPlayingThingShadow.State.Transport[] getAvailableFeatures() {
        if (this.initialized && this.session.getNowPlayingReportedNode() != null) {
            return this.session.getNowPlayingReportedNode().getAvailableFeatures();
        }
        return null;
    }

    public void updateAllowedParentalControls(ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
        logIfNotInitialized();
        this.session.updateAllowedParentalControls(allowedParentalControls);
    }

    protected CastingSession createCastingSession(CastingCategory castingCategory) {
        NoOpCastingSession noOpCastingSession = new NoOpCastingSession();
        if (castingCategory == null) {
            return noOpCastingSession;
        }
        if (AnonymousClass3.$SwitchMap$com$amazon$music$casting$CastingCategory[castingCategory.ordinal()] == 1) {
            return new ChromecastCastingSession(this.context, this.castingVersion);
        }
        return new AmazonCastingSession(this.casting, this.castingVersion);
    }

    /* JADX INFO: renamed from: com.amazon.music.casting.session.CastingSessionManager$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$casting$CastingCategory;

        static {
            int[] iArr = new int[CastingCategory.values().length];
            $SwitchMap$com$amazon$music$casting$CastingCategory = iArr;
            try {
                iArr[CastingCategory.CHROMECAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$CastingCategory[CastingCategory.ALEXA_1P.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$CastingCategory[CastingCategory.ALEXA_3P.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$CastingCategory[CastingCategory.ALEXA_WHA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private synchronized void logIfNotInitialized() throws IllegalStateException {
        if (!this.initialized) {
            LOG.error("Forgot to call initialize?");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLastDevice(CastingDevice castingDevice) {
        Context context = this.context;
        if (context == null) {
            LOG.error("Cannot save last device as context is null!");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.amazon.music_CASTING", 0).edit();
        editorEdit.putString("com.amazon.music.CASTING_LAST_CONNECTED_DEVICE", new Gson().toJson(castingDevice));
        editorEdit.commit();
    }

    public Casting getCasting() {
        return this.casting;
    }
}
