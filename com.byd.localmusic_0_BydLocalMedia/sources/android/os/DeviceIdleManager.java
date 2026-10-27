package android.os;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceIdleManager {
    private final Context mContext;
    private final IDeviceIdleController mService;

    public DeviceIdleManager(Context context, IDeviceIdleController iDeviceIdleController) {
        this.mContext = context;
        this.mService = iDeviceIdleController;
    }

    IDeviceIdleController getService() {
        return this.mService;
    }

    public String[] getSystemPowerWhitelistExceptIdle() {
        try {
            return this.mService.getSystemPowerWhitelistExceptIdle();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String[] getSystemPowerWhitelist() {
        try {
            return this.mService.getSystemPowerWhitelist();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
