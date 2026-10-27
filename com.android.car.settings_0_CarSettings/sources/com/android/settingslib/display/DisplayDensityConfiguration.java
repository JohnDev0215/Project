package com.android.settingslib.display;

import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;

/* JADX INFO: loaded from: classes6.dex */
public class DisplayDensityConfiguration {
    private static final String LOG_TAG = "DisplayDensityConfig";

    static int getDefaultDisplayDensity(int displayId) {
        try {
            IWindowManager wm = WindowManagerGlobal.getWindowManagerService();
            return wm.getInitialDisplayDensity(displayId);
        } catch (RemoteException e) {
            return -1;
        }
    }

    public static void clearForcedDisplayDensity(final int displayId) {
        final int userId = UserHandle.myUserId();
        AsyncTask.execute(new Runnable() { // from class: com.android.settingslib.display.-$$Lambda$DisplayDensityConfiguration$dm3fSLc60rEdpFAtD9PwhewynDU
            @Override // java.lang.Runnable
            public final void run() {
                DisplayDensityConfiguration.lambda$clearForcedDisplayDensity$0(displayId, userId);
            }
        });
    }

    static /* synthetic */ void lambda$clearForcedDisplayDensity$0(int displayId, int userId) {
        try {
            IWindowManager wm = WindowManagerGlobal.getWindowManagerService();
            wm.clearForcedDisplayDensityForUser(displayId, userId);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, "Unable to clear forced display density setting");
        }
    }

    public static void setForcedDisplayDensity(final int displayId, final int density) {
        final int userId = UserHandle.myUserId();
        AsyncTask.execute(new Runnable() { // from class: com.android.settingslib.display.-$$Lambda$DisplayDensityConfiguration$Y1PRkpgOIJnyPn1bdKPH8CzcHNs
            @Override // java.lang.Runnable
            public final void run() {
                DisplayDensityConfiguration.lambda$setForcedDisplayDensity$1(displayId, density, userId);
            }
        });
    }

    static /* synthetic */ void lambda$setForcedDisplayDensity$1(int displayId, int density, int userId) {
        try {
            IWindowManager wm = WindowManagerGlobal.getWindowManagerService();
            wm.setForcedDisplayDensityForUser(displayId, density, userId);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, "Unable to save forced display density setting");
        }
    }
}
