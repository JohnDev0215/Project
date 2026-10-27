package android.hardware.bydauto.xcall;

import android.content.Context;
import android.hardware.bydauto.xcall.tsimpl.XcallManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoXCallDevice {
    public static final String PERMISSION_BYDAUTO_XCALL_COMMON = "android.permission.BYDAUTO_XCALL_COMMON";
    public static final String PERMISSION_BYDAUTO_XCALL_GET = "android.permission.BYDAUTO_XCALL_GET";
    public static final String PERMISSION_BYDAUTO_XCALL_SET = "android.permission.BYDAUTO_XCALL_SET";
    private static BYDAutoXCallDevice sDevice;
    private final XcallManagerImpl mXcallManagerImpl;

    protected BYDAutoXCallDevice(Context context) {
        this.mXcallManagerImpl = XcallManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoXCallDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoXCallDevice(context);
        }
        return sDevice;
    }

    public int getCallBackMode() {
        return this.mXcallManagerImpl.getCallBackMode();
    }

    public int getCallStatus() {
        return this.mXcallManagerImpl.getCallStatus();
    }

    public int getCallType() {
        return this.mXcallManagerImpl.getCallType();
    }

    public int getDevicetype() {
        return 0;
    }

    public String getGetPermission() {
        return PERMISSION_BYDAUTO_XCALL_GET;
    }

    public String getSetPermission() {
        return PERMISSION_BYDAUTO_XCALL_SET;
    }

    public void registerListener(AbsBYDXCallListener absBYDXCallListener) {
        this.mXcallManagerImpl.registerListener(absBYDXCallListener);
    }

    public int setCallType(int i2) {
        return this.mXcallManagerImpl.setCallType(i2);
    }

    public int setRequestType(int i2) {
        return this.mXcallManagerImpl.setRequestType(i2);
    }

    public void unregisterListener(AbsBYDXCallListener absBYDXCallListener) {
        this.mXcallManagerImpl.unregisterListener(absBYDXCallListener);
    }
}
