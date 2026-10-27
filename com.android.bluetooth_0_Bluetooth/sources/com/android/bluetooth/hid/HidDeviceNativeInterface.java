package com.android.bluetooth.hid;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.android.bluetooth.Utils;

/* JADX INFO: loaded from: classes.dex */
public class HidDeviceNativeInterface {
    private static final Object INSTANCE_LOCK = new Object();
    private static final String TAG = "HidDeviceNativeInterface";
    private static HidDeviceNativeInterface sInstance;
    private BluetoothAdapter mAdapter;

    private static native void classInitNative();

    private native void cleanupNative();

    private native boolean connectNative(byte[] bArr);

    private native boolean disconnectNative();

    private native void initNative();

    private native boolean registerAppNative(String str, String str2, String str3, byte b, byte[] bArr, int[] iArr, int[] iArr2);

    private native boolean replyReportNative(byte b, byte b2, byte[] bArr);

    private native boolean reportErrorNative(byte b);

    private native boolean sendReportNative(int i, byte[] bArr);

    private native boolean unplugNative();

    private native boolean unregisterAppNative();

    static {
        classInitNative();
    }

    private HidDeviceNativeInterface() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            Log.wtf(TAG, "No Bluetooth Adapter Available");
        }
    }

    public static HidDeviceNativeInterface getInstance() {
        HidDeviceNativeInterface hidDeviceNativeInterface;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                setInstance(new HidDeviceNativeInterface());
            }
            hidDeviceNativeInterface = sInstance;
        }
        return hidDeviceNativeInterface;
    }

    private static void setInstance(HidDeviceNativeInterface nativeInterface) {
        sInstance = nativeInterface;
    }

    public void init() {
        initNative();
    }

    public void cleanup() {
        cleanupNative();
    }

    public boolean registerApp(String name, String description, String provider, byte subclass, byte[] descriptors, int[] inQos, int[] outQos) {
        return registerAppNative(name, description, provider, subclass, descriptors, inQos, outQos);
    }

    public boolean unregisterApp() {
        return unregisterAppNative();
    }

    public boolean sendReport(int id, byte[] data) {
        return sendReportNative(id, data);
    }

    public boolean replyReport(byte type, byte id, byte[] data) {
        return replyReportNative(type, id, data);
    }

    public boolean unplug() {
        return unplugNative();
    }

    public boolean connect(BluetoothDevice device) {
        return connectNative(getByteAddress(device));
    }

    public boolean disconnect() {
        return disconnectNative();
    }

    public boolean reportError(byte error) {
        return reportErrorNative(error);
    }

    private synchronized void onApplicationStateChanged(byte[] address, boolean registered) {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onApplicationStateChangedFromNative(getDevice(address), registered);
        } else {
            Log.wtf(TAG, "FATAL: onApplicationStateChanged() is called from the stack while service is not available.");
        }
    }

    private synchronized void onConnectStateChanged(byte[] address, int state) {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onConnectStateChangedFromNative(getDevice(address), state);
        } else {
            Log.wtf(TAG, "FATAL: onConnectStateChanged() is called from the stack while service is not available.");
        }
    }

    private synchronized void onGetReport(byte type, byte id, short bufferSize) {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onGetReportFromNative(type, id, bufferSize);
        } else {
            Log.wtf(TAG, "FATAL: onGetReport() is called from the stack while service is not available.");
        }
    }

    private synchronized void onSetReport(byte reportType, byte reportId, byte[] data) {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onSetReportFromNative(reportType, reportId, data);
        } else {
            Log.wtf(TAG, "FATAL: onSetReport() is called from the stack while service is not available.");
        }
    }

    private synchronized void onSetProtocol(byte protocol) {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onSetProtocolFromNative(protocol);
        } else {
            Log.wtf(TAG, "FATAL: onSetProtocol() is called from the stack while service is not available.");
        }
    }

    private synchronized void onInterruptData(byte reportId, byte[] data) {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onInterruptDataFromNative(reportId, data);
        } else {
            Log.wtf(TAG, "FATAL: onInterruptData() is called from the stack while service is not available.");
        }
    }

    private synchronized void onVirtualCableUnplug() {
        HidDeviceService service = HidDeviceService.getHidDeviceService();
        if (service != null) {
            service.onVirtualCableUnplugFromNative();
        } else {
            Log.wtf(TAG, "FATAL: onVirtualCableUnplug() is called from the stack while service is not available.");
        }
    }

    private BluetoothDevice getDevice(byte[] address) {
        if (address == null) {
            return null;
        }
        return this.mAdapter.getRemoteDevice(address);
    }

    private byte[] getByteAddress(BluetoothDevice device) {
        return Utils.getBytesFromAddress(device.getAddress());
    }
}
