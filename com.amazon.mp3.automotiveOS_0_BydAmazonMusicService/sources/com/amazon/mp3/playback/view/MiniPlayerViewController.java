package com.amazon.mp3.playback.view;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.view.ContextMenu;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueUIMetricsManager;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.playback.PlayerContextMenu;
import com.amazon.mp3.playback.PlayerDispatcher;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.PlayerVerticalSwipeListener;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.playback.playbackcontrol.ActionResult;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.DynamicUpsellTranslator;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnLibraryChangedListener;
import com.amazon.music.media.playback.OnPlaybackErrorListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Arrays;
import java.util.List;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes3.dex */
public class MiniPlayerViewController implements OnPlayerClickListener, PlayerNavigationListener, OnPlaybackErrorListener, PlayerVerticalSwipeListener {
    private static final String TAG = "MiniPlayerViewController";
    private static BehaviorSubject<Boolean> sOnNextClickedSubject = BehaviorSubject.create();
    private FragmentActivity mActivity;
    private final CastingConnectionCallback mCastingConnectionCallback;
    private CastingSessionManager mCastingSessionManager;
    private boolean mDragged;
    private final EligibilityUtil mEligibilityUtil;
    private final BroadcastReceiver mFinishBroadcastReceiver;
    private LibraryAccessProviderImpl mLibraryAccessProvider;
    protected MiniPLayerSource mMiniPLayerSource;
    private final OnLibraryChangedListener mOnLibraryChangedListener;
    private OnPlayerVisibilityChangedListener mOnPlayerVisibilityChangedListener;
    protected final ActionValidatedPlaybackController mPlaybackController;
    private List<PlayerViewModel.PlayerContentViewModel> mPlayerContentViewModels;
    private PlayerContextMenu mPlayerContextMenu;
    private PlayerDispatcher mPlayerDispatcher;
    protected PlayerView mPlayerView;
    protected PlayerViewModel mPlayerViewModel;
    private final BroadcastReceiver mShowMiniPlayerReceiver;

    public enum MiniPLayerSource {
        LYRICS,
        XRAY,
        PLAY_QUEUE,
        PLAYER_MINIMIZED,
        CAR_MODE,
        NONE
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onForwardClicked() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onRewindClicked() {
    }

    public void onSwipeDown() {
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showPodcastEpisodeList() {
    }

    protected boolean skipObserverUpdate() {
        return false;
    }

    public Observable<Boolean> getOnNextClickedSubject() {
        return sOnNextClickedSubject;
    }

    public Observer<List<PlayerViewModel.PlayerContentViewModel>> getPlayerContentViewModelsObserver() {
        return new Observer<List<PlayerViewModel.PlayerContentViewModel>>() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<PlayerViewModel.PlayerContentViewModel> playerContentViewModels) {
                if (playerContentViewModels != null) {
                    PlayerViewModel.PlayerContentViewModel playerContentViewModel = playerContentViewModels.get(0);
                    PlayerViewModel.PlayerContentViewModel playerContentViewModel2 = playerContentViewModels.get(1);
                    PlayerViewModel.PlayerContentViewModel playerContentViewModel3 = playerContentViewModels.get(2);
                    if (MiniPlayerViewController.this.mPlayerContentViewModels != null && !MiniPlayerViewController.this.mDragged) {
                        PlayerViewModel.PlayerContentViewModel playerContentViewModel4 = (PlayerViewModel.PlayerContentViewModel) MiniPlayerViewController.this.mPlayerContentViewModels.get(0);
                        PlayerViewModel.PlayerContentViewModel playerContentViewModel5 = (PlayerViewModel.PlayerContentViewModel) MiniPlayerViewController.this.mPlayerContentViewModels.get(2);
                        if (playerContentViewModel2 != null) {
                            if (MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener != null) {
                                MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener.onVisibilityChanged(0);
                            }
                            if (playerContentViewModel2.equals(playerContentViewModel4)) {
                                MiniPlayerViewController.this.mPlayerView.animateToPrev();
                            } else if (playerContentViewModel2.equals(playerContentViewModel5)) {
                                MiniPlayerViewController.this.mPlayerView.animateToNext();
                            }
                        } else if (!MiniPlayerViewController.this.isActive()) {
                            if (MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener != null) {
                                MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener.onVisibilityChanged(8);
                            }
                        } else if (MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener != null) {
                            MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener.onVisibilityChanged(0);
                        }
                    }
                    MiniPlayerViewController.this.mDragged = false;
                    MiniPlayerViewController miniPlayerViewController = MiniPlayerViewController.this;
                    miniPlayerViewController.updateContentView(miniPlayerViewController.mPlayerView.getPrevContentView(), playerContentViewModel);
                    MiniPlayerViewController miniPlayerViewController2 = MiniPlayerViewController.this;
                    miniPlayerViewController2.updateContentView(miniPlayerViewController2.mPlayerView.getCurrentContentView(), playerContentViewModel2);
                    MiniPlayerViewController miniPlayerViewController3 = MiniPlayerViewController.this;
                    miniPlayerViewController3.updateContentView(miniPlayerViewController3.mPlayerView.getNextContentView(), playerContentViewModel3);
                    MiniPlayerViewController.this.mPlayerDispatcher.updateMediaItemArtwork();
                    MiniPlayerViewController.this.mPlayerContentViewModels = playerContentViewModels;
                    MiniPlayerViewController miniPlayerViewController4 = MiniPlayerViewController.this;
                    miniPlayerViewController4.mPlayerView.addedToLibrary(miniPlayerViewController4.isAddedToLibrary());
                }
            }
        };
    }

