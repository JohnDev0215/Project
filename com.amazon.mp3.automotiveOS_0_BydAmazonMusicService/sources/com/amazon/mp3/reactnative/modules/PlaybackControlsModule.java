package com.amazon.mp3.reactnative.modules;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.commands.PlaybackCommand;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents.NextCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents.PauseCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents.PlayCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents.PreviousCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackControls;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.PlaybackProvider;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaybackControlsModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/PlaybackControlsModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "playbackProvider", "Lcom/amazon/music/platform/providers/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/platform/providers/PlaybackProvider;", "playbackProvider$delegate", "Lkotlin/Lazy;", "getName", "", "next", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "pause", PageUriUtils.DO_PLAY_VALUE, "previous", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackControlsModule extends ReactContextBaseJavaModule {
    private static final String TAG = PlaybackControlsModule.class.getSimpleName();
    private final ReactApplicationContext context;

    /* JADX INFO: renamed from: playbackProvider$delegate, reason: from kotlin metadata */
    private final Lazy playbackProvider;

    public PlaybackControlsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
        this.playbackProvider = LazyKt__LazyJVMKt.lazy(new Function0<PlaybackProvider>() { // from class: com.amazon.mp3.reactnative.modules.PlaybackControlsModule$playbackProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlaybackProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(PlaybackProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof PlaybackProvider)) {
                    interfaceProvider = null;
                }
                return (PlaybackProvider) interfaceProvider;
            }
        });
    }

    private final PlaybackProvider getPlaybackProvider() {
        return (PlaybackProvider) this.playbackProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void play(Promise promise) {
        PlaybackControls playbackControlsControls;
        PlaybackCommand play;
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (getPlaybackProvider() == null) {
            promise.reject(TAG, "Playback provider is not available. The play command was not performed. ");
        }
        PlaybackProvider playbackProvider = getPlaybackProvider();
        if (playbackProvider != null && (playbackControlsControls = playbackProvider.controls()) != null && (play = playbackControlsControls.getPlay()) != null) {
            play.execute(new PlaybackCommandEvents().new PlayCommandEvent());
        }
        promise.resolve("Success");
    }

    @ReactMethod
    public final void pause(Promise promise) {
        PlaybackControls playbackControlsControls;
        PlaybackCommand pause;
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (getPlaybackProvider() == null) {
            promise.reject(TAG, "Playback provider is not available. The pause command was not performed. ");
        }
        PlaybackProvider playbackProvider = getPlaybackProvider();
        if (playbackProvider != null && (playbackControlsControls = playbackProvider.controls()) != null && (pause = playbackControlsControls.getPause()) != null) {
            pause.execute(new PlaybackCommandEvents().new PauseCommandEvent());
        }
        promise.resolve("Success");
    }

    @ReactMethod
    public final void next(Promise promise) {
        PlaybackControls playbackControlsControls;
        PlaybackCommand nextItem;
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (getPlaybackProvider() == null) {
            promise.reject(TAG, "Playback provider is not available. The next command was not performed. ");
        }
        PlaybackProvider playbackProvider = getPlaybackProvider();
        if (playbackProvider != null && (playbackControlsControls = playbackProvider.controls()) != null && (nextItem = playbackControlsControls.getNextItem()) != null) {
            nextItem.execute(new PlaybackCommandEvents().new NextCommandEvent());
        }
        promise.resolve("Success");
    }

    @ReactMethod
    public final void previous(Promise promise) {
        PlaybackControls playbackControlsControls;
        PlaybackCommand previousItem;
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (getPlaybackProvider() == null) {
            promise.reject(TAG, "Playback provider is not available. The previous command was not performed. ");
        }
        PlaybackProvider playbackProvider = getPlaybackProvider();
        if (playbackProvider != null && (playbackControlsControls = playbackProvider.controls()) != null && (previousItem = playbackControlsControls.getPreviousItem()) != null) {
            previousItem.execute(new PlaybackCommandEvents().new PreviousCommandEvent());
        }
        promise.resolve("Success");
    }
}
