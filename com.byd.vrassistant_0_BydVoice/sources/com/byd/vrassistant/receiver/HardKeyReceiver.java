package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.FastClickUtils;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.view.ViewStateManger;

/* JADX INFO: loaded from: classes2.dex */
public class HardKeyReceiver extends BroadcastReceiver {
    public static String TAG = "NEU_HardKeyReceiver";
    String ACTION_MEDIA_VOICE_LAUNCHER = "com.intent.action.StartVoice_From_Launcher";
    String ACTION_MEDIA_VOICE_RECEIVER = "com.byd.action.come.from.launcher_Receiver";
    String ACTION_BACK_KEY_EVENT = "com.byd.vrassistant.ACTION_CANCEL";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        RLog.e(TAG, "onReceive: action=" + action);
        if (action.equals(this.ACTION_MEDIA_VOICE_LAUNCHER) && FastClickUtils.isValuableClick()) {
            VoiceAssistantManager.get().setHardKeyPTTEvent(false);
            VoiceAssistantManager.get().setVirtualKeyPTTEvent(true);
            VoiceAssistantManager.get().sendPTTEvent(VehicleAreaEnum.SEAT_ROW_FRONT);
            ViewStateManger.get().executeReShowOnDriverSide();
            if (!VoiceAssistantManager.get().getIsVoiceAssistantIniting()) {
                RLog.i(TAG, "getIsVoiceAssistantIniting = false");
                if (VoiceAssistantManager.get().getShowGuideResult() && !ForegroundAppUtil.isForegroundApp(BydVoiceApplication.getContext(), "com.byd.vrsettings")) {
                    SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
                }
            }
        }
        if (action.equals(this.ACTION_MEDIA_VOICE_RECEIVER) && !ForegroundAppUtil.isForegroundApp(BydVoiceApplication.getContext(), "com.byd.vrsettings")) {
            SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
        }
        if (action.equals(this.ACTION_BACK_KEY_EVENT)) {
            ViewStateManger.get().executeCancelPtt();
        }
    }
}
