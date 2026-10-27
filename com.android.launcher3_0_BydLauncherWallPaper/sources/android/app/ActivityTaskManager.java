package android.app;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Singleton;
import com.android.internal.R;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ActivityTaskManager {
    public static final String EXTRA_IGNORE_TARGET_SECURITY = "android.app.extra.EXTRA_IGNORE_TARGET_SECURITY";
    public static final String EXTRA_OPTIONS = "android.app.extra.OPTIONS";
    public static final String EXTRA_PERMISSION_TOKEN = "android.app.extra.PERMISSION_TOKEN";
    private static final Singleton<IActivityTaskManager> IActivityTaskManagerSingleton = new Singleton<IActivityTaskManager>() { // from class: android.app.ActivityTaskManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public IActivityTaskManager create() {
            return IActivityTaskManager.Stub.asInterface(ServiceManager.getService(Context.ACTIVITY_TASK_SERVICE));
        }
    };
    public static final int INVALID_STACK_ID = -1;
    public static final int INVALID_TASK_ID = -1;
    public static final int RESIZE_MODE_FORCED = 2;
    public static final int RESIZE_MODE_PRESERVE_WINDOW = 1;
    public static final int RESIZE_MODE_SYSTEM = 0;
    public static final int RESIZE_MODE_SYSTEM_SCREEN_ROTATION = 1;
    public static final int RESIZE_MODE_USER = 1;
    public static final int RESIZE_MODE_USER_FORCED = 3;
    public static final int SPLIT_SCREEN_CREATE_MODE_BOTTOM_OR_RIGHT = 1;
    public static final int SPLIT_SCREEN_CREATE_MODE_TOP_OR_LEFT = 0;
    private static int sMaxRecentTasks = -1;

    ActivityTaskManager(Context context, Handler handler) {
    }

    public static IActivityTaskManager getService() {
        return IActivityTaskManagerSingleton.get();
    }

    public boolean setTaskWindowingMode(int i, int i2, boolean z) throws SecurityException {
        try {
            return getService().setTaskWindowingMode(i, i2, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean setTaskWindowingModeSplitScreenPrimary(int i, int i2, boolean z, boolean z2, Rect rect, boolean z3) throws SecurityException {
        try {
            return getService().setTaskWindowingModeSplitScreenPrimary(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeStacksInWindowingModes(int[] iArr) throws SecurityException {
        try {
            getService().removeStacksInWindowingModes(iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeStacksWithActivityTypes(int[] iArr) throws SecurityException {
        try {
            getService().removeStacksWithActivityTypes(iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeAllVisibleRecentTasks() {
        try {
            getService().removeAllVisibleRecentTasks();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static int getMaxRecentTasksStatic() {
        int i = sMaxRecentTasks;
        if (i < 0) {
            i = ActivityManager.isLowRamDeviceStatic() ? 36 : 48;
            sMaxRecentTasks = i;
        }
        return i;
    }

    public static int getDefaultAppRecentsLimitStatic() {
        return getMaxRecentTasksStatic() / 6;
    }

    public static int getMaxAppRecentsLimitStatic() {
        return getMaxRecentTasksStatic() / 2;
    }

    public static boolean supportsMultiWindow(Context context) {
        return (!ActivityManager.isLowRamDeviceStatic() || context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WATCH)) && Resources.getSystem().getBoolean(R.bool.config_supportsMultiWindow);
    }

    public static boolean supportsSplitScreenMultiWindow(Context context) {
        return supportsMultiWindow(context) && Resources.getSystem().getBoolean(R.bool.config_supportsSplitScreenMultiWindow);
    }

    public boolean moveTopActivityToPinnedStack(int i, Rect rect) {
        try {
            return getService().moveTopActivityToPinnedStack(i, rect);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void startSystemLockTaskMode(int i) {
        try {
            getService().startSystemLockTaskMode(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void stopSystemLockTaskMode() {
        try {
            getService().stopSystemLockTaskMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void moveTaskToStack(int i, int i2, boolean z) {
        try {
            getService().moveTaskToStack(i, i2, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void resizeTask(int i, Rect rect) {
        try {
            getService().resizeTask(i, rect, 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void resizeDockedStack(Rect rect, Rect rect2) {
        try {
            getService().resizeDockedStack(rect, rect2, null, null, null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String listAllStacks() {
        try {
            List<ActivityManager.StackInfo> allStackInfos = getService().getAllStackInfos();
            StringBuilder sb = new StringBuilder();
            if (allStackInfos != null) {
                Iterator<ActivityManager.StackInfo> it = allStackInfos.iterator();
                while (it.hasNext()) {
                    sb.append(it.next()).append("\n");
                }
            }
            return sb.toString();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clearLaunchParamsForPackages(List<String> list) {
        try {
            getService().clearLaunchParamsForPackages(list);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void setDisplayToSingleTaskInstance(int i) {
        try {
            getService().setDisplayToSingleTaskInstance(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void requestPictureInPictureMode(IBinder iBinder) {
        try {
            getService().requestPictureInPictureMode(iBinder);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean currentUiModeSupportsErrorDialogs(Configuration configuration) {
        int i = configuration.uiMode & 15;
        return (i == 3 || (i == 6 && Build.IS_USER) || i == 4 || i == 7) ? false : true;
    }

    public static boolean currentUiModeSupportsErrorDialogs(Context context) {
        return currentUiModeSupportsErrorDialogs(context.getResources().getConfiguration());
    }
}
