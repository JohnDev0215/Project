package com.ts.lib.settings.container;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.container.IContainerSettingsService;

/* JADX INFO: loaded from: classes2.dex */
public class ContainerSettingManager implements SettingsAdapterBase {
    private static final String TAG = ContainerSettingManager.class.getSimpleName();
    private IContainerSettingsService mContainerSettingsService;

    public ContainerSettingManager(IBinder binder) {
        LogUtil.debug(TAG, "init container manager");
        this.mContainerSettingsService = IContainerSettingsService.Stub.asInterface(binder);
    }

    public void sendInfo(int index) {
        if (this.mContainerSettingsService == null) {
            LogUtil.error(TAG, "mContainerSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "send Cluster test Info index " + index);
        try {
            this.mContainerSettingsService.sendInfo(index);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "sendInfo error: ", ex);
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
    }
}
