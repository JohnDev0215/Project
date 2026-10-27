package com.android.bluetooth.avrcp;

import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.map.BluetoothMapContent;
import com.android.bluetooth.map.BluetoothMapContentObserver;

/* JADX INFO: loaded from: classes.dex */
class AvrcpPassthrough {
    AvrcpPassthrough() {
    }

    public static int toKeyCode(int operation) {
        switch (operation) {
            case 1:
                return 19;
            case 2:
                return 20;
            case 3:
                return 21;
            case 4:
                return 22;
            case 5:
                return 270;
            case 6:
                return 271;
            case 7:
                return 268;
            case 8:
                return 269;
            default:
                switch (operation) {
                    case 32:
                        return 144;
                    case 33:
                        return 145;
                    case 34:
                        return 146;
                    case 35:
                        return 147;
                    case 36:
                        return 148;
                    case 37:
                        return 149;
                    case 38:
                        return 150;
                    case 39:
                        return 151;
                    case 40:
                        return BluetoothStatsLog.BLUETOOTH_A2DP_PLAYBACK_STATE_CHANGED;
                    case 41:
                        return BluetoothStatsLog.BLUETOOTH_A2DP_CODEC_CONFIG_CHANGED;
                    case 42:
                        return BluetoothStatsLog.BLUETOOTH_DEVICE_FAILED_CONTACT_COUNTER_REPORTED;
                    case 43:
                        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                    case 44:
                        return 28;
                    default:
                        switch (operation) {
                            case 48:
                                return BluetoothStatsLog.BLUETOOTH_CLASSIC_PAIRING_EVENT_REPORTED;
                            case 49:
                                return BluetoothStatsLog.BLUETOOTH_SMP_PAIRING_EVENT_REPORTED;
                            case 50:
                                return 229;
                            default:
                                switch (operation) {
                                    case 52:
                                        return 178;
                                    case 53:
                                        return BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED;
                                    case 54:
                                        return 259;
                                    case 55:
                                        return 92;
                                    case 56:
                                        return 93;
                                    default:
                                        switch (operation) {
                                            case 64:
                                                return 26;
                                            case 65:
                                                return 24;
                                            case 66:
                                                return 25;
                                            case 67:
                                                return 91;
                                            case 68:
                                                return BluetoothStatsLog.BLUETOOTH_ACL_CONNECTION_STATE_CHANGED;
                                            case 69:
                                                return 86;
                                            case 70:
                                                return BluetoothStatsLog.BLUETOOTH_SCO_CONNECTION_STATE_CHANGED;
                                            case 71:
                                                return BluetoothMapContent.MMS_CC;
                                            case 72:
                                                return 89;
                                            case 73:
                                                return 90;
                                            case 74:
                                                return BluetoothMapContent.MMS_BCC;
                                            case 75:
                                                return 87;
                                            case 76:
                                                return 88;
                                            default:
                                                switch (operation) {
                                                    case 113:
                                                        return 131;
                                                    case 114:
                                                        return BluetoothMapContentObserver.MESSAGE_TYPE_RETRIEVE_CONF;
                                                    case 115:
                                                        return 133;
                                                    case 116:
                                                        return 134;
                                                    case 117:
                                                        return 135;
                                                    default:
                                                        return 0;
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
