package com.android.bluetooth.a2dp;

import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class A2dpStackEvent {
    static final int AUDIO_STATE_REMOTE_SUSPEND = 0;
    static final int AUDIO_STATE_STARTED = 2;
    static final int AUDIO_STATE_STOPPED = 1;
    static final int CONNECTION_STATE_CONNECTED = 2;
    static final int CONNECTION_STATE_CONNECTING = 1;
    static final int CONNECTION_STATE_DISCONNECTED = 0;
    static final int CONNECTION_STATE_DISCONNECTING = 3;
    public static final int EVENT_TYPE_AUDIO_STATE_CHANGED = 2;
    public static final int EVENT_TYPE_CODEC_CONFIG_CHANGED = 3;
    public static final int EVENT_TYPE_CONNECTION_STATE_CHANGED = 1;
    private static final int EVENT_TYPE_NONE = 0;
    public BluetoothCodecStatus codecStatus;
    public BluetoothDevice device;
    public int type;
    public int valueInt = 0;

    A2dpStackEvent(int type) {
        this.type = 0;
        this.type = type;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("A2dpStackEvent {type:" + eventTypeToString(this.type));
        result.append(", device:" + this.device.getAddressOnlyforLog());
        result.append(", value1:" + eventTypeValueIntToString(this.type, this.valueInt));
        if (this.codecStatus != null) {
            result.append(", codecStatus:" + this.codecStatus);
        }
        result.append("}");
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
        if (type == 3) {
            return "EVENT_TYPE_CODEC_CONFIG_CHANGED";
        }
        return "EVENT_TYPE_UNKNOWN:" + type;
    }

    private static String eventTypeValueIntToString(int type, int value) {
        if (type != 1) {
            if (type == 2) {
                if (value == 0) {
                    return "REMOTE_SUSPEND";
                }
                if (value != 1) {
                    if (value == 2) {
                        return "STARTED";
                    }
                } else {
                    return "STOPPED";
                }
            }
        } else {
            if (value == 0) {
                return "DISCONNECTED";
            }
            if (value != 1) {
                if (value == 2) {
                    return "CONNECTED";
                }
                if (value == 3) {
                    return "DISCONNECTING";
                }
            } else {
                return "CONNECTING";
            }
        }
        return Integer.toString(value);
    }
}
