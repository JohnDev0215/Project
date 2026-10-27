package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class FactoryCheck8155Receiver extends BroadcastReceiver {
    public static String TAG = "NEU_FactoryCheck8155Receiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        String action = intent.getAction();
        if (action.equals(Constant.ACTION_FACTORY_VOICE_8155)) {
            RLog.e(TAG, "onReceive: " + action);
            VoiceAssistantManager.get().startFactoryVoiceCheckProcess8155();
        }
    }
}
