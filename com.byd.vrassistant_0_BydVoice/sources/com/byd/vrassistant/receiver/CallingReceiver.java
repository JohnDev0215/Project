package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes2.dex */
public class CallingReceiver extends BroadcastReceiver {
    private static final String TAG = TagUtil.getTagName(CallingReceiver.class);
    private static boolean mIncomingFlag = false;
    private static String mIncomingNumber = null;
    public static boolean isCarplayCallRinging = false;
    public static boolean isCarplayCallSending = false;
    public static boolean isCarplayCallOthers = false;
    public static int carplayCallSendingState = 0;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        RLog.i(str, "recevier intent.action=" + action);
        if (action.equals(Constant.ACTION_OUTGOING_ACTION)) {
            RLog.i(str, "Call OUT:" + intent.getStringExtra("android.intent.extra.PHONE_NUMBER"));
            return;
        }
        if (action.equals(Constant.ACTION_PHONE_STATE_ACTION_6125)) {
            int intExtra = intent.getIntExtra("phone_state", -1);
            RLog.i(str, "EXTRA_PHONE_CALL_STATE_TO_AV Value= " + intExtra);
            if (intExtra == 0) {
                RLog.i(str, "**********************挂断0==>正常状态**********************");
                if (PhoneHelper.getInstance().callState == PhoneHelper.phoneState.CALL_STATE_IDLE) {
                    RLog.i(str, "CALL_STATE_IDLE -- break");
                    return;
                }
                if (VoiceAssistantManager.get().isInitialited()) {
                    VoiceAssistantManager.get().startVoiceAssistant();
                    RLog.i(str, "del KEY_TEL_CONTROL");
                    VoiceAssistantManager.get().delShortcutCommand(VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL));
                }
                PhoneHelper.getInstance().setCallingState(false);
                PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_IDLE;
                return;
            }
            if (intExtra != 1) {
                if (intExtra != 2) {
                    return;
                }
                RLog.i(str, "**********************通话2**********************");
                if (VoiceAssistantManager.get().isInitialited()) {
                    VoiceAssistantManager.get().stopVoiceAssistant();
                }
                PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_OFFHOOK;
                return;
            }
            RLog.i(str, "**********************来电话1**********************");
            if (VoiceAssistantManager.get().isInitialited()) {
                RLog.i(str, "add KEY_TEL_CONTROL");
                VoiceAssistantManager.get().processTelShortCutCommand();
                VoiceAssistantManager.get().sendCancelPTTEvent();
            }
            PhoneHelper.getInstance().setCallingState(true);
            PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_RINGING;
            return;
        }
        if (action.equals(Constant.ACTION_CARPLAY_CALLING)) {
            int intExtra2 = intent.getIntExtra("phone_state", -1);
            int intExtra3 = intent.getIntExtra("state", -1);
            RLog.i(str, "EXTRA_PHONE_CALL_STATE_TO_AV phone_state= " + intExtra2);
            RLog.i(str, "EXTRA_PHONE_CALL_STATE_TO_AV state= " + intExtra3);
            carplayCallSendingState = intExtra3;
            switch (intExtra3) {
                case 0:
                    isCarplayCallRinging = false;
                    isCarplayCallSending = false;
                    RLog.i(str, "del KEY_TEL_CONTROL");
                    VoiceAssistantManager.get().delShortcutCommand(VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL));
                    break;
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                    isCarplayCallSending = true;
                    isCarplayCallRinging = false;
                    VoiceAssistantManager.get().sendCancelPTTEvent();
                    break;
                case 2:
                    isCarplayCallSending = false;
                    isCarplayCallRinging = true;
                    if (VoiceAssistantManager.get().isInitialited()) {
                        RLog.i(str, "add KEY_TEL_CONTROL");
                        VoiceAssistantManager.get().processTelShortCutCommand();
                        VoiceAssistantManager.get().sendCancelPTTEvent();
                    }
                    break;
            }
        }
    }
}
