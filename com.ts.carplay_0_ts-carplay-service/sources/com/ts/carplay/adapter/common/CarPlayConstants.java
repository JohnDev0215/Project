package com.ts.carplay.adapter.common;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayConstants {

    public static final class AudioFocusReqResult {
        public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;
        public static final int AUDIOFOCUS_REQUEST_FAILED = 0;
        public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;
    }

    public static final class BluetoothState {
        public static final int STATE_OFF = 10;
        public static final int STATE_ON = 12;
        public static final int STATE_TURNING_OFF = 13;
        public static final int STATE_TURNING_ON = 11;
    }

    public static final class BorrowId {
        public static final String BORROW_ID_AUDIO_OFF = "audioOff";
        public static final String BORROW_ID_NATIVE_VR = "nativeVR";
        public static final String BORROW_ID_PHONE_CALL = "phoneCall";
        public static final String BORROW_ID_SCREEN_OFF = "screenOff";
        public static final String BORROW_ID_STANDBY = "standby";
        public static final String BORROW_ID_UE_ANNOUNCEMENT = "ueAnnouncement";
    }

    public static final class PriorityId {
        public static final int PRIORITY_LEVEL_CARPLAY_AUDIO = 2;
        public static final int PRIORITY_LEVEL_CARPLAY_AUXOUT = 8;
        public static final int PRIORITY_LEVEL_CARPLAY_NAVI = 5;
        public static final int PRIORITY_LEVEL_CARPLAY_PHONE = 4;
        public static final int PRIORITY_LEVEL_CARPLAY_RINGTONE = 9;
        public static final int PRIORITY_LEVEL_CARPLAY_SCREEN = 1;
        public static final int PRIORITY_LEVEL_CARPLAY_SIRI = 3;
        public static final int PRIORITY_LEVEL_HIGH = 6;
        public static final int PRIORITY_LEVEL_HIGHEST = 7;
        public static final int PRIORITY_LEVEL_NORMAL = 0;
    }

    public static final class VehicleGearMode {
        public static final int GEAR_DRIVE = 1;
        public static final int GEAR_NEUTRAL = 0;
        public static final int GEAR_PARK = 2;
        public static final int GEAR_REVERSE = 3;
    }

    public static final class WifiApState {
        public static final int WIFI_STATE_AP_DISABLED = 11;
        public static final int WIFI_STATE_AP_ENABLED = 13;
    }

    public static final class WifiSecurityType {
        public static final int WIFI_SECURITY_NONE = 0;
        public static final int WIFI_SECURITY_UNKNOWN = -1;
        public static final int WIFI_SECURITY_WEP = 1;
        public static final int WIFI_SECURITY_WPA = 2;
        public static final int WIFI_SECURITY_WPA_3_PERSONNAL_ONLY = 4;
        public static final int WIFI_SECURITY_WPA_3_PERSON_TRANSITION_MODE = 3;
    }

    public static final class AudioFocus {
        public static final int AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK = 1;
        public static final int AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK = 4;
        public static final int AUDIO_FOCUS_CARPLAY_AUXOUT = 6;
        public static final int AUDIO_FOCUS_CARPLAY_MEDIA = 0;
        public static final int AUDIO_FOCUS_CARPLAY_RINGTONE = 5;
        public static final int AUDIO_FOCUS_CARPLAY_TEL = 3;
        public static final int AUDIO_FOCUS_CARPLAY_TMP_MEDIA = 7;
        public static final int AUDIO_FOCUS_CARPLAY_VR = 2;

        public static String toString(int type) {
            if (type == 0) {
                return "AUDIO_FOCUS_CARPLAY_MEDIA";
            }
            if (type == 1) {
                return "AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK";
            }
            if (type == 2) {
                return "AUDIO_FOCUS_CARPLAY_VR";
            }
            if (type == 3) {
                return "AUDIO_FOCUS_CARPLAY_TEL";
            }
            if (type == 4) {
                return "AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK";
            }
            if (type == 5) {
                return "AUDIO_FOCUS_CARPLAY_RINGTONE";
            }
            if (type == 6) {
                return "AUDIO_FOCUS_CARPLAY_AUXOUT";
            }
            if (type == 7) {
                return "AUDIO_FOCUS_CARPLAY_TMP_MEDIA";
            }
            return "AUDIO_FOCUS_CARPLAY_UNKNOWN";
        }
    }
}
