package com.amazon.mp3.playback.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.activity.GrantBluetoothPermissionActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.util.SingleInstanceMonitor;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.catalog.fragment.PlayQueueFragment;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.fragment.LyricsFragment;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.PlaylistTooltipExperience;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.fragment.NowPlayingXrayManager;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.explore.fragment.SwipeablePagesFragment;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.utils.ScreenUtil;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingFragmentActivity extends NavigationActivity implements NavigationProvider, DynamicMessageDialogFragmentListener {
    private PublishSubject<LeavingActivityReason> _leavingActivitySubject;
    private ImageView mBlurredArtistImageBackground;
    private SharedPreferences mDefaultSharedPreference;
    private ImageView mDynamicBackgroundImage;
    private FragmentController mFragmentController;
    private SingleInstanceMonitor mInstanceMonitor;
    public Observable<LeavingActivityReason> mLeavingActivitySubject;
    private View mLoadingView;
    public NowPlayingActionBarProvider mNowPlayingActionBarProvider;
    private final ActionValidatedPlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private PlaylistTooltipExperience mPlaylistTooltipExperience;
    private ImageButton mShareButton;
    private Toolbar mToolbar;
    private View mToolbarLayout;
    private NowPlayingMusicExperienceManager musicExperienceManager;
    private NowPlayingDynamicBgSubscriber nowPlayingDynamicBgSubscriber;
    private NowPlayingXrayManager xrayManager;

    public NowPlayingFragmentActivity() {
        PublishSubject<LeavingActivityReason> publishSubjectCreate = PublishSubject.create();
        this._leavingActivitySubject = publishSubjectCreate;
        this.mLeavingActivitySubject = publishSubjectCreate.asObservable();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        this.mLoadingView = findViewById(R.id.loading_view);
        View viewFindViewById = findViewById(R.id.toolbar_layout);
        this.mToolbarLayout = viewFindViewById;
        Toolbar toolbar = (Toolbar) viewFindViewById.findViewById(R.id.toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        if (!isStage()) {
            this.mToolbar.setNavigationIcon(R.drawable.ic_chevron_caretdown);
            this.mToolbar.setNavigationContentDescription(R.string.close_button_content_description);
            this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.activity.NowPlayingFragmentActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$0(view);
                }
            });
        } else {
            this.mToolbar.findViewById(R.id.StageBackButton).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.activity.NowPlayingFragmentActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$1(view);
                }
            });
        }
        if (ScreenUtil.INSTANCE.isPortrait(this)) {
            this.mDynamicBackgroundImage = (ImageView) findViewById(R.id.dynamic_background);
            if (!isStage()) {
                this.mDynamicBackgroundImage.setVisibility(0);
            }
        }
        this.mNowPlayingActionBarProvider = new NowPlayingActionBarProvider();
        SingleInstanceMonitor singleInstanceMonitor = new SingleInstanceMonitor(this);
        this.mInstanceMonitor = singleInstanceMonitor;
        singleInstanceMonitor.onCreate(savedInstanceState);
        this.mFragmentController = new FragmentController(this, R.id.FragmentContainer);
        this.mPlaylistTooltipExperience = new PlaylistTooltipExperience.PlaylistTooltipExperienceBuilder().createPlaylistTooltipExperience(getApplicationContext());
        this.mIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
        ImageButton imageButton = (ImageButton) this.mToolbar.findViewById(R.id.PersistentPlayerShareButton);
        this.mShareButton = imageButton;
        imageButton.setOnClickListener(new NowPlayingShareButtonOnClickListener(this));
        this.mBlurredArtistImageBackground = (ImageView) findViewById(R.id.artist_image_background_blurred);
        this.mDefaultSharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        if (!isStage()) {
            this.xrayManager = new NowPlayingXrayManager(this, this.mDefaultSharedPreference);
        }
        this.musicExperienceManager = new NowPlayingMusicExperienceManager(this);
        if (savedInstanceState == null) {
            handleIntent(getIntent());
        }
        this.nowPlayingDynamicBgSubscriber = new NowPlayingDynamicBgSubscriber(new WeakReference(this.mDynamicBackgroundImage), new WeakReference(this));
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.playback.activity.NowPlayingFragmentActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$2((StyleSheet) obj);
            }
        });
        if (Build.VERSION.SDK_INT < 31 || !hasBluetoothEnabled() || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            return;
        }
        GrantBluetoothPermissionActivity.requestIfNotGranted(getApplicationContext(), GrantBluetoothPermissionActivity.BLUETOOTH_PERMISSIONS, new BluetoothPermissionReceiver(new WeakReference(this)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(StyleSheet styleSheet) {
        DynamicBackgroundImagePublisher.getInstance().subscribeBackgroundImageUpdate(this.nowPlayingDynamicBgSubscriber, styleSheet);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(final Intent intent) {
        this.mFragmentController.navigateFromIntent(intent);
        this.musicExperienceManager.checkIfMusicExperienceOpened(intent);
    }

    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(final Fragment fragment) {
        super.onAttachFragment(fragment);
        if (this.mBlurredArtistImageBackground != null && isStage()) {
            if ((fragment instanceof PlayQueueFragment) || (fragment instanceof CastingFragment) || (fragment instanceof LyricsFragment)) {
                this.mBlurredArtistImageBackground.setAlpha(1.0f);
                this.mBlurredArtistImageBackground.setVisibility(0);
            } else {
                this.mBlurredArtistImageBackground.setAlpha(0.0f);
            }
        }
        if (fragment instanceof OverflowMenuReceiver) {
            ((OverflowMenuReceiver) fragment).setActionBarProvider(this.mNowPlayingActionBarProvider);
        }
    }

    public Fragment getCurrentFragment() {
        FragmentController fragmentController = this.mFragmentController;
        if (fragmentController != null) {
            return fragmentController.getCurrentFragment();
        }
        return null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int featureId, Menu menu) {
        Fragment currentFragment = this.mFragmentController.getCurrentFragment();
        if ((currentFragment instanceof NowPlayingFragment) && menu != null) {
            ((NowPlayingFragment) currentFragment).dismissTooltipOnOverflowOpened();
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHOW_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING).withEventTime(System.currentTimeMillis()).build());
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override // com.amazon.mp3.activity.NavigationActivity, android.app.Activity
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == 16908332) {
            if (isTaskRoot()) {
                startActivity(MusicHomeActivity.getStartIntent(this));
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
                return true;
            }
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        NowPlayingXrayManager nowPlayingXrayManager;
        super.onPause();
        if (!isStage() && this.xrayManager != null) {
            this.mDefaultSharedPreference.edit().putBoolean("com.amazon.mp3.activity.nowplaying.isXrayOpen", this.xrayManager.isXrayOpen()).apply();
        }
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        MediaItemId mediaItemId = currentMediaItem != null ? currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN) : null;
        this.mDefaultSharedPreference.edit().putString("com.amazon.mp3.activity.nowplaying.trackASIN", mediaItemId != null ? mediaItemId.getId() : "").apply();
        if (isStage() || (nowPlayingXrayManager = this.xrayManager) == null || !nowPlayingXrayManager.isXrayOpen()) {
            return;
        }
        this.xrayManager.removeOnPlayStateChangedListener();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        NowPlayingXrayManager nowPlayingXrayManager;
        super.onResume();
        CarModeUtility.checkIfAutoLaunchCarMode(this);
        String stringExtra = getIntent().getStringExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        if (stringExtra != null) {
            LastViewedScreenUtil.setLastViewed(this, LastViewedScreenUtil.LastViewedScreen.NOW_PLAYING, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(stringExtra));
        }
        initializeAlexa();
        if (isStage() || (nowPlayingXrayManager = this.xrayManager) == null || !nowPlayingXrayManager.isXrayOpen()) {
            return;
        }
        this.xrayManager.addOnPlayStateChangedListener();
        this.xrayManager.checkIfXrayAvailable();
    }

    @Override // com.amazon.mp3.activity.BaseActivity
    public boolean shouldDisplayBluemoonExceptions() {
        Uri currentUri = MediaItemHelper.getCurrentUri();
        return currentUri != null && CirrusMediaSource.matchCloud(currentUri);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mInstanceMonitor.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        DynamicBackgroundImagePublisher.getInstance().unSubscribeBackgroundImageUpdate(this.nowPlayingDynamicBgSubscriber);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        NowPlayingXrayManager nowPlayingXrayManager;
        super.onDestroy();
        this.mInstanceMonitor.onDestroy();
        this._leavingActivitySubject.onCompleted();
        if (isStage() || (nowPlayingXrayManager = this.xrayManager) == null) {
            return;
        }
        nowPlayingXrayManager.clear();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateToolbar();
    }

    private void updateToolbar() {
        float dimension = getResources().getDimension(R.dimen.action_bar_height);
        ViewGroup.LayoutParams layoutParams = this.mToolbar.getLayoutParams();
        layoutParams.height = Math.round(dimension);
        this.mToolbar.setLayoutParams(layoutParams);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        NowPlayingXrayManager nowPlayingXrayManager;
        this._leavingActivitySubject.onNext(LeavingActivityReason.BACK_PRESSED);
        DynamicBackgroundImagePublisher.getInstance().resumeDynamicBackgroundImagePublisher();
        if (this.musicExperienceManager.isMusicExperienceOpen() && !this.musicExperienceManager.willPlayAdditionalContent()) {
            this.musicExperienceManager.handleBackButtonClicked();
            return;
        }
        if (handleBackButtonForAlexa()) {
            return;
        }
        Fragment currentFragment = this.mFragmentController.getCurrentFragment();
        if (currentFragment instanceof CastingFragment) {
            if (((CastingFragment) currentFragment).isBackPressed()) {
                CastingUIMetricsLogger.recordDismissDeviceListClickMetric();
            }
        } else if (currentFragment instanceof SwipeablePagesFragment) {
            if (!isStage() && (nowPlayingXrayManager = this.xrayManager) != null && nowPlayingXrayManager.isXrayOpen()) {
                this.xrayManager.removeOnPlayStateChangedListener();
            }
        } else if (currentFragment instanceof NowPlayingFragment) {
            NowPlayingFragment nowPlayingFragment = (NowPlayingFragment) currentFragment;
            if (nowPlayingFragment.isStage() && nowPlayingFragment.onBackPressed()) {
                return;
            }
        }
        super.onBackPressed();
        if (isTaskRoot()) {
            return;
        }
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        this._leavingActivitySubject.onNext(LeavingActivityReason.USER_LEAVE_HINT);
    }

    public void goToLyricsFragment() {
        this.mFragmentController.changeScreenFragment(new LyricsFragment(), true, false, false, R.anim.zoom_in, R.anim.fragment_fade_out, R.anim.zoom_in, R.anim.slide_out_down);
    }

    public void showShareButton(Boolean show) {
        this.mShareButton.setVisibility(show.booleanValue() ? 0 : 8);
    }

    public void showLoadingView(boolean show) {
        this.mLoadingView.setVisibility(show ? 0 : 8);
    }

    public void showActionBar() {
        View view = this.mToolbarLayout;
        if (view != null) {
            view.setVisibility(0);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }

    public void hideActionBar() {
        View view = this.mToolbarLayout;
        if (view != null) {
            view.setVisibility(8);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public NowPlayingXrayManager getXrayManager() {
        return this.xrayManager;
    }

    @Override // com.amazon.mp3.fragment.navigation.NavigationProvider
    public void changeScreenFragment(Fragment fragment, boolean addToBackStack, boolean forceReplace, boolean animate) {
        this.mFragmentController.changeScreenFragment(fragment, addToBackStack, forceReplace, animate);
    }

    @Override // com.amazon.mp3.fragment.navigation.NavigationProvider
    public FragmentController getFragmentController() {
        return this.mFragmentController;
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onDismissClickEvent(DialogInterface dialogInterface) {
        this.mPlaybackController.play();
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onShow(DialogInterface dialogInterface) {
        this.mPlaybackController.pause();
    }

    private class NowPlayingShareButtonOnClickListener implements View.OnClickListener {
        private final FragmentActivity mFragmentActivity;

        public NowPlayingShareButtonOnClickListener(final FragmentActivity fragmentActivity) {
            this.mFragmentActivity = fragmentActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MediaItem currentMediaItem = NowPlayingFragmentActivity.this.mPlaybackController.getCurrentMediaItem();
            if (currentMediaItem != null) {
                MediaItemHelper.shareMediaItem(this.mFragmentActivity, currentMediaItem);
            }
        }
    }

    private class NowPlayingActionBarProvider implements ActionBarProvider {
        private final ActionBar mActionBar;

        public NowPlayingActionBarProvider() {
            ActionBar supportActionBar = NowPlayingFragmentActivity.this.getSupportActionBar();
            this.mActionBar = supportActionBar;
            supportActionBar.setDisplayShowTitleEnabled(false);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void setHeaderView(final View headerView) {
            this.mActionBar.setDisplayShowCustomEnabled(true);
            this.mActionBar.setCustomView(headerView);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void removeHeaderView() {
            this.mActionBar.setDisplayShowCustomEnabled(false);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void requestHomeButtonAsBack() {
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void requestHomeButtonAsStoreClose() {
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void restoreHomeButton() {
            this.mActionBar.setDisplayHomeAsUpEnabled(false);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void setHomeButtonVisibility(final int visible) {
            this.mActionBar.setDisplayShowHomeEnabled(visible == 0);
        }
    }

    public boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    private boolean hasBluetoothEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }
}
