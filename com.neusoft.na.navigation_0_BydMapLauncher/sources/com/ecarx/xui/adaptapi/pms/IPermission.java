package com.ecarx.xui.adaptapi.pms;

import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IPermission {
    public static final int PERMISSION_ALL = 0;
    public static final int PERMISSION_AUDIO = 1;
    public static final int PERMISSION_BT = 2;
    public static final int PERMISSION_DEVICE = 3;
    public static final int PERMISSION_DIM = 4;
    public static final int PERMISSION_DVR = 5;
    public static final int PERMISSION_EVS = 6;
    public static final int PERMISSION_HVAC = 7;
    public static final int PERMISSION_INPUT = 8;
    public static final int PERMISSION_NAVIGATION = 20;
    public static final int PERMISSION_ONCALL = 9;
    public static final int PERMISSION_OTA = 10;
    public static final int PERMISSION_PERIPHERALS = 11;
    public static final int PERMISSION_PKI = 12;
    public static final int PERMISSION_POLICY = 13;
    public static final int PERMISSION_TBOX = 14;
    public static final int PERMISSION_THEME = 15;
    public static final int PERMISSION_TPMS = 16;
    public static final int PERMISSION_VEHICLE = 17;
    public static final int PERMISSION_WIFIAP = 18;
    public static final int PERMISSION_WPC = 19;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Permissions {
    }

    boolean enablePermissionModule(Context context, String str, boolean z);

    boolean isPermissionModuleEnabled();

    void releasePermissionListener(IPermissionListener iPermissionListener);

    void requestPermission(Context context, String str, IPermissionListener iPermissionListener);

    void requestPermission(Context context, String str, int[] iArr, IPermissionListener iPermissionListener);
}
