package com.ts.androidauto.sdk.common;

/* JADX INFO: loaded from: classes.dex */
public class VehicleConst {
    public static final int AAP_DRIVE_STATUS_LIMIT_MESSAGE_LEN = 16;
    public static final int AAP_DRIVE_STATUS_NO_CONFIG = 8;
    public static final int AAP_DRIVE_STATUS_NO_KEYBOARD_INPUT = 2;
    public static final int AAP_DRIVE_STATUS_NO_VIDEO = 1;
    public static final int AAP_DRIVE_STATUS_NO_VOICE_INPUT = 4;
    public static final int AAP_DRIVE_STATUS_UNRESTRICTED = 0;
    public static final String AA_MAKE = "VEHICLE_MAKE";
    public static final String AA_MODEL = "PROXI_VEHICLE_LINE_CONFIGURATION";
    public static final String AA_YEAR = "PROXI_MODEL_YEAR";
    public static final int AUDIO_REPETITION_ABSENT = 0;
    public static final int AUDIO_REPETITION_PRESENT = 1;
    public static final String BEV_332 = "332";
    public static final int BH_BCM2_STATUS_NOTIFY = 136;
    public static final int BH_BCM2_STATUS_REQUEST = 8;
    public static final String DEBUG_STEERING_WHEEL_COMMAND_TYPE = "debug.steering_wheel_command.type";
    public static final String DRIVER_POSITION = "PROXI_DRIVER_SIDE";
    public static final int DUCK_AUDIO_CMD = 149;
    public static final int DUCK_AUDIO_SUBCMD = 30;
    public static final int ENGINEERING_MODE_CMD = 211;
    public static final int ENGINEERING_MODE_ID_LENGTH = 2;
    public static final int ENGINEERING_MODE_ID_SERIAL_HIGH = 0;
    public static final int ENGINEERING_MODE_ID_SERIAL_LOW = 2;
    public static final int ENGINEERING_MODE_MIN_SERIAL_LENGTH = 3;
    public static final int ENGINEERING_MODE_REQUEST_SUBCMD = 1;
    public static final int ENGINEERING_MODE_RESPONSE_SUBCMD = 129;
    public static final int ENGINE_TYPE_CNG = 3;
    public static final int ENGINE_TYPE_DIESEL = 1;
    public static final int ENGINE_TYPE_ELECTRIC = 2;
    public static final int ENGINE_TYPE_GASOLINE = 0;
    public static final String FUEL_TYPE = "PROXI_FUEL_TYPE";
    public static final String GEAR_BOX_TYPE = "PROXI_GEAR_BOX_TYPE";
    public static final int GEAR_BOX_TYPE_MTA = 2;
    public static final int GEAR_BOX_TYPE_MTX = 1;
    public static final String HU_MANUFACTURE = "HU_MANUFACTURE";
    public static final String HU_MODEL = "ACCESSORY_MODEL_NUMBER";
    public static final int IGNITION_CRANKING = 7;
    public static final int IGNITION_INITIALIZATION = 0;
    public static final int IGNITION_OFF = 2;
    public static final int IGNITION_ON = 4;
    public static final int IGNITION_ON_ENGINE_ON = 8;
    public static final int IGNITION_PRE_OFF = 10;
    public static final int IGNITION_PRE_START = 5;
    public static final int IGNITION_SNA = 15;
    public static final int IGNITION_START = 6;
    public static final int INTERACTION_CMD = 133;
    public static final int INTERACTION_SUBCMD = 128;
    public static final int MIC_MUTE_HWKEY_MAP_CMD = 17;
    public static final int MIC_MUTE_HWKEY_MAP_SUBCMD = 128;
    public static final String MX = "MX";
    public static final String OTHERS = "OTHERS";
    public static final int PRODUCT_BASE_YEAR = 2000;
    public static final String PRODUCT_ID = "PRODUCTID";
    public static final String PROXI_AUDIO_REPETITION = "PROXI_AUDIO_REPETITION";
    public static final String PROXI_COUNTRY_CODE = "PROXI_COUNTRY_CODE";
    public static final String PROXI_PROJECTION_MODE = "PROXI_PROJECTION_MODE";
    public static final String PROXI_PROJECTION_MODE_SELECTION = "PROXI_PROJECTION_MODE_SELECTION";
    public static final String PROXI_RRM_VPX_STEERING_WHEEL_COMMAND_TYPE = "PROXI_RRM_VPX_STEERING_WHEEL_COMMAND_TYPE";
    public static final String RENEGADE_521 = "521";
    public static final int REQUEST_FULEDATA_SUBCMD = 3;
    public static final int REQUEST_GEAR_SUBCMD = 6;
    public static final int REQUEST_INTERACTION_SUBCMD = 0;
    public static final int REQUEST_NIGHTMODE_SUBCMD = 5;
    public static final int REQUEST_ODOMETER_SUBCMD = 2;
    public static final int REQUEST_PARKING_SUBCMD = 4;
    public static final String SERIAL_NUMBER = "SERIAL_NO";
    public static final int[] SUBSCRIBE_SUBCMD = {128, 129, 130, 131, 132, 133, 134, 140};
    public static final String TORO_226 = "226";
    public static final int UNDUCK_AUDIO_SUBCMD = 31;
    public static final int VEHICLE_CMD = 249;
    public static final int VEHICLE_DOOR_NOTIFY = 140;
    public static final int VEHICLE_DOOR_REQUEST = 12;
    public static final int VEHICLE_ENGINE_SPEED_SUBCMD = 129;
    public static final int VEHICLE_FULEDATA_SUBCMD = 131;
    public static final int VEHICLE_GEAR_SUBCMD = 134;
    public static final int VEHICLE_NIGHTMODE_SUBCMD = 133;
    public static final int VEHICLE_ODOMETER_SUBCMD = 130;
    public static final int VEHICLE_PARKING_SUBCMD = 132;
    public static final int VEHICLE_SPEED_SUBCMD = 128;
    public static final int VEHICLE_TIRE_PRESSURE_NOTIFY = 139;
    public static final int VEHICLE_TIRE_PRESSURE_REQUEST = 11;
    public static final String VENDOR_ID = "VENDORID";
    public static final String WL = "WL";

