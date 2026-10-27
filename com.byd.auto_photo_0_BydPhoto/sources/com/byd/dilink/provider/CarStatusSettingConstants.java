package com.byd.dilink.provider;

/* JADX INFO: loaded from: classes.dex */
public class CarStatusSettingConstants {

    public static final class DiCareRecord {
        public static final String DICARE_HEV_MILEAGE = "hev_mileage";
        public static final String DICARE_ID = "id";
        public static final String DICARE_MILEAGE_UNIT = "unit";
        public static final String DICARE_TIME = "time";
        public static final String DICARE_TOTAL_MILEAGE = "total_mileage";
        public static final String DICARE_TYPE = "type";
        public static final int TYPE_AT_FLUID = 2;
        public static final int TYPE_BATTERY_COOLANT = 4;
        public static final int TYPE_BRAKE_FLUID = 3;
        public static final int TYPE_ENGINE_OIL = 1;
        public static final int TYPE_MOTOR_COOLANT = 5;
        public static final String UNIT_KM = "km";
        public static final String UNIT_MILE = "mile";
    }

    public static final class TravelInfoData {
        public static final String COLUMN_CAR_STATUS_INFO = "car_status_issue";
        public static final String COLUMN_CAR_STATUS_ISSUE_NUM = "car_status_issue_num";
        public static final String COLUMN_CAR_TRAVEL_INFO_ELEC = "travel_points_elec";
        public static final String COLUMN_CAR_TRAVEL_INFO_ELEC_ONE = "travel_points_elec_one";
        public static final String COLUMN_CAR_TRAVEL_INFO_ELEC_TWO = "travel_points_elec_two";
        public static final String COLUMN_CAR_TRAVEL_INFO_FUEL = "travel_points_fuel";
        public static final String COLUMN_CAR_TRAVEL_INFO_FUEL_ONE = "travel_points_fuel_one";
        public static final String COLUMN_CAR_TRAVEL_INFO_FUEL_TWO = "travel_points_fuel_two";
        public static final String COLUMN_MAINTENANCE_MILE = "car_status_maintenance_mile";
        public static final String COLUMN_MAINTENANCE_TIME = "car_status_maintenance_time";
        public static final String DICARE_AT_FLUID_NO_PROMPT = "dicare_at_fluid_no_prompt";
        public static final String DICARE_BATTERY_COOLANT_NO_PROMPT = "dicare_battery_coolant_no_prompt";
        public static final String DICARE_BRAKE_FLUID_NO_PROMPT = "dicare_brake_fluid_no_prompt";
        public static final String DICARE_ENGINE_OIL_NO_PROMPT = "dicare_engine_oil_no_prompt";
        public static final String DICARE_MOTOR_COOLANT_NO_PROMPT = "dicare_motor_coolant_no_prompt";
        public static final String MAINTENANCE_MILE_SWITCH_STATE = "maintenance_mile_switch_state";
        public static final String MAINTENANCE_TIME_SWITCH_STATE = "maintenance_time_switch_state";
        public static final String SET_COLUMN_MAINTENANCE_MILE = "set_car_status_maintenance_mile";
        public static final String SET_COLUMN_MAINTENANCE_TIME = "set_car_status_maintenance_time";
        public static final String TYRE_PRESSURE_GUIDANCE_NO_PROMPT = "tyre_pressure_guidance_no_prompt";
    }
}
