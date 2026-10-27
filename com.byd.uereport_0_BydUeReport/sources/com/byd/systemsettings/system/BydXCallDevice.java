package com.byd.systemsettings.system;

import android.content.Context;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;
import android.provider.Settings;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydXCallDevice {
    public static final int DEF_VALUE = -1;
    public static final String E_CALL_STATE = "byd_ecall_window_event";
    public static final String TAG_B = "BydXCallDevice";
    public static final int XCALL_NOT_SHOW = 0;
    public static final int XCALL_SHOWING = 1;
    public static final int XCALL_STATE_END = 8;
    public static final int XCALL_STATE_START = 1;
    public static final int XCALL_TYPE_B_CALL = 2;
    public static final int XCALL_TYPE_E_CALL = 3;
    public static final int XCALL_TYPE_NO_CALL = 1;
    private BYDAutoXCallDevice xcall;

    public static class Singleton {
        private static final BydXCallDevice instance = new BydXCallDevice();

        public static BydXCallDevice get() {
            return instance;
        }
    }

    public int getCallStatus() {
        return this.xcall.getCallStatus();
    }

    public int getCallType() {
        return this.xcall.getCallType();
    }

    public int getEcallState(Context context) {
        if (context == null) {
            return -1;
        }
        return Settings.System.getInt(context.getContentResolver(), E_CALL_STATE, -1);
    }

    public void init(Context context) {
        this.xcall = BYDAutoXCallDevice.getInstance(context);
        a.a(TAG_B, "---init--- ");
    }

    public boolean isOnECalling(Context context) {
        int ecallState = getEcallState(context);
        a.a(TAG_B, "---isOnECalling---value: " + ecallState);
        if (ecallState != -1) {
            return ecallState == 1;
        }
        int callType = this.xcall.getCallType();
        a.a(TAG_B, "---isOnECalling---type: " + callType);
        if (3 != callType) {
            return false;
        }
        int callStatus = this.xcall.getCallStatus();
        a.a(TAG_B, "---isOnECalling---state: " + callStatus);
        return callStatus >= 1 && callStatus < 8;
    }

    private BydXCallDevice() {
    }
}
