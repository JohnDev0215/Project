package android.window;

import android.app.ActivityManager;
import android.os.RemoteException;
import android.util.Singleton;
import android.view.SurfaceControl;
import android.window.ITaskOrganizer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TaskOrganizer extends WindowOrganizer {
    private static final Singleton<ITaskOrganizerController> ITaskOrganizerControllerSingleton = new Singleton<ITaskOrganizerController>() { // from class: android.window.TaskOrganizer.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public ITaskOrganizerController create() {
            try {
                return WindowOrganizer.getWindowOrganizerController().getTaskOrganizerController();
            } catch (RemoteException unused) {
                return null;
            }
        }
    };
    private final ITaskOrganizer mInterface = new ITaskOrganizer.Stub() { // from class: android.window.TaskOrganizer.1
        @Override // android.window.ITaskOrganizer
        public void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
            TaskOrganizer.this.onTaskAppeared(runningTaskInfo, surfaceControl);
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
            TaskOrganizer.this.onTaskVanished(runningTaskInfo);
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
            TaskOrganizer.this.onTaskInfoChanged(runningTaskInfo);
        }

        @Override // android.window.ITaskOrganizer
        public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
            TaskOrganizer.this.onBackPressedOnTaskRoot(runningTaskInfo);
        }
    };

    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
    }

    public void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
    }

    public void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
    }

    public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
    }

    public final void registerOrganizer(int i) {
        try {
            getController().registerTaskOrganizer(this.mInterface, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final void unregisterOrganizer() {
        try {
            getController().unregisterTaskOrganizer(this.mInterface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static ActivityManager.RunningTaskInfo createRootTask(int i, int i2) {
        try {
            return getController().createRootTask(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean deleteRootTask(WindowContainerToken windowContainerToken) {
        try {
            return getController().deleteRootTask(windowContainerToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken windowContainerToken, int[] iArr) {
        try {
            return getController().getChildTasks(windowContainerToken, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static List<ActivityManager.RunningTaskInfo> getRootTasks(int i, int[] iArr) {
        try {
            return getController().getRootTasks(i, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static WindowContainerToken getImeTarget(int i) {
        try {
            return getController().getImeTarget(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static void setLaunchRoot(int i, WindowContainerToken windowContainerToken) {
        try {
            getController().setLaunchRoot(i, windowContainerToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setInterceptBackPressedOnTaskRoot(boolean z) {
        try {
            getController().setInterceptBackPressedOnTaskRoot(this.mInterface, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static ITaskOrganizerController getController() {
        return ITaskOrganizerControllerSingleton.get();
    }
}
