package com.amazon.music.media.playback.config;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.access.BasicMediaAccess;
import com.amazon.music.media.playback.access.MediaAccess;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.access.MediaAccessPolicy;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy;
import com.amazon.music.media.playback.impl.DefaultPlaybackProvider;
import com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory;
import com.amazon.music.media.playback.metrics.mts.MTSEventFactory;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.player.CompositePlayer;
import com.amazon.music.media.playback.player.NoOpPlayer;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.NetworkInfoUtil;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.video.HttpVideoDeliveryService;
import com.amazon.music.media.playback.video.ServiceEnvironmentProd;
import com.amazon.music.platform.data.ConnectionType;
import com.amazon.music.platform.data.subscription.NoSubscriptionTracking;
import com.amazon.video.sdk.PlayerSdkConfigData;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackConfig {
    private Class<? extends Activity> activityToOpen;
    private BitRateSelection bitRateSelectionForOTA;
    private BitRateSelection bitRateSelectionForWifi;
    private File cacheDir;
    private PendingIntent clickActionIntent;
    private ClientIdentification clientIdentification;
    private ConcurrencyResolutionPolicy concurrencyPolicy;
    private ConcurrencyProvider concurrencyProvider;
    private final Context context;
    private boolean customKeyHandlingRequired;
    private boolean debugHarley;
    private PlayerPlaybackProvider defaultPlaybackProvider;
    private MTSEventFactory eventFactory;
    private MTSMetricsSender eventSender;
    private float fastForwardSpeed;
    private long ghostListeningTimeoutMillis;
    private ItemLoader<Uri, Bitmap> imageLoader;
    private MediaItem.ImageUriProvider imageUriProvider;
    private boolean isOnlineInteraction;
    private boolean isUsingGhostListeningThresholdFromServer;
    private boolean isVideoEnabled;
    private LibraryAccessProvider libraryAccessProvider;
    private ListenerSet<OnConfigurationChangedListener> listeners;

    @Nullable
    private MediaItem.ImageUriProvider localImageUriProvider;
    private Player localPlayer;
    private boolean loudnessNormalizationEnabled;
    private MediaStreamingStatusProvider mMediaStreamingStatusProvider;
    private PlaybackExperienceModeProvider mPlaybackExperienceModeProvider;
    private long maxSilenceMillis;
    private MediaAccessPolicy mediaAccessPolicy;
    private MediaLinkProvider mediaLinkProvider;
    private MediaNotificationFactory mediaNotificationFactory;
    private boolean mediaSessionLimited;
    private MediaSessionResources mediaSessionResources;
    private OnMediaBrowseHandler onMediaBrowseHandler;
    private PlaybackPersistenceProvider persistenceProvider;
    private ImagePlaceholderProvider placeholderProvider;
    private final Playback playback;
    private UUID playbackInstanceId;
    private PlayerAuthentication playerAuthentication;
    private int prefetchCount;
    private int previewCount;
    private Player primeVideoPlayer;
    private PlayerSdkConfigData pvPlayerSdkConfigData;
    private boolean redact;
    private boolean resetPositionOnRepeat;
    private float rewindSpeed;
    private OnMediaSearchHandler searchHandler;
    private boolean showAddIconBasedOnLibraryProvider;
    private boolean showAddToLibraryIcon;
    private boolean showNotificationInForeground;
    private int simultaneousPrefetchCount;
    private SkipLimitProvider skipLimitProvider;
    private boolean spatialEnabled;
    private File storageDir;
    private boolean streamOnWifiOnly;
    private long thumbsDownSkipDelayMillis;
    private final boolean unitTestMode;
    private boolean useLocalImageUri;
    private UUID videoPlaybackInstanceId;
    private VideoStreamingSettingsProvider videoStreamingProvider;
    private static final long DEFAULT_GHOST_LISTENING_TIMEOUT = TimeUnit.HOURS.toMillis(3);
    private static final long DEFAULT_MAX_SILENCE = TimeUnit.MINUTES.toMillis(5);
    private static final long MIN_MAX_SILENCE = TimeUnit.SECONDS.toMillis(30);
    private static final Logger logger = LoggerFactory.getLogger("PlaybackConfig");
    private static final MediaAccessPolicy DEFAULT_MEDIA_ACCESS_POLICY = new MediaAccessPolicy() { // from class: com.amazon.music.media.playback.config.PlaybackConfig.1
        @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
        public boolean isExplicitAllowed() {
            return true;
        }

        public String toString() {
            return "PlaybackConfig.DEFAULT_MEDIA_ACCESS_POLICY";
        }

        @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
        public String getContentSubscriptionMode() {
            return new NoSubscriptionTracking().contentSubscriptionMode();
        }

        @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
        public String getSubContentSubscriptionMode() {
            return new NoSubscriptionTracking().subContentSubscriptionMode();
        }

        @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
        public MediaAccess getMediaAccess(MediaAccessInfo mediaAccessInfo) {
            return mediaAccessInfo.isOwned() ? BasicMediaAccess.CAN_PLAY_FOREVER : BasicMediaAccess.NONE;
        }
    };

    public interface OnConfigurationChangedListener {
        void onConfigurationChanged(PlaybackConfig playbackConfig);
    }

    public PlaybackConfig(Context context, Playback playback) {
        this(context, playback, false);
    }

    public PlaybackConfig(Context context, Playback playback, boolean z) {
        this.imageLoader = new CachedItemLoader(new SimpleImageLoader(), 3);
        this.showAddIconBasedOnLibraryProvider = true;
        this.showAddToLibraryIcon = true;
        this.resetPositionOnRepeat = false;
        this.useLocalImageUri = false;
        this.previewCount = 4;
        this.prefetchCount = 4;
        this.simultaneousPrefetchCount = 2;
        this.maxSilenceMillis = DEFAULT_MAX_SILENCE;
        this.thumbsDownSkipDelayMillis = 0L;
        this.fastForwardSpeed = 4.0f;
        this.rewindSpeed = -4.0f;
        this.spatialEnabled = false;
        this.loudnessNormalizationEnabled = true;
        this.imageUriProvider = MediaItem.DEFAULT_IMAGE_URI_PROVIDER;
        this.localImageUriProvider = null;
        this.concurrencyPolicy = ConcurrencyResolutionPolicy.MANUAL;
        this.mediaAccessPolicy = DEFAULT_MEDIA_ACCESS_POLICY;
        this.isVideoEnabled = false;
        this.isOnlineInteraction = false;
        this.isUsingGhostListeningThresholdFromServer = true;
        this.ghostListeningTimeoutMillis = DEFAULT_GHOST_LISTENING_TIMEOUT;
        this.debugHarley = false;
        this.redact = true;
        this.listeners = new ListenerSet<>(OnConfigurationChangedListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES);
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        if (applicationContext == null) {
            throw new IllegalArgumentException("Could not obtain application context from " + context);
        }
        if (playback.getPlaybackConfig() != null) {
            throw new IllegalStateException("playback is already initialized");
        }
        this.playback = (Playback) Validate.notNull(playback);
        this.unitTestMode = z;
        this.storageDir = new File(context.getFilesDir(), "dmmplayer_storage");
        this.cacheDir = new File(context.getCacheDir(), "dmmplayer_cache");
        this.eventFactory = new DefaultMTSEventFactory(this);
        this.playbackInstanceId = UUID.randomUUID();
        this.videoPlaybackInstanceId = UUID.randomUUID();
    }

    public Playback getPlayback() {
        return this.playback;
    }

    public Context getContext() {
        return this.context;
    }

    public boolean isUnitTestMode() {
        return this.unitTestMode;
    }

    @Nullable
    public SkipLimitProvider getSkipLimitProvider() {
        return this.skipLimitProvider;
    }

    public void setSkipLimitProvider(@Nullable SkipLimitProvider skipLimitProvider) {
        this.skipLimitProvider = skipLimitProvider;
    }

    public PlaybackExperienceMode getPlaybackExperienceMode() {
        PlaybackExperienceModeProvider playbackExperienceModeProvider = this.mPlaybackExperienceModeProvider;
        return playbackExperienceModeProvider == null ? PlaybackExperienceMode.STANDARD_MODE : playbackExperienceModeProvider.getPlaybackExperienceMode();
    }

    public void setPlaybackExperienceModeProvider(PlaybackExperienceModeProvider playbackExperienceModeProvider) {
        this.mPlaybackExperienceModeProvider = playbackExperienceModeProvider;
    }

    public PlaybackConfig setOnMediaBrowseHandler(@Nullable OnMediaBrowseHandler onMediaBrowseHandler) {
        if (this.onMediaBrowseHandler != onMediaBrowseHandler) {
            this.onMediaBrowseHandler = onMediaBrowseHandler;
            notifyChanged();
        }
        return this;
    }

    public OnMediaBrowseHandler getOnMediaBrowseHandler() {
        return this.onMediaBrowseHandler;
    }

    public PlaybackConfig setOnMediaSearchHandler(@Nullable OnMediaSearchHandler onMediaSearchHandler) {
        if (this.searchHandler != onMediaSearchHandler) {
            this.searchHandler = onMediaSearchHandler;
            notifyChanged();
        }
        return this;
    }

    public PlaybackConfig setMediaSessionResources(@Nullable MediaSessionResources mediaSessionResources) {
        this.mediaSessionResources = mediaSessionResources;
        return this;
    }

    @Nullable
    public MediaSessionResources getMediaSessionResources() {
        return this.mediaSessionResources;
    }

    @Nullable
    public OnMediaSearchHandler getOnMediaSearchHandler() {
        return this.searchHandler;
    }

    public PlaybackConfig setMediaNotificationFactory(MediaNotificationFactory mediaNotificationFactory) {
        if (this.mediaNotificationFactory != mediaNotificationFactory) {
            this.mediaNotificationFactory = mediaNotificationFactory;
            notifyChanged();
        }
        return this;
    }

    public MediaNotificationFactory getMediaNotificationFactory() {
        return this.mediaNotificationFactory;
    }

    public PlaybackConfig setImageLoader(ItemLoader<Uri, Bitmap> itemLoader) {
        if (this.imageLoader != itemLoader) {
            this.imageLoader = itemLoader;
            notifyChanged();
        }
        return this;
    }

    public ItemLoader<Uri, Bitmap> getImageLoader() {
        return this.imageLoader;
    }

    public ImagePlaceholderProvider getPlaceholderProvider() {
        return this.placeholderProvider;
    }

    public PlaybackConfig setShowNotificationInForeground(boolean z) {
        if (this.showNotificationInForeground != z) {
            this.showNotificationInForeground = z;
            notifyChanged();
        }
        return this;
    }

    public boolean shouldShowNotificationInForeground() {
        return this.showNotificationInForeground;
    }

    public PlaybackConfig setGhostListeningTimeoutMillis(long j) {
        if (this.ghostListeningTimeoutMillis != j) {
            logger.info("Changing ghost listening timeout from " + StringUtils.getDurationString(this.ghostListeningTimeoutMillis) + " to " + StringUtils.getDurationString(j));
            this.ghostListeningTimeoutMillis = j;
            notifyChanged();
        }
        return this;
    }

    public long getGhostListeningTimeoutMillis() {
        return this.ghostListeningTimeoutMillis;
    }

    public boolean isUsingGhostListeningThresholdFromServer() {
        return this.isUsingGhostListeningThresholdFromServer;
    }

    public PlaybackConfig setMediaSessionLimited(boolean z) {
        if (this.mediaSessionLimited != z) {
            this.mediaSessionLimited = z;
            notifyChanged();
        }
        return this;
    }

    public boolean isMediaSessionLimited() {
        return this.mediaSessionLimited;
    }

    public PlaybackConfig setStreamOnWifiOnly(boolean z) {
        if (this.streamOnWifiOnly != z) {
            this.streamOnWifiOnly = z;
            notifyChanged();
        }
        return this;
    }

    public boolean isStreamOnWifiOnly() {
        return this.streamOnWifiOnly;
    }

    public PlaybackConfig setVideoStreamingSettingsProvider(VideoStreamingSettingsProvider videoStreamingSettingsProvider) {
        if (this.videoStreamingProvider != videoStreamingSettingsProvider) {
            this.videoStreamingProvider = videoStreamingSettingsProvider;
            notifyChanged();
        }
        return this;
    }

    public PlaybackConfig setCustomKeyHandlingRequired(boolean z) {
        if (this.customKeyHandlingRequired != z) {
            this.customKeyHandlingRequired = z;
            notifyChanged();
        }
        return this;
    }

    public boolean isCustomKeyHandlingRequired() {
        return this.customKeyHandlingRequired;
    }

    public PlaybackConfig setMediaStreamingStatusProvider(MediaStreamingStatusProvider mediaStreamingStatusProvider) {
        if (this.mMediaStreamingStatusProvider != mediaStreamingStatusProvider) {
            this.mMediaStreamingStatusProvider = mediaStreamingStatusProvider;
            notifyChanged();
        }
        return this;
    }

    public MediaStreamingStatusProvider getMediaStreamingStatusProvider() {
        return this.mMediaStreamingStatusProvider;
    }

    public PlaybackConfig setPreviewCount(int i) {
        if (i < 0) {
            StrictMode.crashIfStrict("previewCount should be at least 0, not " + i + "!");
            return this;
        }
        if (this.previewCount != i) {
            this.previewCount = i;
            notifyChanged();
        }
        return this;
    }

    public int getPreviewCount() {
        return this.previewCount;
    }

    public int getPrefetchCount() {
        return this.prefetchCount;
    }

    public int getSimultaneousPrefetchCount() {
        return this.simultaneousPrefetchCount;
    }

    public PlaybackConfig setMaxSilenceMillis(long j) {
        if (j >= MIN_MAX_SILENCE) {
            if (this.maxSilenceMillis != j) {
                this.maxSilenceMillis = j;
                notifyChanged();
            }
            return this;
        }
        StrictMode.crashIfStrict("maxSilenceMillis should probably be around 5 minutes - not " + StringUtils.getDurationString(j) + "!");
        return this;
    }

    public long getMaxSilenceMillis() {
        return this.maxSilenceMillis;
    }

    public PlaybackConfig setThumbsDownSkipDelayMillis(long j) {
        if (j < 0) {
            j = -1;
        }
        if (this.thumbsDownSkipDelayMillis != j) {
            this.thumbsDownSkipDelayMillis = j;
            notifyChanged();
        }
        return this;
    }

    public long getThumbsDownSkipDelayMillis() {
        return this.thumbsDownSkipDelayMillis;
    }

    public float getFastForwardSpeed() {
        return this.fastForwardSpeed;
    }

    public float getRewindSpeed() {
        return this.rewindSpeed;
    }

    public Player getLocalPlayer(@Nullable String str) {
        if (this.localPlayer == null) {
            this.localPlayer = createDefaultLocalPlayer(str);
        }
        return this.localPlayer;
    }

    public Player getPrimeVideoPlayer() {
        if (!this.isVideoEnabled) {
            return new NoOpPlayer(this.context);
        }
        if (this.primeVideoPlayer == null) {
            initializePrimeVideoPlayer();
        }
        return this.primeVideoPlayer;
    }

    public void initializePrimeVideoPlayer() {
        if (this.isVideoEnabled && this.primeVideoPlayer == null) {
            this.primeVideoPlayer = new PrimeVideoPlayer(this.context, this.pvPlayerSdkConfigData, this.videoStreamingProvider, new HttpVideoDeliveryService(this.clientIdentification.getRequestInterceptor(), this.clientIdentification.getMarketplaceID(), new ServiceEnvironmentProd()));
            ((CompositePlayer) getDefaultPlaybackProvider(null).getPlayer()).lateInitializePlayer(this.primeVideoPlayer);
        }
    }

    public void setVideoEnabled(boolean z) {
        this.isVideoEnabled = z;
    }

    protected Player createDefaultLocalPlayer(@Nullable String str) {
        return new HarleyPlayer(this.context, str);
    }

    public PlayerPlaybackProvider getDefaultPlaybackProvider(@Nullable String str) {
        if (this.defaultPlaybackProvider == null) {
            this.defaultPlaybackProvider = new DefaultPlaybackProvider(this);
        }
        this.defaultPlaybackProvider.setMricToken(str);
        if (this.defaultPlaybackProvider.getPlayer() instanceof NoOpPlayer) {
            this.defaultPlaybackProvider.setPlayer(new CompositePlayer(), ControlSource.NONE);
        }
        return this.defaultPlaybackProvider;
    }

    public PlayerPlaybackProvider getDefaultPlaybackProvider(@Nullable String str, OnPlaybackMetricsListener onPlaybackMetricsListener, OnFrameChangedListener onFrameChangedListener, OnPrebufferChangedListener onPrebufferChangedListener) {
        getDefaultPlaybackProvider(str);
        this.defaultPlaybackProvider.setPlaybackMetricsListener(onPlaybackMetricsListener);
        this.defaultPlaybackProvider.setOnFrameChangedListener(onFrameChangedListener);
        this.defaultPlaybackProvider.setOnPrebufferChangedListener(onPrebufferChangedListener);
        return this.defaultPlaybackProvider;
    }

    public PlaybackConfig setClientIdentification(ClientIdentification clientIdentification) {
        if (this.clientIdentification != clientIdentification) {
            this.clientIdentification = clientIdentification;
            notifyChanged();
        }
        return this;
    }

    public ClientIdentification getClientIdentification() {
        return this.clientIdentification;
    }

    public PlaybackConfig setPlayerAuthentication(PlayerAuthentication playerAuthentication) {
        if (this.playerAuthentication != playerAuthentication) {
            this.playerAuthentication = playerAuthentication;
            notifyChanged();
        }
        return this;
    }

    public PlaybackConfig setPlayerAuthentication(String str, String str2) {
        setPlayerAuthentication(new PlayerAuthentication(str, "-----BEGIN RSA PRIVATE KEY-----\n" + str2 + "\n-----END RSA PRIVATE KEY-----"));
        return this;
    }

    public PlayerAuthentication getPlayerAuthentication() {
        return this.playerAuthentication;
    }

    public PlaybackConfig setBitRateSelectionForWifi(BitRateSelection bitRateSelection) {
        if (this.bitRateSelectionForWifi != bitRateSelection) {
            logger.info("Changing BitRateSelection for Wifi from " + this.bitRateSelectionForWifi + " to " + bitRateSelection);
            this.bitRateSelectionForWifi = bitRateSelection;
            notifyChanged();
        }
        return this;
    }

    public PlaybackConfig setBitRateSelectionForOTA(BitRateSelection bitRateSelection) {
        if (this.bitRateSelectionForOTA != bitRateSelection) {
            logger.info("Changing BitRateSelection for OTA from " + this.bitRateSelectionForOTA + " to " + bitRateSelection);
            this.bitRateSelectionForOTA = bitRateSelection;
            notifyChanged();
        }
        return this;
    }

    public BitRateSelection getBitRateSelection() {
        ConnectionType connectionType = NetworkInfoUtil.getConnectionType();
        if (connectionType != null && (connectionType instanceof ConnectionType.WiFiConnection)) {
            return this.bitRateSelectionForWifi;
        }
        return this.bitRateSelectionForOTA;
    }

    public BitRateSelection getBitRateSelectionForWifi() {
        return this.bitRateSelectionForWifi;
    }

    public BitRateSelection getBitRateSelectionForOTA() {
        return this.bitRateSelectionForOTA;
    }

    public PlaybackConfig setSpatialEnabled(boolean z) {
        if (this.spatialEnabled != z) {
            logger.info("Changing spatial enabled from " + this.spatialEnabled + " to " + z);
            this.spatialEnabled = z;
            notifyChanged();
        }
        return this;
    }

    public boolean isSpatialEnabled() {
        return this.spatialEnabled;
    }

    public PlaybackConfig setLoudnessNormalizationEnabled(boolean z) {
        boolean z2 = this.loudnessNormalizationEnabled != z;
        this.loudnessNormalizationEnabled = z;
        if (z2) {
            notifyChanged();
        }
        return this;
    }

    public boolean isLoudnessNormalizationEnabled() {
        return this.loudnessNormalizationEnabled;
    }

    public PlaybackConfig setMediaLinkProvider(MediaLinkProvider mediaLinkProvider) {
        if (this.mediaLinkProvider != mediaLinkProvider) {
            this.mediaLinkProvider = mediaLinkProvider;
            notifyChanged();
        }
        return this;
    }

    public MediaLinkProvider getMediaLinkProvider() {
        return this.mediaLinkProvider;
    }

    public PlaybackConfig setMediaItemImageUriProvider(MediaItem.ImageUriProvider imageUriProvider) {
        if (this.imageUriProvider != imageUriProvider) {
            this.imageUriProvider = imageUriProvider;
            notifyChanged();
        }
        return this;
    }

    public MediaItem.ImageUriProvider getMediaItemImageUriProvider() {
        return this.imageUriProvider;
    }

    public PlaybackConfig setLocalImageUriProvider(MediaItem.ImageUriProvider imageUriProvider) {
        if (this.localImageUriProvider != imageUriProvider) {
            this.localImageUriProvider = imageUriProvider;
            notifyChanged();
        }
        return this;
    }

    public MediaItem.ImageUriProvider getLocalImageUriProvider() {
        return this.localImageUriProvider;
    }

    public PlaybackConfig setMTSMetricsSender(MTSMetricsSender mTSMetricsSender) {
        if (this.eventSender != mTSMetricsSender) {
            this.eventSender = mTSMetricsSender;
            notifyChanged();
        }
        return this;
    }

    public MTSMetricsSender getMTSMetricsSender() {
        return this.eventSender;
    }

    public void setPvPlayerSdkConfigData(PlayerSdkConfigData playerSdkConfigData) {
        this.pvPlayerSdkConfigData = playerSdkConfigData;
    }

    public PlaybackConfig setMTSEventFactory(MTSEventFactory mTSEventFactory) {
        if (this.eventFactory != mTSEventFactory) {
            this.eventFactory = mTSEventFactory;
            notifyChanged();
        }
        return this;
    }

    public MTSEventFactory getMTSEventFactory() {
        return this.eventFactory;
    }

    public PlaybackConfig setConcurrencyProvider(ConcurrencyProvider concurrencyProvider) {
        if (this.concurrencyProvider != concurrencyProvider) {
            this.concurrencyProvider = concurrencyProvider;
            notifyChanged();
        }
        return this;
    }

    public ConcurrencyProvider getConcurrencyProvider() {
        return this.concurrencyProvider;
    }

    public ConcurrencyResolutionPolicy getConcurrencyResolutionPolicy() {
        return this.concurrencyPolicy;
    }

    public PlaybackConfig setMediaAccessPolicy(MediaAccessPolicy mediaAccessPolicy) {
        if (this.mediaAccessPolicy != mediaAccessPolicy) {
            logger.info("Changing MediaAccessPolicy from " + this.mediaAccessPolicy + " to " + mediaAccessPolicy);
            this.mediaAccessPolicy = mediaAccessPolicy;
            notifyChanged();
        }
        return this;
    }

    public MediaAccessPolicy getMediaAccessPolicy() {
        return this.mediaAccessPolicy;
    }

    public void setHarleyDebugModeEnabled(boolean z) {
        if (this.debugHarley != z) {
            this.debugHarley = z;
            notifyChanged();
        }
    }

    public boolean isHarleyDebugModeEnabled() {
        return this.debugHarley;
    }

    public boolean isRedactEnabled() {
        return this.redact;
    }

    public PlaybackConfig setPlaybackPersistenceProvider(PlaybackPersistenceProvider playbackPersistenceProvider) {
        if (this.persistenceProvider != playbackPersistenceProvider) {
            this.persistenceProvider = playbackPersistenceProvider;
            notifyChanged();
        }
        return this;
    }

    public PlaybackPersistenceProvider getPlaybackPersistenceProvider() {
        return this.persistenceProvider;
    }

    public File getCacheDir() {
        return this.cacheDir;
    }

    public File getStorageDir() {
        return this.storageDir;
    }

    public PlaybackConfig setLibraryAccessProvider(LibraryAccessProvider libraryAccessProvider) {
        if (this.libraryAccessProvider != libraryAccessProvider) {
            this.libraryAccessProvider = libraryAccessProvider;
            notifyChanged();
        }
        return this;
    }

    public LibraryAccessProvider getLibraryAccessProvider() {
        return this.libraryAccessProvider;
    }

    public PlaybackConfig setClickAction(PendingIntent pendingIntent) {
        if (this.clickActionIntent != pendingIntent) {
            this.activityToOpen = null;
            this.clickActionIntent = pendingIntent;
            notifyChanged();
        }
        return this;
    }

    public PendingIntent getClickAction() {
        return this.clickActionIntent;
    }

    public void addOnConfigurationChangedListener(OnConfigurationChangedListener onConfigurationChangedListener) {
        this.listeners.add(onConfigurationChangedListener);
    }

    public void removeOnConfigurationChangedListener(OnConfigurationChangedListener onConfigurationChangedListener) {
        this.listeners.remove(onConfigurationChangedListener);
    }

    private void notifyChanged() {
        this.listeners.proxy().onConfigurationChanged(this);
    }

    public UUID getPlaybackInstanceId() {
        return this.playbackInstanceId;
    }

    public void setPlaybackInstanceId(UUID uuid) {
        this.playbackInstanceId = uuid;
    }

    public UUID getVideoPlaybackInstanceId() {
        return this.videoPlaybackInstanceId;
    }

    public void setVideoPlaybackInstanceId(UUID uuid) {
        this.videoPlaybackInstanceId = uuid;
    }

    public boolean isShowAddIconBasedOnLibraryProvider() {
        return this.showAddIconBasedOnLibraryProvider;
    }

    public boolean isShowAddToLibraryIcon() {
        return this.showAddToLibraryIcon;
    }

    public void setShowAddToLibraryIcon(boolean z) {
        this.showAddToLibraryIcon = z;
    }

    public boolean isResetPositionOnRepeat() {
        return this.resetPositionOnRepeat;
    }

    public void setResetPositionOnRepeat(boolean z) {
        this.resetPositionOnRepeat = z;
    }

    public boolean isUseLocalImageUri() {
        return this.useLocalImageUri;
    }

    public void setUseLocalImageUri(boolean z) {
        this.useLocalImageUri = z;
    }

    public boolean isOnlineInteraction() {
        return this.isOnlineInteraction;
    }

    public void setOnlineInteraction(boolean z) {
        this.isOnlineInteraction = z;
    }
}
