package com.byd.vrassistant.utils;

import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CallingReceiver8155 extends BroadcastReceiver {
    private static final String TAG = "NEU_ComingCallListenManager8155";
    private boolean isAddShortcut = false;
    Runnable startStopRunnerable = new Runnable() { // from class: com.byd.vrassistant.utils.CallingReceiver8155.1
        @Override // java.lang.Runnable
        public void run() {
            VoiceAssistantManager.get().startVoiceAssistant();
        }
    };
    Handler startStopHandle = new Handler(BydVoiceApplication.getContext().getMainLooper());

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BluetoothHeadsetClientCall parcelableExtra = intent.getParcelableExtra("android.bluetooth.headsetclient.extra.CALL");
        int state = parcelableExtra.getState();
        String number = parcelableExtra.getNumber();
        RLog.i(TAG, "callState = " + state);
        RLog.i(TAG, "mNumber  = " + pre(number));
        if (state == 0 || state == 1) {
            PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_OFFHOOK;
            if (PhoneHelper.getInstance().mCallingNumbers != null && !PhoneHelper.getInstance().mCallingNumbers.contains(number)) {
                RLog.i(TAG, "add callingList :" + pre(number));
                PhoneHelper.getInstance().mCallingNumbers.add(number);
            }
            if (VoiceAssistantManager.get().isInitialited()) {
                this.startStopHandle.removeCallbacks(this.startStopRunnerable);
                VoiceAssistantManager.get().stopVoiceAssistant();
            }
            RLog.d(TAG, "**********************通话**********************");
            return;
        }
        if (state == 2 || state == 3) {
            RLog.d(TAG, "**********************拨打电话**********************");
            if (VoiceAssistantManager.get().isInitialited()) {
                VoiceAssistantManager.get().sendCancelPTTEvent();
                VoiceAssistantManager.get().clearAllView();
                this.startStopHandle.removeCallbacks(this.startStopRunnerable);
                VoiceAssistantManager.get().stopVoiceAssistant();
                PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_DIALING;
                return;
            }
            return;
        }
        if (state == 4 || state == 5) {
            RLog.d(TAG, "**********************来电话**********************");
            PhoneHelper.getInstance().setCallingState(true);
            PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_RINGING;
            if (!VoiceAssistantManager.get().isInitialited() || this.isAddShortcut) {
                return;
            }
            VoiceAssistantManager.get().processTelShortCutCommand();
            VoiceAssistantManager.get().sendCancelPTTEvent();
            VoiceAssistantManager.get().clearAllView();
            this.isAddShortcut = true;
            return;
        }
        if (state != 7) {
            return;
        }
        RLog.i(TAG, "CALL_STATE_TERMINATED ");
        List<String> list = PhoneHelper.getInstance().mCallingNumbers;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                RLog.i(TAG, "mCallingNumber = " + it.next());
            }
        }
        if (PhoneHelper.getInstance().mCallingNumbers != null && PhoneHelper.getInstance().mCallingNumbers.contains(number)) {
            RLog.i(TAG, "del callingList :" + pre(number));
            PhoneHelper.getInstance().mCallingNumbers.remove(number);
            RLog.i(TAG, "剩余 电话数 : " + PhoneHelper.getInstance().mCallingNumbers.size());
            Iterator<String> it2 = PhoneHelper.getInstance().mCallingNumbers.iterator();
            while (it2.hasNext()) {
                RLog.i(TAG, "剩余电话 = " + it2.next());
            }
        }
        if (PhoneHelper.getInstance().mCallingNumbers == null || PhoneHelper.getInstance().mCallingNumbers.size() != 0) {
            return;
        }
        PhoneHelper.getInstance().setCallingState(false);
        PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_IDLE;
        if (VoiceAssistantManager.get().isInitialited()) {
            this.startStopHandle.removeCallbacks(this.startStopRunnerable);
            VoiceAssistantManager.get().stopVoiceAssistant();
            this.startStopHandle.postDelayed(this.startStopRunnerable, 1000L);
            RLog.d(TAG, "**********************挂断==>正常状态**********************");
            RLog.e(TAG, "del KEY_TEL_CONTROL");
            List<String> shortCutListByLanguage = VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL);
            if (this.isAddShortcut) {
                VoiceAssistantManager.get().delShortcutCommand(shortCutListByLanguage);
                this.isAddShortcut = false;
            }
        }
    }

    private String pre(String str) {
        if (str == null || str.length() <= 4) {
            return str;
        }
        String strSubstring = str.substring(str.length() - 4);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() - 4; i++) {
            sb.append("*");
        }
        return ((Object) sb) + strSubstring;
    }
}
