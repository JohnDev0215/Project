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

    protected BYDAutoXCallDevice(Context con) {
        this.mXcallManagerImpl = XcallManagerImpl.getInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoXCallDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoXCallDevice(con);
        }
        return sDevice;
    }

    public int getDevicetype() {
        return 0;
    }

    public String getSetPermission() {
        return PERMISSION_BYDAUTO_XCALL_SET;
    }

    public String getGetPermission() {
        return PERMISSION_BYDAUTO_XCALL_GET;
    }

    public int setCallType(int callType) {
        return this.mXcallManagerImpl.setCallType(callType);
    }

    public int setRequestType(int requestType) {
        return this.mXcallManagerImpl.setRequestType(requestType);
    }

    public int getCallType() {
        return this.mXcallManagerImpl.getCallType();
    }

    public int getCallStatus() {
        return this.mXcallManagerImpl.getCallStatus();
    }

    public int getCallBackMode() {
        return this.mXcallManagerImpl.getCallBackMode();
    }

    public void registerListener(AbsBYDXCallListener listener) {
        this.mXcallManagerImpl.registerListener(listener);
    }

    public void unregisterListener(AbsBYDXCallListener listener) {
        this.mXcallManagerImpl.unregisterListener(listener);
    }
}
