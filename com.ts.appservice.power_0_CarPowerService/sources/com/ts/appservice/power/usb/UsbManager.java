package com.ts.appservice.power.usb;

import android.content.Context;
import android.os.SystemProperties;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class UsbManager {
    private static final String TAG = UsbManager.class.getSimpleName();
    private static final int UNKNOW = -1;
    private static final String USB1_KEY = "sys.usb1.enable";
    public static final int USB1_TYPE = 1;
    private static final String USB2_KEY = "sys.usb2.enable";
    public static final int USB2_TYPE = 2;
    public static final String USB_DISABLE = "0";
    public static final String USB_ENABLE = "1";
    private static UsbManager sInstance;
    private Context mContext;

    public static UsbManager getInstance(Context context) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (UsbManager.class) {
                if (sInstance == null) {
                    sInstance = new UsbManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private UsbManager(Context context) {
        LogUtil.info(TAG, "TsUsbManager");
        this.mContext = context;
    }

    public int getUsbState(int type) {
        LogUtil.info(TAG, "getUsbState type : " + type);
        if (1 == type) {
            int state = SystemProperties.getInt(USB1_KEY, -1);
            LogUtil.info(TAG, "getUsbState usb1 state : " + state);
            return state;
        }
        if (2 == type) {
            int state2 = SystemProperties.getInt(USB2_KEY, -1);
            LogUtil.info(TAG, "getUsbState usb2 state : " + state2);
            return state2;
        }
        LogUtil.error(TAG, "getUsbState type error : " + type);
        return -1;
    }

    public void setUsbState(int type, String state) {
        LogUtil.info(TAG, "setUsbState type : " + type + " state : " + state);
        if (1 == type) {
            SystemProperties.set(USB1_KEY, state);
            LogUtil.info(TAG, "setUsbState usb1 state : " + state);
            return;
        }
        if (2 == type) {
            SystemProperties.set(USB2_KEY, state);
            LogUtil.info(TAG, "setUsbState usb2 state : " + state);
            return;
        }
        LogUtil.error(TAG, "setUsbState type error : " + type);
    }
}
