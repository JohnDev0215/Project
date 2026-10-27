package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import cerence.ark.assistant.api.ArkAssistant;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.manager.AudioTrackManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceServiceUtils {
    private static final String TAG = "NEU_YZS";

    public static void init(Context context) {
        int vehicleType = SystemSettingManager.get().getVehicleType(context);
        RLog.e(TAG, "===VoiceServiceUtils======vehicleType ===" + vehicleType);
        if (!contains(Constant.limitVehicleType, vehicleType)) {
            RLog.i(TAG, "VehicleType error, cannot start vrassistant!");
            BydVoiceApplication.getContext().sendBroadcast(new Intent(Constant.ACTION_VOICE_PERMISSION_DENY));
            return;
        }
        try {
            RLog.i(TAG, "VoiceServiceUtils===VoiceAssistantManager.get().init()");
            if (!VoiceAssistantManager.get().isInitialited()) {
                SystemSettingManager.get().init();
                VoiceAssistantManager.get().setVehicleType(vehicleType);
                VoiceAssistantManager.get().init();
            } else {
                RLog.i(TAG, "VoiceServiceUtils===VoiceAssistantManager  is init,not init VoiceAssistantManager second");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initCerence(final Context context) {
        RLog.e(TAG, "===VoiceServiceUtils======init ===");
        BYDAutoBodyworkDevice.getInstance(context).registerListener(new AbsBYDAutoBodyworkListener() { // from class: com.byd.vrassistant.utils.VoiceServiceUtils.1
            @Override // android.hardware.IBYDAutoListener
            public void isConnected(boolean z) {
                super.isConnected(z);
                if (z) {
                    int vehicleType = SystemSettingManager.get().getVehicleType(context);
                    RLog.e(VoiceServiceUtils.TAG, "===SystemSettingManager======vehicleType ===" + vehicleType);
                    if (!VoiceServiceUtils.contains(Constant.limitVehicleType, vehicleType)) {
                        RLog.i(VoiceServiceUtils.TAG, "VehicleType error, cannot start vrassistant!");
                        BydVoiceApplication.getContext().sendBroadcast(new Intent(Constant.ACTION_VOICE_PERMISSION_DENY));
                        return;
                    }
                    try {
                        RLog.i(VoiceServiceUtils.TAG, "VoiceAssistantManager.get().init()");
                        if (!VoiceAssistantManager.get().isInitialited()) {
                            SystemSettingManager.get().init();
                            VoiceAssistantManager.get().setVehicleType(vehicleType);
                            VoiceAssistantManager.get().init();
                        } else {
                            RLog.i(VoiceServiceUtils.TAG, "VoiceAssistantManager  is init,not init VoiceAssistantManager second");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean contains(int[] iArr, int i) {
        if (iArr.length == 0) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static void onLowMemory() {
        RLog.i(TAG, "BydVoiceApplication ==onLowMemory");
        RLog.i(TAG, "BydVoiceApplication ==onLowMemory===== ArkAssistant.get().requestTrimMemory==" + ArkAssistant.get().requestTrimMemory());
    }

    public static void startSendNullTrack() {
        try {
            AudioTrackManager.getInstance().startPlay();
        } catch (Exception e) {
            RLog.i(TAG, "startSendNullTrack===RuntimeException");
            throw new RuntimeException(e);
        }
    }

    public static void endSendNullTrack() {
        AudioTrackManager.getInstance().stopPlay();
    }
}
