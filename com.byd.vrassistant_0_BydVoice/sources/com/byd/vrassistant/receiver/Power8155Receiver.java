package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.BYDVMUtils;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.bean.VRPowerStateBean;
import com.byd.vrassistant.utils.RLog;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class Power8155Receiver extends BroadcastReceiver {
    public static String TAG = "NEU_Power8155Receiver";
    String ACTION = Constant.ACTION_CAR_POWER_STATE;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(this.ACTION)) {
            boolean booleanExtra = intent.getBooleanExtra("power_mode", false);
            RLog.e(TAG, "onReceive: " + action + "==power_mode==" + booleanExtra);
            BYDVMUtils.getInstance().getBYDVMViewModel().setPowerState(booleanExtra);
            VRPowerStateBean vRPowerStateBean = new VRPowerStateBean();
            vRPowerStateBean.setLock(booleanExtra);
            EventBus.getDefault().post(vRPowerStateBean);
        }
    }
}
