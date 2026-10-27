package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import com.android.internal.os.SomeArgs;
import com.android.systemui.shared.recents.model.ThumbnailData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TaskStackChangeListeners extends TaskStackListener {
    private static final String TAG = "TaskStackChangeListeners";
    private final Handler mHandler;
    private boolean mRegistered;
    private final List<TaskStackChangeListener> mTaskStackListeners = new ArrayList();
    private final List<TaskStackChangeListener> mTmpListeners = new ArrayList();

    public TaskStackChangeListeners(Looper looper) {
        this.mHandler = new H(looper);
    }

    public void addListener(IActivityManager am, TaskStackChangeListener listener) {
        synchronized (this.mTaskStackListeners) {
            this.mTaskStackListeners.add(listener);
        }
        if (this.mRegistered) {
            return;
        }
        try {
            ActivityTaskManager.getService().registerTaskStackListener(this);
            this.mRegistered = true;
        } catch (Exception e) {
            Log.w(TAG, "Failed to call registerTaskStackListener", e);
        }
    }

    public void removeListener(TaskStackChangeListener listener) {
        boolean zIsEmpty;
        synchronized (this.mTaskStackListeners) {
            this.mTaskStackListeners.remove(listener);
            zIsEmpty = this.mTaskStackListeners.isEmpty();
        }
        if (zIsEmpty && this.mRegistered) {
            try {
                ActivityTaskManager.getService().unregisterTaskStackListener(this);
                this.mRegistered = false;
            } catch (Exception e) {
                Log.w(TAG, "Failed to call unregisterTaskStackListener", e);
            }
        }
    }

    public void onTaskStackChanged() throws RemoteException {
        synchronized (this.mTaskStackListeners) {
            this.mTmpListeners.addAll(this.mTaskStackListeners);
        }
        for (int size = this.mTmpListeners.size() - 1; size >= 0; size--) {
            this.mTmpListeners.get(size).onTaskStackChangedBackground();
        }
        this.mTmpListeners.clear();
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessage(1);
    }

    public void onActivityPinned(String packageName, int userId, int taskId, int stackId) throws RemoteException {
        this.mHandler.removeMessages(3);
        this.mHandler.obtainMessage(3, new PinnedActivityInfo(packageName, userId, taskId, stackId)).sendToTarget();
    }

    public void onActivityUnpinned() throws RemoteException {
        this.mHandler.removeMessages(10);
        this.mHandler.sendEmptyMessage(10);
    }

    public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z, boolean z2, boolean z3) throws RemoteException {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = runningTaskInfo;
        someArgsObtain.argi1 = z ? 1 : 0;
        someArgsObtain.argi2 = z2 ? 1 : 0;
        someArgsObtain.argi3 = z3 ? 1 : 0;
        this.mHandler.removeMessages(4);
        this.mHandler.obtainMessage(4, someArgsObtain).sendToTarget();
    }

    public void onActivityForcedResizable(String packageName, int taskId, int reason) throws RemoteException {
        this.mHandler.obtainMessage(6, taskId, reason, packageName).sendToTarget();
    }

    public void onActivityDismissingDockedStack() throws RemoteException {
        this.mHandler.sendEmptyMessage(7);
    }

    public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo taskInfo, int requestedDisplayId) throws RemoteException {
        this.mHandler.obtainMessage(11, requestedDisplayId, 0, taskInfo).sendToTarget();
    }

    public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo taskInfo, int requestedDisplayId) throws RemoteException {
        this.mHandler.obtainMessage(16, requestedDisplayId, 0, taskInfo).sendToTarget();
    }

    public void onTaskProfileLocked(int taskId, int userId) throws RemoteException {
        this.mHandler.obtainMessage(8, taskId, userId).sendToTarget();
    }

    public void onTaskSnapshotChanged(int taskId, ActivityManager.TaskSnapshot snapshot) throws RemoteException {
        this.mHandler.obtainMessage(2, taskId, 0, snapshot).sendToTarget();
    }

    public void onTaskCreated(int taskId, ComponentName componentName) throws RemoteException {
        this.mHandler.obtainMessage(12, taskId, 0, componentName).sendToTarget();
    }

    public void onTaskRemoved(int taskId) throws RemoteException {
        this.mHandler.obtainMessage(13, taskId, 0).sendToTarget();
    }

    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        this.mHandler.obtainMessage(14, taskInfo).sendToTarget();
    }

    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        this.mHandler.obtainMessage(18, taskInfo).sendToTarget();
    }

    public void onActivityRequestedOrientationChanged(int taskId, int requestedOrientation) throws RemoteException {
        this.mHandler.obtainMessage(15, taskId, requestedOrientation).sendToTarget();
    }

    public void onSizeCompatModeActivityChanged(int displayId, IBinder activityToken) throws RemoteException {
        this.mHandler.obtainMessage(17, displayId, 0, activityToken).sendToTarget();
    }

    public void onSingleTaskDisplayDrawn(int displayId) throws RemoteException {
        this.mHandler.obtainMessage(19, displayId, 0).sendToTarget();
    }

    public void onSingleTaskDisplayEmpty(int displayId) throws RemoteException {
        this.mHandler.obtainMessage(22, displayId, 0).sendToTarget();
    }

    public void onTaskDisplayChanged(int taskId, int newDisplayId) throws RemoteException {
        this.mHandler.obtainMessage(20, taskId, newDisplayId).sendToTarget();
    }

    public void onRecentTaskListUpdated() throws RemoteException {
        this.mHandler.obtainMessage(21).sendToTarget();
    }

    public void onRecentTaskListFrozenChanged(boolean z) {
        this.mHandler.obtainMessage(23, z ? 1 : 0, 0).sendToTarget();
    }

    public void onTaskFocusChanged(int i, boolean z) {
        super.onTaskFocusChanged(i, z);
        this.mHandler.obtainMessage(26, i, z ? 1 : 0).sendToTarget();
    }

    public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo taskInfo) {
        this.mHandler.obtainMessage(24, taskInfo).sendToTarget();
    }

    public void onActivityRotation(int displayId) {
        this.mHandler.obtainMessage(25, displayId, 0).sendToTarget();
    }

    private final class H extends Handler {
        private static final int ON_ACTIVITY_DISMISSING_DOCKED_STACK = 7;
        private static final int ON_ACTIVITY_FORCED_RESIZABLE = 6;
        private static final int ON_ACTIVITY_LAUNCH_ON_SECONDARY_DISPLAY_FAILED = 11;
        private static final int ON_ACTIVITY_LAUNCH_ON_SECONDARY_DISPLAY_REROUTED = 16;
        private static final int ON_ACTIVITY_PINNED = 3;
        private static final int ON_ACTIVITY_REQUESTED_ORIENTATION_CHANGE = 15;
        private static final int ON_ACTIVITY_RESTART_ATTEMPT = 4;
        private static final int ON_ACTIVITY_ROTATION = 25;
        private static final int ON_ACTIVITY_UNPINNED = 10;
        private static final int ON_BACK_PRESSED_ON_TASK_ROOT = 18;
        private static final int ON_SINGLE_TASK_DISPLAY_DRAWN = 19;
        private static final int ON_SINGLE_TASK_DISPLAY_EMPTY = 22;
        private static final int ON_SIZE_COMPAT_MODE_ACTIVITY_CHANGED = 17;
        private static final int ON_TASK_CREATED = 12;
        private static final int ON_TASK_DESCRIPTION_CHANGED = 24;
        private static final int ON_TASK_DISPLAY_CHANGED = 20;
        private static final int ON_TASK_FOCUS_CHANGED = 26;
        private static final int ON_TASK_LIST_FROZEN_UNFROZEN = 23;
        private static final int ON_TASK_LIST_UPDATED = 21;
        private static final int ON_TASK_MOVED_TO_FRONT = 14;
        private static final int ON_TASK_PROFILE_LOCKED = 8;
        private static final int ON_TASK_REMOVED = 13;
        private static final int ON_TASK_SNAPSHOT_CHANGED = 2;
        private static final int ON_TASK_STACK_CHANGED = 1;

        public H(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            synchronized (TaskStackChangeListeners.this.mTaskStackListeners) {
                switch (msg.what) {
                    case 1:
                        Trace.beginSection("onTaskStackChanged");
                        for (int size = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size >= 0; size--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size)).onTaskStackChanged();
                        }
                        Trace.endSection();
                        break;
                    case 2:
                        Trace.beginSection("onTaskSnapshotChanged");
                        for (int size2 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size2 >= 0; size2--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size2)).onTaskSnapshotChanged(msg.arg1, new ThumbnailData((ActivityManager.TaskSnapshot) msg.obj));
                        }
                        Trace.endSection();
                        break;
                    case 3:
                        PinnedActivityInfo pinnedActivityInfo = (PinnedActivityInfo) msg.obj;
                        for (int size3 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size3 >= 0; size3--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size3)).onActivityPinned(pinnedActivityInfo.mPackageName, pinnedActivityInfo.mUserId, pinnedActivityInfo.mTaskId, pinnedActivityInfo.mStackId);
                        }
                        break;
                    case 4:
                        SomeArgs someArgs = (SomeArgs) msg.obj;
                        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) someArgs.arg1;
                        boolean z = someArgs.argi1 != 0;
                        boolean z2 = someArgs.argi2 != 0;
                        boolean z3 = someArgs.argi3 != 0;
                        for (int size4 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size4 >= 0; size4--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size4)).onActivityRestartAttempt(runningTaskInfo, z, z2, z3);
                        }
                        break;
                    case 6:
                        for (int size5 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size5 >= 0; size5--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size5)).onActivityForcedResizable((String) msg.obj, msg.arg1, msg.arg2);
                        }
                        break;
                    case 7:
                        for (int size6 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size6 >= 0; size6--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size6)).onActivityDismissingDockedStack();
                        }
                        break;
                    case 8:
                        for (int size7 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size7 >= 0; size7--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size7)).onTaskProfileLocked(msg.arg1, msg.arg2);
                        }
                        break;
                    case 10:
                        for (int size8 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size8 >= 0; size8--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size8)).onActivityUnpinned();
                        }
                        break;
                    case 11:
                        ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) msg.obj;
                        for (int size9 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size9 >= 0; size9--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size9)).onActivityLaunchOnSecondaryDisplayFailed(runningTaskInfo2);
                        }
                        break;
                    case 12:
                        for (int size10 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size10 >= 0; size10--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size10)).onTaskCreated(msg.arg1, (ComponentName) msg.obj);
                        }
                        break;
                    case 13:
                        for (int size11 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size11 >= 0; size11--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size11)).onTaskRemoved(msg.arg1);
                        }
                        break;
                    case 14:
                        ActivityManager.RunningTaskInfo runningTaskInfo3 = (ActivityManager.RunningTaskInfo) msg.obj;
                        for (int size12 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size12 >= 0; size12--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size12)).onTaskMovedToFront(runningTaskInfo3);
                        }
                        break;
                    case 15:
                        for (int size13 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size13 >= 0; size13--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size13)).onActivityRequestedOrientationChanged(msg.arg1, msg.arg2);
                        }
                        break;
                    case 16:
                        ActivityManager.RunningTaskInfo runningTaskInfo4 = (ActivityManager.RunningTaskInfo) msg.obj;
                        for (int size14 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size14 >= 0; size14--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size14)).onActivityLaunchOnSecondaryDisplayRerouted(runningTaskInfo4);
                        }
                        break;
                    case 17:
                        for (int size15 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size15 >= 0; size15--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size15)).onSizeCompatModeActivityChanged(msg.arg1, (IBinder) msg.obj);
                        }
                        break;
                    case 18:
                        for (int size16 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size16 >= 0; size16--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size16)).onBackPressedOnTaskRoot((ActivityManager.RunningTaskInfo) msg.obj);
                        }
                        break;
                    case 19:
                        for (int size17 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size17 >= 0; size17--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size17)).onSingleTaskDisplayDrawn(msg.arg1);
                        }
                        break;
                    case 20:
                        for (int size18 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size18 >= 0; size18--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size18)).onTaskDisplayChanged(msg.arg1, msg.arg2);
                        }
                        break;
                    case 21:
                        for (int size19 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size19 >= 0; size19--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size19)).onRecentTaskListUpdated();
                        }
                        break;
                    case 22:
                        for (int size20 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size20 >= 0; size20--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size20)).onSingleTaskDisplayEmpty(msg.arg1);
                        }
                        break;
                    case 23:
                        for (int size21 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size21 >= 0; size21--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size21)).onRecentTaskListFrozenChanged(msg.arg1 != 0);
                        }
                        break;
                    case 24:
                        ActivityManager.RunningTaskInfo runningTaskInfo5 = (ActivityManager.RunningTaskInfo) msg.obj;
                        for (int size22 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size22 >= 0; size22--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size22)).onTaskDescriptionChanged(runningTaskInfo5);
                        }
                        break;
                    case 25:
                        for (int size23 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size23 >= 0; size23--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size23)).onActivityRotation(msg.arg1);
                        }
                        break;
                    case 26:
                        for (int size24 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size24 >= 0; size24--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size24)).onTaskFocusChanged(msg.arg1, msg.arg2 != 0);
                        }
                        break;
                }
            }
            if (msg.obj instanceof SomeArgs) {
                ((SomeArgs) msg.obj).recycle();
            }
        }
    }

    private static class PinnedActivityInfo {
        final String mPackageName;
        final int mStackId;
        final int mTaskId;
        final int mUserId;

        PinnedActivityInfo(String packageName, int userId, int taskId, int stackId) {
            this.mPackageName = packageName;
            this.mUserId = userId;
            this.mTaskId = taskId;
            this.mStackId = stackId;
        }
    }
}
