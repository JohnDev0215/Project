package cerence.ark.assistant.controller.domain.cc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface CCRequestsEnum {
    public static final String CANCEL = "cancel";
    public static final String CHECK_FUNCTION_AVAILABILITY = "checkFunctionAvailability";
    public static final String CHECK_VOLUME_CONTROL_AVAILABILITY = "checkVolumeControlAvailability";
    public static final String CLOSE_APP = "closeApp";
    public static final String CONTROL_BLUETOOTH = "controlBluetooth";
    public static final String CONTROL_VOLUME = "controlVolume";
    public static final String CONTROL_WIFI = "controlWifi";
    public static final String GET_ACTIVE_STREAM_TYPE = "getActiveStreamType";
    public static final String GET_SCREEN_BRIGHTNESS = "getScreenBrightness";
    public static final String GET_SCREEN_STATE = "getScreenState";
    public static final String GET_VOLUME = "getVolume";
    public static final String IS_FOREGROUND_APP = "isForegroundApp";
    public static final String OPEN_APP = "openApp";
    public static final String SET_SCREEN_BRIGHTNESS = "setScreenBrightness";
    public static final String SET_SCREEN_STATE = "setScreenState";
    public static final String SET_VOLUME = "setVolume";
}
