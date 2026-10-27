package com.android.server.telecom;

import android.os.RemoteException;
import android.telecom.Log;
import com.android.internal.telecom.IDeviceIdleControllerAdapter;

/* JADX INFO: loaded from: classes.dex */
public class DeviceIdleControllerAdapter {
    private final IDeviceIdleControllerAdapter mAdapter;

    public DeviceIdleControllerAdapter(IDeviceIdleControllerAdapter iDeviceIdleControllerAdapter) {
        this.mAdapter = iDeviceIdleControllerAdapter;
    }

    public void exemptAppTemporarilyForEvent(String str, long j, int i, String str2) {
        try {
            this.mAdapter.exemptAppTemporarilyForEvent(str, j, i, str2);
        } catch (RemoteException e) {
            Log.w("DeviceIdleControllerAdapter", "exemptAppTemporarilyForEvent e=" + e.getMessage(), new Object[0]);
        }
    }
}
