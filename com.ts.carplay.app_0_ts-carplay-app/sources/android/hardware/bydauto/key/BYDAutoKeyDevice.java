package android.hardware.bydauto.key;

import android.content.Context;
import android.hardware.bydauto.key.tsimpl.KeyManagerImpl;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoKeyDevice {
    private static final String TAG = "BYDAutoKeyDevice";
    private static volatile BYDAutoKeyDevice sDevice;
    private final KeyManagerImpl mKeyManagerImpl;

    private BYDAutoKeyDevice(Context con) {
        this.mKeyManagerImpl = KeyManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static BYDAutoKeyDevice getInstance(Context con) {
        if (sDevice == null) {
            synchronized (BYDAutoKeyDevice.class) {
                if (sDevice == null) {
                    sDevice = new BYDAutoKeyDevice(con);
                }
            }
        }
        return sDevice;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
            return -1;
        }
        return keyManagerImpl.getDevicetype();
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
            return null;
        }
        return keyManagerImpl.getSetPermission();
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
            return null;
        }
        return keyManagerImpl.getGetPermission();
    }

    public void registerListener(AbsBYDAutoKeyListener listener) {
        Log.d(TAG, "registerListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.registerListener(listener);
        }
    }

    public void unregisterListener(AbsBYDAutoKeyListener listener) {
        Log.d(TAG, "unregisterListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.unregisterListener(listener);
        }
    }

    public void registerSceneListener(AbsBYDAutoSceneKeyListener listener, int sceneId) {
        Log.d(TAG, "registerSceneListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.registerSceneListener(listener, sceneId);
        }
    }

    public void unregisterSceneListener(AbsBYDAutoSceneKeyListener listener, int sceneId) {
        Log.d(TAG, "unregisterSceneListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.unregisterSceneListener(listener, sceneId);
        }
    }
}
