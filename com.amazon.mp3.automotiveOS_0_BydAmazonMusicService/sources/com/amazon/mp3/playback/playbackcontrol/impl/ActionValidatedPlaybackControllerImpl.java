package com.amazon.mp3.playback.playbackcontrol.impl;

import android.os.Handler;
import androidx.media.VolumeProviderCompat;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueUIMetricsManager;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.PlaybackControlVisibilityQueryable;
import com.amazon.mp3.playback.playbackcontrol.ActionResult;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener;
import com.amazon.mp3.skiplimit.SkipLimitController;
import com.amazon.mp3.skiplimit.SkipLimitControllerProvider;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.RunnableDebouncer;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackErrorListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.platform.data.entity.EntityItem;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionValidatedPlaybackControllerImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u0090\u00012\u00020\u0001:\u0002\u0090\u0001B\u0011\u0012\u0006\u0010z\u001a\u00020y¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000e\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\bH\u0016J\u0018\u0010#\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001aH\u0016J\b\u0010$\u001a\u00020\u0015H\u0016J\b\u0010&\u001a\u00020%H\u0016J\b\u0010'\u001a\u00020%H\u0016J\u0010\u0010$\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\rH\u0016J\u0010\u0010)\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020+0*H\u0016J\b\u0010-\u001a\u00020\bH\u0016J\b\u0010.\u001a\u00020\u0015H\u0016J\b\u0010/\u001a\u00020+H\u0016J\b\u00100\u001a\u00020\bH\u0016J\b\u00102\u001a\u000201H\u0016J\b\u00103\u001a\u00020\u0015H\u0016J\b\u00105\u001a\u000204H\u0016J\b\u00107\u001a\u000206H\u0016J\b\u00108\u001a\u00020\bH\u0016J\u0012\u00109\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010;\u001a\u00020:H\u0016J\n\u0010=\u001a\u0004\u0018\u00010<H\u0016J\u0012\u0010?\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010>H\u0016J\u0012\u0010B\u001a\u00020\u00152\b\u0010A\u001a\u0004\u0018\u00010@H\u0016J\u0012\u0010D\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010CH\u0016J\b\u0010E\u001a\u00020\bH\u0016J\b\u0010F\u001a\u00020\u001aH\u0016J\b\u0010G\u001a\u00020\u0015H\u0016J\u0012\u0010I\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010HH\u0016J\b\u0010J\u001a\u00020\u001aH\u0016J\b\u0010K\u001a\u00020\bH\u0016J\b\u0010L\u001a\u00020\u0006H\u0016J\n\u0010N\u001a\u0004\u0018\u00010MH\u0016J\b\u0010O\u001a\u00020\u001aH\u0016J\b\u0010P\u001a\u00020\bH\u0016J\b\u0010Q\u001a\u00020\bH\u0016J\b\u0010R\u001a\u00020\u0015H\u0016J\b\u0010S\u001a\u00020\u0015H\u0016J\b\u0010T\u001a\u00020\u001aH\u0016J\b\u0010U\u001a\u00020\rH\u0016J\b\u0010V\u001a\u00020\u0015H\u0016J\b\u0010W\u001a\u00020\u0015H\u0016J\u0012\u0010X\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010HH\u0016J\b\u0010Y\u001a\u00020\bH\u0016J\u0012\u0010[\u001a\u00020\u00152\b\u0010Z\u001a\u0004\u0018\u00010+H\u0016J\b\u0010\\\u001a\u00020\u0015H\u0016J\b\u0010]\u001a\u00020\bH\u0016J\b\u0010^\u001a\u00020\rH\u0016J\u0010\u0010a\u001a\u00020\u00152\u0006\u0010`\u001a\u00020_H\u0016J\u0010\u0010c\u001a\u00020\u00112\u0006\u0010b\u001a\u00020\u001aH\u0016J\b\u0010d\u001a\u00020\bH\u0016J\b\u0010e\u001a\u00020\bH\u0016J\b\u0010f\u001a\u00020\u0015H\u0016J\u0010\u0010g\u001a\u00020\u00152\u0006\u0010b\u001a\u00020\u001aH\u0016J\b\u0010h\u001a\u00020\u0015H\u0016J\b\u0010i\u001a\u00020\bH\u0016J\b\u0010j\u001a\u00020\u0015H\u0016J\u0012\u0010k\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010CH\u0016J\n\u0010m\u001a\u0004\u0018\u00010lH\u0016J\b\u0010n\u001a\u00020\u001aH\u0016J\u0010\u0010p\u001a\u00020\u00152\u0006\u0010o\u001a\u00020\u001aH\u0016J\b\u0010q\u001a\u00020\u0015H\u0016J\"\u0010t\u001a\u00020\u00152\b\u0010r\u001a\u0004\u0018\u00010\u00062\u0006\u0010s\u001a\u00020\b2\u0006\u0010(\u001a\u00020\rH\u0016J\u0012\u0010u\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010w\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020vH\u0016J\u0010\u0010x\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020vH\u0016R\u001a\u0010z\u001a\u00020y8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bz\u0010{\u001a\u0004\b|\u0010}R\u0014\u0010~\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u001d\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020v0\u001d8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R!\u0010\u0087\u0001\u001a\u00030\u0082\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0018\u0010\u0089\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001¨\u0006\u0091\u0001"}, d2 = {"Lcom/amazon/mp3/playback/playbackcontrol/impl/ActionValidatedPlaybackControllerImpl;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionResult;", "skipNextForResult", "Lcom/amazon/mp3/skiplimit/SkipLimitController;", "skipLimitController", "", "action", "", "canDisplayAction", "canSkipNext", "canLoop", "canLoopOne", "", "getAmountBuffered", "Lcom/amazon/music/media/playback/ActionAvailability;", "getSkipNextAvailability", "Lcom/amazon/music/media/playback/MediaItem;", "getCurrentMediaItem", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "removeOnPlaybackEventListener", "shuffled", "setShuffled", "shouldPauseWhenTaskRemoved", "", "index", "getUnshuffledIndex", "", "getCurrentQueue", "isLive", "isStreaming", "positionMillis", "seekMode", "seek", PageUriUtils.DO_PLAY_VALUE, "Lcom/amazon/music/media/playback/PlaybackAttributes;", "getCurrentPlaybackAttributes", "getBestAvailablePlaybackAttributes", "requestTime", "skipToIndex", "Ljava/util/EnumSet;", "Lcom/amazon/music/media/playback/RepeatMode;", "getAvailableRepeatModes", "canRateCurrentItem", "togglePlaying", "getRepeatMode", "canRateMediaItems", "Lcom/amazon/music/media/playback/PlayStatus;", "getPlayStatus", "normalSpeed", "Lcom/amazon/music/media/playback/ControlSource;", "getControlSource", "Lcom/amazon/music/media/playback/Playback;", "getPlayback", "canSkipToIndex", "addOnPlaybackEventListener", "", "getPlaybackSpeed", "Landroidx/media/VolumeProviderCompat;", "getVolumeProvider", "Lcom/amazon/music/media/playback/OnPlaybackErrorListener;", "addOnPlaybackErrorListener", "Lcom/amazon/music/media/playback/ChangeReason;", "reason", AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "addOnPlayStateChangedListener", "canRestartItem", "getCurrentIndex", "rewind", "Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "removeOnMetadataChangedListener", "getCurrentItemRating", "canShuffle", "getMediaPlayerName", "Lcom/amazon/music/media/playback/PlayerCapabilities;", "getPlayerCapabilities", "toggleThumbsDown", "canPlay", "isShuffled", "onUserInteraction", "skipPrevious", "toggleThumbsUp", "getDurationMillis", "skipNext", "fastForward", "addOnMetadataChangedListener", "canFastForward", "mode", "setRepeatMode", "toggleRepeatMode", "canRewind", "getPositionMillis", "", "speed", "setPlaybackSpeed", "numSkips", "peek", "canSeek", "canSkipPrevious", "pause", "skip", "restartMediaItem", "isStopped", "previousOrRestart", "removeOnPlayStateChangedListener", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "getMediaCollectionInfo", "getAudioFocusStatus", "rating", "setCurrentItemRating", "autoResolveNextConcurrencyCheck", "deviceName", "shouldPlay", "resolveMaxConcurrency", "removeOnPlaybackErrorListener", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", "addOnLyricsLineUpdateListener", "removeOnLyricsLineUpdateListener", "Lcom/amazon/music/media/playback/PlaybackController;", "controller", "Lcom/amazon/music/media/playback/PlaybackController;", "getController", "()Lcom/amazon/music/media/playback/PlaybackController;", "TAG", "Ljava/lang/String;", "mOnLyricsLineUpdateListeners", "Ljava/util/List;", "Lcom/amazon/mp3/util/RunnableDebouncer;", "mRunnableDebouncer$delegate", "Lkotlin/Lazy;", "getMRunnableDebouncer", "()Lcom/amazon/mp3/util/RunnableDebouncer;", "mRunnableDebouncer", "", "lock", "Ljava/lang/Object;", "Ljava/lang/Runnable;", "mUpdateLyricsLineRunnable", "Ljava/lang/Runnable;", "<init>", "(Lcom/amazon/music/media/playback/PlaybackController;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ActionValidatedPlaybackControllerImpl implements ActionValidatedPlaybackController {
    private final String TAG;
    private final PlaybackController controller;
    private final Object lock;
    private final List<OnLyricsLineUpdateListener> mOnLyricsLineUpdateListeners;

    /* JADX INFO: renamed from: mRunnableDebouncer$delegate, reason: from kotlin metadata */
    private final Lazy mRunnableDebouncer;
    private final Runnable mUpdateLyricsLineRunnable;

    /* JADX INFO: compiled from: ActionValidatedPlaybackControllerImpl.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActionAvailability.values().length];
            iArr[ActionAvailability.AVAILABLE.ordinal()] = 1;
            iArr[ActionAvailability.UNAVAILABLE_SKIP_LIMIT.ordinal()] = 2;
            iArr[ActionAvailability.UNAVAILABLE_CONTENT_TYPE.ordinal()] = 3;
            iArr[ActionAvailability.UNAVAILABLE.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ActionValidatedPlaybackControllerImpl(PlaybackController controller) {
        Intrinsics.checkNotNullParameter(controller, "controller");
        this.controller = controller;
        String simpleName = ActionValidatedPlaybackControllerImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "ActionValidatedPlaybackC…pl::class.java.simpleName");
        this.TAG = simpleName;
        this.mOnLyricsLineUpdateListeners = new ArrayList();
        this.mRunnableDebouncer = LazyKt__LazyJVMKt.lazy(new Function0<RunnableDebouncer>() { // from class: com.amazon.mp3.playback.playbackcontrol.impl.ActionValidatedPlaybackControllerImpl$mRunnableDebouncer$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RunnableDebouncer invoke() {
                return new RunnableDebouncer(new Handler());
            }
        });
        this.lock = new Object();
        this.mUpdateLyricsLineRunnable = new Runnable() { // from class: com.amazon.mp3.playback.playbackcontrol.impl.ActionValidatedPlaybackControllerImpl$mUpdateLyricsLineRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                Object obj = this.this$0.lock;
                ActionValidatedPlaybackControllerImpl actionValidatedPlaybackControllerImpl = this.this$0;
                synchronized (obj) {
                    long positionMillis = actionValidatedPlaybackControllerImpl.getPositionMillis();
                    Iterator it = actionValidatedPlaybackControllerImpl.mOnLyricsLineUpdateListeners.iterator();
                    while (it.hasNext()) {
                        ((OnLyricsLineUpdateListener) it.next()).onLyricsLineUpdated(positionMillis);
                    }
                    if (!actionValidatedPlaybackControllerImpl.mOnLyricsLineUpdateListeners.isEmpty()) {
                        actionValidatedPlaybackControllerImpl.getMRunnableDebouncer().postDelayed(this, 50L);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        };
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController
    public PlaybackController getController() {
        return this.controller;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RunnableDebouncer getMRunnableDebouncer() {
        return (RunnableDebouncer) this.mRunnableDebouncer.getValue();
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController
    public ActionResult skipNextForResult() {
        return skipNextForResult(SkipLimitControllerProvider.INSTANCE.getController());
    }

    public final ActionResult skipNextForResult(SkipLimitController skipLimitController) {
        Intrinsics.checkNotNullParameter(skipLimitController, "skipLimitController");
        if (getController().getCurrentMediaItem() instanceof AdMediaItem) {
            CloudQueueUIMetricsManager cloudQueueUIMetricsManager = CloudQueueUIMetricsManager.get();
            MediaItem currentMediaItem = getController().getCurrentMediaItem();
            if (currentMediaItem == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.cloudqueue.sequencer.AdMediaItem");
            }
            cloudQueueUIMetricsManager.sendAdSkipClickingEvent((AdMediaItem) currentMediaItem);
        }
        SkipLimitProviderImpl skipLimitProviderImpl = SkipLimitProviderImpl.INSTANCE;
        skipLimitProviderImpl.onSkip();
        if (getController().getCurrentMediaItem() instanceof InterludeMediaItem) {
            Log.debug(this.TAG, "Interludes are always skippable and don't count towards limit.");
            getController().skipNext();
            return ActionResult.OK;
        }
        ActionAvailability skipNextAvailability = getController().getSkipNextAvailability();
        int i = skipNextAvailability == null ? -1 : WhenMappings.$EnumSwitchMapping$0[skipNextAvailability.ordinal()];
        if (i == 1) {
            Log.debug(this.TAG, "Skip next action accepted.");
            Log.debug(this.TAG, Intrinsics.stringPlus("number of skips remaining: ", Integer.valueOf(skipLimitProviderImpl.getCount())));
            getController().skipNext();
            skipLimitController.onUserNext();
            return ActionResult.OK;
        }
        if (i == 2 || i == 3) {
            Log.debug(this.TAG, "Skip next action was rejected. Should upsell.");
            return ActionResult.REJECTED_SHOULD_UPSELL;
        }
        if (i == 4) {
            Log.debug(this.TAG, "Skip next action was rejected.");
            return ActionResult.REJECTED;
        }
        return ActionResult.REJECTED;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController
    public boolean canDisplayAction(String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        EntityItem currentMediaItem = getController().getCurrentMediaItem();
        if (currentMediaItem instanceof PlaybackControlVisibilityQueryable) {
            switch (action.hashCode()) {
                case -1504652583:
                    if (action.equals("SHUFFLE")) {
                        return canShuffle();
                    }
                    return ((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action);
                case -491148553:
                    if (action.equals("PREVIOUS")) {
                        if (((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action) || canRestartItem()) {
                            return true;
                        }
                    }
                    return ((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action);
                case 2342564:
                    if (action.equals("LOOP")) {
                        return canLoop();
                    }
                    return ((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action);
                case 2392819:
                    if (action.equals("NEXT")) {
                        if (((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action) && (canSkipNext() || getSkipNextAvailability() == ActionAvailability.UNAVAILABLE_SKIP_LIMIT)) {
                            return true;
                        }
                    }
                    return ((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action);
                default:
                    return ((PlaybackControlVisibilityQueryable) currentMediaItem).canDisplay(action);
            }
            return false;
        }
        switch (action.hashCode()) {
            case -1504652583:
                if (action.equals("SHUFFLE")) {
                    return canShuffle();
                }
                break;
            case -1253560789:
                if (action.equals("LOOP_ONE")) {
                    return canLoopOne();
                }
                break;
            case -491148553:
                if (action.equals("PREVIOUS")) {
                    return canSkipPrevious() || canRestartItem() || CastingUtil.isCasting();
                }
                break;
            case -474384116:
                if (action.equals("RATE_TRACK")) {
                    return canRateCurrentItem();
                }
                break;
            case -297756555:
                if (action.equals("SCRUB_FORWARD")) {
                    return canFastForward();
                }
                break;
            case 2342564:
                if (action.equals("LOOP")) {
                    return canLoop();
                }
                break;
            case 2392819:
                if (action.equals("NEXT")) {
                    return canSkipNext() || getSkipNextAvailability() == ActionAvailability.UNAVAILABLE_SKIP_LIMIT;
                }
                break;
            case 998145043:
                if (action.equals("SCRUB_BACKWARD")) {
                    return canSeek();
                }
                break;
        }
        Log.error(this.TAG, "Unsupported action " + action + '.');
        return false;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSkipNext() {
        return getController().canSkipNext();
    }

    public boolean canLoop() {
        return getController().getAvailableRepeatModes().equals(RepeatMode.ALL_REPEAT_MODES);
    }

    public boolean canLoopOne() {
        return getController().getAvailableRepeatModes().equals(RepeatMode.ALL_REPEAT_MODES) || getController().getAvailableRepeatModes().equals(RepeatMode.REPEAT_ONE_OR_NONE);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public long getAmountBuffered() {
        return getController().getAmountBuffered();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public ActionAvailability getSkipNextAvailability() {
        ActionAvailability skipNextAvailability = getController().getSkipNextAvailability();
        Intrinsics.checkNotNullExpressionValue(skipNextAvailability, "controller.skipNextAvailability");
        return skipNextAvailability;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public MediaItem getCurrentMediaItem() {
        return getController().getCurrentMediaItem();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnPlaybackEventListener(OnPlaybackEventListener listener) {
        getController().removeOnPlaybackEventListener(listener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void setShuffled(boolean shuffled) {
        getController().setShuffled(shuffled);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean shouldPauseWhenTaskRemoved() {
        return getController().shouldPauseWhenTaskRemoved();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int getUnshuffledIndex(int index) {
        return getController().getUnshuffledIndex(index);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public List<MediaItem> getCurrentQueue() {
        List<MediaItem> currentQueue = getController().getCurrentQueue();
        Intrinsics.checkNotNullExpressionValue(currentQueue, "controller.currentQueue");
        return currentQueue;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isLive() {
        return getController().isLive();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isStreaming() {
        return getController().isStreaming();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void seek(long positionMillis, int seekMode) {
        getController().seek(positionMillis, seekMode);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void play() {
        getController().play();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        PlaybackAttributes currentPlaybackAttributes = getController().getCurrentPlaybackAttributes();
        Intrinsics.checkNotNullExpressionValue(currentPlaybackAttributes, "controller.currentPlaybackAttributes");
        return currentPlaybackAttributes;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlaybackAttributes getBestAvailablePlaybackAttributes() {
        PlaybackAttributes bestAvailablePlaybackAttributes = getController().getBestAvailablePlaybackAttributes();
        Intrinsics.checkNotNullExpressionValue(bestAvailablePlaybackAttributes, "controller.bestAvailablePlaybackAttributes");
        return bestAvailablePlaybackAttributes;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void play(long requestTime) {
        getController().play(requestTime);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skipToIndex(int index) {
        getController().skipToIndex(index);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public EnumSet<RepeatMode> getAvailableRepeatModes() {
        EnumSet<RepeatMode> availableRepeatModes = getController().getAvailableRepeatModes();
        Intrinsics.checkNotNullExpressionValue(availableRepeatModes, "controller.availableRepeatModes");
        return availableRepeatModes;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRateCurrentItem() {
        return getController().canRateCurrentItem();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void togglePlaying() {
        getController().togglePlaying();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public RepeatMode getRepeatMode() {
        RepeatMode repeatMode = getController().getRepeatMode();
        Intrinsics.checkNotNullExpressionValue(repeatMode, "controller.repeatMode");
        return repeatMode;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRateMediaItems() {
        return getController().canRateMediaItems();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlayStatus getPlayStatus() {
        PlayStatus playStatus = getController().getPlayStatus();
        Intrinsics.checkNotNullExpressionValue(playStatus, "controller.playStatus");
        return playStatus;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void normalSpeed() {
        getController().normalSpeed();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public ControlSource getControlSource() {
        ControlSource controlSource = getController().getControlSource();
        Intrinsics.checkNotNullExpressionValue(controlSource, "controller.controlSource");
        return controlSource;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public Playback getPlayback() {
        Playback playback = getController().getPlayback();
        Intrinsics.checkNotNullExpressionValue(playback, "controller.playback");
        return playback;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSkipToIndex() {
        return getController().canSkipToIndex();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnPlaybackEventListener(OnPlaybackEventListener listener) {
        getController().addOnPlaybackEventListener(listener);
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, com.amazon.music.media.playback.PlaybackController
    public float getPlaybackSpeed() {
        return getController().getPlaybackSpeed();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public VolumeProviderCompat getVolumeProvider() {
        return getController().getVolumeProvider();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnPlaybackErrorListener(OnPlaybackErrorListener listener) {
        getController().addOnPlaybackErrorListener(listener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void stop(ChangeReason reason) {
        SkipLimitControllerProvider.INSTANCE.getController().clearSession();
        getController().stop(reason);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnPlayStateChangedListener(OnPlayStateChangedListener listener) {
        getController().addOnPlayStateChangedListener(listener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRestartItem() {
        return getController().canRestartItem();
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, com.amazon.music.media.playback.PlaybackController
    public int getCurrentIndex() {
        return getController().getCurrentIndex();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void rewind() {
        getController().rewind();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnMetadataChangedListener(OnMetadataChangedListener listener) {
        getController().removeOnMetadataChangedListener(listener);
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, com.amazon.music.media.playback.PlaybackController
    public int getCurrentItemRating() {
        return getController().getCurrentItemRating();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canShuffle() {
        return getController().canShuffle();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public String getMediaPlayerName() {
        String mediaPlayerName = getController().getMediaPlayerName();
        Intrinsics.checkNotNullExpressionValue(mediaPlayerName, "controller.mediaPlayerName");
        return mediaPlayerName;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlayerCapabilities getPlayerCapabilities() {
        return getController().getPlayerCapabilities();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int toggleThumbsDown() {
        int i = getController().toggleThumbsDown();
        SkipLimitControllerProvider.INSTANCE.getController().onUserNext();
        return i;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canPlay() {
        return getController().canPlay();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isShuffled() {
        return getController().isShuffled();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void onUserInteraction() {
        getController().onUserInteraction();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skipPrevious() {
        getController().skipPrevious();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int toggleThumbsUp() {
        return getController().toggleThumbsUp();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public long getDurationMillis() {
        return getController().getDurationMillis();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skipNext() {
        skipNextForResult();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void fastForward() {
        getController().fastForward();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnMetadataChangedListener(OnMetadataChangedListener listener) {
        getController().addOnMetadataChangedListener(listener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canFastForward() {
        return getController().canFastForward();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void setRepeatMode(RepeatMode mode) {
        getController().setRepeatMode(mode);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void toggleRepeatMode() {
        getController().toggleRepeatMode();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRewind() {
        return getController().canRewind();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public long getPositionMillis() {
        return getController().getPositionMillis();
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, com.amazon.music.media.playback.PlaybackController
    public void setPlaybackSpeed(double speed) {
        getController().setPlaybackSpeed(speed);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public MediaItem peek(int numSkips) {
        MediaItem mediaItemPeek = getController().peek(numSkips);
        Intrinsics.checkNotNullExpressionValue(mediaItemPeek, "controller.peek(numSkips)");
        return mediaItemPeek;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSeek() {
        return getController().canSeek();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSkipPrevious() {
        return getController().canSkipPrevious();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void pause() {
        getController().pause();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skip(int numSkips) {
        getController().skip(numSkips);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void restartMediaItem() {
        getController().restartMediaItem();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isStopped() {
        return getController().isStopped();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void previousOrRestart() {
        getController().previousOrRestart();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnPlayStateChangedListener(OnPlayStateChangedListener listener) {
        getController().removeOnPlayStateChangedListener(listener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public MediaCollectionInfo getMediaCollectionInfo() {
        return getController().getMediaCollectionInfo();
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, com.amazon.music.media.playback.PlaybackController
    public int getAudioFocusStatus() {
        return getController().getAudioFocusStatus();
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, com.amazon.music.media.playback.PlaybackController
    public void setCurrentItemRating(int rating) {
        getController().setCurrentItemRating(rating);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void autoResolveNextConcurrencyCheck() {
        getController().autoResolveNextConcurrencyCheck();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void resolveMaxConcurrency(String deviceName, boolean shouldPlay, long requestTime) {
        getController().resolveMaxConcurrency(deviceName, shouldPlay, requestTime);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnPlaybackErrorListener(OnPlaybackErrorListener listener) {
        getController().removeOnPlaybackErrorListener(listener);
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController
    public void addOnLyricsLineUpdateListener(OnLyricsLineUpdateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.lock) {
            if (this.mOnLyricsLineUpdateListeners.contains(listener)) {
                return;
            }
            this.mOnLyricsLineUpdateListeners.add(listener);
            getMRunnableDebouncer().post(this.mUpdateLyricsLineRunnable);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController
    public void removeOnLyricsLineUpdateListener(OnLyricsLineUpdateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.lock) {
            this.mOnLyricsLineUpdateListeners.remove(listener);
        }
    }
}
