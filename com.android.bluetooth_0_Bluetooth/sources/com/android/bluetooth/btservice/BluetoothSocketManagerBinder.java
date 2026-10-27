package com.android.bluetooth.btservice;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothSocketManager;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import com.android.bluetooth.Utils;

/* JADX INFO: loaded from: classes.dex */
class BluetoothSocketManagerBinder extends IBluetoothSocketManager.Stub {
    private static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    private static final int INVALID_FD = -1;
    private static final String TAG = "BluetoothSocketManagerBinder";
    private AdapterService mService;

    BluetoothSocketManagerBinder(AdapterService service) {
        this.mService = service;
    }

    void cleanUp() {
        this.mService = null;
    }

    public ParcelFileDescriptor connectSocket(BluetoothDevice device, int type, ParcelUuid uuid, int port, int flag) {
        enforceBluetoothAndActiveUser();
        return marshalFd(this.mService.connectSocketNative(Utils.getBytesFromAddress(device.getAddress()), type, Utils.uuidToByteArray(uuid), port, flag, Binder.getCallingUid()));
    }

    public ParcelFileDescriptor createSocketChannel(int type, String serviceName, ParcelUuid uuid, int port, int flag) {
        enforceBluetoothAndActiveUser();
        return marshalFd(this.mService.createSocketChannelNative(type, serviceName, Utils.uuidToByteArray(uuid), port, flag, Binder.getCallingUid()));
    }

    public void requestMaximumTxDataLength(BluetoothDevice device) {
        enforceBluetoothAndActiveUser();
        this.mService.requestMaximumTxDataLengthNative(Utils.getBytesFromAddress(device.getAddress()));
    }

    private void enforceBluetoothAndActiveUser() {
        if (!Utils.checkCallerAllowManagedProfiles(this.mService)) {
            throw new SecurityException("Not allowed for non-active user");
        }
        this.mService.enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
    }

    private static ParcelFileDescriptor marshalFd(int fd) {
        if (fd == -1) {
            return null;
        }
        return ParcelFileDescriptor.adoptFd(fd);
    }
}
