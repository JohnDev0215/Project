package com.android.systemui.stackdivider;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.SurfaceSession;
import android.window.TaskOrganizer;
import androidx.appcompat.widget.ActivityChooserView;
import com.android.systemui.stackdivider.view.SplitScreenEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
class SplitScreenTaskOrganizer extends TaskOrganizer {
    private static final boolean DEBUG = true;
    private static final String TAG = "SplitScreenTaskOrg";
    final Divider mDivider;
    ActivityManager.RunningTaskInfo mPrimary;
    SurfaceControl mPrimaryDim;
    SurfaceControl mPrimarySurface;
    ActivityManager.RunningTaskInfo mSecondary;
    SurfaceControl mSecondaryDim;
    SurfaceControl mSecondarySurface;
    Rect mHomeBounds = new Rect();
    private boolean mSplitScreenSupported = false;
    final SurfaceSession mSurfaceSession = new SurfaceSession();

    SplitScreenTaskOrganizer(Divider divider) {
        this.mDivider = divider;
    }

    void init() throws RemoteException {
        registerOrganizer(3);
        registerOrganizer(4);
        synchronized (this) {
            try {
                try {
                    this.mPrimary = TaskOrganizer.createRootTask(0, 3);
                    this.mSecondary = TaskOrganizer.createRootTask(0, 4);
                } catch (Exception e) {
                    unregisterOrganizer();
                    throw e;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    boolean isSplitScreenSupported() {
        return this.mSplitScreenSupported;
    }

    SurfaceControl.Transaction getTransaction() {
        return this.mDivider.mTransactionPool.acquire();
    }

    void releaseTransaction(SurfaceControl.Transaction t) {
        this.mDivider.mTransactionPool.release(t);
    }

    public void onTaskAppeared(ActivityManager.RunningTaskInfo taskInfo, SurfaceControl leash) {
        Log.w(TAG, "onTaskAppeared taskInfo : " + taskInfo);
        synchronized (this) {
            if (this.mPrimary != null && this.mSecondary != null) {
                if (taskInfo.token.equals(this.mPrimary.token)) {
                    this.mPrimarySurface = leash;
                } else if (taskInfo.token.equals(this.mSecondary.token)) {
                    this.mSecondarySurface = leash;
                }
                if (!this.mSplitScreenSupported && this.mPrimarySurface != null && this.mSecondarySurface != null) {
                    this.mSplitScreenSupported = true;
                    this.mPrimaryDim = new SurfaceControl.Builder(this.mSurfaceSession).setParent(this.mPrimarySurface).setColorLayer().setName("Primary Divider Dim").setCallsite("SplitScreenTaskOrganizer.onTaskAppeared").build();
                    this.mSecondaryDim = new SurfaceControl.Builder(this.mSurfaceSession).setParent(this.mSecondarySurface).setColorLayer().setName("Secondary Divider Dim").setCallsite("SplitScreenTaskOrganizer.onTaskAppeared").build();
                    SurfaceControl.Transaction transaction = getTransaction();
                    transaction.setLayer(this.mPrimaryDim, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    transaction.setColor(this.mPrimaryDim, new float[]{0.0f, 0.0f, 0.0f});
                    transaction.setLayer(this.mSecondaryDim, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    transaction.setColor(this.mSecondaryDim, new float[]{0.0f, 0.0f, 0.0f});
                    transaction.apply();
                    releaseTransaction(transaction);
                }
                return;
            }
            Log.w(TAG, "Received onTaskAppeared before creating root tasks " + taskInfo);
        }
    }

    public void onTaskVanished(ActivityManager.RunningTaskInfo taskInfo) {
        Log.w(TAG, "onTaskVanished taskInfo : " + taskInfo);
        synchronized (this) {
            boolean z = true;
            boolean z2 = this.mPrimary != null && taskInfo.token.equals(this.mPrimary.token);
            if (this.mSecondary == null || !taskInfo.token.equals(this.mSecondary.token)) {
                z = false;
            }
            if (this.mSplitScreenSupported && (z2 || z)) {
                this.mSplitScreenSupported = false;
                SurfaceControl.Transaction transaction = getTransaction();
                transaction.remove(this.mPrimaryDim);
                transaction.remove(this.mSecondaryDim);
                transaction.remove(this.mPrimarySurface);
                transaction.remove(this.mSecondarySurface);
                transaction.apply();
                releaseTransaction(transaction);
                this.mDivider.onTaskVanished();
            }
        }
    }

    public void onTaskInfoChanged(final ActivityManager.RunningTaskInfo taskInfo) {
        Log.w(TAG, "onTaskInfoChanged taskInfo : " + taskInfo);
        if (taskInfo.displayId != 0) {
            return;
        }
        this.mDivider.getHandler().post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenTaskOrganizer$04wSNB9jpR95BdN3qKBN7nkJJwI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onTaskInfoChanged$0$SplitScreenTaskOrganizer(taskInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleTaskInfoChanged, reason: merged with bridge method [inline-methods] */
    public void lambda$onTaskInfoChanged$0$SplitScreenTaskOrganizer(ActivityManager.RunningTaskInfo info) {
        if (!this.mSplitScreenSupported) {
            Log.e(TAG, "Got handleTaskInfoChanged when not initialized: " + info);
            return;
        }
        boolean z = false;
        boolean z2 = this.mSecondary.topActivityType == 2 || (this.mSecondary.topActivityType == 3 && this.mDivider.isHomeStackResizable());
        boolean z3 = this.mPrimary.topActivityType == 0;
        boolean z4 = this.mSecondary.topActivityType == 0;
        if (info.token.asBinder() == this.mPrimary.token.asBinder()) {
            this.mPrimary = info;
        } else if (info.token.asBinder() == this.mSecondary.token.asBinder()) {
            this.mSecondary = info;
        }
        boolean z5 = this.mPrimary.topActivityType == 0;
        boolean z6 = this.mSecondary.topActivityType == 0;
        if (this.mSecondary.topActivityType == 2 || (this.mSecondary.topActivityType == 3 && this.mDivider.isHomeStackResizable())) {
            z = true;
        }
        Log.d(TAG, "handleTaskInfoChanged, info: " + this.mPrimary + "  " + this.mSecondary);
        Log.w(TAG, "handleTaskInfoChanged primaryIsEmpty : " + z5 + ", primaryWasEmpty : " + z3 + ", secondaryIsEmpty : " + z6 + ", secondaryWasEmpty : " + z4);
        if (z5 == z3 && z4 == z6 && z2 == z) {
            Log.d(TAG, "No relevant changes");
            return;
        }
        if (!z5 && !z6) {
            if (z) {
                Log.d(TAG, "   ensureMinimizedSplit");
                this.mDivider.ensureMinimizedSplit();
                return;
            } else {
                Log.d(TAG, "   ensureNormalSplit");
                this.mDivider.ensureNormalSplit();
                return;
            }
        }
        Log.d(TAG, " at-least one split empty " + this.mPrimary.topActivityType + "  " + this.mSecondary.topActivityType);
        if (this.mDivider.isDividerVisible()) {
            Log.d(TAG, "    was in split, so this means leave it " + this.mPrimary.topActivityType + "  " + this.mSecondary.topActivityType);
            this.mDivider.startDismissSplit();
            EventBus.getDefault().post(new SplitScreenEvent(3));
            EventBus.getDefault().post(new SplitScreenEvent(4));
            return;
        }
        if (!z5 && z3 && z4) {
            Log.d(TAG, "   was not in split, but primary is populated, so enter it");
            this.mDivider.startEnterSplit();
        }
    }
}
