package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import android.os.Looper;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetObjectsFactory {
    private static HeadsetObjectsFactory sInstance;
    private static final String TAG = HeadsetObjectsFactory.class.getSimpleName();
    private static final Object INSTANCE_LOCK = new Object();

    private HeadsetObjectsFactory() {
    }

    public static HeadsetObjectsFactory getInstance() {
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new HeadsetObjectsFactory();
            }
        }
        return sInstance;
    }

    private static void setInstanceForTesting(HeadsetObjectsFactory objectsFactory) {
        Utils.enforceInstrumentationTestMode();
        synchronized (INSTANCE_LOCK) {
            Log.d(TAG, "setInstanceForTesting(), set to " + objectsFactory);
            sInstance = objectsFactory;
        }
    }

    public HeadsetStateMachine makeStateMachine(BluetoothDevice device, Looper looper, HeadsetService headsetService, AdapterService adapterService, HeadsetNativeInterface nativeInterface, HeadsetSystemInterface systemInterface) {
        return HeadsetStateMachine.make(device, looper, headsetService, adapterService, nativeInterface, systemInterface);
    }

    public void destroyStateMachine(HeadsetStateMachine stateMachine) {
        HeadsetStateMachine.destroy(stateMachine);
    }

    public HeadsetSystemInterface makeSystemInterface(HeadsetService service) {
        return new HeadsetSystemInterface(service);
    }

    public HeadsetNativeInterface getNativeInterface() {
        return HeadsetNativeInterface.getInstance();
    }
}
