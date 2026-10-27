package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class AudioMicReceiver extends BroadcastReceiver {
    private static final String TAG = "NEU_AudioMicReceiver";
    public static volatile boolean isCarplayRelease = true;
    public static volatile boolean isMicRelease = true;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Constant.ACTION_REQUEST_MIC.equals(action)) {
            String stringExtra = intent.getStringExtra("who");
            String stringExtra2 = intent.getStringExtra("usemic");
            if (stringExtra.equals("androidauto")) {
                isMicRelease = false;
                VoiceAssistantManager.get().sendCancelPTTEvent();
            } else if (stringExtra.equals("carplay")) {
                if (isMicRelease) {
                    VoiceAssistantManager.get().sendCancelPTTEvent();
                }
                isMicRelease = false;
                isCarplayRelease = false;
            }
            RLog.i(TAG, "receiver who[" + stringExtra + "] request_mic broadcast,useMic=" + stringExtra2);
            return;
        }
        if (Constant.ACTION_RELEASE_MIC.equals(action)) {
            String stringExtra3 = intent.getStringExtra("who");
            RLog.i(TAG, "receiver who[" + stringExtra3 + "] release_mic broadcast");
            if (stringExtra3.equals("carplay")) {
                isCarplayRelease = true;
                isMicRelease = true;
                return;
            } else {
                if (stringExtra3.equals("androidauto")) {
                    isMicRelease = true;
                    return;
                }
                return;
            }
        }
        if (Constant.ACTION_CARPLAY_CALLING.equals(action)) {
            RLog.i(TAG, "ACTION_CARPLAY_CALLING : android.broadcast.carplay.calling");
            VoiceAssistantManager.get().sendCancelPTTEvent();
        } else if (Constant.ACTION_AA_OPEN.equals(action)) {
            RLog.i(TAG, "ACTION_AA_OPEN : com.google.androidauto.start");
            VoiceAssistantManager.get().sendCancelPTTEvent();
        }
    }
}
