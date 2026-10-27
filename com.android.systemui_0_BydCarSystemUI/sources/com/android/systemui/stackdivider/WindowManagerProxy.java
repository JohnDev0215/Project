package com.android.systemui.stackdivider;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.WindowManagerGlobal;
import android.window.TaskOrganizer;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.systemui.TransactionPool;
import com.android.systemui.stackdivider.SyncTransactionQueue;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class WindowManagerProxy {
    private static final String TAG = "WindowManagerProxy";
    private final SyncTransactionQueue mSyncTransactionQueue;
    private static final int[] HOME_AND_RECENTS = {2, 3};
    private static final int[] CONTROLLED_ACTIVITY_TYPES = {1, 2, 3, 0};
    private static final int[] CONTROLLED_WINDOWING_MODES = {1, 4, 0};
    private final Rect mDockedRect = new Rect();
    private final Rect mTmpRect1 = new Rect();
    private final Rect mTouchableRegion = new Rect();
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private final Runnable mSetTouchableRegionRunnable = new Runnable() { // from class: com.android.systemui.stackdivider.WindowManagerProxy.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                synchronized (WindowManagerProxy.this.mDockedRect) {
                    WindowManagerProxy.this.mTmpRect1.set(WindowManagerProxy.this.mTouchableRegion);
                }
                WindowManagerGlobal.getWindowManagerService().setDockedStackDividerTouchRegion(WindowManagerProxy.this.mTmpRect1);
            } catch (RemoteException e) {
                Log.w(WindowManagerProxy.TAG, "Failed to set touchable region: " + e);
            }
        }
    };
    private final DividerUtils mDividerUtils = DividerUtils.getInstance();

    WindowManagerProxy(TransactionPool transactionPool, Handler handler) {
        this.mSyncTransactionQueue = new SyncTransactionQueue(transactionPool, handler);
    }

    void dismissOrMaximizeDocked(final SplitScreenTaskOrganizer tiles, final SplitDisplayLayout layout, final boolean dismissOrMaximize) {
        this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$WindowManagerProxy$XOpM2xLVMWgZQkxv_VBy9jm6maI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dismissOrMaximizeDocked$0$WindowManagerProxy(tiles, layout, dismissOrMaximize);
            }
        });
    }

    public void setResizing(final boolean resizing) {
        this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.stackdivider.WindowManagerProxy.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityTaskManager.getService().setSplitScreenResizing(resizing);
                } catch (RemoteException e) {
                    Log.w(WindowManagerProxy.TAG, "Error calling setDockedStackResizing: " + e);
                }
            }
        });
    }

    public void setTouchRegion(Rect region) {
        synchronized (this.mDockedRect) {
            this.mTouchableRegion.set(region);
        }
        this.mExecutor.execute(this.mSetTouchableRegionRunnable);
    }

    void applyResizeSplits(int position, SplitDisplayLayout splitLayout) {
        BydLogHelper.logD("bounds applyResizeSplits splitLayout : " + splitLayout.toString());
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        splitLayout.resizeSplits(position, windowContainerTransaction);
        applySyncTransaction(windowContainerTransaction);
    }

    private static boolean getHomeAndRecentsTasks(List<ActivityManager.RunningTaskInfo> out, WindowContainerToken parent) {
        List childTasks;
        if (parent == null) {
            childTasks = TaskOrganizer.getRootTasks(0, HOME_AND_RECENTS);
        } else {
            childTasks = TaskOrganizer.getChildTasks(parent, HOME_AND_RECENTS);
        }
        int size = childTasks.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) childTasks.get(i);
            out.add(runningTaskInfo);
            if (runningTaskInfo.topActivityType == 2) {
                z = runningTaskInfo.isResizeable;
            }
        }
        return z;
    }

    static boolean applyHomeTasksMinimized(SplitDisplayLayout layout, WindowContainerToken parent, WindowContainerTransaction wct) {
        Rect rect;
        ArrayList arrayList = new ArrayList();
        boolean homeAndRecentsTasks = getHomeAndRecentsTasks(arrayList, parent);
        BydLogHelper.logD("bounds applyHomeTasksMinimized 1 isHomeResizable : " + homeAndRecentsTasks);
        if (homeAndRecentsTasks) {
            BydLogHelper.logD("bounds applyHomeTasksMinimized 1 layout : " + layout);
            BydLogHelper.logD("bounds applyHomeTasksMinimized 1 layout calcResizableMinimizedHomeStackBounds : " + layout.calcResizableMinimizedHomeStackBounds());
            rect = layout.calcResizableMinimizedHomeStackBounds();
        } else {
            boolean z = false;
            rect = new Rect(0, 0, 0, 0);
            int size = arrayList.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                if (((ActivityManager.RunningTaskInfo) arrayList.get(size)).topActivityType == 2) {
                    int i = ((ActivityManager.RunningTaskInfo) arrayList.get(size)).configuration.orientation;
                    boolean zIsLandscape = layout.mDisplayLayout.isLandscape();
                    if (i == 2 || (i == 0 && zIsLandscape)) {
                        z = true;
                    }
                    rect.right = z == zIsLandscape ? layout.mDisplayLayout.width() : layout.mDisplayLayout.height();
                    rect.bottom = z == zIsLandscape ? layout.mDisplayLayout.height() : layout.mDisplayLayout.width();
                } else {
                    size--;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            if (!homeAndRecentsTasks) {
                if (((ActivityManager.RunningTaskInfo) arrayList.get(size2)).topActivityType != 3) {
                    wct.setWindowingMode(((ActivityManager.RunningTaskInfo) arrayList.get(size2)).token, 1);
                    BydLogHelper.logD("bounds applyHomeTasksMinimized 1 homeBounds : " + rect);
                    wct.setBounds(((ActivityManager.RunningTaskInfo) arrayList.get(size2)).token, rect);
                }
            } else {
                BydLogHelper.logD("bounds applyHomeTasksMinimized 1 homeBounds : " + rect);
                wct.setBounds(((ActivityManager.RunningTaskInfo) arrayList.get(size2)).token, rect);
            }
        }
        layout.mTiles.mHomeBounds.set(rect);
        return homeAndRecentsTasks;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean applyEnterSplit(com.android.systemui.stackdivider.SplitScreenTaskOrganizer r11, com.android.systemui.stackdivider.SplitDisplayLayout r12) {
        /*
            Method dump skipped, instruction units count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.WindowManagerProxy.applyEnterSplit(com.android.systemui.stackdivider.SplitScreenTaskOrganizer, com.android.systemui.stackdivider.SplitDisplayLayout):boolean");
    }

    static boolean isHomeOrRecentTask(ActivityManager.RunningTaskInfo ti) {
        int activityType = ti.configuration.windowConfiguration.getActivityType();
        return activityType == 2 || activityType == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: applyDismissSplit, reason: merged with bridge method [inline-methods] */
    public void lambda$dismissOrMaximizeDocked$0$WindowManagerProxy(final SplitScreenTaskOrganizer tiles, SplitDisplayLayout layout, boolean dismissOrMaximize) {
        int i;
        int i2;
        TaskOrganizer.setLaunchRoot(0, (WindowContainerToken) null);
        List childTasks = TaskOrganizer.getChildTasks(tiles.mPrimary.token, (int[]) null);
        List childTasks2 = TaskOrganizer.getChildTasks(tiles.mSecondary.token, (int[]) null);
        List rootTasks = TaskOrganizer.getRootTasks(0, HOME_AND_RECENTS);
        rootTasks.removeIf(new Predicate() { // from class: com.android.systemui.stackdivider.-$$Lambda$WindowManagerProxy$3Zkqrd1w4Aopwb5p4IzCKzERfFs
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WindowManagerProxy.lambda$applyDismissSplit$1(tiles, (ActivityManager.RunningTaskInfo) obj);
            }
        });
        if (childTasks.isEmpty() && childTasks2.isEmpty() && rootTasks.isEmpty()) {
            return;
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        if (dismissOrMaximize) {
            for (int size = childTasks.size() - 1; size >= 0; size--) {
                BydLogHelper.logD("applyDismissSplit 1 reparent : " + childTasks.get(size));
                windowContainerTransaction.reparent(((ActivityManager.RunningTaskInfo) childTasks.get(size)).token, (WindowContainerToken) null, true);
            }
            boolean z = false;
            for (int size2 = childTasks2.size() - 1; size2 >= 0; size2--) {
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) childTasks2.get(size2);
                BydLogHelper.logD("applyDismissSplit 2 reparent : " + runningTaskInfo);
                windowContainerTransaction.reparent(runningTaskInfo.token, (WindowContainerToken) null, true);
                if (isHomeOrRecentTask(runningTaskInfo)) {
                    windowContainerTransaction.setBounds(runningTaskInfo.token, (Rect) null);
                    windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 0);
                    if (size2 == 0) {
                        z = true;
                    }
                }
            }
            if (z) {
                boolean zIsLandscape = layout.mDisplayLayout.isLandscape();
                if (zIsLandscape) {
                    i = layout.mSecondary.left - tiles.mHomeBounds.left;
                } else {
                    i = layout.mSecondary.left;
                }
                if (zIsLandscape) {
                    i2 = layout.mSecondary.top;
                } else {
                    i2 = layout.mSecondary.top - tiles.mHomeBounds.top;
                }
                SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                transaction.setPosition(tiles.mSecondarySurface, i, i2);
                Rect rect = new Rect(0, 0, layout.mDisplayLayout.width(), layout.mDisplayLayout.height());
                rect.offset(-i, -i2);
                transaction.setWindowCrop(tiles.mSecondarySurface, rect);
                windowContainerTransaction.setBoundsChangeTransaction(tiles.mSecondary.token, transaction);
            }
        } else {
            for (int size3 = childTasks2.size() - 1; size3 >= 0; size3--) {
                if (!isHomeOrRecentTask((ActivityManager.RunningTaskInfo) childTasks2.get(size3))) {
                    BydLogHelper.logD("applyDismissSplit 3 reparent : " + childTasks2.get(size3));
                    windowContainerTransaction.reparent(((ActivityManager.RunningTaskInfo) childTasks2.get(size3)).token, (WindowContainerToken) null, false);
                }
            }
            for (int size4 = childTasks2.size() - 1; size4 >= 0; size4--) {
                ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) childTasks2.get(size4);
                if (isHomeOrRecentTask(runningTaskInfo2)) {
                    BydLogHelper.logD("applyDismissSplit 4 reparent : " + runningTaskInfo2);
                    windowContainerTransaction.reparent(runningTaskInfo2.token, (WindowContainerToken) null, true);
                    windowContainerTransaction.setBounds(runningTaskInfo2.token, (Rect) null);
                    windowContainerTransaction.setWindowingMode(runningTaskInfo2.token, 0);
                }
            }
            for (int size5 = childTasks.size() - 1; size5 >= 0; size5--) {
                BydLogHelper.logD("applyDismissSplit 5 reparent : " + childTasks.get(size5));
                windowContainerTransaction.reparent(((ActivityManager.RunningTaskInfo) childTasks.get(size5)).token, (WindowContainerToken) null, true);
            }
        }
        for (int size6 = rootTasks.size() - 1; size6 >= 0; size6--) {
            windowContainerTransaction.setBounds(((ActivityManager.RunningTaskInfo) rootTasks.get(size6)).token, (Rect) null);
            windowContainerTransaction.setWindowingMode(((ActivityManager.RunningTaskInfo) rootTasks.get(size6)).token, 0);
        }
        windowContainerTransaction.setFocusable(tiles.mPrimary.token, true);
        BydLogHelper.logD("bounds applyDismissSplit wct : " + windowContainerTransaction);
        applySyncTransaction(windowContainerTransaction);
    }

    static /* synthetic */ boolean lambda$applyDismissSplit$1(SplitScreenTaskOrganizer splitScreenTaskOrganizer, ActivityManager.RunningTaskInfo runningTaskInfo) {
        return runningTaskInfo.token.equals(splitScreenTaskOrganizer.mSecondary.token) || runningTaskInfo.token.equals(splitScreenTaskOrganizer.mPrimary.token);
    }

    void applySyncTransaction(WindowContainerTransaction wct) {
        BydLogHelper.logD("bounds applySyncTransaction wct : " + wct);
        this.mSyncTransactionQueue.queue(wct);
    }

    boolean queueSyncTransactionIfWaiting(WindowContainerTransaction wct) {
        return this.mSyncTransactionQueue.queueIfWaiting(wct);
    }

    void runInSync(SyncTransactionQueue.TransactionRunnable runnable) {
        this.mSyncTransactionQueue.runInSync(runnable);
    }
}
