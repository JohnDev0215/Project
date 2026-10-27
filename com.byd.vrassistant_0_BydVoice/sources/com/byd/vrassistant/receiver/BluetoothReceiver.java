package com.byd.vrassistant.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.dynamic.ContentType;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothReceiver extends BroadcastReceiver {
    private static final String TAG = TagUtil.getTagName(BluetoothReceiver.class);
    public static boolean BT_CONNECTED = false;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.bluetooth.device.action.FOUND".equals(action)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice.getBondState() != 12) {
                PhoneHelper.getInstance().setBTRssi(intent.getExtras().getShort("android.bluetooth.device.extra.RSSI"));
                return;
            } else {
                if (bluetoothDevice.getBondState() == 10) {
                    PhoneHelper.getInstance().setBTRssi((short) 0);
                    return;
                }
                return;
            }
        }
        if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
            intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0);
            switch (intExtra) {
                case 10:
                    String str = TAG;
                    RLog.i(str, "STATE_OFF");
                    if (PhoneHelper.getInstance().callState != PhoneHelper.phoneState.CALL_STATE_IDLE) {
                        PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_IDLE;
                        PhoneHelper.getInstance().mCallingNumbers.clear();
                        RLog.i(str, "startVoiceAssistant");
                        VoiceAssistantManager.get().stopVoiceAssistant();
                        VoiceAssistantManager.get().startVoiceAssistant();
                        RLog.i(str, "del KEY_TEL_CONTROL");
                        VoiceAssistantManager.get().delShortcutCommand(VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL));
                    }
                    break;
                case 11:
                    RLog.i(TAG, "STATE_TURNING_ON");
                    break;
                case 12:
                    RLog.i(TAG, "STATE_ON");
                    break;
                case 13:
                    RLog.i(TAG, "STATE_TURNING_OFF");
                    break;
            }
        }
        if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", 0);
            intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", 0);
            if (intExtra2 != 0) {
                if (intExtra2 == 1) {
                    RLog.i(TAG, "STATE_CONNECTING");
                    return;
                }
                if (intExtra2 == 2) {
                    RLog.d(TAG, "STATE_CONNECTED, ");
                    BT_CONNECTED = true;
                    PhoneHelper.getInstance().connectService();
                    return;
                } else {
                    if (intExtra2 != 3) {
                        return;
                    }
                    RLog.d(TAG, "STATE_DISCONNECTING, ");
                    return;
                }
            }
            String str2 = TAG;
            RLog.d(str2, "STATE_DISCONNECTED, ");
            ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
            if (PhoneHelper.getInstance().callState != PhoneHelper.phoneState.CALL_STATE_IDLE) {
                PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_IDLE;
                PhoneHelper.getInstance().mCallingNumbers.clear();
                RLog.i(str2, "startVoiceAssistant");
                VoiceAssistantManager.get().stopVoiceAssistant();
                VoiceAssistantManager.get().startVoiceAssistant();
                RLog.i(str2, "del KEY_TEL_CONTROL");
                VoiceAssistantManager.get().delShortcutCommand(VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL));
            }
            BT_CONNECTED = false;
            return;
        }
        if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
            RLog.i(TAG, "ACTION_ACL_CONNECTED, ");
        } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
            RLog.d(TAG, "ACTION_ACL_DISCONNECTED, ");
        }
    }
}
