package com.android.quickstep;

import com.android.quickstep.RemoteAnimationTargets;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class RemoteAnimationTargets {
    public final RemoteAnimationTargetCompat[] apps;
    public final boolean hasRecents;
    private final CopyOnWriteArrayList<ReleaseCheck> mReleaseChecks = new CopyOnWriteArrayList<>();
    private boolean mReleased = false;
    public final int targetMode;
    public final RemoteAnimationTargetCompat[] unfilteredApps;
    public final RemoteAnimationTargetCompat[] wallpapers;

    public RemoteAnimationTargets(RemoteAnimationTargetCompat[] apps, RemoteAnimationTargetCompat[] wallpapers, int targetMode) {
        boolean z = false;
        ArrayList arrayList = new ArrayList();
        if (apps != null) {
            boolean z2 = false;
            for (RemoteAnimationTargetCompat remoteAnimationTargetCompat : apps) {
                if (remoteAnimationTargetCompat.mode == targetMode) {
                    arrayList.add(remoteAnimationTargetCompat);
                }
                z2 |= remoteAnimationTargetCompat.activityType == 3;
            }
            z = z2;
        }
        this.unfilteredApps = apps;
        this.apps = (RemoteAnimationTargetCompat[]) arrayList.toArray(new RemoteAnimationTargetCompat[arrayList.size()]);
        this.wallpapers = wallpapers;
        this.targetMode = targetMode;
        this.hasRecents = z;
    }

    public RemoteAnimationTargetCompat findTask(int taskId) {
        for (RemoteAnimationTargetCompat remoteAnimationTargetCompat : this.apps) {
            if (remoteAnimationTargetCompat.taskId == taskId) {
                return remoteAnimationTargetCompat;
            }
        }
        return null;
    }

    public boolean isAnimatingHome() {
        for (RemoteAnimationTargetCompat remoteAnimationTargetCompat : this.unfilteredApps) {
            if (remoteAnimationTargetCompat.activityType == 2) {
                return true;
            }
        }
        return false;
    }

    public void addReleaseCheck(ReleaseCheck check) {
        this.mReleaseChecks.add(check);
    }

    public void release() {
        if (this.mReleased) {
            return;
        }
        for (ReleaseCheck releaseCheck : this.mReleaseChecks) {
            if (!releaseCheck.mCanRelease) {
                releaseCheck.addOnSafeToReleaseCallback(new Runnable() { // from class: com.android.quickstep.-$$Lambda$MveWKDLsxa5bN8O1s1grtEOVbhY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.release();
                    }
                });
                return;
            }
        }
        this.mReleaseChecks.clear();
        this.mReleased = true;
        for (RemoteAnimationTargetCompat remoteAnimationTargetCompat : this.unfilteredApps) {
            remoteAnimationTargetCompat.release();
        }
        for (RemoteAnimationTargetCompat remoteAnimationTargetCompat2 : this.wallpapers) {
            remoteAnimationTargetCompat2.release();
        }
    }

    public static class ReleaseCheck {
        private Runnable mAfterApplyCallback;
        boolean mCanRelease = false;

        protected void setCanRelease(boolean canRelease) {
            Runnable runnable;
            this.mCanRelease = canRelease;
            if (!canRelease || (runnable = this.mAfterApplyCallback) == null) {
                return;
            }
            this.mAfterApplyCallback = null;
            runnable.run();
        }

        void addOnSafeToReleaseCallback(final Runnable callback) {
            if (this.mCanRelease) {
                callback.run();
                return;
            }
            final Runnable runnable = this.mAfterApplyCallback;
            if (runnable == null) {
                this.mAfterApplyCallback = callback;
            } else {
                this.mAfterApplyCallback = new Runnable() { // from class: com.android.quickstep.-$$Lambda$RemoteAnimationTargets$ReleaseCheck$EJj84HPyROP7NNsHLznnd3L7zDo
                    @Override // java.lang.Runnable
                    public final void run() {
                        RemoteAnimationTargets.ReleaseCheck.lambda$addOnSafeToReleaseCallback$0(callback, runnable);
                    }
                };
            }
        }

        static /* synthetic */ void lambda$addOnSafeToReleaseCallback$0(Runnable runnable, Runnable runnable2) {
            runnable.run();
            runnable2.run();
        }
    }
}
