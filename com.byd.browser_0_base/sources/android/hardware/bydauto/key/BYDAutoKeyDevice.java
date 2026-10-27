package android.hardware.bydauto.key;

import android.content.Context;
import android.hardware.bydauto.key.tsimpl.KeyManagerImpl;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoKeyDevice {
    private static final String TAG = "BYDAutoKeyDevice";
    private static volatile BYDAutoKeyDevice sDevice;
    private final KeyManagerImpl mKeyManagerImpl;

    private BYDAutoKeyDevice(Context context) {
        this.mKeyManagerImpl = KeyManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static BYDAutoKeyDevice getInstance(Context context) {
        if (sDevice == null) {
            synchronized (BYDAutoKeyDevice.class) {
                if (sDevice == null) {
                    sDevice = new BYDAutoKeyDevice(context);
                }
            }
        }
        return sDevice;
    }

    public int getDevicetype() {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl != null) {
            return keyManagerImpl.getDevicetype();
        }
        Log.e(TAG, "mKeyManagerImpl is null ");
        return -1;
    }

    public String getGetPermission() {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl != null) {
            return keyManagerImpl.getGetPermission();
        }
        Log.e(TAG, "mKeyManagerImpl is null ");
        return null;
    }

    public String getSetPermission() {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl != null) {
            return keyManagerImpl.getSetPermission();
        }
        Log.e(TAG, "mKeyManagerImpl is null ");
        return null;
    }

    public void registerListener(AbsBYDAutoKeyListener absBYDAutoKeyListener) {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.registerListener(absBYDAutoKeyListener);
        }
    }

    public void registerSceneListener(AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i) {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.registerSceneListener(absBYDAutoSceneKeyListener, i);
        }
    }

    public void unregisterListener(AbsBYDAutoKeyListener absBYDAutoKeyListener) {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.unregisterListener(absBYDAutoKeyListener);
        }
    }

    public void unregisterSceneListener(AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i) {
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            Log.e(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.unregisterSceneListener(absBYDAutoSceneKeyListener, i);
        }
    }
}
