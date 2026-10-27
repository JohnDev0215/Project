package com.ts.qnxapp.fdbusClientForAndroid;

import android.os.Bundle;
import com.ts.qnxapp.util.InteractionToBundle;
import com.ts.qnxapp.util.LogUtil;
import com.ts.qnxapp.util.OtaToBundle;
import com.ts.qnxapp.util.TemperatureToBundle;
import ipc.fdbus.FdbusMessage;

/* JADX INFO: loaded from: classes.dex */
public class FdbusUtil {
    private static final int MAX_MSGTYPE_DIGIT = 15;
    private static final Object SYNCLOCK = new Object();
    private static final String TAG = "FdbusUtil";
    private static volatile FdbusUtil sInstance;
    private OtaToBundle mOtaToBundle = OtaToBundle.getInstance();
    private InteractionToBundle mInteractionToBundle = InteractionToBundle.getInstance();
    private TemperatureToBundle mTemperatureToBundle = TemperatureToBundle.getInstance();

    public FdbusUtil() {
        LogUtil.logI(TAG, "FdbusUtil new");
    }

    public static FdbusUtil getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (SYNCLOCK) {
            if (sInstance == null) {
                sInstance = new FdbusUtil();
            }
        }
        return sInstance;
    }

    public Bundle messageToBundle(FdbusMessage msg, int flag, int msgType) {
        Bundle bundle = new Bundle();
        if (msg.returnValue() != 0) {
            LogUtil.logE(TAG, "msg.returnValue error returnValue = " + msg.returnValue());
            return null;
        }
        if (msgType != 1 && msgType != 2 && msgType != 3) {
            switch (msgType) {
                case 8:
                    Bundle bundle2 = this.mOtaToBundle.messageOtaToBundle(msg, flag);
                    LogUtil.logW(TAG, "messageToBundle:  bundle = " + bundle2);
                    return bundle2;
                case 9:
                    break;
                case 10:
                    Bundle bundle3 = this.mTemperatureToBundle.messageTemperatureToBundle(msg, flag);
                    return bundle3;
                default:
                    return bundle;
            }
        }
        Bundle bundle4 = this.mInteractionToBundle.messageInteractionToBundle(msg, flag);
        LogUtil.logI(TAG, "messageToBundle:  bundle = " + bundle4);
        return bundle4;
    }

    public int commandTransact(int msgType, int subType) {
        int commandId = (subType << 15) | msgType;
        return commandId;
    }
}
