package com.byd.card.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class HomeBroadcastReceiver extends BroadcastReceiver {
    public static final String CLOSE_SYSTEM = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String TAG = "HomeBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(CLOSE_SYSTEM)) {
            Log.d(TAG, "onReceive ACTION_CLOSE_SYSTEM_DIALOGS");
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(intent.getStringExtra("reason"))) {
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HOME_KEY));
                SplitScreenHelpManager.getInstance(context).hideSplitNavView();
                return;
            }
            return;
        }
        if (action.equals("android.intent.action.USER_UNLOCKED")) {
            Log.w(TAG, "onReceive ACTION_USER_UNLOCKED");
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.USER_UNLOCKED));
        }
    }
}
