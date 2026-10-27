package com.amazon.mp3.playback.view.multiminiplayer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.models.PlayQueue;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.ActiveDevicePlayback;
import com.amazon.digitalmusicxp.types.DeviceContext;
import com.amazon.digitalmusicxp.types.DeviceIdentifier;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.QueueIdTransferSeed;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.view.OnPlayerClickListener;
import com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueMiniPlayerController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\u0003H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0016J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016J\u0012\u0010\u001c\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0005H\u0016J\u0012\u0010\u001f\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016R\u0014\u0010!\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u001c\u0010-\u001a\n ,*\u0004\u0018\u00010+0+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.¨\u00060"}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueMiniPlayerController;", "Lcom/amazon/mp3/playback/view/OnPlayerClickListener;", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "", "isCastable", "", "showConfirmDialog", "Lcom/amazon/music/ui/model/castingtile/CastingTileModel;", "model", "setupCasting", "checkAlwaysMoveStream", "onPlayPauseClicked", "onPlayerClicked", "onRewindClicked", "onForwardClicked", "canRestart", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "onPrevClicked", "onThumbsDownClicked", "onThumbsUpClicked", "onNextClicked", "onShuffleClicked", "onRepeatClicked", "showPlayQueue", "showPodcastEpisodeList", "Landroid/view/ContextMenu;", "menu", "onCreateContextMenu", "addTrackToLibrary", "showCastingView", "showNowPlaying", "", "DIALOG_NAME", "Ljava/lang/String;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "activeDevicePlayback", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "castingTileModel", "Lcom/amazon/music/ui/model/castingtile/CastingTileModel;", "Lcom/amazon/music/casting/session/CastingSessionManager;", "kotlin.jvm.PlatformType", "castingSessionManager", "Lcom/amazon/music/casting/session/CastingSessionManager;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CloudQueueMiniPlayerController implements OnPlayerClickListener, PlayerNavigationListener {
    private final String DIALOG_NAME;
    private ActiveDevicePlayback activeDevicePlayback;
    private FragmentActivity activity;
    private final CastingSessionManager castingSessionManager;
    private CastingTileModel castingTileModel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CloudQueueMiniPlayerController.class.getSimpleName();
    private static final String ALWAYS_MOVE_STREAM = "ALWAYS_MOVE_STREAM";

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void addTrackToLibrary() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean canRestart() {
        return false;
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void onCreateContextMenu(ContextMenu menu) {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onForwardClicked() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean onNextClicked(InteractionType type) {
        return false;
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean onPrevClicked(InteractionType type) {
        return false;
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onRepeatClicked() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onRewindClicked() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onShuffleClicked() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onThumbsDownClicked() {
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onThumbsUpClicked() {
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showCastingView() {
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showPlayQueue() {
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showPodcastEpisodeList() {
    }

    /* JADX INFO: compiled from: CloudQueueMiniPlayerController.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueMiniPlayerController$Companion;", "", "()V", "ALWAYS_MOVE_STREAM", "", "getALWAYS_MOVE_STREAM", "()Ljava/lang/String;", "TAG", "kotlin.jvm.PlatformType", "callInitiateQueue", "", "activeDevicePlayback", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "startNowPlayingScreen", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getALWAYS_MOVE_STREAM() {
            return CloudQueueMiniPlayerController.ALWAYS_MOVE_STREAM;
        }

        public final void callInitiateQueue(final ActiveDevicePlayback activeDevicePlayback, final FragmentActivity activity) {
            Intrinsics.checkNotNullParameter(activeDevicePlayback, "activeDevicePlayback");
            Intrinsics.checkNotNullParameter(activity, "activity");
            if (activity instanceof MusicHomeActivity) {
                ((MusicHomeActivity) activity).showLoadingView(true);
            } else if (activity instanceof NowPlayingFragmentActivity) {
                ((NowPlayingFragmentActivity) activity).showLoadingView(true);
            }
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get();
            final String customerId = AccountDetailUtil.get().getCustomerId();
            final String deviceId = accountCredentialStorage.getDeviceId();
            final String deviceType = accountCredentialStorage.getDeviceType();
            final QueueIdTransferSeed queueIdTransferSeed = new QueueIdTransferSeed(activeDevicePlayback.getQueueId(), new DeviceIdentifier(activeDevicePlayback.getDeviceId(), activeDevicePlayback.getDeviceType()), Boolean.TRUE);
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CloudQueueMiniPlayerController.Companion.m1545callInitiateQueue$lambda3(activeDevicePlayback, customerId, deviceId, deviceType, queueIdTransferSeed, activity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: callInitiateQueue$lambda-3, reason: not valid java name */
        public static final void m1545callInitiateQueue$lambda3(final ActiveDevicePlayback activeDevicePlayback, final String str, final String str2, final String str3, final QueueIdTransferSeed queueTransferSeed, final FragmentActivity activity) {
            Intrinsics.checkNotNullParameter(activeDevicePlayback, "$activeDevicePlayback");
            Intrinsics.checkNotNullParameter(queueTransferSeed, "$queueTransferSeed");
            Intrinsics.checkNotNullParameter(activity, "$activity");
            CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController$Companion$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueueMiniPlayerController.Companion.m1546callInitiateQueue$lambda3$lambda1(activeDevicePlayback, str, str2, str3, queueTransferSeed, activity, (CloudQueueAppClient) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController$Companion$$ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueueMiniPlayerController.Companion.m1548callInitiateQueue$lambda3$lambda2((Throwable) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: callInitiateQueue$lambda-3$lambda-1, reason: not valid java name */
        public static final void m1546callInitiateQueue$lambda3$lambda1(ActiveDevicePlayback activeDevicePlayback, String customerId, String targetDeviceId, String targetDeviceType, QueueIdTransferSeed queueTransferSeed, final FragmentActivity activity, CloudQueueAppClient cloudQueueAppClient) {
            Intrinsics.checkNotNullParameter(activeDevicePlayback, "$activeDevicePlayback");
            Intrinsics.checkNotNullParameter(queueTransferSeed, "$queueTransferSeed");
            Intrinsics.checkNotNullParameter(activity, "$activity");
            Intrinsics.checkNotNullExpressionValue(cloudQueueAppClient, "cloudQueueAppClient");
            String title = activeDevicePlayback.getTitle();
            if (title == null) {
                title = "";
            }
            Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
            Intrinsics.checkNotNullExpressionValue(targetDeviceId, "targetDeviceId");
            Intrinsics.checkNotNullExpressionValue(targetDeviceType, "targetDeviceType");
            Outcome outcomeStartCloudQueue$default = CloudQueueAppClient.DefaultImpls.startCloudQueue$default(cloudQueueAppClient, title, customerId, new DeviceContext(targetDeviceId, targetDeviceType), CollectionsKt__CollectionsJVMKt.listOf(new QueueSeed(null, QueueSeedTypeEnum.QUEUE_ID_TRANSFER, queueTransferSeed, null, null, null, null, null, null, null, null, null, null, 8185, null)), null, null, null, null, null, 480, null);
            activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController$Companion$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    CloudQueueMiniPlayerController.Companion.m1547callInitiateQueue$lambda3$lambda1$lambda0(activity);
                }
            });
            if (!(outcomeStartCloudQueue$default instanceof Outcome.Success)) {
                Log.error(CloudQueueMiniPlayerController.TAG, "amplify module fails to get the result to start playback");
                return;
            }
            CloudQueueMiniPlayerController.INSTANCE.startNowPlayingScreen(activity, activeDevicePlayback);
            PlayQueue playQueue = (PlayQueue) ((Outcome.Success) outcomeStartCloudQueue$default).getValue();
            List<GenericQueueEntity> playableEntities = playQueue.getPlayableEntities();
            if (playableEntities == null) {
                playableEntities = CollectionsKt__CollectionsKt.emptyList();
            }
            List<GenericQueueEntity> list = playableEntities;
            List<QueueSequenceSlice> queueSlices = playQueue.getQueueSlices();
            QueueSequenceSlice queueSequenceSlice = queueSlices == null ? null : (QueueSequenceSlice) CollectionsKt___CollectionsKt.firstOrNull((List) queueSlices);
            if (queueSequenceSlice == null) {
                Log.warning(CloudQueueMiniPlayerController.TAG, "callInitiateQueue: queueSequenceSlice is null");
                return;
            }
            ControlSource controlSource = ControlSource.APP_UI;
            CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            MetricsContext onlineInteractionMetricsContext = companion.getOnlineInteractionMetricsContext(((GenericQueueEntity) CollectionsKt___CollectionsKt.first((List) list)).getMetricsContext(), PlaybackPageType.EXTERNAL);
            Queue queueMetaData = playQueue.getQueueMetaData();
            Intrinsics.checkNotNullExpressionValue(controlSource, "controlSource");
            companion.startCloudQueuePlayback(new CloudQueueMiniPlayerController$Companion$callInitiateQueue$1$1$2(playQueue, queueSequenceSlice), activity, null, companion.convertGenericQueueEntitiesToMediaItems(list, queueMetaData, onlineInteractionMetricsContext, controlSource, null), 0, false, true, LoopModeEnum.LOOP_OFF, playQueue.getQueueCapability(), onlineInteractionMetricsContext, controlSource, null, null, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: callInitiateQueue$lambda-3$lambda-1$lambda-0, reason: not valid java name */
        public static final void m1547callInitiateQueue$lambda3$lambda1$lambda0(FragmentActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            if (activity.isFinishing()) {
                return;
            }
            if (activity instanceof MusicHomeActivity) {
                ((MusicHomeActivity) activity).showLoadingView(false);
            } else if (activity instanceof NowPlayingFragmentActivity) {
                ((NowPlayingFragmentActivity) activity).showLoadingView(false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: callInitiateQueue$lambda-3$lambda-2, reason: not valid java name */
        public static final void m1548callInitiateQueue$lambda3$lambda2(Throwable th) {
            Log.error(CloudQueueMiniPlayerController.TAG, "Error creating cloudQueueAppClient ", th);
        }

        private final void startNowPlayingScreen(Context context, ActiveDevicePlayback activeDevicePlayback) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
            intentForContentUri.putExtra("PULL_TRANSFER_TOAST", activeDevicePlayback.getDeviceName());
            context.startActivity(intentForContentUri);
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }
        }
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onPlayPauseClicked() {
        showConfirmDialog();
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onPlayerClicked() {
        if (isCastable()) {
            CastingTileModel castingTileModel = this.castingTileModel;
            if (castingTileModel == null) {
                return;
            }
            setupCasting(castingTileModel);
            return;
        }
        showConfirmDialog();
    }

    private final boolean isCastable() {
        return this.castingTileModel != null;
    }

    @Override // com.amazon.mp3.playback.PlayerNavigationListener
    public void showNowPlaying(InteractionType type) {
        PendingIntent clickAction;
        try {
            PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
            if (playbackConfig != null && (clickAction = playbackConfig.getClickAction()) != null) {
                clickAction.send();
            }
        } catch (PendingIntent.CanceledException e) {
            Log.warning(TAG, "could not restore Now Playing screen", e);
        }
    }

    private final void showConfirmDialog() {
        FragmentActivity fragmentActivity = null;
        if (checkAlwaysMoveStream()) {
            Companion companion = INSTANCE;
            ActiveDevicePlayback activeDevicePlayback = this.activeDevicePlayback;
            FragmentActivity fragmentActivity2 = this.activity;
            if (fragmentActivity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            } else {
                fragmentActivity = fragmentActivity2;
            }
            companion.callInitiateQueue(activeDevicePlayback, fragmentActivity);
            return;
        }
        CloudQueuePullConfirmDialog cloudQueuePullConfirmDialog = new CloudQueuePullConfirmDialog(this.activeDevicePlayback);
        FragmentActivity fragmentActivity3 = this.activity;
        if (fragmentActivity3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        } else {
            fragmentActivity = fragmentActivity3;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "activity.supportFragmentManager.beginTransaction()");
        fragmentTransactionBeginTransaction.add(cloudQueuePullConfirmDialog, this.DIALOG_NAME);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void setupCasting(CastingTileModel model) {
        CastingDevice.Builder builderWithImageUrl = new CastingDevice.Builder(CastingCategory.fromString(model.category.get()), model.targetId.get()).withDeviceTypeId(model.deviceTypeId.get()).withName(model.title.get()).withIsPlaying(false).withImageUrl(model.imageUrl.get());
        FragmentActivity fragmentActivity = null;
        this.castingSessionManager.registerListener((CastingConnectionCallback) null);
        this.castingSessionManager.connect(builderWithImageUrl.build());
        FragmentActivity fragmentActivity2 = this.activity;
        if (fragmentActivity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        } else {
            fragmentActivity = fragmentActivity2;
        }
        ((MusicHomeActivity) fragmentActivity).showLoadingView(true);
    }

    private final boolean checkAlwaysMoveStream() {
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            fragmentActivity = null;
        }
        return fragmentActivity.getPreferences(0).getBoolean(ALWAYS_MOVE_STREAM, false);
    }
}
