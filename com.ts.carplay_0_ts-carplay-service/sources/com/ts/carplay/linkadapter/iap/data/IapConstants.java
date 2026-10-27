package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public class IapConstants {

    public static final class BtProfiles {
        public static final int ADVANCED_AUDIO_DISTRIBUTION = 8;
        public static final int AUDIO_VIDEO_REMOTE_CONTROL = 4;
        public static final int HANDS_FREE = 1;
        public static final int HUMAN_INTERFACE_DEVICE = 16;
        public static final int IAP2_LINK = 32;
        public static final int MESSAGE_ACCESS = 128;
        public static final int PERSONALAREA_NETWORK_ACCESS_POINT = 64;
        public static final int PERSONALAREA_NETWORK_CLIENT = 256;
        public static final int PHONE_BOOK_ACCESS = 2;
    }

    public static final class PairingState {
        public static final int PAIRING_ALREADY_CONNECTED = 1;
        public static final int PAIRING_CANCEL = 2;
        public static final int PAIRING_SUCCESS = 0;
        public static final int PAIRING_UNKOWN_STATE = -1;
    }

    public static final class WifiChannelType {
        public static final int WIFI_CHANNEL_1 = 1;
        public static final int WIFI_CHANNEL_11 = 11;
        public static final int WIFI_CHANNEL_149 = 149;
        public static final int WIFI_CHANNEL_153 = 153;
        public static final int WIFI_CHANNEL_157 = 157;
        public static final int WIFI_CHANNEL_161 = 161;
        public static final int WIFI_CHANNEL_36 = 36;
        public static final int WIFI_CHANNEL_40 = 40;
        public static final int WIFI_CHANNEL_44 = 44;
        public static final int WIFI_CHANNEL_48 = 48;
        public static final int WIFI_CHANNEL_6 = 6;
        public static final int WIFI_CHANNEL_UNKNOWN = -1;
    }

    public static final class WifiSecurityType {
        public static final int WIFI_SECURITY_NONE = 0;
        public static final int WIFI_SECURITY_UNKNOWN = -1;
        public static final int WIFI_SECURITY_WEP = 1;
        public static final int WIFI_SECURITY_WPA = 2;
    }

    public static final class ClientType {
        public static final int CARLIFE_BT = 2;
        public static final int CARLIFE_USB_DEVICE = 0;
        public static final int CARLIFE_USB_HOST = 1;
        public static final int CARLIFE_WIFI = 3;
        public static final int CARPLAY_BT = 18;
        public static final int CARPLAY_USB_DEVICE = 16;
        public static final int CARPLAY_USB_HOST = 17;
        public static final int CARPLAY_WIFI = 19;
        public static final int NONE = 153;

        public static boolean isValidType(int value) {
            if (value != 0 && value != 1 && value != 2 && value != 3) {
                switch (value) {
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }
    }
}
