package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.view.ViewStateManger;

/* JADX INFO: loaded from: classes2.dex */
public class HomeListenerReceiver extends BroadcastReceiver {
    private String TAG = "NEU_HomeListenerReceiver";
    final String SYSTEM_DIALOG_REASON_KEY = MediaControl.PARAM_REASON;
    final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    final String SYSTEM_DIALOG_REASON_RECENT_KEY = "recentapps";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra(MediaControl.PARAM_REASON);
        RLog.d(this.TAG, "action:" + action + ",reason:" + stringExtra);
        if (!action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") || stringExtra == null) {
            return;
        }
        if (stringExtra.equals("homekey")) {
            if (SystemSettingManager.get().isSendingHomeKey()) {
                SystemSettingManager.get().setSendingHomeKey(false);
                return;
            } else {
                ViewStateManger.get().executeCancelPtt();
                return;
            }
        }
        stringExtra.equals("recentapps");
    }
}
