package com.amazon.mp3.playback.harley;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.TimeSpan;
import com.amazon.digitalmusicplayback.CustomerBenefitFlags;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.CustomerServiceTierEnum;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.map.DeviceAttributesQuery;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bluetooth.BluetoothServiceListener;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.dialog.fragment.BackgroundRestrictionsDialogFragment;
import com.amazon.mp3.dialog.fragment.GhostListeningDialogFragment;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ghostlistening.GhostListeningMetricsProvider;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.HarleyUserPreferencesUpdater;
import com.amazon.mp3.playback.activity.PlaybackInitializationActivity;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.casting.CastingConnectionHandler;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.AudioEffectController;
import com.amazon.mp3.playback.streaming.concurrency.StreamingConcurrencyStatusConnector;
import com.amazon.mp3.playback.video.AuthContextData;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import com.amazon.mp3.util.AutoPlaySFAUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.voice.AlexaAwareMTSEventFactory;
import com.amazon.mp3.voice.AlexaAwareMTSMetricsSender;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.AdpProviderMAPR5;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.CachedItemLoader;
import com.amazon.music.media.playback.config.ClientIdentification;
import com.amazon.music.media.playback.config.DefaultMediaNotificationFactory;
import com.amazon.music.media.playback.config.MediaSessionResources;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.config.SimpleImageLoader;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ExperimentFeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.video.sdk.AdConfig;
import com.amazon.video.sdk.AdConfigData;
import com.amazon.video.sdk.AdConfigProvider;
import com.amazon.video.sdk.EPrivacyConsentDataProvider;
import com.amazon.video.sdk.PlayerSdkConfigData;
import com.amazon.video.sdk.content.LocalContentManagerConfigData;
import com.amazon.video.sdk.player.NoOpPlaybackEnvelopeValidator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackInitializationHelper {
    private static final String TAG = "PlaybackInitializationHelper";
    private static AccountStateChangeListener currentAccountListener;
    private AudioVideoPlaybackNotificationReceiver audioVideoPlaybackNotificationReceiver;
    private final Context context;
    private final ExplicitLanguageManager explicitFilter;
    private boolean mHasCheckedSystemSettings;
    private final AccountManager accountManager = AccountManagerSingleton.get();
    private final AccountStateChangeListener accountListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            ChangeReason changeReason;
            Playback.getInstance();
            if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
                ExplicitLanguageManager unused = PlaybackInitializationHelper.this.explicitFilter;
                if (ExplicitLanguageManager.isExplicitFilterEnabled()) {
                    changeReason = ChangeReason.EXPLICIT_FILTER_ENABLED;
                } else {
                    changeReason = ChangeReason.EXPLICIT_FILTER_DISABLED;
                }
                if (PlaybackInitializationHelper.this.shouldStopIfExplicitChanges()) {
                    PlaybackControllerProvider.getController(ControlSource.APP_UI).stop(changeReason);
                    NowPlayingManager.getInstance().clear();
                }
            }
            if (set.contains(AccountStateField.USER_BENEFITS)) {
                PlaybackInitializationHelper.this.updateUserBenefits();
                new HarleyUserPreferencesUpdater(PlaybackInitializationHelper.this.context).updateUserQualityPreferences();
            }
            MediaAccessPolicyHelper.updateMediaAccessPolicy(PlaybackInitializationHelper.this.context);
            if (UserSubscriptionUtil.isNightwingOnly()) {
                Playback.getInstance().getPlaybackConfig().setPreviewCount(0);
            } else {
                Playback.getInstance().getPlaybackConfig().setPreviewCount(4);
            }
        }
    };
    private final OnPlaybackEventListener mPlaybackEventListener = new AnonymousClass2();

    public PlaybackInitializationHelper(final Context context) {
        this.context = context;
        this.explicitFilter = new ExplicitLanguageManager(context.getApplicationContext());
    }

    public void init() {
        Log.warning(TAG, "Initializing playback instance.");
        initPlayback();
        initPlaybackNotificationReceiver();
        registerBootstrapSubjectListeners();
        AccountStateChangeListener accountStateChangeListener = currentAccountListener;
        if (accountStateChangeListener != this.accountListener) {
            if (accountStateChangeListener != null) {
                this.accountManager.deregisterListener(accountStateChangeListener);
            }
            AccountStateChangeListener accountStateChangeListener2 = this.accountListener;
            currentAccountListener = accountStateChangeListener2;
            this.accountManager.registerListener(accountStateChangeListener2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldStopIfExplicitChanges() {
        MediaCollectionInfo mediaCollectionInfo = Playback.getInstance().getPlaybackController(ControlSource.NONE).getMediaCollectionInfo();
        if (mediaCollectionInfo == null) {
            return false;
        }
        MediaCollectionType type = mediaCollectionInfo.getType();
        return type == MediaCollectionType.PRIME_STATION || type == MediaCollectionType.UNLIMITED_STATION || type == MediaCollectionType.AMF_STATION || type == MediaCollectionType.GOLDEN_PLAYLIST || type == MediaCollectionType.AMF_STATION_SEED || type == MediaCollectionType.PRIME_STATION_SEED || type == MediaCollectionType.UNLIMITED_STATION_SEED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public void updateUserBenefits() {
        Player localPlayer = Playback.getInstance().getPlaybackConfig().getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.context));
        if (!(localPlayer instanceof HarleyPlayer)) {
            Log.error(TAG, "Local player is not harley player, cannot update Harley on the account state changes");
            return;
        }
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        boolean zIsPrime = userSubscription.isPrime();
        boolean zIsSonicRush = userSubscription.isSonicRush();
        boolean zIsHawkfireAllDevices = userSubscription.isHawkfireAllDevices();
        boolean zIsKatana = userSubscription.isKatana();
        boolean zIsNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        EnumSet<CustomerBenefitFlags> enumSetNoneOf = EnumSet.noneOf(CustomerBenefitFlags.class);
        if (zIsPrime) {
            enumSetNoneOf.add(CustomerBenefitFlags.PRIME);
        }
        if (zIsSonicRush) {
            enumSetNoneOf.add(CustomerBenefitFlags.SONICRUSH);
        }
        if (zIsHawkfireAllDevices) {
            enumSetNoneOf.add(CustomerBenefitFlags.HAWKFIRE);
        }
        if (zIsKatana) {
            enumSetNoneOf.add(CustomerBenefitFlags.KATANA);
        }
        if (zIsNightwingOnly) {
            enumSetNoneOf.add(CustomerBenefitFlags.NIGHTWING);
        }
        ((HarleyPlayer) localPlayer).setCustomerBenefits(enumSetNoneOf);
        Log.info(TAG, "Updated Harley on the account state changes isPrime: %s isSonicRush: %s isUnlimited: %s isKatana: %s isNightwing: %s", Boolean.valueOf(zIsPrime), Boolean.valueOf(zIsSonicRush), Boolean.valueOf(zIsHawkfireAllDevices), Boolean.valueOf(zIsKatana), Boolean.valueOf(zIsNightwingOnly));
    }

    public static void updateSpatialMode(final Context context) {
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        if (playbackConfig != null) {
            updateSpatialMode(context, playbackConfig);
        }
    }

    private static void updateSpatialMode(final Context context, @NonNull final PlaybackConfig config) {
        config.setSpatialEnabled(SettingsUtil.isSpatialModeSettingEnabled(context));
    }

    public void updatePrioritizeDownloadPreference() {
        Player localPlayer = Playback.getInstance().getPlaybackConfig().getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.context));
        if (!(localPlayer instanceof HarleyPlayer)) {
            Log.error(TAG, "Local player is not harley player, cannot update Harley on the account state changes");
            return;
        }
        boolean zIsPrioritizeDownloadsEnabled = SettingsUtil.isPrioritizeDownloadsEnabled(this.context);
        ((HarleyPlayer) localPlayer).setPrioritizeDownloadsPlayback(zIsPrioritizeDownloadsEnabled);
        Log.info(TAG, "Updated Harley on the account state changes isPrioritizeDownloadsEnabled: %s,", Boolean.valueOf(zIsPrioritizeDownloadsEnabled));
    }

    public void initPlayback() {
        Playback playback = Playback.getInstance();
        if (playback.getPlaybackConfig() == null) {
            Log.debug(TAG, "Initializing playback");
            PlaybackConfig playbackConfig = new PlaybackConfig(this.context, playback);
            CachedItemLoader cachedItemLoader = new CachedItemLoader(new SimpleImageLoader(), 3);
            int i = UserSubscriptionUtil.getUserSubscription().isNightwingOnly() ? 0 : 500;
            playbackConfig.setImageLoader(cachedItemLoader);
            playbackConfig.setShowNotificationInForeground(true);
            playbackConfig.setMediaSessionLimited(false);
            playbackConfig.setMaxSilenceMillis(TimeUnit.MINUTES.toMillis(5L));
            playbackConfig.setThumbsDownSkipDelayMillis(i);
            playbackConfig.setMediaSessionResources(new MediaSessionResources.Builder().setShuffleActionName(this.context.getString(R.string.shuffle_on)).setUnshuffleActionName(this.context.getString(R.string.shuffle_off)).setShuffleSelectedDrawable(R.drawable.ic_auto_shuffle_pressed).setShuffleNormalDrawable(R.drawable.ic_auto_shuffle_unpressed).setThumbsUpActionName(this.context.getString(R.string.dmusic_prime_stations_thumbs_up_selected_description)).setThumbsUpUnrateActionName(this.context.getString(R.string.dmusic_prime_stations_thumbs_up_not_selected_description)).setThumbsUpNormalDrawable(R.drawable.ic_action_like_outline).setThumbsUpSelectedDrawable(R.drawable.ic_action_like).setThumbsDownActionName(this.context.getString(R.string.dmusic_prime_stations_thumbs_down_selected_description)).setThumbsDownUnrateActionName(this.context.getString(R.string.dmusic_prime_stations_thumbs_down_not_selected_description)).setThumbsDownNormalDrawable(R.drawable.ic_action_dislike_outline).setThumbsDownSelectedDrawable(R.drawable.ic_action_dislike).setAddToLibraryActionName(this.context.getString(R.string.add_to_library_showcase_title)).setAddToLibraryDrawable(R.drawable.add_to_library_button).setRepeatNoneModeActionName(this.context.getString(R.string.repeat_off)).setRepeatAllModeActionName(this.context.getString(R.string.repeat_all)).setRepeatOneModeActionName(this.context.getString(R.string.repeat_one)).setRepeatNoneModeDrawable(R.drawable.ic_action_repeat_none).setRepeatAllModeDrawable(R.drawable.ic_action_repeat_all).setRepeatOneModeDrawable(R.drawable.ic_action_repeat_one).build());
            playbackConfig.setLibraryAccessProvider(LibraryAccessProviderImpl.getInstance(this.context));
            playbackConfig.setPlaybackPersistenceProvider(PlaybackPersistenceManager.getInstance());
            playbackConfig.setSkipLimitProvider(SkipLimitProviderImpl.INSTANCE);
            playbackConfig.setPlaybackExperienceModeProvider(CarModeUtility.INSTANCE);
            Context context = this.context;
            ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.NOTIFICATION);
            ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
            DefaultMediaNotificationFactory defaultMediaNotificationFactory = new DefaultMediaNotificationFactory(context, controller, playbackConfig, childUserUtil.isChildUser(this.context));
            setPlaybackConfigAction(this.context, playbackConfig);
            playbackConfig.setMediaNotificationFactory(defaultMediaNotificationFactory);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
            playbackConfig.setBitRateSelectionForWifi(getUserApplicableBitrateSelectedForWifi(this.context, defaultSharedPreferences).convertToBitRateSelection());
            playbackConfig.setBitRateSelectionForOTA(getUserApplicableBitrateSelectedForOTA(this.context, defaultSharedPreferences).convertToBitRateSelection());
            updateSpatialMode(this.context, playbackConfig);
            playbackConfig.setLoudnessNormalizationEnabled(defaultSharedPreferences.getBoolean(this.context.getString(R.string.setting_key_is_loudness_normalization_enabled), true));
            playbackConfig.setStreamOnWifiOnly("WIFI_ONLY".equals(SettingsUtil.getStreamingNetworkPreference(this.context, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO)));
            playbackConfig.setHarleyDebugModeEnabled(true);
            playbackConfig.setMTSEventFactory(new AlexaAwareMTSEventFactory(playbackConfig));
            playbackConfig.setMTSMetricsSender(new AlexaAwareMTSMetricsSender(playbackConfig, MetricsHolder.getManager(), new MTSMetricsSender(playbackConfig, MetricsHolder.getManager())));
            ActionValidatedPlaybackController controller2 = PlaybackControllerProvider.getController(ControlSource.APP_UI);
            controller2.addOnPlayStateChangedListener(new OnPlayStateChangedListenerImpl());
            controller2.addOnPlaybackEventListener(this.mPlaybackEventListener);
            playback.addOnPlaybackCommandListener(new OnPlaybackCommandListenerForMetrics());
            playback.init(playbackConfig);
            setMediaSessionActivity(playback.getMediaSession());
            playback.setMricToken(childUserUtil.getMusicRequestIdentityContextToken(this.context));
            playbackConfig.setMediaLinkProvider(new DMLSMediaLinkProvider());
            playbackConfig.setConcurrencyProvider(new DMLSConcurrencyProvider(playbackConfig));
            playbackConfig.setMediaItemImageUriProvider(new GeneralImageUriProvider(this.context));
            AudioEffectController.initialize(this.context);
            updatePrioritizeDownloadPreference();
            return;
        }
        Log.debug(TAG, "Not Initializing playback due to existing playback config");
    }

    private void setMediaSessionActivity(@NonNull final MediaSessionCompat mediaSession) {
        Intent intent = new Intent(LibraryActivityFactory.getIntentForContentUri(this.context, MediaProvider.NowPlaying.CONTENT_URI));
        intent.addFlags(268435456);
        mediaSession.setSessionActivity(PendingIntent.getActivity(this.context, 0, intent, 201326592));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isMusicVideoInQueue() {
        Iterator<MediaItem> it = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentQueue().iterator();
        while (it.hasNext()) {
            if (MediaItemExtensions.isVideo(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isQueueEmpty() {
        return PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentQueue().size() == 0;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.harley.PlaybackInitializationHelper$2, reason: invalid class name */
    class AnonymousClass2 implements OnPlaybackEventListener {
        AnonymousClass2() {
        }

        @Override // com.amazon.music.media.playback.OnPlaybackEventListener
        public void onPlaybackEvent(int event, ControlSource controlSource) {
            if (PlaybackInitializationHelper.this.isMusicVideoInQueue()) {
                return;
            }
            if (event == 28 && ((AutoPlaySFAUtil.isPlayQueueSequencer() || AutoPlaySFAUtil.isCloudQueueSequencer()) && !PlaybackInitializationHelper.this.isQueueEmpty())) {
                FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
                if (featureGateProvider.isFeatureAvailable(Feature.music_never_stops) && SettingsUtil.isSfaSettingEnabled(PlaybackInitializationHelper.this.context)) {
                    startSFAInBackGround();
                } else if (featureGateProvider.isFeatureAvailable(Feature.music_never_stops_prime)) {
                    handleMusicNeverStopsForPrime();
                }
            } else if (event == 18) {
                AutoPlaySFAUtil.onSequencerChanged();
            }
            if (event == 11 || event == 1) {
                AutoPlaySFAUtil.increaseDialogSeenTracks();
            }
        }

        private void handleMusicNeverStopsForPrime() {
            int i = AnonymousClass7.$SwitchMap$com$amazon$music$platform$data$WeblabTreatment[((ExperimentFeatureGateProvider) Providers.INSTANCE.get(ExperimentFeatureGateProvider.class)).evaluateFeatureAndGetTreatment(Feature.music_never_stops_prime, Weblab.DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AB_V3.toString(), Weblab.DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AA_V3.toString()).ordinal()];
            if (i == 1) {
                if (!AutoPlaySFAUtil.isAutoPlayAvailable(PlaybackInitializationHelper.this.context)) {
                    AutoPlaySFAUtil.setSfaAvaliableFlag(PlaybackInitializationHelper.this.context);
                    if (AutoPlaySFAUtil.isCustomerTopListeners()) {
                        SettingsUtil.setSfa(PlaybackInitializationHelper.this.context, false);
                        return;
                    } else {
                        SettingsUtil.setSfa(PlaybackInitializationHelper.this.context, true);
                        startSFAInBackGround();
                        return;
                    }
                }
                startSFAInBackGround();
                return;
            }
            if (i == 2) {
                if (!AutoPlaySFAUtil.isAutoPlayAvailable(PlaybackInitializationHelper.this.context)) {
                    AutoPlaySFAUtil.setSfaAvaliableFlag(PlaybackInitializationHelper.this.context);
                    ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper$2$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$handleMusicNeverStopsForPrime$0();
                        }
                    });
                    return;
                } else {
                    startSFAInBackGround();
                    return;
                }
            }
            if (i != 3) {
                AutoPlaySFAUtil.resetSfaFlags(PlaybackInitializationHelper.this.context);
            } else if (!AutoPlaySFAUtil.isAutoPlayAvailable(PlaybackInitializationHelper.this.context)) {
                AutoPlaySFAUtil.setSfaAvaliableFlag(PlaybackInitializationHelper.this.context);
                SettingsUtil.setSfa(PlaybackInitializationHelper.this.context, false);
            } else {
                startSFAInBackGround();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$handleMusicNeverStopsForPrime$0() {
            if (AutoPlaySFAUtil.isCustomerInEarlyLifeCycle(PlaybackInitializationHelper.this.context)) {
                SettingsUtil.setSfa(PlaybackInitializationHelper.this.context, false);
            } else {
                SettingsUtil.setSfa(PlaybackInitializationHelper.this.context, true);
                startSFAInBackGround();
            }
        }

        private void startSFAInBackGround() {
            if (!SettingsUtil.isSfaSettingEnabled(PlaybackInitializationHelper.this.context) || PlaybackInitializationHelper.this.isCloudQueueSFAPlaying()) {
                return;
            }
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startSFAInBackGround$1();
                }
            });
            AutoPlaySFAUtil.sendEndOfQueueMetric();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$startSFAInBackGround$1() {
            PlaybackInitializationHelper.this.startSFAFromCurrentQueue();
            MetricsLogger.emitUIContentViewMetric(ContentName.SFA_NOW_PLAYING, ContainerType.NOW_PLAYING);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.harley.PlaybackInitializationHelper$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$platform$data$WeblabTreatment;

        static {
            int[] iArr = new int[WeblabTreatment.values().length];
            $SwitchMap$com$amazon$music$platform$data$WeblabTreatment = iArr;
            try {
                iArr[WeblabTreatment.T1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$platform$data$WeblabTreatment[WeblabTreatment.T2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$platform$data$WeblabTreatment[WeblabTreatment.T3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void initPlaybackNotificationReceiver() {
        AudioVideoPlaybackNotificationReceiver audioVideoPlaybackNotificationReceiver = this.audioVideoPlaybackNotificationReceiver;
        if (audioVideoPlaybackNotificationReceiver != null) {
            audioVideoPlaybackNotificationReceiver.removeReceiver(this.context);
        }
        AudioVideoPlaybackNotificationReceiver audioVideoPlaybackNotificationReceiver2 = new AudioVideoPlaybackNotificationReceiver();
        this.audioVideoPlaybackNotificationReceiver = audioVideoPlaybackNotificationReceiver2;
        audioVideoPlaybackNotificationReceiver2.addReceiver(this.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCloudQueueSFAPlaying() {
        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
        if (cloudQueueSequencer != null) {
            List<QueueSeed> queueSeeds = cloudQueueSequencer.getQueue().getQueueSeeds();
            if (queueSeeds.size() > 0 && queueSeeds.get(0).getQueueSeedType() == QueueSeedTypeEnum.TRACK_LIST && queueSeeds.get(0).getPlayMode() == PlayModeEnum.SIMILAR) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void startSFAFromCurrentQueue() {
        try {
            StationUtils.startAutoPlaySFA(this.context, StationUtils.getTrackListSeedFromQueue());
            Log.debug(TAG, "Initiating SFA playback ");
        } catch (Exception e) {
            Log.debug(TAG, "Failed to start SFA " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StreamingBitrate getUserApplicableBitrateSelectedForWifi(final Context context, final SharedPreferences prefs) {
        StreamingBitrate streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(prefs.getString(context.getString(R.string.setting_key_multi_bitrate_streaming_for_wifi), StreamingBitrate.HIGH.getEntryValue()));
        if (streamingBitrateFromEntryValue != StreamingBitrate.KATANA || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return streamingBitrateFromEntryValue;
        }
        StreamingBitrate streamingBitrate = StreamingBitrate.AUTO;
        SettingsUtil.setMultiBitrateStreamingPreferenceForWifi(context, streamingBitrate.getEntryValue());
        return streamingBitrate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StreamingBitrate getUserApplicableBitrateSelectedForOTA(final Context context, final SharedPreferences prefs) {
        StreamingBitrate streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(prefs.getString(context.getString(R.string.setting_key_multi_bitrate_streaming), StreamingBitrate.HIGH.getEntryValue()));
        if (streamingBitrateFromEntryValue != StreamingBitrate.KATANA || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return streamingBitrateFromEntryValue;
        }
        StreamingBitrate streamingBitrate = StreamingBitrate.AUTO;
        SettingsUtil.setMultiBitrateStreamingPreferenceForOTA(context, streamingBitrate.getEntryValue());
        return streamingBitrate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlayerSdkConfigData getPVPlayerSdkConfig() {
        AuthContextData authContextData = new AuthContextData(this.context);
        DeviceAttributesQuery deviceAttributesQuery = new DeviceAttributesQuery(this.context);
        String deviceTypeId = getDeviceTypeId(deviceAttributesQuery);
        return new PlayerSdkConfigData(this.context, authContextData, "q419yw5kcz2ra0", deviceAttributesQuery.fetchDeviceSerialNumber(), new NoOpPlaybackEnvelopeValidator(), deviceTypeId, 0, new AdConfigProvider() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper$$ExternalSyntheticLambda1
            @Override // com.amazon.video.sdk.AdConfigProvider
            public final AdConfig getAdConfig() {
                return PlaybackInitializationHelper.lambda$getPVPlayerSdkConfig$0();
            }
        }, new EPrivacyConsentDataProvider() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper$$ExternalSyntheticLambda2
            @Override // com.amazon.video.sdk.EPrivacyConsentDataProvider
            public final EPrivacyConsentData getEPrivacyConsentData() {
                return EPrivacyConsentData.EMPTY;
            }
        }, new LocalContentManagerConfigData(0L, null, TimeSpan.fromSeconds(5.0d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ AdConfig lambda$getPVPlayerSdkConfig$0() {
        return new AdConfigData("null", true);
    }

    private String getDeviceTypeId(final DeviceAttributesQuery deviceAttributesQuery) {
        try {
            return deviceAttributesQuery.fetchCentralDeviceType();
        } catch (DeviceDataStoreException e) {
            Log.warning(TAG, "Failed to fetch deviceTypeId from MAP.", e);
            return "A1MPSLFC7L5AFK";
        }
    }

    private static void setPlaybackConfigAction(final Context context, final PlaybackConfig config) {
        config.setClickAction(PendingIntentUtil.INSTANCE.getActivity(context, 0, new Intent(context, (Class<?>) PlaybackInitializationActivity.class), 0, true));
    }

    private void registerBootstrapSubjectListeners() {
        BootstrapSingletonTask.get().registerUserObserver().observeOn(Schedulers.newThread()).concatMap(new Func1<Void, Observable<?>>() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper.6
            @Override // rx.functions.Func1
            public Observable<?> call(final Void aVoid) {
                PlaybackInitializationHelper.this.updatePlaybackConfig();
                if (!Objects.equals(PlaybackInitializationHelper.this.context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
                    return CastingConnectionHandler.initChromecastCastContext(PlaybackInitializationHelper.this.context);
                }
                return Observable.just(null);
            }
        }).doOnError(new Action1<Throwable>() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper.5
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(PlaybackInitializationHelper.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
            }
        }).subscribe(new Action1<Object>() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper.3
            @Override // rx.functions.Action1
            public void call(final Object input) {
                PlaybackInitializationHelper.this.updateUserBenefits();
                MediaAccessPolicyHelper.updateMediaAccessPolicy(PlaybackInitializationHelper.this.context);
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(PlaybackInitializationHelper.this.context);
                Playback.getInstance().getPlaybackConfig().setBitRateSelectionForWifi(PlaybackInitializationHelper.getUserApplicableBitrateSelectedForWifi(PlaybackInitializationHelper.this.context, defaultSharedPreferences).convertToBitRateSelection());
                Playback.getInstance().getPlaybackConfig().setBitRateSelectionForOTA(PlaybackInitializationHelper.getUserApplicableBitrateSelectedForOTA(PlaybackInitializationHelper.this.context, defaultSharedPreferences).convertToBitRateSelection());
                CastingConnectionHandler.init(PlaybackInitializationHelper.this.context.getApplicationContext());
                boolean zIsVideosEnabled = AmazonApplication.getCapabilities().isVideosEnabled();
                if (zIsVideosEnabled) {
                    PrimeVideoStreamingSettingsProvider primeVideoStreamingSettingsProvider = new PrimeVideoStreamingSettingsProvider(PlaybackInitializationHelper.this.context);
                    StreamingConcurrencyStatusConnector streamingConcurrencyStatusConnector = StreamingConcurrencyStatusConnector.getInstance(PlaybackInitializationHelper.this.context);
                    PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
                    playbackConfig.setVideoStreamingSettingsProvider(primeVideoStreamingSettingsProvider);
                    playbackConfig.setVideoEnabled(zIsVideosEnabled);
                    playbackConfig.setPvPlayerSdkConfigData(PlaybackInitializationHelper.this.getPVPlayerSdkConfig());
                    playbackConfig.initializePrimeVideoPlayer();
                    playbackConfig.setMediaStreamingStatusProvider(streamingConcurrencyStatusConnector);
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper.4
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(PlaybackInitializationHelper.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaybackConfig() {
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        ClientIdentification clientIdentificationConstructClientIdentification = constructClientIdentification();
        if (clientIdentificationConstructClientIdentification == null) {
            if (playbackConfig.getClientIdentification() != null) {
                controller.stop(ChangeReason.SYSTEM_STOP);
            }
        } else {
            playbackConfig.setClientIdentification(clientIdentificationConstructClientIdentification);
            updatePlaybackConfigAuthenticationInfo(this.context);
        }
    }

    public static void updatePlaybackConfigAuthenticationInfo(Context context) {
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        try {
            AdpProviderMAPR5 adpProviderMAPR5 = new AdpProviderMAPR5(context.getApplicationContext());
            playbackConfig.setPlayerAuthentication(adpProviderMAPR5.getAdpInfo().adpToken, adpProviderMAPR5.getAdpInfo().privateKeyString);
            PlaybackPersistenceManager.getInstance().onHarleyReady();
        } catch (AuthenticationException e) {
            Log.error(TAG, "AuthenticationException in creating a new AdpProviderMAPR5, message:" + e.getMessage(), e);
        } catch (Exception e2) {
            Log.error(TAG, "Exception in playback initialization bootstrap, message: " + e2.getMessage(), e2);
        }
        Log.info(TAG, "Updated DMMPlayer with client and permissions information.");
    }

    private ClientIdentification constructClientIdentification() {
        String strName;
        try {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.context);
            String deviceId = accountCredentialStorage.getDeviceId();
            String deviceType = accountCredentialStorage.getDeviceType();
            String obfuscatedId = AccountDetailUtil.get(this.context).getHomeMarketPlace().getObfuscatedId();
            AccountDetailUtil.get(this.context);
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            String version = ApplicationAttributes.getInstance(this.context).getVersion();
            String customerId = AccountDetailUtil.get(this.context).getCustomerId();
            if (UserSubscriptionUtil.isNightwingOnly()) {
                strName = CustomerServiceTierEnum.NIGHTWING.name();
            } else {
                strName = (UserSubscriptionUtil.isHawkfireKatana() ? CustomerServiceTierEnum.UNLIMITED_HD : UserSubscriptionUtil.isHawkfire() ? CustomerServiceTierEnum.UNLIMITED : CustomerServiceTierEnum.PRIME).name();
            }
            return new ClientIdentification.Builder().setDeviceID(deviceId).setDeviceTypeID(deviceType).setClientID("Android").setAppVersion(version).setMarketplaceID(obfuscatedId).setTerritoryID(musicTerritoryOfResidence).setDeviceDescription(Build.MANUFACTURER + MqttTopic.SINGLE_LEVEL_WILDCARD + accountCredentialStorage.getDeviceType()).setDeviceCategory("Android").setOsVersion(Build.VERSION.RELEASE).setRequestInterceptor(new RequestInterceptorFactory(this.context).provideRequestInterceptor()).setCustomerID(customerId).setCustomerServiceTier(strName).build();
        } catch (Exception unused) {
            Log.warning(TAG, "Not enough information to create ClientIdentification.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkSystemSettingsAffectingPlayback() {
        if (Build.VERSION.SDK_INT < 28) {
            return true;
        }
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        if (!(currentActivity instanceof FragmentActivity)) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) this.context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (activityManager != null && activityManager.isBackgroundRestricted()) {
            FragmentManager supportFragmentManager = ((FragmentActivity) currentActivity).getSupportFragmentManager();
            Log.info(TAG, "Battery restrictions enabled - showing dialog to recommend turning it off.");
            new BackgroundRestrictionsDialogFragment().show(supportFragmentManager, "TAG_BACKGROUND_RESTRICTIONS_DIALOG");
        }
        return true;
    }

    private class OnPlayStateChangedListenerImpl implements OnPlayStateChangedListener {
        private BluetoothServiceListener mBluetoothListener;

        private OnPlayStateChangedListenerImpl() {
        }

        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            Playback playback = Playback.getInstance();
            ControlSource controlSource = ControlSource.APP_UI;
            boolean zWillPlay = playback.getPlaybackController(controlSource).getPlayStatus().willPlay();
            if (GhostListeningDialogFragment.shouldShow() && PlaybackControllerProvider.getController(controlSource).getPlayStatus().willPlay()) {
                GhostListeningDialogFragment.setShouldShow(false);
                GhostListeningMetricsProvider.getContinueListeningMetric(false).publish();
                Log.debug(PlaybackInitializationHelper.TAG, "Inactivity threshold handled from outside the ghost listening dialog.");
            }
            if (zWillPlay && !Objects.equals(PlaybackInitializationHelper.this.context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
                if (this.mBluetoothListener == null) {
                    BluetoothServiceListener bluetoothServiceListener = new BluetoothServiceListener(null);
                    this.mBluetoothListener = bluetoothServiceListener;
                    bluetoothServiceListener.init(PlaybackInitializationHelper.this.context);
                }
                this.mBluetoothListener.startListening();
                if (!PlaybackInitializationHelper.this.mHasCheckedSystemSettings) {
                    PlaybackInitializationHelper playbackInitializationHelper = PlaybackInitializationHelper.this;
                    playbackInitializationHelper.mHasCheckedSystemSettings = playbackInitializationHelper.checkSystemSettingsAffectingPlayback();
                }
            } else {
                BluetoothServiceListener bluetoothServiceListener2 = this.mBluetoothListener;
                if (bluetoothServiceListener2 != null) {
                    bluetoothServiceListener2.stopListening();
                }
            }
            if (CloudQueueSequencer.isCloudQueuePlaying()) {
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackInitializationHelper.OnPlayStateChangedListenerImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CloudQueuePlaybackUtil.INSTANCE.updateDevicePlaybackState(PlaybackInitializationHelper.this.context);
                    }
                });
            }
        }
    }
}
