package com.android.car.hal;

import android.car.VehicleHvacFanDirection;
import android.hardware.automotive.vehicle.V2_0.EvConnectorType;
import android.hardware.automotive.vehicle.V2_0.FuelType;
import android.hardware.automotive.vehicle.V2_0.PortLocationType;
import android.hardware.automotive.vehicle.V2_0.VehicleAreaSeat;
import android.hardware.automotive.vehicle.V2_0.VehicleGear;
import android.hardware.automotive.vehicle.V2_0.VehicleIgnitionState;
import android.hardware.automotive.vehicle.V2_0.VehicleLightState;
import android.hardware.automotive.vehicle.V2_0.VehicleLightSwitch;
import android.hardware.automotive.vehicle.V2_0.VehicleOilLevel;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.hardware.automotive.vehicle.V2_0.VehiclePropertyType;
import android.hardware.automotive.vehicle.V2_0.VehicleSeatOccupancyState;
import android.hardware.automotive.vehicle.V2_0.VehicleTurnSignal;
import android.hardware.automotive.vehicle.V2_0.VehicleUnit;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.android.car.Manifest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class PropertyHalServiceIds {
    private static final int PERMISSION_CAR_VENDOR_DEFAULT = 0;
    private static final int PERMISSION_CAR_VENDOR_NOT_ACCESSIBLE = -268435456;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_1 = 69632;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_10 = 659456;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_2 = 135168;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_3 = 200704;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_4 = 266240;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_5 = 331776;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_6 = 397312;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_7 = 462848;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_8 = 528384;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_9 = 593920;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_DOOR = 4;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_ENGINE = 12;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_HVAC = 14;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_INFO = 10;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_LIGHT = 16;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_MIRROR = 8;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_SEAT = 6;
    private static final int PERMISSION_GET_CAR_VENDOR_CATEGORY_WINDOW = 2;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_1 = 65536;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_10 = 655360;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_2 = 131072;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_3 = 196608;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_4 = 262144;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_5 = 327680;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_6 = 393216;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_7 = 458752;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_8 = 524288;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_9 = 589824;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_DOOR = 3;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_ENGINE = 11;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_HVAC = 13;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_INFO = 9;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_LIGHT = 15;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_MIRROR = 7;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_SEAT = 5;
    private static final int PERMISSION_SET_CAR_VENDOR_CATEGORY_WINDOW = 1;
    private static final String TAG = "PropertyHalServiceIds";
    private static final Set<Integer> FUEL_TYPE = new HashSet(getIntegersFromDataEnums(FuelType.class));
    private static final Set<Integer> EV_CONNECTOR_TYPE = new HashSet(getIntegersFromDataEnums(EvConnectorType.class));
    private static final Set<Integer> PORT_LOCATION = new HashSet(getIntegersFromDataEnums(PortLocationType.class));
    private static final Set<Integer> VEHICLE_SEAT = new HashSet(getIntegersFromDataEnums(VehicleAreaSeat.class));
    private static final Set<Integer> OIL_LEVEL = new HashSet(getIntegersFromDataEnums(VehicleOilLevel.class));
    private static final Set<Integer> VEHICLE_GEAR = new HashSet(getIntegersFromDataEnums(VehicleGear.class));
    private static final Set<Integer> TURN_SIGNAL = new HashSet(getIntegersFromDataEnums(VehicleTurnSignal.class));
    private static final Set<Integer> IGNITION_STATE = new HashSet(getIntegersFromDataEnums(VehicleIgnitionState.class));
    private static final Set<Integer> VEHICLE_UNITS = new HashSet(getIntegersFromDataEnums(VehicleUnit.class));
    private static final Set<Integer> SEAT_OCCUPANCY_STATE = new HashSet(getIntegersFromDataEnums(VehicleSeatOccupancyState.class));
    private static final Set<Integer> VEHICLE_LIGHT_STATE = new HashSet(getIntegersFromDataEnums(VehicleLightState.class));
    private static final Set<Integer> VEHICLE_LIGHT_SWITCH = new HashSet(getIntegersFromDataEnums(VehicleLightSwitch.class));
    private static final int HVAC_FAN_DIRECTION_COMBINATIONS = generateAllCombination(VehicleHvacFanDirection.class);
    private final SparseArray<Pair<String, String>> mProps = new SparseArray<>();
    private final HashSet<Integer> mPropForUnits = new HashSet<>();
    private final HashMap<Integer, Set<Integer>> mPropToValidValue = new HashMap<>();
    private final HashMap<Integer, Integer> mPropToValidBitFlag = new HashMap<>();

    public PropertyHalServiceIds() {
        this.mProps.put(VehicleProperty.DOOR_POS, new Pair<>(Manifest.permission.CONTROL_CAR_DOORS, Manifest.permission.CONTROL_CAR_DOORS));
        this.mProps.put(VehicleProperty.DOOR_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_DOORS, Manifest.permission.CONTROL_CAR_DOORS));
        this.mProps.put(VehicleProperty.DOOR_LOCK, new Pair<>(Manifest.permission.CONTROL_CAR_DOORS, Manifest.permission.CONTROL_CAR_DOORS));
        this.mProps.put(VehicleProperty.MIRROR_Z_POS, new Pair<>(Manifest.permission.CONTROL_CAR_MIRRORS, Manifest.permission.CONTROL_CAR_MIRRORS));
        this.mProps.put(VehicleProperty.MIRROR_Z_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_MIRRORS, Manifest.permission.CONTROL_CAR_MIRRORS));
        this.mProps.put(VehicleProperty.MIRROR_Y_POS, new Pair<>(Manifest.permission.CONTROL_CAR_MIRRORS, Manifest.permission.CONTROL_CAR_MIRRORS));
        this.mProps.put(VehicleProperty.MIRROR_Y_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_MIRRORS, Manifest.permission.CONTROL_CAR_MIRRORS));
        this.mProps.put(VehicleProperty.MIRROR_LOCK, new Pair<>(Manifest.permission.CONTROL_CAR_MIRRORS, Manifest.permission.CONTROL_CAR_MIRRORS));
        this.mProps.put(VehicleProperty.MIRROR_FOLD, new Pair<>(Manifest.permission.CONTROL_CAR_MIRRORS, Manifest.permission.CONTROL_CAR_MIRRORS));
        this.mProps.put(VehicleProperty.SEAT_MEMORY_SELECT, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_MEMORY_SET, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BELT_BUCKLED, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BELT_HEIGHT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BELT_HEIGHT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_FORE_AFT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_FORE_AFT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BACKREST_ANGLE_1_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BACKREST_ANGLE_1_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BACKREST_ANGLE_2_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_BACKREST_ANGLE_2_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEIGHT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEIGHT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_DEPTH_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_DEPTH_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_TILT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_TILT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_LUMBAR_FORE_AFT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_LUMBAR_FORE_AFT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_LUMBAR_SIDE_SUPPORT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_LUMBAR_SIDE_SUPPORT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEADREST_HEIGHT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEADREST_HEIGHT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEADREST_ANGLE_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEADREST_ANGLE_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEADREST_FORE_AFT_POS, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_HEADREST_FORE_AFT_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, Manifest.permission.CONTROL_CAR_SEATS));
        this.mProps.put(VehicleProperty.SEAT_OCCUPANCY, new Pair<>(Manifest.permission.CONTROL_CAR_SEATS, null));
        this.mProps.put(VehicleProperty.WINDOW_POS, new Pair<>(Manifest.permission.CONTROL_CAR_WINDOWS, Manifest.permission.CONTROL_CAR_WINDOWS));
        this.mProps.put(VehicleProperty.WINDOW_MOVE, new Pair<>(Manifest.permission.CONTROL_CAR_WINDOWS, Manifest.permission.CONTROL_CAR_WINDOWS));
        this.mProps.put(VehicleProperty.WINDOW_LOCK, new Pair<>(Manifest.permission.CONTROL_CAR_WINDOWS, Manifest.permission.CONTROL_CAR_WINDOWS));
        this.mProps.put(VehicleProperty.HVAC_FAN_SPEED, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_FAN_DIRECTION, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_TEMPERATURE_CURRENT, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_TEMPERATURE_SET, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_DEFROSTER, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_ELECTRIC_DEFROSTER_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_AC_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_MAX_AC_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_MAX_DEFROST_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_RECIRC_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_DUAL_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_AUTO_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_SEAT_TEMPERATURE, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_SIDE_MIRROR_HEAT, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_STEERING_WHEEL_HEAT, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_TEMPERATURE_DISPLAY_UNITS, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_ACTUAL_FAN_SPEED_RPM, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_POWER_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_FAN_DIRECTION_AVAILABLE, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_AUTO_RECIRC_ON, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.HVAC_SEAT_VENTILATION, new Pair<>(Manifest.permission.CONTROL_CAR_CLIMATE, Manifest.permission.CONTROL_CAR_CLIMATE));
        this.mProps.put(VehicleProperty.INFO_VIN, new Pair<>(Manifest.permission.CAR_IDENTIFICATION, null));
        this.mProps.put(VehicleProperty.INFO_MAKE, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_MODEL, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_MODEL_YEAR, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_FUEL_CAPACITY, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_FUEL_TYPE, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_EV_BATTERY_CAPACITY, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_EV_CONNECTOR_TYPE, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_FUEL_DOOR_LOCATION, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_MULTI_EV_PORT_LOCATIONS, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_EV_PORT_LOCATION, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_DRIVER_SEAT, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.INFO_EXTERIOR_DIMENSIONS, new Pair<>(Manifest.permission.CAR_INFO, null));
        this.mProps.put(VehicleProperty.PERF_ODOMETER, new Pair<>(Manifest.permission.CAR_MILEAGE, null));
        this.mProps.put(VehicleProperty.PERF_VEHICLE_SPEED, new Pair<>(Manifest.permission.CAR_SPEED, null));
        this.mProps.put(VehicleProperty.PERF_VEHICLE_SPEED_DISPLAY, new Pair<>(Manifest.permission.CAR_SPEED, null));
        this.mProps.put(VehicleProperty.ENGINE_COOLANT_TEMP, new Pair<>(Manifest.permission.CAR_ENGINE_DETAILED, null));
        this.mProps.put(VehicleProperty.ENGINE_OIL_LEVEL, new Pair<>(Manifest.permission.CAR_ENGINE_DETAILED, null));
        this.mProps.put(VehicleProperty.ENGINE_OIL_TEMP, new Pair<>(Manifest.permission.CAR_ENGINE_DETAILED, null));
        this.mProps.put(VehicleProperty.ENGINE_RPM, new Pair<>(Manifest.permission.CAR_ENGINE_DETAILED, null));
        this.mProps.put(VehicleProperty.WHEEL_TICK, new Pair<>(Manifest.permission.CAR_SPEED, null));
        this.mProps.put(VehicleProperty.FUEL_LEVEL, new Pair<>(Manifest.permission.CAR_ENERGY, null));
        this.mProps.put(VehicleProperty.FUEL_DOOR_OPEN, new Pair<>(Manifest.permission.CAR_ENERGY_PORTS, Manifest.permission.CONTROL_CAR_ENERGY_PORTS));
        this.mProps.put(VehicleProperty.EV_BATTERY_LEVEL, new Pair<>(Manifest.permission.CAR_ENERGY, null));
        this.mProps.put(VehicleProperty.EV_CHARGE_PORT_OPEN, new Pair<>(Manifest.permission.CAR_ENERGY_PORTS, Manifest.permission.CONTROL_CAR_ENERGY_PORTS));
        this.mProps.put(VehicleProperty.EV_CHARGE_PORT_CONNECTED, new Pair<>(Manifest.permission.CAR_ENERGY_PORTS, null));
        this.mProps.put(VehicleProperty.EV_BATTERY_INSTANTANEOUS_CHARGE_RATE, new Pair<>(Manifest.permission.CAR_ENERGY, null));
        this.mProps.put(VehicleProperty.RANGE_REMAINING, new Pair<>(Manifest.permission.CAR_ENERGY, Manifest.permission.ADJUST_RANGE_REMAINING));
        this.mProps.put(VehicleProperty.TIRE_PRESSURE, new Pair<>(Manifest.permission.CAR_TIRES, null));
        this.mProps.put(VehicleProperty.PERF_STEERING_ANGLE, new Pair<>(Manifest.permission.READ_CAR_STEERING, null));
        this.mProps.put(VehicleProperty.PERF_REAR_STEERING_ANGLE, new Pair<>(Manifest.permission.READ_CAR_STEERING, null));
        this.mProps.put(VehicleProperty.GEAR_SELECTION, new Pair<>(Manifest.permission.CAR_POWERTRAIN, null));
        this.mProps.put(VehicleProperty.CURRENT_GEAR, new Pair<>(Manifest.permission.CAR_POWERTRAIN, null));
        this.mProps.put(VehicleProperty.PARKING_BRAKE_ON, new Pair<>(Manifest.permission.CAR_POWERTRAIN, null));
        this.mProps.put(VehicleProperty.PARKING_BRAKE_AUTO_APPLY, new Pair<>(Manifest.permission.CAR_POWERTRAIN, null));
        this.mProps.put(VehicleProperty.FUEL_LEVEL_LOW, new Pair<>(Manifest.permission.CAR_ENERGY, null));
        this.mProps.put(VehicleProperty.NIGHT_MODE, new Pair<>(Manifest.permission.CAR_EXTERIOR_ENVIRONMENT, null));
        this.mProps.put(VehicleProperty.TURN_SIGNAL_STATE, new Pair<>(Manifest.permission.CAR_EXTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.IGNITION_STATE, new Pair<>(Manifest.permission.CAR_POWERTRAIN, null));
        this.mProps.put(VehicleProperty.ABS_ACTIVE, new Pair<>(Manifest.permission.CAR_DYNAMICS_STATE, null));
        this.mProps.put(VehicleProperty.TRACTION_CONTROL_ACTIVE, new Pair<>(Manifest.permission.CAR_DYNAMICS_STATE, null));
        this.mProps.put(VehicleProperty.ENV_OUTSIDE_TEMPERATURE, new Pair<>(Manifest.permission.CAR_EXTERIOR_ENVIRONMENT, null));
        this.mProps.put(VehicleProperty.HEADLIGHTS_STATE, new Pair<>(Manifest.permission.CAR_EXTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.HIGH_BEAM_LIGHTS_STATE, new Pair<>(Manifest.permission.CAR_EXTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.FOG_LIGHTS_STATE, new Pair<>(Manifest.permission.CAR_EXTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.HAZARD_LIGHTS_STATE, new Pair<>(Manifest.permission.CAR_EXTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.HEADLIGHTS_SWITCH, new Pair<>(Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS, Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS));
        this.mProps.put(VehicleProperty.HIGH_BEAM_LIGHTS_SWITCH, new Pair<>(Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS, Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS));
        this.mProps.put(VehicleProperty.FOG_LIGHTS_SWITCH, new Pair<>(Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS, Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS));
        this.mProps.put(VehicleProperty.HAZARD_LIGHTS_SWITCH, new Pair<>(Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS, Manifest.permission.CONTROL_CAR_EXTERIOR_LIGHTS));
        this.mProps.put(VehicleProperty.READING_LIGHTS_STATE, new Pair<>(Manifest.permission.READ_CAR_INTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.CABIN_LIGHTS_STATE, new Pair<>(Manifest.permission.READ_CAR_INTERIOR_LIGHTS, null));
        this.mProps.put(VehicleProperty.READING_LIGHTS_SWITCH, new Pair<>(Manifest.permission.CONTROL_CAR_INTERIOR_LIGHTS, Manifest.permission.CONTROL_CAR_INTERIOR_LIGHTS));
        this.mProps.put(VehicleProperty.CABIN_LIGHTS_SWITCH, new Pair<>(Manifest.permission.CONTROL_CAR_INTERIOR_LIGHTS, Manifest.permission.CONTROL_CAR_INTERIOR_LIGHTS));
        SparseArray<Pair<String, String>> sparseArray = this.mProps;
        Pair<String, String> pair = new Pair<>(Manifest.permission.READ_CAR_DISPLAY_UNITS, Manifest.permission.CONTROL_CAR_DISPLAY_UNITS);
        Integer numValueOf = Integer.valueOf(VehicleProperty.DISTANCE_DISPLAY_UNITS);
        sparseArray.put(VehicleProperty.DISTANCE_DISPLAY_UNITS, pair);
        this.mPropForUnits.add(numValueOf);
        SparseArray<Pair<String, String>> sparseArray2 = this.mProps;
        Pair<String, String> pair2 = new Pair<>(Manifest.permission.READ_CAR_DISPLAY_UNITS, Manifest.permission.CONTROL_CAR_DISPLAY_UNITS);
        Integer numValueOf2 = Integer.valueOf(VehicleProperty.FUEL_VOLUME_DISPLAY_UNITS);
        sparseArray2.put(VehicleProperty.FUEL_VOLUME_DISPLAY_UNITS, pair2);
        this.mPropForUnits.add(numValueOf2);
        SparseArray<Pair<String, String>> sparseArray3 = this.mProps;
        Pair<String, String> pair3 = new Pair<>(Manifest.permission.READ_CAR_DISPLAY_UNITS, Manifest.permission.CONTROL_CAR_DISPLAY_UNITS);
        Integer numValueOf3 = Integer.valueOf(VehicleProperty.TIRE_PRESSURE_DISPLAY_UNITS);
        sparseArray3.put(VehicleProperty.TIRE_PRESSURE_DISPLAY_UNITS, pair3);
        this.mPropForUnits.add(numValueOf3);
        SparseArray<Pair<String, String>> sparseArray4 = this.mProps;
        Pair<String, String> pair4 = new Pair<>(Manifest.permission.READ_CAR_DISPLAY_UNITS, Manifest.permission.CONTROL_CAR_DISPLAY_UNITS);
        Integer numValueOf4 = Integer.valueOf(VehicleProperty.EV_BATTERY_DISPLAY_UNITS);
        sparseArray4.put(VehicleProperty.EV_BATTERY_DISPLAY_UNITS, pair4);
        this.mPropForUnits.add(numValueOf4);
        this.mProps.put(VehicleProperty.FUEL_CONSUMPTION_UNITS_DISTANCE_OVER_VOLUME, new Pair<>(Manifest.permission.READ_CAR_DISPLAY_UNITS, Manifest.permission.CONTROL_CAR_DISPLAY_UNITS));
        this.mPropForUnits.add(Integer.valueOf(VehicleProperty.FUEL_CONSUMPTION_UNITS_DISTANCE_OVER_VOLUME));
        this.mProps.put(VehicleProperty.VEHICLE_SPEED_DISPLAY_UNITS, new Pair<>(Manifest.permission.READ_CAR_DISPLAY_UNITS, Manifest.permission.CONTROL_CAR_DISPLAY_UNITS));
        this.mPropForUnits.add(Integer.valueOf(VehicleProperty.VEHICLE_SPEED_DISPLAY_UNITS));
        this.mProps.put(VehicleProperty.SUPPORT_CUSTOMIZE_VENDOR_PERMISSION, new Pair<>(Manifest.permission.READ_CAR_VENDOR_PERMISSION_INFO, null));
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.INFO_FUEL_TYPE), FUEL_TYPE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.INFO_EV_CONNECTOR_TYPE), EV_CONNECTOR_TYPE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.INFO_FUEL_DOOR_LOCATION), PORT_LOCATION);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.INFO_DRIVER_SEAT), VEHICLE_SEAT);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.INFO_MULTI_EV_PORT_LOCATIONS), PORT_LOCATION);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.ENGINE_OIL_LEVEL), OIL_LEVEL);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.GEAR_SELECTION), VEHICLE_GEAR);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.CURRENT_GEAR), VEHICLE_GEAR);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.TURN_SIGNAL_STATE), TURN_SIGNAL);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.IGNITION_STATE), IGNITION_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HVAC_TEMPERATURE_DISPLAY_UNITS), VEHICLE_UNITS);
        this.mPropToValidValue.put(numValueOf, VEHICLE_UNITS);
        this.mPropToValidValue.put(numValueOf2, VEHICLE_UNITS);
        this.mPropToValidValue.put(numValueOf3, VEHICLE_UNITS);
        this.mPropToValidValue.put(numValueOf4, VEHICLE_UNITS);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.SEAT_OCCUPANCY), SEAT_OCCUPANCY_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HIGH_BEAM_LIGHTS_STATE), VEHICLE_LIGHT_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HEADLIGHTS_STATE), VEHICLE_LIGHT_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.FOG_LIGHTS_STATE), VEHICLE_LIGHT_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HAZARD_LIGHTS_STATE), VEHICLE_LIGHT_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.CABIN_LIGHTS_STATE), VEHICLE_LIGHT_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.READING_LIGHTS_STATE), VEHICLE_LIGHT_STATE);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HEADLIGHTS_SWITCH), VEHICLE_LIGHT_SWITCH);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HIGH_BEAM_LIGHTS_SWITCH), VEHICLE_LIGHT_SWITCH);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.FOG_LIGHTS_SWITCH), VEHICLE_LIGHT_SWITCH);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.HAZARD_LIGHTS_SWITCH), VEHICLE_LIGHT_SWITCH);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.CABIN_LIGHTS_SWITCH), VEHICLE_LIGHT_SWITCH);
        this.mPropToValidValue.put(Integer.valueOf(VehicleProperty.READING_LIGHTS_SWITCH), VEHICLE_LIGHT_SWITCH);
        this.mPropToValidBitFlag.put(Integer.valueOf(VehicleProperty.HVAC_FAN_DIRECTION_AVAILABLE), Integer.valueOf(HVAC_FAN_DIRECTION_COMBINATIONS));
        this.mPropToValidBitFlag.put(Integer.valueOf(VehicleProperty.HVAC_FAN_DIRECTION), Integer.valueOf(HVAC_FAN_DIRECTION_COMBINATIONS));
    }

    public String getReadPermission(int propId) {
        Pair<String, String> p = this.mProps.get(propId);
        if (p != null) {
            if (p.first == null) {
                Log.e(TAG, "propId is not available for reading : 0x" + Integer.toHexString(propId));
            }
            return (String) p.first;
        }
        if (isVendorProperty(propId)) {
            return Manifest.permission.CAR_VENDOR_EXTENSION;
        }
        return null;
    }

    public String getWritePermission(int propId) {
        Pair<String, String> p = this.mProps.get(propId);
        if (p != null) {
            if (p.second == null) {
                Log.e(TAG, "propId is not writable : 0x" + Integer.toHexString(propId));
            }
            return (String) p.second;
        }
        if (isVendorProperty(propId)) {
            return Manifest.permission.CAR_VENDOR_EXTENSION;
        }
        return null;
    }

    private static boolean isVendorProperty(int propId) {
        return ((-268435456) & propId) == 536870912;
    }

    public boolean isSupportedProperty(int propId) {
        return this.mProps.get(propId) != null || isVendorProperty(propId);
    }

    public boolean isPropertyToChangeUnits(int propertyId) {
        return this.mPropForUnits.contains(Integer.valueOf(propertyId));
    }

    public void customizeVendorPermission(List<Integer> configArray) {
        if (configArray == null || configArray.size() % 3 != 0) {
            throw new IllegalArgumentException("ConfigArray for SUPPORT_CUSTOMIZE_VENDOR_PERMISSION is wrong");
        }
        int propId = 0;
        while (propId < configArray.size()) {
            int index = propId + 1;
            int propId2 = configArray.get(propId).intValue();
            if (!isVendorProperty(propId2)) {
                throw new IllegalArgumentException("Property Id: " + propId2 + " is not in vendor range");
            }
            int index2 = index + 1;
            int readPermission = configArray.get(index).intValue();
            int index3 = index2 + 1;
            int writePermission = configArray.get(index2).intValue();
            this.mProps.put(propId2, new Pair<>(toPermissionString(readPermission, propId2), toPermissionString(writePermission, propId2)));
            propId = index3;
        }
    }

    private String toPermissionString(int permissionEnum, int propId) {
        switch (permissionEnum) {
            case -268435456:
                return null;
            case 65536:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_1;
            case 69632:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_1;
            case 131072:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_2;
            case 135168:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_2;
            case 196608:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_3;
            case 200704:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_3;
            case 262144:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_4;
            case 266240:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_4;
            case 327680:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_5;
            case 331776:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_5;
            case 393216:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_6;
            case 397312:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_6;
            case 458752:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_7;
            case 462848:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_7;
            case 524288:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_8;
            case 528384:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_8;
            case 589824:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_9;
            case 593920:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_9;
            case 655360:
                return Manifest.permission.SET_CAR_VENDOR_CATEGORY_10;
            case 659456:
                return Manifest.permission.GET_CAR_VENDOR_CATEGORY_10;
            default:
                switch (permissionEnum) {
                    case 0:
                        return Manifest.permission.CAR_VENDOR_EXTENSION;
                    case 1:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_WINDOW;
                    case 2:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_WINDOW;
                    case 3:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_DOOR;
                    case 4:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_DOOR;
                    case 5:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_SEAT;
                    case 6:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_SEAT;
                    case 7:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_MIRROR;
                    case 8:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_MIRROR;
                    case 9:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_INFO;
                    case 10:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_INFO;
                    case 11:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_ENGINE;
                    case 12:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_ENGINE;
                    case 13:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_HVAC;
                    case 14:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_HVAC;
                    case 15:
                        return Manifest.permission.SET_CAR_VENDOR_CATEGORY_LIGHT;
                    case 16:
                        return Manifest.permission.GET_CAR_VENDOR_CATEGORY_LIGHT;
                    default:
                        throw new IllegalArgumentException("permission Id: " + permissionEnum + " for property:" + propId + " is invalid vendor permission Id");
                }
        }
    }

    public boolean checkPayload(VehiclePropValue propValue) {
        if ((propValue.prop & VehiclePropertyType.MASK) == 14680064) {
            return true;
        }
        if (!checkFormatForAllProperties(propValue)) {
            Log.e(TAG, "Property value" + propValue + "has an invalid data format");
            return false;
        }
        if (this.mPropToValidValue.containsKey(Integer.valueOf(propValue.prop))) {
            return checkDataEnum(propValue);
        }
        if (this.mPropToValidBitFlag.containsKey(Integer.valueOf(propValue.prop))) {
            return checkValidBitFlag(propValue);
        }
        return true;
    }

    private boolean checkValidBitFlag(VehiclePropValue propValue) {
        int flagCombination = this.mPropToValidBitFlag.get(Integer.valueOf(propValue.prop)).intValue();
        Iterator<Integer> it = propValue.value.int32Values.iterator();
        while (it.hasNext()) {
            int value = it.next().intValue();
            if ((value & flagCombination) != value) {
                return false;
            }
        }
        return true;
    }

    private boolean checkFormatForAllProperties(VehiclePropValue propValue) {
        int propId = propValue.prop;
        VehiclePropValue.RawValue rawValue = propValue.value;
        int sizeOfAllValue = rawValue.int32Values.size() + rawValue.floatValues.size() + rawValue.int64Values.size() + rawValue.bytes.size() + rawValue.stringValue.length();
        if (sizeOfAllValue == 0) {
            Log.e(TAG, "Property value is empty: " + propValue);
            return false;
        }
        int i = 16711680 & propId;
        if (i == 1048576) {
            return sizeOfAllValue == rawValue.stringValue.length();
        }
        if (i == 2097152 || i == 4194304) {
            return sizeOfAllValue == 1 && rawValue.int32Values.size() == 1;
        }
        if (i == 4259840) {
            return sizeOfAllValue == rawValue.int32Values.size();
        }
        if (i == 5242880) {
            return sizeOfAllValue == 1 && rawValue.int64Values.size() == 1;
        }
        if (i == 5308416) {
            return sizeOfAllValue == rawValue.int64Values.size();
        }
        if (i == 6291456) {
            return sizeOfAllValue == 1 && rawValue.floatValues.size() == 1;
        }
        if (i == 6356992) {
            return sizeOfAllValue == rawValue.floatValues.size();
        }
        if (i == 7340032) {
            return sizeOfAllValue == rawValue.bytes.size();
        }
        throw new IllegalArgumentException("Unexpected property type for propId: " + Integer.toHexString(propId));
    }

    private boolean checkDataEnum(VehiclePropValue propValue) {
        int propId = propValue.prop;
        VehiclePropValue.RawValue rawValue = propValue.value;
        Set<Integer> validValue = this.mPropToValidValue.get(Integer.valueOf(propId));
        Iterator<Integer> it = rawValue.int32Values.iterator();
        while (it.hasNext()) {
            int value = it.next().intValue();
            if (!validValue.contains(Integer.valueOf(value))) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getIntegersFromDataEnums(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Integer> integerList = new ArrayList<>(5);
        for (Field f : fields) {
            if (f.getType() == Integer.TYPE) {
                try {
                    integerList.add(Integer.valueOf(f.getInt(clazz)));
                } catch (Exception e) {
                    Log.w(TAG, "Failed to get value");
                }
            }
        }
        return integerList;
    }

    private static int generateAllCombination(Class clazz) {
        List<Integer> allBits = getIntegersFromDataEnums(clazz);
        int combination = allBits.get(0).intValue();
        for (int i = 1; i < allBits.size(); i++) {
            combination |= allBits.get(i).intValue();
        }
        return combination;
    }
}