    public MiniPlayerViewController(final FragmentActivity activity, final PlayerView playerView, MiniPLayerSource triggerSource) {
        this(activity, playerView, activity, triggerSource);
    }

    public MiniPlayerViewController(final FragmentActivity activity, final PlayerView playerView, LifecycleOwner lifecycleOwner, final MiniPLayerSource triggerSource) {
        this.mEligibilityUtil = new EligibilityUtil();
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.mPlaybackController = controller;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(final Context context, final Intent intent) {
                if (MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener != null) {
                    MiniPlayerViewController.this.mOnPlayerVisibilityChangedListener.onVisibilityChanged(8);
                }
            }
        };
        this.mFinishBroadcastReceiver = broadcastReceiver;
        BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                MiniPlayerViewController miniPlayerViewController = MiniPlayerViewController.this;
                if (miniPlayerViewController.mPlayerView != null) {
                    miniPlayerViewController.refreshPlayer();
                }
            }
        };
        this.mShowMiniPlayerReceiver = broadcastReceiver2;
        CastingConnectionCallback castingConnectionCallback = new CastingConnectionCallback() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController.4
            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onError(final CastingException e) {
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onConnected(final CastingDevice castingDevice) {
                PlayerViewModel playerViewModel = MiniPlayerViewController.this.mPlayerViewModel;
                if (playerViewModel != null) {
                    playerViewModel.updateIsCasting(true);
                }
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onDisconnected(TerminationReason terminationReason) {
                PlayerViewModel playerViewModel = MiniPlayerViewController.this.mPlayerViewModel;
                if (playerViewModel != null) {
                    playerViewModel.updateIsCasting(false);
                }
            }
        };
        this.mCastingConnectionCallback = castingConnectionCallback;
        OnLibraryChangedListener onLibraryChangedListener = new OnLibraryChangedListener() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController.5
            @Override // com.amazon.music.media.playback.OnLibraryChangedListener
            public void OnLibraryChanged() {
                MiniPlayerViewController miniPlayerViewController = MiniPlayerViewController.this;
                PlayerView playerView2 = miniPlayerViewController.mPlayerView;
                if (playerView2 != null) {
                    playerView2.addedToLibrary(miniPlayerViewController.isAddedToLibrary());
                }
            }
        };
        this.mOnLibraryChangedListener = onLibraryChangedListener;
        this.mActivity = activity;
        this.mPlayerView = playerView;
        this.mPlayerViewModel = (PlayerViewModel) ViewModelProviders.of(activity).get(PlayerViewModel.class);
        PlayerDispatcher playerDispatcher = new PlayerDispatcher(this.mPlayerViewModel);
        this.mPlayerDispatcher = playerDispatcher;
        playerDispatcher.attachtoPlaybackListener();
        this.mPlayerContextMenu = new PlayerContextMenu(this.mActivity);
        controller.addOnPlaybackErrorListener(this);
        this.mPlayerView.addPlayerClickListener(this);
        this.mPlayerView.addPlayerNavigationListener(this);
        this.mPlayerView.addPlayerVerticalSwipeListener(this);
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        this.mCastingSessionManager = castingSessionManager;
        castingSessionManager.registerListener(castingConnectionCallback);
        LibraryAccessProviderImpl libraryAccessProviderImpl = LibraryAccessProviderImpl.getInstance(this.mActivity);
        this.mLibraryAccessProvider = libraryAccessProviderImpl;
        libraryAccessProviderImpl.addOnLibraryChangedListener(onLibraryChangedListener);
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.playback.view.miniplayer.hide");
        IntentFilter intentFilter2 = new IntentFilter("com.amazon.mp3.playback.view.miniplayer.show");
        intentFilter.addAction("com.amazon.mp3.library.activity.nowplaying.clear_and_finish");
        intentFilter2.addAction("com.amazon.mp3.library.activity.nowplaying.show");
        this.mActivity.registerReceiver(broadcastReceiver, intentFilter);
        this.mActivity.registerReceiver(broadcastReceiver2, intentFilter2);
        this.mMiniPLayerSource = triggerSource;
        init(lifecycleOwner);
    }

    public void init(LifecycleOwner lifecycleOwner) {
        PlayerViewModel playerViewModel = this.mPlayerViewModel;
        if (playerViewModel == null || this.mPlayerView == null || lifecycleOwner == null) {
            return;
        }
        playerViewModel.setHasNextObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$0((Boolean) obj);
            }
        });
        this.mPlayerViewModel.setHasPrevObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$1((Boolean) obj);
            }
        });
        this.mPlayerViewModel.setPlayerViewConfigObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$2((PlayerViewConfig) obj);
            }
        });
        this.mPlayerViewModel.setPlayerContentListObserver(lifecycleOwner, getPlayerContentViewModelsObserver());
        this.mPlayerViewModel.setRepeatModeObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$3((RepeatMode) obj);
            }
        });
        this.mPlayerViewModel.setShuffleObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$4((Boolean) obj);
            }
        });
        this.mPlayerViewModel.setIsCastingObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$5((Boolean) obj);
            }
        });
        this.mPlayerViewModel.setProgressObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$6((Long) obj);
            }
        });
        this.mPlayerViewModel.setDurationObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$7((Long) obj);
            }
        });
        this.mPlayerViewModel.setRatingObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$8((Integer) obj);
            }
        });
        this.mPlayerViewModel.setPlayerStateObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda12
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$9((PlayerViewModel.PlayerState) obj);
            }
        });
        this.mPlayerViewModel.setCurrentBitmapObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$10((Bitmap) obj);
            }
        });
        this.mPlayerViewModel.setPrevBitmapObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$11((Bitmap) obj);
            }
        });
        this.mPlayerViewModel.setNextBitmapObserver(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewController$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$12((Bitmap) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(Boolean bool) {
        if (skipObserverUpdate() || bool == null) {
            return;
        }
        this.mPlayerView.hasNextTrack(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1(Boolean bool) {
        if (skipObserverUpdate() || bool == null) {
            return;
        }
        this.mPlayerView.hasPrevTrack(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(PlayerViewConfig playerViewConfig) {
        if (skipObserverUpdate() || playerViewConfig == null) {
            return;
        }
        this.mPlayerView.updatePlaybackControl(playerViewConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$3(RepeatMode repeatMode) {
        if (repeatMode != null) {
            this.mPlayerView.repeatStateChanged(repeatMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$4(Boolean bool) {
        if (bool != null) {
            this.mPlayerView.shuffleStateChanged(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$5(Boolean bool) {
        if (bool != null) {
            this.mPlayerView.castingStateChanged(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$6(Long l) {
        if (skipObserverUpdate() || l == null) {
            return;
        }
        this.mPlayerView.updateProgress(l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$7(Long l) {
        if (skipObserverUpdate() || l == null) {
            return;
        }
        this.mPlayerView.updateDuration(l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$8(Integer num) {
        if (num != null) {
            this.mPlayerView.ratingChanged(num.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$9(PlayerViewModel.PlayerState playerState) {
        if (skipObserverUpdate() || playerState == null) {
            return;
        }
        this.mPlayerView.setPlayerState(playerState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$10(Bitmap bitmap) {
        if (skipObserverUpdate() || bitmap == null) {
            return;
        }
        this.mPlayerView.setCurrentBitmap(bitmap);
        trySendAdUIContentViewEvent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$11(Bitmap bitmap) {
        if (bitmap != null) {
            this.mPlayerView.setPrevBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$12(Bitmap bitmap) {
        if (bitmap != null) {
            this.mPlayerView.setNextBitmap(bitmap);
        }
    }

    private void trySendAdUIContentViewEvent() {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem instanceof AdMediaItem) {
            CloudQueueUIMetricsManager.get().sendMiniPlayerAdUIContentViewEvent((AdMediaItem) currentMediaItem);
        }
    }

    public void setOnPlayerVisibilityChangedListener(final OnPlayerVisibilityChangedListener listener) {
        this.mOnPlayerVisibilityChangedListener = listener;
    }

    public void refreshPlayer() {
        boolean zIsActive = isActive();
        OnPlayerVisibilityChangedListener onPlayerVisibilityChangedListener = this.mOnPlayerVisibilityChangedListener;
        if (onPlayerVisibilityChangedListener != null) {
            onPlayerVisibilityChangedListener.onVisibilityChanged(zIsActive ? 0 : 8);
        }
        if (zIsActive) {
            this.mPlayerDispatcher.refresh();
            this.mPlayerViewModel.updateIsCasting(this.mCastingSessionManager.isConnected());
        }
    }

    public boolean isActive() {
        return (this.mPlaybackController.getCurrentMediaItem() == null && this.mPlaybackController.getMediaCollectionInfo() == null && this.mPlaybackController.getPlayStatus() == PlayStatus.USER_PAUSED) ? false : true;
    }

    public void clear() {
        this.mOnPlayerVisibilityChangedListener = null;
        this.mActivity.unregisterReceiver(this.mFinishBroadcastReceiver);
        this.mActivity.unregisterReceiver(this.mShowMiniPlayerReceiver);
        this.mPlayerDispatcher.detachPlaybackListener();
        this.mPlaybackController.removeOnPlaybackErrorListener(this);
        this.mLibraryAccessProvider.removeOnLibraryChangedListener(this.mOnLibraryChangedListener);
        this.mCastingSessionManager.unregisterListener(this.mCastingConnectionCallback);
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onPlayPauseClicked() {
        sendUiClickMetric(getPageType(), (this.mPlaybackController.getPlayStatus().willPlay() ^ true ? ActionType.PLAY : ActionType.PAUSE).getMetricsValue(), InteractionType.TAP);
        this.mPlaybackController.togglePlaying();
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean canRestart() {
        return this.mPlayerViewModel.getCanRestartValue();
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean onPrevClicked(InteractionType type) {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (this.mPlaybackController.canSkipPrevious() || this.mPlaybackController.canRestartItem()) {
            if (type == InteractionType.DRAG) {
                this.mDragged = true;
            }
            sendUiClickMetric(getPageType(), ActionType.SKIP_PREVIOUS.getMetricsValue(), type);
            this.mPlaybackController.previousOrRestart();
            return true;
        }
        if (currentMediaItem instanceof AdMediaItem) {
            displayBlockingUpsell(UpsellReason.AD_SKIP, currentMediaItem);
            return false;
        }
        DynamicUpsellTranslator.showBlockingCapabilityUpsell(this.mActivity, currentMediaItem, QueueEntityCapabilityTranslator.QueueEntityCapability.PREVIOUS);
        return false;
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean onNextClicked(final InteractionType type) {
        ActionResult actionResultSkipNextForResult = this.mPlaybackController.skipNextForResult();
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        sendUiClickMetric(getPageType(), ActionType.SKIP_NEXT.getMetricsValue(), type);
        int i = AnonymousClass6.$SwitchMap$com$amazon$mp3$playback$playbackcontrol$ActionResult[actionResultSkipNextForResult.ordinal()];
        if (i == 1) {
            if (type == InteractionType.DRAG) {
                this.mDragged = true;
            }
            sOnNextClickedSubject.onNext(Boolean.TRUE);
            return true;
        }
        if (i == 2) {
            displayBlockingUpsell(UpsellReason.TRACK_SKIP_LIMIT, currentMediaItem);
            return true;
        }
        if (i != 3) {
            return false;
        }
        if (currentMediaItem instanceof AdMediaItem) {
            displayBlockingUpsell(UpsellReason.AD_SKIP, currentMediaItem);
            return false;
        }
        if (getCapabilityUpsellReason() == null) {
            return false;
        }
        displayBlockingUpsell(UpsellReason.TRACK_NEXT, currentMediaItem);
        return false;
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onRepeatClicked() {
        if (UserSubscriptionUtil.isNightwingOnly()) {
            UpsellUtil.raiseBlockingUpsell(this.mActivity, UpsellUtil.getAsinFromMediaItem(this.mPlaybackController.getCurrentMediaItem()), UpsellReason.TRACK_REPEAT);
            return;
        }
        RepeatMode nextRepeatMode = RepeatMode.getNextRepeatMode(this.mPlaybackController.getRepeatMode(), this.mPlaybackController.getAvailableRepeatModes());
        PageType currentPageType = getPageType();
        int i = AnonymousClass6.$SwitchMap$com$amazon$music$media$playback$RepeatMode[nextRepeatMode.ordinal()];
        if (i == 1) {
            sendUiClickMetric(currentPageType, ActionType.REPEAT_ONE.getMetricsValue(), InteractionType.TAP);
        } else if (i == 2) {
            sendUiClickMetric(currentPageType, ActionType.REPEAT_ALL_ON.getMetricsValue(), InteractionType.TAP);
        } else if (i == 3) {
            sendUiClickMetric(currentPageType, ActionType.REPEAT_OFF.getMetricsValue(), InteractionType.TAP);
        }
        this.mPlaybackController.toggleRepeatMode();
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onShuffleClicked() {
        if (UserSubscriptionUtil.isNightwingOnly()) {
            UpsellUtil.raiseBlockingUpsell(this.mActivity, UpsellUtil.getAsinFromMediaItem(this.mPlaybackController.getCurrentMediaItem()), UpsellReason.TRACK_SHUFFLE_PLAY);
        } else if (this.mPlaybackController.canShuffle()) {
            sendUiClickMetric(getPageType(), (this.mPlaybackController.isShuffled() ? ActionType.SHUFFLE_OFF : ActionType.SHUFFLE_ON).getMetricsValue(), InteractionType.TAP);
            this.mPlaybackController.setShuffled(!r0.isShuffled());
        }
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onThumbsUpClicked() {
        if (this.mPlaybackController.canRateCurrentItem()) {
            this.mPlaybackController.toggleThumbsUp();
        }
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onThumbsDownClicked() {
        if (this.mPlaybackController.canRateCurrentItem()) {
            this.mPlaybackController.toggleThumbsDown();
        }
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onPlayerClicked() {
        showNowPlaying(InteractionType.TAP);
    }

    @Override // com.amazon.mp3.playback.PlayerVerticalSwipeListener
    public void onSwipeUp() {
        showNowPlaying(InteractionType.SWIPE);
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showNowPlaying(InteractionType type) {
        try {
            PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
            if (playbackConfig != null) {
                playbackConfig.getClickAction().send();
            }
            if (type == null) {
                type = InteractionType.TAP;
            }
            sendUiClickMetric(getPageType(), ActionType.GO_NOW_PLAYING.getMetricsValue(), type);
        } catch (PendingIntent.CanceledException e) {
            Log.warning(TAG, "could not restore Now Playing screen", e);
        }
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showPlayQueue() {
        Intent nowPlayingQueueIntent = NowPlayingQueueUtil.getNowPlayingQueueIntent(this.mActivity);
        if (nowPlayingQueueIntent != null) {
            this.mActivity.startActivity(nowPlayingQueueIntent);
            PlayerContextMenu.sendMetricEvent(ActionType.GO_PLAY_QUEUE, InteractionType.TAP, this.mPlaybackController.getCurrentMediaItem().getMediaItemId(MediaItemId.Type.ASIN).getId(), EntityIdType.PLAYLIST_ID);
        }
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void addTrackToLibrary() {
        PlayerContextMenu.sendMetricEvent(ActionType.ADD_TRACK_TO_LIBRARY, InteractionType.TAP, this.mPlaybackController.getCurrentMediaItem().getMediaItemId(MediaItemId.Type.ASIN).getId(), EntityIdType.ASIN);
        if (UserSubscriptionUtil.isNightwingOnly()) {
            UpsellUtil.raiseBlockingUpsell(this.mActivity, UpsellUtil.getAsinFromMediaItem(this.mPlaybackController.getCurrentMediaItem()), UpsellReason.ADD_TO_LIBRARY);
        } else {
            this.mLibraryAccessProvider.addToLibrary(this.mPlaybackController.getCurrentMediaItem());
        }
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void onCreateContextMenu(final ContextMenu menu) {
        this.mPlayerContextMenu.onCreateOptionMenu(menu, this.mActivity.getMenuInflater());
        this.mPlayerContextMenu.onUpdateOptionMenu(menu);
        new BauhausTextStyler().styleMenu(menu);
    }

    public boolean onContextItemSelected(final MenuItem item) {
        return this.mPlayerContextMenu.onOptionsItemSelected(item);
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showCastingView() {
        Intent startIntent = CastingFragment.getStartIntent(this.mActivity);
        CastingUIMetricsLogger.recordCastingButtonClickMetric(CastingUIMetricsLogger.ContentName.MINI_PLAYER, getPageType());
        this.mActivity.startActivity(startIntent);
    }

    private void displayBlockingUpsell(final UpsellReason upsellReason, final MediaItem mediaItem) {
        UpsellUtil.showBlockingUpsell(this.mActivity, UpsellUtil.getAsinFromMediaItem(mediaItem), upsellReason, UpsellSourceEntity.TRACK);
    }

    private com.amazon.music.platform.playback.capabilities.UpsellReason getCapabilityUpsellReason() {
        return this.mEligibilityUtil.getUpsell(MediaItemHelper.getCurrentUri(), this.mActivity.getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAddedToLibrary() {
        LibraryAccessProviderImpl libraryAccessProviderImpl = this.mLibraryAccessProvider;
        if (libraryAccessProviderImpl != null) {
            return libraryAccessProviderImpl.isInLibrary(this.mPlaybackController.getCurrentMediaItem());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentView(PlayerContentView miniPlayerContentView, PlayerViewModel.PlayerContentViewModel playerContentViewModel) {
        if (playerContentViewModel != null) {
            miniPlayerContentView.setLoading(false);
            miniPlayerContentView.setTitle(playerContentViewModel.getTitle());
            miniPlayerContentView.setSubtitle(getNewSubtitle(playerContentViewModel.getSubtitle()));
        } else {
            miniPlayerContentView.setLoading(true);
            miniPlayerContentView.setDefaultArtwork();
        }
    }

    private String createNewSubtitle(final String subtitle) {
        List listAsList = Arrays.asList(subtitle.split(" - "));
        if (listAsList.size() < 2) {
            return subtitle;
        }
        String str = (String) listAsList.get(0);
        String str2 = (String) listAsList.get(1);
        String string = this.mActivity.getString(R.string.dmusic_now_playing_based_on);
        if (str2.contains(StringUtil.cleanFormatString(string))) {
            return str + " - " + str2;
        }
        return str + " - " + String.format(string, str2);
    }

    private String getNewSubtitle(final String subtitle) {
        if ((!NowPlayingUtil.isSFAStationPlaying() && !NowPlayingUtil.isAMFStationPlaying()) || NowPlayingUtil.isUserPlaylistPlaying() || NowPlayingUtil.isGoldenPlaylistPlaying()) {
            return NowPlayingUtil.isPrimeOrAMUStationPlaying() ? createNewSubtitle(subtitle) : subtitle;
        }
        return createNewSubtitle(subtitle);
    }

    protected void sendUiClickMetric(final PageType pageType, final String actionType, final InteractionType type) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(pageType).withExperienceMode(getCurrentExperienceMode().getMetricsValue()).withInteractionType(type).withContentInfo(MetricsLogger.getContentInfo(CastingUIMetricsLogger.ContentName.MINI_PLAYER.getMetricName())).build());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.MiniPlayerViewController$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$playbackcontrol$ActionResult;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$view$MiniPlayerViewController$MiniPLayerSource;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$RepeatMode;

        static {
            int[] iArr = new int[MiniPLayerSource.values().length];
            $SwitchMap$com$amazon$mp3$playback$view$MiniPlayerViewController$MiniPLayerSource = iArr;
            try {
                iArr[MiniPLayerSource.LYRICS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$MiniPlayerViewController$MiniPLayerSource[MiniPLayerSource.XRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$MiniPlayerViewController$MiniPLayerSource[MiniPLayerSource.PLAY_QUEUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$MiniPlayerViewController$MiniPLayerSource[MiniPLayerSource.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[RepeatMode.values().length];
            $SwitchMap$com$amazon$music$media$playback$RepeatMode = iArr2;
            try {
                iArr2[RepeatMode.REPEAT_ONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[ActionResult.values().length];
            $SwitchMap$com$amazon$mp3$playback$playbackcontrol$ActionResult = iArr3;
            try {
                iArr3[ActionResult.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$playbackcontrol$ActionResult[ActionResult.REJECTED_SHOULD_UPSELL.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$playbackcontrol$ActionResult[ActionResult.REJECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: renamed from: getCurrentPageType */
    protected PageType getPageType() {
        int i = AnonymousClass6.$SwitchMap$com$amazon$mp3$playback$view$MiniPlayerViewController$MiniPLayerSource[this.mMiniPLayerSource.ordinal()];
        if (i == 1) {
            return PageType.LYRICS;
        }
        if (i == 2) {
            return PageType.XRAY;
        }
        if (i == 3) {
            return PageType.PLAY_QUEUE;
        }
        if (i != 4) {
            return null;
        }
        return PageType.MINI_TRANSPORT;
    }

    protected ExperienceMode getCurrentExperienceMode() {
        return ExperienceMode.MOBILE;
    }

    @Override // com.amazon.music.media.playback.OnPlaybackErrorListener
    public void onPlaybackError(final PlaybackException e) {
        Log.info(TAG, "receieved Playback error, clearing the mini player", e);
        OnPlayerVisibilityChangedListener onPlayerVisibilityChangedListener = this.mOnPlayerVisibilityChangedListener;
        if (onPlayerVisibilityChangedListener != null) {
            onPlayerVisibilityChangedListener.onVisibilityChanged(8);
        }
    }
}
