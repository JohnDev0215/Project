package com.amazon.mp3.activity;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.activity.SsoWelcomeActivity;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.auto.waze.WazeNavigationStatusListener;
import com.amazon.mp3.auto.waze.WazeSdkConnection;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bluetooth.BluetoothKeyHandler;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.dialog.StorageDialog;
import com.amazon.mp3.dialog.TryOurWidgetDialogManager;
import com.amazon.mp3.dialog.fragment.GhostListeningDialogFragment;
import com.amazon.mp3.dialog.fragment.PrimeContentExpiredDialogFragment;
import com.amazon.mp3.ftu.FTUActivity;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.hawkfire.upsell.ConcurrentStreamingUnlimitedFamilyUpsell;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.dialog.CirrusErrorDialogs;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.library.provider.source.local.DeleteTracksContract$Presenter;
import com.amazon.mp3.library.provider.source.local.DeleteTracksContract$View;
import com.amazon.mp3.library.provider.source.local.DeleteTracksPresenter;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.navigation.FindDestinationHandler;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.ConcurrencyErrorDialogActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.fragment.PersistentPlayerFragment;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.exception.PlaybackErrorHandler;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.service.ComponentCheckService;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.CampaignMetrics;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.mp3.voice.wakeword.WakeWordHelper;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.ghostlistening.GhostListeningDialog;
import com.amazon.music.inappmessaging.external.DynamicMessageFragmentFactory;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.GhostListeningException;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.NetworkUnavailableException;
import com.amazon.music.media.playback.NoPlayableContentException;
import com.amazon.music.media.playback.OnPlaybackErrorListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.voice.VoiceManager;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.waze.sdk.WazeNavigationBar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseActivity extends JobSessionActivity implements DeleteTracksContract$View, WazeNavigationStatusListener {
    private static final long MILLIS_IN_A_WEEK = TimeUnit.DAYS.toMillis(7);
    private static final String TAG = "BaseActivity";
    private static boolean sIsFullMemory = false;
    private static boolean sIsLowMemory = false;
    private DeleteTracksContract$Presenter deleteTracksPresenter;
    private int mCurrentTheme;
    private CirrusErrorDialogs mErrorDialogs;
    protected boolean mIsAlexaEnabled;
    private boolean mIsDestroyed;
    private ViewTreeObserver.OnGlobalFocusChangeListener mOnGlobalFocusChangeListener;
    private final OnPlaybackErrorListener mOnPlaybackErrorListener;
    private final OnPlaybackErrorListener mPersistentOnPlaybackErrorListener;
    private PersistentPlayerFragment mPlayer;
    private TryOurWidgetDialogManager.TryOurWidgetBroadcastReceiver mTryOurWidgetBroadcastReceiver;
    private Subscription mUserSubscription;
    private WazeNavigationBar mWazeNavigationBar;
    private int mOrientation = 0;
    private final HashMap<Integer, Dialog> mFragmentDialogs = new HashMap<>();
    private boolean mFinishOnResume = false;
    protected boolean isShowErrorDialog = true;
    private boolean mTryOurWidgetBroadcastReceiverRegistered = false;
    private BroadcastReceiver mCloudCirrusErrorReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.BaseActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.mErrorDialogs.checkForErrorsAndShowDialogIfNecessary();
        }
    };
    private final BroadcastReceiver mDynamicMessageReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.BaseActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DialogFragment dialogFragmentCreateDynamicMessageFragment = DynamicMessageFragmentFactory.createDynamicMessageFragment(intent.getExtras(), Boolean.TRUE);
            if (dialogFragmentCreateDynamicMessageFragment != null) {
                try {
                    dialogFragmentCreateDynamicMessageFragment.show(BaseActivity.this.getSupportFragmentManager(), (String) null);
                } catch (IllegalStateException e) {
                    Log.info(BaseActivity.TAG, "IllegalStateException occurs when trying to show dynamic message dialog: ", e.toString());
                }
            }
        }
    };

    protected boolean automaticallyCheckForUpdates() {
        return true;
    }

    public boolean shouldDisplayBluemoonExceptions() {
        return false;
    }

    public BaseActivity() {
        this.mOnPlaybackErrorListener = new OnPlaybackErrorListenerImpl();
        this.mPersistentOnPlaybackErrorListener = new PersistentOnPlaybackErrorListenerImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGhostListeningDialog() {
        this.mUserSubscription = BootstrapSingletonTask.get(this).registerUserObserver().subscribe(new Action1<Void>() { // from class: com.amazon.mp3.activity.BaseActivity.3
            @Override // rx.functions.Action1
            public void call(Void aVoid) {
                if (!GhostListeningDialogFragment.shouldShow() || BaseActivity.this.isPaused()) {
                    return;
                }
                GhostListeningDialogFragment.setShouldShow(false);
                new GhostListeningDialogFragment().show(BaseActivity.this.getSupportFragmentManager(), GhostListeningDialog.TAG);
                NavigationAppEvent.builder("ghostListeningDialog").withOrientation(ScreenUtil.getScreenOrientation(BaseActivity.this)).publish();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.BaseActivity.4
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                Log.debug(BaseActivity.TAG, "Error displaying ghost listening dialog: " + throwable);
            }
        });
    }

    public static void setLowMemory(Class<?> c, boolean value) {
        if (!StorageInfo.class.equals(c) && !LauncherActivity.class.equals(c) && !IntentProxyActivity.class.equals(c) && !ExternalLauncherActivity.class.equals(c)) {
            throw new RuntimeException(new IllegalAccessException("setLowMemory can only be accessed by StorageInfo, LaunchActivity, ExternalLauncherActivity and IntentProxyActivity"));
        }
        sIsLowMemory = value;
    }

    public static void setFullMemory(Class<?> c, boolean value) {
        if (!StorageInfo.class.equals(c) && !LauncherActivity.class.equals(c) && !IntentProxyActivity.class.equals(c) && !ExternalLauncherActivity.class.equals(c)) {
            throw new RuntimeException(new IllegalAccessException("setFullMemory can only be accessed by StorageInfo, LaunchActivity, ExternalLauncherActivity and IntentProxyActivity"));
        }
        sIsFullMemory = value;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int i = this.mOrientation;
        int i2 = newConfig.orientation;
        if (i != i2) {
            this.mOrientation = i2;
        }
        if (this.mCurrentTheme == 2132083459) {
            if (i2 == 1) {
                setContentViewLayoutMargin(getResources().getInteger(R.integer.layout_vertical_margin_percent_portrait), getResources().getInteger(R.integer.layout_horizontal_margin_percent_portrait));
            } else {
                setContentViewLayoutMargin(getResources().getInteger(R.integer.layout_vertical_margin_percent_landscape), getResources().getInteger(R.integer.layout_horizontal_margin_percent_landscape));
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        PersistentPlayerFragment persistentPlayerFragment = this.mPlayer;
        if (persistentPlayerFragment != null) {
            persistentPlayerFragment.onWindowFocusChanged(hasFocus);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (BluetoothKeyHandler.shouldHandleBluetoothKeyEvent(event, getCurrentFocus())) {
            return onBluetoothKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean onBluetoothKeyDown(int keyCode, KeyEvent event) {
        String action = BluetoothKeyHandler.getAction(keyCode);
        if (action != null) {
            if (action.equals("https://music.amazon.com/search?source=keyCodeSearch")) {
                if (AmazonApplication.getCapabilities().isBluetoothSearchEnabled()) {
                    onBluetoothSearchKeyDown(keyCode, event, action);
                }
            } else {
                Intent intent = new Intent(action);
                intent.putExtra("com.amazon.mp3.playstate_mutation_reason", PlayStateMutationReason.BLUETOOTH_KEY_CONTROL);
                intent.setPackage("com.amazon.mp3");
                startService(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean onBluetoothSearchKeyDown(final int keyCode, final KeyEvent event, final String action) {
        try {
            new FindDestinationHandler().navigateTo((Context) this, new FindDestination(Uri.parse(action)));
            return true;
        } catch (Exception e) {
            Log.debug(TAG, "onKeyDown() unable to handle FindDestination: %s", e.getMessage());
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            super.unregisterReceiver(receiver);
        } catch (IllegalArgumentException unused) {
            Log.debug(TAG, "IllegalArgumentException thrown by unregisterReceiver()");
        } catch (IllegalStateException unused2) {
            Log.debug(TAG, "IllegalStateException thrown by unregisterReceiver()");
        }
    }

    @Deprecated
    public void showFragmentDialog(int id, Dialog dialog) {
        try {
            removeDialog(id);
        } catch (Exception e) {
            Log.debug(TAG, "showFragmentDialog threw: %s", e.getMessage());
        }
        this.mFragmentDialogs.put(Integer.valueOf(id), dialog);
        showDialog(id);
    }

    @Deprecated
    public void removeFragmentDialog(int id) {
        this.mFragmentDialogs.remove(Integer.valueOf(id));
        try {
            removeDialog(id);
        } catch (Exception e) {
            Log.debug(TAG, "removeFragmentDialog threw: %s", e.getMessage());
        }
    }

    public PersistentPlayerFragment getPersistentPlayer() {
        return this.mPlayer;
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(final int id) {
        Dialog dialogRemove = this.mFragmentDialogs.remove(Integer.valueOf(id));
        return dialogRemove != null ? dialogRemove : super.onCreateDialog(id);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recordCampaignAppOpen();
    }

    private void recordCampaignAppOpen() {
        String source = CampaignMetrics.loadCampaignReferrer(this).getSource();
        if ("".equals(source)) {
            return;
        }
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            BaseMetricsRecorder.getMetricsRecorder(getApplicationContext()).getReferralAndCampaignMetrics().recordCampaignOpen(source, packageInfo.firstInstallTime == packageInfo.lastUpdateTime);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mErrorDialogs = new CirrusErrorDialogs(this);
        recordCampaignAppOpen();
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).addOnPlaybackErrorListener(this.mPersistentOnPlaybackErrorListener);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized()) {
            VoiceManager voiceManagerSingleton = VoiceManagerSingleton.getInstance();
            if (voiceManagerSingleton.isAlexaUiVisible()) {
                voiceManagerSingleton.onSaveInstanceState();
            }
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (shouldHidePersistentPlayerOnSoftKeyboard()) {
            this.mOnGlobalFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.amazon.mp3.activity.BaseActivity.5
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    BaseActivity.this.updatePersistentPlayerVisibility(newFocus);
                }
            };
            getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(this.mOnGlobalFocusChangeListener);
        }
        boolean booleanExtra = getIntent().getBooleanExtra("com.amazon.mp3.launch.EXTERNAL", false);
        if (!FTUSettingsUtil.isUserFTUByBusinessDefinition(this) || booleanExtra) {
            AccountCredentialUtil.get(this).synchronizeWithDeviceRegistration(this, shouldEnsureRegistration());
        }
        BaseMetricsRecorder.getMetricsRecorder(getApplicationContext()).getFirstTimeUseMetrics().recordFirstAppOpen();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized() && !isChangingConfigurations() && VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
            VoiceManagerSingleton.getInstance().softCloseAlexaUi();
        }
        AmazonApplication.getApplication().setPausedState(true);
        this.mErrorDialogs.onPause();
        CacheManager.getInstance().onActivityPaused();
        unregisterTryOurWidgetReceiver();
        unregisterReceiver(this.mCloudCirrusErrorReceiver);
        unregisterReceiver(this.mDynamicMessageReceiver);
        PlaybackControllerProvider.getController(ControlSource.APP_UI).removeOnPlaybackErrorListener(this.mOnPlaybackErrorListener);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        if (sIsFullMemory) {
            Log.info(TAG, "Finishing %s due to full memory", getClass().getName());
            finish();
        }
        boolean booleanExtra = getIntent().getBooleanExtra("com.amazon.mp3.launch.EXTERNAL", false);
        if (FTUSettingsUtil.isUserFTUByBusinessDefinition(this) && !booleanExtra) {
            FTUActivity.start(this, null);
        }
        AmazonApplication.getApplication().setPausedState(false);
        this.mErrorDialogs.onResume();
        registerTryOurWidgetReceiver();
        CacheManager.getInstance().onActivityResumed();
        checkStorageSpace();
        super.onResume();
        if (automaticallyCheckForUpdates()) {
            UpdateCheckActivity.showIfUpdateIsAvailable(this);
        }
        updatePersistentPlayerVisibility(getCurrentFocus());
        FailedToAddPrimeTrackToastUtil.showToastIfNecessaryAndPossible(this);
        if (GhostListeningDialogFragment.shouldShow()) {
            showGhostListeningDialog();
        }
        registerReceiver(this.mDynamicMessageReceiver, new IntentFilter(InAppMessageBroadcasts.DISPLAY_BLOCKING_DYNAMIC_MESSAGE));
        registerReceiver(this.mCloudCirrusErrorReceiver, new IntentFilter("com.amazon.mp3.ACTION_CIRRUS_ACCOUNT_ERROR"));
        initializeSubscriptionManager();
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).addOnPlaybackErrorListener(this.mOnPlaybackErrorListener);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalFocusChangeListener(this.mOnGlobalFocusChangeListener);
        Subscription subscription = this.mUserSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.mIsDestroyed = true;
        this.mFragmentDialogs.clear();
        DeleteTracksContract$Presenter deleteTracksContract$Presenter = this.deleteTracksPresenter;
        if (deleteTracksContract$Presenter != null) {
            deleteTracksContract$Presenter.detachView();
        }
        PlaybackControllerProvider.getController(ControlSource.APP_UI).removeOnPlaybackErrorListener(this.mPersistentOnPlaybackErrorListener);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    protected boolean shouldEnsureRegistration() {
        return (LastViewedScreenUtil.getLastViewedScreen(this) == LastViewedScreenUtil.LastViewedScreen.LIBRARY && LastViewedScreenUtil.getLastViewedSource(this) == LastViewedScreenUtil.LastViewedSource.DEVICE) ? false : true;
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        if (resid == 2132083459) {
            if (getResources().getConfiguration().orientation == 1) {
                setContentViewLayoutMargin(getResources().getInteger(R.integer.layout_vertical_margin_percent_portrait), getResources().getInteger(R.integer.layout_horizontal_margin_percent_portrait));
            } else {
                setContentViewLayoutMargin(getResources().getInteger(R.integer.layout_vertical_margin_percent_landscape), getResources().getInteger(R.integer.layout_horizontal_margin_percent_landscape));
            }
        }
        this.mCurrentTheme = resid;
    }

    protected void checkStorageSpace() {
        Dialog dialogCreate;
        if (StorageInfo.isDataPartitionFull()) {
            Dialog dialogCreate2 = StorageDialog.create(this, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.BaseActivity.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        ((NotificationManager) BaseActivity.this.getSystemService("notification")).cancel(1005);
                    } catch (Exception e) {
                        Log.error(BaseActivity.TAG, "Unable to dismiss notification", e);
                    }
                    BaseActivity.this.stopService(new Intent(BaseActivity.this, (Class<?>) ComponentCheckService.class));
                    dialog.cancel();
                    boolean unused = BaseActivity.sIsFullMemory = true;
                    BaseActivity.this.finish();
                }
            }, StorageDialog.LOW_STORAGE_ERROR);
            if (dialogCreate2 != null) {
                dialogCreate2.show();
                return;
            }
            return;
        }
        if (AmazonApplication.hasDisplayedLowMemoryWarning() || !StorageInfo.isDataPartitionLow() || (dialogCreate = StorageDialog.create(this, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.BaseActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ((NotificationManager) BaseActivity.this.getSystemService("notification")).cancel(1005);
                } catch (Exception e) {
                    Log.error(BaseActivity.TAG, "Unable to dismiss notification", e);
                }
                dialog.cancel();
                boolean unused = BaseActivity.sIsLowMemory = true;
            }
        }, StorageDialog.LOW_STORAGE_WARNING)) == null) {
            return;
        }
        dialogCreate.show();
        AmazonApplication.setDisplyedLowMemoryWarning(true);
    }

    private void initializeSubscriptionManager() {
        boolean zIsSignedIn = AccountCredentialUtil.get(this).isSignedIn();
        boolean zIsUserInHawkfireMarketplace = AccountDetailUtil.get(this).isUserInHawkfireMarketplace();
        boolean z = !SubscriptionManagerSingleton.isInitialized();
        if (zIsSignedIn && zIsUserInHawkfireMarketplace && z) {
            AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.activity.BaseActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    SubscriptionManagerSingleton.initialize(BaseActivity.this);
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SignInFlowLauncherActivity.REQUEST_CODE) {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this);
            if (resultCode == 0) {
                Log.error(TAG, "%s tried to register with SSO, but failed. finishing...", getClass());
                finish();
                return;
            } else {
                if (accountCredentialStorage.hasShownSsoWelcome()) {
                    return;
                }
                startActivityForResult(SsoWelcomeActivity.createIntent(this), SsoWelcomeActivity.REQUEST_CODE);
                return;
            }
        }
        if (requestCode == FTUActivity.REQUEST_CODE && resultCode == -1) {
            startActivity(PrimeActivityFactory.getHomeIntent(this));
            return;
        }
        if (requestCode == GrantSDCardPermissionMainPageActivity.SDCARD_PERMISSION_MAIN_PAGE_REQUEST_CODE) {
            Bundle extras = data.getExtras();
            Objects.requireNonNull(extras);
            String string = extras.getString("com.amazon.mp3.tracksuri");
            DeleteTracksPresenter deleteTracksPresenter = new DeleteTracksPresenter(this);
            this.deleteTracksPresenter = deleteTracksPresenter;
            deleteTracksPresenter.executeDeletionOnCurrentThread(string);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.local.DeleteTracksContract$View
    public void showTracksDeletionResult(DeleteListener.DeletionResult result) {
        final String string;
        if (result.getIsSuccess()) {
            if (result.getTotal() == 1) {
                string = getString(R.string.sdcard_storage_permission_activity_song_removed_from_sdcard);
            } else {
                string = String.format(getString(R.string.sdcard_storage_permission_activity_songs_removed_successfully), Integer.valueOf(result.getTotalDeleted()), Integer.valueOf(result.getTotal()));
            }
        } else if (result.getTotal() == 1) {
            string = getString(R.string.sdcard_storage_permission_song_unsuccessfully_removed);
        } else {
            string = String.format(getString(R.string.sdcard_storage_permission_song_partly_removed), Integer.valueOf(result.getTotalDeleted()), Integer.valueOf(result.getTotal()));
        }
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.mp3.activity.BaseActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showTracksDeletionResult$0(string);
            }
        });
        AccessibilityUtil.announceForAccessibility(getApplicationContext(), string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showTracksDeletionResult$0(String str) {
        BauhausToastUtils.showTextToast(this, str, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePersistentPlayerVisibility(View focusedView) {
        if (shouldHidePersistentPlayerOnSoftKeyboard()) {
            PersistentPlayerFragment persistentPlayer = getPersistentPlayer();
            if (!(getResources().getConfiguration().hardKeyboardHidden == 2) || persistentPlayer == null || persistentPlayer.getView() == null) {
                return;
            }
            persistentPlayer.getView().setVisibility(focusedView instanceof EditText ? 8 : 0);
        }
    }

    private boolean shouldHidePersistentPlayerOnSoftKeyboard() {
        return ScreenUtil.isNormalOrSmallScreen();
    }

    private void setContentViewLayoutMargin(int verticalPercentage, int horizontalPercentage) {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int height = defaultDisplay.getHeight();
        int width = defaultDisplay.getWidth();
        getWindow().getDecorView().setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -1));
        getWindow().setLayout(width, height);
        double d = (((double) verticalPercentage) / 100.0d) * ((double) height);
        int i = ((int) ((((double) horizontalPercentage) / 100.0d) * ((double) width))) / 2;
        int i2 = ((int) d) / 2;
        ((ViewGroup.MarginLayoutParams) findViewById(android.R.id.content).getLayoutParams()).setMargins(i, i2, i, i2);
    }

    private void registerTryOurWidgetReceiver() {
        TryOurWidgetDialogManager tryOurWidgetDialogManager = TryOurWidgetDialogManager.INSTANCE;
        if (tryOurWidgetDialogManager.wasDialogAlreadyShowed()) {
            return;
        }
        this.mTryOurWidgetBroadcastReceiverRegistered = true;
        this.mTryOurWidgetBroadcastReceiver = new TryOurWidgetDialogManager.TryOurWidgetBroadcastReceiver(getSupportFragmentManager());
        Log.debug(TAG, "Registering TryOurWidgetReceiver");
        registerReceiver(this.mTryOurWidgetBroadcastReceiver, new IntentFilter(TryOurWidgetDialogManager.ACTION_TRY_OUR_WIDGET_DIALOG_REQUIRED));
        tryOurWidgetDialogManager.broadcastTryOurDialogReceiverIfMissed();
    }

    private void unregisterTryOurWidgetReceiver() {
        if (this.mTryOurWidgetBroadcastReceiverRegistered) {
            this.mTryOurWidgetBroadcastReceiverRegistered = false;
            Log.debug(TAG, "Unregistering TryOurWidgetReceiver");
            unregisterReceiver(this.mTryOurWidgetBroadcastReceiver);
        }
    }

    protected void initializeAlexa() {
        boolean zIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
        this.mIsAlexaEnabled = zIsAlexaEnabled;
        if (zIsAlexaEnabled && (!VoiceManagerSingleton.isInitialized() || VoiceManagerFactory.shouldRefresh())) {
            VoiceManagerFactory.initializeVoiceManager(getApplicationContext());
        }
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized()) {
            if (VoiceManagerSingleton.getInstance().isInitRequired()) {
                VoiceManagerSingleton.getInstance().handleReopenMusicApp(getApplicationContext());
            }
            VoiceManagerSingleton.getInstance().attachView(this, (ViewGroup) findViewById(android.R.id.content));
            WakeWordHelper.resumeWakeWordService(this, true);
        }
    }

    protected boolean handleBackButtonForAlexa() {
        if (!this.mIsAlexaEnabled || !VoiceManagerSingleton.isInitialized() || !VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
            return false;
        }
        VoiceManagerSingleton.getInstance().closeAlexaUi();
        return true;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level < 20) {
            Log.info(TAG, "On trim memory called %d", Integer.valueOf(level));
            if (level >= 15) {
                CacheManager.getInstance().clearImages();
                CacheManager.getInstance().pause();
            } else if (level >= 10) {
                CacheManager.getInstance().weakenCache();
                if (StoreWebView.instanceCreated()) {
                    StoreWebView.getInstance(this).freeMemory();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConcurrencyErrorDialogOrUpsellDialog(final ConcurrencyException concurrencyException) {
        Intent intent;
        Context applicationContext = getApplicationContext();
        if (concurrencyException.getDevices() != null && concurrencyException.getDevices().size() != 0) {
            ConcurrentStreamingUnlimitedFamilyUpsell concurrentStreamingUnlimitedFamilyUpsell = new ConcurrentStreamingUnlimitedFamilyUpsell(applicationContext);
            concurrentStreamingUnlimitedFamilyUpsell.updateConcurrentStreamingReachedCounter();
            if (!AmazonApplication.getCapabilities().isConcurrentFamilyUpsellEnabled()) {
                intent = ConcurrencyErrorDialogActivity.getIntent(applicationContext, "");
            } else if (concurrentStreamingUnlimitedFamilyUpsell.isEligibleForConcurrencyFamilyUpsell(UserSubscriptionUtil.getUserSubscription().isPrimeOnly())) {
                intent = concurrentStreamingUnlimitedFamilyUpsell.handleConcurrencyErrorForPrimeAndGetIntent(applicationContext, concurrencyException);
            } else if (concurrentStreamingUnlimitedFamilyUpsell.isEligibleForConcurrencyFamilyOneClickUpsell(UserSubscriptionUtil.getUserSubscription().isNightwingOnly())) {
                intent = concurrentStreamingUnlimitedFamilyUpsell.handleConcurrencyErrorForNightWingAndGetIntent(applicationContext, concurrencyException);
            } else if (concurrentStreamingUnlimitedFamilyUpsell.isEligibleForConcurrencyFamilyOneClickUpsell(UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices())) {
                intent = concurrentStreamingUnlimitedFamilyUpsell.handleConcurrencyErrorForHawkfireAndGetIntent(applicationContext, concurrencyException);
            } else {
                intent = ConcurrencyErrorDialogActivity.getIntent(applicationContext, concurrencyException.getDevices().get(0).getName());
            }
        } else {
            intent = ConcurrencyErrorDialogActivity.getIntent(applicationContext, "");
        }
        applicationContext.startActivity(intent);
    }

    private class OnPlaybackErrorListenerImpl implements OnPlaybackErrorListener {
        private OnPlaybackErrorListenerImpl() {
        }

        @Override // com.amazon.music.media.playback.OnPlaybackErrorListener
        public void onPlaybackError(final PlaybackException e) {
            if (e instanceof ConcurrencyException) {
                BaseActivity.this.showConcurrencyErrorDialogOrUpsellDialog((ConcurrencyException) e);
                return;
            }
            if (e instanceof NetworkUnavailableException) {
                if (NowPlayingUtil.isAlexaPlaying()) {
                    PlaybackControllerProvider.getController(ControlSource.NONE).pause();
                }
                PlaybackErrorHandler.handlePlaybackError(BaseActivity.this.getApplicationContext(), null, e);
            } else {
                if (e instanceof NoPlayableContentException) {
                    MediaItem mediaItem = e.getMediaItem();
                    if (MediaItemExtensions.isVideo(mediaItem)) {
                        ContentAccessUtil.canContinueContentUnavailableReason((FragmentActivity) BaseActivity.this, MediaItemHelper.getContentUnavailableReason(e.getMediaItem()), false, (Object) mediaItem);
                        return;
                    } else {
                        ContentAccessUtil.canContinueContentUnavailableReason((FragmentActivity) BaseActivity.this, MediaItemHelper.getContentUnavailableReason(e.getMediaItem()), false, BaseActivity.this.isShowErrorDialog);
                        return;
                    }
                }
                if (e.getPlaybackError() == PlaybackException.PlaybackError.DrmLicenseExpired) {
                    new PrimeContentExpiredDialogFragment().show(BaseActivity.this.getSupportFragmentManager(), (String) null);
                } else {
                    if (e instanceof GhostListeningException) {
                        return;
                    }
                    PlaybackErrorHandler.handlePlaybackError(BaseActivity.this.getApplicationContext(), null, e);
                }
            }
        }
    }

    private class PersistentOnPlaybackErrorListenerImpl implements OnPlaybackErrorListener {
        private PersistentOnPlaybackErrorListenerImpl() {
        }

        @Override // com.amazon.music.media.playback.OnPlaybackErrorListener
        public void onPlaybackError(final PlaybackException e) {
            if (e instanceof GhostListeningException) {
                GhostListeningDialogFragment.setShouldShow(true);
                BaseActivity.this.showGhostListeningDialog();
            }
        }
    }

    public void initializeWazeBanner(WazeNavigationBar wazeNavigationBar) {
        boolean zIsWazeIntegrationEnabled;
        this.mWazeNavigationBar = wazeNavigationBar;
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final int i = defaultSharedPreferences.getInt("com.amazon.mp3.wazeBannerDismissCount", 0);
        long time = new Date().getTime() - defaultSharedPreferences.getLong("com.amazon.mp3.wazeBannerLastDismissedDate", new Date().getTime());
        WazeSdkConnection.INSTANCE.setWazeNavigationStatusListener(this);
        try {
            zIsWazeIntegrationEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isWazeIntegrationEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Did not access feature flag WAZE_INTEGRATION successfully with error message: " + e.getMessage());
            zIsWazeIntegrationEnabled = false;
        }
        if (zIsWazeIntegrationEnabled && shouldDisplayWazeBanner(i, time)) {
            wazeNavigationBar.setVisibility(0);
            MetricsLogger.emitUIContentViewMetric(ContentName.WAZE_BANNER, ContainerType.BANNER);
            wazeNavigationBar.enableBluetoothDetection(true);
        } else {
            wazeNavigationBar.setVisibility(8);
        }
        wazeNavigationBar.setListener(new WazeNavigationBar.WazeNavigationBarListener() { // from class: com.amazon.mp3.activity.BaseActivity.9
            @Override // com.waze.sdk.WazeNavigationBar.WazeNavigationBarListener
            public void onStartSdk() {
                Log.debug(BaseActivity.TAG, "Waze  banner was clicked");
                UserInteractionAppEvent.builder("selectWazeBanner").publish();
                WazeSdkConnection.INSTANCE.connectToWazeIfNeeded(BaseActivity.this.getApplicationContext(), true);
            }

            @Override // com.waze.sdk.WazeNavigationBar.WazeNavigationBarListener
            public boolean onCloseNavigationBar() {
                Log.debug(BaseActivity.TAG, "Waze  banner was dismissed");
                UserInteractionAppEvent.builder("selectWazeBannerClose").publish();
                defaultSharedPreferences.edit().putInt("com.amazon.mp3.wazeBannerDismissCount", i + 1).apply();
                defaultSharedPreferences.edit().putLong("com.amazon.mp3.wazeBannerLastDismissedDate", new Date().getTime()).apply();
                return false;
            }
        });
    }

    private boolean shouldDisplayWazeBanner(int dismissCount, long epochMillisSinceDismissal) {
        if (dismissCount == 0) {
            return true;
        }
        if (dismissCount != 1 || epochMillisSinceDismissal < MILLIS_IN_A_WEEK) {
            return (dismissCount == 2 && epochMillisSinceDismissal >= MILLIS_IN_A_WEEK * 2) || WazeSdkConnection.INSTANCE.isWazeSdkConnected();
        }
        return true;
    }

    @Override // com.amazon.mp3.auto.waze.WazeNavigationStatusListener
    public void onNavigationStatusChanged(boolean isNavigating) {
        WazeNavigationBar wazeNavigationBar = this.mWazeNavigationBar;
        if (wazeNavigationBar != null) {
            if (isNavigating) {
                wazeNavigationBar.enableBluetoothDetection(false);
                findViewById(R.id.wazeNavBar).setVisibility(0);
            } else {
                wazeNavigationBar.enableBluetoothDetection(true);
            }
        }
    }
}
