package com.android.systemui.shared.system;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityOptionsCompat {
    public static ActivityOptions makeSplitScreenOptions(boolean dockTopLeft) {
        return makeSplitScreenOptions(dockTopLeft, true);
    }

    public static ActivityOptions makeSplitScreenOptions(boolean z, boolean z2) {
        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
        activityOptionsMakeBasic.setLaunchWindowingMode(z2 ? 3 : 4);
        activityOptionsMakeBasic.setSplitScreenCreateMode(!z ? 1 : 0);
        return activityOptionsMakeBasic;
    }

    public static ActivityOptions makeFreeformOptions() {
        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
        activityOptionsMakeBasic.setLaunchWindowingMode(5);
        return activityOptionsMakeBasic;
    }

    public static ActivityOptions makeRemoteAnimation(RemoteAnimationAdapterCompat remoteAnimationAdapter) {
        return ActivityOptions.makeRemoteAnimation(remoteAnimationAdapter.getWrapped());
    }

    public static ActivityOptions makeCustomAnimation(Context context, int enterResId, int exitResId, final Runnable callback, final Handler callbackHandler) {
        return ActivityOptions.makeCustomAnimation(context, enterResId, exitResId, callbackHandler, new ActivityOptions.OnAnimationStartedListener() { // from class: com.android.systemui.shared.system.ActivityOptionsCompat.1
            public void onAnimationStarted() {
                Runnable runnable = callback;
                if (runnable != null) {
                    callbackHandler.post(runnable);
                }
            }
        }, null);
    }

    public static ActivityOptions setFreezeRecentTasksList(ActivityOptions opts) {
        opts.setFreezeRecentTasksReordering();
        return opts;
    }
}
