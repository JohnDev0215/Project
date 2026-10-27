package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.appservice.caradapter.d.n;
import com.ts.appservice.caradapter.d.s;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager;
import com.ts.lib.car.hardware.doorlock.DoorlockEventChangeValue;
import com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager;
import com.ts.lib.car.hardware.gearbox.GearboxEventChangeValue;
import com.ts.lib.car.hardware.health.CarVendorHealthManager;
import com.ts.lib.car.hardware.health.HealthEventChangeValue;
import com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager;
import com.ts.lib.car.hardware.instrument.InstrumentEventChangeValue;
import com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager;
import com.ts.lib.car.hardware.rearviewmirror.RearviewmirrorEventChangeValue;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.ICabinService;
import com.ts.lib.caradapter.cabin.ICabinStateCallback;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;

/* JADX INFO: compiled from: CabinServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ICabinService.Stub implements com.ts.appservice.caradapter.d.l, com.ts.appservice.caradapter.d.g, n, s, com.ts.appservice.caradapter.d.k {
    private static final String w0 = d.class.getSimpleName();
    private int A;
    private int B;
    private int C;
    private boolean F;
    private int G;
    private boolean H;
    private int I;
    private boolean J;
    private boolean K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private a X;
    private int Y;
    private int Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CarVendorInstrumentManager f25a;
    private int a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CarVendorDoorlockManager f26b;
    private int b0;
    private CarVendorRearviewmirrorManager c;
    private int c0;
    private CarVendorHealthManager d;
    private int d0;
    private CarVendorGearboxManager e;
    private int e0;
    private com.ts.appservice.caradapter.a.c f;
    private int f0;
    private int g0;
    private int h0;
    private int i0;
    private int j0;
    private int k0;
    private int l0;
    private int m0;
    private int n0;
    private int o0;
    private int p0;
    private int q0;
    private int r0;
    private int s0;
    private int t;
    private int t0;
    private int u;
    private int u0;
    private int v;
    private int v0;
    private int w;
    private int x;
    private int y;
    private int z;
    private CabinPacket g = new CabinPacket();
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private int s = 0;
    private int D = 0;
    private int E = 0;
    private final HandlerThread W = com.ts.appservice.caradapter.e.b.a("VEHICLE-CABIN");

    /* JADX INFO: compiled from: CabinServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 4) {
                d.this.w(message.arg1, message.arg2, (HealthEventChangeValue) message.obj);
                return;
            }
            if (i == 5) {
                d.this.u(message.arg1, message.arg2, (DoorlockEventChangeValue) message.obj);
                return;
            }
            if (i == 6) {
                d.this.x(message.arg1, message.arg2, (InstrumentEventChangeValue) message.obj);
            } else if (i == 11) {
                d.this.y(message.arg1, message.arg2, (RearviewmirrorEventChangeValue) message.obj);
            } else {
                if (i != 12) {
                    return;
                }
                d.this.v(message.arg1, message.arg2, (GearboxEventChangeValue) message.obj);
            }
        }
    }

    public d(Context context) {
        this.f = new com.ts.appservice.caradapter.a.c(context);
    }

    public static byte[] B(String str) {
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

    public static long r(byte[] bArr) {
        String str = w0;
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
    public void u(int i, int i2, DoorlockEventChangeValue doorlockEventChangeValue) {
        String str = w0;
        LogUtil.verbose(str, "notifyHealthEventValue:" + doorlockEventChangeValue);
        try {
            switch (i) {
                case 557856512:
                    this.g.setDoorLockStatusAreaChildlockLeftS(doorlockEventChangeValue.getDoorLockStatusAreaChildLockLeftR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_CHILDLOCK_LEFT:" + doorlockEventChangeValue.getDoorLockStatusAreaChildLockLeftR());
                    break;
                case 557856513:
                    this.g.setDoorLockStatusAreaBack(doorlockEventChangeValue.getDoorLockStatusAreaBack());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_BACK:" + doorlockEventChangeValue.getDoorLockStatusAreaBack());
                    break;
                case 557856514:
                    this.g.setDoorLockStatusAreaLeftFront(doorlockEventChangeValue.getDoorLockStatusAreaLeftFront());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_LEFT_FRONT:" + doorlockEventChangeValue.getDoorLockStatusAreaLeftFront());
                    break;
                case 557856515:
                    this.g.setDoorLockStatusRightFront(doorlockEventChangeValue.getDoorLockStatusRightFront());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_RIGHT_FRONT:" + doorlockEventChangeValue.getDoorLockStatusRightFront());
                    break;
                case 557856516:
                    this.g.setDoorLockStatusLeftRear(doorlockEventChangeValue.getDoorLockStatusLeftRear());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_LEFT_REAR:" + doorlockEventChangeValue.getDoorLockStatusLeftRear());
                    break;
                case 557856517:
                    this.g.setDoorLockStatusAreaRightRear(doorlockEventChangeValue.getDoorLockStatusAreaRightRear());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_RIGHT_REAR:" + doorlockEventChangeValue.getDoorLockStatusAreaRightRear());
                    break;
                case 557856519:
                    this.g.setAllDoorsLock(doorlockEventChangeValue.getAllDoorsLock());
                    LogUtil.verbose(str, "ID_ALL_DOORS_LOCK:" + doorlockEventChangeValue.getAllDoorsLock());
                    break;
                case 557856520:
                    this.g.setDoorLockStatusAreaChildlockRightStatusS(doorlockEventChangeValue.getDoorLockStatusAreaChildlockRightStatusS());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_CHILDLOCK_RIGHT_STATUSS:" + doorlockEventChangeValue.getDoorLockStatusAreaChildlockRightStatusS());
                    break;
                case 557856521:
                    this.g.setDoorStatusAreaLeftFrontStatusR(doorlockEventChangeValue.getDoorStatusAreaLeftFrontStatusR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_LEFT_FRONT_STATUSR:" + doorlockEventChangeValue.getDoorStatusAreaLeftFrontStatusR());
                    break;
                case 557856522:
                    this.g.setDoorStatusAreaRightFrontStatusR(doorlockEventChangeValue.getDoorStatusAreaRightFrontStatusR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_RIGHT_FRONT_STATUSR:" + doorlockEventChangeValue.getDoorStatusAreaRightFrontStatusR());
                    break;
                case 557856523:
                    this.g.setDoorStatusAreaLeftRearStatusR(doorlockEventChangeValue.getDoorStatusAreaLeftRearStatusR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_LEFT_REAR_STATUSR:" + doorlockEventChangeValue.getDoorStatusAreaLeftRearStatusR());
                    break;
                case 557856524:
                    this.g.setDoorStatusAreaRightRearStatusR(doorlockEventChangeValue.getDoorStatusAreaRightRearStatusR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_RIGHT_REAR_STATUSR:" + doorlockEventChangeValue.getDoorStatusAreaRightRearStatusR());
                    break;
                case 557856525:
                    this.g.setDoorStatusAreaBackStatusR(doorlockEventChangeValue.getDoorStatusAreaBackStatusR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_BACK_STATUSR:" + doorlockEventChangeValue.getDoorStatusAreaBackStatusR());
                    break;
                case 557856526:
                    this.g.setDoorLockStatusAreaChildLockRight(doorlockEventChangeValue.getDoorLockStatusAreaChildLockRight());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_CHILDLOCK_RIGHT:" + doorlockEventChangeValue.getDoorLockStatusAreaChildLockRight());
                    break;
                case 557856528:
                    this.g.setLockHeatProtectedStateR(doorlockEventChangeValue.getLockHeatProtectedStateR());
                    LogUtil.verbose(str, "ID_LOCK_HEAT_PROTECTED_STATE_R:" + doorlockEventChangeValue.getLockHeatProtectedStateR());
                    break;
                case 557856531:
                    this.g.setDoorLockCockpitHatchStateR(doorlockEventChangeValue.getDoorLockCockpitHatchStateR());
                    LogUtil.verbose(str, "ID_DOOR_LOCK_COCKPIT_HATCH_STATE_R:" + doorlockEventChangeValue.getDoorLockCockpitHatchStateR());
                    break;
            }
            this.f.m(i, i2, this.g);
            LogUtil.verbose(str, "notifyDoorLockEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(int i, int i2, GearboxEventChangeValue gearboxEventChangeValue) {
        String str = w0;
        int gearR = 0;
        try {
            if (i == 557857290) {
                gearR = gearboxEventChangeValue.getGearR();
                this.g.setGearR(gearR);
                LogUtil.verbose(str, "ID_GEAR_R:" + gearR);
            } else if (i == 557857293) {
                gearR = gearboxEventChangeValue.getBrakePedalStatusR();
                this.g.setBrakePedalStatusR(gearR);
                LogUtil.verbose(str, "ID_BRAKE_PEDAL_STATUS_R:" + gearR);
            } else if (i != 557860357) {
                switch (i) {
                    case 557857283:
                        gearR = gearboxEventChangeValue.getBrakeFluidLevel();
                        this.g.setBrakeFluidLevel(gearR);
                        LogUtil.verbose(str, "ID_SPEED_UNITR:" + gearR);
                        break;
                    case 557857284:
                        gearR = gearboxEventChangeValue.getParkBrakeSwitch();
                        this.g.setParkBrakeSwitch(gearR);
                        LogUtil.verbose(str, "ID_SPEED_UNITR:" + gearR);
                        break;
                    case 557857285:
                        gearR = gearboxEventChangeValue.getBrakePedalState();
                        this.g.setBrakePedalState(gearR);
                        LogUtil.verbose(str, "ID_SPEED_UNITR:" + gearR);
                        break;
                    case 557857286:
                        gearR = gearboxEventChangeValue.getGearboxAutoModeTypeTwo();
                        this.g.setGearboxAutoModeTypeTwo(gearR);
                        LogUtil.verbose(str, "ID_GEAR_BOX_AUTO_MODE_TYPE_TWO:" + gearR);
                        break;
                    case 557857287:
                        gearR = gearboxEventChangeValue.getGearboxManualModeLevelR();
                        this.g.setGearboxManualModeLevelR(gearR);
                        LogUtil.verbose(str, "ID_GEARBOX_MANUAL_MODE_LEVELR:" + gearR);
                        break;
                    case 557857288:
                        gearR = gearboxEventChangeValue.getEpbStateR();
                        this.g.setEpbStateR(gearR);
                        LogUtil.verbose(str, "ID_E_P_B_STATE_R:" + gearR);
                        break;
                }
            } else {
                gearR = gearboxEventChangeValue.getGearboxAutoModeType();
                this.g.setGearboxAutoModeType(gearR);
                LogUtil.verbose(str, "ID_GEAR_BOX_AUTO_MODE_TYPE:" + gearR);
            }
            this.f.m(i, i2, this.g);
            LogUtil.verbose(str, "notifyGearBoxtEventChangeValue:" + i + " result=" + gearR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i, int i2, HealthEventChangeValue healthEventChangeValue) {
        String str = w0;
        try {
            switch (i) {
                case 557850624:
                    int oilHint = healthEventChangeValue.getOilHint();
                    this.g.setOilHint(oilHint);
                    LogUtil.verbose(str, "ID_OIL_HINT:" + oilHint);
                    break;
                case 557850625:
                    int oilLifetimeEngine = healthEventChangeValue.getOilLifetimeEngine();
                    this.g.setOilLifetimeEngine(oilLifetimeEngine);
                    LogUtil.verbose(str, "ID_OIL_LIFE_TIME_ENGINE:" + oilLifetimeEngine);
                    break;
                case 557850626:
                    int oilLifetimeTransmission = healthEventChangeValue.getOilLifetimeTransmission();
                    this.g.setOilLifetimeTransmission(oilLifetimeTransmission);
                    LogUtil.verbose(str, "ID_OIL_LIFE_TIME_TRANSMISSION:" + oilLifetimeTransmission);
                    break;
                case 557850627:
                    int oilLifetimeBrake = healthEventChangeValue.getOilLifetimeBrake();
                    this.g.setOilLifetimeBrake(oilLifetimeBrake);
                    LogUtil.verbose(str, "ID_OIL_LIFE_TIME_BRAKE:" + oilLifetimeBrake);
                    break;
                case 557850628:
                    int oilLifetimeBattery = healthEventChangeValue.getOilLifetimeBattery();
                    this.g.setOilLifetimeBattery(oilLifetimeBattery);
                    LogUtil.verbose(str, "ID_OIL_LIFE_TIME_BATTERY:" + oilLifetimeBattery);
                    break;
                case 557850629:
                    int oilLifetimeElec = healthEventChangeValue.getOilLifetimeElec();
                    this.g.setOilLifetimeElec(oilLifetimeElec);
                    LogUtil.verbose(str, "ID_OIL_LIFE_TIME_ELEC:" + oilLifetimeElec);
                    break;
                case 557850630:
                    int i3 = healthEventChangeValue.getresetOilLifetimeEngine();
                    this.g.setresetOilLifetimeEngine(i3);
                    LogUtil.verbose(str, "ID_RESET_OIL_LEFE_TIME_ENGINE:" + i3);
                    break;
                case 557850631:
                    int i4 = healthEventChangeValue.getresetOilLifetimeTransmission();
                    this.g.setresetOilLifetimeTransmission(i4);
                    LogUtil.verbose(str, "ID_RESET_OIL_LEFE_TIME_TRANSMISSION:" + i4);
                    break;
                case 557850632:
                    int i5 = healthEventChangeValue.getresetOilLifetimeBrake();
                    this.g.setresetOilLifetimeBrake(i5);
                    LogUtil.verbose(str, "ID_RESET_OIL_LEFE_TIME_BRAKE:" + i5);
                    break;
                case 557850633:
                    int i6 = healthEventChangeValue.getresetOilLifetimeBattery();
                    this.g.setresetOilLifetimeBattery(i6);
                    LogUtil.verbose(str, "ID_RESET_OIL_LEFE_TIME_BATTERY:" + i6);
                    break;
                case 557850634:
                    int i7 = healthEventChangeValue.getresetOilLifetimeELec();
                    this.g.setresetOilLifetimeELec(i7);
                    LogUtil.verbose(str, "ID_RESET_OIL_LEFE_TIME_ELEC:" + i7);
                    break;
                case 557850635:
                    int oilLevel = healthEventChangeValue.getOilLevel();
                    this.g.setOilLevel(oilLevel);
                    LogUtil.verbose(str, "ID_OIL_LEVEL:" + oilLevel);
                    break;
                case 557850636:
                    int frontWiperCheckStateR = healthEventChangeValue.getFrontWiperCheckStateR();
                    this.g.setFrontWiperCheckStateR(frontWiperCheckStateR);
                    LogUtil.verbose(str, "ID_FRONT_WIPER_CHECK_STATER:" + frontWiperCheckStateR);
                    break;
                case 557850637:
                    int frontWiperCheckStateS = healthEventChangeValue.getFrontWiperCheckStateS();
                    this.g.setFrontWiperCheckStateS(frontWiperCheckStateS);
                    LogUtil.verbose(str, "ID_FRONT_WIPER_CHECK_STATES:" + frontWiperCheckStateS);
                    break;
                case 557850638:
                    int rearWiperCheckStateR = healthEventChangeValue.getRearWiperCheckStateR();
                    this.g.setRearWiperCheckStateR(rearWiperCheckStateR);
                    LogUtil.verbose(str, "ID_REAR_WIPER_CHECK_STATER:" + rearWiperCheckStateR);
                    break;
                case 557850639:
                    int rearWiperCheckStateS = healthEventChangeValue.getRearWiperCheckStateS();
                    this.g.setRearWiperCheckStateS(rearWiperCheckStateS);
                    LogUtil.verbose(str, "ID_REAR_WIPER_CHECK_STATES:" + rearWiperCheckStateS);
                    break;
                case 557850640:
                    int i8 = healthEventChangeValue.getresetTirePressure();
                    this.g.setresetTirePressure(i8);
                    LogUtil.verbose(str, "ID_RESET_TIRE_PRESSURE:" + i8);
                    break;
                case 557850641:
                    int oilUpdateState = healthEventChangeValue.getOilUpdateState();
                    this.g.setOilUpdateState(oilUpdateState);
                    LogUtil.verbose(str, "ID_OILUPDATESTATE:" + oilUpdateState);
                    break;
                case 557850642:
                    int tyreFaultLocation = healthEventChangeValue.getTyreFaultLocation();
                    this.g.setTyreFaultLocation(tyreFaultLocation);
                    LogUtil.verbose(str, "ID_TYREFAULTLOCATION:" + tyreFaultLocation);
                    break;
                case 557850647:
                    int ePBStateR = healthEventChangeValue.getEPBStateR();
                    this.g.setEPBStateR(ePBStateR);
                    LogUtil.verbose(str, "ID_EPB_STATE_R:" + ePBStateR);
                    break;
                case 557850648:
                    int resetOilLifetimeChangedEngineR = healthEventChangeValue.getResetOilLifetimeChangedEngineR();
                    this.g.setResetOilLifetimeChangedEngineR(resetOilLifetimeChangedEngineR);
                    LogUtil.verbose(str, "ID_RESET_OIL_LIFETIME_CHANGED_ENGINER:" + resetOilLifetimeChangedEngineR);
                    break;
                case 557850649:
                    int resetOilLifetimeChangedTransMissionR = healthEventChangeValue.getResetOilLifetimeChangedTransMissionR();
                    this.g.setResetOilLifetimeChangedTransMissionR(resetOilLifetimeChangedTransMissionR);
                    LogUtil.verbose(str, "ID_RESET_OIL_LIFETIME_CHANGED_TRANS_MISSIONR:" + resetOilLifetimeChangedTransMissionR);
                    break;
                case 557850650:
                    int resetOilLifetimeChangedBreakR = healthEventChangeValue.getResetOilLifetimeChangedBreakR();
                    this.g.setResetOilLifetimeChangedBreakR(resetOilLifetimeChangedBreakR);
                    LogUtil.verbose(str, "ID_RESET_OIL_LIFETIME_CHANGED_BREAKR:" + resetOilLifetimeChangedBreakR);
                    break;
                case 557850651:
                    int resetOilLifetimeChangedBatteryR = healthEventChangeValue.getResetOilLifetimeChangedBatteryR();
                    this.g.setResetOilLifetimeChangedBatteryR(resetOilLifetimeChangedBatteryR);
                    LogUtil.verbose(str, "ID_RESET_OIL_LIFETIME_CHANGED_BATTERYR:" + resetOilLifetimeChangedBatteryR);
                    break;
                case 557850652:
                    int resetOilLifetimeChangedElecR = healthEventChangeValue.getResetOilLifetimeChangedElecR();
                    this.g.setResetOilLifetimeChangedElecR(resetOilLifetimeChangedElecR);
                    LogUtil.verbose(str, "ID_RESET_OIL_LIFETIME_CHANGED_ELECR:" + resetOilLifetimeChangedElecR);
                    break;
                case 557850653:
                    int oilMaintainanceHintOilLife = healthEventChangeValue.getOilMaintainanceHintOilLife();
                    this.g.setOilMaintainanceHintOilLife(oilMaintainanceHintOilLife);
                    LogUtil.verbose(str, "ID_OIL_MAINTAINANCE_HINT_OIL_LIFE:" + oilMaintainanceHintOilLife);
                    break;
                case 557850654:
                    int oilMaintainanceHintTransmissionOilLife = healthEventChangeValue.getOilMaintainanceHintTransmissionOilLife();
                    this.g.setOilMaintainanceHintTransmissionOilLife(oilMaintainanceHintTransmissionOilLife);
                    LogUtil.verbose(str, "ID_OIL_MAINTAINANCE_HINT_TRANSMISSION_OILLIFE:" + oilMaintainanceHintTransmissionOilLife);
                    break;
                case 557850655:
                    int oilMaintainanceHintBrakeFluidLife = healthEventChangeValue.getOilMaintainanceHintBrakeFluidLife();
                    this.g.setOilMaintainanceHintBrakeFluidLife(oilMaintainanceHintBrakeFluidLife);
                    LogUtil.verbose(str, "ID_OIL_MAINTAINANCE_HINT_BRAKE_FLUID_LIFE:" + oilMaintainanceHintBrakeFluidLife);
                    break;
                case 557850656:
                    int oilMaintainanceHintLongLifeCoolant = healthEventChangeValue.getOilMaintainanceHintLongLifeCoolant();
                    this.g.setOilMaintainanceHintLongLifeCoolant(oilMaintainanceHintLongLifeCoolant);
                    LogUtil.verbose(str, "ID_OIL_MAINTAINANCE_HINT_LONG_LIFE_COOLANT:" + oilMaintainanceHintLongLifeCoolant);
                    break;
                case 557850657:
                    int oilMaintainanceHintBladeBatteryCoolant = healthEventChangeValue.getOilMaintainanceHintBladeBatteryCoolant();
                    this.g.setOilMaintainanceHintBladeBatteryCoolant(oilMaintainanceHintBladeBatteryCoolant);
                    LogUtil.verbose(str, "ID_OIL_MAINTAINANCE_HINT_BLADE_BATTERY_COOLANT:" + oilMaintainanceHintBladeBatteryCoolant);
                    break;
                case 557850659:
                    int transportModeR = healthEventChangeValue.getTransportModeR();
                    this.g.setTransportModeR(transportModeR);
                    LogUtil.verbose(str, "ID_TRANSPORT_MODE_R:" + transportModeR);
                    break;
                case 557850660:
                    int transportModeStateHintR = healthEventChangeValue.getTransportModeStateHintR();
                    this.g.setTransportModeStateHintR(transportModeStateHintR);
                    LogUtil.verbose(str, "ID_TRANSPORT_MODE_STATE_HINT_R:" + transportModeStateHintR);
                    break;
                case 557850662:
                    int epbHintR = healthEventChangeValue.getEpbHintR();
                    this.g.setEpbHintR(epbHintR);
                    LogUtil.debug(str, "ID_EPB_HINT_R:" + epbHintR);
                    break;
                case 557850663:
                    int oilLifetimeNewOilLifeR = healthEventChangeValue.getOilLifetimeNewOilLifeR();
                    this.g.setOilLifetimeNewOilLifeR(oilLifetimeNewOilLifeR);
                    LogUtil.debug(str, "ID_OIL_LIFETIME_NEW_OIL_LIFE_R:" + oilLifetimeNewOilLifeR);
                    break;
                case 557850664:
                    int oilLifetimeNewTransmissionR = healthEventChangeValue.getOilLifetimeNewTransmissionR();
                    this.g.setOilLifetimeNewTransmissionR(oilLifetimeNewTransmissionR);
                    LogUtil.debug(str, "ID_OIL_LIFETIME_NEW_TRANSMISSION_R:" + oilLifetimeNewTransmissionR);
                    break;
                case 557850665:
                    int oilLifetimeNewBrakeFluidLifeBrakeFluidLifeR = healthEventChangeValue.getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR();
                    this.g.setOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR(oilLifetimeNewBrakeFluidLifeBrakeFluidLifeR);
                    LogUtil.debug(str, "getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR:" + oilLifetimeNewBrakeFluidLifeBrakeFluidLifeR);
                    break;
                case 557850666:
                    int oilLifetimeNewBladeBatteryCoolantR = healthEventChangeValue.getOilLifetimeNewBladeBatteryCoolantR();
                    this.g.setOilLifetimeNewBladeBatteryCoolantR(oilLifetimeNewBladeBatteryCoolantR);
                    LogUtil.debug(str, "ID_OIL_LIFETIME_NEW_BLADE_BATTERY_COOLANT_R:" + oilLifetimeNewBladeBatteryCoolantR);
                    break;
                case 557850667:
                    int oilLifetimeNewLongLifeCoolantLongLifeCoolantR = healthEventChangeValue.getOilLifetimeNewLongLifeCoolantLongLifeCoolantR();
                    this.g.setOilLifetimeNewLongLifeCoolantLongLifeCoolantR(oilLifetimeNewLongLifeCoolantLongLifeCoolantR);
                    LogUtil.debug(str, "OilLifetimeNewLongLifeCoolantLongLifeCoolantR:" + oilLifetimeNewLongLifeCoolantLongLifeCoolantR);
                    break;
                case 557850668:
                    int oilMaintainanceDialogHintOilLifeR = healthEventChangeValue.getOilMaintainanceDialogHintOilLifeR();
                    this.g.setOilMaintainanceDialogHintOilLifeR(oilMaintainanceDialogHintOilLifeR);
                    LogUtil.debug(str, "ID_OIL_MAINTENANCE_DIALOG_HINT_OIL_LIFE_R:" + oilMaintainanceDialogHintOilLifeR);
                    break;
                case 557850669:
                    int oilMaintainanceDialogHintTransmissionR = healthEventChangeValue.getOilMaintainanceDialogHintTransmissionR();
                    this.g.setOilMaintainanceDialogHintTransmissionR(oilMaintainanceDialogHintTransmissionR);
                    LogUtil.debug(str, "ID_OIL_MAINTENANCE_DIALOG_HINT_TRANSMISSION_R:" + oilMaintainanceDialogHintTransmissionR);
                    break;
                case 557850671:
                    int oilMaintainanceDialogHintBladeBatteryCoolantR = healthEventChangeValue.getOilMaintainanceDialogHintBladeBatteryCoolantR();
                    this.g.setOilMaintainanceDialogHintBladeBatteryCoolantR(oilMaintainanceDialogHintBladeBatteryCoolantR);
                    LogUtil.debug(str, "OilMaintainanceDialogHintBladeBatteryCoolantR:" + oilMaintainanceDialogHintBladeBatteryCoolantR);
                    break;
                case 557850672:
                    int oilMaintainanceDialogHintLongLifeCoolantR = healthEventChangeValue.getOilMaintainanceDialogHintLongLifeCoolantR();
                    this.g.setOilMaintainanceDialogHintLongLifeCoolantR(oilMaintainanceDialogHintLongLifeCoolantR);
                    LogUtil.debug(str, "getOilMaintainanceDialogHintLongLifeCoolantR:" + oilMaintainanceDialogHintLongLifeCoolantR);
                    break;
                case 557850673:
                    int oilMaintainanceDialogHintBrakeFluidLifeR = healthEventChangeValue.getOilMaintainanceDialogHintBrakeFluidLifeR();
                    this.g.setOilMaintainanceDialogHintBrakeFluidLifeR(oilMaintainanceDialogHintBrakeFluidLifeR);
                    LogUtil.debug(str, "getOilMaintainanceDialogHintBrakeFluidLifeR:" + oilMaintainanceDialogHintBrakeFluidLifeR);
                    break;
                case 557850679:
                    int batteryHealthStatusR = healthEventChangeValue.getBatteryHealthStatusR();
                    this.g.setBatteryHealthStatusR(batteryHealthStatusR);
                    LogUtil.debug(str, "ID_BATTERY_HEALTH_STATUS_R:" + batteryHealthStatusR);
                    break;
            }
            this.f.m(i, i2, this.g);
            LogUtil.verbose(str, "notifyHealthEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i, int i2, InstrumentEventChangeValue instrumentEventChangeValue) {
        String str = w0;
        try {
            switch (i) {
                case 557857026:
                    int instrumentBrightnessLeftR = instrumentEventChangeValue.getInstrumentBrightnessLeftR();
                    this.g.setInstrumentBrightnessLeft(instrumentBrightnessLeftR);
                    LogUtil.verbose(str, "ID_INSTRUMENT_BRIGHTNESS_LEFT_R:" + instrumentBrightnessLeftR);
                    break;
                case 557857027:
                    int instrumentBrightnessR = instrumentEventChangeValue.getInstrumentBrightnessR();
                    this.g.setInstrumentBrightnessR(instrumentBrightnessR);
                    LogUtil.verbose(str, "ID_INSTRUMENT_BRIGHTNESS_R:" + instrumentBrightnessR);
                    break;
                default:
                    switch (i) {
                        case 557857029:
                            int instrumentAutoDimmStateR = instrumentEventChangeValue.getInstrumentAutoDimmStateR();
                            this.g.setInstrumentAutoDimmStateR(instrumentAutoDimmStateR);
                            LogUtil.verbose(str, "ID_INSTRUMENT_AUTO_DIMM_STATE_R:" + instrumentAutoDimmStateR);
                            break;
                        case 557857032:
                            int milesTypeR = instrumentEventChangeValue.getMilesTypeR();
                            this.g.setMilesTypeR(milesTypeR);
                            LogUtil.verbose(str, "ID_MILES_TYPE_R:" + milesTypeR);
                            break;
                        case 557857039:
                            int maintenanceInfoOneR = instrumentEventChangeValue.getMaintenanceInfoOneR();
                            this.g.setMaintenanceInfoThreeR(instrumentEventChangeValue.getMaintenanceInfoThreeR());
                            this.g.setMaintenanceInfoOneR(maintenanceInfoOneR);
                            LogUtil.verbose(str, "ID_MAINTENANCE_INFO_ONER:" + maintenanceInfoOneR);
                            break;
                        case 557857047:
                            int unitPressureLeftR = instrumentEventChangeValue.getUnitPressureLeftR();
                            this.g.setUnitPressureLeftR(unitPressureLeftR);
                            LogUtil.verbose(str, "ID_UNIT_PRESSURE_LEFT_R:" + unitPressureLeftR);
                            break;
                        case 557857049:
                            int unitFuelConsumptionAndDistanceLeftR = instrumentEventChangeValue.getUnitFuelConsumptionAndDistanceLeftR();
                            this.g.setUnitFuelConsumptionAndDistanceLeftR(unitFuelConsumptionAndDistanceLeftR);
                            LogUtil.verbose(str, "ID_UNIT_FUEL_CONSUMPTION_AND_DISTANCE_LEFT_R:" + unitFuelConsumptionAndDistanceLeftR);
                            break;
                        case 557857056:
                            int malFunctionInfosPleaseCheckTheBrakeSysRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckTheBrakeSysRed();
                            this.g.setMalFunctionInfosPleaseCheckTheBrakeSysRed(malFunctionInfosPleaseCheckTheBrakeSysRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_BRAKE_SYS_RED:" + malFunctionInfosPleaseCheckTheBrakeSysRed);
                            break;
                        case 557857057:
                            int malFunctionInfosBrakeFluidLevelIsLowRed = instrumentEventChangeValue.getMalFunctionInfosBrakeFluidLevelIsLowRed();
                            this.g.setMalFunctionInfosBrakeFluidLevelIsLowRed(malFunctionInfosBrakeFluidLevelIsLowRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_BRAKE_FLUID_LEVEL_IS_LOW_RED:" + malFunctionInfosBrakeFluidLevelIsLowRed);
                            break;
                        case 557857058:
                            int malFunctionInfosPleaseCheckTheSteerSysRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckTheSteerSysRed();
                            this.g.setMalFunctionInfosPleaseCheckTheSteerSysRed(malFunctionInfosPleaseCheckTheSteerSysRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_STEER_SYS_RED:" + malFunctionInfosPleaseCheckTheSteerSysRed);
                            break;
                        case 557857059:
                            int malFunctionInfosEngineCoolTempTooHigh1Red = instrumentEventChangeValue.getMalFunctionInfosEngineCoolTempTooHigh1Red();
                            this.g.setMalFunctionInfosEngineCoolTempTooHigh1Red(malFunctionInfosEngineCoolTempTooHigh1Red);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH1_RED:" + malFunctionInfosEngineCoolTempTooHigh1Red);
                            break;
                        case 557857060:
                            int malFunctionInfosEngineCoolTempTooHigh2Red = instrumentEventChangeValue.getMalFunctionInfosEngineCoolTempTooHigh2Red();
                            this.g.setMalFunctionInfosEngineCoolTempTooHigh2Red(malFunctionInfosEngineCoolTempTooHigh2Red);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH2_RED:" + malFunctionInfosEngineCoolTempTooHigh2Red);
                            break;
                        case 557857061:
                            int malFunctionInfosInsufficientOilPressureRed = instrumentEventChangeValue.getMalFunctionInfosInsufficientOilPressureRed();
                            this.g.setMalFunctionInfosInsufficientOilPressureRed(malFunctionInfosInsufficientOilPressureRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_INSUFFICIENT_OIL_PRESSURE_RED:" + malFunctionInfosInsufficientOilPressureRed);
                            break;
                        case 557857062:
                            int malFunctionInfosPleaseCheckThePowerSysRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckThePowerSysRed();
                            this.g.setMalFunctionInfosPleaseCheckThePowerSysRed(malFunctionInfosPleaseCheckThePowerSysRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_POWER_SYS_RED:" + malFunctionInfosPleaseCheckThePowerSysRed);
                            break;
                        case 557857063:
                            int malFunctionInfosLVPowerSupplySysFaultyRed = instrumentEventChangeValue.getMalFunctionInfosLVPowerSupplySysFaultyRed();
                            this.g.setMalFunctionInfosLvPowerSupplySysFaultyRed(malFunctionInfosLVPowerSupplySysFaultyRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LV_POWER_SUPPLY_SYS_FAULTY_RED:" + malFunctionInfosLVPowerSupplySysFaultyRed);
                            break;
                        case 557857064:
                            int malFunctionInfosStartTypeIronBatFaultyRed = instrumentEventChangeValue.getMalFunctionInfosStartTypeIronBatFaultyRed();
                            this.g.setMalFunctionInfosStartTypeIronBatFaultyRed(malFunctionInfosStartTypeIronBatFaultyRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_START_TYPE_IRON_BAT_FAULTY_RED:" + malFunctionInfosStartTypeIronBatFaultyRed);
                            break;
                        case 557857065:
                            int malFunctionInfosPleaseCheckVehicleNetRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckVehicleNetRed();
                            this.g.setMalFunctionInfosPleaseCheckVehicleNetRed(malFunctionInfosPleaseCheckVehicleNetRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_VEHICLE_NET_RED:" + malFunctionInfosPleaseCheckVehicleNetRed);
                            break;
                        case 557857066:
                            int malFunctionInfosPleaseCheckTransmiSys1Red = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckTransmiSys1Red();
                            this.g.setMalFunctionInfosPleaseCheckTransmiSys1Red(malFunctionInfosPleaseCheckTransmiSys1Red);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMI_SYS1_RED:" + malFunctionInfosPleaseCheckTransmiSys1Red);
                            break;
                        case 557857067:
                            int malFunctionInfosPleaseCheckTransmisSys2Red = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckTransmisSys2Red();
                            this.g.setMalFunctionInfosPleaseCheckTransmisSys2Red(malFunctionInfosPleaseCheckTransmisSys2Red);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMIS_SYS2_RED:" + malFunctionInfosPleaseCheckTransmisSys2Red);
                            break;
                        case 557857068:
                            int malFunctionInfosLowBatteryLimitedPowerRed = instrumentEventChangeValue.getMalFunctionInfosLowBatteryLimitedPowerRed();
                            this.g.setMalFunctionInfosLowBatteryLimitedPowerRed(malFunctionInfosLowBatteryLimitedPowerRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LOW_BATTERY_LIMITED_POWER_RED:" + malFunctionInfosLowBatteryLimitedPowerRed);
                            break;
                        case 557857069:
                            int malFunctionInfosEVFunctionIsLimitedRed = instrumentEventChangeValue.getMalFunctionInfosEVFunctionIsLimitedRed();
                            this.g.setMalFunctionInfosEvFunctionIsLimitedRed(malFunctionInfosEVFunctionIsLimitedRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_EV_FUNCTION_IS_LIMITED_RED:" + malFunctionInfosEVFunctionIsLimitedRed);
                            break;
                        case 557857070:
                            int malFunctionInfosExitSmartDrivingModeRed = instrumentEventChangeValue.getMalFunctionInfosExitSmartDrivingModeRed();
                            this.g.setMalFunctionInfosExitSmartDrivingModeRed(malFunctionInfosExitSmartDrivingModeRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_EXIT_SMART_DRIVING_MODE_RED:" + malFunctionInfosExitSmartDrivingModeRed);
                            break;
                        case 557857071:
                            int malFunctionInfosIntelligentDriveFailureRed = instrumentEventChangeValue.getMalFunctionInfosIntelligentDriveFailureRed();
                            this.g.setMalFunctionInfosIntelligentDriveFailureRed(malFunctionInfosIntelligentDriveFailureRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_INTELLIGENT_DRIVE_FAILURE_RED:" + malFunctionInfosIntelligentDriveFailureRed);
                            break;
                        case 557857072:
                            int malFunctionInfosPleaseCheckEPBRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckEPBRed();
                            this.g.setMalFunctionInfosPleaseCheckEpbRed(malFunctionInfosPleaseCheckEPBRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_EPB_RED:" + malFunctionInfosPleaseCheckEPBRed);
                            break;
                        case 557857073:
                            int malFunctionInfosLackOfParkCapacityYellow = instrumentEventChangeValue.getMalFunctionInfosLackOfParkCapacityYellow();
                            this.g.setMalFunctionInfosLackOfParkCapacityYellow(malFunctionInfosLackOfParkCapacityYellow);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LACK_OF_PARK_CAPACITY_YELLOW:" + malFunctionInfosLackOfParkCapacityYellow);
                            break;
                        case 557857074:
                            int malFunctionInfosPleaseCheckLBMSRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckLBMSRed();
                            this.g.setMalFunctionInfosPleaseCheckLbmsRed(malFunctionInfosPleaseCheckLBMSRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LBMS_RED:" + malFunctionInfosPleaseCheckLBMSRed);
                            break;
                        case 557857075:
                            int malFunctionInfosPleaseCheckLVSupSysRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckLVSupSysRed();
                            this.g.setMalFunctionInfosPleaseCheckLvSupSysRed(malFunctionInfosPleaseCheckLVSupSysRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LV_SUP_SYS_RED:" + malFunctionInfosPleaseCheckLVSupSysRed);
                            break;
                        case 557857076:
                            int malFunctionInfosPleaseCheckEngineSystemRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckEngineSystemRed();
                            this.g.setMalFunctionInfosPleaseCheckEngineSystemRed(malFunctionInfosPleaseCheckEngineSystemRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ENGINE_SYSTEM_RED:" + malFunctionInfosPleaseCheckEngineSystemRed);
                            break;
                        case 557857077:
                            int malFunctionInfosEngAccessoriesLimitedRed = instrumentEventChangeValue.getMalFunctionInfosEngAccessoriesLimitedRed();
                            this.g.setMalFunctionInfosEngAccessoriesLimitedRed(malFunctionInfosEngAccessoriesLimitedRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_ENG_ACCESSORIES_LIMITED_RED:" + malFunctionInfosEngAccessoriesLimitedRed);
                            break;
                        case 557857078:
                            int malFunctionInfosTransmisLimitedinFuncRed = instrumentEventChangeValue.getMalFunctionInfosTransmisLimitedinFuncRed();
                            this.g.setMalFunctionInfosTransmisLimitedinFuncRed(malFunctionInfosTransmisLimitedinFuncRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_TRANSMIS_LIMITEDIN_FUNC_RED:" + malFunctionInfosTransmisLimitedinFuncRed);
                            break;
                        case 557857079:
                            int malFunctionInfosHighClutchTemperatureRed = instrumentEventChangeValue.getMalFunctionInfosHighClutchTemperatureRed();
                            this.g.setMalFunctionInfosHighClutchTemperatureRed(malFunctionInfosHighClutchTemperatureRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_HIGH_CLUTCH_TEMPERATURE_RED:" + malFunctionInfosHighClutchTemperatureRed);
                            break;
                        case 557857080:
                            int malFunctionInfosGearboxOilTempTooHighRed = instrumentEventChangeValue.getMalFunctionInfosGearboxOilTempTooHighRed();
                            this.g.setMalFunctionInfosGearboxOilTempTooHighRed(malFunctionInfosGearboxOilTempTooHighRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_GEARBOX_OIL_TEMP_TOO_HIGH_RED:" + malFunctionInfosGearboxOilTempTooHighRed);
                            break;
                        case 557857081:
                            int malFunctionInfosPleaseCheckSRSSystemRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckSRSSystemRed();
                            this.g.setMalFunctionInfosPleaseCheckSrsSystemRed(malFunctionInfosPleaseCheckSRSSystemRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SRS_SYSTEM_RED:" + malFunctionInfosPleaseCheckSRSSystemRed);
                            break;
                        case 557857082:
                            int malFunctionInfosPleaseCheckADBSystemRed = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckADBSystemRed();
                            this.g.setMalFunctionInfosPleaseCheckAdbSystemRed(malFunctionInfosPleaseCheckADBSystemRed);
                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ADB_SYSTEM_RED:" + malFunctionInfosPleaseCheckADBSystemRed);
                            break;
                        case 557857140:
                            int sportModeStateR = instrumentEventChangeValue.getSportModeStateR();
                            this.g.setSportModeStateR(sportModeStateR);
                            LogUtil.debug(str, "ID_SPORT_MODE_STATE:" + sportModeStateR);
                            break;
                        case 557857144:
                            int dayNightModeR = instrumentEventChangeValue.getDayNightModeR();
                            this.g.setDayNightModeR(dayNightModeR);
                            LogUtil.verbose(str, "ID_DAY_NIGHT_MODER:" + dayNightModeR);
                            break;
                        case 557857146:
                            int maintenanceInfoTwoR = instrumentEventChangeValue.getMaintenanceInfoTwoR();
                            this.g.setMaintenanceInfoThreeR(instrumentEventChangeValue.getMaintenanceInfoThreeR());
                            this.g.setMaintenanceInfoTwoR(maintenanceInfoTwoR);
                            LogUtil.verbose(str, "ID_MAINTENANCE_INFO_TWOR:" + maintenanceInfoTwoR);
                            break;
                        case 557857152:
                            int totalMileageR = instrumentEventChangeValue.getTotalMileageR();
                            this.g.setTotalMileageR(totalMileageR);
                            LogUtil.verbose(str, "ID_TOTAL_MILEAGER:" + totalMileageR);
                            break;
                        case 557857156:
                            int maintenanceInfoFourR = instrumentEventChangeValue.getMaintenanceInfoFourR();
                            this.g.setMaintenanceInfoFourR(maintenanceInfoFourR);
                            LogUtil.verbose(str, "ID_MAINTENANCE_INFO_FOURR:" + maintenanceInfoFourR);
                            break;
                        case 557857158:
                            int speedUpFormInsR = instrumentEventChangeValue.getSpeedUpFormInsR();
                            this.g.setSpeedUpFormIns(speedUpFormInsR);
                            LogUtil.verbose(str, "ID_SPEED_UP_FORM_INS_R:" + speedUpFormInsR);
                            break;
                        case 557857169:
                            int allWeatherLightsStateR = instrumentEventChangeValue.getAllWeatherLightsStateR();
                            this.g.setAllWeatherLightsStateR(allWeatherLightsStateR);
                            LogUtil.debug(str, "ID_ALL_WEATHER_LIGHTS_STATE_R:" + allWeatherLightsStateR);
                            break;
                        case 557857207:
                            int masterVolumeFirstR = instrumentEventChangeValue.getMasterVolumeFirstR();
                            this.g.setMasterVolumeFirstR(masterVolumeFirstR);
                            LogUtil.debug(str, "ID_MASTER_VOLUME_FIRST_R:" + masterVolumeFirstR);
                            break;
                        case 557857208:
                            int masterVolumeSecondR = instrumentEventChangeValue.getMasterVolumeSecondR();
                            this.g.setMasterVolumeSecondR(masterVolumeSecondR);
                            LogUtil.debug(str, "ID_MASTER_VOLUME_SECOND_R:" + masterVolumeSecondR);
                            break;
                        case 557857209:
                            int muteStateFirstR = instrumentEventChangeValue.getMuteStateFirstR();
                            this.g.setMuteStateFirstR(muteStateFirstR);
                            LogUtil.debug(str, "ID_MUTE_STATE_FIRST_R:" + muteStateFirstR);
                            break;
                        case 557857210:
                            int muteStateSecondR = instrumentEventChangeValue.getMuteStateSecondR();
                            this.g.setMuteStateSecondR(muteStateSecondR);
                            LogUtil.debug(str, "ID_MUTE_STATE_SECONDE_R:" + muteStateSecondR);
                            break;
                        case 557867009:
                            int speedUpCountStateR = instrumentEventChangeValue.getSpeedUpCountStateR();
                            this.g.setSpeedUpCountStateR(speedUpCountStateR);
                            LogUtil.verbose(str, "ID_SPEED_UP_COUNT_STATER:" + speedUpCountStateR);
                            break;
                        case 557867010:
                            int speedUnitR = instrumentEventChangeValue.getSpeedUnitR();
                            this.g.setSpeedUnit(speedUnitR);
                            LogUtil.verbose(str, "ID_SPEED_UNITR:" + speedUnitR);
                            break;
                        case 557867011:
                            int accCounterUiR = instrumentEventChangeValue.getAccCounterUiR();
                            this.g.setAccCounterUiR(accCounterUiR);
                            LogUtil.verbose(str, "ID_ACC_COUNTER_UI:" + accCounterUiR);
                            break;
                        case 557868033:
                            int mileageUnitR = instrumentEventChangeValue.getMileageUnitR();
                            this.g.setMileageUnitR(mileageUnitR);
                            LogUtil.verbose(str, "ID_MILEAGE_UNITR:" + mileageUnitR);
                            break;
                        case 557868037:
                            int areaInfoR = instrumentEventChangeValue.getAreaInfoR();
                            this.g.setAreaInfoR(areaInfoR);
                            LogUtil.verbose(str, "ID_AREA_INFO_R:" + areaInfoR);
                            break;
                        case 557868043:
                            int distanceMileageUnitR = instrumentEventChangeValue.getDistanceMileageUnitR();
                            this.g.setDistanceMileageUnitR(distanceMileageUnitR);
                            LogUtil.debug(str, "ID_DISTANCE_MILEAGE_UNIT_R:" + distanceMileageUnitR);
                            break;
                        case 557869062:
                            int lowPowerBatteryWarnLightColorR = instrumentEventChangeValue.getLowPowerBatteryWarnLightColorR();
                            this.g.setLowPowerBatteryWarnLightColorR(lowPowerBatteryWarnLightColorR);
                            LogUtil.verbose(str, "ID_LOW_POWER_BATTERY_WARN_LIGHT_COLOR_R:" + lowPowerBatteryWarnLightColorR);
                            break;
                        case 557869067:
                            int lowFuelWarnLightColorR = instrumentEventChangeValue.getLowFuelWarnLightColorR();
                            this.g.setLowFuelWarnLightColorR(lowFuelWarnLightColorR);
                            LogUtil.verbose(str, "ID_LOW_FUEL_WARN_LIGHT_COLOR_R:" + lowFuelWarnLightColorR);
                            break;
                        case 557875262:
                            int iNSCenterDisplayR = instrumentEventChangeValue.getINSCenterDisplayR();
                            this.g.setINSCenterDisplayR(iNSCenterDisplayR);
                            LogUtil.verbose(str, "ID_INS_CENTER_DISPLAYR:" + iNSCenterDisplayR);
                            break;
                        case 559954186:
                            float externalChargingPower = instrumentEventChangeValue.getExternalChargingPower();
                            this.g.setExternalChargingPower(externalChargingPower);
                            LogUtil.verbose(str, "ID_EXTERNAL_CHARGING_POWER:" + externalChargingPower);
                            break;
                        default:
                            switch (i) {
                                case 557857051:
                                    int unitPowerLeftR = instrumentEventChangeValue.getUnitPowerLeftR();
                                    this.g.setUnitPowerLeftR(unitPowerLeftR);
                                    LogUtil.verbose(str, "ID_UNIT_POWER_LEFT_R:" + unitPowerLeftR);
                                    break;
                                case 557857052:
                                    int alarmBuzzleState = instrumentEventChangeValue.getAlarmBuzzleState();
                                    this.g.setAlarmBuzzleState(alarmBuzzleState);
                                    LogUtil.verbose(str, "ID_ALARM_BUZZLE_STATE:" + alarmBuzzleState);
                                    break;
                                case 557857053:
                                    int malfunctionInfo = instrumentEventChangeValue.getMalfunctionInfo();
                                    this.g.setMalfunctionInfo(malfunctionInfo);
                                    LogUtil.verbose(str, "ID_MALFUNCTION_INFO:" + malfunctionInfo);
                                    break;
                                case 557857054:
                                    int malFunctionInfosPleaseLeaveVehicleImmdRed = instrumentEventChangeValue.getMalFunctionInfosPleaseLeaveVehicleImmdRed();
                                    this.g.setMalFunctionInfosPleaseLeaveVehicleImmdRed(malFunctionInfosPleaseLeaveVehicleImmdRed);
                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_LEAVE_VEHICLE_IMMD_RED:" + malFunctionInfosPleaseLeaveVehicleImmdRed);
                                    break;
                                default:
                                    switch (i) {
                                        case 557857084:
                                            int malFunctionInfosCheckHeadlightSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckHeadlightSysYellow();
                                            this.g.setMalFunctionInfosCheckHeadlightSysYellow(malFunctionInfosCheckHeadlightSysYellow);
                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_HEADLIGHT_SYS_YELLOW:" + malFunctionInfosCheckHeadlightSysYellow);
                                            break;
                                        case 557857085:
                                            int malFunctionInfosEngineStartFailedRed = instrumentEventChangeValue.getMalFunctionInfosEngineStartFailedRed();
                                            this.g.setMalFunctionInfosEngineStartFailedRed(malFunctionInfosEngineStartFailedRed);
                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_ENGINE_START_FAILED_RED:" + malFunctionInfosEngineStartFailedRed);
                                            break;
                                        case 557857086:
                                            int malFunctionInfosCoolantLiquiLevelLowYellow = instrumentEventChangeValue.getMalFunctionInfosCoolantLiquiLevelLowYellow();
                                            this.g.setMalFunctionInfosCoolantLiquiLevelLowYellow(malFunctionInfosCoolantLiquiLevelLowYellow);
                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_COOLANT_LIQUI_LEVEL_LOW_YELLOW:" + malFunctionInfosCoolantLiquiLevelLowYellow);
                                            break;
                                        case 557857087:
                                            int malFunctionInfosACHotManaFailYellow = instrumentEventChangeValue.getMalFunctionInfosACHotManaFailYellow();
                                            this.g.setMalFunctionInfosAcHotManaFailYellow(malFunctionInfosACHotManaFailYellow);
                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_AC_HOT_MANA_FAIL_YELLOW:" + malFunctionInfosACHotManaFailYellow);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557857090:
                                                    int malFunctionInfosCheckTheOilMonitSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckTheOilMonitSysYellow();
                                                    this.g.setMalFunctionInfosCheckTheOilMonitSysYellow(malFunctionInfosCheckTheOilMonitSysYellow);
                                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_THE_OIL_MONIT_SYS_YELLOW:" + malFunctionInfosCheckTheOilMonitSysYellow);
                                                    break;
                                                case 557857091:
                                                    int malFunctionInfosCheckTirePresMonSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckTirePresMonSysYellow();
                                                    this.g.setMalFunctionInfosCheckTirePresMonSysYellow(malFunctionInfosCheckTirePresMonSysYellow);
                                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_TIRE_PRES_MON_SYS_YELLOW:" + malFunctionInfosCheckTirePresMonSysYellow);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557857093:
                                                            int malFunctionInfosCheckTheBrakSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckTheBrakSysYellow();
                                                            this.g.setMalFunctionInfosCheckTheBrakSysYellow(malFunctionInfosCheckTheBrakSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_THE_BRAK_SYS_YELLOW:" + malFunctionInfosCheckTheBrakSysYellow);
                                                            break;
                                                        case 557857094:
                                                            int malFunctionInfosPleaseCheckABSSysYellow = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckABSSysYellow();
                                                            this.g.setMalFunctionInfosPleaseCheckAbsSysYellow(malFunctionInfosPleaseCheckABSSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ABS_SYS_YELLOW:" + malFunctionInfosPleaseCheckABSSysYellow);
                                                            break;
                                                        case 557857095:
                                                            int malFunctionInfosCheckGenerationSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckGenerationSysYellow();
                                                            this.g.setMalFunctionInfosCheckGenerationSysYellow(malFunctionInfosCheckGenerationSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_GENERATION_SYS_YELLOW:" + malFunctionInfosCheckGenerationSysYellow);
                                                            break;
                                                        case 557857096:
                                                            int malFunctionInfosPleaseCheckGearSysYellow = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckGearSysYellow();
                                                            this.g.setMalFunctionInfosPleaseCheckGearSysYellow(malFunctionInfosPleaseCheckGearSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_GEAR_SYS_YELLOW:" + malFunctionInfosPleaseCheckGearSysYellow);
                                                            break;
                                                        case 557857097:
                                                            int malFunctionInfosPleaseCheckESPSysYellow = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckESPSysYellow();
                                                            this.g.setMalFunctionInfosPleaseCheckEspSysYellow(malFunctionInfosPleaseCheckESPSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ESP_SYS_YELLOW:" + malFunctionInfosPleaseCheckESPSysYellow);
                                                            break;
                                                        case 557857098:
                                                            int malFunctionInfosPleaseCheckHDCSysYellow = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckHDCSysYellow();
                                                            this.g.setMalFunctionInfosPleaseCheckHdcSysYellow(malFunctionInfosPleaseCheckHDCSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_HDC_SYS_YELLOW:" + malFunctionInfosPleaseCheckHDCSysYellow);
                                                            break;
                                                        case 557857099:
                                                            int malFunctionInfosCheckBrakePlateYellow = instrumentEventChangeValue.getMalFunctionInfosCheckBrakePlateYellow();
                                                            this.g.setMalFunctionInfosCheckBrakePlateYellow(malFunctionInfosCheckBrakePlateYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_BRAKE_PLATE_YELLOW:" + malFunctionInfosCheckBrakePlateYellow);
                                                            break;
                                                        case 557857100:
                                                            int malFunctionInfosCheckVideoControllerYellow = instrumentEventChangeValue.getMalFunctionInfosCheckVideoControllerYellow();
                                                            this.g.setMalFunctionInfosCheckVideoControllerYellow(malFunctionInfosCheckVideoControllerYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_VIDEO_CONTROLLER_YELLOW:" + malFunctionInfosCheckVideoControllerYellow);
                                                            break;
                                                        case 557857101:
                                                            int malFunctionInfosCheckFMRRYellow = instrumentEventChangeValue.getMalFunctionInfosCheckFMRRYellow();
                                                            this.g.setMalFunctionInfosCheckFmrrYellow(malFunctionInfosCheckFMRRYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_FMRR_YELLOW:" + malFunctionInfosCheckFMRRYellow);
                                                            break;
                                                        case 557857102:
                                                            int malFunctionInfosAEBFunctLimitYellow = instrumentEventChangeValue.getMalFunctionInfosAEBFunctLimitYellow();
                                                            this.g.setMalFunctionInfosAebFunctLimitYellow(malFunctionInfosAEBFunctLimitYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_AEB_FUNCT_LIMIT_YELLOW:" + malFunctionInfosAEBFunctLimitYellow);
                                                            break;
                                                        case 557857103:
                                                            int malFunctionInfosCheckPredCollAlaSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckPredCollAlaSysYellow();
                                                            this.g.setMalFunctionInfosCheckPredCollAlaSysYellow(malFunctionInfosCheckPredCollAlaSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_PRED_COLL_ALA_SYS_YELLOW:" + malFunctionInfosCheckPredCollAlaSysYellow);
                                                            break;
                                                        case 557857104:
                                                            int malFunctionInfosCheckPedestriDeteSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckPedestriDeteSysYellow();
                                                            this.g.setMalFunctionInfosCheckPedestriDeteSysYellow(malFunctionInfosCheckPedestriDeteSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_PEDESTRI_DETE_SYS_YELLOW:" + malFunctionInfosCheckPedestriDeteSysYellow);
                                                            break;
                                                        case 557857105:
                                                            int malFunctionInfosLimitedLaneKeepingYellow = instrumentEventChangeValue.getMalFunctionInfosLimitedLaneKeepingYellow();
                                                            this.g.setMalFunctionInfosLimitedLaneKeepingYellow(malFunctionInfosLimitedLaneKeepingYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LIMITED_LANE_KEEPING_YELLOW:" + malFunctionInfosLimitedLaneKeepingYellow);
                                                            break;
                                                        case 557857106:
                                                            int malFunctionInfosLimitedAdaptiveCruiseYellow = instrumentEventChangeValue.getMalFunctionInfosLimitedAdaptiveCruiseYellow();
                                                            this.g.setMalFunctionInfosLimitedAdaptiveCruiseYellow(malFunctionInfosLimitedAdaptiveCruiseYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LIMITED_ADAPTIVE_CRUISE_YELLOW:" + malFunctionInfosLimitedAdaptiveCruiseYellow);
                                                            break;
                                                        case 557857107:
                                                            int malFunctionInfosLimitLaneDepartuFuncYellow = instrumentEventChangeValue.getMalFunctionInfosLimitLaneDepartuFuncYellow();
                                                            this.g.setMalFunctionInfosLimitLaneDepartuFuncYellow(malFunctionInfosLimitLaneDepartuFuncYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LIMIT_LANE_DEPARTU_FUNC_YELLOW:" + malFunctionInfosLimitLaneDepartuFuncYellow);
                                                            break;
                                                        case 557857108:
                                                            int malFunctionInfosLimitedBlindSpotMoniYellow = instrumentEventChangeValue.getMalFunctionInfosLimitedBlindSpotMoniYellow();
                                                            this.g.setMalFunctionInfosLimitedBlindSpotMoniYellow(malFunctionInfosLimitedBlindSpotMoniYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_LIMITED_BLIND_SPOT_MONI_YELLOW:" + malFunctionInfosLimitedBlindSpotMoniYellow);
                                                            break;
                                                        case 557857109:
                                                            int malFunctionInfosCheckDischargeEquipYellow = instrumentEventChangeValue.getMalFunctionInfosCheckDischargeEquipYellow();
                                                            this.g.setMalFunctionInfosCheckDischargeEquipYellow(malFunctionInfosCheckDischargeEquipYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_DISCHARGE_EQUIP_YELLOW:" + malFunctionInfosCheckDischargeEquipYellow);
                                                            break;
                                                        case 557857110:
                                                            int malFunctionInfosCheckChargOutEquipYellow = instrumentEventChangeValue.getMalFunctionInfosCheckChargOutEquipYellow();
                                                            this.g.setMalFunctionInfosCheckChargOutEquipYellow(malFunctionInfosCheckChargOutEquipYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_CHARG_OUT_EQUIP_YELLOW:" + malFunctionInfosCheckChargOutEquipYellow);
                                                            break;
                                                        case 557857111:
                                                            int malFunctionInfosCheckOnBoardcharSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckOnBoardcharSysYellow();
                                                            this.g.setMalFunctionInfosCheckOnBoardcharSysYellow(malFunctionInfosCheckOnBoardcharSysYellow);
                                                            LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_ON_BOARDCHAR_SYS_YELLOW:" + malFunctionInfosCheckOnBoardcharSysYellow);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 557857129:
                                                                    int malFunctionInfosAPAFunctionLimitWhite = instrumentEventChangeValue.getMalFunctionInfosAPAFunctionLimitWhite();
                                                                    this.g.setMalFunctionInfosApaFunctionLimitWhite(malFunctionInfosAPAFunctionLimitWhite);
                                                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_APA_FUNCTION_LIMIT_WHITE:" + malFunctionInfosAPAFunctionLimitWhite);
                                                                    break;
                                                                case 557857130:
                                                                    int malFunctionInfosCheckMotPretBeltSysYellow = instrumentEventChangeValue.getMalFunctionInfosCheckMotPretBeltSysYellow();
                                                                    this.g.setMalFunctionInfosCheckMotPretBeltSysYellow(malFunctionInfosCheckMotPretBeltSysYellow);
                                                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_CHECK_MOT_PRET_BELT_SYS_YELLOW:" + malFunctionInfosCheckMotPretBeltSysYellow);
                                                                    break;
                                                                case 557857131:
                                                                    int malFunctionInfosPleaseCheckMemorySysYellow = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckMemorySysYellow();
                                                                    this.g.setMalFunctionInfosPleaseCheckMemorySysYellow(malFunctionInfosPleaseCheckMemorySysYellow);
                                                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_MEMORY_SYS_YELLOW:" + malFunctionInfosPleaseCheckMemorySysYellow);
                                                                    break;
                                                                case 557857132:
                                                                    int malFunctionInfosPleaseCheckSunrooSysYellow = instrumentEventChangeValue.getMalFunctionInfosPleaseCheckSunrooSysYellow();
                                                                    this.g.setMalFunctionInfosPleaseCheckSunrooSysYellow(malFunctionInfosPleaseCheckSunrooSysYellow);
                                                                    LogUtil.verbose(str, "ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SUNROO_SYS_YELLOW:" + malFunctionInfosPleaseCheckSunrooSysYellow);
                                                                    break;
                                                                case 557857133:
                                                                    int iNSNavDisplayModeR = instrumentEventChangeValue.getINSNavDisplayModeR();
                                                                    this.g.setINSNavDisplayModeR(iNSNavDisplayModeR);
                                                                    LogUtil.verbose(str, "ID_INS_NAV_DISPLAY_MODE_R:" + iNSNavDisplayModeR);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 557857135:
                                                                            int iNSAdasCenterDisplaySend4C1 = instrumentEventChangeValue.getINSAdasCenterDisplaySend4C1();
                                                                            this.g.setINSAdasCenterDisplaySend4C1(iNSAdasCenterDisplaySend4C1);
                                                                            LogUtil.verbose(str, "ID_INS_ADAS_CENTER_DISPLAY_SEND_4C1:" + iNSAdasCenterDisplaySend4C1);
                                                                            break;
                                                                        case 557857136:
                                                                            int iNSAdasCenterDisplayRespond40C = instrumentEventChangeValue.getINSAdasCenterDisplayRespond40C();
                                                                            this.g.setINSAdasCenterDisplayRespond40C(iNSAdasCenterDisplayRespond40C);
                                                                            LogUtil.verbose(str, "ID_INS_ADAS_CENTER_DISPLAY_RESPOND_40C:" + iNSAdasCenterDisplayRespond40C);
                                                                            break;
                                                                        case 557857137:
                                                                            int iNSAdasCenterDisplaySend26F = instrumentEventChangeValue.getINSAdasCenterDisplaySend26F();
                                                                            this.g.setINSAdasCenterDisplaySend26F(iNSAdasCenterDisplaySend26F);
                                                                            LogUtil.verbose(str, "ID_INS_ADAS_CENTER_DISPLAY_SEND_26F:" + iNSAdasCenterDisplaySend26F);
                                                                            break;
                                                                        case 557857138:
                                                                            int iNSAdasCenterDisplay = instrumentEventChangeValue.getINSAdasCenterDisplay();
                                                                            this.g.setINSAdasCenterDisplay(iNSAdasCenterDisplay);
                                                                            LogUtil.verbose(str, "ID_INS_ADAS_CENTER_DISPLAY:" + iNSAdasCenterDisplay);
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 557857171:
                                                                                    int headlightControlModeR = instrumentEventChangeValue.getHeadlightControlModeR();
                                                                                    this.g.setHeadlightControlModeR(headlightControlModeR);
                                                                                    LogUtil.debug(str, "ID_HEADLIGHT_CONTROL_MODE_R:" + headlightControlModeR);
                                                                                    break;
                                                                                case 557857172:
                                                                                    int rearFogLightStateR = instrumentEventChangeValue.getRearFogLightStateR();
                                                                                    this.g.setRearFogLightStateR(rearFogLightStateR);
                                                                                    LogUtil.debug(str, "ID_REAR_FOG_LIGHT_STATE_R:" + rearFogLightStateR);
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 557857182:
                                                                                            int musicProgressFirstR = instrumentEventChangeValue.getMusicProgressFirstR();
                                                                                            this.g.setMusicProgressFirstR(musicProgressFirstR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_PROGRESS_2F8_R:" + musicProgressFirstR);
                                                                                            break;
                                                                                        case 557857183:
                                                                                            int musicProgressSecondR = instrumentEventChangeValue.getMusicProgressSecondR();
                                                                                            this.g.setMusicProgressSecondR(musicProgressSecondR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_PROGRESS_2FC_R:" + musicProgressSecondR);
                                                                                            break;
                                                                                        case 557857184:
                                                                                            int musicControlStatusFirstR = instrumentEventChangeValue.getMusicControlStatusFirstR();
                                                                                            this.g.setMusicControlStatusFirstR(musicControlStatusFirstR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_CONTROL_STATUS_FIRST_R:" + musicControlStatusFirstR);
                                                                                            break;
                                                                                        case 557857185:
                                                                                            int musicControlStatusSecondR = instrumentEventChangeValue.getMusicControlStatusSecondR();
                                                                                            this.g.setMusicControlStatusSecondR(musicControlStatusSecondR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_CONTROL_STATUS_SECOND_R:" + musicControlStatusSecondR);
                                                                                            break;
                                                                                        case 557857186:
                                                                                            int musicChannelStatusFirstR = instrumentEventChangeValue.getMusicChannelStatusFirstR();
                                                                                            this.g.setMusicChannelStatusFirstR(musicChannelStatusFirstR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_CHANNEL_STATUS_FIRST_R:" + musicChannelStatusFirstR);
                                                                                            break;
                                                                                        case 557857187:
                                                                                            int musicChannelStatusSecondR = instrumentEventChangeValue.getMusicChannelStatusSecondR();
                                                                                            this.g.setMusicChannelStatusSecondR(musicChannelStatusSecondR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_CHANNEL_STATUS_SECOND_R:" + musicChannelStatusSecondR);
                                                                                            break;
                                                                                        case 557857188:
                                                                                            int musicResourceConfigFirstR = instrumentEventChangeValue.getMusicResourceConfigFirstR();
                                                                                            this.g.setMusicResourceConfigFirstR(musicResourceConfigFirstR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_RESOURCE_CONFIG_FIRST_R:" + musicResourceConfigFirstR);
                                                                                            break;
                                                                                        case 557857189:
                                                                                            int musicResourceConfigSecondR = instrumentEventChangeValue.getMusicResourceConfigSecondR();
                                                                                            this.g.setMusicResourceConfigSecondR(musicResourceConfigSecondR);
                                                                                            LogUtil.debug(str, "ID_MUSIC_RESOURCE_CONFIG_SECOND_R:" + musicResourceConfigSecondR);
                                                                                            break;
                                                                                    }
                                                                                    break;
                                                                            }
                                                                            break;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
            this.f.m(i, i2, this.g);
            LogUtil.verbose(str, "notifyInstrumentEventChangeValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i, int i2, RearviewmirrorEventChangeValue rearviewmirrorEventChangeValue) {
        String str = w0;
        LogUtil.verbose(str, "notifyRearViewMirrorEventValue:" + rearviewmirrorEventChangeValue);
        try {
            switch (i) {
                case 557858048:
                    this.g.setAutoExternalRearMirrorFoldActionStateR(rearviewmirrorEventChangeValue.getAutoExternalRearMirrorFoldActionStateR());
                    LogUtil.verbose(str, "ID_AUTO_EXTERNAL_REAR_MIRROR_FOLD_ACTION_STATER:" + rearviewmirrorEventChangeValue.getAutoExternalRearMirrorFoldActionStateR());
                    break;
                case 557858049:
                    this.g.setAutoExternalRearMirrorFoldedStateR(rearviewmirrorEventChangeValue.getAutoExternalRearMirrorFoldedStateR());
                    LogUtil.verbose(str, "ID_AUTO_EXTERNAL_REAR_MIRROR_FOLDED_STATER:" + rearviewmirrorEventChangeValue.getAutoExternalRearMirrorFoldedStateR());
                    break;
            }
            this.f.m(i, i2, this.g);
            LogUtil.verbose(str, "notifyRearViewMirrorEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void z() {
        LogUtil.info(w0, "quit");
        if (this.W != null) {
            a aVar = this.X;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.W.quitSafely();
        }
    }

    public void A(CarVendorHealthManager carVendorHealthManager, CarVendorDoorlockManager carVendorDoorlockManager, CarVendorGearboxManager carVendorGearboxManager, CarVendorInstrumentManager carVendorInstrumentManager, CarVendorRearviewmirrorManager carVendorRearviewmirrorManager) {
        this.d = carVendorHealthManager;
        this.f26b = carVendorDoorlockManager;
        this.e = carVendorGearboxManager;
        this.f25a = carVendorInstrumentManager;
        this.c = carVendorRearviewmirrorManager;
    }

    @Override // com.ts.appservice.caradapter.d.k
    public void a(GearboxEventChangeValue gearboxEventChangeValue) {
        String str = w0;
        if (gearboxEventChangeValue == null) {
            LogUtil.error(str, "onGearBoxChangeEvent:value=null");
            return;
        }
        int propertyId = gearboxEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onGearBoxChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 12;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = gearboxEventChangeValue.getAreaId();
        messageObtain.obj = gearboxEventChangeValue;
        this.X.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.n
    public void b(InstrumentEventChangeValue instrumentEventChangeValue) {
        String str = w0;
        if (instrumentEventChangeValue == null) {
            LogUtil.error(str, "onInstrumentChangeEvent:value=null");
            return;
        }
        int propertyId = instrumentEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onInstrumentChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 6;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = instrumentEventChangeValue.getAreaId();
        messageObtain.obj = instrumentEventChangeValue;
        this.X.sendMessage(messageObtain);
    }

    public int clearDistanceMilesType(int i) {
        String str = w0;
        LogUtil.verbose(str, " clearDistanceMilesType.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                carVendorInstrumentManager.setclearDistanceMilesTypeS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " clearDistanceMilesType.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " clearDistanceMilesType.out=0");
        return 0;
    }

    @Override // com.ts.appservice.caradapter.d.l
    public void d(HealthEventChangeValue healthEventChangeValue) {
        String str = w0;
        if (healthEventChangeValue == null) {
            LogUtil.error(str, "onHvacChangeEvent:value=null");
            return;
        }
        int propertyId = healthEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onHealthChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 4;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = healthEventChangeValue.getAreaId();
        messageObtain.obj = healthEventChangeValue;
        this.X.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.g
    public void f(DoorlockEventChangeValue doorlockEventChangeValue) {
        String str = w0;
        if (doorlockEventChangeValue == null) {
            LogUtil.error(str, "onHvacChangeEvent:value=null");
            return;
        }
        int propertyId = doorlockEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onDoorlockChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 5;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = doorlockEventChangeValue.getAreaId();
        messageObtain.obj = doorlockEventChangeValue;
        this.X.sendMessage(messageObtain);
    }

    public int getAccCounterUi() {
        String str = w0;
        LogUtil.info(str, "getAccCounterUi");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int accCounterUiR = carVendorInstrumentManager.getAccCounterUiR();
            LogUtil.verbose(str, "getAccCounterUi=" + accCounterUiR);
            return accCounterUiR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public byte[] getAccountMessage() {
        return new byte[]{0};
    }

    public int getAlarmBuzzleState() {
        String str = w0;
        LogUtil.verbose(str, "getAlarmBuzzleState");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int alarmBuzzleState = carVendorInstrumentManager.getAlarmBuzzleState();
            LogUtil.verbose(str, "getAlarmBuzzleState=" + alarmBuzzleState);
            return alarmBuzzleState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAllWeatherLightsState() {
        int allWeatherLightsStateR;
        String str = w0;
        LogUtil.verbose(str, " getAllWeatherLightsState.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                allWeatherLightsStateR = carVendorInstrumentManager.getAllWeatherLightsStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                allWeatherLightsStateR = -1;
            }
            LogUtil.verbose(str, " getAllWeatherLightsState.out=" + allWeatherLightsStateR);
            return allWeatherLightsStateR;
        }
        LogUtil.verbose(str, " getAllWeatherLightsState.null == mInstrumentManager");
        allWeatherLightsStateR = -1;
        LogUtil.verbose(str, " getAllWeatherLightsState.out=" + allWeatherLightsStateR);
        return allWeatherLightsStateR;
    }

    public int getAreaInfoR() {
        String str = w0;
        LogUtil.info(str, "getAreaInfoR");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int areaInfoR = carVendorInstrumentManager.getAreaInfoR();
            LogUtil.verbose(str, "getAreaInfoR=" + areaInfoR);
            return areaInfoR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAssistDriveMemSysOperation() {
        return 0;
    }

    public int getAtmosphereLightValue() {
        return this.G;
    }

    public int getAtmosphereMode() {
        return this.L;
    }

    public int getAutoExternalRearMirrorAntiglareState() {
        LogUtil.verbose(w0, "Service getAutoExternalRearMirrorAntiglareState");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        String str = w0;
        LogUtil.verbose(str, "Service getAutoExternalRearMirrorFoldActionState");
        CarVendorRearviewmirrorManager carVendorRearviewmirrorManager = this.c;
        if (carVendorRearviewmirrorManager == null) {
            return 0;
        }
        try {
            int autoExternalRearMirrorFoldActionStateR = carVendorRearviewmirrorManager.getAutoExternalRearMirrorFoldActionStateR();
            LogUtil.verbose(str, "getAutoExternalRearMirrorFoldActionState=" + autoExternalRearMirrorFoldActionStateR);
            return autoExternalRearMirrorFoldActionStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoExternalRearMirrorFoldedState() {
        String str = w0;
        LogUtil.verbose(str, "Service getAutoExternalRearMirrorFoldedState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858049, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAutoExternalRearMirrorFoldedState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAutoExternalRearMirrorState() {
        LogUtil.verbose(w0, "Service getAutoExternalRearMirrorState");
        return 0;
    }

    public int getBatteryHealthStatus() {
        int batteryHealthStatusR;
        String str = w0;
        LogUtil.verbose(str, " getBatteryHealthStatus.in");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager != null) {
            try {
                batteryHealthStatusR = carVendorHealthManager.getBatteryHealthStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                batteryHealthStatusR = -1;
            }
            LogUtil.verbose(str, " getBatteryHealthStatus.out=" + batteryHealthStatusR);
            return batteryHealthStatusR;
        }
        LogUtil.verbose(str, " getBatteryHealthStatus.null == mCarVendorHealthManager");
        batteryHealthStatusR = -1;
        LogUtil.verbose(str, " getBatteryHealthStatus.out=" + batteryHealthStatusR);
        return batteryHealthStatusR;
    }

    public int getBrakeFluidLevel() {
        String str = w0;
        LogUtil.verbose(str, "getBrakeFluidLevel");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int brakeFluidLevel = carVendorGearboxManager.getBrakeFluidLevel();
            LogUtil.verbose(str, "getBrakeFluidLevel=" + brakeFluidLevel);
            return brakeFluidLevel;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBrakePedalState() {
        String str = w0;
        LogUtil.verbose(str, "getBrakePedalState");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int brakePedalState = carVendorGearboxManager.getBrakePedalState();
            LogUtil.verbose(str, "getBrakePedalState=" + brakePedalState);
            return brakePedalState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBrakePedalStatus() {
        int brakePedalStatusR;
        String str = w0;
        LogUtil.verbose(str, " getBrakePedalStatus.in");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager != null) {
            try {
                brakePedalStatusR = carVendorGearboxManager.getBrakePedalStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                brakePedalStatusR = -1;
            }
            LogUtil.verbose(str, " getBrakePedalStatus.out=" + brakePedalStatusR);
            return brakePedalStatusR;
        }
        LogUtil.verbose(str, " getBrakePedalStatus.null == mCarVendorGearboxManager");
        brakePedalStatusR = -1;
        LogUtil.verbose(str, " getBrakePedalStatus.out=" + brakePedalStatusR);
        return brakePedalStatusR;
    }

    public int getCabinRearDoorOpenStatus() {
        return this.T;
    }

    public int getCabinRearDoorPlgmSet() {
        return this.S;
    }

    public int getCabinWinStRow1Left() {
        return this.O;
    }

    public int getCabinWinStRow1Right() {
        return this.P;
    }

    public int getCabinWinStRow2Left() {
        return this.Q;
    }

    public int getCabinWinStRow2Right() {
        return this.R;
    }

    public int getCanmsgFlagAvas2() {
        return this.Y;
    }

    public int getCanmsgFlagBcmBcan1() {
        return this.Z;
    }

    public int getCanmsgFlagBcmBcan2() {
        return this.a0;
    }

    public int getCanmsgFlagBcmBcan4() {
        return this.b0;
    }

    public int getCanmsgFlagBcmBcan5() {
        return this.c0;
    }

    public int getCanmsgFlagBcmTpms1B() {
        return this.d0;
    }

    public int getCanmsgFlagBcmTpms2B() {
        return this.e0;
    }

    public int getCanmsgFlagBsdm1() {
        return this.f0;
    }

    public int getCanmsgFlagBsds1() {
        return this.g0;
    }

    public int getCanmsgFlagDms1() {
        return this.h0;
    }

    public int getCanmsgFlagEbb3A() {
        return this.i0;
    }

    public int getCanmsgFlagEps1() {
        return this.j0;
    }

    public int getCanmsgFlagFcp3() {
        return this.k0;
    }

    public int getCanmsgFlagFcp4() {
        return this.l0;
    }

    public int getCanmsgFlagFcp5() {
        return this.m0;
    }

    public int getCanmsgFlagFldcm1() {
        return this.n0;
    }

    public int getCanmsgFlagFllcu1() {
        return this.o0;
    }

    public int getCanmsgFlagFrdcm1() {
        return this.p0;
    }

    public int getCanmsgFlagFrlcu1() {
        return this.q0;
    }

    public int getCanmsgFlagPlgm1() {
        return this.r0;
    }

    public int getCanmsgFlagSbm5B() {
        return this.s0;
    }

    public int getCanmsgFlagSbm9B() {
        return this.t0;
    }

    public int getCustomColorValue() {
        return this.I;
    }

    public int getDayNightMode() {
        return 0;
    }

    public int getDistanceMileageUnit() {
        int distanceMileageUnitR;
        String str = w0;
        LogUtil.verbose(str, " getDistanceMileageUnit.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                distanceMileageUnitR = carVendorInstrumentManager.getDistanceMileageUnitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                distanceMileageUnitR = -1;
            }
            LogUtil.verbose(str, " getDistanceMileageUnit.out=" + distanceMileageUnitR);
            return distanceMileageUnitR;
        }
        LogUtil.verbose(str, " getDistanceMileageUnit.null == mInstrumentManager");
        distanceMileageUnitR = -1;
        LogUtil.verbose(str, " getDistanceMileageUnit.out=" + distanceMileageUnitR);
        return distanceMileageUnitR;
    }

    public int getDoorLockCockpitHatchState() {
        int doorLockCockpitHatchStateR;
        String str = w0;
        LogUtil.verbose(str, " getDoorLockCockpitHatchState.in");
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager != null) {
            try {
                doorLockCockpitHatchStateR = carVendorDoorlockManager.getDoorLockCockpitHatchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                doorLockCockpitHatchStateR = -1;
            }
            LogUtil.verbose(str, " getDoorLockCockpitHatchState.out=" + doorLockCockpitHatchStateR);
            return doorLockCockpitHatchStateR;
        }
        LogUtil.verbose(str, " getDoorLockCockpitHatchState.null == mCarVendorDoorlockManager");
        doorLockCockpitHatchStateR = -1;
        LogUtil.verbose(str, " getDoorLockCockpitHatchState.out=" + doorLockCockpitHatchStateR);
        return doorLockCockpitHatchStateR;
    }

    public int getDoorLockStatusAreaBack() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856513, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(w0, "getDoorLockStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorLockStatusAreaChildLockRight() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856526, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(w0, "getDoorLockStatusAreaChildLockRight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorLockStatusAreaLeftFront() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856514, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(w0, "getDoorLockStatusAreaLeftFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorLockStatusAreaRightRear() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856517, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(w0, "getDoorLockStatusAreaRightRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorLockStatusLeftRear() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856516, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(w0, "getDoorLockStatusLeftRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorLockStatusRightFront() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856515, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(w0, "getDoorLockStatusRightFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStatusAreaBack() {
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            int doorStatusAreaBackStatusR = carVendorDoorlockManager.getDoorStatusAreaBackStatusR();
            LogUtil.verbose(w0, "getDoorStatusAreaBackStatusR=" + doorStatusAreaBackStatusR);
            return doorStatusAreaBackStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDoorStatusAreaLeftFront() {
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            int doorStatusAreaLeftFrontStatusR = carVendorDoorlockManager.getDoorStatusAreaLeftFrontStatusR();
            LogUtil.verbose(w0, "getDoorStatusAreaLeftFrontStatusR=" + doorStatusAreaLeftFrontStatusR);
            return doorStatusAreaLeftFrontStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDoorStatusAreaLeftRear() {
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            int doorStatusAreaLeftRearStatusR = carVendorDoorlockManager.getDoorStatusAreaLeftRearStatusR();
            LogUtil.verbose(w0, "getDoorStatusAreaLeftRearStatusR=" + doorStatusAreaLeftRearStatusR);
            return doorStatusAreaLeftRearStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDoorStatusAreaRightFront() {
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            int doorStatusAreaRightFrontStatusR = carVendorDoorlockManager.getDoorStatusAreaRightFrontStatusR();
            LogUtil.verbose(w0, "getDoorStatusAreaRightFrontStatusR=" + doorStatusAreaRightFrontStatusR);
            return doorStatusAreaRightFrontStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDoorStatusAreaRightRear() {
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            int doorStatusAreaRightRearStatusR = carVendorDoorlockManager.getDoorStatusAreaRightRearStatusR();
            LogUtil.verbose(w0, "getDoorStatusAreaRightRearStatusR=" + doorStatusAreaRightRearStatusR);
            return doorStatusAreaRightRearStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDoorStatusChildLeft() {
        String str = w0;
        LogUtil.verbose(str, "CarAdapterService getDoorStatusChildLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856512, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorLockStatusAreaChildLockLeftR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStatusChildRight() {
        String str = w0;
        LogUtil.verbose(str, "CarAdapterService getDoorStatusChildRight");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856526, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorLockStatusAreaChildLockRight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getDrivePepsPuid() {
        return 0;
    }

    @Deprecated
    public byte[] getDriveSbmWelcomeTokenTotal() {
        return new byte[]{0};
    }

    public int getEPBConfig() {
        String str = w0;
        LogUtil.verbose(str, "Service getEPBConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850644, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEPBConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEPBState() {
        String str = w0;
        LogUtil.verbose(str, "Service getEPBState");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int ePBStateR = carVendorHealthManager.getEPBStateR();
            LogUtil.verbose(str, "getEPBStateR=" + ePBStateR);
            return ePBStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getEPBType() {
        String str = w0;
        LogUtil.verbose(str, "Service getEPBType");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850645, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEPBTtpe prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEpbHint() {
        String str = w0;
        LogUtil.verbose(str, "Service getEpbHint");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int epbHintR = carVendorHealthManager.getEpbHintR();
            LogUtil.verbose(str, "getEpbHintR=" + epbHintR);
            return epbHintR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getEpbStateGearBox() {
        String str = w0;
        LogUtil.verbose(str, " getEPBState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857288, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEpbStateGearBox prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public float getExternalChargingPower() {
        String str = w0;
        LogUtil.verbose(str, "getExternalChargingPower");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0.0f;
        }
        try {
            float externalChargingPower = carVendorInstrumentManager.getExternalChargingPower();
            LogUtil.verbose(str, "getExternalChargingPower=" + externalChargingPower);
            return externalChargingPower;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getFraganceConcentration() {
        return this.v0;
    }

    public int getFraganceTaste() {
        return this.u0;
    }

    public int getFrontWiperCheckState() {
        String str = w0;
        LogUtil.verbose(str, "Service getFrontWiperCheckState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850636, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getFrontWiperCheckState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getGear() {
        String str = w0;
        LogUtil.info(str, "getGear");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int gearR = carVendorGearboxManager.getGearR();
            LogUtil.verbose(str, "getGearR=" + gearR);
            return gearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGearboxAutoModeLevel() {
        String str = w0;
        LogUtil.info(str, "getGearboxAutoModeLevel");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int gearboxAutoModeTypeTwo = carVendorGearboxManager.getGearboxAutoModeTypeTwo();
            LogUtil.verbose(str, "getGearboxAutoModeTypeTwo=" + gearboxAutoModeTypeTwo);
            return gearboxAutoModeTypeTwo;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGearboxAutoModeType() {
        String str = w0;
        LogUtil.verbose(str, "getGearboxAutoModeType");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int gearboxAutoModeType = carVendorGearboxManager.getGearboxAutoModeType();
            LogUtil.verbose(str, "getGearboxAutoModeType=" + gearboxAutoModeType);
            return gearboxAutoModeType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getGearboxCode() {
        Exception e;
        int gearboxCode;
        String str = w0;
        LogUtil.verbose(str, "getGearboxCode");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        int i = 0;
        if (carVendorGearboxManager != null) {
            try {
                gearboxCode = carVendorGearboxManager.getGearboxCode();
                try {
                    return String.valueOf(gearboxCode);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    LogUtil.error(str, "getGearboxCode:code==nul");
                    i = gearboxCode;
                    return String.valueOf(i);
                }
            } catch (Exception e3) {
                e = e3;
                gearboxCode = 0;
            }
        }
        return String.valueOf(i);
    }

    public int getGearboxManualModeLevel() {
        String str = w0;
        LogUtil.verbose(str, "getGearboxManualModeLevel");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int gearboxManualModeLevelR = carVendorGearboxManager.getGearboxManualModeLevelR();
            LogUtil.verbose(str, "getGearboxManualModeLevelR=" + gearboxManualModeLevelR);
            return gearboxManualModeLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGearboxType() {
        String str = w0;
        LogUtil.verbose(str, "getGearboxType");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int gearboxType = carVendorGearboxManager.getGearboxType();
            LogUtil.verbose(str, "getGearboxType=" + gearboxType);
            return gearboxType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHdcState() {
        return this.U;
    }

    public int getHeadlightControlMode() {
        int headlightControlModeR;
        String str = w0;
        LogUtil.verbose(str, " getHeadlightControlMode.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                headlightControlModeR = carVendorInstrumentManager.getHeadlightControlModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                headlightControlModeR = -1;
            }
            LogUtil.verbose(str, " getHeadlightControlMode.out=" + headlightControlModeR);
            return headlightControlModeR;
        }
        LogUtil.verbose(str, " getHeadlightControlMode.null == mInstrumentManager");
        headlightControlModeR = -1;
        LogUtil.verbose(str, " getHeadlightControlMode.out=" + headlightControlModeR);
        return headlightControlModeR;
    }

    public int getINSAdasCenterDisplay() {
        String str = w0;
        LogUtil.verbose(str, "getINSAdasCenterDisplay");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int insAdasCenterDisplayRespond40C = carVendorInstrumentManager.getInsAdasCenterDisplayRespond40C();
            LogUtil.verbose(str, "getInsAdasCenterDisplayRespond40C=" + insAdasCenterDisplayRespond40C);
            return insAdasCenterDisplayRespond40C;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getINSCenterDisplay() {
        String str = w0;
        LogUtil.verbose(str, "getINSCenterDisplay");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int iNSCenterDisplayR = carVendorInstrumentManager.getINSCenterDisplayR();
            LogUtil.verbose(str, "getINSCenterDisplayR=" + iNSCenterDisplayR);
            return iNSCenterDisplayR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getINSNavDisplayMode() {
        String str = w0;
        LogUtil.verbose(str, "getINSNavDisplayMode");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int iNSNavDisplayModeR = carVendorInstrumentManager.getINSNavDisplayModeR();
            LogUtil.verbose(str, "getINSNavDisplayModeR=" + iNSNavDisplayModeR);
            return iNSNavDisplayModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInstrumentAutoDimmState() {
        String str = w0;
        LogUtil.verbose(str, "getInstrumentAutoDimmState");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int instrumentAutoDimmStateR = carVendorInstrumentManager.getInstrumentAutoDimmStateR();
            LogUtil.verbose(str, "getInstrumentAutoDimmStateR=" + instrumentAutoDimmStateR);
            return instrumentAutoDimmStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInstrumentBrightness() {
        String str = w0;
        LogUtil.verbose(str, "getInstrumentBrightness");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int instrumentBrightnessR = carVendorInstrumentManager.getInstrumentBrightnessR();
            LogUtil.verbose(str, "getInstrumentBrightnessR=" + instrumentBrightnessR);
            return instrumentBrightnessR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInstrumentBrightnessLeft() {
        String str = w0;
        LogUtil.verbose(str, "getInstrumentBrightnessLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857026, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getInstrumentBrightnessLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getInstrumentConfigure() {
        String str = w0;
        LogUtil.verbose(str, "getInstrumentConfigure is execute");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int instrumentConfigure = carVendorInstrumentManager.getInstrumentConfigure();
            LogUtil.verbose(str, "getInstrumentConfigure=" + instrumentConfigure);
            return instrumentConfigure;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInstrumentScreenType() {
        String str = w0;
        LogUtil.verbose(str, "getInstrumentScreenType is execute");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int instrumentScreenType = carVendorInstrumentManager.getInstrumentScreenType();
            LogUtil.verbose(str, "getInstrumentScreenType=" + instrumentScreenType);
            return instrumentScreenType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInstrumentTheme() {
        String str = w0;
        LogUtil.verbose(str, "getInstrumentTheme");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int instrumentThemeR = carVendorInstrumentManager.getInstrumentThemeR();
            LogUtil.verbose(str, "getInstrumentThemeR=" + instrumentThemeR);
            return instrumentThemeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftChildLockState() {
        int leftChildLockStateR;
        String str = w0;
        LogUtil.verbose(str, " getLeftChildLockState.in");
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager != null) {
            try {
                leftChildLockStateR = carVendorDoorlockManager.getLeftChildLockStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftChildLockStateR = -1;
            }
            LogUtil.verbose(str, " getLeftChildLockState.out=" + leftChildLockStateR);
            return leftChildLockStateR;
        }
        LogUtil.verbose(str, " getLeftChildLockState.null == mCarVendorDoorlockManager");
        leftChildLockStateR = -1;
        LogUtil.verbose(str, " getLeftChildLockState.out=" + leftChildLockStateR);
        return leftChildLockStateR;
    }

    public int getLockHeatProtectedStateR() {
        String str = w0;
        LogUtil.info(str, "getLockHeatProtectedStateR");
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return 0;
        }
        try {
            int lockHeatProtectedStateR = carVendorDoorlockManager.getLockHeatProtectedStateR();
            LogUtil.verbose(str, "getLockHeatProtectedStateR=" + lockHeatProtectedStateR);
            return lockHeatProtectedStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLowFuelWarnLightColor() {
        String str = w0;
        LogUtil.info(str, "getLowFuelWarnLightColor");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int lowFuelWarnLightColorR = carVendorInstrumentManager.getLowFuelWarnLightColorR();
            LogUtil.verbose(str, "getLowFuelWarnLightColor=" + lowFuelWarnLightColorR);
            return lowFuelWarnLightColorR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLowPowerBatteryWarnLightColor() {
        String str = w0;
        LogUtil.info(str, "getLowPowerBatteryWarnLightColor");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int lowPowerBatteryWarnLightColorR = carVendorInstrumentManager.getLowPowerBatteryWarnLightColorR();
            LogUtil.verbose(str, "getLowPowerBatteryWarnLightColor=" + lowPowerBatteryWarnLightColorR);
            return lowPowerBatteryWarnLightColorR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMaintenanceInfoTimeState() {
        return 0;
    }

    public int getMalfunctionInfo(int i) {
        LogUtil.verbose(w0, "getMalfunctionInfo");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            switch (i) {
                case 1:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseLeaveVehicleImmdRed();
                case 2:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckTheBrakeSysRed();
                case 3:
                    return carVendorInstrumentManager.getMalFunctionInfosBrakeFluidLevelIsLowRed();
                case 4:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckTheSteerSysRed();
                case 5:
                    return carVendorInstrumentManager.getMalFunctionInfosEngineCoolTempTooHigh1Red();
                case 6:
                    return carVendorInstrumentManager.getMalFunctionInfosEngineCoolTempTooHigh2Red();
                case 7:
                    return carVendorInstrumentManager.getMalFunctionInfosInsufficientOilPressureRed();
                case 8:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckThePowerSysRed();
                case 9:
                    return carVendorInstrumentManager.getMalFunctionInfosLVPowerSupplySysFaultyRed();
                case 10:
                    return carVendorInstrumentManager.getMalFunctionInfosStartTypeIronBatFaultyRed();
                case 11:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckVehicleNetRed();
                case 12:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckTransmiSys1Red();
                case 13:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckTransmisSys2Red();
                case 14:
                    return carVendorInstrumentManager.getMalFunctionInfosLowBatteryLimitedPowerRed();
                case 15:
                    return carVendorInstrumentManager.getMalFunctionInfosEVFunctionIsLimitedRed();
                case 16:
                    return carVendorInstrumentManager.getMalFunctionInfosExitSmartDrivingModeRed();
                case 17:
                    return carVendorInstrumentManager.getMalFunctionInfosIntelligentDriveFailureRed();
                case 18:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckEPBRed();
                case 19:
                    return carVendorInstrumentManager.getMalFunctionInfosLackOfParkCapacityYellow();
                case 20:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckLBMSRed();
                case 21:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckLVSupSysRed();
                case 22:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckEngineSystemRed();
                case 23:
                    return carVendorInstrumentManager.getMalFunctionInfosEngAccessoriesLimitedRed();
                case 24:
                    return carVendorInstrumentManager.getMalFunctionInfosTransmisLimitedinFuncRed();
                case 25:
                    return carVendorInstrumentManager.getMalFunctionInfosHighClutchTemperatureRed();
                case 26:
                    return carVendorInstrumentManager.getMalFunctionInfosGearboxOilTempTooHighRed();
                case 27:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckSRSSystemRed();
                case 28:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckADBSystemRed();
                case 29:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckHeadlightSysYellow();
                case 30:
                    return carVendorInstrumentManager.getMalFunctionInfosEngineStartFailedRed();
                case 31:
                    return carVendorInstrumentManager.getMalFunctionInfosCoolantLiquiLevelLowYellow();
                case 32:
                    return carVendorInstrumentManager.getMalFunctionInfosACHotManaFailYellow();
                case 33:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckTheOilMonitSysYellow();
                case 34:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckTirePresMonSysYellow();
                case 35:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckTheBrakSysYellow();
                case 36:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckABSSysYellow();
                case 37:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckGenerationSysYellow();
                case 38:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckGearSysYellow();
                case 39:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckESPSysYellow();
                case 40:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckHDCSysYellow();
                case 41:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckBrakePlateYellow();
                case 42:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckVideoControllerYellow();
                case 43:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckFMRRYellow();
                case 44:
                    return carVendorInstrumentManager.getMalFunctionInfosAEBFunctLimitYellow();
                case 45:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckPredCollAlaSysYellow();
                case 46:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckPedestriDeteSysYellow();
                case 47:
                    return carVendorInstrumentManager.getMalFunctionInfosLimitedLaneKeepingYellow();
                case 48:
                    return carVendorInstrumentManager.getMalFunctionInfosLimitedAdaptiveCruiseYellow();
                case 49:
                    return carVendorInstrumentManager.getMalFunctionInfosLimitLaneDepartuFuncYellow();
                case 50:
                    return carVendorInstrumentManager.getMalFunctionInfosLimitedBlindSpotMoniYellow();
                case 51:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckDischargeEquipYellow();
                case 52:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckChargOutEquipYellow();
                case 53:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckOnBoardcharSysYellow();
                case 54:
                    return carVendorInstrumentManager.getMalFunctionInfosAPAFunctionLimitWhite();
                case 55:
                    return carVendorInstrumentManager.getMalFunctionInfosCheckMotPretBeltSysYellow();
                case 56:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckMemorySysYellow();
                case 57:
                    return carVendorInstrumentManager.getMalFunctionInfosPleaseCheckSunrooSysYellow();
                default:
                    return 0;
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int[] getMalfunctionInfos() {
        ArrayList arrayList = new ArrayList();
        LogUtil.verbose(w0, "getMalfunctionInfos");
        int[] iArr = new int[78];
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return iArr;
        }
        try {
            arrayList.add(Integer.valueOf(carVendorInstrumentManager.getMalFunctionInfosPleaseLeaveVehicleImmdRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckTheBrakeSysRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosBrakeFluidLevelIsLowRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckTheSteerSysRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosEngineCoolTempTooHigh1Red()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosEngineCoolTempTooHigh2Red()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosInsufficientOilPressureRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckThePowerSysRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLVPowerSupplySysFaultyRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosStartTypeIronBatFaultyRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckVehicleNetRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckTransmiSys1Red()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckTransmisSys2Red()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLowBatteryLimitedPowerRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosEVFunctionIsLimitedRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosExitSmartDrivingModeRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosIntelligentDriveFailureRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckEPBRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLackOfParkCapacityYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckLBMSRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckLVSupSysRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckEngineSystemRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosEngAccessoriesLimitedRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosTransmisLimitedinFuncRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosHighClutchTemperatureRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosGearboxOilTempTooHighRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckSRSSystemRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckADBSystemRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckHeadlightSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosEngineStartFailedRed()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCoolantLiquiLevelLowYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosACHotManaFailYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckTheOilMonitSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckTirePresMonSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckTheBrakSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckABSSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckGenerationSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckGearSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckESPSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckHDCSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckBrakePlateYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckVideoControllerYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckFMRRYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosAEBFunctLimitYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckPredCollAlaSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckPedestriDeteSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLimitedLaneKeepingYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLimitedAdaptiveCruiseYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLimitLaneDepartuFuncYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosLimitedBlindSpotMoniYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckDischargeEquipYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckChargOutEquipYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckOnBoardcharSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosAPAFunctionLimitWhite()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosCheckMotPretBeltSysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckMemorySysYellow()));
            arrayList.add(Integer.valueOf(this.f25a.getMalFunctionInfosPleaseCheckSunrooSysYellow()));
            return arrayList.stream().mapToInt(new ToIntFunction() { // from class: com.ts.appservice.caradapter.c.b
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((Integer) obj).intValue();
                }
            }).toArray();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return iArr;
        }
    }

    public int getMasterVolumeFirst(int i) {
        int masterVolumeFirstR;
        String str = w0;
        LogUtil.verbose(str, " getMasterVolumeFirst.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                masterVolumeFirstR = carVendorInstrumentManager.getMasterVolumeFirstR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                masterVolumeFirstR = -1;
            }
            LogUtil.verbose(str, " getMasterVolumeFirst.out=" + masterVolumeFirstR);
            return masterVolumeFirstR;
        }
        LogUtil.verbose(str, " getMasterVolumeFirst.null == mInstrumentManager");
        masterVolumeFirstR = -1;
        LogUtil.verbose(str, " getMasterVolumeFirst.out=" + masterVolumeFirstR);
        return masterVolumeFirstR;
    }

    public int getMasterVolumeSecond(int i) {
        int masterVolumeSecondR;
        String str = w0;
        LogUtil.verbose(str, " getMasterVolumeSecond.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                masterVolumeSecondR = carVendorInstrumentManager.getMasterVolumeSecondR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                masterVolumeSecondR = -1;
            }
            LogUtil.verbose(str, " getMasterVolumeSecond.out=" + masterVolumeSecondR);
            return masterVolumeSecondR;
        }
        LogUtil.verbose(str, " getMasterVolumeSecond.null == mInstrumentManager");
        masterVolumeSecondR = -1;
        LogUtil.verbose(str, " getMasterVolumeSecond.out=" + masterVolumeSecondR);
        return masterVolumeSecondR;
    }

    public int getMileageUnit() {
        String str = w0;
        LogUtil.verbose(str, "Service getMileageUnit");
        if (this.d == null) {
            return 0;
        }
        try {
            int mileageUnitR = this.f25a.getMileageUnitR();
            LogUtil.verbose(str, "getMileageUnitR=" + mileageUnitR);
            return mileageUnitR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public long getMilesByType(int i) {
        String str = w0;
        LogUtil.verbose(str, "getMilesByType");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0L;
        }
        try {
            int milesByType = carVendorInstrumentManager.getMilesByType();
            LogUtil.verbose(str, "getMilesByType=" + milesByType);
            return milesByType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public int getMilesType() {
        String str = w0;
        LogUtil.verbose(str, "getMilesType");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int milesTypeR = carVendorInstrumentManager.getMilesTypeR();
            LogUtil.verbose(str, "getMilesTypeR=" + milesTypeR);
            return milesTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMusicChannelStatusFirst(int i) {
        int musicChannelStatusFirstR;
        String str = w0;
        LogUtil.verbose(str, " getMusicChannelStatusFirst.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicChannelStatusFirstR = carVendorInstrumentManager.getMusicChannelStatusFirstR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicChannelStatusFirstR = -1;
            }
            LogUtil.verbose(str, " getMusicChannelStatusFirst.out=" + musicChannelStatusFirstR);
            return musicChannelStatusFirstR;
        }
        LogUtil.verbose(str, " getMusicChannelStatusFirst.null == mInstrumentManager");
        musicChannelStatusFirstR = -1;
        LogUtil.verbose(str, " getMusicChannelStatusFirst.out=" + musicChannelStatusFirstR);
        return musicChannelStatusFirstR;
    }

    public int getMusicChannelStatusSecond(int i) {
        int musicChannelStatusSecondR;
        String str = w0;
        LogUtil.verbose(str, " getMusicChannelStatusSecond.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicChannelStatusSecondR = carVendorInstrumentManager.getMusicChannelStatusSecondR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicChannelStatusSecondR = -1;
            }
            LogUtil.verbose(str, " getMusicChannelStatusSecond.out=" + musicChannelStatusSecondR);
            return musicChannelStatusSecondR;
        }
        LogUtil.verbose(str, " getMusicChannelStatusSecond.null == mInstrumentManager");
        musicChannelStatusSecondR = -1;
        LogUtil.verbose(str, " getMusicChannelStatusSecond.out=" + musicChannelStatusSecondR);
        return musicChannelStatusSecondR;
    }

    public int getMusicControlStatusFirst(int i) {
        int musicControlStatusFirstR;
        String str = w0;
        LogUtil.verbose(str, " getMusicControlStatusFirst.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicControlStatusFirstR = carVendorInstrumentManager.getMusicControlStatusFirstR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicControlStatusFirstR = -1;
            }
            LogUtil.verbose(str, " getMusicControlStatusFirst.out=" + musicControlStatusFirstR);
            return musicControlStatusFirstR;
        }
        LogUtil.verbose(str, " getMusicControlStatusFirst.null == mInstrumentManager");
        musicControlStatusFirstR = -1;
        LogUtil.verbose(str, " getMusicControlStatusFirst.out=" + musicControlStatusFirstR);
        return musicControlStatusFirstR;
    }

    public int getMusicControlStatusSecond(int i) {
        int musicControlStatusSecondR;
        String str = w0;
        LogUtil.verbose(str, " getMusicControlStatusSecond.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicControlStatusSecondR = carVendorInstrumentManager.getMusicControlStatusSecondR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicControlStatusSecondR = -1;
            }
            LogUtil.verbose(str, " getMusicControlStatusSecond.out=" + musicControlStatusSecondR);
            return musicControlStatusSecondR;
        }
        LogUtil.verbose(str, " getMusicControlStatusSecond.null == mInstrumentManager");
        musicControlStatusSecondR = -1;
        LogUtil.verbose(str, " getMusicControlStatusSecond.out=" + musicControlStatusSecondR);
        return musicControlStatusSecondR;
    }

    public int getMusicProgressFirst(int i) {
        int musicProgressFirstR;
        String str = w0;
        LogUtil.verbose(str, " getMusicProgressFirst.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicProgressFirstR = carVendorInstrumentManager.getMusicProgressFirstR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicProgressFirstR = -1;
            }
            LogUtil.verbose(str, " getMusicProgressFirst.out=" + musicProgressFirstR);
            return musicProgressFirstR;
        }
        LogUtil.verbose(str, " getMusicProgressFirst.null == mInstrumentManager");
        musicProgressFirstR = -1;
        LogUtil.verbose(str, " getMusicProgressFirst.out=" + musicProgressFirstR);
        return musicProgressFirstR;
    }

    public int getMusicProgressSecond(int i) {
        int musicProgressSecondR;
        String str = w0;
        LogUtil.verbose(str, " getMusicProgressSecond.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicProgressSecondR = carVendorInstrumentManager.getMusicProgressSecondR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicProgressSecondR = -1;
            }
            LogUtil.verbose(str, " getMusicProgressSecond.out=" + musicProgressSecondR);
            return musicProgressSecondR;
        }
        LogUtil.verbose(str, " getMusicProgressSecond.null == mInstrumentManager");
        musicProgressSecondR = -1;
        LogUtil.verbose(str, " getMusicProgressSecond.out=" + musicProgressSecondR);
        return musicProgressSecondR;
    }

    public int getMusicResourceConfigFirst(int i) {
        int musicResourceConfigFirstR;
        String str = w0;
        LogUtil.verbose(str, " getMusicResourceConfigFirst.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicResourceConfigFirstR = carVendorInstrumentManager.getMusicResourceConfigFirstR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicResourceConfigFirstR = -1;
            }
            LogUtil.verbose(str, " getMusicResourceConfigFirst.out=" + musicResourceConfigFirstR);
            return musicResourceConfigFirstR;
        }
        LogUtil.verbose(str, " getMusicResourceConfigFirst.null == mInstrumentManager");
        musicResourceConfigFirstR = -1;
        LogUtil.verbose(str, " getMusicResourceConfigFirst.out=" + musicResourceConfigFirstR);
        return musicResourceConfigFirstR;
    }

    public int getMusicResourceConfigSecond(int i) {
        int musicResourceConfigSecondR;
        String str = w0;
        LogUtil.verbose(str, " getMusicResourceConfigSecond.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                musicResourceConfigSecondR = carVendorInstrumentManager.getMusicResourceConfigSecondR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                musicResourceConfigSecondR = -1;
            }
            LogUtil.verbose(str, " getMusicResourceConfigSecond.out=" + musicResourceConfigSecondR);
            return musicResourceConfigSecondR;
        }
        LogUtil.verbose(str, " getMusicResourceConfigSecond.null == mInstrumentManager");
        musicResourceConfigSecondR = -1;
        LogUtil.verbose(str, " getMusicResourceConfigSecond.out=" + musicResourceConfigSecondR);
        return musicResourceConfigSecondR;
    }

    public int getMuteStateFirst(int i) {
        int muteStateFirstR;
        String str = w0;
        LogUtil.verbose(str, " getMuteStateFirst.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                muteStateFirstR = carVendorInstrumentManager.getMuteStateFirstR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                muteStateFirstR = -1;
            }
            LogUtil.verbose(str, " getMuteStateFirst.out=" + muteStateFirstR);
            return muteStateFirstR;
        }
        LogUtil.verbose(str, " getMuteStateFirst.null == mInstrumentManager");
        muteStateFirstR = -1;
        LogUtil.verbose(str, " getMuteStateFirst.out=" + muteStateFirstR);
        return muteStateFirstR;
    }

    public int getMuteStateSecond(int i) {
        int muteStateSecondR;
        String str = w0;
        LogUtil.verbose(str, " getMuteStateSecond.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                muteStateSecondR = carVendorInstrumentManager.getMuteStateSecondR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                muteStateSecondR = -1;
            }
            LogUtil.verbose(str, " getMuteStateSecond.out=" + muteStateSecondR);
            return muteStateSecondR;
        }
        LogUtil.verbose(str, " getMuteStateSecond.null == mInstrumentManager");
        muteStateSecondR = -1;
        LogUtil.verbose(str, " getMuteStateSecond.out=" + muteStateSecondR);
        return muteStateSecondR;
    }

    public int getOilHint() {
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilHint = carVendorHealthManager.getOilHint();
            LogUtil.verbose(w0, "getOilHint=" + oilHint);
            return oilHint;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getOilLevel() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilLevel");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850635, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOilLevel prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOilLifetimeBattery() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilLifetimeBattery");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850627, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOilLifetimeBrake prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOilLifetimeBrake() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilLifetimeBrake");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850629, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOilLifetimeBrake prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOilLifetimeElec() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilLifetimeElec");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850628, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSeatVentilatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOilLifetimeEngine() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilLifetimeEngine");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850625, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOilLifetimeEngine prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOilLifetimeNew(int i) {
        int oilLifetimeNewOilLifeR;
        String str = w0;
        LogUtil.verbose(str, " getOilLifetimeNew.in");
        CarVendorHealthManager carVendorHealthManager = this.d;
        int i2 = -1;
        if (carVendorHealthManager != null) {
            try {
                if (i == 0) {
                    oilLifetimeNewOilLifeR = carVendorHealthManager.getOilLifetimeNewOilLifeR();
                } else if (i == 1) {
                    oilLifetimeNewOilLifeR = carVendorHealthManager.getOilLifetimeNewTransmissionR();
                } else if (i == 2) {
                    oilLifetimeNewOilLifeR = carVendorHealthManager.getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR();
                } else if (i == 3) {
                    oilLifetimeNewOilLifeR = carVendorHealthManager.getOilLifetimeNewBladeBatteryCoolantR();
                } else if (i == 4) {
                    oilLifetimeNewOilLifeR = carVendorHealthManager.getOilLifetimeNewLongLifeCoolantLongLifeCoolantR();
                }
                i2 = oilLifetimeNewOilLifeR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " getOilLifetimeNew.null == mCarVendorHealthManager");
        }
        LogUtil.verbose(str, " getOilLifetimeNew.out=" + i2);
        return i2;
    }

    public int getOilLifetimeTransmission() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilLifetimeTransmission");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850626, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOilLifetimeTransmission prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOilMaintainanceDialogHint(int i) {
        int oilMaintainanceDialogHintOilLifeR;
        String str = w0;
        LogUtil.verbose(str, " getOilMaintainanceDialogHint.in");
        CarVendorHealthManager carVendorHealthManager = this.d;
        int i2 = -1;
        if (carVendorHealthManager != null) {
            try {
                if (i == 0) {
                    oilMaintainanceDialogHintOilLifeR = carVendorHealthManager.getOilMaintainanceDialogHintOilLifeR();
                } else if (i == 1) {
                    oilMaintainanceDialogHintOilLifeR = carVendorHealthManager.getOilMaintainanceDialogHintTransmissionR();
                } else if (i == 2) {
                    oilMaintainanceDialogHintOilLifeR = carVendorHealthManager.getOilMaintainanceDialogHintBladeBatteryCoolantR();
                } else if (i == 3) {
                    oilMaintainanceDialogHintOilLifeR = carVendorHealthManager.getOilMaintainanceDialogHintLongLifeCoolantR();
                } else if (i == 4) {
                    oilMaintainanceDialogHintOilLifeR = carVendorHealthManager.getOilMaintainanceDialogHintBrakeFluidLifeR();
                }
                i2 = oilMaintainanceDialogHintOilLifeR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " getOilMaintainanceDialogHint.null == mCarVendorHealthManager");
        }
        LogUtil.verbose(str, " getOilMaintainanceDialogHint.out=" + i2);
        return i2;
    }

    public int getOilMaintainanceHintBrake() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilMaintainanceHintBrake");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilMaintainanceHintBrakeFluidLife = carVendorHealthManager.getOilMaintainanceHintBrakeFluidLife();
            LogUtil.verbose(str, "getOilMaintainanceHintBrakeFluidLife=" + oilMaintainanceHintBrakeFluidLife);
            return oilMaintainanceHintBrakeFluidLife;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getOilMaintainanceHintCoolBladeBattery() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilMaintainanceHintCoolBladeBattery");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilMaintainanceHintBladeBatteryCoolant = carVendorHealthManager.getOilMaintainanceHintBladeBatteryCoolant();
            LogUtil.verbose(str, "getOilMaintainanceHintBladeBatteryCoolant=" + oilMaintainanceHintBladeBatteryCoolant);
            return oilMaintainanceHintBladeBatteryCoolant;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getOilMaintainanceHintCoolMotorElec() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilMaintainanceHintCoolMotorElec");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilMaintainanceHintLongLifeCoolant = carVendorHealthManager.getOilMaintainanceHintLongLifeCoolant();
            LogUtil.verbose(str, "getOilMaintainanceHintLongLifeCoolant=" + oilMaintainanceHintLongLifeCoolant);
            return oilMaintainanceHintLongLifeCoolant;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getOilMaintainanceHintEngine() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilMaintainanceHintEngine");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilMaintainanceHintOilLife = carVendorHealthManager.getOilMaintainanceHintOilLife();
            LogUtil.verbose(str, "getOilMaintainanceHintOilLife=" + oilMaintainanceHintOilLife);
            return oilMaintainanceHintOilLife;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getOilMaintainanceHintTransmission() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilMaintainanceHintTransmission");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilMaintainanceHintTransmissionOilLife = carVendorHealthManager.getOilMaintainanceHintTransmissionOilLife();
            LogUtil.verbose(str, "getOilMaintainanceHintTransmissionOilLife=" + oilMaintainanceHintTransmissionOilLife);
            return oilMaintainanceHintTransmissionOilLife;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getOilUpdateState() {
        String str = w0;
        LogUtil.verbose(str, "Service getOilUpdateState");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int oilUpdateState = carVendorHealthManager.getOilUpdateState();
            LogUtil.verbose(str, "getOilUpdateState=" + oilUpdateState);
            return oilUpdateState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getParkBrakeSwitch() {
        String str = w0;
        LogUtil.verbose(str, "getParkBrakeSwitch");
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager == null) {
            return 0;
        }
        try {
            int parkBrakeSwitch = carVendorGearboxManager.getParkBrakeSwitch();
            LogUtil.verbose(str, "getParkBrakeSwitch=" + parkBrakeSwitch);
            return parkBrakeSwitch;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengnerSeatOccupied() {
        return this.V;
    }

    @Deprecated
    public int getPhysicalKeyBindResult() {
        return 0;
    }

    public int getRearFogLightState() {
        int rearFogLightStateR;
        String str = w0;
        LogUtil.verbose(str, " getRearFogLightState.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                rearFogLightStateR = carVendorInstrumentManager.getRearFogLightStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFogLightStateR = -1;
            }
            LogUtil.verbose(str, " getRearFogLightState.out=" + rearFogLightStateR);
            return rearFogLightStateR;
        }
        LogUtil.verbose(str, " getRearFogLightState.null == mInstrumentManager");
        rearFogLightStateR = -1;
        LogUtil.verbose(str, " getRearFogLightState.out=" + rearFogLightStateR);
        return rearFogLightStateR;
    }

    public int getRearWiperCheckState() {
        String str = w0;
        LogUtil.verbose(str, "Service getRearWiperCheckState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850638, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearWiperCheckState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightChildLockState() {
        int rightChildLockStateR;
        String str = w0;
        LogUtil.verbose(str, " getRightChildLockState.in");
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager != null) {
            try {
                rightChildLockStateR = carVendorDoorlockManager.getRightChildLockStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightChildLockStateR = -1;
            }
            LogUtil.verbose(str, " getRightChildLockState.out=" + rightChildLockStateR);
            return rightChildLockStateR;
        }
        LogUtil.verbose(str, " getRightChildLockState.null == mCarVendorDoorlockManager");
        rightChildLockStateR = -1;
        LogUtil.verbose(str, " getRightChildLockState.out=" + rightChildLockStateR);
        return rightChildLockStateR;
    }

    public int getRoofTopOperationSt() {
        return this.B;
    }

    public int getSeatMassageLevel(int i) {
        return 0;
    }

    public int getSeatMassageType(int i) {
        return 0;
    }

    public int getSeatVentilationState(int i) {
        return 0;
    }

    public int getSeatWarmState(int i) {
        return 0;
    }

    public int getSpeedUnit() {
        String str = w0;
        LogUtil.verbose(str, "Service getSpeedUnit");
        if (this.d == null) {
            return 0;
        }
        try {
            int speedUnitR = this.f25a.getSpeedUnitR();
            LogUtil.verbose(str, "getSpeedUnitR=" + speedUnitR);
            return speedUnitR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int getSpeedUpCountState() {
        String str = w0;
        LogUtil.verbose(str, "getSpeedUpCountState");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int speedUpCountStateR = carVendorInstrumentManager.getSpeedUpCountStateR();
            LogUtil.verbose(str, "getSpeedUpCountStateR=" + speedUpCountStateR);
            return speedUpCountStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSpeedUpFormIns() {
        int speedUpFormInsR;
        String str = w0;
        LogUtil.verbose(str, " getSpeedUpFormIns.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                speedUpFormInsR = carVendorInstrumentManager.getSpeedUpFormInsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                speedUpFormInsR = -1;
            }
        } else {
            speedUpFormInsR = -1;
        }
        LogUtil.verbose(str, " getSpeedUpFormIns.out = " + speedUpFormInsR);
        return speedUpFormInsR;
    }

    public int getSportModeState() {
        String str = w0;
        LogUtil.verbose(str, "getSportModeState");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int sportModeState = carVendorInstrumentManager.getSportModeState();
            LogUtil.verbose(str, "getSportModeState=" + sportModeState);
            return sportModeState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteeringWheelWarmState() {
        return 0;
    }

    public int getTailgateDetectionSport() {
        return this.N;
    }

    public int getTailgateOpenStatus() {
        return this.M;
    }

    public int getTotalMileage() {
        String str = w0;
        LogUtil.verbose(str, "Service getTotalMileage");
        if (this.d == null) {
            return 0;
        }
        try {
            int totalMileageR = this.f25a.getTotalMileageR();
            LogUtil.verbose(str, "getTotalMileageR=" + totalMileageR);
            return totalMileageR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int getTransportMode() {
        String str = w0;
        LogUtil.verbose(str, "Service getTransportMode");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850659, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTransportMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTransportModeStateHint() {
        String str = w0;
        LogUtil.verbose(str, "Service getTransportModeStateHint");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            int transportModeStateHintR = carVendorHealthManager.getTransportModeStateHintR();
            LogUtil.verbose(str, "getTransportModeStateHintR=" + transportModeStateHintR);
            return transportModeStateHintR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getTyreFaultLocation() {
        String str = w0;
        LogUtil.verbose(str, "Service getTyreFaultLocation");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557850642, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTyreFaultLocation prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getUnit() {
        String str = w0;
        LogUtil.verbose(str, "getUnit");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitTemperatureInstrumentR = carVendorInstrumentManager.getUnitTemperatureInstrumentR();
            LogUtil.verbose(str, "getUnitTemperatureInstrumentR=" + unitTemperatureInstrumentR);
            return unitTemperatureInstrumentR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitFuelConsumptionAndDistanceInstrument() {
        String str = w0;
        LogUtil.verbose(str, "getUnitFuelConsumptionAndDistanceInstrument");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitFuelConsumptionAndDistanceInstrumentR = carVendorInstrumentManager.getUnitFuelConsumptionAndDistanceInstrumentR();
            LogUtil.verbose(str, "getUnitFuelConsumptionAndDistanceInstrumentR=" + unitFuelConsumptionAndDistanceInstrumentR);
            return unitFuelConsumptionAndDistanceInstrumentR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitFuelConsumptionAndDistanceLeft() {
        String str = w0;
        LogUtil.verbose(str, "getUnitFuelConsumptionAndDistanceLeft");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitFuelConsumptionAndDistanceLeftR = carVendorInstrumentManager.getUnitFuelConsumptionAndDistanceLeftR();
            LogUtil.verbose(str, "getUnitFuelConsumptionAndDistanceLeftR=" + unitFuelConsumptionAndDistanceLeftR);
            return unitFuelConsumptionAndDistanceLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitPowerInstrument() {
        String str = w0;
        LogUtil.verbose(str, "getUnitPowerInstrument");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitPowerInstrumentR = carVendorInstrumentManager.getUnitPowerInstrumentR();
            LogUtil.verbose(str, "getUnitPowerInstrumentR=" + unitPowerInstrumentR);
            return unitPowerInstrumentR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitPowerLeft() {
        String str = w0;
        LogUtil.verbose(str, "getUnitPowerLeft");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitPowerLeftR = carVendorInstrumentManager.getUnitPowerLeftR();
            LogUtil.verbose(str, "getUnitPowerLeftR=" + unitPowerLeftR);
            return unitPowerLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitPressureInstrument() {
        String str = w0;
        LogUtil.verbose(str, "getUnitPressureInstrument");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitPressureInstrumentR = carVendorInstrumentManager.getUnitPressureInstrumentR();
            LogUtil.verbose(str, "getUnitPressureInstrumentR=" + unitPressureInstrumentR);
            return unitPressureInstrumentR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitPressureLeft() {
        String str = w0;
        LogUtil.verbose(str, "getUnitPressureLeft");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitPressureLeftR = carVendorInstrumentManager.getUnitPressureLeftR();
            LogUtil.verbose(str, "getUnitPressureLeftR=" + unitPressureLeftR);
            return unitPressureLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUnitTemperatureLeft() {
        String str = w0;
        LogUtil.verbose(str, "getUnitTemperatureLeftR");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            int unitTemperatureLeftR = carVendorInstrumentManager.getUnitTemperatureLeftR();
            LogUtil.verbose(str, "getUnitTemperatureLeftR=" + unitTemperatureLeftR);
            return unitTemperatureLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWinFlOperationSt() {
        return this.x;
    }

    public int getWinFlState() {
        return this.t;
    }

    public int getWinFrOperationSt() {
        return this.y;
    }

    public int getWinFrState() {
        return this.u;
    }

    public int getWinRlOperationSt() {
        return this.z;
    }

    public int getWinRlState() {
        return this.v;
    }

    public int getWinRrOperationSt() {
        return this.A;
    }

    public int getWinRrState() {
        return this.w;
    }

    public int getWinSunroofOpenDegree() {
        return this.C;
    }

    public int getWindowRoofTopPos() {
        return this.s;
    }

    public int getWindowRowOneLeftPos() {
        return this.o;
    }

    public int getWindowRowOneRightPos() {
        return this.p;
    }

    public int getWindowRowTwoLeftPos() {
        return this.q;
    }

    public int getWindowRowTwoRightPos() {
        return this.r;
    }

    public int getWindowSunShadeMove() {
        return this.E;
    }

    public int getWindowSunShadePos() {
        return this.D;
    }

    @Override // com.ts.appservice.caradapter.d.s
    public void i(RearviewmirrorEventChangeValue rearviewmirrorEventChangeValue) {
        String str = w0;
        if (rearviewmirrorEventChangeValue == null) {
            LogUtil.error(str, "onRearViewMirrorChangeEvent:value=null");
            return;
        }
        int propertyId = rearviewmirrorEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onRearViewMirrorChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 11;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = rearviewmirrorEventChangeValue.getAreaId();
        messageObtain.obj = rearviewmirrorEventChangeValue;
        this.X.sendMessage(messageObtain);
    }

    public boolean isAtmosphereLight() {
        return this.F;
    }

    public boolean isCustomColor() {
        return this.H;
    }

    public boolean isDriverDoorOpen() {
        return this.k;
    }

    public boolean isFollowingDrivingMode() {
        return this.J;
    }

    public boolean isFollowingMusicMode() {
        return this.K;
    }

    public boolean isHoodDoorOpen() {
        return this.i;
    }

    public boolean isPassengerDoorOpen() {
        return this.l;
    }

    public boolean isRearDoorOpen() {
        return this.j;
    }

    public boolean isRowTwoLeftDoorOpen() {
        return this.m;
    }

    public boolean isRowTwoRightDoorOpen() {
        return this.n;
    }

    public int resetOilLifetimeBattery() {
        LogUtil.verbose(w0, "Service resetOilLifetimeBattery");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.resetOilLifetimeBrake(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int resetOilLifetimeBrake() {
        LogUtil.verbose(w0, "Service resetOilLifetimeBrake");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.resetOilLifetimeELec(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int resetOilLifetimeELec() {
        LogUtil.verbose(w0, "Service resetOilLifetimeELec");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.resetOilLifetimeBattery(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int resetOilLifetimeEngine() {
        LogUtil.verbose(w0, "Service resetOilLifetimeEngine");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.resetOilLifetimeEngine(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int resetOilLifetimeTransmission() {
        LogUtil.verbose(w0, "Service resetOilLifetimeTransmission");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.resetOilLifetimeTransmission(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int resetTirePressure() {
        LogUtil.verbose(w0, "Service resetTirePressure");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            carVendorHealthManager.resetTirePressure(0);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void s() {
        com.ts.appservice.caradapter.a.c cVar = this.f;
        if (cVar != null) {
            this.h = false;
            cVar.g();
        }
        z();
    }

    @Deprecated
    public void setAccountActive(int i) {
    }

    @Deprecated
    public void setAccountId(int i) {
    }

    @Deprecated
    public void setAccountInfo(byte[] bArr) {
    }

    public int setAllDoorsLock(int i) {
        LogUtil.verbose(w0, "Service setAllDoorsLock" + i);
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return 0;
        }
        try {
            carVendorDoorlockManager.setAllDoorsLock(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int setAreaInfo(int i) {
        LogUtil.info(w0, "setAreaInfo");
        return 0;
    }

    public void setAtmosphereLight(boolean z) {
    }

    public void setAtmosphereLightAndDrivingMode(byte[] bArr) {
    }

    public void setAtmosphereLightValue(int i) {
    }

    public void setAtmosphereMode(int i) {
    }

    public int setAutoExternalRearMirrorFoldState(int i) {
        LogUtil.verbose(w0, "Service setAutoExternalRearMirrorFoldState" + i);
        CarVendorRearviewmirrorManager carVendorRearviewmirrorManager = this.c;
        if (carVendorRearviewmirrorManager == null) {
            return 0;
        }
        try {
            carVendorRearviewmirrorManager.setAutoExternalRearMirrorFoldStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setBreathableButton() {
    }

    public void setCabinRearDoorPos(int i) {
    }

    public void setCloseTailgate(int i) {
    }

    public void setCustomColor(int i) {
    }

    public void setCustomColorState(boolean z) {
    }

    public int setDayNightMode(int i) {
        LogUtil.verbose(w0, "setDayNightModeS" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setDayNightModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public void setDeleteEcuAccount(int i) {
    }

    public int setDoorLockStatusAreaChildlockLeftS(int i) {
        LogUtil.verbose(w0, "setDoorLockStatusAreaChildlockLeftS=" + i);
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            carVendorDoorlockManager.setDoorLockStatusAreaChildlockLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setDoorLockStatusAreaChildlockRightS(int i) {
        LogUtil.verbose(w0, "setDoorLockStatusAreaChildlockRightS=" + i);
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            carVendorDoorlockManager.setDoorLockStatusAreaChildlockRightStatusS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Deprecated
    public void setDriveAcuMsmSave(int i) {
    }

    public int setEPBState(int i) {
        LogUtil.verbose(w0, "Service setEPBState" + i);
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.setEPBStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setElectricTailgateOpen(boolean z) {
    }

    public int setEpbStateGearBox(int i) {
        LogUtil.verbose(w0, " setEPBState.in" + i);
        CarVendorGearboxManager carVendorGearboxManager = this.e;
        if (carVendorGearboxManager != null) {
            try {
                carVendorGearboxManager.setEpbStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public void setFollowingDrivingMode(boolean z) {
    }

    public void setFollowingMusicMode(boolean z) {
    }

    public void setFraganceConcentration(int i) {
    }

    public void setFraganceTaste(int i) {
    }

    public int setFrontWiperCheckState(int i) {
        LogUtil.verbose(w0, "Service setFrontWiperCheckState" + i);
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            carVendorHealthManager.setFrontWiperCheckStateS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setHdcSwitch(int i) {
    }

    public int setHeadlightControlMode(int i) {
        String str = w0;
        LogUtil.verbose(str, " setHeadlightControlMode.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                carVendorInstrumentManager.setHeadlightControlModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setHeadlightControlMode.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setHeadlightControlMode.out=0");
        return 0;
    }

    public int setINSAdasCenterDisplay(int i) {
        LogUtil.verbose(w0, "setINSAdasCenterDisplay" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setINSAdasCenterDisplaySend4C1(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setINSAdasCenterDisplayRespond(int i) {
        LogUtil.verbose(w0, "setINSAdasCenterDisplayRespond");
        return 0;
    }

    public int setINSAdasCenterDisplaySend(int i) {
        LogUtil.verbose(w0, "setINSAdasCenterDisplaySend" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setINSAdasCenterDisplaySend26F(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setINSCenterDisplay(int i) {
        LogUtil.verbose(w0, "getSportModeState" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setINSCenterDisplayS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setINSNavDisplayMode(int i) {
        LogUtil.verbose(w0, "setINSNavDisplayMode" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setINSNavDisplayModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setInstrumentAutoDimmState(int i) {
        LogUtil.verbose(w0, "setInstrumentAutoDimmState" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setInstrumentAutoDimmStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setInstrumentBrightness(int i) {
        LogUtil.verbose(w0, "setInstrumentBrightness" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setInstrumentBrightnessS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setInstrumentDisplayLanguageS(int i) {
        LogUtil.verbose(w0, "setInstrumentDisplayLanguageS" + i);
        return 0;
    }

    public int setInstrumentTheme(int i) {
        LogUtil.verbose(w0, "setInstrumentTheme" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setInstrumentThemeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMaintenanceInfo(int i) {
        LogUtil.verbose(w0, "setInstrumentAutoDimmState" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setInstrumentAutoDimmStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMaintenanceInfoMileState(int i) {
        LogUtil.verbose(w0, "setMaintenanceInfoTimeState" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setMaintenanceInfoMileStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMaintenanceInfoTimeState(int i) {
        LogUtil.verbose(w0, "setMaintenanceInfoTimeState" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setMaintenanceInfoTimeState(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMasterVolume(int i) {
        String str = w0;
        LogUtil.verbose(str, " setMasterVolume.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                carVendorInstrumentManager.setMasterVolumeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setMasterVolume.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setMasterVolume.out=0");
        return 0;
    }

    public int setMilesType(int i) {
        LogUtil.verbose(w0, "setMilesType" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setMilesTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMusicInfo(int i, int i2, int i3, int i4) {
        String str = w0;
        LogUtil.verbose(str, " setMusicInfo.in");
        if (this.f25a != null) {
            try {
                LogUtil.verbose(str, " setMusicInfo array:" + Arrays.toString(new int[]{i, i2, i3, i4}));
                this.f25a.setMusicInfoPlayStateS(i);
                this.f25a.setMusicInfoRadioStateS(i2);
                this.f25a.setMusicInfoPlayProgressS(i3);
                this.f25a.setMusicInfoMusicSourceS(i4);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setMusicInfo.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setMusicInfo.out=0");
        return 0;
    }

    public int setMusicName(String str) {
        String str2 = w0;
        LogUtil.verbose(str2, " setMusicName.in" + str);
        int i = 0;
        if (this.f25a == null) {
            return 0;
        }
        try {
            byte[] bArrB = B(str);
            int length = 94;
            if (bArrB.length < 94) {
                length = bArrB.length;
            }
            int i2 = 7;
            int i3 = ((length + 1) / 7) + 1;
            byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i3, 7);
            byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, (length / 7) + 1, 6);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            boolean z = true;
            while (i4 < i3) {
                if (i4 == 0) {
                    i2 = 6;
                }
                int i7 = i;
                while (i7 < i2) {
                    if (i5 < length) {
                        bArr[i4][i7] = bArrB[i5];
                        i6 += bArr[i4][i7];
                        i5++;
                    } else {
                        if (z) {
                            i6 &= 255;
                            bArr[i4][i7] = (byte) i6;
                            LogUtil.optionError(str2, " check = " + i6);
                            i7++;
                            z = false;
                        }
                        if (i7 < i2) {
                            bArr[i4][i7] = -1;
                        }
                    }
                    i7++;
                }
                i4++;
                i = 0;
                i2 = 7;
            }
            LogUtil.optionError(str2, " length.in" + length);
            for (int i8 = 0; i8 < bArr.length; i8++) {
                for (int i9 = 0; i9 < 6; i9++) {
                    LogUtil.optionError(str2, " newBytes.in" + ((int) bArr[i8][i9]) + " ");
                    bArr2[0][i9] = bArr[0][i9];
                }
                switch (i8) {
                    case 0:
                        this.f25a.setMusicNameS(new long[]{length, r(bArr2[i8])});
                        for (int i10 = 0; i10 < 6; i10++) {
                            LogUtil.optionError(str2, " setMusicNameS.in" + ((int) bArr2[i8][i10]) + " ");
                        }
                        continue;
                        break;
                    case 1:
                        this.f25a.setMusicNameSb2(bArr[i8]);
                        for (int i11 = 0; i11 < bArr[i8].length; i11++) {
                            LogUtil.optionError(str2, " setMusicNameSb2.in" + ((int) bArr[i8][i11]) + " ");
                        }
                        break;
                    case 2:
                        this.f25a.setMusicNameSb3(bArr[i8]);
                        for (int i12 = 0; i12 < bArr[i8].length; i12++) {
                            LogUtil.optionError(str2, " setMusicNameSb3.in" + ((int) bArr[i8][i12]) + " ");
                        }
                        break;
                    case 3:
                        this.f25a.setMusicNameSb4(bArr[i8]);
                        for (int i13 = 0; i13 < bArr[i8].length; i13++) {
                            LogUtil.optionError(str2, " setMusicNameSb4.in" + ((int) bArr[i8][i13]) + " ");
                        }
                        break;
                    case 4:
                        this.f25a.setMusicNameSb5(bArr[i8]);
                        for (int i14 = 0; i14 < bArr[i8].length; i14++) {
                            LogUtil.optionError(str2, " setMusicNameSb5.in" + ((int) bArr[i8][i14]) + " ");
                        }
                        break;
                    case 5:
                        this.f25a.setMusicNameSb6(bArr[i8]);
                        for (int i15 = 0; i15 < bArr[i8].length; i15++) {
                            LogUtil.optionError(str2, " setMusicNameSb6.in" + ((int) bArr[i8][i15]) + " ");
                        }
                        break;
                    case 6:
                        this.f25a.setMusicNameSb7(bArr[i8]);
                        for (int i16 = 0; i16 < bArr[i8].length; i16++) {
                            LogUtil.optionError(str2, " setMusicNameSb7.in" + ((int) bArr[i8][i16]) + " ");
                        }
                        break;
                    case 7:
                        this.f25a.setMusicNameSb8(bArr[i8]);
                        for (int i17 = 0; i17 < bArr[i8].length; i17++) {
                            LogUtil.optionError(str2, " setMusicNameSb8.in" + ((int) bArr[i8][i17]) + " ");
                        }
                        break;
                    case 8:
                        this.f25a.setMusicNameSb9(bArr[i8]);
                        for (int i18 = 0; i18 < bArr[i8].length; i18++) {
                            LogUtil.optionError(str2, " setMusicNameSb9.in" + ((int) bArr[i8][i18]) + " ");
                        }
                        break;
                    case 9:
                        this.f25a.setMusicNameSbA(bArr[i8]);
                        for (int i19 = 0; i19 < bArr[i8].length; i19++) {
                            LogUtil.optionError(str2, " setMusicNameSbA.in" + ((int) bArr[i8][i19]) + " ");
                        }
                        break;
                    case 10:
                        this.f25a.setMusicNameSbB(bArr[i8]);
                        for (int i20 = 0; i20 < bArr[i8].length; i20++) {
                            LogUtil.optionError(str2, " setMusicNameSbB.in" + ((int) bArr[i8][i20]) + " ");
                        }
                        break;
                    case 11:
                        this.f25a.setMusicNameSbC(bArr[i8]);
                        for (int i21 = 0; i21 < bArr[i8].length; i21++) {
                            LogUtil.optionError(str2, " setMusicNameSbC.in" + ((int) bArr[i8][i21]) + " ");
                        }
                        break;
                    case 12:
                        this.f25a.setMusicNameSbD(bArr[i8]);
                        for (int i22 = 0; i22 < bArr[i8].length; i22++) {
                            LogUtil.optionError(str2, " setMusicNameSbD.in" + ((int) bArr[i8][i22]) + " ");
                        }
                        break;
                    case 13:
                        this.f25a.setMusicNameSbE(bArr[i8]);
                        for (int i23 = 0; i23 < bArr[i8].length; i23++) {
                            LogUtil.optionError(str2, " setMusicNameSbE.in" + ((int) bArr[i8][i23]) + " ");
                        }
                        break;
                }
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setMusicRhythmMode(int i) {
    }

    public int setMusicSingerName(String str) {
        String str2 = w0;
        LogUtil.verbose(str2, " setMusicSingerName.in" + str);
        int i = 0;
        if (this.f25a == null) {
            return 0;
        }
        try {
            byte[] bArrB = B(str);
            int length = 94;
            if (bArrB.length < 94) {
                length = bArrB.length;
            }
            int i2 = 7;
            int i3 = ((length + 1) / 7) + 1;
            byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i3, 7);
            int i4 = 6;
            byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, (length / 7) + 1, 6);
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            boolean z = true;
            while (i5 < i3) {
                if (i5 == 0) {
                    i2 = i4;
                }
                int i8 = i7;
                while (i < i2) {
                    if (i6 < length) {
                        bArr[i5][i] = bArrB[i6];
                        i8 += bArr[i5][i];
                        i6++;
                    } else {
                        if (z) {
                            i8 &= 255;
                            bArr[i5][i] = (byte) i8;
                            LogUtil.optionError(str2, " check = " + i8);
                            i++;
                            z = false;
                        }
                        if (i < i2) {
                            bArr[i5][i] = -1;
                        }
                    }
                    i++;
                }
                i5++;
                i7 = i8;
                i = 0;
                i2 = 7;
                i4 = 6;
            }
            LogUtil.optionError(str2, " length.in" + length);
            for (int i9 = 0; i9 < bArr.length; i9++) {
                for (int i10 = 0; i10 < 6; i10++) {
                    LogUtil.optionError(str2, " newBytes.in" + ((int) bArr[i9][i10]) + " ");
                    bArr2[0][i10] = bArr[0][i10];
                }
                switch (i9) {
                    case 0:
                        this.f25a.setMusicSingerNameS(new long[]{length, r(bArr2[i9])});
                        for (int i11 = 0; i11 < 6; i11++) {
                            LogUtil.optionError(str2, " setMusicSingerName.in" + ((int) bArr2[i9][i11]) + " ");
                        }
                        continue;
                        break;
                    case 1:
                        this.f25a.setMusicSingerNameS92(bArr[i9]);
                        for (int i12 = 0; i12 < bArr[i9].length; i12++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i12]) + " ");
                        }
                        break;
                    case 2:
                        this.f25a.setMusicSingerNameS93(bArr[i9]);
                        for (int i13 = 0; i13 < bArr[i9].length; i13++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i13]) + " ");
                        }
                        break;
                    case 3:
                        this.f25a.setMusicSingerNameS94(bArr[i9]);
                        for (int i14 = 0; i14 < bArr[i9].length; i14++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i14]) + " ");
                        }
                        break;
                    case 4:
                        this.f25a.setMusicSingerNameS95(bArr[i9]);
                        for (int i15 = 0; i15 < bArr[i9].length; i15++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i15]) + " ");
                        }
                        break;
                    case 5:
                        this.f25a.setMusicSingerNameS96(bArr[i9]);
                        for (int i16 = 0; i16 < bArr[i9].length; i16++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i16]) + " ");
                        }
                        break;
                    case 6:
                        this.f25a.setMusicSingerNameS97(bArr[i9]);
                        for (int i17 = 0; i17 < bArr[i9].length; i17++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i17]) + " ");
                        }
                        break;
                    case 7:
                        this.f25a.setMusicSingerNameS98(bArr[i9]);
                        for (int i18 = 0; i18 < bArr[i9].length; i18++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i18]) + " ");
                        }
                        break;
                    case 8:
                        this.f25a.setMusicSingerNameS99(bArr[i9]);
                        for (int i19 = 0; i19 < bArr[i9].length; i19++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i19]) + " ");
                        }
                        break;
                    case 9:
                        this.f25a.setMusicSingerNameS9A(bArr[i9]);
                        for (int i20 = 0; i20 < bArr[i9].length; i20++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i20]) + " ");
                        }
                        break;
                    case 10:
                        this.f25a.setMusicSingerNameS9B(bArr[i9]);
                        for (int i21 = 0; i21 < bArr[i9].length; i21++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i21]) + " ");
                        }
                        break;
                    case 11:
                        this.f25a.setMusicSingerNameS9C(bArr[i9]);
                        for (int i22 = 0; i22 < bArr[i9].length; i22++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i22]) + " ");
                        }
                        break;
                    case 12:
                        this.f25a.setMusicSingerNameS9D(bArr[i9]);
                        for (int i23 = 0; i23 < bArr[i9].length; i23++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i23]) + " ");
                        }
                        break;
                    case 13:
                        this.f25a.setMusicSingerNameS9E(bArr[i9]);
                        for (int i24 = 0; i24 < bArr[i9].length; i24++) {
                            LogUtil.optionError(str2, " setMusicSingerNameS92.in" + ((int) bArr[i9][i24]) + " ");
                        }
                        break;
                }
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int setMuteState(int i) {
        String str = w0;
        LogUtil.verbose(str, " setMuteState.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                carVendorInstrumentManager.setMuteStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setMuteState.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setMuteState.out=0");
        return 0;
    }

    public int setNotifyMapStateS(int i) {
        LogUtil.verbose(w0, "setNotifyMapStateS" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setNotifyMapStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setNotifyScreenStatusS(int i) {
        LogUtil.info(w0, "setNotifyScreenStatusS" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setNotifyScreenStatusS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setOilMaintainanceNotHint(int i, int i2) {
        String str = w0;
        LogUtil.verbose(str, " setOilMaintainanceNotHint.in");
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager != null) {
            try {
                if (i == 0) {
                    carVendorHealthManager.setOilMaintainanceNotHintOilLifeS(i2);
                    return 1;
                }
                if (i == 1) {
                    carVendorHealthManager.setOilMaintainanceNotHintTransmissionS(i2);
                    return 1;
                }
                if (i == 2) {
                    carVendorHealthManager.setOilMaintainanceNotHintBrakeFluidLifeS(i2);
                    return 1;
                }
                if (i == 3) {
                    carVendorHealthManager.setOilMaintainanceNotHintLongLifeCoolantS(i2);
                    return 1;
                }
                if (i == 4) {
                    carVendorHealthManager.setOilMaintainanceNotHintBladeBatteryCoolantS(i2);
                    return 1;
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setOilMaintainanceNotHint.null == mCarVendorHealthManager");
        }
        LogUtil.verbose(str, " setOilMaintainanceNotHint.out=0");
        return 0;
    }

    public int setOilUpdateTag(int i) {
        LogUtil.verbose(w0, "Service setOilUpdateTag" + i);
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.setOilUpdateTagS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public void setOpenTailgate(int i) {
    }

    public void setOuterMirrorControl(int[] iArr) {
    }

    public int setPadPrsntTime(int i, int i2, int i3) {
        String str = w0;
        LogUtil.verbose(str, " setPadPrsntTime.in");
        if (this.f25a != null) {
            try {
                int[] iArr = {i, i2, i3};
                LogUtil.verbose(str, " setPadPrsntTime array:" + Arrays.toString(iArr));
                this.f25a.setPadPrsntTimeS(iArr);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setPadPrsntTime.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setPadPrsntTime.out=0");
        return 0;
    }

    public int setPadTotalTime(int i, int i2, int i3) {
        String str = w0;
        LogUtil.verbose(str, " setPadTotalTime.in");
        if (this.f25a != null) {
            try {
                int[] iArr = {i, i2, i3};
                LogUtil.verbose(str, " setPadPrsntTime array:" + Arrays.toString(iArr));
                this.f25a.setPadTotalTimeS(iArr);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setPadTotalTime.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setPadTotalTime.out=0");
        return 0;
    }

    @Deprecated
    public void setPhysicalKeyBind(int i) {
    }

    public int setPowerOffChargedState(int i) {
        LogUtil.verbose(w0, "Service setPowerOffChargedState" + i);
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            carVendorHealthManager.setTransportModeStateHintS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setRadioChannel(String str) {
        String str2 = w0;
        LogUtil.verbose(str2, " setRadioChannel.in" + str);
        int i = 0;
        if (this.f25a == null) {
            return 0;
        }
        try {
            byte[] bArrB = B(str);
            int length = 94;
            if (bArrB.length < 94) {
                length = bArrB.length;
            }
            int i2 = 7;
            int i3 = ((length + 1) / 7) + 1;
            byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i3, 7);
            byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, (length / 7) + 1, 6);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            boolean z = true;
            while (i4 < i3) {
                if (i4 == 0) {
                    i2 = 6;
                }
                int i7 = i;
                while (i7 < i2) {
                    if (i5 < length) {
                        bArr[i4][i7] = bArrB[i5];
                        i6 += bArr[i4][i7];
                        i5++;
                    } else {
                        if (z) {
                            i6 &= 255;
                            bArr[i4][i7] = (byte) i6;
                            LogUtil.optionError(str2, " check = " + i6);
                            i7++;
                            z = false;
                        }
                        if (i7 < i2) {
                            bArr[i4][i7] = -1;
                        }
                    }
                    i7++;
                }
                i4++;
                i = 0;
                i2 = 7;
            }
            LogUtil.optionError(str2, " length.in" + length);
            for (int i8 = 0; i8 < bArr.length; i8++) {
                for (int i9 = 0; i9 < 6; i9++) {
                    LogUtil.optionError(str2, " newBytes.in" + ((int) bArr[i8][i9]) + " ");
                    bArr2[0][i9] = bArr[0][i9];
                }
                switch (i8) {
                    case 0:
                        this.f25a.setRadioChannelS(new long[]{length, r(bArr2[i8])});
                        for (int i10 = 0; i10 < 6; i10++) {
                            LogUtil.optionError(str2, " setRadioChannelS.in" + ((int) bArr2[i8][i10]) + " ");
                        }
                        continue;
                        break;
                    case 1:
                        this.f25a.setRadioChannelSd2(bArr[i8]);
                        for (int i11 = 0; i11 < bArr[i8].length; i11++) {
                            LogUtil.optionError(str2, " setRadioChannelSd2.in" + ((int) bArr[i8][i11]) + " ");
                        }
                        break;
                    case 2:
                        this.f25a.setRadioChannelSd3(bArr[i8]);
                        for (int i12 = 0; i12 < bArr[i8].length; i12++) {
                            LogUtil.optionError(str2, " setRadioChannelSd3.in" + ((int) bArr[i8][i12]) + " ");
                        }
                        break;
                    case 3:
                        this.f25a.setRadioChannelSd4(bArr[i8]);
                        for (int i13 = 0; i13 < bArr[i8].length; i13++) {
                            LogUtil.optionError(str2, " setRadioChannelSd4.in" + ((int) bArr[i8][i13]) + " ");
                        }
                        break;
                    case 4:
                        this.f25a.setRadioChannelSd5(bArr[i8]);
                        for (int i14 = 0; i14 < bArr[i8].length; i14++) {
                            LogUtil.optionError(str2, " setRadioChannelSd5.in" + ((int) bArr[i8][i14]) + " ");
                        }
                        break;
                    case 5:
                        this.f25a.setRadioChannelSd6(bArr[i8]);
                        for (int i15 = 0; i15 < bArr[i8].length; i15++) {
                            LogUtil.optionError(str2, " setRadioChannelSd6.in" + ((int) bArr[i8][i15]) + " ");
                        }
                        break;
                    case 6:
                        this.f25a.setRadioChannelSd7(bArr[i8]);
                        for (int i16 = 0; i16 < bArr[i8].length; i16++) {
                            LogUtil.optionError(str2, " setRadioChannelSd7.in" + ((int) bArr[i8][i16]) + " ");
                        }
                        break;
                    case 7:
                        this.f25a.setRadioChannelSd8(bArr[i8]);
                        for (int i17 = 0; i17 < bArr[i8].length; i17++) {
                            LogUtil.optionError(str2, " setRadioChannelSd8.in" + ((int) bArr[i8][i17]) + " ");
                        }
                        break;
                    case 8:
                        this.f25a.setRadioChannelSd9(bArr[i8]);
                        for (int i18 = 0; i18 < bArr[i8].length; i18++) {
                            LogUtil.optionError(str2, " setRadioChannelSd9.in" + ((int) bArr[i8][i18]) + " ");
                        }
                        break;
                    case 9:
                        this.f25a.setRadioChannelSdA(bArr[i8]);
                        for (int i19 = 0; i19 < bArr[i8].length; i19++) {
                            LogUtil.optionError(str2, " setRadioChannelSdA.in" + ((int) bArr[i8][i19]) + " ");
                        }
                        break;
                    case 10:
                        this.f25a.setRadioChannelSdB(bArr[i8]);
                        for (int i20 = 0; i20 < bArr[i8].length; i20++) {
                            LogUtil.optionError(str2, " setRadioChannelSdB.in" + ((int) bArr[i8][i20]) + " ");
                        }
                        break;
                    case 11:
                        this.f25a.setRadioChannelSdC(bArr[i8]);
                        for (int i21 = 0; i21 < bArr[i8].length; i21++) {
                            LogUtil.optionError(str2, " setRadioChannelSdC.in" + ((int) bArr[i8][i21]) + " ");
                        }
                        break;
                    case 12:
                        this.f25a.setRadioChannelSdD(bArr[i8]);
                        for (int i22 = 0; i22 < bArr[i8].length; i22++) {
                            LogUtil.optionError(str2, " setRadioChannelSdD.in" + ((int) bArr[i8][i22]) + " ");
                        }
                        break;
                    case 13:
                        this.f25a.setRadioChannelSdE(bArr[i8]);
                        for (int i23 = 0; i23 < bArr[i8].length; i23++) {
                            LogUtil.optionError(str2, " setRadioChannelSdE.in" + ((int) bArr[i8][i23]) + " ");
                        }
                        break;
                }
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int setRearFogLightState(int i) {
        String str = w0;
        LogUtil.verbose(str, " setRearFogLightState.in");
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager != null) {
            try {
                carVendorInstrumentManager.setRearFogLightStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRearFogLightState.null == mInstrumentManager");
        }
        LogUtil.verbose(str, " setRearFogLightState.out=0");
        return 0;
    }

    public int setRearWindowLock(int i) {
        LogUtil.verbose(w0, "setRearWindowLock=" + i);
        CarVendorDoorlockManager carVendorDoorlockManager = this.f26b;
        if (carVendorDoorlockManager == null) {
            return -1;
        }
        try {
            carVendorDoorlockManager.setRearWindowLockS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setRearWiperCheckState(int i) {
        LogUtil.verbose(w0, "Service setRearWiperCheckState" + i);
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return 0;
        }
        try {
            carVendorHealthManager.setRearWiperCheckStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int setScreenDisplayState(int i) {
        return -1;
    }

    public int setSeatMassageLevel(int i, int i2) {
        return 0;
    }

    public int setSeatMassageType(int i, int i2) {
        return 0;
    }

    public void setSeatSteeringWheelOperate(int[] iArr) {
    }

    public int setSeatVentilationState(int i, int i2) {
        return 0;
    }

    public int setSeatWarmState(int i, int i2) {
        return 0;
    }

    public int setSpeedUpCountState(int i) {
        LogUtil.verbose(w0, "setSpeedUpCountState" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setSpeedUpCountStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSteeringWheelWarmState(int i) {
        return 0;
    }

    public void setSunRoofUpPos(int i) {
    }

    public void setSunShadeStatus(int i) {
    }

    public void setTailgateMemPos(int i) {
    }

    @Deprecated
    public void setTailgateOpenSet(int i) {
    }

    public void setTailgateOpenSwitch(int i) {
    }

    public int setTransportMode(int i) {
        LogUtil.verbose(w0, "Service setTransportMode" + i);
        CarVendorHealthManager carVendorHealthManager = this.d;
        if (carVendorHealthManager == null) {
            return -1;
        }
        try {
            carVendorHealthManager.setTransportModeS(i);
            return -1;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setUnitUnitFuel(int i) {
        LogUtil.verbose(w0, "setUnitUnitFuel" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setUnitFuelConsumptionAndDistanceS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setUnitUnitPower(int i) {
        LogUtil.verbose(w0, "setUnitUnitPower" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setUnitPowerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setUnitUnitPressure(int i) {
        LogUtil.verbose(w0, "setUnitUnitPressure" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setUnitPressureS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setUnitUnitTemperaure(int i) {
        LogUtil.verbose(w0, "setUnitUnitTemperaure" + i);
        CarVendorInstrumentManager carVendorInstrumentManager = this.f25a;
        if (carVendorInstrumentManager == null) {
            return 0;
        }
        try {
            carVendorInstrumentManager.setUnitTemperatureS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setWindowRowOneLeftPos(int i) {
    }

    public void setWindowRowOneLeftSlideBtn(int i) {
    }

    public void setWindowRowOneRightPos(int i) {
    }

    public void setWindowRowOneRightSlideBtn(int i) {
    }

    public void setWindowRowTwoLeftPos(int i) {
    }

    public void setWindowRowTwoLeftSlideBtn(int i) {
    }

    public void setWindowRowTwoRightPos(int i) {
    }

    public void setWindowRowTwoRightSlideBtn(int i) {
    }

    public void setWindowSetRoofTop1(int i) {
    }

    public void setWindowSetRow1Left(int i) {
    }

    public void setWindowSetRow1Right(int i) {
    }

    public void setWindowSetRow2Left(int i) {
    }

    public void setWindowSetRow2Right(int i) {
    }

    public boolean subscribeCabinCallback(ICabinStateCallback iCabinStateCallback) {
        com.ts.appservice.caradapter.a.c cVar = this.f;
        if (cVar == null || iCabinStateCallback == null) {
            return false;
        }
        cVar.h(iCabinStateCallback);
        if (!this.h) {
            return true;
        }
        this.f.l();
        return true;
    }

    public void t(CarVendorHealthManager carVendorHealthManager, CarVendorDoorlockManager carVendorDoorlockManager, CarVendorGearboxManager carVendorGearboxManager, CarVendorInstrumentManager carVendorInstrumentManager, CarVendorRearviewmirrorManager carVendorRearviewmirrorManager) {
        this.d = carVendorHealthManager;
        this.f26b = carVendorDoorlockManager;
        this.e = carVendorGearboxManager;
        this.f25a = carVendorInstrumentManager;
        this.c = carVendorRearviewmirrorManager;
        try {
            if (this.X == null) {
                this.X = new a(this.W.getLooper());
                LogUtil.info(w0, "initCabinManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.c cVar = this.f;
        if (cVar != null) {
            this.h = true;
            cVar.l();
        }
    }

    public boolean unsubscribeCabinCallback(ICabinStateCallback iCabinStateCallback) {
        com.ts.appservice.caradapter.a.c cVar = this.f;
        if (cVar == null || iCabinStateCallback == null) {
            return false;
        }
        cVar.i(iCabinStateCallback);
        return true;
    }
}
