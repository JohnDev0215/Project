package com.ts.androidauto.fsdk.common;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceConst {
    public static final int AUX = 3;
    public static final int BTA = 2;
    public static final int DUCK_AUDIO_MODE_AAP = 2;
    public static final int ID_ANDROIDAUTO_MEDIA = 56;
    public static final int IPOD = 5;
    public static final int IPOD2 = 6;
    public static final int MTP = 8;
    public static final int MTP2 = 9;
    public static final int NONE = -1;
    public static final String POPUP_BT_MD_AUTH_FAILED = "smartprojection_textview_popup_aa_md_auth_failed";
    public static final String POPUP_BUTTON_TEXT_NO = "smartprojection_button_text_no";
    public static final String POPUP_BUTTON_TEXT_OK = "smartprojection_button_text_ok";
    public static final String POPUP_BUTTON_TEXT_YES = "smartprojection_button_text_yes";
    public static final String POPUP_LOW_BATTERY = "smartprojection_textview_popup_aa_low_battery";
    public static final String POPUP_POOR_WIRELESS_NETWORK = "smartprojection_textview_popup_aa_poor_wireless_network";
    public static final String POPUP_USER_SWITCH_CAN_NOT_STARTED = "smartprojection_textview_popup_aa_switch_can_not_started";
    public static final String POPUP_USER_SWITCH_SECOND_FAILED = "smartprojection_textview_popup_aa_switch_can_not_started";
    public static final int RADIO_AM = 11;
    public static final int RADIO_FM = 13;
    public static final int SXM = 16;
    public static final int USB1 = 0;
    public static final int USB2 = 1;

    public enum AppState {
        APP_ON,
        APP_OFF
    }

    public enum AppType {
        NATIVE_PHONE,
        NATIVE_SIRI,
        NATIVE_NAVI,
        NATIVE_BTA,
        REAR_CAMERA,
        NATIVE_IPOD,
        NATIVE_ASR,
        NATIVE_SCREEN_OFF
    }

    public enum AudioFocusNotify {
        AUDIOFOCUS_GAIN,
        AUDIOFOCUS_VR_GAIN,
        AUDIOFOCUS_NAVI_GAIN,
        AUDIOFOCUS_LOSS,
        AUDIOFOCUS_LOSS_TRANSIENT,
        AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK,
        AUDIOFOCUS_PHONE_INTERRUPT_START,
        AUDIOFOCUS_PHONE_INTERRUPT_END
    }

    public enum AudioFocusReqResult {
        AUDIOFOCUS_REQUEST_FAILED,
        AUDIOFOCUS_REQUEST_GRANTED,
        AUDIOFOCUS_REQUEST_DELAYED
    }

    public enum AudioFocusRequest {
        AUDIOFOCUS_DEFAULT,
        AUDIOFOCUS_MEDIA_GAIN,
        AUDIOFOCUS_VR_GAIN,
        AUDIOFOCUS_NAVI_GAIN,
        AUDIOFOCUS_MEDIA_RELEASE,
        AUDIOFOCUS_NAVI_RELEASE,
        AUDIOFOCUS_VR_RELEASE
    }

    public enum AudioId {
        NATIVE_MEDIA,
        NATIVE_INTERRUPT,
        NATIVE_HIGHPRIORITY,
        PROJECTION_AAP_MEDIA,
        PROJECTION_AAP_VR,
        PROJECTION_AAP_VR_48k,
        PROJECTION_AAP_NAVI,
        PROJECTION_CARLIFE_MEDIA,
        PROJECTION_CARPLAY_MEDIA,
        PROJECTION_CARPLAY_ALERT,
        NO_APP,
        NATIVE_INFO_AUDIO
    }

    public enum DuckReqResult {
        DUCK_REQUEST_FAILED,
        DUCK_REQUEST_GRANTED
    }

    public enum LastVideoMode {
        PROJECTION_MODE,
        NATIVE_MODE
    }

    public enum MicChangeEvent {
        EVENT_NOT_GRANTED,
        EVENT_GRANTED,
        EVENT_RELEASE_NOTIFICATION,
        EVENT_ERROR
    }

    public enum MicFocusReqResult {
        MICFOCUS_REQUEST_FAILED,
        MICFOCUS_REQUEST_GRANTED,
        MICFOCUS_REQUEST_RELEASE_NOTIFICATION
    }

    public enum MicFocusRequest {
        MICFOCUS_REQUEST_DEFAULT,
        MICFOCUS_REQUEST_ANDROIDAUTO_VR,
        MICFOCUS_RELEASE_ANDROIDAUTO_VR
    }

    public enum PhoneDisplayStatus {
        STATUS_ON,
        STATUS_OFF
    }

    public enum PhoneStatus {
        STATUS_ON,
        STATUS_OFF
    }

    public enum PowerOffConfirmStatus {
        STATUS_ON,
        STATUS_OFF
    }

    public enum PowerStatus {
        STATUS_ON,
        STATUS_OFF
    }

    public enum ScreenId {
        NATIVE_MEDIA,
        PROJECTION_MEDIA,
        USB_MUSIC,
        BT_MUSIC,
        IPOD_MUSIC,
        RADIO_FM,
        RADIO_AM,
        NATIVE_PHONE_CALL,
        PROJECTION_PHONE_CALL,
        NATIVE_NAVI,
        PROJECTION_NAVI,
        NATIVE_VR,
        PROJECTION_VR,
        REAR_CAMERA,
        NO_APP
    }

    public enum ScreenPriorityId {
        PRIORITY_LEVEL_DEFAULT,
        PRIORITY_LEVEL_ONE,
        PRIORITY_LEVEL_TWO,
        PRIORITY_LEVEL_THREE,
        PRIORITY_LEVEL_FOUR,
        PRIORITY_LEVEL_FIVE,
        PRIORITY_LEVEL_SIX,
        PRIORITY_LEVEL_SEVEN,
        PRIORITY_LEVEL_EIGHT,
        PRIORITY_LEVEL_NINE,
        PRIORITY_LEVEL_TEN,
        PRIORITY_LEVEL_ELEVEN,
        PRIORITY_LEVEL_TWELEV,
        PRIORITY_LEVEL_CUSTOMIZE,
        PRIORITY_LEVEL_MAX
    }

    public enum ScreenStatus {
        STATUS_ON,
        STATUS_OFF
    }

    public enum VideoFocusReqResult {
        VIDEOFOCUS_REQUEST_FAILED,
        VIDEOFOCUS_REQUEST_GRANTED
    }

    public enum VideoFocusRequest {
        VIDEOFOCUS_REQUEST_NATIVE,
        VIDEOFOCUS_REQUEST_AA_480P,
        VIDEOFOCUS_REQUEST_AA_720P,
        VIDEOFOCUS_REQUEST_AA_1080P,
        VIDEOFOCUS_REQUEST_PROJECTED
    }

    public enum VideoFocusStatus {
        VIDEOFOCUS_STATUS_NATIVE,
        VIDEOFOCUS_STATUS_PROJECTED
    }
}
