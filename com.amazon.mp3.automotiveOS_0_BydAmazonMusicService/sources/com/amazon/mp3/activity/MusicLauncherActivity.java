package com.amazon.mp3.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser;
import com.amazon.mp3.account.signintasks.BranchIdentityTask;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.ftu.FTUActivity;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.identityprofiles.IdentityActivityHelper;
import com.amazon.mp3.identityprofiles.ProfileChangedStateFlowCollector;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.inappmessaging.InAppMessagingManager;
import com.amazon.mp3.init.ForegroundLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.library.dialog.BlockChildUserFlowDialog;
import com.amazon.mp3.library.dialog.NetworkFailureWithSettingsDialog;
import com.amazon.mp3.library.provider.InactivityProvider;
import com.amazon.mp3.navigation.BranchManager;
import com.amazon.mp3.navigation.DeeplinkHandlerActivity;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.upsell.PrimeUpsellSettingsUtil;
import com.amazon.mp3.service.metrics.AppsflyerAppstartMetricLogger;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.LogFiles;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.destination.SignupDestination;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.AnonymousRealTimeMTSEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ProfileProvider;
import java.util.Optional;
import java.util.function.Function;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.StateFlow;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class MusicLauncherActivity extends Activity {
    public static final int RESULT_FINISH = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = MusicLauncherActivity.class.getSimpleName();
    private DynamicMessagingManager mDynamicMessagingManager;
    private Uri mExternalUri;
    private InAppMessagingManager mInAppMessagingManager;
    private LaunchVector mLaunchVector;
    private AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_NOT_REQUIRED;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private ProfileChangedStateFlowCollector profileChangedCollector = new ProfileChangedStateFlowCollector((StateFlow) Optional.ofNullable((ProfileProvider) Providers.INSTANCE.get(ProfileProvider.class)).map(new Function() { // from class: com.amazon.mp3.activity.MusicLauncherActivity$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ProfileProvider) obj).getProfileSwitchNotificationPublisher();
        }
    }).orElse(null), GlobalScope.INSTANCE);

    protected enum LaunchVector {
        DEVICE_ARTISTS,
        LIBRARY_CLOUD,
        LIBRARY_DEVICE,
        FIRST_TIME_USE,
        KINDLE_HOME,
        STORE_MAIN,
        PRIME_MUSIC,
        PRIME_PLAYLISTS,
        PROFILE_SELECTION,
        FIND,
        SIGN_IN,
        SSO_WELCOME_SCREEN,
        NAVIGATE_TO_OPENING_DEEPLINK;

        public static LaunchVector fromScreen(LastViewedScreenUtil.LastViewedScreen screen, LastViewedScreenUtil.LastViewedSource source) {
            int i = AnonymousClass13.$SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[screen.ordinal()];
            if (i == 1) {
                return fromLibrarySource(source);
            }
            if (i == 2) {
                return STORE_MAIN;
            }
            if (i == 3) {
                return PRIME_MUSIC;
            }
            if (i == 4) {
                return FIND;
            }
            return LIBRARY_CLOUD;
        }

        private static LaunchVector fromLibrarySource(LastViewedScreenUtil.LastViewedSource source) {
            int i = AnonymousClass13.$SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedSource[source.ordinal()];
            if (i == 1) {
                return LIBRARY_CLOUD;
            }
            if (i == 2) {
                return LIBRARY_DEVICE;
            }
            return LIBRARY_CLOUD;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExternalUriAndUpdateDeeplinkAssociateTag(final Uri uri, final String deeplinkSourceName) {
        this.mExternalUri = uri;
        if (uri != null) {
            Log.debug(TAG, String.format("Saving deep link URI from %s: %s", deeplinkSourceName, uri.toString()));
            DeeplinkHandlerActivity.appOpenedFromDeeplink();
            AssociateTag.getInstance(this).setAssociateTagFromDeeplink(this.mExternalUri);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context applicationContext = getApplicationContext();
        new BranchManager().getUriFromBranchReferringParameters(this).subscribe(new Action1<Uri>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.1
            @Override // rx.functions.Action1
            public void call(final Uri uri) {
                Uri referrer = PlatformUtil.isVersionOrGreater(22) ? MusicLauncherActivity.this.getReferrer() : null;
                MetricsLogger.sendDeeplinkIntentReceivedFlexEvent(uri, referrer, true);
                if (DeeplinkHandlerActivity.getDeeplinkRefererUri() != null) {
                    referrer = DeeplinkHandlerActivity.getDeeplinkRefererUri();
                }
                Uri baseDeeplinkUrl = DeeplinksManager.get(MusicLauncherActivity.this).getBaseDeeplinkUrl(uri);
                DeeplinkHandlerActivity.setDeeplinkUri(baseDeeplinkUrl);
                DeeplinkHandlerActivity.setDeeplinkRefererUri(referrer);
                MetricsHolder.getManager().handleEvent(new AnonymousRealTimeMTSEvent(MetricsLogger.createDeeplinkInteractionEvent(baseDeeplinkUrl, baseDeeplinkUrl, referrer, "deeplinkInvoked", DeeplinkHandlerActivity.getBranchLink())));
                MusicLauncherActivity.this.setExternalUriAndUpdateDeeplinkAssociateTag(uri, "Branch SDK");
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.2
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                if (throwable instanceof BranchManager.BranchReturnedNoUriException) {
                    Log.debug(MusicLauncherActivity.TAG, "Branch returned no data for this session. This is expected if app was already installed on this device at time of deep link click.Message : %s", throwable.getMessage());
                }
            }
        });
        if (!StorageInfo.canOpenDatabase(applicationContext)) {
            StorageInfo.createSQLOutOfSpaceDialog(applicationContext);
            finish();
            return;
        }
        this.profileChangedCollector.collect(this);
        new InitializationHandler(new ForegroundLaunchInitializationTask(this, new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(this)))).initializeOnNewThread();
        this.compositeSubscription.add(BootstrapSingletonTask.get(applicationContext).registerUserObserver().observeOn(Schedulers.computation()).map(new Func1<Object, Boolean>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // rx.functions.Func1
            public Boolean call(final Object o) {
                MusicLauncherActivity.this.mInAppMessagingManager = new InAppMessagingManager(applicationContext);
                return Boolean.valueOf(InAppMessagingManager.supportsInAppMessaging());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.3
            @Override // rx.functions.Action1
            public void call(final Boolean supportsInAppMessaging) {
                if (MusicLauncherActivity.this.mInAppMessagingManager == null || !supportsInAppMessaging.booleanValue()) {
                    return;
                }
                MusicLauncherActivity.this.mInAppMessagingManager.loadInAppMessage();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.4
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(MusicLauncherActivity.TAG, "Not initializing IAM due to BootstrapSingletonTask error: " + throwable.getMessage());
            }
        }));
        this.compositeSubscription.add(BootstrapSingletonTask.get(applicationContext).registerUserObserver().observeOn(Schedulers.computation()).map(new Func1<Object, Boolean>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // rx.functions.Func1
            public Boolean call(final Object o) {
                MusicLauncherActivity.this.mDynamicMessagingManager = new DynamicMessagingManager(applicationContext);
                return Boolean.valueOf(DynamicMessagingManager.isDynamicMessagingSupported());
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.6
            @Override // rx.functions.Action1
            public void call(final Boolean supportsDynamicMessaging) {
                if (MusicLauncherActivity.this.mDynamicMessagingManager == null || !supportsDynamicMessaging.booleanValue()) {
                    return;
                }
                MusicLauncherActivity.this.mDynamicMessagingManager.initializeDynMsg();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.7
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.error(MusicLauncherActivity.TAG, "Not initializing Dynamic Message due to BootstrapSingletonTask error: ", throwable);
            }
        }));
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (defaultSharedPreferences.getBoolean("com.amazon.mp3_key_is_first_browse_home_build", true)) {
            InactivityProvider.reset(this);
            defaultSharedPreferences.edit().putBoolean("com.amazon.mp3_key_is_first_browse_home_build", false).apply();
        }
        setContentView(R.layout.upsell_container);
        SettingsUtil.updateSettings(this);
        setExternalUriAndUpdateDeeplinkAssociateTag(getExternalUri(getIntent()), "App Launch");
        LogFiles.uploadDeviceLogsIfNeeded(applicationContext, defaultSharedPreferences);
        Curate.onStart(applicationContext);
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.activity.MusicLauncherActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MusicLauncherActivity.lambda$onCreate$0(applicationContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreate$0(Context context) {
        if (context != null) {
            AppsflyerAppstartMetricLogger.logAppStartEvent(context);
            if (!AccountCredentialUtil.get(context).isSignInComplete() || BranchIdentityTask.isBranchUserIdentified()) {
                return;
            }
            Log.info(TAG, "Setting Branch identity for signed in customer");
            BranchIdentityTask.setBranchIdentity(context);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        onIntentReceived(intent);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.compositeSubscription.clear();
    }

    protected void handleNewIntent(final Intent intent) {
        setIntent(intent);
        if (intent == null) {
            finish();
            return;
        }
        String str = TAG;
        Log.debug(str, "Music launched with action: %s, flags: %s. - vector: %s", intent.getAction(), Integer.valueOf(intent.getFlags()), this.mLaunchVector);
        if (ChildUserUtil.INSTANCE.isChildUser(getApplicationContext())) {
            Log.verbose(str, "Applying hawkfire benefits for child user");
            this.state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIBING;
            if (isHawkfireSubscriptionCompletedForChildUser()) {
                return;
            }
            applyHawkFireBenefitsForChildUser();
            return;
        }
        handleNewNonNullIntent(intent, false);
    }

    private boolean isHawkfireSubscriptionCompletedForChildUser() {
        AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus hawkfireBenefitStatus = this.state;
        return hawkfireBenefitStatus == AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_NOT_REQUIRED || hawkfireBenefitStatus == AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_SUCCESS;
    }

    private void applyHawkFireBenefitsForChildUser() {
        new AddHawkFireBenefitsForChildUser(getApplicationContext(), new AddHawkFireBenefitsForChildUser.AddHawkFireListener() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.9
            @Override // com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser.AddHawkFireListener
            public void onFailure(boolean isAccountFull) {
                Log.error(MusicLauncherActivity.TAG, "Adding Hawkfire benefits to child user failed.");
                MusicLauncherActivity.this.state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_FAILED_WITH_ERROR;
                MusicLauncherActivity musicLauncherActivity = MusicLauncherActivity.this;
                musicLauncherActivity.handleNewNonNullIntent(musicLauncherActivity.getIntent(), isAccountFull);
            }

            @Override // com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser.AddHawkFireListener
            public void onSuccess() {
                Log.verbose(MusicLauncherActivity.TAG, "Hawkfire benefits applied successfully to child user.");
                MusicLauncherActivity.this.state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_SUCCESS;
                MusicLauncherActivity musicLauncherActivity = MusicLauncherActivity.this;
                musicLauncherActivity.handleNewNonNullIntent(musicLauncherActivity.getIntent(), false);
            }
        }).execute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewNonNullIntent(Intent intent, boolean isAccountFull) {
        LaunchVector launchVectorLaunchVectorWhenSignedIn;
        if (this.state == AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_FAILED_WITH_ERROR) {
            showCannotProceedDialog(isAccountFull);
            return;
        }
        LaunchVector launchVector = this.mLaunchVector;
        if (launchVector != null) {
            handleLaunch(launchVector);
            this.mLaunchVector = null;
            return;
        }
        FTUSettingsUtil.setUpgradeCustomer(this, isSignedIn());
        if (AccountCredentialUtil.get(this).isSignInComplete() || !AccountCredentialStorage.get(this).shouldPerformSignInTasks()) {
            WebTarget webTarget = (WebTarget) intent.getSerializableExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
            if (webTarget != null) {
                if (!FTUActivity.start(this, webTarget)) {
                    onActivityResult(FTUActivity.REQUEST_CODE, 0, null);
                    handleLaunch(this.mLaunchVector);
                }
                intent.removeExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
                return;
            }
            launchVectorLaunchVectorWhenSignedIn = launchVectorWhenSignedIn();
        } else if (ConnectivityUtil.hasAnyInternetConnection()) {
            launchVectorLaunchVectorWhenSignedIn = LaunchVector.SIGN_IN;
        } else {
            showNetworkFailureDialog();
            return;
        }
        handleLaunch(launchVectorLaunchVectorWhenSignedIn);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        onIntentReceived(getIntent());
    }

    private void onIntentReceived(final Intent intent) {
        if (AccountCredentialUtil.get(this).isSignInComplete()) {
            bootstrapHandleNewIntent(intent);
        } else {
            handleNewIntent(intent);
        }
    }

    private void bootstrapHandleNewIntent(final Intent intent) {
        this.compositeSubscription.add(BootstrapSingletonTask.get(getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.10
            @Override // rx.functions.Action1
            public void call(final Object o) {
                MusicLauncherActivity.this.handleNewIntent(intent);
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.11
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(MusicLauncherActivity.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
            }
        }));
    }

    private LaunchVector launchVectorWhenSignedIn() {
        Uri uri;
        if (!ConnectivityUtil.hasAnyInternetConnection() && !InactivityProvider.isInactive(this)) {
            return launchVectorFromLastScreen();
        }
        if (!AccountRequestUtil.isAccountValid(this) || (!ConnectivityUtil.hasAnyInternetConnection() && InactivityProvider.isInactive(this))) {
            return LaunchVector.LIBRARY_DEVICE;
        }
        if (IdentityActivityHelper.isProfileNotSelected(getApplicationContext())) {
            return LaunchVector.PROFILE_SELECTION;
        }
        if (!FTUSettingsUtil.isUserFTUByBusinessDefinition(getApplicationContext()) && (uri = this.mExternalUri) != null) {
            Log.verbose(TAG, "Handling external deep link URI: %s ", uri.toString());
            return LaunchVector.NAVIGATE_TO_OPENING_DEEPLINK;
        }
        if (!FTUSettingsUtil.isUserFTUByBusinessDefinition(getApplicationContext()) && !InactivityProvider.isInactive(this)) {
            return launchVectorFromLastScreen();
        }
        if (!FTUSettingsUtil.isUserFTUByBusinessDefinition(getApplicationContext()) && InactivityProvider.isInactive(this)) {
            return getLaunchVectorForMarketplace(LaunchVector.PRIME_MUSIC, launchVectorForNonPrimeMarket());
        }
        if (FTUSettingsUtil.isUserFTUByBusinessDefinition(getApplicationContext()) && this.mExternalUri != null) {
            FTUSettingsUtil.setShowIAMForFTUDeeplink(true);
            FTUSettingsUtil.setSuppressHomeForFTUDeeplink(true);
        }
        FTUSettingsUtil.setFTUShown(getApplicationContext());
        Uri uri2 = this.mExternalUri;
        if (uri2 != null) {
            Log.verbose(TAG, "Handling external deep link URI: %s ", uri2.toString());
            return LaunchVector.NAVIGATE_TO_OPENING_DEEPLINK;
        }
        return getLaunchVectorForMarketplace(LaunchVector.FIRST_TIME_USE, launchVectorForNonPrimeMarket());
    }

    private LaunchVector getLaunchVectorForMarketplace(final LaunchVector launchVectorForPrimeMarket, final LaunchVector launchVectorForNonPrimeMarket) {
        return AmazonApplication.getCapabilities().shouldBrowseBeSupported() ? launchVectorForPrimeMarket : launchVectorForNonPrimeMarket;
    }

    private LaunchVector launchVectorForNonPrimeMarket() {
        if (customerHasCloudContent()) {
            return LaunchVector.LIBRARY_CLOUD;
        }
        if (customerHasDeviceContent()) {
            return LaunchVector.DEVICE_ARTISTS;
        }
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            return LaunchVector.LIBRARY_DEVICE;
        }
        return LaunchVector.STORE_MAIN;
    }

    private LaunchVector launchVectorFromLastScreen() {
        return LaunchVector.fromScreen(LastViewedScreenUtil.getLastViewedScreen(this), LastViewedScreenUtil.getLastViewedSource(this, LastViewedScreenUtil.LastViewedSource.CLOUD));
    }

    protected void handleLaunch(LaunchVector vector) {
        if (vector == null) {
            finish();
        }
        switch (AnonymousClass13.$SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[vector.ordinal()]) {
            case 1:
                if (!FTUActivity.start(this, null)) {
                    onActivityResult(FTUActivity.REQUEST_CODE, 0, null);
                    handleLaunch(this.mLaunchVector);
                }
                break;
            case 2:
                finish();
                break;
            case 3:
                Intent libraryCloudIntent = getLibraryCloudIntent();
                checkCallingIntent(libraryCloudIntent);
                startActivity(libraryCloudIntent);
                finish();
                break;
            case 4:
                navigateToLibraryDevice();
                finish();
                break;
            case 5:
                Intent libraryDeviceIntent = getLibraryDeviceIntent();
                libraryDeviceIntent.putExtra("com.amazon.mp3.library.fragment.EXTRA_TAB_POSITION_TO_OPEN", 2);
                checkCallingIntent(libraryDeviceIntent);
                startActivity(libraryDeviceIntent);
                finish();
                break;
            case 6:
                Intent storeIntent = getStoreIntent();
                checkCallingIntent(storeIntent);
                startActivity(storeIntent);
                finish();
                break;
            case 7:
                navigateToPrimeMusic();
                finish();
                break;
            case 8:
                Intent playlistsIntent = PrimeActivityFactory.getPlaylistsIntent(this);
                checkCallingIntent(playlistsIntent);
                startActivity(playlistsIntent);
                finish();
                break;
            case 9:
                Intent intentToProfilePicker = IdentityActivityHelper.getIntentToProfilePicker(this);
                checkCallingIntent(intentToProfilePicker);
                startActivity(intentToProfilePicker);
                break;
            case 10:
                navigateToFind();
                finish();
                break;
            case 11:
                startActivityForResult(new Intent(getApplicationContext(), (Class<?>) SignInFlowLauncherActivity.class), SignInFlowLauncherActivity.REQUEST_CODE);
                break;
            case 12:
                if (!DeviceAuthorizationManager.getInstance().isAuthorized()) {
                    startDeviceAuthorizationActivity();
                } else {
                    handleDeeplinkHelper(this);
                }
                break;
            default:
                finish();
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.MusicLauncherActivity$13, reason: invalid class name */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedSource;

        static {
            int[] iArr = new int[LaunchVector.values().length];
            $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector = iArr;
            try {
                iArr[LaunchVector.FIRST_TIME_USE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.KINDLE_HOME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.LIBRARY_CLOUD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.LIBRARY_DEVICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.DEVICE_ARTISTS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.STORE_MAIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.PRIME_MUSIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.PRIME_PLAYLISTS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.PROFILE_SELECTION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.FIND.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.SIGN_IN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$MusicLauncherActivity$LaunchVector[LaunchVector.NAVIGATE_TO_OPENING_DEEPLINK.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[LastViewedScreenUtil.LastViewedSource.values().length];
            $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedSource = iArr2;
            try {
                iArr2[LastViewedScreenUtil.LastViewedSource.CLOUD.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedSource[LastViewedScreenUtil.LastViewedSource.DEVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr3 = new int[LastViewedScreenUtil.LastViewedScreen.values().length];
            $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen = iArr3;
            try {
                iArr3[LastViewedScreenUtil.LastViewedScreen.LIBRARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[LastViewedScreenUtil.LastViewedScreen.STORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[LastViewedScreenUtil.LastViewedScreen.SEARCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    static class PostDeeplinkHandlingTask implements Runnable {
        private Destination destination;

        PostDeeplinkHandlingTask(final Destination destination) {
            this.destination = destination;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.destination.isSuccess()) {
                MetricsLogger.sendDeeplinkInteractionEvent(DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkRefererUri(), "deeplinkLanding", DeeplinkHandlerActivity.getBranchLink());
            } else {
                MetricsLogger.sendDeeplinkInteractionEvent(DeeplinkHandlerActivity.DEFAULT_FALLBACK_URI, DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkRefererUri(), "deeplinkLanding", DeeplinkHandlerActivity.getBranchLink());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeeplinkHelper(MusicLauncherActivity musicLauncherActivity) {
        try {
            DeeplinksManager deeplinksManager = DeeplinksManager.get(musicLauncherActivity);
            Destination destination = deeplinksManager.getDestination(deeplinksManager.getSanitizedExternalUri(this.mExternalUri));
            deeplinksManager.handle(destination, new PostDeeplinkHandlingTask(destination));
            if (isSignupDeeplink(this.mExternalUri)) {
                FTUSettingsUtil.setFTUShown(getApplicationContext());
            }
            finish();
        } catch (Exception e) {
            Log.error(TAG, "Cannot open deeplink, proceeding with usual route", e);
            MetricsLogger.sendDeeplinkInteractionEvent(DeeplinkHandlerActivity.DEFAULT_FALLBACK_URI, DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkRefererUri(), "deeplinkLanding", DeeplinkHandlerActivity.getBranchLink());
            handleLaunch(getLaunchVectorForMarketplace(LaunchVector.PRIME_MUSIC, launchVectorForNonPrimeMarket()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x000f, code lost:
    
        if (r3 != 2) goto L25;
     */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onActivityResult(int r2, int r3, android.content.Intent r4) {
        /*
            r1 = this;
            int r4 = com.amazon.mp3.ftu.FTUActivity.REQUEST_CODE
            if (r2 != r4) goto L4b
            com.amazon.mp3.activity.MusicLauncherActivity$LaunchVector r2 = com.amazon.mp3.activity.MusicLauncherActivity.LaunchVector.LIBRARY_CLOUD
            r4 = -1
            if (r3 == r4) goto L2e
            if (r3 == 0) goto L2e
            r4 = 1
            if (r3 == r4) goto L12
            r4 = 2
            if (r3 == r4) goto L23
            goto L48
        L12:
            android.content.Intent r2 = r1.getIntent()
            r3 = 0
            java.lang.String r4 = "com.amazon.mp3_key_back_exits_app"
            boolean r2 = r2.getBooleanExtra(r4, r3)
            if (r2 == 0) goto L23
            r1.finish()
            return
        L23:
            android.content.Intent r2 = r1.getLibraryDeviceIntent()
            r1.startActivity(r2)
            r1.finish()
            return
        L2e:
            android.net.Uri r2 = r1.mExternalUri
            if (r2 == 0) goto L35
            com.amazon.mp3.activity.MusicLauncherActivity$LaunchVector r2 = com.amazon.mp3.activity.MusicLauncherActivity.LaunchVector.NAVIGATE_TO_OPENING_DEEPLINK
            goto L48
        L35:
            boolean r2 = com.amazon.mp3.ftu.FTUSettingsUtil.customerSkippedATC(r1)
            if (r2 == 0) goto L3e
            com.amazon.mp3.activity.MusicLauncherActivity$LaunchVector r2 = com.amazon.mp3.activity.MusicLauncherActivity.LaunchVector.FIND
            goto L48
        L3e:
            com.amazon.mp3.activity.MusicLauncherActivity$LaunchVector r2 = com.amazon.mp3.activity.MusicLauncherActivity.LaunchVector.PRIME_MUSIC
            com.amazon.mp3.activity.MusicLauncherActivity$LaunchVector r3 = r1.launchVectorForNonPrimeMarket()
            com.amazon.mp3.activity.MusicLauncherActivity$LaunchVector r2 = r1.getLaunchVectorForMarketplace(r2, r3)
        L48:
            r1.mLaunchVector = r2
            goto L70
        L4b:
            int r4 = com.amazon.mp3.account.activity.SignInFlowLauncherActivity.REQUEST_CODE
            if (r2 != r4) goto L70
            java.lang.String r2 = com.amazon.mp3.activity.MusicLauncherActivity.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Returning to onActivityResult after SignInFlowLauncherActivity with result code: "
            r4.append(r0)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.amazon.mp3.util.Log.info(r2, r4)
            if (r3 == 0) goto L6d
            int r2 = com.amazon.mp3.activity.MusicLauncherActivity.RESULT_FINISH
            if (r3 != r2) goto L6c
            goto L6d
        L6c:
            return
        L6d:
            r1.finish()
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.activity.MusicLauncherActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    private void navigateToLibraryDevice() {
        Intent libraryDeviceIntent = getLibraryDeviceIntent();
        checkCallingIntent(libraryDeviceIntent);
        startActivity(libraryDeviceIntent);
    }

    private void navigateToFind() {
        Intent findIntent = SearchTabFragmentHost.getFindIntent(this, null);
        checkCallingIntent(findIntent);
        startActivity(findIntent);
    }

    private void navigateToPrimeMusic() {
        Intent primeSectionIntent = PrimeActivityFactory.getPrimeSectionIntent(this);
        checkCallingIntent(primeSectionIntent);
        startActivity(primeSectionIntent);
    }

    private void startDeviceAuthorizationActivity() {
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.MusicLauncherActivity.12
            @Override // java.lang.Runnable
            public void run() {
                Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(MusicLauncherActivity.this.getApplicationContext());
                if (deviceAuthorizationIntent != null) {
                    MusicLauncherActivity.this.startActivity(deviceAuthorizationIntent);
                }
                MusicLauncherActivity musicLauncherActivity = MusicLauncherActivity.this;
                musicLauncherActivity.handleDeeplinkHelper(musicLauncherActivity);
                MusicLauncherActivity.this.finish();
            }
        });
    }

    private Uri getExternalUri(final Intent intent) {
        String string;
        if (intent.getExtras() == null || (string = intent.getExtras().getString("com.amazon.mp3.extra.EXTERNAL_URI")) == null) {
            return null;
        }
        return Uri.parse(string);
    }

    private void checkCallingIntent(Intent intent) {
        if (getIntent().getBooleanExtra("com.amazon.mp3.launch.LOADCURRENT", false)) {
            intent.putExtra("com.amazon.mp3.launch.LOADCURRENT", true);
        }
    }

    private boolean isSignedIn() {
        return AccountCredentialUtil.get(this).isSignedIn();
    }

    private boolean customerHasCloudContent() {
        Boolean boolHasCloudTracks = PrimeUpsellSettingsUtil.hasCloudTracks(this);
        return boolHasCloudTracks != null && boolHasCloudTracks.booleanValue();
    }

    private boolean customerHasDeviceContent() {
        Boolean boolHasDeviceTracks = PrimeUpsellSettingsUtil.hasDeviceTracks(this);
        return boolHasDeviceTracks != null && boolHasDeviceTracks.booleanValue();
    }

    private Intent getLibraryIntent(String cirrusMediaSource) {
        return MusicHomeActivity.getStartIntent(this).putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName()).putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", cirrusMediaSource);
    }

    private Intent getLibraryCloudIntent() {
        return getLibraryIntent("cirrus");
    }

    private Intent getLibraryDeviceIntent() {
        return getLibraryIntent("cirrus-local");
    }

    private Intent getStoreIntent() {
        return HTMLStoreActivityFactory.getStartIntent(this);
    }

    private void showNetworkFailureDialog() {
        startActivity(new Intent(this, (Class<?>) NetworkFailureWithSettingsDialog.class));
        finish();
    }

    private void showCannotProceedDialog(boolean isAccountFull) {
        Intent intent = new Intent(this, (Class<?>) BlockChildUserFlowDialog.class);
        intent.putExtra("accountFull", isAccountFull);
        startActivity(intent);
        finish();
    }

    private boolean isSignupDeeplink(final Uri externalUri) {
        if (externalUri == null) {
            return false;
        }
        try {
            return DeeplinksManager.get(this).getDestination(externalUri) instanceof SignupDestination;
        } catch (Exception e) {
            Log.error(TAG, "Could not get destination from url", e);
            return false;
        }
    }
}
