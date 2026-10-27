package cerence.ark.assistant.controller.domain.vehiclecontrol;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface VehicleControlRequestsEnum {
    public static final String CHECK_VEHICLE_FUNCTION_AVAILABILITY = "checkVehicleFunctionAvailability";
    public static final String CHECK_VEHICLE_STATUS = "checkVehicleStatus";
    public static final String CONTROL_HEADLIGHTS = "controlHeadlights";
    public static final String CONTROL_HIGH_BEAMS = "controlHighBeams";
    public static final String CONTROL_HUD = "controlHUD";
    public static final String CONTROL_LOCK = "controlLock";
    public static final String CONTROL_PANORAMIC_VIEW = "controlPanoramicView";
    public static final String CONTROL_SUNROOF = "controlSunroof";
    public static final String CONTROL_TRUNK = "controlTrunk";
    public static final String FIND_VEHICLE = "findVehicle";
    public static final String GET_AMBIENT_LIGHT_STATE = "getAmbientLightState";
    public static final String GET_MILEAGE_INFO = "getMileageInfo";
    public static final String GET_SUNROOF_STATE = "getSunroofState";
    public static final String GET_SUNSHADE_STATE = "getSunshadeState";
    public static final String GET_VEHICLE_CONNECTION_STATUS = "getVehicleConnectionStatus";
    public static final String GET_WINDOW_STATE = "getWindowState";
    public static final String HAS_SUNSHADE_SWITCH = "hasSunshadeSwitch";
    public static final String IS_DARK_OUTSIDE = "isDarkOutside";
    public static final String IS_RAINING = "isRaining";
    public static final String OPEN_GAS_CAP = "openGasCap";
    public static final String QUERY_CURRENT_SPEED = "queryCurrentSpeed";
    public static final String QUERY_ENERGY_CONSUMPTION = "queryEnergyConsumption";
    public static final String QUERY_ENERGY_TYPE = "queryEnergyType";
    public static final String QUERY_ENGINE_TEMPERATURE = "queryEngineTemperature";
    public static final String QUERY_NEXT_MAINTENANCE = "queryNextMaintenance";
    public static final String QUERY_RECENT_MAX_SPEED = "queryRecentMaxSpeed";
    public static final String QUERY_REMAINING_MILEAGE = "queryRemainingMileage";
    public static final String QUERY_TIRE_PRESSURE = "queryTirePressure";
    public static final String SET_ACTIVE_SPEAKERS = "setActiveSpeakers";
    public static final String SET_AMBIENT_LIGHT_COLOR = "setAmbientLightColor";
    public static final String SET_AMBIENT_LIGHT_STATE = "setAmbientLightState";
    public static final String SET_CLUSTER_MODE = "setClusterMode";
    public static final String SET_DRIVING_MODE = "setDrivingMode";
    public static final String SET_HUDMODE = "setHUDMode";
    public static final String SET_SUNSHADE_STATE = "setSunshadeState";
    public static final String SET_WINDOW_STATE = "setWindowState";
}
