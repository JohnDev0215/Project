package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.eventbus.NavigationEvent;
import com.byd.vrassistant.utils.RLog;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationReceiver extends BroadcastReceiver {
    public static String TAG = "NEU_NavigationReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        RLog.i(TAG, "onReceive: action==" + action);
        if (action != null) {
            if (action.equals(Constant.TELENAV_NAVNOTIFICATION)) {
                boolean booleanExtra = intent.getBooleanExtra("navigation_ready_for_binding", false);
                RLog.i(TAG, "onReceive: isReadyForBinding==" + booleanExtra);
                EventBus.getDefault().post(new NavigationEvent(booleanExtra, 0));
                return;
            }
            return;
        }
        RLog.i(TAG, "onReceive: action==null");
    }
}
