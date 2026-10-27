package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetStackEvent extends HeadsetMessageObject {
    public static final int EVENT_TYPE_ANSWER_CALL = 4;
    public static final int EVENT_TYPE_AT_CHLD = 10;
    public static final int EVENT_TYPE_AT_CIND = 12;
    public static final int EVENT_TYPE_AT_CLCC = 14;
    public static final int EVENT_TYPE_AT_COPS = 13;
    public static final int EVENT_TYPE_AUDIO_STATE_CHANGED = 2;
    public static final int EVENT_TYPE_BIA = 20;
    public static final int EVENT_TYPE_BIEV = 19;
    public static final int EVENT_TYPE_BIND = 18;
    public static final int EVENT_TYPE_CONNECTION_STATE_CHANGED = 1;
    public static final int EVENT_TYPE_DIAL_CALL = 7;
    public static final int EVENT_TYPE_HANGUP_CALL = 5;
    public static final int EVENT_TYPE_KEY_PRESSED = 16;
    public static final int EVENT_TYPE_NOISE_REDUCTION = 9;
    public static final int EVENT_TYPE_NONE = 0;
    public static final int EVENT_TYPE_SEND_DTMF = 8;
    public static final int EVENT_TYPE_SUBSCRIBER_NUMBER_REQUEST = 11;
    public static final int EVENT_TYPE_UNKNOWN_AT = 15;
    public static final int EVENT_TYPE_VOLUME_CHANGED = 6;
    public static final int EVENT_TYPE_VR_STATE_CHANGED = 3;
    public static final int EVENT_TYPE_WBS = 17;
    public final BluetoothDevice device;
    public final int type;
    public final int valueInt;
    public final int valueInt2;
    public final HeadsetMessageObject valueObject;
    public final String valueString;

    public HeadsetStackEvent(int type, BluetoothDevice device) {
        this(type, 0, 0, null, null, device);
    }

    public HeadsetStackEvent(int type, int valueInt, BluetoothDevice device) {
        this(type, valueInt, 0, null, null, device);
    }

    public HeadsetStackEvent(int type, int valueInt, int valueInt2, BluetoothDevice device) {
        this(type, valueInt, valueInt2, null, null, device);
    }

    public HeadsetStackEvent(int type, String valueString, BluetoothDevice device) {
        this(type, 0, 0, valueString, null, device);
    }

    public HeadsetStackEvent(int type, HeadsetMessageObject valueObject, BluetoothDevice device) {
        this(type, 0, 0, null, valueObject, device);
    }

    public HeadsetStackEvent(int type, int valueInt, int valueInt2, String valueString, HeadsetMessageObject valueObject, BluetoothDevice device) {
        this.type = type;
        this.valueInt = valueInt;
        this.valueInt2 = valueInt2;
        this.valueString = valueString;
        this.valueObject = valueObject;
        Objects.requireNonNull(device);
        this.device = device;
    }

    public String getTypeString() {
        switch (this.type) {
            case 0:
                return "EVENT_TYPE_NONE";
            case 1:
                return "EVENT_TYPE_CONNECTION_STATE_CHANGED";
            case 2:
                return "EVENT_TYPE_AUDIO_STATE_CHANGED";
            case 3:
                return "EVENT_TYPE_VR_STATE_CHANGED";
            case 4:
                return "EVENT_TYPE_ANSWER_CALL";
            case 5:
                return "EVENT_TYPE_HANGUP_CALL";
            case 6:
                return "EVENT_TYPE_VOLUME_CHANGED";
            case 7:
                return "EVENT_TYPE_DIAL_CALL";
            case 8:
                return "EVENT_TYPE_SEND_DTMF";
            case 9:
                return "EVENT_TYPE_NOISE_REDUCTION";
            case 10:
                return "EVENT_TYPE_AT_CHLD";
            case 11:
                return "EVENT_TYPE_SUBSCRIBER_NUMBER_REQUEST";
            case 12:
                return "EVENT_TYPE_AT_CIND";
            case 13:
                return "EVENT_TYPE_AT_COPS";
            case 14:
                return "EVENT_TYPE_AT_CLCC";
            case 15:
                return "EVENT_TYPE_UNKNOWN_AT";
            case 16:
                return "EVENT_TYPE_KEY_PRESSED";
            case 17:
                return "EVENT_TYPE_WBS";
            case 18:
                return "EVENT_TYPE_BIND";
            case 19:
                return "EVENT_TYPE_BIEV";
            case 20:
                return "EVENT_TYPE_BIA";
            default:
                return "UNKNOWN";
        }
    }

    @Override // com.android.bluetooth.hfp.HeadsetMessageObject
    public void buildString(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        builder.append(getTypeString());
        builder.append("[");
        builder.append(this.type);
        builder.append("]");
        builder.append(", valInt=");
        builder.append(this.valueInt);
        builder.append(", valInt2=");
        builder.append(this.valueInt2);
        builder.append(", valString=");
        builder.append(this.valueString);
        builder.append(", valObject=");
        builder.append(this.valueObject);
        builder.append(", device=");
        builder.append(this.device);
    }
}
