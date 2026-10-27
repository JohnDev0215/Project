package com.android.systemui.shared.system;

/* JADX INFO: loaded from: classes.dex */
public interface RemoteAnimationRunnerCompat {
    void onAnimationCancelled();

    void onAnimationStart(RemoteAnimationTargetCompat[] apps, RemoteAnimationTargetCompat[] wallpapers, Runnable finishedCallback);
}
