package com.android.launcher3;

import android.util.Log;
import com.android.launcher3.LauncherAnimationRunner;
import com.android.launcher3.WrappedAnimationRunnerImpl;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;

/* JADX INFO: loaded from: classes.dex */
public class WrappedLauncherAnimationRunner<R extends WrappedAnimationRunnerImpl> extends LauncherAnimationRunner {
    private R mImpl;

    public WrappedLauncherAnimationRunner(R animationRunnerImpl, boolean startAtFrontOfQueue) {
        super(animationRunnerImpl.getHandler(), startAtFrontOfQueue);
        this.mImpl = animationRunnerImpl;
    }

    @Override // com.android.launcher3.LauncherAnimationRunner, com.android.launcher3.WrappedAnimationRunnerImpl
    public void onCreateAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, LauncherAnimationRunner.AnimationResult result) {
        Log.e("WrappedLauncherAnimationRunner", "onCreateAnimation");
        R r = this.mImpl;
        Log.e("WrappedLauncherAnimationRunner", "onCreateAnimation animationRunnerImpl = " + r);
        if (r != null) {
            r.onCreateAnimation(appTargets, wallpaperTargets, result);
        }
    }
}
