package com.amazon.mp3.playback.view;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.mp3.cloudqueue.PlaybackActionCapability;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.cloudqueue.sequencer.PlaybackActionCapabilityQueryable;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.fragment.NPSOverlayMetricsReporter;
import com.amazon.mp3.playback.playbackcontrol.ActionResult;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import com.amazon.mp3.util.DynamicUpsellTranslator;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.player.PlayerViewAction;
import com.amazon.mp3.view.player.PlayerViewEnabledStateManager;
import com.amazon.mp3.voice.NowPlayingVoiceAccessProvider;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.Closeable;
import rx.Observable;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class PersistentPlayer implements Closeable {
    private static final String TAG = PersistentPlayer.class.getSimpleName();
    private static PublishSubject<Boolean> sOnNextClickedSubject = PublishSubject.create();
    private static boolean sVisible;
    protected PlayerViewEnabledStateManager enabledStateManager;
    protected BaseButton.StyleBuilder largeGlassIconBuilder;
    protected final Context mContext;
    protected final View.OnCreateContextMenuListener mCreateContextMenuListener;
    private PlayStatus mCurrentStatus;
    protected final View.OnLongClickListener mLongClickListener;
    protected MediaItem mMediaItem;
    protected final BaseButton mNextButton;
    private PlayStatus mPendingStatus;
    protected final BaseButton mPlayPauseButton;
    protected ActionValidatedPlaybackController mPlaybackController;
    private final View mPlayerView;
    protected final BaseButton mPrevButton;
    private final ProgressBar mProgressBar;
    protected BaseButton.StyleBuilder mediumGlassIconBuilder;
    protected BaseButton.StyleBuilder mediumIconBuilder;
    protected BaseButton.StyleBuilder smallIconBuilder;
    protected StyleSheet styleSheet;
    protected BaseButton.StyleBuilder tinyIconBuilder;
    protected Handler mHandler = new Handler();
    protected View.OnClickListener mPlayerPlayButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f$0.lambda$new$0(view);
        }
    };
    private boolean mPrevButtonEnabled = false;
    private final NPSOverlayMetricsReporter mNPSOverlayMetricsReporter = NPSOverlayMetricsReporter.INSTANCE.getNPSOverlayMetricsReporter();
    private final Runnable mButtonUpdateRunnable = new Runnable() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1();
        }
    };
    private final OnPlayStateChangedListener mPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda2
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public final void onPlayStateChanged() {
            this.f$0.lambda$new$4();
        }
    };
    private final SkipLimitProviderImpl.OnSkipLimitUpdateListener mSkipLimitUpdateListener = new SkipLimitProviderImpl.OnSkipLimitUpdateListener() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda3
        @Override // com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl.OnSkipLimitUpdateListener
        public final void onSkipLimitUpdated() {
            this.f$0.lambda$new$5();
        }
    };
    private View.OnClickListener mPlayerPrevButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda4
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f$0.lambda$new$6(view);
        }
    };
    private View.OnClickListener mPlayerNextButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda5
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f$0.lambda$new$7(view);
        }
    };

    public enum PlayerType {
        GENERIC,
        NOW_PLAYING,
        PRIME_STATIONS,
        ALEXA,
        VIDEO,
        CLOUDQUEUE
    }

    public static synchronized boolean isVisible() {
        return sVisible;
    }

    public static synchronized void setVisible(boolean visible) {
        sVisible = visible;
    }

    public Observable<Boolean> getOnNextClickedSubject() {
        return sOnNextClickedSubject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        onPlayPauseButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        PlayStatus playStatus = this.mPendingStatus;
        if (playStatus != null) {
            updatePlaybackButton(playStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4() {
        showIfRequired();
        updatePlaybackButton();
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem != null && !currentMediaItem.equals(this.mMediaItem)) {
            this.mMediaItem = currentMediaItem;
        }
        MediaItemHelper.getAbstractItemObservable(this.mMediaItem).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$new$2((AbstractItem) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                PersistentPlayer.lambda$new$3((Throwable) obj);
            }
        });
        updateViewEnabledState();
        updateButtonState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(AbstractItem abstractItem) throws Throwable {
        updatePrevButton(abstractItem, this.mMediaItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$3(Throwable th) throws Throwable {
        th.printStackTrace();
        Log.error(TAG, "Failed updating previous button", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$5() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.playback.view.PersistentPlayer$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateNextButtonImageState();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6(View view) {
        onPrevButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$7(View view) {
        onNextButtonClick();
    }

    protected PersistentPlayer(View playerView, ActionValidatedPlaybackController playbackController, View.OnCreateContextMenuListener createContextMenuListener, View.OnLongClickListener longClickListener) {
        this.mContext = playerView.getContext();
        this.mCreateContextMenuListener = createContextMenuListener;
        this.mLongClickListener = longClickListener;
        this.mPlaybackController = playbackController;
        this.mPlayerView = playerView;
        playerView.setVisibility(8);
        PlayerViewEnabledStateManager playerViewEnabledStateManager = new PlayerViewEnabledStateManager(this.mPlaybackController);
        this.enabledStateManager = playerViewEnabledStateManager;
        playerViewEnabledStateManager.setControlsAreEnabled(true);
        ProgressBar progressBar = (ProgressBar) playerView.findViewById(R.id.progress);
        this.mProgressBar = progressBar;
        progressBar.setOnClickListener(this.mPlayerPlayButtonClickListener);
        BaseButton baseButton = (BaseButton) playerView.findViewById(com.amazon.mp3.automotiveOS.R.id.PersistentPlayerPlayButton);
        this.mPlayPauseButton = baseButton;
        baseButton.setOnClickListener(this.mPlayerPlayButtonClickListener);
        BaseButton baseButton2 = (BaseButton) playerView.findViewById(com.amazon.mp3.automotiveOS.R.id.PersistentPlayerPrevButton);
        this.mPrevButton = baseButton2;
        baseButton2.setOnClickListener(this.mPlayerPrevButtonClickListener);
        BaseButton baseButton3 = (BaseButton) playerView.findViewById(com.amazon.mp3.automotiveOS.R.id.PersistentPlayerNextButton);
        this.mNextButton = baseButton3;
        baseButton3.setOnClickListener(this.mPlayerNextButtonClickListener);
    }

    public static PersistentPlayer create(PlayerType persistentPlayerType, View playerView, ActionValidatedPlaybackController playbackController, View.OnCreateContextMenuListener createContextMenuListener, View.OnLongClickListener longClickListener, LifecycleOwner lifecycleOwner, StyleSheet styleSheet, NowPlayingVoiceAccessProvider voiceAccessProvider) {
        return new NowPlayingPersistentPlayer(playerView, playbackController, createContextMenuListener, longClickListener, persistentPlayerType, lifecycleOwner, styleSheet, voiceAccessProvider);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setControlsEnabled(boolean areControlsEnabled) {
        this.enabledStateManager.setControlsAreEnabled(areControlsEnabled);
        updateViewEnabledState();
    }

    protected void updateViewEnabledState() {
        this.enabledStateManager.updateConfigForMediaItem(this.mPlaybackController.getController().getCurrentMediaItem());
        setEnabledState(this.mPlayPauseButton, PlayerViewAction.PLAY);
        setEnabledState(this.mPrevButton, PlayerViewAction.PREVIOUS);
        setEnabledState(this.mNextButton, PlayerViewAction.NEXT);
    }

    protected void updateButtonState() {
        updatePlaybackButton();
        updatePreviousContentDescription();
        updateNextContentDescription();
    }

    protected void setEnabledState(final View view, final PlayerViewAction playerAction) {
        setEnabledState(view, playerAction, false);
    }

    protected void setEnabledState(final View view, final PlayerViewAction playerAction, final boolean invisibleWhenGone) {
        if (view != null) {
            this.enabledStateManager.getEnabledViewState(playerAction).updateViewState(view, Boolean.valueOf(invisibleWhenGone));
        }
    }

    protected void startListeningToPlaybackEvents() {
        this.mPlaybackController.addOnPlayStateChangedListener(this.mPlayStateChangedListener);
    }

    protected void startListeningToSkipLimitUpdates() {
        SkipLimitProviderImpl.INSTANCE.addOnSkipLimitUpdateListener(this.mSkipLimitUpdateListener);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mPlayStateChangedListener);
        SkipLimitProviderImpl.INSTANCE.removeOnSkipLimitUpdateListener(this.mSkipLimitUpdateListener);
    }

    private void updateNextContentDescription() {
        this.mNextButton.setContentDescription(this.mContext.getString(this.mNextButton.isEnabled() ? com.amazon.mp3.automotiveOS.R.string.dmusic_player_btn_next_description : com.amazon.mp3.automotiveOS.R.string.dmusic_player_btn_next_description_disabled));
    }

    public void hide() {
        setVisible(false);
        this.mPlayerView.setVisibility(8);
    }

    public void show() {
        setVisible(true);
        this.mPlayerView.setVisibility(0);
    }

    public void showIfRequired() {
        boolean z = this.mPlaybackController.canPlay() || this.mPlaybackController.getCurrentMediaItem() != null;
        Log.debug(TAG, "showIfRequired: nowPlayingHasSongs=" + z);
        if (z) {
            show();
        } else {
            hide();
        }
    }

    private void updatePreviousContentDescription() {
        this.mPrevButton.setContentDescription(this.mContext.getString(this.mPrevButton.isEnabled() ? com.amazon.mp3.automotiveOS.R.string.dmusic_player_previous_button_description : com.amazon.mp3.automotiveOS.R.string.dmusic_player_previous_button_description_disabled));
    }

    private void setLoadingImage() {
        if (this.enabledStateManager.getControlsAreEnabled()) {
            this.mPlayPauseButton.setVisibility(4);
            this.mProgressBar.setVisibility(0);
        }
    }

    protected void initStyle(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilder;
        Context context;
        int i;
        IconSizeKey iconSizeKey = IconSizeKey.LARGE;
        IconStyleKey iconStyleKey = IconStyleKey.GLASS;
        this.largeGlassIconBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        IconSizeKey iconSizeKey2 = IconSizeKey.MEDIUM;
        this.mediumGlassIconBuilder = styleSheet.getIconBuilder(iconSizeKey2, iconStyleKey);
        IconStyleKey iconStyleKey2 = IconStyleKey.PRIMARY;
        this.mediumIconBuilder = styleSheet.getIconBuilder(iconSizeKey2, iconStyleKey2);
        this.smallIconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, iconStyleKey2);
        this.tinyIconBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, iconStyleKey2);
        if (isXSmallScreenPortrait(styleSheet) || (ScreenUtil.isLandscape() && !isTabletLandscape(styleSheet))) {
            iconSizeKey = iconSizeKey2;
        }
        ButtonSize iconSize = styleSheet.getIconSize(iconSizeKey);
        if (iconSize != null) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(this.mProgressBar, iconSize.getWidth().intValue(), iconSize.getHeight().intValue(), Boolean.FALSE, 0, 0, 0, 0);
        }
        if (isXSmallScreenPortrait(styleSheet) || (ScreenUtil.isLandscape() && !isTabletLandscape(styleSheet))) {
            styleBuilder = this.mediumGlassIconBuilder;
        } else {
            styleBuilder = this.largeGlassIconBuilder;
        }
        if (styleBuilder != null) {
            styleBuilder.withIcon(getContext().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.ic_playback_play)).applyStyle(this.mPlayPauseButton);
        }
        BaseButton.StyleBuilder secondaryIconBuilder = getSecondaryIconBuilder(styleSheet);
        if (secondaryIconBuilder != null) {
            secondaryIconBuilder.withIcon(getContext().getDrawable(Feature.prev_button_enabled.isEnabled() ? com.amazon.mp3.automotiveOS.R.drawable.ic_playback_previous : com.amazon.mp3.automotiveOS.R.drawable.ic_playback_previous_disabled)).applyStyle(this.mPrevButton);
            if (isNextButtonDisabled()) {
                context = getContext();
                i = com.amazon.mp3.automotiveOS.R.drawable.ic_playback_next_disabled;
            } else {
                context = getContext();
                i = com.amazon.mp3.automotiveOS.R.drawable.ic_playback_next;
            }
            secondaryIconBuilder.withIcon(context.getDrawable(i)).applyStyle(this.mNextButton);
        }
    }

    protected boolean isXSmallScreenPortrait(final StyleSheet styleSheet) {
        return ScreenUtil.isPortrait() && styleSheet.getWidthScreenSizeKey(getContext()).compareTo(ScreenSizeKey.XSMALL) <= 0;
    }

    protected boolean isMediumOrLargerScreenPortrait(final StyleSheet styleSheet) {
        return ScreenUtil.isPortrait() && styleSheet.getWidthScreenSizeKey(getContext()).compareTo(ScreenSizeKey.MEDIUM) >= 0;
    }

    protected boolean isTabletLandscape(final StyleSheet styleSheet) {
        return styleSheet.getLandscapeType(this.mContext).equals(LandscapeType.TABLET_LANDSCAPE);
    }

    private boolean isEquivalentPlayStatus(final PlayStatus status1, final PlayStatus status2) {
        return (status1 == null || status2 == null || status1 == status2) ? status1 == status2 : status1.willPlay() == status2.willPlay() && status1.isDelayed() == status2.isDelayed();
    }

    private void updatePlaybackButton() {
        PlayStatus playStatus = this.mPlaybackController.getPlayStatus();
        PlayStatus playStatus2 = playStatus.isDelayed() ? PlayStatus.RENDERING : playStatus;
        if (isEquivalentPlayStatus(this.mCurrentStatus, playStatus)) {
            this.mPendingStatus = null;
            this.mHandler.removeCallbacks(this.mButtonUpdateRunnable);
            return;
        }
        if (isEquivalentPlayStatus(this.mPendingStatus, playStatus)) {
            return;
        }
        PlayStatus playStatus3 = this.mCurrentStatus;
        if (playStatus3 == null || playStatus == playStatus2) {
            this.mHandler.removeCallbacks(this.mButtonUpdateRunnable);
            updatePlaybackButton(playStatus);
            return;
        }
        if (!isEquivalentPlayStatus(playStatus3, playStatus2)) {
            updatePlaybackButton(playStatus2);
        }
        this.mPendingStatus = playStatus;
        this.mHandler.removeCallbacks(this.mButtonUpdateRunnable);
        this.mHandler.postDelayed(this.mButtonUpdateRunnable, 250L);
    }

    private void updatePlaybackButton(final PlayStatus playStatus) {
        this.mCurrentStatus = playStatus;
        this.mPendingStatus = null;
        if (playStatus.isDelayed()) {
            setLoadingImage();
        } else {
            setPlayButtonImage(playStatus.willPlay());
        }
    }

    protected void updatePrevButton(EntityEligibilitiesProvider entityEligibilitiesProvider, MediaItem mediaItem) {
        Drawable drawable;
        boolean zIsEnabled = Feature.prev_button_enabled.isEnabled();
        if (entityEligibilitiesProvider != null) {
            zIsEnabled = new EligibilityUtil().getPrevButtonEnabled(entityEligibilitiesProvider);
        }
        if (!UserSubscriptionUtil.getUserSubscription().isNightwingOnly() && !UserSubscriptionUtil.getUserSubscription().isSonicRush()) {
            zIsEnabled = zIsEnabled || mediaItem.isInterlude();
        }
        boolean z = zIsEnabled || mediaItem.isBOP();
        BaseButton.StyleBuilder secondaryIconBuilder = getSecondaryIconBuilder(this.styleSheet);
        if (secondaryIconBuilder != null && z != this.mPrevButtonEnabled) {
            if (z) {
                drawable = getContext().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.ic_playback_previous);
            } else {
                drawable = getContext().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.ic_playback_previous_disabled);
            }
            secondaryIconBuilder.withIcon(drawable).applyStyle(this.mPrevButton);
        }
        this.mPrevButtonEnabled = z;
    }

    private void onPlayPauseButtonClick() {
        ActionType actionType;
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        this.mPlaybackController.togglePlaying();
        if (this.mPlaybackController.getPlayStatus().willPlay()) {
            actionType = ActionType.PLAY;
        } else {
            actionType = ActionType.PAUSE;
        }
        sendUiClickEvent(actionType);
    }

    private void setPlayButtonImage(boolean playing) {
        this.mProgressBar.setVisibility(8);
        this.mPlayPauseButton.setIcon(this.mContext.getDrawable(playing ? com.amazon.mp3.automotiveOS.R.drawable.ic_playback_pause : com.amazon.mp3.automotiveOS.R.drawable.ic_play_bauhaus));
        this.mPlayPauseButton.setContentDescription(this.mContext.getString(playing ? com.amazon.mp3.automotiveOS.R.string.dmusic_player_btn_pause_description : com.amazon.mp3.automotiveOS.R.string.dmusic_player_btn_play_description));
        this.mPlayPauseButton.setVisibility(0);
    }

    protected void onNextButtonClick() {
        this.mNPSOverlayMetricsReporter.setPositionOnNextPreviousButtonClick(this.mPlaybackController.getPositionMillis());
        sendUiClickEvent(ActionType.SKIP_NEXT);
        ActionResult actionResultSkipNextForResult = this.mPlaybackController.skipNextForResult();
        sOnNextClickedSubject.onNext(Boolean.TRUE);
        Log.debug(TAG, "onNextButtonClick actionResult: " + actionResultSkipNextForResult);
        if (actionResultSkipNextForResult == ActionResult.OK || !Feature.skip_limit_blocking_upsell.isEnabled()) {
            return;
        }
        displayBlockingUpsell(UpsellReason.TRACK_SKIP_LIMIT, this.mPlaybackController.getCurrentMediaItem());
    }

    protected void onPrevButtonClick() {
        this.mNPSOverlayMetricsReporter.setPositionOnNextPreviousButtonClick(this.mPlaybackController.getPositionMillis());
        sendUiClickEvent(ActionType.SKIP_PREVIOUS);
        PlaybackActionCapability actionCapability = getActionCapability("PREVIOUS");
        Log.debug(TAG, "onPrevButtonClick capability: " + actionCapability);
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (this.mPrevButtonEnabled && (actionCapability == null || actionCapability.getActionStatus() == 0)) {
            this.mPlaybackController.previousOrRestart();
        } else {
            if (UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices()) {
                return;
            }
            DynamicUpsellTranslator.showBlockingCapabilityUpsell((FragmentActivity) getContext(), currentMediaItem, QueueEntityCapabilityTranslator.QueueEntityCapability.PREVIOUS);
        }
    }

    private boolean isNextButtonDisabled() {
        return this.mPlaybackController.getSkipNextAvailability() == ActionAvailability.UNAVAILABLE_SKIP_LIMIT;
    }

    private void displayBlockingUpsell(final UpsellReason upsellReason, final MediaItem mediaItem) {
        UpsellUtil.showBlockingUpsell((FragmentActivity) getContext(), UpsellUtil.getAsinFromMediaItem(mediaItem), upsellReason, UpsellSourceEntity.TRACK);
    }

    private BaseButton.StyleBuilder getSecondaryIconBuilder(StyleSheet styleSheet) {
        if (isXSmallScreenPortrait(styleSheet) || (ScreenUtil.isLandscape() && !isTabletLandscape(styleSheet))) {
            return this.smallIconBuilder;
        }
        return this.mediumIconBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNextButtonImageState() {
        Context context;
        int i;
        BaseButton.StyleBuilder secondaryIconBuilder = getSecondaryIconBuilder(this.styleSheet);
        if (secondaryIconBuilder != null) {
            if (isNextButtonDisabled()) {
                context = getContext();
                i = com.amazon.mp3.automotiveOS.R.drawable.ic_playback_next_disabled;
            } else {
                context = getContext();
                i = com.amazon.mp3.automotiveOS.R.drawable.ic_playback_next;
            }
            secondaryIconBuilder.withIcon(context.getDrawable(i)).applyStyle(this.mNextButton);
        }
    }

    @Nullable
    private PlaybackActionCapability getActionCapability(String action) {
        EntityItem currentMediaItem = this.mPlaybackController.getController().getCurrentMediaItem();
        if (currentMediaItem instanceof PlaybackActionCapabilityQueryable) {
            return ((PlaybackActionCapabilityQueryable) currentMediaItem).getPlaybackActionCapability(action);
        }
        return null;
    }

    protected void sendUiClickEvent(final ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING).withEventTime(System.currentTimeMillis()).build());
    }
}
