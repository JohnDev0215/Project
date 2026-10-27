package com.android.bluetooth.btservice.bluetoothkeystore;

import android.util.Log;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
final class BluetoothKeystoreNativeInterface {
    private static final Object INSTANCE_LOCK = new Object();
    private static final String TAG = "BluetoothKeystoreNativeInterface";
    private static BluetoothKeystoreNativeInterface sInstance;

    private static native void classInitNative();

    private native void cleanupNative();

    private native void initNative();

    static {
        classInitNative();
    }

    private BluetoothKeystoreNativeInterface() {
    }

    public static BluetoothKeystoreNativeInterface getInstance() {
        BluetoothKeystoreNativeInterface bluetoothKeystoreNativeInterface;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new BluetoothKeystoreNativeInterface();
            }
            bluetoothKeystoreNativeInterface = sInstance;
        }
        return bluetoothKeystoreNativeInterface;
    }

    public void init() {
        initNative();
    }

    public void cleanup() {
        cleanupNative();
    }

    private void setEncryptKeyOrRemoveKeyCallback(String prefixString, String decryptedString) {
        BluetoothKeystoreService service = BluetoothKeystoreService.getBluetoothKeystoreService();
        if (service != null) {
            try {
                service.setEncryptKeyOrRemoveKey(prefixString, decryptedString);
                return;
            } catch (IOException e) {
                Log.e(TAG, "IO error while file operating.");
                return;
            } catch (InterruptedException e2) {
                Log.e(TAG, "Interrupted while operating.");
                return;
            } catch (NoSuchAlgorithmException e3) {
                Log.e(TAG, "encrypt could not find the algorithm: SHA256");
                return;
            }
        }
        Log.e(TAG, "Event ignored, service not available: " + prefixString);
    }

    private String getKeyCallback(String prefixString) {
        BluetoothKeystoreService service = BluetoothKeystoreService.getBluetoothKeystoreService();
        if (service != null) {
            return service.getKey(prefixString);
        }
        Log.e(TAG, "Event ignored, service not available: " + prefixString);
        return null;
    }
}
