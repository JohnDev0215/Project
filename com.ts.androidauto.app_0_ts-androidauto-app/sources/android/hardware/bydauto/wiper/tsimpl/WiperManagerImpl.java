package android.hardware.bydauto.wiper.tsimpl;

import android.content.Context;
import android.hardware.bydauto.wiper.AbsBYDAutoWiperListener;

/* JADX INFO: loaded from: classes.dex */
public class WiperManagerImpl {
    protected static final String TAG = "WiperManagerImpl";
    private static volatile WiperManagerImpl mWiperManagerImpl;

    private WiperManagerImpl(Context con) {
    }

    public static WiperManagerImpl getInstance(Context con) {
        if (mWiperManagerImpl == null) {
            synchronized (WiperManagerImpl.class) {
                if (mWiperManagerImpl == null) {
                    mWiperManagerImpl = new WiperManagerImpl(con);
                }
            }
        }
        return mWiperManagerImpl;
    }

    public int getDevicetype() {
        return 0;
    }

    public String getSetPermission() {
        return null;
    }

    public String getGetPermission() {
        return null;
    }

    public int getWindscreenWiperRelayState() {
        return 0;
    }

    public int getWindscreenWiperResetState(int area) {
        return 0;
    }

    public int getWindscreenWiperSensitivity() {
        return 0;
    }

    public void getAllStatus() {
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return true;
    }

    public int getType() {
        return 0;
    }

    public int[] getFeatureList() {
        return null;
    }

    public void registerListener(AbsBYDAutoWiperListener l) {
    }

    public void registerListener(AbsBYDAutoWiperListener l, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoWiperListener l) {
    }
}
