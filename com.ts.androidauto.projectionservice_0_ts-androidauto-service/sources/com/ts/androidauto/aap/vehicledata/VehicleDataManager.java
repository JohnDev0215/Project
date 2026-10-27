package com.ts.androidauto.aap.vehicledata;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.os.SystemClock;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.VehicleConst;
import com.ts.androidauto.fsdk.data.VehicleAccelerometerData;
import com.ts.androidauto.fsdk.data.VehicleFuleData;
import com.ts.androidauto.fsdk.data.VehicleGear;
import com.ts.androidauto.fsdk.data.VehicleGyroscopeData;
import com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleDataManager implements IVehicleControllerListener {
    private static final int INVALID_VALUE = -1000;
    private static final String SIMULATIONLOCATIONPATH = "/sdcard/location.txt";
    public static final String SINK_PROVIDER = "Sink";
    private static final String TAG = "AAP" + VehicleDataManager.class.getSimpleName();
    private static VehicleDataManager mVehicleDataManager;
    private Context mContext;
    private GalIntegration mGal;
    private VehicleInfo mVehicleInfo;
    private IVehicleControllerAdapter mVehicleControllerAdapter = null;
    private ArrayList<Integer> mSupportedSensors = new ArrayList<>();
    private SimulationGuidanceThread mSimulationGuidanceThread = null;
    private SendVehicleThread mSendVehicleThread = null;
    private int mLastGearData = -1000;
    private boolean mSimulationNavi = false;

    public static synchronized VehicleDataManager getInstance() {
        if (mVehicleDataManager == null) {
            mVehicleDataManager = new VehicleDataManager();
        }
        return mVehicleDataManager;
    }

    public void initSensorManager(Context context, GalIntegration gal, IVehicleControllerAdapter vehicleControllerAdapter, boolean is500Bev) {
        LogUtil.info(TAG, "Init vehicle data manager.");
        this.mVehicleControllerAdapter = vehicleControllerAdapter;
        vehicleControllerAdapter.addVehicleControllerListener(this);
        this.mGal = gal;
        this.mContext = context;
        this.mLastGearData = -1000;
        this.mSimulationNavi = false;
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onVehicleNightModeChanged(boolean nightMode) {
        if (isAapConnected()) {
            reportNightModeData(nightMode);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onMicMuteHwKeyChanged(boolean status) {
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onVehicleInfoReceived(boolean info, boolean serialNum) {
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onVehicleGearChanged(VehicleGear gear) {
        if (isAapConnected()) {
            reportGearData(gear);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onVehicleParkingBreakChanged(boolean parking) {
        if (isAapConnected()) {
            reportParkingBrakeData(parking);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onDrivingStatusChanged(int status) {
        if (isAapConnected()) {
            reportDrivingStatusData(status);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onVehicleOdometerChanged(int meter) {
        if (isAapConnected()) {
            reportOdometerData(meter, false, 0.0d);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onVehicleFuleStatusChanged(VehicleFuleData fuleData) {
        if (isAapConnected()) {
            reportFuelData(fuleData);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onDoorStatusChanged(boolean[] doorStatus) {
        if (isAapConnected()) {
            reportDoorStatus(doorStatus);
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerListener
    public void onTyreStatusChanged(float[] tyreStatus) {
        if (isAapConnected()) {
            reportTyreStatus(tyreStatus);
        }
    }

    public void registerSensorListener() {
        LogUtil.info(TAG, "registerSensorListener is start!");
        IVehicleControllerAdapter iVehicleControllerAdapter = this.mVehicleControllerAdapter;
        if (iVehicleControllerAdapter != null) {
            iVehicleControllerAdapter.registerSensorListener();
        }
    }

    public void unregisterSensorListener() {
        LogUtil.info(TAG, "unregisterSensorListener is start!");
        IVehicleControllerAdapter iVehicleControllerAdapter = this.mVehicleControllerAdapter;
        if (iVehicleControllerAdapter != null) {
            iVehicleControllerAdapter.unregisterSensorListener();
        }
    }

    public void reportVehicleData() {
        if (this.mVehicleControllerAdapter != null) {
            LogUtil.verbose(TAG, "Report Vehicle Data for first time : NightMode:" + this.mVehicleControllerAdapter.isNightMode() + " Gear:" + this.mVehicleControllerAdapter.getGearPosition().ordinal() + " Parking:" + this.mVehicleControllerAdapter.isParkingBreak());
            reportNightModeData(this.mVehicleControllerAdapter.isNightMode());
            reportGearData(this.mVehicleControllerAdapter.getGearPosition());
        }
    }

    public void startSendDrivingStatus() {
        LogUtil.verbose(TAG, "startSendDrivingStatus is invoke!");
        IVehicleControllerAdapter iVehicleControllerAdapter = this.mVehicleControllerAdapter;
        if (iVehicleControllerAdapter != null) {
            int status = iVehicleControllerAdapter.getVehicleDrivingStatus();
            reportDrivingStatusData(status);
        }
    }

    public List<Integer> getSupportedSensors() {
        ArrayList<VehicleConst.SensorType> supportedSensorList;
        this.mSupportedSensors.clear();
        IVehicleControllerAdapter iVehicleControllerAdapter = this.mVehicleControllerAdapter;
        if (iVehicleControllerAdapter != null && (supportedSensorList = iVehicleControllerAdapter.getSupportedSensorList()) != null) {
            for (VehicleConst.SensorType sensorType : supportedSensorList) {
                int transSensorType = convertSensorType(sensorType);
                if (transSensorType != 0) {
                    this.mSupportedSensors.add(Integer.valueOf(transSensorType));
                }
            }
        }
        LogUtil.debug(TAG, "getSupportedSensors list size:" + this.mSupportedSensors.size());
        return this.mSupportedSensors;
    }

    private int convertSensorType(VehicleConst.SensorType sensorType) {
        switch (AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[sensorType.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 10;
            case 10:
                return 11;
            case 11:
                return 12;
            case 12:
                return 13;
            case 13:
                return 15;
            case 14:
                return 16;
            case 15:
                return 17;
            case 16:
                return 18;
            case 17:
                return 19;
            case 18:
                return 20;
            case 19:
                return 21;
            case 20:
                return 22;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAccelerometerData(float accelerationX, float accelerationY, float accelerationZ) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportAccelerometerData(true, accelerationX, true, accelerationY, true, accelerationZ);
        }
    }

    private void reportFuelData(VehicleFuleData fuleData) {
        GalIntegration galIntegration;
        LogUtil.info(TAG, "Report Fuel Data to the GAL  FuleStatus:" + fuleData.getFuleStatus() + " LowFuleStatus:" + fuleData.getLowFuleStatus() + " RemainingDrivingRange:" + fuleData.getRemainingDrivingRange() + " connect status:" + isAapConnected());
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportFuelData(true, fuleData.getFuleStatus(), true, fuleData.getRemainingDrivingRange(), true, fuleData.getLowFuleStatus());
        }
    }

    private void reportOdometerData(double kmsE1, boolean hasTripKms, double tripKms) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportOdometerData(kmsE1, hasTripKms, tripKms);
        }
    }

    private void reportParkingBrakeData(boolean engaged) {
        GalIntegration galIntegration;
        LogUtil.info(TAG, "Report Parking Brake Data to the GAL " + engaged + " connect status:" + isAapConnected());
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportParkingBrakeData(engaged);
        }
    }

    private void reportDrivingStatusData(int drivingStatus) {
        GalIntegration galIntegration;
        LogUtil.info(TAG, "Report Driving Status Data to the GAL " + drivingStatus + " connect status:" + isAapConnected());
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportDrivingStatusData(drivingStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportLocation(Location location) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportLocation(location);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportSpeedData(float speed) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            VehicleGear gear = this.mVehicleControllerAdapter.getGearPosition();
            if (gear == VehicleGear.VEHICLE_GEAR_R) {
                this.mGal.sensors.reportSpeedData(speed * (-1.0f), false, false, false, 0);
            } else {
                this.mGal.sensors.reportSpeedData(speed, false, false, false, 0);
            }
        }
    }

    private void reportEngineSpeedData(double speed) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportRpmData(speed);
        }
    }

    private void reportTemperatureData(float temper) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportEnvironmentData(true, temper, false, 0.0f, false, 0);
        }
    }

    private void reportDoorStatus(boolean[] doorStatus) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportDoorData(false, false, false, false, doorStatus);
        }
    }

    private void reportTyreStatus(float[] tyreStatus) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportTirePressureData(tyreStatus);
        }
    }

    private void reportGearData(VehicleGear gear) {
        GalIntegration galIntegration;
        LogUtil.info(TAG, "Report gear to the GAL. Gear value :" + gear);
        int aapGear = 101;
        switch (AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[gear.ordinal()]) {
            case 1:
            case 2:
                aapGear = 101;
                break;
            case 3:
                aapGear = 102;
                break;
            case 4:
                aapGear = 0;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                aapGear = 100;
                break;
            default:
                LogUtil.warning(TAG, "Skip gears with no meaning for aap: " + gear);
                break;
        }
        if (this.mLastGearData == aapGear) {
            LogUtil.warning(TAG, "Don't report the same gear to MD " + gear);
            return;
        }
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportGearData(aapGear);
            this.mLastGearData = aapGear;
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.vehicledata.VehicleDataManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType;
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear;

        static {
            int[] iArr = new int[VehicleGear.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear = iArr;
            try {
                iArr[VehicleGear.VEHICLE_GEAR_AVAIABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_P.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_R.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_N.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_D.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_L.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_TIP_AUTOSTICK.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_PLUS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_MINUS.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$data$VehicleGear[VehicleGear.VEHICLE_GEAR_SPORT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            int[] iArr2 = new int[VehicleConst.SensorType.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType = iArr2;
            try {
                iArr2[VehicleConst.SensorType.SENSOR_TYPE_LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_COMPASS.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_SPEED.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_RPM.ordinal()] = 4;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_ODOMETER.ordinal()] = 5;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_FUEL.ordinal()] = 6;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_PARKING_BRAKE.ordinal()] = 7;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_GEAR.ordinal()] = 8;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_NIGHT_MODE.ordinal()] = 9;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_ENVIRONMENT.ordinal()] = 10;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_HVAC.ordinal()] = 11;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_DRIVING_STATUS.ordinal()] = 12;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_PASSENGER_DATA.ordinal()] = 13;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_DOOR_DATA.ordinal()] = 14;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_LIGHT_DATA.ordinal()] = 15;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_TIRE_PRESSURE_DATA.ordinal()] = 16;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_ACCELEROMETER_DATA.ordinal()] = 17;
            } catch (NoSuchFieldError e27) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_GYROSCOPE_DATA.ordinal()] = 18;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_GPS_SATELLITE_DATA.ordinal()] = 19;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_TOLL_CARD.ordinal()] = 20;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_OBDII_DIAGNOSTIC_CODE.ordinal()] = 21;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$VehicleConst$SensorType[VehicleConst.SensorType.SENSOR_TYPE_DEAD_RECKONING_DATA.ordinal()] = 22;
            } catch (NoSuchFieldError e32) {
            }
        }
    }

    private void reportNightModeData(boolean nightMode) {
        GalIntegration galIntegration;
        LogUtil.info(TAG, "Report night mode  to the GAL. Night mode :" + nightMode + " connect Status:" + isAapConnected());
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportNightModeData(nightMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportGyroscopeData(float rotationSpeedX, float rotationSpeedY, float rotationSpeedZ) {
        GalIntegration galIntegration;
        if (isAapConnected() && (galIntegration = this.mGal) != null && galIntegration.sensors != null) {
            this.mGal.sensors.reportGyroscopeData(true, rotationSpeedX, true, rotationSpeedY, true, rotationSpeedZ);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void reportGpsSatelliteData(GpsStatus gpsStatus) {
        if (gpsStatus == null) {
            if (isAapConnected()) {
                boolean z = this.mSimulationNavi;
                GalIntegration galIntegration = this.mGal;
                if (galIntegration != null && galIntegration.sensors != null) {
                    this.mGal.sensors.reportGpsSatelliteData(z ? 1 : 0, false, 0, null, null, null, null, null);
                    return;
                }
                return;
            }
            return;
        }
        int i = 0;
        int i2 = 0;
        Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
        while (it.hasNext()) {
            i++;
            if (it.next().usedInFix()) {
                i2++;
            }
        }
        int[] iArr = new int[i];
        float[] fArr = new float[i];
        boolean[] zArr = new boolean[i];
        float[] fArr2 = new float[i];
        float[] fArr3 = new float[i];
        int i3 = 0;
        for (GpsSatellite gpsSatellite : gpsStatus.getSatellites()) {
            iArr[i3] = gpsSatellite.getPrn();
            fArr[i3] = gpsSatellite.getSnr();
            zArr[i3] = gpsSatellite.usedInFix();
            fArr2[i3] = gpsSatellite.getAzimuth();
            fArr3[i3] = gpsSatellite.getElevation();
            i3++;
        }
        if (isAapConnected()) {
            GalIntegration galIntegration2 = this.mGal;
            if (galIntegration2 != null && galIntegration2.sensors != null) {
                this.mGal.sensors.reportGpsSatelliteData(i2, true, i, iArr, fArr, zArr, fArr2, fArr3);
            }
        }
    }

    public void startSendVehicleThread() {
        LogUtil.verbose(TAG, "startSendVehicleThread is invoke!");
        if (this.mSendVehicleThread != null) {
            stopSendVehicleThread();
        }
        SendVehicleThread sendVehicleThread = new SendVehicleThread();
        this.mSendVehicleThread = sendVehicleThread;
        sendVehicleThread.start();
    }

    public void stopSendVehicleThread() {
        LogUtil.verbose(TAG, "stopSendVehicleThread is invoke!");
        SendVehicleThread sendVehicleThread = this.mSendVehicleThread;
        if (sendVehicleThread != null) {
            try {
                sendVehicleThread.finishFocusThread();
                this.mSendVehicleThread.join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            this.mSendVehicleThread = null;
        }
    }

    public class SendVehicleThread extends Thread {
        private boolean sendVehicleFlag = true;
        private int count = 0;

        public SendVehicleThread() {
        }

        public void finishFocusThread() {
            this.sendVehicleFlag = false;
            this.count = 0;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.verbose(VehicleDataManager.TAG, "SendVehicleThread run");
            while (this.sendVehicleFlag && !Thread.currentThread().isInterrupted()) {
                if (VehicleDataManager.this.mVehicleControllerAdapter != null) {
                    VehicleGyroscopeData vehicleGyroscopeData = VehicleDataManager.this.mVehicleControllerAdapter.getVehicleGyroscopeData();
                    VehicleDataManager.this.reportGyroscopeData(vehicleGyroscopeData.getRotationSpeedX(), vehicleGyroscopeData.getRotationSpeedY(), vehicleGyroscopeData.getRotationSpeedZ());
                    VehicleDataManager vehicleDataManager = VehicleDataManager.this;
                    vehicleDataManager.reportSpeedData(vehicleDataManager.mVehicleControllerAdapter.getSpeed());
                    VehicleAccelerometerData vehicleAccelerometerData = VehicleDataManager.this.mVehicleControllerAdapter.getVehicleAccelerometerData();
                    VehicleDataManager.this.reportAccelerometerData(vehicleAccelerometerData.getAccelerationX(), vehicleAccelerometerData.getAccelerationY(), vehicleAccelerometerData.getAccelerationZ());
                    int i = this.count + 1;
                    this.count = i;
                    if (i >= 10) {
                        this.count = 0;
                        if (!VehicleDataManager.this.mSimulationNavi) {
                            VehicleDataManager vehicleDataManager2 = VehicleDataManager.this;
                            vehicleDataManager2.reportGpsSatelliteData(vehicleDataManager2.mVehicleControllerAdapter.getVehicleGpsStatus());
                            VehicleDataManager vehicleDataManager3 = VehicleDataManager.this;
                            vehicleDataManager3.reportLocation(vehicleDataManager3.mVehicleControllerAdapter.getLastLocation());
                        }
                    }
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException exception) {
                    LogUtil.verbose(VehicleDataManager.TAG, "SendVehicleThread InterruptedException:" + exception.toString());
                }
            }
        }
    }

    public void releaseSensor() {
        IVehicleControllerAdapter iVehicleControllerAdapter = this.mVehicleControllerAdapter;
        if (iVehicleControllerAdapter != null) {
            iVehicleControllerAdapter.removeVehicleControllerListener(this);
        }
        stopSendVehicleThread();
        stopSimulationGuidance();
        unregisterSensorListener();
        ArrayList<Integer> arrayList = this.mSupportedSensors;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.mLastGearData = -1000;
        this.mSimulationNavi = false;
    }

    private boolean isAapConnected() {
        return AapController.getInstance().isAapConnected();
    }

    public synchronized void setVehicleInfo(VehicleInfo vehicleInfo) {
        LogUtil.verbose(TAG, "setVehicleInfo info=" + vehicleInfo);
        if (vehicleInfo != null) {
            this.mVehicleInfo = vehicleInfo;
        }
    }

    public synchronized VehicleInfo getVehicleInfo() {
        if (this.mVehicleInfo == null) {
            this.mVehicleInfo = new VehicleInfo();
        }
        return this.mVehicleInfo;
    }

    public void startSimulationGuidance() {
        this.mSimulationNavi = true;
        LogUtil.verbose(TAG, "Test: startSimulationGuidance is invoke!" + this.mSimulationNavi);
        if (this.mSimulationGuidanceThread != null) {
            stopSimulationGuidance();
        }
        SimulationGuidanceThread simulationGuidanceThread = new SimulationGuidanceThread();
        this.mSimulationGuidanceThread = simulationGuidanceThread;
        simulationGuidanceThread.start();
    }

    public void stopSimulationGuidance() {
        this.mSimulationNavi = false;
        LogUtil.verbose(TAG, "Test: stopSimulationGuidance is invoke!" + this.mSimulationNavi);
        SimulationGuidanceThread simulationGuidanceThread = this.mSimulationGuidanceThread;
        if (simulationGuidanceThread != null) {
            simulationGuidanceThread.finish();
            SimulationGuidanceThread.interrupted();
            this.mSimulationGuidanceThread = null;
        }
    }

    public class SimulationGuidanceThread extends Thread {
        private boolean simulationFlag = true;

        public SimulationGuidanceThread() {
        }

        public void finish() {
            this.simulationFlag = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.verbose(VehicleDataManager.TAG, "SimulationGuidanceThread run");
            while (this.simulationFlag && !Thread.currentThread().isInterrupted()) {
                LogUtil.info(VehicleDataManager.TAG, "Test: readSimulationData");
                VehicleDataManager.this.readSimulationData(VehicleDataManager.SIMULATIONLOCATIONPATH);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException exception) {
                    LogUtil.verbose(VehicleDataManager.TAG, "SimulationGuidanceThread InterruptedException:" + exception.toString());
                }
            }
        }
    }

    public String readSimulationData(String strFilePath) {
        File file = new File(strFilePath);
        if (file.isDirectory()) {
            LogUtil.info(TAG, "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                try {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    try {
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        while (true) {
                            try {
                                String line = buffreader.readLine();
                                if (line == null || !this.mSimulationNavi) {
                                    break;
                                }
                                String[] data = line.split(",");
                                Location location = new Location(SINK_PROVIDER);
                                BigDecimal minLatitude = new BigDecimal(data[2]);
                                double minlat = minLatitude.doubleValue();
                                BigDecimal maxLongitude = new BigDecimal(data[1]);
                                double minlon = maxLongitude.doubleValue();
                                location.setLatitude(minlat);
                                location.setLongitude(minlon);
                                reportGpsSatelliteData(null);
                                reportLocation(location);
                                SystemClock.sleep(1000L);
                            } finally {
                            }
                        }
                        LogUtil.info(TAG, "Test: instream.close()");
                        buffreader.close();
                        inputreader.close();
                        instream.close();
                    } finally {
                    }
                } finally {
                }
            } catch (FileNotFoundException e) {
                LogUtil.info(TAG, "The File doesn't not exist.");
            } catch (IOException exception) {
                LogUtil.info(TAG, exception.getMessage());
            }
        }
        return strFilePath;
    }

    public boolean checkSimulationFileExists() {
        File file = new File(SIMULATIONLOCATIONPATH);
        if (file.exists()) {
            return true;
        }
        return false;
    }
}
