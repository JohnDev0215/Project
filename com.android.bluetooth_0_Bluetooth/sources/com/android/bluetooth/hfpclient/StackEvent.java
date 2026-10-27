package com.android.bluetooth.hfpclient;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class StackEvent {
    public static final int EVENT_TYPE_AUDIO_STATE_CHANGED = 2;
    public static final int EVENT_TYPE_BATTERY_LEVEL = 7;
    public static final int EVENT_TYPE_CALL = 9;
    public static final int EVENT_TYPE_CALLHELD = 11;
    public static final int EVENT_TYPE_CALLSETUP = 10;
    public static final int EVENT_TYPE_CALL_WAITING = 14;
    public static final int EVENT_TYPE_CLIP = 13;
    public static final int EVENT_TYPE_CMD_RESULT = 17;
    public static final int EVENT_TYPE_CONNECTION_STATE_CHANGED = 1;
    public static final int EVENT_TYPE_CURRENT_CALLS = 15;
    public static final int EVENT_TYPE_IN_BAND_RINGTONE = 19;
    public static final int EVENT_TYPE_NETWORK_SIGNAL = 6;
    public static final int EVENT_TYPE_NETWORK_STATE = 4;
    public static final int EVENT_TYPE_NONE = 0;
    public static final int EVENT_TYPE_OPERATOR_NAME = 8;
    public static final int EVENT_TYPE_RESP_AND_HOLD = 12;
    public static final int EVENT_TYPE_RING_INDICATION = 21;
    public static final int EVENT_TYPE_ROAMING_STATE = 5;
    public static final int EVENT_TYPE_SUBSCRIBER_INFO = 18;
    public static final int EVENT_TYPE_UNKNOWN_EVENT = 22;
    public static final int EVENT_TYPE_VOLUME_CHANGED = 16;
    public static final int EVENT_TYPE_VR_STATE_CHANGED = 3;
    public static final int STACK_EVENT = 100;
    public int type;
    public int valueInt = 0;
    public int valueInt2 = 0;
    public int valueInt3 = 0;
    public int valueInt4 = 0;
    public String valueString = null;
    public BluetoothDevice device = null;

    StackEvent(int type) {
        this.type = 0;
        this.type = type;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("StackEvent {type:" + eventTypeToString(this.type));
        result.append(", value1:" + this.valueInt);
        result.append(", value2:" + this.valueInt2);
        result.append(", value3:" + this.valueInt3);
        result.append(", value4:" + this.valueInt4);
        result.append(", string: \"" + this.valueString + "\"");
        result.append(", device:" + this.device.getAddressOnlyforLog() + "}");
        return result.toString();
    }

    private static String eventTypeToString(int type) {
        if (type == 0) {
            return "EVENT_TYPE_NONE";
        }
        if (type == 1) {
            return "EVENT_TYPE_CONNECTION_STATE_CHANGED";
        }
        if (type == 2) {
            return "EVENT_TYPE_AUDIO_STATE_CHANGED";
        }
        if (type != 21) {
            switch (type) {
                case 4:
                    return "EVENT_TYPE_NETWORK_STATE";
                case 5:
                    return "EVENT_TYPE_ROAMING_STATE";
                case 6:
                    return "EVENT_TYPE_NETWORK_SIGNAL";
                case 7:
                    return "EVENT_TYPE_BATTERY_LEVEL";
                case 8:
                    return "EVENT_TYPE_OPERATOR_NAME";
                case 9:
                    return "EVENT_TYPE_CALL";
                case 10:
                    return "EVENT_TYPE_CALLSETUP";
                case 11:
                    return "EVENT_TYPE_CALLHELD";
                case 12:
                    return "EVENT_TYPE_RESP_AND_HOLD";
                case 13:
                    return "EVENT_TYPE_CLIP";
                case 14:
                    return "EVENT_TYPE_CALL_WAITING";
                case 15:
                    return "EVENT_TYPE_CURRENT_CALLS";
                case 16:
                    return "EVENT_TYPE_VOLUME_CHANGED";
                case 17:
                    return "EVENT_TYPE_CMD_RESULT";
                case 18:
                    return "EVENT_TYPE_SUBSCRIBER_INFO";
                default:
                    return "EVENT_TYPE_UNKNOWN:" + type;
            }
        }
        return "EVENT_TYPE_RING_INDICATION";
    }
}
