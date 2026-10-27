package com.android.car.hal;

import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.hardware.automotive.vehicle.V2_0.VehicleArea;
import android.hardware.automotive.vehicle.V2_0.VehicleAreaConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehiclePropertyType;
import android.util.Log;
import com.android.car.CarServiceUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class CarPropertyUtils {
    private static final int CONFIG_ARRAY_LENGTH = 9;
    private static final int[] DEFAULT_AREAIDS = {0};
    private static final String TAG = "CarPropertyUtils";

    private CarPropertyUtils() {
    }

    static CarPropertyValue<?> toCarPropertyValue(VehiclePropValue halValue, int propertyId) {
        boolean z;
        String str;
        String str2;
        String string;
        long loValue;
        int inValue;
        float flValue;
        int boValue;
        Class<?> clazz = getJavaClass(halValue.prop & VehiclePropertyType.MASK);
        int areaId = halValue.areaId;
        int status = halValue.status;
        long timestamp = halValue.timestamp;
        VehiclePropValue.RawValue v = halValue.value;
        if (Boolean.class == clazz) {
            ArrayList<Integer> int32Values = v.int32Values;
            if (!int32Values.isEmpty()) {
                int boValue2 = int32Values.get(0).intValue();
                boValue = boValue2;
            } else {
                Log.e(TAG, "Boolean type int32Values is Null");
                boValue = 0;
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, Boolean.valueOf(boValue == 1));
        }
        if (Float.class == clazz) {
            ArrayList<Float> floatValues = v.floatValues;
            if (!floatValues.isEmpty()) {
                float flValue2 = floatValues.get(0).floatValue();
                flValue = flValue2;
            } else {
                Log.e(TAG, "Float type floatValues is Null");
                flValue = 0.0f;
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, Float.valueOf(flValue));
        }
        if (Integer.class == clazz) {
            ArrayList<Integer> int32Values2 = v.int32Values;
            if (!int32Values2.isEmpty()) {
                int inValue2 = int32Values2.get(0).intValue();
                inValue = inValue2;
            } else {
                Log.e(TAG, "Integer type int32Values is Null");
                inValue = 0;
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, Integer.valueOf(inValue));
        }
        if (Long.class == clazz) {
            ArrayList<Long> int64Values = v.int64Values;
            if (!int64Values.isEmpty()) {
                long loValue2 = int64Values.get(0).longValue();
                loValue = loValue2;
            } else {
                Log.e(TAG, "Long type int64Values is Null");
                loValue = 0;
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, Long.valueOf(loValue));
        }
        if (Float[].class == clazz) {
            Float[] values = new Float[v.floatValues.size()];
            for (int i = 0; i < values.length; i++) {
                values[i] = v.floatValues.get(i);
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, values);
        }
        if (Integer[].class == clazz) {
            Integer[] values2 = new Integer[v.int32Values.size()];
            for (int i2 = 0; i2 < values2.length; i2++) {
                values2[i2] = v.int32Values.get(i2);
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, values2);
        }
        if (Long[].class == clazz) {
            Long[] values3 = new Long[v.int64Values.size()];
            for (int i3 = 0; i3 < values3.length; i3++) {
                values3[i3] = v.int64Values.get(i3);
            }
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, values3);
        }
        if (String.class == clazz) {
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, v.stringValue);
        }
        if (byte[].class == clazz) {
            byte[] halData = CarServiceUtils.toByteArray(v.bytes);
            return new CarPropertyValue<>(propertyId, areaId, status, timestamp, halData);
        }
        if (Object[].class == clazz) {
            try {
                string = v.toString();
                z = false;
                str = "Unexpected type in: ";
                str2 = TAG;
            } catch (Exception e) {
                z = false;
                str = "Unexpected type in: ";
                str2 = TAG;
            }
            try {
                return new CarPropertyValue<>(propertyId, areaId, status, timestamp, string);
            } catch (Exception e2) {
                Log.e(str2, str + propertyId + " CarPropertyUtils data type convert exception!!!!!!!");
                return new CarPropertyValue<>(propertyId, areaId, status, timestamp, Boolean.valueOf(z));
            }
        }
        throw new IllegalArgumentException("Unexpected type in: " + propertyId);
    }

    static CarPropertyValue<?> toMixedCarPropertyValue(VehiclePropValue halValue, int propertyId, boolean containBoolean) {
        int areaId = halValue.areaId;
        int status = halValue.status;
        long timestamp = halValue.timestamp;
        VehiclePropValue.RawValue value = halValue.value;
        List<Object> valuesList = new ArrayList<>();
        valuesList.add(value.stringValue);
        if (containBoolean) {
            boolean boolValue = value.int32Values.get(0).intValue() == 1;
            valuesList.add(Boolean.valueOf(boolValue));
            valuesList.addAll(value.int32Values.subList(1, value.int32Values.size()));
        } else {
            valuesList.addAll(value.int32Values);
        }
        valuesList.addAll(value.int64Values);
        valuesList.addAll(value.floatValues);
        valuesList.addAll(value.bytes);
        return new CarPropertyValue<>(propertyId, areaId, status, timestamp, valuesList.toArray());
    }

    static VehiclePropValue toVehiclePropValue(CarPropertyValue carPropertyValue, int i) {
        VehiclePropValue vehiclePropValue = new VehiclePropValue();
        vehiclePropValue.prop = i;
        vehiclePropValue.areaId = carPropertyValue.getAreaId();
        VehiclePropValue.RawValue rawValue = vehiclePropValue.value;
        Object value = carPropertyValue.getValue();
        if (value instanceof Boolean) {
            rawValue.int32Values.add(Integer.valueOf(((Boolean) value).booleanValue() ? 1 : 0));
        } else if (value instanceof Integer) {
            rawValue.int32Values.add((Integer) value);
        } else if (value instanceof Integer[]) {
            Collections.addAll(rawValue.int32Values, (Integer[]) value);
        } else if (value instanceof Float) {
            rawValue.floatValues.add((Float) value);
        } else if (value instanceof Float[]) {
            Collections.addAll(rawValue.floatValues, (Float[]) value);
        } else if (value instanceof Long) {
            rawValue.int64Values.add((Long) value);
        } else if (value instanceof Long[]) {
            Collections.addAll(rawValue.int64Values, (Long[]) value);
        } else if (value instanceof String) {
            rawValue.stringValue = (String) value;
        } else if (value instanceof byte[]) {
            for (byte b : (byte[]) value) {
                rawValue.bytes.add(Byte.valueOf(b));
            }
        } else {
            throw new IllegalArgumentException("Unexpected type in: " + carPropertyValue);
        }
        return vehiclePropValue;
    }

    static VehiclePropValue toMixedVehiclePropValue(CarPropertyValue carPropertyValue, int i, int[] iArr) {
        if (iArr.length != 9) {
            throw new IllegalArgumentException("Unexpected configArray in:" + carPropertyValue);
        }
        VehiclePropValue vehiclePropValue = new VehiclePropValue();
        vehiclePropValue.prop = i;
        vehiclePropValue.areaId = carPropertyValue.getAreaId();
        VehiclePropValue.RawValue rawValue = vehiclePropValue.value;
        Object[] objArr = (Object[]) carPropertyValue.getValue();
        int i2 = 0;
        if (iArr[0] != 0) {
            rawValue.stringValue = (String) objArr[0];
            i2 = 0 + 1;
        }
        if (iArr[1] != 0) {
            rawValue.int32Values.add(Integer.valueOf(((Boolean) objArr[i2]).booleanValue() ? 1 : 0));
            i2++;
        }
        for (int i3 = iArr[2] + iArr[3]; i3 != 0; i3--) {
            rawValue.int32Values.add((Integer) objArr[i2]);
            i2++;
        }
        for (int i4 = iArr[4] + iArr[5]; i4 != 0; i4--) {
            rawValue.int64Values.add((Long) objArr[i2]);
            i2++;
        }
        for (int i5 = iArr[6] + iArr[7]; i5 != 0; i5--) {
            rawValue.floatValues.add((Float) objArr[i2]);
            i2++;
        }
        if (iArr[8] != 0) {
            Collections.addAll(rawValue.bytes, (Byte[]) objArr[i2]);
        }
        return vehiclePropValue;
    }

    static CarPropertyConfig<?> toCarPropertyConfig(VehiclePropConfig p, int propertyId) {
        int areaType = getVehicleAreaType(p.prop & VehicleArea.MASK);
        Class<?> clazz = getJavaClass(p.prop & VehiclePropertyType.MASK);
        if (p.areaConfigs.isEmpty()) {
            return CarPropertyConfig.newBuilder(clazz, propertyId, areaType, 1).addAreas(DEFAULT_AREAIDS).setAccess(p.access).setChangeMode(p.changeMode).setConfigArray(p.configArray).setConfigString(p.configString).setMaxSampleRate(p.maxSampleRate).setMinSampleRate(p.minSampleRate).build();
        }
        CarPropertyConfig.Builder builder = CarPropertyConfig.newBuilder(clazz, propertyId, areaType, p.areaConfigs.size()).setAccess(p.access).setChangeMode(p.changeMode).setConfigArray(p.configArray).setConfigString(p.configString).setMaxSampleRate(p.maxSampleRate).setMinSampleRate(p.minSampleRate);
        for (VehicleAreaConfig area : p.areaConfigs) {
            if (classMatched(Integer.class, clazz)) {
                builder.addAreaConfig(area.areaId, Integer.valueOf(area.minInt32Value), Integer.valueOf(area.maxInt32Value));
            } else if (classMatched(Float.class, clazz)) {
                builder.addAreaConfig(area.areaId, Float.valueOf(area.minFloatValue), Float.valueOf(area.maxFloatValue));
            } else if (classMatched(Long.class, clazz)) {
                builder.addAreaConfig(area.areaId, Long.valueOf(area.minInt64Value), Long.valueOf(area.maxInt64Value));
            } else if (classMatched(Boolean.class, clazz) || classMatched(Float[].class, clazz) || classMatched(Integer[].class, clazz) || classMatched(Long[].class, clazz) || classMatched(String.class, clazz) || classMatched(byte[].class, clazz) || classMatched(Object[].class, clazz)) {
                builder.addArea(area.areaId);
            } else {
                throw new IllegalArgumentException("Unexpected type: " + clazz);
            }
        }
        return builder.build();
    }

    private static int getVehicleAreaType(int halArea) {
        if (halArea == 16777216) {
            return 0;
        }
        if (halArea == 50331648) {
            return 2;
        }
        if (halArea == 67108864) {
            return 5;
        }
        if (halArea == 83886080) {
            return 3;
        }
        if (halArea == 100663296) {
            return 4;
        }
        if (halArea == 117440512) {
            return 6;
        }
        throw new RuntimeException("Unsupported area type " + halArea);
    }

    private static Class<?> getJavaClass(int halType) {
        if (halType == 1048576) {
            return String.class;
        }
        if (halType == 2097152) {
            return Boolean.class;
        }
        if (halType == 4194304) {
            return Integer.class;
        }
        if (halType == 4259840) {
            return Integer[].class;
        }
        if (halType == 5242880) {
            return Long.class;
        }
        if (halType == 5308416) {
            return Long[].class;
        }
        if (halType == 6291456) {
            return Float.class;
        }
        if (halType == 6356992) {
            return Float[].class;
        }
        if (halType == 7340032) {
            return byte[].class;
        }
        if (halType == 14680064) {
            return Object[].class;
        }
        Log.e(TAG, "getJavaClass Unexpected type: " + Integer.toHexString(halType));
        return Object[].class;
    }

    private static boolean classMatched(Class<?> class1, Class<?> class2) {
        return class1 == class2 || class1.getComponentType() == class2;
    }
}
