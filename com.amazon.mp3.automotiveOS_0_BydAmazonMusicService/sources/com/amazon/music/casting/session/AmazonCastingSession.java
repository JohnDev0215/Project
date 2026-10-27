package com.amazon.music.casting.session;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.amazon.music.casting.Casting;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.music.casting.Configuration;
import com.amazon.music.casting.providers.ConnectionInfoProvider;
import com.amazon.music.casting.providers.CreateQueueProvider;
import com.amazon.music.casting.providers.TransferQueueProvider;
import com.amazon.music.casting.session.CastingSessionTimer;
import com.amazon.music.casting.session.iot.IotConnectionConfig;
import com.amazon.music.casting.session.iot.IotConnectionManager;
import com.amazon.music.casting.session.iot.MessengerType;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.casting.session.things.ClientThingShadowMessenger;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger;
import com.amazon.music.casting.session.things.ThingShadowFactory;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.casting.CastingConnectFailedType;
import com.amazon.music.metrics.mts.event.definition.casting.CastingDeviceConnectFailedEvent;
import com.amazon.music.metrics.mts.event.definition.casting.CastingDeviceConnectedEvent;
import com.amazon.music.metrics.mts.event.definition.casting.CastingDeviceDisconnectedEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttLastWillAndTestament;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.google.gson.Gson;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AmazonCastingSession implements CastingSession {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) AmazonCastingSession.class);
    private static GetConnectionInfoResponse connectionInfo;
    private static CognitoCachingCredentialsProvider credentialsProvider;
    private CastingDevice activeDevice;
    private Handler backgroundHandler;
    private CastingSessionConfig castingSessionConfig;
    private long castingVersion;
    protected ClientThingShadowMessenger clientThingShadowMessenger;
    private CastingConnectionCallback connectionCallback;
    private Context context;
    private AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus currentStatus;
    private String deviceTypeId;
    private Handler foregroundHandler;
    protected HandlerThread handlerThread;
    protected IotConnectionManager iotConnectionManager;
    private Long lastConnectAttemptedTimestamp;
    protected NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger;
    private CastingPlaybackCallback playbackCallback;
    private int reconnectAttempts;
    private final int reconnectAttemptsThreshold;
    private CastingService service;
    private Configuration serviceConfig;
    private TerminationReason terminationReason;
    private boolean updateOnlineState;
    private AtomicBoolean hasNotifiedOnDisconnected = new AtomicBoolean();
    private AtomicBoolean hasEmittedCastingInitiated = new AtomicBoolean();
    private CastingConnectionType currentConnectionType = CastingConnectionType.MANUAL;
    protected CastingSessionTimer.CastingSessionTimerListener castingSessionTimerListener = new CastingSessionTimer.CastingSessionTimerListener() { // from class: com.amazon.music.casting.session.AmazonCastingSession.1
        @Override // com.amazon.music.casting.session.CastingSessionTimer.CastingSessionTimerListener
        public void onTimerExpired(TerminationReason terminationReason) {
            AmazonCastingSession.LOG.info("Ending casting session, timer has expired");
            AmazonCastingSession.this.disconnect(terminationReason);
        }
    };

    static /* synthetic */ int access$608(AmazonCastingSession amazonCastingSession) {
        int i = amazonCastingSession.reconnectAttempts;
        amazonCastingSession.reconnectAttempts = i + 1;
        return i;
    }

    public AmazonCastingSession(Casting casting, long j) {
        this.service = casting.getService();
        this.context = casting.getContext();
        this.castingVersion = j;
        Configuration configuration = casting.getConfiguration();
        this.serviceConfig = configuration;
        CastingSessionConfig castingSessionConfig = configuration.getCastingSessionConfig();
        this.castingSessionConfig = castingSessionConfig;
        this.reconnectAttemptsThreshold = castingSessionConfig.getReconnectAttemptsThreshold();
        LOG.debug("AmazonCastingSession started with " + this.castingSessionConfig);
    }

    public static void clearSession() {
        LOG.debug("clearSession()");
        connectionInfo = null;
        credentialsProvider = null;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public synchronized void connect(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback, CastingConnectionType castingConnectionType) {
        LOG.debug("connect() device = " + castingDevice);
        this.connectionCallback = castingConnectionCallback;
        this.playbackCallback = castingPlaybackCallback;
        this.activeDevice = castingDevice;
        this.currentConnectionType = castingConnectionType;
        this.lastConnectAttemptedTimestamp = Long.valueOf(System.currentTimeMillis());
        CastingDevice castingDevice2 = this.activeDevice;
        if (castingDevice2 != null) {
            this.deviceTypeId = castingDevice2.getDeviceTypeId();
        }
        this.updateOnlineState = true;
        connectInternal();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public synchronized void disconnect(TerminationReason terminationReason) {
        Logger logger = LOG;
        logger.debug("disconnect() terminationReason = " + this.terminationReason);
        this.terminationReason = terminationReason;
        Handler handler = this.backgroundHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        } else {
            logger.debug("disconnect() backgroundHandler is null unexpectedly");
        }
        pause();
        this.currentStatus = AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost;
        notifyOnDisconnectedOnUiThread();
        this.activeDevice = null;
        this.hasEmittedCastingInitiated.set(false);
        Handler handler2 = this.backgroundHandler;
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger = AmazonCastingSession.this.nowPlayingThingShadowMessenger;
                        if (nowPlayingThingShadowMessenger != null) {
                            nowPlayingThingShadowMessenger.unsubscribe();
                        }
                        ClientThingShadowMessenger clientThingShadowMessenger = AmazonCastingSession.this.clientThingShadowMessenger;
                        if (clientThingShadowMessenger != null) {
                            clientThingShadowMessenger.updateState(false);
                        }
                        AmazonCastingSession amazonCastingSession = AmazonCastingSession.this;
                        amazonCastingSession.nowPlayingThingShadowMessenger = null;
                        amazonCastingSession.clientThingShadowMessenger = null;
                    } catch (AmazonClientException e) {
                        AmazonCastingSession.LOG.error("Failed to disconnect gracefully", (Throwable) e);
                    }
                }
            });
        } else {
            logger.debug("disconnect() backgroundHandler is null unexpectedly");
        }
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public synchronized void restore(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback) {
        LOG.debug("restore() device = " + castingDevice);
        this.connectionCallback = castingConnectionCallback;
        this.playbackCallback = castingPlaybackCallback;
        this.currentConnectionType = CastingConnectionType.MANUAL;
        this.activeDevice = castingDevice;
        if (castingDevice != null) {
            this.deviceTypeId = castingDevice.getDeviceTypeId();
        }
        this.updateOnlineState = false;
        connectInternal();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void refresh() {
        LOG.debug("refresh()");
        try {
            if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
                return;
            }
            this.nowPlayingThingShadowMessenger.subscribe(true);
        } catch (AmazonClientException e) {
            LOG.error("refresh failed", (Throwable) e);
        }
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public boolean isConnected() {
        return this.currentStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public boolean isConnecting() {
        AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus aWSIotMqttClientStatus = this.currentStatus;
        return aWSIotMqttClientStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connecting || aWSIotMqttClientStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public CastingDevice getCastingDevice() {
        return this.activeDevice;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void play() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.play();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateQueue(final CreateQueueRequest createQueueRequest, final CastingQueueCallback castingQueueCallback) {
        Handler handler = this.backgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.3
                @Override // java.lang.Runnable
                public void run() {
                    CastingQueueResult castingQueueResultFromString = CastingQueueResult.UNKNOWN_FAILURE;
                    try {
                        try {
                            CreateQueueResponse createQueueResponseCreateQueue = new CreateQueueProvider(AmazonCastingSession.this.service).createQueue(createQueueRequest);
                            castingQueueResultFromString = CastingQueueResult.fromString(createQueueResponseCreateQueue.getStatus());
                            castingQueueResultFromString.setIsCreateQueueResult(true);
                            if (createQueueResponseCreateQueue.getQueue() == null || createQueueResponseCreateQueue.getQueue().getQueueId() == null) {
                                AmazonCastingSession.LOG.error("Missing Queue Id associated to the createQueue call");
                            } else {
                                castingQueueResultFromString.setQueueId(createQueueResponseCreateQueue.getQueue().getQueueId());
                            }
                        } catch (CastingException e) {
                            AmazonCastingSession.LOG.error("Failed to call createQueue", (Throwable) e);
                        }
                    } finally {
                        castingQueueCallback.onQueueUpdated(castingQueueResultFromString);
                    }
                }
            });
        } else {
            LOG.debug("updateQueue() backgroundHandler is null unexpectedly");
        }
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateQueue(final TransferQueueRequest transferQueueRequest, final CastingQueueCallback castingQueueCallback) {
        Handler handler = this.backgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.4
                @Override // java.lang.Runnable
                public void run() {
                    CastingQueueResult castingQueueResultFromString = CastingQueueResult.UNKNOWN_FAILURE;
                    try {
                        try {
                            castingQueueResultFromString = CastingQueueResult.fromString(new TransferQueueProvider(AmazonCastingSession.this.service).transferQueue(transferQueueRequest).getStatus());
                        } catch (CastingException e) {
                            AmazonCastingSession.LOG.error("Failed to call transferQueue", (Throwable) e);
                        }
                    } finally {
                        castingQueueCallback.onQueueUpdated(castingQueueResultFromString);
                    }
                }
            });
        } else {
            LOG.debug("updateQueue() backgroundHandler is null unexpectedly");
        }
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void pause() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.pause();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void previous() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.previous();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void next() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.next();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void seek(long j) {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.seek(j);
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setLoopMode(NowPlayingThingShadow.State.LoopMode loopMode) {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.setLoopMode(loopMode);
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setShuffled(boolean z) {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.setShuffleEnabled(z);
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setVolume(float f) {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.setVolume(f);
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public float getVolume() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return 0.0f;
        }
        return this.nowPlayingThingShadowMessenger.getVolume();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void onVolumeUp() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.onVolumeUp();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void onVolumeDown() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.nowPlayingThingShadowMessenger.onVolumeDown();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public NowPlayingThingShadow.State.ReportedNode getNowPlayingReportedNode() {
        if (this.nowPlayingThingShadowMessenger == null || !isConnected()) {
            return null;
        }
        return this.nowPlayingThingShadowMessenger.getReportedNode();
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateAllowedParentalControls(ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
        if (this.clientThingShadowMessenger == null || !isConnected()) {
            return;
        }
        this.clientThingShadowMessenger.updateAllowedParentalControls(allowedParentalControls);
    }

    protected AWSIotMqttClientStatusCallback getAwsIotMqttCallback() {
        return new AWSIotMqttClientStatusCallback() { // from class: com.amazon.music.casting.session.AmazonCastingSession.5
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
            public synchronized void onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus aWSIotMqttClientStatus, Throwable th) {
                NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger;
                AmazonCastingSession.LOG.info("onStatusChanged: status = " + aWSIotMqttClientStatus + " throwable = " + th);
                AmazonCastingSession.this.currentStatus = aWSIotMqttClientStatus;
                try {
                    int i = AnonymousClass10.$SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttClientStatusCallback$AWSIotMqttClientStatus[AmazonCastingSession.this.currentStatus.ordinal()];
                    boolean z = true;
                    if (i == 1) {
                        AmazonCastingSession amazonCastingSession = AmazonCastingSession.this;
                        if (amazonCastingSession.clientThingShadowMessenger != null) {
                            if (amazonCastingSession.updateOnlineState) {
                                if (AmazonCastingSession.this.backgroundHandler != null) {
                                    AmazonCastingSession.this.backgroundHandler.postDelayed(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.5.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            AmazonCastingSession.this.clientThingShadowMessenger.updateState(true);
                                        }
                                    }, 200L);
                                    AmazonCastingSession.this.clientThingShadowMessenger.subscribe(false);
                                    if (AmazonCastingSession.this.activeDevice.shouldOverridePlayback() && AmazonCastingSession.this.reconnectAttempts <= 0) {
                                        z = false;
                                    }
                                    NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger2 = AmazonCastingSession.this.nowPlayingThingShadowMessenger;
                                    if (nowPlayingThingShadowMessenger2 != null) {
                                        nowPlayingThingShadowMessenger2.subscribe(z);
                                    }
                                    AmazonCastingSession amazonCastingSession2 = AmazonCastingSession.this;
                                    amazonCastingSession2.notifyOnConnectedOnUiThread(amazonCastingSession2.activeDevice);
                                } else {
                                    AmazonCastingSession.LOG.debug("onStatusChanged: backgroundHandler is null unexpectedly");
                                }
                            } else {
                                AmazonCastingSession.this.clientThingShadowMessenger.subscribe(true);
                            }
                        }
                        AmazonCastingSession.this.hasNotifiedOnDisconnected.set(false);
                        AmazonCastingSession.this.reconnectAttempts = 0;
                    } else if (i == 2) {
                        AmazonCastingSession.access$608(AmazonCastingSession.this);
                        if (AmazonCastingSession.this.reconnectAttempts == 1 && (nowPlayingThingShadowMessenger = AmazonCastingSession.this.nowPlayingThingShadowMessenger) != null) {
                            nowPlayingThingShadowMessenger.resetDisconnectTimer();
                        }
                        if (AmazonCastingSession.this.reconnectAttempts >= AmazonCastingSession.this.reconnectAttemptsThreshold) {
                            AmazonCastingSession.LOG.debug("Notifying client of disconnect, SDK will keep trying to reconnect");
                            AmazonCastingSession.this.notifyOnDisconnectedOnUiThread();
                        }
                    } else if (i == 3) {
                        AmazonCastingSession amazonCastingSession3 = AmazonCastingSession.this;
                        if (amazonCastingSession3.handlerThread != null) {
                            amazonCastingSession3.handleCastingTerminated(amazonCastingSession3.reconnectAttempts);
                            AmazonCastingSession.this.reconnectAttempts = 0;
                            AmazonCastingSession.this.notifyOnDisconnectedOnUiThread();
                            AmazonCastingSession.this.hasEmittedCastingInitiated.set(false);
                            AmazonCastingSession.this.handlerThread.quitSafely();
                            AmazonCastingSession.this.handlerThread = null;
                        }
                    }
                } catch (AmazonClientException e) {
                    AmazonCastingSession.LOG.error("Failed to handle onStatusChanged", (Throwable) e);
                }
            }
        };
    }

    /* JADX INFO: renamed from: com.amazon.music.casting.session.AmazonCastingSession$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttClientStatusCallback$AWSIotMqttClientStatus;

        static {
            int[] iArr = new int[AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.values().length];
            $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttClientStatusCallback$AWSIotMqttClientStatus = iArr;
            try {
                iArr[AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttClientStatusCallback$AWSIotMqttClientStatus[AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttClientStatusCallback$AWSIotMqttClientStatus[AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected CastingConnectionCallback getClientThingShadowCallback() {
        return new CastingConnectionCallback() { // from class: com.amazon.music.casting.session.AmazonCastingSession.6
            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onConnected(CastingDevice castingDevice) {
                AmazonCastingSession.LOG.debug("onConnected() device = " + castingDevice);
                try {
                    if (castingDevice != AmazonCastingSession.this.activeDevice) {
                        AmazonCastingSession.LOG.debug("Requested to connect to new device: " + castingDevice);
                        NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger = AmazonCastingSession.this.nowPlayingThingShadowMessenger;
                        if (nowPlayingThingShadowMessenger != null) {
                            nowPlayingThingShadowMessenger.unsubscribe();
                        }
                        AmazonCastingSession amazonCastingSession = AmazonCastingSession.this;
                        amazonCastingSession.connect(castingDevice, amazonCastingSession.connectionCallback, AmazonCastingSession.this.playbackCallback, AmazonCastingSession.this.currentConnectionType);
                        return;
                    }
                    if (AmazonCastingSession.this.updateOnlineState || AmazonCastingSession.this.currentStatus != AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {
                        return;
                    }
                    AmazonCastingSession amazonCastingSession2 = AmazonCastingSession.this;
                    amazonCastingSession2.notifyOnConnectedOnUiThread(amazonCastingSession2.activeDevice);
                    AmazonCastingSession.this.nowPlayingThingShadowMessenger.subscribe(true);
                    AmazonCastingSession.this.updateOnlineState = false;
                } catch (AmazonClientException e) {
                    AmazonCastingSession.LOG.error("Failed to handle client thing shadow callback", (Throwable) e);
                }
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onDisconnected(TerminationReason terminationReason) {
                AmazonCastingSession.LOG.debug("onDisconnected()");
                if (terminationReason != TerminationReason.REMOTELY_DISCONNECTED) {
                    AmazonCastingSession.this.currentStatus = AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost;
                    AmazonCastingSession.this.activeDevice = null;
                    try {
                        AmazonCastingSession.this.iotConnectionManager.getIotMqttManager().setMqttLastWillAndTestament(null);
                        AmazonCastingSession.this.iotConnectionManager.disconnect(MessengerType.CLIENT);
                        return;
                    } catch (AmazonClientException e) {
                        AmazonCastingSession.LOG.error("Failed to gracefully disconnect", (Throwable) e);
                        return;
                    }
                }
                AmazonCastingSession.this.disconnect(terminationReason);
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onError(CastingException castingException) {
                AmazonCastingSession.LOG.debug("onError() ex = " + castingException);
                AmazonCastingSession.this.reportErrorMetrics(castingException);
                AmazonCastingSession.this.connectionCallback.onError(castingException);
            }
        };
    }

    private void connectInternal() {
        createHandlers();
        Handler handler = this.backgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AmazonCastingSession.this.initializeIoT();
                        AmazonCastingSession amazonCastingSession = AmazonCastingSession.this;
                        amazonCastingSession.clientThingShadowMessenger = ThingShadowFactory.createClientThingShadowMessenger(amazonCastingSession.iotConnectionManager.getIotMqttManager(), AmazonCastingSession.connectionInfo.getClientId(), AmazonCastingSession.this.activeDevice, AmazonCastingSession.this.backgroundHandler, AmazonCastingSession.this.castingSessionConfig);
                        AmazonCastingSession amazonCastingSession2 = AmazonCastingSession.this;
                        amazonCastingSession2.clientThingShadowMessenger.setCallback(amazonCastingSession2.getClientThingShadowCallback());
                        AmazonCastingSession amazonCastingSession3 = AmazonCastingSession.this;
                        amazonCastingSession3.nowPlayingThingShadowMessenger = ThingShadowFactory.createNowPlayingThingShadowMessenger(amazonCastingSession3.iotConnectionManager.getIotMqttManager(), AmazonCastingSession.this.activeDevice.getTargetId(), AmazonCastingSession.this.backgroundHandler, AmazonCastingSession.this.playbackCallback, AmazonCastingSession.this.castingSessionConfig, AmazonCastingSession.this.castingSessionTimerListener);
                        AmazonCastingSession amazonCastingSession4 = AmazonCastingSession.this;
                        amazonCastingSession4.iotConnectionManager.registerStatusCallback(MessengerType.CLIENT, amazonCastingSession4.getAwsIotMqttCallback());
                        AmazonCastingSession.this.iotConnectionManager.connect();
                    } catch (CastingException | IllegalArgumentException | NullPointerException e) {
                        if (e instanceof CastingException) {
                            CastingException castingException = (CastingException) e;
                            AmazonCastingSession.this.reportErrorMetrics(castingException);
                            AmazonCastingSession.this.connectionCallback.onError(castingException);
                        }
                        AmazonCastingSession.LOG.error("Received exception while trying to connect", e);
                    }
                }
            });
        } else {
            LOG.debug("connectInternal() backgroundHandler is null unexpectedly");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportErrorMetrics(CastingException castingException) {
        if (this.activeDevice == null) {
            return;
        }
        CastingConnectFailedType castingConnectFailedType = CastingConnectFailedType.EXCEPTION;
        if (castingException.getReason() == CastingException.Reason.NETWORK_ERROR) {
            castingConnectFailedType = CastingConnectFailedType.NETWORK_ERROR;
        } else if (castingException.getReason() == CastingException.Reason.TIMED_OUT) {
            castingConnectFailedType = CastingConnectFailedType.TIMED_OUT;
        }
        MetricsHolder.getManager().handleEvent(new CastingDeviceConnectFailedEvent(this.activeDevice.getCategory().getName(), castingConnectFailedType, this.castingVersion));
    }

    private void createHandlers() {
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        HandlerThread handlerThread2 = new HandlerThread("AmazonCastingSession-BackgroundHandlerThread", 10);
        this.handlerThread = handlerThread2;
        handlerThread2.start();
        this.backgroundHandler = new Handler(this.handlerThread.getLooper());
        this.foregroundHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeIoT() throws CastingException, IllegalArgumentException, NullPointerException {
        if (connectionInfo == null) {
            connectionInfo = getConnectionInfo();
        }
        if (this.iotConnectionManager == null) {
            this.iotConnectionManager = getIotManager();
        }
        this.iotConnectionManager.getIotMqttManager().setMqttLastWillAndTestament(getLastWillAndTestament());
    }

    private AWSIotMqttLastWillAndTestament getLastWillAndTestament() {
        return new AWSIotMqttLastWillAndTestament(ThingShadowFactory.getLastWillTopic(connectionInfo.getClientId()), new Gson().toJson(new ClientThingShadow.Builder().withReportedNode(new ClientThingShadow.State.ReportedNode.Builder().withConnectedTo("").withIsOnline(Boolean.FALSE).withAbnormalDisconnect(Boolean.TRUE).build()).build(), ClientThingShadow.class), AWSIotMqttQos.QOS0);
    }

    private GetConnectionInfoResponse getConnectionInfo() throws CastingException {
        return new ConnectionInfoProvider(this.service).getConnectionInfo();
    }

    private IotConnectionManager getIotManager() {
        return IotConnectionManager.getInstance(this.context, new IotConnectionConfig(new IotConnectionConfig.Builder(this.castingSessionConfig.getMaxTimeoutSeconds(), this.castingSessionConfig.getMaxDeviceOfflineTimeoutSeconds(), this.castingSessionConfig.getMinReconnectIntervalSeconds(), this.castingSessionConfig.getMaxReconnectIntervalSeconds(), this.castingSessionConfig.getReconnectAttemptsThreshold(), connectionInfo.getIotEndpoint(), connectionInfo.getIdentityPoolId(), connectionInfo.getClientId(), connectionInfo.getRegion())), this.serviceConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOnConnectedOnUiThread(final CastingDevice castingDevice) {
        Logger logger = LOG;
        logger.debug("notifyOnConnectedOnUiThread() device = " + castingDevice);
        if (this.foregroundHandler == null) {
            logger.debug("foregroundHandler is null in notifyOnConnectedOnUiThread() unexpectedly");
            return;
        }
        if (!this.hasEmittedCastingInitiated.get() && this.currentStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected && this.activeDevice != null && this.lastConnectAttemptedTimestamp != null) {
            MetricsHolder.getManager().handleEvent(new CastingDeviceConnectedEvent(this.activeDevice.getName(), this.activeDevice.getDeviceTypeId(), getDeviceTypeIdFromCastingDevice(this.activeDevice), this.activeDevice.getCategory().getName(), this.currentConnectionType.getName(), System.currentTimeMillis() - this.lastConnectAttemptedTimestamp.longValue(), this.castingVersion));
            this.lastConnectAttemptedTimestamp = null;
            this.hasEmittedCastingInitiated.set(true);
        }
        this.foregroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.8
            @Override // java.lang.Runnable
            public void run() {
                AmazonCastingSession.this.connectionCallback.onConnected(castingDevice);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOnDisconnectedOnUiThread() {
        Logger logger = LOG;
        logger.debug("notifyOnDisconnectedOnUiThread()");
        if (this.foregroundHandler == null) {
            logger.debug("foregroundHandler is null in notifyOnDisconnectedOnUiThread() unexpectedly");
        } else if (this.hasNotifiedOnDisconnected.compareAndSet(false, true)) {
            if (this.currentStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost && this.activeDevice != null) {
                MetricsHolder.getManager().handleEvent(new CastingDeviceDisconnectedEvent(this.activeDevice.getName(), this.activeDevice.getDeviceTypeId(), getDeviceTypeIdFromCastingDevice(this.activeDevice), this.activeDevice.getCategory().getName(), this.terminationReason, this.castingVersion));
            }
            this.foregroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.AmazonCastingSession.9
                @Override // java.lang.Runnable
                public void run() {
                    AmazonCastingSession.this.connectionCallback.onDisconnected(AmazonCastingSession.this.terminationReason);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCastingTerminated(int i) {
        if (this.terminationReason == null) {
            if (i > 0) {
                this.terminationReason = TerminationReason.NETWORK_ERROR;
            } else {
                this.terminationReason = TerminationReason.PLAYBACK_INACTIVITY;
            }
        }
    }

    private String getDeviceTypeIdFromCastingDevice(CastingDevice castingDevice) {
        if (castingDevice == null) {
            return "";
        }
        String[] strArrSplit = castingDevice.getTargetId().split("-");
        return strArrSplit.length != 3 ? "" : strArrSplit[2];
    }
}