    public enum VehicleDrivingStatus {
        DRIVING_STATUS_UNDER_THREE_PARKINGBREAK_AND_P,
        DRIVING_STATUS_UNDER_THREE,
        DRIVING_STATUS_EXCEED_FIVE;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }

    public enum VehicleFuelType {
        FUEL_TYPE_GASOLINE,
        FUEL_TYPE_DIESEL,
        FUEL_TYPE_CNG,
        FUEL_TYPE_GPL,
        FUEL_TYPE_ELECTRIC,
        FUEL_TYPE_HYBRID,
        FUEL_TYPE_FLEX,
        FUEL_TYPE_OTHER;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }

    public enum AapVehicleDrivePosition {
        AAP_DRIVE_LEFT,
        AAP_DRIVE_RIGHT,
        AAP_DRIVE_CENTER,
        AAP_DRIVE_UNKNOWN;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }

    public enum AapVehicleFuelType {
        AAP_FUEL_UNLEADED_GASOLINE,
        AAP_FUEL_LEADED_GASOLINE,
        AAP_FUEL_DIESEL_1,
        AAP_FUEL_DIESEL_2,
        AAP_FUEL_BIODIESEL,
        AAP_FUEL_E85,
        AAP_FUEL_LPG,
        AAP_FUEL_CNG,
        AAP_FUEL_LNG,
        AAP_FUEL_ELECTRIC,
        AAP_FUEL_HYDROGEN,
        AAP_FUEL_OTHER,
        AAP_FUEL_UNKNOWN;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }

    public enum AapEvConnectorType {
        AAP_EV_CONNECTOR_J1772,
        AAP_EV_CONNECTOR_MENNEKES,
        AAP_EV_CONNECTOR_CHADEMO,
        AAP_EV_CONNECTOR_COMBO_1,
        AAP_EV_CONNECTOR_COMBO_2,
        AAP_EV_CONNECTOR_TESLA_ROADSTER,
        AAP_EV_CONNECTOR_TESLA_HPWC,
        AAP_EV_CONNECTOR_TESLA_SUPERCHARGER,
        AAP_EV_CONNECTOR_GBT,
        AAP_EV_CONNECTOR_OTHER,
        AAP_EV_CONNECTOR_UNKNOWN;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }

    public enum AapHardkeyEvent {
        AAP_KEYCODE_MEDIA,
        AAP_KEYCODE_TEL,
        AAP_KEYCODE_NAVIGATION,
        AAP_KEYCODE_HOME,
        AAP_KEYCODE_SEARCH,
        AAP_KEYCODE_BACK,
        AAP_KEYCODE_MEDIA_PLAY_PAUSE,
        AAP_KEYCODE_MEDIA_PLAY,
        AAP_KEYCODE_MEDIA_PAUSE,
        AAP_KEYCODE_MEDIA_PREVIOUS,
        AAP_KEYCODE_MEDIA_NEXT,
        AAP_KEYCODE_DPAD_RIGHT,
        AAP_KEYCODE_DPAD_LEFT,
        AAP_KEYCODE_DPAD_UP,
        AAP_KEYCODE_DPAD_DOWN,
        AAP_KEYCODE_DPAD_CENTER,
        AAP_KEYCODE_ROTARY_CONTROLLER;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }
}
