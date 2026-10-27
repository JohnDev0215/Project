package com.amazon.mp3.playback.casting;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingSkillService;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.casting.CastingViewsFactory;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.MediaAccessPolicyHelper;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ThreadUtil;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingFactory;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.chromecast.ConnectionListener;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.player.CompositePlayer;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.ChromecastPlayer;
import com.amazon.music.media.playback.sequencer.NoOpSequencer;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.google.android.gms.common.GoogleApiAvailability;
import rx.Observable;

/* JADX INFO: loaded from: classes3.dex */
public class CastingConnectionHandler {
    private static final String TAG = "CastingConnectionHandler";
    private static CastingConnectionHandler sCastingConnectionHandler;
    private final Context mContext;
    private String mDeviceId;
    private final Playback playback = Playback.getInstance();
    private final PlaybackController playbackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private final CastingSessionManager mCastingSessionManager = CastingSessionManager.getInstance();
    private int mLastCastingSessionId = -1;
    private String mricToken = ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext());
    private final CastingConnectionCallback mCastingConnectionCallback = new CastingConnectionCallback() { // from class: com.amazon.mp3.playback.casting.CastingConnectionHandler.1
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(final CastingDevice castingDevice) {
            int castingSessionId = CastingConnectionHandler.this.mCastingSessionManager.getCastingSessionId();
            boolean z = CastingConnectionHandler.this.mLastCastingSessionId != castingSessionId;
            if (z) {
                CastingConnectionHandler.this.mLastCastingSessionId = castingSessionId;
            }
            if (CastingCategory.CHROMECAST.equals(castingDevice.getCategory())) {
                CastingConnectionHandler.this.setPlayerToChromecast();
            } else {
                CastingConnectionHandler.this.setPlayerToAlexa((castingDevice.shouldOverridePlayback() || !castingDevice.isPlaying()) && z);
                NowPlayingUtil.showNowPlaying(CastingConnectionHandler.this.mContext);
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason reason) {
            PlayerPlaybackProvider defaultPlaybackProvider = CastingConnectionHandler.this.playback.getPlaybackConfig().getDefaultPlaybackProvider(CastingConnectionHandler.this.mricToken);
            defaultPlaybackProvider.setPlayer(new CompositePlayer(), ControlSource.APP_UI);
            PlaybackProvider playbackProvider = CastingConnectionHandler.this.playback.getPlaybackProvider();
            Log.info(CastingConnectionHandler.TAG, "onDisconnected, calling on playback.setPlaybackProvider -> shutdown(false)");
            if (!(playbackProvider instanceof CastingPlaybackProvider)) {
                CastingConnectionHandler.this.playback.setPlaybackProvider(defaultPlaybackProvider);
            } else {
                CastingPlaybackProvider castingPlaybackProvider = (CastingPlaybackProvider) playbackProvider;
                castingPlaybackProvider.onDisconnected();
                CastingConnectionHandler.this.playback.setPlaybackProvider(defaultPlaybackProvider);
                CastingConnectionHandler.this.recoverAlexaSequencer(castingPlaybackProvider, (SequencerPlaybackProvider) defaultPlaybackProvider);
            }
            CastingConnectionHandler.this.showCastingConnectionToast(false);
            if (reason == TerminationReason.USER_DISCONNECT) {
                CastingStateManager castingStateManager = CastingStateManager.getInstance();
                Intent intent = new Intent(CastingConnectionHandler.this.mContext, (Class<?>) CastingSkillService.class);
                intent.putExtra("skillType", "transferPlayback");
                intent.putExtra("queueId", castingStateManager.getQueueId());
                ServiceUtil.startService(CastingConnectionHandler.this.mContext, intent);
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(final CastingException e) {
            CastingConnectionHandler.this.setPlayerToLocal();
        }
    };
    private final ConnectionListener mConnectionListener = new ConnectionListener() { // from class: com.amazon.mp3.playback.casting.CastingConnectionHandler.2
        @Override // com.amazon.music.chromecast.ConnectionListener
        public void onConnected() {
            String chromecastDeviceId;
            Log.debug(CastingConnectionHandler.TAG, "Chromecast connected");
            MediaAccessPolicyHelper.updateMediaAccessPolicy(CastingConnectionHandler.this.mContext);
            CastingConnectionHandler.this.showCastingConnectionToast(true);
            String receiverApplicationId = "";
            if (ChromecastController.getInstance() == null) {
                Log.info(CastingConnectionHandler.TAG, "chromecast controller instance is null. ReceiverAppId and ChromecastAppId cannot be retrieved, so they will appear as empty strings in flexEvent chromecastConnected");
                chromecastDeviceId = "";
            } else {
                receiverApplicationId = ChromecastController.getInstance().getReceiverApplicationId();
                chromecastDeviceId = ChromecastController.getInstance().getChromecastDeviceId();
            }
            MetricsLogger.sendEvent(FlexEvent.builder("chromecastConnected").withFlexStr1(CastingConnectionHandler.this.mDeviceId).withFlexStr2(receiverApplicationId).withFlexStr3(chromecastDeviceId).build());
        }

        @Override // com.amazon.music.chromecast.ConnectionListener
        public void onDisconnected() {
            Log.debug(CastingConnectionHandler.TAG, "Chromecast disconnected");
            MediaAccessPolicyHelper.updateMediaAccessPolicy(CastingConnectionHandler.this.mContext);
            if (CastingConnectionHandler.this.playbackController != null) {
                CastingConnectionHandler.this.playbackController.pause();
            }
            MetricsLogger.sendEvent(FlexEvent.builder("chromecastDisconnected").withFlexStr1(CastingConnectionHandler.this.mDeviceId).build());
        }

        @Override // com.amazon.music.chromecast.ConnectionListener
        public void onDevicesAvailable() {
            Log.debug(CastingConnectionHandler.TAG, "Devices available.");
            MetricsLogger.sendEvent(FlexEvent.builder("chromecastDevicesAvailable").withFlexStr1(CastingConnectionHandler.this.mDeviceId).build());
        }
    };

    public static void init(final Context context) {
        if (sCastingConnectionHandler == null) {
            sCastingConnectionHandler = new CastingConnectionHandler(context.getApplicationContext());
        }
    }

    public static Observable<Void> initChromecastCastContext(final Context context) {
        ThreadUtil.dumpStacktraceOnMainThread();
        return ChromecastController.getInstance().initializeCastContext(context);
    }

    private CastingConnectionHandler(final Context context) {
        this.mContext = context;
        initializeChromecast();
        initializeCastingSessionManager();
    }

    private void initializeCastingSessionManager() {
        this.mCastingSessionManager.registerListener(this.mCastingConnectionCallback);
        if (!this.mCastingSessionManager.isInitialized()) {
            this.mCastingSessionManager.initialize(CastingFactory.createCasting(CastingViewsFactory.createCastingConfiguration(this.mContext).build()), 1L);
            this.mCastingSessionManager.registerListener(CastingStateManager.getInstance());
        }
        if (CastingUtil.isAlexaCastingEnabled() || (this.mCastingSessionManager.getLastDevice() != null && CastingCategory.CHROMECAST.equals(this.mCastingSessionManager.getLastDevice().getCategory()))) {
            if (CastingUtil.isCasting()) {
                this.mCastingSessionManager.refresh();
            } else {
                this.mCastingSessionManager.restore();
            }
            this.mLastCastingSessionId = this.mCastingSessionManager.getCastingSessionId();
        }
    }

    private void initializeChromecast() {
        String str = TAG;
        Log.debug(str, "Initializing chromecast logic");
        boolean z = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.mContext) == 0;
        this.mDeviceId = AccountCredentialStorage.get(this.mContext).getDeviceId();
        if (!z) {
            Log.warning(str, "Older version of GooglePlayServices. Will not initialize CastSDK.");
        } else {
            ChromecastController.getInstance().initialize(this.mContext, this.mConnectionListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerToAlexa(final boolean override) {
        Log.debug(TAG, "setPlayerToAlexa(" + override + ")");
        PlaybackProvider playbackProvider = this.playback.getPlaybackProvider();
        if (playbackProvider instanceof CastingPlaybackProvider) {
            return;
        }
        Sequencer sequencer = playbackProvider instanceof SequencerPlaybackProvider ? ((SequencerPlaybackProvider) playbackProvider).getSequencer() : null;
        long positionMillis = this.playbackController.getPositionMillis();
        this.playbackController.stop(ChangeReason.SWITCHING_PLAYERS);
        CastingPlaybackProvider castingPlaybackProvider = new CastingPlaybackProvider(this.playback.getPlaybackConfig());
        if (override && sequencer != null) {
            castingPlaybackProvider.setSequencerAndPosition(sequencer, positionMillis, new SequencerPlaybackProvider.SimpleMediaItemVerifier(sequencer.getCurrentMediaItem()), ControlSource.APP_UI);
        } else if (sequencer == null || (sequencer instanceof NoOpSequencer)) {
            castingPlaybackProvider.setDisconnectSequencer(sequencer);
        } else {
            castingPlaybackProvider.setDisconnectSequencer(new PlaceholderSequencer());
            castingPlaybackProvider.setSequencerAndPosition(sequencer, positionMillis, new SequencerPlaybackProvider.SimpleMediaItemVerifier(sequencer.getCurrentMediaItem()), ControlSource.APP_UI);
        }
        this.playback.setPlaybackProvider(castingPlaybackProvider);
        showCastingConnectionToast(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerToChromecast() {
        MediaAccessInfo mediaAccessInfo;
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem != null && (mediaAccessInfo = currentMediaItem.getMediaAccessInfo()) != null && !mediaAccessInfo.isCastable()) {
            BauhausToastUtils.showDefaultToast(this.mContext, this.mContext.getString(R.string.cast_unsupported_content_text), 1);
        }
        setPlayer(new ChromecastPlayer(this.mContext), this.playbackController.getPlayStatus().willPlay());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerToLocal() {
        setPlayer(new CompositePlayer(), false);
    }

    private void setPlayer(final Player player, final boolean startPlaying) {
        PlaybackProvider playbackProvider = this.playback.getPlaybackProvider();
        PlayerPlaybackProvider defaultPlaybackProvider = this.playback.getPlaybackConfig().getDefaultPlaybackProvider(this.mricToken);
        ControlSource controlSource = ControlSource.APP_UI;
        defaultPlaybackProvider.setPlayer(player, controlSource);
        if (playbackProvider != defaultPlaybackProvider && (playbackProvider instanceof SequencerPlaybackProvider)) {
            ((SequencerPlaybackProvider) defaultPlaybackProvider).setSequencerAndPosition(((SequencerPlaybackProvider) playbackProvider).getSequencer(), this.playbackController.getPositionMillis(), null, controlSource);
        }
        Log.info(TAG, "setPlayer, calling on playback.setPlaybackProvider() -> shutdown(false)");
        this.playback.setPlaybackProvider(defaultPlaybackProvider);
        if (startPlaying) {
            this.playbackController.play();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recoverAlexaSequencer(final CastingPlaybackProvider castingProvider, final SequencerPlaybackProvider defaultProvider) {
        Log.debug(TAG, "recoverAlexaSequencer(" + castingProvider + ", " + defaultProvider + ")");
        final long positionMillis = castingProvider.getPositionMillis();
        final MediaItem currentMediaItem = castingProvider.getCurrentMediaItem();
        final PlaceholderSequencer placeholderSequencer = new PlaceholderSequencer();
        defaultProvider.setSequencer(placeholderSequencer, ControlSource.APP_UI);
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingConnectionHandler.3
            @Override // java.lang.Runnable
            public void run() {
                castingProvider.recoverSequencer();
                final Sequencer sequencer = castingProvider.getSequencer();
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingConnectionHandler.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                        CastingConnectionHandler.this.replaceSequencer(placeholderSequencer, sequencer, currentMediaItem, positionMillis);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceSequencer(final Sequencer oldSequencer, final Sequencer newSequencer, final MediaItem oldMediaItem, final long positionMillis) {
        Log.debug(TAG, "replaceSequencer(" + oldSequencer + ", " + newSequencer + ", " + positionMillis + ")");
        PlaybackProvider playbackProvider = this.playback.getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
            if (sequencerPlaybackProvider.getSequencer() == oldSequencer) {
                if (newSequencer == null) {
                    sequencerPlaybackProvider.setSequencer(new NoOpSequencer(), ControlSource.APP_UI);
                } else {
                    boolean zWillPlay = this.playbackController.getPlayStatus().willPlay();
                    sequencerPlaybackProvider.setSequencerAndPosition(newSequencer, positionMillis, new SequencerPlaybackProvider.SimpleMediaItemVerifier(oldMediaItem), ControlSource.APP_UI);
                    if (zWillPlay && !newSequencer.isAfterEnd()) {
                        this.playbackController.play();
                    }
                }
            }
        }
        if (this.playbackController.canPlay()) {
            return;
        }
        this.mContext.sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.finish"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCastingConnectionToast(final boolean isConnected) {
        String lastCastingDeviceName = CastingUtil.getLastCastingDeviceName(this.mContext);
        if (TextUtils.isEmpty(lastCastingDeviceName)) {
            return;
        }
        if (isConnected) {
            BauhausToastUtils.showDefaultToast(this.mContext, this.mContext.getString(R.string.casting_connected_to_text, lastCastingDeviceName), 0);
        } else {
            BauhausToastUtils.showDefaultToast(this.mContext, this.mContext.getString(R.string.casting_continue_on_disconnect, lastCastingDeviceName), 0);
        }
    }
}
