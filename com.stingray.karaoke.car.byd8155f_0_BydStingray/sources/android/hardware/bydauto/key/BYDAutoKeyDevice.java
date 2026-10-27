package android.hardware.bydauto.key;

import android.content.Context;
import android.hardware.bydauto.key.tsimpl.KeyManagerImpl;
import android.util.Log;
import h6.n;

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
                try {
                    if (sDevice == null) {
                        sDevice = new BYDAutoKeyDevice(context);
                    }
                } finally {
                }
            }
        }
        return sDevice;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl != null) {
            return keyManagerImpl.getDevicetype();
        }
        n.j(TAG, "mKeyManagerImpl is null ");
        return -1;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl != null) {
            return keyManagerImpl.getGetPermission();
        }
        n.j(TAG, "mKeyManagerImpl is null ");
        return null;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl != null) {
            return keyManagerImpl.getSetPermission();
        }
        n.j(TAG, "mKeyManagerImpl is null ");
        return null;
    }

    public void registerListener(AbsBYDAutoKeyListener absBYDAutoKeyListener) {
        Log.d(TAG, "registerListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            n.j(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.registerListener(absBYDAutoKeyListener);
        }
    }

    public void registerSceneListener(AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i7) {
        Log.d(TAG, "registerSceneListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            n.j(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.registerSceneListener(absBYDAutoSceneKeyListener, i7);
        }
    }

    public void unregisterListener(AbsBYDAutoKeyListener absBYDAutoKeyListener) {
        Log.d(TAG, "unregisterListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            n.j(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.unregisterListener(absBYDAutoKeyListener);
        }
    }

    public void unregisterSceneListener(AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i7) {
        Log.d(TAG, "unregisterSceneListener: ");
        KeyManagerImpl keyManagerImpl = this.mKeyManagerImpl;
        if (keyManagerImpl == null) {
            n.j(TAG, "mKeyManagerImpl is null ");
        } else {
            keyManagerImpl.unregisterSceneListener(absBYDAutoSceneKeyListener, i7);
        }
    }
}
