package com.amazon.music.skyfire.ui.skyfire;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableNextMethod;
import ExternalActionInterface.v1_0.ImmutablePauseMethod;
import ExternalActionInterface.v1_0.ImmutablePlayMethod;
import ExternalActionInterface.v1_0.ImmutablePreviousMethod;
import ExternalActionInterface.v1_0.ImmutableSetRepeatStateMethod;
import ExternalActionInterface.v1_0.ImmutableSetShuffleStateMethod;
import ExternalActionInterface.v1_0.ImmutableSkipBackwardMethod;
import ExternalActionInterface.v1_0.ImmutableSkipForwardMethod;
import ExternalActionInterface.v1_0.ImmutableStopMethod;
import ExternalActionInterface.v1_0.ImmutableUpdatePlaybackSpeedMethod;
import ExternalActionInterface.v1_0.ImmutableUpdateVolumeMethod;
import ExternalActionInterface.v1_0.NextMethod;
import ExternalActionInterface.v1_0.PauseMethod;
import ExternalActionInterface.v1_0.PlayMethod;
import ExternalActionInterface.v1_0.PreviousMethod;
import ExternalActionInterface.v1_0.SetRepeatStateMethod;
import ExternalActionInterface.v1_0.SetShuffleStateMethod;
import ExternalActionInterface.v1_0.SkipBackwardMethod;
import ExternalActionInterface.v1_0.SkipForwardMethod;
import ExternalActionInterface.v1_0.StopMethod;
import ExternalActionInterface.v1_0.UpdatePlaybackSpeedMethod;
import ExternalActionInterface.v1_0.UpdateVolumeMethod;
import android.content.Context;
import android.media.AudioManager;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: ExternalActionMethodsRegistry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/ExternalActionMethodsRegistry;", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "LCoreInterface/v1_0/Method;", "applicationContext", "Landroid/content/Context;", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "(Landroid/content/Context;Lcom/amazon/music/skyfire/SkyFireApplication;Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;)V", "executeMethod", "", "method", "ownerId", "", "onMethodExecutedListener", "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "registerMethods", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExternalActionMethodsRegistry implements MethodCallback<Method> {
    private final Context applicationContext;
    private final PlaybackProvider playbackProvider;
    private final SkyFireApplication skyFireApplication;

    public ExternalActionMethodsRegistry(Context applicationContext, SkyFireApplication skyFireApplication, PlaybackProvider playbackProvider) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(skyFireApplication, "skyFireApplication");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        this.applicationContext = applicationContext;
        this.skyFireApplication = skyFireApplication;
        this.playbackProvider = playbackProvider;
    }

    public final void registerMethods() {
        HashSet hashSet = new HashSet();
        String canonicalName = ImmutablePlayMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName);
        hashSet.add(canonicalName);
        String canonicalName2 = ImmutablePauseMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName2);
        hashSet.add(canonicalName2);
        String canonicalName3 = ImmutableStopMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName3);
        hashSet.add(canonicalName3);
        String canonicalName4 = ImmutableNextMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName4);
        hashSet.add(canonicalName4);
        String canonicalName5 = ImmutablePreviousMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName5);
        hashSet.add(canonicalName5);
        String canonicalName6 = ImmutableSkipForwardMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName6);
        hashSet.add(canonicalName6);
        String canonicalName7 = ImmutableSkipBackwardMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName7);
        hashSet.add(canonicalName7);
        String canonicalName8 = ImmutableSetRepeatStateMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName8);
        hashSet.add(canonicalName8);
        String canonicalName9 = ImmutableSetShuffleStateMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName9);
        hashSet.add(canonicalName9);
        String canonicalName10 = ImmutableUpdateVolumeMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName10);
        hashSet.add(canonicalName10);
        String canonicalName11 = ImmutableUpdatePlaybackSpeedMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName11);
        hashSet.add(canonicalName11);
        this.skyFireApplication.registerMethods(SetsKt__SetsKt.emptySet(), hashSet, this);
    }

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public void executeMethod(Method method, String ownerId, OnMethodExecutedListener<Method> onMethodExecutedListener) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(onMethodExecutedListener, "onMethodExecutedListener");
        if (method instanceof PauseMethod) {
            this.playbackProvider.pause();
        } else if (method instanceof PlayMethod) {
            this.playbackProvider.resume();
        } else if (method instanceof StopMethod) {
            this.playbackProvider.stopPlaybackAndClearQueue();
        } else if (method instanceof NextMethod) {
            this.playbackProvider.nextTrack();
        } else if (method instanceof PreviousMethod) {
            this.playbackProvider.previousTrack();
        } else if (method instanceof SkipForwardMethod) {
            this.playbackProvider.skipForward();
        } else if (method instanceof SkipBackwardMethod) {
            this.playbackProvider.skipBackward();
        } else if (method instanceof SetRepeatStateMethod) {
            PlaybackProvider playbackProvider = this.playbackProvider;
            SetRepeatStateMethod.State state = ((SetRepeatStateMethod) method).state();
            Intrinsics.checkNotNullExpressionValue(state, "method.state()");
            playbackProvider.changeRepeatMode(state);
        } else if (method instanceof SetShuffleStateMethod) {
            PlaybackProvider playbackProvider2 = this.playbackProvider;
            SetShuffleStateMethod.State state2 = ((SetShuffleStateMethod) method).state();
            Intrinsics.checkNotNullExpressionValue(state2, "method.state()");
            playbackProvider2.changeShuffleMode(state2);
        } else if (method instanceof UpdateVolumeMethod) {
            double volume = AndroidClientInformationCache.INSTANCE.getVolume();
            Object systemService = this.applicationContext.getSystemService((Class<Object>) AudioManager.class);
            Intrinsics.checkNotNullExpressionValue(systemService, "applicationContext.getSy…AudioManager::class.java)");
            AudioManager audioManager = (AudioManager) systemService;
            audioManager.setStreamVolume(3, MathKt__MathJVMKt.roundToInt(volume * ((double) audioManager.getStreamMaxVolume(3))), 0);
        } else if (method instanceof UpdatePlaybackSpeedMethod) {
            this.playbackProvider.setPlaybackSpeed((float) AndroidClientInformationCache.INSTANCE.getPlaybackSpeed());
        }
        onMethodExecutedListener.onMethodExecuted(method);
    }
}
