package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.AppGlobals;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Constants;
import com.android.systemui.shared.recents.model.ThumbnailData;
import com.byd.card.forbidden.ForbiddenFullActivity;
import com.byd.card.forbidden.ForbiddenSplitPrimaryActivity;
import com.byd.card.forbidden.ForbiddenSplitSecondaryActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class ActivityManagerWrapper {
    private static final String TAG = "ActivityManagerWrapper";
    private static final ActivityManagerWrapper sInstance = new ActivityManagerWrapper();
    private final PackageManager mPackageManager = AppGlobals.getInitialApplication().getPackageManager();
    private final BackgroundExecutor mBackgroundExecutor = BackgroundExecutor.get();
    private final TaskStackChangeListeners mTaskStackChangeListeners = new TaskStackChangeListeners(Looper.getMainLooper());

    private ActivityManagerWrapper() {
    }

    public static ActivityManagerWrapper getInstance() {
        return sInstance;
    }

    public int getCurrentUserId() {
        try {
            UserInfo currentUser = ActivityManager.getService().getCurrentUser();
            if (currentUser != null) {
                return currentUser.id;
            }
            return 0;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private boolean isForbiddenTop(ActivityManager.RunningTaskInfo info) {
        return TextUtils.equals(info.topActivity.getClassName(), ForbiddenFullActivity.class.getName()) || TextUtils.equals(info.topActivity.getClassName(), ForbiddenSplitPrimaryActivity.class.getName()) || TextUtils.equals(info.topActivity.getClassName(), ForbiddenSplitSecondaryActivity.class.getName());
    }

    public ActivityManager.RunningTaskInfo getRunningTaskExceptForbidden(boolean[] outIsForbiddenTop) {
        try {
            List<ActivityManager.RunningTaskInfo> filteredTasks = ActivityTaskManager.getService().getFilteredTasks(3, false);
            if (filteredTasks.isEmpty()) {
                Log.d(TAG, "getRunningTaskExceptForbidden, task empty!");
                return null;
            }
            boolean z = false;
            for (ActivityManager.RunningTaskInfo runningTaskInfo : filteredTasks) {
                if (runningTaskInfo.displayId == 0) {
                    if (outIsForbiddenTop != null && outIsForbiddenTop.length > 0 && !z) {
                        outIsForbiddenTop[0] = isForbiddenTop(runningTaskInfo);
                        z = true;
                    }
                    if (!isForbiddenTop(runningTaskInfo)) {
                        Log.d(TAG, "getRunningTaskExceptForbidden, info " + runningTaskInfo.topActivity);
                        return runningTaskInfo;
                    }
                }
            }
            Log.d(TAG, "getRunningTaskExceptForbidden, return null! tasks = " + filteredTasks);
            return null;
        } catch (RemoteException e) {
            Log.d(TAG, "getRunningTaskExceptForbidden, return null! e = " + e);
            return null;
        }
    }

    public ActivityManager.RunningTaskInfo getRunningTask() {
        return getRunningTask(3);
    }

    public ActivityManager.RunningTaskInfo getRunningTask(int ignoreActivityType) {
        try {
            List<ActivityManager.RunningTaskInfo> filteredTasks = ActivityTaskManager.getService().getFilteredTasks(2, false);
            if (filteredTasks.isEmpty()) {
                Log.d(TAG, "getRunningTask, task empty!");
                return null;
            }
            for (ActivityManager.RunningTaskInfo runningTaskInfo : filteredTasks) {
                if (runningTaskInfo.displayId == 0) {
                    return runningTaskInfo;
                }
            }
            Log.d(TAG, "getRunningTask, return null! tasks = " + filteredTasks);
            return null;
        } catch (RemoteException e) {
            Log.d(TAG, "getRunningTask, return null! e = " + e);
            return null;
        }
    }

    public List<ActivityManager.RecentTaskInfo> getRecentTasks(int numTasks, int userId) {
        try {
            return ActivityTaskManager.getService().getRecentTasks(numTasks, 2, userId).getList();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get recent tasks", e);
            return new ArrayList();
        }
    }

    public String getBadgedActivityLabel(ActivityInfo info, int userId) {
        return getBadgedLabel(info.loadLabel(this.mPackageManager).toString(), userId);
    }

    private String getBadgedLabel(String label, int userId) {
        return userId != UserHandle.myUserId() ? this.mPackageManager.getUserBadgedLabel(label, new UserHandle(userId)).toString() : label;
    }

    public String getBadgedContentDescription(ActivityInfo info, int userId, ActivityManager.TaskDescription td) {
        String string;
        if (td != null && td.getLabel() != null) {
            string = td.getLabel();
        } else {
            string = info.loadLabel(this.mPackageManager).toString();
        }
        String string2 = info.applicationInfo.loadLabel(this.mPackageManager).toString();
        String badgedLabel = getBadgedLabel(string2, userId);
        return string2.equals(string) ? badgedLabel : badgedLabel + " " + string;
    }

    public ThumbnailData getTaskThumbnail(int taskId, boolean reducedResolution) {
        ActivityManager.TaskSnapshot taskSnapshot;
        try {
            taskSnapshot = ActivityTaskManager.getService().getTaskSnapshot(taskId, reducedResolution);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to retrieve task snapshot", e);
            taskSnapshot = null;
        }
        if (taskSnapshot != null) {
            return new ThumbnailData(taskSnapshot);
        }
        return new ThumbnailData();
    }

    public boolean isScreenPinningActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() == 2;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public Future<?> closeSystemWindows(final String reason) {
        return this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityManager.getService().closeSystemDialogs(reason);
                } catch (RemoteException e) {
                    Log.w(ActivityManagerWrapper.TAG, "Failed to close system windows", e);
                }
            }
        });
    }

    public void removeTask(final int taskId) {
        this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityTaskManager.getService().removeTask(taskId);
                } catch (RemoteException e) {
                    Log.w(ActivityManagerWrapper.TAG, "Failed to remove task=" + taskId, e);
                }
            }
        });
    }

    public void killProcess(final String packageName) {
        this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.3
            @Override // java.lang.Runnable
            public void run() {
                ((ActivityManager) AppGlobals.getInitialApplication().getSystemService(Constants.PackageInfo.ACTIVITY)).killBackgroundProcesses(packageName);
            }
        });
    }

    public void killProcessByGPack(String processName) {
        Intent intent = new Intent();
        intent.setAction(Constants.SYS_KILL_PROCESS);
        intent.setPackage("com.gpack.agent");
        intent.putExtra("processName", processName);
        intent.setFlags(268435456);
        AppGlobals.getInitialApplication().sendBroadcast(intent);
    }

    public boolean isLockToAppActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() != 0;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void registerTaskStackListener(TaskStackChangeListener listener) {
        synchronized (this.mTaskStackChangeListeners) {
            this.mTaskStackChangeListeners.addListener(ActivityManager.getService(), listener);
        }
    }

    public void unregisterTaskStackListener(TaskStackChangeListener listener) {
        synchronized (this.mTaskStackChangeListeners) {
            this.mTaskStackChangeListeners.removeListener(listener);
        }
    }

    public boolean setTaskWindowingModeSplitScreenPrimary(int taskId) {
        try {
            boolean taskWindowingModeSplitScreenPrimary = ActivityTaskManager.getService().setTaskWindowingModeSplitScreenPrimary(taskId, true);
            Log.d(TAG, "setTaskWindowingModeSplitScreenPrimary result = " + taskWindowingModeSplitScreenPrimary);
            return taskWindowingModeSplitScreenPrimary;
        } catch (RemoteException e) {
            Log.e(TAG, "setTaskWindowingModeSplitScreenPrimary RemoteException e " + e);
            e.printStackTrace();
            return false;
        }
    }

    public boolean startActivityFromRecents(int taskId, ActivityOptions options) {
        Bundle bundle;
        if (options == null) {
            bundle = null;
        } else {
            try {
                bundle = options.toBundle();
            } catch (Exception unused) {
                return false;
            }
        }
        ActivityTaskManager.getService().startActivityFromRecents(taskId, bundle);
        return true;
    }
}
