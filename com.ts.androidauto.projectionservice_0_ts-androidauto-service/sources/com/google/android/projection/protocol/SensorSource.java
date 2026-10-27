package com.google.android.projection.protocol;

import android.location.Location;
import android.util.Log;
import com.google.android.projection.proto.Protos;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public class SensorSource implements CarServiceProvider {
    public static final int GEAR_1 = 1;
    public static final int GEAR_10 = 10;
    public static final int GEAR_2 = 2;
    public static final int GEAR_3 = 3;
    public static final int GEAR_4 = 4;
    public static final int GEAR_5 = 5;
    public static final int GEAR_6 = 6;
    public static final int GEAR_7 = 7;
    public static final int GEAR_8 = 8;
    public static final int GEAR_9 = 9;
    public static final int GEAR_DRIVE = 100;
    public static final int GEAR_NEUTRAL = 0;
    public static final int GEAR_PARK = 101;
    public static final int GEAR_REVERSE = 102;
    public static final int LOCATION_CHARACTERIZATION_ACCELEROMETER_FUSION = 4;
    public static final int LOCATION_CHARACTERIZATION_CAR_SPEED_FUSION = 64;
    public static final int LOCATION_CHARACTERIZATION_COMPASS_FUSION = 8;
    public static final int LOCATION_CHARACTERIZATION_DEAD_RECKONED = 128;
    public static final int LOCATION_CHARACTERIZATION_GYROSCOPE_FUSION = 2;
    public static final int LOCATION_CHARACTERIZATION_PRIOR_LOCATIONS = 1;
    public static final int LOCATION_CHARACTERIZATION_RAW_GPS_ONLY = 256;
    public static final int LOCATION_CHARACTERIZATION_STEERING_ANGLE_FUSION = 32;
    public static final int LOCATION_CHARACTERIZATION_WHEEL_SPEED_FUSION = 16;
    private static final int NOT_SET = Integer.MIN_VALUE;
    public static final int SENSOR_TYPE_ACCELEROMETER_DATA = 19;
    public static final int SENSOR_TYPE_COMPASS = 2;
    public static final int SENSOR_TYPE_DEAD_RECKONING_DATA = 14;
    public static final int SENSOR_TYPE_DOOR_DATA = 16;
    public static final int SENSOR_TYPE_DRIVING_STATUS = 13;
    public static final int SENSOR_TYPE_ENVIRONMENT = 11;
    public static final int SENSOR_TYPE_FUEL = 6;
    public static final int SENSOR_TYPE_GEAR = 8;
    public static final int SENSOR_TYPE_GPS_SATELLITE_DATA = 21;
    public static final int SENSOR_TYPE_GYROSCOPE_DATA = 20;
    public static final int SENSOR_TYPE_HVAC = 12;
    public static final int SENSOR_TYPE_LIGHT_DATA = 17;
    public static final int SENSOR_TYPE_LOCATION = 1;
    public static final int SENSOR_TYPE_NIGHT_MODE = 10;
    public static final int SENSOR_TYPE_OBDII_DIAGNOSTIC_CODE = 9;
    public static final int SENSOR_TYPE_ODOMETER = 5;
    public static final int SENSOR_TYPE_PARKING_BRAKE = 7;
    public static final int SENSOR_TYPE_PASSENGER_DATA = 15;
    public static final int SENSOR_TYPE_RPM = 4;
    public static final int SENSOR_TYPE_SPEED = 3;
    public static final int SENSOR_TYPE_TIRE_PRESSURE_DATA = 18;
    public static final String TAG = "AHU.SENSOR";
    private final int[] mDefaultSupportedSensors;
    private final int[] mEvConnectorTypes;
    private final int[] mFuelTypes;
    private final int mLocationCharacterization;
    private Protos.LocationData mLocationOverride;
    private long mNativeSensorSource;
    private Protos.AccelerometerData mOverrideAccelerometerData;
    private ByteString mOverrideDiagnostics;
    private Protos.EnvironmentData mOverrideEnvironmentData;
    private Protos.FuelData mOverrideFuelLevel;
    private Protos.Gear mOverrideGear;
    private Protos.GpsSatelliteData mOverrideGpsSatelliteData;
    private Protos.GyroscopeData mOverrideGyroscopeData;
    private Protos.HvacData mOverrideHvacData;
    private Boolean mOverrideNightMode;
    private Boolean mOverrideParkingBrake;
    private final int[] mSupportedSensors;
    private int mOverrideSpeedE3 = Integer.MIN_VALUE;
    private int mOverrideBearingE6 = Integer.MIN_VALUE;
    private int mOverridePitchE6 = Integer.MIN_VALUE;
    private int mOverrideRollE6 = Integer.MIN_VALUE;
    private int mOverrideRpmE3 = Integer.MIN_VALUE;
    private int mOverrideKmsE1 = Integer.MIN_VALUE;
    private int mOverrideDrivingStatus = Integer.MIN_VALUE;

    private native int nativeInit(int i, long j, int i2, int[] iArr, int[] iArr2) throws IllegalStateException;

    private native void nativeRegisterSensor(int i);

    private native int nativeReportAccelerometerData(boolean z, int i, boolean z2, int i2, boolean z3, int i3);

    private native int nativeReportCompassData(int i, boolean z, int i2, boolean z2, int i3);

    private native int nativeReportDeadReckoningData(boolean z, int i, int[] iArr);

    private native int nativeReportDiagnosticsData(byte[] bArr);

    private native int nativeReportDoorData(boolean z, boolean z2, boolean z3, boolean z4, boolean[] zArr);

    private native int nativeReportDrivingStatusData(int i);

    private native int nativeReportEnvironmentData(boolean z, int i, boolean z2, int i2, boolean z3, int i3);

    private native int nativeReportFuelData(boolean z, int i, boolean z2, int i2, boolean z3, boolean z4);

    private native int nativeReportGearData(int i);

    private native int nativeReportGpsSatelliteData(int i, boolean z, int i2, int[] iArr, int[] iArr2, boolean[] zArr, int[] iArr3, int[] iArr4);

    private native int nativeReportGyroscopeData(boolean z, int i, boolean z2, int i2, boolean z3, int i3);

    private native int nativeReportHvacData(boolean z, int i, boolean z2, int i2);

    private native int nativeReportLightData(boolean z, int i, boolean z2, int i2, boolean z3, boolean z4);

    private native int nativeReportLocation(long j, int i, int i2, boolean z, int i3, boolean z2, int i4, boolean z3, int i5, boolean z4, int i6);

    private native int nativeReportNightModeData(boolean z);

    private native int nativeReportOdometerData(int i, boolean z, int i2);

    private native int nativeReportParkingBrakeData(boolean z);

    private native int nativeReportPassengerData(boolean z);

    private native int nativeReportRpmData(int i);

    private native int nativeReportSensorError(int i, int i2);

    private native int nativeReportSpeedData(int i, boolean z, boolean z2, boolean z3, int i2);

    private native int nativeReportTirePressureData(int[] iArr);

    private native void nativeShutdown();

    public SensorSource(int[] supportedSensors, int locationCharacterization, Protos.FuelType[] fuelTypes, Protos.EvConnectorType[] connectorTypes) {
        int[] iArr = {1, 3, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 19, 20, 21};
        this.mDefaultSupportedSensors = iArr;
        if (supportedSensors == null) {
            this.mSupportedSensors = iArr;
        } else {
            this.mSupportedSensors = supportedSensors;
        }
        this.mLocationCharacterization = locationCharacterization;
        this.mFuelTypes = new int[fuelTypes.length];
        for (int i = 0; i < fuelTypes.length; i++) {
            this.mFuelTypes[i] = fuelTypes[i].getNumber();
        }
        int i2 = connectorTypes.length;
        this.mEvConnectorTypes = new int[i2];
        for (int i3 = 0; i3 < connectorTypes.length; i3++) {
            this.mEvConnectorTypes[i3] = connectorTypes[i3].getNumber();
        }
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver, this.mLocationCharacterization, this.mFuelTypes, this.mEvConnectorTypes) != 0) {
            return false;
        }
        for (int sensor : this.mSupportedSensors) {
            nativeRegisterSensor(sensor);
        }
        return true;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeSensorSource;
    }

    public void reportLocation(Location location) {
        int latitude;
        int longitude;
        boolean hasAccuracy;
        int accuracy;
        boolean hasAltitude;
        int altitude;
        boolean hasSpeed;
        int speed;
        boolean hasBearing;
        int bearing;
        int longitude2 = 0;
        int accuracy2 = 0;
        int altitude2 = 0;
        int bearing2 = 0;
        if (location != null && this.mLocationOverride == null) {
            int latitude2 = (int) Math.round(location.getLatitude() * 1.0E7d);
            int longitude3 = (int) Math.round(location.getLongitude() * 1.0E7d);
            boolean hasAccuracy2 = location.hasAccuracy();
            int accuracy3 = (int) Math.round(((double) location.getAccuracy()) * 1000.0d);
            boolean hasAltitude2 = location.hasAltitude();
            int altitude3 = (int) Math.round(location.getAltitude() * 100.0d);
            boolean hasSpeed2 = location.hasSpeed();
            int speed2 = (int) Math.round(((double) location.getSpeed()) * 1000.0d);
            boolean hasBearing2 = location.hasBearing();
            int bearing3 = (int) Math.round(((double) location.getBearing()) * 1000000.0d);
            latitude = latitude2;
            longitude = longitude3;
            hasAccuracy = hasAccuracy2;
            accuracy = accuracy3;
            hasAltitude = hasAltitude2;
            altitude = altitude3;
            hasSpeed = hasSpeed2;
            speed = speed2;
            hasBearing = hasBearing2;
            bearing = bearing3;
        } else {
            Protos.LocationData locationData = this.mLocationOverride;
            if (locationData != null) {
                int latitude3 = locationData.hasLatitudeE7() ? this.mLocationOverride.getLatitudeE7() : 0;
                if (this.mLocationOverride.hasLongitudeE7()) {
                    longitude2 = this.mLocationOverride.getLongitudeE7();
                }
                boolean hasAccuracy3 = this.mLocationOverride.hasAccuracyE3();
                if (this.mLocationOverride.hasAccuracyE3()) {
                    accuracy2 = this.mLocationOverride.getAccuracyE3();
                }
                boolean hasAltitude3 = this.mLocationOverride.hasAltitudeE2();
                if (this.mLocationOverride.hasAltitudeE2()) {
                    altitude2 = this.mLocationOverride.getAltitudeE2();
                }
                boolean hasBearing3 = this.mLocationOverride.hasBearingE6();
                if (this.mLocationOverride.hasBearingE6()) {
                    bearing2 = this.mLocationOverride.getBearingE6();
                }
                boolean hasSpeed3 = this.mLocationOverride.hasSpeedE3();
                if (!this.mLocationOverride.hasSpeedE3()) {
                    latitude = latitude3;
                    longitude = longitude2;
                    hasAccuracy = hasAccuracy3;
                    accuracy = accuracy2;
                    hasAltitude = hasAltitude3;
                    altitude = altitude2;
                    hasSpeed = hasSpeed3;
                    speed = 0;
                    hasBearing = hasBearing3;
                    bearing = bearing2;
                } else {
                    int speed3 = this.mLocationOverride.getSpeedE3();
                    latitude = latitude3;
                    longitude = longitude2;
                    hasAccuracy = hasAccuracy3;
                    accuracy = accuracy2;
                    hasAltitude = hasAltitude3;
                    altitude = altitude2;
                    hasSpeed = hasSpeed3;
                    speed = speed3;
                    hasBearing = hasBearing3;
                    bearing = bearing2;
                }
            } else {
                Log.w("AHU.SENSOR", "Location and mGpsOverride are both null.");
                return;
            }
        }
        nativeReportLocation(0L, latitude, longitude, hasAccuracy, accuracy, hasAltitude, altitude, hasSpeed, speed, hasBearing, bearing);
    }

    public void reportCompassData(int bearing, boolean hasPitch, int pitch, boolean hasRoll, int roll) {
        int bearing2 = (int) (((double) bearing) * 1000000.0d);
        int pitch2 = (int) (((double) pitch) * 1000000.0d);
        int roll2 = (int) (((double) roll) * 1000000.0d);
        if (this.mOverrideBearingE6 != Integer.MIN_VALUE) {
            bearing2 = this.mOverrideBearingE6;
            hasPitch = this.mOverridePitchE6 != Integer.MIN_VALUE;
            pitch2 = this.mOverridePitchE6;
            hasRoll = this.mOverrideRollE6 != Integer.MIN_VALUE;
            roll2 = this.mOverrideRollE6;
        }
        nativeReportCompassData(bearing2, hasPitch, pitch2, hasRoll, roll2);
    }

    public void reportSpeedData(float speed, boolean hasCruiseEngaged, boolean cruiseEngaged, boolean hasCruiseSetSpeed, int cruiseSetSpeed) {
        float speed2 = (float) (((double) speed) * 1000.0d);
        int i = this.mOverrideSpeedE3;
        if (i != Integer.MIN_VALUE) {
            speed2 = i;
        }
        nativeReportSpeedData((int) speed2, hasCruiseEngaged, cruiseEngaged, hasCruiseSetSpeed, cruiseSetSpeed);
    }

    public void reportRpmData(double rpm) {
        double rpm2 = rpm * 1000.0d;
        int i = this.mOverrideRpmE3;
        if (i != Integer.MIN_VALUE) {
            rpm2 = i;
        }
        nativeReportRpmData((int) rpm2);
    }

    public void reportOdometerData(double kms, boolean hasTripKms, double tripKms) {
        double kmsE1 = kms * 10.0d;
        double tripKms2 = tripKms * 10.0d;
        int i = this.mOverrideKmsE1;
        if (i != Integer.MIN_VALUE) {
            kmsE1 = i;
        }
        nativeReportOdometerData((int) kmsE1, hasTripKms, (int) tripKms2);
    }

    public void reportFuelData(boolean hasRemaining, int fuelRemainingPercent, boolean hasRange, int rangeKm, boolean hasLowLevelWarning, boolean lowLevelWarning) {
        Protos.FuelData fuelData = this.mOverrideFuelLevel;
        if (fuelData != null) {
            hasRemaining = fuelData.hasFuelLevel();
            if (hasRemaining) {
                fuelRemainingPercent = this.mOverrideFuelLevel.getFuelLevel();
            }
            hasRange = this.mOverrideFuelLevel.hasRange();
            if (hasRange) {
                rangeKm = this.mOverrideFuelLevel.getRange();
            }
            hasLowLevelWarning = this.mOverrideFuelLevel.hasLowFuelWarning();
            if (hasLowLevelWarning) {
                lowLevelWarning = this.mOverrideFuelLevel.getLowFuelWarning();
            }
        }
        nativeReportFuelData(hasRemaining, fuelRemainingPercent, hasRange, rangeKm, hasLowLevelWarning, lowLevelWarning);
    }

    public void reportParkingBrakeData(boolean engaged) {
        Boolean bool = this.mOverrideParkingBrake;
        if (bool != null) {
            engaged = bool.booleanValue();
        }
        nativeReportParkingBrakeData(engaged);
    }

    public void reportGearData(int gear) {
        Protos.Gear gear2 = this.mOverrideGear;
        if (gear2 != null) {
            gear = gear2.getNumber();
        }
        nativeReportGearData(gear);
    }

    public void reportDiagnosticsData(byte[] data) {
        ByteString byteString = this.mOverrideDiagnostics;
        if (byteString != null) {
            data = byteString.toByteArray();
        }
        nativeReportDiagnosticsData(data);
    }

    public void reportNightModeData(boolean nightMode) {
        Boolean bool = this.mOverrideNightMode;
        if (bool != null) {
            nightMode = bool.booleanValue();
        }
        nativeReportNightModeData(nightMode);
    }

    public void overrideNightModeData(Boolean nightMode) {
        this.mOverrideNightMode = nightMode;
        if (nightMode != null) {
            nativeReportNightModeData(nightMode.booleanValue());
        }
    }

    public void reportEnvironmentData(boolean hasTemperature, float temperature, boolean hasPressure, float pressure, boolean hasRain, int rain) {
        int temperatureInt = (int) Math.round(((double) temperature) * 1000.0d);
        int pressureInt = (int) Math.round(((double) pressure) * 1000.0d);
        Protos.EnvironmentData environmentData = this.mOverrideEnvironmentData;
        if (environmentData != null) {
            hasTemperature = environmentData.hasTemperatureE3();
            if (hasTemperature) {
                temperatureInt = this.mOverrideEnvironmentData.getTemperatureE3();
            }
            hasPressure = this.mOverrideEnvironmentData.hasPressureE3();
            if (hasPressure) {
                pressureInt = this.mOverrideEnvironmentData.getPressureE3();
            }
            hasRain = this.mOverrideEnvironmentData.hasRain();
            if (hasRain) {
                pressureInt = this.mOverrideEnvironmentData.getRain();
            }
        }
        nativeReportEnvironmentData(hasTemperature, temperatureInt, hasPressure, pressureInt, hasRain, rain);
    }

    public void reportHvacData(boolean hasTargetTemperature, float targetTemperature, boolean hasCurrentTemperature, float currentTemperature) {
        int targetTemperatureInt = (int) Math.round(((double) targetTemperature) * 1000.0d);
        int currentTemperatureInt = (int) Math.round(((double) currentTemperature) * 1000.0d);
        Protos.HvacData hvacData = this.mOverrideHvacData;
        if (hvacData != null) {
            hasTargetTemperature = hvacData.hasTargetTemperatureE3();
            if (hasTargetTemperature) {
                targetTemperatureInt = this.mOverrideHvacData.getTargetTemperatureE3();
            }
            hasCurrentTemperature = this.mOverrideHvacData.hasCurrentTemperatureE3();
            if (hasCurrentTemperature) {
                currentTemperatureInt = this.mOverrideHvacData.getCurrentTemperatureE3();
            }
        }
        nativeReportHvacData(hasTargetTemperature, targetTemperatureInt, hasCurrentTemperature, currentTemperatureInt);
    }

    public void reportDrivingStatusData(int drivingStatus) {
        if (this.mOverrideDrivingStatus != Integer.MIN_VALUE) {
            drivingStatus = this.mOverrideDrivingStatus;
        }
        nativeReportDrivingStatusData(drivingStatus);
    }

    public void reportAccelerometerData(boolean hasAccelerationX, float accelerationX, boolean hasAccelerationY, float accelerationY, boolean hasAccelerationZ, float accelerationZ) {
        float accelerationX2 = (float) (((double) accelerationX) * 1000.0d);
        float accelerationY2 = (float) (((double) accelerationY) * 1000.0d);
        float accelerationZ2 = (float) (((double) accelerationZ) * 1000.0d);
        Protos.AccelerometerData accelerometerData = this.mOverrideAccelerometerData;
        if (accelerometerData != null) {
            hasAccelerationX = accelerometerData.hasAccelerationXE3();
            if (hasAccelerationX) {
                accelerationX2 = this.mOverrideAccelerometerData.getAccelerationXE3();
            }
            hasAccelerationY = this.mOverrideAccelerometerData.hasAccelerationYE3();
            if (hasAccelerationY) {
                accelerationY2 = this.mOverrideAccelerometerData.getAccelerationYE3();
            }
            hasAccelerationZ = this.mOverrideAccelerometerData.hasAccelerationZE3();
            if (hasAccelerationZ) {
                accelerationZ2 = this.mOverrideAccelerometerData.getAccelerationZE3();
            }
        }
        nativeReportAccelerometerData(hasAccelerationX, (int) accelerationX2, hasAccelerationY, (int) accelerationY2, hasAccelerationZ, (int) accelerationZ2);
    }

    public void reportGyroscopeData(boolean hasRotationSpeedX, float rotationSpeedX, boolean hasRotationSpeedY, float rotationSpeedY, boolean hasRotationSpeedZ, float rotationSpeedZ) {
        float rotationSpeedX2 = (float) (((double) rotationSpeedX) * 1000.0d);
        float rotationSpeedY2 = (float) (((double) rotationSpeedY) * 1000.0d);
        float rotationSpeedZ2 = (float) (((double) rotationSpeedZ) * 1000.0d);
        Protos.GyroscopeData gyroscopeData = this.mOverrideGyroscopeData;
        if (gyroscopeData != null) {
            hasRotationSpeedX = gyroscopeData.hasRotationSpeedXE3();
            if (hasRotationSpeedX) {
                rotationSpeedX2 = this.mOverrideGyroscopeData.getRotationSpeedXE3();
            }
            hasRotationSpeedY = this.mOverrideGyroscopeData.hasRotationSpeedYE3();
            if (hasRotationSpeedY) {
                rotationSpeedY2 = this.mOverrideGyroscopeData.getRotationSpeedYE3();
            }
            hasRotationSpeedZ = this.mOverrideGyroscopeData.hasRotationSpeedZE3();
            if (hasRotationSpeedZ) {
                rotationSpeedZ2 = this.mOverrideGyroscopeData.getRotationSpeedZE3();
            }
        }
        nativeReportGyroscopeData(hasRotationSpeedX, (int) rotationSpeedX2, hasRotationSpeedY, (int) rotationSpeedY2, hasRotationSpeedZ, (int) rotationSpeedZ2);
    }

    private static int[] convertFloatArray(float[] array, float scale) {
        if (array == null) {
            return null;
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Math.round(array[i] * scale);
        }
        return result;
    }

    public void reportGpsSatelliteData(int numberInUse, boolean hasNumberInView, int numberInView, int[] prns, float[] snrs, boolean[] usedInFix, float[] azimuths, float[] elevations) {
        int[] prns2;
        boolean[] usedInFix2;
        int[] snrsE3;
        int[] azimuthsE3;
        int[] elevationsE3;
        int numberInUse2;
        boolean hasNumberInView2;
        int numberInView2;
        Protos.GpsSatelliteData localOverrideGpsSatelliteData = this.mOverrideGpsSatelliteData;
        if (localOverrideGpsSatelliteData != null) {
            int numberInUse3 = localOverrideGpsSatelliteData.getNumberInUse();
            boolean hasNumberInView3 = localOverrideGpsSatelliteData.hasNumberInView();
            int[] prns3 = null;
            int[] elevationsE32 = null;
            int[] azimuthsE32 = null;
            int[] snrsE32 = null;
            boolean[] usedInFix3 = null;
            if (!hasNumberInView3) {
                snrsE3 = null;
                azimuthsE3 = null;
                elevationsE3 = null;
                numberInUse2 = numberInUse3;
                hasNumberInView2 = hasNumberInView3;
                prns2 = null;
                usedInFix2 = null;
                numberInView2 = numberInView;
            } else {
                int numberInView3 = localOverrideGpsSatelliteData.getNumberInView();
                int satellitesCount = localOverrideGpsSatelliteData.getSatellitesCount();
                if (satellitesCount > 0) {
                    prns3 = new int[satellitesCount];
                    snrsE32 = new int[satellitesCount];
                    usedInFix3 = new boolean[satellitesCount];
                    if (localOverrideGpsSatelliteData.getSatellites(0).hasAzimuthE3()) {
                        azimuthsE32 = new int[satellitesCount];
                        elevationsE32 = new int[satellitesCount];
                    }
                    for (int i = 0; i < satellitesCount; i++) {
                        Protos.GpsSatellite satellite = localOverrideGpsSatelliteData.getSatellites(i);
                        prns3[i] = satellite.getPrn();
                        snrsE32[i] = satellite.getSnrE3();
                        usedInFix3[i] = satellite.getUsedInFix();
                        if (azimuthsE32 != null && elevationsE32 != null) {
                            azimuthsE32[i] = satellite.getAzimuthE3();
                            elevationsE32[i] = satellite.getElevationE3();
                        }
                    }
                }
                snrsE3 = snrsE32;
                azimuthsE3 = azimuthsE32;
                elevationsE3 = elevationsE32;
                numberInUse2 = numberInUse3;
                hasNumberInView2 = hasNumberInView3;
                prns2 = prns3;
                usedInFix2 = usedInFix3;
                numberInView2 = numberInView3;
            }
        } else {
            int[] snrsE33 = convertFloatArray(snrs, 1000.0f);
            int[] azimuthsE33 = convertFloatArray(azimuths, 1000.0f);
            int[] elevationsE33 = convertFloatArray(elevations, 1000.0f);
            prns2 = prns;
            usedInFix2 = usedInFix;
            snrsE3 = snrsE33;
            azimuthsE3 = azimuthsE33;
            elevationsE3 = elevationsE33;
            numberInUse2 = numberInUse;
            hasNumberInView2 = hasNumberInView;
            numberInView2 = numberInView;
        }
        nativeReportGpsSatelliteData(numberInUse2, hasNumberInView2, numberInView2, prns2, snrsE3, usedInFix2, azimuthsE3, elevationsE3);
    }

    public void reportDeadReckoningData(boolean hasSteeringAngle, double steeringAngle, float[] wheelSpeed) {
        double steeringAngle2 = steeringAngle * 10.0d;
        int[] wheelSpeedE3 = new int[wheelSpeed != null ? wheelSpeed.length : 0];
        for (int i = 0; i < wheelSpeedE3.length; i++) {
            wheelSpeedE3[i] = (int) Math.round(((double) wheelSpeed[i]) * 1000.0d);
        }
        int i2 = (int) steeringAngle2;
        nativeReportDeadReckoningData(hasSteeringAngle, i2, wheelSpeedE3);
    }

    public void reportLightData(boolean hasHeadLightState, int headLightState, boolean hasTurnIndicatorState, int turnIndicatorState, boolean hasHazardLightsOn, boolean hazardLightsOn) {
        nativeReportLightData(hasHeadLightState, headLightState, hasTurnIndicatorState, turnIndicatorState, hasHazardLightsOn, hazardLightsOn);
    }

    public void reportPassengerData(boolean passengerPresent) {
        nativeReportPassengerData(passengerPresent);
    }

    public void reportDoorData(boolean hasHoodOpen, boolean hoodOpen, boolean hasTrunkOpen, boolean trunkOpen, boolean[] doorOpen) {
        nativeReportDoorData(hasHoodOpen, hoodOpen, hasTrunkOpen, trunkOpen, doorOpen);
    }

    public void reportTirePressureData(float[] tirePressure) {
        int[] tirePressureE2 = new int[tirePressure != null ? tirePressure.length : 0];
        for (int i = 0; i < tirePressureE2.length; i++) {
            tirePressureE2[i] = (int) Math.round(((double) tirePressure[i]) * 100.0d);
        }
        nativeReportTirePressureData(tirePressureE2);
    }

    public void injectForTest(Protos.SensorBatch sensors) {
        if (sensors.getLocationDataCount() == 0) {
            this.mLocationOverride = null;
        } else {
            this.mLocationOverride = sensors.getLocationData(0);
            reportLocation(null);
        }
        if (sensors.getSpeedDataCount() == 0 || !sensors.getSpeedData(0).hasSpeedE3()) {
            this.mOverrideSpeedE3 = Integer.MIN_VALUE;
        } else {
            this.mOverrideSpeedE3 = sensors.getSpeedData(0).getSpeedE3();
        }
        if (sensors.getCompassDataCount() == 0 || !sensors.getCompassData(0).hasBearingE6()) {
            this.mOverrideBearingE6 = Integer.MIN_VALUE;
            this.mOverridePitchE6 = Integer.MIN_VALUE;
            this.mOverrideRollE6 = Integer.MIN_VALUE;
        } else {
            this.mOverrideBearingE6 = sensors.getCompassData(0).getBearingE6();
            this.mOverridePitchE6 = Integer.MIN_VALUE;
            this.mOverrideRollE6 = Integer.MIN_VALUE;
            if (sensors.getCompassData(0).hasPitchE6()) {
                this.mOverridePitchE6 = sensors.getCompassData(0).getPitchE6();
            }
            if (sensors.getCompassData(0).hasRollE6()) {
                this.mOverrideRollE6 = sensors.getCompassData(0).getRollE6();
            }
        }
        if (sensors.getRpmDataCount() == 0 || !sensors.getRpmData(0).hasRpmE3()) {
            this.mOverrideRpmE3 = Integer.MIN_VALUE;
        } else {
            this.mOverrideRpmE3 = sensors.getRpmData(0).getRpmE3();
        }
        if (sensors.getOdometerDataCount() == 0 || !sensors.getOdometerData(0).hasKmsE1()) {
            this.mOverrideKmsE1 = Integer.MIN_VALUE;
        } else {
            this.mOverrideKmsE1 = sensors.getOdometerData(0).getKmsE1();
        }
        if (sensors.getFuelDataCount() == 0) {
            this.mOverrideFuelLevel = null;
        } else {
            this.mOverrideFuelLevel = sensors.getFuelData(0);
        }
        if (sensors.getParkingBrakeDataCount() == 0 || !sensors.getParkingBrakeData(0).hasParkingBrake()) {
            this.mOverrideParkingBrake = null;
        } else {
            this.mOverrideParkingBrake = Boolean.valueOf(sensors.getParkingBrakeData(0).getParkingBrake());
        }
        if (sensors.getGearDataCount() == 0 || !sensors.getGearData(0).hasGear()) {
            this.mOverrideGear = null;
        } else {
            this.mOverrideGear = sensors.getGearData(0).getGear();
        }
        if (sensors.getDiagnosticsDataCount() == 0 || !sensors.getDiagnosticsData(0).hasDtc()) {
            this.mOverrideDiagnostics = null;
        } else {
            this.mOverrideDiagnostics = sensors.getDiagnosticsData(0).getDtc();
        }
        if (sensors.getNightModeDataCount() == 0 || !sensors.getNightModeData(0).hasNightMode()) {
            this.mOverrideNightMode = null;
        } else {
            this.mOverrideNightMode = Boolean.valueOf(sensors.getNightModeData(0).getNightMode());
        }
        if (sensors.getEnvironmentDataCount() == 0) {
            this.mOverrideEnvironmentData = null;
        } else {
            this.mOverrideEnvironmentData = sensors.getEnvironmentData(0);
        }
        if (sensors.getHvacDataCount() == 0) {
            this.mOverrideHvacData = null;
        } else {
            this.mOverrideHvacData = sensors.getHvacData(0);
        }
        if (sensors.getDrivingStatusDataCount() == 0 || !sensors.getDrivingStatusData(0).hasStatus()) {
            this.mOverrideDrivingStatus = Integer.MIN_VALUE;
        } else {
            this.mOverrideDrivingStatus = sensors.getDrivingStatusData(0).getStatus();
        }
        if (sensors.getAccelerometerDataCount() == 0) {
            this.mOverrideAccelerometerData = null;
        } else {
            this.mOverrideAccelerometerData = sensors.getAccelerometerData(0);
        }
        if (sensors.getGyroscopeDataCount() == 0) {
            this.mOverrideGyroscopeData = null;
        } else {
            this.mOverrideGyroscopeData = sensors.getGyroscopeData(0);
        }
        if (sensors.getGpsSatelliteDataCount() == 0) {
            this.mOverrideGpsSatelliteData = null;
        } else {
            this.mOverrideGpsSatelliteData = sensors.getGpsSatelliteData(0);
        }
    }

    public void reportSensorError(int sensorType, int errorCode) {
        nativeReportSensorError(sensorType, errorCode);
    }
}
