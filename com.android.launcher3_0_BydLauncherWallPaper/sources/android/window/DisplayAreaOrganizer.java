package android.window;

import android.os.RemoteException;
import android.util.Singleton;
import android.view.SurfaceControl;
import android.window.IDisplayAreaOrganizer;

/* JADX INFO: loaded from: classes3.dex */
public class DisplayAreaOrganizer extends WindowOrganizer {
    public static final int FEATURE_DEFAULT_TASK_CONTAINER = 1;
    public static final int FEATURE_ROOT = 0;
    public static final int FEATURE_SYSTEM_FIRST = 0;
    public static final int FEATURE_SYSTEM_LAST = 10000;
    public static final int FEATURE_UNDEFINED = -1;
    public static final int FEATURE_VENDOR_FIRST = 10001;
    public static final int FEATURE_WINDOW_TOKENS = 2;
    private static final Singleton<IDisplayAreaOrganizerController> IDisplayAreaOrganizerControllerSingleton = new Singleton<IDisplayAreaOrganizerController>() { // from class: android.window.DisplayAreaOrganizer.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public IDisplayAreaOrganizerController create() {
            try {
                return WindowOrganizer.getWindowOrganizerController().getDisplayAreaOrganizerController();
            } catch (RemoteException unused) {
                return null;
            }
        }
    };
    private final IDisplayAreaOrganizer mInterface = new IDisplayAreaOrganizer.Stub() { // from class: android.window.DisplayAreaOrganizer.1
        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl surfaceControl) {
            DisplayAreaOrganizer.this.onDisplayAreaAppeared(displayAreaInfo, surfaceControl);
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) {
            DisplayAreaOrganizer.this.onDisplayAreaVanished(displayAreaInfo);
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) {
            DisplayAreaOrganizer.this.onDisplayAreaInfoChanged(displayAreaInfo);
        }
    };

    public void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl surfaceControl) {
    }

    public void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) {
    }

    public void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) {
    }

    public void registerOrganizer(int i) {
        try {
            getController().registerOrganizer(this.mInterface, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterOrganizer() {
        try {
            getController().unregisterOrganizer(this.mInterface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static IDisplayAreaOrganizerController getController() {
        return IDisplayAreaOrganizerControllerSingleton.get();
    }
}
