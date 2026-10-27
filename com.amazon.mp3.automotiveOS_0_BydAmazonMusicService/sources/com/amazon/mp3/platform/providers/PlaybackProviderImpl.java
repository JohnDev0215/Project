package com.amazon.mp3.platform.providers;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.platform.mediaitem.EntityItemUtils;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.Listener;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.QueueId;
import com.amazon.music.platform.events.PlaybackStateEvent;
import com.amazon.music.platform.playback.PlaybackStateListener;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents;
import com.amazon.music.platform.playback.commands.PlaybackControls;
import com.amazon.music.platform.playback.data.PlaybackDevice;
import com.amazon.music.platform.playback.data.PlaybackPlayingMode;
import com.amazon.music.platform.playback.data.PlaybackRequest;
import com.amazon.music.platform.playback.state.DevicePlaybackState;
import com.amazon.music.platform.playback.state.DisplayMode;
import com.amazon.music.platform.playback.state.LoopMode;
import com.amazon.music.platform.playback.state.PlayQueueMode;
import com.amazon.music.platform.playback.state.PlaybackState;
import com.amazon.music.platform.playback.state.ShuffleMode;
import com.amazon.music.platform.providers.PlaybackProvider;
import com.amazon.music.platform.providers.impl.PlaybackCommandImpl;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b.\u0010/J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\n\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010\n\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016R\u0017\u0010\u001b\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001fR\u0014\u0010 \u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R0\u0010+\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(0'j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(`*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R$\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\u00140'j\b\u0012\u0004\u0012\u00020\u0014`*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010,\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lcom/amazon/mp3/platform/providers/PlaybackProviderImpl;", "Lcom/amazon/music/platform/providers/PlaybackProvider;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "Lcom/amazon/music/platform/playback/state/PlaybackState;", "getPlaybackState", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getParentEntityItemId", "Lcom/amazon/music/platform/playback/state/DevicePlaybackState;", "state", "Lcom/amazon/music/platform/playback/data/PlaybackPlayingMode;", "mode", "Lcom/amazon/music/platform/playback/commands/PlaybackControls;", "controls", "Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "playbackRequest", "Lcom/amazon/music/platform/Outcome;", "initiatePlayback", "(Lcom/amazon/music/platform/playback/data/PlaybackRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/playback/PlaybackStateListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "addPlaybackStateListener", "removePlaybackStateListener", "onPlayStateChanged", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackControls", "Lcom/amazon/music/platform/playback/commands/PlaybackControls;", "Lcom/amazon/music/platform/providers/impl/PlaybackCommandImpl;", "commandHandler", "Lcom/amazon/music/platform/providers/impl/PlaybackCommandImpl;", "getCommandHandler", "()Lcom/amazon/music/platform/providers/impl/PlaybackCommandImpl;", "Ljava/util/HashSet;", "Lcom/amazon/music/platform/data/Listener;", "", "Lkotlin/collections/HashSet;", "stateListeners", "Ljava/util/HashSet;", "playbackStateListener", "<init>", "(Landroid/content/Context;Lcom/amazon/music/media/playback/PlaybackController;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaybackProviderImpl implements PlaybackProvider, OnPlayStateChangedListener {
    private final PlaybackCommandImpl commandHandler;
    private final Context context;
    private final PlaybackController playbackController;
    private final PlaybackControls playbackControls;
    private final HashSet<PlaybackStateListener> playbackStateListener;
    private final HashSet<Listener<Object>> stateListeners;

    /* JADX INFO: compiled from: PlaybackProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayStatus.values().length];
            iArr[PlayStatus.RENDERING.ordinal()] = 1;
            iArr[PlayStatus.USER_PAUSED.ordinal()] = 2;
            iArr[PlayStatus.SYSTEM_PAUSED.ordinal()] = 3;
            iArr[PlayStatus.SEEKING.ordinal()] = 4;
            iArr[PlayStatus.BUFFERING.ordinal()] = 5;
            iArr[PlayStatus.SWITCHING_PLAYERS.ordinal()] = 6;
            iArr[PlayStatus.LOADING.ordinal()] = 7;
            iArr[PlayStatus.PREPARING.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PlaybackProviderImpl(Context context, PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.context = context;
        this.playbackController = playbackController;
        this.commandHandler = new PlaybackCommandImpl(true, CollectionsKt__CollectionsKt.mutableListOf(new InitiateMusicPlaybackCommandHandler()));
        this.stateListeners = new HashSet<>();
        this.playbackStateListener = new HashSet<>();
        playbackController.addOnPlayStateChangedListener(this);
        this.playbackControls = new PlaybackControlsImpl(null, 1, null);
    }

    public /* synthetic */ PlaybackProviderImpl(Context context, PlaybackController playbackController, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? PlaybackControllerProvider.INSTANCE.getController(ControlSource.NONE) : playbackController);
    }

    public final PlaybackCommandImpl getCommandHandler() {
        return this.commandHandler;
    }

    @Override // com.amazon.music.platform.providers.PlaybackProvider
    public DevicePlaybackState state() {
        return state(null);
    }

    public DevicePlaybackState state(PlaybackPlayingMode mode) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.context);
        PlaybackProgressImpl playbackProgressImpl = new PlaybackProgressImpl(this.playbackController);
        LoopMode loopMode = LoopMode.LOOP_OFF;
        CloudQueueSequencer.Companion companion = CloudQueueSequencer.INSTANCE;
        CloudQueueSequencer cloudQueueSequencer = companion.getCloudQueueSequencer();
        ShuffleMode shuffleMode = cloudQueueSequencer != null && cloudQueueSequencer.isShuffled() ? ShuffleMode.SHUFFLE_ON : ShuffleMode.SHUFFLE_OFF;
        PlaybackState playbackState = getPlaybackState(this.playbackController);
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        EntityItemIdentifier entityItemIdentifier = currentMediaItem == null ? null : currentMediaItem.getEntityItemIdentifier();
        EntityItemIdentifier parentEntityItemId = getParentEntityItemId();
        String deviceId = accountCredentialStorage.getDeviceId();
        Intrinsics.checkNotNullExpressionValue(deviceId, "credentialStorage.deviceId");
        String deviceType = accountCredentialStorage.getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "credentialStorage.deviceType");
        PlaybackDevice playbackDevice = new PlaybackDevice(deviceId, deviceType, null, 4, null);
        PlayQueueMode playQueueMode = PlayQueueMode.LOCAL;
        DisplayMode displayMode = DisplayMode.AUDIO_ONLY;
        CloudQueueSequencer cloudQueueSequencer2 = companion.getCloudQueueSequencer();
        return new DevicePlaybackState(playbackProgressImpl, loopMode, shuffleMode, playbackState, entityItemIdentifier, parentEntityItemId, playbackDevice, playQueueMode, displayMode, cloudQueueSequencer2 == null ? false : cloudQueueSequencer2.getIsUserInitiated());
    }

    private final PlaybackState getPlaybackState(PlaybackController playbackController) {
        if (playbackController.getPlayStatus() == null) {
            return PlaybackState.ENDED;
        }
        PlayStatus playStatus = playbackController.getPlayStatus();
        switch (playStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$0[playStatus.ordinal()]) {
            case 1:
                return PlaybackState.PLAYING;
            case 2:
            case 3:
            case 4:
            case 5:
                return PlaybackState.PAUSED;
            case 6:
            case 7:
            case 8:
                return PlaybackState.WAITING_TO_PLAY;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final EntityItemIdentifier getParentEntityItemId() {
        String queueId;
        Uri contentUri;
        CloudQueueSequencer.Companion companion = CloudQueueSequencer.INSTANCE;
        CloudQueueSequencer cloudQueueSequencer = companion.getCloudQueueSequencer();
        if (cloudQueueSequencer != null && (contentUri = cloudQueueSequencer.getContentUri()) != null) {
            return EntityItemUtils.INSTANCE.getCollectionEntityItemIdentifier(contentUri);
        }
        CloudQueueSequencer cloudQueueSequencer2 = companion.getCloudQueueSequencer();
        if (cloudQueueSequencer2 == null || (queueId = cloudQueueSequencer2.getQueueId()) == null) {
            return null;
        }
        return new EntityItemIdentifier(new QueueId(queueId), CollectionType.Queue.INSTANCE);
    }

    @Override // com.amazon.music.platform.providers.PlaybackProvider
    /* JADX INFO: renamed from: controls, reason: from getter */
    public PlaybackControls getPlaybackControls() {
        return this.playbackControls;
    }

    @Override // com.amazon.music.platform.providers.PlaybackProvider
    public Object initiatePlayback(PlaybackRequest playbackRequest, Continuation<? super Outcome<DevicePlaybackState>> continuation) {
        getCommandHandler().execute(new PlaybackCommandEvents.InitiateCommandEvent(new PlaybackCommandEvents(), playbackRequest));
        return new Outcome.Success(state());
    }

    @Override // com.amazon.music.platform.providers.PlaybackProvider
    public void addPlaybackStateListener(PlaybackStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.playbackStateListener.add(listener);
    }

    @Override // com.amazon.music.platform.providers.PlaybackProvider
    public void removePlaybackStateListener(PlaybackStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.playbackStateListener.remove(listener);
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        DevicePlaybackState devicePlaybackStateState;
        EntityItemIdentifier currentEntityItemId;
        if (this.playbackStateListener.size() == 0 || (currentEntityItemId = (devicePlaybackStateState = state()).getCurrentEntityItemId()) == null) {
            return;
        }
        PlaybackStateEvent playbackStateEvent = new PlaybackStateEvent(devicePlaybackStateState.getPlaybackState(), currentEntityItemId, devicePlaybackStateState.getParentEntityItemId());
        Iterator<T> it = this.playbackStateListener.iterator();
        while (it.hasNext()) {
            ((PlaybackStateListener) it.next()).onEvent(playbackStateEvent);
        }
    }
}
