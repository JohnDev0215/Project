package android.hardware.bydauto.wiper.tsimpl;

import android.content.Context;
import android.hardware.bydauto.wiper.AbsBYDAutoWiperListener;

/* JADX INFO: loaded from: classes.dex */
public class WiperManagerImpl {
    protected static final String TAG = "WiperManagerImpl";
    private static volatile WiperManagerImpl mWiperManagerImpl;

    private WiperManagerImpl(Context context) {
    }

    public static WiperManagerImpl getInstance(Context context) {
        if (mWiperManagerImpl == null) {
            synchronized (WiperManagerImpl.class) {
                if (mWiperManagerImpl == null) {
                    mWiperManagerImpl = new WiperManagerImpl(context);
                }
            }
        }
        return mWiperManagerImpl;
    }

    public void getAllStatus() {
    }

    public int getDevicetype() {
        return 0;
    }

    public int[] getFeatureList() {
        return null;
    }

    public String getGetPermission() {
        return null;
    }

    public String getSetPermission() {
        return null;
    }

    public int getType() {
        return 0;
    }

    public int getWindscreenWiperRelayState() {
        return 0;
    }

    public int getWindscreenWiperResetState(int i7) {
        return 0;
    }

    public int getWindscreenWiperSensitivity() {
        return 0;
    }

    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        return true;
    }

    public void registerListener(AbsBYDAutoWiperListener absBYDAutoWiperListener) {
    }

    public void registerListener(AbsBYDAutoWiperListener absBYDAutoWiperListener, int[] iArr) {
    }

    public void unregisterListener(AbsBYDAutoWiperListener absBYDAutoWiperListener) {
    }
}
