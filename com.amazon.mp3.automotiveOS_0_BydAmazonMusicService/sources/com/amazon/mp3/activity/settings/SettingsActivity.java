package com.amazon.mp3.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.activity.AccountDeregistrationDialogActivity;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.activity.SignOutConfirmationDialog;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.credentials.MapAccount;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.AudioEffectActivity;
import com.amazon.mp3.activity.CantileverActivity;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.GrantStoragePermissionActivity;
import com.amazon.mp3.activity.LanguagePreferenceActivity;
import com.amazon.mp3.activity.URLDisplayActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.activity.settings.viewer.WidgetViewerActivity;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeConfigs;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeBrushViewModel;
import com.amazon.mp3.auto.waze.WazeSdkConnection;
import com.amazon.mp3.automaticimport.AutomaticImportManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bluetooth.BluetoothUtil;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.capability.DemoModeManager;
import com.amazon.mp3.capability.DevModeCapabilities;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.cookieconsent.CookieConsentManager;
import com.amazon.mp3.curate.provider.CurateEndpointSettingsProvider;
import com.amazon.mp3.dialog.EqualizerDialogBuilder;
import com.amazon.mp3.dialog.ExplicitFilterDialogBuilder;
import com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity;
import com.amazon.mp3.download.service.AutoDownloadPurchaseReceiver;
import com.amazon.mp3.elf.ExplicitLanguageFilterMetrics;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.CloudQueueAuthEndpointURLFactory;
import com.amazon.mp3.environment.url.ConfigurableURLs;
import com.amazon.mp3.environment.url.DMAutomotiveEndPointURLFactory;
import com.amazon.mp3.environment.url.DynMsgEndpointProvider;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.environment.url.MuseEndPointURLFactory;
import com.amazon.mp3.explore.ExploreEndpointOverrideFactory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.featuregate.PCAEvaluator;
import com.amazon.mp3.feed.FeedEndpointFactory;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.help.HelpUtil;
import com.amazon.mp3.init.ForegroundLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.language.preference.AppDisplayLanguageSetting;
import com.amazon.mp3.language.preference.LanguagePreferenceProvider;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.DbDiagnostic;
import com.amazon.mp3.library.provider.source.local.SDCardUtil;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.net.AdpToOAuthMigrationEvaluator;
import com.amazon.mp3.platform.providers.AppConfigProvider;
import com.amazon.mp3.platform.providers.WeblabProviderImpl;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.mp3.playback.service.AudioEffectController;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.upsell.nightwing.AdsPreferenceSettingsActivity;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.FirstTimeUseMetrics;
import com.amazon.mp3.service.metrics.IMetricsRecorder;
import com.amazon.mp3.service.metrics.LoginMetrics;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.timer.SleepTimeChoiceDialog;
import com.amazon.mp3.timer.SleepTimerService;
import com.amazon.mp3.upsellweb.CancelMembershipWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.ManageFamilyPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilder;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OtaUtil;
import com.amazon.mp3.util.ResourceUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.mp3.voice.wakeword.WakeWordHelper;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.account.User;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationPreference;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.featuregating.weblabs.WeblabDescriptor;
import com.amazon.music.languagepreference.LanguagePreference;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.android.StorageNotAvailableException;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.push.PushCampaignEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.Callback;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.runtimePreferences.RuntimePreferenceManager;
import com.amazon.music.subscription.UpsellStrategy;
import com.amazon.music.voice.VoiceManager;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastEndpointOverrideFactory;
import com.google.android.gms.stats.CodePackage;
import com.squareup.picasso.Picasso;
import com.waze.sdk.WazeAudioSdk;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class SettingsActivity extends PreferenceActivity implements ImportSettingDialogFragment.ConfirmActionProvider {
    private static final String SCROLL_TO_FLAG;
    private static final String TAG;
    private Subscription checkSDCardStatusSubscription;
    private ExplicitLanguageManager mExplicitLanguageManager;
    private View mFeatureBanner;
    private View mFeatureBannerDismissIcon;
    private View mFeatureBannerExitToAppIcon;
    private View mFeatureBannerExploreIcon;
    private TextView mFeatureBannerMessage;
    private boolean mIsAlexaEnabled;
    private String mLocale;
    private MetricsManager mMetricsManager;
    private boolean mRefreshClicked;
    private Subscription migrationSubscription;
    public static final int SETTINGS_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    public static final int DOWNLOAD_LOCATION_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final int STREAMING_NETWORK_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final int DEVICE_AUTHORIZATION_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private boolean mClearCacheReceiverRegistered = false;
    private int mEmailLoggingTapCounter = 0;
    private int mDeviceTagTapCounter = 0;
    private boolean mIsRedownloadRemoveAllEnabled = true;
    private NowPlayingManager mNowPlayingManager = NowPlayingManager.getInstance();
    private Integer scrollToPrefId = null;
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS)) {
                SettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SettingsActivity.this.updateSubscriptionManagementPreferences();
                    }
                });
            }
        }
    };
    private final SharedPreferences.OnSharedPreferenceChangeListener mPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.24
        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public void onSharedPreferenceChanged(SharedPreferences prefs, String s) {
            AccessibilityManager accessibilityManager;
            final SettingsActivity settingsActivity = SettingsActivity.this;
            boolean zEquals = s.equals(settingsActivity.getString(R.string.setting_key_cirrus_download_pref));
            int i = R.layout.action_toggleon;
            if (zEquals) {
                boolean z = prefs.getBoolean(SettingsActivity.this.getString(R.string.setting_key_cirrus_download_pref), false);
                SwitchPreference switchPreference = (SwitchPreference) SettingsActivity.this.findPreference(R.string.setting_key_cirrus_download_pref);
                if (switchPreference != null) {
                    if (!z) {
                        i = R.layout.action_toggleoff;
                    }
                    switchPreference.setWidgetLayoutResource(i);
                }
                if (!WebViewUtil.isBlueMoon(settingsActivity) || !z || DeviceAuthorizationManager.getInstance().isAuthorized()) {
                    SettingsActivity.this.setAutoDownload(z);
                    return;
                } else {
                    AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.24.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(settingsActivity);
                            if (deviceAuthorizationIntent != null) {
                                deviceAuthorizationIntent.putExtra("com.amazon.mp3.util.RETURN_TO_PREVIOUS", true);
                                SettingsActivity.this.startActivityForResult(deviceAuthorizationIntent, SettingsActivity.DEVICE_AUTHORIZATION_REQUEST_CODE);
                            }
                        }
                    });
                    return;
                }
            }
            if (s.equals(SettingsActivity.this.getString(R.string.setting_key_sleep_timer))) {
                SwitchPreference switchPreference2 = (SwitchPreference) SettingsActivity.this.findPreference(R.string.setting_key_sleep_timer);
                if (!Boolean.valueOf(SettingsUtil.isSleepTimerEnabled(SettingsActivity.this.getApplicationContext())).booleanValue()) {
                    i = R.layout.action_toggleoff;
                }
                switchPreference2.setWidgetLayoutResource(i);
                switchPreference2.setChecked(SettingsUtil.isSleepTimerEnabled(SettingsActivity.this.getApplicationContext()));
                return;
            }
            if (s.equals(SettingsActivity.this.getString(R.string.setting_key_wake_word_toggle))) {
                SwitchPreference switchPreference3 = (SwitchPreference) SettingsActivity.this.findPreference(R.string.setting_key_wake_word_toggle);
                if (switchPreference3 != null && switchPreference3.isChecked()) {
                    WakeWordHelper.turnOnWakeWordService(settingsActivity, true);
                    UserInteractionAppEvent.builder("handsfreeModeOn").publish();
                    switchPreference3.setWidgetLayoutResource(R.layout.action_toggleon);
                    return;
                } else {
                    WakeWordHelper.turnOffWakeWordServiceWithDelay(SettingsActivity.this.getApplicationContext());
                    UserInteractionAppEvent.builder("handsfreeModeOff").publish();
                    if (switchPreference3 != null) {
                        switchPreference3.setWidgetLayoutResource(R.layout.action_toggleoff);
                        return;
                    }
                    return;
                }
            }
            if (s.equals(SettingsActivity.this.getString(R.string.setting_key_dev_iam_endpoint))) {
                SettingsActivity.this.updateIAMPreference();
                return;
            }
            if (s.equals(SettingsActivity.this.getString(R.string.setting_key_parental_control_explicit_language))) {
                if (!AccessibilityUtil.isAccessibilityEnabled(SettingsActivity.this.getBaseContext()) || (accessibilityManager = (AccessibilityManager) SettingsActivity.this.getSystemService("accessibility")) == null) {
                    return;
                }
                accessibilityManager.interrupt();
                return;
            }
            if (s.equals(SettingsActivity.this.getString(R.string.setting_key_dev_cantilever_endpoint))) {
                SettingsActivity.this.updateCantileverEndpointPreference();
            }
        }
    };
    private final BroadcastReceiver mClearCacheFinishedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.26
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                SettingsActivity.this.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                Log.error(SettingsActivity.TAG, "Receiver not registered", e);
            }
            SyncService.startTimeCheckedSync(SettingsActivity.this.getApplicationContext());
        }
    };

    public static boolean isPicassoDebugOn(final Context context) {
        return false;
    }

    public static boolean shouldDisableBauhausPages(final Context context) {
        return false;
    }

    public static boolean shouldEnableFindLandingPageDevelopmentMode(final Context context) {
        return false;
    }

    public static boolean shouldUseArcusTestConfig(final Context context) {
        return false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return true;
    }

    static /* synthetic */ int access$508(SettingsActivity settingsActivity) {
        int i = settingsActivity.mEmailLoggingTapCounter;
        settingsActivity.mEmailLoggingTapCounter = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(SettingsActivity settingsActivity) {
        int i = settingsActivity.mDeviceTagTapCounter;
        settingsActivity.mDeviceTagTapCounter = i + 1;
        return i;
    }

    static {
        String simpleName = SettingsActivity.class.getSimpleName();
        TAG = simpleName;
        SCROLL_TO_FLAG = simpleName + "_SCROLL_TO";
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, SettingsActivity.class);
        intent.addFlags(131072);
        return intent;
    }

    public static Intent getStartAndScrollToIntent(final Context context, int pref) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, SettingsActivity.class);
        intent.addFlags(131072);
        intent.putExtra(SCROLL_TO_FLAG, pref);
        return intent;
    }

    public static void start(Activity parentActivity) {
        parentActivity.startActivityForResult(getStartIntent(parentActivity), 0);
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference instanceof SwitchPreference) {
            if (keyMatches(preference, R.string.setting_key_sleep_timer)) {
                handleSleepTimerClick(preference);
            }
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        Setting clickedSetting = SettingsUtil.getClickedSetting(preference, this);
        if (clickedSetting == null) {
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        switch (AnonymousClass40.$SwitchMap$com$amazon$mp3$activity$settings$Setting[clickedSetting.ordinal()]) {
            case 1:
                return handleClearCacheClick();
            case 2:
                return handleResyncClick();
            case 3:
                return handleSignOutClick();
            case 4:
                return handleManageSubscriptionClick();
            case 5:
                return handleManageFamilyClick();
            case 6:
                return handleCancelMembershipClick();
            case 7:
                return handleUpsellClick();
            case 8:
                return handleStreamingNetworkClick(StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO);
            case 9:
                return handleStreamingNetworkClick(StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO);
            case 10:
                return handleStreamingPreferenceClick();
            case 11:
                return handleDownloadPreferenceClick();
            case 12:
                return handleDownloadLocationClick();
            case 13:
                return downloadMissingSDCardContent();
            case 14:
                return removeMissingSDCardContent();
            case 15:
                return handleContactUsClick();
            case 16:
                return handleLegalInfoClick();
            case 17:
                return handleLegalInfoImprintClick();
            case 18:
                return handleExtractDBClick();
            case 19:
                return handleGetHelpClick();
            case 20:
                return handleManageCookiePreference();
            case 21:
                return handleLanguagePreferenceClick();
            case 22:
                return handleEqualizerClick();
            case 23:
                return showWidgetViewer();
            case 24:
                return handlePushTopicSelectionClick();
            case 25:
                return handleCarModeFabPreferenceClick();
            case 26:
                return AppDisplayLanguageSetting.INSTANCE.handleManageLanguageClick(this);
            case 27:
                nativeCrash();
                break;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void nativeCrash() {
        Log.error(TAG, "Invoking native crash");
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        if (playbackConfig != null) {
            Player localPlayer = playbackConfig.getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext()));
            if (localPlayer instanceof HarleyPlayer) {
                ((HarleyPlayer) localPlayer).nativeCrash();
            }
        }
    }

    private boolean removeMissingSDCardContent() {
        RemoveMissingSDCardFragment.show(this);
        return true;
    }

    private boolean downloadMissingSDCardContent() {
        DownloadMissingSDCardFragment.show(this);
        return true;
    }

    private boolean handleClearCacheClick() {
        startActivity(ClearCacheActivity.getStartIntent(this, -108809));
        if (!this.mClearCacheReceiverRegistered) {
            registerReceiver(this.mClearCacheFinishedReceiver, new IntentFilter("com.amazon.mp3.ACTION_CACHE_CLEARED"));
            this.mClearCacheReceiverRegistered = true;
        }
        performDbDiagnosticAsync();
        return true;
    }

    private void performDbDiagnosticAsync() {
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (new DbDiagnostic(CirrusDatabase.getWritableDatabase(SettingsActivity.this.getApplicationContext())).performDiagnosticAndFixFoundIssues()) {
                    Log.debug(SettingsActivity.TAG, "DB diagnostic is completed.");
                }
            }
        });
    }

    private boolean handleResyncClick() {
        int i;
        if (this.mRefreshClicked) {
            i = 63147;
        } else {
            i = 63163;
            this.mRefreshClicked = true;
        }
        SyncService.startSync(this, i);
        BauhausToastUtils.showTextToast(this, R.string.dmusic_setting_resync_toast, 0);
        Curate.requestLibrarySync();
        return false;
    }

    private void handleSleepTimerClick(final Preference preference) {
        SwitchPreference switchPreference = (SwitchPreference) preference;
        NavigationAppEvent.builder("settingsSleepTime").withOrientation(ScreenUtil.getScreenOrientation(this)).publish();
        switchPreference.setWidgetLayoutResource(switchPreference.isChecked() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        if (switchPreference.isChecked()) {
            new SleepTimeChoiceDialog(this).show();
        } else {
            SleepTimerService.stopTimer(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleSignOutClick() {
        if (TextUtils.isEmpty(getAccountId())) {
            IMetricsRecorder metricsRecorder = BaseMetricsRecorder.getMetricsRecorder(getApplicationContext());
            metricsRecorder.getFirstTimeUseMetrics().recordFirstSignInAttempt(FirstTimeUseMetrics.Source.DEVICE);
            metricsRecorder.getLoginMetrics().recordLoginAttempt(LoginMetrics.Source.DEVICE);
            startActivityForResult(new Intent(this, (Class<?>) SignInFlowLauncherActivity.class), SignInFlowLauncherActivity.REQUEST_CODE);
            return true;
        }
        new SignOutConfirmationDialog(this).show();
        return true;
    }

    private boolean handleManageSubscriptionClick() {
        SettingsPageWebTargetBuilder settingsPageWebTargetBuilderNewBuilder;
        UserInteractionAppEvent.builder("ManageAccount").publish();
        NavigationAppEvent.builder("ManageAccount").publish();
        Context applicationContext = getApplicationContext();
        if (SubscriptionManagerSingleton.isInitialized() && SubscriptionManagerSingleton.getInstance().isOnGoogleInAppBillingPlan()) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/account/subscriptions")));
            return false;
        }
        if (new AccountInfo(applicationContext).getUserSubscription().isSonicRush() && !UserSubscriptionUtil.isHawkfire() && AmazonApplication.getCapabilities().isGPBLaunchedInTerritory()) {
            settingsPageWebTargetBuilderNewBuilder = new SettingsPageWebTargetBuilderFactory(applicationContext).newPlanComparisonViewBuilder();
        } else {
            settingsPageWebTargetBuilderNewBuilder = new SettingsPageWebTargetBuilderFactory(applicationContext).newBuilder();
        }
        UnlimitedUpsellActivity.start(this, settingsPageWebTargetBuilderNewBuilder.build());
        return false;
    }

    private boolean handleManageFamilyClick() {
        UserInteractionAppEvent.builder("ManageFamily").publish();
        NavigationAppEvent.builder("ManageFamily").publish();
        UnlimitedUpsellActivity.start(this, new ManageFamilyPageWebTargetBuilderFactory(getApplicationContext()).newBuilder().build());
        return false;
    }

    private boolean handleCancelMembershipClick() {
        UserInteractionAppEvent.builder("CancelMembership").publish();
        NavigationAppEvent.builder("CancelMembership").publish();
        UnlimitedUpsellActivity.start(this, new CancelMembershipWebTargetBuilderFactory(getApplicationContext()).newBuilder().build());
        return false;
    }

    private boolean handleUpsellClick() {
        UserInteractionAppEvent.builder("subSettingsUpsell").withPageType("SETTINGS").publish();
        Context applicationContext = getApplicationContext();
        if (AmazonApplication.getCapabilities().shouldUseDynamicMessagingForAdsSettingsOnDemandListeningUpsell()) {
            startActivity(new Intent(this, (Class<?>) SettingsDynamicUpsellFragmentActivity.class));
            return false;
        }
        UnlimitedUpsellActivity.start(this, new OfferPageWebTargetBuilderFactory(applicationContext).newBuilder().build());
        return false;
    }

    private boolean handleStreamingNetworkClick(final StreamingNetworkPreferenceActivity.Type type) {
        Intent intent = new Intent(this, (Class<?>) StreamingNetworkPreferenceActivity.class);
        intent.putExtra("STREAMING_NETWORK_PREFERENCE_TYPE", type);
        startActivityForResult(intent, STREAMING_NETWORK_REQUEST_CODE);
        return true;
    }

    private boolean handleStreamingPreferenceClick() {
        WideDialogActivity.showActivity(this, WideDialogActivityPurpose.SHOW_KATANA_AUDIO_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog);
        UserInteractionAppEvent.builder("goAudioQualitySettings").publish();
        return true;
    }

    private boolean handleDownloadPreferenceClick() {
        WideDialogActivity.showActivity(this, WideDialogActivityPurpose.SHOW_KATANA_DOWNLOAD_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog);
        UserInteractionAppEvent.builder("goDownloadSettings").publish();
        return true;
    }

    private boolean handleCarModeFabPreferenceClick() {
        WideDialogActivity.showActivity(this, WideDialogActivityPurpose.SHOW_CAR_MODE_FAB_PREFERENCES, R.style.Theme_AmazonMP3_Dialog);
        AutoUiEventUtil.sendUiClickEvent(ActionType.GO_CAR_MODE_SETTINGS, PageType.SETTINGS);
        return true;
    }

    private boolean handleDownloadLocationClick() {
        startActivityForResult(new Intent(this, (Class<?>) DownloadLocationPreferenceActivity.class), DOWNLOAD_LOCATION_CODE);
        return false;
    }

    private boolean handleContactUsClick() {
        return onContactUs();
    }

    private boolean handleLegalInfoClick() {
        startActivity(new Intent(this, (Class<?>) LegalInformationActivity.class));
        return false;
    }

    private boolean handleLegalInfoImprintClick() {
        String legalImprintUrl = getLegalImprintUrl();
        if (TextUtils.isEmpty(legalImprintUrl)) {
            Log.error(TAG, "No legal URI returned by Configuration! Check home market place and device language.");
            return false;
        }
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(legalImprintUrl)));
        return false;
    }

    private boolean handleExtractDBClick() throws Throwable {
        dumpProtectedFiles();
        return false;
    }

    private boolean handleGetHelpClick() {
        Intent helpIntent = HelpUtil.getHelpIntent(getApplicationContext(), HelpUtil.HelpType.LIBRARY);
        if (helpIntent == null || helpIntent.resolveActivity(getPackageManager()) == null) {
            Log.error(TAG, "No activity available to open Help page. Please ensure a web browse is installed and enabled.");
            return false;
        }
        startActivity(helpIntent);
        return false;
    }

    private boolean handleManageCookiePreference() {
        URL value = ConfigurableURLs.COOKIE_CONSENT_PREFERENCE_URL.getValue();
        if (value != null) {
            startActivity(URLDisplayActivity.getStartIntent(getApplicationContext(), value.toString()));
            return false;
        }
        Log.error(TAG, "URL no found for COOKIE_CONSENT_PREFERENCE_URL.");
        return false;
    }

    private boolean handleLanguagePreferenceClick() {
        showLanguagePreference();
        return false;
    }

    private boolean handleEqualizerClick() {
        if (CastingUtil.isCasting()) {
            new EqualizerDialogBuilder(this).getUnavailableWhileCastingDialog().show();
        } else {
            startActivity(new Intent(getApplicationContext(), (Class<?>) AudioEffectActivity.class));
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_SETTINGS_EQUALIZER).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        return false;
    }

    private boolean handlePushTopicSelectionClick() {
        MetricsHolder.getManager().handleEvent(PushCampaignEvent.builder("NOTIFICATION_SETTINGS_DIALOG_OPENED", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("appInstallid", ""), "", "sonar", "regular").build());
        WideDialogActivity.showActivity(this, WideDialogActivityPurpose.SHOW_PUSH_TOPIC_SELECTION, R.style.Theme_AmazonMP3_Dialog);
        return true;
    }

    private boolean showWidgetViewer() {
        startActivity(new Intent(getApplicationContext(), (Class<?>) WidgetViewerActivity.class));
        return false;
    }

    private void setUpWakeWordPreference() {
        if (!AmazonApplication.getCapabilities().canAccessWakeWord()) {
            PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_alexa_section);
            if (preferenceCategoryFindPreferenceCategory == null) {
                return;
            }
            getPreferenceScreen().removePreference(preferenceCategoryFindPreferenceCategory);
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_wake_word_toggle);
        if (switchPreference == null) {
            Log.warning(TAG, "Wakeword preference is null. Failing to initialize the toggle to the previously saved value.");
            return;
        }
        boolean zIsWakeWordUserSettingEnabled = SettingsUtil.isWakeWordUserSettingEnabled(getApplicationContext());
        switchPreference.setChecked(zIsWakeWordUserSettingEnabled);
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(zIsWakeWordUserSettingEnabled).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        if (CastingUtil.isCastingToAlexa()) {
            switchPreference.setEnabled(false);
            switchPreference.setSummary(getString(R.string.casting_unavailable_setting_text, CastingUtil.getCastingDeviceName(getApplicationContext())));
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        int intExtra;
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        new InitializationHandler(new ForegroundLaunchInitializationTask(this, new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(this)))).initializeOnNewThread();
        addPreferencesFromResource(R.xml.common_settings_preferences);
        setContentView(R.layout.common_settings_activity);
        View viewFindViewById = findViewById(R.id.feature_banner);
        this.mFeatureBanner = viewFindViewById;
        if (viewFindViewById != null) {
            TextView textView = (TextView) viewFindViewById.findViewById(R.id.feature_banner_message);
            this.mFeatureBannerMessage = textView;
            textView.setGravity(8388627);
            this.mFeatureBannerExitToAppIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_icon);
            this.mFeatureBannerDismissIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_icon_dismiss);
            this.mFeatureBannerExploreIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_view);
        }
        if (savedInstanceState != null) {
            this.mLocale = savedInstanceState.getString("com.amazon.mp3.STATE_LOCALE");
        }
        this.mExplicitLanguageManager = new ExplicitLanguageManager(getApplicationContext());
        this.mMetricsManager = MetricsHolder.getManager();
        NavigationAppEvent.builder("settings").withOrientation(ScreenUtil.getScreenOrientation(this)).publish();
        Intent intent = getIntent();
        if (intent != null && (intExtra = intent.getIntExtra(SCROLL_TO_FLAG, -1)) != -1) {
            this.scrollToPrefId = Integer.valueOf(intExtra);
        }
        PreferenceManager.setDefaultValues(this, R.xml.common_settings_preferences, false);
        initialize();
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ListView listView = (ListView) findViewById(android.R.id.list);
        if (listView == null || listView.getParent() == null || listView.getParent().getParent() == null || listView.getParent().getParent().getParent() == null) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) listView.getParent().getParent().getParent();
        Toolbar toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, (ViewGroup) linearLayout, false);
        linearLayout.addView(toolbar, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SettingsActivity.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        SettingsUtil.getPrefs(this).registerOnSharedPreferenceChangeListener(this.mPreferenceChangeListener);
        updateCirrusPreferences();
        updateSignInOutPreference();
        updateSubscriptionManagementPreferences();
        updateDownloadPreferences();
        updateStoragePreferenceCategory();
        updateStorePreference();
        initializeAlexa();
        updateIAMPreference();
        updateKatanaStreamingPreference();
        updateCantileverEndpointPreference();
        syncUIWithWakeWordPreference();
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized() && !isChangingConfigurations() && VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
            VoiceManagerSingleton.getInstance().softCloseAlexaUi();
        }
        SettingsUtil.getPrefs(this).unregisterOnSharedPreferenceChangeListener(this.mPreferenceChangeListener);
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    public void onDestroy() {
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        Subscription subscription = this.migrationSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.migrationSubscription.unsubscribe();
            this.migrationSubscription = null;
        }
        Subscription subscription2 = this.checkSDCardStatusSubscription;
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            this.checkSDCardStatusSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putString("com.amazon.mp3.STATE_LOCALE", getResources().getConfiguration().locale.toString());
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized()) {
            VoiceManager voiceManagerSingleton = VoiceManagerSingleton.getInstance();
            if (voiceManagerSingleton.isAlexaUiVisible()) {
                voiceManagerSingleton.onSaveInstanceState();
            }
        }
        super.onSaveInstanceState(outState);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized() && VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
            VoiceManagerSingleton.getInstance().closeAlexaUi();
        } else {
            setResult(-1);
            super.onBackPressed();
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IMetricsRecorder metricsRecorder = BaseMetricsRecorder.getMetricsRecorder(getApplicationContext());
        if (resultCode == 0 && requestCode == SignInFlowLauncherActivity.REQUEST_CODE) {
            setResult(3);
            finish();
            return;
        }
        int i = SignInFlowLauncherActivity.REQUEST_CODE;
        if (requestCode == i && AccountCredentialUtil.get(this).isSignedIn()) {
            metricsRecorder.getRecurrentUseMetrics().recordUserSignedIn();
            metricsRecorder.getFirstTimeUseMetrics().recordFirstSuccessfulSignIn(FirstTimeUseMetrics.Source.DEVICE);
        } else if (requestCode == AccountDeregistrationDialogActivity.REQUEST_CODE && AccountCredentialUtil.get(this).isSignedOut()) {
            metricsRecorder.getRecurrentUseMetrics().recordUserSignedOut();
        }
        if (requestCode == i || requestCode == AccountDeregistrationDialogActivity.REQUEST_CODE) {
            updateSignInOutPreference();
            updateSubscriptionManagementPreferences();
            updateCirrusPreferences();
            updateKatanaStreamingPreference();
            if (TextUtils.isEmpty(getAccountId())) {
                metricsRecorder.getFirstTimeUseMetrics().recordFirstSignInAttempt(FirstTimeUseMetrics.Source.DEVICE);
                metricsRecorder.getLoginMetrics().recordLoginAttempt(LoginMetrics.Source.DEVICE);
                startActivityForResult(new Intent(this, (Class<?>) SignInFlowLauncherActivity.class), i);
                return;
            } else {
                setResult(-1);
                finish();
                return;
            }
        }
        if (requestCode == STREAMING_NETWORK_REQUEST_CODE) {
            updateCirrusPreferences();
        } else if (requestCode == DEVICE_AUTHORIZATION_REQUEST_CODE) {
            setAutoDownload(DeviceAuthorizationManager.getInstance().isAuthorized());
        } else if (requestCode == DOWNLOAD_LOCATION_CODE) {
            updateDownloadPreferences();
        }
    }

    protected void initializeAlexa() {
        boolean zIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
        this.mIsAlexaEnabled = zIsAlexaEnabled;
        if (zIsAlexaEnabled) {
            if (!VoiceManagerSingleton.isInitialized() || VoiceManagerFactory.shouldRefresh()) {
                VoiceManagerFactory.initializeVoiceManager(getApplicationContext());
            }
            if (VoiceManagerSingleton.getInstance().isInitRequired()) {
                VoiceManagerSingleton.getInstance().handleReopenMusicApp(getApplicationContext());
            }
            VoiceManagerSingleton.getInstance().attachView(this, (ViewGroup) ((ListView) findViewById(android.R.id.list)).getRootView());
            WakeWordHelper.resumeWakeWordService(this, true);
        }
    }

    private boolean onContactUs() {
        boolean z = !AmazonApplication.BETA;
        boolean booleanPref = SettingsUtil.getBooleanPref(getApplicationContext(), R.string.setting_key_dev_debug_contact_us_pref);
        if ((z && !Log.getNonCriticalLoggingEnabled()) || booleanPref) {
            startActivity(new Intent(this, (Class<?>) CantileverActivity.class));
        } else {
            Intent intent = new Intent(this, (Class<?>) CantileverActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.amazon.mp3.extra.CantileverActivity.EXTRA_LAUNCH_FEEDBACK_FORM", true);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Preference findPreference(int id) {
        return getPreferenceScreen().findPreference(getString(id));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PreferenceCategory findPreferenceCategory(int id) {
        return (PreferenceCategory) findPreference(getString(id));
    }

    private void showLanguagePreference() {
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            startActivityForResult(LanguagePreferenceActivity.getStartIntent(this), LanguagePreferenceActivity.REQUEST_CODE);
            return;
        }
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_no_connection_title);
        bauhausDialogBuilder.setMessage(ResourceUtil.getNoConnectionStringId());
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        bauhausDialogBuilder.create().show();
    }

    private void initialize() {
        View view;
        PreferenceCategory preferenceCategoryFindPreferenceCategory;
        PreferenceCategory preferenceCategoryFindPreferenceCategory2;
        Preference preferenceFindPreference;
        PreferenceCategory preferenceCategoryFindPreferenceCategory3;
        PreferenceCategory preferenceCategoryFindPreferenceCategory4;
        Preference preferenceFindPreference2;
        updateBuildPreference();
        updateResyncPreference();
        updateDeviceIdPreference();
        updateSignInOutPreference();
        setupCirrusPreferences();
        updateKatanaStreamingPreference();
        setupKatanaDownloadPreference();
        setupExplicitFilterPreference();
        setupLoudnessPreference();
        setupDeveloperPreferences();
        setRuntimeDownloadPreferences();
        updateDownloadPreferences();
        setUpWakeWordPreference();
        setUpBehavioralAdsPreference();
        setUpEqualizerPreference();
        setupWazeConnectionPreference();
        setupCarModePreferences();
        setupImportScanPreference();
        setupStorePreference();
        setupSpatialModePreference();
        setupPrioritizeDownloadsPreference();
        setupAppDisplayLanguagePreference();
        setupPodcastTranscriptsPreference();
        if (SettingsUtil.shouldShowRedownloadUIUntilAcknowledged(getApplicationContext())) {
            Log.debug(TAG, "Should show Redownload UI unless already shown and user already acknowledged, as per shared preference.");
            showRedownloadDialogAndRibbon();
        } else if (SettingsUtil.getBooleanPref(this, R.string.setting_key_redownload_ribbon_dismissed) && (view = this.mFeatureBanner) != null) {
            view.setVisibility(8);
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (isLoggingPreferenceDisabled() && (preferenceFindPreference2 = preferenceScreen.findPreference(getString(R.string.setting_key_category_logging))) != null) {
            getPreferenceScreen().removePreference(preferenceFindPreference2);
        }
        if (!AmazonApplication.getCapabilities().isStoreSupported() && (preferenceCategoryFindPreferenceCategory4 = findPreferenceCategory(R.string.setting_key_category_playback)) != null) {
            removePreference(preferenceCategoryFindPreferenceCategory4, R.string.setting_key_cirrus_download_pref);
        }
        boolean z = false;
        if (!((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.music_never_stops) && !PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("isAutoPlaySfaCustomer", false)) {
            removeSfaSetting();
        }
        setupSfaToggleListener();
        if (ConnectivityUtil.isWifiOnlyDevice() && (preferenceCategoryFindPreferenceCategory3 = findPreferenceCategory(R.string.setting_key_category_playback)) != null) {
            removePreference(preferenceCategoryFindPreferenceCategory3, R.string.setting_key_audio_streaming_network_pref);
            removePreference(preferenceCategoryFindPreferenceCategory3, R.string.setting_key_video_streaming_network_pref);
        }
        if (!AmazonApplication.getCapabilities().secondaryStoragePresent(this) && (preferenceFindPreference = findPreference(R.string.setting_key_download_location_pref)) != null) {
            preferenceFindPreference.setEnabled(false);
        }
        LanguagePreference languagePreferenceBuildLanguagePreference = LanguagePreferenceProvider.buildLanguagePreference(this);
        if (languagePreferenceBuildLanguagePreference != null && !languagePreferenceBuildLanguagePreference.isAvailableInSettings(FeatureFlagsProvider.getFeatureFlagProvider())) {
            PreferenceCategory preferenceCategoryFindPreferenceCategory5 = findPreferenceCategory(R.string.setting_key_category_your_account);
            Preference preferenceFindPreference3 = findPreference(R.string.setting_key_language_preference);
            if (preferenceCategoryFindPreferenceCategory5 != null && preferenceFindPreference3 != null) {
                preferenceCategoryFindPreferenceCategory5.removePreference(preferenceFindPreference3);
            }
        }
        PreferenceCategory preferenceCategoryFindPreferenceCategory6 = findPreferenceCategory(R.string.setting_key_category_info);
        if (preferenceCategoryFindPreferenceCategory6 != null) {
            preferenceCategoryFindPreferenceCategory6.findPreference(getString(R.string.setting_key_contact_us)).setTitle(R.string.dmusic_setting_help_and_feedback_title);
        }
        if (Log.getNonCriticalLoggingEnabled() && !SettingsUtil.getBooleanPref(getApplicationContext(), R.string.setting_key_dev_debug_contact_us_pref) && (preferenceCategoryFindPreferenceCategory2 = findPreferenceCategory(R.string.setting_key_category_info)) != null) {
            preferenceCategoryFindPreferenceCategory2.findPreference(getString(R.string.setting_key_contact_us)).setTitle(R.string.dmusic_logging_email_logfile_title);
        }
        if (DemoModeManager.get(getApplicationContext()).isDemoMode() && (preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_info)) != null) {
            removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_contact_us);
        }
        if (!shouldShowBehavioralAdsPreference()) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_info), R.string.setting_key_ad_prefs);
        }
        removeInapplicableCategories();
        removeInapplicableCategoriesForChildUser();
        if (TextUtils.isEmpty(getLegalImprintUrl())) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_info), R.string.setting_key_legal_info_imprint);
        }
        CookieConsentManager cookieConsentManager = CookieConsentManager.instance;
        if (!cookieConsentManager.isCookieConsentFlowSupported(false) && !cookieConsentManager.isCookieConsentFlowSupported(true)) {
            z = true;
        }
        if (z) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_info), R.string.setting_key_manage_cookie_preferences);
        }
    }

    private void setupAppDisplayLanguagePreference() {
        if (!((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.app_display_language)) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_your_account), R.string.setting_app_display_language);
            return;
        }
        Preference preferenceFindPreference = findPreference(getString(R.string.setting_app_display_language));
        String displayLanguage = AppDisplayLanguageSetting.INSTANCE.getDisplayLanguage(getApplicationContext());
        if (EnumUtils.isValidEnum(AppLocales.class, displayLanguage)) {
            preferenceFindPreference.setSummary(AppLocales.valueOf(displayLanguage).getEntryResourceId());
        }
    }

    private void removeInapplicableCategoriesForChildUser() {
        if (ChildUserUtil.INSTANCE.isChildUser(getApplicationContext())) {
            PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_info);
            if (preferenceCategoryFindPreferenceCategory != null) {
                removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_contact_us);
                removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_legal_info);
                removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_get_help);
                removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_enable_store);
                removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_import);
            }
            getPreferenceScreen().removePreference(findPreferenceCategory(R.string.setting_key_category_your_account));
            if (findPreferenceCategory(R.string.setting_key_category_playback) != null) {
                SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_parental_control_explicit_language);
                switchPreference.setEnabled(false);
                switchPreference.setChecked(true);
            }
        }
    }

    private void removeInapplicableCategories() {
        if (AccountRequestUtil.isAccountValid(this)) {
            return;
        }
        getPreferenceScreen().removePreference(findPreferenceCategory(R.string.setting_key_category_your_account));
        getPreferenceScreen().removePreference(findPreferenceCategory(R.string.setting_key_category_playback));
        getPreferenceScreen().removePreference(findPreferenceCategory(R.string.setting_key_category_library));
    }

    private void removeSfaSetting() {
        PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_playback);
        if (preferenceCategoryFindPreferenceCategory != null) {
            removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_sfa);
        }
    }

    private void setUpEqualizerPreference() {
        if (!AudioEffectController.isAudioEffectsPageSupported()) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_playback), R.string.setting_key_equalizer);
        } else if (CastingUtil.isCasting()) {
            findPreference(R.string.setting_key_equalizer).setLayoutResource(R.layout.setting_preference_disable);
        }
    }

    private void setupStorePreference() {
        SwitchPreference switchPreference = (SwitchPreference) findPreferenceCategory(R.string.setting_key_category_info).findPreference(getString(R.string.setting_key_enable_store));
        if (switchPreference == null) {
            return;
        }
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.4
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object o) {
                preference.setWidgetLayoutResource(((Boolean) o).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                return true;
            }
        });
    }

    private void setupImportScanPreference() {
        final SwitchPreference switchPreference = (SwitchPreference) findPreferenceCategory(R.string.setting_key_category_info).findPreference(getString(R.string.setting_import));
        if (switchPreference == null) {
            return;
        }
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.5
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object o) {
                ImportSettingDialogFragment importSettingDialogFragmentNewInstance;
                Log.debug(SettingsActivity.TAG, "Import preference changed");
                preference.setWidgetLayoutResource(((Boolean) o).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                if (o.toString().equals("true")) {
                    importSettingDialogFragmentNewInstance = ImportSettingDialogFragment.INSTANCE.newInstance(SettingsActivity.this.getString(R.string.enable_automatic_import_title), SettingsActivity.this.getString(R.string.enable_automatic_import_description), SettingsActivity.this.getString(R.string.enable), SettingsActivity.this.getResources().getColor(R.color.black), SettingsActivity.this.getResources().getColor(R.color.prime_blue), SettingsActivity.this.getAutoImportEnabledAction(switchPreference));
                } else {
                    importSettingDialogFragmentNewInstance = ImportSettingDialogFragment.INSTANCE.newInstance(SettingsActivity.this.getString(R.string.disable_automatic_import_title), SettingsActivity.this.getString(R.string.disable_automatic_import_description), SettingsActivity.this.getString(R.string.dmusic_button_yes), SettingsActivity.this.getResources().getColor(R.color.white), SettingsActivity.this.getResources().getColor(R.color.red), SettingsActivity.this.getAutoImportDisabledAction(switchPreference));
                }
                importSettingDialogFragmentNewInstance.show(SettingsActivity.this.getFragmentManager().beginTransaction(), ImportSettingDialogFragment.class.getSimpleName());
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImportSettingDialogFragment.ConfirmAction getAutoImportEnabledAction(final SwitchPreference pref) {
        return new ImportSettingDialogFragment.ConfirmAction() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.6
            @Override // com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment.ConfirmAction
            public void execute() {
                Log.info(SettingsActivity.TAG, "Automatic Import Enabled.");
                pref.setChecked(true);
                MetricsLogger.sendEvent(FlexEvent.builder("autoImportEnabled").build());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImportSettingDialogFragment.ConfirmAction getAutoImportDisabledAction(final SwitchPreference pref) {
        return new ImportSettingDialogFragment.ConfirmAction() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.7
            @Override // com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment.ConfirmAction
            public void execute() {
                Log.info(SettingsActivity.TAG, "Automatic Import Disabled.");
                pref.setChecked(false);
                MetricsLogger.sendEvent(FlexEvent.builder("autoImportDisabled").build());
                new AutomaticImportManager(SettingsActivity.this).removeImportedContent(AmazonApplication.getBackgroundHandler());
                SettingsActivity settingsActivity = SettingsActivity.this;
                BauhausToastUtils.showTextToast(settingsActivity, settingsActivity.getString(R.string.disable_automatic_import_toast), 0);
            }
        };
    }

    @Override // com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment.ConfirmActionProvider
    public ImportSettingDialogFragment.ConfirmAction getConfirmAction() {
        SwitchPreference switchPreference = (SwitchPreference) findPreferenceCategory(R.string.setting_key_category_info).findPreference(getString(R.string.setting_import));
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        if (switchPreference.isChecked()) {
            return getAutoImportDisabledAction(switchPreference);
        }
        return getAutoImportEnabledAction(switchPreference);
    }

    private boolean shouldShowBehavioralAdsPreference() {
        try {
            if (FeatureFlagsProvider.getFeatureFlagProvider().isBehavioralAdsNotificationSupported()) {
                if (UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
                    return true;
                }
            }
            return false;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBehavioralAdsNotificationSupported() && UserSubscriptionUtil.getUserSubscription().isNightwingOnly();
        }
    }

    private void setUpBehavioralAdsPreference() {
        Preference preferenceFindPreference = findPreference(R.string.setting_key_ad_prefs);
        final Intent intent = new Intent(this, (Class<?>) AdsPreferenceSettingsActivity.class);
        preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.8
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(final Preference preference) {
                SettingsActivity.this.startActivity(intent);
                return false;
            }
        });
    }

    private void setupSfaToggleListener() {
        final SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_sfa);
        if (switchPreference == null) {
            return;
        }
        switchPreference.setWidgetLayoutResource(switchPreference.isChecked() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.9
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object o) {
                Log.debug(SettingsActivity.TAG, "Stations From Anywhere setting preference change.");
                switchPreference.setWidgetLayoutResource(((Boolean) o).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                if (o.toString().equals("true")) {
                    SettingsActivity.this.sendClickEvent(ActionType.SELECT_SFA_PREFERENCE_ON);
                    return true;
                }
                SettingsActivity.this.sendClickEvent(ActionType.SELECT_SFA_PREFERENCE_OFF);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendClickEvent(final ActionType actionType) {
        if (this.mMetricsManager != null) {
            this.mMetricsManager.handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).build());
        }
    }

    private void setRuntimeDownloadPreferences() {
        PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_playback);
        if (preferenceCategoryFindPreferenceCategory != null) {
            new RuntimePreferenceManager().loadPreferencesForCategory("DOWNLOAD", preferenceCategoryFindPreferenceCategory);
        }
    }

    private void removePreference(PreferenceCategory category, int stringKeyId) {
        Preference preferenceFindPreference = findPreference(getString(stringKeyId));
        if (preferenceFindPreference != null) {
            category.removePreference(preferenceFindPreference);
        }
    }

    private boolean isLoggingPreferenceDisabled() {
        return !Log.getNonCriticalLoggingEnabled();
    }

    private void setupCirrusPreferences() {
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            getPreferenceScreen().removePreference(findPreference(R.string.setting_key_resync));
            PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_playback);
            preferenceCategoryFindPreferenceCategory.removePreference(findPreference(R.string.setting_key_audio_streaming_network_pref));
            preferenceCategoryFindPreferenceCategory.removePreference(findPreference(R.string.setting_key_video_streaming_network_pref));
            preferenceCategoryFindPreferenceCategory.removePreference(findPreference(R.string.setting_key_cirrus_download_pref));
            return;
        }
        updateCirrusPreferences();
    }

    private void updateBuildPreference() {
        Preference preferenceFindPreference = findPreference(R.string.setting_key_build_number);
        if (preferenceFindPreference != null) {
            ApplicationAttributes applicationAttributes = ApplicationAttributes.getInstance(this);
            preferenceFindPreference.setTitle(getString(R.string.dmusic_setting_build_title));
            Object[] objArr = new Object[3];
            objArr[0] = applicationAttributes.getVersion();
            objArr[1] = OtaUtil.isEnabled() ? CodePackage.OTA : "No OTA";
            objArr[2] = Integer.valueOf(applicationAttributes.getVersionCode() % DurationKt.NANOS_IN_MILLIS);
            preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_build_summary, objArr));
            if (Log.getNonCriticalLoggingEnabled()) {
                return;
            }
            preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.10
                @Override // android.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(final Preference preference) {
                    SettingsActivity.access$508(SettingsActivity.this);
                    if (SettingsActivity.this.mEmailLoggingTapCounter != 5) {
                        return false;
                    }
                    BauhausToastUtils.showTextToast(SettingsActivity.this, R.string.dmusic_setting_enable_email_logging, 1);
                    Log.setNonCriticalLoggingEnabled(true);
                    PlaylistUtil.logPlaylistDetails(SettingsActivity.this.getContentResolver());
                    PreferenceCategory preferenceCategoryFindPreferenceCategory = SettingsActivity.this.findPreferenceCategory(R.string.setting_key_category_info);
                    if (preferenceCategoryFindPreferenceCategory == null || SettingsUtil.getBooleanPref(SettingsActivity.this.getApplicationContext(), R.string.setting_key_dev_debug_contact_us_pref)) {
                        return false;
                    }
                    preferenceCategoryFindPreferenceCategory.findPreference(SettingsActivity.this.getString(R.string.setting_key_contact_us)).setTitle(R.string.dmusic_logging_email_logfile_title);
                    return false;
                }
            });
        }
    }

    private void updateResyncPreference() {
        Preference preferenceFindPreference = findPreference(R.string.setting_key_resync);
        if (preferenceFindPreference != null) {
            long lastUpdateTime = ApplicationAttributes.getInstance(this).getLastUpdateTime();
            if (lastUpdateTime != 0) {
                preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_build, DateFormat.getDateInstance().format(new Date(lastUpdateTime))));
            }
        }
    }

    private void updateDeviceIdPreference() {
        final Preference preferenceFindPreference = findPreference(R.string.setting_key_device_id);
        if (preferenceFindPreference != null) {
            final AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this);
            preferenceFindPreference.setSummary(accountCredentialStorage.getDeviceId());
            preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.11
                @Override // android.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(final Preference preference) {
                    SettingsActivity.access$708(SettingsActivity.this);
                    if (SettingsActivity.this.mDeviceTagTapCounter == 5) {
                        Preference preference2 = preferenceFindPreference;
                        StringBuilder sb = new StringBuilder();
                        sb.append(accountCredentialStorage.getDeviceId());
                        SettingsActivity settingsActivity = SettingsActivity.this;
                        sb.append(settingsActivity.getString(R.string.dmusic_setting_device_tag, AssociateTag.getInstance(settingsActivity).getAssociateTag()));
                        preference2.setSummary(sb.toString());
                    }
                    return false;
                }
            });
        }
    }

    private void setupKatanaDownloadPreference() {
        Preference preferenceFindPreference = findPreference(R.string.setting_key_download_quality_pref);
        if (preferenceFindPreference != null && ConnectivityUtil.isWifiOnlyDevice(getApplicationContext())) {
            preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_downloads_quality_only_wifi_pref_summary));
        } else {
            preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_downloads_quality_with_mobile_data_pref_summary));
        }
    }

    private void updateKatanaStreamingPreference() {
        PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_playback);
        Preference preferenceFindPreference = findPreference(R.string.setting_key_multi_bitrate_streaming);
        if (preferenceFindPreference == null || TextUtils.isEmpty(getAccountId()) || !UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace() || UserSubscriptionUtil.isNightwingOnly()) {
            if (preferenceFindPreference != null) {
                removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_multi_bitrate_streaming);
            }
        } else {
            preferenceCategoryFindPreferenceCategory.addPreference(preferenceFindPreference);
            if (ConnectivityUtil.isWifiOnlyDevice()) {
                preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_streaming_quality_pref_summary_only_wifi));
            } else {
                preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_streaming_quality_pref_summary));
            }
        }
    }

    private void setupExplicitFilterPreference() {
        Capabilities capabilities = AmazonApplication.getCapabilities();
        if (!capabilities.isExplicitLanguageFilterSupported()) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_playback), R.string.setting_key_parental_control_explicit_language);
            return;
        }
        final SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_parental_control_explicit_language);
        if (switchPreference == null) {
            return;
        }
        switchPreference.setChecked(capabilities.isExplicitLanguageFilterEnabled());
        switchPreference.setWidgetLayoutResource(capabilities.isExplicitLanguageFilterEnabled() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.13
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                switchPreference.setWidgetLayoutResource(((Boolean) newValue).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                SettingsActivity.this.handleExplicitPreferenceChange(switchPreference);
                return false;
            }
        });
        updateExplicitStatusText(switchPreference);
    }

    private void setupLoudnessPreference() {
        final SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_pref_key_loudness_normalization);
        if (switchPreference == null) {
            return;
        }
        boolean z = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.setting_key_is_loudness_normalization_enabled), true);
        switchPreference.setChecked(z);
        switchPreference.setWidgetLayoutResource(z ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.14
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this.getApplicationContext());
                Boolean bool = (Boolean) newValue;
                boolean zBooleanValue = bool.booleanValue();
                switchPreference.setWidgetLayoutResource(bool.booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                Playback.getInstance().getPlaybackConfig().setLoudnessNormalizationEnabled(zBooleanValue);
                ((SwitchPreference) preference).setChecked(zBooleanValue);
                defaultSharedPreferences.edit().putBoolean(SettingsActivity.this.getString(R.string.setting_key_is_loudness_normalization_enabled), zBooleanValue).apply();
                if (zBooleanValue) {
                    UserInteractionAppEvent.builder("selectNormalizationOn").withPageType("SETTINGS").publish();
                    return true;
                }
                UserInteractionAppEvent.builder("selectNormalizationOff").withPageType("SETTINGS").publish();
                return true;
            }
        });
    }

    private void setupPodcastTranscriptsPreference() {
        if (!AmazonApplication.getCapabilities().isPodcastTranscriptFeatureEnabled()) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_playback), R.string.setting_pref_key_podcast_transcription);
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_pref_key_podcast_transcription);
        if (switchPreference == null) {
            return;
        }
        switchPreference.setChecked(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.setting_key_is_podcast_transcript_disabled), false));
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.15
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this.getApplicationContext());
                boolean zBooleanValue = ((Boolean) newValue).booleanValue();
                Podcast.getPlayback().setPodcastTranscriptsDisabledFromSettings(zBooleanValue);
                ((SwitchPreference) preference).setChecked(zBooleanValue);
                preference.setWidgetLayoutResource(zBooleanValue ? R.layout.action_toggleon : R.layout.action_toggleoff);
                defaultSharedPreferences.edit().putBoolean(SettingsActivity.this.getString(R.string.setting_key_is_podcast_transcript_disabled), zBooleanValue).apply();
                if (zBooleanValue) {
                    UserInteractionAppEvent.builder("selectPodcastTranscriptionOff").withPageType("SETTINGS").publish();
                    return true;
                }
                UserInteractionAppEvent.builder("selectPodcastTranscriptionOn").withPageType("SETTINGS").publish();
                return true;
            }
        });
    }

    private void setupSpatialModePreference() {
        if (!AmazonApplication.getCapabilities().isSpatialAudioEnabled(this)) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_playback), R.string.setting_pref_key_spatial_mode);
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_pref_key_spatial_mode);
        if (switchPreference == null) {
            return;
        }
        boolean z = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.setting_pref_key_spatial_mode), true);
        switchPreference.setChecked(z);
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(z).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.16
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                String string;
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this.getApplicationContext());
                boolean zBooleanValue = ((Boolean) newValue).booleanValue();
                preference.setWidgetLayoutResource(zBooleanValue ? R.layout.action_toggleon : R.layout.action_toggleoff);
                ((SwitchPreference) preference).setChecked(zBooleanValue);
                defaultSharedPreferences.edit().putBoolean(SettingsActivity.this.getString(R.string.setting_pref_key_spatial_mode), zBooleanValue).apply();
                PlaybackInitializationHelper.updateSpatialMode(SettingsActivity.this.getApplicationContext());
                PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.NONE);
                MediaItem currentMediaItem = playbackController.getCurrentMediaItem();
                ContentEncoding maxContentEncoding = currentMediaItem != null ? ContentEncodingDecoder.getMaxContentEncoding(currentMediaItem.getContentEncodings()) : null;
                if (zBooleanValue) {
                    if (maxContentEncoding == ContentEncoding.DOLBY_ATMOS) {
                        string = SettingsActivity.this.getResources().getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
                    } else if (maxContentEncoding == ContentEncoding.SONY_360) {
                        string = SettingsActivity.this.getResources().getString(R.string.dmusic_nerd_stats_360_quality);
                    } else {
                        string = SettingsActivity.this.getResources().getString(R.string.dmusic_ivy_spatial_mode_on_quality);
                    }
                } else {
                    string = SettingsActivity.this.getResources().getString(R.string.dmusic_ivy_spatial_mode_off_quality);
                }
                String string2 = SettingsActivity.this.getResources().getString(R.string.dmusic_ivy_spatial_mode_toast_message, string);
                if (playbackController.getPlayStatus() != PlayStatus.USER_PAUSED && playbackController.getPlayStatus() != PlayStatus.SYSTEM_PAUSED) {
                    BauhausToastUtils.showTextToast(SettingsActivity.this.getApplicationContext(), string2, 0);
                }
                ActionType actionType = ActionType.SELECT_ATMOS_360_OFF;
                if (zBooleanValue) {
                    actionType = ActionType.SELECT_ATMOS_360_ON;
                }
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withContentInfo(MetricsLogger.getContentInfo("atmos360Setting")).withEventTime(System.currentTimeMillis()).withPageType(PageType.SETTINGS).build());
                return true;
            }
        });
    }

    private void setupPrioritizeDownloadsPreference() {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_pref_key_prioritize_downloads);
        if (switchPreference == null) {
            return;
        }
        if (!Capabilities.isPrioritizeDownloadsSupported()) {
            removePreference(findPreferenceCategory(R.string.setting_key_category_playback), R.string.setting_pref_key_prioritize_downloads);
            return;
        }
        boolean z = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.setting_pref_key_prioritize_downloads), false);
        switchPreference.setChecked(z);
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(z).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.17
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this.getApplicationContext());
                boolean zBooleanValue = ((Boolean) newValue).booleanValue();
                preference.setWidgetLayoutResource(zBooleanValue ? R.layout.action_toggleon : R.layout.action_toggleoff);
                ((SwitchPreference) preference).setChecked(zBooleanValue);
                PlaybackInitializationHelper playbackInitializationHelper = new PlaybackInitializationHelper(SettingsActivity.this.getApplicationContext());
                SettingsUtil.setPrioritizeDownloadsPreference(SettingsActivity.this.getApplicationContext(), zBooleanValue);
                playbackInitializationHelper.updatePrioritizeDownloadPreference();
                ActionType actionType = ActionType.SELECT_PRIORITIZE_DOWNLOADS_OFF;
                if (zBooleanValue) {
                    actionType = ActionType.SELECT_PRIORITIZE_DOWNLOADS_ON;
                }
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withContentInfo(MetricsLogger.getContentInfo("prioritizeDownloadsSetting")).withEventTime(System.currentTimeMillis()).withPageType(PageType.SETTINGS).build());
                return true;
            }
        });
    }

    private void setupWazeConnectionPreference() {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_waze_connection);
        if (switchPreference == null) {
            return;
        }
        boolean zIsWazeIntegrationEnabled = false;
        try {
            zIsWazeIntegrationEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isWazeIntegrationEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Did not access feature flag WAZE_INTEGRATION successfully with error message: " + e.getMessage());
        }
        if (!zIsWazeIntegrationEnabled) {
            ((PreferenceCategory) findPreference(getString(R.string.setting_key_category_info))).removePreference(switchPreference);
        }
        WazeSdkConnection wazeSdkConnection = WazeSdkConnection.INSTANCE;
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(wazeSdkConnection.isWazeSdkConnected()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setChecked(wazeSdkConnection.isWazeSdkConnected());
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.18
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this.getApplicationContext());
                boolean zBooleanValue = ((Boolean) newValue).booleanValue();
                boolean zIsWazeVersionSupported = WazeAudioSdk.isWazeVersionSupported(SettingsActivity.this.getApplicationContext());
                if (zIsWazeVersionSupported) {
                    SwitchPreference switchPreference2 = (SwitchPreference) preference;
                    switchPreference2.setChecked(zBooleanValue);
                    switchPreference2.setWidgetLayoutResource(Boolean.valueOf(zBooleanValue).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                    defaultSharedPreferences.edit().putBoolean(SettingsActivity.this.getString(R.string.setting_waze_connection), zBooleanValue).apply();
                }
                if (zBooleanValue) {
                    Log.debug(SettingsActivity.TAG, "Waze integration settings toggled to ON");
                    UserInteractionAppEvent.builder("selectWazeSettingOn").publish();
                    WazeSdkConnection.INSTANCE.connectToWazeIfNeeded(SettingsActivity.this.getApplicationContext(), true);
                } else {
                    Log.debug(SettingsActivity.TAG, "Waze integration settings toggled to OFF");
                    UserInteractionAppEvent.builder("selectWazeSettingOff").publish();
                    WazeSdkConnection.INSTANCE.disconnectFromWaze();
                }
                return zIsWazeVersionSupported;
            }
        });
    }

    private void setupCarModePreferences() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(R.string.setting_car_mode);
        if (preferenceCategory == null) {
            return;
        }
        if (!CarModeUtility.isCarModeEnabled()) {
            ((PreferenceScreen) findPreference(getString(R.string.setting_preference_screen))).removePreference(preferenceCategory);
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_car_mode_launch_automatically);
        boolean zIsCarModeAutoLaunchAllowed = CarModeConfigs.isCarModeAutoLaunchAllowed();
        int i = R.layout.action_toggleon;
        if (zIsCarModeAutoLaunchAllowed) {
            switchPreference.setWidgetLayoutResource(switchPreference.isChecked() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        } else {
            switchPreference.setChecked(false);
            switchPreference.setWidgetLayoutResource(R.layout.action_toggleoff);
        }
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda11
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$setupCarModePreferences$1(preference, obj);
            }
        });
        SwitchPreference switchPreference2 = (SwitchPreference) findPreference(R.string.setting_car_mode_keep_screen_active);
        if (!switchPreference2.isChecked()) {
            i = R.layout.action_toggleoff;
        }
        switchPreference2.setWidgetLayoutResource(i);
        switchPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda12
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return SettingsActivity.lambda$setupCarModePreferences$2(preference, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupCarModePreferences$1(final Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (zBooleanValue) {
            BluetoothUtil.INSTANCE.enableAutoCarModeWithBTPermission(this, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.19
                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onGranted() {
                    SettingsActivity.this.handleAutoLaunchPreferenceChange((SwitchPreference) preference, true, true);
                    BluetoothUtil.INSTANCE.startCarDetection();
                    SettingsActivity.this.recreate();
                }

                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onDenied(final String[] permissions) {
                    SettingsActivity.this.handleAutoLaunchPreferenceChange((SwitchPreference) preference, false, false);
                    SettingsActivity.this.recreate();
                }
            }, Build.VERSION.SDK_INT);
        } else {
            handleAutoLaunchPreferenceChange((SwitchPreference) preference, false, true);
        }
        return zBooleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setupCarModePreferences$2(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        SwitchPreference switchPreference = (SwitchPreference) preference;
        switchPreference.setChecked(zBooleanValue);
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(zBooleanValue).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        if (zBooleanValue) {
            Log.debug(TAG, " Keep Screen Active in Car Mode Turned ON");
            AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_SCREEN_ACTIVE_ON, PageType.SETTINGS);
        } else {
            Log.debug(TAG, "Keep Screen Active in Car Mode Turned OFF");
            AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_SCREEN_ACTIVE_OFF, PageType.SETTINGS);
        }
        return zBooleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAutoLaunchPreferenceChange(SwitchPreference preference, boolean enableAutoLaunch, boolean isExplicitClick) {
        if (preference == null) {
            return;
        }
        preference.setChecked(enableAutoLaunch);
        preference.setWidgetLayoutResource(enableAutoLaunch ? R.layout.action_toggleon : R.layout.action_toggleoff);
        if (enableAutoLaunch) {
            Log.debug(TAG, " Start Car Mode Automatically Turned ON");
            if (isExplicitClick) {
                AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_ENTER_AUTOMATICALLY_ON, PageType.SETTINGS);
                return;
            }
            return;
        }
        Log.debug(TAG, "Start Car Mode Automatically Turned OFF");
        if (isExplicitClick) {
            AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_ENTER_AUTOMATICALLY_OFF, PageType.SETTINGS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleExplicitPreferenceChange(final SwitchPreference pref) {
        ExplicitLanguageFilterMetrics.emitSettingClickMetric();
        ExplicitFilterDialogBuilder.builder(this).withPositiveButtonOnClickListener(getExplicitDialogOnClickListener(pref)).withExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()).build().show();
    }

    private DialogInterface.OnClickListener getExplicitDialogOnClickListener(final SwitchPreference preference) {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.20
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                if (!ConnectivityUtil.checkConnectivityAndShowDialog(SettingsActivity.this) || SettingsActivity.this.mExplicitLanguageManager == null) {
                    return;
                }
                SettingsActivity.this.mExplicitLanguageManager.toggleExplicitFilterSetting(preference);
            }
        };
    }

    private void updateExplicitStatusText(final SwitchPreference pref) {
        ExplicitLanguageManager explicitLanguageManager = this.mExplicitLanguageManager;
        if (explicitLanguageManager == null) {
            return;
        }
        explicitLanguageManager.setPreferenceTitleForAutomation(pref);
        this.mExplicitLanguageManager.setPreferenceSubtitle(pref);
    }

    private String getAccountId() {
        String accountId = AccountCredentialStorage.get(this).getAccountId();
        if (!TextUtils.isEmpty(accountId)) {
            return accountId;
        }
        String account = MapAccount.getManager(this).getAccount();
        return account == null ? "" : account;
    }

    private void updateSignInOutPreference() {
        Preference preferenceFindPreference = findPreference(R.string.setting_key_sign_out);
        if (preferenceFindPreference == null) {
            return;
        }
        if (!AmazonApplication.getCapabilities().isSignOutSettingEnabled(this)) {
            PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_info);
            if (preferenceCategoryFindPreferenceCategory != null) {
                preferenceCategoryFindPreferenceCategory.removePreference(preferenceFindPreference);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(getAccountId())) {
            preferenceFindPreference.setTitle(R.string.dmusic_setting_sign_in_title);
            preferenceFindPreference.setEnabled(true);
            if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
                preferenceFindPreference.setSummary(R.string.dmusic_setting_sign_in_desc);
                return;
            } else {
                preferenceFindPreference.setSummary(R.string.dmusic_setting_sign_in_noncirrus_desc);
                return;
            }
        }
        preferenceFindPreference.setEnabled(true);
        preferenceFindPreference.setTitle(R.string.dmusic_sign_out);
        preferenceFindPreference.setSummary(getString(R.string.dmusic_setting_sign_out_enabled_desc, AccountCredentialStorage.get().getUsername()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubscriptionManagementPreferences() {
        new SubscriptionSettingsUpdater(findPreference(R.string.setting_key_upsell), findPreference(R.string.setting_key_manage_subscription), findPreference(R.string.setting_key_manage_family), findPreference(R.string.setting_key_cancel_membership), findPreferenceCategory(R.string.setting_key_category_your_account), findPreferenceCategory(R.string.setting_key_category_info), AccountDetailUtil.get(this), SubscriptionManagerSingleton.isInitialized() ? SubscriptionManagerSingleton.getInstance() : null, UserSubscriptionUtil.getUserSubscription(), getApplicationContext()).update();
    }

    private void displayHarleyVersion() {
        getAndDisplayPlayerVersion(findPreference(R.string.setting_key_dev_harley_version), true);
    }

    private void displayPrimeVideoPlayerVersion() {
        getAndDisplayPlayerVersion(findPreference(R.string.setting_key_dev_prime_video_version), false);
    }

    private void getAndDisplayPlayerVersion(final Preference preference, final boolean isHarleyPlayer) {
        PlaybackConfig playbackConfig;
        if (preference == null || (playbackConfig = Playback.getInstance().getPlaybackConfig()) == null) {
            return;
        }
        if (isHarleyPlayer) {
            Player localPlayer = playbackConfig.getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(getApplicationContext()));
            if (localPlayer instanceof HarleyPlayer) {
                preference.setSummary(((HarleyPlayer) localPlayer).getVersion());
                return;
            }
            return;
        }
        Player primeVideoPlayer = playbackConfig.getPrimeVideoPlayer();
        if (primeVideoPlayer instanceof PrimeVideoPlayer) {
            preference.setSummary(((PrimeVideoPlayer) primeVideoPlayer).getPvPlayerSdk().getVersion());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIAMPreference() {
        EditTextPreference editTextPreference = (EditTextPreference) findPreference(R.string.setting_key_dev_iam_endpoint);
        if (editTextPreference == null) {
            return;
        }
        if (StringUtils.isNotBlank(editTextPreference.getText())) {
            editTextPreference.setSummary("Entered endpoint: " + editTextPreference.getText());
            return;
        }
        editTextPreference.setSummary("Enter an endpoint url.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCantileverEndpointPreference() {
        EditTextPreference editTextPreference = (EditTextPreference) findPreference(R.string.setting_key_dev_cantilever_endpoint);
        if (editTextPreference == null) {
            return;
        }
        if (StringUtils.isNotBlank(editTextPreference.getText())) {
            editTextPreference.setSummary("Entered endpoint: " + editTextPreference.getText());
            return;
        }
        editTextPreference.setSummary("Enter an endpoint url.");
    }

    private void updateStorePreference() {
        if (AmazonApplication.getCapabilities().shouldShowStoreSetting()) {
            return;
        }
        removePreference(findPreferenceCategory(R.string.setting_key_category_info), R.string.setting_key_enable_store);
    }

    private boolean keyMatches(Preference preference, int settingsId) {
        return preference.getKey().equals(getString(settingsId));
    }

    private void updateCirrusPreferences() {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_cirrus_download_pref);
        Preference preferenceFindPreference = findPreference(R.string.setting_key_resync);
        Preference preferenceFindPreference2 = findPreference(R.string.setting_key_audio_streaming_network_pref);
        Preference preferenceFindPreference3 = findPreference(R.string.setting_key_video_streaming_network_pref);
        boolean zIsSignedIn = AccountCredentialUtil.get(this).isSignedIn();
        AccountDetailStorage accountDetailStorage = AccountDetailStorage.get(this);
        boolean zHasAcceptedTermsOfUse = accountDetailStorage.hasAcceptedTermsOfUse();
        boolean z = User.CloudAccountStatus.LOCKED == accountDetailStorage.getAccountStatus();
        if (switchPreference != null) {
            boolean z2 = WebViewUtil.isBlueMoon(this) && accountDetailStorage.isAccountVerified();
            boolean zIsChecked = switchPreference.isChecked();
            switchPreference.setEnabled((zIsSignedIn && zHasAcceptedTermsOfUse) || z2 || zIsChecked);
            switchPreference.setWidgetLayoutResource(Boolean.valueOf(zIsChecked).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        }
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setEnabled(zIsSignedIn && zHasAcceptedTermsOfUse);
        }
        if (preferenceFindPreference2 != null) {
            String streamingNetworkPreference = SettingsUtil.getStreamingNetworkPreference(this, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO);
            preferenceFindPreference2.setEnabled(zIsSignedIn && zHasAcceptedTermsOfUse && !z);
            if ("ALL_NETWORKS".equals(streamingNetworkPreference) || "WIFI_OR_3G4G".equals(streamingNetworkPreference)) {
                preferenceFindPreference2.setSummary(R.string.dmusic_setting_audio_streaming_network_desc_all);
            } else if ("WIFI_ONLY".equals(streamingNetworkPreference)) {
                preferenceFindPreference2.setSummary(R.string.dmusic_setting_audio_streaming_network_desc_wifi_only);
            } else {
                throw new IllegalArgumentException("Invalid audio streaming network setting: " + streamingNetworkPreference);
            }
        }
        if (preferenceFindPreference3 != null && AmazonApplication.getCapabilities().isVideosEnabled()) {
            String streamingNetworkPreference2 = SettingsUtil.getStreamingNetworkPreference(this, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO);
            preferenceFindPreference3.setEnabled(zIsSignedIn && zHasAcceptedTermsOfUse && !z);
            if ("ALL_NETWORKS".equals(streamingNetworkPreference2) || "WIFI_OR_3G4G".equals(streamingNetworkPreference2)) {
                preferenceFindPreference3.setSummary(R.string.dmusic_setting_video_streaming_network_desc_all);
                return;
            } else {
                if ("WIFI_ONLY".equals(streamingNetworkPreference2)) {
                    preferenceFindPreference3.setSummary(R.string.dmusic_setting_video_streaming_network_desc_wifi_only);
                    return;
                }
                throw new IllegalArgumentException("Invalid video streaming network setting: " + streamingNetworkPreference2);
            }
        }
        if (preferenceFindPreference3 == null || AmazonApplication.getCapabilities().isVideosEnabled()) {
            return;
        }
        findPreferenceCategory(R.string.setting_key_category_playback).removePreference(preferenceFindPreference3);
    }

    private void updateDownloadPreferences() {
        Preference preferenceFindPreference = findPreference(R.string.setting_key_download_location_pref);
        if (preferenceFindPreference != null) {
            StorageLocation preferredStorageLocation = new StorageLocationPreference(this).getPreferredStorageLocation();
            if (StorageLocation.DEVICE.equals(preferredStorageLocation) || !preferenceFindPreference.isEnabled()) {
                preferenceFindPreference.setSummary(R.string.dmusic_setting_download_location_internal_desc);
            } else {
                if (StorageLocation.SDCARD.equals(preferredStorageLocation)) {
                    preferenceFindPreference.setSummary(R.string.dmusic_setting_download_location_external_desc);
                    return;
                }
                throw new IllegalArgumentException("Invalid download location setting");
            }
        }
    }

    public void scrollToItem(int preferenceId) {
        final ListView listView = getListView();
        PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(preferenceId);
        if (preferenceCategoryFindPreferenceCategory == null || listView == null || listView.getAdapter() == null) {
            return;
        }
        for (final int i = 0; i < listView.getAdapter().getCount(); i++) {
            if (((Preference) listView.getAdapter().getItem(i)) == preferenceCategoryFindPreferenceCategory) {
                listView.postDelayed(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.21
                    @Override // java.lang.Runnable
                    public void run() {
                        listView.smoothScrollToPositionFromTop(i, 0);
                    }
                }, 250L);
                return;
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(final boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Integer num = this.scrollToPrefId;
        if (num != null) {
            scrollToItem(num.intValue());
            this.scrollToPrefId = null;
        }
    }

    public void setIsRedownloadRemoveAllEnabled(boolean enabled) {
        this.mIsRedownloadRemoveAllEnabled = enabled;
        runOnUiThread(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.22
            @Override // java.lang.Runnable
            public void run() {
                SettingsActivity.this.updateStoragePreferenceCategory();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStoragePreferenceCategory() {
        final PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_storage);
        preferenceCategoryFindPreferenceCategory.setTitle(preferenceCategoryFindPreferenceCategory.getTitle().toString().toUpperCase(AmazonApplication.getLocale()));
        final Capabilities capabilities = AmazonApplication.getCapabilities();
        final Preference preferenceFindPreference = preferenceCategoryFindPreferenceCategory.findPreference(getString(R.string.setting_key_remove_greyed_songs));
        final Preference preferenceFindPreference2 = preferenceCategoryFindPreferenceCategory.findPreference(getString(R.string.setting_key_download_greyed_songs));
        if (preferenceFindPreference != null && preferenceFindPreference2 != null) {
            preferenceCategoryFindPreferenceCategory.removePreference(preferenceFindPreference);
            preferenceCategoryFindPreferenceCategory.removePreference(preferenceFindPreference2);
            preferenceFindPreference.setEnabled(this.mIsRedownloadRemoveAllEnabled);
            preferenceFindPreference2.setEnabled(this.mIsRedownloadRemoveAllEnabled);
        }
        if (!capabilities.isDeviceSDCapable(getApplicationContext())) {
            removePreference(preferenceCategoryFindPreferenceCategory, R.string.setting_key_sd_card_status);
        }
        final Preference preferenceFindPreference3 = findPreference(R.string.setting_key_sd_card_status);
        if (preferenceFindPreference3 != null) {
            this.checkSDCardStatusSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$updateStoragePreferenceCategory$3(capabilities, (Subscriber) obj);
                }
            }).onErrorReturn(new Func1() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda4
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return SettingsActivity.lambda$updateStoragePreferenceCategory$4((Throwable) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toSingle().subscribe(new Action1() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda5
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    SettingsActivity.lambda$updateStoragePreferenceCategory$5(preferenceFindPreference3, (Boolean) obj);
                }
            });
        }
        final Preference preferenceFindPreference4 = preferenceCategoryFindPreferenceCategory.findPreference(getString(R.string.setting_key_clear_caches));
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.23
            @Override // java.lang.Runnable
            public void run() {
                Preference preference;
                boolean z = TrackUtil.isSDCardMissingContent(SettingsActivity.this.getApplicationContext(), "cirrus") || PrimePlaylistUtil.isAnyPrimePlaylistTrackMissing(SettingsActivity.this.getApplicationContext());
                Log.debug(SettingsActivity.TAG, "Found missing SD card content: " + z);
                if (!z || preferenceFindPreference == null || preferenceFindPreference2 == null || (preference = preferenceFindPreference4) == null) {
                    return;
                }
                preferenceCategoryFindPreferenceCategory.removePreference(preference);
                preferenceCategoryFindPreferenceCategory.addPreference(preferenceFindPreference);
                preferenceCategoryFindPreferenceCategory.addPreference(preferenceFindPreference2);
                preferenceCategoryFindPreferenceCategory.addPreference(preferenceFindPreference4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateStoragePreferenceCategory$3(Capabilities capabilities, Subscriber subscriber) {
        subscriber.onNext(Boolean.valueOf(capabilities.secondaryStoragePresent(this) && !SDCardUtil.isSdCardReadOnly(this)));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$updateStoragePreferenceCategory$4(Throwable th) {
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStoragePreferenceCategory$5(Preference preference, Boolean bool) {
        if (bool.booleanValue()) {
            preference.setSummary(R.string.dmusic_setting_storage_sd_card_status_summary_online_pref);
            preference.setIcon(R.drawable.sdcard_status_green_oval);
        } else {
            preference.setSummary(R.string.dmusic_setting_storage_sd_card_status_summary_offline_pref);
            preference.setIcon(R.drawable.sdcard_status_red_oval);
        }
    }

    private void syncUIWithWakeWordPreference() {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_wake_word_toggle);
        if (switchPreference == null) {
            Log.warning(TAG, "WakeWord preference is null. Failing to update the toggle to the most updated value");
        } else {
            switchPreference.setChecked(SettingsUtil.isWakeWordUserSettingEnabled(getApplicationContext()));
            switchPreference.setWidgetLayoutResource(Boolean.valueOf(SettingsUtil.isWakeWordUserSettingEnabled(getApplicationContext())).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAutoDownload(boolean autoDownload) {
        if (!autoDownload) {
            AutoDownloadPurchaseReceiver.setComponentEnabled(this, false);
            return;
        }
        final SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_cirrus_download_pref);
        GrantStoragePermissionActivity.requestIfNotGranted(this, MusicDownloader.PERMISSIONS_FOR_DOWNLOAD, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.25
            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onGranted() {
                SettingsUtil.setCirrusDownloadPreference(this, true);
                SettingsUtil.setLastAutoDownloadDateToLastPurchaseDate(this);
                AutoDownloadPurchaseReceiver.setComponentEnabled(this, true);
                SwitchPreference switchPreference2 = switchPreference;
                if (switchPreference2 != null) {
                    switchPreference2.setChecked(true);
                }
            }

            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onDenied(String[] permissions) {
                SettingsUtil.setCirrusDownloadPreference(this, false);
                SwitchPreference switchPreference2 = switchPreference;
                if (switchPreference2 != null) {
                    switchPreference2.setChecked(false);
                }
            }
        });
    }

    private void setupDeveloperPreferences() {
        if (hideDeveloperPreference()) {
            return;
        }
        setupRemoteConfig();
        setupArcusTestBetaConfig();
        setupWeblabOverride();
        setupEndpoints();
        setupDebugMetricsFileDevPreference();
        setupPicassoPreference();
        setupLeakCanaryPreference();
        displayHarleyVersion();
        displayPrimeVideoPlayerVersion();
        setupMpqsPreference();
        setupShowContactUsInDebugBuild();
        setupBauhausPreference();
        setupToggleAdpToOAuthMigration();
        setupTogglePCAFeature();
    }

    private void setupEndpoints() {
        final ListPreference listPreference = (ListPreference) findPreference(R.string.setting_key_dev_cirrus_endpoint);
        final ListPreference listPreference2 = (ListPreference) findPreference(R.string.setting_key_dev_store_endpoint);
        final ListPreference listPreference3 = (ListPreference) findPreference(R.string.setting_key_dev_webview_endpoint);
        final ListPreference listPreference4 = (ListPreference) findPreference(R.string.setting_key_dev_config_file_endpoint);
        final ListPreference listPreference5 = (ListPreference) findPreference(R.string.setting_key_dev_playlist_endpoint);
        final ListPreference listPreference6 = (ListPreference) findPreference(R.string.setting_key_dev_dmls_endpoint);
        final ListPreference listPreference7 = (ListPreference) findPreference(R.string.setting_key_dev_cta_endpoint);
        final ListPreference listPreference8 = (ListPreference) findPreference(R.string.setting_key_dev_search_endpoint);
        final ListPreference listPreference9 = (ListPreference) findPreference(R.string.setting_key_dev_stratus_endpoint);
        final ListPreference listPreference10 = (ListPreference) findPreference(R.string.setting_key_dev_nautilus_endpoint);
        final ListPreference listPreference11 = (ListPreference) findPreference(R.string.setting_key_dev_cirrus_v3_endpoint);
        final ListPreference listPreference12 = (ListPreference) findPreference(R.string.setting_key_dev_ftu_endpoint);
        final ListPreference listPreference13 = (ListPreference) findPreference(R.string.setting_key_dev_automotive_endpoint);
        final EditTextPreference editTextPreference = (EditTextPreference) findPreference(R.string.setting_key_dev_iam_endpoint);
        final ListPreference listPreference14 = (ListPreference) findPreference(R.string.setting_key_dev_dynmsg_endpoint);
        final ListPreference listPreference15 = (ListPreference) findPreference(R.string.setting_key_dev_explore_service_endpoint_override);
        final ListPreference listPreference16 = (ListPreference) findPreference(R.string.setting_key_dev_muse_endpoint);
        final ListPreference listPreference17 = (ListPreference) findPreference(R.string.setting_key_dev_brushv3_endpoint);
        final ListPreference listPreference18 = (ListPreference) findPreference(R.string.setting_key_dev_activity_feed_service_endpoint_override);
        final ListPreference listPreference19 = (ListPreference) findPreference(R.string.setting_key_podcast_endpoint_override);
        final EditTextPreference editTextPreference2 = (EditTextPreference) findPreference(R.string.setting_key_dev_cantilever_endpoint);
        final ListPreference listPreference20 = (ListPreference) findPreference(R.string.setting_key_dev_curate_service_endpoint_override);
        EditTextPreference editTextPreference3 = (EditTextPreference) findPreference(R.string.setting_key_dev_chromecast_receiver_app_id_override);
        final ListPreference listPreference21 = (ListPreference) findPreference(R.string.setting_key_dev_cloudqueue_auth_endpoint);
        findPreference(R.string.setting_key_dev_native_crash);
        final SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_dev_custom_endpoint_enabled);
        switchPreference.setWidgetLayoutResource(switchPreference.isChecked() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.27
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                SwitchPreference switchPreference2 = switchPreference;
                boolean zBooleanValue = Boolean.valueOf(switchPreference2.isChecked()).booleanValue();
                int i = R.layout.action_toggleon;
                switchPreference2.setWidgetLayoutResource(zBooleanValue ? R.layout.action_toggleon : R.layout.action_toggleoff);
                if (switchPreference.isChecked()) {
                    return true;
                }
                SettingsUtil.EnvironmentPref.removeEnvironment(SettingsActivity.this.getApplicationContext());
                SwitchPreference switchPreference3 = switchPreference;
                if (!switchPreference3.isChecked()) {
                    i = R.layout.action_toggleoff;
                }
                switchPreference3.setWidgetLayoutResource(i);
                listPreference.setSummary(Environment.CLOUD.getCurrentEndPointId());
                listPreference2.setSummary(Environment.STORE.getCurrentEndPointId());
                listPreference3.setSummary(Environment.WEBVIEW.getCurrentEndPointId());
                listPreference4.setSummary(Environment.CONFIG.getCurrentEndPointId());
                listPreference5.setSummary(Environment.MUSIC_PLAYLIST_SERVICE.getCurrentEndPointId());
                listPreference6.setSummary(Environment.DMLS.getCurrentEndPointId());
                listPreference7.setSummary(Environment.CLIENT_BUDDY_CTA.getCurrentEndPointId());
                listPreference8.setSummary(Environment.SEARCH.getCurrentEndPointId());
                listPreference9.setSummary(Environment.STRATUS.getCurrentEndPointId());
                listPreference10.setSummary(Environment.NAUTILUS.getCurrentEndPointId());
                listPreference11.setSummary(Environment.CIRRUS_V3.getCurrentEndPointId());
                listPreference12.setSummary(Environment.FTU.getCurrentEndPointId());
                listPreference13.setSummary(Environment.AUTOMOTIVE.getCurrentOverrideId());
                editTextPreference.setText(Environment.IAM_URL.getUrl().toString());
                listPreference14.setSummary(Environment.DYNMSG_URL.getCurrentOverrideId());
                listPreference15.setSummary(Environment.EXPLORE_SERVICE.getCurrentOverrideId());
                ListPreference listPreference22 = listPreference16;
                MuseEndPointURLFactory museEndPointURLFactory = Environment.MUSE_URL;
                listPreference22.setSummary(museEndPointURLFactory.getCurrentOverrideId(R.string.setting_key_dev_muse_endpoint));
                listPreference17.setSummary(museEndPointURLFactory.getCurrentOverrideId(R.string.setting_key_dev_brushv3_endpoint));
                listPreference18.setSummary(Environment.ACTIVITY_FEED_SERVICE.getCurrentOverrideId());
                listPreference19.setSummary(Environment.PODCAST.getCurrentOverrideId());
                editTextPreference2.setText(EndpointsProvider.get().getCantileverEndpoint());
                listPreference20.setSummary(Environment.CURATE_SERVICE.getCurrentOverrideId());
                listPreference21.setSummary(Environment.CLOUD_QUEUE_AUTH.getCurrentOverrideId());
                Log.debug(SettingsActivity.TAG, "The custom EndPoint feature has been disabled.");
                return true;
            }
        });
        Set<String> setIdSet = IntlConfiguration.ENDPOINT.idSet();
        String[] strArr = new String[setIdSet.size()];
        setIdSet.toArray(strArr);
        setupCustomEndPointList(listPreference, strArr, Environment.CLOUD.getCurrentEndPointId());
        setupCustomEndPointList(listPreference2, strArr, Environment.STORE.getCurrentEndPointId());
        setupCustomEndPointList(listPreference3, strArr, Environment.WEBVIEW.getCurrentEndPointId());
        setupCustomEndPointList(listPreference4, strArr, Environment.CONFIG.getCurrentEndPointId());
        setupCustomEndPointList(listPreference5, strArr, Environment.MUSIC_PLAYLIST_SERVICE.getCurrentEndPointId());
        setupCustomEndPointList(listPreference6, strArr, Environment.DMLS.getCurrentEndPointId());
        setupCustomEndPointList(listPreference7, strArr, Environment.CLIENT_BUDDY_CTA.getCurrentEndPointId());
        setupCustomEndPointList(listPreference8, strArr, Environment.SEARCH.getCurrentEndPointId());
        setupCustomEndPointList(listPreference9, strArr, Environment.STRATUS.getCurrentEndPointId());
        setupCustomEndPointList(listPreference10, strArr, Environment.NAUTILUS.getCurrentEndPointId());
        setupCustomEndPointList(listPreference11, strArr, Environment.CIRRUS_V3.getCurrentEndPointId());
        setupCustomEndPointList(listPreference12, strArr, Environment.FTU.getCurrentEndPointId());
        ExploreEndpointOverrideFactory exploreEndpointOverrideFactory = Environment.EXPLORE_SERVICE;
        setupCustomEndPointList(listPreference15, exploreEndpointOverrideFactory.overrideEntries(), exploreEndpointOverrideFactory.getCurrentOverrideId());
        MuseEndPointURLFactory museEndPointURLFactory = Environment.MUSE_URL;
        setupCustomEndPointList(listPreference16, museEndPointURLFactory.overrideEntries(R.string.setting_key_dev_muse_endpoint), museEndPointURLFactory.getCurrentOverrideId(R.string.setting_key_dev_muse_endpoint));
        setupCustomEndPointList(listPreference17, museEndPointURLFactory.overrideEntries(R.string.setting_key_dev_brushv3_endpoint), museEndPointURLFactory.getCurrentOverrideId(R.string.setting_key_dev_brushv3_endpoint));
        FeedEndpointFactory feedEndpointFactory = Environment.ACTIVITY_FEED_SERVICE;
        setupCustomEndPointList(listPreference18, feedEndpointFactory.overrideEntries(), feedEndpointFactory.getCurrentOverrideId());
        CurateEndpointSettingsProvider curateEndpointSettingsProvider = Environment.CURATE_SERVICE;
        setupCustomEndPointList(listPreference20, curateEndpointSettingsProvider.overrideEntries(), curateEndpointSettingsProvider.getCurrentOverrideId());
        PodcastEndpointOverrideFactory podcastEndpointOverrideFactory = Environment.PODCAST;
        setupCustomEndPointListForPodcast(listPreference19, podcastEndpointOverrideFactory.overrideEntries(), podcastEndpointOverrideFactory.getCurrentOverrideId());
        setupCustomEndPointEditTextForChromecast(editTextPreference3);
        DMAutomotiveEndPointURLFactory dMAutomotiveEndPointURLFactory = Environment.AUTOMOTIVE;
        setupCustomEndPointListForAutomotive(listPreference13, dMAutomotiveEndPointURLFactory.overrideEntries(), dMAutomotiveEndPointURLFactory.getCurrentOverrideId());
        CloudQueueAuthEndpointURLFactory cloudQueueAuthEndpointURLFactory = Environment.CLOUD_QUEUE_AUTH;
        setupCustomEndPointListWithCallback(listPreference21, cloudQueueAuthEndpointURLFactory.overrideEntries(), cloudQueueAuthEndpointURLFactory.getCurrentOverrideId(), new Callback() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda7
            @Override // com.amazon.music.platform.data.Callback
            public final void callback() {
                this.f$0.lambda$setupEndpoints$6();
            }
        });
        setupCustomEndPointEditText(editTextPreference, "https://6jca9sg8u2.execute-api.us-east-1.amazonaws.com/dev/api/v1/experiences");
        DynMsgEndpointProvider dynMsgEndpointProvider = Environment.DYNMSG_URL;
        setupCustomEndPointList(listPreference14, dynMsgEndpointProvider.overrideEntries(), dynMsgEndpointProvider.getCurrentOverrideId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupEndpoints$6() {
        BauhausToastUtils.showTextToast(this, getResources().getString(R.string.dmusic_setting_dev_restart_app), 0);
    }

    private void setupPicassoPreference() {
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(getResources().getString(R.string.setting_key_dev_picasso_debug));
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof SwitchPreference)) {
            return;
        }
        final SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.28
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(final Preference preference) {
                SwitchPreference switchPreference2 = switchPreference;
                switchPreference2.setWidgetLayoutResource(Boolean.valueOf(switchPreference2.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                Picasso.get().setIndicatorsEnabled(switchPreference.isChecked());
                return true;
            }
        });
    }

    private void setupLeakCanaryPreference() {
        final String string = getResources().getString(R.string.dmusic_setting_key_leak_canary);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(string);
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof SwitchPreference)) {
            return;
        }
        final SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        switchPreference.setChecked(defaultSharedPreferences.getBoolean(string, false));
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(defaultSharedPreferences.getBoolean(string, false)).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda1
            @Override // android.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setupLeakCanaryPreference$7(switchPreference, defaultSharedPreferences, string, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupLeakCanaryPreference$7(SwitchPreference switchPreference, SharedPreferences sharedPreferences, String str, Preference preference) {
        boolean zIsChecked = switchPreference.isChecked();
        String str2 = TAG;
        Log.debug(str2, "leak canary toggled to " + zIsChecked);
        sharedPreferences.edit().putBoolean(str, zIsChecked).apply();
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(zIsChecked).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        BauhausToastUtils.showTextToast(this, getResources().getString(R.string.dmusic_setting_dev_restart_app), 0);
        Log.debug(str2, "Leak Canary preference toggled");
        return true;
    }

    private void setupMpqsPreference() {
        final String string = getResources().getString(R.string.dmusic_setting_key_mpqs);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(string);
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof SwitchPreference)) {
            return;
        }
        final SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        switchPreference.setChecked(defaultSharedPreferences.getBoolean(string, false));
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(defaultSharedPreferences.getBoolean(string, false)).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda0
            @Override // android.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setupMpqsPreference$8(switchPreference, defaultSharedPreferences, string, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupMpqsPreference$8(SwitchPreference switchPreference, SharedPreferences sharedPreferences, String str, Preference preference) {
        boolean zIsChecked = switchPreference.isChecked();
        Log.debug(TAG, "MPQS preference toggled to " + zIsChecked);
        sharedPreferences.edit().putBoolean(str, zIsChecked).apply();
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(zIsChecked).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        BauhausToastUtils.showTextToast(this, getResources().getString(R.string.dmusic_setting_dev_restart_app), 0);
        return true;
    }

    private void setupShowContactUsInDebugBuild() {
        final String string = getResources().getString(R.string.setting_key_dev_debug_contact_us_pref);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(string);
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof SwitchPreference)) {
            return;
        }
        final SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        switchPreference.setChecked(defaultSharedPreferences.getBoolean(string, false));
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(defaultSharedPreferences.getBoolean(string, false)).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda2
            @Override // android.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setupShowContactUsInDebugBuild$9(switchPreference, defaultSharedPreferences, string, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupShowContactUsInDebugBuild$9(SwitchPreference switchPreference, SharedPreferences sharedPreferences, String str, Preference preference) {
        boolean zIsChecked = switchPreference.isChecked();
        Log.debug(TAG, "ContactUs Enabled in Debug Build preference toggled to " + zIsChecked);
        sharedPreferences.edit().putBoolean(str, zIsChecked).apply();
        String string = getResources().getString(R.string.dmusic_setting_key_dev_debug_contact_us_pref_title);
        preference.setWidgetLayoutResource(Boolean.valueOf(zIsChecked).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        BauhausToastUtils.showTextToast(this, string, 0);
        PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_info);
        if (preferenceCategoryFindPreferenceCategory == null) {
            return true;
        }
        if (zIsChecked) {
            preferenceCategoryFindPreferenceCategory.findPreference(getString(R.string.setting_key_contact_us)).setTitle(R.string.dmusic_setting_help_and_feedback_title);
            return true;
        }
        preferenceCategoryFindPreferenceCategory.findPreference(getString(R.string.setting_key_contact_us)).setTitle(R.string.dmusic_logging_email_logfile_title);
        return true;
    }

    private void setupArcusTestBetaConfig() {
        disableArcusConfig(R.string.setting_key_dev_arcus_test_config);
        disableArcusConfig(R.string.setting_key_dev_arcus_beta_config);
    }

    private void setupBauhausPreference() {
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(getResources().getString(R.string.setting_key_dev_picasso_debug));
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof SwitchPreference)) {
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
        switchPreference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.29
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object o) {
                preference.setWidgetLayoutResource(((Boolean) o).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                return true;
            }
        });
    }

    private void setupRemoteConfig() {
        final Context applicationContext = getApplicationContext();
        ListPreference listPreference = (ListPreference) findPreference(R.string.setting_key_dev_remote_arcus_picker);
        List<String> environments = AppConfigProvider.INSTANCE.getEnvironments(applicationContext, R.raw.internal_arcus_accounts);
        CharSequence[] charSequenceArr = new CharSequence[environments.size() + 1];
        final String string = getApplicationContext().getResources().getString(R.string.dmusic_setting_dev_override_weblab_default_entry);
        int i = 0;
        charSequenceArr[0] = string;
        while (i < environments.size()) {
            int i2 = i + 1;
            charSequenceArr[i2] = environments.get(i);
            i = i2;
        }
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(charSequenceArr);
        String environmentFromSharedPreferences = AppConfigProvider.INSTANCE.getEnvironmentFromSharedPreferences(applicationContext);
        if (environmentFromSharedPreferences == null) {
            environmentFromSharedPreferences = string;
        }
        listPreference.setValue(environmentFromSharedPreferences);
        listPreference.setSummary(environmentFromSharedPreferences);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.30
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object newValue) {
                new BauhausDialogBuilder(SettingsActivity.this).setTitle("Remote Arcus config changed").setMessage("App will terminate and use the new environment on restart. Click ok to proceed!").setPositiveButton(SettingsActivity.this.getString(R.string.dmusic_button_ok), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.30.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialogInterface, final int i3) {
                        boolean zSaveEnvironmentToSharedPreferences;
                        String string2 = newValue.toString();
                        if (string2.equals(string)) {
                            zSaveEnvironmentToSharedPreferences = AppConfigProvider.INSTANCE.saveEnvironmentToSharedPreferences(applicationContext, null);
                        } else {
                            zSaveEnvironmentToSharedPreferences = AppConfigProvider.INSTANCE.saveEnvironmentToSharedPreferences(applicationContext, string2);
                        }
                        if (zSaveEnvironmentToSharedPreferences) {
                            preference.setSummary(string2);
                            System.exit(0);
                        }
                    }
                }).setNegativeButton(SettingsActivity.this.getString(R.string.dmusic_button_cancel), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.30.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialog, final int which) {
                    }
                }).create().show();
                return true;
            }
        });
    }

    private void disableArcusConfig(int preferenceId) {
        ((SwitchPreference) findPreference(preferenceId)).setEnabled(false);
    }

    private void setupWeblabOverride() {
        final ListPreference listPreference = (ListPreference) findPreference(R.string.setting_key_dev_override_weblab);
        final ListPreference listPreference2 = (ListPreference) findPreference(R.string.setting_key_dev_override_weblab_treatment);
        final String string = getApplicationContext().getResources().getString(R.string.dmusic_setting_dev_override_weblab_default_entry);
        final WeblabProviderImpl weblabProviderImpl = (WeblabProviderImpl) Providers.INSTANCE.get(WeblabProvider.class);
        List<WeblabDescriptor> allRegisteredWeblabs = weblabProviderImpl.getAllRegisteredWeblabs();
        int size = allRegisteredWeblabs.size();
        int i = size + 1;
        CharSequence[] charSequenceArr = new CharSequence[i];
        CharSequence[] charSequenceArr2 = new CharSequence[i];
        charSequenceArr[0] = string;
        charSequenceArr2[0] = string;
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            charSequenceArr[i3] = allRegisteredWeblabs.get(i2).getName();
            charSequenceArr2[i3] = allRegisteredWeblabs.get(i2).getName();
            i2 = i3;
        }
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(charSequenceArr2);
        if (weblabProviderImpl.getOverriddenWeblab() == null) {
            listPreference.setValue(string);
        } else {
            listPreference.setSummary(weblabProviderImpl.getOverriddenWeblab());
        }
        WeblabTreatment[] weblabTreatmentArrValues = WeblabTreatment.values();
        CharSequence[] charSequenceArr3 = new CharSequence[weblabTreatmentArrValues.length + 1];
        CharSequence[] charSequenceArr4 = new CharSequence[weblabTreatmentArrValues.length + 1];
        charSequenceArr3[0] = string;
        charSequenceArr4[0] = string;
        int i4 = 0;
        while (i4 < weblabTreatmentArrValues.length) {
            int i5 = i4 + 1;
            charSequenceArr3[i5] = weblabTreatmentArrValues[i4].name();
            charSequenceArr4[i5] = weblabTreatmentArrValues[i4].toString();
            i4 = i5;
        }
        listPreference2.setEntries(charSequenceArr3);
        listPreference2.setEntryValues(charSequenceArr4);
        if (weblabProviderImpl.getOverriddenTreatment() == null) {
            listPreference2.setValue(string);
            listPreference2.setEnabled(false);
        } else {
            listPreference2.setSummary(weblabProviderImpl.getOverriddenTreatment());
        }
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.31
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String value = listPreference.getValue();
                if (!value.equals(string)) {
                    weblabProviderImpl.unlockWeblab(value);
                }
                String string2 = newValue.toString();
                listPreference2.setEnabled((string2 == null || string2.equals(string)) ? false : true);
                listPreference2.setValue(string);
                listPreference2.setSummary(string);
                preference.setSummary(newValue.toString());
                return true;
            }
        });
        listPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.32
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (!newValue.toString().equals(string)) {
                    weblabProviderImpl.lockWeblab(listPreference.getValue(), newValue.toString());
                }
                preference.setSummary(newValue.toString());
                return true;
            }
        });
    }

    private boolean hideDeveloperPreference() {
        if (new DevModeCapabilities().isDeveloperToolEnabled()) {
            return false;
        }
        PreferenceCategory preferenceCategoryFindPreferenceCategory = findPreferenceCategory(R.string.setting_key_category_developer);
        if (preferenceCategoryFindPreferenceCategory == null) {
            return true;
        }
        getPreferenceScreen().removePreference(preferenceCategoryFindPreferenceCategory);
        return true;
    }

    private void setupCustomEndPointList(final ListPreference pref, String[] entries, String summary) {
        pref.setEntries(entries);
        pref.setEntryValues(entries);
        pref.setSummary(summary);
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.33
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                pref.setSummary((CharSequence) newValue);
                return true;
            }
        });
    }

    private void setupCustomEndPointListWithCallback(final ListPreference pref, String[] entries, String summary, final Callback cb) {
        pref.setEntries(entries);
        pref.setEntryValues(entries);
        pref.setSummary(summary);
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.34
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                pref.setSummary((CharSequence) newValue);
                Callback callback = cb;
                if (callback == null) {
                    return true;
                }
                callback.callback();
                return true;
            }
        });
    }

    private void setupCustomEndPointListForAutomotive(ListPreference preference, String[] entries, String summary) {
        preference.setEntries(entries);
        preference.setEntryValues(entries);
        preference.setSummary(summary);
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda15
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference2, Object obj) {
                return this.f$0.lambda$setupCustomEndPointListForAutomotive$10(preference2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupCustomEndPointListForAutomotive$10(Preference preference, Object obj) {
        preference.setSummary((CharSequence) obj);
        CarModeBrushViewModel.INSTANCE.invalidateCache(getApplication());
        return true;
    }

    private void setupCustomEndPointListForPodcast(final ListPreference podcast, String[] entries, String summary) {
        podcast.setEntries(entries);
        podcast.setEntryValues(entries);
        podcast.setSummary(summary);
        podcast.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.35
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                podcast.setSummary((CharSequence) newValue);
                SettingsActivity.this.handleSignOutClick();
                return true;
            }
        });
    }

    private void setupCustomEndPointEditText(final EditTextPreference pref, String summary) {
        if (pref.getText() != null) {
            pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.36
                @Override // android.preference.Preference.OnPreferenceChangeListener
                public boolean onPreferenceChange(Preference preference, Object o) {
                    EditTextPreference editTextPreference = pref;
                    editTextPreference.setText(editTextPreference.getText());
                    return true;
                }
            });
            return;
        }
        pref.setText(summary);
        pref.setSummary(summary);
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.37
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                pref.setText((String) newValue);
                pref.setSummary((CharSequence) newValue);
                return true;
            }
        });
    }

    private void setupCustomEndPointEditTextForChromecast(final EditTextPreference pref) {
        pref.setSummary(summaryForChromecast(pref.getText(), false));
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.38
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(SettingsActivity.this.summaryForChromecast((CharSequence) newValue, true));
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String summaryForChromecast(final CharSequence prefText, final boolean isOnChange) {
        String string;
        if (StringUtils.isBlank(prefText)) {
            string = getApplicationContext().getString(R.string.dmusic_setting_key_dev_chromecast_receiver_app_id_override_default);
        } else {
            string = prefText.toString();
        }
        return isOnChange ? getApplicationContext().getString(R.string.dmusic_setting_key_dev_chromecast_receiver_app_id_override_restart_note, string) : string;
    }

    private void setupDebugMetricsFileDevPreference() {
        final SwitchPreference switchPreference = (SwitchPreference) findPreference(R.string.setting_key_clear_debug_metrics);
        if (switchPreference != null) {
            switchPreference.setChecked(MetricsLogger.getDebugLoggingState());
            switchPreference.setWidgetLayoutResource(Boolean.valueOf(MetricsLogger.getDebugLoggingState()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
            switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity.39
                @Override // android.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference) {
                    String str;
                    preference.setWidgetLayoutResource(Boolean.valueOf(switchPreference.isChecked()).booleanValue() ? R.layout.action_toggleon : R.layout.action_toggleoff);
                    if (switchPreference.isChecked()) {
                        try {
                            MetricsLogger.enableDebugLogging(SettingsActivity.this);
                            str = "Enabled";
                        } catch (StorageNotAvailableException unused) {
                            switchPreference.setChecked(false);
                            str = "Error";
                        }
                    } else {
                        MetricsLogger.disableDebugLogging();
                        str = "Disable";
                    }
                    BauhausToastUtils.showTextToast(SettingsActivity.this, SettingsActivity.this.getString(R.string.dmusic_setting_clear_debug_metrics_title) + ":" + str, 0);
                    return true;
                }
            });
        }
    }

    private void setupToggleAdpToOAuthMigration() {
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(getResources().getString(R.string.setting_key_dev_toggle_adp_oauth_migration));
        if (preferenceFindPreference instanceof SwitchPreference) {
            SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
            boolean zIsEnabled = AdpToOAuthMigrationEvaluator.getInstance(getApplicationContext()).isEnabled();
            switchPreference.setChecked(zIsEnabled);
            switchPreference.setWidgetLayoutResource(zIsEnabled ? R.layout.action_toggleon : R.layout.action_toggleoff);
            switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda10
                @Override // android.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$setupToggleAdpToOAuthMigration$11(preference);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupToggleAdpToOAuthMigration$11(Preference preference) {
        if (!(preference instanceof SwitchPreference)) {
            return false;
        }
        AdpToOAuthMigrationEvaluator.getInstance(getApplicationContext()).toggle();
        Log.debug(TAG, "ADP to OAuth Migration preference toggled");
        SwitchPreference switchPreference = (SwitchPreference) preference;
        switchPreference.setWidgetLayoutResource(switchPreference.isChecked() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        BauhausToastUtils.showTextToast(this, getResources().getString(R.string.dmusic_setting_dev_restart_app), 0);
        return true;
    }

    private void setupTogglePCAFeature() {
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(getResources().getString(R.string.setting_key_dev_toggle_pca));
        if (preferenceFindPreference instanceof SwitchPreference) {
            SwitchPreference switchPreference = (SwitchPreference) preferenceFindPreference;
            boolean zIsEnabled = PCAEvaluator.getInstance(getApplicationContext()).isEnabled();
            switchPreference.setChecked(zIsEnabled);
            switchPreference.setWidgetLayoutResource(zIsEnabled ? R.layout.action_toggleon : R.layout.action_toggleoff);
            switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda6
                @Override // android.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$setupTogglePCAFeature$12(preference);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupTogglePCAFeature$12(Preference preference) {
        if (!(preference instanceof SwitchPreference)) {
            return false;
        }
        PCAEvaluator.getInstance(getApplicationContext()).toggle();
        Log.debug(TAG, "PCA Feature preference toggled");
        SwitchPreference switchPreference = (SwitchPreference) preference;
        switchPreference.setWidgetLayoutResource(switchPreference.isChecked() ? R.layout.action_toggleon : R.layout.action_toggleoff);
        BauhausToastUtils.showTextToast(this, getResources().getString(R.string.dmusic_setting_dev_restart_app), 0);
        return true;
    }

    private void dumpProtectedFiles() throws Throwable {
        String str = File.separator;
        String str2 = str + "databases" + str;
        String str3 = str2 + "CirrusMediaSource.db";
        String str4 = str2 + "recently_played.db";
        try {
            String str5 = android.os.Environment.getDataDirectory().getPath() + str + "data" + str + "com.amazon.mp3";
            File file = new File(str5 + str3);
            File file2 = new File(str5 + str4);
            File file3 = new File(str5 + "/files/logs/log.txt");
            String str6 = StorageInfo.getInstance().getExternalStorageDirectory() + str + AmazonApplication.getExternalStorageAppDirectory() + str + AmazonApplication.getExternalStorageTempDirectory();
            File file4 = new File(str6 + str3);
            File file5 = new File(str6 + str4);
            File file6 = new File(str6 + str + "log.txt");
            FileUtil.copyFile(file, file4, false);
            FileUtil.copyFile(file2, file5, false);
            FileUtil.copyFile(file3, file6, false);
            Log.debug(TAG, "Extracted " + file + " to " + file4);
        } catch (StorageInfo.DeviceNotAvailableException e) {
            Log.error(TAG, "Error application storage path", e);
        } catch (IOException e2) {
            Log.error(TAG, "Error performing I/O operation", e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void showRedownloadDialogAndRibbon() {
        /*
            r4 = this;
            com.amazon.music.subscription.UpsellStrategy r0 = new com.amazon.music.subscription.UpsellStrategy
            com.amazon.music.account.AccountManager r1 = com.amazon.music.account.AccountManagerSingleton.get()
            com.amazon.music.featureflag.FeatureFlagProvider r2 = com.amazon.mp3.capability.FeatureFlagsProvider.getFeatureFlagProvider()
            r0.<init>(r1, r2)
            com.amazon.music.subscription.UpsellStrategy$Upsell r0 = r0.getUpsellType()
            int[] r1 = com.amazon.mp3.activity.settings.SettingsActivity.AnonymousClass40.$SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L45
            r3 = 2
            if (r0 == r3) goto L43
            r3 = 3
            if (r0 == r3) goto L24
            goto L56
        L24:
            com.amazon.mp3.environment.MusicTerritory r0 = com.amazon.mp3.environment.MusicTerritory.INDIA
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L31
            goto L43
        L31:
            com.amazon.mp3.environment.MusicTerritory r0 = com.amazon.mp3.environment.MusicTerritory.CANADA
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L3e
            goto L43
        L3e:
            boolean r1 = com.amazon.mp3.prime.Branding.shouldShowPrimeBranding()
            goto L56
        L43:
            r1 = r2
            goto L56
        L45:
            boolean r0 = com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton.isInitialized()
            if (r0 == 0) goto L56
            com.amazon.music.nautilus.SubscriptionManager r0 = com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton.getInstance()
            boolean r0 = r0.isOnHomeOnlyTrial()
            if (r0 == 0) goto L56
            goto L43
        L56:
            if (r1 == 0) goto L59
            return
        L59:
            android.app.Application r0 = r4.getApplication()
            rx.Observable r0 = com.amazon.mp3.library.service.sync.PreWidevineDBUpdateOperation.getMigrationBannerObservable(r0)
            rx.Scheduler r1 = rx.android.schedulers.AndroidSchedulers.mainThread()
            rx.Observable r0 = r0.observeOn(r1)
            com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda8 r1 = new com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda8
            r1.<init>()
            com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda9 r2 = new com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda9
            r2.<init>()
            rx.Subscription r0 = r0.subscribe(r1, r2)
            r4.migrationSubscription = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.activity.settings.SettingsActivity.showRedownloadDialogAndRibbon():void");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.settings.SettingsActivity$40, reason: invalid class name */
    static /* synthetic */ class AnonymousClass40 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$activity$settings$Setting;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell;

        static {
            int[] iArr = new int[UpsellStrategy.Upsell.values().length];
            $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell = iArr;
            try {
                iArr[UpsellStrategy.Upsell.TO_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell[UpsellStrategy.Upsell.TO_HAWKFIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell[UpsellStrategy.Upsell.TO_PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Setting.values().length];
            $SwitchMap$com$amazon$mp3$activity$settings$Setting = iArr2;
            try {
                iArr2[Setting.CLEAR_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.RESYNC.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.SIGN_OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.MANAGE_SUBSCRIPTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.MANAGE_FAMILY.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.CANCEL_MEMBERSHIP.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.UPSELL.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.AUDIO_STREAMING_NETWORK.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.VIDEO_STREAMING_NETWORK.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.STREAMING_PREFERENCE.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.DOWNLOAD_QUALITY_PREFERENCE.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.DOWNLOAD_LOCATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.DOWNLOAD_MISSING_SDCARD_CONTENT.ordinal()] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.REMOVE_MISSING_SDCARD_CONTENT.ordinal()] = 14;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.CONTACT_US.ordinal()] = 15;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.LEGAL_INFO.ordinal()] = 16;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.LEGAL_INFO_IMPRINT.ordinal()] = 17;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.EXTRACT_DB_FILE.ordinal()] = 18;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.GET_HELP.ordinal()] = 19;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.MANAGE_COOKIE_PREFERENCE.ordinal()] = 20;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.LANGUAGE_PREFERENCE.ordinal()] = 21;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.EQUALIZER.ordinal()] = 22;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.WIDGET_VIEWER.ordinal()] = 23;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.PUSH_TOPIC.ordinal()] = 24;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.CAR_MODE_FAB.ordinal()] = 25;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.APP_DISPLAY_LANGUAGE.ordinal()] = 26;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$Setting[Setting.NATIVE_CRASH.ordinal()] = 27;
            } catch (NoSuchFieldError unused30) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$15(Boolean bool) {
        View view;
        if (bool.booleanValue()) {
            if (!SettingsUtil.getBooleanPref(getApplication().getApplicationContext(), R.string.setting_key_redownload_popup_dismissed)) {
                Log.debug(TAG, "Should show Redownload pop up as per shared preference.");
                WideDialogActivity.showActivity(this, WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
            }
            if (SettingsUtil.getBooleanPref(this, R.string.setting_key_redownload_ribbon_dismissed)) {
                View view2 = this.mFeatureBanner;
                if (view2 != null) {
                    view2.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.mFeatureBannerExitToAppIcon == null || (view = this.mFeatureBannerExploreIcon) == null || this.mFeatureBannerDismissIcon == null || this.mFeatureBannerMessage == null || this.mFeatureBanner == null) {
                return;
            }
            view.setVisibility(8);
            this.mFeatureBannerExitToAppIcon.setVisibility(8);
            this.mFeatureBannerDismissIcon.setVisibility(0);
            this.mFeatureBannerMessage.setText(getResources().getText(R.string.dmusic_redownload_offline_title));
            this.mFeatureBanner.setVisibility(0);
            this.mFeatureBannerDismissIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda13
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    this.f$0.lambda$showRedownloadDialogAndRibbon$13(view3);
                }
            });
            this.mFeatureBanner.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.settings.SettingsActivity$$ExternalSyntheticLambda14
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    this.f$0.lambda$showRedownloadDialogAndRibbon$14(view3);
                }
            });
            MetricsLogger.migrateExistingDownloadsRibbonViewed();
            return;
        }
        View view3 = this.mFeatureBanner;
        if (view3 != null) {
            view3.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$13(View view) {
        UserInteractionAppEvent.builder("selectDismiss").publish();
        SettingsUtil.setBooleanPref(this, R.string.setting_key_redownload_ribbon_dismissed, true);
        View view2 = this.mFeatureBanner;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$14(View view) {
        WideDialogActivity.showActivity(this, WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showRedownloadDialogAndRibbon$16(Throwable th) {
        Log.error(TAG, "Following error occurred while finding offline content: " + th.getMessage());
    }

    private String getLegalImprintUrl() {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        if (configuration != null) {
            return configuration.string("url_legal_imprint");
        }
        return null;
    }
}
