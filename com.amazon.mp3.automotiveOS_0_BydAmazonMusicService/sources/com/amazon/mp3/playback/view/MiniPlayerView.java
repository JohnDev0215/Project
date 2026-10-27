package com.amazon.mp3.playback.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.catalog.fragment.PlayQueueFragment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.PlayerVerticalSwipeListener;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.ViewSlideAnimator;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.MiniPlayerContentView;
import com.amazon.mp3.view.OnDragEventListener;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;

/* JADX INFO: loaded from: classes3.dex */
public class MiniPlayerView extends FrameLayout implements PlayerView {
    private static final String TAG = MiniPlayerView.class.getSimpleName();
    protected ImageButton mAddToLibraryButton;
    protected ImageButton mCastingButton;
    private Context mContext;
    protected ImageButton mContextMenuButton;
    private boolean mHasNext;
    private boolean mHasPrev;
    private MiniPlayerContentView mMiniPlayerContentViewCurrent;
    private MiniPlayerContentView mMiniPlayerContentViewNext;
    private MiniPlayerContentView mMiniPlayerContentViewPrev;
    protected ImageButton mNextButton;
    private View.OnClickListener mOnClickListener;
    private Runnable mOnLongClickListener;
    private OnPlayerClickListener mOnPlayerClickListener;
    private BaseButton mPlayButton;
    protected ImageButton mPlayQueueButton;
    final ActionValidatedPlaybackController mPlaybackController;
    private PlayerNavigationListener mPlayerNavigationListener;
    private PlayerVerticalSwipeListener mPlayerVerticalSwipeListener;
    private PlayerViewConfig mPlayerViewConfig;
    protected ImageButton mPrevButton;
    private ProgressBar mProgressBar;
    protected ImageButton mRepeatButton;
    protected ImageButton mShuffleButton;
    private ImageButton mThumbsDownButton;
    private ImageButton mThumbsUpButton;
    private TouchEventHandler mTouchEventHandler;
    private ViewHorizontalDragAnimator mViewHorizontalDragAnimator;
    private ViewSlideAnimator mViewSlideAnimator;

    protected void OnSwipeLeftError() {
    }

    protected void OnSwipeRightError() {
    }

    public MiniPlayerView(final Context context) {
        this(context, null);
    }

