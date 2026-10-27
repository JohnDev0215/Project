package com.amazon.music.chromecast;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.EnvironmentCompat;
import androidx.mediarouter.app.MediaRouteButton;
import com.amazon.music.chromecast.ChromecastNerdStats;
import com.amazon.music.chromecast.CustomData;
import com.amazon.music.chromecast.alert.ReceiverAlert;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.CastStateListener;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastController {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastController.class);
    private static final ReceiverAlert<?> NULL_RECEIVER_ALERT = null;
    private static ChromecastController sInstance = null;
    private CastContext mCastContext;
    private CastSession mCastSession;
    private ConnectionListener mConnectionListener;
    private MediaMetadata mCurrentMediaMetadata;
    private long mDuration;
    private Handler mHandler;
    private IntroductoryOverlay mIntroductoryOverlay;
    private MediaRouteButton mIntroductoryOverlayButton;
    private OnPlaybackAttributesChangeListener mOnPlaybackAttributesChangeListener;
    private long mPosition;
    private RemoteMediaClient.ProgressListener mProgressListener;
    private RemoteMediaClient.Callback mRemoteMediaClientListener;
    private SessionManagerListener<CastSession> mSessionManagerListener;
    private boolean mInitialized = false;
    private boolean mPlaybackStarted = true;
    private boolean mPlaybackCompleted = true;
    private CastStateListener mCastStateListener = null;
    private AtomicBoolean isConnected = new AtomicBoolean(false);
    private boolean mShouldShowOverlay = false;
    private boolean mIsNerdStatsChannelActive = false;
    private boolean mNormalizeLoudness = false;
    private boolean mEnableSpatialToggle = false;
    private ReceiverAlert<?> mReceiverAlert = NULL_RECEIVER_ALERT;
    private String mChromecastModelName = null;
    private String mCastSenderUuid = null;
    private LocaleAccess mLocaleAccess = null;
    private final ChromecastNerdStats mChromecastNerdStats = new ChromecastNerdStats(this);
    private final ChromecastUpdate mChromecastUpdate = new ChromecastUpdate();

    public interface OnPlaybackAttributesChangeListener {
        void onPlaybackAttributesChange(ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes, ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes2);
    }

    @VisibleForTesting
    enum RemotePlaybackType {
        THIS_DEVICE_THIS_TRACK,
        THIS_DEVICE_OTHER_TRACK,
        OTHER_DEVICE,
        NONE
    }

    private ChromecastController() {
    }

    public static synchronized ChromecastController getInstance() {
        if (sInstance == null) {
            sInstance = new ChromecastController();
        }
        return sInstance;
    }

    public Observable<Void> initializeCastContext(final Context context) {
        return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.chromecast.ChromecastController.1
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Void> subscriber) {
                CastContext.getSharedInstance(context, Executors.newSingleThreadExecutor()).addOnCompleteListener(new OnCompleteListener<CastContext>() { // from class: com.amazon.music.chromecast.ChromecastController.1.1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(@NonNull Task<CastContext> task) {
                        if (!task.isSuccessful()) {
                            ChromecastController.LOG.error("Encountered error while initializing CastContext: ", (Throwable) task.getException());
                        }
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                });
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    public void initialize(Context context, ConnectionListener connectionListener) {
        if (this.mInitialized) {
            LOG.debug("ChromecastController instance is already initialized.");
            return;
        }
        LOG.debug("Initializing ChromecastController instance.");
        try {
            this.mConnectionListener = connectionListener;
            setupListeners();
            CastContext sharedInstance = CastContext.getSharedInstance();
            this.mCastContext = sharedInstance;
            sharedInstance.getSessionManager().addSessionManagerListener(this.mSessionManagerListener, CastSession.class);
            this.mCastContext.addCastStateListener(this.mCastStateListener);
            this.mCastSession = this.mCastContext.getSessionManager().getCurrentCastSession();
            this.mHandler = new Handler(context.getMainLooper());
            this.mLocaleAccess = new LocaleAccess(context);
            this.mInitialized = true;
        } catch (RuntimeException e) {
            LOG.error("Encountered error while initializing Chromecast SDK: ", (Throwable) e);
            this.mInitialized = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runOnUiThread(Runnable runnable) {
        if (!this.mInitialized) {
            LOG.error("Skipping runOnUiThread() because ChromecastController instance is not initialized.");
        } else {
            this.mHandler.post(runnable);
        }
    }

    private void setupListeners() {
        LOG.trace("Setting up Chromecast listeners.");
        this.mSessionManagerListener = new SessionManagerListener<CastSession>() { // from class: com.amazon.music.chromecast.ChromecastController.2
            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionEnded(CastSession castSession, int i) {
                ChromecastController.LOG.debug("Session ended. " + CastStatusCodes.getStatusCodeString(i));
                onApplicationDisconnected();
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResumed(CastSession castSession, boolean z) {
                ChromecastController.LOG.debug("Session resumed. " + z);
                onApplicationConnected(castSession);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResumeFailed(CastSession castSession, int i) {
                ChromecastController.LOG.debug("Session resume failed. " + CastStatusCodes.getStatusCodeString(i));
                onApplicationDisconnected();
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStarted(CastSession castSession, String str) {
                ChromecastController.LOG.debug("Session started. " + str);
                onApplicationConnected(castSession);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStartFailed(CastSession castSession, int i) {
                ChromecastController.LOG.debug("Session start failed. " + CastStatusCodes.getStatusCodeString(i));
                onApplicationDisconnected();
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStarting(CastSession castSession) {
                ChromecastController.LOG.debug("Session is starting.");
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionEnding(CastSession castSession) {
                ChromecastController.LOG.debug("Session is ending.");
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResuming(CastSession castSession, String str) {
                ChromecastController.LOG.debug("Session is resuming. " + str);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionSuspended(CastSession castSession, int i) {
                ChromecastController.LOG.debug("Session is suspended. " + i);
            }

            private void onApplicationConnected(CastSession castSession) {
                ChromecastController.LOG.debug("Application connected.");
                ChromecastController.this.mCastSession = castSession;
                ChromecastController.this.isConnected.set(true);
                ChromecastController.this.mConnectionListener.onConnected();
                ChromecastController.this.mChromecastNerdStats.initializeChannel(castSession);
                ChromecastController.this.mChromecastUpdate.initializeChannel(castSession);
                CastDevice castDevice = castSession.getCastDevice();
                if (castDevice != null) {
                    ChromecastController.this.mChromecastModelName = castDevice.getModelName();
                }
            }

            private void onApplicationDisconnected() {
                ChromecastController.LOG.debug("Application disconnected.");
                if (ChromecastController.this.isConnected.compareAndSet(true, false)) {
                    ChromecastController.this.mConnectionListener.onDisconnected();
                }
                ChromecastController.this.mChromecastUpdate.closeChannel();
                ChromecastController.this.mChromecastModelName = null;
            }
        };
        this.mCastStateListener = new CastStateListener() { // from class: com.amazon.music.chromecast.ChromecastController.3
            @Override // com.google.android.gms.cast.framework.CastStateListener
            public void onCastStateChanged(int i) {
                if (i != 1) {
                    ChromecastController.LOG.debug("Devices available.");
                    ChromecastController.this.mShouldShowOverlay = true;
                    ChromecastController.this.showIntroductoryOverlay();
                }
                if (i == 2) {
                    ChromecastController.this.mConnectionListener.onDevicesAvailable();
                }
                if (i == 4 || i == 2) {
                    ChromecastController chromecastController = ChromecastController.this;
                    ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes = ChromecastNerdStats.ChromecastPlaybackAttributes.UNKNOWN;
                    chromecastController.updatePlaybackAttributes(chromecastPlaybackAttributes, chromecastPlaybackAttributes, false, ChromecastController.NULL_RECEIVER_ALERT);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showIntroductoryOverlay() {
        MediaRouteButton mediaRouteButton;
        if (this.mIntroductoryOverlay == null || (mediaRouteButton = this.mIntroductoryOverlayButton) == null || mediaRouteButton.getVisibility() != 0) {
            return;
        }
        this.mIntroductoryOverlayButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.chromecast.ChromecastController.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ChromecastController.this.runOnUiThread(new Runnable() { // from class: com.amazon.music.chromecast.ChromecastController.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ChromecastController.LOG.debug("Showing introductory overlay.");
                        ChromecastController.this.mShouldShowOverlay = false;
                        ChromecastController.this.mIntroductoryOverlay.show();
                    }
                });
                ChromecastController.this.mIntroductoryOverlayButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public boolean isConnected() {
        return this.isConnected.get();
    }

    public boolean isInitialized() {
        LOG.trace("Checking whether this instance is initialized.");
        return this.mInitialized;
    }

    public void loadTrack(final TrackInformation trackInformation, final CustomData customData, final boolean z, final long j, final PlaybackListener playbackListener) {
        this.mPosition = 0L;
        this.mDuration = 0L;
        this.mCastSenderUuid = customData.getDeviceId();
        LOG.trace("Creating runnable for loadTrack.");
        runOnUiThread(new Runnable() { // from class: com.amazon.music.chromecast.ChromecastController.6
            @Override // java.lang.Runnable
            public void run() {
                ChromecastController.LOG.debug("Trying to load media onto Chromecast. Retrieving cast session.");
                if (ChromecastController.this.mCastSession == null) {
                    ChromecastController.LOG.debug("A cast session does not exist.");
                    return;
                }
                ChromecastController.LOG.trace("A cast session exists. Retrieving RemoteMediaClient.");
                RemoteMediaClient remoteMediaClient = ChromecastController.this.mCastSession.getRemoteMediaClient();
                if (remoteMediaClient == null) {
                    ChromecastController.LOG.error("RemoteMediaClient does not exist. Failed to load track.");
                    playbackListener.onLoadError(913);
                    return;
                }
                try {
                    MediaInfo mediaInfoBuildMediaInfo = ChromecastController.this.buildMediaInfo(trackInformation, customData);
                    ChromecastController.LOG.trace("RemoteMediaClient exists. Adding playback listeners.");
                    ChromecastController.this.addPlaybackListeners(playbackListener);
                    ChromecastController.LOG.trace("Retrieving streamUrl for track.");
                    ChromecastController.this.mPlaybackStarted = false;
                    ChromecastController.this.mPlaybackCompleted = false;
                    remoteMediaClient.load(mediaInfoBuildMediaInfo, z, j).setResultCallback(new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: com.amazon.music.chromecast.ChromecastController.6.1
                        @Override // com.google.android.gms.common.api.ResultCallback
                        public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                            ChromecastController.LOG.debug("Received load result.");
                            Status status = mediaChannelResult.getStatus();
                            if (status.isSuccess()) {
                                ChromecastController.LOG.debug("Successfully loaded track.");
                                AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                                ChromecastController.this.mPosition = j;
                                playbackListener.onLoadSuccess();
                                return;
                            }
                            ChromecastController.LOG.error("Failed to load track.");
                            playbackListener.onLoadError(status.getStatusCode());
                        }
                    });
                } catch (JSONException unused) {
                    ChromecastController.LOG.error("Failed to create MediaInfo, can't start playback on Chromecast");
                    playbackListener.onLoadError(914);
                }
            }
        });
    }

    public void seek(final long j, final SimpleListener simpleListener) {
        LOG.trace("Creating runnable for seek.");
        runOnUiThread(new Runnable() { // from class: com.amazon.music.chromecast.ChromecastController.7
            @Override // java.lang.Runnable
            public void run() {
                ChromecastController.LOG.debug("Seeking through track.");
                if (ChromecastController.this.mCastSession == null) {
                    ChromecastController.LOG.warn("A cast session does not exist.");
                    return;
                }
                RemoteMediaClient remoteMediaClient = ChromecastController.this.mCastSession.getRemoteMediaClient();
                if (remoteMediaClient == null) {
                    ChromecastController.LOG.error("RemoteMediaClient does not exist. Failed to seek track.");
                    simpleListener.onError(913);
                } else {
                    remoteMediaClient.seek(j).setResultCallback(new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: com.amazon.music.chromecast.ChromecastController.7.1
                        @Override // com.google.android.gms.common.api.ResultCallback
                        public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                            Status status = mediaChannelResult.getStatus();
                            if (status.isSuccess()) {
                                ChromecastController.LOG.debug("Successfully seeked track.");
                                simpleListener.onSuccess();
                            } else {
                                ChromecastController.LOG.error("Failed to seek track.");
                                simpleListener.onError(status.getStatusCode());
                            }
                        }
                    });
                }
            }
        });
    }

    public void play(final SimpleListener simpleListener) {
        LOG.trace("Creating runnable for play.");
        runOnUiThread(new Runnable() { // from class: com.amazon.music.chromecast.ChromecastController.8
            @Override // java.lang.Runnable
            public void run() {
                ChromecastController.LOG.debug("Playing track.");
                if (ChromecastController.this.mCastSession == null) {
                    ChromecastController.LOG.warn("A cast session does not exist.");
                    return;
                }
                RemoteMediaClient remoteMediaClient = ChromecastController.this.mCastSession.getRemoteMediaClient();
                if (remoteMediaClient == null) {
                    ChromecastController.LOG.error("RemoteMediaClient does not exist. Failed to play track.");
                    simpleListener.onError(913);
                } else {
                    remoteMediaClient.play().setResultCallback(new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: com.amazon.music.chromecast.ChromecastController.8.1
                        @Override // com.google.android.gms.common.api.ResultCallback
                        public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                            Status status = mediaChannelResult.getStatus();
                            if (status.isSuccess()) {
                                ChromecastController.LOG.debug("Successfully played track.");
                                simpleListener.onSuccess();
                            } else {
                                ChromecastController.LOG.error("Failed to play track.");
                                simpleListener.onError(status.getStatusCode());
                            }
                        }
                    });
                }
            }
        });
    }

    public void pause(final SimpleListener simpleListener) {
        LOG.trace("Creating runnable for pause.");
        runOnUiThread(new Runnable() { // from class: com.amazon.music.chromecast.ChromecastController.9
            @Override // java.lang.Runnable
            public void run() {
                ChromecastController.LOG.debug("Pausing track.");
                if (ChromecastController.this.mCastSession == null) {
                    ChromecastController.LOG.warn("A cast session does not exist.");
                    return;
                }
                RemoteMediaClient remoteMediaClient = ChromecastController.this.mCastSession.getRemoteMediaClient();
                if (remoteMediaClient == null) {
                    ChromecastController.LOG.error("RemoteMediaClient does not exist. Failed to pause track.");
                    simpleListener.onError(913);
                } else {
                    remoteMediaClient.pause().setResultCallback(new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: com.amazon.music.chromecast.ChromecastController.9.1
                        @Override // com.google.android.gms.common.api.ResultCallback
                        public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                            Status status = mediaChannelResult.getStatus();
                            if (status.isSuccess()) {
                                ChromecastController.LOG.debug("Successfully paused track.");
                                simpleListener.onSuccess();
                            } else {
                                ChromecastController.LOG.error("Failed to pause track.");
                                simpleListener.onError(status.getStatusCode());
                            }
                        }
                    });
                }
            }
        });
    }

    public void stop(final SimpleListener simpleListener) {
        LOG.trace("Creating runnable for stop.");
        runOnUiThread(new Runnable() { // from class: com.amazon.music.chromecast.ChromecastController.10
            @Override // java.lang.Runnable
            public void run() {
                ChromecastController.LOG.debug("Stopping track.");
                if (ChromecastController.this.mCastSession != null) {
                    RemoteMediaClient remoteMediaClient = ChromecastController.this.mCastSession.getRemoteMediaClient();
                    if (remoteMediaClient == null) {
                        ChromecastController.LOG.debug("RemoteMediaClient does not exist.");
                        simpleListener.onSuccess();
                        return;
                    } else {
                        remoteMediaClient.stop().setResultCallback(new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: com.amazon.music.chromecast.ChromecastController.10.1
                            @Override // com.google.android.gms.common.api.ResultCallback
                            public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                                Status status = mediaChannelResult.getStatus();
                                if (status.isSuccess()) {
                                    ChromecastController.LOG.debug("Successfully stopped track.");
                                    simpleListener.onSuccess();
                                } else {
                                    ChromecastController.LOG.error("Failed to stop track.");
                                    simpleListener.onError(status.getStatusCode());
                                }
                            }
                        });
                        return;
                    }
                }
                simpleListener.onSuccess();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPlaybackListeners(final PlaybackListener playbackListener) {
        if (this.mCastSession == null) {
            LOG.warn("A cast session does not exist.");
            return;
        }
        if (this.mProgressListener != null) {
            LOG.debug("Removing existing progress listener.");
            this.mCastSession.getRemoteMediaClient().removeProgressListener(this.mProgressListener);
        }
        if (this.mRemoteMediaClientListener != null) {
            LOG.debug("Removing existing playback listener.");
            this.mCastSession.getRemoteMediaClient().unregisterCallback(this.mRemoteMediaClientListener);
        }
        this.mProgressListener = new RemoteMediaClient.ProgressListener() { // from class: com.amazon.music.chromecast.ChromecastController.11
            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
            public void onProgressUpdated(long j, long j2) {
                ChromecastController.this.mDuration = j2;
                ChromecastController.this.mPosition = j;
            }
        };
        this.mRemoteMediaClientListener = new RemoteMediaClient.Callback() { // from class: com.amazon.music.chromecast.ChromecastController.12
            private String playerStateToString(int i) {
                return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? EnvironmentCompat.MEDIA_UNKNOWN : "loading" : "buffering" : "paused" : "playing" : "idle";
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onAdBreakStatusUpdated() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onMetadataUpdated() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onPreloadStatusUpdated() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onQueueStatusUpdated() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onSendingRemoteMediaRequest() {
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onStatusUpdated() {
                if (ChromecastController.this.mCastSession == null) {
                    ChromecastController.LOG.warn("A cast session does not exist.");
                    return;
                }
                MediaStatus mediaStatus = ChromecastController.this.mCastSession.getRemoteMediaClient().getMediaStatus();
                if (mediaStatus == null) {
                    return;
                }
                int playerState = mediaStatus.getPlayerState();
                ChromecastController.LOG.debug("onStatusUpdated: " + playerStateToString(playerState));
                if (playerState == 1 && mediaStatus.getIdleReason() == 1 && !ChromecastController.this.mPlaybackCompleted && ChromecastController.this.mPlaybackStarted) {
                    ChromecastController.LOG.debug("Playback of track is complete.");
                    ChromecastController.this.mPlaybackCompleted = true;
                    ChromecastController chromecastController = ChromecastController.this;
                    ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes = ChromecastNerdStats.ChromecastPlaybackAttributes.UNKNOWN;
                    chromecastController.updatePlaybackAttributes(chromecastPlaybackAttributes, chromecastPlaybackAttributes, false, ChromecastController.NULL_RECEIVER_ALERT);
                    playbackListener.onPlaybackComplete();
                }
                if (playerState == 2 && !ChromecastController.this.mPlaybackStarted) {
                    ChromecastController.LOG.debug("Successfully playing track.");
                    ChromecastController.this.mPlaybackStarted = true;
                }
                if (playerState != 2) {
                    if (playerState == 3) {
                        ChromecastController.LOG.debug("Track is paused on Chromecast.");
                        playbackListener.onRemotePause();
                        return;
                    }
                    return;
                }
                ChromecastController.LOG.debug("Track is playing on Chromecast.");
                int i = AnonymousClass13.$SwitchMap$com$amazon$music$chromecast$ChromecastController$RemotePlaybackType[ChromecastController.getRemotePlaybackType(ChromecastController.this.mCastSession, ChromecastController.this.mCastSenderUuid, ChromecastController.this.mCurrentMediaMetadata).ordinal()];
                if (i == 1) {
                    playbackListener.onRemotePlay();
                } else if (i == 3) {
                    ChromecastController.this.mCastContext.getSessionManager().endCurrentSession(false);
                } else {
                    if (i != 4) {
                        return;
                    }
                    ChromecastController.this.mCastContext.getSessionManager().endCurrentSession(true);
                }
            }
        };
        Logger logger = LOG;
        logger.debug("Adding progress listener.");
        RemoteMediaClient remoteMediaClient = this.mCastSession.getRemoteMediaClient();
        remoteMediaClient.addProgressListener(this.mProgressListener, 10L);
        logger.debug("Adding playback listener.");
        remoteMediaClient.registerCallback(this.mRemoteMediaClientListener);
    }

    /* JADX INFO: renamed from: com.amazon.music.chromecast.ChromecastController$13, reason: invalid class name */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$chromecast$ChromecastController$RemotePlaybackType;

        static {
            int[] iArr = new int[RemotePlaybackType.values().length];
            $SwitchMap$com$amazon$music$chromecast$ChromecastController$RemotePlaybackType = iArr;
            try {
                iArr[RemotePlaybackType.THIS_DEVICE_THIS_TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$chromecast$ChromecastController$RemotePlaybackType[RemotePlaybackType.THIS_DEVICE_OTHER_TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$chromecast$ChromecastController$RemotePlaybackType[RemotePlaybackType.OTHER_DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$chromecast$ChromecastController$RemotePlaybackType[RemotePlaybackType.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @VisibleForTesting
    static RemotePlaybackType getRemotePlaybackType(CastSession castSession, String str, MediaMetadata mediaMetadata) {
        if (castSession == null) {
            LOG.warn("A cast session does not exist.");
            return RemotePlaybackType.NONE;
        }
        MediaInfo mediaInfo = castSession.getRemoteMediaClient().getMediaInfo();
        if (mediaInfo == null) {
            LOG.trace("getRemotePlaybackType() returning NONE because mediaInfo is null");
            return RemotePlaybackType.NONE;
        }
        MediaMetadata metadata = mediaInfo.getMetadata();
        if (metadata == null) {
            LOG.trace("getRemotePlaybackType() returning NONE because mediaMetadata is null");
            return RemotePlaybackType.NONE;
        }
        if (str != null && str.equals(metadata.getString("amazon_music_chromecast_sender_uuid"))) {
            if (metadata.equals(mediaMetadata)) {
                LOG.trace("getRemotePlaybackType() returning THIS_DEVICE_THIS_TRACK because remote mediaMetadata sender UUID and track are equal to local");
                return RemotePlaybackType.THIS_DEVICE_THIS_TRACK;
            }
            LOG.trace("getRemotePlaybackType() returning THIS_DEVICE_OTHER_TRACK because remote mediaMetadata sender UUID is equal to local, but track is not");
            return RemotePlaybackType.THIS_DEVICE_OTHER_TRACK;
        }
        LOG.trace("getRemotePlaybackType() returning OTHER_DEVICE because remote mediaMetadata sender UUID is not equal to local");
        return RemotePlaybackType.OTHER_DEVICE;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getPosition() {
        return this.mPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaInfo buildMediaInfo(TrackInformation trackInformation, CustomData customData) throws JSONException {
        Logger logger = LOG;
        logger.debug("Building MediaInfo.");
        String title = trackInformation.getTitle();
        String artistName = trackInformation.getArtistName();
        String albumName = trackInformation.getAlbumName();
        long duration = trackInformation.getDuration() * 1000;
        String streamUrl = trackInformation.getStreamUrl();
        String mimeType = trackInformation.getStreamType().getMimeType();
        String albumArtUrl = trackInformation.getAlbumArtUrl();
        if (albumArtUrl == null) {
            albumArtUrl = "https://s3.amazonaws.com/chromecast-receiver-application-do-not-delete/album_art_placeholder.png";
        }
        this.mCurrentMediaMetadata = new MediaMetadata(3);
        logger.debug("Track information: trackTitle: " + title);
        logger.debug("Track information: trackArtist: " + artistName);
        logger.debug("Track information: trackAlbumName: " + albumName);
        logger.debug("Track information: trackDuration: " + duration);
        logger.debug("Track information: trackStreamUrl: " + streamUrl);
        logger.debug("Track information: albumArtUrl: " + albumArtUrl);
        logger.debug("Track information: streamMimeType: " + mimeType);
        this.mCurrentMediaMetadata.putString("amazon_music_chromecast_sender_uuid", this.mCastSenderUuid);
        this.mCurrentMediaMetadata.putString(MediaMetadata.KEY_TITLE, title);
        this.mCurrentMediaMetadata.putString(MediaMetadata.KEY_ARTIST, artistName);
        this.mCurrentMediaMetadata.putString(MediaMetadata.KEY_ALBUM_TITLE, albumName);
        this.mCurrentMediaMetadata.addImage(new WebImage(Uri.parse(albumArtUrl)));
        customData.setLocale(this.mLocaleAccess.currentLocaleString());
        return new MediaInfo.Builder(streamUrl).setStreamType(1).setContentType(mimeType).setMetadata(this.mCurrentMediaMetadata).setStreamDuration(duration).setCustomData(customData.toJSONObject()).build();
    }

    public String getReceiverApplicationId() {
        if (this.mInitialized) {
            return this.mCastContext.getCastOptions().getReceiverApplicationId();
        }
        return null;
    }

    public List<StreamType> getSupportedStreamTypes() {
        return Arrays.asList(StreamType.DASH, StreamType.HLS, StreamType.PROGRESSIVE);
    }

    public String getChromecastDeviceId() {
        CastSession castSession = this.mCastSession;
        if (castSession == null) {
            LOG.warn("A cast session does not yet exist. Cannot return chromecastDeviceId");
            return null;
        }
        if (castSession.getCastDevice() == null) {
            LOG.warn("A cast device is not connected to the existing cast session. Cannot return chromecastDeviceId");
            return null;
        }
        return this.mCastSession.getCastDevice().getDeviceId();
    }

    public void setOnPlaybackAttributesChangeListener(OnPlaybackAttributesChangeListener onPlaybackAttributesChangeListener) {
        this.mOnPlaybackAttributesChangeListener = onPlaybackAttributesChangeListener;
    }

    void updatePlaybackAttributes(ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes, ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes2, boolean z, ReceiverAlert<?> receiverAlert) {
        this.mEnableSpatialToggle = z;
        this.mReceiverAlert = receiverAlert;
        OnPlaybackAttributesChangeListener onPlaybackAttributesChangeListener = this.mOnPlaybackAttributesChangeListener;
        if (onPlaybackAttributesChangeListener != null) {
            onPlaybackAttributesChangeListener.onPlaybackAttributesChange(chromecastPlaybackAttributes, chromecastPlaybackAttributes2);
        }
    }

    void setIsChromecastNerdStatsChannelActive(boolean z) {
        this.mIsNerdStatsChannelActive = z;
    }

    public boolean isChromecastNerdStatsChannelActive() {
        return this.mIsNerdStatsChannelActive;
    }

    public boolean enableSpatialToggle() {
        return this.mChromecastUpdate.isInitialized() && this.mEnableSpatialToggle;
    }

    public void handlePlaybackUpdate(CustomData.Update update) {
        this.mChromecastUpdate.sendUpdate(update);
        Boolean boolNormalizeLoudness = update.normalizeLoudness();
        setNormalizeLoudness(boolNormalizeLoudness != null ? boolNormalizeLoudness.booleanValue() : false);
    }

    public void setNormalizeLoudness(boolean z) {
        this.mNormalizeLoudness = z;
    }

    boolean normalizeLoudness() {
        return this.mNormalizeLoudness;
    }

    @Nullable
    String chromecastModelName() {
        return this.mChromecastModelName;
    }

    @Nullable
    public ReceiverAlert<?> receiverAlert() {
        return this.mReceiverAlert;
    }
}
