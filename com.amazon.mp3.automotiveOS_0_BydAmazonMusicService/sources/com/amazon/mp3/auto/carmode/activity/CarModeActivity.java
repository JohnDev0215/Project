package com.amazon.mp3.auto.carmode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.SubDeviceConnectionUtils;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeDisclaimerFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeNowPlayingFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.find.view.CarModeSearchBrushFragment;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.auto.provider.SubDeviceType;
import com.amazon.music.media.auto.util.SubDeviceConnectionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import com.waze.sdk.WazeNavigationBar;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 #2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001#B\u0005¢\u0006\u0002\u0010\u0004J<\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0010\u001a\u00020\u0011\"\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\u001c\u0010\u0018\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016J\u0012\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001e\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u001f\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\bH\u0014J\b\u0010!\u001a\u00020\bH\u0014J\b\u0010\"\u001a\u00020\bH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/auto/carmode/activity/CarModeActivity;", "Lcom/amazon/mp3/activity/BaseActivity;", "Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "()V", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "doFragmentTransaction", "", "fragment", "Landroidx/fragment/app/Fragment;", "action", "Lcom/amazon/mp3/auto/carmode/CarModeAction;", "addToBackStack", "", "animate", "customAnimationIds", "", "", "getCurrentFragment", "handleIntent", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "isPodcastPlaying", "navigateTo", "bundle", "Landroid/os/Bundle;", "onBackPressed", "onCreate", "savedInstanceState", "onNewIntent", "onPlayStateChanged", "onResume", "onStart", "onStop", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeActivity extends BaseActivity implements CarModeNavigation, OnPlayStateChangedListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private final ActionValidatedPlaybackController playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);

    /* JADX INFO: compiled from: CarModeActivity.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CarModeAction.values().length];
            iArr[CarModeAction.SHOW_DISCLAIMER_PAGE.ordinal()] = 1;
            iArr[CarModeAction.SHOW_WELCOME_PAGE.ordinal()] = 2;
            iArr[CarModeAction.SHOW_ON_BOARDING_PAGE.ordinal()] = 3;
            iArr[CarModeAction.SHOW_HOME_PAGE.ordinal()] = 4;
            iArr[CarModeAction.SHOW_NOW_PLAYING_PAGE.ordinal()] = 5;
            iArr[CarModeAction.SHOW_PLAY_QUEUE_PAGE.ordinal()] = 6;
            iArr[CarModeAction.SHOW_EXIT_PAGE.ordinal()] = 7;
            iArr[CarModeAction.SHOW_SEARCH_PAGE.ordinal()] = 8;
            iArr[CarModeAction.SHOW_DETAIL_PAGE.ordinal()] = 9;
            iArr[CarModeAction.SHOW_PODCAST_EPISODE_LIST_PAGE.ordinal()] = 10;
            iArr[CarModeAction.EXIT_CAR_MODE.ordinal()] = 11;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlayStatus.values().length];
            iArr2[PlayStatus.LOADING.ordinal()] = 1;
            iArr2[PlayStatus.BUFFERING.ordinal()] = 2;
            iArr2[PlayStatus.PREPARING.ordinal()] = 3;
            iArr2[PlayStatus.RENDERING.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: compiled from: CarModeActivity.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002R\u0014\u0010\u0007\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\bR\u0014\u0010\f\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/auto/carmode/activity/CarModeActivity$Companion;", "", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "getStartIntent", "", "EXTRAS_CAR_MODE_ACTION", "Ljava/lang/String;", "EXTRAS_CAR_MODE_DETAIL_PAGE_URI", "EXTRAS_CAR_MODE_LAUNCH", "EXTRAS_CAR_MODE_USER_EXIT", "PODCAST_SHOW_ID", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent getStartIntent(Context context) {
            return new Intent(context, (Class<?>) CarModeActivity.class);
        }
    }

    static {
        String simpleName = CarModeActivity.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "CarModeActivity::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_mode);
        this.isShowErrorDialog = false;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        initializeWazeBanner((WazeNavigationBar) findViewById(R.id.wazeNavBar));
        Boolean carModeKeepScreenActivePreference = SettingsUtil.getCarModeKeepScreenActivePreference(this);
        Intrinsics.checkNotNullExpressionValue(carModeKeepScreenActivePreference, "getCarModeKeepScreenActivePreference(this)");
        if (carModeKeepScreenActivePreference.booleanValue()) {
            getWindow().addFlags(128);
        }
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            handleIntent(intent);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.playbackController.addOnPlayStateChangedListener(this);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        initializeAlexa();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent == null) {
            return;
        }
        handleIntent(intent);
    }

    private final void handleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Object obj = extras == null ? null : extras.get("carModeAction");
        navigateTo(obj instanceof CarModeAction ? (CarModeAction) obj : null, extras);
    }

    public final Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.car_mode_fragment_container);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (handleBackButtonForAlexa()) {
            return;
        }
        super.onBackPressed();
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeNavigation
    public void navigateTo(CarModeAction action, Bundle bundle) {
        Fragment carModeBrushFragment;
        PageType pageType;
        Fragment carModeBrushFragment2;
        switch (action == null ? -1 : WhenMappings.$EnumSwitchMapping$0[action.ordinal()]) {
            case 1:
                Log.debug(TAG, "Launching car mode disclaimer fragment");
                doFragmentTransaction$default(this, new CarModeDisclaimerFragment(), null, false, false, new int[0], 14, null);
                break;
            case 2:
                Log.debug(TAG, "Launching car mode welcome fragment");
                doFragmentTransaction$default(this, new CarModeWelcomeFragment(), null, false, false, new int[0], 14, null);
                break;
            case 3:
                Log.debug(TAG, "Launching first time user onboarding fragment");
                doFragmentTransaction$default(this, new CarModeOnBoardingFragment(), null, false, false, new int[0], 14, null);
                break;
            case 4:
                Log.debug(TAG, "Launching car mode landing fragment");
                if (bundle == null ? false : bundle.getBoolean("carModeLaunch", false)) {
                    SubDeviceConnectionUtil.DefaultImpls.sendSubDeviceConnectedEvent$default(SubDeviceConnectionUtils.INSTANCE, SubDeviceType.CAR_MODE.toString(), false, 2, null);
                    CarModeUtility.INSTANCE.performCarModeLaunchInit();
                }
                if (AmazonApplication.getCapabilities().isCarModeV2Enabled()) {
                    carModeBrushFragment = new CarModeV2BrushFragment();
                } else {
                    carModeBrushFragment = new CarModeBrushFragment();
                }
                Fragment fragment = carModeBrushFragment;
                fragment.setArguments(bundle);
                doFragmentTransaction$default(this, fragment, null, false, false, new int[0], 14, null);
                break;
            case 5:
                Log.debug(TAG, "Launching car mode now playing fragment");
                doFragmentTransaction(new CarModeNowPlayingFragment(), action, true, true, R.anim.slide_in_up, 0, 0, R.anim.slide_out_down);
                break;
            case 6:
                Log.debug(TAG, "Launching car mode play queue fragment");
                doFragmentTransaction$default(this, new CarModePlayQueueFragment(), action, true, false, new int[0], 8, null);
                break;
            case 7:
                Log.debug(TAG, "Launching car mode exit fragment");
                CarModeExitFragment carModeExitFragment = new CarModeExitFragment();
                carModeExitFragment.setArguments(bundle);
                doFragmentTransaction$default(this, carModeExitFragment, action, true, false, new int[0], 8, null);
                break;
            case 8:
                Log.debug(TAG, "Launching car mode search fragment");
                doFragmentTransaction$default(this, CarModeSearchBrushFragment.INSTANCE.newInstance(bundle), null, false, false, new int[0], 14, null);
                break;
            case 9:
                Log.debug(TAG, "Launching car mode detail fragment");
                CarModeDetailFragment carModeDetailFragment = new CarModeDetailFragment();
                carModeDetailFragment.setArguments(bundle);
                doFragmentTransaction(carModeDetailFragment, action, true, true, R.anim.slide_in_left, 0, 0, R.anim.slide_out_right);
                break;
            case 10:
                Log.debug(TAG, "Launching car mode podcast episode list fragment");
                doFragmentTransaction$default(this, CarModePodcastEpisodeListFragment.INSTANCE.newInstance(bundle), action, true, false, new int[0], 8, null);
                break;
            case 11:
                boolean z = bundle != null ? bundle.getBoolean("carModeUserExit", false) : false;
                SubDeviceConnectionUtils.INSTANCE.sendSubDeviceDisconnectedEvent(SubDeviceType.CAR_MODE.toString());
                CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
                carModeUtility.resetCarModeData();
                carModeUtility.setUserExitedStatus(z);
                if (z) {
                    if (AmazonApplication.getCapabilities().isCarModeV2Enabled()) {
                        pageType = PageType.CAR_MODE_HOME;
                    } else {
                        pageType = PageType.CAR_MODE_PRESET_LIST;
                    }
                    AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_CLOSE, pageType, ExperienceMode.CAR_MODE, null, 8, null);
                }
                finish();
                break;
            default:
                Log.error(TAG, "No matching car mode action found. navigating to car mode home fragment");
                if (AmazonApplication.getCapabilities().isCarModeV2Enabled()) {
                    carModeBrushFragment2 = new CarModeV2BrushFragment();
                } else {
                    carModeBrushFragment2 = new CarModeBrushFragment();
                }
                Fragment fragment2 = carModeBrushFragment2;
                fragment2.setArguments(bundle);
                doFragmentTransaction$default(this, fragment2, null, false, false, new int[0], 14, null);
                break;
        }
    }

    static /* synthetic */ void doFragmentTransaction$default(CarModeActivity carModeActivity, Fragment fragment, CarModeAction carModeAction, boolean z, boolean z2, int[] iArr, int i, Object obj) {
        if ((i & 2) != 0) {
            carModeAction = null;
        }
        carModeActivity.doFragmentTransaction(fragment, carModeAction, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, iArr);
    }

    private final void doFragmentTransaction(Fragment fragment, CarModeAction action, boolean addToBackStack, boolean animate, int... customAnimationIds) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction, "beginTransaction()");
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        if (addToBackStack) {
            fragmentTransactionBeginTransaction.addToBackStack(action == null ? null : action.name());
        }
        if (animate && customAnimationIds.length == 4) {
            fragmentTransactionBeginTransaction.setCustomAnimations(customAnimationIds[0], customAnimationIds[1], customAnimationIds[2], customAnimationIds[3]);
        }
        fragmentTransactionBeginTransaction.replace(R.id.car_mode_fragment_container, fragment);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        if (isPodcastPlaying()) {
            return;
        }
        Playback playback = Podcast.getPlayback();
        PlayStatus playStatus = this.playbackController.getPlayStatus();
        int i = playStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$1[playStatus.ordinal()];
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            Log.info(TAG, "Do not clear podcast player.");
        } else {
            if (playback == null) {
                return;
            }
            playback.clear();
        }
    }

    private final boolean isPodcastPlaying() {
        return this.playbackController.getCurrentMediaItem() != null && this.playbackController.getCurrentMediaItem().getType() == MediaItem.Type.PODCAST_CONTENT;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.playbackController.removeOnPlayStateChangedListener(this);
    }
}