    public MiniPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.mTouchEventHandler = new TouchEventHandler();
        this.mOnClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (MiniPlayerView.this.mPlayerNavigationListener != null) {
                    MiniPlayerView.this.mOnPlayerClickListener.onPlayerClicked();
                    MiniPlayerView.this.mViewHorizontalDragAnimator.resetViews();
                }
            }
        };
        this.mOnLongClickListener = new Runnable() { // from class: com.amazon.mp3.playback.view.MiniPlayerView.2
            @Override // java.lang.Runnable
            public void run() {
                if (MiniPlayerView.this.mPlayerViewConfig == null || !MiniPlayerView.this.mPlayerViewConfig.isShowContextMenu()) {
                    return;
                }
                MiniPlayerView.this.showContextMenu();
            }
        };
        this.mViewHorizontalDragAnimator = new ViewHorizontalDragAnimator();
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        initView(context);
        this.mViewSlideAnimator = new ViewSlideAnimator(this.mMiniPlayerContentViewCurrent);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.mini_player_view, this);
        this.mMiniPlayerContentViewCurrent = (MiniPlayerContentView) findViewById(R.id.MiniPlayerContentCurrent);
        this.mMiniPlayerContentViewPrev = (MiniPlayerContentView) findViewById(R.id.MiniPlayerContentPrev);
        this.mMiniPlayerContentViewNext = (MiniPlayerContentView) findViewById(R.id.MiniPlayerContentNext);
        this.mPlayButton = (BaseButton) findViewById(R.id.MiniPlayerPlayButton);
        this.mNextButton = (ImageButton) findViewById(R.id.MiniPlayerNextButton);
        this.mPrevButton = (ImageButton) findViewById(R.id.MiniPlayerPrevButton);
        this.mRepeatButton = (ImageButton) findViewById(R.id.MiniPlayerRepeatButton);
        this.mShuffleButton = (ImageButton) findViewById(R.id.MiniPlayerShuffleButton);
        this.mThumbsUpButton = (ImageButton) findViewById(R.id.MiniPlayerThumbsUpButton);
        this.mThumbsDownButton = (ImageButton) findViewById(R.id.MiniPlayerThumbsDownButton);
        this.mPlayQueueButton = (ImageButton) findViewById(R.id.MiniPlayerQueueButton);
        this.mCastingButton = (ImageButton) findViewById(R.id.MiniPlayerCastingButton);
        this.mAddToLibraryButton = (ImageButton) findViewById(R.id.MiniPlayerAddToLibraryButton);
        this.mContextMenuButton = (ImageButton) findViewById(R.id.MiniPlayerContextMenuButton);
        StyleSheetProvider.getStyleSheet().observe((FragmentActivity) context, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.initPlayPauseButton((StyleSheet) obj);
            }
        });
        this.mMiniPlayerContentViewPrev.setVisibility(4);
        this.mMiniPlayerContentViewNext.setVisibility(4);
        attachClickListeners();
        attachVerticalDragAnimatorListener();
        attachHorizontalDragAnimatorListener();
        refreshTouchListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPlayPauseButton(final StyleSheet styleSheet) {
        this.mPlayButton.setPadding(0, 0, 0, 0);
        if (isStage() && isLyricsSharingEnabled() && (this.mContext instanceof NowPlayingFragmentActivity)) {
            BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
            if (iconBuilder != null) {
                iconBuilder.withIcon(getResources().getDrawable(R.drawable.mini_player_btn_pause_tray)).applyStyle(this.mPlayButton);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.spacer_12), 0);
            this.mPlayButton.setLayoutParams(layoutParams);
            return;
        }
        BaseButton.StyleBuilder iconBuilder2 = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, null);
        if (iconBuilder2 != null) {
            iconBuilder2.withIcon(getResources().getDrawable(R.drawable.mini_player_btn_pause_tray)).applyStyle(this.mPlayButton);
        }
    }

    private boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    private boolean isLyricsSharingEnabled() {
        return AmazonApplication.getCapabilities().isLyricsSharingEnabled();
    }

    public void setProgressBar(final ProgressBar progressBar) {
        this.mProgressBar = progressBar;
        progressBar.setProgressDrawable(this.mContext.getDrawable(R.drawable.mini_player_progress_bar));
        this.mProgressBar.setIndeterminateDrawable(this.mContext.getDrawable(R.drawable.mini_player_progress_bar));
        this.mProgressBar.setAlpha(0.6f);
    }

    public void setMinimizeViewVisibility(int visibility) {
        this.mMiniPlayerContentViewCurrent.setMinimizeViewVisibility(visibility);
        this.mMiniPlayerContentViewPrev.setMinimizeViewVisibility(visibility);
        this.mMiniPlayerContentViewNext.setMinimizeViewVisibility(visibility);
    }

    @Override // android.view.View
    protected void onCreateContextMenu(final ContextMenu menu) {
        super.onCreateContextMenu(menu);
        this.mPlayerNavigationListener.onCreateContextMenu(menu);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerClickListener(final OnPlayerClickListener onPlayerClickListener) {
        this.mOnPlayerClickListener = onPlayerClickListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerNavigationListener(final PlayerNavigationListener playerNavigationListener) {
        this.mPlayerNavigationListener = playerNavigationListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerVerticalSwipeListener(final PlayerVerticalSwipeListener playerVerticalSwipeListener) {
        this.mPlayerVerticalSwipeListener = playerVerticalSwipeListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updateProgress(final long progress) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setProgress((int) progress);
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updateDuration(final long duration) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setMax((int) duration);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.MiniPlayerView$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$PlayerState;

        static {
            int[] iArr = new int[PlayerViewModel.PlayerState.values().length];
            $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$PlayerState = iArr;
            try {
                iArr[PlayerViewModel.PlayerState.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$PlayerState[PlayerViewModel.PlayerState.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$PlayerState[PlayerViewModel.PlayerState.PLAYING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setPlayerState(final PlayerViewModel.PlayerState playerState) {
        if (playerState == null) {
            return;
        }
        int i = AnonymousClass6.$SwitchMap$com$amazon$mp3$playback$PlayerViewModel$PlayerState[playerState.ordinal()];
        if (i == 1) {
            setLoading(true);
            showPauseIcon();
        } else if (i == 2) {
            setLoading(false);
            showPlayIcon();
        } else {
            if (i != 3) {
                return;
            }
            setLoading(false);
            showPauseIcon();
        }
    }

    public void hasNextTrack(final boolean hasNext) {
        this.mHasNext = hasNext;
        ImageButton imageButton = this.mNextButton;
        if (imageButton == null) {
            return;
        }
        imageButton.setEnabled(hasNext);
        this.mNextButton.setImageResource(R.drawable.mini_player_btn_next_tray);
        if (this.mPlaybackController.canSkipNext()) {
            return;
        }
        this.mNextButton.setEnabled(true);
        this.mNextButton.setImageResource(R.drawable.ic_playback_next_disabled);
    }

    public void hasPrevTrack(final boolean hasPrev) {
        this.mHasPrev = hasPrev;
        ImageButton imageButton = this.mPrevButton;
        if (imageButton == null) {
            return;
        }
        imageButton.setEnabled(hasPrev);
        this.mPrevButton.setImageResource(R.drawable.mini_player_btn_previous_tray);
        if (this.mPlaybackController.canSkipPrevious() || this.mPlaybackController.canRestartItem()) {
            return;
        }
        this.mPrevButton.setEnabled(true);
        this.mPrevButton.setImageResource(R.drawable.ic_playback_previous_disabled);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void shuffleStateChanged(final boolean shuffled) {
        ImageButton imageButton = this.mShuffleButton;
        if (imageButton == null) {
            return;
        }
        imageButton.setImageDrawable(this.mContext.getDrawable(shuffled ? R.drawable.ic_playback_shuffle_active : R.drawable.ic_playback_shuffle));
        this.mShuffleButton.setActivated(shuffled);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void repeatStateChanged(final RepeatMode repeatMode) {
        ImageButton imageButton = this.mRepeatButton;
        if (imageButton == null) {
            return;
        }
        if (repeatMode == RepeatMode.REPEAT_ONE) {
            imageButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_playback_repeat_one));
            this.mRepeatButton.setActivated(true);
        } else if (repeatMode == RepeatMode.REPEAT_ALL) {
            imageButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_playback_repeat_all));
            this.mRepeatButton.setActivated(true);
        } else {
            imageButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_playback_repeat));
            this.mRepeatButton.setActivated(false);
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void ratingChanged(int rating) {
        ImageButton imageButton;
        if (this.mThumbsUpButton == null || (imageButton = this.mThumbsDownButton) == null) {
            return;
        }
        if (rating == 0) {
            imageButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_action_dislike));
            this.mThumbsUpButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_action_like));
            this.mThumbsUpButton.setActivated(false);
            this.mThumbsDownButton.setActivated(false);
            return;
        }
        if (rating == 1) {
            imageButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_action_dislike));
            this.mThumbsUpButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_action_like_active));
            this.mThumbsUpButton.setActivated(true);
            this.mThumbsDownButton.setActivated(false);
            return;
        }
        if (rating != 2) {
            return;
        }
        imageButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_action_dislike_active));
        this.mThumbsUpButton.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_action_like));
        this.mThumbsUpButton.setActivated(false);
        this.mThumbsDownButton.setActivated(true);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addedToLibrary(final boolean added) {
        ImageButton imageButton = this.mAddToLibraryButton;
        if (imageButton == null) {
            return;
        }
        imageButton.setImageDrawable(this.mContext.getDrawable(added ? R.drawable.ic_action_add_disabled : R.drawable.ic_action_add));
        this.mAddToLibraryButton.setEnabled(!added);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void castingStateChanged(final boolean connected) {
        ImageButton imageButton = this.mCastingButton;
        if (imageButton != null) {
            imageButton.setImageDrawable(this.mContext.getDrawable(connected ? R.drawable.ic_casting_active : R.drawable.ic_casting_inactive));
            this.mCastingButton.setActivated(connected);
        }
        String castingDeviceName = CastingUtil.getCastingDeviceName(getContext());
        WeblabTreatment castingIngressExperimentTreatment = AmazonApplication.getCapabilities().getCastingIngressExperimentTreatment();
        if ((castingIngressExperimentTreatment == WeblabTreatment.C || castingIngressExperimentTreatment == WeblabTreatment.T1) && connected && !TextUtils.isEmpty(castingDeviceName)) {
            String string = getResources().getString(R.string.cloudqueue_playing_on_device, castingDeviceName);
            getNextContentView().setDeviceName(string);
            getCurrentContentView().setDeviceName(string);
            getPrevContentView().setDeviceName(string);
            return;
        }
        getNextContentView().setDeviceName("");
        getCurrentContentView().setDeviceName("");
        getPrevContentView().setDeviceName("");
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public MiniPlayerContentView getCurrentContentView() {
        return this.mMiniPlayerContentViewCurrent;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public MiniPlayerContentView getPrevContentView() {
        return this.mMiniPlayerContentViewPrev;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public MiniPlayerContentView getNextContentView() {
        return this.mMiniPlayerContentViewNext;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void animateToPrev() {
        this.mViewSlideAnimator.cancelAnimations();
        this.mViewSlideAnimator.setCurrentView(this.mMiniPlayerContentViewCurrent);
        this.mViewSlideAnimator.setReplacementView(this.mMiniPlayerContentViewPrev);
        this.mViewSlideAnimator.executeSwipe(ViewSlideAnimator.ViewSlideAction.RIGHT);
        updateContentViewsOnPrevious();
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void animateToNext() {
        this.mViewSlideAnimator.cancelAnimations();
        this.mViewSlideAnimator.setCurrentView(this.mMiniPlayerContentViewCurrent);
        this.mViewSlideAnimator.setReplacementView(this.mMiniPlayerContentViewNext);
        this.mViewSlideAnimator.executeSwipe(ViewSlideAnimator.ViewSlideAction.LEFT);
        updateContentViewsOnNext();
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setCurrentBitmap(final Bitmap bitmap) {
        MiniPlayerContentView miniPlayerContentView = this.mMiniPlayerContentViewCurrent;
        if (miniPlayerContentView != null) {
            miniPlayerContentView.setArtworkBitmap(bitmap);
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setNextBitmap(final Bitmap bitmap) {
        MiniPlayerContentView miniPlayerContentView = this.mMiniPlayerContentViewNext;
        if (miniPlayerContentView != null) {
            miniPlayerContentView.setArtworkBitmap(bitmap);
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setPrevBitmap(final Bitmap bitmap) {
        MiniPlayerContentView miniPlayerContentView = this.mMiniPlayerContentViewPrev;
        if (miniPlayerContentView != null) {
            miniPlayerContentView.setArtworkBitmap(bitmap);
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updatePlaybackControl(final PlayerViewConfig playerViewConfig) {
        this.mPlayerViewConfig = playerViewConfig;
        boolean z = !isInPlayQueuePage(this.mContext);
        setImageButtonVisibility(this.mNextButton, playerViewConfig.isShowNext() && (!AmazonApplication.getCapabilities().isCastingIngressExperimentEnabled() || getResources().getConfiguration().smallestScreenWidthDp >= 480));
        setImageButtonVisibility(this.mPrevButton, playerViewConfig.isShowPrevious());
        setBaseButtonVisibility(this.mPlayButton, playerViewConfig.isShowPlayPause());
        setImageButtonVisibility(this.mAddToLibraryButton, playerViewConfig.isShowAddToLibrary());
        setImageButtonVisibility(this.mContextMenuButton, playerViewConfig.isShowContextMenu());
        setImageButtonVisibility(this.mCastingButton, playerViewConfig.isShowCasting() && !UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isCastingIngressExperimentEnabled() && !ChildUserUtil.INSTANCE.isChildUser(getContext()));
        setImageButtonVisibility(this.mPlayQueueButton, playerViewConfig.isShowPlayQueue() && !Feature.Hide_Mini_Player_PlayQueue.isEnabled() && z);
        setImageButtonVisibility(this.mShuffleButton, playerViewConfig.isShowShuffle());
        setImageButtonVisibility(this.mRepeatButton, playerViewConfig.isShowRepeat());
        setImageButtonVisibility(this.mThumbsUpButton, playerViewConfig.isShowRating());
        setImageButtonVisibility(this.mThumbsDownButton, playerViewConfig.isShowRating());
        showHideButtonsForStage();
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerViewConfig getPlayerViewConfig() {
        return this.mPlayerViewConfig;
    }

    private void attachClickListeners() {
        setImageButtonClickListener(this.mNextButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$0(view);
            }
        });
        this.mPlayButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$1(view);
            }
        });
        setImageButtonClickListener(this.mPrevButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$2(view);
            }
        });
        setImageButtonClickListener(this.mShuffleButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$3(view);
            }
        });
        setImageButtonClickListener(this.mRepeatButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$4(view);
            }
        });
        setImageButtonClickListener(this.mThumbsUpButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$5(view);
            }
        });
        setImageButtonClickListener(this.mThumbsDownButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$6(view);
            }
        });
        setImageButtonClickListener(this.mPlayQueueButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$7(view);
            }
        });
        setImageButtonClickListener(this.mCastingButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$8(view);
            }
        });
        setImageButtonClickListener(this.mAddToLibraryButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$9(view);
            }
        });
        setImageButtonClickListener(this.mContextMenuButton, new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$attachClickListeners$10(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$0(View view) {
        next(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$1(View view) {
        PlayStatus playStatus = Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getPlayStatus();
        if (NowPlayingUtil.isVideoPlaying() && playStatus != null && !playStatus.willPlay()) {
            this.mPlayerNavigationListener.showNowPlaying(InteractionType.TAP);
        }
        play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$2(View view) {
        prev(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$3(View view) {
        shuffle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$4(View view) {
        repeat();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$5(View view) {
        thumbsUp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$6(View view) {
        thumbsDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$7(View view) {
        showPlayQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$8(View view) {
        showCastingView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$9(View view) {
        addToLibrary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachClickListeners$10(View view) {
        showContextMenu();
    }

    private void setLoading(final boolean loading) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setIndeterminate(loading);
        }
    }

    private void setImageButtonClickListener(final ImageButton button, View.OnClickListener listener) {
        if (button != null) {
            button.setOnClickListener(listener);
        }
    }

    protected void setImageButtonVisibility(final ImageButton button, boolean visible) {
        if (button != null) {
            button.setVisibility(visible ? 0 : 8);
        }
    }

    private void setBaseButtonVisibility(final BaseButton button, final boolean visible) {
        if (button != null) {
            button.setVisibility(visible ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentViewsOnNext() {
        MiniPlayerContentView miniPlayerContentView = this.mMiniPlayerContentViewPrev;
        this.mMiniPlayerContentViewPrev = this.mMiniPlayerContentViewCurrent;
        this.mMiniPlayerContentViewCurrent = this.mMiniPlayerContentViewNext;
        this.mMiniPlayerContentViewNext = miniPlayerContentView;
        refreshTouchListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentViewsOnPrevious() {
        MiniPlayerContentView miniPlayerContentView = this.mMiniPlayerContentViewPrev;
        this.mMiniPlayerContentViewPrev = this.mMiniPlayerContentViewNext;
        this.mMiniPlayerContentViewNext = this.mMiniPlayerContentViewCurrent;
        this.mMiniPlayerContentViewCurrent = miniPlayerContentView;
        refreshTouchListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play() {
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            onPlayerClickListener.onPlayPauseClicked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean next(InteractionType type) {
        if (type == null) {
            type = InteractionType.TAP;
        }
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            return onPlayerClickListener.onNextClicked(type);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean prev(InteractionType type) {
        if (type == null) {
            type = InteractionType.TAP;
        }
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            return onPlayerClickListener.onPrevClicked(type);
        }
        return false;
    }

    private void shuffle() {
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            onPlayerClickListener.onShuffleClicked();
        }
    }

    private void repeat() {
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            onPlayerClickListener.onRepeatClicked();
        }
    }

    private void thumbsUp() {
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            onPlayerClickListener.onThumbsUpClicked();
        }
    }

    private void thumbsDown() {
        OnPlayerClickListener onPlayerClickListener = this.mOnPlayerClickListener;
        if (onPlayerClickListener != null) {
            onPlayerClickListener.onThumbsDownClicked();
        }
    }

    private void showPlayQueue() {
        PlayerNavigationListener playerNavigationListener = this.mPlayerNavigationListener;
        if (playerNavigationListener != null) {
            playerNavigationListener.showPlayQueue();
        }
    }

    private void showCastingView() {
        PlayerNavigationListener playerNavigationListener = this.mPlayerNavigationListener;
        if (playerNavigationListener != null) {
            playerNavigationListener.showCastingView();
        }
    }

    private void addToLibrary() {
        PlayerNavigationListener playerNavigationListener = this.mPlayerNavigationListener;
        if (playerNavigationListener != null) {
            playerNavigationListener.addTrackToLibrary();
        }
    }

    private void showPauseIcon() {
        BaseButton baseButton = this.mPlayButton;
        if (baseButton != null) {
            baseButton.clearAnimation();
            this.mPlayButton.setIcon(this.mContext.getDrawable(R.drawable.mini_player_btn_pause_tray));
            if (this.mPlayerViewConfig != null) {
                this.mPlayButton.setContentDescription(getResources().getString(R.string.dmusic_player_btn_pause_description));
            }
        }
    }

    private void showPlayIcon() {
        BaseButton baseButton = this.mPlayButton;
        if (baseButton != null) {
            baseButton.clearAnimation();
            this.mPlayButton.setIcon(this.mContext.getDrawable(R.drawable.mini_player_btn_play_tray));
            this.mPlayButton.setContentDescription(getResources().getString(R.string.dmusic_player_btn_play_description));
        }
    }

    private void attachVerticalDragAnimatorListener() {
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        setOnTouchListener(touchEventHandler.getTouchEventListener());
        touchEventHandler.setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView.3
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(final float x) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(final float x) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(final float y) {
                if (Math.abs(y) < MiniPlayerView.this.getHeight() || MiniPlayerView.this.mPlayerVerticalSwipeListener == null) {
                    return;
                }
                MiniPlayerView.this.mPlayerVerticalSwipeListener.onSwipeDown();
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(final float y) {
                if (Math.abs(y) < MiniPlayerView.this.getHeight() || MiniPlayerView.this.mPlayerVerticalSwipeListener == null) {
                    return;
                }
                MiniPlayerView.this.mPlayerVerticalSwipeListener.onSwipeUp();
            }
        });
        touchEventHandler.setOnClickListener(this.mContext, this.mOnClickListener);
        touchEventHandler.setOnLongClickListener(this.mOnLongClickListener);
    }

    private void attachHorizontalDragAnimatorListener() {
        this.mTouchEventHandler.setOnDragEventListener(new OnDragEventListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView.4
            @Override // com.amazon.mp3.view.OnDragEventListener
            public void onDragDown(final float y) {
            }

            @Override // com.amazon.mp3.view.OnDragEventListener
            public void onDragUp(final float y) {
            }

            @Override // com.amazon.mp3.view.OnDragEventListener
            public void onStartDrag() {
                MiniPlayerView.this.mViewSlideAnimator.cancelAnimations();
                MiniPlayerView.this.mViewHorizontalDragAnimator.initializeViews(MiniPlayerView.this.mMiniPlayerContentViewCurrent, MiniPlayerView.this.mMiniPlayerContentViewPrev, MiniPlayerView.this.mMiniPlayerContentViewNext);
            }

            @Override // com.amazon.mp3.view.OnDragEventListener
            public void onDragLeft(final float x) {
                MiniPlayerView.this.mViewHorizontalDragAnimator.dragView(x);
            }

            @Override // com.amazon.mp3.view.OnDragEventListener
            public void onDragRight(final float x) {
                MiniPlayerView.this.mViewHorizontalDragAnimator.dragView(x);
            }
        });
        this.mTouchEventHandler.setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerView.5
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(final float x) {
                if (Math.abs(x) > MiniPlayerView.this.mMiniPlayerContentViewCurrent.getWidth() / 2) {
                    if (MiniPlayerView.this.mHasNext) {
                        if (MiniPlayerView.this.next(InteractionType.DRAG)) {
                            MiniPlayerView.this.updateContentViewsOnNext();
                            MiniPlayerView.this.mViewHorizontalDragAnimator.updateViews(MiniPlayerView.this.mMiniPlayerContentViewCurrent, MiniPlayerView.this.mMiniPlayerContentViewPrev, MiniPlayerView.this.mMiniPlayerContentViewNext);
                        }
                    } else {
                        MiniPlayerView.this.OnSwipeLeftError();
                    }
                }
                MiniPlayerView.this.mViewHorizontalDragAnimator.resetViews();
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(final float x) {
                if (Math.abs(x) > MiniPlayerView.this.mMiniPlayerContentViewCurrent.getWidth() / 2) {
                    if (NowPlayingUtil.isAdjacentItemVideo(false)) {
                        MiniPlayerView.this.prev(InteractionType.SWIPE);
                    } else if (!NowPlayingUtil.isVideoPlaying() || !NowPlayingUtil.isStartOfPlayQueue()) {
                        if (MiniPlayerView.this.mHasPrev) {
                            if (MiniPlayerView.this.prev(InteractionType.DRAG) && !MiniPlayerView.this.mOnPlayerClickListener.canRestart()) {
                                MiniPlayerView.this.updateContentViewsOnPrevious();
                                MiniPlayerView.this.mViewHorizontalDragAnimator.updateViews(MiniPlayerView.this.mMiniPlayerContentViewCurrent, MiniPlayerView.this.mMiniPlayerContentViewPrev, MiniPlayerView.this.mMiniPlayerContentViewNext);
                            }
                        } else {
                            MiniPlayerView.this.OnSwipeRightError();
                        }
                    } else {
                        MiniPlayerView.this.play();
                    }
                }
                MiniPlayerView.this.mViewHorizontalDragAnimator.resetViews();
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(final float y) {
                if (Math.abs(y) >= MiniPlayerView.this.getHeight() && MiniPlayerView.this.mPlayerVerticalSwipeListener != null) {
                    MiniPlayerView.this.mPlayerVerticalSwipeListener.onSwipeDown();
                }
                MiniPlayerView.this.mViewHorizontalDragAnimator.resetViews();
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(final float y) {
                if (Math.abs(y) >= MiniPlayerView.this.getHeight() && MiniPlayerView.this.mPlayerVerticalSwipeListener != null) {
                    MiniPlayerView.this.mPlayerVerticalSwipeListener.onSwipeUp();
                }
                MiniPlayerView.this.mViewHorizontalDragAnimator.resetViews();
            }
        });
        this.mTouchEventHandler.setOnClickListener(this.mContext, this.mOnClickListener);
        this.mTouchEventHandler.setOnLongClickListener(this.mOnLongClickListener);
    }

    private void refreshTouchListener() {
        this.mMiniPlayerContentViewNext.setOnTouchListener(null);
        this.mMiniPlayerContentViewPrev.setOnTouchListener(null);
        this.mMiniPlayerContentViewCurrent.setOnTouchListener(this.mTouchEventHandler.getTouchEventListener());
    }

    public ImageButton getNextButton() {
        return this.mNextButton;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isInPlayQueuePage(Context context) {
        return (context instanceof NavigationProvider) && (((NavigationProvider) context).getFragmentController().getCurrentFragment() instanceof PlayQueueFragment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isInFullScreenStageOverlay(Context context) {
        if (context instanceof NavigationProvider) {
            NavigationProvider navigationProvider = (NavigationProvider) context;
            if ((navigationProvider.getFragmentController().getCurrentFragment() instanceof NowPlayingFragment) || (navigationProvider.getFragmentController().getCurrentFragment() instanceof PlayQueueFragment)) {
                return true;
            }
        }
        return false;
    }

    public void showHideButtonsForStage() {
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled() && isInFullScreenStageOverlay(this.mContext)) {
            setImageButtonVisibility(this.mCastingButton, false);
            if (isLyricsSharingEnabled()) {
                setImageButtonVisibility(this.mNextButton, false);
            } else {
                setImageButtonVisibility(this.mNextButton, true);
            }
            if (ScreenUtil.isLargeOrExtraLargeScreen()) {
                setImageButtonVisibility(this.mAddToLibraryButton, false);
                setImageButtonVisibility(this.mPlayQueueButton, false);
                setImageButtonVisibility(this.mShuffleButton, false);
                setImageButtonVisibility(this.mRepeatButton, false);
                setImageButtonVisibility(this.mThumbsUpButton, false);
                setImageButtonVisibility(this.mThumbsDownButton, false);
            }
        }
    }
}
