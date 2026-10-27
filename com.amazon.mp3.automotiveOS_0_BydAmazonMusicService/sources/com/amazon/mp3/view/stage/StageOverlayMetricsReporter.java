package com.amazon.mp3.view.stage;

import android.content.Context;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.view.stage.overlay.OverlaySelectionType;
import com.amazon.mp3.view.stage.overlay.OverlayType;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageOverlayMetricsReporter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 O2\u00020\u0001:\u0001OB\u001b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010%\u001a\u0004\u0018\u00010$¢\u0006\u0004\bM\u0010NJ\b\u0010\u0003\u001a\u00020\u0002H\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0003J\b\u0010\u0005\u001a\u00020\u0002H\u0003J\u0012\u0010\b\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\u0002H\u0002J2\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0002H\u0002J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0002H\u0002J\b\u0010\u0017\u001a\u00020\u0002H\u0002J\b\u0010\u0018\u001a\u00020\u0002H\u0002J\u0006\u0010\u0019\u001a\u00020\u0002J\u000e\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0010J\b\u0010\u001c\u001a\u00020\u0002H\u0007R$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010%\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0018\u0010+\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R.\u00101\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000e008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00104R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0018\u0010:\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00060<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00060<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010>R\"\u0010A\u001a\u00020@8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010L¨\u0006P"}, d2 = {"Lcom/amazon/mp3/view/stage/StageOverlayMetricsReporter;", "Landroidx/lifecycle/LifecycleObserver;", "", "onResume", "onPause", "onDestroy", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "overlayType", "onOverlayChanged", "", "currentSystemTime", "setStartTime", "setEndTime", "reportEndMetrics", "Lkotlin/Pair;", "pair", "", "isAutomaticPreview", "makeNewPairWithNewTimes", "sendMetrics", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContentName;", "getContentNameFromOverlayType", "reset", "removeLifeCycleObserver", "setLifeCycleObserver", "onConfigurationChanged", "sendStart", "sendDwellTimeMetrics", "resetContextAndLifecycle", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "setLifecycle", "(Landroidx/lifecycle/Lifecycle;)V", "currentOverlay", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "currentOverlaySelectionType", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "", "timeSpentMap", "Ljava/util/Map;", "lastStartTime", "J", "lastEndTime", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "", "listOfOverlayToSendOverlayMetrics", "Ljava/util/List;", "listOfOverlayNotIncludedInDwellTime", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "overlayTypeStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "getOverlayTypeStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "setOverlayTypeStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;)V", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "onPlayStateChangedListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "onPlaybackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "<init>", "(Landroid/content/Context;Landroidx/lifecycle/Lifecycle;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageOverlayMetricsReporter implements LifecycleObserver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static StageOverlayMetricsReporter stageOverlayMetricsReporter;
    private Context context;
    private OverlayType currentOverlay;
    private OverlaySelectionType currentOverlaySelectionType;
    private long lastEndTime;
    private long lastStartTime;
    private Lifecycle lifecycle;
    private final List<OverlayType> listOfOverlayNotIncludedInDwellTime;
    private final List<OverlayType> listOfOverlayToSendOverlayMetrics;
    private MediaItem mediaItem;
    private final OnPlayStateChangedListener onPlayStateChangedListener;
    private final OnPlaybackEventListener onPlaybackEventListener;
    public OverlayTypeStateProvider overlayTypeStateProvider;
    private final ActionValidatedPlaybackController playbackController;
    private Map<OverlayType, Pair<Long, Long>> timeSpentMap = new LinkedHashMap();

    /* JADX INFO: compiled from: StageOverlayMetricsReporter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OverlayType.values().length];
            iArr[OverlayType.ALBUM_ART.ordinal()] = 1;
            iArr[OverlayType.XRAY_PARTIAL.ordinal()] = 2;
            iArr[OverlayType.XRAY_FULLSCREEN.ordinal()] = 3;
            iArr[OverlayType.LYRICS_PARTIAL.ordinal()] = 4;
            iArr[OverlayType.LYRICS_FULLSCREEN.ordinal()] = 5;
            iArr[OverlayType.CASTING.ordinal()] = 6;
            iArr[OverlayType.PLAY_QUEUE.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StageOverlayMetricsReporter(Context context, Lifecycle lifecycle) {
        this.context = context;
        this.lifecycle = lifecycle;
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        OverlayType overlayType = OverlayType.XRAY_FULLSCREEN;
        OverlayType overlayType2 = OverlayType.LYRICS_FULLSCREEN;
        this.listOfOverlayToSendOverlayMetrics = CollectionsKt__CollectionsKt.listOf((Object[]) new OverlayType[]{OverlayType.ALBUM_ART, overlayType, overlayType2, OverlayType.LYRICS_PARTIAL, OverlayType.XRAY_PARTIAL});
        this.listOfOverlayNotIncludedInDwellTime = CollectionsKt__CollectionsKt.listOf((Object[]) new OverlayType[]{overlayType, overlayType2, OverlayType.CASTING, OverlayType.PLAY_QUEUE});
        OnPlayStateChangedListener onPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.view.stage.StageOverlayMetricsReporter$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                StageOverlayMetricsReporter.m1751onPlayStateChangedListener$lambda2(this.f$0);
            }
        };
        this.onPlayStateChangedListener = onPlayStateChangedListener;
        OnPlaybackEventListener onPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.view.stage.StageOverlayMetricsReporter$$ExternalSyntheticLambda1
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                StageOverlayMetricsReporter.m1752onPlaybackEventListener$lambda5(this.f$0, i, controlSource);
            }
        };
        this.onPlaybackEventListener = onPlaybackEventListener;
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        if (this.overlayTypeStateProvider != null) {
            getOverlayTypeStateProvider().getCurrentOverlay().observeForever(new Observer() { // from class: com.amazon.mp3.view.stage.StageOverlayMetricsReporter$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    StageOverlayMetricsReporter.m1750_init_$lambda6(this.f$0, (OverlayType) obj);
                }
            });
            OverlayType value = getOverlayTypeStateProvider().getCurrentOverlay().getValue();
            if (value != null) {
                this.currentOverlay = value;
            }
            this.currentOverlaySelectionType = getOverlayTypeStateProvider().getOverlaySelectionType();
        }
        this.mediaItem = controller.getCurrentMediaItem();
        controller.addOnPlayStateChangedListener(onPlayStateChangedListener);
        controller.addOnPlaybackEventListener(onPlaybackEventListener);
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public final OverlayTypeStateProvider getOverlayTypeStateProvider() {
        OverlayTypeStateProvider overlayTypeStateProvider = this.overlayTypeStateProvider;
        if (overlayTypeStateProvider != null) {
            return overlayTypeStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayTypeStateProvider");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayStateChangedListener$lambda-2, reason: not valid java name */
    public static final void m1751onPlayStateChangedListener$lambda2(StageOverlayMetricsReporter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            return;
        }
        Context context = this$0.context;
        NowPlayingFragmentActivity nowPlayingFragmentActivity = context instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) context : null;
        if (nowPlayingFragmentActivity != null) {
            Fragment currentFragment = nowPlayingFragmentActivity.getCurrentFragment();
            NowPlayingFragment nowPlayingFragment = currentFragment instanceof NowPlayingFragment ? (NowPlayingFragment) currentFragment : null;
            if (nowPlayingFragment != null && nowPlayingFragment.isResumed()) {
                this$0.setEndTime(SystemClock.uptimeMillis());
                this$0.reportEndMetrics();
                this$0.sendMetrics();
            }
        }
        this$0.reset();
        this$0.currentOverlay = null;
        this$0.mediaItem = currentMediaItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlaybackEventListener$lambda-5, reason: not valid java name */
    public static final void m1752onPlaybackEventListener$lambda5(StageOverlayMetricsReporter this$0, int i, ControlSource noName_1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (i == 4 || i == 27) {
            Context context = this$0.context;
            NowPlayingFragmentActivity nowPlayingFragmentActivity = context instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) context : null;
            if (nowPlayingFragmentActivity != null) {
                Fragment currentFragment = nowPlayingFragmentActivity.getCurrentFragment();
                NowPlayingFragment nowPlayingFragment = currentFragment instanceof NowPlayingFragment ? (NowPlayingFragment) currentFragment : null;
                if (nowPlayingFragment != null && nowPlayingFragment.isResumed()) {
                    this$0.setEndTime(SystemClock.uptimeMillis());
                    this$0.reportEndMetrics();
                }
            }
            this$0.sendMetrics();
            this$0.reset();
            this$0.mediaItem = this$0.playbackController.getCurrentMediaItem();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-6, reason: not valid java name */
    public static final void m1750_init_$lambda6(StageOverlayMetricsReporter this$0, OverlayType overlayType) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOverlayChanged(overlayType);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private final void onResume() {
        setStartTime(SystemClock.uptimeMillis());
        this.currentOverlaySelectionType = getOverlayTypeStateProvider().getOverlaySelectionType();
        if (CollectionsKt___CollectionsKt.contains(this.listOfOverlayNotIncludedInDwellTime, this.currentOverlay)) {
            return;
        }
        sendDwellTimeMetrics(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private final void onPause() {
        setEndTime(SystemClock.uptimeMillis());
        reportEndMetrics();
        sendMetrics();
        if (CollectionsKt___CollectionsKt.contains(this.listOfOverlayNotIncludedInDwellTime, this.currentOverlay)) {
            return;
        }
        sendDwellTimeMetrics(false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private final void onDestroy() {
        if (Intrinsics.areEqual(this.currentOverlaySelectionType, OverlaySelectionType.Automatic.INSTANCE)) {
            this.currentOverlay = null;
        }
    }

    private final void onOverlayChanged(OverlayType overlayType) {
        Context context = this.context;
        NowPlayingFragmentActivity nowPlayingFragmentActivity = context instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) context : null;
        if (nowPlayingFragmentActivity != null) {
            Fragment currentFragment = nowPlayingFragmentActivity.getCurrentFragment();
            NowPlayingFragment nowPlayingFragment = currentFragment instanceof NowPlayingFragment ? (NowPlayingFragment) currentFragment : null;
            if (nowPlayingFragment != null && nowPlayingFragment.isResumed()) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                setEndTime(jUptimeMillis);
                reportEndMetrics();
                if (this.currentOverlay != overlayType) {
                    sendMetrics();
                }
                setStartTime(jUptimeMillis);
                if (CollectionsKt___CollectionsKt.contains(this.listOfOverlayNotIncludedInDwellTime, this.currentOverlay) && !CollectionsKt___CollectionsKt.contains(this.listOfOverlayNotIncludedInDwellTime, overlayType)) {
                    sendDwellTimeMetrics(true);
                } else {
                    OverlayType overlayType2 = this.currentOverlay;
                    if (overlayType2 != null && !CollectionsKt___CollectionsKt.contains(this.listOfOverlayNotIncludedInDwellTime, overlayType2) && CollectionsKt___CollectionsKt.contains(this.listOfOverlayNotIncludedInDwellTime, overlayType)) {
                        sendDwellTimeMetrics(false);
                    }
                }
            }
        }
        this.currentOverlay = overlayType;
        this.currentOverlaySelectionType = getOverlayTypeStateProvider().getOverlaySelectionType();
    }

    public final void onConfigurationChanged() {
        setEndTime(SystemClock.uptimeMillis());
        reportEndMetrics();
    }

    private final void setStartTime(long currentSystemTime) {
        this.lastStartTime = currentSystemTime;
    }

    private final void setEndTime(long currentSystemTime) {
        this.lastEndTime = currentSystemTime;
    }

    private final void reportEndMetrics() {
        OverlayType overlayType = this.currentOverlay;
        switch (overlayType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[overlayType.ordinal()]) {
            case 1:
                if (Intrinsics.areEqual(this.currentOverlaySelectionType, OverlaySelectionType.Automatic.INSTANCE)) {
                    Map<OverlayType, Pair<Long, Long>> map = this.timeSpentMap;
                    OverlayType overlayType2 = OverlayType.ALBUM_ART;
                    map.put(overlayType2, makeNewPairWithNewTimes(map.get(overlayType2), true));
                } else {
                    Map<OverlayType, Pair<Long, Long>> map2 = this.timeSpentMap;
                    OverlayType overlayType3 = OverlayType.ALBUM_ART;
                    map2.put(overlayType3, makeNewPairWithNewTimes(map2.get(overlayType3), false));
                }
                break;
            case 2:
                if (Intrinsics.areEqual(this.currentOverlaySelectionType, OverlaySelectionType.Automatic.INSTANCE)) {
                    Map<OverlayType, Pair<Long, Long>> map3 = this.timeSpentMap;
                    OverlayType overlayType4 = OverlayType.XRAY_PARTIAL;
                    map3.put(overlayType4, makeNewPairWithNewTimes(map3.get(overlayType4), true));
                } else {
                    Map<OverlayType, Pair<Long, Long>> map4 = this.timeSpentMap;
                    OverlayType overlayType5 = OverlayType.XRAY_PARTIAL;
                    map4.put(overlayType5, makeNewPairWithNewTimes(map4.get(overlayType5), false));
                }
                break;
            case 3:
                Map<OverlayType, Pair<Long, Long>> map5 = this.timeSpentMap;
                OverlayType overlayType6 = OverlayType.XRAY_FULLSCREEN;
                map5.put(overlayType6, makeNewPairWithNewTimes(map5.get(overlayType6), false));
                break;
            case 4:
                if (Intrinsics.areEqual(this.currentOverlaySelectionType, OverlaySelectionType.Automatic.INSTANCE)) {
                    Map<OverlayType, Pair<Long, Long>> map6 = this.timeSpentMap;
                    OverlayType overlayType7 = OverlayType.LYRICS_PARTIAL;
                    map6.put(overlayType7, makeNewPairWithNewTimes(map6.get(overlayType7), true));
                } else {
                    Map<OverlayType, Pair<Long, Long>> map7 = this.timeSpentMap;
                    OverlayType overlayType8 = OverlayType.LYRICS_PARTIAL;
                    map7.put(overlayType8, makeNewPairWithNewTimes(map7.get(overlayType8), false));
                }
                break;
            case 5:
                Map<OverlayType, Pair<Long, Long>> map8 = this.timeSpentMap;
                OverlayType overlayType9 = OverlayType.LYRICS_FULLSCREEN;
                map8.put(overlayType9, makeNewPairWithNewTimes(map8.get(overlayType9), false));
                break;
            case 6:
                Map<OverlayType, Pair<Long, Long>> map9 = this.timeSpentMap;
                OverlayType overlayType10 = OverlayType.CASTING;
                map9.put(overlayType10, makeNewPairWithNewTimes(map9.get(overlayType10), false));
                break;
            case 7:
                Map<OverlayType, Pair<Long, Long>> map10 = this.timeSpentMap;
                OverlayType overlayType11 = OverlayType.PLAY_QUEUE;
                map10.put(overlayType11, makeNewPairWithNewTimes(map10.get(overlayType11), false));
                break;
        }
    }

    private final Pair<Long, Long> makeNewPairWithNewTimes(Pair<Long, Long> pair, boolean isAutomaticPreview) {
        long j = this.lastEndTime - this.lastStartTime;
        if (pair == null) {
            if (isAutomaticPreview) {
                return new Pair<>(0L, Long.valueOf(j));
            }
            return new Pair<>(Long.valueOf(j), 0L);
        }
        if (isAutomaticPreview) {
            return new Pair<>(pair.getFirst(), Long.valueOf(pair.getSecond().longValue() + j));
        }
        return new Pair<>(Long.valueOf(pair.getFirst().longValue() + j), pair.getSecond());
    }

    private final void sendMetrics() {
        MediaItemId mediaItemId;
        boolean zAreEqual = Intrinsics.areEqual(this.currentOverlaySelectionType, OverlaySelectionType.Automatic.INSTANCE);
        OverlayType overlayType = this.currentOverlay;
        if (overlayType == null || !CollectionsKt___CollectionsKt.contains(this.listOfOverlayToSendOverlayMetrics, overlayType) || this.timeSpentMap.get(this.currentOverlay) == null) {
            return;
        }
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithPageType = UiContentViewEvent.clientEventBuilder().withContentName(getContentNameFromOverlayType(this.currentOverlay, zAreEqual)).withPageType(PageType.NOW_PLAYING_STAGE.getMetricsValue());
        MediaItem mediaItem = this.mediaItem;
        String id = null;
        if (mediaItem != null && (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            id = mediaItemId.getId();
        }
        MetricsLogger.sendEvent(clientEventBuilderWithPageType.withEntityId(String.valueOf(id)).withEntityType(EntityType.TRACK).withEntityIdType(EntityIdType.ASIN).build());
        this.timeSpentMap.clear();
    }

    public final void sendDwellTimeMetrics(boolean sendStart) {
        MediaItemId mediaItemId;
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithPageType = UiContentViewEvent.clientEventBuilder().withContentName(sendStart ? ContentName.NOW_PLAYING_STAGE_START : ContentName.NOW_PLAYING_STAGE_STOP).withPageType(PageType.NOW_PLAYING_STAGE.getMetricsValue());
        MediaItem mediaItem = this.mediaItem;
        String id = null;
        if (mediaItem != null && (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            id = mediaItemId.getId();
        }
        clientEventBuilderWithPageType.withEntityId(String.valueOf(id)).withEntityType(EntityType.TRACK).withEntityIdType(EntityIdType.ASIN).build();
    }

    private final ContentName getContentNameFromOverlayType(OverlayType overlayType, boolean isAutomaticPreview) {
        int i = overlayType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[overlayType.ordinal()];
        if (i == 1) {
            if (isAutomaticPreview) {
                return ContentName.ALBUM_ART_VIEWED_PREVIEW;
            }
            return ContentName.ALBUM_ART_VIEWED_MANUAL;
        }
        if (i == 2) {
            if (isAutomaticPreview) {
                return ContentName.XRAY_VIEWED_PREVIEW;
            }
            return ContentName.XRAY_VIEWED_MANUAL;
        }
        if (i == 3) {
            return ContentName.XRAY_VIEWED_FULLSCREEN;
        }
        if (i != 4) {
            if (i != 5) {
                return null;
            }
            return ContentName.LYRICS_VIEWED_FULLSCREEN;
        }
        if (isAutomaticPreview) {
            return ContentName.LYRICS_VIEWED_PREVIEW;
        }
        return ContentName.LYRICS_VIEWED_MANUAL;
    }

    private final void reset() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        setEndTime(jUptimeMillis);
        setStartTime(jUptimeMillis);
        this.timeSpentMap.clear();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void resetContextAndLifecycle() {
        this.context = null;
        removeLifeCycleObserver();
    }

    /* JADX INFO: compiled from: StageOverlayMetricsReporter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/view/stage/StageOverlayMetricsReporter$Companion;", "", "()V", "stageOverlayMetricsReporter", "Lcom/amazon/mp3/view/stage/StageOverlayMetricsReporter;", "getStageOverlayMetricsReporter", "context", "Landroid/content/Context;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StageOverlayMetricsReporter getStageOverlayMetricsReporter(Context context, Lifecycle lifecycle) {
            if (StageOverlayMetricsReporter.stageOverlayMetricsReporter == null) {
                StageOverlayMetricsReporter.stageOverlayMetricsReporter = new StageOverlayMetricsReporter(context, lifecycle);
            }
            StageOverlayMetricsReporter stageOverlayMetricsReporter = StageOverlayMetricsReporter.stageOverlayMetricsReporter;
            if (stageOverlayMetricsReporter != null) {
                stageOverlayMetricsReporter.removeLifeCycleObserver();
            }
            StageOverlayMetricsReporter stageOverlayMetricsReporter2 = StageOverlayMetricsReporter.stageOverlayMetricsReporter;
            if (stageOverlayMetricsReporter2 != null) {
                stageOverlayMetricsReporter2.setContext(context);
            }
            StageOverlayMetricsReporter stageOverlayMetricsReporter3 = StageOverlayMetricsReporter.stageOverlayMetricsReporter;
            if (stageOverlayMetricsReporter3 != null) {
                stageOverlayMetricsReporter3.setLifecycle(lifecycle);
            }
            StageOverlayMetricsReporter stageOverlayMetricsReporter4 = StageOverlayMetricsReporter.stageOverlayMetricsReporter;
            if (stageOverlayMetricsReporter4 != null) {
                stageOverlayMetricsReporter4.setLifeCycleObserver();
            }
            StageOverlayMetricsReporter stageOverlayMetricsReporter5 = StageOverlayMetricsReporter.stageOverlayMetricsReporter;
            if (stageOverlayMetricsReporter5 != null) {
                return stageOverlayMetricsReporter5;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.view.stage.StageOverlayMetricsReporter");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeLifeCycleObserver() {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        this.lifecycle = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLifeCycleObserver() {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle == null) {
            return;
        }
        lifecycle.addObserver(this);
    }
}
