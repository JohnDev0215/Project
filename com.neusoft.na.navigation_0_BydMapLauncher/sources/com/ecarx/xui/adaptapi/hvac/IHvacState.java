package com.ecarx.xui.adaptapi.hvac;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IHvacState {
    public static final int AIR_CONDITIONER_AUTO_MODE_HIGH = 4;
    public static final int AIR_CONDITIONER_AUTO_MODE_NORMAL = 2;
    public static final int AIR_CONDITIONER_AUTO_MODE_OFF = 0;
    public static final int AIR_CONDITIONER_AUTO_MODE_SILENT = 1;
    public static final int AQS_LEVEL_DEFAULT = Integer.MAX_VALUE;
    public static final int AQS_LEVEL_HIGH = 2147483644;
    public static final int AQS_LEVEL_LOW = 2147483646;
    public static final int AQS_LEVEL_MID = 2147483645;
    public static final int BLOWING_ALL = 7;
    public static final int BLOWING_LEVEL_7 = 7;
    public static final int BLOWING_LEVEL_8 = 8;
    public static final int BLOWING_MODE_AUTO_SWITCH = Integer.MAX_VALUE;

    @Deprecated
    public static final int BLOWING_MODE_DEFROSTING = 4;
    public static final int BLOWING_MODE_FACE = 1;
    public static final int BLOWING_MODE_FACE_AND_FRONT_WINDOW = 5;
    public static final int BLOWING_MODE_FACE_AND_LEG = 3;

    @Deprecated
    public static final int BLOWING_MODE_FOOT = 8;

    @Deprecated
    public static final int BLOWING_MODE_FOOT_AND_FACE = 9;
    public static final int BLOWING_MODE_FRONT_WINDOW = 4;
    public static final int BLOWING_MODE_LEG = 2;

    @Deprecated
    public static final int BLOWING_MODE_LEG_AND_DEFROSTING = 6;
    public static final int BLOWING_MODE_LEG_AND_FRONT_WINDOW = 6;
    public static final int BLOWING_NONE = 0;
    public static final int FRAGRANCE_JASMINE = 2;
    public static final int FRAGRANCE_LEVEL_DEFAULT = 2147483637;
    public static final int FRAGRANCE_LEVEL_HIGH = 3;
    public static final int FRAGRANCE_LEVEL_LOW = 1;
    public static final int FRAGRANCE_LEVEL_MID = 2;
    public static final int FRAGRANCE_LEVEL_OFF = 0;
    public static final int FRAGRANCE_LONGJING = 1;
    public static final int FRAGRANCE_SANDAL = 3;
    public static final int FUNCTION_PARKING_HEATER = 2;
    public static final int FUNCTION_STEERING_WHEEL_HEATER = 1;
    public static final int IONS_MODE_AUTO = 2;
    public static final int IONS_MODE_ERROR = 16;
    public static final int IONS_MODE_HIGH = 64;
    public static final int IONS_MODE_LOW = 32;
    public static final int IONS_MODE_MANUAL = 4;
    public static final int IONS_MODE_OFF = 0;
    public static final int IONS_MODE_ON = 128;
    public static final int IONS_MODE_QUITE = 1;
    public static final int IONS_MODE_SLEEP = 8;
    public static final int LOOP_MODE_DEFAULT = 1;
    public static final int LOOP_MODE_EXTERNAL = 2;
    public static final int LOOP_MODE_INTERNAL = 4;
    public static final int PM2_5_AQI_LEVEL_UNKNOWN = -1;
    public static final int SEAT_HEATING_DURATION_DEFAULT = 2147483637;
    public static final int SEAT_HEATING_DURATION_LONG = Integer.MAX_VALUE;
    public static final int SEAT_HEATING_DURATION_NORMAL = 2147483646;
    public static final int SEAT_HEATING_DURATION_OFF = 0;
    public static final int SEAT_HEATING_DURATION_SHORT = 2147483645;
    public static final int SEAT_HEATING_LEVEL_AUTO = 2147483644;
    public static final int SEAT_HEATING_LEVEL_DEFAULT = 2147483637;
    public static final int SEAT_HEATING_LEVEL_LOW = 2147483645;
    public static final int SEAT_HEATING_LEVEL_MAX = Integer.MAX_VALUE;
    public static final int SEAT_HEATING_LEVEL_MID = 2147483646;
    public static final int SEAT_HEATING_LEVEL_OFF = 0;
    public static final int SEAT_HEAT_PRE_CONDITIONING_ALL = 2147483644;
    public static final int SEAT_HEAT_PRE_CONDITIONING_DRIVER = 2147483646;
    public static final int SEAT_HEAT_PRE_CONDITIONING_OFF = Integer.MAX_VALUE;
    public static final int SEAT_HEAT_PRE_CONDITIONING_PASSENGER = 2147483645;
    public static final int SEAT_MASSAGE_DURATION_DEFAULT = 2147483637;
    public static final int SEAT_MASSAGE_DURATION_LONG = Integer.MAX_VALUE;
    public static final int SEAT_MASSAGE_DURATION_NORMAL = 2147483646;
    public static final int SEAT_MASSAGE_DURATION_OFF = 0;
    public static final int SEAT_MASSAGE_DURATION_SHORT = 2147483645;
    public static final int SEAT_MASSAGE_LEVEL_DEFAULT = 2147483637;
    public static final int SEAT_MASSAGE_LEVEL_LEVEL1 = 2147483645;
    public static final int SEAT_MASSAGE_LEVEL_LEVEL2 = 2147483646;
    public static final int SEAT_MASSAGE_LEVEL_LEVEL3 = Integer.MAX_VALUE;
    public static final int SEAT_MASSAGE_LEVEL_OFF = 0;
    public static final int SEAT_VENTILATION_DURATION_DEFAULT = 2147483637;
    public static final int SEAT_VENTILATION_DURATION_LONG = Integer.MAX_VALUE;
    public static final int SEAT_VENTILATION_DURATION_NORMAL = 2147483646;
    public static final int SEAT_VENTILATION_DURATION_OFF = 0;
    public static final int SEAT_VENTILATION_DURATION_SHORT = 2147483645;
    public static final int SEAT_VENTILATION_LEVEL_DEFAULT = 2147483637;
    public static final int SEAT_VENTILATION_LEVEL_LEVEL1 = 2147483645;
    public static final int SEAT_VENTILATION_LEVEL_LEVEL2 = 2147483646;
    public static final int SEAT_VENTILATION_LEVEL_LEVEL3 = Integer.MAX_VALUE;
    public static final int SEAT_VENTILATION_LEVEL_OFF = 0;
    public static final int TEMPERATURE_TREND_DECLINE = 2;
    public static final int TEMPERATURE_TREND_NONE = 0;
    public static final int TEMPERATURE_TREND_RISING = 1;
    public static final int TEMPERATURE_UNIT_CELSIUS = 1;
    public static final int TEMPERATURE_UNIT_FAHRENHEIT = 2;
    public static final int TYPE_AC = 37;
    public static final int TYPE_AC_BLOWING_RANGE_IS_EIGHT = 59;
    public static final int TYPE_AC_CO2 = 55;
    public static final int TYPE_AC_ECO = 56;
    public static final int TYPE_AC_MAX = 6;
    public static final int TYPE_AC_PTC_HEAT = 54;
    public static final int TYPE_AC_RCP = 57;
    public static final int TYPE_AC_THRID_PANEL = 58;
    public static final int TYPE_ADS_AUTO_DEFROST = 45;
    public static final int TYPE_AIR_CONDITIONER_AUTO = 44;
    public static final int TYPE_AIR_CONDITIONER_AUTOMODE = 5;
    public static final int TYPE_AIR_RAISE_WINDOW_SWITCH = 32;
    public static final int TYPE_AMBIENT_PM25 = 35;
    public static final int TYPE_AQS = 28;
    public static final int TYPE_AUTOMATIC_FRONT_DEFROST = 15;
    public static final int TYPE_AUTOMATIC_REAR_DEFROST = 24;
    public static final int TYPE_AUTOMATIC_SEAT_HEATING = 16;
    public static final int TYPE_AUTOMATIC_SEAT_HEATING_TIMER = 20;
    public static final int TYPE_AUTOMATIC_SEAT_HEAT_LEVEL = 18;
    public static final int TYPE_AUTOMATIC_SEAT_VENTILATION = 17;
    public static final int TYPE_AUTOMATIC_SEAT_VENTILATION_LEVEL = 19;
    public static final int TYPE_AUTOMATIC_SEAT_VENTILATION_TIMER = 21;
    public static final int TYPE_AUTOMATIC_VENTILATION = 52;
    public static final int TYPE_AUTOMATIC_VENTILATION_DRY = 53;
    public static final int TYPE_DUAL = 11;
    public static final int TYPE_FAN_SPEED = 43;
    public static final int TYPE_FRAGRANCE = 46;
    public static final int TYPE_FRONT_DEFROST = 7;
    public static final int TYPE_FRONT_DEFROST_MAX = 8;
    public static final int TYPE_HVAC_ONOFF = 51;
    public static final int TYPE_INDOOR_PM25 = 36;
    public static final int TYPE_INTERNAL_LOOP_TIMER = 14;
    public static final int TYPE_ION = 29;
    public static final int TYPE_KEEP_COMFORTABLE = 48;
    public static final int TYPE_LEFT_TEMPERATURE = 39;
    public static final int TYPE_PARKING_AIR_PRE_CONDITIONING = 22;
    public static final int TYPE_PARKING_SEAT_HEATING_PRE_CONDITIONING = 23;
    public static final int TYPE_PM25_AUTO_PURGING = 50;
    public static final int TYPE_PM25_DETECTION = 49;
    public static final int TYPE_PM25_WARNING = 34;
    public static final int TYPE_REAR_DEFROST = 9;
    public static final int TYPE_REAR_DEFROST_MAX = 10;
    public static final int TYPE_REAR_LOCK = 30;
    public static final int TYPE_RIGHT_TEMPERATURE = 31;
    public static final int TYPE_SEAT_HEATER = 12;
    public static final int TYPE_SEAT_MASSAGE = 33;
    public static final int TYPE_SEAT_VENTILATION = 13;
    public static final int TYPE_TOUCH_BOARD = 47;
    public static final int WINDOW_TYPE_DEFAULT = 2147483637;
    public static final int WINDOW_TYPE_HARDWARE_TOAST = 3;
    public static final int WINDOW_TYPE_LEFT_TMP = 1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_RIGHT_TMP = 2;
    public static final int WINDOW_TYPE_TOUCHBOARD_POP = 4;

    public @interface AqsLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoAirConditionerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BlowingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FragranceLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FragranceType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Function {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IonsMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoopMode {
    }

    public @interface SeatHeatPreConditioningStatus {
    }

    public @interface SeatHeatingDurationMode {
    }

    public @interface SeatHeatingLevel {
    }

    public @interface SeatMassageDurationMode {
    }

    public @interface SeatMassageLevel {
    }

    public @interface SeatVentilationDurationMode {
    }

    public @interface SeatVentilationLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TemperatureTendency {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TemperatureUnit {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WindowType {
    }

    @Deprecated
    int getAQSLevel();

    int getAcComfortLevel();

    int getAirCoditionerAutoMode();

    @Deprecated
    int getAmbientAQSLevel();

    @Deprecated
    int getAmbientPM2_5AQI();

    @Deprecated
    int getAmbientPM2_5AQILevel();

    double getAmbientTemperature();

    int getAutoSeatHeatingDuration(int i);

    int getAutoSeatHeatingLevel(int i);

    int getAutoSeatVentilationDuration(int i);

    int getAutoSeatVentilationLevel(int i);

    int getBlowingLevel();

    int getBlowingMode();

    int getDisplayRequestWindowType();

    @Deprecated
    int getDriverSeatHeatingLevel();

    int getFragranceLevel();

    int getFragranceType();

    boolean getHvacOnOffInVehicleZone(int i);

    int getHvacTemperatureTendency(int i);

    int getIonsMode();

    double getLeftAreaTemperature();

    int getLoopMode();

    @Deprecated
    int getPM2_5AQI();

    @Deprecated
    int getPM2_5AQILevel();

    int getParkingSeatHeatPreConditioning();

    @Deprecated
    int getPassengerSeatHeatingLevel();

    double getRightAreaTemperature();

    int getSeatHeatingLevel(int i);

    int getSeatMassageLevel(int i);

    int getSeatVentilationLevel(int i);

    int getTemperatureUnit();

    int getVehicleZoneUnderHvacControl();

    boolean isACLostConnection();

    @Deprecated
    boolean isAcAutoOn();

    boolean isAcCZISOpen();

    boolean isAcEco();

    boolean isAcMax();

    boolean isAcOn();

    boolean isAcPTCHeat();

    boolean isAcVentilaDry();

    boolean isAirConditionerAutoModeOn();

    boolean isAirRaiseWindowSwitchOn();

    @Deprecated
    boolean isAqsOn();

    boolean isAutoFrontDefrosting();

    boolean isAutoRearDefrosting();

    boolean isAutoSeatHeatingOn(int i);

    boolean isAutoSeatMassageOn(int i);

    boolean isAutoSeatventilationOn(int i);

    FunctionStatus isBlowingModeAvaiable(int i);

    boolean isDisplayRequestByAC();

    @Deprecated
    boolean isDriverSeatHeatingOn();

    boolean isDualAreaSeparatelyControllOn();

    @Deprecated
    boolean isDualOn();

    boolean isFrontDefrosting();

    boolean isFrontDefrostingMax();

    boolean isHvacControlLockedInSpecifiedRow(int i);

    boolean isHvacOn();

    boolean isInternalLoopTimerOn();

    FunctionStatus isLoopModeAvaiable(int i);

    boolean isParkingAirPreConditioningOn();

    @Deprecated
    boolean isPassegerSeatHeatingOn();

    @Deprecated
    boolean isPm25Warning();

    boolean isRearDefrosting();

    boolean isRearDefrostingMax();

    boolean isSeatDisplayRequestBySeat();

    boolean isSteeringWheelHeatingOn();

    boolean isSupporedFuctionOn(int i);
}
