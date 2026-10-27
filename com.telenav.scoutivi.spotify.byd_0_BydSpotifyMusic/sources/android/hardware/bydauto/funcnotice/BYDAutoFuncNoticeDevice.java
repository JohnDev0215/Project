package android.hardware.bydauto.funcnotice;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoFuncNoticeDevice extends AbsBYDAutoDevice {
    public static final int FUNCNOTICE_COMMAND_BUSY = -2147482647;
    public static final int FUNCNOTICE_COMMAND_FAILED = -2147482648;
    public static final int FUNCNOTICE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int FUNCNOTICE_COMMAND_SUCCESS = 0;
    public static final int FUNCNOTICE_COMMAND_TIMEOUT = -2147482646;
    public static final int FUNC_3RD_APP = 9;
    public static final int FUNC_APP_MENU = 8;
    public static final int FUNC_BACK = 0;
    public static final int FUNC_BL_OFF = 11;
    public static final int FUNC_MEDIA = 2;
    public static final int FUNC_NAVI = 1;
    public static final int FUNC_PANORAMA = 3;
    public static final int FUNC_PHONE = 5;
    public static final int FUNC_PHONE_LINK = 4;
    public static final int FUNC_SCREENS = 10;
    public static final int FUNC_SETTIGN = 6;
    public static final int FUNC_STATUSBAR = 7;
    protected static final String TAG = "BYDAutoFuncNoticeDevice";

    BYDAutoFuncNoticeDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoFuncNoticeDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    public int getFuncNotice() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoFuncNoticeListener absBYDAutoFuncNoticeListener) {
        throw new RuntimeException("Stub!");
    }

    public void setAllStatus() {
        throw new RuntimeException("Stub!");
    }

    public int setFuncNotice(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoFuncNoticeListener absBYDAutoFuncNoticeListener) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoFuncNoticeListener absBYDAutoFuncNoticeListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
