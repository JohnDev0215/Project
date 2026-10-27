package com.byd.vrassistant.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.manager.VoiceAssistantManager;

/* JADX INFO: loaded from: classes2.dex */
public class XCallToolReceiver8155 extends BroadcastReceiver {
    private static final String TAG = "NEU_XCallToolReceiver8155";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.ACTION_XCALLDISMISS_ACTION_8155)) {
            RLog.i(TAG, "XCallToolReceiver8155,开启VR");
            PhoneHelper.getInstance().isEcallModel = false;
            VoiceAssistantManager.get().startVoiceAssistant();
        } else if (intent.getAction().equals(Constant.ACTION_XCALLSHOW_ACTION_8155)) {
            RLog.i(TAG, "XCallToolReceiver8155,关闭VR");
            VoiceAssistantManager.get().sendCancelPTTEvent();
            VoiceAssistantManager.get().stopVoiceAssistant();
            PhoneHelper.getInstance().isEcallModel = true;
        }
    }
}
