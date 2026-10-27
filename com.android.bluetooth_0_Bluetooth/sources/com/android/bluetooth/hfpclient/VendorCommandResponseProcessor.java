package com.android.bluetooth.hfpclient;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.INetd;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.ProfileService;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class VendorCommandResponseProcessor {
    private static final Map<String, Integer> SUPPORTED_VENDOR_AT_COMMANDS;
    private static final Map<String, Integer> SUPPORTED_VENDOR_EVENTS;
    private final NativeInterface mNativeInterface;
    private final HeadsetClientService mService;
    private static final String TAG = "VendorCommandResponseProcessor";
    private static final boolean DBG = Log.isLoggable(TAG, 3);

    static {
        HashMap map = new HashMap();
        SUPPORTED_VENDOR_AT_COMMANDS = map;
        map.put("+XAPL=", 76);
        SUPPORTED_VENDOR_AT_COMMANDS.put("+IPHONEACCEV=", 76);
        SUPPORTED_VENDOR_AT_COMMANDS.put("+APLSIRI?", 76);
        SUPPORTED_VENDOR_AT_COMMANDS.put("+APLEFM", 76);
        HashMap map2 = new HashMap();
        SUPPORTED_VENDOR_EVENTS = map2;
        map2.put("+APLSIRI:", 76);
        SUPPORTED_VENDOR_EVENTS.put("+XAPL=", 76);
    }

    VendorCommandResponseProcessor(HeadsetClientService context, NativeInterface nativeInterface) {
        this.mService = context;
        this.mNativeInterface = nativeInterface;
    }

    public boolean sendCommand(int vendorId, String atCommand, BluetoothDevice device) {
        String commandWord;
        if (device == null) {
            Log.w(TAG, "processVendorCommand device is null");
            return false;
        }
        int indexOfSemicolon = atCommand.indexOf(59);
        if (indexOfSemicolon > 0) {
            Log.e(TAG, "Do not support ; and more than one command:" + atCommand);
            return false;
        }
        int indexOfEqual = atCommand.indexOf(61);
        int indexOfQuestionMark = atCommand.indexOf(63);
        if (indexOfEqual > 0) {
            commandWord = atCommand.substring(0, indexOfEqual + 1);
        } else if (indexOfQuestionMark > 0) {
            commandWord = atCommand.substring(0, indexOfQuestionMark + 1);
        } else {
            commandWord = atCommand;
        }
        String commandWord2 = commandWord.replaceAll("\\s+", INetd.NEXTHOP_NONE);
        if (SUPPORTED_VENDOR_AT_COMMANDS.get(commandWord2) == Integer.valueOf(vendorId)) {
            if (!this.mNativeInterface.sendATCmd(Utils.getBytesFromAddress(device.getAddress()), 16, 0, 0, atCommand)) {
                Log.e(TAG, "Failed to send vendor specific at command");
                return false;
            }
            logD("Send vendor command: " + atCommand);
            return true;
        }
        Log.e(TAG, "Invalid command " + atCommand + ", " + vendorId + ". Cand=" + commandWord2);
        return false;
    }

    public boolean processEvent(String atString, BluetoothDevice device) {
        String eventCode;
        if (device == null) {
            Log.w(TAG, "processVendorEvent device is null");
            return false;
        }
        int indexOfEqual = atString.indexOf(61);
        int indexOfColon = atString.indexOf(58);
        if (indexOfEqual > 0) {
            eventCode = atString.substring(0, indexOfEqual + 1);
        } else if (indexOfColon > 0) {
            eventCode = atString.substring(0, indexOfColon + 1);
        } else {
            eventCode = atString;
        }
        String eventCode2 = eventCode.replaceAll("\\s+", INetd.NEXTHOP_NONE);
        Integer vendorId = SUPPORTED_VENDOR_EVENTS.get(eventCode2);
        if (vendorId == null) {
            Log.e(TAG, "Invalid response: " + atString + ". " + eventCode2);
            return false;
        }
        broadcastVendorSpecificEventIntent(vendorId.intValue(), eventCode2, atString, device);
        logD("process vendor event " + vendorId + ", " + eventCode2 + ", " + atString + " for device" + device);
        return true;
    }

    private void broadcastVendorSpecificEventIntent(int vendorId, String vendorEventCode, String vendorResponse, BluetoothDevice device) {
        logD("broadcastVendorSpecificEventIntent(" + vendorResponse + ")");
        Intent intent = new Intent("android.bluetooth.headsetclient.profile.action.VENDOR_SPECIFIC_EVENT");
        intent.putExtra("android.bluetooth.headsetclient.extra.VENDOR_ID", vendorId);
        intent.putExtra("android.bluetooth.headsetclient.extra.VENDOR_EVENT_CODE", vendorEventCode);
        intent.putExtra("android.bluetooth.headsetclient.extra.VENDOR_EVENT_FULL_ARGS", vendorResponse);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    private void logD(String msg) {
        if (DBG) {
            Log.d(TAG, msg);
        }
    }
}
