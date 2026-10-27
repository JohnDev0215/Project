package com.byd.auto_emanual.util;

import android.content.Context;
import android.graphics.Point;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public class DeviceUtil {
    public static final int LEFT_RUDDER = 0;
    public static final int RIGHT_RUDDER = 1;
    private static final String TAG = "DeviceUtil";
    private static boolean isRight;

    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point;
    }

    public static boolean isRightDriver() {
        isRight = Utils.getProperty(BYDAutoSysPropertiesDevice.RIGHT_RUDDER_VALUE, "false").equals("true");
        MLog.i(TAG, "isRightDriver = " + isRight);
        return isRight;
    }
}
