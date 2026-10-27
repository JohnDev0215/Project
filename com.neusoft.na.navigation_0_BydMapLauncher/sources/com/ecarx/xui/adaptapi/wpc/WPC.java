package com.ecarx.xui.adaptapi.wpc;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import com.ecarx.xui.adaptapi.wpc.impl.WPCImpl;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WPC extends AdaptAPI {
    public static final int CHARGING_STATUS_CHARGING = 2;
    public static final int CHARGING_STATUS_ERROR = 5;
    public static final int CHARGING_STATUS_FULLY_CHARGED = 3;
    public static final int CHARGING_STATUS_NO_DEVICE = 1;
    public static final int CHARGING_STATUS_OVERHEAT_OR_FOD = 4;
    public static final int CHARGING_STATUS_TAKE_MOBILE_DEVICE = 6;
    public static final int WORKING_MODE_AUTO = 2;
    public static final int WORKING_MODE_OFF = 1;

    public interface StateListener {
        void onChargingStatus(int i);

        void onWorkingMode(int i);
    }

    public abstract int getChargingStatus();

    public abstract int getWorkingMode();

    public abstract FunctionStatus isWPCSupported();

    public abstract void setStateListener(StateListener stateListener);

    public abstract int setWorkingMode(int i);

    public static WPC create(Context context) {
        if (!SignVerify.instance().appVerify(context)) {
            return null;
        }
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(108);
        Log.d("WPC", "hasWirelessCharge=" + z);
        if (z) {
            return new WPCImpl(context);
        }
        return null;
    }
}
