package com.amazon.mp3.fragment.contextmenu;

import android.app.Application;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlayerModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.RedownloadStateResolver;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlayerOverflowMenuMoreButtonsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#J\u0018\u0010!\u001a\u00020 2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u001dH\u0002J\u0006\u0010&\u001a\u00020 J\u0010\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)H\u0002J\u001a\u0010*\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0006H\u0002J6\u0010+\u001a\u00020 2\b\u0010,\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0006H\u0002J\u000e\u00102\u001a\u00020 2\u0006\u0010\"\u001a\u00020#J\u0006\u00103\u001a\u00020 J\u0006\u00104\u001a\u00020 J\u0010\u00105\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\fR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\f¨\u00066"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreButtonsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "TAG", "", "kotlin.jvm.PlatformType", "dislikeStateObservable", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/amazon/music/media/playback/ratings/LikeState;", "getDislikeStateObservable", "()Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "downloadListener", "com/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreButtonsViewModel$downloadListener$1", "Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreButtonsViewModel$downloadListener$1;", "downloadStateObservable", "Lcom/amazon/music/downloads/DownloadState;", "getDownloadStateObservable", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "playbackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "repeatModeObservable", "Lcom/amazon/music/media/playback/RepeatMode;", "getRepeatModeObservable", "shuffleObservable", "", "getShuffleObservable", "dislikeTrack", "", "downloadTrack", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "asin", "shouldRedownload", "initiaizeOverflowState", "isFreeTierNonPurchasedItem", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "raiseUpsellDialog", "sendUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "pageSubType", "stationFromAnything", "toggleRepeatMode", "toggleShuffle", "updateTrackDownloadState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerOverflowMenuMoreButtonsViewModel extends AndroidViewModel {
    private final String TAG;
    private final BehaviorSubject<LikeState> dislikeStateObservable;
    private final PlayerOverflowMenuMoreButtonsViewModel$downloadListener$1 downloadListener;
    private final BehaviorSubject<DownloadState> downloadStateObservable;
    private final PlaybackController playbackController;
    private final OnPlaybackEventListener playbackEventListener;
    private final BehaviorSubject<RepeatMode> repeatModeObservable;
    private final BehaviorSubject<Boolean> shuffleObservable;

    /* JADX INFO: compiled from: PlayerOverflowMenuMoreButtonsViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            iArr[RepeatMode.REPEAT_NONE.ordinal()] = 1;
            iArr[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            iArr[RepeatMode.REPEAT_ALL.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsViewModel$downloadListener$1] */
    public PlayerOverflowMenuMoreButtonsViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.TAG = PlayerOverflowMenuMoreButtonsViewModel.class.getSimpleName();
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        BehaviorSubject<RepeatMode> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(controller.getRepeatMode());
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(playbackController.repeatMode)");
        this.repeatModeObservable = behaviorSubjectCreateDefault;
        BehaviorSubject<Boolean> behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault(Boolean.valueOf(controller.isShuffled()));
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault2, "createDefault(playbackController.isShuffled)");
        this.shuffleObservable = behaviorSubjectCreateDefault2;
        BehaviorSubject<DownloadState> behaviorSubjectCreateDefault3 = BehaviorSubject.createDefault(DownloadState.UNKNOWN);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault3, "createDefault(DownloadState.UNKNOWN)");
        this.downloadStateObservable = behaviorSubjectCreateDefault3;
        MediaItem currentMediaItem = controller.getCurrentMediaItem();
        LikeState likeState = currentMediaItem == null ? null : currentMediaItem.getLikeState();
        BehaviorSubject<LikeState> behaviorSubjectCreateDefault4 = BehaviorSubject.createDefault(likeState == null ? LikeState.NEUTRAL : likeState);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault4, "createDefault(playbackCo…ate ?: LikeState.NEUTRAL)");
        this.dislikeStateObservable = behaviorSubjectCreateDefault4;
        this.playbackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsViewModel$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                PlayerOverflowMenuMoreButtonsViewModel.m1136playbackEventListener$lambda0(this.f$0, i, controlSource);
            }
        };
        final HashSet hashSet = new HashSet();
        this.downloadListener = new DownloadListener(hashSet) { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsViewModel$downloadListener$1
            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onProgressUpdate(String id, Group group, float percentageDownloaded) {
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(group, "group");
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onProgressUpdate(String id, Item item, float percentageDownloaded) {
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(item, "item");
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onStatusUpdate(DownloadState downloadState, String id, Group group) {
                Intrinsics.checkNotNullParameter(downloadState, "downloadState");
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(group, "group");
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onStatusUpdate(DownloadState downloadState, String id, Item item) {
                DownloadState downloadState2;
                Intrinsics.checkNotNullParameter(downloadState, "downloadState");
                Intrinsics.checkNotNullParameter(id, "id");
                MediaItem currentMediaItem2 = this.this$0.getPlaybackController().getCurrentMediaItem();
                if (item == null || MediaItemHelper.getAsin(currentMediaItem2) == null || !Intrinsics.areEqual(MediaItemHelper.getAsin(currentMediaItem2), item.getAsin()) || downloadState != (downloadState2 = DownloadState.DOWNLOADED)) {
                    return;
                }
                this.this$0.getDownloadStateObservable().onNext(downloadState2);
            }
        };
    }

    public final PlaybackController getPlaybackController() {
        return this.playbackController;
    }

    public final BehaviorSubject<RepeatMode> getRepeatModeObservable() {
        return this.repeatModeObservable;
    }

    public final BehaviorSubject<Boolean> getShuffleObservable() {
        return this.shuffleObservable;
    }

    public final BehaviorSubject<DownloadState> getDownloadStateObservable() {
        return this.downloadStateObservable;
    }

    public final BehaviorSubject<LikeState> getDislikeStateObservable() {
        return this.dislikeStateObservable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playbackEventListener$lambda-0, reason: not valid java name */
    public static final void m1136playbackEventListener$lambda0(PlayerOverflowMenuMoreButtonsViewModel this$0, int i, ControlSource noName_1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (i != 1 && i != 2 && i != 3) {
            switch (i) {
                case 24:
                    this$0.shuffleObservable.onNext(Boolean.TRUE);
                    break;
                case 25:
                    this$0.shuffleObservable.onNext(Boolean.FALSE);
                    break;
                case 26:
                    this$0.repeatModeObservable.onNext(this$0.playbackController.getRepeatMode());
                    break;
            }
        }
        BehaviorSubject<LikeState> behaviorSubject = this$0.dislikeStateObservable;
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        behaviorSubject.onNext(currentMediaItem == null ? null : currentMediaItem.getLikeState());
        MediaItem currentMediaItem2 = this$0.playbackController.getCurrentMediaItem();
        Intrinsics.checkNotNullExpressionValue(currentMediaItem2, "playbackController.currentMediaItem");
        this$0.updateTrackDownloadState(currentMediaItem2);
    }

    public final void initiaizeOverflowState() {
        this.playbackController.addOnPlaybackEventListener(this.playbackEventListener);
        if (this.playbackController.getCurrentMediaItem() != null) {
            MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
            Intrinsics.checkNotNullExpressionValue(currentMediaItem, "playbackController.currentMediaItem");
            updateTrackDownloadState(currentMediaItem);
        }
    }

    public final void toggleRepeatMode() {
        this.playbackController.toggleRepeatMode();
        this.repeatModeObservable.onNext(this.playbackController.getRepeatMode());
        RepeatMode repeatMode = this.playbackController.getRepeatMode();
        int i = repeatMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[repeatMode.ordinal()];
        if (i == 1) {
            sendUiClickEvent$default(this, ActionType.REPEAT_OFF, null, null, null, 14, null);
        } else if (i == 2) {
            sendUiClickEvent$default(this, ActionType.REPEAT_ONE, null, null, null, 14, null);
        } else {
            if (i != 3) {
                return;
            }
            sendUiClickEvent$default(this, ActionType.REPEAT_ALL_ON, null, null, null, 14, null);
        }
    }

    public final void toggleShuffle() {
        this.playbackController.setShuffled(!r0.isShuffled());
        this.shuffleObservable.onNext(Boolean.valueOf(this.playbackController.isShuffled()));
        CloudQueueSequencer.Companion companion = CloudQueueSequencer.INSTANCE;
        if (companion.isCloudQueuePlaying()) {
            companion.shuffleAll(this.playbackController.isShuffled());
        }
        sendUiClickEvent$default(this, this.playbackController.isShuffled() ? ActionType.SHUFFLE_ON : ActionType.SHUFFLE_OFF, null, null, null, 14, null);
    }

    public final void downloadTrack(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        MediaItem mediaItem = this.playbackController.getCurrentMediaItem();
        String asin = MediaItemHelper.getAsin(mediaItem);
        ActionType actionType = ActionType.DOWNLOAD_OVERFLOW;
        if (mediaItem.getMediaAccessInfo() != null && mediaItem.getMediaAccessInfo().isAvailableOffline()) {
            actionType = ActionType.REDOWNLOAD_OVERFLOW;
        }
        sendUiClickEvent$default(this, actionType, asin, EntityIdType.ASIN, null, 8, null);
        AbstractItem abstractItem = MediaItemHelper.getAbstractItem(mediaItem, true, null);
        Intrinsics.checkNotNullExpressionValue(mediaItem, "mediaItem");
        if (isFreeTierNonPurchasedItem(mediaItem) || (abstractItem != null && new PlayerModelConfiguration().shouldShowContextMenuDownloadUpsellButton(abstractItem))) {
            raiseUpsellDialog(activity, asin);
            return;
        }
        if (this.downloadStateObservable.getValue() != DownloadState.DOWNLOADED) {
            LibraryAccessProviderImpl.getInstance(getApplication().getApplicationContext()).addToLibrary(mediaItem);
            if (MediaItemHelper.canDownloadMediaItem(mediaItem)) {
                Intrinsics.checkNotNullExpressionValue(asin, "asin");
                downloadTrack(asin, mediaItem.getMediaAccessInfo().isAvailableOffline());
            }
        }
    }

    public final void stationFromAnything(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if ((currentMediaItem == null ? null : currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            String id = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN).getId();
            StationUtils.startSFA(activity, id, "TRACK_SEED", PlaybackPageType.PLAYLISTS_LIST, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(id, EntityIdType.SFA_TRACK_SEED, PageType.NOW_PLAYING_OVERFLOW);
        }
    }

    public final void dislikeTrack() {
        String str;
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || !AmazonApplication.getCapabilities().isNPSLikesSupported()) {
            return;
        }
        LikeState likeState = currentMediaItem.getLikeState();
        LikeState likeState2 = LikeState.DISLIKE;
        LikeState likeState3 = likeState == likeState2 ? LikeState.NEUTRAL : likeState2;
        boolean z = likeState3 == likeState2;
        ActionType actionType = z ? ActionType.THUMBS_DOWN_OVERFLOW : ActionType.THUMBS_DOWN_UNDO_OVERFLOW;
        if (this.playbackController.canRateMediaItems()) {
            str = null;
            try {
                currentMediaItem.setRating(z ? 2 : 0, this.playbackController.getCurrentIndex());
            } catch (Exception unused) {
                Log.debug(this.TAG, "Sequencer does not support indices.");
            }
        } else {
            currentMediaItem.setLikeState(likeState3);
            str = "onDemand";
        }
        if (z) {
            BauhausToastUtils.showTextToast(getApplication().getApplicationContext(), R.string.dmusic_dislike_action_toast, 0);
            if (this.playbackController.canSkipNext()) {
                this.playbackController.skipNext();
            }
        }
        sendUiClickEvent(actionType, MediaItemHelper.getAsin(currentMediaItem), EntityIdType.ASIN, str);
        this.dislikeStateObservable.onNext(likeState3);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsViewModel$updateTrackDownloadState$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerOverflowMenuMoreButtonsViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsViewModel$updateTrackDownloadState$1", f = "PlayerOverflowMenuMoreButtonsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MediaItem $mediaItem;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MediaItem mediaItem, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mediaItem = mediaItem;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerOverflowMenuMoreButtonsViewModel.this.new AnonymousClass1(this.$mediaItem, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DownloadState downloadStateUpdateLibrary = LibraryAccessProviderImpl.getInstance(PlayerOverflowMenuMoreButtonsViewModel.this.getApplication().getApplicationContext()).updateLibrary(this.$mediaItem);
            String asin = MediaItemHelper.getAsin(this.$mediaItem);
            DownloadState downloadState = DownloadState.DOWNLOADED;
            boolean z = downloadStateUpdateLibrary == downloadState && asin != null && MusicDownloader.getInstance(PlayerOverflowMenuMoreButtonsViewModel.this.getApplication().getApplicationContext()).isTrackAvailableOffline(asin, true);
            if ((!z || !new RedownloadStateResolver(AmazonApplication.getApplication().getApplicationContext()).shouldRedownloadTrack(MediaItemHelper.getAsin(this.$mediaItem), true, this.$mediaItem.getContentEncodings()) || !AmazonApplication.getCapabilities().isSpatialAudioEnabled(AmazonApplication.getApplication().getApplicationContext())) && z) {
                PlayerOverflowMenuMoreButtonsViewModel.this.getDownloadStateObservable().onNext(downloadState);
            } else {
                PlayerOverflowMenuMoreButtonsViewModel.this.getDownloadStateObservable().onNext(DownloadState.UNKNOWN);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateTrackDownloadState(MediaItem mediaItem) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(mediaItem, null), 2, null);
    }

    private final boolean isFreeTierNonPurchasedItem(MediaItem mediaItem) {
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        return UserSubscriptionUtil.isNightwingOnly() && !(mediaAccessInfo != null && mediaAccessInfo.isOwned());
    }

    private final void downloadTrack(String asin, boolean shouldRedownload) {
        Context applicationContext = getApplication().getApplicationContext();
        MusicDownloader.getInstance(applicationContext).registerDownloadListener(this.downloadListener);
        addRequestId(asin);
        new AddAndDownloadTrackTask(applicationContext, asin, true, true, shouldRedownload, (OnItemFinishedAddingListener) null).execute(new Void[0]);
        this.downloadStateObservable.onNext(DownloadState.QUEUED);
    }

    private final void raiseUpsellDialog(FragmentActivity activity, String asin) {
        UpsellUtil.showBlockingUpsell(activity, asin, UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.TRACK);
    }

    static /* synthetic */ void sendUiClickEvent$default(PlayerOverflowMenuMoreButtonsViewModel playerOverflowMenuMoreButtonsViewModel, ActionType actionType, String str, EntityIdType entityIdType, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            entityIdType = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        playerOverflowMenuMoreButtonsViewModel.sendUiClickEvent(actionType, str, entityIdType, str2);
    }

    private final void sendUiClickEvent(ActionType actionType, String entityId, EntityIdType entityIdType, String pageSubType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING_OVERFLOW).withEventTime(System.currentTimeMillis()).withEntityId(entityId).withEntityIdType(entityIdType).withPageSubType(pageSubType).build());
    }
}
