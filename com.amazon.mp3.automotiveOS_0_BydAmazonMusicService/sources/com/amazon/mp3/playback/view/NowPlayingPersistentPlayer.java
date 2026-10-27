package com.amazon.mp3.playback.view;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.view.PersistentPlayer;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.view.TrackRatingListener;
import com.amazon.mp3.view.player.EnabledViewState;
import com.amazon.mp3.view.player.NowPlayingViewConfig;
import com.amazon.mp3.view.player.PlayerRatingViewManager;
import com.amazon.mp3.view.player.PlayerViewAction;
import com.amazon.mp3.voice.NowPlayingVoiceAccessProvider;
import com.amazon.music.explore.widgets.views.IngressButtonView;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.utils.SpacerParser;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.rxjava3.functions.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class NowPlayingPersistentPlayer extends PersistentPlayer {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) NowPlayingPersistentPlayer.class);
    private final String TAG;
    private BaseButton mAlexaButton;
    private final View.OnClickListener mAlexaListener;
    private final Handler mHandler;
    private LifecycleOwner mLifecycleOwner;
    private BaseButton mOuterLeftButton;
    private BaseButton mOuterRightButton;
    private final ConstraintLayout mPersistentPlayer;
    protected PlayQueueButton mPlayQueueButton;
    private NowPlayingViewConfig.PlayerContentType mPlayerContentType;
    private View.OnClickListener mPlayerRepeatButtonClickListener;
    private View.OnClickListener mPlayerShuffleButtonClickListener;
    private PersistentPlayer.PlayerType mPlayerType;
    private View mPlayerView;
    protected BaseButton mRepeatButton;
    private final View.OnClickListener mShareListener;
    protected BaseButton mShuffleButton;
    protected BaseButton mThumbsDownButton;
    protected BaseButton mThumbsUpButton;
    private final PlayerRatingViewManager ratingViewManager;
    private NowPlayingVoiceAccessProvider voiceAccessProvider;
    private final LinearLayout xrayIngressButtonContainer;

    private void showShare(final boolean shouldShow) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        if (VoiceManagerSingleton.isInitialized()) {
            this.voiceAccessProvider.onAlexaClicked(MediaItemExtensions.isVideo(this.mPlaybackController.getCurrentMediaItem()) ? AlexaEntryPoint.NOW_PLAYING_VIDEO : AlexaEntryPoint.NOW_PLAYING_AUDIO);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(View view) {
        MediaItemHelper.shareMediaItem(getContext(), this.mPlaybackController.getCurrentMediaItem());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(View view) {
        onShuffleButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(View view) {
        onRepeatButtonClick();
    }

    public NowPlayingPersistentPlayer(View playerView, ActionValidatedPlaybackController playbackController, View.OnCreateContextMenuListener createContextMenuListener, View.OnLongClickListener longClickListener, PersistentPlayer.PlayerType playerType, LifecycleOwner lifecycleOwner, StyleSheet styleSheet, NowPlayingVoiceAccessProvider voiceAccessProvider) {
        super(playerView, playbackController, createContextMenuListener, longClickListener);
        this.TAG = getClass().getSimpleName();
        this.voiceAccessProvider = null;
        this.ratingViewManager = new PlayerRatingViewManager(this.mPlaybackController);
        this.mPlayerContentType = NowPlayingViewConfig.PlayerContentType.DEFAULT;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(view);
            }
        };
        this.mAlexaListener = onClickListener;
        this.mShareListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1(view);
            }
        };
        this.mPlayerShuffleButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$2(view);
            }
        };
        this.mPlayerRepeatButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$3(view);
            }
        };
        this.mPlayerType = playerType;
        this.mPlayerView = playerView;
        this.mLifecycleOwner = lifecycleOwner;
        this.voiceAccessProvider = voiceAccessProvider;
        this.styleSheet = styleSheet;
        this.mHandler = new Handler(getContext().getMainLooper());
        this.mPersistentPlayer = (ConstraintLayout) playerView.findViewById(R.id.PersistentPlayerControls);
        this.mOuterLeftButton = (BaseButton) playerView.findViewById(R.id.PersistentPlayerOuterLeftButton);
        this.mOuterRightButton = (BaseButton) playerView.findViewById(R.id.PersistentPlayerOuterRightButton);
        PlayQueueButton playQueueButton = (PlayQueueButton) playerView.findViewById(R.id.PersistentPlayerPlayQueueButton);
        this.mPlayQueueButton = playQueueButton;
        playQueueButton.setPageType(PageType.NOW_PLAYING);
        BaseButton baseButton = (BaseButton) playerView.findViewById(R.id.iv_alexa);
        this.mAlexaButton = baseButton;
        baseButton.setOnClickListener(onClickListener);
        this.xrayIngressButtonContainer = (LinearLayout) playerView.findViewById(R.id.xray_ingress_button);
        initStyle(styleSheet);
        updateViewEnabledState();
        updateButtonState();
        startListeningToPlaybackEvents();
        startListeningToSkipLimitUpdates();
        resizePersistentPlayer((ConstraintLayout) playerView, styleSheet);
    }

    @Override // com.amazon.mp3.playback.view.PersistentPlayer
    protected void initStyle(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilder2;
        if (styleSheet == null) {
            return;
        }
        super.initStyle(styleSheet);
        if (isXSmallScreenPortrait(styleSheet)) {
            styleBuilder = this.tinyIconBuilder;
        } else {
            styleBuilder = this.smallIconBuilder;
        }
        if (styleBuilder != null) {
            styleBuilder.withIcon(getContext().getDrawable(R.drawable.ic_playback_playqueue)).applyStyle(this.mPlayQueueButton);
        }
        if (isXSmallScreenPortrait(styleSheet)) {
            styleBuilder2 = this.smallIconBuilder;
        } else {
            styleBuilder2 = this.mediumIconBuilder;
        }
        if (styleBuilder2 != null) {
            styleBuilder2.withIcon(getContext().getDrawable(R.drawable.ic_alexa_bauhaus)).applyStyle(this.mAlexaButton);
        }
    }

    private void initContentTypeDependentControls() {
        BaseButton.StyleBuilder styleBuilder;
        final MediaItem currentMediaItem = this.mPlaybackController.getController().getCurrentMediaItem();
        NowPlayingViewConfig.PlayerContentType playerContentTypeFromMediaItem = NowPlayingViewConfig.PlayerContentType.fromMediaItem(currentMediaItem);
        this.mPlayerContentType = playerContentTypeFromMediaItem;
        if (playerContentTypeFromMediaItem.equals(NowPlayingViewConfig.PlayerContentType.DEFAULT)) {
            return;
        }
        this.enabledStateManager.updateConfigForMediaItem(this.mPlaybackController.getController().getCurrentMediaItem());
        if (this.enabledStateManager.isSupported(PlayerViewAction.RATE)) {
            BaseButton baseButton = this.mOuterRightButton;
            this.mThumbsUpButton = baseButton;
            BaseButton baseButton2 = this.mOuterLeftButton;
            this.mThumbsDownButton = baseButton2;
            this.mShuffleButton = null;
            this.mRepeatButton = null;
            this.ratingViewManager.setRatingButtons(baseButton, baseButton2, this.mLifecycleOwner, getContext());
        } else {
            ButtonStyle iconStyle = this.styleSheet.getIconStyle(IconStyleKey.ACCENT);
            if (isXSmallScreenPortrait(this.styleSheet)) {
                styleBuilder = this.tinyIconBuilder;
            } else {
                styleBuilder = this.smallIconBuilder;
            }
            BaseButton baseButton3 = this.mOuterRightButton;
            this.mShuffleButton = baseButton3;
            if (baseButton3 != null) {
                baseButton3.setOnClickListener(this.mPlayerShuffleButtonClickListener);
                this.mShuffleButton.setId(R.id.PersistentPlayerShuffleButton);
                this.mShuffleButton.setContentDescription(this.mContext.getResources().getString(R.string.dmusic_player_turn_on_shuffle_button_description));
                styleBuilder.withIcon(getContext().getDrawable(R.drawable.ic_playback_shuffle)).withActiveStyle(iconStyle).applyStyle(this.mShuffleButton);
            }
            BaseButton baseButton4 = this.mOuterLeftButton;
            this.mRepeatButton = baseButton4;
            if (baseButton4 != null) {
                baseButton4.setOnClickListener(this.mPlayerRepeatButtonClickListener);
                this.mRepeatButton.setId(R.id.PersistentPlayerRepeatButton);
                this.mRepeatButton.setContentDescription(this.mContext.getResources().getString(R.string.dmusic_player_repeat_all_songs_button_description));
                styleBuilder.withIcon(getContext().getDrawable(R.drawable.ic_playback_repeat)).withActiveStyle(iconStyle).applyStyle(this.mRepeatButton);
            }
            this.mThumbsUpButton = null;
            this.mThumbsDownButton = null;
            this.ratingViewManager.setRatingButtons(null, null, this.mLifecycleOwner, getContext());
        }
        MediaItemHelper.getAbstractItemObservable(currentMediaItem).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$initContentTypeDependentControls$4(currentMediaItem, (AbstractItem) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$initContentTypeDependentControls$5((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initContentTypeDependentControls$4(MediaItem mediaItem, AbstractItem abstractItem) throws Throwable {
        updatePrevButton(abstractItem, mediaItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initContentTypeDependentControls$5(Throwable th) throws Throwable {
        th.printStackTrace();
        Log.error(this.TAG, "Failed updating previous button", th);
    }

    private void resizePersistentPlayer(final ConstraintLayout parentView, final StyleSheet styleSheet) {
        int iDpToPixels;
        if (isMediumOrLargerScreenPortrait(styleSheet) || isTabletLandscape(styleSheet)) {
            iDpToPixels = SpacerParser.INSTANCE.dpToPixels(getContext().getResources(), 336);
        } else {
            iDpToPixels = SpacerParser.INSTANCE.dpToPixels(getContext().getResources(), 256);
        }
        this.mPersistentPlayer.measure(0, 0);
        int measuredWidth = this.mPersistentPlayer.getMeasuredWidth();
        if (ScreenUtil.isPortrait()) {
            this.mPersistentPlayer.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -2));
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(parentView);
            constraintSet.connect(R.id.PersistentPlayerContainer, 1, 0, 1);
            constraintSet.connect(R.id.PersistentPlayerContainer, 2, 0, 2);
            constraintSet.connect(R.id.PersistentPlayerContainer, 3, 0, 3);
            constraintSet.connect(R.id.PersistentPlayerContainer, 4, R.id.bottom_guideline, 3);
            constraintSet.applyTo(parentView);
        }
        if ((!ScreenUtil.isPortrait() || isMediumOrLargerScreenPortrait(styleSheet)) && measuredWidth > iDpToPixels) {
            this.mPersistentPlayer.setLayoutParams(new ConstraintLayout.LayoutParams(iDpToPixels, -2));
            ConstraintSet constraintSet2 = new ConstraintSet();
            constraintSet2.clone(parentView);
            constraintSet2.connect(R.id.PersistentPlayerContainer, 1, 0, 1);
            constraintSet2.connect(R.id.PersistentPlayerContainer, 2, 0, 2);
            constraintSet2.connect(R.id.PersistentPlayerContainer, 3, 0, 3);
            constraintSet2.connect(R.id.PersistentPlayerContainer, 4, R.id.bottom_guideline, 3);
            constraintSet2.applyTo(parentView);
        }
    }

    public void addXrayIngressButton(final IngressButtonView ingressButtonView) {
        this.xrayIngressButtonContainer.addView(ingressButtonView);
        this.xrayIngressButtonContainer.setVisibility(0);
    }

    public LinearLayout getXrayIngressContainer() {
        return this.xrayIngressButtonContainer;
    }

    @Override // com.amazon.mp3.playback.view.PersistentPlayer
    protected void updateViewEnabledState() {
        super.updateViewEnabledState();
        if (!this.mPlayerContentType.equals(NowPlayingViewConfig.PlayerContentType.fromMediaItem(this.mPlaybackController.getController().getCurrentMediaItem())) || this.ratingViewManager.shouldUpdateButtonInit(this.enabledStateManager)) {
            initContentTypeDependentControls();
        }
        if (MediaItemExtensions.isVideo(this.mPlaybackController.getCurrentMediaItem())) {
            showVideoControls();
            return;
        }
        if (MediaItemHelper.isInterludeMediaItem(this.mPlaybackController.getCurrentMediaItem())) {
            showInterludeControls();
            return;
        }
        setEnabledState(this.mRepeatButton, PlayerViewAction.REPEAT, true);
        setEnabledState(this.mShuffleButton, PlayerViewAction.SHUFFLE, true);
        setEnabledState(this.mPlayQueueButton, PlayerViewAction.PLAY_QUEUE, true);
        setEnabledState(this.mAlexaButton, PlayerViewAction.ALEXA);
        this.ratingViewManager.updateEnabledState(this.enabledStateManager);
    }

    @Override // com.amazon.mp3.playback.view.PersistentPlayer
    protected void updateButtonState() {
        super.updateButtonState();
        updateRepeatAndShuffleState();
        if (this.mThumbsUpButton != null) {
            this.ratingViewManager.updateThumbState();
        }
    }

    protected void updateRepeatAndShuffleState() {
        BaseButton baseButton = this.mRepeatButton;
        if (baseButton != null && baseButton.isEnabled() && this.mRepeatButton.getVisibility() == 0) {
            updateRepeatImage(this.mPlaybackController.getRepeatMode());
        }
        BaseButton baseButton2 = this.mShuffleButton;
        if (baseButton2 != null && baseButton2.isEnabled() && this.mShuffleButton.getVisibility() == 0) {
            updateShuffleImage(this.mPlaybackController.isShuffled());
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$RepeatMode;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            $SwitchMap$com$amazon$music$media$playback$RepeatMode = iArr;
            try {
                iArr[RepeatMode.REPEAT_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected void onRepeatButtonClick() {
        this.mPlaybackController.toggleRepeatMode();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$RepeatMode[this.mPlaybackController.getRepeatMode().ordinal()];
        if (i == 1) {
            sendUiClickEvent(ActionType.REPEAT_ALL_ON);
        } else if (i == 2) {
            sendUiClickEvent(ActionType.REPEAT_ONE);
        } else {
            if (i != 3) {
                return;
            }
            sendUiClickEvent(ActionType.REPEAT_OFF);
        }
    }

    protected void updateRepeatImage(final RepeatMode repeatMode) {
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateRepeatImage$6(repeatMode);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateRepeatImage$6(RepeatMode repeatMode) {
        int i;
        if (this.mRepeatButton == null) {
            return;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$RepeatMode[repeatMode.ordinal()];
        if (i2 == 1) {
            this.mRepeatButton.setActiveIcon(getContext().getDrawable(R.drawable.ic_playback_repeat));
            this.mRepeatButton.setActive(true);
            i = R.string.dmusic_player_repeat_one_song_button_description;
        } else if (i2 == 2) {
            this.mRepeatButton.setActiveIcon(getContext().getDrawable(R.drawable.ic_playback_repeat_one));
            this.mRepeatButton.setActive(true);
            i = R.string.dmusic_player_repeat_off_button_description;
        } else if (i2 == 3) {
            this.mRepeatButton.setActive(false);
            i = R.string.dmusic_player_repeat_all_songs_button_description;
        } else {
            throw new IllegalArgumentException("Invalid repeat state: " + repeatMode);
        }
        this.mRepeatButton.setContentDescription(this.mContext.getString(i));
    }

    protected void onShuffleButtonClick() {
        ActionType actionType;
        this.mPlaybackController.setShuffled(!r0.isShuffled());
        if (CloudQueueSequencer.isCloudQueuePlaying()) {
            CloudQueueSequencer.shuffleAll(this.mPlaybackController.isShuffled());
        }
        if (this.mPlaybackController.isShuffled()) {
            actionType = ActionType.SHUFFLE_ON;
        } else {
            actionType = ActionType.SHUFFLE_OFF;
        }
        sendUiClickEvent(actionType);
    }

    protected void updateShuffleImage(final boolean shuffleOn) {
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.playback.view.NowPlayingPersistentPlayer$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateShuffleImage$7(shuffleOn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateShuffleImage$7(boolean z) {
        BaseButton baseButton = this.mShuffleButton;
        if (baseButton == null) {
            return;
        }
        baseButton.setActive(z);
        this.mShuffleButton.setContentDescription(this.mContext.getString(z ? R.string.dmusic_player_turn_off_shuffle_button_description : R.string.dmusic_player_turn_on_shuffle_button_description));
    }

    public void addThumbsListener(final TrackRatingListener trackRatingListener) {
        this.ratingViewManager.addThumbsListener(trackRatingListener);
    }

    public void removeThumbsListener(final TrackRatingListener trackRatingListener) {
        this.ratingViewManager.removeThumbsListener(trackRatingListener);
    }

    public void showVideoControls() {
        showShuffle(true);
        showShare(false);
        showPlayQueue(ScreenUtil.isPortrait());
        showAlexa(ScreenUtil.isPortrait() && this.enabledStateManager.isSupported(PlayerViewAction.ALEXA));
        showRepeat(true);
        showThumbsUp(false);
    }

    public void showInterludeControls() {
        showShuffle(false);
        showShare(false);
        EnabledViewState enabledViewState = this.enabledStateManager.getEnabledViewState(PlayerViewAction.PLAY_QUEUE);
        PlayQueueButton playQueueButton = this.mPlayQueueButton;
        if (playQueueButton != null && enabledViewState != EnabledViewState.ENABLED) {
            playQueueButton.setVisibility(8);
        } else {
            showPlayQueue(CloudQueueSequencer.isCloudQueuePlaying() && CastingUtil.isPlayQueueSupported());
        }
        showAlexa(this.enabledStateManager.isSupported(PlayerViewAction.ALEXA));
        showRepeat(false);
        showThumbsUp(false);
    }

    private void showShuffle(final boolean shouldShow) {
        BaseButton baseButton = this.mShuffleButton;
        if (baseButton == null) {
            return;
        }
        if (shouldShow) {
            baseButton.setVisibility(0);
        } else {
            baseButton.setVisibility(8);
        }
    }

    private void showPlayQueue(final boolean shouldShow) {
        PlayQueueButton playQueueButton = this.mPlayQueueButton;
        if (playQueueButton == null) {
            return;
        }
        playQueueButton.setEnabled(shouldShow);
        if (shouldShow) {
            this.mPlayQueueButton.setVisibility(0);
        }
    }

    private void showRepeat(final boolean shouldShow) {
        BaseButton baseButton = this.mRepeatButton;
        if (baseButton == null) {
            return;
        }
        if (shouldShow) {
            baseButton.setVisibility(0);
        } else {
            baseButton.setVisibility(8);
        }
    }

    private void showAlexa(final boolean shouldShow) {
        BaseButton baseButton = this.mAlexaButton;
        if (baseButton == null) {
            return;
        }
        if (shouldShow) {
            baseButton.setVisibility(0);
        } else {
            baseButton.setVisibility(8);
        }
    }

    private void showThumbsUp(final boolean shouldShow) {
        BaseButton baseButton = this.mThumbsUpButton;
        if (baseButton == null) {
            return;
        }
        if (shouldShow) {
            baseButton.setVisibility(0);
        } else {
            baseButton.setVisibility(8);
        }
    }
}
