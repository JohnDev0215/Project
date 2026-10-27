package com.android.launcher3;

import android.os.Handler;
import com.android.launcher3.LauncherAnimationRunner;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;

/* JADX INFO: loaded from: classes.dex */
public interface WrappedAnimationRunnerImpl {
    Handler getHandler();

    void onCreateAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, LauncherAnimationRunner.AnimationResult result);
}
