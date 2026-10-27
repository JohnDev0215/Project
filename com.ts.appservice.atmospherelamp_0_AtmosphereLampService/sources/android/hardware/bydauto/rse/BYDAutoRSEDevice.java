package android.hardware.bydauto.rse;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoRSEDevice extends AbsBYDAutoDevice {
    public static final int CLOSE_SUCCESS = 2;
    public static final int FB_ITEM_AUDIO_SWITCH = 9;
    public static final int FB_ITEM_KID_MODE = 7;
    public static final int FB_ITEM_MONITOR_SWITCH = 8;
    public static final int FB_ITEM_SCREEN_OFF = 10;
    public static final int IVI_ALLOW_FOLLOW_LINK = 1;
    public static final int IVI_CAST_SCREEN_TYPE_INVALID = 0;
    public static final int IVI_CAST_SCREEN_TYPE_SINK = 2;
    public static final int IVI_CAST_SCREEN_TYPE_SOURCE = 1;
    public static final int IVI_ENCRYPTION_INVALID = 0;
    public static final int IVI_ENCRYPTION_WPA2_PSK = 1;
    public static final int IVI_HOTPOST_LINK_LIMIT_INTIAL_VALUE = 0;
    public static final int IVI_HOTPOST_LINK_LIMIT_MAX = 10;
    public static final int IVI_HOTPOST_LINK_LIMIT_MIN = 1;
    public static final int IVI_HOTPOST_RESET_INVALID = 0;
    public static final int IVI_HOTPOST_RESET_VALID = 1;
    public static final int IVI_HOTPOST_STATE_CLOSED = 2;
    public static final int IVI_HOTPOST_STATE_INVALID = 0;
    public static final int IVI_HOTPOST_STATE_OPEN = 1;
    public static final int IVI_KARO_STATE_CLOSED = 0;
    public static final int IVI_KARO_STATE_IVI = 1;
    public static final int IVI_KARO_STATE_IVI_LEFT_RSE = 3;
    public static final int IVI_KARO_STATE_IVI_RIGHT_RSE = 2;
    public static final int IVI_KARO_STATE_LEFT_RIGHT_RSE = 5;
    public static final int IVI_KARO_STATE_LEFT_RSE = 4;
    public static final int IVI_KARO_STATE_LEFT_RSE_IVI = 6;
    public static final int IVI_KARO_STATE_RIGHT_LEFT_RSE = 8;
    public static final int IVI_KARO_STATE_RIGHT_RSE = 7;
    public static final int IVI_KARO_STATE_RIGHT_RSE_IVI = 9;
    public static final int IVI_KARO_WAY_INVALID = 0;
    public static final int IVI_KARO_WAY_IVI_KARO = 1;
    public static final int IVI_KARO_WAY_LEFT_RSE_IVI = 2;
    public static final int IVI_KARO_WAY_RIGHT_RSE_IVI = 3;
    public static final int IVI_NOT_ALLOW_FOLLOW_LINK = 0;
    public static final int IVI_NO_ENCRYPTION = 2;
    public static final int IVI_SET_TYPE_FOLLOW_LINK = 1;
    public static final int IVI_SET_TYPE_HOTPOST = 0;
    public static final int IVI_WIFI_STATE_EXTERNAL_HOTPOST = 2;
    public static final int IVI_WIFI_STATE_HOTPOST = 1;
    public static final int IVI_WIFI_STATE_INVALID = 0;
    public static final int LEFT_RSE = 0;
    public static final int MODE_CLOSE = 0;
    public static final int MODE_OPEN = 1;
    public static final int OPEN_SUCCESS = 1;
    public static final int REQUEST_IVI_EXIT_AUDIO = 3;
    public static final int REQUEST_IVI_INVALID = 0;
    public static final int REQUEST_IVI_SYNC_STATE = 1;
    public static final int REQUEST_IVI_USE_AUDIO = 2;
    public static final int RIGHT_RSE = 1;
    public static final int RSE_COMMAND_BUSY = -2147482647;
    public static final int RSE_COMMAND_FAILED = -2147482648;
    public static final int RSE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int RSE_COMMAND_SUCCESS = 0;
    public static final int RSE_COMMAND_TIMEOUT = -2147482646;
    protected static final String TAG = "BYDAutoRSEDevice";

    BYDAutoRSEDevice(Context con) {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoRSEDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    public int sendKidModeToRSE(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendAudioCtlModeToRSE(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendMonitorModeToRSE(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendLinkCtrlModeToRSE(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendScreenOffModeToRSE(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public int getWriteFeedbackFromRse(int source, int item) {
        throw new RuntimeException("Stub!");
    }

    public int sendHotpostResetToRSE(int result) {
        throw new RuntimeException("Stub!");
    }

    public int sendIVIHotpostStateToRSE(int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendFollowLinkToRSE(int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendHotpostLinkLimitToRSE(int value) {
        throw new RuntimeException("Stub!");
    }

    public int sendEncryptionToRSE(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendCastDiffToRSE(int source, int role) {
        throw new RuntimeException("Stub!");
    }

    public int sendStartKaraokeMethodToRSE(int method) {
        throw new RuntimeException("Stub!");
    }

    public int sendHotpostUsernameToRSE(byte[] username) {
        throw new RuntimeException("Stub!");
    }

    public int sendHotpostPasswordToRSE(byte[] password) {
        throw new RuntimeException("Stub!");
    }

    public int sendFollowLinkUsernameToRSE(byte[] username) {
        throw new RuntimeException("Stub!");
    }

    public int sendFollowLinkPasswordToRSE(byte[] password) {
        throw new RuntimeException("Stub!");
    }

    public int sendMacAddressToRSE(byte[] mac) {
        throw new RuntimeException("Stub!");
    }

    public int sendIVIWifiStateToRSE(int state) {
        throw new RuntimeException("Stub!");
    }

    public int sendKaraokeStateToRSE(int state) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRSEListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRSEListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoRSEListener l) {
        throw new RuntimeException("Stub!");
    }
}
