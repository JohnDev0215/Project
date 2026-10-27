package android.window;

import android.app.ActivityTaskManager;
import android.os.RemoteException;
import android.util.Singleton;
import android.view.SurfaceControl;

/* JADX INFO: loaded from: classes3.dex */
public class WindowOrganizer {
    private static final Singleton<IWindowOrganizerController> IWindowOrganizerControllerSingleton = new Singleton<IWindowOrganizerController>() { // from class: android.window.WindowOrganizer.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public IWindowOrganizerController create() {
            try {
                return ActivityTaskManager.getService().getWindowOrganizerController();
            } catch (RemoteException unused) {
                return null;
            }
        }
    };

    public static void applyTransaction(WindowContainerTransaction windowContainerTransaction) {
        try {
            getWindowOrganizerController().applyTransaction(windowContainerTransaction);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int applySyncTransaction(WindowContainerTransaction windowContainerTransaction, WindowContainerTransactionCallback windowContainerTransactionCallback) {
        try {
            return getWindowOrganizerController().applySyncTransaction(windowContainerTransaction, windowContainerTransactionCallback.mInterface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static SurfaceControl takeScreenshot(WindowContainerToken windowContainerToken) {
        try {
            SurfaceControl surfaceControl = new SurfaceControl();
            if (getWindowOrganizerController().takeScreenshot(windowContainerToken, surfaceControl)) {
                return surfaceControl;
            }
            return null;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    static IWindowOrganizerController getWindowOrganizerController() {
        return IWindowOrganizerControllerSingleton.get();
    }
}
