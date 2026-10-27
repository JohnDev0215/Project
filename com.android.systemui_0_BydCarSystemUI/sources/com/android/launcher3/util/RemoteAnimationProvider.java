package com.android.launcher3.util;

import android.animation.AnimatorSet;
import android.app.ActivityOptions;
import android.content.Context;
import android.os.Handler;
import androidx.appcompat.widget.ActivityChooserView;
import com.android.launcher3.LauncherAnimationRunner;
import com.android.launcher3.WrappedLauncherAnimationRunner;
import com.android.systemui.shared.system.ActivityOptionsCompat;
import com.android.systemui.shared.system.RemoteAnimationAdapterCompat;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class RemoteAnimationProvider {
    LauncherAnimationRunner mAnimationRunner;

    public abstract AnimatorSet createWindowAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets);

    ActivityOptions toActivityOptions(Handler handler, long duration, final Context context) {
        this.mAnimationRunner = new LauncherAnimationRunner(handler, false) { // from class: com.android.launcher3.util.RemoteAnimationProvider.1
            @Override // com.android.launcher3.LauncherAnimationRunner, com.android.launcher3.WrappedAnimationRunnerImpl
            public void onCreateAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, LauncherAnimationRunner.AnimationResult result) {
                result.setAnimation(RemoteAnimationProvider.this.createWindowAnimation(appTargets, wallpaperTargets), context);
            }
        };
        return ActivityOptionsCompat.makeRemoteAnimation(new RemoteAnimationAdapterCompat(new WrappedLauncherAnimationRunner(this.mAnimationRunner, false), duration, 0L));
    }

    public static RemoteAnimationTargetCompat findLowestOpaqueLayerTarget(RemoteAnimationTargetCompat[] appTargets, int mode) {
        int i;
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = -1;
        for (int length = appTargets.length - 1; length >= 0; length--) {
            RemoteAnimationTargetCompat remoteAnimationTargetCompat = appTargets[length];
            if (remoteAnimationTargetCompat.mode == mode && !remoteAnimationTargetCompat.isTranslucent && (i = remoteAnimationTargetCompat.prefixOrderIndex) < i2) {
                i3 = length;
                i2 = i;
            }
        }
        if (i3 != -1) {
            return appTargets[i3];
        }
        return null;
    }
}
