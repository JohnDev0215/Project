package android.hardware.bydauto.wiper;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.wiper.tsimpl.WiperManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoWiperDevice extends AbsBYDAutoDevice {
    protected static final String TAG = "BYDAutoWiperDevice";
    public static final int WIPER_AREA_FRONT = 0;
    public static final int WIPER_AREA_REAR = 1;
    public static final int WIPER_COMMAND_BUSY = -2147482647;
    public static final int WIPER_COMMAND_FAILED = -2147482648;
    public static final int WIPER_COMMAND_INVALID = -2147482645;
    public static final int WIPER_COMMAND_SUCCESS = 0;
    public static final int WIPER_COMMAND_TIMEOUT = -2147482646;
    public static final int WIPER_RELAY_STATE_ACTUATION = 1;
    public static final int WIPER_RELAY_STATE_DISCONNECT = 2;
    public static final int WIPER_RELAY_STATE_INVALID = 0;
    public static final int WIPER_SENSITIVITY_1 = 1;
    public static final int WIPER_SENSITIVITY_2 = 2;
    public static final int WIPER_SENSITIVITY_3 = 3;
    public static final int WIPER_SENSITIVITY_4 = 4;
    public static final int WIPER_SENSITIVITY_INVALID = 0;
    public static final int WIPER_STATE_INVALID = 0;
    public static final int WIPER_STATE_NOT_RESET = 1;
    public static final int WIPER_STATE_RESET = 2;
    private static volatile BYDAutoWiperDevice mBYDAutoWiperDevice;
    private WiperManagerImpl mWiperManagerImpl;

    private BYDAutoWiperDevice(Context con) {
        super(con);
        this.mWiperManagerImpl = WiperManagerImpl.getInstance(con);
    }

    public static BYDAutoWiperDevice getInstance(Context con) {
        if (mBYDAutoWiperDevice == null) {
            synchronized (BYDAutoWiperDevice.class) {
                if (mBYDAutoWiperDevice == null) {
                    mBYDAutoWiperDevice = new BYDAutoWiperDevice(con.getApplicationContext());
                }
            }
        }
        return mBYDAutoWiperDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mWiperManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mWiperManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mWiperManagerImpl.getGetPermission();
    }

    public int getWindscreenWiperRelayState() {
        return this.mWiperManagerImpl.getWindscreenWiperRelayState();
    }

    public int getWindscreenWiperResetState(int area) {
        return this.mWiperManagerImpl.getWindscreenWiperResetState(area);
    }

    public int getWindscreenWiperSensitivity() {
        return this.mWiperManagerImpl.getWindscreenWiperSensitivity();
    }

    public void getAllStatus() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return this.mWiperManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mWiperManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mWiperManagerImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoWiperListener l) {
        this.mWiperManagerImpl.registerListener(l);
    }

    public void registerListener(AbsBYDAutoWiperListener l, int[] featureIDs) {
        this.mWiperManagerImpl.registerListener(l, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoWiperListener l) {
        this.mWiperManagerImpl.unregisterListener(l);
    }
}
