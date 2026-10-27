package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenOffReceiver extends BroadcastReceiver {
    public static String ACTION_POWERSCREEN_ENTER = "android.intent.action.ENTER_POWER_SCREEN";
    public static String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF_CLICK";
    public static final String EXIT_AUDIO_SCREEN_ACTION = "android.intent.action.CLOSE_AUDIO_SCREEN";
    public static String TAG = "NEU_ScreenOffReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ACTION_SCREEN_OFF) || action.equals(ACTION_POWERSCREEN_ENTER)) {
            RLog.e(TAG, "onReceive: " + action);
            VoiceAssistantManager.get().sendCancelPTTEventSilence();
        }
    }
}
