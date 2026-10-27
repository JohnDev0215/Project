package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class HardKeyReceiverEx extends BroadcastReceiver {
    public static String ACTION_MEDIA_VOICE_BUTTON = "android.intent.action.MEDIA_VOICE";
    public static String TAG = "NEU_HardKeyReceiverEx";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.e(TAG, "onReceive: " + intent.getAction());
        if (!VoiceAssistantManager.get().getIsVoiceAssistantIniting()) {
            RLog.i(TAG, "getIsVoiceAssistantIniting = false");
            if (VoiceAssistantManager.get().getShowGuideResult() && !ForegroundAppUtil.isForegroundApp(BydVoiceApplication.getContext(), "com.byd.vrsettings") && !ForegroundAppUtil.isForegroundApp(BydVoiceApplication.getContext(), "com.byd.vrsettings")) {
                SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
            }
        }
        if (VoiceAssistantManager.get().getDialogEvent() == DialogEvent.Event.CONVERSATION_END) {
            if (!AudioMicReceiver.isMicRelease) {
                RLog.i(TAG, "isMicRelease = " + AudioMicReceiver.isMicRelease);
                return;
            }
            VoiceAssistantManager.get().setHardKeyPTTEvent(true);
            VoiceAssistantManager.get().setVirtualKeyPTTEvent(false);
            VoiceAssistantManager.get().sendPTTEvent(SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT);
            return;
        }
        VoiceAssistantManager.get().sendCancelPTTEvent();
    }
}
