package com.byd.vrassistant.utils;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.manager.VoiceAssistantManager;

/* JADX INFO: loaded from: classes2.dex */
public class DevicePairingReceiver extends BroadcastReceiver {
    private static final String TAG = "NEU_DevicePairingReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.i(TAG, "DevicePairingReceiver");
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        RLog.i(TAG, "deviceAddress :" + bluetoothDevice.getAddress());
        BluetoothDevice bluetoothDevice2 = PhoneHelper.getInstance().mBluetoothDevice;
        if (bluetoothDevice.getBondState() != 10 || bluetoothDevice2 == null) {
            return;
        }
        RLog.i(TAG, "device Unpair");
        if (PhoneHelper.getInstance().callState == PhoneHelper.phoneState.CALL_STATE_IDLE || !bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
            return;
        }
        PhoneHelper.getInstance().callState = PhoneHelper.phoneState.CALL_STATE_IDLE;
        PhoneHelper.getInstance().mCallingNumbers.clear();
        RLog.i(TAG, "startVoiceAssistant");
        VoiceAssistantManager.get().stopVoiceAssistant();
        VoiceAssistantManager.get().startVoiceAssistant();
        RLog.i(TAG, "del KEY_TEL_CONTROL");
        VoiceAssistantManager.get().delShortcutCommand(VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL));
    }
}
