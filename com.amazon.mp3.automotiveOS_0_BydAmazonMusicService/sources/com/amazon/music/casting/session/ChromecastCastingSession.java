package com.amazon.music.casting.session;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.core.os.EnvironmentCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.casting.CastingConnectFailedType;
import com.amazon.music.metrics.mts.event.definition.casting.CastingDeviceConnectFailedEvent;
import com.amazon.music.metrics.mts.event.definition.casting.CastingDeviceConnectedEvent;
import com.amazon.music.metrics.mts.event.definition.casting.CastingDeviceDisconnectedEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastCastingSession implements CastingSession {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastCastingSession.class);
    private CastingDevice activeDevice;
    private final CastContext castContext;
    private long castingVersion;
    private CastingConnectionCallback connectionListener;
    private final Handler foregroundHandler;
    private Long lastConnectAttemptedTimestamp;
    private final MediaRouteSelector mediaRouteSelector;
    private final MediaRouter mediaRouter;
    private TerminationReason terminationReason;
    private AtomicBoolean isConnected = new AtomicBoolean(false);
    private AtomicBoolean isConnecting = new AtomicBoolean(false);
    final SessionManagerListener<CastSession> castSessionSessionManagerListener = new SessionManagerListener<CastSession>() { // from class: com.amazon.music.casting.session.ChromecastCastingSession.1
        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarting(CastSession castSession) {
            ChromecastCastingSession.LOG.debug("onSessionStarting");
            ChromecastCastingSession.this.isConnecting.set(true);
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarted(CastSession castSession, String str) {
            ChromecastCastingSession.LOG.debug("onSessionStarted");
            ChromecastCastingSession.this.notifyOnConnected();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStartFailed(CastSession castSession, int i) {
            ChromecastCastingSession.LOG.debug("onSessionStartFailed");
            MetricsHolder.getManager().handleEvent(new CastingDeviceConnectFailedEvent(CastingCategory.CHROMECAST.getName(), CastingConnectFailedType.NETWORK_ERROR, ChromecastCastingSession.this.castingVersion));
            ChromecastCastingSession.this.terminationReason = TerminationReason.NETWORK_ERROR;
            ChromecastCastingSession.this.notifyOnDisconnected();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnding(CastSession castSession) {
            ChromecastCastingSession.LOG.debug("onSessionEnding");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnded(CastSession castSession, int i) {
            ChromecastCastingSession.LOG.debug("onSessionEnded");
            ChromecastCastingSession.this.notifyOnDisconnected();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResuming(CastSession castSession, String str) {
            ChromecastCastingSession.this.lastConnectAttemptedTimestamp = Long.valueOf(System.currentTimeMillis());
            ChromecastCastingSession.LOG.debug("onSessionResuming");
            ChromecastCastingSession.this.isConnecting.set(true);
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumed(CastSession castSession, boolean z) {
            ChromecastCastingSession.LOG.debug("onSessionResumed");
            ChromecastCastingSession.this.notifyOnConnected();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumeFailed(CastSession castSession, int i) {
            ChromecastCastingSession.LOG.debug("onSessionResumeFailed");
            MetricsHolder.getManager().handleEvent(new CastingDeviceConnectFailedEvent(CastingCategory.CHROMECAST.getName(), CastingConnectFailedType.NETWORK_ERROR, ChromecastCastingSession.this.castingVersion));
            ChromecastCastingSession.this.terminationReason = TerminationReason.NETWORK_ERROR;
            ChromecastCastingSession.this.notifyOnDisconnected();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionSuspended(CastSession castSession, int i) {
            ChromecastCastingSession.LOG.debug("onSessionSuspended");
            MetricsHolder.getManager().handleEvent(new CastingDeviceDisconnectedEvent(ChromecastCastingSession.this.activeDevice.getName(), castSession.getCastDevice().getModelName(), null, ChromecastCastingSession.this.activeDevice.getCategory().getName(), TerminationReason.UNKNOWN, ChromecastCastingSession.this.castingVersion));
        }
    };

    @Override // com.amazon.music.casting.session.CastingSession
    public NowPlayingThingShadow.State.ReportedNode getNowPlayingReportedNode() {
        return null;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void next() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void onVolumeDown() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void onVolumeUp() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void pause() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void play() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void previous() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void seek(long j) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setLoopMode(NowPlayingThingShadow.State.LoopMode loopMode) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setShuffled(boolean z) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateAllowedParentalControls(ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateQueue(CreateQueueRequest createQueueRequest, CastingQueueCallback castingQueueCallback) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateQueue(TransferQueueRequest transferQueueRequest, CastingQueueCallback castingQueueCallback) {
    }

    public ChromecastCastingSession(Context context, long j) {
        String receiverApplicationId = ChromecastController.getInstance().getReceiverApplicationId();
        this.castingVersion = j;
        this.mediaRouter = MediaRouter.getInstance(context);
        this.mediaRouteSelector = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(receiverApplicationId)).build();
        this.foregroundHandler = new Handler(Looper.getMainLooper());
        this.castContext = CastContext.getSharedInstance(context);
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void connect(final CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback, CastingConnectionType castingConnectionType) {
        LOG.debug("connect, with cast state " + this.castContext.getCastState());
        killZombieSessionDuringConnect();
        this.connectionListener = castingConnectionCallback;
        this.activeDevice = castingDevice;
        this.lastConnectAttemptedTimestamp = Long.valueOf(System.currentTimeMillis());
        this.castContext.getSessionManager().addSessionManagerListener(this.castSessionSessionManagerListener, CastSession.class);
        this.foregroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.ChromecastCastingSession.2
            @Override // java.lang.Runnable
            public void run() {
                for (MediaRouter.RouteInfo routeInfo : ChromecastCastingSession.this.mediaRouter.getRoutes()) {
                    if (routeInfo.getId().equals(castingDevice.getTargetId())) {
                        ChromecastCastingSession.this.mediaRouter.selectRoute(routeInfo);
                        return;
                    }
                }
            }
        });
    }

    private void killZombieSessionDuringConnect() {
        try {
            if (this.castContext.getCastState() == 4) {
                SessionManager sessionManager = this.castContext.getSessionManager();
                CastSession currentCastSession = sessionManager.getCurrentCastSession();
                Logger logger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Ending a zombie Cast Framework session, id ");
                sb.append(currentCastSession != null ? currentCastSession.getSessionId() : EnvironmentCompat.MEDIA_UNKNOWN);
                logger.warn(sb.toString());
                sessionManager.endCurrentSession(true);
            }
        } catch (Exception e) {
            LOG.error("Ending a zombie Cast Framework session failed", (Throwable) e);
        }
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void disconnect(final TerminationReason terminationReason) {
        this.foregroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.ChromecastCastingSession.3
            @Override // java.lang.Runnable
            public void run() {
                TerminationReason terminationReason2 = terminationReason;
                if (terminationReason2 == null) {
                    terminationReason2 = TerminationReason.NETWORK_ERROR;
                }
                TerminationReason terminationReason3 = terminationReason2;
                CastSession castSession = ChromecastCastingSession.this.getCastSession();
                if (ChromecastCastingSession.this.activeDevice != null && castSession != null) {
                    MetricsHolder.getManager().handleEvent(new CastingDeviceDisconnectedEvent(ChromecastCastingSession.this.activeDevice.getName(), castSession.getCastDevice().getModelName(), null, ChromecastCastingSession.this.activeDevice.getCategory().getName(), terminationReason3, ChromecastCastingSession.this.castingVersion));
                }
                ChromecastCastingSession.this.mediaRouter.unselect(1);
                ChromecastCastingSession.this.activeDevice = null;
                ChromecastCastingSession.this.lastConnectAttemptedTimestamp = null;
            }
        });
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void restore(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback) {
        connect(castingDevice, castingConnectionCallback, castingPlaybackCallback, CastingConnectionType.MANUAL);
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public synchronized void refresh() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public boolean isConnected() {
        return this.isConnected.get();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public boolean isConnecting() {
        return this.isConnecting.get();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public CastingDevice getCastingDevice() {
        return this.activeDevice;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setVolume(float f) {
        if (getCastSession() != null) {
            try {
                getCastSession().setVolume(f / 100.0f);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        LOG.error("Cannot setVolume as castSession is null");
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public float getVolume() {
        if (getCastSession() != null) {
            return ((float) getCastSession().getVolume()) * 100.0f;
        }
        LOG.error("Cannot getVolume as castSession is null");
        return 0.0f;
    }

    protected CastSession getCastSession() {
        CastContext castContext = this.castContext;
        if (castContext == null || castContext.getSessionManager() == null) {
            return null;
        }
        return this.castContext.getSessionManager().getCurrentCastSession();
    }

    protected void notifyOnDisconnected() {
        CastingConnectionCallback castingConnectionCallback;
        TerminationReason terminationReason = TerminationReason.NETWORK_ERROR;
        TerminationReason terminationReason2 = this.terminationReason;
        if (terminationReason2 != null) {
            terminationReason = terminationReason2;
        }
        this.castContext.getSessionManager().removeSessionManagerListener(this.castSessionSessionManagerListener, CastSession.class);
        this.activeDevice = null;
        if ((this.isConnected.compareAndSet(true, false) || this.isConnecting.compareAndSet(true, false)) && (castingConnectionCallback = this.connectionListener) != null) {
            castingConnectionCallback.onDisconnected(terminationReason);
        }
    }

    protected void notifyOnConnected() {
        this.isConnected.set(true);
        this.isConnecting.set(false);
        if (this.connectionListener == null || this.activeDevice == null) {
            return;
        }
        if (this.lastConnectAttemptedTimestamp != null) {
            this.foregroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.ChromecastCastingSession.4
                @Override // java.lang.Runnable
                public void run() {
                    CastSession castSession = ChromecastCastingSession.this.getCastSession();
                    if (castSession != null) {
                        MetricsHolder.getManager().handleEvent(new CastingDeviceConnectedEvent(ChromecastCastingSession.this.activeDevice.getName(), castSession.getCastDevice().getModelName(), null, ChromecastCastingSession.this.activeDevice.getCategory().getName(), CastingConnectionType.MANUAL.getName(), System.currentTimeMillis() - ChromecastCastingSession.this.lastConnectAttemptedTimestamp.longValue(), ChromecastCastingSession.this.castingVersion));
                        ChromecastCastingSession.this.lastConnectAttemptedTimestamp = null;
                    }
                }
            });
        }
        this.connectionListener.onConnected(this.activeDevice);
    }
}
