package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.ts.appservice.caradapter.d.A;
import com.ts.appservice.caradapter.d.u;
import com.ts.appservice.caradapter.d.y;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ambient.CarVendorAmbientManager;
import com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager;
import com.ts.lib.car.hardware.safetybelt.SafetybeltEventChangeValue;
import com.ts.lib.car.hardware.statics.CarVendorStaticManager;
import com.ts.lib.car.hardware.statics.StaticEventChangeValue;
import com.ts.lib.car.hardware.whud.CarVendorWhudManager;
import com.ts.lib.car.hardware.whud.WhudEventChangeValue;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.ICarGeneralCallback;
import com.ts.lib.caradapter.general.ICarGeneralService;
import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: CarGeneralServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends ICarGeneralService.Stub implements A, u, y {
    private static final String f1 = i.class.getSimpleName();
    private int A;
    private float A0;
    private int B;
    private int B0;
    private int C;
    private int C0;
    private int D;
    private int D0;
    private int E;
    private float E0;
    private int F;
    private float F0;
    private int G;
    private float G0;
    private int H;
    private float H0;
    private int I;
    private float I0;
    private int J;
    private int J0;
    private int K;
    private int K0;
    private int L;
    private float L0;
    private int M;
    private int M0;
    private int N;
    private float N0;
    private int O;
    private float O0;
    private int P;
    private int P0;
    private int Q;
    private int Q0;
    private int R;
    private int R0;
    private int S;
    private int S0;
    private int T;
    private int T0;
    private int U;
    private int U0;
    private String V;
    private int V0;
    private int W;
    private int W0;
    private int X;
    private int X0;
    private int Y;
    private int Y0;
    private int Z;
    private int Z0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CarVendorSafetybeltManager f71a;
    private int a0;
    private int a1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CarVendorStaticManager f72b;
    private int b0;
    private int b1;
    private CarVendorWhudManager c;
    private int c0;
    private int c1;
    private CarVendorAmbientManager d;
    private int d0;
    private float d1;
    private int e0;
    private float e1;
    private String f;
    private int f0;
    private String g;
    private int g0;
    private int h;
    private int h0;
    private int i;
    private int i0;
    private int j;
    private int j0;
    private int k;
    private String k0;
    private boolean l;
    private int l0;
    private int m;
    private int m0;
    private com.ts.appservice.caradapter.a.e n;
    private int n0;
    private int o0;
    private a p;
    private int p0;
    private int q0;
    private Context r;
    private int r0;
    private int s;
    private int s0;
    private int t;
    private int t0;
    private int u;
    private int u0;
    private int v;
    private int v0;
    private int w;
    private int w0;
    private int x;
    private int x0;
    private float y0;
    private int z;
    private int z0;
    private GeneralPacket e = new GeneralPacket();
    private boolean q = false;
    private boolean y = false;
    private final HandlerThread o = com.ts.appservice.caradapter.e.b.a("VEHICLE-GENERAL");

    /* JADX INFO: compiled from: CarGeneralServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                i.this.p(message.arg1, message.arg2, (SafetybeltEventChangeValue) message.obj);
                return;
            }
            if (i == 2) {
                i.this.q(message.arg1, message.arg2, (StaticEventChangeValue) message.obj);
                return;
            }
            if (i == 3) {
                i.this.r(message.arg1, message.arg2, (WhudEventChangeValue) message.obj);
                return;
            }
            LogUtil.error(i.f1, "Unexpected message: " + message.what);
        }
    }

    public i(Context context) {
        this.r = context;
        this.n = new com.ts.appservice.caradapter.a.e(context);
    }

    public static long l(byte[] bArr) {
        String str = f1;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int length2 = bArr.length - 1;
        int i = 0;
        while (length2 >= 0) {
            bArr2[length2] = bArr[i];
            length2--;
            i++;
        }
        LogUtil.optionError(str, " bytesToLong.in" + Arrays.toString(bArr));
        LogUtil.optionError(str, " bytesToLong.reverser" + Arrays.toString(bArr2));
        long j = 0;
        for (int i2 = 0; i2 < length; i2++) {
            j = (j << 8) | ((long) (bArr2[i2] & 255));
        }
        System.out.println("result:" + j);
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(int i, int i2, SafetybeltEventChangeValue safetybeltEventChangeValue) {
        String str = f1;
        try {
            switch (i) {
                case 557860864:
                    int safetyBeltStatusMain = safetybeltEventChangeValue.getSafetyBeltStatusMain();
                    this.s0 = safetyBeltStatusMain;
                    this.e.setSafetyBeltStatusMain(safetyBeltStatusMain);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_MAIN:" + this.s0);
                    break;
                case 557860865:
                    int safetyBeltStatusDeputy = safetybeltEventChangeValue.getSafetyBeltStatusDeputy();
                    this.p0 = safetyBeltStatusDeputy;
                    this.e.setSafetyBeltStatusDeputy(safetyBeltStatusDeputy);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_DEPUTY:" + this.p0);
                    break;
                case 557860866:
                    int safetyBeltStatusLeftTwoRear = safetybeltEventChangeValue.getSafetyBeltStatusLeftTwoRear();
                    this.r0 = safetyBeltStatusLeftTwoRear;
                    this.e.setSafetyBeltStatusLeftTwoRear(safetyBeltStatusLeftTwoRear);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_LEFT_TWO_REAR:" + this.r0);
                    break;
                case 557860867:
                    int safetyBeltStatusMidTwoRear = safetybeltEventChangeValue.getSafetyBeltStatusMidTwoRear();
                    this.u0 = safetyBeltStatusMidTwoRear;
                    this.e.setSafetyBeltStatusMidTwoRear(safetyBeltStatusMidTwoRear);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_MID_TWO_REAR:" + this.u0);
                    break;
                case 557860868:
                    int safetyBeltStatusRightTwoRear = safetybeltEventChangeValue.getSafetyBeltStatusRightTwoRear();
                    this.w0 = safetyBeltStatusRightTwoRear;
                    this.e.setSafetyBeltStatusRightTwoRear(safetyBeltStatusRightTwoRear);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_RIGHT_TWO_REAR:" + this.w0);
                    break;
                case 557860869:
                    int safetyBeltStatusLeftThreeRear = safetybeltEventChangeValue.getSafetyBeltStatusLeftThreeRear();
                    this.q0 = safetyBeltStatusLeftThreeRear;
                    this.e.setSafetyBeltStatusLeftThreeRear(safetyBeltStatusLeftThreeRear);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_LEFT_THREE_REAR:" + this.q0);
                    break;
                case 557860870:
                    int safetyBeltStatusMidThreeRear = safetybeltEventChangeValue.getSafetyBeltStatusMidThreeRear();
                    this.t0 = safetyBeltStatusMidThreeRear;
                    this.e.setSafetyBeltStatusMidThreeRear(safetyBeltStatusMidThreeRear);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_MID_THREE_REAR:" + this.t0);
                    break;
                case 557860871:
                    int safetyBeltStatusRightThreeRear = safetybeltEventChangeValue.getSafetyBeltStatusRightThreeRear();
                    this.v0 = safetyBeltStatusRightThreeRear;
                    this.e.setSafetyBeltStatusRightThreeRear(safetyBeltStatusRightThreeRear);
                    LogUtil.verbose(str, "ID_SAFETY_BELT_STATUS_RIGHT_THREE_REAR:" + this.v0);
                    break;
                case 557860875:
                    int driverBeltStatusR = safetybeltEventChangeValue.getDriverBeltStatusR();
                    this.x0 = driverBeltStatusR;
                    this.e.setDriverBeltStatusR(driverBeltStatusR);
                    LogUtil.verbose(str, "ID_DRIVER_BELT_STATUS_R:" + this.x0);
                    break;
                case 557860876:
                    int mainSeatStateR = safetybeltEventChangeValue.getMainSeatStateR();
                    this.e.setMainSeatStateR(mainSeatStateR);
                    LogUtil.debug(str, "ID_+MAIN_SEAT_STATE_R:" + mainSeatStateR);
                    break;
            }
            this.n.l(i, i2, this.e);
            LogUtil.verbose(str, "notifySafetybeltEventChangeValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i, int i2, StaticEventChangeValue staticEventChangeValue) {
        String str = f1;
        try {
            switch (i) {
                case 557859841:
                    int elecDrivingRangeValueR = staticEventChangeValue.getElecDrivingRangeValueR();
                    this.z0 = elecDrivingRangeValueR;
                    this.e.setElecDrivingRangeValueR(elecDrivingRangeValueR);
                    LogUtil.verbose(str, "ID_ELEC_DRIVING_RANGE_VALUER:" + this.z0);
                    break;
                case 557859843:
                    int fuelDrivingRangeValueR = staticEventChangeValue.getFuelDrivingRangeValueR();
                    this.B0 = fuelDrivingRangeValueR;
                    this.e.setFuelDrivingRangeValueR(fuelDrivingRangeValueR);
                    LogUtil.verbose(str, "ID_FUEL_DRIVING_RANGE_VALUER:" + this.B0);
                    break;
                case 557859844:
                    int fuelPercentageValueR = staticEventChangeValue.getFuelPercentageValueR();
                    this.C0 = fuelPercentageValueR;
                    this.e.setFuelPercentageValueR(fuelPercentageValueR);
                    LogUtil.verbose(str, "ID_FUEL_PERCENTAGE_VALUER:" + this.C0);
                    break;
                case 557859850:
                    int keyBatteryLevelR = staticEventChangeValue.getKeyBatteryLevelR();
                    this.D0 = keyBatteryLevelR;
                    this.e.setKeyBatteryLevelR(keyBatteryLevelR);
                    LogUtil.verbose(str, "ID_KEY_BATTERY_LEVELR:" + this.D0);
                    break;
                case 557859855:
                    int averageSpeedValueS = staticEventChangeValue.getAverageSpeedValueS();
                    this.K0 = averageSpeedValueS;
                    this.e.setAverageSpeedValueS(averageSpeedValueS);
                    LogUtil.verbose(str, "ID_AVERAGE_SPEED_VALUES:" + this.K0);
                    break;
                case 557859857:
                    int eVMileageValueS = staticEventChangeValue.getEVMileageValueS();
                    this.J0 = eVMileageValueS;
                    this.e.setEVMileageValueS(eVMileageValueS);
                    LogUtil.verbose(str, "ID_E_V_MILEAGE_VALUES:" + this.J0);
                    break;
                case 557859877:
                    int drivingRangeAllR = staticEventChangeValue.getDrivingRangeAllR();
                    this.b1 = drivingRangeAllR;
                    this.e.setDrivingRangeAllR(drivingRangeAllR);
                    LogUtil.verbose(str, "ID_DRIVING_RANGE_ALL_R:" + this.b1);
                    break;
                case 557859878:
                    int drivingRangeUnitR = staticEventChangeValue.getDrivingRangeUnitR();
                    this.c1 = drivingRangeUnitR;
                    this.e.setDrivingRangeUnitR(drivingRangeUnitR);
                    LogUtil.verbose(str, "ID_DRIVING_RANGE_UNIT_R:" + this.c1);
                    break;
                case 557869856:
                    int currentTripElecUnitR = staticEventChangeValue.getCurrentTripElecUnitR();
                    this.e.setCurrentTripElecUnitR(currentTripElecUnitR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_ELEC_UNIT_R:" + currentTripElecUnitR);
                    break;
                case 557869857:
                    int currentTripTimeR = staticEventChangeValue.getCurrentTripTimeR();
                    this.e.setCurrentTripTimeR(currentTripTimeR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_TIME_R:" + currentTripTimeR);
                    break;
                case 557875215:
                    int elecDrivingRangeValueTwoR = staticEventChangeValue.getElecDrivingRangeValueTwoR();
                    this.M0 = elecDrivingRangeValueTwoR;
                    this.e.setElecDrivingRangeValueTwoR(elecDrivingRangeValueTwoR);
                    LogUtil.verbose(str, "ELEC_DRIVING_RANGE_VALUE_TWOR:" + this.M0);
                    break;
                case 557875217:
                    int currentTripFuelUnitR = staticEventChangeValue.getCurrentTripFuelUnitR();
                    this.e.setCurrentTripFuelUnitR(currentTripFuelUnitR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_FUEL_UNIT_R:" + currentTripFuelUnitR);
                    break;
                case 557875219:
                    int speedUnitR = staticEventChangeValue.getSpeedUnitR();
                    this.e.setSpeedUnitR(speedUnitR);
                    LogUtil.debug(str, "ID_SPEED_UNIT_R:" + speedUnitR);
                    break;
                case 557875222:
                    int elecConsUnitR = staticEventChangeValue.getElecConsUnitR();
                    this.e.setElecConsUnitR(elecConsUnitR);
                    LogUtil.debug(str, "ID_ELEC_CONS_UNIT_R:" + elecConsUnitR);
                    break;
                case 557875258:
                    int elecUnitR = staticEventChangeValue.getElecUnitR();
                    this.e.setElecUnitR(elecUnitR);
                    LogUtil.debug(str, "ID_ELEC_UNIT_R:" + elecUnitR);
                    break;
                case 557875259:
                    int currentTripMileageUnitR = staticEventChangeValue.getCurrentTripMileageUnitR();
                    this.e.setCurrentTripMileageUnitR(currentTripMileageUnitR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_MILEAGE_UNIT_R:" + currentTripMileageUnitR);
                    break;
                case 559956992:
                    float drivingTimeValueR = staticEventChangeValue.getDrivingTimeValueR();
                    this.y0 = drivingTimeValueR;
                    this.e.setDrivingTimeValueR(drivingTimeValueR);
                    LogUtil.verbose(str, "ID_DRIVING_TIME_VALUER:" + this.y0);
                    break;
                case 559956994:
                    float elecPercentageValueR = staticEventChangeValue.getElecPercentageValueR();
                    this.A0 = elecPercentageValueR;
                    this.e.setElecPercentageValueR(elecPercentageValueR);
                    LogUtil.verbose(str, "ID_ELEC_PERCENTAGE_VALUER:" + this.A0);
                    break;
                case 559956997:
                    float totalElecConPHMValueR = staticEventChangeValue.getTotalElecConPHMValueR();
                    this.E0 = totalElecConPHMValueR;
                    this.e.setTotalElecConPHMValueR(totalElecConPHMValueR);
                    LogUtil.verbose(str, "ID_TOTAL_ELEC_CON_P_H_M_VALUER:" + this.E0);
                    break;
                case 559956998:
                    float totalFuelConPHMValueR = staticEventChangeValue.getTotalFuelConPHMValueR();
                    this.G0 = totalFuelConPHMValueR;
                    this.e.setTotalFuelConPHMValueR(totalFuelConPHMValueR);
                    LogUtil.verbose(str, "ID_TOTAL_FUEL_CON_P_H_M_VALUER:" + this.G0);
                    break;
                case 559956999:
                    float totalFuelConValueR = staticEventChangeValue.getTotalFuelConValueR();
                    this.H0 = totalFuelConValueR;
                    this.e.setTotalFuelConValueR(totalFuelConValueR);
                    LogUtil.verbose(str, "ID_TOTAL_FUEL_CON_VALUER:" + this.H0);
                    break;
                case 559957000:
                    float totalElecConValueR = staticEventChangeValue.getTotalElecConValueR();
                    this.F0 = totalElecConValueR;
                    this.e.setTotalElecConValueR(totalElecConValueR);
                    LogUtil.verbose(str, "ID_TOTAL_ELEC_CON_VALUER:" + this.F0);
                    break;
                case 559957001:
                    float totalMileageValueR = staticEventChangeValue.getTotalMileageValueR();
                    this.I0 = totalMileageValueR;
                    this.e.setTotalMileageValueR(totalMileageValueR);
                    LogUtil.verbose(str, "ID_TOTAL_MILEAGE_VALUER:" + this.I0);
                    break;
                case 559957010:
                    float drivingTimeValueTwoR = staticEventChangeValue.getDrivingTimeValueTwoR();
                    this.L0 = drivingTimeValueTwoR;
                    this.e.setDrivingTimeValueTwoR(drivingTimeValueTwoR);
                    LogUtil.verbose(str, "ID_DRIVING_TIME_VALUE_TWOR:" + this.L0);
                    break;
                case 559957024:
                    float remainingBatteryPowerR = staticEventChangeValue.getRemainingBatteryPowerR();
                    this.N0 = remainingBatteryPowerR;
                    this.e.setRemainingBatteryPowerR(remainingBatteryPowerR);
                    LogUtil.verbose(str, "ID_REMAINING_BATTERY_POWER_R:" + this.N0);
                    break;
                case 559957025:
                    float evRemainingBatteryPowerR = staticEventChangeValue.getEvRemainingBatteryPowerR();
                    this.O0 = evRemainingBatteryPowerR;
                    this.e.setEvRemainingBatteryPowerR(evRemainingBatteryPowerR);
                    LogUtil.verbose(str, "ID_EV_REMAINING_BATTERY_POWER_R:" + this.O0);
                    break;
                case 559965186:
                    float mileageNumberOneR = staticEventChangeValue.getMileageNumberOneR();
                    this.d1 = mileageNumberOneR;
                    this.e.setMileageNumberOneR(mileageNumberOneR);
                    LogUtil.verbose(str, "ID_MILEAGE_NUMBER_ONER:" + this.d1);
                    break;
                case 559965187:
                    float mileageNumberTwoR = staticEventChangeValue.getMileageNumberTwoR();
                    this.e1 = mileageNumberTwoR;
                    this.e.setMileageNumberTwoR(mileageNumberTwoR);
                    LogUtil.verbose(str, "ID_MILEAGE_NUMBER_TWOR:" + this.e1);
                    break;
                case 559967007:
                    float currentTripElecR = staticEventChangeValue.getCurrentTripElecR();
                    this.e.setCurrentTripElecR(currentTripElecR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_ELEC_R:" + currentTripElecR);
                    break;
                case 559967010:
                    float currentTripFuelR = staticEventChangeValue.getCurrentTripFuelR();
                    this.e.setCurrentTripFuelR(currentTripFuelR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_FUEL_R:" + currentTripFuelR);
                    break;
                case 559972404:
                    float currentTripMileageR = staticEventChangeValue.getCurrentTripMileageR();
                    this.e.setCurrentTripMileageR(currentTripMileageR);
                    LogUtil.debug(str, "ID_CURRENT_TRIP_MILEAGE_R:" + currentTripMileageR);
                    break;
            }
            this.n.l(i, i2, this.e);
            LogUtil.verbose(str, "notifyStatisticEventChangeValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i, int i2, WhudEventChangeValue whudEventChangeValue) {
        String str = f1;
        try {
            if (i != 557875227) {
                switch (i) {
                    case 557872384:
                        int showAdasWorkR = whudEventChangeValue.getShowAdasWorkR();
                        this.P0 = showAdasWorkR;
                        this.e.setShowAdasWorkR(showAdasWorkR);
                        LogUtil.optionError(str, "ID_SHOW_ADAS_WORKR:" + this.P0);
                        break;
                    case 557872385:
                        int vehicleSpaceR = whudEventChangeValue.getVehicleSpaceR();
                        this.Q0 = vehicleSpaceR;
                        this.e.setVehicleSpaceR(vehicleSpaceR);
                        LogUtil.optionError(str, "ID_VEHICLE_SPACER:" + this.Q0);
                        break;
                    case 557872386:
                        int aheadVehicleSpaceR = whudEventChangeValue.getAheadVehicleSpaceR();
                        this.R0 = aheadVehicleSpaceR;
                        this.e.setAheadVehicleSpaceR(aheadVehicleSpaceR);
                        LogUtil.optionError(str, "ID_AHEAD_VEHICLE_SPACER:" + this.R0);
                        break;
                    case 557872387:
                        int textHintR = whudEventChangeValue.getTextHintR();
                        this.S0 = textHintR;
                        this.e.setTextHintR(textHintR);
                        LogUtil.optionError(str, "ID_TEXT_HINTR:" + this.S0);
                        break;
                    case 557872388:
                        int vehicleBodyPositionR = whudEventChangeValue.getVehicleBodyPositionR();
                        this.T0 = vehicleBodyPositionR;
                        this.e.setVehicleBodyPositionR(vehicleBodyPositionR);
                        LogUtil.optionError(str, "ID_VEHICLE_BODY_POSITIONR:" + this.T0);
                        break;
                    case 557872389:
                        int leftLineR = whudEventChangeValue.getLeftLineR();
                        this.U0 = leftLineR;
                        this.e.setLeftLineR(leftLineR);
                        LogUtil.optionError(str, "ID_LEFT_LINER:" + this.U0);
                        break;
                    case 557872390:
                        int rightLineR = whudEventChangeValue.getRightLineR();
                        this.V0 = rightLineR;
                        this.e.setRightLineR(rightLineR);
                        LogUtil.optionError(str, "ID_RIGHT_LINER:" + this.V0);
                        break;
                    case 557872391:
                        int accCruisingSpeedValueR = whudEventChangeValue.getAccCruisingSpeedValueR();
                        this.W0 = accCruisingSpeedValueR;
                        this.e.setAccCruisingSpeedValueR(accCruisingSpeedValueR);
                        LogUtil.optionError(str, "ID_ACC_CRUISING_SPEED_VALUER:" + this.W0);
                        break;
                    case 557872392:
                        int trafficSignRecognitionValueR = whudEventChangeValue.getTrafficSignRecognitionValueR();
                        this.X0 = trafficSignRecognitionValueR;
                        this.e.setTrafficSignRecognitionValueR(trafficSignRecognitionValueR);
                        LogUtil.optionError(str, "ID_TRAFFIC_SIGN_RECOGNITION_VALUER:" + this.X0);
                        break;
                    case 557872393:
                        int accCruisingLampR = whudEventChangeValue.getAccCruisingLampR();
                        this.Y0 = accCruisingLampR;
                        this.e.setAccCruisingLampR(accCruisingLampR);
                        LogUtil.optionError(str, "ID_ACC_CRUISING_LAMPR:" + this.Y0);
                        break;
                    case 557872394:
                        int accCruisingLampColorR = whudEventChangeValue.getAccCruisingLampColorR();
                        this.Z0 = accCruisingLampColorR;
                        this.e.setAccCruisingLampColorR(accCruisingLampColorR);
                        LogUtil.optionError(str, "ID_ACC_CRUISING_LAMP_COLORR:" + this.Z0);
                        break;
                }
            } else {
                int textMessageHintR = whudEventChangeValue.getTextMessageHintR();
                this.a1 = textMessageHintR;
                this.e.setTextMessageHintR(textMessageHintR);
                LogUtil.optionError(str, "ID_TEXT_MESSAGE_HINTR:" + this.a1);
            }
            this.n.l(i, i2, this.e);
            LogUtil.verbose(str, "notifyWhudEventChangeValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void s() {
        LogUtil.info(f1, "quit");
        if (this.o != null) {
            a aVar = this.p;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.o.quitSafely();
        }
    }

    private void u(boolean z) {
    }

    public static byte[] v(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[str.length() * 2];
        sb.setLength(0);
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            sb.append("\\u");
            int i2 = cCharAt & 255;
            String hexString = Integer.toHexString(i2);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
            int i3 = i * 2;
            bArr[i3] = (byte) i2;
            int i4 = cCharAt >>> '\b';
            String hexString2 = Integer.toHexString(i4);
            if (hexString2.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString2);
            bArr[i3 + 1] = (byte) (i4 & 255);
        }
        new String(sb);
        return bArr;
    }

    private void w(boolean z) {
        LogUtil.verbose(f1, "timeUpdated:" + z);
        Intent intent = new Intent("android.intent.action.TIME_SET");
        intent.putExtra("android.intent.extra.TIME_PREF_24_HOUR_FORMAT", z ? 1 : 0);
        this.r.sendBroadcast(intent);
    }

    @Override // com.ts.appservice.caradapter.d.A
    public void a(WhudEventChangeValue whudEventChangeValue) {
        String str = f1;
        if (whudEventChangeValue == null) {
            LogUtil.error(str, "onWhudEventChange:value=null");
            return;
        }
        int propertyId = whudEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onWhudEventChange-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 3;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = whudEventChangeValue.getAreaId();
        messageObtain.obj = whudEventChangeValue;
        this.p.sendMessage(messageObtain);
    }

    public int getAccCruisingLamp() {
        int accCruisingLampR;
        String str = f1;
        LogUtil.optionError(str, " getAccCruisingLamp.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                accCruisingLampR = carVendorWhudManager.getAccCruisingLampR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                accCruisingLampR = -1;
            }
        } else {
            accCruisingLampR = -1;
        }
        LogUtil.optionError(str, " getAccCruisingLamp.out = " + accCruisingLampR);
        return accCruisingLampR;
    }

    public int getAccCruisingLampColor() {
        int accCruisingLampColorR;
        String str = f1;
        LogUtil.optionError(str, " getAccCruisingLampColor.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                accCruisingLampColorR = carVendorWhudManager.getAccCruisingLampColorR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                accCruisingLampColorR = -1;
            }
        } else {
            accCruisingLampColorR = -1;
        }
        LogUtil.optionError(str, " getAccCruisingLampColor.out = " + accCruisingLampColorR);
        return accCruisingLampColorR;
    }

    public int getAccCruisingSpeedValue() {
        int accCruisingSpeedValueR;
        String str = f1;
        LogUtil.optionError(str, " getAccCruisingSpeedValue.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                accCruisingSpeedValueR = carVendorWhudManager.getAccCruisingSpeedValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                accCruisingSpeedValueR = -1;
            }
        } else {
            accCruisingSpeedValueR = -1;
        }
        LogUtil.optionError(str, " getAccCruisingSpeedValue.out = " + accCruisingSpeedValueR);
        return accCruisingSpeedValueR;
    }

    public int getAheadVehicleSpace() {
        int aheadVehicleSpaceR;
        String str = f1;
        LogUtil.optionError(str, " getAheadVehicleSpace.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                aheadVehicleSpaceR = carVendorWhudManager.getAheadVehicleSpaceR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                aheadVehicleSpaceR = -1;
            }
        } else {
            aheadVehicleSpaceR = -1;
        }
        LogUtil.optionError(str, " getAheadVehicleSpace.out = " + aheadVehicleSpaceR);
        return aheadVehicleSpaceR;
    }

    public float getAverageSpeed() {
        String str = f1;
        LogUtil.verbose(str, "getAverageSpeed");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            int averageSpeedValueS = carVendorStaticManager.getAverageSpeedValueS();
            LogUtil.verbose(str, "getAverageSpeedValueS=" + averageSpeedValueS);
            return averageSpeedValueS;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getAverageSpeedValue() {
        String str = f1;
        LogUtil.verbose(str, "getAverageSpeedValueS");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int averageSpeedValueS = carVendorStaticManager.getAverageSpeedValueS();
            LogUtil.verbose(str, "getAverageSpeedValueS=" + averageSpeedValueS);
            return averageSpeedValueS;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getBatteryPackEnergy() {
        String str = f1;
        LogUtil.verbose(str, "getBatteryPackEnergyR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float batteryPackEnergyR = carVendorStaticManager.getBatteryPackEnergyR();
            LogUtil.verbose(str, "getBatteryPackEnergyR=" + batteryPackEnergyR);
            return batteryPackEnergyR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public byte[] getCalibrationConfiguration() {
        return new byte[]{0};
    }

    public int getCanmsgFlagGwCdc1B() {
        return this.z;
    }

    public int getCanmsgFlagGwDcu21B() {
        return this.A;
    }

    public int getCanmsgFlagGwEcan1B() {
        return this.B;
    }

    public int getCanmsgFlagGwEcan2B() {
        return this.C;
    }

    public int getCanmsgFlagGwEcan3B() {
        return this.D;
    }

    public int getCanmsgFlagGwEcan4B() {
        return this.E;
    }

    public int getCanmsgFlagGwPcs1B() {
        return this.F;
    }

    public int getCanmsgFlagGwPcs1Pb() {
        return this.G;
    }

    public int getCanmsgFlagGwPcu1B() {
        return this.H;
    }

    public int getCanmsgFlagGwSas1B() {
        return this.I;
    }

    public int getCanmsgFlagGwSrs2B() {
        return this.J;
    }

    public int getCanmsgFlagGwVcu10B() {
        return this.K;
    }

    public int getCanmsgFlagGwVcu11B() {
        return this.L;
    }

    public int getCanmsgFlagGwVcu16B() {
        return this.M;
    }

    public int getCanmsgFlagGwVcu1B() {
        return this.N;
    }

    public int getCanmsgFlagGwVcu20B() {
        return this.O;
    }

    public int getCanmsgFlagGwVcu23Eb() {
        return this.P;
    }

    public int getCanmsgFlagGwVcu24Eb() {
        return this.Q;
    }

    public int getCanmsgFlagGwVcu2B() {
        return this.R;
    }

    public int getCanmsgFlagGwVcu39Eb() {
        return this.S;
    }

    public int getCanmsgFlagGwVcu41Eb() {
        return this.T;
    }

    public int getClusterBrightness() {
        return this.f0;
    }

    public int getCurrentLanguage() {
        return this.m;
    }

    public float getCurrentTripElec() {
        float currentTripElecR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripElec.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripElecR = carVendorStaticManager.getCurrentTripElecR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripElecR = -1.0f;
            }
            LogUtil.verbose(str, " getCurrentTripElec.out=" + currentTripElecR);
            return currentTripElecR;
        }
        LogUtil.verbose(str, " getCurrentTripElec.null == mStaticManger");
        currentTripElecR = -1.0f;
        LogUtil.verbose(str, " getCurrentTripElec.out=" + currentTripElecR);
        return currentTripElecR;
    }

    public int getCurrentTripElecUnit() {
        int currentTripElecUnitR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripElecUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripElecUnitR = carVendorStaticManager.getCurrentTripElecUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripElecUnitR = -1;
            }
            LogUtil.verbose(str, " getCurrentTripElecUnit.out=" + currentTripElecUnitR);
            return currentTripElecUnitR;
        }
        LogUtil.verbose(str, " getCurrentTripElecUnit.null == mStaticManger");
        currentTripElecUnitR = -1;
        LogUtil.verbose(str, " getCurrentTripElecUnit.out=" + currentTripElecUnitR);
        return currentTripElecUnitR;
    }

    public float getCurrentTripFuel() {
        float currentTripFuelR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripFuel.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripFuelR = carVendorStaticManager.getCurrentTripFuelR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripFuelR = -1.0f;
            }
            LogUtil.verbose(str, " getCurrentTripFuel.out=" + currentTripFuelR);
            return currentTripFuelR;
        }
        LogUtil.verbose(str, " getCurrentTripFuel.null == mStaticManger");
        currentTripFuelR = -1.0f;
        LogUtil.verbose(str, " getCurrentTripFuel.out=" + currentTripFuelR);
        return currentTripFuelR;
    }

    public int getCurrentTripFuelUnit() {
        int currentTripFuelUnitR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripFuelUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripFuelUnitR = carVendorStaticManager.getCurrentTripFuelUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripFuelUnitR = -1;
            }
            LogUtil.verbose(str, " getCurrentTripFuelUnit.out=" + currentTripFuelUnitR);
            return currentTripFuelUnitR;
        }
        LogUtil.verbose(str, " getCurrentTripFuelUnit.null == mStaticManger");
        currentTripFuelUnitR = -1;
        LogUtil.verbose(str, " getCurrentTripFuelUnit.out=" + currentTripFuelUnitR);
        return currentTripFuelUnitR;
    }

    public float getCurrentTripMileage() {
        float currentTripMileageR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripMileage.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripMileageR = carVendorStaticManager.getCurrentTripMileageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripMileageR = -1.0f;
            }
            LogUtil.verbose(str, " getCurrentTripMileage.out=" + currentTripMileageR);
            return currentTripMileageR;
        }
        LogUtil.verbose(str, " getCurrentTripMileage.null == mStaticManger");
        currentTripMileageR = -1.0f;
        LogUtil.verbose(str, " getCurrentTripMileage.out=" + currentTripMileageR);
        return currentTripMileageR;
    }

    public int getCurrentTripMileageUnit() {
        int currentTripMileageUnitR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripMileageUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripMileageUnitR = carVendorStaticManager.getCurrentTripMileageUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripMileageUnitR = -1;
            }
            LogUtil.verbose(str, " getCurrentTripMileageUnit.out=" + currentTripMileageUnitR);
            return currentTripMileageUnitR;
        }
        LogUtil.verbose(str, " getCurrentTripMileageUnit.null == mStaticManger");
        currentTripMileageUnitR = -1;
        LogUtil.verbose(str, " getCurrentTripMileageUnit.out=" + currentTripMileageUnitR);
        return currentTripMileageUnitR;
    }

    public int getCurrentTripTime() {
        int currentTripTimeR;
        String str = f1;
        LogUtil.verbose(str, " getCurrentTripTime.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                currentTripTimeR = carVendorStaticManager.getCurrentTripTimeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripTimeR = -1;
            }
            LogUtil.verbose(str, " getCurrentTripTime.out=" + currentTripTimeR);
            return currentTripTimeR;
        }
        LogUtil.verbose(str, " getCurrentTripTime.null == mStaticManger");
        currentTripTimeR = -1;
        LogUtil.verbose(str, " getCurrentTripTime.out=" + currentTripTimeR);
        return currentTripTimeR;
    }

    public int getDriverBeltStatus() {
        int driverBeltStatusR;
        String str = f1;
        LogUtil.verbose(str, " getDriverBeltStatus.in");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager != null) {
            try {
                driverBeltStatusR = carVendorSafetybeltManager.getDriverBeltStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverBeltStatusR = -1;
            }
            LogUtil.verbose(str, " getDriverBeltStatus.out=" + driverBeltStatusR);
            return driverBeltStatusR;
        }
        LogUtil.verbose(str, " getDriverBeltStatus.null == mSafetybeltManager");
        driverBeltStatusR = -1;
        LogUtil.verbose(str, " getDriverBeltStatus.out=" + driverBeltStatusR);
        return driverBeltStatusR;
    }

    public int getDrivingRangeAll() {
        int drivingRangeAllR;
        String str = f1;
        LogUtil.verbose(str, " getDrivingRangeAll.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                drivingRangeAllR = carVendorStaticManager.getDrivingRangeAllR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                drivingRangeAllR = -1;
            }
            LogUtil.verbose(str, " getDrivingRangeAll.out=" + drivingRangeAllR);
            return drivingRangeAllR;
        }
        LogUtil.verbose(str, " getDrivingRangeAll.null == mStaticManger");
        drivingRangeAllR = -1;
        LogUtil.verbose(str, " getDrivingRangeAll.out=" + drivingRangeAllR);
        return drivingRangeAllR;
    }

    public int getDrivingRangeUnit() {
        int drivingRangeUnitR;
        String str = f1;
        LogUtil.verbose(str, " getDrivingRangeUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                drivingRangeUnitR = carVendorStaticManager.getDrivingRangeUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                drivingRangeUnitR = -1;
            }
            LogUtil.verbose(str, " getDrivingRangeUnit.out=" + drivingRangeUnitR);
            return drivingRangeUnitR;
        }
        LogUtil.verbose(str, " getDrivingRangeUnit.null == mStaticManger");
        drivingRangeUnitR = -1;
        LogUtil.verbose(str, " getDrivingRangeUnit.out=" + drivingRangeUnitR);
        return drivingRangeUnitR;
    }

    public float getDrivingTimeValue() {
        String str = f1;
        LogUtil.verbose(str, "getDrivingTimeValueR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float drivingTimeValueTwoR = carVendorStaticManager.getDrivingTimeValueTwoR();
            LogUtil.verbose(str, "getDrivingTimeValueTwoR=" + drivingTimeValueTwoR);
            return drivingTimeValueTwoR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getEVMileageValue() {
        String str = f1;
        LogUtil.verbose(str, "getEVMileageValueS");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int eVMileageValueS = carVendorStaticManager.getEVMileageValueS();
            LogUtil.verbose(str, "getEVMileageValueS=" + eVMileageValueS);
            return eVMileageValueS;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEbCallStatus() {
        return this.v;
    }

    public int getEbCallType() {
        return this.u;
    }

    public int[] getEcuSerialNumber() {
        return new int[]{0};
    }

    public int getElecConsUnit() {
        int elecConsUnitR;
        String str = f1;
        LogUtil.verbose(str, " getElecConsUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                elecConsUnitR = carVendorStaticManager.getElecConsUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                elecConsUnitR = -1;
            }
            LogUtil.verbose(str, " getElecConsUnit.out=" + elecConsUnitR);
            return elecConsUnitR;
        }
        LogUtil.verbose(str, " getElecConsUnit.null == mStaticManger");
        elecConsUnitR = -1;
        LogUtil.verbose(str, " getElecConsUnit.out=" + elecConsUnitR);
        return elecConsUnitR;
    }

    public int getElecDrivingRangeByStandard() {
        String str = f1;
        LogUtil.verbose(str, "getElecDrivingRangeByStandard");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int elecDrivingRangeByStandardR = carVendorStaticManager.getElecDrivingRangeByStandardR();
            LogUtil.verbose(str, "getElecDrivingRangeByStandardR=" + elecDrivingRangeByStandardR);
            return elecDrivingRangeByStandardR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElecDrivingRangeValue() {
        String str = f1;
        LogUtil.verbose(str, "getElecDrivingRangeValue");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int elecDrivingRangeValueTwoR = carVendorStaticManager.getElecDrivingRangeValueTwoR();
            LogUtil.verbose(str, "getElecDrivingRangeValueTwoR=" + elecDrivingRangeValueTwoR);
            return elecDrivingRangeValueTwoR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getElecPercentageValue() {
        String str = f1;
        LogUtil.verbose(str, "getElecPercentageValue");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float elecPercentageValueR = carVendorStaticManager.getElecPercentageValueR();
            LogUtil.verbose(str, "getElecPercentageValueR=" + elecPercentageValueR);
            return elecPercentageValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getElecUnit() {
        int elecUnitR;
        String str = f1;
        LogUtil.verbose(str, " getElecUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                elecUnitR = carVendorStaticManager.getElecUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                elecUnitR = -1;
            }
            LogUtil.verbose(str, " getElecUnit.out=" + elecUnitR);
            return elecUnitR;
        }
        LogUtil.verbose(str, " getElecUnit.null == mStaticManger");
        elecUnitR = -1;
        LogUtil.verbose(str, " getElecUnit.out=" + elecUnitR);
        return elecUnitR;
    }

    public float getEvRemainingBatteryPower() {
        String str = f1;
        LogUtil.info(str, "getEvRemainingBatteryPower");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float evRemainingBatteryPowerR = carVendorStaticManager.getEvRemainingBatteryPowerR();
            LogUtil.verbose(str, "getEvRemainingBatteryPowerR=" + evRemainingBatteryPowerR);
            return evRemainingBatteryPowerR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getFactoryResetState() {
        return this.o0;
    }

    public int getFuelDrivingRangeValue() {
        String str = f1;
        LogUtil.verbose(str, "getFuelDrivingRangeValueR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int fuelDrivingRangeValueR = carVendorStaticManager.getFuelDrivingRangeValueR();
            LogUtil.verbose(str, "getFuelDrivingRangeValueR=" + fuelDrivingRangeValueR);
            return fuelDrivingRangeValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFuelPercentageValue() {
        String str = f1;
        LogUtil.verbose(str, "getFuelPercentageValueR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int fuelPercentageValueR = carVendorStaticManager.getFuelPercentageValueR();
            LogUtil.verbose(str, "getFuelPercentageValueR=" + fuelPercentageValueR);
            return fuelPercentageValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte[] getFunctionConfiguration() {
        return new byte[]{0};
    }

    public int getHEVMileageValue() {
        String str = f1;
        LogUtil.verbose(str, "getHEVMileageValueS");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int hEVMileageValueS = carVendorStaticManager.getHEVMileageValueS();
            LogUtil.verbose(str, "getHEVMileageValueS=" + hEVMileageValueS);
            return hEVMileageValueS;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte[] getHeadUnitConfiguration() {
        return new byte[]{0};
    }

    public int getHomeScreenBrightness() {
        return this.g0;
    }

    public int getHudState() {
        return this.X;
    }

    public int getInstrumentBacklight() {
        return this.c0;
    }

    public int getInstrumentVolume() {
        return this.h;
    }

    public int getKeyBatteryLevel() {
        String str = f1;
        LogUtil.verbose(str, "getKeyBatteryLevelR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            int keyBatteryLevelR = carVendorStaticManager.getKeyBatteryLevelR();
            LogUtil.verbose(str, "getKeyBatteryLevelR=" + keyBatteryLevelR);
            return keyBatteryLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftLine() {
        int leftLineR;
        String str = f1;
        LogUtil.optionError(str, " getLeftLine.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                leftLineR = carVendorWhudManager.getLeftLineR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftLineR = -1;
            }
        } else {
            leftLineR = -1;
        }
        LogUtil.optionError(str, " getLeftLine.out = " + leftLineR);
        return leftLineR;
    }

    public int getMainSeatState() {
        int mainSeatStateR;
        String str = f1;
        LogUtil.verbose(str, " getMainSeatState.in service");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager != null) {
            try {
                mainSeatStateR = carVendorSafetybeltManager.getMainSeatStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainSeatStateR = -1;
            }
            LogUtil.verbose(str, " getMainSeatState.out service =" + mainSeatStateR);
            return mainSeatStateR;
        }
        LogUtil.verbose(str, " getMainSeatState.null");
        mainSeatStateR = -1;
        LogUtil.verbose(str, " getMainSeatState.out service =" + mainSeatStateR);
        return mainSeatStateR;
    }

    public int getMaintenanceAlertCycle() {
        return this.x;
    }

    public int getMaintenanceAlertRepeat() {
        return this.w;
    }

    public int getMcuTimeFormat() {
        return this.U;
    }

    public byte[] getMenuConfiguration() {
        return new byte[]{0};
    }

    public float getMileageNumberOne() {
        String str = f1;
        LogUtil.verbose(str, "getMileageNumberOne");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float mileageNumberOneR = carVendorStaticManager.getMileageNumberOneR();
            LogUtil.verbose(str, "getMileageNumberOne=" + mileageNumberOneR);
            return mileageNumberOneR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getMileageNumberTwo() {
        String str = f1;
        LogUtil.verbose(str, "getMileageNumberTwo");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float mileageNumberTwoR = carVendorStaticManager.getMileageNumberTwoR();
            LogUtil.verbose(str, "getMileageNumberTwo=" + mileageNumberTwoR);
            return mileageNumberTwoR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getMileageUnit() {
        return this.k;
    }

    public int[] getNaviGyroAccelUpdateMsg() {
        return new int[]{0};
    }

    public String getNaviUuid() {
        return this.k0;
    }

    public int getOilConsumptionUnit() {
        return this.k;
    }

    public int getPassengerStatus(int i) {
        return 0;
    }

    public int getPhoneState() {
        return this.W;
    }

    public int getPowerParkingLamp() {
        return this.e0;
    }

    public int getPowerStateReq() {
        return this.d0;
    }

    public int getPressureUnit() {
        return this.j;
    }

    public float getRemainingBatteryPower() {
        String str = f1;
        LogUtil.verbose(str, "getRemainingBatteryPower");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float remainingBatteryPowerR = carVendorStaticManager.getRemainingBatteryPowerR();
            LogUtil.verbose(str, "getRemainingBatteryPowerR=" + remainingBatteryPowerR);
            return remainingBatteryPowerR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getRightLine() {
        int rightLineR;
        String str = f1;
        LogUtil.optionError(str, " getRightLine.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                rightLineR = carVendorWhudManager.getRightLineR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightLineR = -1;
            }
        } else {
            rightLineR = -1;
        }
        LogUtil.optionError(str, " getRightLine.out = " + rightLineR);
        return rightLineR;
    }

    public int getSafetyBeltStatusDeputy() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusDeputy");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusDeputy = carVendorSafetybeltManager.getSafetyBeltStatusDeputy();
            LogUtil.verbose(str, "getSafetyBeltStatusDeputy=" + safetyBeltStatusDeputy);
            return safetyBeltStatusDeputy;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusLeftThreeRear() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusLeftThreeRear");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusLeftThreeRear = carVendorSafetybeltManager.getSafetyBeltStatusLeftThreeRear();
            LogUtil.verbose(str, "getSafetyBeltStatusLeftThreeRear=" + safetyBeltStatusLeftThreeRear);
            return safetyBeltStatusLeftThreeRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusLeftTwoRear() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusLEFT");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusLeftTwoRear = carVendorSafetybeltManager.getSafetyBeltStatusLeftTwoRear();
            LogUtil.verbose(str, "getSafetyBeltStatusLeftTwoRear=" + safetyBeltStatusLeftTwoRear);
            return safetyBeltStatusLeftTwoRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusMain() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusMain");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusMain = carVendorSafetybeltManager.getSafetyBeltStatusMain();
            LogUtil.verbose(str, "getSafetyBeltStatusMain=" + safetyBeltStatusMain);
            return safetyBeltStatusMain;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusMidThreeRear() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusMidThreeRear");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusMidThreeRear = carVendorSafetybeltManager.getSafetyBeltStatusMidThreeRear();
            LogUtil.verbose(str, "getSafetyBeltStatusMidThreeRear=" + safetyBeltStatusMidThreeRear);
            return safetyBeltStatusMidThreeRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusMidTwoRear() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusMID");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusMidTwoRear = carVendorSafetybeltManager.getSafetyBeltStatusMidTwoRear();
            LogUtil.verbose(str, "getSafetyBeltStatusMidTwoRear=" + safetyBeltStatusMidTwoRear);
            return safetyBeltStatusMidTwoRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusRightThreeRear() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusRightThreeRear");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusRightThreeRear = carVendorSafetybeltManager.getSafetyBeltStatusRightThreeRear();
            LogUtil.verbose(str, "getSafetyBeltStatusRightThreeRear=" + safetyBeltStatusRightThreeRear);
            return safetyBeltStatusRightThreeRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafetyBeltStatusRightTwoRear() {
        String str = f1;
        LogUtil.verbose(str, "getSafetyBeltStatusRIGHT");
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.f71a;
        if (carVendorSafetybeltManager == null) {
            return 0;
        }
        try {
            int safetyBeltStatusRightTwoRear = carVendorSafetybeltManager.getSafetyBeltStatusRightTwoRear();
            LogUtil.verbose(str, "getSafetyBeltStatusRightTwoRear=" + safetyBeltStatusRightTwoRear);
            return safetyBeltStatusRightTwoRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSecondaryScreenBrightness() {
        return this.h0;
    }

    public long getSettingsMcuTime() {
        return this.U;
    }

    public String getSettingsMcuTimeZone() {
        return this.V;
    }

    public int getShowAdasWork() {
        int showAdasWorkR;
        String str = f1;
        LogUtil.optionError(str, " getShowAdasWork.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                showAdasWorkR = carVendorWhudManager.getShowAdasWorkR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                showAdasWorkR = -1;
            }
        } else {
            showAdasWorkR = -1;
        }
        LogUtil.optionError(str, " getShowAdasWork.out = " + showAdasWorkR);
        return showAdasWorkR;
    }

    public int getSoundAncSwitch() {
        return this.a0;
    }

    public int getSoundEffect() {
        return this.Z;
    }

    public int getSoundEssSwitch() {
        return this.b0;
    }

    public int getSoundWarningPedestrians() {
        return this.Y;
    }

    public int getSpeedUnit() {
        int speedUnitR;
        String str = f1;
        LogUtil.verbose(str, " getSpeedUnit.in");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                speedUnitR = carVendorStaticManager.getSpeedUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                speedUnitR = -1;
            }
            LogUtil.verbose(str, " getSpeedUnit.out=" + speedUnitR);
            return speedUnitR;
        }
        LogUtil.verbose(str, " getSpeedUnit.null == mStaticManger");
        speedUnitR = -1;
        LogUtil.verbose(str, " getSpeedUnit.out=" + speedUnitR);
        return speedUnitR;
    }

    public int getTboxResetFailReason() {
        return this.t;
    }

    public int getTboxResetStatus() {
        return this.s;
    }

    public boolean getTboxSettingsResetState() {
        return this.y;
    }

    public int getTemperatureUnit() {
        return this.i;
    }

    public int getTemperatureWarningMsg() {
        return this.i0;
    }

    public int getTextHint() {
        int textHintR;
        String str = f1;
        LogUtil.optionError(str, " getTextHint.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                textHintR = carVendorWhudManager.getTextHintR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                textHintR = -1;
            }
        } else {
            textHintR = -1;
        }
        LogUtil.optionError(str, " getTextHint.out = " + textHintR);
        return textHintR;
    }

    public int getTextMessageHint() {
        int textMessageHintR;
        String str = f1;
        LogUtil.optionError(str, " getTextMessageHint.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                textMessageHintR = carVendorWhudManager.getTextMessageHintR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                textMessageHintR = -1;
            }
        } else {
            textMessageHintR = -1;
        }
        LogUtil.optionError(str, " getTextMessageHint.out = " + textMessageHintR);
        return textMessageHintR;
    }

    public float getTotalElecConPHMValue() {
        String str = f1;
        LogUtil.verbose(str, "getTotalElecConPHMValue");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float totalElecConPHMValueR = carVendorStaticManager.getTotalElecConPHMValueR();
            LogUtil.verbose(str, "getTotalElecConPHMValueR=" + totalElecConPHMValueR);
            return totalElecConPHMValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTotalElecConValue() {
        String str = f1;
        LogUtil.verbose(str, "getTotalElecConValueR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float totalElecConValueR = carVendorStaticManager.getTotalElecConValueR();
            LogUtil.verbose(str, "getTotalElecConValueR=" + totalElecConValueR);
            return totalElecConValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTotalFuelConPHMValue() {
        String str = f1;
        LogUtil.verbose(str, "getTotalFuelConPHMValue");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float totalFuelConPHMValueR = carVendorStaticManager.getTotalFuelConPHMValueR();
            LogUtil.verbose(str, "getTotalFuelConPHMValueR=" + totalFuelConPHMValueR);
            return totalFuelConPHMValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTotalFuelConValue() {
        String str = f1;
        LogUtil.verbose(str, "getTotalFuelConValueR");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0.0f;
        }
        try {
            float totalFuelConValueR = carVendorStaticManager.getTotalFuelConValueR();
            LogUtil.verbose(str, "getTotalFuelConValueR=" + totalFuelConValueR);
            return totalFuelConValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTotalMileageValue() {
        String str = f1;
        LogUtil.verbose(str, "getTotalMileageValueR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(559957001, 0);
        if (carPropertyValueA == null) {
            return 0.0f;
        }
        LogUtil.verbose(str, "getTotalMileageValue prop value " + carPropertyValueA.getValue());
        return ((Float) carPropertyValueA.getValue()).floatValue();
    }

    public int getTrafficSignRecognitionValue() {
        int trafficSignRecognitionValueR;
        String str = f1;
        LogUtil.optionError(str, " getTrafficSignRecognitionValue.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                trafficSignRecognitionValueR = carVendorWhudManager.getTrafficSignRecognitionValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trafficSignRecognitionValueR = -1;
            }
        } else {
            trafficSignRecognitionValueR = -1;
        }
        LogUtil.optionError(str, " getTrafficSignRecognitionValue.out = " + trafficSignRecognitionValueR);
        return trafficSignRecognitionValueR;
    }

    public int getTsrAvaibleDriveDistance() {
        return this.l0;
    }

    public int getTsrFuelLevelStatus() {
        return this.m0;
    }

    public int getTsrFuelLevelWarningStatus() {
        return this.n0;
    }

    public int getVehicleBodyPosition() {
        int vehicleBodyPositionR;
        String str = f1;
        LogUtil.optionError(str, " getVehicleBodyPosition.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                vehicleBodyPositionR = carVendorWhudManager.getVehicleBodyPositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                vehicleBodyPositionR = -1;
            }
        } else {
            vehicleBodyPositionR = -1;
        }
        LogUtil.optionError(str, " getVehicleBodyPosition.out = " + vehicleBodyPositionR);
        return vehicleBodyPositionR;
    }

    public byte[] getVehicleConfiguration() {
        return new byte[]{0};
    }

    public int getVehicleSpace() {
        int vehicleSpaceR;
        String str = f1;
        LogUtil.optionError(str, " getVehicleSpace.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                vehicleSpaceR = carVendorWhudManager.getVehicleSpaceR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                vehicleSpaceR = -1;
            }
        } else {
            vehicleSpaceR = -1;
        }
        LogUtil.optionError(str, " getVehicleSpace.out = " + vehicleSpaceR);
        return vehicleSpaceR;
    }

    public String getVersion() {
        LogUtil.info(f1, "getVersion:");
        return "";
    }

    public int getVoltageWarningMsg() {
        return this.j0;
    }

    public boolean is24Hours() {
        LogUtil.verbose(f1, "is24Hours:");
        return "24".equals(Settings.System.getString(this.r.getContentResolver(), "time_12_24"));
    }

    public boolean isAutoSyncTime() {
        boolean z = Settings.Global.getInt(this.r.getContentResolver(), "auto_gps_time", 0) != 0;
        LogUtil.verbose(f1, "isAutoSyncTime :" + z);
        return z;
    }

    public boolean isRearBeltReminder() {
        return this.l;
    }

    public void n() {
        com.ts.appservice.caradapter.a.e eVar = this.n;
        if (eVar != null) {
            this.q = false;
            eVar.g();
        }
        s();
    }

    public void o(CarVendorSafetybeltManager carVendorSafetybeltManager, CarVendorStaticManager carVendorStaticManager, CarVendorWhudManager carVendorWhudManager, CarVendorAmbientManager carVendorAmbientManager) {
        this.f71a = carVendorSafetybeltManager;
        this.f72b = carVendorStaticManager;
        this.c = carVendorWhudManager;
        this.d = carVendorAmbientManager;
        try {
            if (this.p == null) {
                this.p = new a(this.o.getLooper());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.e eVar = this.n;
        if (eVar != null) {
            this.q = true;
            eVar.m();
        }
    }

    @Override // com.ts.appservice.caradapter.d.u
    public void onSafetybeltEventChange(SafetybeltEventChangeValue safetybeltEventChangeValue) {
        String str = f1;
        if (safetybeltEventChangeValue == null) {
            LogUtil.error(str, "onSafetybeltEventChange:value=null");
            return;
        }
        int propertyId = safetybeltEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onSafetybeltEventChange-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = safetybeltEventChangeValue.getAreaId();
        messageObtain.obj = safetybeltEventChangeValue;
        this.p.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.y
    public void onStaticEventChange(StaticEventChangeValue staticEventChangeValue) {
        String str = f1;
        if (staticEventChangeValue == null) {
            LogUtil.error(str, "onSafetybeltEventChange:value=null");
            return;
        }
        int propertyId = staticEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onStaticEventChange-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = staticEventChangeValue.getAreaId();
        messageObtain.obj = staticEventChangeValue;
        this.p.sendMessage(messageObtain);
    }

    public String readCarModel() {
        LogUtil.info(f1, "readCarModel:");
        return "";
    }

    public String readFirmwareVersion() {
        return this.f;
    }

    public String readHardwareVersion() {
        return this.g;
    }

    public String readManufacturer() {
        LogUtil.info(f1, "readManufacturer:");
        return "";
    }

    public String readSerialNumber() {
        LogUtil.verbose(f1, "readSerialNumber:");
        return "";
    }

    public int resetAverageSpeed(int i) {
        LogUtil.verbose(f1, "setAverageSpeedS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setAverageSpeedS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int resetData() {
        return 0;
    }

    public int resetDrivingTimeValue(int i) {
        LogUtil.verbose(f1, "setDrivingTimeValueS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setDrivingTimeValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int resetExtChargeCapacity(int i) {
        LogUtil.verbose(f1, "setExtChargeCapacityS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setExtChargeCapacityS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int resetStatisticDate(int i, int i2, int i3, int i4) {
        int[] iArr = {i, i2, i3, i4};
        LogUtil.verbose(f1, "resetStatisticDate" + Arrays.toString(iArr));
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            try {
                carVendorStaticManager.resetStatisticDateS(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int resetTotalFuelConValue(int i) {
        LogUtil.verbose(f1, "setTotalFuelConValueS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setTotalFuelConValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int resetTravelDate(int i) {
        int i2;
        String str = f1;
        LogUtil.verbose(str, " resetTravelDate.in service");
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager != null) {
            i2 = 1;
            try {
                carVendorStaticManager.setResetTravelDateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " resetTravelDate.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " resetTravelDate.out service =" + i2);
        return i2;
    }

    public void resetfactory() {
    }

    public int sendIsaDataToAdas(int[] iArr) {
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setSendIsaData2AdasS(iArr);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void set24Hours(boolean z) {
        LogUtil.verbose(f1, "set24Hours:" + z);
        Settings.System.putString(this.r.getContentResolver(), "time_12_24", z ? "24" : "12");
        w(z);
    }

    public int setArrivalDayEstimated(int i) {
        LogUtil.optionError(f1, " setArrivalDayEstimated.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setArrivalDayEstimatedS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setArrivalHourEstimated(int i) {
        String str = f1;
        LogUtil.optionError(str, " setArrivalHourEstimated.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setArrivalHourEstimatedS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setArrivalHourEstimated.out = -1");
        return -1;
    }

    public int setArrivalMinuteEstimated(int i) {
        String str = f1;
        LogUtil.optionError(str, " setArrivalMinuteEstimated.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setArrivalMinuteEstimatedS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setArrivalMinuteEstimated.out = -1");
        return -1;
    }

    public int setArrivalSecondEstimated(int i) {
        String str = f1;
        LogUtil.optionError(str, " setArrivalSecondEstimated.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setArrivalSecondEstimatedS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setArrivalSecondEstimated.out = -1");
        return -1;
    }

    public void setAutoSyncTime(boolean z) {
        LogUtil.verbose(f1, "setAutoSyncTime:" + z);
        Settings.Global.putInt(this.r.getContentResolver(), "auto_gps_time", z ? 1 : 0);
    }

    public int setCallNumber(String str) {
        String str2 = f1;
        LogUtil.optionError(str2, " setCallNumber.in");
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                int length = bArrV.length;
                byte[] bArr = new byte[length + 1];
                for (int i = 0; i < length; i++) {
                    bArr[i] = bArrV[i];
                }
                bArr[length] = -1;
                this.c.setCallNumberLenS(length);
                this.c.setCallNumberS(bArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str2, " setCallNumber.out = -1");
        return -1;
    }

    public int setCallState(int i) {
        String str = f1;
        LogUtil.optionError(str, " setCallState.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setCallStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setCallState.out = -1");
        return -1;
    }

    public int setCallTimeHour(int i) {
        String str = f1;
        LogUtil.optionError(str, " setCallTimeHour.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setCallTimeHourS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setCallTimeHour.out = -1");
        return -1;
    }

    public int setCallTimeMinute(int i) {
        String str = f1;
        LogUtil.optionError(str, " setCallTimeMinute.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setCallTimeMinuteS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setCallTimeMinute.out = -1");
        return -1;
    }

    public int setCallTimeSecond(int i) {
        String str = f1;
        LogUtil.optionError(str, " setCallTimeSecond.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setCallTimeSecondS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setCallTimeSecond.out = -1");
        return -1;
    }

    public void setClusterBrightness(int i) {
    }

    public int setCurrentDayUnitLen(String str) {
        String str2 = f1;
        LogUtil.verbose(str2, " setCurrentDayUnitLen.in" + str);
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                LogUtil.optionError(str2, " setCurrentDayUnitLen.bytes" + Arrays.toString(bArrV));
                int length = 52;
                if (bArrV.length < 52) {
                    length = bArrV.length;
                }
                int i = ((length + 2) / 7) + 1;
                byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i, 7);
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < i) {
                    for (int i5 = i2 == 0 ? 2 : 0; i5 < 7; i5++) {
                        if (i3 < length) {
                            bArr[i2][i5] = bArrV[i3];
                            i4 += bArr[i2][i5];
                            i3++;
                        } else if (i5 < 7) {
                            bArr[i2][i5] = -1;
                        }
                    }
                    i2++;
                }
                LogUtil.optionError(str2, " check = " + i4);
                LogUtil.optionError(str2, " length.in" + length);
                this.c.setCurrentDayUnitLenS(new long[]{(long) length, (long) ((byte) (i4 ^ 255))});
                this.c.setCurrentDayUnit1S(new byte[]{bArr[0][2], bArr[0][3], bArr[0][4], bArr[0][5], bArr[0][6]});
                for (int i6 = 1; i6 < bArr.length; i6++) {
                    switch (i6) {
                        case 1:
                            this.c.setCurrentDayUnit2S(bArr[i6]);
                            for (int i7 = 0; i7 < bArr[i6].length; i7++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit1S.in" + ((int) bArr[i6][i7]) + " ");
                            }
                            break;
                        case 2:
                            this.c.setCurrentDayUnit3S(bArr[i6]);
                            for (int i8 = 0; i8 < bArr[i6].length; i8++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit2S.in" + ((int) bArr[i6][i8]) + " ");
                            }
                            break;
                        case 3:
                            this.c.setCurrentDayUnit4S(bArr[i6]);
                            for (int i9 = 0; i9 < bArr[i6].length; i9++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit3S.in" + ((int) bArr[i6][i9]) + " ");
                            }
                            break;
                        case 4:
                            this.c.setCurrentDayUnit5S(bArr[i6]);
                            for (int i10 = 0; i10 < bArr[i6].length; i10++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit4S.in" + ((int) bArr[i6][i10]) + " ");
                            }
                            break;
                        case 5:
                            this.c.setCurrentDayUnit6S(bArr[i6]);
                            for (int i11 = 0; i11 < bArr[i6].length; i11++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit5S.in" + ((int) bArr[i6][i11]) + " ");
                            }
                            break;
                        case 6:
                            this.c.setCurrentDayUnit7S(bArr[i6]);
                            for (int i12 = 0; i12 < bArr[i6].length; i12++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit6S.in" + ((int) bArr[i6][i12]) + " ");
                            }
                            break;
                        case 7:
                            this.c.setCurrentDayUnit8S(bArr[i6]);
                            for (int i13 = 0; i13 < bArr[i6].length; i13++) {
                                LogUtil.optionError(str2, " setCurrentDayUnit7S.in" + ((int) bArr[i6][i13]) + " ");
                            }
                            break;
                    }
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setCurrentHourUnitLen(String str) {
        String str2 = f1;
        LogUtil.verbose(str2, " setCurrentDayUnitLen.in" + str);
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                LogUtil.optionError(str2, " setCurrentDayUnitLen.bytes" + Arrays.toString(bArrV));
                int length = 52;
                if (bArrV.length < 52) {
                    length = bArrV.length;
                }
                int i = ((length + 2) / 7) + 1;
                byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i, 7);
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < i) {
                    for (int i5 = i2 == 0 ? 2 : 0; i5 < 7; i5++) {
                        if (i3 < length) {
                            bArr[i2][i5] = bArrV[i3];
                            i4 += bArr[i2][i5];
                            i3++;
                        } else if (i5 < 7) {
                            bArr[i2][i5] = -1;
                        }
                    }
                    i2++;
                }
                LogUtil.optionError(str2, " check = " + i4);
                LogUtil.optionError(str2, " length.in" + length);
                this.c.setCurrentHourUnitLenS(new long[]{(long) length, (long) ((byte) (i4 ^ 255))});
                this.c.setCurrentHourUnit1S(new byte[]{bArr[0][2], bArr[0][3], bArr[0][4], bArr[0][5], bArr[0][6]});
                for (int i6 = 1; i6 < bArr.length; i6++) {
                    switch (i6) {
                        case 1:
                            this.c.setCurrentHourUnit2S(bArr[i6]);
                            for (int i7 = 0; i7 < bArr[i6].length; i7++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit1S.in" + ((int) bArr[i6][i7]) + " ");
                            }
                            break;
                        case 2:
                            this.c.setCurrentHourUnit3S(bArr[i6]);
                            for (int i8 = 0; i8 < bArr[i6].length; i8++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit2S.in" + ((int) bArr[i6][i8]) + " ");
                            }
                            break;
                        case 3:
                            this.c.setCurrentHourUnit4S(bArr[i6]);
                            for (int i9 = 0; i9 < bArr[i6].length; i9++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit3S.in" + ((int) bArr[i6][i9]) + " ");
                            }
                            break;
                        case 4:
                            this.c.setCurrentHourUnit5S(bArr[i6]);
                            for (int i10 = 0; i10 < bArr[i6].length; i10++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit4S.in" + ((int) bArr[i6][i10]) + " ");
                            }
                            break;
                        case 5:
                            this.c.setCurrentHourUnit6S(bArr[i6]);
                            for (int i11 = 0; i11 < bArr[i6].length; i11++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit5S.in" + ((int) bArr[i6][i11]) + " ");
                            }
                            break;
                        case 6:
                            this.c.setCurrentHourUnit7S(bArr[i6]);
                            for (int i12 = 0; i12 < bArr[i6].length; i12++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit6S.in" + ((int) bArr[i6][i12]) + " ");
                            }
                            break;
                        case 7:
                            this.c.setCurrentHourUnit8S(bArr[i6]);
                            for (int i13 = 0; i13 < bArr[i6].length; i13++) {
                                LogUtil.optionError(str2, " setCurrentHourUnit7S.in" + ((int) bArr[i6][i13]) + " ");
                            }
                            break;
                    }
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setCurrentLanguage(int i) {
    }

    public int setCurrentMinUnitLen(String str) {
        String str2 = f1;
        LogUtil.verbose(str2, " setCurrentMinUnitLen.in" + str);
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                LogUtil.optionError(str2, " setCurrentMinUnitLen.bytes" + Arrays.toString(bArrV));
                int length = 52;
                if (bArrV.length < 52) {
                    length = bArrV.length;
                }
                int i = ((length + 2) / 7) + 1;
                byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i, 7);
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < i) {
                    for (int i5 = i2 == 0 ? 2 : 0; i5 < 7; i5++) {
                        if (i3 < length) {
                            bArr[i2][i5] = bArrV[i3];
                            i4 += bArr[i2][i5];
                            i3++;
                        } else if (i5 < 7) {
                            bArr[i2][i5] = -1;
                        }
                    }
                    i2++;
                }
                LogUtil.optionError(str2, " check = " + i4);
                LogUtil.optionError(str2, " length.in" + length);
                this.c.setCurrentMinUnitLenS(new long[]{(long) length, (long) ((byte) (i4 ^ 255))});
                this.c.setCurrentMinUnit1S(new byte[]{bArr[0][2], bArr[0][3], bArr[0][4], bArr[0][5], bArr[0][6]});
                for (int i6 = 1; i6 < bArr.length; i6++) {
                    switch (i6) {
                        case 1:
                            this.c.setCurrentMinUnit2S(bArr[i6]);
                            for (int i7 = 0; i7 < bArr[i6].length; i7++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit1S.in" + ((int) bArr[i6][i7]) + " ");
                            }
                            break;
                        case 2:
                            this.c.setCurrentMinUnit3S(bArr[i6]);
                            for (int i8 = 0; i8 < bArr[i6].length; i8++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit2S.in" + ((int) bArr[i6][i8]) + " ");
                            }
                            break;
                        case 3:
                            this.c.setCurrentMinUnit4S(bArr[i6]);
                            for (int i9 = 0; i9 < bArr[i6].length; i9++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit3S.in" + ((int) bArr[i6][i9]) + " ");
                            }
                            break;
                        case 4:
                            this.c.setCurrentMinUnit5S(bArr[i6]);
                            for (int i10 = 0; i10 < bArr[i6].length; i10++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit4S.in" + ((int) bArr[i6][i10]) + " ");
                            }
                            break;
                        case 5:
                            this.c.setCurrentMinUnit6S(bArr[i6]);
                            for (int i11 = 0; i11 < bArr[i6].length; i11++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit5S.in" + ((int) bArr[i6][i11]) + " ");
                            }
                            break;
                        case 6:
                            this.c.setCurrentMinUnit7S(bArr[i6]);
                            for (int i12 = 0; i12 < bArr[i6].length; i12++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit6S.in" + ((int) bArr[i6][i12]) + " ");
                            }
                            break;
                        case 7:
                            this.c.setCurrentMinUnit8S(bArr[i6]);
                            for (int i13 = 0; i13 < bArr[i6].length; i13++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit7S.in" + ((int) bArr[i6][i13]) + " ");
                            }
                            break;
                    }
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setDispSettingScdisplaycontrolMsg(int i, int i2) {
    }

    public int setDistanceAhead(int i) {
        LogUtil.optionError(f1, " setDistanceAhead.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDistanceAheadS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDistanceAheadUnit(int i) {
        LogUtil.optionError(f1, " setDistanceAheadUnit.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDistanceAheadUnitS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDistanceFrontTarget(int i) {
        LogUtil.optionError(f1, " setDistanceFrontTarget.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDistanceFrontTargetS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDistanceFrontTargetUnit(int i) {
        LogUtil.optionError(f1, " setDistanceFrontTargetUnit.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDistanceFrontTargetUnitS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingDays(int i) {
        LogUtil.optionError(f1, " setDriveRemainingDays.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingDaysS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingHour(int i) {
        LogUtil.optionError(f1, " setDriveRemainingHour.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingHourS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingHourFormat(int i) {
        LogUtil.optionError(f1, " setDriveRemainingHourFormat.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingHourFormatS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingMileage(long j) {
        LogUtil.optionError(f1, " setDriveRemainingMileage.in" + j);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingMileageS(j);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingMinute(int i) {
        LogUtil.optionError(f1, " setDriveRemainingMinute.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingMinuteS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingSecond(int i) {
        LogUtil.optionError(f1, " setDriveRemainingSecond.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingSecondS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDriveRemainingUnit(int i) {
        LogUtil.optionError(f1, " setDriveRemainingUnit.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setDriveRemainingUnitS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setEasyNavGuideInfo(long[] jArr) {
        LogUtil.optionError(f1, " setEasyNavGuideInfo.in" + Arrays.toString(jArr));
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setEasyNavGuideInfoS(jArr);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setHangUpEBcall(int i) {
    }

    public void setHomeScreenBrightness(int i) {
    }

    public void setHudOffState(int i) {
    }

    public void setIcmLcdDetectionMode(int i) {
    }

    public void setInstrumentBacklight(int i) {
    }

    public void setInstrumentCumulativeDrivingDataReset(int i) {
    }

    public void setInstrumentDisplayReset(int i) {
    }

    public void setInstrumentVolume(int i) {
    }

    public int setKtvResource(int i) {
        LogUtil.info(f1, "setKtvResource" + i);
        CarVendorAmbientManager carVendorAmbientManager = this.d;
        if (carVendorAmbientManager == null) {
            return 0;
        }
        try {
            carVendorAmbientManager.setKtvResourceS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setMaintenanceAlertCycle(int i) {
    }

    public void setMaintenanceAlertRepeat(int i) {
    }

    public void setMaintenanceResetReminder(int i) {
    }

    public void setMcuTimeFormat(int i) {
    }

    public int setMediaSource(int i) {
        String str = f1;
        LogUtil.optionError(str, " setMediaSource.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setMediaSourceS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setMediaSource.out = -1");
        return -1;
    }

    public void setMileageUnit(int i) {
        LogUtil.verbose(f1, "setMileageUnit :" + i);
    }

    public int setMusicCurrentName(String str) {
        String str2 = f1;
        LogUtil.optionError(str2, " setMusicCurrentName.in");
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                int length = bArrV.length;
                byte[] bArr = new byte[length + 1];
                for (int i = 0; i < length; i++) {
                    bArr[i] = bArrV[i];
                }
                bArr[length] = -1;
                this.c.setMusicCurrentNameLenS(length);
                this.c.setMusicCurrentNameS(bArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str2, " setMusicCurrentName.out = -1");
        return -1;
    }

    public int setMusicPlayProgress(int i) {
        String str = f1;
        LogUtil.optionError(str, " setMusicPlayProgress.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setMusicPlayProgressS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setMusicPlayProgress.out = -1");
        return -1;
    }

    public int setMusicPlayState(int i) {
        String str = f1;
        LogUtil.verbose(str, " setMusicPlayState.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setMusicPlayStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setMusicPlayState.out = -1");
        return -1;
    }

    public int setNameNextRoad(String str) {
        String str2 = f1;
        LogUtil.optionError(str2, " setNameNextRoad.in" + str);
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                int length = bArrV.length;
                byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, (length / 7) + 1, 7);
                byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, (length / 7) + 1, 6);
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 == 0 ? 6 : 7;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (i < length) {
                            bArr[i2][i4] = bArrV[i];
                            i++;
                        } else {
                            bArr[i2][i4] = -1;
                        }
                    }
                    i2++;
                }
                LogUtil.optionError(str2, " length.in" + length);
                for (int i5 = 0; i5 < bArr.length; i5++) {
                    for (int i6 = 0; i6 < 6; i6++) {
                        LogUtil.optionError(str2, " newBytes.in" + ((int) bArr[i5][i6]) + " ");
                        bArr2[0][i6] = bArr[0][i6];
                    }
                    switch (i5) {
                        case 0:
                            this.c.setNewNameNextLenAndRoadS(new long[]{length, l(bArr2[i5])});
                            for (int i7 = 0; i7 < 6; i7++) {
                                LogUtil.optionError(str2, " setNewNameNextLenAndRoadS.in" + ((int) bArr2[i5][i7]) + " ");
                            }
                            break;
                        case 1:
                            this.c.setNewNameNextRoadNameSa2(bArr[i5]);
                            for (int i8 = 0; i8 < bArr[i5].length; i8++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa2.in" + ((int) bArr[i5][i8]) + " ");
                            }
                            break;
                        case 2:
                            this.c.setNewNameNextRoadNameSa3(bArr[i5]);
                            for (int i9 = 0; i9 < bArr[i5].length; i9++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa3.in" + ((int) bArr[i5][i9]) + " ");
                            }
                            break;
                        case 3:
                            this.c.setNewNameNextRoadNameSa4(bArr[i5]);
                            for (int i10 = 0; i10 < bArr[i5].length; i10++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa4.in" + ((int) bArr[i5][i10]) + " ");
                            }
                            break;
                        case 4:
                            this.c.setNewNameNextRoadNameSa5(bArr[i5]);
                            for (int i11 = 0; i11 < bArr[i5].length; i11++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa5.in" + ((int) bArr[i5][i11]) + " ");
                            }
                            break;
                        case 5:
                            this.c.setNewNameNextRoadNameSa6(bArr[i5]);
                            for (int i12 = 0; i12 < bArr[i5].length; i12++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa6.in" + ((int) bArr[i5][i12]) + " ");
                            }
                            break;
                        case 6:
                            this.c.setNewNameNextRoadNameSa7(bArr[i5]);
                            for (int i13 = 0; i13 < bArr[i5].length; i13++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa7.in" + ((int) bArr[i5][i13]) + " ");
                            }
                            break;
                        case 7:
                            this.c.setNewNameNextRoadNameSa8(bArr[i5]);
                            for (int i14 = 0; i14 < bArr[i5].length; i14++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa8.in" + ((int) bArr[i5][i14]) + " ");
                            }
                            break;
                        case 8:
                            this.c.setNewNameNextRoadNameSa9(bArr[i5]);
                            for (int i15 = 0; i15 < bArr[i5].length; i15++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSa9.in" + ((int) bArr[i5][i15]) + " ");
                            }
                            break;
                        case 9:
                            this.c.setNewNameNextRoadNameSaA(bArr[i5]);
                            for (int i16 = 0; i16 < bArr[i5].length; i16++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSaA.in" + ((int) bArr[i5][i16]) + " ");
                            }
                            break;
                        case 10:
                            this.c.setNewNameNextRoadNameSaB(bArr[i5]);
                            for (int i17 = 0; i17 < bArr[i5].length; i17++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSaB.in" + ((int) bArr[i5][i17]) + " ");
                            }
                            break;
                        case 11:
                            this.c.setNewNameNextRoadNameSaC(bArr[i5]);
                            for (int i18 = 0; i18 < bArr[i5].length; i18++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSaC.in" + ((int) bArr[i5][i18]) + " ");
                            }
                            break;
                        case 12:
                            this.c.setNewNameNextRoadNameSaD(bArr[i5]);
                            for (int i19 = 0; i19 < bArr[i5].length; i19++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSaD.in" + ((int) bArr[i5][i19]) + " ");
                            }
                            break;
                        case 13:
                            this.c.setNewNameNextRoadNameSaE(bArr[i5]);
                            for (int i20 = 0; i20 < bArr[i5].length; i20++) {
                                LogUtil.optionError(str2, " setNewNameNextRoadNameSaE.in" + ((int) bArr[i5][i20]) + " ");
                            }
                            break;
                    }
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setNavGuideInfo(int i) {
        LogUtil.optionError(f1, " setNavGuideInfo.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setNavGuideInfoS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setNavGuideInfoAdvanced(int i) {
        LogUtil.optionError(f1, " setNavGuideInfoAdvanced.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager == null) {
            return -1;
        }
        try {
            carVendorWhudManager.setNavGuideInfoAdvancedS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setNaviState(int i) {
        int i2;
        String str = f1;
        LogUtil.optionError(str, " setNaviState.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setNaviStateS(i);
                i2 = 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i2 = -1;
            }
        } else {
            i2 = -1;
        }
        LogUtil.optionError(str, " setNaviState.out = " + i);
        return i2;
    }

    public int setNaviType(int i) {
        String str = f1;
        LogUtil.optionError(str, " setNaviType.in" + i);
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setNaviTypeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setNaviType.out = -1");
        return -1;
    }

    public int setNextRoadDistanceUnitDataLen(String str) {
        String str2 = f1;
        LogUtil.verbose(str2, " setNextRoadDistanceUnitDataLen.in" + str);
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                LogUtil.optionError(str2, " setNextRoadDistanceUnitDataLen.bytes" + Arrays.toString(bArrV));
                int length = 52;
                if (bArrV.length < 52) {
                    length = bArrV.length;
                }
                int i = ((length + 2) / 7) + 1;
                byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i, 7);
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < i) {
                    for (int i5 = i2 == 0 ? 2 : 0; i5 < 7; i5++) {
                        if (i3 < length) {
                            bArr[i2][i5] = bArrV[i3];
                            i4 += bArr[i2][i5];
                            i3++;
                        } else if (i5 < 7) {
                            bArr[i2][i5] = -1;
                        }
                    }
                    i2++;
                }
                LogUtil.optionError(str2, " check = " + i4);
                LogUtil.optionError(str2, " length.in" + length);
                this.c.setNextRoadDistanceUnitDataLenS(new long[]{(long) length, (long) ((byte) (i4 ^ 255))});
                this.c.setNextRoadDistanceUnitData1S(new byte[]{bArr[0][2], bArr[0][3], bArr[0][4], bArr[0][5], bArr[0][6]});
                for (int i6 = 1; i6 < bArr.length; i6++) {
                    switch (i6) {
                        case 1:
                            this.c.setNextRoadDistanceUnitData2S(bArr[i6]);
                            for (int i7 = 0; i7 < bArr[i6].length; i7++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData2S.in" + ((int) bArr[i6][i7]) + " ");
                            }
                            break;
                        case 2:
                            this.c.setNextRoadDistanceUnitData3S(bArr[i6]);
                            for (int i8 = 0; i8 < bArr[i6].length; i8++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData3S.in" + ((int) bArr[i6][i8]) + " ");
                            }
                            break;
                        case 3:
                            this.c.setNextRoadDistanceUnitData4S(bArr[i6]);
                            for (int i9 = 0; i9 < bArr[i6].length; i9++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData4S.in" + ((int) bArr[i6][i9]) + " ");
                            }
                            break;
                        case 4:
                            this.c.setNextRoadDistanceUnitData5S(bArr[i6]);
                            for (int i10 = 0; i10 < bArr[i6].length; i10++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData5S.in" + ((int) bArr[i6][i10]) + " ");
                            }
                            break;
                        case 5:
                            this.c.setNextRoadDistanceUnitData6S(bArr[i6]);
                            for (int i11 = 0; i11 < bArr[i6].length; i11++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData6S.in" + ((int) bArr[i6][i11]) + " ");
                            }
                            break;
                        case 6:
                            this.c.setNextRoadDistanceUnitData7S(bArr[i6]);
                            for (int i12 = 0; i12 < bArr[i6].length; i12++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData7S.in" + ((int) bArr[i6][i12]) + " ");
                            }
                            break;
                        case 7:
                            this.c.setNextRoadDistanceUnitData8S(bArr[i6]);
                            for (int i13 = 0; i13 < bArr[i6].length; i13++) {
                                LogUtil.optionError(str2, " setNextRoadDistanceUnitData8S.in" + ((int) bArr[i6][i13]) + " ");
                            }
                            break;
                    }
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setNextRoadDistanceUnitDataLenV2(String str, String str2) {
        return 1;
    }

    public void setOilConsumptionUnit(int i) {
    }

    public int setOverseasMapSupplier(int i) {
        String str = f1;
        LogUtil.optionError(str, " setOverseasMapSupplier.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setOverseasMapSupplierS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setOverseasMapSupplier.out = -1");
        return -1;
    }

    public void setPhoneState(int i) {
    }

    public void setPowerMessagesCanScpwkeysystemawakeMsg(int i) {
    }

    public void setPowerOtaDelaySystemIdle(int i) {
    }

    public void setPowerStateReport(int i) {
    }

    public void setPressureUnit(int i) {
    }

    public int setRadioPlayState(int i) {
        String str = f1;
        LogUtil.optionError(str, " setRadioPlayState.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setRadioPlayStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setRadioPlayState.out = -1");
        return -1;
    }

    public void setRearBeltReminder(boolean z) {
    }

    public void setRestoreDefaultSettings(boolean z) {
        LogUtil.verbose(f1, "setRestoreDefaultSettings:" + z);
        u(z);
    }

    public int setRoadAccidentS(int i) {
        LogUtil.info(f1, "setRoadAccidentS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setRoadAccidentS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRoadCongestedS(int i) {
        LogUtil.info(f1, "setRoadCongestedS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setRoadCongestedS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRoadConstructionS(int i) {
        LogUtil.info(f1, "setRoadConstructionS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setRoadConstructionS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRoadImpassableS(int i) {
        LogUtil.info(f1, "setRoadImpassableS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setRoadImpassableS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setScreenDisplayState(int i) {
        String str = f1;
        LogUtil.optionError(str, " setScreenDisplayState.in");
        CarVendorWhudManager carVendorWhudManager = this.c;
        if (carVendorWhudManager != null) {
            try {
                carVendorWhudManager.setScreenDisplayStateS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.optionError(str, " setScreenDisplayState.out = -1");
        return -1;
    }

    public void setSecondaryScreenBrightness(int i) {
    }

    public void setSettingsMcuTime(long j) {
    }

    public void setSettingsMcuTimeSyncReq(int i) {
    }

    public void setSettingsMcuTimeZone(String str) {
    }

    public void setSoundAncSwitch(int i) {
    }

    public void setSoundEffect(int i) {
    }

    public void setSoundEssSwitch(int i) {
    }

    public void setSoundWarningPedestrians(int i) {
    }

    public void setStartBcall(int i) {
    }

    public int setTargetDistanceUnitDataLen(String str) {
        String str2 = f1;
        LogUtil.verbose(str2, " setCurrentMinUnitLen.in" + str);
        if (this.c != null) {
            try {
                byte[] bArrV = v(str);
                LogUtil.optionError(str2, " setCurrentMinUnitLen.bytes" + Arrays.toString(bArrV));
                int length = 52;
                if (bArrV.length < 52) {
                    length = bArrV.length;
                }
                int i = ((length + 2) / 7) + 1;
                byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i, 7);
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < i) {
                    for (int i5 = i2 == 0 ? 2 : 0; i5 < 7; i5++) {
                        if (i3 < length) {
                            bArr[i2][i5] = bArrV[i3];
                            i4 += bArr[i2][i5];
                            i3++;
                        } else if (i5 < 7) {
                            bArr[i2][i5] = -1;
                        }
                    }
                    i2++;
                }
                LogUtil.optionError(str2, " check = " + i4);
                LogUtil.optionError(str2, " length.in" + length);
                this.c.setCurrentMinUnitLenS(new long[]{(long) length, (long) ((byte) (i4 ^ 255))});
                this.c.setCurrentMinUnit1S(new byte[]{bArr[0][2], bArr[0][3], bArr[0][4], bArr[0][5], bArr[0][6]});
                for (int i6 = 1; i6 < bArr.length; i6++) {
                    switch (i6) {
                        case 1:
                            this.c.setCurrentMinUnit2S(bArr[i6]);
                            for (int i7 = 0; i7 < bArr[i6].length; i7++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit1S.in" + ((int) bArr[i6][i7]) + " ");
                            }
                            break;
                        case 2:
                            this.c.setCurrentMinUnit3S(bArr[i6]);
                            for (int i8 = 0; i8 < bArr[i6].length; i8++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit2S.in" + ((int) bArr[i6][i8]) + " ");
                            }
                            break;
                        case 3:
                            this.c.setCurrentMinUnit4S(bArr[i6]);
                            for (int i9 = 0; i9 < bArr[i6].length; i9++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit3S.in" + ((int) bArr[i6][i9]) + " ");
                            }
                            break;
                        case 4:
                            this.c.setCurrentMinUnit5S(bArr[i6]);
                            for (int i10 = 0; i10 < bArr[i6].length; i10++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit4S.in" + ((int) bArr[i6][i10]) + " ");
                            }
                            break;
                        case 5:
                            this.c.setCurrentMinUnit6S(bArr[i6]);
                            for (int i11 = 0; i11 < bArr[i6].length; i11++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit5S.in" + ((int) bArr[i6][i11]) + " ");
                            }
                            break;
                        case 6:
                            this.c.setCurrentMinUnit7S(bArr[i6]);
                            for (int i12 = 0; i12 < bArr[i6].length; i12++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit6S.in" + ((int) bArr[i6][i12]) + " ");
                            }
                            break;
                        case 7:
                            this.c.setCurrentMinUnit8S(bArr[i6]);
                            for (int i13 = 0; i13 < bArr[i6].length; i13++) {
                                LogUtil.optionError(str2, " setCurrentMinUnit7S.in" + ((int) bArr[i6][i13]) + " ");
                            }
                            break;
                    }
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setTboxResetAuthKey(int i) {
    }

    public void setTboxResetAuthRand(int i) {
    }

    public void setTboxResetReq(int i) {
    }

    public void setTemperatureUnit(int i) {
    }

    @Deprecated
    public void setTimeSyncDay(int i) {
    }

    @Deprecated
    public void setTimeSyncHour(int i) {
    }

    @Deprecated
    public void setTimeSyncMinute(int i) {
    }

    @Deprecated
    public void setTimeSyncMonth(int i) {
    }

    @Deprecated
    public void setTimeSyncSecond(int i) {
    }

    @Deprecated
    public void setTimeSyncYear(int i) {
    }

    public int setTrafficSignTypeS(int i) {
        LogUtil.info(f1, "setTrafficSignTypeS" + i);
        CarVendorStaticManager carVendorStaticManager = this.f72b;
        if (carVendorStaticManager == null) {
            return 0;
        }
        try {
            carVendorStaticManager.setTrafficSignTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean subscribeCarGeneralCallback(ICarGeneralCallback iCarGeneralCallback) {
        com.ts.appservice.caradapter.a.e eVar = this.n;
        if (eVar == null || iCarGeneralCallback == null) {
            return true;
        }
        eVar.h(iCarGeneralCallback);
        if (!this.q) {
            return true;
        }
        this.n.m();
        return true;
    }

    public void t(CarVendorSafetybeltManager carVendorSafetybeltManager, CarVendorStaticManager carVendorStaticManager, CarVendorWhudManager carVendorWhudManager, CarVendorAmbientManager carVendorAmbientManager) {
        this.f71a = carVendorSafetybeltManager;
        this.f72b = carVendorStaticManager;
        this.c = carVendorWhudManager;
        this.d = carVendorAmbientManager;
    }

    public boolean unsubscribeCarGeneralCallback(ICarGeneralCallback iCarGeneralCallback) {
        com.ts.appservice.caradapter.a.e eVar = this.n;
        if (eVar == null) {
            return true;
        }
        eVar.i(iCarGeneralCallback);
        return true;
    }
}
