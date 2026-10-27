package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class FactoryDataPackCheckReceiver extends BroadcastReceiver {
    public static String TAG = "NEU_FactoryDataPackCheckReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        String action = intent.getAction();
        if (action.equals(Constant.ACTION_FACTORY_VOICE_DP_CHECK)) {
            RLog.e(TAG, "onReceive: " + action);
            VoiceAssistantManager.get().startFactoryVoiceDataPackageCheck(intent.getStringExtra("Platform"), intent.getStringExtra("Countrycode"), intent.getStringExtra("DPPath"), intent.getStringExtra("FactoryConfigFile"));
        }
    }
}
