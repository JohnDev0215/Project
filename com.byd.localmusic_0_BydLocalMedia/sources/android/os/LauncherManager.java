package android.os;

import android.annotation.SystemApi;
import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class LauncherManager {
    private static final String TAG = LauncherManager.class.getSimpleName();
    private Context mContext;
    private ILauncherManager mLauncherManagerService;

    public LauncherManager(Context context, ILauncherManager iLauncherManager) {
        this.mContext = context;
        this.mLauncherManagerService = iLauncherManager;
    }

    @SystemApi
    public void setDefaultLauncher(String str) {
        if (this.mLauncherManagerService == null) {
            Log.e(TAG, "mLauncherManagerService is null");
            return;
        }
        Log.e(TAG, "setDefaultLauncher: " + str);
        try {
            this.mLauncherManagerService.setDefaultLauncher(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isFirstBoot() {
        ILauncherManager iLauncherManager = this.mLauncherManagerService;
        if (iLauncherManager == null) {
            Log.e(TAG, "mLauncherManagerService is null");
            return false;
        }
        try {
            return iLauncherManager.isFirstBoot();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
