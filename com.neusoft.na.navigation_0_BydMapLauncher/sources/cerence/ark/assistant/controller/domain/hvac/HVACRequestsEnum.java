package cerence.ark.assistant.controller.domain.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface HVACRequestsEnum {
    public static final String CHECK_HVAC_FUNCTION_AVAILABILITY = "checkHvacFunctionAvailability";
    public static final String GET_ACTEMPERATURE = "getACTemperature";
    public static final String GET_DEFROSTER = "getDefroster";
    public static final String GET_FAN_DIRECTION = "getFanDirection";
    public static final String GET_HAVC_LEVEL = "getHavcLevel";
    public static final String GET_HVAC_STATE = "getHvacState";
    public static final String GET_MAX_HAVC_LEVEL = "getMaxHavcLevel";
    public static final String IS_ENGINE_ON_REQUIRED = "isEngineOnRequired";
    public static final String IS_ENGINE_STARTED = "isEngineStarted";
    public static final String IS_POWER_ON = "isPowerOn";
    public static final String IS_POWER_ON_REQUIRED = "isPowerOnRequired";
    public static final String SET_ACTEMPERATURE = "setACTemperature";
    public static final String SET_DEFROSTER = "setDefroster";
    public static final String SET_FAN_DIRECTION = "setFanDirection";
    public static final String SET_HVAC_LEVEL = "setHvacLevel";
    public static final String SET_HVAC_STATE = "setHvacState";
}
