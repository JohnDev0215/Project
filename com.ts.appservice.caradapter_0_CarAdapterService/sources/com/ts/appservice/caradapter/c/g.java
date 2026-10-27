package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import com.ts.appservice.caradapter.d.InterfaceC0009a;
import com.ts.appservice.caradapter.d.InterfaceC0010b;
import com.ts.appservice.caradapter.d.o;
import com.ts.appservice.caradapter.d.z;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager;
import com.ts.lib.car.hardware.carbody.CarbodyEventChangeValue;
import com.ts.lib.car.hardware.charging.CarVendorChargingManager;
import com.ts.lib.car.hardware.charging.ChargingEventChangeValue;
import com.ts.lib.car.hardware.energy.CarVendorEnergyManager;
import com.ts.lib.car.hardware.energy.EnergyEventChangeValue;
import com.ts.lib.car.hardware.engine.CarVendorEngineManager;
import com.ts.lib.car.hardware.engine.EngineEventChangeValue;
import com.ts.lib.car.hardware.light.CarVendorLightManager;
import com.ts.lib.car.hardware.light.LightEventChangeValue;
import com.ts.lib.car.hardware.tyre.CarVendorTyreManager;
import com.ts.lib.car.hardware.tyre.TyreEventChangeValue;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.ICarBodyCallback;
import com.ts.lib.caradapter.body.ICarBodyService;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: CarBodyServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends ICarBodyService.Stub implements InterfaceC0010b, InterfaceC0009a, com.ts.appservice.caradapter.d.h, com.ts.appservice.caradapter.d.i, z, o {
    private static final String C3 = g.class.getSimpleName();
    private int A;
    private int A0;
    private int A1;
    private int A2;
    private int B;
    private int B0;
    private int B1;
    private int B2;
    private int C;
    private int C0;
    private int C1;
    private int C2;
    private int D;
    private int D0;
    private int D1;
    private int D2;
    private int E;
    private int E0;
    private int E1;
    private int E2;
    private int F;
    private int F0;
    private int F1;
    private int F2;
    private int G;
    private int G0;
    private int G1;
    private int G2;
    private int H;
    private int H0;
    private int H1;
    private int H2;
    private int I0;
    private int I1;
    private int I2;
    private int J;
    private int J0;
    private int J1;
    private int J2;
    private int K;
    private int K0;
    private int K1;
    private int K2;
    private int L;
    private int L0;
    private int L1;
    private int L2;
    private int M;
    private int M0;
    private int M1;
    private int M2;
    private int N;
    private int N0;
    private int N1;
    private int N2;
    private int O;
    private int O0;
    private int O1;
    private int O2;
    private float P;
    private int P0;
    private int P1;
    private int P2;
    private int Q;
    private int Q0;
    private int Q1;
    private int Q2;
    private int R;
    private int R0;
    private int R1;
    private int R2;
    private int S;
    private int S0;
    private int S1;
    private int S2;
    private int T;
    private int T0;
    private int T1;
    private int T2;
    private int U;
    private int U0;
    private int U1;
    private int U2;
    private int V;
    private int V0;
    private float V1;
    private int V2;
    private int W;
    private int W0;
    private int W1;
    private int W2;
    private int X;
    private int X0;
    private int X1;
    private int X2;
    private int Y;
    private int Y0;
    private int Y1;
    private int Y2;
    private int Z;
    private int Z0;
    private int Z1;
    private int Z2;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CarVendorChargingManager f65a;
    private int a0;
    private int a1;
    private int a2;
    private int a3;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CarVendorCarbodyManager f66b;
    private int b0;
    private int b1;
    private int b2;
    private int b3;
    private CarVendorEnergyManager c;
    private int c0;
    private int c1;
    private int c2;
    private int c3;
    private CarVendorEngineManager d;
    private int d0;
    private int d1;
    private float d2;
    private int d3;
    private CarVendorLightManager e;
    private int e0;
    private int e1;
    private int e2;
    private int e3;
    private CarVendorTyreManager f;
    private float f0;
    private int f1;
    private int f2;
    private int f3;
    private com.ts.appservice.caradapter.a.b g;
    private int g0;
    private int g1;
    private int g2;
    private int g3;
    private int h0;
    private int h1;
    private int h2;
    private int h3;
    private final HandlerThread i;
    private int i0;
    private int i1;
    private int i2;
    private int i3;
    private a j;
    private int j0;
    private int j1;
    private int j2;
    private int j3;
    private Context k;
    private int k0;
    private int k1;
    private int k2;
    private int k3;
    private int l;
    private int l0;
    private int l1;
    private int l2;
    private int l3;
    private int m;
    private int m0;
    private int m1;
    private int m2;
    private int m3;
    private int n;
    private int n0;
    private int n1;
    private int n2;
    private int n3;
    private int o;
    private int o0;
    private int o1;
    private int o2;
    private int o3;
    private int p;
    private int p0;
    private int p1;
    private int p2;
    private int p3;
    private int q;
    private int q0;
    private int q1;
    private int q2;
    private int q3;
    private int r;
    private int r0;
    private int r1;
    private int r2;
    private int r3;
    private int s;
    private int s0;
    private int s1;
    private int s2;
    private int s3;
    private int t;
    private int t0;
    private int t1;
    private int t2;
    private int t3;
    private int u;
    private int u0;
    private int u1;
    private int u2;
    private int u3;
    private int v;
    private int v0;
    private int v1;
    private int v2;
    private int v3;
    private int w;
    private int w0;
    private int w1;
    private int w2;
    private int w3;
    private int x;
    private int x0;
    private int x1;
    private int x2;
    private int[] x3;
    private int y;
    private int y0;
    private int y1;
    private int y2;
    private int[] y3;
    private int z;
    private int z0;
    private int z1;
    private int z2;
    private CarBodyPacket h = new CarBodyPacket();
    private boolean I = false;
    private int[] z3 = new int[2];
    private int[] A3 = new int[2];
    private int[] B3 = new int[5];

    /* JADX INFO: compiled from: CarBodyServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 7:
                    g.this.A(message.arg1, message.arg2, (EnergyEventChangeValue) message.obj);
                    break;
                case 8:
                    g.this.z(message.arg1, message.arg2, (ChargingEventChangeValue) message.obj);
                    break;
                case 9:
                    g.this.B(message.arg1, message.arg2, (EngineEventChangeValue) message.obj);
                    break;
                case 10:
                    g.this.D(message.arg1, message.arg2, (TyreEventChangeValue) message.obj);
                    break;
                case 11:
                    g.this.C(message.arg1, message.arg2, (LightEventChangeValue) message.obj);
                    break;
                case 12:
                    g.this.y(message.arg1, message.arg2, (CarbodyEventChangeValue) message.obj);
                    break;
                default:
                    LogUtil.error(g.C3, "Unexpected message: " + message.what);
                    break;
            }
        }
    }

    public g(Context context) {
        new HashMap();
        this.k = context;
        this.g = new com.ts.appservice.caradapter.a.b(this.k);
        this.i = com.ts.appservice.caradapter.e.b.a("VEHICLE-BODY");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(int i, int i2, EnergyEventChangeValue energyEventChangeValue) {
        String str = C3;
        try {
            switch (i) {
                case 557847552:
                    this.h.setmOperationMode(energyEventChangeValue.getOperationMode());
                    LogUtil.verbose(str, "ID_OPERATION_MODER-" + energyEventChangeValue.getOperationMode());
                    break;
                case 557847553:
                    this.h.setmRoadSurfaceMode(energyEventChangeValue.getRoadSurfaceMode());
                    LogUtil.verbose(str, "ID_ROAD_SURFACE_MODE:" + energyEventChangeValue.getRoadSurfaceMode());
                    break;
                case 557847554:
                    this.h.setmPowerGenerationState(energyEventChangeValue.getPowerGenerationState());
                    LogUtil.verbose(str, "ID_POWER_GENERATION_STATE:" + energyEventChangeValue.getPowerGenerationState());
                    break;
                case 557847555:
                    this.h.setmPowerGenerationValue(energyEventChangeValue.getPowerGenerationValue());
                    LogUtil.verbose(str, "ID_POWER_GENERATION_VALUE:" + energyEventChangeValue.getPowerGenerationValue());
                    break;
                case 557847556:
                    this.h.setmEnergyMode(energyEventChangeValue.getEnergyMode());
                    LogUtil.verbose(str, "ID_ENERGY_MODER:" + energyEventChangeValue.getEnergyMode());
                    break;
                case 557847557:
                    this.h.setmDriveDistanceShowModeS(energyEventChangeValue.getDriveDistanceShowModeS());
                    LogUtil.verbose(str, "ID_DRIVE_DISTANCE_SHOW_MODES:" + energyEventChangeValue.getDriveDistanceShowModeS());
                    break;
                case 557847558:
                    this.h.setmDriveDistanceShowModeR(energyEventChangeValue.getDriveDistanceShowModeR());
                    LogUtil.verbose(str, "ID_DRIVE_DISTANCE_SHOW_MODER:" + energyEventChangeValue.getDriveDistanceShowModeR());
                    break;
                default:
                    switch (i) {
                        case 557847563:
                            this.h.setmEPedalStateR(energyEventChangeValue.getEPedalStateR());
                            LogUtil.verbose(str, "ID_EPEDAL_STATER:" + energyEventChangeValue.getEPedalStateR());
                            break;
                        case 557847564:
                            this.h.setmEPedalStateS(energyEventChangeValue.getEPedalStateS());
                            LogUtil.verbose(str, "ID_EPEDAL_STATES:" + energyEventChangeValue.getEPedalStateS());
                            break;
                        case 557847565:
                            this.h.setmElectricShowModeR(energyEventChangeValue.getElectricShowModeR());
                            LogUtil.verbose(str, "ID_ELECTRIC_SHOW_MODER:" + energyEventChangeValue.getElectricShowModeR());
                            break;
                        case 557847566:
                            this.h.setmElectricShowModeS(energyEventChangeValue.getElectricShowModeS());
                            LogUtil.verbose(str, "ID_ELECTRIC_SHOW_MODES:" + energyEventChangeValue.getElectricShowModeS());
                            break;
                        case 557847567:
                            this.h.setmMandatoryElectricStateR(energyEventChangeValue.getMandatoryElectricStateR());
                            LogUtil.verbose(str, "ID_MANDATORY_ELECTRIC_STATER:" + energyEventChangeValue.getMandatoryElectricStateR());
                            break;
                        case 557847568:
                            this.h.setmMandatoryElectricStateS(energyEventChangeValue.getMandatoryElectricStateS());
                            LogUtil.verbose(str, "ID_MANDATORY_ELECTRIC_STATES:" + energyEventChangeValue.getMandatoryElectricStateS());
                            break;
                        case 557847569:
                            this.h.setmDriveDistanceShowStandardR(energyEventChangeValue.getDriveDistanceShowStandardR());
                            LogUtil.verbose(str, "ID_DRIVE_DISTANCE_SHOW_STANDARDR:" + energyEventChangeValue.getDriveDistanceShowStandardR());
                            break;
                        case 557847570:
                            this.h.setmDriveDistanceShowStandardS(energyEventChangeValue.getDriveDistanceShowStandardS());
                            LogUtil.verbose(str, "ID_DRIVE_DISTANCE_SHOW_STANDARDS:" + energyEventChangeValue.getDriveDistanceShowStandardS());
                            break;
                        case 557847571:
                            this.h.setmResetCurveData(energyEventChangeValue.getResetCurveData());
                            LogUtil.verbose(str, "ID_RESET_CURVE_DATA:" + energyEventChangeValue.getResetCurveData());
                            break;
                        case 557847572:
                            this.h.setmMemorySportStateR(energyEventChangeValue.getMemorySportStateR());
                            LogUtil.verbose(str, "ID_MEMORY_SPORT_STATER:" + energyEventChangeValue.getMemorySportStateR());
                            break;
                        case 557847573:
                            this.h.setmMemorySportStateS(energyEventChangeValue.getMemorySportStateS());
                            LogUtil.verbose(str, "ID_MEMORY_SPORT_STATES:" + energyEventChangeValue.getMemorySportStateS());
                            break;
                        case 557847574:
                            this.h.setmMemoryMaxEvStateR(energyEventChangeValue.getMemoryMaxEvStateR());
                            LogUtil.verbose(str, "ID_MEMORY_MAX_EV_STATER:" + energyEventChangeValue.getMemoryMaxEvStateR());
                            break;
                        case 557847575:
                            this.h.setmMemoryMaxEvStateS(energyEventChangeValue.getMemoryMaxEvStateS());
                            LogUtil.verbose(str, "ID_MEMORY_MAX_EV_STATES:" + energyEventChangeValue.getMemoryMaxEvStateS());
                            break;
                        case 557847576:
                            this.h.setmMemoryElectricStateR(energyEventChangeValue.getMemoryElectricStateR());
                            LogUtil.verbose(str, "ID_MEMORY_ELECTRIC_STATER:" + energyEventChangeValue.getMemoryElectricStateR());
                            break;
                        case 557847577:
                            this.h.setmMemoryElectricStateS(energyEventChangeValue.getMemoryElectricStateS());
                            LogUtil.verbose(str, "ID_MEMORY_ELECTRIC_STATES:" + energyEventChangeValue.getMemoryElectricStateS());
                            break;
                        case 557847578:
                            this.h.setmPreSaleModelStateR(energyEventChangeValue.getPreSaleModelStateR());
                            LogUtil.verbose(str, "ID_PRE_SALE_MODEL_STATER:" + energyEventChangeValue.getPreSaleModelStateR());
                            break;
                        case 557847579:
                            this.h.setmPreSaleModelStateS(energyEventChangeValue.getPreSaleModelStateS());
                            LogUtil.verbose(str, "ID_PRE_SALE_MODEL_STATES:" + energyEventChangeValue.getPreSaleModelStateS());
                            break;
                        case 557847580:
                            this.h.setOilPointFlag(energyEventChangeValue.getOilPointFlag());
                            LogUtil.verbose(str, "ID_OIL_POINT_FLAG:" + energyEventChangeValue.getOilPointFlag());
                            break;
                        case 557847581:
                            this.h.setElectricPointFlag(energyEventChangeValue.getElectricPointFlag());
                            LogUtil.verbose(str, "ID_ELECTRIC_POINT_FLAG:" + energyEventChangeValue.getElectricPointFlag());
                            break;
                        default:
                            switch (i) {
                                case 557847586:
                                    this.h.setEnergyStateR(energyEventChangeValue.getEnergyStateR());
                                    LogUtil.verbose(str, "ID_ENERGY_STATER:" + energyEventChangeValue.getEnergyStateR());
                                    break;
                                case 557847587:
                                    this.h.setHevElectricPointFlagR(energyEventChangeValue.getHevElectricPointFlagR());
                                    LogUtil.verbose(str, "ID_HEV_ELECTRIC_POINT_FLAG_R:" + energyEventChangeValue.getHevElectricPointFlagR());
                                    break;
                                default:
                                    switch (i) {
                                        case 557847590:
                                            this.h.setOperationModeNormalR(energyEventChangeValue.getOperationModeNormalR());
                                            LogUtil.verbose(str, "ID_OPERATION_MODE_NORMAL_R:" + energyEventChangeValue.getOperationModeNormalR());
                                            break;
                                        case 557847592:
                                            this.h.setRoadSurfaceModeNormalR(energyEventChangeValue.getRoadSurfaceModeNormalR());
                                            LogUtil.verbose(str, "ID_ROAD_SURFACE_MODE_NORMAL_R:" + energyEventChangeValue.getRoadSurfaceModeNormalR());
                                            break;
                                        case 557847594:
                                            int i3 = energyEventChangeValue.getiTacpStateR();
                                            this.h.setiTacpStateR(i3);
                                            LogUtil.debug(str, "ID_I_T_A_C_P_STATE_R:" + i3);
                                            break;
                                        case 557847601:
                                            int chargePortCoverTipR = energyEventChangeValue.getChargePortCoverTipR();
                                            this.h.setChargePortCoverTipR(chargePortCoverTipR);
                                            LogUtil.debug(str, "ID_CHARGE_PORT_COVER_TIP_R:" + chargePortCoverTipR);
                                            break;
                                        case 557847617:
                                            int yEnergyConLimitR = energyEventChangeValue.getYEnergyConLimitR();
                                            this.h.setyEnergyConLimitR(yEnergyConLimitR);
                                            LogUtil.debug(str, "ID_Y_ENERGY_CON_LIMIT_R:" + yEnergyConLimitR);
                                            break;
                                        case 557860387:
                                            int rollBenchModeStatusR = energyEventChangeValue.getRollBenchModeStatusR();
                                            this.h.setRollBenchModeStatusR(rollBenchModeStatusR);
                                            LogUtil.debug(str, "ID_ROLL_BENCH_MODE_STATUS_R:" + rollBenchModeStatusR);
                                            break;
                                        case 559944740:
                                            this.h.setHevLast200MElectricConR(energyEventChangeValue.getHevLast200MElectricConR());
                                            LogUtil.verbose(str, "ID_HEV_LAST_200_M_ELECTRIC_CON_R:" + energyEventChangeValue.getHevLast200MElectricConR());
                                            break;
                                        default:
                                            switch (i) {
                                                case 557847596:
                                                    int i4 = energyEventChangeValue.getiTacpAllowTagR();
                                                    this.h.setiTacpAllowTagR(i4);
                                                    LogUtil.debug(str, "ID_I_T_A_C_P_ALLOW_TAG_R:" + i4);
                                                    break;
                                                case 557847597:
                                                    int changePHintR = energyEventChangeValue.getChangePHintR();
                                                    this.h.setChangePHintR(changePHintR);
                                                    LogUtil.debug(str, "ID_CHANGE_P_HINT_R:" + changePHintR);
                                                    break;
                                                case 557847598:
                                                    int i5 = energyEventChangeValue.getiTacExitTagR();
                                                    this.h.setiTacExitTagR(i5);
                                                    LogUtil.debug(str, "ID_I_T_A_C_EXIT_TAG_R:" + i5);
                                                    break;
                                                case 557847599:
                                                    int i6 = energyEventChangeValue.getiTacModeR();
                                                    this.h.setiTacModeR(i6);
                                                    LogUtil.debug(str, "ID_I_T_A_C_MODE_R:" + i6);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557847604:
                                                            int tripDrivePercentR = energyEventChangeValue.getTripDrivePercentR();
                                                            this.h.setTripDrivePercentR(tripDrivePercentR);
                                                            LogUtil.debug(str, "ID_TRIP_DRIVE_PERCENT_R:" + tripDrivePercentR);
                                                            break;
                                                        case 557847605:
                                                            int tripACPercentR = energyEventChangeValue.getTripACPercentR();
                                                            this.h.setTripAcPercentR(tripACPercentR);
                                                            LogUtil.debug(str, "ID_TRIP_A_C_PERCENT_R:" + tripACPercentR);
                                                            break;
                                                        case 557847606:
                                                            int tripElecAppliancePercentR = energyEventChangeValue.getTripElecAppliancePercentR();
                                                            this.h.setTripElecAppliancePercentR(tripElecAppliancePercentR);
                                                            LogUtil.debug(str, "ID_TRIP_ELEC_APPLIANCE_PERCENT_R:" + tripElecAppliancePercentR);
                                                            break;
                                                        case 557847607:
                                                            int tripOtherPercentR = energyEventChangeValue.getTripOtherPercentR();
                                                            this.h.setTripOtherPercentR(tripOtherPercentR);
                                                            LogUtil.debug(str, "ID_TRIP_OTHER_PERCENT_R:" + tripOtherPercentR);
                                                            break;
                                                        case 557847608:
                                                            int tripPointFlagR = energyEventChangeValue.getTripPointFlagR();
                                                            this.h.setTripPointFlagR(tripPointFlagR);
                                                            LogUtil.debug(str, "ID_TRIP_POINT_FLAG_R:" + tripPointFlagR);
                                                            break;
                                                        case 557847609:
                                                            int tripDrivePercent50KMR = energyEventChangeValue.getTripDrivePercent50KMR();
                                                            this.h.setTripDrivePercent50kmr(tripDrivePercent50KMR);
                                                            LogUtil.debug(str, "ID_TRIP_DRIVE_PERCENT50_K_M_R:" + tripDrivePercent50KMR);
                                                            break;
                                                        case 557847610:
                                                            int tripACPercent50KMR = energyEventChangeValue.getTripACPercent50KMR();
                                                            this.h.setTripAcPercent50kmr(tripACPercent50KMR);
                                                            LogUtil.debug(str, "ID_TRIP_A_C_PERCENT50_K_M_R:" + tripACPercent50KMR);
                                                            break;
                                                        case 557847611:
                                                            int tripElecAppliancePercent50KMR = energyEventChangeValue.getTripElecAppliancePercent50KMR();
                                                            this.h.setTripElecAppliancePercent50kmr(tripElecAppliancePercent50KMR);
                                                            LogUtil.debug(str, "ID_TRIP_ELEC_APPLIANCE_PERCENT50_K_M_R:" + tripElecAppliancePercent50KMR);
                                                            break;
                                                        case 557847612:
                                                            int tripOtherPercent50KMR = energyEventChangeValue.getTripOtherPercent50KMR();
                                                            this.h.setTripOtherPercent50kmr(tripOtherPercent50KMR);
                                                            LogUtil.debug(str, "ID_TRIP_OTHER_PERCENT50_K_M_R:" + tripOtherPercent50KMR);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 559944734:
                                                                    this.h.setLast200MOilCon(energyEventChangeValue.getLast200MOilCon());
                                                                    LogUtil.verbose(str, "ID_LAST200_M_OIL_CON:" + energyEventChangeValue.getLast200MOilCon());
                                                                    break;
                                                                case 559944735:
                                                                    this.h.setLast200MElectricCon(energyEventChangeValue.getLast200MElectricCon());
                                                                    LogUtil.verbose(str, "ID_LAST200_M_ELECTRIC_CON:" + energyEventChangeValue.getLast200MElectricCon());
                                                                    break;
                                                                case 559944736:
                                                                    this.h.setLast50KmOilConAvg(energyEventChangeValue.getLast50KMOilConAvg());
                                                                    LogUtil.verbose(str, "ID_LAST50_K_M_OIL_CON_AVG:" + energyEventChangeValue.getLast50KMOilConAvg());
                                                                    break;
                                                                case 559944737:
                                                                    this.h.setLast50KmElectricConAvg(energyEventChangeValue.getLast50KMElectricConAvg());
                                                                    LogUtil.verbose(str, "ID_LAST50_K_M_ELECTRIC_CON_AVG:" + energyEventChangeValue.getLast50KMElectricConAvg());
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 559944765:
                                                                            float tripOilConAvgR = energyEventChangeValue.getTripOilConAvgR();
                                                                            this.h.setTripOilConAvgR(tripOilConAvgR);
                                                                            LogUtil.debug(str, "ID_TRIP_OIL_CON_AVG_R:" + tripOilConAvgR);
                                                                            break;
                                                                        case 559944766:
                                                                            float tripEnergyConAvgR = energyEventChangeValue.getTripEnergyConAvgR();
                                                                            this.h.setTripEnergyConAvgR(tripEnergyConAvgR);
                                                                            LogUtil.debug(str, "ID_TRIP_ENERGY_CON_AVG_R:" + tripEnergyConAvgR);
                                                                            break;
                                                                        case 559944767:
                                                                            float yEnergyConR = energyEventChangeValue.getYEnergyConR();
                                                                            this.h.setyEnergyConR(yEnergyConR);
                                                                            LogUtil.debug(str, "ID_Y_ENERGY_CON_R:" + yEnergyConR);
                                                                            break;
                                                                        case 559944768:
                                                                            float tripElecConAvgR = energyEventChangeValue.getTripElecConAvgR();
                                                                            this.h.setTripElecConAvgR(tripElecConAvgR);
                                                                            LogUtil.debug(str, "ID_TRIP_ELEC_CON_AVG_R:" + tripElecConAvgR);
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 559944770:
                                                                                    float tripLast50KMElectricConAvgR = energyEventChangeValue.getTripLast50KMElectricConAvgR();
                                                                                    this.h.setTripLast50kmElectricConAvgR(tripLast50KMElectricConAvgR);
                                                                                    LogUtil.debug(str, "ID_TRIP_LAST50_K_M_ELECTRIC_CON_AVG_R:" + tripLast50KMElectricConAvgR);
                                                                                    break;
                                                                                case 559944771:
                                                                                    float tripLast50KMOilConAvgR = energyEventChangeValue.getTripLast50KMOilConAvgR();
                                                                                    this.h.setTripLast50kmOilConAvgR(tripLast50KMOilConAvgR);
                                                                                    LogUtil.debug(str, "ID_TRIP_LAST50_K_M_OIL_CON_AVG_R:" + tripLast50KMOilConAvgR);
                                                                                    break;
                                                                                case 559944772:
                                                                                    float tripLast50KMFuelConAvgR = energyEventChangeValue.getTripLast50KMFuelConAvgR();
                                                                                    this.h.setTripLast50kmFuelConAvgR(tripLast50KMFuelConAvgR);
                                                                                    LogUtil.debug(str, "ID_TRIP_LAST50_K_M_FUEL_CON_AVG_R:" + tripLast50KMFuelConAvgR);
                                                                                    break;
                                                                                default:
                                                                                    return;
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
            this.g.m(i, i2, this.h);
            LogUtil.verbose(str, "notifyDriveModeEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(int i, int i2, EngineEventChangeValue engineEventChangeValue) {
        String str = C3;
        try {
            switch (i) {
                case 557851650:
                    int enginePowerR = engineEventChangeValue.getEnginePowerR();
                    this.W1 = enginePowerR;
                    this.h.setmEnginePowerR(enginePowerR);
                    LogUtil.verbose(str, "ID_ENGINE_POWERR:" + this.W1);
                    break;
                case 557851651:
                    int engineSpeedR = engineEventChangeValue.getEngineSpeedR();
                    this.X1 = engineSpeedR;
                    this.h.setmEngineSpeedR(engineSpeedR);
                    LogUtil.verbose(str, "ID_ENGINE_SPEEDR:" + this.X1);
                    break;
                case 557851652:
                    int engineCoolantLevelR = engineEventChangeValue.getEngineCoolantLevelR();
                    this.U1 = engineCoolantLevelR;
                    this.h.setmEngineCoolantLevelR(engineCoolantLevelR);
                    LogUtil.verbose(str, "ID_ENGINE_COOLANT_LEVELR:" + this.U1);
                    break;
                case 557851654:
                    int engineConditionR = engineEventChangeValue.getEngineConditionR();
                    this.T1 = engineConditionR;
                    this.h.setmEngineConditionR(engineConditionR);
                    LogUtil.verbose(str, "ID_ENGINE_CONDITIONR:" + this.T1);
                    break;
                case 557851655:
                    int oilLevelR = engineEventChangeValue.getOilLevelR();
                    this.Y1 = oilLevelR;
                    this.h.setmOilLevelR(oilLevelR);
                    LogUtil.verbose(str, "ID_OIL_LEVELR:" + this.Y1);
                    break;
                case 559948808:
                    float engineDisplacementR = engineEventChangeValue.getEngineDisplacementR();
                    this.V1 = engineDisplacementR;
                    this.h.setmEngineDisplacementR(engineDisplacementR);
                    LogUtil.verbose(str, "ID_ENGINE_DISPLACEMENTR:" + this.V1);
                    break;
            }
            this.g.m(i, engineEventChangeValue.getAreaId(), this.h);
            LogUtil.verbose(str, "notifyEngineEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(int i, int i2, TyreEventChangeValue tyreEventChangeValue) {
        String str = C3;
        try {
            if (i == 557861386) {
                int tyreTurnState = tyreEventChangeValue.getTyreTurnState();
                this.j2 = tyreTurnState;
                this.h.setmTyreTurnState(tyreTurnState);
                LogUtil.verbose(str, "ID_TYRE_TURN_STATE:" + this.j2);
            } else if (i != 559958537) {
                switch (i) {
                    case 557861376:
                        int tyreAirLeakState = tyreEventChangeValue.getTyreAirLeakState();
                        this.b2 = tyreAirLeakState;
                        this.h.setmTyreAirLeakState(tyreAirLeakState);
                        LogUtil.verbose(str, "ID_TYRE_AIR_LEAK_STATE:" + this.b2);
                        break;
                    case 557861377:
                        int tyreBatteryState = tyreEventChangeValue.getTyreBatteryState();
                        this.c2 = tyreBatteryState;
                        this.h.setmTyreBatteryState(tyreBatteryState);
                        LogUtil.verbose(str, "ID_TYRE_BATTERY_STATE:" + this.c2);
                        break;
                    case 557861378:
                        int tyrePressureState = tyreEventChangeValue.getTyrePressureState();
                        this.e2 = tyrePressureState;
                        this.h.setmTyrePressureState(tyrePressureState);
                        LogUtil.verbose(str, "ID_TYRE_PRESSURE_STATE:" + this.e2);
                        break;
                    case 557861379:
                        int tyrePressureValue = tyreEventChangeValue.getTyrePressureValue();
                        this.f2 = tyrePressureValue;
                        this.h.setmTyrePressureValue(tyrePressureValue);
                        LogUtil.verbose(str, "ID_TYRE_PRESSURE_VALUE:" + this.f2);
                        break;
                    case 557861380:
                        int tyreSignalState = tyreEventChangeValue.getTyreSignalState();
                        this.g2 = tyreSignalState;
                        this.h.setmTyreSignalState(tyreSignalState);
                        LogUtil.verbose(str, "ID_TYRE_SIGNAL_STATE:" + this.g2);
                        break;
                    case 557861381:
                        int tyreSystemState = tyreEventChangeValue.getTyreSystemState();
                        this.h2 = tyreSystemState;
                        this.h.setmTyreSystemState(tyreSystemState);
                        LogUtil.verbose(str, "ID_TYRE_SYSTEM_STATE:" + this.h2);
                        break;
                    case 557861382:
                        int tyreTemperatureState = tyreEventChangeValue.getTyreTemperatureState();
                        this.i2 = tyreTemperatureState;
                        this.h.setmTyreTemperatureState(tyreTemperatureState);
                        LogUtil.verbose(str, "ID_TYRE_TEMPERATURE_STATE:" + this.i2);
                        break;
                    case 557861383:
                        int indirectTyreSystemState = tyreEventChangeValue.getIndirectTyreSystemState();
                        this.a2 = indirectTyreSystemState;
                        this.h.setmIndirectTyreSystemState(indirectTyreSystemState);
                        LogUtil.verbose(str, "ID_INDIRECT_TYRE_SYSTEM_STATE:" + this.a2);
                        break;
                    case 557861384:
                        int indirectTyreConfirm = tyreEventChangeValue.getIndirectTyreConfirm();
                        this.Z1 = indirectTyreConfirm;
                        this.h.setmIndirectTyreConfirm(indirectTyreConfirm);
                        LogUtil.verbose(str, "ID_INDIRECT_TYRE_CONFIRM:" + this.Z1);
                        break;
                    default:
                        switch (i) {
                            case 557861388:
                                int tyrePressureWarnLeftFrontR = tyreEventChangeValue.getTyrePressureWarnLeftFrontR();
                                this.h.setTyrePressureWarnLeftFrontR(tyrePressureWarnLeftFrontR);
                                LogUtil.verbose(str, "ID_TYRE_PRESSURE_WARN_LEFT_FRONT_R:" + tyrePressureWarnLeftFrontR);
                                break;
                            case 557861389:
                                int tyrePressureWarnRightFrontR = tyreEventChangeValue.getTyrePressureWarnRightFrontR();
                                this.h.setTyrePressureWarnRightFrontR(tyrePressureWarnRightFrontR);
                                LogUtil.verbose(str, "ID_TYRE_PRESSURE_WARN_RIGHT_FRONT_R:" + tyrePressureWarnRightFrontR);
                                break;
                            case 557861390:
                                int tyrePressureWarnLeftRearR = tyreEventChangeValue.getTyrePressureWarnLeftRearR();
                                this.h.setTyrePressureWarnLeftRearR(tyrePressureWarnLeftRearR);
                                LogUtil.verbose(str, "ID_TYRE_PRESSURE_WARN_LEFT_REAR_R:" + tyrePressureWarnLeftRearR);
                                break;
                            case 557861391:
                                int tyrePressureWarnRightRearR = tyreEventChangeValue.getTyrePressureWarnRightRearR();
                                this.h.setTyrePressureWarnRightRearR(tyrePressureWarnRightRearR);
                                LogUtil.verbose(str, "ID_TYRE_PRESSURE_WARN_RIGHT_REAR_R:" + tyrePressureWarnRightRearR);
                                break;
                            default:
                                switch (i) {
                                    case 557869844:
                                        int indirectTyrePressureStateLfR = tyreEventChangeValue.getIndirectTyrePressureStateLfR();
                                        this.h.setIndirectTyrePressureStateLfR(indirectTyrePressureStateLfR);
                                        LogUtil.verbose(str, "ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R:" + indirectTyrePressureStateLfR);
                                        break;
                                    case 557869845:
                                        int indirectTyrePressureStateRfR = tyreEventChangeValue.getIndirectTyrePressureStateRfR();
                                        this.h.setIndirectTyrePressureStateRfR(indirectTyrePressureStateRfR);
                                        LogUtil.verbose(str, "ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R:" + indirectTyrePressureStateRfR);
                                        break;
                                    case 557869846:
                                        int indirectTyrePressureStateLbR = tyreEventChangeValue.getIndirectTyrePressureStateLbR();
                                        this.h.setIndirectTyrePressureStateLbR(indirectTyrePressureStateLbR);
                                        LogUtil.verbose(str, "ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R:" + indirectTyrePressureStateLbR);
                                        break;
                                    case 557869847:
                                        int indirectTyrePressureStateRbR = tyreEventChangeValue.getIndirectTyrePressureStateRbR();
                                        this.h.setIndirectTyrePressureStateRbR(indirectTyrePressureStateRbR);
                                        LogUtil.verbose(str, "ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R:" + indirectTyrePressureStateRbR);
                                        break;
                                    default:
                                        switch (i) {
                                            case 557875234:
                                                int tyreColorByTypeLfR = tyreEventChangeValue.getTyreColorByTypeLfR();
                                                this.h.setTyreColorByTypeLfR(tyreColorByTypeLfR);
                                                LogUtil.verbose(str, "ID_TYRE_COLOR_BY_TYPE_LF_R:" + tyreColorByTypeLfR);
                                                break;
                                            case 557875235:
                                                int tyreColorByTypeRfR = tyreEventChangeValue.getTyreColorByTypeRfR();
                                                this.h.setTyreColorByTypeRfR(tyreColorByTypeRfR);
                                                LogUtil.verbose(str, "ID_TYRE_COLOR_BY_TYPE_RF_R:" + tyreColorByTypeRfR);
                                                break;
                                            case 557875236:
                                                int tyreColorByTypeLbR = tyreEventChangeValue.getTyreColorByTypeLbR();
                                                this.h.setTyreColorByTypeLbR(tyreColorByTypeLbR);
                                                LogUtil.verbose(str, "ID_TYRE_COLOR_BY_TYPE_LB_R:" + tyreColorByTypeLbR);
                                                break;
                                            case 557875237:
                                                int tyreColorByTypeRbR = tyreEventChangeValue.getTyreColorByTypeRbR();
                                                this.h.setTyreColorByTypeRbR(tyreColorByTypeRbR);
                                                LogUtil.verbose(str, "ID_TYRE_COLOR_BY_TYPE_RB_R:" + tyreColorByTypeRbR);
                                                break;
                                            case 557875238:
                                                int tyreTemperatureValueR = tyreEventChangeValue.getTyreTemperatureValueR();
                                                this.h.setTyreTemperatureValueR(tyreTemperatureValueR);
                                                LogUtil.verbose(str, "ID_TYRE_TEMPERATURE_VALUE_R:" + tyreTemperatureValueR);
                                                break;
                                            case 557875239:
                                                int systemUndervoltageStatusR = tyreEventChangeValue.getSystemUndervoltageStatusR();
                                                this.h.setSystemUndervoltageStatusR(systemUndervoltageStatusR);
                                                LogUtil.verbose(str, "ID_SYSTEM_UNDERVOLTAGE_STATUS_R:" + systemUndervoltageStatusR);
                                                break;
                                            default:
                                                switch (i) {
                                                    case 559972381:
                                                        float tyrePressureValueByTypeLfR = tyreEventChangeValue.getTyrePressureValueByTypeLfR();
                                                        this.h.setTyrePressureValueByTypeLfR(tyrePressureValueByTypeLfR);
                                                        LogUtil.verbose(str, "ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R:" + tyrePressureValueByTypeLfR);
                                                        break;
                                                    case 559972382:
                                                        float tyrePressureValueByTypeRfR = tyreEventChangeValue.getTyrePressureValueByTypeRfR();
                                                        this.h.setTyrePressureValueByTypeRfR(tyrePressureValueByTypeRfR);
                                                        LogUtil.verbose(str, "ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R:" + tyrePressureValueByTypeRfR);
                                                        break;
                                                    case 559972383:
                                                        float tyrePressureValueByTypeLbR = tyreEventChangeValue.getTyrePressureValueByTypeLbR();
                                                        this.h.setTyrePressureValueByTypeLbR(tyrePressureValueByTypeLbR);
                                                        LogUtil.verbose(str, "ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R:" + tyrePressureValueByTypeLbR);
                                                        break;
                                                    case 559972384:
                                                        float tyrePressureValueByTypeRbR = tyreEventChangeValue.getTyrePressureValueByTypeRbR();
                                                        this.h.setTyrePressureValueByTypeRbR(tyrePressureValueByTypeRbR);
                                                        LogUtil.verbose(str, "ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R:" + tyrePressureValueByTypeRbR);
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
            } else {
                float tyreBatteryValue = tyreEventChangeValue.getTyreBatteryValue();
                this.d2 = tyreBatteryValue;
                this.h.setmTyreBatteryValue(tyreBatteryValue);
                LogUtil.verbose(str, "ID_TYRE_BATTERY_VALUE:" + this.d2);
            }
            this.g.m(i, tyreEventChangeValue.getAreaId(), this.h);
            LogUtil.verbose(str, "notifyTyreEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void E() {
        LogUtil.verbose(C3, "quit");
        if (this.i != null) {
            a aVar = this.j;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.i.quitSafely();
        }
    }

    private synchronized void G(String str, int i) {
        SystemProperties.set("persist.car.auto.type", String.valueOf(i));
        LogUtil.warning(C3, "Updated system property " + str + " with value" + i);
    }

    private int t(int i) {
        return i == 0 ? 1 : 0;
    }

    private synchronized int v() {
        String str;
        str = SystemProperties.get("persist.car.auto.type", "0");
        LogUtil.warning(C3, "Read system property with value " + str);
        return Integer.parseInt(str);
    }

    private int w(int[] iArr) {
        String str = C3;
        if (iArr == null || iArr.length < 2) {
            LogUtil.info(str, " value is length is not 2 ");
            return 0;
        }
        LogUtil.info(str, " value is length is not 2 " + iArr[1]);
        return t(iArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i, int i2, CarbodyEventChangeValue carbodyEventChangeValue) {
        String str = C3;
        try {
            switch (i) {
                case 557845265:
                    int fuelElecLowPowerR = carbodyEventChangeValue.getFuelElecLowPowerR();
                    this.r3 = fuelElecLowPowerR;
                    this.h.setFuelElecLowPowerR(fuelElecLowPowerR);
                    LogUtil.verbose(str, "ID_FUEL_ELEC_LOW_POWER_R:" + this.r3);
                    break;
                case 557857850:
                    this.h.setRecoverOrSaveParamsChanged5(carbodyEventChangeValue.getRecoverOrSaveParamsChanged5());
                    LogUtil.verbose(str, "ID_RECOVERORSAVEPARAMSCHANGED5:" + carbodyEventChangeValue.getRecoverOrSaveParamsChanged5());
                    break;
                case 557860644:
                    int lightRearPosition = carbodyEventChangeValue.getLightRearPosition();
                    this.G = lightRearPosition;
                    this.h.setLightRearPosition(lightRearPosition);
                    LogUtil.verbose(str, "ID_LIGHT_REAR_POSITIONR:" + this.G);
                    break;
                case 557862936:
                    int windowOpenPercentLeftFrontR = carbodyEventChangeValue.getWindowOpenPercentLeftFrontR();
                    this.x2 = windowOpenPercentLeftFrontR;
                    this.h.setmWindowOpenPercentLeftFrontR(windowOpenPercentLeftFrontR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_LEFT_FRONT_R:" + this.x2);
                    break;
                case 557862937:
                    int windowOpenPercentLeftRearR = carbodyEventChangeValue.getWindowOpenPercentLeftRearR();
                    this.y2 = windowOpenPercentLeftRearR;
                    this.h.setmWindowOpenPercentLeftRearR(windowOpenPercentLeftRearR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_LEFT_REAR_R:" + this.y2);
                    break;
                case 557862938:
                    int windowOpenPercentRightFrontR = carbodyEventChangeValue.getWindowOpenPercentRightFrontR();
                    this.B2 = windowOpenPercentRightFrontR;
                    this.h.setmWindowOpenPercentRightFrontR(windowOpenPercentRightFrontR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_RIGHT_FRONT_R:" + this.B2);
                    break;
                case 557862939:
                    int windowOpenPercentRightRearR = carbodyEventChangeValue.getWindowOpenPercentRightRearR();
                    this.C2 = windowOpenPercentRightRearR;
                    this.h.setmWindowOpenPercentRightRearR(windowOpenPercentRightRearR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_RIGHT_REAR_R:" + this.C2);
                    break;
                case 557862940:
                    int windowOpenPercentMoonR = carbodyEventChangeValue.getWindowOpenPercentMoonR();
                    this.z2 = windowOpenPercentMoonR;
                    this.h.setmWindowOpenPercentMoonR(windowOpenPercentMoonR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_MOON_R:" + this.z2);
                    break;
                case 557862941:
                    int windowOpenPercentSunR = carbodyEventChangeValue.getWindowOpenPercentSunR();
                    this.D2 = windowOpenPercentSunR;
                    this.h.setmWindowOpenPercentSunR(windowOpenPercentSunR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_SUN_R:" + this.D2);
                    break;
                case 557862942:
                    int moonRoofConfig = carbodyEventChangeValue.getMoonRoofConfig();
                    this.t2 = moonRoofConfig;
                    this.h.setmMoonRoofConfig(moonRoofConfig);
                    LogUtil.verbose(str, "ID_MOON_ROOF_CONFIG:" + this.t2);
                    break;
                case 557862943:
                    int doorStateLeftFront = carbodyEventChangeValue.getDoorStateLeftFront();
                    this.o2 = doorStateLeftFront;
                    this.h.setmDoorStateLeftFront(doorStateLeftFront);
                    LogUtil.verbose(str, "ID_DOOR_STATE_LEFT_FRONT:" + this.o2);
                    break;
                case 557862944:
                    int doorStateRightFront = carbodyEventChangeValue.getDoorStateRightFront();
                    this.r2 = doorStateRightFront;
                    this.h.setmDoorStateRightFront(doorStateRightFront);
                    LogUtil.verbose(str, "ID_DOOR_STATE_RIGHT_FRONT:" + this.r2);
                    break;
                case 557862945:
                    int doorStateLeftRear = carbodyEventChangeValue.getDoorStateLeftRear();
                    this.p2 = doorStateLeftRear;
                    this.h.setmDoorStateLeftRear(doorStateLeftRear);
                    LogUtil.verbose(str, "ID_DOOR_STATE_LEFT_REAR:" + this.p2);
                    break;
                case 557862946:
                    int doorStateRightRear = carbodyEventChangeValue.getDoorStateRightRear();
                    this.s2 = doorStateRightRear;
                    this.h.setmDoorStateRightRear(doorStateRightRear);
                    LogUtil.verbose(str, "ID_DOOR_STATE_RIGHT_REAR:" + this.s2);
                    break;
                case 557862947:
                    int doorStateLuggage = carbodyEventChangeValue.getDoorStateLuggage();
                    this.q2 = doorStateLuggage;
                    this.h.setmDoorStateLuggage(doorStateLuggage);
                    LogUtil.verbose(str, "ID_DOOR_STATE_LUGGAGE:" + this.q2);
                    break;
                case 557862948:
                    int doorStateHood = carbodyEventChangeValue.getDoorStateHood();
                    this.n2 = doorStateHood;
                    this.h.setmDoorStateHood(doorStateHood);
                    LogUtil.verbose(str, "ID_DOOR_STATE_HOOD:" + this.n2);
                    break;
                case 557862949:
                    int windowStateMoonRoofR = carbodyEventChangeValue.getWindowStateMoonRoofR();
                    this.L2 = windowStateMoonRoofR;
                    this.h.setmWindowStateMoonRoofR(windowStateMoonRoofR);
                    LogUtil.verbose(str, "ID_WINDOW_STATE_MOON_ROOFR:" + this.L2);
                    break;
                case 557862950:
                    int windowStateSunshadeR = carbodyEventChangeValue.getWindowStateSunshadeR();
                    this.M2 = windowStateSunshadeR;
                    this.h.setmWindowStateSunshadeR(windowStateSunshadeR);
                    LogUtil.verbose(str, "ID_WINDOW_STATE_SUNSHADER:" + this.M2);
                    break;
                case 557862951:
                    int energyTypeR = carbodyEventChangeValue.getEnergyTypeR();
                    this.J2 = energyTypeR;
                    this.h.setmEnergyTypeR(energyTypeR);
                    LogUtil.verbose(str, "ID_ENERGY_TYPER:" + this.J2);
                    break;
                case 557862952:
                    int sunRoofStateR = carbodyEventChangeValue.getSunRoofStateR();
                    this.K2 = sunRoofStateR;
                    this.h.setmSunRoofStateR(sunRoofStateR);
                    LogUtil.verbose(str, "ID_SUN_ROOF_STATER:" + this.K2);
                    break;
                case 557862953:
                    int leftStrutMorotRunningState = carbodyEventChangeValue.getLeftStrutMorotRunningState();
                    this.j3 = leftStrutMorotRunningState;
                    this.h.setLeftStrutMorotRunningState(leftStrutMorotRunningState);
                    LogUtil.verbose(str, "ID_LEFT_STRUT_MOROT_RUNNING_STATE:" + this.j3);
                    break;
                case 557862954:
                    int windowInitStateFL = carbodyEventChangeValue.getWindowInitStateFL();
                    this.O2 = windowInitStateFL;
                    this.h.setWindowInitStateFL(windowInitStateFL);
                    LogUtil.verbose(str, "ID_WINDOW_INIT_STATE_FL:" + this.O2);
                    break;
                case 557862955:
                    int windowInitStateRL = carbodyEventChangeValue.getWindowInitStateRL();
                    this.Q2 = windowInitStateRL;
                    this.h.setWindowInitStateRL(windowInitStateRL);
                    LogUtil.verbose(str, "mWindowInitStateRL:" + this.Q2);
                    break;
                case 557862956:
                    int windowInitStateFR = carbodyEventChangeValue.getWindowInitStateFR();
                    this.P2 = windowInitStateFR;
                    this.h.setWindowInitStateFR(windowInitStateFR);
                    LogUtil.verbose(str, "ID_WINDOW_INIT_STATE_FR:" + this.P2);
                    break;
                case 557862957:
                    int windowInitStateRR = carbodyEventChangeValue.getWindowInitStateRR();
                    this.R2 = windowInitStateRR;
                    this.h.setWindowInitStateRR(windowInitStateRR);
                    LogUtil.verbose(str, "ID_WINDOW_INIT_STATE_RR:" + this.R2);
                    break;
                case 557862958:
                    int windowThermalProtectionStateFL = carbodyEventChangeValue.getWindowThermalProtectionStateFL();
                    this.k3 = windowThermalProtectionStateFL;
                    this.h.setWindowThermalProtectionStateFl(windowThermalProtectionStateFL);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_RL:" + this.k3);
                    break;
                case 557862959:
                    int windowThermalProtectionStateRL = carbodyEventChangeValue.getWindowThermalProtectionStateRL();
                    this.l3 = windowThermalProtectionStateRL;
                    this.h.setWindowThermalProtectionStateRl(windowThermalProtectionStateRL);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_FL:" + this.l3);
                    break;
                case 557862960:
                    int windowThermalProtectionStateFR = carbodyEventChangeValue.getWindowThermalProtectionStateFR();
                    this.m3 = windowThermalProtectionStateFR;
                    this.h.setWindowThermalProtectionStateFr(windowThermalProtectionStateFR);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_FR:" + this.m3);
                    break;
                case 557862961:
                    int windowThermalProtectionStateRR = carbodyEventChangeValue.getWindowThermalProtectionStateRR();
                    this.n3 = windowThermalProtectionStateRR;
                    this.h.setWindowThermalProtectionStateRr(windowThermalProtectionStateRR);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_RR:" + this.n3);
                    break;
                case 557862962:
                    int windowThermalProtectionStateSkylight = carbodyEventChangeValue.getWindowThermalProtectionStateSkylight();
                    this.o3 = windowThermalProtectionStateSkylight;
                    this.h.setWindowThermalProtectionStateSkylight(windowThermalProtectionStateSkylight);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_SKYLIGHT:" + this.o3);
                    break;
                case 557862963:
                    int windowThermalProtectionStateFrontSunshade = carbodyEventChangeValue.getWindowThermalProtectionStateFrontSunshade();
                    this.p3 = windowThermalProtectionStateFrontSunshade;
                    this.h.setWindowThermalProtectionStateFrontSunshade(windowThermalProtectionStateFrontSunshade);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_FRONT_SUNSHADE:" + this.p3);
                    break;
                case 557862964:
                    int windowThermalProtectionStateRearSunshade = carbodyEventChangeValue.getWindowThermalProtectionStateRearSunshade();
                    this.q3 = windowThermalProtectionStateRearSunshade;
                    this.h.setWindowThermalProtectionStateRearSunshade(windowThermalProtectionStateRearSunshade);
                    LogUtil.verbose(str, "ID_WINDOW_THERMAL_PROTECTION_STATE_REAR_SUNSHADE:" + this.q3);
                    break;
                case 557862965:
                    int windowPermitStateR = carbodyEventChangeValue.getWindowPermitStateR();
                    this.h.setWindowPermitState(windowPermitStateR);
                    LogUtil.verbose(str, "ID_WINDOW_PERMIT_STATER:" + windowPermitStateR);
                    break;
                case 557862966:
                    int windowblindInitStateR = carbodyEventChangeValue.getWindowblindInitStateR();
                    this.T2 = windowblindInitStateR;
                    this.h.setWindowblindInitStateR(windowblindInitStateR);
                    LogUtil.verbose(str, "ID_WINDOWBLIND_INIT_STATER:" + this.T2);
                    break;
                case 557862967:
                    int sunroofInitStateR = carbodyEventChangeValue.getSunroofInitStateR();
                    this.U2 = sunroofInitStateR;
                    this.h.setSunroofInitStateR(sunroofInitStateR);
                    LogUtil.verbose(str, "ID_SUNROOF_INIT_STATER:" + this.U2);
                    break;
                case 557862968:
                    int sunroofCloseNoticeR = carbodyEventChangeValue.getSunroofCloseNoticeR();
                    this.V2 = sunroofCloseNoticeR;
                    this.h.setSunroofCloseNoticeR(sunroofCloseNoticeR);
                    LogUtil.verbose(str, "ID_SUNROOF_CLOSE_NOTICER:" + this.V2);
                    break;
                case 557862969:
                    int sunroofWindowblindPositionR = carbodyEventChangeValue.getSunroofWindowblindPositionR();
                    this.W2 = sunroofWindowblindPositionR;
                    this.h.setSunroofWindowblindPositionR(sunroofWindowblindPositionR);
                    LogUtil.verbose(str, "ID_SUNROOF_WINDOWBLIND_POSITIONR:" + this.W2);
                    break;
                case 557862970:
                    int sunroofPositionR = carbodyEventChangeValue.getSunroofPositionR();
                    this.X2 = sunroofPositionR;
                    this.h.setSunroofPositionR(sunroofPositionR);
                    LogUtil.verbose(str, "ID_SUNROOF_POSITIONR:" + this.X2);
                    break;
                case 557862991:
                    int windowOpenPercentRearPanelR = carbodyEventChangeValue.getWindowOpenPercentRearPanelR();
                    this.h.setWindowOpenPercentRearPanel(windowOpenPercentRearPanelR);
                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_REAR_PANELR:" + windowOpenPercentRearPanelR);
                    break;
                case 557862994:
                    int antiTheftStatusR = carbodyEventChangeValue.getAntiTheftStatusR();
                    this.S2 = antiTheftStatusR;
                    this.h.setAntiTheftStatusR(antiTheftStatusR);
                    LogUtil.verbose(str, "ID_ANTI_THEFT_STATUS_R:" + this.S2);
                    break;
                case 557863013:
                    int readyIndicatorLightStateR = carbodyEventChangeValue.getReadyIndicatorLightStateR();
                    this.h.setReadyIndicatorLightStateR(readyIndicatorLightStateR);
                    LogUtil.verbose(str, "ID_READY_INDICATOR_LIGHT_STATER:" + readyIndicatorLightStateR);
                    break;
                case 557863019:
                    int rearWindowblindInitStateNewR = carbodyEventChangeValue.getRearWindowblindInitStateNewR();
                    this.s3 = rearWindowblindInitStateNewR;
                    this.h.setRearWindowblindInitStateNewR(rearWindowblindInitStateNewR);
                    LogUtil.verbose(str, "ID_REAR_WINDOWBLIND_INIT_STATE_NEW_R:" + this.s3);
                    break;
                case 557863049:
                    int screenControlSlideDoorLeftLeftR = carbodyEventChangeValue.getScreenControlSlideDoorLeftLeftR();
                    this.h.setScreenControlSlideDoorLeftLeftR(screenControlSlideDoorLeftLeftR);
                    LogUtil.debug(str, "ID_SCREEN_CONTROL_SLIDE_DOOR_LEFT_LEFT_R:" + screenControlSlideDoorLeftLeftR);
                    break;
                case 557863050:
                    int screenControlSlideDoorRightLeftR = carbodyEventChangeValue.getScreenControlSlideDoorRightLeftR();
                    this.h.setScreenControlSlideDoorRightLeftR(screenControlSlideDoorRightLeftR);
                    LogUtil.debug(str, "ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_LEFT_R:" + screenControlSlideDoorRightLeftR);
                    break;
                case 557863051:
                    int screenControlSlideDoorLLeftRightR = carbodyEventChangeValue.getScreenControlSlideDoorLLeftRightR();
                    this.h.setScreenControlSlideDoorLLeftRightR(screenControlSlideDoorLLeftRightR);
                    LogUtil.debug(str, "ID_SCREEN_CONTROL_SLIDE_DOOR_L_LEFT_RIGHT_R:" + screenControlSlideDoorLLeftRightR);
                    break;
                case 557863052:
                    int screenControlSlideDoorRightRightR = carbodyEventChangeValue.getScreenControlSlideDoorRightRightR();
                    this.h.setScreenControlSlideDoorRightRightR(screenControlSlideDoorRightRightR);
                    LogUtil.debug(str, "ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_RIGHT_R:" + screenControlSlideDoorRightRightR);
                    break;
                case 557863053:
                    int screenControlSlideDoorSlidingLeftLeftR = carbodyEventChangeValue.getScreenControlSlideDoorSlidingLeftLeftR();
                    this.h.setScreenControlSlideDoorSlidingLeftLeftR(screenControlSlideDoorSlidingLeftLeftR);
                    LogUtil.debug(str, "ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_LEFT_R:" + screenControlSlideDoorSlidingLeftLeftR);
                    break;
                case 557863054:
                    int screenControlSlideDoorSlidingRightLeftR = carbodyEventChangeValue.getScreenControlSlideDoorSlidingRightLeftR();
                    this.h.setScreenControlSlideDoorSlidingRightLeftR(screenControlSlideDoorSlidingRightLeftR);
                    LogUtil.debug(str, "SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_LEFT_R:" + screenControlSlideDoorSlidingRightLeftR);
                    break;
                case 557863055:
                    int screenControlSlideDoorSlidingLeftRightR = carbodyEventChangeValue.getScreenControlSlideDoorSlidingLeftRightR();
                    this.h.setScreenControlSlideDoorSlidingLeftRightR(screenControlSlideDoorSlidingLeftRightR);
                    LogUtil.debug(str, "ISCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_RIGHT_R:" + screenControlSlideDoorSlidingLeftRightR);
                    break;
                case 557863056:
                    int screenControlSlideDoorSlidingRightRightR = carbodyEventChangeValue.getScreenControlSlideDoorSlidingRightRightR();
                    this.h.setScreenControlSlideDoorSlidingRightRightR(screenControlSlideDoorSlidingRightRightR);
                    LogUtil.debug(str, "SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_RIGHT_R:" + screenControlSlideDoorSlidingRightRightR);
                    break;
                case 557863057:
                    int evReadyIndicatorLightStateR = carbodyEventChangeValue.getEvReadyIndicatorLightStateR();
                    this.h.setEvReadyIndicatorLightStateR(evReadyIndicatorLightStateR);
                    LogUtil.debug(str, "ID_EV_READY_INDICATOR_LIGHT_STATE_R:" + evReadyIndicatorLightStateR);
                    break;
                case 557863061:
                    int internalDoorHandleStateByRearBCMLFR = carbodyEventChangeValue.getInternalDoorHandleStateByRearBCMLFR();
                    this.h.setInternalDoorHandleStateByRearBCMLFR(internalDoorHandleStateByRearBCMLFR);
                    LogUtil.debug(str, "DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_F_R:" + internalDoorHandleStateByRearBCMLFR);
                    break;
                case 557863062:
                    int internalDoorHandleStateByRearBCMLRR = carbodyEventChangeValue.getInternalDoorHandleStateByRearBCMLRR();
                    this.h.setInternalDoorHandleStateByRearBCMLRR(internalDoorHandleStateByRearBCMLRR);
                    LogUtil.debug(str, "DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_R_R:" + internalDoorHandleStateByRearBCMLRR);
                    break;
                case 557863063:
                    int internalDoorHandleStateByRearBCMRFR = carbodyEventChangeValue.getInternalDoorHandleStateByRearBCMRFR();
                    this.h.setInternalDoorHandleStateByRearBCMRFR(internalDoorHandleStateByRearBCMRFR);
                    LogUtil.debug(str, "DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_F_R:" + internalDoorHandleStateByRearBCMRFR);
                    break;
                case 557863064:
                    int internalDoorHandleStateByRearBCMRRR = carbodyEventChangeValue.getInternalDoorHandleStateByRearBCMRRR();
                    this.h.setInternalDoorHandleStateByRearBCMRRR(internalDoorHandleStateByRearBCMRRR);
                    LogUtil.debug(str, "DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_R_R:" + internalDoorHandleStateByRearBCMRRR);
                    break;
                case 557863067:
                    int thirdRowSeatsExecutionResultsR = carbodyEventChangeValue.getThirdRowSeatsExecutionResultsR();
                    this.h.setThirdRowSeatsExecutionResultsR(thirdRowSeatsExecutionResultsR);
                    LogUtil.debug(str, "ID_THIRD_ROW_SEATS_EXECUTION_RESULTS_R:" + thirdRowSeatsExecutionResultsR);
                    break;
                case 557863068:
                    int leftSeatRailsRow3FeedbackPositionR = carbodyEventChangeValue.getLeftSeatRailsRow3FeedbackPositionR();
                    this.h.setLeftSeatRailsRow3FeedbackPositionR(leftSeatRailsRow3FeedbackPositionR);
                    LogUtil.debug(str, "ID_LEFT_SEAT_RAILS_ROW3_FEEDBACK_POSITION_R:" + leftSeatRailsRow3FeedbackPositionR);
                    break;
                case 557863069:
                    int rightSeatRailsRow3FeedbackPositionR = carbodyEventChangeValue.getRightSeatRailsRow3FeedbackPositionR();
                    this.h.setRightSeatRailsRow3FeedbackPositionR(rightSeatRailsRow3FeedbackPositionR);
                    LogUtil.debug(str, "ID_RIGHT_SEAT_RAILS_ROW3_FEEDBACK_POSITION_R:" + rightSeatRailsRow3FeedbackPositionR);
                    break;
                case 557863070:
                    int leftSeatRow3NonElectricallyAdjustStateR = carbodyEventChangeValue.getLeftSeatRow3NonElectricallyAdjustStateR();
                    this.h.setLeftSeatRow3NonElectricallyAdjustStateR(leftSeatRow3NonElectricallyAdjustStateR);
                    LogUtil.debug(str, "ID_LEFT_SEAT_ROW3_NON_ELECTRICALLY_ADJUST_STATE_R:" + leftSeatRow3NonElectricallyAdjustStateR);
                    break;
                case 557863071:
                    int fseSeatSystemWorkModeR = carbodyEventChangeValue.getFseSeatSystemWorkModeR();
                    this.h.setFseSeatSystemWorkModeR(fseSeatSystemWorkModeR);
                    LogUtil.debug(str, "ID_FSE_SEAT_SYSTEM_WORK_MODE_R:" + fseSeatSystemWorkModeR);
                    break;
                case 557863076:
                    int seatModeCurFeedbackStatus2R = carbodyEventChangeValue.getSeatModeCurFeedbackStatus2R();
                    this.h.setSeatModeCurFeedbackStatus2R(seatModeCurFeedbackStatus2R);
                    LogUtil.debug(str, "ID_SEAT_MODE_CUR_FEEDBACK_STATUS2_R:" + seatModeCurFeedbackStatus2R);
                    break;
                case 557863077:
                    int seatModeSuccessFeedbackStatus2R = carbodyEventChangeValue.getSeatModeSuccessFeedbackStatus2R();
                    this.h.setSeatModeSuccessFeedbackStatus2R(seatModeSuccessFeedbackStatus2R);
                    LogUtil.debug(str, "ID_SEAT_MODE_SUCCESS_FEEDBACK_STATUS2_R:" + seatModeSuccessFeedbackStatus2R);
                    break;
                case 557863078:
                    int seatModeFailFeedbackStatus2R = carbodyEventChangeValue.getSeatModeFailFeedbackStatus2R();
                    this.h.setSeatModeFailFeedbackStatus2R(seatModeFailFeedbackStatus2R);
                    LogUtil.debug(str, "ID_SEAT_MODE_FAIL_FEEDBACK_STATUS2_R:" + seatModeFailFeedbackStatus2R);
                    break;
                case 557863079:
                    int seatModeCurFeedbackStatusR = carbodyEventChangeValue.getSeatModeCurFeedbackStatusR();
                    this.h.setSeatModeCurFeedbackStatusR(seatModeCurFeedbackStatusR);
                    LogUtil.debug(str, "ID_SEAT_MODE_CUR_FEEDBACK_STATUS_R:" + seatModeCurFeedbackStatusR);
                    break;
                case 557863080:
                    int seatModeSuccessFeedbackStatusR = carbodyEventChangeValue.getSeatModeSuccessFeedbackStatusR();
                    this.h.setSeatModeSuccessFeedbackStatusR(seatModeSuccessFeedbackStatusR);
                    LogUtil.debug(str, "ID_SEAT_MODE_SUCCESS_FEEDBACK_STATUS_R:" + seatModeSuccessFeedbackStatusR);
                    break;
                case 557863081:
                    int seatModeFailFeedbackStatusR = carbodyEventChangeValue.getSeatModeFailFeedbackStatusR();
                    this.h.setSeatModeFailFeedbackStatusR(seatModeFailFeedbackStatusR);
                    LogUtil.debug(str, "ID_SEAT_MODE_FAIL_FEEDBACK_STATUS_R:" + seatModeFailFeedbackStatusR);
                    break;
                case 557863086:
                    int leftMidDoorVoiceWakeUpChangedR = carbodyEventChangeValue.getLeftMidDoorVoiceWakeUpChangedR();
                    this.h.setLeftMidDoorVoiceWakeUpChangedR(leftMidDoorVoiceWakeUpChangedR);
                    LogUtil.debug(str, "ID_LEFT_MID_DOOR_VOICE_WAKE_UP_CHANGED_R:" + leftMidDoorVoiceWakeUpChangedR);
                    break;
                case 557863087:
                    int rightMidDoorVoiceWakeUpChangedR = carbodyEventChangeValue.getRightMidDoorVoiceWakeUpChangedR();
                    this.h.setRightMidDoorVoiceWakeUpChangedR(rightMidDoorVoiceWakeUpChangedR);
                    LogUtil.debug(str, "ID_RIGHT_MID_DOOR_VOICE_WAKE_UP_CHANGED_R:" + rightMidDoorVoiceWakeUpChangedR);
                    break;
                case 557868039:
                    int shiftModeR = carbodyEventChangeValue.getShiftModeR();
                    this.h.setShiftModeR(shiftModeR);
                    LogUtil.debug(str, "ID_SHIFT_MODE_R:" + shiftModeR);
                    break;
                case 557875227:
                    int batteryVoltageLevelLowTips = carbodyEventChangeValue.getBatteryVoltageLevelLowTips();
                    this.h.setBatteryVoltageLevelLowTips(batteryVoltageLevelLowTips);
                    LogUtil.verbose(str, "ID_BATTERY_VOLTAGE_LEVEL_LOW_TIPS:" + batteryVoltageLevelLowTips);
                    break;
                case 557945600:
                    int[] can055R = carbodyEventChangeValue.getCan055R();
                    this.x3 = can055R;
                    this.h.setCan055R(can055R);
                    break;
                case 557945603:
                    int[] can0d5R = carbodyEventChangeValue.getCan0d5R();
                    this.x3 = can0d5R;
                    this.h.setCan0d5(can0d5R);
                    break;
                case 557945605:
                    int[] can10dR = carbodyEventChangeValue.getCan10dR();
                    this.x3 = can10dR;
                    this.h.setCan10d(can10dR);
                    break;
                case 557945606:
                    int[] can121R = carbodyEventChangeValue.getCan121R();
                    this.x3 = can121R;
                    this.h.setCan121(can121R);
                    break;
                case 557945607:
                    int[] can123R = carbodyEventChangeValue.getCan123R();
                    this.x3 = can123R;
                    this.h.setCan123(can123R);
                    break;
                case 557945608:
                    int[] can12dR = carbodyEventChangeValue.getCan12dR();
                    this.x3 = can12dR;
                    this.h.setCan12d(can12dR);
                    break;
                case 557945609:
                    int[] can12fR = carbodyEventChangeValue.getCan12fR();
                    this.x3 = can12fR;
                    this.h.setCan12f(can12fR);
                    break;
                case 557945610:
                    int[] can133R = carbodyEventChangeValue.getCan133R();
                    this.x3 = can133R;
                    this.h.setCan133(can133R);
                    break;
                case 557945611:
                    int[] can138R = carbodyEventChangeValue.getCan138R();
                    this.x3 = can138R;
                    this.h.setCan138(can138R);
                    break;
                case 557945612:
                    int[] can13fR = carbodyEventChangeValue.getCan13fR();
                    this.x3 = can13fR;
                    this.h.setCan13f(can13fR);
                    break;
                case 557945613:
                    int[] can144R = carbodyEventChangeValue.getCan144R();
                    this.x3 = can144R;
                    this.h.setCan144(can144R);
                    break;
                case 557945614:
                    int[] can151R = carbodyEventChangeValue.getCan151R();
                    this.x3 = can151R;
                    this.h.setCan151(can151R);
                    break;
                case 557945615:
                    int[] can167R = carbodyEventChangeValue.getCan167R();
                    this.x3 = can167R;
                    this.h.setCan167(can167R);
                    break;
                case 557945616:
                    int[] can18aR = carbodyEventChangeValue.getCan18aR();
                    this.x3 = can18aR;
                    this.h.setCan18a(can18aR);
                    break;
                case 557945617:
                    int[] can19fR = carbodyEventChangeValue.getCan19fR();
                    this.x3 = can19fR;
                    this.h.setCan19f(can19fR);
                    break;
                case 557945618:
                    int[] can1c0R = carbodyEventChangeValue.getCan1c0R();
                    this.x3 = can1c0R;
                    this.h.setCan1c0(can1c0R);
                    break;
                case 557945619:
                    int[] can1d1R = carbodyEventChangeValue.getCan1d1R();
                    this.x3 = can1d1R;
                    this.h.setCan1d1(can1d1R);
                    break;
                case 557945620:
                    int[] can218R = carbodyEventChangeValue.getCan218R();
                    this.x3 = can218R;
                    this.h.setCan218(can218R);
                    break;
                case 557945621:
                    int[] can223R = carbodyEventChangeValue.getCan223R();
                    this.x3 = can223R;
                    this.h.setCan223(can223R);
                    break;
                case 557945622:
                    int[] can233R = carbodyEventChangeValue.getCan233R();
                    this.x3 = can233R;
                    this.h.setCan233(can233R);
                    break;
                case 557945623:
                    int[] can235R = carbodyEventChangeValue.getCan235R();
                    this.x3 = can235R;
                    this.h.setCan235(can235R);
                    break;
                case 557945624:
                    int[] can23aR = carbodyEventChangeValue.getCan23aR();
                    this.x3 = can23aR;
                    this.h.setCan23a(can23aR);
                    break;
                case 557945625:
                    int[] can242R = carbodyEventChangeValue.getCan242R();
                    this.x3 = can242R;
                    this.h.setCan242(can242R);
                    break;
                case 557945626:
                    int[] can245R = carbodyEventChangeValue.getCan245R();
                    this.x3 = can245R;
                    this.h.setCan245(can245R);
                    break;
                case 557945627:
                    int[] can248R = carbodyEventChangeValue.getCan248R();
                    this.x3 = can248R;
                    this.h.setCan248(can248R);
                    break;
                case 557945628:
                    int[] can24aR = carbodyEventChangeValue.getCan24aR();
                    this.x3 = can24aR;
                    this.h.setCan24a(can24aR);
                    break;
                case 557945629:
                    int[] can24cR = carbodyEventChangeValue.getCan24cR();
                    this.x3 = can24cR;
                    this.h.setCan24c(can24cR);
                    break;
                case 557945630:
                    int[] can25dR = carbodyEventChangeValue.getCan25dR();
                    this.x3 = can25dR;
                    this.h.setCan25d(can25dR);
                    break;
                case 557945631:
                    int[] can261R = carbodyEventChangeValue.getCan261R();
                    this.x3 = can261R;
                    this.h.setCan261(can261R);
                    break;
                case 557945632:
                    int[] can26fR = carbodyEventChangeValue.getCan26fR();
                    this.x3 = can26fR;
                    this.h.setCan26f(can26fR);
                    break;
                case 557945633:
                    int[] can271R = carbodyEventChangeValue.getCan271R();
                    this.x3 = can271R;
                    this.h.setCan271(can271R);
                    break;
                case 557945634:
                    int[] can272R = carbodyEventChangeValue.getCan272R();
                    this.x3 = can272R;
                    this.h.setCan272(can272R);
                    break;
                case 557945635:
                    int[] can275R = carbodyEventChangeValue.getCan275R();
                    this.x3 = can275R;
                    this.h.setCan275(can275R);
                    break;
                case 557945636:
                    int[] can27aR = carbodyEventChangeValue.getCan27aR();
                    this.x3 = can27aR;
                    this.h.setCan27a(can27aR);
                    break;
                case 557945637:
                    int[] can287R = carbodyEventChangeValue.getCan287R();
                    this.x3 = can287R;
                    this.h.setCan287(can287R);
                    break;
                case 557945638:
                    int[] can288R = carbodyEventChangeValue.getCan288R();
                    this.x3 = can288R;
                    this.h.setCan288(can288R);
                    break;
                case 557945639:
                    int[] can28fR = carbodyEventChangeValue.getCan28fR();
                    this.x3 = can28fR;
                    this.h.setCan28f(can28fR);
                    break;
                case 557945640:
                    int[] can294R = carbodyEventChangeValue.getCan294R();
                    this.x3 = can294R;
                    this.h.setCan294(can294R);
                    break;
                case 557945641:
                    int[] can296R = carbodyEventChangeValue.getCan296R();
                    this.x3 = can296R;
                    this.h.setCan296(can296R);
                    break;
                case 557945642:
                    int[] can29cR = carbodyEventChangeValue.getCan29cR();
                    this.x3 = can29cR;
                    this.h.setCan29c(can29cR);
                    break;
                case 557945643:
                    int[] can29eR = carbodyEventChangeValue.getCan29eR();
                    this.x3 = can29eR;
                    this.h.setCan29e(can29eR);
                    break;
                case 557945644:
                    int[] can2a4R = carbodyEventChangeValue.getCan2a4R();
                    this.x3 = can2a4R;
                    this.h.setCan2a4(can2a4R);
                    break;
                case 557945645:
                    int[] can2a6R = carbodyEventChangeValue.getCan2a6R();
                    this.x3 = can2a6R;
                    this.h.setCan2a6(can2a6R);
                    break;
                case 557945646:
                    int[] can2a7R = carbodyEventChangeValue.getCan2a7R();
                    this.x3 = can2a7R;
                    this.h.setCan2a7(can2a7R);
                    break;
                case 557945647:
                    int[] can2b0R = carbodyEventChangeValue.getCan2b0R();
                    this.x3 = can2b0R;
                    this.h.setCan2b0(can2b0R);
                    break;
                case 557945648:
                    int[] can2b4R = carbodyEventChangeValue.getCan2b4R();
                    this.x3 = can2b4R;
                    this.h.setCan2b4(can2b4R);
                    break;
                case 557945649:
                    int[] can2b6R = carbodyEventChangeValue.getCan2b6R();
                    this.x3 = can2b6R;
                    this.h.setCan2b6(can2b6R);
                    break;
                case 557945650:
                    int[] can2baR = carbodyEventChangeValue.getCan2baR();
                    this.x3 = can2baR;
                    this.h.setCan2ba(can2baR);
                    break;
                case 557945651:
                    int[] can2bcR = carbodyEventChangeValue.getCan2bcR();
                    this.x3 = can2bcR;
                    this.h.setCan2bc(can2bcR);
                    break;
                case 557945652:
                    int[] can2c0R = carbodyEventChangeValue.getCan2c0R();
                    this.x3 = can2c0R;
                    this.h.setCan2c0(can2c0R);
                    break;
                case 557945653:
                    int[] can2d5R = carbodyEventChangeValue.getCan2d5R();
                    this.x3 = can2d5R;
                    this.h.setCan2d5(can2d5R);
                    break;
                case 557945654:
                    int[] can2ebR = carbodyEventChangeValue.getCan2ebR();
                    this.x3 = can2ebR;
                    this.h.setCan2eb(can2ebR);
                    break;
                case 557945655:
                    int[] can2f0R = carbodyEventChangeValue.getCan2f0R();
                    this.x3 = can2f0R;
                    this.h.setCan2f0(can2f0R);
                    break;
                case 557945656:
                    int[] can30dR = carbodyEventChangeValue.getCan30dR();
                    this.x3 = can30dR;
                    this.h.setCan30d(can30dR);
                    break;
                case 557945657:
                    int[] can31eR = carbodyEventChangeValue.getCan31eR();
                    this.x3 = can31eR;
                    this.h.setCan31e(can31eR);
                    break;
                case 557945658:
                    int[] can321R = carbodyEventChangeValue.getCan321R();
                    this.x3 = can321R;
                    this.h.setCan321(can321R);
                    break;
                case 557945659:
                    int[] can323R = carbodyEventChangeValue.getCan323R();
                    this.x3 = can323R;
                    this.h.setCan323(can323R);
                    break;
                case 557945660:
                    int[] can32dR = carbodyEventChangeValue.getCan32dR();
                    this.x3 = can32dR;
                    this.h.setCan32d(can32dR);
                    break;
                case 557945661:
                    int[] can334R = carbodyEventChangeValue.getCan334R();
                    this.x3 = can334R;
                    this.h.setCan334(can334R);
                    break;
                case 557945662:
                    int[] can337R = carbodyEventChangeValue.getCan337R();
                    this.x3 = can337R;
                    this.h.setCan337(can337R);
                    break;
                case 557945663:
                    int[] can341R = carbodyEventChangeValue.getCan341R();
                    this.x3 = can341R;
                    this.h.setCan341(can341R);
                    break;
                case 557945664:
                    int[] can342R = carbodyEventChangeValue.getCan342R();
                    this.x3 = can342R;
                    this.h.setCan342(can342R);
                    break;
                case 557945665:
                    int[] can346R = carbodyEventChangeValue.getCan346R();
                    this.x3 = can346R;
                    this.h.setCan346(can346R);
                    break;
                case 557945666:
                    int[] can347R = carbodyEventChangeValue.getCan347R();
                    this.x3 = can347R;
                    this.h.setCan347(can347R);
                    break;
                case 557945667:
                    int[] can34aR = carbodyEventChangeValue.getCan34aR();
                    this.x3 = can34aR;
                    this.h.setCan34a(can34aR);
                    break;
                case 557945668:
                    int[] can34fR = carbodyEventChangeValue.getCan34fR();
                    this.x3 = can34fR;
                    this.h.setCan34f(can34fR);
                    break;
                case 557945669:
                    int[] can36bR = carbodyEventChangeValue.getCan36bR();
                    this.x3 = can36bR;
                    this.h.setCan36b(can36bR);
                    break;
                case 557945670:
                    int[] can36dR = carbodyEventChangeValue.getCan36dR();
                    this.x3 = can36dR;
                    this.h.setCan36d(can36dR);
                    break;
                case 557945671:
                    int[] can36eR = carbodyEventChangeValue.getCan36eR();
                    this.x3 = can36eR;
                    this.h.setCan36e(can36eR);
                    break;
                case 557945672:
                    int[] can37fR = carbodyEventChangeValue.getCan37fR();
                    this.x3 = can37fR;
                    this.h.setCan37f(can37fR);
                    break;
                case 557945673:
                    int[] can38aR = carbodyEventChangeValue.getCan38aR();
                    this.x3 = can38aR;
                    this.h.setCan38a(can38aR);
                    break;
                case 557945674:
                    int[] can38bR = carbodyEventChangeValue.getCan38bR();
                    this.x3 = can38bR;
                    this.h.setCan38b(can38bR);
                    break;
                case 557945675:
                    int[] can394R = carbodyEventChangeValue.getCan394R();
                    this.x3 = can394R;
                    this.h.setCan394(can394R);
                    break;
                case 557945676:
                    int[] can396R = carbodyEventChangeValue.getCan396R();
                    this.x3 = can396R;
                    this.h.setCan396(can396R);
                    break;
                case 557945677:
                    int[] can3a0R = carbodyEventChangeValue.getCan3a0R();
                    this.x3 = can3a0R;
                    this.h.setCan3a0(can3a0R);
                    break;
                case 557945678:
                    int[] can3a7R = carbodyEventChangeValue.getCan3a7R();
                    this.x3 = can3a7R;
                    this.h.setCan3a7(can3a7R);
                    break;
                case 557945679:
                    int[] can3acR = carbodyEventChangeValue.getCan3acR();
                    this.x3 = can3acR;
                    this.h.setCan3ac(can3acR);
                    break;
                case 557945680:
                    int[] can3adR = carbodyEventChangeValue.getCan3adR();
                    this.x3 = can3adR;
                    this.h.setCan3ad(can3adR);
                    break;
                case 557945681:
                    int[] can3b0R = carbodyEventChangeValue.getCan3b0R();
                    this.x3 = can3b0R;
                    this.h.setCan3b0(can3b0R);
                    break;
                case 557945682:
                    int[] can3b3R = carbodyEventChangeValue.getCan3b3R();
                    this.x3 = can3b3R;
                    this.h.setCan3b3(can3b3R);
                    break;
                case 557945683:
                    int[] can3b4R = carbodyEventChangeValue.getCan3b4R();
                    this.x3 = can3b4R;
                    this.h.setCan3b4(can3b4R);
                    break;
                case 557945684:
                    int[] can3bbR = carbodyEventChangeValue.getCan3bbR();
                    this.x3 = can3bbR;
                    this.h.setCan3bb(can3bbR);
                    break;
                case 557945685:
                    int[] can3bdR = carbodyEventChangeValue.getCan3bdR();
                    this.x3 = can3bdR;
                    this.h.setCan3bd(can3bdR);
                    break;
                case 557945686:
                    int[] can3c1R = carbodyEventChangeValue.getCan3c1R();
                    this.x3 = can3c1R;
                    this.h.setCan3c1(can3c1R);
                    break;
                case 557945687:
                    int[] can3c8R = carbodyEventChangeValue.getCan3c8R();
                    this.x3 = can3c8R;
                    this.h.setCan3c8(can3c8R);
                    break;
                case 557945688:
                    int[] can3caR = carbodyEventChangeValue.getCan3caR();
                    this.x3 = can3caR;
                    this.h.setCan3ca(can3caR);
                    break;
                case 557945689:
                    int[] can3cdR = carbodyEventChangeValue.getCan3cdR();
                    this.x3 = can3cdR;
                    this.h.setCan3cd(can3cdR);
                    break;
                case 557945690:
                    int[] can3d9R = carbodyEventChangeValue.getCan3d9R();
                    this.x3 = can3d9R;
                    this.h.setCan3d9(can3d9R);
                    break;
                case 557945691:
                    int[] can3daR = carbodyEventChangeValue.getCan3daR();
                    this.x3 = can3daR;
                    this.h.setCan3da(can3daR);
                    break;
                case 557945692:
                    int[] can3f1R = carbodyEventChangeValue.getCan3f1R();
                    this.x3 = can3f1R;
                    this.h.setCan3f1(can3f1R);
                    break;
                case 557945693:
                    int[] can3f3R = carbodyEventChangeValue.getCan3f3R();
                    this.x3 = can3f3R;
                    this.h.setCan3f3(can3f3R);
                    break;
                case 557945694:
                    int[] can3feR = carbodyEventChangeValue.getCan3feR();
                    this.x3 = can3feR;
                    this.h.setCan3fe(can3feR);
                    break;
                case 557945695:
                    int[] can3ffR = carbodyEventChangeValue.getCan3ffR();
                    this.x3 = can3ffR;
                    this.h.setCan3ff(can3ffR);
                    break;
                case 557945696:
                    int[] can401R = carbodyEventChangeValue.getCan401R();
                    this.x3 = can401R;
                    this.h.setCan401(can401R);
                    break;
                case 557945697:
                    int[] can402R = carbodyEventChangeValue.getCan402R();
                    this.x3 = can402R;
                    this.h.setCan402(can402R);
                    break;
                case 557945698:
                    int[] can404R = carbodyEventChangeValue.getCan404R();
                    this.x3 = can404R;
                    this.h.setCan404(can404R);
                    break;
                case 557945699:
                    int[] can407R = carbodyEventChangeValue.getCan407R();
                    this.x3 = can407R;
                    this.h.setCan407(can407R);
                    break;
                case 557945700:
                    int[] can40dR = carbodyEventChangeValue.getCan40dR();
                    this.x3 = can40dR;
                    this.h.setCan40d(can40dR);
                    break;
                case 557945701:
                    int[] can417R = carbodyEventChangeValue.getCan417R();
                    this.x3 = can417R;
                    this.h.setCan417(can417R);
                    break;
                case 557945702:
                    int[] can418R = carbodyEventChangeValue.getCan418R();
                    this.x3 = can418R;
                    this.h.setCan418(can418R);
                    break;
                case 557945703:
                    int[] can41aR = carbodyEventChangeValue.getCan41aR();
                    this.x3 = can41aR;
                    this.h.setCan41a(can41aR);
                    break;
                case 557945704:
                    int[] can41cR = carbodyEventChangeValue.getCan41cR();
                    this.x3 = can41cR;
                    this.h.setCan41c(can41cR);
                    break;
                case 557945705:
                    int[] can41eR = carbodyEventChangeValue.getCan41eR();
                    this.x3 = can41eR;
                    this.h.setCan41e(can41eR);
                    break;
                case 557945706:
                    int[] can41fR = carbodyEventChangeValue.getCan41fR();
                    this.x3 = can41fR;
                    this.h.setCan41f(can41fR);
                    break;
                case 557945707:
                    int[] can429R = carbodyEventChangeValue.getCan429R();
                    this.x3 = can429R;
                    this.h.setCan429(can429R);
                    break;
                case 557945708:
                    int[] can42eR = carbodyEventChangeValue.getCan42eR();
                    this.x3 = can42eR;
                    this.h.setCan42e(can42eR);
                    break;
                case 557945709:
                    int[] can42fR = carbodyEventChangeValue.getCan42fR();
                    this.x3 = can42fR;
                    this.h.setCan42f(can42fR);
                    break;
                case 557945710:
                    int[] can432R = carbodyEventChangeValue.getCan432R();
                    this.x3 = can432R;
                    this.h.setCan432(can432R);
                    LogUtil.debug(str, "ID_CAN_432_STATE:arr:" + Arrays.toString(this.x3));
                    break;
                case 557945711:
                    int[] can433R = carbodyEventChangeValue.getCan433R();
                    this.x3 = can433R;
                    this.h.setCan433(can433R);
                    break;
                case 557945712:
                    int[] can434R = carbodyEventChangeValue.getCan434R();
                    this.x3 = can434R;
                    this.h.setCan434(can434R);
                    break;
                case 557945713:
                    int[] can436R = carbodyEventChangeValue.getCan436R();
                    this.x3 = can436R;
                    this.h.setCan436(can436R);
                    break;
                case 557945714:
                    int[] can437R = carbodyEventChangeValue.getCan437R();
                    this.x3 = can437R;
                    this.h.setCan437(can437R);
                    break;
                case 557945715:
                    int[] can438R = carbodyEventChangeValue.getCan438R();
                    this.x3 = can438R;
                    this.h.setCan438(can438R);
                    break;
                case 557945716:
                    int[] can43eR = carbodyEventChangeValue.getCan43eR();
                    this.x3 = can43eR;
                    this.h.setCan43e(can43eR);
                    break;
                case 557945717:
                    int[] can43fR = carbodyEventChangeValue.getCan43fR();
                    this.x3 = can43fR;
                    this.h.setCan43f(can43fR);
                    break;
                case 557945718:
                    int[] can443R = carbodyEventChangeValue.getCan443R();
                    this.x3 = can443R;
                    this.h.setCan443(can443R);
                    break;
                case 557945719:
                    int[] can444R = carbodyEventChangeValue.getCan444R();
                    this.x3 = can444R;
                    this.h.setCan444(can444R);
                    break;
                case 557945720:
                    int[] can447R = carbodyEventChangeValue.getCan447R();
                    this.x3 = can447R;
                    this.h.setCan447(can447R);
                    break;
                case 557945721:
                    int[] can449R = carbodyEventChangeValue.getCan449R();
                    this.x3 = can449R;
                    this.h.setCan449(can449R);
                    break;
                case 557945722:
                    int[] can453R = carbodyEventChangeValue.getCan453R();
                    this.x3 = can453R;
                    this.h.setCan453(can453R);
                    break;
                case 557945723:
                    int[] can454R = carbodyEventChangeValue.getCan454R();
                    this.x3 = can454R;
                    this.h.setCan454(can454R);
                    break;
                case 557945724:
                    int[] can461R = carbodyEventChangeValue.getCan461R();
                    this.x3 = can461R;
                    this.h.setCan461(can461R);
                    break;
                case 557945725:
                    int[] can46cR = carbodyEventChangeValue.getCan46cR();
                    this.x3 = can46cR;
                    this.h.setCan46c(can46cR);
                    break;
                case 557945726:
                    int[] can470R = carbodyEventChangeValue.getCan470R();
                    this.x3 = can470R;
                    this.h.setCan470(can470R);
                    break;
                case 557945727:
                    int[] can475R = carbodyEventChangeValue.getCan475R();
                    this.x3 = can475R;
                    this.h.setCan475(can475R);
                    break;
                case 557945728:
                    int[] can480R = carbodyEventChangeValue.getCan480R();
                    this.x3 = can480R;
                    this.h.setCan480(can480R);
                    break;
                case 557945729:
                    int[] can481R = carbodyEventChangeValue.getCan481R();
                    this.x3 = can481R;
                    this.h.setCan481(can481R);
                    break;
                case 557945730:
                    int[] can486R = carbodyEventChangeValue.getCan486R();
                    this.x3 = can486R;
                    this.h.setCan486(can486R);
                    break;
                case 557945731:
                    int[] can487R = carbodyEventChangeValue.getCan487R();
                    this.x3 = can487R;
                    this.h.setCan487(can487R);
                    break;
                case 557945732:
                    int[] can488R = carbodyEventChangeValue.getCan488R();
                    this.x3 = can488R;
                    this.h.setCan488(can488R);
                    break;
                case 557945733:
                    int[] can489R = carbodyEventChangeValue.getCan489R();
                    this.x3 = can489R;
                    this.h.setCan489(can489R);
                    break;
                case 557945734:
                    int[] can48aR = carbodyEventChangeValue.getCan48aR();
                    this.x3 = can48aR;
                    this.h.setCan48a(can48aR);
                    break;
                case 557945735:
                    int[] can48eR = carbodyEventChangeValue.getCan48eR();
                    this.x3 = can48eR;
                    this.h.setCan48e(can48eR);
                    break;
                case 557945736:
                    int[] can48fR = carbodyEventChangeValue.getCan48fR();
                    this.x3 = can48fR;
                    this.h.setCan48f(can48fR);
                    break;
                case 557945737:
                    int[] can494R = carbodyEventChangeValue.getCan494R();
                    this.x3 = can494R;
                    this.h.setCan494(can494R);
                    break;
                case 557945738:
                    int[] can49cR = carbodyEventChangeValue.getCan49cR();
                    this.x3 = can49cR;
                    this.h.setCan49c(can49cR);
                    break;
                case 557945739:
                    int[] can4a1R = carbodyEventChangeValue.getCan4a1R();
                    this.x3 = can4a1R;
                    this.h.setCan4a1(can4a1R);
                    break;
                case 557945740:
                    int[] can4a4R = carbodyEventChangeValue.getCan4a4R();
                    this.x3 = can4a4R;
                    this.h.setCan4a4(can4a4R);
                    break;
                case 557945741:
                    int[] can4a5R = carbodyEventChangeValue.getCan4a5R();
                    this.x3 = can4a5R;
                    this.h.setCan4a5(can4a5R);
                    break;
                case 557945742:
                    int[] can4a7R = carbodyEventChangeValue.getCan4a7R();
                    this.x3 = can4a7R;
                    this.h.setCan4a7(can4a7R);
                    break;
                case 557945743:
                    int[] can4b1R = carbodyEventChangeValue.getCan4b1R();
                    this.x3 = can4b1R;
                    this.h.setCan4b1(can4b1R);
                    break;
                case 557945744:
                    int[] can4b2R = carbodyEventChangeValue.getCan4b2R();
                    this.x3 = can4b2R;
                    this.h.setCan4b2(can4b2R);
                    break;
                case 557945745:
                    int[] can4b3R = carbodyEventChangeValue.getCan4b3R();
                    this.x3 = can4b3R;
                    this.h.setCan4b3(can4b3R);
                    break;
                case 557945746:
                    int[] can4b6R = carbodyEventChangeValue.getCan4b6R();
                    this.x3 = can4b6R;
                    this.h.setCan4b6(can4b6R);
                    break;
                case 557945747:
                    int[] can4bbR = carbodyEventChangeValue.getCan4bbR();
                    this.x3 = can4bbR;
                    this.h.setCan4bb(can4bbR);
                    break;
                case 557945748:
                    int[] can4bdR = carbodyEventChangeValue.getCan4bdR();
                    this.x3 = can4bdR;
                    this.h.setCan4bd(can4bdR);
                    break;
                case 557945749:
                    int[] can4bfR = carbodyEventChangeValue.getCan4bfR();
                    this.x3 = can4bfR;
                    this.h.setCan4bf(can4bfR);
                    break;
                case 557945750:
                    int[] can4c2R = carbodyEventChangeValue.getCan4c2R();
                    this.x3 = can4c2R;
                    this.h.setCan4c2(can4c2R);
                    break;
                case 557945751:
                    int[] can4c8R = carbodyEventChangeValue.getCan4c8R();
                    this.x3 = can4c8R;
                    this.h.setCan4c8(can4c8R);
                    break;
                case 557945752:
                    int[] can4d9R = carbodyEventChangeValue.getCan4d9R();
                    this.x3 = can4d9R;
                    this.h.setCan4d9(can4d9R);
                    break;
                case 557945753:
                    int[] can4e5R = carbodyEventChangeValue.getCan4e5R();
                    this.x3 = can4e5R;
                    this.h.setCan4e5(can4e5R);
                    break;
                case 557945754:
                    int[] can4e6R = carbodyEventChangeValue.getCan4e6R();
                    this.x3 = can4e6R;
                    this.h.setCan4e6(can4e6R);
                    break;
                case 557945755:
                    int[] can4f2R = carbodyEventChangeValue.getCan4f2R();
                    this.x3 = can4f2R;
                    this.h.setCan4f2(can4f2R);
                    break;
                case 557945756:
                    int[] can4f4R = carbodyEventChangeValue.getCan4f4R();
                    this.x3 = can4f4R;
                    this.h.setCan4f4(can4f4R);
                    break;
                case 557945757:
                    int[] can4f6R = carbodyEventChangeValue.getCan4f6R();
                    this.x3 = can4f6R;
                    this.h.setCan4f6(can4f6R);
                    break;
                case 557945758:
                    int[] can4f7R = carbodyEventChangeValue.getCan4f7R();
                    this.x3 = can4f7R;
                    this.h.setCan4f7(can4f7R);
                    break;
                case 557945759:
                    int[] can4faR = carbodyEventChangeValue.getCan4faR();
                    this.x3 = can4faR;
                    this.h.setCan4fa(can4faR);
                    break;
                case 557945760:
                    int[] can4fbR = carbodyEventChangeValue.getCan4fbR();
                    this.x3 = can4fbR;
                    this.h.setCan4fb(can4fbR);
                    break;
                case 557945761:
                    int[] can4fdR = carbodyEventChangeValue.getCan4fdR();
                    this.x3 = can4fdR;
                    this.h.setCan4fd(can4fdR);
                    break;
                case 557945762:
                    int[] can4feR = carbodyEventChangeValue.getCan4feR();
                    this.x3 = can4feR;
                    this.h.setCan4fe(can4feR);
                    break;
                case 557945763:
                    int[] can344R = carbodyEventChangeValue.getCan344R();
                    this.x3 = can344R;
                    this.h.setCan344(can344R);
                    break;
                case 557945764:
                    int[] can319R = carbodyEventChangeValue.getCan319R();
                    this.x3 = can319R;
                    this.h.setCan319(can319R);
                    break;
                case 557945765:
                    int[] can258R = carbodyEventChangeValue.getCan258R();
                    this.x3 = can258R;
                    this.h.setCan258(can258R);
                    break;
                case 557945766:
                    int[] can351R = carbodyEventChangeValue.getCan351R();
                    this.x3 = can351R;
                    this.h.setCan351R(can351R);
                    break;
                case 557945767:
                    int[] can4e8R = carbodyEventChangeValue.getCan4e8R();
                    this.x3 = can4e8R;
                    this.h.setCan4e8R(can4e8R);
                    break;
                case 557945768:
                    int[] can4e9R = carbodyEventChangeValue.getCan4e9R();
                    this.x3 = can4e9R;
                    this.h.setCan4e9R(can4e9R);
                    break;
                case 557945769:
                    int[] can257R = carbodyEventChangeValue.getCan257R();
                    this.x3 = can257R;
                    this.h.setCan257R(can257R);
                    break;
                case 557945770:
                    int[] can48cR = carbodyEventChangeValue.getCan48cR();
                    this.x3 = can48cR;
                    this.h.setCan48cR(can48cR);
                    break;
                case 557945771:
                    int[] can3d6R = carbodyEventChangeValue.getCan3d6R();
                    this.x3 = can3d6R;
                    this.h.setCan3d6R(can3d6R);
                    break;
                case 557945772:
                    int[] can38fR = carbodyEventChangeValue.getCan38fR();
                    this.x3 = can38fR;
                    this.h.setCan38fR(can38fR);
                    break;
                case 557945773:
                    int[] can464R = carbodyEventChangeValue.getCan464R();
                    this.x3 = can464R;
                    this.h.setCan464R(can464R);
                    break;
                case 557945774:
                    int[] can338R = carbodyEventChangeValue.getCan338R();
                    this.x3 = can338R;
                    this.h.setCan338R(can338R);
                    break;
                case 557945775:
                    int[] can145R = carbodyEventChangeValue.getCan145R();
                    this.x3 = can145R;
                    this.h.setCan145R(can145R);
                    break;
                case 557945777:
                    int[] can1eaR = carbodyEventChangeValue.getCan1eaR();
                    this.x3 = can1eaR;
                    this.h.setCan1eaR(can1eaR);
                    break;
                case 557945778:
                    int[] can359R = carbodyEventChangeValue.getCan359R();
                    this.x3 = can359R;
                    this.h.setCan359R(can359R);
                    break;
                case 559954810:
                    float steeringWheelY = carbodyEventChangeValue.getSteeringWheelY();
                    this.h.setSteeringWheelY(steeringWheelY);
                    LogUtil.verbose(str, "ID_STEERING_WHEEL_Y:" + steeringWheelY);
                    break;
                case 559954811:
                    float steeringWheelZ = carbodyEventChangeValue.getSteeringWheelZ();
                    this.h.setSteeringWheelZ(steeringWheelZ);
                    LogUtil.verbose(str, "ID_STEERING_WHEEL_Z:" + steeringWheelZ);
                    break;
                case 559954812:
                    float rearviewMirrorYLeft = carbodyEventChangeValue.getRearviewMirrorYLeft();
                    this.h.setRearviewMirrorYLeft(rearviewMirrorYLeft);
                    LogUtil.verbose(str, "ID_REARVIEW_MIRROR_Y_LEFT :" + rearviewMirrorYLeft);
                    break;
                case 559954813:
                    float rearviewMirrorYRight = carbodyEventChangeValue.getRearviewMirrorYRight();
                    this.h.setRearviewMirrorYRight(rearviewMirrorYRight);
                    LogUtil.verbose(str, "ID_REARVIEW_MIRROR_Y_RIGHT :" + rearviewMirrorYRight);
                    break;
                case 559954814:
                    float rearviewMirrorXLeft = carbodyEventChangeValue.getRearviewMirrorXLeft();
                    this.h.setRearviewMirrorXLeft(rearviewMirrorXLeft);
                    LogUtil.verbose(str, "ID_REARVIEW_MIRROR_X_LEFT :" + rearviewMirrorXLeft);
                    break;
                case 559954815:
                    float rearviewMirrorXRight = carbodyEventChangeValue.getRearviewMirrorXRight();
                    this.h.setRearviewMirrorXRight(rearviewMirrorXRight);
                    LogUtil.verbose(str, "ID_REARVIEW_MIRROR_X_RIGHT :" + rearviewMirrorXRight);
                    break;
                default:
                    switch (i) {
                        case 557857805:
                            int i3 = carbodyEventChangeValue.getonSaveSeatParamsAllChangedPlace5();
                            this.h.setonSaveSeatParamsAllChangedPlace5(i3);
                            LogUtil.verbose(str, "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE5:" + i3);
                            break;
                        case 557857806:
                            int i4 = carbodyEventChangeValue.getonSaveSeatParamsAllChangedPlace7();
                            this.h.setonSaveSeatParamsAllChangedPlace7(i4);
                            LogUtil.verbose(str, "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE7:" + i4);
                            break;
                        default:
                            switch (i) {
                                case 557858048:
                                    this.h.setAutoExternalRearMirrorFoldActionStateR(carbodyEventChangeValue.getAutoExternalRearMirrorFoldActionStateR());
                                    LogUtil.verbose(str, "ID_AUTO_EXTERNAL_REAR_MIRROR_FOLD_ACTION_STATER:" + carbodyEventChangeValue.getAutoExternalRearMirrorFoldActionStateR());
                                    break;
                                case 557858049:
                                    this.h.setAutoExternalRearMirrorFoldedStateR(carbodyEventChangeValue.getAutoExternalRearMirrorFoldedStateR());
                                    LogUtil.verbose(str, "ID_AUTO_EXTERNAL_REAR_MIRROR_FOLDED_STATER:" + carbodyEventChangeValue.getAutoExternalRearMirrorFoldedStateR());
                                    break;
                                default:
                                    switch (i) {
                                        case 557862912:
                                            int windowStateLeftFront = carbodyEventChangeValue.getWindowStateLeftFront();
                                            this.F2 = windowStateLeftFront;
                                            this.h.setmWindowStateLeftFront(windowStateLeftFront);
                                            LogUtil.verbose(str, "ID_WINDOW_STATE_LEFT_FRONT:" + this.F2);
                                            break;
                                        case 557862913:
                                            int windowStateLeftRear = carbodyEventChangeValue.getWindowStateLeftRear();
                                            this.G2 = windowStateLeftRear;
                                            this.h.setmWindowStateLeftRear(windowStateLeftRear);
                                            LogUtil.verbose(str, "ID_WINDOW_STATE_LEFT_REAR:" + this.G2);
                                            break;
                                        case 557862914:
                                            int windowStateRightFront = carbodyEventChangeValue.getWindowStateRightFront();
                                            this.H2 = windowStateRightFront;
                                            this.h.setmWindowStateRightFront(windowStateRightFront);
                                            LogUtil.verbose(str, "ID_WINDOW_STATE_RIGHT_FRONT:" + this.H2);
                                            break;
                                        case 557862915:
                                            int windowStateRightRear = carbodyEventChangeValue.getWindowStateRightRear();
                                            this.I2 = windowStateRightRear;
                                            this.h.setmWindowStateRightRear(windowStateRightRear);
                                            LogUtil.verbose(str, "ID_WINDOW_STATE_RIGHT_REAR:" + this.I2);
                                            break;
                                        case 557862916:
                                            int windowControlStateLeftFrontR = carbodyEventChangeValue.getWindowControlStateLeftFrontR();
                                            this.h.setWindowControlStateLeftFrontR(windowControlStateLeftFrontR);
                                            LogUtil.verbose(str, "ID_WINDOW_CONTROL_STATE_LEFT_FRONT_R:" + windowControlStateLeftFrontR);
                                            break;
                                        case 557862917:
                                            int windowControlStateRightFrontR = carbodyEventChangeValue.getWindowControlStateRightFrontR();
                                            this.h.setWindowControlStateRightFrontR(windowControlStateRightFrontR);
                                            LogUtil.verbose(str, "ID_WINDOW_CONTROL_STATE_RIGHT_FRONT_R:" + windowControlStateRightFrontR);
                                            break;
                                        case 557862918:
                                            int windowControlStateLeftRearR = carbodyEventChangeValue.getWindowControlStateLeftRearR();
                                            this.h.setWindowControlStateLeftRearR(windowControlStateLeftRearR);
                                            LogUtil.verbose(str, "ID_WINDOW_CONTROL_STATE_LEFT_REAR_R:" + windowControlStateLeftRearR);
                                            break;
                                        case 557862919:
                                            int windowControlStateRightRearR = carbodyEventChangeValue.getWindowControlStateRightRearR();
                                            this.h.setWindowControlStateRightRearR(windowControlStateRightRearR);
                                            LogUtil.verbose(str, "ID_WINDOW_CONTROL_STATE_RIGHT_REAR_R:" + windowControlStateRightRearR);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557862924:
                                                    int windowOpenPercentMoonS = carbodyEventChangeValue.getWindowOpenPercentMoonS();
                                                    this.A2 = windowOpenPercentMoonS;
                                                    this.h.setmWindowOpenPercentMoonS(windowOpenPercentMoonS);
                                                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_MOON_S:" + this.A2);
                                                    break;
                                                case 557862925:
                                                    int windowOpenPercentSunS = carbodyEventChangeValue.getWindowOpenPercentSunS();
                                                    this.E2 = windowOpenPercentSunS;
                                                    this.h.setmWindowOpenPercentSunS(windowOpenPercentSunS);
                                                    LogUtil.verbose(str, "ID_WINDOW_OPEN_PERCENT_SUN_S:" + this.E2);
                                                    break;
                                                case 557862926:
                                                    int batteryVoltageLevel = carbodyEventChangeValue.getBatteryVoltageLevel();
                                                    this.m2 = batteryVoltageLevel;
                                                    this.h.setmBatteryVoltageLevel(batteryVoltageLevel);
                                                    LogUtil.verbose(str, "ID_BATTERY_VOLTAGE_LEVEL:" + this.m2);
                                                    break;
                                                case 557862927:
                                                    int powerLevel = carbodyEventChangeValue.getPowerLevel();
                                                    this.u2 = powerLevel;
                                                    this.h.setmPowerLevel(powerLevel);
                                                    LogUtil.verbose(str, "ID_POWER_LEVEL:" + this.u2);
                                                    break;
                                                case 557862928:
                                                    int steeringWheelValueAngel = carbodyEventChangeValue.getSteeringWheelValueAngel();
                                                    this.v2 = steeringWheelValueAngel;
                                                    this.h.setmSteeringWheelValueAngel(steeringWheelValueAngel);
                                                    LogUtil.verbose(str, "ID_STEERING_WHEEL_VALUE_ANGEL:" + this.v2);
                                                    break;
                                                case 557862929:
                                                    int steeringWheelValueSpeed = carbodyEventChangeValue.getSteeringWheelValueSpeed();
                                                    this.w2 = steeringWheelValueSpeed;
                                                    this.h.setmSteeringWheelValueSpeed(steeringWheelValueSpeed);
                                                    LogUtil.verbose(str, "ID_STEERING_WHEEL_VALUE_SPEED:" + this.w2);
                                                    break;
                                                case 557862930:
                                                    int autoType = carbodyEventChangeValue.getAutoType();
                                                    this.l2 = autoType;
                                                    this.h.setmAutoType(autoType);
                                                    LogUtil.verbose(str, "ID_AUTO_TYPE:" + this.l2);
                                                    break;
                                                case 557862931:
                                                    int autoSystemState = carbodyEventChangeValue.getAutoSystemState();
                                                    this.k2 = autoSystemState;
                                                    this.h.setmAutoSystemState(autoSystemState);
                                                    LogUtil.verbose(str, "ID_AUTO_SYSTEM_STATE:" + this.k2);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557862972:
                                                            int rearWindowblindStateR = carbodyEventChangeValue.getRearWindowblindStateR();
                                                            this.v3 = rearWindowblindStateR;
                                                            this.h.setRearWindowblindStateR(rearWindowblindStateR);
                                                            LogUtil.verbose(str, "ID_REAR_WINDOWBLIND_STATER:" + this.v3);
                                                            break;
                                                        case 557862973:
                                                            int rearWindowblindInitStateR = carbodyEventChangeValue.getRearWindowblindInitStateR();
                                                            this.t3 = rearWindowblindInitStateR;
                                                            this.h.setRearWindowblindInitStateR(rearWindowblindInitStateR);
                                                            LogUtil.verbose(str, "ID_REAR_WINDOWBLIND_INIT_STATER:" + this.t3);
                                                            break;
                                                        case 557862974:
                                                            int rearWindowblindPositionR = carbodyEventChangeValue.getRearWindowblindPositionR();
                                                            this.u3 = rearWindowblindPositionR;
                                                            this.h.setRearWindowblindPositionR(rearWindowblindPositionR);
                                                            LogUtil.verbose(str, "ID_REAR_WINDOWBLIND_POSITIONR:" + this.u3);
                                                            break;
                                                        case 557862975:
                                                            int backDoorInitStateR = carbodyEventChangeValue.getBackDoorInitStateR();
                                                            this.Y2 = backDoorInitStateR;
                                                            this.h.setBackDoorInitStateR(backDoorInitStateR);
                                                            LogUtil.verbose(str, "ID_BACK_DOOR_INIT_STATER:" + this.Y2);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 557862978:
                                                                    int backDoorCurStateR = carbodyEventChangeValue.getBackDoorCurStateR();
                                                                    this.Z2 = backDoorCurStateR;
                                                                    this.h.setBackDoorCurStateR(backDoorCurStateR);
                                                                    LogUtil.verbose(str, "ID_BACK_DOOR_CUR_STATER:" + this.Z2);
                                                                    break;
                                                                case 557862979:
                                                                    int rightStrutMorotRunningStateR = carbodyEventChangeValue.getRightStrutMorotRunningStateR();
                                                                    this.a3 = rightStrutMorotRunningStateR;
                                                                    this.h.setRightStrutMorotRunningStateR(rightStrutMorotRunningStateR);
                                                                    LogUtil.verbose(str, "ID_RIGHT_STRUT_MOROT_RUNNING_STATER:" + this.a3);
                                                                    break;
                                                                case 557862980:
                                                                    int backDoorPositionR = carbodyEventChangeValue.getBackDoorPositionR();
                                                                    this.N2 = backDoorPositionR;
                                                                    this.h.setBackDoorPositionR(backDoorPositionR);
                                                                    LogUtil.verbose(str, "ID_BACK_DOOR_POSITIONR:" + this.N2);
                                                                    break;
                                                                case 557862981:
                                                                    int backDoorAntiPlayModeR = carbodyEventChangeValue.getBackDoorAntiPlayModeR();
                                                                    this.b3 = backDoorAntiPlayModeR;
                                                                    this.h.setBackDoorAntiPlayModeR(backDoorAntiPlayModeR);
                                                                    LogUtil.verbose(str, "ID_BACK_DOOR_ANTI_PLAY_MODER:" + this.b3);
                                                                    break;
                                                                case 557862982:
                                                                    int elecSlideDoorStateR = carbodyEventChangeValue.getElecSlideDoorStateR();
                                                                    this.c3 = elecSlideDoorStateR;
                                                                    this.h.setElecSlideDoorStateR(elecSlideDoorStateR);
                                                                    LogUtil.verbose(str, "ID_ELEC_SLIDE_DOOR_STATER:" + this.c3);
                                                                    break;
                                                                case 557862983:
                                                                    int leftSlideDoorStateR = carbodyEventChangeValue.getLeftSlideDoorStateR();
                                                                    this.d3 = leftSlideDoorStateR;
                                                                    this.h.setLeftSlideDoorStateR(leftSlideDoorStateR);
                                                                    LogUtil.verbose(str, "ID_LEFT_SLIDE_DOOR_STATER:" + this.d3);
                                                                    break;
                                                                case 557862984:
                                                                    int rightSlideDoorStateR = carbodyEventChangeValue.getRightSlideDoorStateR();
                                                                    this.e3 = rightSlideDoorStateR;
                                                                    this.h.setRightSlideDoorStateR(rightSlideDoorStateR);
                                                                    LogUtil.verbose(str, "ID_RIGHT_SLIDE_DOOR_STATER:" + this.e3);
                                                                    break;
                                                                case 557862985:
                                                                    int leftSlideDoorActionR = carbodyEventChangeValue.getLeftSlideDoorActionR();
                                                                    this.f3 = leftSlideDoorActionR;
                                                                    this.h.setLeftSlideDoorActionR(leftSlideDoorActionR);
                                                                    LogUtil.verbose(str, "ID_LEFT_SLIDE_DOOR_ACTIONR:" + this.f3);
                                                                    break;
                                                                case 557862986:
                                                                    int rightSlideDoorActionR = carbodyEventChangeValue.getRightSlideDoorActionR();
                                                                    this.g3 = rightSlideDoorActionR;
                                                                    this.h.setRightSlideDoorActionR(rightSlideDoorActionR);
                                                                    LogUtil.verbose(str, "ID_RIGHT_SLIDE_DOOR_ACTIONR:" + this.g3);
                                                                    break;
                                                                case 557862987:
                                                                    int leftSlideDoorPercentR = carbodyEventChangeValue.getLeftSlideDoorPercentR();
                                                                    this.h3 = leftSlideDoorPercentR;
                                                                    this.h.setLeftSlideDoorPercentR(leftSlideDoorPercentR);
                                                                    LogUtil.verbose(str, "ID_LEFT_SLIDE_DOOR_PERCENTR:" + this.h3);
                                                                    break;
                                                                case 557862988:
                                                                    int rightSlideDoorPercentR = carbodyEventChangeValue.getRightSlideDoorPercentR();
                                                                    this.i3 = rightSlideDoorPercentR;
                                                                    this.h.setRightSlideDoorPercentR(rightSlideDoorPercentR);
                                                                    LogUtil.verbose(str, "ID_RIGHT_SLIDE_DOOR_PERCENTR:" + this.i3);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 557863016:
                                                                            int smartVoiceLimit = carbodyEventChangeValue.getSmartVoiceLimit();
                                                                            this.h.setSmartVoiceLimit(smartVoiceLimit);
                                                                            LogUtil.verbose(str, "ID_SMART_VOICE_LIMIT_R:" + smartVoiceLimit);
                                                                            break;
                                                                        case 557863017:
                                                                            int bcmVechileStatus = carbodyEventChangeValue.getBcmVechileStatus();
                                                                            this.h.setBcmVechileStatus(bcmVechileStatus);
                                                                            LogUtil.verbose(str, "ID_B_C_M_VECHILE_STATUS_R:" + bcmVechileStatus);
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 557863022:
                                                                                    this.h.setDoorLockStatusRightFront(carbodyEventChangeValue.getDoorLockStatusRightFront());
                                                                                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_RIGHT_FRONT:" + carbodyEventChangeValue.getDoorLockStatusRightFront());
                                                                                    break;
                                                                                case 557863023:
                                                                                    this.h.setDoorLockStatusLeftRear(carbodyEventChangeValue.getDoorLockStatusLeftRear());
                                                                                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_LEFT_REAR:" + carbodyEventChangeValue.getDoorLockStatusLeftRear());
                                                                                    break;
                                                                                case 557863024:
                                                                                    this.h.setDoorLockStatusAreaRightRear(carbodyEventChangeValue.getDoorLockStatusAreaRightRear());
                                                                                    LogUtil.verbose(str, "ID_DOOR_LOCK_AREA_RIGHT_REAR:" + carbodyEventChangeValue.getDoorLockStatusAreaRightRear());
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 557863041:
                                                                                            int seatModeWakeupFeedbackStateR = carbodyEventChangeValue.getSeatModeWakeupFeedbackStateR();
                                                                                            this.h.setSeatModeWakeupFeedbackStateR(seatModeWakeupFeedbackStateR);
                                                                                            LogUtil.debug(str, "ID_SEAT_MODE_WAKEUP_FEEDBACK_STATE_R:" + seatModeWakeupFeedbackStateR);
                                                                                            break;
                                                                                        case 557863042:
                                                                                            int internalDoorHandleStateLeftFrontR = carbodyEventChangeValue.getInternalDoorHandleStateLeftFrontR();
                                                                                            this.h.setInternalDoorHandleStateLeftFrontR(internalDoorHandleStateLeftFrontR);
                                                                                            LogUtil.debug(str, "ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_FRONT_R:" + internalDoorHandleStateLeftFrontR);
                                                                                            break;
                                                                                        case 557863043:
                                                                                            int internalDoorHandleStateRightFrontR = carbodyEventChangeValue.getInternalDoorHandleStateRightFrontR();
                                                                                            this.h.setInternalDoorHandleStateRightFrontR(internalDoorHandleStateRightFrontR);
                                                                                            LogUtil.debug(str, "ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_FRONT_R:" + internalDoorHandleStateRightFrontR);
                                                                                            break;
                                                                                        case 557863044:
                                                                                            int internalDoorHandleStateLeftBackR = carbodyEventChangeValue.getInternalDoorHandleStateLeftBackR();
                                                                                            this.h.setInternalDoorHandleStateLeftBackR(internalDoorHandleStateLeftBackR);
                                                                                            LogUtil.debug(str, "ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_BACK_R:" + internalDoorHandleStateLeftBackR);
                                                                                            break;
                                                                                        case 557863045:
                                                                                            int internalDoorHandleStateRightBackR = carbodyEventChangeValue.getInternalDoorHandleStateRightBackR();
                                                                                            this.h.setInternalDoorHandleStateRightBackR(internalDoorHandleStateRightBackR);
                                                                                            LogUtil.debug(str, "ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_BACK_R:" + internalDoorHandleStateRightBackR);
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
            this.g.m(i, i2, this.h);
            LogUtil.verbose(str, "notifyCarbodyEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(int i, int i2, ChargingEventChangeValue chargingEventChangeValue) {
        String str = C3;
        try {
            switch (i) {
                case 557855744:
                    int chargerFaultStateR = chargingEventChangeValue.getChargerFaultStateR();
                    this.K = chargerFaultStateR;
                    this.h.setChargerFaultStateR(chargerFaultStateR);
                    LogUtil.verbose(str, "ID_CHARGER_FAULT_STATER:" + this.K);
                    break;
                case 557855745:
                    int chargerWorkStateR = chargingEventChangeValue.getChargerWorkStateR();
                    this.M = chargerWorkStateR;
                    this.h.setChargerWorkStateR(chargerWorkStateR);
                    LogUtil.verbose(str, "ID_CHARGER_WORK_STATER:" + this.M);
                    break;
                case 557855746:
                    int chargingResetTimeHourR = chargingEventChangeValue.getChargingResetTimeHourR();
                    this.Q = chargingResetTimeHourR;
                    this.h.setChargingResetTimeHourR(chargingResetTimeHourR);
                    LogUtil.verbose(str, "ID_CHARGING_RESET_TIME_HOURR:" + this.Q);
                    break;
                case 557855747:
                    int chargingResetTimeMinuteR = chargingEventChangeValue.getChargingResetTimeMinuteR();
                    this.R = chargingResetTimeMinuteR;
                    this.h.setChargingResetTimeMinuteR(chargingResetTimeMinuteR);
                    LogUtil.verbose(str, "ID_CHARGING_RESET_TIME_MINUTER:" + this.R);
                    break;
                case 557855748:
                    int charingPortLockRebackStateR = chargingEventChangeValue.getCharingPortLockRebackStateR();
                    this.Z = charingPortLockRebackStateR;
                    this.h.setCharingPortLockRebackStateR(charingPortLockRebackStateR);
                    LogUtil.verbose(str, "ID_CHARING_PORT_LOCK_REBACK_STATER:" + this.Z);
                    break;
                case 557855749:
                    int dischargeRequestStateR = chargingEventChangeValue.getDischargeRequestStateR();
                    this.b0 = dischargeRequestStateR;
                    this.h.setDischargeRequestStateR(dischargeRequestStateR);
                    LogUtil.verbose(str, "ID_DISCHARGE_REQUEST_STATER:" + this.b0);
                    break;
                case 557855750:
                    int chargerStateR = chargingEventChangeValue.getChargerStateR();
                    this.L = chargerStateR;
                    this.h.setChargerStateR(chargerStateR);
                    LogUtil.verbose(str, "ID_CHARGER_STATER:" + this.L);
                    break;
                case 557855751:
                    int chargingGunStateR = chargingEventChangeValue.getChargingGunStateR();
                    this.O = chargingGunStateR;
                    this.h.setChargingGunStateR(chargingGunStateR);
                    LogUtil.verbose(str, "ID_CHARGING_GUN_STATER:" + this.O);
                    break;
                default:
                    switch (i) {
                        case 557855753:
                            int batteryManagementDeviceStateR = chargingEventChangeValue.getBatteryManagementDeviceStateR();
                            this.J = batteryManagementDeviceStateR;
                            this.h.setBatteryManagementDeviceStateR(batteryManagementDeviceStateR);
                            LogUtil.verbose(str, "ID_BATTERY_MANAGEMENT_DEVICE_STATER:" + this.J);
                            break;
                        case 557855754:
                            int chargingScheduleEnableStateR = chargingEventChangeValue.getChargingScheduleEnableStateR();
                            this.S = chargingScheduleEnableStateR;
                            this.h.setChargingScheduleEnableStateR(chargingScheduleEnableStateR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_ENABLE_STATER:" + this.S);
                            break;
                        case 557855755:
                            int chargingScheduleStateR = chargingEventChangeValue.getChargingScheduleStateR();
                            this.T = chargingScheduleStateR;
                            this.h.setChargingScheduleStateR(chargingScheduleStateR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_STATER:" + this.T);
                            break;
                        case 557855756:
                            int chargingScheduleTimeYearR = chargingEventChangeValue.getChargingScheduleTimeYearR();
                            this.Y = chargingScheduleTimeYearR;
                            this.h.setChargingScheduleTimeYearR(chargingScheduleTimeYearR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_TIME_YEARR:" + this.Y);
                            break;
                        case 557855757:
                            int chargingScheduleTimeMonthR = chargingEventChangeValue.getChargingScheduleTimeMonthR();
                            this.X = chargingScheduleTimeMonthR;
                            this.h.setChargingScheduleTimeMonthR(chargingScheduleTimeMonthR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_TIME_MONTHR:" + this.X);
                            break;
                        case 557855758:
                            int chargingScheduleTimeDayR = chargingEventChangeValue.getChargingScheduleTimeDayR();
                            this.U = chargingScheduleTimeDayR;
                            this.h.setChargingScheduleTimeDayR(chargingScheduleTimeDayR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_TIME_DAYR:" + this.U);
                            break;
                        case 557855759:
                            int chargingScheduleTimeHourR = chargingEventChangeValue.getChargingScheduleTimeHourR();
                            this.V = chargingScheduleTimeHourR;
                            this.h.setChargingScheduleTimeHourR(chargingScheduleTimeHourR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_TIME_HOURR:" + this.V);
                            break;
                        case 557855760:
                            int chargingScheduleTimeMinuteR = chargingEventChangeValue.getChargingScheduleTimeMinuteR();
                            this.W = chargingScheduleTimeMinuteR;
                            this.h.setChargingScheduleTimeMinuteR(chargingScheduleTimeMinuteR);
                            LogUtil.verbose(str, "ID_CHARGING_SCHEDULE_TIME_MINUTER:" + this.W);
                            break;
                        default:
                            switch (i) {
                                case 557855762:
                                    int chargeToggleGuardR = chargingEventChangeValue.getChargeToggleGuardR();
                                    this.N = chargeToggleGuardR;
                                    this.h.setChargeToggleGuardR(chargeToggleGuardR);
                                    LogUtil.verbose(str, "ID_CHARGE_TOGGLE_GUARDR:" + this.N);
                                    break;
                                case 557855764:
                                    int movePowerStationToggleR = chargingEventChangeValue.getMovePowerStationToggleR();
                                    this.g0 = movePowerStationToggleR;
                                    this.h.setMovePowerStationToggleR(movePowerStationToggleR);
                                    LogUtil.verbose(str, "ID_MOVE_POWER_STATION_TOGGLER:" + this.g0);
                                    break;
                                case 557855766:
                                    int dischargeLimitR = chargingEventChangeValue.getDischargeLimitR();
                                    this.a0 = dischargeLimitR;
                                    this.h.setDischargeLimitR(dischargeLimitR);
                                    LogUtil.verbose(str, "ID_DISCHARGE_LIMITR:" + this.a0);
                                    break;
                                case 557855768:
                                    int dischargeToggleR = chargingEventChangeValue.getDischargeToggleR();
                                    this.e0 = dischargeToggleR;
                                    this.h.setDischargeToggleR(dischargeToggleR);
                                    LogUtil.verbose(str, "ID_DISCHARGE_TOGGLER:" + this.e0);
                                    break;
                                case 557855774:
                                    int producePowerWhenLowPowerR = chargingEventChangeValue.getProducePowerWhenLowPowerR();
                                    this.h0 = producePowerWhenLowPowerR;
                                    this.h.setProducePowerWhenLowPowerR(producePowerWhenLowPowerR);
                                    LogUtil.verbose(str, "ID_PRODUCE_POWER_WHEN_LOW_POWERR:" + this.h0);
                                    break;
                                case 557855793:
                                    int vtovDischargeLowestValR = chargingEventChangeValue.getVtovDischargeLowestValR();
                                    this.h.setVtovDischargeLowestValR(vtovDischargeLowestValR);
                                    LogUtil.verbose(str, "ID_VTOV_DISCHARGE_LOWEST_VALR:" + vtovDischargeLowestValR);
                                    break;
                                case 557855798:
                                    int chargerWorkStateFixR = chargingEventChangeValue.getChargerWorkStateFixR();
                                    this.m0 = chargerWorkStateFixR;
                                    this.h.setChargerWorkStateFixR(chargerWorkStateFixR);
                                    LogUtil.verbose(str, "ID_CHARGER_WORK_FIX_STATER:" + this.m0);
                                    break;
                                case 557855804:
                                    int chargeStopCapacityStateR = chargingEventChangeValue.getChargeStopCapacityStateR();
                                    this.Q1 = chargeStopCapacityStateR;
                                    this.h.setChargeStopCapacityStateR(chargeStopCapacityStateR);
                                    LogUtil.verbose(str, "ID_CHARGE_STOP_CAPACITY_STATER:" + this.Q1);
                                    break;
                                case 557855806:
                                    int chargeToaggleGuardMixR = chargingEventChangeValue.getChargeToaggleGuardMixR();
                                    this.l0 = chargeToaggleGuardMixR;
                                    this.h.setChargeToaggleGuardMixR(chargeToaggleGuardMixR);
                                    LogUtil.verbose(str, "ID_CHARGE_TOAGGLE_GUARD_MIXR:" + this.l0);
                                    break;
                                case 557855807:
                                    int vtovDischargeLimitValR = chargingEventChangeValue.getVtovDischargeLimitValR();
                                    this.h.setVtovDischargeLimitValR(vtovDischargeLimitValR);
                                    LogUtil.verbose(str, "ID_VTOV_DISCHARGE_LIMIT_VALR:" + vtovDischargeLimitValR);
                                    break;
                                case 557855808:
                                    int chargingRestTimeHourR = chargingEventChangeValue.getChargingRestTimeHourR();
                                    this.Q = chargingRestTimeHourR;
                                    this.h.setChargingResetTimeHourR(chargingRestTimeHourR);
                                    LogUtil.verbose(str, "ID_CHARGING_RESTTIME_HOURR:" + this.Q);
                                    break;
                                case 557855809:
                                    int chargingRestTimeMinuteR = chargingEventChangeValue.getChargingRestTimeMinuteR();
                                    this.R = chargingRestTimeMinuteR;
                                    this.h.setChargingResetTimeMinuteR(chargingRestTimeMinuteR);
                                    LogUtil.verbose(str, "ID_CHARGING_RESTTIME_MINUTER:" + this.R);
                                    break;
                                case 557855810:
                                    int vtovDischargeConnectState = chargingEventChangeValue.getVtovDischargeConnectState();
                                    this.R1 = vtovDischargeConnectState;
                                    this.h.setVtovDischargeConnectState(vtovDischargeConnectState);
                                    LogUtil.verbose(str, "ID_VTOV_DISCHARGE_CONNECT_STATE:" + this.R1);
                                    break;
                                case 557855811:
                                    int obcWakeNoSenseCharge1R = chargingEventChangeValue.getObcWakeNoSenseCharge1R();
                                    this.g1 = obcWakeNoSenseCharge1R;
                                    this.h.setObcWakeNoSenseCharge1R(obcWakeNoSenseCharge1R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE1_R:" + this.g1);
                                    break;
                                case 557855812:
                                    int obcWakeNoSenseCharge2R = chargingEventChangeValue.getObcWakeNoSenseCharge2R();
                                    this.h1 = obcWakeNoSenseCharge2R;
                                    this.h.setObcWakeNoSenseCharge2R(obcWakeNoSenseCharge2R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE2_R:" + this.h1);
                                    break;
                                case 557855813:
                                    int obcWakeNoSenseCharge3R = chargingEventChangeValue.getObcWakeNoSenseCharge3R();
                                    this.i1 = obcWakeNoSenseCharge3R;
                                    this.h.setObcWakeNoSenseCharge3R(obcWakeNoSenseCharge3R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE3_R:" + this.i1);
                                    break;
                                case 557855814:
                                    int obcWakeNoSenseCharge4R = chargingEventChangeValue.getObcWakeNoSenseCharge4R();
                                    this.j1 = obcWakeNoSenseCharge4R;
                                    this.h.setObcWakeNoSenseCharge4R(obcWakeNoSenseCharge4R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE4_R:" + this.j1);
                                    break;
                                case 557855815:
                                    int obcWakeNoSenseCharge5R = chargingEventChangeValue.getObcWakeNoSenseCharge5R();
                                    this.k1 = obcWakeNoSenseCharge5R;
                                    this.h.setObcWakeNoSenseCharge5R(obcWakeNoSenseCharge5R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE5_R:" + this.k1);
                                    break;
                                case 557855816:
                                    int obcWakeNoSenseCharge6R = chargingEventChangeValue.getObcWakeNoSenseCharge6R();
                                    this.l1 = obcWakeNoSenseCharge6R;
                                    this.h.setObcWakeNoSenseCharge6R(obcWakeNoSenseCharge6R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE6_R:" + this.l1);
                                    break;
                                case 557855817:
                                    int obcWakeNoSenseCharge7R = chargingEventChangeValue.getObcWakeNoSenseCharge7R();
                                    this.m1 = obcWakeNoSenseCharge7R;
                                    this.h.setObcWakeNoSenseCharge7R(obcWakeNoSenseCharge7R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE7_R:" + this.m1);
                                    break;
                                case 557855818:
                                    int obcWakeNoSenseCharge8R = chargingEventChangeValue.getObcWakeNoSenseCharge8R();
                                    this.n1 = obcWakeNoSenseCharge8R;
                                    this.h.setObcWakeNoSenseCharge8R(obcWakeNoSenseCharge8R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE8_R:" + this.n1);
                                    break;
                                case 557855819:
                                    int obcWakeNoSenseCharge9R = chargingEventChangeValue.getObcWakeNoSenseCharge9R();
                                    this.o1 = obcWakeNoSenseCharge9R;
                                    this.h.setObcWakeNoSenseCharge9R(obcWakeNoSenseCharge9R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE9_R:" + this.o1);
                                    break;
                                case 557855820:
                                    int obcWakeNoSenseCharge10R = chargingEventChangeValue.getObcWakeNoSenseCharge10R();
                                    this.p1 = obcWakeNoSenseCharge10R;
                                    this.h.setObcWakeNoSenseCharge10R(obcWakeNoSenseCharge10R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE10_R:" + this.p1);
                                    break;
                                case 557855821:
                                    int obcWakeNoSenseCharge11R = chargingEventChangeValue.getObcWakeNoSenseCharge11R();
                                    this.q1 = obcWakeNoSenseCharge11R;
                                    this.h.setObcWakeNoSenseCharge11R(obcWakeNoSenseCharge11R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE11_R:" + this.q1);
                                    break;
                                case 557855822:
                                    int obcWakeNoSenseCharge12R = chargingEventChangeValue.getObcWakeNoSenseCharge12R();
                                    this.r1 = obcWakeNoSenseCharge12R;
                                    this.h.setObcWakeNoSenseCharge12R(obcWakeNoSenseCharge12R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE12_R:" + this.r1);
                                    break;
                                case 557855823:
                                    int obcWakeNoSenseCharge13R = chargingEventChangeValue.getObcWakeNoSenseCharge13R();
                                    this.s1 = obcWakeNoSenseCharge13R;
                                    this.h.setObcWakeNoSenseCharge13R(obcWakeNoSenseCharge13R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE13_R:" + this.s1);
                                    break;
                                case 557855824:
                                    int obcWakeNoSenseCharge14R = chargingEventChangeValue.getObcWakeNoSenseCharge14R();
                                    this.t1 = obcWakeNoSenseCharge14R;
                                    this.h.setObcWakeNoSenseCharge14R(obcWakeNoSenseCharge14R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE14_R:" + this.t1);
                                    break;
                                case 557855825:
                                    int obcWakeNoSenseCharge15R = chargingEventChangeValue.getObcWakeNoSenseCharge15R();
                                    this.u1 = obcWakeNoSenseCharge15R;
                                    this.h.setObcWakeNoSenseCharge15R(obcWakeNoSenseCharge15R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE15_R:" + this.u1);
                                    break;
                                case 557855826:
                                    int obcWakeNoSenseCharge16R = chargingEventChangeValue.getObcWakeNoSenseCharge16R();
                                    this.v1 = obcWakeNoSenseCharge16R;
                                    this.h.setObcWakeNoSenseCharge16R(obcWakeNoSenseCharge16R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE16_R:" + this.v1);
                                    break;
                                case 557855827:
                                    int obcWakeNoSenseCharge17R = chargingEventChangeValue.getObcWakeNoSenseCharge17R();
                                    this.w1 = obcWakeNoSenseCharge17R;
                                    this.h.setObcWakeNoSenseCharge17R(obcWakeNoSenseCharge17R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE17_R:" + this.w1);
                                    break;
                                case 557855828:
                                    int obcWakeNoSenseCharge18R = chargingEventChangeValue.getObcWakeNoSenseCharge18R();
                                    this.x1 = obcWakeNoSenseCharge18R;
                                    this.h.setObcWakeNoSenseCharge18R(obcWakeNoSenseCharge18R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE18_R:" + this.x1);
                                    break;
                                case 557855829:
                                    int obcWakeNoSenseCharge19R = chargingEventChangeValue.getObcWakeNoSenseCharge19R();
                                    this.y1 = obcWakeNoSenseCharge19R;
                                    this.h.setObcWakeNoSenseCharge19R(obcWakeNoSenseCharge19R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE19_R:" + this.y1);
                                    break;
                                case 557855830:
                                    int obcWakeNoSenseCharge20R = chargingEventChangeValue.getObcWakeNoSenseCharge20R();
                                    this.z1 = obcWakeNoSenseCharge20R;
                                    this.h.setObcWakeNoSenseCharge20R(obcWakeNoSenseCharge20R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE20_R:" + this.z1);
                                    break;
                                case 557855831:
                                    int obcWakeNoSenseCharge21R = chargingEventChangeValue.getObcWakeNoSenseCharge21R();
                                    this.A1 = obcWakeNoSenseCharge21R;
                                    this.h.setObcWakeNoSenseCharge21R(obcWakeNoSenseCharge21R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE21_R:" + this.A1);
                                    break;
                                case 557855832:
                                    int obcWakeNoSenseCharge22R = chargingEventChangeValue.getObcWakeNoSenseCharge22R();
                                    this.B1 = obcWakeNoSenseCharge22R;
                                    this.h.setObcWakeNoSenseCharge22R(obcWakeNoSenseCharge22R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE22_R:" + this.B1);
                                    break;
                                case 557855833:
                                    int obcWakeNoSenseCharge23R = chargingEventChangeValue.getObcWakeNoSenseCharge23R();
                                    this.C1 = obcWakeNoSenseCharge23R;
                                    this.h.setObcWakeNoSenseCharge23R(obcWakeNoSenseCharge23R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE23_R:" + this.C1);
                                    break;
                                case 557855834:
                                    int obcWakeNoSenseCharge24R = chargingEventChangeValue.getObcWakeNoSenseCharge24R();
                                    this.D1 = obcWakeNoSenseCharge24R;
                                    this.h.setObcWakeNoSenseCharge24R(obcWakeNoSenseCharge24R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE24_R:" + this.D1);
                                    break;
                                case 557855835:
                                    int obcWakeNoSenseCharge25R = chargingEventChangeValue.getObcWakeNoSenseCharge25R();
                                    this.E1 = obcWakeNoSenseCharge25R;
                                    this.h.setObcWakeNoSenseCharge25R(obcWakeNoSenseCharge25R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE25_R:" + this.E1);
                                    break;
                                case 557855836:
                                    int obcWakeNoSenseCharge26R = chargingEventChangeValue.getObcWakeNoSenseCharge26R();
                                    this.F1 = obcWakeNoSenseCharge26R;
                                    this.h.setObcWakeNoSenseCharge26R(obcWakeNoSenseCharge26R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE26_R:" + this.F1);
                                    break;
                                case 557855837:
                                    int obcWakeNoSenseCharge27R = chargingEventChangeValue.getObcWakeNoSenseCharge27R();
                                    this.G1 = obcWakeNoSenseCharge27R;
                                    this.h.setObcWakeNoSenseCharge27R(obcWakeNoSenseCharge27R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE27_R:" + this.G1);
                                    break;
                                case 557855838:
                                    int obcWakeNoSenseCharge28R = chargingEventChangeValue.getObcWakeNoSenseCharge28R();
                                    this.H1 = obcWakeNoSenseCharge28R;
                                    this.h.setObcWakeNoSenseCharge28R(obcWakeNoSenseCharge28R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE28_R:" + this.H1);
                                    break;
                                case 557855839:
                                    int obcWakeNoSenseCharge29R = chargingEventChangeValue.getObcWakeNoSenseCharge29R();
                                    this.I1 = obcWakeNoSenseCharge29R;
                                    this.h.setObcWakeNoSenseCharge29R(obcWakeNoSenseCharge29R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE29_R:" + this.I1);
                                    break;
                                case 557855840:
                                    int obcWakeNoSenseCharge30R = chargingEventChangeValue.getObcWakeNoSenseCharge30R();
                                    this.J1 = obcWakeNoSenseCharge30R;
                                    this.h.setObcWakeNoSenseCharge30R(obcWakeNoSenseCharge30R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE30_R:" + this.J1);
                                    break;
                                case 557855841:
                                    int obcWakeNoSenseCharge31R = chargingEventChangeValue.getObcWakeNoSenseCharge31R();
                                    this.K1 = obcWakeNoSenseCharge31R;
                                    this.h.setObcWakeNoSenseCharge31R(obcWakeNoSenseCharge31R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE31_R:" + this.K1);
                                    break;
                                case 557855842:
                                    int obcWakeNoSenseCharge32R = chargingEventChangeValue.getObcWakeNoSenseCharge32R();
                                    this.L1 = obcWakeNoSenseCharge32R;
                                    this.h.setObcWakeNoSenseCharge32R(obcWakeNoSenseCharge32R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE32_R:" + this.L1);
                                    break;
                                case 557855843:
                                    int obcWakeNoSenseCharge33R = chargingEventChangeValue.getObcWakeNoSenseCharge33R();
                                    this.M1 = obcWakeNoSenseCharge33R;
                                    this.h.setObcWakeNoSenseCharge33R(obcWakeNoSenseCharge33R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE33_R:" + this.M1);
                                    break;
                                case 557855844:
                                    int obcWakeNoSenseCharge34R = chargingEventChangeValue.getObcWakeNoSenseCharge34R();
                                    this.N1 = obcWakeNoSenseCharge34R;
                                    this.h.setObcWakeNoSenseCharge34R(obcWakeNoSenseCharge34R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE34_R:" + this.N1);
                                    break;
                                case 557855845:
                                    int obcWakeNoSenseCharge35R = chargingEventChangeValue.getObcWakeNoSenseCharge35R();
                                    this.O1 = obcWakeNoSenseCharge35R;
                                    this.h.setObcWakeNoSenseCharge35R(obcWakeNoSenseCharge35R);
                                    LogUtil.verbose(str, "ID_OBC_WAKE_NO_SENSE_CHARGE35_R:" + this.O1);
                                    break;
                                case 557856266:
                                    int situPowerGenerationStateR = chargingEventChangeValue.getSituPowerGenerationStateR();
                                    this.q0 = situPowerGenerationStateR;
                                    this.h.setSituPowerGenerationStateR(situPowerGenerationStateR);
                                    LogUtil.verbose(str, "ID_SITU_POWER_GENERATION_STATE_R:" + this.q0);
                                    break;
                                case 557856276:
                                    int noSenseChargeStateR = chargingEventChangeValue.getNoSenseChargeStateR();
                                    this.w0 = noSenseChargeStateR;
                                    this.h.setNoSenseChargeStateR(noSenseChargeStateR);
                                    LogUtil.verbose(str, "ID_NO_SENSE_CHARGE_STATE_R:" + this.w0);
                                    break;
                                case 557856280:
                                    int acChargingLimitR = chargingEventChangeValue.getAcChargingLimitR();
                                    this.h.setAcChargingLimitR(acChargingLimitR);
                                    LogUtil.verbose(str, "ID_A_C_CHARGING_LIMIT_R:" + acChargingLimitR);
                                    break;
                                case 557856339:
                                    int chargeStopCapacityStateNotLiR = chargingEventChangeValue.getChargeStopCapacityStateNotLiR();
                                    this.h.setChargeStopCapacityStateNotLiR(chargeStopCapacityStateNotLiR);
                                    LogUtil.verbose(str, "ID_CHARGE_STOP_CAPACITY_STATE_NOT_LI_R:" + chargeStopCapacityStateNotLiR);
                                    break;
                                case 557856437:
                                    int hevChargePlaneEndTimeYearR = chargingEventChangeValue.getHevChargePlaneEndTimeYearR();
                                    this.h.setHevChargePlaneEndTimeYearR(hevChargePlaneEndTimeYearR);
                                    LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R:" + hevChargePlaneEndTimeYearR);
                                    break;
                                case 557856438:
                                    int hevChargePlanEndTimeMonthR = chargingEventChangeValue.getHevChargePlanEndTimeMonthR();
                                    this.h.setHevChargePlanEndTimeMonthR(hevChargePlanEndTimeMonthR);
                                    LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R:" + hevChargePlanEndTimeMonthR);
                                    break;
                                case 557921815:
                                    int[] hevChargePlanEndTimeR = chargingEventChangeValue.getHevChargePlanEndTimeR();
                                    this.y3 = hevChargePlanEndTimeR;
                                    this.h.setHevChargePlanEndTimeR(hevChargePlanEndTimeR);
                                    LogUtil.verbose(str, "ID_H_E_V_CHARGE_PLAN_END_TIME_R:" + Arrays.toString(this.y3));
                                    break;
                                case 559952904:
                                    float chargingPowerR = chargingEventChangeValue.getChargingPowerR();
                                    this.P = chargingPowerR;
                                    this.h.setChargingPowerR(chargingPowerR);
                                    LogUtil.verbose(str, "ID_CHARGING_POWERR:" + this.P);
                                    break;
                                case 559952946:
                                    float vtovDischargeQuantityR = chargingEventChangeValue.getVtovDischargeQuantityR();
                                    this.f0 = vtovDischargeQuantityR;
                                    this.h.setVtovDischargeQuantityR(vtovDischargeQuantityR);
                                    LogUtil.verbose(str, "ID_VTOV_DISCHARGE_QUANTITYR:" + this.f0);
                                    break;
                                case 567856339:
                                    int hevChargePlanEndTimeYearDayR = chargingEventChangeValue.getHevChargePlanEndTimeYearDayR();
                                    this.h.setHevChargePlanEndTimeYearDayR(hevChargePlanEndTimeYearDayR);
                                    LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_END_TIME_DAY_R:" + hevChargePlanEndTimeYearDayR);
                                    break;
                                case 567856340:
                                    int hevChargePlanEndTimeYearHourR = chargingEventChangeValue.getHevChargePlanEndTimeYearHourR();
                                    this.h.setHevChargePlanEndTimeYearHourR(hevChargePlanEndTimeYearHourR);
                                    LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R:" + hevChargePlanEndTimeYearHourR);
                                    break;
                                case 567856341:
                                    int hevChargePlanEndTimeYearMinuteR = chargingEventChangeValue.getHevChargePlanEndTimeYearMinuteR();
                                    this.h.setHevChargePlanEndTimeYearMinuteR(hevChargePlanEndTimeYearMinuteR);
                                    LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R:" + hevChargePlanEndTimeYearMinuteR);
                                    break;
                                default:
                                    switch (i) {
                                        case 557855771:
                                            int dischargeTimeHourR = chargingEventChangeValue.getDischargeTimeHourR();
                                            this.c0 = dischargeTimeHourR;
                                            this.h.setDischargeTimeHourR(dischargeTimeHourR);
                                            LogUtil.verbose(str, "ID_DISCHARGE_TIME_HOURR:" + this.c0);
                                            break;
                                        case 557855772:
                                            int dischargeTimeMinuteR = chargingEventChangeValue.getDischargeTimeMinuteR();
                                            this.d0 = dischargeTimeMinuteR;
                                            this.h.setDischargeTimeMinuteR(dischargeTimeMinuteR);
                                            LogUtil.verbose(str, "ID_DISCHARGE_TIME_MINUTER:" + this.d0);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557855776:
                                                    int wirelessChargingSwitchStateR = chargingEventChangeValue.getWirelessChargingSwitchStateR();
                                                    this.j0 = wirelessChargingSwitchStateR;
                                                    this.h.setWirelessChargingSwitchStateR(wirelessChargingSwitchStateR);
                                                    LogUtil.verbose(str, "ID_WIRELESS_CHARGING_SWITCH_STATER:" + this.j0);
                                                    break;
                                                case 557855777:
                                                    int wirelessChargingStateR = chargingEventChangeValue.getWirelessChargingStateR();
                                                    this.i0 = wirelessChargingStateR;
                                                    this.h.setWirelessChargingStateR(wirelessChargingStateR);
                                                    LogUtil.verbose(str, "ID_WIRELESS_CHARGING_STATER:" + this.i0);
                                                    break;
                                                case 557855778:
                                                    int vtovChargeBatteryLevelNumR = chargingEventChangeValue.getVtovChargeBatteryLevelNumR();
                                                    this.k0 = vtovChargeBatteryLevelNumR;
                                                    this.h.setVtovChargeBatteryLevelNumR(vtovChargeBatteryLevelNumR);
                                                    LogUtil.verbose(str, "ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR:" + this.k0);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557855800:
                                                            int chargingCapStateLeftR = chargingEventChangeValue.getChargingCapStateLeftR();
                                                            this.n0 = chargingCapStateLeftR;
                                                            this.h.setChargingCapStateLeftR(chargingCapStateLeftR);
                                                            LogUtil.verbose(str, "ID_CHARGING_CAP_STATE_LEFTR:" + this.n0);
                                                            break;
                                                        case 557855801:
                                                            int chargingCapStateRightR = chargingEventChangeValue.getChargingCapStateRightR();
                                                            this.o0 = chargingCapStateRightR;
                                                            this.h.setChargingCapStateRightR(chargingCapStateRightR);
                                                            LogUtil.verbose(str, "ID_CHARGING_CAP_STATE_RIGHTR:" + this.o0);
                                                            break;
                                                        case 557855802:
                                                            int chargeTempCtlStateR = chargingEventChangeValue.getChargeTempCtlStateR();
                                                            this.p0 = chargeTempCtlStateR;
                                                            this.h.setChargeTempCtlStateR(chargeTempCtlStateR);
                                                            LogUtil.verbose(str, "ID_CHARGE_TEMP_CTL_STATER:" + this.p0);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 557856268:
                                                                    int situPowerGenerationSupportR = chargingEventChangeValue.getSituPowerGenerationSupportR();
                                                                    this.r0 = situPowerGenerationSupportR;
                                                                    this.h.setSituPowerGenerationSupportR(situPowerGenerationSupportR);
                                                                    LogUtil.verbose(str, "ID_SITU_POWER_GENERATION_SUPPORT_R:" + this.r0);
                                                                    break;
                                                                case 557856269:
                                                                    int chargePreheatStateR = chargingEventChangeValue.getChargePreheatStateR();
                                                                    this.s0 = chargePreheatStateR;
                                                                    this.h.setChargePreheatStateR(chargePreheatStateR);
                                                                    LogUtil.verbose(str, "ID_CHARGE_PREHEAT_STATE_R:" + this.s0);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 557856272:
                                                                            int carPlanStateR = chargingEventChangeValue.getCarPlanStateR();
                                                                            this.t0 = carPlanStateR;
                                                                            this.h.setCarPlanStateR(carPlanStateR);
                                                                            LogUtil.verbose(str, "ID_CAR_PLAN_STATE_R:" + this.t0);
                                                                            break;
                                                                        case 557856273:
                                                                            int carPlanAccOnStateR = chargingEventChangeValue.getCarPlanAccOnStateR();
                                                                            this.u0 = carPlanAccOnStateR;
                                                                            this.h.setCarPlanAccOnStateR(carPlanAccOnStateR);
                                                                            LogUtil.verbose(str, "ID_CAR_PLAN_ACC_ON_STATE_R:" + this.u0);
                                                                            break;
                                                                        case 557856274:
                                                                            int carPlanBatteryHeatStateR = chargingEventChangeValue.getCarPlanBatteryHeatStateR();
                                                                            this.v0 = carPlanBatteryHeatStateR;
                                                                            this.h.setCarPlanBatteryHeatStateR(carPlanBatteryHeatStateR);
                                                                            LogUtil.verbose(str, "ID_CAR_PLAN_BATTERY_HEAT_STATE_R:" + this.v0);
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 557856288:
                                                                                    int bmsWakeNoSenseCharge1R = chargingEventChangeValue.getBmsWakeNoSenseCharge1R();
                                                                                    this.x0 = bmsWakeNoSenseCharge1R;
                                                                                    this.h.setBmsWakeNoSenseCharge1R(bmsWakeNoSenseCharge1R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE1_R:" + this.x0);
                                                                                    break;
                                                                                case 557856289:
                                                                                    int bmsWakeNoSenseCharge2R = chargingEventChangeValue.getBmsWakeNoSenseCharge2R();
                                                                                    this.y0 = bmsWakeNoSenseCharge2R;
                                                                                    this.h.setBmsWakeNoSenseCharge2R(bmsWakeNoSenseCharge2R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE2_R:" + this.y0);
                                                                                    break;
                                                                                case 557856290:
                                                                                    int bmsWakeNoSenseCharge3R = chargingEventChangeValue.getBmsWakeNoSenseCharge3R();
                                                                                    this.z0 = bmsWakeNoSenseCharge3R;
                                                                                    this.h.setBmsWakeNoSenseCharge3R(bmsWakeNoSenseCharge3R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE3_R:" + this.z0);
                                                                                    break;
                                                                                case 557856291:
                                                                                    int bmsWakeNoSenseCharge4R = chargingEventChangeValue.getBmsWakeNoSenseCharge4R();
                                                                                    this.A0 = bmsWakeNoSenseCharge4R;
                                                                                    this.h.setBmsWakeNoSenseCharge4R(bmsWakeNoSenseCharge4R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE4_R:" + this.A0);
                                                                                    break;
                                                                                case 557856292:
                                                                                    int bmsWakeNoSenseCharge5R = chargingEventChangeValue.getBmsWakeNoSenseCharge5R();
                                                                                    this.B0 = bmsWakeNoSenseCharge5R;
                                                                                    this.h.setBmsWakeNoSenseCharge5R(bmsWakeNoSenseCharge5R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE5_R:" + this.B0);
                                                                                    break;
                                                                                case 557856293:
                                                                                    int bmsWakeNoSenseCharge6R = chargingEventChangeValue.getBmsWakeNoSenseCharge6R();
                                                                                    this.C0 = bmsWakeNoSenseCharge6R;
                                                                                    this.h.setBmsWakeNoSenseCharge6R(bmsWakeNoSenseCharge6R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE6_R:" + this.C0);
                                                                                    break;
                                                                                case 557856294:
                                                                                    int bmsWakeNoSenseCharge7R = chargingEventChangeValue.getBmsWakeNoSenseCharge7R();
                                                                                    this.D0 = bmsWakeNoSenseCharge7R;
                                                                                    this.h.setBmsWakeNoSenseCharge7R(bmsWakeNoSenseCharge7R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE7_R:" + this.D0);
                                                                                    break;
                                                                                case 557856295:
                                                                                    int bmsWakeNoSenseCharge8R = chargingEventChangeValue.getBmsWakeNoSenseCharge8R();
                                                                                    this.E0 = bmsWakeNoSenseCharge8R;
                                                                                    this.h.setBmsWakeNoSenseCharge8R(bmsWakeNoSenseCharge8R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE8_R:" + this.E0);
                                                                                    break;
                                                                                case 557856296:
                                                                                    int bmsWakeNoSenseCharge9R = chargingEventChangeValue.getBmsWakeNoSenseCharge9R();
                                                                                    this.F0 = bmsWakeNoSenseCharge9R;
                                                                                    this.h.setBmsWakeNoSenseCharge9R(bmsWakeNoSenseCharge9R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE9_R:" + this.F0);
                                                                                    break;
                                                                                case 557856297:
                                                                                    int bmsWakeNoSenseCharge10R = chargingEventChangeValue.getBmsWakeNoSenseCharge10R();
                                                                                    this.G0 = bmsWakeNoSenseCharge10R;
                                                                                    this.h.setBmsWakeNoSenseCharge10R(bmsWakeNoSenseCharge10R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE10_R:" + this.G0);
                                                                                    break;
                                                                                case 557856298:
                                                                                    int bmsWakeNoSenseCharge11R = chargingEventChangeValue.getBmsWakeNoSenseCharge11R();
                                                                                    this.H0 = bmsWakeNoSenseCharge11R;
                                                                                    this.h.setBmsWakeNoSenseCharge11R(bmsWakeNoSenseCharge11R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE11_R:" + this.H0);
                                                                                    break;
                                                                                case 557856299:
                                                                                    int bmsWakeNoSenseCharge12R = chargingEventChangeValue.getBmsWakeNoSenseCharge12R();
                                                                                    this.I0 = bmsWakeNoSenseCharge12R;
                                                                                    this.h.setBmsWakeNoSenseCharge12R(bmsWakeNoSenseCharge12R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE12_R:" + this.I0);
                                                                                    break;
                                                                                case 557856300:
                                                                                    int bmsWakeNoSenseCharge13R = chargingEventChangeValue.getBmsWakeNoSenseCharge13R();
                                                                                    this.J0 = bmsWakeNoSenseCharge13R;
                                                                                    this.h.setBmsWakeNoSenseCharge13R(bmsWakeNoSenseCharge13R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE13_R:" + this.J0);
                                                                                    break;
                                                                                case 557856301:
                                                                                    int bmsWakeNoSenseCharge14R = chargingEventChangeValue.getBmsWakeNoSenseCharge14R();
                                                                                    this.K0 = bmsWakeNoSenseCharge14R;
                                                                                    this.h.setBmsWakeNoSenseCharge14R(bmsWakeNoSenseCharge14R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE14_R:" + this.K0);
                                                                                    break;
                                                                                case 557856302:
                                                                                    int bmsWakeNoSenseCharge15R = chargingEventChangeValue.getBmsWakeNoSenseCharge15R();
                                                                                    this.L0 = bmsWakeNoSenseCharge15R;
                                                                                    this.h.setBmsWakeNoSenseCharge15R(bmsWakeNoSenseCharge15R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE15_R:" + this.L0);
                                                                                    break;
                                                                                case 557856303:
                                                                                    int bmsWakeNoSenseCharge16R = chargingEventChangeValue.getBmsWakeNoSenseCharge16R();
                                                                                    this.M0 = bmsWakeNoSenseCharge16R;
                                                                                    this.h.setBmsWakeNoSenseCharge16R(bmsWakeNoSenseCharge16R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE16_R:" + this.M0);
                                                                                    break;
                                                                                case 557856304:
                                                                                    int bmsWakeNoSenseCharge17R = chargingEventChangeValue.getBmsWakeNoSenseCharge17R();
                                                                                    this.N0 = bmsWakeNoSenseCharge17R;
                                                                                    this.h.setBmsWakeNoSenseCharge17R(bmsWakeNoSenseCharge17R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE17_R:" + this.N0);
                                                                                    break;
                                                                                case 557856305:
                                                                                    int bmsWakeNoSenseCharge18R = chargingEventChangeValue.getBmsWakeNoSenseCharge18R();
                                                                                    this.O0 = bmsWakeNoSenseCharge18R;
                                                                                    this.h.setBmsWakeNoSenseCharge18R(bmsWakeNoSenseCharge18R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE18_R:" + this.O0);
                                                                                    break;
                                                                                case 557856306:
                                                                                    int bmsWakeNoSenseCharge19R = chargingEventChangeValue.getBmsWakeNoSenseCharge19R();
                                                                                    this.P0 = bmsWakeNoSenseCharge19R;
                                                                                    this.h.setBmsWakeNoSenseCharge19R(bmsWakeNoSenseCharge19R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE19_R:" + this.P0);
                                                                                    break;
                                                                                case 557856307:
                                                                                    int bmsWakeNoSenseCharge20R = chargingEventChangeValue.getBmsWakeNoSenseCharge20R();
                                                                                    this.Q0 = bmsWakeNoSenseCharge20R;
                                                                                    this.h.setBmsWakeNoSenseCharge20R(bmsWakeNoSenseCharge20R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE20_R:" + this.Q0);
                                                                                    break;
                                                                                case 557856308:
                                                                                    int bmsWakeNoSenseCharge21R = chargingEventChangeValue.getBmsWakeNoSenseCharge21R();
                                                                                    this.R0 = bmsWakeNoSenseCharge21R;
                                                                                    this.h.setBmsWakeNoSenseCharge21R(bmsWakeNoSenseCharge21R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE21_R:" + this.R0);
                                                                                    break;
                                                                                case 557856309:
                                                                                    int bmsWakeNoSenseCharge22R = chargingEventChangeValue.getBmsWakeNoSenseCharge22R();
                                                                                    this.S0 = bmsWakeNoSenseCharge22R;
                                                                                    this.h.setBmsWakeNoSenseCharge22R(bmsWakeNoSenseCharge22R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE22_R:" + this.S0);
                                                                                    break;
                                                                                case 557856310:
                                                                                    int bmsWakeNoSenseCharge23R = chargingEventChangeValue.getBmsWakeNoSenseCharge23R();
                                                                                    this.T0 = bmsWakeNoSenseCharge23R;
                                                                                    this.h.setBmsWakeNoSenseCharge23R(bmsWakeNoSenseCharge23R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE23_R:" + this.T0);
                                                                                    break;
                                                                                case 557856311:
                                                                                    int bmsWakeNoSenseCharge24R = chargingEventChangeValue.getBmsWakeNoSenseCharge24R();
                                                                                    this.U0 = bmsWakeNoSenseCharge24R;
                                                                                    this.h.setBmsWakeNoSenseCharge24R(bmsWakeNoSenseCharge24R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE24_R:" + this.U0);
                                                                                    break;
                                                                                case 557856312:
                                                                                    int bmsWakeNoSenseCharge25R = chargingEventChangeValue.getBmsWakeNoSenseCharge25R();
                                                                                    this.V0 = bmsWakeNoSenseCharge25R;
                                                                                    this.h.setBmsWakeNoSenseCharge25R(bmsWakeNoSenseCharge25R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE25_R:" + this.V0);
                                                                                    break;
                                                                                case 557856313:
                                                                                    int bmsWakeNoSenseCharge26R = chargingEventChangeValue.getBmsWakeNoSenseCharge26R();
                                                                                    this.W0 = bmsWakeNoSenseCharge26R;
                                                                                    this.h.setBmsWakeNoSenseCharge26R(bmsWakeNoSenseCharge26R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE26_R:" + this.W0);
                                                                                    break;
                                                                                case 557856314:
                                                                                    int bmsWakeNoSenseCharge27R = chargingEventChangeValue.getBmsWakeNoSenseCharge27R();
                                                                                    this.X0 = bmsWakeNoSenseCharge27R;
                                                                                    this.h.setBmsWakeNoSenseCharge27R(bmsWakeNoSenseCharge27R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE27_R:" + this.X0);
                                                                                    break;
                                                                                case 557856315:
                                                                                    int bmsWakeNoSenseCharge28R = chargingEventChangeValue.getBmsWakeNoSenseCharge28R();
                                                                                    this.Y0 = bmsWakeNoSenseCharge28R;
                                                                                    this.h.setBmsWakeNoSenseCharge28R(bmsWakeNoSenseCharge28R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE28_R:" + this.Y0);
                                                                                    break;
                                                                                case 557856316:
                                                                                    int bmsWakeNoSenseCharge29R = chargingEventChangeValue.getBmsWakeNoSenseCharge29R();
                                                                                    this.Z0 = bmsWakeNoSenseCharge29R;
                                                                                    this.h.setBmsWakeNoSenseCharge29R(bmsWakeNoSenseCharge29R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE29_R:" + this.Z0);
                                                                                    break;
                                                                                case 557856317:
                                                                                    int bmsWakeNoSenseCharge30R = chargingEventChangeValue.getBmsWakeNoSenseCharge30R();
                                                                                    this.a1 = bmsWakeNoSenseCharge30R;
                                                                                    this.h.setBmsWakeNoSenseCharge30R(bmsWakeNoSenseCharge30R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE30_R:" + this.a1);
                                                                                    break;
                                                                                case 557856318:
                                                                                    int bmsWakeNoSenseCharge31R = chargingEventChangeValue.getBmsWakeNoSenseCharge31R();
                                                                                    this.b1 = bmsWakeNoSenseCharge31R;
                                                                                    this.h.setBmsWakeNoSenseCharge31R(bmsWakeNoSenseCharge31R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE31_R:" + this.b1);
                                                                                    break;
                                                                                case 557856319:
                                                                                    int bmsWakeNoSenseCharge32R = chargingEventChangeValue.getBmsWakeNoSenseCharge32R();
                                                                                    this.c1 = bmsWakeNoSenseCharge32R;
                                                                                    this.h.setBmsWakeNoSenseCharge32R(bmsWakeNoSenseCharge32R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE32_R:" + this.c1);
                                                                                    break;
                                                                                case 557856320:
                                                                                    int bmsWakeNoSenseCharge33R = chargingEventChangeValue.getBmsWakeNoSenseCharge33R();
                                                                                    this.d1 = bmsWakeNoSenseCharge33R;
                                                                                    this.h.setBmsWakeNoSenseCharge33R(bmsWakeNoSenseCharge33R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE33_R:" + this.d1);
                                                                                    break;
                                                                                case 557856321:
                                                                                    int bmsWakeNoSenseCharge34R = chargingEventChangeValue.getBmsWakeNoSenseCharge34R();
                                                                                    this.e1 = bmsWakeNoSenseCharge34R;
                                                                                    this.h.setBmsWakeNoSenseCharge34R(bmsWakeNoSenseCharge34R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE34_R:" + this.e1);
                                                                                    break;
                                                                                case 557856322:
                                                                                    int bmsWakeNoSenseCharge35R = chargingEventChangeValue.getBmsWakeNoSenseCharge35R();
                                                                                    this.f1 = bmsWakeNoSenseCharge35R;
                                                                                    this.h.setBmsWakeNoSenseCharge35R(bmsWakeNoSenseCharge35R);
                                                                                    LogUtil.verbose(str, "ID_BMS_WAKE_SENSE_CHARGE35_R:" + this.f1);
                                                                                    break;
                                                                                case 557856323:
                                                                                    int vtovDischargeLowPowerHintR = chargingEventChangeValue.getVtovDischargeLowPowerHintR();
                                                                                    this.P1 = vtovDischargeLowPowerHintR;
                                                                                    this.h.setVtovDischargeLowPowerHintR(vtovDischargeLowPowerHintR);
                                                                                    LogUtil.verbose(str, "ID_VTOV_DISCHARGE_LOW_POWER_HINT_R:" + this.P1);
                                                                                    break;
                                                                                case 557856324:
                                                                                    int chargePreheatAllowR = chargingEventChangeValue.getChargePreheatAllowR();
                                                                                    this.S1 = chargePreheatAllowR;
                                                                                    this.h.setChargePreheatAllowR(chargePreheatAllowR);
                                                                                    LogUtil.verbose(str, "ID_CHARGE_PREHEAT_ALLOW_R" + this.S1);
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 557856326:
                                                                                            int wirelessChargingFrSwitchStateR = chargingEventChangeValue.getWirelessChargingFrSwitchStateR();
                                                                                            this.h.setWirelessChargingFrSwitchStateR(wirelessChargingFrSwitchStateR);
                                                                                            LogUtil.verbose(str, "ID_WIRELESS_CHARGING_F_R_SWITCH_STATE_R:" + wirelessChargingFrSwitchStateR);
                                                                                            break;
                                                                                        case 557856327:
                                                                                            int wirelessChargingFrStateR = chargingEventChangeValue.getWirelessChargingFrStateR();
                                                                                            this.h.setWirelessChargingFrStateR(wirelessChargingFrStateR);
                                                                                            LogUtil.verbose(str, "ID_WIRELESS_CHARGING_F_R_STATE_R:" + wirelessChargingFrStateR);
                                                                                            break;
                                                                                        case 557856328:
                                                                                            int wirelessChargingPowerFlStateR = chargingEventChangeValue.getWirelessChargingPowerFlStateR();
                                                                                            this.h.setWirelessChargingPowerFlStateR(wirelessChargingPowerFlStateR);
                                                                                            LogUtil.verbose(str, "ID_WIRELESS_CHARGING_POWER_F_L_STATE_R:" + wirelessChargingPowerFlStateR);
                                                                                            break;
                                                                                        case 557856329:
                                                                                            int wirelessChargingPowerFrStateR = chargingEventChangeValue.getWirelessChargingPowerFrStateR();
                                                                                            this.h.setWirelessChargingPowerFrStateR(wirelessChargingPowerFrStateR);
                                                                                            LogUtil.verbose(str, "ID_WIRELESS_CHARGING_POWER_F_R_STATE_R:" + wirelessChargingPowerFrStateR);
                                                                                            break;
                                                                                        case 557856330:
                                                                                            int i3 = chargingEventChangeValue.getgetPortCapStateRightR();
                                                                                            this.h.setgetPortCapStateRightR(i3);
                                                                                            LogUtil.verbose(str, "ID_PORT_CAP_STATE_RIGHT_R:" + i3);
                                                                                            break;
                                                                                        case 557856331:
                                                                                            int i4 = chargingEventChangeValue.getgetPortCapStateLeftR();
                                                                                            this.h.setgetPortCapStateLeftR(i4);
                                                                                            LogUtil.verbose(str, "ID_PORT_CAP_STATE_LEFT_R:" + i4);
                                                                                            break;
                                                                                        case 557856332:
                                                                                            int hevChargePlanStartTimeYearR = chargingEventChangeValue.getHevChargePlanStartTimeYearR();
                                                                                            this.h.setHevChargePlanStartTimeYearR(hevChargePlanStartTimeYearR);
                                                                                            LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_START_TIME_YEAR_R:" + hevChargePlanStartTimeYearR);
                                                                                            break;
                                                                                        case 557856333:
                                                                                            int hevChargePlanStartTimeMonthR = chargingEventChangeValue.getHevChargePlanStartTimeMonthR();
                                                                                            this.h.setHevChargePlanStartTimeMonthR(hevChargePlanStartTimeMonthR);
                                                                                            LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_START_TIME_MONTH_R:" + hevChargePlanStartTimeMonthR);
                                                                                            break;
                                                                                        case 557856334:
                                                                                            int hevChargePlanStartTimeYearDayR = chargingEventChangeValue.getHevChargePlanStartTimeYearDayR();
                                                                                            this.h.setHevChargePlanStartTimeYearDayR(hevChargePlanStartTimeYearDayR);
                                                                                            LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_START_TIME_DAY_R:" + hevChargePlanStartTimeYearDayR);
                                                                                            break;
                                                                                        case 557856335:
                                                                                            int hevChargePlanStartTimeYearHourR = chargingEventChangeValue.getHevChargePlanStartTimeYearHourR();
                                                                                            this.h.setHevChargePlanStartTimeYearHourR(hevChargePlanStartTimeYearHourR);
                                                                                            LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_START_TIME_HOUR_R:" + hevChargePlanStartTimeYearHourR);
                                                                                            break;
                                                                                        case 557856336:
                                                                                            int hevChargePlanStartTimeYearMinuteR = chargingEventChangeValue.getHevChargePlanStartTimeYearMinuteR();
                                                                                            this.h.setHevChargePlanStartTimeYearMinuteR(hevChargePlanStartTimeYearMinuteR);
                                                                                            LogUtil.verbose(str, "ID_HEV_CHARGE_PLAN_START_TIME_MINUTE_R:" + hevChargePlanStartTimeYearMinuteR);
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
            this.g.m(i, chargingEventChangeValue.getAreaId(), this.h);
            LogUtil.verbose(str, "notifyChargingEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void C(int i, int i2, LightEventChangeValue lightEventChangeValue) {
        String str = C3;
        try {
            switch (i) {
                case 557857169:
                    int allWeatherLightSwitchStateR = lightEventChangeValue.getAllWeatherLightSwitchStateR();
                    this.h.setAllWeatherLightSwitchStateR(allWeatherLightSwitchStateR);
                    LogUtil.debug(str, "ID_+ALL_WEATHER_LIGHT_SWITCH_STATE_R:" + allWeatherLightSwitchStateR);
                    break;
                case 557857171:
                    int headlightControlModeR = lightEventChangeValue.getHeadlightControlModeR();
                    this.h.setHeadlightControlModeR(headlightControlModeR);
                    LogUtil.debug(str, "ID_+HEADLIGHT_CONTROL_MODE_R:" + headlightControlModeR);
                    break;
                case 557857172:
                    int rearFogLightSwitchStateR = lightEventChangeValue.getRearFogLightSwitchStateR();
                    this.h.setRearFogLightSwitchStateR(rearFogLightSwitchStateR);
                    LogUtil.debug(str, "ID_+REAR_FOG_LIGHT_SWITCH_STATE_R:" + rearFogLightSwitchStateR);
                    break;
                case 557857710:
                    int ialColorStarRingR = lightEventChangeValue.getIalColorStarRingR();
                    this.h.setIalColorStarRingR(ialColorStarRingR);
                    LogUtil.verbose(str, "ID_IAL_COLOR_STAR_RING_R :" + ialColorStarRingR);
                    break;
                case 557857711:
                    int ialColorStarrySkyR = lightEventChangeValue.getIalColorStarrySkyR();
                    this.h.setIalColorStarrySkyR(ialColorStarrySkyR);
                    LogUtil.verbose(str, "ID_IAL_COLOR_STARRY_SKY_R :" + ialColorStarrySkyR);
                    break;
                case 557857712:
                    int ialColorFrontR = lightEventChangeValue.getIalColorFrontR();
                    this.h.setIalColorFrontR(ialColorFrontR);
                    LogUtil.verbose(str, "ID_IAL_COLOR_FRONT_R :" + ialColorFrontR);
                    break;
                case 557857713:
                    int ialColorBackR = lightEventChangeValue.getIalColorBackR();
                    this.h.setIalColorBackR(ialColorBackR);
                    LogUtil.verbose(str, "ID_IAL_COLOR_BACK_R :" + ialColorBackR);
                    break;
                case 557857716:
                    int ialBrightnessStarRingR = lightEventChangeValue.getIalBrightnessStarRingR();
                    this.h.setIalBrightnessStarRingR(ialBrightnessStarRingR);
                    LogUtil.verbose(str, "ID_IAL_BRIGHTNESS_STAR_RING_R :" + ialBrightnessStarRingR);
                    break;
                case 557857717:
                    int ialBrightnessStarrySkyR = lightEventChangeValue.getIalBrightnessStarrySkyR();
                    this.h.setIalBrightnessStarrySkyR(ialBrightnessStarrySkyR);
                    LogUtil.verbose(str, "ID_IAL_BRIGHTNESS_STARRY_SKY_R :" + ialBrightnessStarrySkyR);
                    break;
                case 557857718:
                    int ialBrightnessBackR = lightEventChangeValue.getIalBrightnessBackR();
                    this.h.setIalBrightnessBackR(ialBrightnessBackR);
                    LogUtil.verbose(str, "ID_IAL_BRIGHTNESS_BACK_R :" + ialBrightnessBackR);
                    break;
                case 557857719:
                    int ialBrightnessFrontR = lightEventChangeValue.getIalBrightnessFrontR();
                    this.h.setIalBrightnessFrontR(ialBrightnessFrontR);
                    LogUtil.verbose(str, "ID_IAL_BRIGHTNESS_FRONT_R :" + ialBrightnessFrontR);
                    break;
                case 557857721:
                    int lastSetedArea = lightEventChangeValue.getLastSetedArea();
                    this.r = lastSetedArea;
                    this.h.setmLastSetedArea(lastSetedArea);
                    LogUtil.verbose(str, "ID_LAST_SETED_AREA:" + this.r);
                    break;
                case 557860608:
                    int lightAutoStatusOne = lightEventChangeValue.getLightAutoStatusOne();
                    this.s = lightAutoStatusOne;
                    this.h.setmLightAutoStatusOne(lightAutoStatusOne);
                    LogUtil.verbose(str, "ID_LIGHT_AUTO_STATUS_ONE:" + this.s);
                    break;
                case 557860609:
                    int lightAutoStatusTwo = lightEventChangeValue.getLightAutoStatusTwo();
                    this.t = lightAutoStatusTwo;
                    this.h.setmLightAutoStatusTwo(lightAutoStatusTwo);
                    LogUtil.verbose(str, "ID_LIGHT_AUTO_STATUS_TWO:" + this.t);
                    break;
                case 557860610:
                    int aFSSwitch = lightEventChangeValue.getAFSSwitch();
                    this.l = aFSSwitch;
                    this.h.setAfsSwitch(aFSSwitch);
                    LogUtil.verbose(str, "ID_AFS_SWITCH:" + this.l);
                    break;
                case 557860611:
                    int ambientMusicModeStateR = lightEventChangeValue.getAmbientMusicModeStateR();
                    this.p = ambientMusicModeStateR;
                    this.h.setmAmbientMusicModeStateR(ambientMusicModeStateR);
                    LogUtil.verbose(str, "ID_AMBIENT_MUSIC_MODE_STATE_R:" + this.p);
                    break;
                case 557860613:
                    int ambientMusicModeR = lightEventChangeValue.getAmbientMusicModeR();
                    this.o = ambientMusicModeR;
                    this.h.setmAmbientMusicModeR(ambientMusicModeR);
                    LogUtil.verbose(str, "ID_AMBIENT_MUSIC_MODE_R:" + this.o);
                    break;
                case 557860615:
                    int ambientMulticolorStateR = lightEventChangeValue.getAmbientMulticolorStateR();
                    this.n = ambientMulticolorStateR;
                    this.h.setmAmbientMulticolorStateR(ambientMulticolorStateR);
                    LogUtil.verbose(str, "ID_AMBIENT_MULTI_COLOR_STATE_R:" + this.n);
                    break;
                case 557860617:
                    int ambientMulticolorModeR = lightEventChangeValue.getAmbientMulticolorModeR();
                    this.m = ambientMulticolorModeR;
                    this.h.setmAmbientMulticolorModeR(ambientMulticolorModeR);
                    LogUtil.verbose(str, "ID_AMBIENT_MULTI_COLOR_MODE_R:" + this.m);
                    break;
                case 557860619:
                    int dayTimeLightStateR = lightEventChangeValue.getDayTimeLightStateR();
                    this.q = dayTimeLightStateR;
                    this.h.setmDayTimeLightStateR(dayTimeLightStateR);
                    LogUtil.verbose(str, "ID_DAY_TIME_LIGHT_STATE_R:" + this.q);
                    break;
                case 557860624:
                    int turnLightFlashStateR = lightEventChangeValue.getTurnLightFlashStateR();
                    this.u = turnLightFlashStateR;
                    this.h.setmTurnLightFlashStateR(turnLightFlashStateR);
                    LogUtil.verbose(str, "ID_TURN_LIGHT_FLASH_STATE_R:" + this.u);
                    break;
                case 557860629:
                    int reversingLightState = lightEventChangeValue.getReversingLightState();
                    this.E = reversingLightState;
                    this.h.setReversingLightState(reversingLightState);
                    LogUtil.verbose(str, "ID_REVERSING_LIGHT_STATE:" + this.E);
                    break;
                case 557860630:
                    int turnLightState = lightEventChangeValue.getTurnLightState();
                    this.F = turnLightState;
                    this.h.setTurnLightState(turnLightState);
                    LogUtil.verbose(str, "ID_TURN_LIGHT_STATE:" + this.F);
                    break;
                case 557860631:
                    int ambientFeedBack = lightEventChangeValue.getAmbientFeedBack();
                    this.D = ambientFeedBack;
                    this.h.setAmbientFeedBack(ambientFeedBack);
                    LogUtil.verbose(str, "ID_AMBIENT_FEED_BACK:" + this.D);
                    break;
                case 557860633:
                    int reversingLightStateR = lightEventChangeValue.getReversingLightStateR();
                    this.B = reversingLightStateR;
                    this.h.setReversingLightStateR(reversingLightStateR);
                    LogUtil.verbose(str, "ID_REVERSING_LIGHT_STATER:" + this.B);
                    break;
                case 557860634:
                    int turnLightStateR = lightEventChangeValue.getTurnLightStateR();
                    this.C = turnLightStateR;
                    this.h.setTurnLightState(turnLightStateR);
                    LogUtil.verbose(str, "ID_TURN_LIGHT_STATER:" + this.C);
                    break;
                case 557860635:
                    int ambientFeedBackR = lightEventChangeValue.getAmbientFeedBackR();
                    this.w = ambientFeedBackR;
                    this.h.setAmbientFeedBack(ambientFeedBackR);
                    LogUtil.verbose(str, "ID_AMBIENT_FEED_BACKR:" + this.w);
                    break;
                case 557860637:
                    int lightLowBeamStatusR = lightEventChangeValue.getLightLowBeamStatusR();
                    this.y = lightLowBeamStatusR;
                    this.h.setLightLowBeamStatusR(lightLowBeamStatusR);
                    LogUtil.verbose(str, "ID_LIGHT_LOW_BEAM_STATUSR:" + this.y);
                    break;
                case 557860638:
                    int lightHighBeamR = lightEventChangeValue.getLightHighBeamR();
                    this.x = lightHighBeamR;
                    this.h.setLightHighBeamR(lightHighBeamR);
                    LogUtil.verbose(str, "ID_LIGHT_HIGH_BEAMR:" + this.x);
                    break;
                case 557860642:
                    int lightRearFog = lightEventChangeValue.getLightRearFog();
                    this.z = lightRearFog;
                    this.h.setLightRearFog(lightRearFog);
                    LogUtil.verbose(str, "ID_LIGHT_REAR_FOGR:" + this.z);
                    break;
                case 557860643:
                    int dayTimeRunningLightStateR = lightEventChangeValue.getDayTimeRunningLightStateR();
                    this.H = dayTimeRunningLightStateR;
                    this.h.setDayTimeRunningLightState(dayTimeRunningLightStateR);
                    LogUtil.verbose(str, "ID_DAY_TIME_RUNNING_LIGHT_STATE_R:" + this.H);
                    break;
                case 557860648:
                    int backlightLevelR = lightEventChangeValue.getBacklightLevelR();
                    this.w3 = backlightLevelR;
                    this.h.setBacklightLevelR(backlightLevelR);
                    LogUtil.verbose(str, "ID_BACKLIGHT_LEVEL_R:" + this.w3);
                    break;
                case 557860657:
                    int leftFrontReadingLampStatusR = lightEventChangeValue.getLeftFrontReadingLampStatusR();
                    this.h.setLeftFrontReadingLampStatusR(leftFrontReadingLampStatusR);
                    LogUtil.debug(str, "ID_LEFT_FRONT_READING_LAMP_STATUS_R:" + leftFrontReadingLampStatusR);
                    break;
                case 557860658:
                    int rightFrontReadingLampStatusR = lightEventChangeValue.getRightFrontReadingLampStatusR();
                    this.h.setRightFrontReadingLampStatusR(rightFrontReadingLampStatusR);
                    LogUtil.debug(str, "ID_RIGHT_FRONT_READING_LAMP_STATUS_R:" + rightFrontReadingLampStatusR);
                    break;
                case 557860659:
                    int leftMiddleRowSideRLightSwitchR = lightEventChangeValue.getLeftMiddleRowSideRLightSwitchR();
                    this.h.setLeftMiddleRowSideRLightSwitchR(leftMiddleRowSideRLightSwitchR);
                    LogUtil.debug(str, "ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R:" + leftMiddleRowSideRLightSwitchR);
                    break;
                case 557860660:
                    int rightMiddleRowRLightSwitchR = lightEventChangeValue.getRightMiddleRowRLightSwitchR();
                    this.h.setRightMiddleRowRLightSwitchR(rightMiddleRowRLightSwitchR);
                    LogUtil.debug(str, "ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R:" + rightMiddleRowRLightSwitchR);
                    break;
                case 557860661:
                    int leftMiddleRowRLightRotaryStateR = lightEventChangeValue.getLeftMiddleRowRLightRotaryStateR();
                    this.h.setLeftMiddleRowRLightRotaryStateR(leftMiddleRowRLightRotaryStateR);
                    LogUtil.debug(str, "ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R:" + leftMiddleRowRLightRotaryStateR);
                    break;
                case 557860662:
                    int rightMiddleRowRLightRotaryR = lightEventChangeValue.getRightMiddleRowRLightRotaryR();
                    this.h.setRightMiddleRowRLightRotaryR(rightMiddleRowRLightRotaryR);
                    LogUtil.debug(str, "ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R:" + rightMiddleRowRLightRotaryR);
                    break;
                case 557860663:
                    int leftRearRLightSwitchR = lightEventChangeValue.getLeftRearRLightSwitchR();
                    this.h.setLeftRearRLightSwitchR(leftRearRLightSwitchR);
                    LogUtil.debug(str, "ID_LEFT_REAR_R_LIGHT_SWITCH_R:" + leftRearRLightSwitchR);
                    break;
                case 557860664:
                    int rightRearRLightSwitchR = lightEventChangeValue.getRightRearRLightSwitchR();
                    this.h.setRightRearRLightSwitchR(rightRearRLightSwitchR);
                    LogUtil.debug(str, "ID_RIGHT_REAR_R_LIGHT_SWITCH_R:" + rightRearRLightSwitchR);
                    break;
                case 557860665:
                    int cargoLightSwitchStateR = lightEventChangeValue.getCargoLightSwitchStateR();
                    this.h.setCargoLightSwitchStateR(cargoLightSwitchStateR);
                    LogUtil.debug(str, "ID_CARGO_LIGHT_SWITCH_STATE_R:" + cargoLightSwitchStateR);
                    break;
                case 557860666:
                    int cargoLightSwitchConfigR = lightEventChangeValue.getCargoLightSwitchConfigR();
                    this.h.setCargoLightSwitchConfigR(cargoLightSwitchConfigR);
                    LogUtil.debug(str, "ID_CARGO_LIGHT_SWITCH_CONFIG_R:" + cargoLightSwitchConfigR);
                    break;
                case 557860667:
                    int cargoLightSwitchStateS = lightEventChangeValue.getCargoLightSwitchStateS();
                    this.h.setCargoLightSwitchStateS(cargoLightSwitchStateS);
                    LogUtil.debug(str, "ID_CARGO_LIGHT_SWITCH_STATE_S:" + cargoLightSwitchStateS);
                    break;
                case 557860668:
                    int leftCornerLightStatusR = lightEventChangeValue.getLeftCornerLightStatusR();
                    this.h.setLeftCornerLightStatusR(leftCornerLightStatusR);
                    LogUtil.debug(str, "ID_LEFT_CORNER_LIGHT_STATUS_R:" + leftCornerLightStatusR);
                    break;
                case 557860669:
                    int rightCornerLightStatusR = lightEventChangeValue.getRightCornerLightStatusR();
                    this.h.setRightCornerLightStatusR(rightCornerLightStatusR);
                    LogUtil.debug(str, "ID_RIGHT_CORNER_LIGHT_STATUS_R:" + rightCornerLightStatusR);
                    break;
                case 557860670:
                    int frontFogLampStatusR = lightEventChangeValue.getFrontFogLampStatusR();
                    this.h.setFrontFogLampStatusR(frontFogLampStatusR);
                    LogUtil.debug(str, "ID_FRONT_FOG_LAMP_STATUS_R:" + frontFogLampStatusR);
                    break;
                case 557860671:
                    int frontCenterLightStatusR = lightEventChangeValue.getFrontCenterLightStatusR();
                    this.h.setFrontCenterLightStatusR(frontCenterLightStatusR);
                    LogUtil.debug(str, "ID_FRONT_CENTER_LIGHT_STATUS_R:" + frontCenterLightStatusR);
                    break;
                case 557860672:
                    int positionLightDisplayFeedCallbackR = lightEventChangeValue.getPositionLightDisplayFeedCallbackR();
                    this.h.setPositionLightDisplayFeedCallbackR(positionLightDisplayFeedCallbackR);
                    LogUtil.debug(str, "ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R:" + positionLightDisplayFeedCallbackR);
                    break;
                case 557860674:
                    int rightDaytimeLightStateR = lightEventChangeValue.getRightDaytimeLightStateR();
                    this.h.setRightDaytimeLightStateR(rightDaytimeLightStateR);
                    LogUtil.debug(str, "ID_RIGHT_DAYTIME_LIGHT_STATE_R:" + rightDaytimeLightStateR);
                    break;
                case 557860675:
                    int leftDaytimeLightStateR = lightEventChangeValue.getLeftDaytimeLightStateR();
                    this.h.setLeftDaytimeLightStateR(leftDaytimeLightStateR);
                    LogUtil.debug(str, "ID_LEFT_DAYTIME_LIGHT_STATE_R:" + leftDaytimeLightStateR);
                    break;
                case 557860677:
                    int hightlightPositionR = lightEventChangeValue.getHightlightPositionR();
                    this.h.setHightlightPositionR(hightlightPositionR);
                    LogUtil.debug(str, "ID_HIGHTLIGHT_POSITION_R:" + hightlightPositionR);
                    break;
                case 557860680:
                    int leftFrontPositionSwlStatusR = lightEventChangeValue.getLeftFrontPositionSwlStatusR();
                    this.h.setLeftFrontPositionSwlStatusR(leftFrontPositionSwlStatusR);
                    LogUtil.debug(str, "ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R:" + leftFrontPositionSwlStatusR);
                    break;
                case 557860681:
                    int rightFrontPositionSwlStatusR = lightEventChangeValue.getRightFrontPositionSwlStatusR();
                    this.h.setRightFrontPositionSwlStatusR(rightFrontPositionSwlStatusR);
                    LogUtil.debug(str, "ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R:" + rightFrontPositionSwlStatusR);
                    break;
                case 557860682:
                    int leftDaytimeRunLampStateR = lightEventChangeValue.getLeftDaytimeRunLampStateR();
                    this.h.setLeftDaytimeRunLampStateR(leftDaytimeRunLampStateR);
                    LogUtil.debug(str, "ID_LEFT_DAYTIME_RUN_LAMP_STATE_R:" + leftDaytimeRunLampStateR);
                    break;
                case 557860683:
                    int rightDaytimeRunLampStateR = lightEventChangeValue.getRightDaytimeRunLampStateR();
                    this.h.setRightDaytimeRunLampStateR(rightDaytimeRunLampStateR);
                    LogUtil.debug(str, "ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R:" + rightDaytimeRunLampStateR);
                    break;
                case 557860688:
                    int frontFogLightSwitchStateR = lightEventChangeValue.getFrontFogLightSwitchStateR();
                    this.h.setFrontFogLightSwitchStateR(frontFogLightSwitchStateR);
                    LogUtil.debug(str, "ID_+FRONT_FOG_LIGHT_SWITCH_STATE_R:" + frontFogLightSwitchStateR);
                    break;
                case 557860695:
                    int middleSternLampStateR = lightEventChangeValue.getMiddleSternLampStateR();
                    this.h.setMiddleSternLampStateR(middleSternLampStateR);
                    LogUtil.debug(str, "ID_+MIDDLE_STERN_LAMP_STATE_R:" + middleSternLampStateR);
                    break;
                case 557860696:
                    int ambientStateS = lightEventChangeValue.getAmbientStateS();
                    this.h.setAmbientStateS(ambientStateS);
                    LogUtil.debug(str, "ID_+AMBIENT_STATE_S:" + ambientStateS);
                    break;
                case 557860697:
                    int ambientStateR = lightEventChangeValue.getAmbientStateR();
                    this.h.setAmbientStateR(ambientStateR);
                    LogUtil.debug(str, "ID_+AMBIENT_STATE_R:" + ambientStateR);
                    break;
                case 557860699:
                    int lightRearGlowLogoWorkStateR = lightEventChangeValue.getLightRearGlowLogoWorkStateR();
                    this.h.setLightRearGlowLogoWorkStateR(lightRearGlowLogoWorkStateR);
                    LogUtil.debug(str, "ID_+LIGHT_REAR_GLOW_LOGO_WORK_STATE_R:" + lightRearGlowLogoWorkStateR);
                    break;
                case 557860700:
                    int lightAdasIndicatorStatusR = lightEventChangeValue.getLightAdasIndicatorStatusR();
                    this.h.setLightAdasIndicatorStatusR(lightAdasIndicatorStatusR);
                    LogUtil.debug(str, "ID_+LIGHT_ADAS_INDICATOR_STATUS_R:" + lightAdasIndicatorStatusR);
                    break;
                case 557860701:
                    int lightFrontGlowLogoWorkStateR = lightEventChangeValue.getLightFrontGlowLogoWorkStateR();
                    this.h.setLightFrontGlowLogoWorkStateR(lightFrontGlowLogoWorkStateR);
                    LogUtil.debug(str, "ID_+LIGHT_FRONT_GLOW_LOGO_WORK_STATE_R:" + lightFrontGlowLogoWorkStateR);
                    break;
                case 557860703:
                    int atmospereLightNightWeakenModeConfigR = lightEventChangeValue.getAtmospereLightNightWeakenModeConfigR();
                    this.h.setAtmospereLightNightWeakenModeConfigR(atmospereLightNightWeakenModeConfigR);
                    LogUtil.debug(str, "ID_+ATMOSPERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG_R:" + atmospereLightNightWeakenModeConfigR);
                    break;
                case 557860706:
                    int atmospereLampPopupPromptR = lightEventChangeValue.getAtmospereLampPopupPromptR();
                    this.h.setAtmospereLampPopupPromptR(atmospereLampPopupPromptR);
                    LogUtil.debug(str, "ID_+ATMOSPERE_LAMP_POPUP_PROMPT_R:" + atmospereLampPopupPromptR);
                    break;
                case 557860707:
                    int themeColorSwitchStateR = lightEventChangeValue.getThemeColorSwitchStateR();
                    this.h.setThemeColorSwitchStateR(themeColorSwitchStateR);
                    LogUtil.debug(str, "ID_+THEME_COLOR_SWITCH_STATE_R:" + themeColorSwitchStateR);
                    break;
                case 557860709:
                    int atmospereLampUiDisplayR = lightEventChangeValue.getAtmospereLampUiDisplayR();
                    this.h.setAtmospereLampUiDisplayR(atmospereLampUiDisplayR);
                    LogUtil.debug(str, "ID_+ATMOSPERE_LAMP_UI_DISPLAY_R:" + atmospereLampUiDisplayR);
                    break;
                case 557860710:
                    int themeColorFeedBackR = lightEventChangeValue.getThemeColorFeedBackR();
                    this.h.setThemeColorFeedBackR(themeColorFeedBackR);
                    LogUtil.debug(str, "ID_+THEME_COLOR_FEED_BACK_R:" + themeColorFeedBackR);
                    break;
                case 557870867:
                    int stopLightStateR = lightEventChangeValue.getStopLightStateR();
                    this.v = stopLightStateR;
                    this.h.setStopLightStateR(stopLightStateR);
                    LogUtil.verbose(str, "ID_STOP_LIGHT_STATE:" + this.v);
                    break;
                case 557879809:
                    int lightSideStatusR = lightEventChangeValue.getLightSideStatusR();
                    this.A = lightSideStatusR;
                    this.h.setLightSideStatusR(lightSideStatusR);
                    LogUtil.verbose(str, "ID_LIGHT_SIDE_STATUSR:" + this.A);
                    break;
            }
            this.g.m(i, i2, this.h);
            LogUtil.verbose(str, "notifyLightEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void F(CarVendorChargingManager carVendorChargingManager, CarVendorCarbodyManager carVendorCarbodyManager, CarVendorEnergyManager carVendorEnergyManager, CarVendorEngineManager carVendorEngineManager, CarVendorTyreManager carVendorTyreManager, CarVendorLightManager carVendorLightManager) {
        this.c = carVendorEnergyManager;
        this.f65a = carVendorChargingManager;
        this.f66b = carVendorCarbodyManager;
        this.d = carVendorEngineManager;
        this.f = carVendorTyreManager;
        this.e = carVendorLightManager;
    }

    @Override // com.ts.appservice.caradapter.d.InterfaceC0009a
    public void a(CarbodyEventChangeValue carbodyEventChangeValue) {
        String str = C3;
        if (carbodyEventChangeValue == null) {
            LogUtil.error(str, "onCarBodyChangeEvent:value=null");
            return;
        }
        int propertyId = carbodyEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onCarBodyChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 12;
        messageObtain.arg1 = propertyId;
        messageObtain.obj = carbodyEventChangeValue;
        if (propertyId != 557862927 || 2 != carbodyEventChangeValue.getPowerLevel()) {
            this.j.sendMessage(messageObtain);
        } else if (Settings.System.getInt(this.k.getContentResolver(), "car.bodywork.acc.on", 0) != 0) {
            this.j.sendMessageDelayed(messageObtain, 5000L);
        } else {
            this.j.sendMessage(messageObtain);
            Settings.System.putInt(this.k.getContentResolver(), "car.bodywork.acc.on", 1);
        }
    }

    @Override // com.ts.appservice.caradapter.d.z
    public void b(TyreEventChangeValue tyreEventChangeValue) {
        String str = C3;
        if (tyreEventChangeValue == null) {
            LogUtil.error(str, "onTyreChangeEvent:value=null");
            return;
        }
        int propertyId = tyreEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onTyreChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 10;
        messageObtain.arg1 = propertyId;
        messageObtain.obj = tyreEventChangeValue;
        this.j.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.i
    public void d(EngineEventChangeValue engineEventChangeValue) {
        String str = C3;
        if (engineEventChangeValue == null) {
            LogUtil.error(str, "onEngineChangeEvent:value=null");
            return;
        }
        int propertyId = engineEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onEngineChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 9;
        messageObtain.arg1 = propertyId;
        messageObtain.obj = engineEventChangeValue;
        this.j.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.h
    public void f(EnergyEventChangeValue energyEventChangeValue) {
        String str = C3;
        if (energyEventChangeValue == null) {
            LogUtil.error(str, "onEnergyChangeEvent:value=null");
            return;
        }
        int propertyId = energyEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onEnergyChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 7;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = energyEventChangeValue.getAreaId();
        messageObtain.obj = energyEventChangeValue;
        this.j.sendMessage(messageObtain);
    }

    public int getAFSSwitch() {
        String str = C3;
        LogUtil.verbose(str, "getAFSSwitch");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                int aFSSwitch = carVendorLightManager.getAFSSwitch();
                LogUtil.verbose(str, "getAFSSwitch=" + aFSSwitch);
                return aFSSwitch;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.l;
    }

    public int getAcChargingLimit() {
        int acChargingLimitR;
        String str = C3;
        LogUtil.verbose(str, " getAcChargingLimit.in");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                acChargingLimitR = carVendorChargingManager.getAcChargingLimitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acChargingLimitR = -1;
            }
            LogUtil.verbose(str, " getAcChargingLimit.out=" + acChargingLimitR);
            return acChargingLimitR;
        }
        LogUtil.verbose(str, " getAcChargingLimit.null == mCarVendorChargingManager");
        acChargingLimitR = -1;
        LogUtil.verbose(str, " getAcChargingLimit.out=" + acChargingLimitR);
        return acChargingLimitR;
    }

    public int getAlarmState() {
        return 0;
    }

    public int getAllWeatherLightSwitchState() {
        int allWeatherLightSwitchStateR;
        String str = C3;
        LogUtil.verbose(str, " getAllWeatherLightSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                allWeatherLightSwitchStateR = carVendorLightManager.getAllWeatherLightSwitchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                allWeatherLightSwitchStateR = -1;
            }
            LogUtil.verbose(str, " getAllWeatherLightSwitchState.out service =" + allWeatherLightSwitchStateR);
            return allWeatherLightSwitchStateR;
        }
        LogUtil.verbose(str, " getAllWeatherLightSwitchState.null");
        allWeatherLightSwitchStateR = -1;
        LogUtil.verbose(str, " getAllWeatherLightSwitchState.out service =" + allWeatherLightSwitchStateR);
        return allWeatherLightSwitchStateR;
    }

    public int[] getAmbientColors(int i) {
        return null;
    }

    public int getAmbientColorsSupport() {
        String str = C3;
        LogUtil.verbose(str, "getAmbientColorsSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856188, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAmbientColorsSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAmbientFeedBack() {
        String str = C3;
        LogUtil.verbose(str, "Service getAmbientFeedBack");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ambientFeedBack = carVendorLightManager.getAmbientFeedBack();
            LogUtil.verbose(str, "getAmbientFeedBack=" + ambientFeedBack);
            return ambientFeedBack;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientMulticolorMode(int i) {
        String str = C3;
        LogUtil.verbose(str, "getAmbientMulticolorMode");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                int ambientMulticolorModeR = carVendorLightManager.getAmbientMulticolorModeR();
                LogUtil.verbose(str, "getAmbientMulticolorModeR=" + ambientMulticolorModeR);
                return ambientMulticolorModeR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.m;
    }

    public int getAmbientMulticolorState(int i) {
        String str = C3;
        LogUtil.verbose(str, "getAmbientMulticolorState");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                int ambientMulticolorStateR = carVendorLightManager.getAmbientMulticolorStateR();
                LogUtil.verbose(str, "getAmbientMulticolorStateR=" + ambientMulticolorStateR);
                return ambientMulticolorStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.n;
    }

    public int getAmbientMusicMode(int i) {
        String str = C3;
        LogUtil.verbose(str, "getAmbientMusicMode light");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                int ambientMusicModeR = carVendorLightManager.getAmbientMusicModeR();
                LogUtil.verbose(str, "light getAmbientMusicModeR=" + ambientMusicModeR);
                return ambientMusicModeR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.o;
    }

    public int getAmbientMusicModeState(int i) {
        String str = C3;
        LogUtil.verbose(str, "getAmbientMusicModeState");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                int ambientMusicModeStateR = carVendorLightManager.getAmbientMusicModeStateR();
                LogUtil.verbose(str, "getAmbientMusicModeStateR=" + ambientMusicModeStateR);
                return ambientMusicModeStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.p;
    }

    public int getAmbientMusicRhym(int i) {
        return 0;
    }

    public int getAmbientState() {
        int ambientStateR;
        String str = C3;
        LogUtil.verbose(str, " getAmbientState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                ambientStateR = carVendorLightManager.getAmbientStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ambientStateR = -1;
            }
            LogUtil.verbose(str, " getAmbientState.out service =" + ambientStateR);
            return ambientStateR;
        }
        LogUtil.verbose(str, " getAmbientState.null");
        ambientStateR = -1;
        LogUtil.verbose(str, " getAmbientState.out service =" + ambientStateR);
        return ambientStateR;
    }

    public int getAmbientSwitchConfig() {
        int ambientSwitchConfigR;
        String str = C3;
        LogUtil.verbose(str, " getAmbientSwitchConfig.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                ambientSwitchConfigR = carVendorLightManager.getAmbientSwitchConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ambientSwitchConfigR = -1;
            }
            LogUtil.verbose(str, " getAmbientSwitchConfig.out service =" + ambientSwitchConfigR);
            return ambientSwitchConfigR;
        }
        LogUtil.verbose(str, " getAmbientSwitchConfig.null");
        ambientSwitchConfigR = -1;
        LogUtil.verbose(str, " getAmbientSwitchConfig.out service =" + ambientSwitchConfigR);
        return ambientSwitchConfigR;
    }

    public int getAntiTheftStatus() {
        String str = C3;
        LogUtil.verbose(str, "getAntiTheftStatus");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int antiTheftStatusR = carVendorCarbodyManager.getAntiTheftStatusR();
            LogUtil.verbose(str, "getAntiTheftStatusR=" + antiTheftStatusR);
            return antiTheftStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAtmospereLampPopupPrompt() {
        int atmospereLampPopupPromptR;
        String str = C3;
        LogUtil.verbose(str, " getAtmospereLampPopupPrompt.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                atmospereLampPopupPromptR = carVendorLightManager.getAtmospereLampPopupPromptR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                atmospereLampPopupPromptR = -1;
            }
            LogUtil.verbose(str, " getAtmospereLampPopupPrompt.out service =" + atmospereLampPopupPromptR);
            return atmospereLampPopupPromptR;
        }
        LogUtil.verbose(str, " getAtmospereLampPopupPrompt.null");
        atmospereLampPopupPromptR = -1;
        LogUtil.verbose(str, " getAtmospereLampPopupPrompt.out service =" + atmospereLampPopupPromptR);
        return atmospereLampPopupPromptR;
    }

    public int getAtmospereLampUiDisplay() {
        int atmospereLampUiDisplayR;
        String str = C3;
        LogUtil.verbose(str, " getAtmospereLampUiDisplay.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                atmospereLampUiDisplayR = carVendorLightManager.getAtmospereLampUiDisplayR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                atmospereLampUiDisplayR = -1;
            }
            LogUtil.verbose(str, " getAtmospereLampUiDisplay.out service =" + atmospereLampUiDisplayR);
            return atmospereLampUiDisplayR;
        }
        LogUtil.verbose(str, " getAtmospereLampUiDisplay.null");
        atmospereLampUiDisplayR = -1;
        LogUtil.verbose(str, " getAtmospereLampUiDisplay.out service =" + atmospereLampUiDisplayR);
        return atmospereLampUiDisplayR;
    }

    public int getAtmospereLightNightWeakenMode() {
        int atmospereLightNightWeakenModeR;
        String str = C3;
        LogUtil.verbose(str, " getAtmospereLightNightWeakenMode.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                atmospereLightNightWeakenModeR = carVendorLightManager.getAtmospereLightNightWeakenModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                atmospereLightNightWeakenModeR = -1;
            }
            LogUtil.verbose(str, " getAtmospereLightNightWeakenMode.out service =" + atmospereLightNightWeakenModeR);
            return atmospereLightNightWeakenModeR;
        }
        LogUtil.verbose(str, " getAtmospereLightNightWeakenMode.null");
        atmospereLightNightWeakenModeR = -1;
        LogUtil.verbose(str, " getAtmospereLightNightWeakenMode.out service =" + atmospereLightNightWeakenModeR);
        return atmospereLightNightWeakenModeR;
    }

    public int getAtmospereLightNightWeakenModeConfig() {
        int atmospereLightNightWeakenModeConfigR;
        String str = C3;
        LogUtil.verbose(str, " getAtmospereLightNightWeakenModeConfig.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                atmospereLightNightWeakenModeConfigR = carVendorLightManager.getAtmospereLightNightWeakenModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                atmospereLightNightWeakenModeConfigR = -1;
            }
            LogUtil.verbose(str, " getAtmospereLightNightWeakenModeConfig.out service =" + atmospereLightNightWeakenModeConfigR);
            return atmospereLightNightWeakenModeConfigR;
        }
        LogUtil.verbose(str, " getAtmospereLightNightWeakenModeConfig.null");
        atmospereLightNightWeakenModeConfigR = -1;
        LogUtil.verbose(str, " getAtmospereLightNightWeakenModeConfig.out service =" + atmospereLightNightWeakenModeConfigR);
        return atmospereLightNightWeakenModeConfigR;
    }

    public int getAtmosphereLampBodyDisplay() {
        int atmosphereLampBodyDisplayR;
        String str = C3;
        LogUtil.verbose(str, " getAtmosphereLampBodyDisplay.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                atmosphereLampBodyDisplayR = carVendorLightManager.getAtmosphereLampBodyDisplayR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                atmosphereLampBodyDisplayR = -1;
            }
            LogUtil.verbose(str, " getAtmosphereLampBodyDisplay.out service =" + atmosphereLampBodyDisplayR);
            return atmosphereLampBodyDisplayR;
        }
        LogUtil.verbose(str, " getAtmosphereLampBodyDisplay.null");
        atmosphereLampBodyDisplayR = -1;
        LogUtil.verbose(str, " getAtmosphereLampBodyDisplay.out service =" + atmosphereLampBodyDisplayR);
        return atmosphereLampBodyDisplayR;
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        String str = C3;
        LogUtil.verbose(str, "Service getAutoExternalRearMirrorFoldActionState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int autoExternalRearMirrorFoldActionStateR = carVendorCarbodyManager.getAutoExternalRearMirrorFoldActionStateR();
            LogUtil.verbose(str, "getAutoExternalRearMirrorFoldActionStateR=" + autoExternalRearMirrorFoldActionStateR);
            return autoExternalRearMirrorFoldActionStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoExternalRearMirrorFoldedState() {
        String str = C3;
        LogUtil.verbose(str, "Service getAutoExternalRearMirrorFoldedState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858049, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAutoExternalRearMirrorFoldedState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAutoModelName() {
        return 0;
    }

    public int getAutoSystemState() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int autoSystemState = carVendorCarbodyManager.getAutoSystemState();
                LogUtil.verbose(C3, "getAutoSystemState=" + autoSystemState);
                return autoSystemState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.k2;
    }

    public synchronized int getAutoType() {
        String str = C3;
        synchronized (this) {
            LogUtil.warning(str, "getAutoType");
            CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862930, 0);
            if (carPropertyValueA == null) {
                LogUtil.warning(str, "getAutoType prop value is null");
                return 0;
            }
            int iA = com.ts.appservice.caradapter.e.e.a(carPropertyValueA.getValue());
            if (iA != 0) {
                G("40d", iA);
            }
            LogUtil.warning(str, "getAutoType prop value " + iA + ",getCachedValue:" + v());
            return v();
        }
    }

    public synchronized int getAutoTypeTwo() {
        String str = C3;
        synchronized (this) {
            LogUtil.warning(str, "getAutoTypeTwo");
            CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557863031, 0);
            if (carPropertyValueA == null) {
                LogUtil.warning(str, "getAutoTypeTwo prop value is null");
                return 0;
            }
            int iA = com.ts.appservice.caradapter.e.e.a(carPropertyValueA.getValue());
            if (iA != 0) {
                G("38f", iA);
            }
            LogUtil.warning(str, "getAutoTypeTwo prop value " + iA);
            return iA;
        }
    }

    public String getAutoVIN() {
        String str = SystemProperties.get("persist.sys.cloud_virtual_vin", "");
        LogUtil.error(C3, "getAutoVIN: " + str);
        return str;
    }

    public int getAwningConfigutation() {
        int awningConfigutationR;
        String str = C3;
        LogUtil.verbose(str, " getAwningConfigutation.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                awningConfigutationR = carVendorCarbodyManager.getAwningConfigutationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                awningConfigutationR = -1;
            }
            LogUtil.verbose(str, " getAwningConfigutation.out=" + awningConfigutationR);
            return awningConfigutationR;
        }
        LogUtil.verbose(str, " getAwningConfigutation.null == mCarVendorCarbodyManager");
        awningConfigutationR = -1;
        LogUtil.verbose(str, " getAwningConfigutation.out=" + awningConfigutationR);
        return awningConfigutationR;
    }

    public int getBackDoorAntiPlayMode() {
        String str = C3;
        LogUtil.verbose(str, "Service getBackDoorAntiPlayMode");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int backDoorAntiPlayModeR = carVendorCarbodyManager.getBackDoorAntiPlayModeR();
            LogUtil.verbose(str, "getBackDoorAntiPlayModeR=" + backDoorAntiPlayModeR);
            return backDoorAntiPlayModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBackDoorCurState() {
        String str = C3;
        LogUtil.verbose(str, "Service getBackDoorCurState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int backDoorCurStateR = carVendorCarbodyManager.getBackDoorCurStateR();
            LogUtil.verbose(str, "getBackDoorCurStateR=" + backDoorCurStateR);
            return backDoorCurStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBackDoorInitState() {
        String str = C3;
        LogUtil.verbose(str, "Service getBackDoorInitState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int backDoorInitStateR = carVendorCarbodyManager.getBackDoorInitStateR();
            LogUtil.verbose(str, "getBackDoorInitStateR=" + backDoorInitStateR);
            return backDoorInitStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBackDoorPosition() {
        String str = C3;
        LogUtil.verbose(str, "Service getBackDoorPosition");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int backDoorPositionR = carVendorCarbodyManager.getBackDoorPositionR();
                LogUtil.verbose(str, "getBackDoorPositionR=" + backDoorPositionR);
                return backDoorPositionR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.N2;
    }

    public int getBacklightLevel() {
        String str = C3;
        LogUtil.verbose(str, " getBacklightLevel.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860648, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBacklightLevels prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public long getBatteryCapcity() {
        return 0L;
    }

    public long getBatteryLevelNum() {
        return 0L;
    }

    public int getBatteryManagementDeviceState() {
        String str = C3;
        LogUtil.verbose(str, "getBatteryManagementDeviceState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int batteryManagementDeviceStateR = carVendorChargingManager.getBatteryManagementDeviceStateR();
                LogUtil.verbose(str, "getBatteryManagementDeviceStateR=" + batteryManagementDeviceStateR);
                return batteryManagementDeviceStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.J;
    }

    public int getBatteryStateSupportMoveSatetion() {
        return 0;
    }

    public int getBatteryType() {
        String str = C3;
        LogUtil.verbose(str, "Service getBatteryType");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int batteryTypeR = carVendorChargingManager.getBatteryTypeR();
            LogUtil.verbose(str, "getBatteryTypeR=" + batteryTypeR);
            return batteryTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBatteryVoltageLevel() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int batteryVoltageLevel = carVendorCarbodyManager.getBatteryVoltageLevel();
                LogUtil.verbose(C3, "getBatteryVoltageLevel=" + batteryVoltageLevel);
                return batteryVoltageLevel;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.m2;
    }

    public int getBcmVechileStatus() {
        int bcmVechileStatus;
        String str = C3;
        LogUtil.verbose(str, " getBcmVechileStatus.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                bcmVechileStatus = carVendorCarbodyManager.getBcmVechileStatus();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bcmVechileStatus = -1;
            }
        } else {
            bcmVechileStatus = -1;
        }
        LogUtil.verbose(str, " getBcmVechileStatus.out = " + bcmVechileStatus);
        return bcmVechileStatus;
    }

    public byte[] getBmsWakeNoSenseCharge() {
        LogUtil.verbose(C3, "getBMSWakeNoSenseCharge is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                return new byte[]{(byte) carVendorChargingManager.getBmsWakeNoSenseCharge1R(), (byte) this.f65a.getBmsWakeNoSenseCharge2R(), (byte) this.f65a.getBmsWakeNoSenseCharge3R(), (byte) this.f65a.getBmsWakeNoSenseCharge4R(), (byte) this.f65a.getBmsWakeNoSenseCharge5R(), (byte) this.f65a.getBmsWakeNoSenseCharge6R(), (byte) this.f65a.getBmsWakeNoSenseCharge7R(), (byte) this.f65a.getBmsWakeNoSenseCharge8R(), (byte) this.f65a.getBmsWakeNoSenseCharge9R(), (byte) this.f65a.getBmsWakeNoSenseCharge10R(), (byte) this.f65a.getBmsWakeNoSenseCharge11R(), (byte) this.f65a.getBmsWakeNoSenseCharge12R(), (byte) this.f65a.getBmsWakeNoSenseCharge13R(), (byte) this.f65a.getBmsWakeNoSenseCharge14R(), (byte) this.f65a.getBmsWakeNoSenseCharge15R(), (byte) this.f65a.getBmsWakeNoSenseCharge16R(), (byte) this.f65a.getBmsWakeNoSenseCharge17R(), (byte) this.f65a.getBmsWakeNoSenseCharge18R(), (byte) this.f65a.getBmsWakeNoSenseCharge19R(), (byte) this.f65a.getBmsWakeNoSenseCharge20R(), (byte) this.f65a.getBmsWakeNoSenseCharge21R(), (byte) this.f65a.getBmsWakeNoSenseCharge22R(), (byte) this.f65a.getBmsWakeNoSenseCharge23R(), (byte) this.f65a.getBmsWakeNoSenseCharge24R(), (byte) this.f65a.getBmsWakeNoSenseCharge25R(), (byte) this.f65a.getBmsWakeNoSenseCharge26R(), (byte) this.f65a.getBmsWakeNoSenseCharge27R(), (byte) this.f65a.getBmsWakeNoSenseCharge28R(), (byte) this.f65a.getBmsWakeNoSenseCharge29R(), (byte) this.f65a.getBmsWakeNoSenseCharge30R(), (byte) this.f65a.getBmsWakeNoSenseCharge31R(), (byte) this.f65a.getBmsWakeNoSenseCharge32R(), (byte) this.f65a.getBmsWakeNoSenseCharge33R(), (byte) this.f65a.getBmsWakeNoSenseCharge34R(), (byte) this.f65a.getBmsWakeNoSenseCharge35R()};
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return new byte[35];
    }

    public int getCarPlanAccOnState() {
        String str = C3;
        LogUtil.verbose(str, "getCarPlanAccOnState is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int carPlanAccOnStateR = carVendorChargingManager.getCarPlanAccOnStateR();
            LogUtil.verbose(str, "getCarPlanAccOnStateR=" + carPlanAccOnStateR);
            return carPlanAccOnStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCarPlanBatteryHeatState() {
        String str = C3;
        LogUtil.verbose(str, "getCarPlanBatteryHeatState is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int carPlanBatteryHeatStateR = carVendorChargingManager.getCarPlanBatteryHeatStateR();
            LogUtil.verbose(str, "getCarPlanBatteryHeatStateR=" + carPlanBatteryHeatStateR);
            return carPlanBatteryHeatStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCarPlanState() {
        String str = C3;
        LogUtil.verbose(str, "getCarPlanState is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int carPlanStateR = carVendorChargingManager.getCarPlanStateR();
            LogUtil.verbose(str, "getCarPlanStateR=" + carPlanStateR);
            return carPlanStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCarType() {
        return 0;
    }

    public int getCargoLightSwitchConfig() {
        String str = C3;
        LogUtil.verbose(str, " getCargoLightSwitchConfig.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860666, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCargoLightSwitchConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCargoLightSwitchState() {
        String str = C3;
        LogUtil.verbose(str, " getCargoLightSwitchState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860665, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCargoLightSwitchState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getChangePHint() {
        int changePHintR;
        String str = C3;
        LogUtil.verbose(str, " getChangePHint.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                changePHintR = carVendorEnergyManager.getChangePHintR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                changePHintR = -1;
            }
            LogUtil.verbose(str, " getChangePHint.out=" + changePHintR);
            return changePHintR;
        }
        LogUtil.verbose(str, " getChangePHint.null == mCarVendorEnergyManager");
        changePHintR = -1;
        LogUtil.verbose(str, " getChangePHint.out=" + changePHintR);
        return changePHintR;
    }

    public int getChargeBatteryLevelBegin() {
        String str = C3;
        LogUtil.verbose(str, "Service getChargeBatteryLevelBegin");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeBatteryLevelBeginR = carVendorChargingManager.getChargeBatteryLevelBeginR();
            LogUtil.verbose(str, "getChargeBatteryLevelBeginR=" + chargeBatteryLevelBeginR);
            return chargeBatteryLevelBeginR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeBatteryLevelNum() {
        return 0;
    }

    public int getChargeBatteryLevelNumLimit() {
        return 0;
    }

    public int getChargeBatteryLimitTemp() {
        String str = C3;
        LogUtil.verbose(str, "Service getChargeBatteryLimitTemp");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeBatteryLimitTempR = carVendorChargingManager.getChargeBatteryLimitTempR();
            LogUtil.verbose(str, "getChargeBatteryLimitTempR=" + chargeBatteryLimitTempR);
            return chargeBatteryLimitTempR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeBatteryTemp() {
        String str = C3;
        LogUtil.verbose(str, "Service getChargeBatteryTemp");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeBatteryTempR = carVendorChargingManager.getChargeBatteryTempR();
            LogUtil.verbose(str, "getChargeBatteryTempR=" + chargeBatteryTempR);
            return chargeBatteryTempR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeBatteryType() {
        return 0;
    }

    public float getChargeElectric() {
        String str = C3;
        LogUtil.verbose(str, "Service getChargeElectric");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0.0f;
        }
        try {
            float chargeElectricR = carVendorChargingManager.getChargeElectricR();
            LogUtil.verbose(str, "getChargeElectricR=" + chargeElectricR);
            return chargeElectricR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getChargeLimit() {
        return 0.0f;
    }

    public int getChargePortCoverTip() {
        int chargePortCoverTipR;
        String str = C3;
        LogUtil.verbose(str, " getChargePortCoverTip.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                chargePortCoverTipR = carVendorEnergyManager.getChargePortCoverTipR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                chargePortCoverTipR = -1;
            }
            LogUtil.verbose(str, " getChargePortCoverTip.out=" + chargePortCoverTipR);
            return chargePortCoverTipR;
        }
        LogUtil.verbose(str, " getChargePortCoverTip.null == mCarVendorEnergyManager");
        chargePortCoverTipR = -1;
        LogUtil.verbose(str, " getChargePortCoverTip.out=" + chargePortCoverTipR);
        return chargePortCoverTipR;
    }

    public int getChargePreheatAllow() {
        String str = C3;
        LogUtil.verbose(str, "getChargePreheatAllow");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargePreheatAllowR = carVendorChargingManager.getChargePreheatAllowR();
                LogUtil.verbose(str, "getChargePreheatAllowR=" + chargePreheatAllowR);
                return chargePreheatAllowR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.S1;
    }

    public int getChargePreheatState() {
        String str = C3;
        LogUtil.verbose(str, "getChargePreheatState is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargePreheatStateR = carVendorChargingManager.getChargePreheatStateR();
            LogUtil.verbose(str, "getChargePreheatStateR=" + chargePreheatStateR);
            return chargePreheatStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeStopCapacityState() {
        String str = C3;
        LogUtil.verbose(str, "getChargeStopCapacityState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeStopCapacityStateR = carVendorChargingManager.getChargeStopCapacityStateR();
            LogUtil.verbose(str, "getChargeStopCapacityStateR=" + chargeStopCapacityStateR);
            return chargeStopCapacityStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeStopCapacityStateNotLi() {
        int chargeStopCapacityStateNotLiR;
        String str = C3;
        LogUtil.verbose(str, " getChargeStopCapacityStateNotLi.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                chargeStopCapacityStateNotLiR = carVendorChargingManager.getChargeStopCapacityStateNotLiR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                chargeStopCapacityStateNotLiR = -1;
            }
            LogUtil.verbose(str, " getChargeStopCapacityStateNotLi.out service =" + chargeStopCapacityStateNotLiR);
            return chargeStopCapacityStateNotLiR;
        }
        LogUtil.verbose(str, " getChargeStopCapacityStateNotLi.null");
        chargeStopCapacityStateNotLiR = -1;
        LogUtil.verbose(str, " getChargeStopCapacityStateNotLi.out service =" + chargeStopCapacityStateNotLiR);
        return chargeStopCapacityStateNotLiR;
    }

    public int getChargeTempCtlState() {
        String str = C3;
        LogUtil.verbose(str, "getChargeTempCtlState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeTempCtlStateR = carVendorChargingManager.getChargeTempCtlStateR();
            LogUtil.verbose(str, "getChargeTempCtlStateR=" + chargeTempCtlStateR);
            return chargeTempCtlStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeToggleGuard() {
        String str = C3;
        LogUtil.verbose(str, "getChargeToggleGuard");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargeToggleGuardR = carVendorChargingManager.getChargeToggleGuardR();
                LogUtil.verbose(str, "getChargeToggleGuardR=" + chargeToggleGuardR);
                return chargeToggleGuardR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.N;
    }

    public int getChargeToggleGuardMix() {
        String str = C3;
        LogUtil.verbose(str, "getChargeToggleGuardMix");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeToaggleGuardMixR = carVendorChargingManager.getChargeToaggleGuardMixR();
            LogUtil.verbose(str, "getChargeToaggleGuardMixR=" + chargeToaggleGuardMixR);
            return chargeToaggleGuardMixR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeVoltage() {
        String str = C3;
        LogUtil.verbose(str, "Service getChargeVoltage");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargeVoltageR = carVendorChargingManager.getChargeVoltageR();
            LogUtil.verbose(str, "getChargeVoltageR=" + chargeVoltageR);
            return chargeVoltageR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getChargeVoltageMax() {
        String str = C3;
        LogUtil.verbose(str, "Service getChargeVoltageMax");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0.0f;
        }
        try {
            float chargeVoltageMaxR = carVendorChargingManager.getChargeVoltageMaxR();
            LogUtil.verbose(str, "getChargeVoltageMaxR=" + chargeVoltageMaxR);
            return chargeVoltageMaxR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getChargerFaultState() {
        String str = C3;
        LogUtil.verbose(str, "getChargerFaultState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargerFaultStateR = carVendorChargingManager.getChargerFaultStateR();
                LogUtil.verbose(str, "getChargerFaultStateR=" + chargerFaultStateR);
                return chargerFaultStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.K;
    }

    public int getChargerState() {
        String str = C3;
        LogUtil.verbose(str, "getChargerState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargerStateR = carVendorChargingManager.getChargerStateR();
                LogUtil.verbose(str, "getChargerStateR=" + chargerStateR);
                return chargerStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.L;
    }

    public int getChargerWorkState() {
        String str = C3;
        LogUtil.verbose(str, "getChargerWorkState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargerWorkStateFixR = carVendorChargingManager.getChargerWorkStateFixR();
                LogUtil.verbose(str, "getChargerWorkStateFixR=" + chargerWorkStateFixR);
                return chargerWorkStateFixR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.m0;
    }

    public int getChargingCapState(int i) {
        return 0;
    }

    public int getChargingCapStateLeft() {
        String str = C3;
        LogUtil.verbose(str, "getChargingCapStateLeft");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargingCapStateLeftR = carVendorChargingManager.getChargingCapStateLeftR();
            LogUtil.verbose(str, "getChargingCapStateLeftR=" + chargingCapStateLeftR);
            return chargingCapStateLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargingCapStateRight() {
        String str = C3;
        LogUtil.verbose(str, "getChargingCapStateRight");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int chargingCapStateRightR = carVendorChargingManager.getChargingCapStateRightR();
            LogUtil.verbose(str, "getChargingCapStateRightR=" + chargingCapStateRightR);
            return chargingCapStateRightR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getChargingCapacity() {
        return 0.0f;
    }

    public int getChargingGunNotInsertedState() {
        return 0;
    }

    public int getChargingGunState() {
        String str = C3;
        LogUtil.verbose(str, "getChargingGunState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargingGunStateR = carVendorChargingManager.getChargingGunStateR();
                LogUtil.verbose(str, "getChargingGunStateR=" + chargingGunStateR);
                return chargingGunStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.O;
    }

    public int[] getChargingMySchedulePeriod(int i) {
        return null;
    }

    public int getChargingNow() {
        return 0;
    }

    public int getChargingPortLockRebackState() {
        String str = C3;
        LogUtil.verbose(str, "getChargingPortLockRebackState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int charingPortLockRebackStateR = carVendorChargingManager.getCharingPortLockRebackStateR();
                LogUtil.verbose(str, "getCharingPortLockRebackStateR=" + charingPortLockRebackStateR);
                return charingPortLockRebackStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.Z;
    }

    public float getChargingPower() {
        String str = C3;
        LogUtil.verbose(str, "getChargingPower");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return -1.0f;
        }
        try {
            float chargingPowerR = carVendorChargingManager.getChargingPowerR();
            LogUtil.verbose(str, "getChargingPowerR=" + chargingPowerR);
            return chargingPowerR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public int[] getChargingRestTime() {
        String str = C3;
        LogUtil.verbose(str, "getChargingRestTime");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                this.z3[0] = carVendorChargingManager.getChargingRestTimeHourR();
                this.z3[1] = this.f65a.getChargingRestTimeMinuteR();
                LogUtil.verbose(str, "getChargingRestTime=" + Arrays.toString(this.z3));
                return this.z3;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        int[] iArr = this.z3;
        iArr[0] = this.Q;
        iArr[1] = this.R;
        return iArr;
    }

    public int getChargingScheduleEnableState() {
        String str = C3;
        LogUtil.verbose(str, "getChargingScheduleEnableState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargingScheduleEnableStateR = carVendorChargingManager.getChargingScheduleEnableStateR();
                LogUtil.verbose(str, "getChargingScheduleEnableStateR=" + chargingScheduleEnableStateR);
                return chargingScheduleEnableStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.S;
    }

    public int getChargingSchedulePeriod() {
        return 0;
    }

    public int getChargingScheduleState() {
        String str = C3;
        LogUtil.verbose(str, "getChargingScheduleState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int chargingScheduleStateR = carVendorChargingManager.getChargingScheduleStateR();
                LogUtil.verbose(str, "getChargingScheduleStateR=" + chargingScheduleStateR);
                return chargingScheduleStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.T;
    }

    public int[] getChargingScheduleTime() {
        String str = C3;
        LogUtil.verbose(str, "getChargingScheduleState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                this.B3[0] = carVendorChargingManager.getChargingScheduleTimeYearR();
                this.B3[1] = this.f65a.getChargingScheduleTimeMonthR();
                this.B3[2] = this.f65a.getChargingScheduleTimeDayR();
                this.B3[3] = this.f65a.getChargingScheduleTimeHourR();
                this.B3[4] = this.f65a.getChargingScheduleTimeMinuteR();
                LogUtil.verbose(str, "getChargingScheduleTime=" + Arrays.toString(this.B3));
                return this.B3;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        int[] iArr = this.B3;
        iArr[0] = this.Y;
        iArr[1] = this.X;
        iArr[2] = this.U;
        iArr[3] = this.V;
        iArr[4] = this.W;
        return iArr;
    }

    public long[] getChargingStartAndEndTime() {
        return null;
    }

    public int getChargingType() {
        return 0;
    }

    public int getDayLightSupport() {
        String str = C3;
        LogUtil.verbose(str, "getDayLightSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856041, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDayLightSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDayTimeLightState() {
        String str = C3;
        LogUtil.verbose(str, "getDayTimeLightState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860619, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDayTimeLightState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDayTimeRunningLightState() {
        String str = C3;
        LogUtil.verbose(str, " getDayTimeRunningLightState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860643, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDayTimeRunningLightState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDelayLightSupport(int i, int i2) {
        return 0;
    }

    public int getDischargeBatteryLevelNum() {
        return 0;
    }

    public int getDischargeCapacity() {
        return 0;
    }

    public float getDischargeElectric() {
        String str = C3;
        LogUtil.verbose(str, "Service getDischargeElectric");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0.0f;
        }
        try {
            float dischargeElectricR = carVendorChargingManager.getDischargeElectricR();
            LogUtil.verbose(str, "getDischargeElectricR=" + dischargeElectricR);
            return dischargeElectricR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getDischargeGunConnectStation() {
        return 0;
    }

    public int getDischargeGunTimer() {
        String str = C3;
        LogUtil.verbose(str, "Service getDischargeGunTimer");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int dischargeGunTimerR = carVendorChargingManager.getDischargeGunTimerR();
            LogUtil.verbose(str, "getDischargeGunTimerR=" + dischargeGunTimerR);
            return dischargeGunTimerR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDischargeLimit() {
        String str = C3;
        LogUtil.verbose(str, "getDischargeLimit");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int dischargeLimitR = carVendorChargingManager.getDischargeLimitR();
                LogUtil.verbose(str, "getDischargeLimitR=" + dischargeLimitR);
                return dischargeLimitR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.a0;
    }

    public int getDischargeLimitMin() {
        return 0;
    }

    public int getDischargeOverReason() {
        String str = C3;
        LogUtil.verbose(str, "Service getDischargeOverReason");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int dischargeOverReasonR = carVendorChargingManager.getDischargeOverReasonR();
            LogUtil.verbose(str, "getDischargeOverReasonR=" + dischargeOverReasonR);
            return dischargeOverReasonR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDischargeRemainTime() {
        String str = C3;
        LogUtil.verbose(str, "Service getDischargeRemainTime");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int dischargeRemainTimeR = carVendorChargingManager.getDischargeRemainTimeR();
            LogUtil.verbose(str, "getDischargeRemainTimeR=" + dischargeRemainTimeR);
            return dischargeRemainTimeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDischargeRequestState() {
        String str = C3;
        LogUtil.verbose(str, "getDischargeRequestState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int dischargeRequestStateR = carVendorChargingManager.getDischargeRequestStateR();
                LogUtil.verbose(str, "getDischargeRequestStateR=" + dischargeRequestStateR);
                return dischargeRequestStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.b0;
    }

    public int[] getDischargeTime() {
        String str = C3;
        LogUtil.verbose(str, "getDischargeTime");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                this.A3[0] = carVendorChargingManager.getDischargeTimeHourR();
                this.A3[1] = this.f65a.getDischargeTimeMinuteR();
                LogUtil.verbose(str, "getDischargeTime" + Arrays.toString(this.A3));
                return this.A3;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        int[] iArr = this.A3;
        iArr[0] = this.c0;
        iArr[1] = this.d0;
        return iArr;
    }

    public int getDischargeToggle() {
        String str = C3;
        LogUtil.verbose(str, "getDischargeToggle");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int dischargeToggleR = carVendorChargingManager.getDischargeToggleR();
                LogUtil.verbose(str, "getDischargeToggleR=" + dischargeToggleR);
                return dischargeToggleR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.e0;
    }

    public int getDischargeTotalTime() {
        String str = C3;
        LogUtil.verbose(str, "Service getDischargeTotalTime");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int dischargeTotalTimeR = carVendorChargingManager.getDischargeTotalTimeR();
            LogUtil.verbose(str, "getDischargeTotalTimeR=" + dischargeTotalTimeR);
            return dischargeTotalTimeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDischargeVoltage() {
        String str = C3;
        LogUtil.verbose(str, "Service getDischargeVoltage");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int dischargeVoltageR = carVendorChargingManager.getDischargeVoltageR();
            LogUtil.verbose(str, "getDischargeVoltageR=" + dischargeVoltageR);
            return dischargeVoltageR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoorLightState() {
        String str = C3;
        LogUtil.verbose(str, "getDoorLightState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860608, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorLightState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorLightSupport(int i) {
        return 0;
    }

    public int getDoorLockStatusAreaRightRear() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int doorLockStatusAreaRightRear = carVendorCarbodyManager.getDoorLockStatusAreaRightRear();
            LogUtil.verbose(C3, "getDoorLockStatusAreaRightRear=" + doorLockStatusAreaRightRear);
            return doorLockStatusAreaRightRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoorLockStatusLeftRear() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int doorLockStatusLeftRear = carVendorCarbodyManager.getDoorLockStatusLeftRear();
            LogUtil.verbose(C3, "getDoorLockStatusLeftRear=" + doorLockStatusLeftRear);
            return doorLockStatusLeftRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoorLockStatusRightFront() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int doorLockStatusRightFront = carVendorCarbodyManager.getDoorLockStatusRightFront();
            LogUtil.verbose(C3, "getDoorLockStatusRightFront=" + doorLockStatusRightFront);
            return doorLockStatusRightFront;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoorState(int i) {
        return 0;
    }

    public int getDoorStateHood() {
        String str = C3;
        LogUtil.verbose(str, "Service getDoorStateHood");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862948, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorStateHood prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStateLeftFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getDoorStateLeftFront");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862943, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorStateLeftFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStateLeftRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getDoorStateLeftRear");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862945, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorStateLeftRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStateLuggage() {
        String str = C3;
        LogUtil.verbose(str, "Service getDoorStateLuggage");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862947, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorStateLuggage prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStateRightFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getDoorStateRightFront");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862944, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorStateRightFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorStateRightRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getDoorStateRightRear");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862946, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorStateRightRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDriveDistanceShowMode() {
        String str = C3;
        LogUtil.verbose(str, "getDriveDistanceShowMode");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int driveDistanceShowModeR = carVendorEnergyManager.getDriveDistanceShowModeR();
            LogUtil.verbose(str, "getDriveDistanceShowModeR=" + driveDistanceShowModeR);
            return driveDistanceShowModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriveDistanceShowStandard() {
        String str = C3;
        LogUtil.verbose(str, "getDriveDistanceShowStandard");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int driveDistanceShowStandardR = carVendorEnergyManager.getDriveDistanceShowStandardR();
            LogUtil.verbose(str, "getDriveDistanceShowStandardR=" + driveDistanceShowStandardR);
            return driveDistanceShowStandardR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEPedalState() {
        String str = C3;
        LogUtil.verbose(str, "getEPedalState");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int ePedalStateR = carVendorEnergyManager.getEPedalStateR();
            LogUtil.verbose(str, "getEPedalStateR=" + ePedalStateR);
            return ePedalStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElecSlideDoorState() {
        String str = C3;
        LogUtil.verbose(str, "Service getElecSlideDoorState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862982, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getElecSlideDoorState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getElectricPedalConfig() {
        int electricPedalConfigR;
        String str = C3;
        LogUtil.verbose(str, " getElectricPedalConfig.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                electricPedalConfigR = carVendorCarbodyManager.getElectricPedalConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                electricPedalConfigR = -1;
            }
            LogUtil.verbose(str, " getElectricPedalConfig.out=" + electricPedalConfigR);
            return electricPedalConfigR;
        }
        LogUtil.verbose(str, " getElectricPedalConfig == null");
        electricPedalConfigR = -1;
        LogUtil.verbose(str, " getElectricPedalConfig.out=" + electricPedalConfigR);
        return electricPedalConfigR;
    }

    public int getElectricPointFlag() {
        String str = C3;
        LogUtil.verbose(str, "getElectricPointFlag");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int electricPointFlag = carVendorEnergyManager.getElectricPointFlag();
            LogUtil.verbose(str, "getElectricPointFlag=" + electricPointFlag);
            return electricPointFlag;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElectricPortCapStateLeft() {
        int electricPortCapStateLeftR;
        String str = C3;
        LogUtil.verbose(str, " getElectricPortCapStateLeft.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                electricPortCapStateLeftR = carVendorChargingManager.getElectricPortCapStateLeftR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                electricPortCapStateLeftR = -1;
            }
            LogUtil.verbose(str, " getElectricPortCapStateLeft.out service =" + electricPortCapStateLeftR);
            return electricPortCapStateLeftR;
        }
        LogUtil.verbose(str, " getElectricPortCapStateLeft.null");
        electricPortCapStateLeftR = -1;
        LogUtil.verbose(str, " getElectricPortCapStateLeft.out service =" + electricPortCapStateLeftR);
        return electricPortCapStateLeftR;
    }

    public int getElectricPortCapStateRight() {
        int electricPortCapStateRightR;
        String str = C3;
        LogUtil.verbose(str, " getElectricPortCapStateRight.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                electricPortCapStateRightR = carVendorChargingManager.getElectricPortCapStateRightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                electricPortCapStateRightR = -1;
            }
            LogUtil.verbose(str, " getElectricPortCapStateRight.out service =" + electricPortCapStateRightR);
            return electricPortCapStateRightR;
        }
        LogUtil.verbose(str, " getElectricPortCapStateRight.null");
        electricPortCapStateRightR = -1;
        LogUtil.verbose(str, " getElectricPortCapStateRight.out service =" + electricPortCapStateRightR);
        return electricPortCapStateRightR;
    }

    public int getElectricShowMode() {
        String str = C3;
        LogUtil.verbose(str, "getElectricShowMode");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557847565, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getElectricShowMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEnergyCurve() {
        return 0;
    }

    public float[] getEnergyElectricCurveX(int i, int i2) {
        return new float[0];
    }

    public float[] getEnergyElectricCurveY(int i, int i2) {
        return new float[0];
    }

    public int getEnergyFeedback() {
        return 0;
    }

    public int getEnergyMode() {
        String str = C3;
        LogUtil.verbose(str, "getEnergyMode");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int energyMode = carVendorEnergyManager.getEnergyMode();
            LogUtil.verbose(str, "getEnergyMode=" + energyMode);
            return energyMode;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float[] getEnergyOilCurveX(int i, int i2) {
        return new float[0];
    }

    public float[] getEnergyOilCurveY(int i, int i2) {
        return new float[0];
    }

    public int getEnergyState() {
        String str = C3;
        LogUtil.verbose(str, "Service getEnergyState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557847586, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEnergyState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEnergyType() {
        String str = C3;
        LogUtil.verbose(str, "service getEnergyType");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int energyTypeR = carVendorCarbodyManager.getEnergyTypeR();
            LogUtil.verbose(str, "getEnergyTypeR=" + energyTypeR);
            return energyTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getEngineCode() {
        Exception e;
        int engineCodeR;
        String str = C3;
        CarVendorEngineManager carVendorEngineManager = this.d;
        int i = -1;
        if (carVendorEngineManager != null) {
            try {
                engineCodeR = carVendorEngineManager.getEngineCodeR();
                try {
                    LogUtil.verbose(str, "getEngineCode=" + engineCodeR);
                    return engineCodeR == 0 ? "371QA" : engineCodeR == 1 ? "473QB" : engineCodeR == 2 ? "473QC" : Integer.toString(engineCodeR);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    LogUtil.error(str, "getEngineCode:code=null");
                    i = engineCodeR;
                    return Integer.toString(i);
                }
            } catch (Exception e3) {
                e = e3;
                engineCodeR = -1;
            }
        }
        return Integer.toString(i);
    }

    public int getEngineCondition() {
        String str = C3;
        LogUtil.verbose(str, "getEngineCondition");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager == null) {
            return 0;
        }
        try {
            int engineConditionR = carVendorEngineManager.getEngineConditionR();
            LogUtil.verbose(str, "getEngineCondition=" + engineConditionR);
            return engineConditionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEngineCoolantLevel() {
        String str = C3;
        LogUtil.verbose(str, "getEngineCoolantLevel");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager == null) {
            return 0;
        }
        try {
            int engineCoolantLevelR = carVendorEngineManager.getEngineCoolantLevelR();
            LogUtil.verbose(str, "getEngineCoolantLevel" + engineCoolantLevelR);
            return engineCoolantLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getEngineDisplacement() {
        String str = C3;
        LogUtil.verbose(str, "getEngineDisplacement");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager == null) {
            return 0.0f;
        }
        try {
            float engineDisplacementR = carVendorEngineManager.getEngineDisplacementR();
            LogUtil.verbose(str, "getEngineDisplacement=" + engineDisplacementR);
            return engineDisplacementR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getEnginePower() {
        String str = C3;
        LogUtil.verbose(str, "getEnginePower");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager != null) {
            try {
                int enginePowerR = carVendorEngineManager.getEnginePowerR();
                LogUtil.verbose(str, "getEnginePower" + enginePowerR);
                return enginePowerR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.W1;
    }

    public int getEngineSpeed() {
        String str = C3;
        LogUtil.verbose(str, "getEngineSpeed");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager != null) {
            try {
                int engineSpeedR = carVendorEngineManager.getEngineSpeedR();
                LogUtil.verbose(str, "getEngineSpeed" + engineSpeedR);
                return engineSpeedR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.X1;
    }

    public int getEvReadyIndicatorLightState() {
        int evReadyIndicatorLightStateR;
        String str = C3;
        LogUtil.verbose(str, " getEvReadyIndicatorLightState.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                evReadyIndicatorLightStateR = carVendorCarbodyManager.getEvReadyIndicatorLightStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                evReadyIndicatorLightStateR = -1;
            }
            LogUtil.verbose(str, " getEvReadyIndicatorLightState.out=" + evReadyIndicatorLightStateR);
            return evReadyIndicatorLightStateR;
        }
        LogUtil.verbose(str, " getEvReadyIndicatorLightState == mCarVendorCarbodyManager");
        evReadyIndicatorLightStateR = -1;
        LogUtil.verbose(str, " getEvReadyIndicatorLightState.out=" + evReadyIndicatorLightStateR);
        return evReadyIndicatorLightStateR;
    }

    public int getFourPeopleEnjoyConfig() {
        int fourPeopleEnjoyConfigR;
        String str = C3;
        LogUtil.verbose(str, " getFourPeopleEnjoyConfig.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                fourPeopleEnjoyConfigR = carVendorCarbodyManager.getFourPeopleEnjoyConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fourPeopleEnjoyConfigR = -1;
            }
            LogUtil.verbose(str, " getFourPeopleEnjoyConfig.out=" + fourPeopleEnjoyConfigR);
            return fourPeopleEnjoyConfigR;
        }
        LogUtil.verbose(str, " getFourPeopleEnjoyConfig == null");
        fourPeopleEnjoyConfigR = -1;
        LogUtil.verbose(str, " getFourPeopleEnjoyConfig.out=" + fourPeopleEnjoyConfigR);
        return fourPeopleEnjoyConfigR;
    }

    public int getFourPeopleEnjoyConfig2() {
        int fourPeopleEnjoyConfig2R;
        String str = C3;
        LogUtil.verbose(str, " getFourPeopleEnjoyConfig2.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                fourPeopleEnjoyConfig2R = carVendorCarbodyManager.getFourPeopleEnjoyConfig2R();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fourPeopleEnjoyConfig2R = -1;
            }
            LogUtil.verbose(str, " getFourPeopleEnjoyConfig2.out service =" + fourPeopleEnjoyConfig2R);
            return fourPeopleEnjoyConfig2R;
        }
        LogUtil.verbose(str, " getFourPeopleEnjoyConfig2.null");
        fourPeopleEnjoyConfig2R = -1;
        LogUtil.verbose(str, " getFourPeopleEnjoyConfig2.out service =" + fourPeopleEnjoyConfig2R);
        return fourPeopleEnjoyConfig2R;
    }

    public int getFrontCenterLightStatus() {
        int frontCenterLightStatusR;
        String str = C3;
        LogUtil.verbose(str, " getFrontCenterLightStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                frontCenterLightStatusR = carVendorLightManager.getFrontCenterLightStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontCenterLightStatusR = -1;
            }
            LogUtil.verbose(str, " getFrontCenterLightStatus.out=" + frontCenterLightStatusR);
            return frontCenterLightStatusR;
        }
        LogUtil.verbose(str, " getFrontCenterLightStatus == null");
        frontCenterLightStatusR = -1;
        LogUtil.verbose(str, " getFrontCenterLightStatus.out=" + frontCenterLightStatusR);
        return frontCenterLightStatusR;
    }

    public int getFrontFogLampStatus() {
        int frontFogLampStatusR;
        String str = C3;
        LogUtil.verbose(str, " getFrontFogLampStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                frontFogLampStatusR = carVendorLightManager.getFrontFogLampStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontFogLampStatusR = -1;
            }
            LogUtil.verbose(str, " getFrontFogLampStatus.out=" + frontFogLampStatusR);
            return frontFogLampStatusR;
        }
        LogUtil.verbose(str, " getFrontFogLampStatus == null");
        frontFogLampStatusR = -1;
        LogUtil.verbose(str, " getFrontFogLampStatus.out=" + frontFogLampStatusR);
        return frontFogLampStatusR;
    }

    public int getFrontFogLightConfig() {
        int frontFogLightConfigR;
        String str = C3;
        LogUtil.verbose(str, " getFrontFogLightConfig.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                frontFogLightConfigR = carVendorLightManager.getFrontFogLightConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontFogLightConfigR = -1;
            }
            LogUtil.verbose(str, " getFrontFogLightConfig.out service =" + frontFogLightConfigR);
            return frontFogLightConfigR;
        }
        LogUtil.verbose(str, " getFrontFogLightConfig.null");
        frontFogLightConfigR = -1;
        LogUtil.verbose(str, " getFrontFogLightConfig.out service =" + frontFogLightConfigR);
        return frontFogLightConfigR;
    }

    public int getFrontFogLightSwitchState() {
        int frontFogLightSwitchStateR;
        String str = C3;
        LogUtil.verbose(str, " getFrontFogLightSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                frontFogLightSwitchStateR = carVendorLightManager.getFrontFogLightSwitchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontFogLightSwitchStateR = -1;
            }
            LogUtil.verbose(str, " getFrontFogLightSwitchState.out service =" + frontFogLightSwitchStateR);
            return frontFogLightSwitchStateR;
        }
        LogUtil.verbose(str, " getFrontFogLightSwitchState.null");
        frontFogLightSwitchStateR = -1;
        LogUtil.verbose(str, " getFrontFogLightSwitchState.out service =" + frontFogLightSwitchStateR);
        return frontFogLightSwitchStateR;
    }

    public int getFseLagerBedModeConfig() {
        int fseLagerBedModeConfigR;
        String str = C3;
        LogUtil.verbose(str, " getFseLagerBedModeConfig.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                fseLagerBedModeConfigR = carVendorCarbodyManager.getFseLagerBedModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fseLagerBedModeConfigR = -1;
            }
            LogUtil.verbose(str, " getFseLagerBedModeConfig.out service =" + fseLagerBedModeConfigR);
            return fseLagerBedModeConfigR;
        }
        LogUtil.verbose(str, " getFseLagerBedModeConfig.null");
        fseLagerBedModeConfigR = -1;
        LogUtil.verbose(str, " getFseLagerBedModeConfig.out service =" + fseLagerBedModeConfigR);
        return fseLagerBedModeConfigR;
    }

    public int getFseSeatSystemWorkMode() {
        int fseSeatSystemWorkModeR;
        String str = C3;
        LogUtil.verbose(str, " getFseSeatSystemWorkMode.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                fseSeatSystemWorkModeR = carVendorCarbodyManager.getFseSeatSystemWorkModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fseSeatSystemWorkModeR = -1;
            }
            LogUtil.verbose(str, " getFseSeatSystemWorkMode.out service =" + fseSeatSystemWorkModeR);
            return fseSeatSystemWorkModeR;
        }
        LogUtil.verbose(str, " getFseSeatSystemWorkMode.null");
        fseSeatSystemWorkModeR = -1;
        LogUtil.verbose(str, " getFseSeatSystemWorkMode.out service =" + fseSeatSystemWorkModeR);
        return fseSeatSystemWorkModeR;
    }

    public int getFuelElecLowPower() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int fuelElecLowPowerR = carVendorCarbodyManager.getFuelElecLowPowerR();
                LogUtil.verbose(C3, "getFuelElecLowPowerR=" + fuelElecLowPowerR);
                return fuelElecLowPowerR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.r3;
    }

    public int getFullChgRemanTimH() {
        String str = C3;
        LogUtil.verbose(str, "Service getFullChgRemanTimH");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int fullChgRemanTimHR = carVendorChargingManager.getFullChgRemanTimHR();
            LogUtil.verbose(str, "getFullChgRemanTimHR=" + fullChgRemanTimHR);
            return fullChgRemanTimHR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFullChgRemanTimM() {
        String str = C3;
        LogUtil.verbose(str, "Service getFullChgRemanTimM");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int fullChgRemanTimMR = carVendorChargingManager.getFullChgRemanTimMR();
            LogUtil.verbose(str, "getFullChgRemanTimMR=" + fullChgRemanTimMR);
            return fullChgRemanTimMR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadlampSupport(int i) {
        return 0;
    }

    public int getHeadlightControlMode() {
        int headlightControlModeR;
        String str = C3;
        LogUtil.verbose(str, " getHeadlightControlMode.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                headlightControlModeR = carVendorLightManager.getHeadlightControlModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                headlightControlModeR = -1;
            }
            LogUtil.verbose(str, " getHeadlightControlMode.out service =" + headlightControlModeR);
            return headlightControlModeR;
        }
        LogUtil.verbose(str, " getHeadlightControlMode.null");
        headlightControlModeR = -1;
        LogUtil.verbose(str, " getHeadlightControlMode.out service =" + headlightControlModeR);
        return headlightControlModeR;
    }

    public int[] getHevChargePlanEndTime() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanEndTimeR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int[] hevChargePlanEndTimeR = carVendorChargingManager.getHevChargePlanEndTimeR();
                LogUtil.verbose(str, "getHevChargePlanEndTimeR=" + Arrays.toString(hevChargePlanEndTimeR));
                return hevChargePlanEndTimeR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int getHevChargePlanEndTimeMonthR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanEndTimeMonthR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanEndTimeMonthR = carVendorChargingManager.getHevChargePlanEndTimeMonthR();
            LogUtil.verbose(str, "getHevChargePlanEndTimeMonthR=" + hevChargePlanEndTimeMonthR);
            return hevChargePlanEndTimeMonthR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanEndTimeTag() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanEndTimeTag");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanEndTimeTagR = carVendorChargingManager.getHevChargePlanEndTimeTagR();
            LogUtil.verbose(str, "getHevChargePlanEndTimeTag=" + hevChargePlanEndTimeTagR);
            return hevChargePlanEndTimeTagR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanEndTimeYearDayR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanEndTimeYearDayR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanEndTimeYearDayR = carVendorChargingManager.getHevChargePlanEndTimeYearDayR();
            LogUtil.verbose(str, "getHevChargePlanEndTimeYearDayR=" + hevChargePlanEndTimeYearDayR);
            return hevChargePlanEndTimeYearDayR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanEndTimeYearHourR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanEndTimeYearHourR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanEndTimeYearHourR = carVendorChargingManager.getHevChargePlanEndTimeYearHourR();
            LogUtil.verbose(str, "getHevChargePlanEndTimeYearHourR=" + hevChargePlanEndTimeYearHourR);
            return hevChargePlanEndTimeYearHourR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanEndTimeYearMinuteR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanEndTimeYearMinuteR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanEndTimeYearMinuteR = carVendorChargingManager.getHevChargePlanEndTimeYearMinuteR();
            LogUtil.verbose(str, "getHevChargePlanEndTimeYearMinuteR=" + hevChargePlanEndTimeYearMinuteR);
            return hevChargePlanEndTimeYearMinuteR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanStartTimeMonthR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanStartTimeMonthR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanStartTimeMonthR = carVendorChargingManager.getHevChargePlanStartTimeMonthR();
            LogUtil.verbose(str, "getHevChargePlanStartTimeMonthR=" + hevChargePlanStartTimeMonthR);
            return hevChargePlanStartTimeMonthR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanStartTimeTag() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanStartTimeTag");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanStartTimeTagR = carVendorChargingManager.getHevChargePlanStartTimeTagR();
            LogUtil.verbose(str, "getHevChargePlanStartTimeTag=" + hevChargePlanStartTimeTagR);
            return hevChargePlanStartTimeTagR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanStartTimeYearDayR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanStartTimeYearDayR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanStartTimeYearDayR = carVendorChargingManager.getHevChargePlanStartTimeYearDayR();
            LogUtil.verbose(str, "getHevChargePlanStartTimeYearDayR=" + hevChargePlanStartTimeYearDayR);
            return hevChargePlanStartTimeYearDayR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanStartTimeYearHourR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanStartTimeYearHourR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanStartTimeYearHourR = carVendorChargingManager.getHevChargePlanStartTimeYearHourR();
            LogUtil.verbose(str, "getHevChargePlanStartTimeYearHourR=" + hevChargePlanStartTimeYearHourR);
            return hevChargePlanStartTimeYearHourR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanStartTimeYearMinuteR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanStartTimeYearMinuteR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanStartTimeYearMinuteR = carVendorChargingManager.getHevChargePlanStartTimeYearMinuteR();
            LogUtil.verbose(str, "getHevChargePlanStartTimeYearMinuteR=" + hevChargePlanStartTimeYearMinuteR);
            return hevChargePlanStartTimeYearMinuteR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlanStartTimeYearR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlanStartTimeYearR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlanStartTimeYearR = carVendorChargingManager.getHevChargePlanStartTimeYearR();
            LogUtil.verbose(str, "getHevChargePlanStartTimeYearR=" + hevChargePlanStartTimeYearR);
            return hevChargePlanStartTimeYearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargePlaneEndTimeYearR() {
        String str = C3;
        LogUtil.info(str, "getHevChargePlaneEndTimeYearR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int hevChargePlaneEndTimeYearR = carVendorChargingManager.getHevChargePlaneEndTimeYearR();
            LogUtil.verbose(str, "getHevChargePlaneEndTimeYearR=" + hevChargePlaneEndTimeYearR);
            return hevChargePlaneEndTimeYearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHevChargingType() {
        LogUtil.verbose(C3, "getHevChargingType");
        return 0;
    }

    public int getHevElectricPointFlag() {
        String str = C3;
        LogUtil.verbose(str, "getHevElectricPointFlag is execute");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int hevElectricPointFlagR = carVendorEnergyManager.getHevElectricPointFlagR();
            LogUtil.verbose(str, "getHevElectricPointFlagR=" + hevElectricPointFlagR);
            return hevElectricPointFlagR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getHevLast200MElectricCon() {
        String str = C3;
        LogUtil.verbose(str, "getHevLast200MElectricCon is execute");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0.0f;
        }
        try {
            float hevLast200MElectricConR = carVendorEnergyManager.getHevLast200MElectricConR();
            LogUtil.verbose(str, "getHevLast200MElectricConR=" + hevLast200MElectricConR);
            return hevLast200MElectricConR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getHightlightPosition() {
        int hightlightPositionR;
        String str = C3;
        LogUtil.verbose(str, " getHightlightPosition.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                hightlightPositionR = carVendorLightManager.getHightlightPositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                hightlightPositionR = -1;
            }
            LogUtil.verbose(str, " getHightlightPosition.out=" + hightlightPositionR);
            return hightlightPositionR;
        }
        LogUtil.verbose(str, " getHightlightPosition == null");
        hightlightPositionR = -1;
        LogUtil.verbose(str, " getHightlightPosition.out=" + hightlightPositionR);
        return hightlightPositionR;
    }

    public int getHightlightPositionConfig() {
        int hightlightPositionConfigR;
        String str = C3;
        LogUtil.verbose(str, " getHightlightPositionConfig.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                hightlightPositionConfigR = carVendorLightManager.getHightlightPositionConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                hightlightPositionConfigR = -1;
            }
            LogUtil.verbose(str, " getHightlightPositionConfig.out=" + hightlightPositionConfigR);
            return hightlightPositionConfigR;
        }
        LogUtil.verbose(str, " getHightlightPositionConfig == null");
        hightlightPositionConfigR = -1;
        LogUtil.verbose(str, " getHightlightPositionConfig.out=" + hightlightPositionConfigR);
        return hightlightPositionConfigR;
    }

    public int getIalBrightnessBack() {
        LogUtil.verbose(C3, "getIalBrightnessBack");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            return carVendorLightManager.getIalBrightnessBackR();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalBrightnessFront() {
        String str = C3;
        LogUtil.verbose(str, "getIalBrightnessFront");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialBrightnessFrontR = carVendorLightManager.getIalBrightnessFrontR();
            LogUtil.verbose(str, "getIalBrightnessFrontR=" + ialBrightnessFrontR);
            return ialBrightnessFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalBrightnessStarRing() {
        String str = C3;
        LogUtil.verbose(str, "getIalBrightnessStarRing");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialBrightnessStarRingR = carVendorLightManager.getIalBrightnessStarRingR();
            LogUtil.verbose(str, "getIalBrightnessStarRingR=" + ialBrightnessStarRingR);
            return ialBrightnessStarRingR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalBrightnessStarrySky() {
        String str = C3;
        LogUtil.verbose(str, "getIalBrightnessStarrySky");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialBrightnessStarrySkyR = carVendorLightManager.getIalBrightnessStarrySkyR();
            LogUtil.verbose(str, "getIalBrightnessStarrySkyR=" + ialBrightnessStarrySkyR);
            return ialBrightnessStarrySkyR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalColor(int i) {
        String str = C3;
        LogUtil.verbose(str, "getIalColor");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialColorBackR = carVendorLightManager.getIalColorBackR();
            LogUtil.verbose(str, "getIalColorBackR=" + ialColorBackR);
            return ialColorBackR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalColorFront() {
        String str = C3;
        LogUtil.verbose(str, "getIALColorFront");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialColorFrontR = carVendorLightManager.getIalColorFrontR();
            LogUtil.verbose(str, "getIalColorFrontR=" + ialColorFrontR);
            return ialColorFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalColorStarRing() {
        String str = C3;
        LogUtil.verbose(str, "getIALColorStarRing");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialColorStarRingR = carVendorLightManager.getIalColorStarRingR();
            LogUtil.verbose(str, "getIalColorStarRingR=" + ialColorStarRingR);
            return ialColorStarRingR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIalColorStarrySky() {
        String str = C3;
        LogUtil.verbose(str, "getIALColorStarrySky");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int ialColorStarrySkyR = carVendorLightManager.getIalColorStarrySkyR();
            LogUtil.verbose(str, "getIalColorStarrySkyR=" + ialColorStarrySkyR);
            return ialColorStarrySkyR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIndirectTyrePressureStateLb() {
        int indirectTyrePressureStateLbR;
        String str = C3;
        LogUtil.verbose(str, " getIndirectTyrePressureStateLb.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                indirectTyrePressureStateLbR = carVendorTyreManager.getIndirectTyrePressureStateLbR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                indirectTyrePressureStateLbR = -1;
            }
            LogUtil.verbose(str, " getIndirectTyrePressureStateLb.out=" + indirectTyrePressureStateLbR);
            return indirectTyrePressureStateLbR;
        }
        LogUtil.verbose(str, " getIndirectTyrePressureStateLb.null == mCarVendorTyreManager");
        indirectTyrePressureStateLbR = -1;
        LogUtil.verbose(str, " getIndirectTyrePressureStateLb.out=" + indirectTyrePressureStateLbR);
        return indirectTyrePressureStateLbR;
    }

    public int getIndirectTyrePressureStateLf() {
        int indirectTyrePressureStateLfR;
        String str = C3;
        LogUtil.verbose(str, " getIndirectTyrePressureStateLf.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                indirectTyrePressureStateLfR = carVendorTyreManager.getIndirectTyrePressureStateLfR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                indirectTyrePressureStateLfR = -1;
            }
            LogUtil.verbose(str, " getIndirectTyrePressureStateLf.out=" + indirectTyrePressureStateLfR);
            return indirectTyrePressureStateLfR;
        }
        LogUtil.verbose(str, " getIndirectTyrePressureStateLf.null == mCarVendorTyreManager");
        indirectTyrePressureStateLfR = -1;
        LogUtil.verbose(str, " getIndirectTyrePressureStateLf.out=" + indirectTyrePressureStateLfR);
        return indirectTyrePressureStateLfR;
    }

    public int getIndirectTyrePressureStateRb() {
        int indirectTyrePressureStateRbR;
        String str = C3;
        LogUtil.verbose(str, " getIndirectTyrePressureStateRb.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                indirectTyrePressureStateRbR = carVendorTyreManager.getIndirectTyrePressureStateRbR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                indirectTyrePressureStateRbR = -1;
            }
            LogUtil.verbose(str, " getIndirectTyrePressureStateRb.out=" + indirectTyrePressureStateRbR);
            return indirectTyrePressureStateRbR;
        }
        LogUtil.verbose(str, " getIndirectTyrePressureStateRb.null == mCarVendorTyreManager");
        indirectTyrePressureStateRbR = -1;
        LogUtil.verbose(str, " getIndirectTyrePressureStateRb.out=" + indirectTyrePressureStateRbR);
        return indirectTyrePressureStateRbR;
    }

    public int getIndirectTyrePressureStateRf() {
        int indirectTyrePressureStateRfR;
        String str = C3;
        LogUtil.verbose(str, " getIndirectTyrePressureStateRf.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                indirectTyrePressureStateRfR = carVendorTyreManager.getIndirectTyrePressureStateRfR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                indirectTyrePressureStateRfR = -1;
            }
            LogUtil.verbose(str, " getIndirectTyrePressureStateRf.out=" + indirectTyrePressureStateRfR);
            return indirectTyrePressureStateRfR;
        }
        LogUtil.verbose(str, " getIndirectTyrePressureStateRf.null == mCarVendorTyreManager");
        indirectTyrePressureStateRfR = -1;
        LogUtil.verbose(str, " getIndirectTyrePressureStateRf.out=" + indirectTyrePressureStateRfR);
        return indirectTyrePressureStateRfR;
    }

    public int getIndirectTyreSystemState() {
        String str = C3;
        LogUtil.verbose(str, "getIndirectTyreSystemState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int indirectTyreSystemState = carVendorTyreManager.getIndirectTyreSystemState();
                LogUtil.verbose(str, "getIndirectTyreSystemState=" + indirectTyreSystemState);
                return indirectTyreSystemState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.a2;
    }

    public int getIndoorLightSupport(int i) {
        return 0;
    }

    public int getIntelligentKeepTemMode() {
        return 0;
    }

    public int getIntelligentOrCoerceKeepBattery() {
        return 0;
    }

    public int getIntelligentPostionFeedback() {
        int intelligentPostionFeedbackR;
        String str = C3;
        LogUtil.verbose(str, " getIntelligentPostionFeedback.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                intelligentPostionFeedbackR = carVendorLightManager.getIntelligentPostionFeedbackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                intelligentPostionFeedbackR = -1;
            }
            LogUtil.verbose(str, " getIntelligentPostionFeedback.out=" + intelligentPostionFeedbackR);
            return intelligentPostionFeedbackR;
        }
        LogUtil.verbose(str, " getIntelligentPostionFeedback == null");
        intelligentPostionFeedbackR = -1;
        LogUtil.verbose(str, " getIntelligentPostionFeedback.out=" + intelligentPostionFeedbackR);
        return intelligentPostionFeedbackR;
    }

    public int getInternalDoorHandleStateByRearBcmlfr() {
        int internalDoorHandleStateByRearBCMLFR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlfr.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateByRearBCMLFR = carVendorCarbodyManager.getInternalDoorHandleStateByRearBCMLFR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateByRearBCMLFR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlfr.out=" + internalDoorHandleStateByRearBCMLFR);
            return internalDoorHandleStateByRearBCMLFR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlfr.null");
        internalDoorHandleStateByRearBCMLFR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlfr.out=" + internalDoorHandleStateByRearBCMLFR);
        return internalDoorHandleStateByRearBCMLFR;
    }

    public int getInternalDoorHandleStateByRearBcmlrr() {
        int internalDoorHandleStateByRearBCMLRR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlrr.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateByRearBCMLRR = carVendorCarbodyManager.getInternalDoorHandleStateByRearBCMLRR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateByRearBCMLRR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlrr.out=" + internalDoorHandleStateByRearBCMLRR);
            return internalDoorHandleStateByRearBCMLRR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlrr.null");
        internalDoorHandleStateByRearBCMLRR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmlrr.out=" + internalDoorHandleStateByRearBCMLRR);
        return internalDoorHandleStateByRearBCMLRR;
    }

    public int getInternalDoorHandleStateByRearBcmrfr() {
        int internalDoorHandleStateByRearBCMRFR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrfr.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateByRearBCMRFR = carVendorCarbodyManager.getInternalDoorHandleStateByRearBCMRFR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateByRearBCMRFR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrfr.out=" + internalDoorHandleStateByRearBCMRFR);
            return internalDoorHandleStateByRearBCMRFR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrfr.null");
        internalDoorHandleStateByRearBCMRFR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrfr.out=" + internalDoorHandleStateByRearBCMRFR);
        return internalDoorHandleStateByRearBCMRFR;
    }

    public int getInternalDoorHandleStateByRearBcmrrr() {
        int internalDoorHandleStateByRearBCMRRR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrrr.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateByRearBCMRRR = carVendorCarbodyManager.getInternalDoorHandleStateByRearBCMRRR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateByRearBCMRRR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrrr.out=" + internalDoorHandleStateByRearBCMRRR);
            return internalDoorHandleStateByRearBCMRRR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrrr.null");
        internalDoorHandleStateByRearBCMRRR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateByRearBcmrrr.out=" + internalDoorHandleStateByRearBCMRRR);
        return internalDoorHandleStateByRearBCMRRR;
    }

    public int getInternalDoorHandleStateLeftBack(int i) {
        int internalDoorHandleStateLeftBackR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateLeftBack.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateLeftBackR = carVendorCarbodyManager.getInternalDoorHandleStateLeftBackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateLeftBackR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateLeftBack.out=" + internalDoorHandleStateLeftBackR);
            return internalDoorHandleStateLeftBackR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateLeftBack == null");
        internalDoorHandleStateLeftBackR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateLeftBack.out=" + internalDoorHandleStateLeftBackR);
        return internalDoorHandleStateLeftBackR;
    }

    public int getInternalDoorHandleStateLeftFront(int i) {
        int internalDoorHandleStateLeftFrontR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateLeftFront.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateLeftFrontR = carVendorCarbodyManager.getInternalDoorHandleStateLeftFrontR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateLeftFrontR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateLeftFront.out=" + internalDoorHandleStateLeftFrontR);
            return internalDoorHandleStateLeftFrontR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateLeftFront == null");
        internalDoorHandleStateLeftFrontR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateLeftFront.out=" + internalDoorHandleStateLeftFrontR);
        return internalDoorHandleStateLeftFrontR;
    }

    public int getInternalDoorHandleStateRightBack(int i) {
        int internalDoorHandleStateRightBackR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateRightBack.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateRightBackR = carVendorCarbodyManager.getInternalDoorHandleStateRightBackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateRightBackR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateRightBack.out=" + internalDoorHandleStateRightBackR);
            return internalDoorHandleStateRightBackR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateRightBack == null");
        internalDoorHandleStateRightBackR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateRightBack.out=" + internalDoorHandleStateRightBackR);
        return internalDoorHandleStateRightBackR;
    }

    public int getInternalDoorHandleStateRightFront(int i) {
        int internalDoorHandleStateRightFrontR;
        String str = C3;
        LogUtil.verbose(str, " getInternalDoorHandleStateRightFront.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                internalDoorHandleStateRightFrontR = carVendorCarbodyManager.getInternalDoorHandleStateRightFrontR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                internalDoorHandleStateRightFrontR = -1;
            }
            LogUtil.verbose(str, " getInternalDoorHandleStateRightFront.out=" + internalDoorHandleStateRightFrontR);
            return internalDoorHandleStateRightFrontR;
        }
        LogUtil.verbose(str, " getInternalDoorHandleStateRightFront == null");
        internalDoorHandleStateRightFrontR = -1;
        LogUtil.verbose(str, " getInternalDoorHandleStateRightFront.out=" + internalDoorHandleStateRightFrontR);
        return internalDoorHandleStateRightFrontR;
    }

    public float getLast200mElectricCon() {
        String str = C3;
        LogUtil.verbose(str, "getLast200MElectricCon");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0.0f;
        }
        try {
            float last200MElectricCon = carVendorEnergyManager.getLast200MElectricCon();
            LogUtil.verbose(str, "getLast200MElectricCon=" + last200MElectricCon);
            return last200MElectricCon;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getLast200mOilCon() {
        String str = C3;
        LogUtil.verbose(str, "getLast200MOilCon");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0.0f;
        }
        try {
            float last200MOilCon = carVendorEnergyManager.getLast200MOilCon();
            LogUtil.verbose(str, "getLast200MOilCon=" + last200MOilCon);
            return last200MOilCon;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getLast50KmElectricConAvg() {
        String str = C3;
        LogUtil.verbose(str, "getLast50KMElectricConAvg");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0.0f;
        }
        try {
            float last50KMElectricConAvg = carVendorEnergyManager.getLast50KMElectricConAvg();
            LogUtil.verbose(str, "getLast50KMElectricConAvg=" + last50KMElectricConAvg);
            return last50KMElectricConAvg;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getLast50KmOilConAvg() {
        String str = C3;
        LogUtil.verbose(str, "getLast50KMOilConAvg");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0.0f;
        }
        try {
            float last50KMOilConAvg = carVendorEnergyManager.getLast50KMOilConAvg();
            LogUtil.verbose(str, "getLast50KMOilConAvg=" + last50KMOilConAvg);
            return last50KMOilConAvg;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getLastSetedArea() {
        String str = C3;
        LogUtil.verbose(str, "getLastSetedArea");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int lastSetedArea = carVendorLightManager.getLastSetedArea();
            LogUtil.verbose(str, "getLastSetedArea=" + lastSetedArea);
            return lastSetedArea;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftCornerLightStatus() {
        int leftCornerLightStatusR;
        String str = C3;
        LogUtil.verbose(str, " getLeftCornerLightStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftCornerLightStatusR = carVendorLightManager.getLeftCornerLightStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftCornerLightStatusR = -1;
            }
            LogUtil.verbose(str, " getLeftCornerLightStatus.out=" + leftCornerLightStatusR);
            return leftCornerLightStatusR;
        }
        LogUtil.verbose(str, " getLeftCornerLightStatus == null");
        leftCornerLightStatusR = -1;
        LogUtil.verbose(str, " getLeftCornerLightStatus.out=" + leftCornerLightStatusR);
        return leftCornerLightStatusR;
    }

    public int getLeftDaytimeLightState() {
        int leftDaytimeLightStateR;
        String str = C3;
        LogUtil.verbose(str, " getLeftDaytimeLightState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftDaytimeLightStateR = carVendorLightManager.getLeftDaytimeLightStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftDaytimeLightStateR = -1;
            }
            LogUtil.verbose(str, " getLeftDaytimeLightState.out=" + leftDaytimeLightStateR);
            return leftDaytimeLightStateR;
        }
        LogUtil.verbose(str, " getLeftDaytimeLightState == null");
        leftDaytimeLightStateR = -1;
        LogUtil.verbose(str, " getLeftDaytimeLightState.out=" + leftDaytimeLightStateR);
        return leftDaytimeLightStateR;
    }

    public int getLeftDaytimeRunLampState() {
        int leftDaytimeRunLampStateR;
        String str = C3;
        LogUtil.verbose(str, " getLeftDaytimeRunLampState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftDaytimeRunLampStateR = carVendorLightManager.getLeftDaytimeRunLampStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftDaytimeRunLampStateR = -1;
            }
            LogUtil.verbose(str, " getLeftDaytimeRunLampState.out=" + leftDaytimeRunLampStateR);
            return leftDaytimeRunLampStateR;
        }
        LogUtil.verbose(str, " getLeftDaytimeRunLampState.null == mCarVendorLightManager");
        leftDaytimeRunLampStateR = -1;
        LogUtil.verbose(str, " getLeftDaytimeRunLampState.out=" + leftDaytimeRunLampStateR);
        return leftDaytimeRunLampStateR;
    }

    public int getLeftFrontPositionSwlStatus() {
        int leftFrontPositionSwlStatusR;
        String str = C3;
        LogUtil.verbose(str, " getLeftFrontPositionSwlStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftFrontPositionSwlStatusR = carVendorLightManager.getLeftFrontPositionSwlStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftFrontPositionSwlStatusR = -1;
            }
            LogUtil.verbose(str, " getLeftFrontPositionSwlStatus.out=" + leftFrontPositionSwlStatusR);
            return leftFrontPositionSwlStatusR;
        }
        LogUtil.verbose(str, " getLeftFrontPositionSwlStatus == null");
        leftFrontPositionSwlStatusR = -1;
        LogUtil.verbose(str, " getLeftFrontPositionSwlStatus.out=" + leftFrontPositionSwlStatusR);
        return leftFrontPositionSwlStatusR;
    }

    public int getLeftFrontReadingLampStatus() {
        int leftFrontReadingLampStatusR;
        String str = C3;
        LogUtil.verbose(str, " getLeftFrontReadingLampStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftFrontReadingLampStatusR = carVendorLightManager.getLeftFrontReadingLampStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftFrontReadingLampStatusR = -1;
            }
            LogUtil.verbose(str, " getLeftFrontReadingLampStatus.out=" + leftFrontReadingLampStatusR);
            return leftFrontReadingLampStatusR;
        }
        LogUtil.verbose(str, " getLeftFrontReadingLampStatus == null");
        leftFrontReadingLampStatusR = -1;
        LogUtil.verbose(str, " getLeftFrontReadingLampStatus.out=" + leftFrontReadingLampStatusR);
        return leftFrontReadingLampStatusR;
    }

    public int getLeftMiddleRowRLightRotaryState() {
        int leftMiddleRowRLightRotaryStateR;
        String str = C3;
        LogUtil.verbose(str, " getLeftMiddleRowRLightRotaryState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftMiddleRowRLightRotaryStateR = carVendorLightManager.getLeftMiddleRowRLightRotaryStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftMiddleRowRLightRotaryStateR = -1;
            }
            LogUtil.verbose(str, " getLeftMiddleRowRLightRotaryState.out=" + leftMiddleRowRLightRotaryStateR);
            return leftMiddleRowRLightRotaryStateR;
        }
        LogUtil.verbose(str, " getLeftMiddleRowRLightRotaryState == null");
        leftMiddleRowRLightRotaryStateR = -1;
        LogUtil.verbose(str, " getLeftMiddleRowRLightRotaryState.out=" + leftMiddleRowRLightRotaryStateR);
        return leftMiddleRowRLightRotaryStateR;
    }

    public int getLeftMiddleRowSideRLightSwitch() {
        int leftMiddleRowSideRLightSwitchR;
        String str = C3;
        LogUtil.verbose(str, " getLeftMiddleRowSideRLightSwitch.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftMiddleRowSideRLightSwitchR = carVendorLightManager.getLeftMiddleRowSideRLightSwitchR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftMiddleRowSideRLightSwitchR = -1;
            }
            LogUtil.verbose(str, " getLeftMiddleRowSideRLightSwitch.out=" + leftMiddleRowSideRLightSwitchR);
            return leftMiddleRowSideRLightSwitchR;
        }
        LogUtil.verbose(str, " getLeftMiddleRowSideRLightSwitch == null");
        leftMiddleRowSideRLightSwitchR = -1;
        LogUtil.verbose(str, " getLeftMiddleRowSideRLightSwitch.out=" + leftMiddleRowSideRLightSwitchR);
        return leftMiddleRowSideRLightSwitchR;
    }

    public int getLeftRearRLightSwitch() {
        int leftRearRLightSwitchR;
        String str = C3;
        LogUtil.verbose(str, " getLeftRearRLightSwitch.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                leftRearRLightSwitchR = carVendorLightManager.getLeftRearRLightSwitchR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftRearRLightSwitchR = -1;
            }
            LogUtil.verbose(str, " getLeftRearRLightSwitch.out=" + leftRearRLightSwitchR);
            return leftRearRLightSwitchR;
        }
        LogUtil.verbose(str, " getLeftRearRLightSwitch == null");
        leftRearRLightSwitchR = -1;
        LogUtil.verbose(str, " getLeftRearRLightSwitch.out=" + leftRearRLightSwitchR);
        return leftRearRLightSwitchR;
    }

    public int getLeftSeatRailsRow3FeedbackPosition() {
        int leftSeatRailsRow3FeedbackPositionR;
        String str = C3;
        LogUtil.verbose(str, " getLeftSeatRailsRow3FeedbackPosition.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                leftSeatRailsRow3FeedbackPositionR = carVendorCarbodyManager.getLeftSeatRailsRow3FeedbackPositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftSeatRailsRow3FeedbackPositionR = -1;
            }
            LogUtil.verbose(str, " getLeftSeatRailsRow3FeedbackPosition.out service =" + leftSeatRailsRow3FeedbackPositionR);
            return leftSeatRailsRow3FeedbackPositionR;
        }
        LogUtil.verbose(str, " getLeftSeatRailsRow3FeedbackPosition.null");
        leftSeatRailsRow3FeedbackPositionR = -1;
        LogUtil.verbose(str, " getLeftSeatRailsRow3FeedbackPosition.out service =" + leftSeatRailsRow3FeedbackPositionR);
        return leftSeatRailsRow3FeedbackPositionR;
    }

    public int getLeftSeatRow3NonElectricallyAdjustState() {
        int leftSeatRow3NonElectricallyAdjustStateR;
        String str = C3;
        LogUtil.verbose(str, " getLeftSeatRow3NonElectricallyAdjustState.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                leftSeatRow3NonElectricallyAdjustStateR = carVendorCarbodyManager.getLeftSeatRow3NonElectricallyAdjustStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftSeatRow3NonElectricallyAdjustStateR = -1;
            }
            LogUtil.verbose(str, " getLeftSeatRow3NonElectricallyAdjustState.out service =" + leftSeatRow3NonElectricallyAdjustStateR);
            return leftSeatRow3NonElectricallyAdjustStateR;
        }
        LogUtil.verbose(str, " getLeftSeatRow3NonElectricallyAdjustState.null");
        leftSeatRow3NonElectricallyAdjustStateR = -1;
        LogUtil.verbose(str, " getLeftSeatRow3NonElectricallyAdjustState.out service =" + leftSeatRow3NonElectricallyAdjustStateR);
        return leftSeatRow3NonElectricallyAdjustStateR;
    }

    public int getLeftSlideDoorAction() {
        String str = C3;
        LogUtil.verbose(str, "Service getLeftSlideDoorAction");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862985, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportFragrance prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftSlideDoorPercent() {
        String str = C3;
        LogUtil.verbose(str, "Service getLeftSlideDoorPercent");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int leftSlideDoorPercentR = carVendorCarbodyManager.getLeftSlideDoorPercentR();
            LogUtil.verbose(str, "getLeftSlideDoorPercentR=" + leftSlideDoorPercentR);
            return leftSlideDoorPercentR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftSlideDoorState() {
        String str = C3;
        LogUtil.verbose(str, "Service getLeftSlideDoorState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862983, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftSlideDoorState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftStrutMorotRunningState() {
        String str = C3;
        LogUtil.verbose(str, "Service getLeftStrutMorotRunningState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862953, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftStrutMorotRunningState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLightAdasIndicatorStatus() {
        int lightAdasIndicatorStatusR;
        String str = C3;
        LogUtil.verbose(str, " getLightAdasIndicatorStatus.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                lightAdasIndicatorStatusR = carVendorLightManager.getLightAdasIndicatorStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lightAdasIndicatorStatusR = -1;
            }
            LogUtil.verbose(str, " getLightAdasIndicatorStatus.out service =" + lightAdasIndicatorStatusR);
            return lightAdasIndicatorStatusR;
        }
        LogUtil.verbose(str, " getLightAdasIndicatorStatus.null");
        lightAdasIndicatorStatusR = -1;
        LogUtil.verbose(str, " getLightAdasIndicatorStatus.out service =" + lightAdasIndicatorStatusR);
        return lightAdasIndicatorStatusR;
    }

    public int getLightAutoStatus() {
        LogUtil.verbose(C3, "getLightAutoStatus");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            return carVendorLightManager.getLightAutoStatusTwo();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLightDelayTime(int i) {
        return 0;
    }

    public int getLightFoot() {
        LogUtil.verbose(C3, "Service getLightFoot");
        return 0;
    }

    public int getLightFrontFog() {
        LogUtil.verbose(C3, "Service getLightFrontFog");
        return 0;
    }

    public int getLightFrontGlowLogoWorkState() {
        int lightFrontGlowLogoWorkStateR;
        String str = C3;
        LogUtil.verbose(str, " getLightFrontGlowLogoWorkState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                lightFrontGlowLogoWorkStateR = carVendorLightManager.getLightFrontGlowLogoWorkStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lightFrontGlowLogoWorkStateR = -1;
            }
            LogUtil.verbose(str, " getLightFrontGlowLogoWorkState.out service =" + lightFrontGlowLogoWorkStateR);
            return lightFrontGlowLogoWorkStateR;
        }
        LogUtil.verbose(str, " getLightFrontGlowLogoWorkState.null");
        lightFrontGlowLogoWorkStateR = -1;
        LogUtil.verbose(str, " getLightFrontGlowLogoWorkState.out service =" + lightFrontGlowLogoWorkStateR);
        return lightFrontGlowLogoWorkStateR;
    }

    public int getLightHighBeam() {
        String str = C3;
        LogUtil.verbose(str, "Service getLightHighBeam");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int lightHighBeamR = carVendorCarbodyManager.getLightHighBeamR();
            LogUtil.verbose(str, "getLightHighBeamR=" + lightHighBeamR);
            return lightHighBeamR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLightLeftTurnSignal() {
        LogUtil.verbose(C3, "Service getLightLeftTurnSignal");
        return 0;
    }

    public int getLightLowBeamStatus() {
        String str = C3;
        LogUtil.verbose(str, "Service getLightLowBeamStatus");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int lightLowBeamStatusR = carVendorCarbodyManager.getLightLowBeamStatusR();
            LogUtil.verbose(str, "getLightLowBeamStatusR=" + lightLowBeamStatusR);
            return lightLowBeamStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLightRearFog() {
        String str = C3;
        LogUtil.verbose(str, "Service getLightRearFog");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int lightRearFog = carVendorCarbodyManager.getLightRearFog();
            LogUtil.verbose(str, "getLightRearFog=" + lightRearFog);
            return lightRearFog;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLightRearGlowLogoWorkState() {
        int lightRearGlowLogoWorkStateR;
        String str = C3;
        LogUtil.verbose(str, " getLightRearGlowLogoWorkState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                lightRearGlowLogoWorkStateR = carVendorLightManager.getLightRearGlowLogoWorkStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lightRearGlowLogoWorkStateR = -1;
            }
            LogUtil.verbose(str, " getLightRearGlowLogoWorkState.out service =" + lightRearGlowLogoWorkStateR);
            return lightRearGlowLogoWorkStateR;
        }
        LogUtil.verbose(str, " getLightRearGlowLogoWorkState.null");
        lightRearGlowLogoWorkStateR = -1;
        LogUtil.verbose(str, " getLightRearGlowLogoWorkState.out service =" + lightRearGlowLogoWorkStateR);
        return lightRearGlowLogoWorkStateR;
    }

    public int getLightRearPosition() {
        String str = C3;
        LogUtil.verbose(str, "Service getLightRearPosition");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int lightRearPosition = carVendorCarbodyManager.getLightRearPosition();
            LogUtil.verbose(str, "getLightRearPosition=" + lightRearPosition);
            return lightRearPosition;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLightRightTurnSignal() {
        LogUtil.verbose(C3, "Service getLightRightTurnSignal");
        return 0;
    }

    public int getLightSideStatus() {
        String str = C3;
        LogUtil.verbose(str, "Service getLightSideStatus");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int lightSideStatusR = carVendorCarbodyManager.getLightSideStatusR();
            LogUtil.verbose(str, "getLightSideStatusR=" + lightSideStatusR);
            return lightSideStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMain2SaveOrRecoverState() {
        String str = C3;
        LogUtil.info(str, "getMain2SaveOrRecoverState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int recoverOrSaveParamsChanged5 = carVendorCarbodyManager.getRecoverOrSaveParamsChanged5();
            LogUtil.verbose(str, "getRecoverOrSaveParamsChanged5=" + recoverOrSaveParamsChanged5);
            return recoverOrSaveParamsChanged5;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMainSaveOrRecoverState() {
        String str = C3;
        LogUtil.info(str, "getMainSaveOrRecoverState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int i = carVendorCarbodyManager.getonSaveSeatParamsAllChangedPlace7();
            LogUtil.verbose(str, "getonSaveSeatParamsAllChangedPlace7=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMandatoryElectricState() {
        String str = C3;
        LogUtil.verbose(str, "getMandatoryElectricState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557847567, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMandatoryElectricState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMemoryElectricState() {
        String str = C3;
        LogUtil.verbose(str, "getMemoryElectricState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557847576, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMemoryElectricState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMemoryMaxEvState() {
        String str = C3;
        LogUtil.verbose(str, "getMemoryMaxEvState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557847574, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMemoryMaxEvState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMemorySportState() {
        String str = C3;
        LogUtil.verbose(str, "getMemorySportState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557847572, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMemorySportState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMiddleSternLampState() {
        int middleSternLampStateR;
        String str = C3;
        LogUtil.verbose(str, " getMiddleSternLampState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                middleSternLampStateR = carVendorLightManager.getMiddleSternLampStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                middleSternLampStateR = -1;
            }
            LogUtil.verbose(str, " getMiddleSternLampState.out service =" + middleSternLampStateR);
            return middleSternLampStateR;
        }
        LogUtil.verbose(str, " getMiddleSternLampState.null");
        middleSternLampStateR = -1;
        LogUtil.verbose(str, " getMiddleSternLampState.out service =" + middleSternLampStateR);
        return middleSternLampStateR;
    }

    public int getMoonRoofConfig() {
        String str = C3;
        LogUtil.verbose(str, "getMoonRoofConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862942, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMoonRoofConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMoonRoofConfigTwo() {
        int moonRoofConfigR;
        String str = C3;
        LogUtil.verbose(str, " getMoonRoofConfigTwo.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                moonRoofConfigR = carVendorCarbodyManager.getMoonRoofConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                moonRoofConfigR = -1;
            }
            LogUtil.verbose(str, " getMoonRoofConfigTwo.out=" + moonRoofConfigR);
            return moonRoofConfigR;
        }
        LogUtil.verbose(str, " getMoonRoofConfigTwo == null");
        moonRoofConfigR = -1;
        LogUtil.verbose(str, " getMoonRoofConfigTwo.out=" + moonRoofConfigR);
        return moonRoofConfigR;
    }

    public int getMoonRoofPercentControlFlag() {
        String str = C3;
        LogUtil.verbose(str, " getMoonRoofPercentControlFlag.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557863029, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMoonRoofPercentControlFlag prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMoonRoofType() {
        return 0;
    }

    public int getMovePowerStationToggle() {
        String str = C3;
        LogUtil.verbose(str, "getChargeToggleGuard");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int movePowerStationToggleR = carVendorChargingManager.getMovePowerStationToggleR();
                LogUtil.verbose(str, "getMovePowerStationToggleR=" + movePowerStationToggleR);
                return movePowerStationToggleR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.g0;
    }

    public int[] getNextChargingTime() {
        return null;
    }

    public int getNoSenseChargeState() {
        String str = C3;
        LogUtil.verbose(str, "getNoSenseChargeState is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int noSenseChargeStateR = carVendorChargingManager.getNoSenseChargeStateR();
            LogUtil.verbose(str, "getNoSenseChargeStateR=" + noSenseChargeStateR);
            return noSenseChargeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte[] getObcWakeNoSenseCharge() {
        LogUtil.verbose(C3, "getOBCWakeNoSenseCharge is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                return new byte[]{(byte) carVendorChargingManager.getObcWakeNoSenseCharge1R(), (byte) this.f65a.getObcWakeNoSenseCharge2R(), (byte) this.f65a.getObcWakeNoSenseCharge3R(), (byte) this.f65a.getObcWakeNoSenseCharge4R(), (byte) this.f65a.getObcWakeNoSenseCharge5R(), (byte) this.f65a.getObcWakeNoSenseCharge6R(), (byte) this.f65a.getObcWakeNoSenseCharge7R(), (byte) this.f65a.getObcWakeNoSenseCharge8R(), (byte) this.f65a.getObcWakeNoSenseCharge9R(), (byte) this.f65a.getObcWakeNoSenseCharge10R(), (byte) this.f65a.getObcWakeNoSenseCharge11R(), (byte) this.f65a.getObcWakeNoSenseCharge12R(), (byte) this.f65a.getObcWakeNoSenseCharge13R(), (byte) this.f65a.getObcWakeNoSenseCharge14R(), (byte) this.f65a.getObcWakeNoSenseCharge15R(), (byte) this.f65a.getObcWakeNoSenseCharge16R(), (byte) this.f65a.getObcWakeNoSenseCharge17R(), (byte) this.f65a.getObcWakeNoSenseCharge18R(), (byte) this.f65a.getObcWakeNoSenseCharge19R(), (byte) this.f65a.getObcWakeNoSenseCharge20R(), (byte) this.f65a.getObcWakeNoSenseCharge21R(), (byte) this.f65a.getObcWakeNoSenseCharge22R(), (byte) this.f65a.getObcWakeNoSenseCharge23R(), (byte) this.f65a.getObcWakeNoSenseCharge24R(), (byte) this.f65a.getObcWakeNoSenseCharge25R(), (byte) this.f65a.getObcWakeNoSenseCharge26R(), (byte) this.f65a.getObcWakeNoSenseCharge27R(), (byte) this.f65a.getObcWakeNoSenseCharge28R(), (byte) this.f65a.getObcWakeNoSenseCharge29R(), (byte) this.f65a.getObcWakeNoSenseCharge30R(), (byte) this.f65a.getObcWakeNoSenseCharge31R(), (byte) this.f65a.getObcWakeNoSenseCharge32R(), (byte) this.f65a.getObcWakeNoSenseCharge33R(), (byte) this.f65a.getObcWakeNoSenseCharge34R(), (byte) this.f65a.getObcWakeNoSenseCharge35R()};
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return new byte[35];
    }

    public int getOilLevel() {
        String str = C3;
        LogUtil.verbose(str, "getOilLevel");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager != null) {
            try {
                int oilLevelR = carVendorEngineManager.getOilLevelR();
                LogUtil.verbose(str, "getOilLevel" + oilLevelR);
                return oilLevelR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.Y1;
    }

    public int getOilPointFlag() {
        String str = C3;
        LogUtil.verbose(str, "getOilPointFlag");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int oilPointFlag = carVendorEnergyManager.getOilPointFlag();
            LogUtil.verbose(str, "getOilPointFlag=" + oilPointFlag);
            return oilPointFlag;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperationMode() {
        String str = C3;
        LogUtil.verbose(str, "getOperationMode");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int operationMode = carVendorEnergyManager.getOperationMode();
            LogUtil.verbose(str, "getOperationMode=" + operationMode);
            return operationMode;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperationModeNormal() {
        int operationModeNormalR;
        String str = C3;
        LogUtil.verbose(str, " getOperationModeNormal.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                operationModeNormalR = carVendorEnergyManager.getOperationModeNormalR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                operationModeNormalR = -1;
            }
            LogUtil.verbose(str, " getOperationModeNormal.out=" + operationModeNormalR);
            return operationModeNormalR;
        }
        LogUtil.verbose(str, " getOperationModeNormal.null == mCarVendorEnergyManager");
        operationModeNormalR = -1;
        LogUtil.verbose(str, " getOperationModeNormal.out=" + operationModeNormalR);
        return operationModeNormalR;
    }

    public int getPorducePowerWhenLowPower() {
        String str = C3;
        LogUtil.verbose(str, "getPorducePowerWhenLowPower");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int producePowerWhenLowPowerR = carVendorChargingManager.getProducePowerWhenLowPowerR();
                LogUtil.verbose(str, "getProducePowerWhenLowPowerR=" + producePowerWhenLowPowerR);
                return producePowerWhenLowPowerR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.h0;
    }

    public int getPositionLightDisplayFeedCallback() {
        int positionLightDisplayFeedCallbackR;
        String str = C3;
        LogUtil.verbose(str, " getPositionLightDisplayFeedCallback.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                positionLightDisplayFeedCallbackR = carVendorLightManager.getPositionLightDisplayFeedCallbackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                positionLightDisplayFeedCallbackR = -1;
            }
            LogUtil.verbose(str, " getPositionLightDisplayFeedCallback.out=" + positionLightDisplayFeedCallbackR);
            return positionLightDisplayFeedCallbackR;
        }
        LogUtil.verbose(str, " getPositionLightDisplayFeedCallback == null");
        positionLightDisplayFeedCallbackR = -1;
        LogUtil.verbose(str, " getPositionLightDisplayFeedCallback.out=" + positionLightDisplayFeedCallbackR);
        return positionLightDisplayFeedCallbackR;
    }

    public int getPowerGenerationState() {
        String str = C3;
        LogUtil.verbose(str, "getPowerGenerationState");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int powerGenerationState = carVendorEnergyManager.getPowerGenerationState();
            LogUtil.verbose(str, "getPowerGenerationState=" + powerGenerationState);
            return powerGenerationState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPowerGenerationValue() {
        String str = C3;
        LogUtil.verbose(str, "getPowerGenerationValue");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int powerGenerationValue = carVendorEnergyManager.getPowerGenerationValue();
            LogUtil.verbose(str, "getPowerGenerationValue=" + powerGenerationValue);
            return powerGenerationValue;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPowerLevel() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862927, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(C3, "getPowerLevel prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPreSaleModelState() {
        String str = C3;
        LogUtil.verbose(str, "getPreSaleModelState");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int preSaleModelStateR = carVendorEnergyManager.getPreSaleModelStateR();
            LogUtil.verbose(str, "getPreSaleModelStateR=" + preSaleModelStateR);
            return preSaleModelStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int[] getPreferentialtariffSection() {
        return null;
    }

    public int getReadyIndicatorLightState() {
        String str = C3;
        LogUtil.verbose(str, "Service getReadyIndicatorLightState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int readyIndicatorLightStateR = carVendorCarbodyManager.getReadyIndicatorLightStateR();
            LogUtil.verbose(str, "getReadyIndicatorLightStateR=" + readyIndicatorLightStateR);
            return readyIndicatorLightStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getRealAutoVIN() {
        String str = C3;
        LogUtil.verbose(str, "Service getRealAutoVIN");
        if (this.f66b == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            sb.append((char) this.f66b.getRealAutoVinOneR());
            sb.append((char) this.f66b.getRealAutoVinTwoR());
            sb.append((char) this.f66b.getRealAutoVinThreeR());
            sb.append((char) this.f66b.getRealAutoVinFourR());
            sb.append((char) this.f66b.getRealAutoVinFiveR());
            sb.append((char) this.f66b.getRealAutoVinSixR());
            sb.append((char) this.f66b.getRealAutoVinSevenR());
            sb.append((char) this.f66b.getRealAutoVinEightR());
            sb.append((char) this.f66b.getRealAutoVinNineR());
            sb.append((char) this.f66b.getRealAutoVinTenR());
            sb.append((char) this.f66b.getRealAutoVinElevenR());
            sb.append((char) this.f66b.getRealAutoVinTwelveR());
            sb.append((char) this.f66b.getRealAutoVinThirteenR());
            sb.append((char) this.f66b.getRealAutoVinFourteenR());
            sb.append((char) this.f66b.getRealAutoVinFifteenR());
            sb.append((char) this.f66b.getRealAutoVinSixteenR());
            sb.append((char) this.f66b.getRealAutoVinSeventeenR());
            LogUtil.verbose(str, "Service getRealAutoVIN" + sb.toString());
            return sb.toString();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getRearFogLightSwitchState() {
        int rearFogLightSwitchStateR;
        String str = C3;
        LogUtil.verbose(str, " getRearFogLightSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rearFogLightSwitchStateR = carVendorLightManager.getRearFogLightSwitchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFogLightSwitchStateR = -1;
            }
            LogUtil.verbose(str, " getRearFogLightSwitchState.out service =" + rearFogLightSwitchStateR);
            return rearFogLightSwitchStateR;
        }
        LogUtil.verbose(str, " getRearFogLightSwitchState.null");
        rearFogLightSwitchStateR = -1;
        LogUtil.verbose(str, " getRearFogLightSwitchState.out service =" + rearFogLightSwitchStateR);
        return rearFogLightSwitchStateR;
    }

    public int getRearLampLightState() {
        int lightRearPosition;
        String str = C3;
        LogUtil.verbose(str, " getRearLampLightState.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                lightRearPosition = carVendorCarbodyManager.getLightRearPosition();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lightRearPosition = -1;
            }
        } else {
            lightRearPosition = -1;
        }
        LogUtil.verbose(str, " getRearLampLightState.out = " + lightRearPosition);
        return lightRearPosition;
    }

    public int getRearWindowblindInitState() {
        String str = C3;
        LogUtil.verbose(str, "Service getRearWindowblindInitState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int rearWindowblindInitStateNewR = carVendorCarbodyManager.getRearWindowblindInitStateNewR();
            LogUtil.verbose(str, "getRearWindowblindInitStateNewR=" + rearWindowblindInitStateNewR);
            return rearWindowblindInitStateNewR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearWindowblindPosition() {
        String str = C3;
        LogUtil.verbose(str, "Service getRearWindowblindPosition");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int rearWindowblindPositionR = carVendorCarbodyManager.getRearWindowblindPositionR();
            LogUtil.verbose(str, "getRearWindowblindPositionR=" + rearWindowblindPositionR);
            return rearWindowblindPositionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearWindowblindState() {
        String str = C3;
        LogUtil.verbose(str, "Service getRearWindowblindState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int rearWindowblindStateR = carVendorCarbodyManager.getRearWindowblindStateR();
            LogUtil.verbose(str, "getRearWindowblindStateR=" + rearWindowblindStateR);
            return rearWindowblindStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearviewMirrorSupport() {
        String str = C3;
        LogUtil.verbose(str, "getRearviewMirrorSupport");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int rearviewMirrorSupport = carVendorCarbodyManager.getRearviewMirrorSupport();
            LogUtil.verbose(str, "getRearviewMirrorSupport=" + rearviewMirrorSupport);
            return rearviewMirrorSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getRearviewMirrorXLeft() {
        String str = C3;
        LogUtil.verbose(str, "getRearviewMirrorXLeft");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorXLeft = carVendorCarbodyManager.getRearviewMirrorXLeft();
            LogUtil.verbose(str, "getRearviewMirrorXLeft=" + rearviewMirrorXLeft);
            return rearviewMirrorXLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getRearviewMirrorXRight() {
        String str = C3;
        LogUtil.verbose(str, "getRearviewMirrorXRight");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorXRight = carVendorCarbodyManager.getRearviewMirrorXRight();
            LogUtil.verbose(str, "getRearviewMirrorXRight=" + rearviewMirrorXRight);
            return rearviewMirrorXRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getRearviewMirrorYLeft() {
        String str = C3;
        LogUtil.verbose(str, "getRearviewMirrorYLeft");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorYLeft = carVendorCarbodyManager.getRearviewMirrorYLeft();
            LogUtil.verbose(str, "getRearviewMirrorYLeft=" + rearviewMirrorYLeft);
            return rearviewMirrorYLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getRearviewMirrorYRight() {
        String str = C3;
        LogUtil.verbose(str, "getRearviewMirrorYRight");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorYRight = carVendorCarbodyManager.getRearviewMirrorYRight();
            LogUtil.verbose(str, "getRearviewMirrorYRight=" + rearviewMirrorYRight);
            return rearviewMirrorYRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getRestoreSeatConfig() {
        int restoreSeatConfigR;
        String str = C3;
        LogUtil.verbose(str, " getRestoreSeatConfig.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                restoreSeatConfigR = carVendorCarbodyManager.getRestoreSeatConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                restoreSeatConfigR = -1;
            }
            LogUtil.verbose(str, " getRestoreSeatConfig.out service =" + restoreSeatConfigR);
            return restoreSeatConfigR;
        }
        LogUtil.verbose(str, " getRestoreSeatConfig.null");
        restoreSeatConfigR = -1;
        LogUtil.verbose(str, " getRestoreSeatConfig.out service =" + restoreSeatConfigR);
        return restoreSeatConfigR;
    }

    public int getReversingLightState() {
        String str = C3;
        LogUtil.verbose(str, "Service getReversingLightState");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int reversingLightState = carVendorLightManager.getReversingLightState();
            LogUtil.verbose(str, "getReversingLightState=" + reversingLightState);
            return reversingLightState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRightCornerLightStatus() {
        int rightCornerLightStatusR;
        String str = C3;
        LogUtil.verbose(str, " getRightCornerLightStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightCornerLightStatusR = carVendorLightManager.getRightCornerLightStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightCornerLightStatusR = -1;
            }
            LogUtil.verbose(str, " getRightCornerLightStatus.out=" + rightCornerLightStatusR);
            return rightCornerLightStatusR;
        }
        LogUtil.verbose(str, " getRightCornerLightStatus == null");
        rightCornerLightStatusR = -1;
        LogUtil.verbose(str, " getRightCornerLightStatus.out=" + rightCornerLightStatusR);
        return rightCornerLightStatusR;
    }

    public int getRightDaytimeLightState() {
        int rightDaytimeLightStateR;
        String str = C3;
        LogUtil.verbose(str, " getRightDaytimeLightState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightDaytimeLightStateR = carVendorLightManager.getRightDaytimeLightStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightDaytimeLightStateR = -1;
            }
            LogUtil.verbose(str, " getRightDaytimeLightState.out=" + rightDaytimeLightStateR);
            return rightDaytimeLightStateR;
        }
        LogUtil.verbose(str, " getRightDaytimeLightState == null");
        rightDaytimeLightStateR = -1;
        LogUtil.verbose(str, " getRightDaytimeLightState.out=" + rightDaytimeLightStateR);
        return rightDaytimeLightStateR;
    }

    public int getRightDaytimeRunLampState() {
        int rightDaytimeRunLampStateR;
        String str = C3;
        LogUtil.verbose(str, " getRightDaytimeRunLampState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightDaytimeRunLampStateR = carVendorLightManager.getRightDaytimeRunLampStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightDaytimeRunLampStateR = -1;
            }
            LogUtil.verbose(str, " getRightDaytimeRunLampState.out=" + rightDaytimeRunLampStateR);
            return rightDaytimeRunLampStateR;
        }
        LogUtil.verbose(str, " getRightDaytimeRunLampState.null == mCarVendorLightManager");
        rightDaytimeRunLampStateR = -1;
        LogUtil.verbose(str, " getRightDaytimeRunLampState.out=" + rightDaytimeRunLampStateR);
        return rightDaytimeRunLampStateR;
    }

    public int getRightFrontPositionSwlStatus() {
        int rightFrontPositionSWLStatusR;
        String str = C3;
        LogUtil.verbose(str, " getRightFrontPositionSwlStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightFrontPositionSWLStatusR = carVendorLightManager.getRightFrontPositionSWLStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightFrontPositionSWLStatusR = -1;
            }
            LogUtil.verbose(str, " getRightFrontPositionSwlStatus.out=" + rightFrontPositionSWLStatusR);
            return rightFrontPositionSWLStatusR;
        }
        LogUtil.verbose(str, " getRightFrontPositionSwlStatus == null");
        rightFrontPositionSWLStatusR = -1;
        LogUtil.verbose(str, " getRightFrontPositionSwlStatus.out=" + rightFrontPositionSWLStatusR);
        return rightFrontPositionSWLStatusR;
    }

    public int getRightFrontReadingLampStatus() {
        int rightFrontReadingLampStatusR;
        String str = C3;
        LogUtil.verbose(str, " getRightFrontReadingLampStatus.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightFrontReadingLampStatusR = carVendorLightManager.getRightFrontReadingLampStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightFrontReadingLampStatusR = -1;
            }
            LogUtil.verbose(str, " getRightFrontReadingLampStatus.out=" + rightFrontReadingLampStatusR);
            return rightFrontReadingLampStatusR;
        }
        LogUtil.verbose(str, " getRightFrontReadingLampStatus == null");
        rightFrontReadingLampStatusR = -1;
        LogUtil.verbose(str, " getRightFrontReadingLampStatus.out=" + rightFrontReadingLampStatusR);
        return rightFrontReadingLampStatusR;
    }

    public int getRightMiddleRowRLightRotary() {
        int rightMiddleRowRLightRotaryR;
        String str = C3;
        LogUtil.verbose(str, " getRightMiddleRowRLightRotary.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightMiddleRowRLightRotaryR = carVendorLightManager.getRightMiddleRowRLightRotaryR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightMiddleRowRLightRotaryR = -1;
            }
            LogUtil.verbose(str, " getRightMiddleRowRLightRotary.out=" + rightMiddleRowRLightRotaryR);
            return rightMiddleRowRLightRotaryR;
        }
        LogUtil.verbose(str, " getRightMiddleRowRLightRotary == null");
        rightMiddleRowRLightRotaryR = -1;
        LogUtil.verbose(str, " getRightMiddleRowRLightRotary.out=" + rightMiddleRowRLightRotaryR);
        return rightMiddleRowRLightRotaryR;
    }

    public int getRightMiddleRowRLightSwitch() {
        int rightMiddleRowRLightSwitchR;
        String str = C3;
        LogUtil.verbose(str, " getRightMiddleRowRLightSwitch.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightMiddleRowRLightSwitchR = carVendorLightManager.getRightMiddleRowRLightSwitchR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightMiddleRowRLightSwitchR = -1;
            }
            LogUtil.verbose(str, " getRightMiddleRowRLightSwitch.out=" + rightMiddleRowRLightSwitchR);
            return rightMiddleRowRLightSwitchR;
        }
        LogUtil.verbose(str, " getRightMiddleRowRLightSwitch == null");
        rightMiddleRowRLightSwitchR = -1;
        LogUtil.verbose(str, " getRightMiddleRowRLightSwitch.out=" + rightMiddleRowRLightSwitchR);
        return rightMiddleRowRLightSwitchR;
    }

    public int getRightRearRLightSwitch() {
        int rightRearRLightSwitchR;
        String str = C3;
        LogUtil.verbose(str, " getRightRearRLightSwitch.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                rightRearRLightSwitchR = carVendorLightManager.getRightRearRLightSwitchR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightRearRLightSwitchR = -1;
            }
            LogUtil.verbose(str, " getRightRearRLightSwitch.out=" + rightRearRLightSwitchR);
            return rightRearRLightSwitchR;
        }
        LogUtil.verbose(str, " getRightRearRLightSwitch == null");
        rightRearRLightSwitchR = -1;
        LogUtil.verbose(str, " getRightRearRLightSwitch.out=" + rightRearRLightSwitchR);
        return rightRearRLightSwitchR;
    }

    public int getRightSeatRailsRow3FeedbackPosition() {
        int rightSeatRailsRow3FeedbackPositionR;
        String str = C3;
        LogUtil.verbose(str, " getRightSeatRailsRow3FeedbackPosition.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                rightSeatRailsRow3FeedbackPositionR = carVendorCarbodyManager.getRightSeatRailsRow3FeedbackPositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightSeatRailsRow3FeedbackPositionR = -1;
            }
            LogUtil.verbose(str, " getRightSeatRailsRow3FeedbackPosition.out service =" + rightSeatRailsRow3FeedbackPositionR);
            return rightSeatRailsRow3FeedbackPositionR;
        }
        LogUtil.verbose(str, " getRightSeatRailsRow3FeedbackPosition.null");
        rightSeatRailsRow3FeedbackPositionR = -1;
        LogUtil.verbose(str, " getRightSeatRailsRow3FeedbackPosition.out service =" + rightSeatRailsRow3FeedbackPositionR);
        return rightSeatRailsRow3FeedbackPositionR;
    }

    public int getRightSlideDoorAction() {
        String str = C3;
        LogUtil.verbose(str, "Service getRightSlideDoorAction");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862986, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportFragrance prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightSlideDoorPercent() {
        String str = C3;
        LogUtil.verbose(str, "Service getRightSlideDoorPercent");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int rightSlideDoorPercentR = carVendorCarbodyManager.getRightSlideDoorPercentR();
            LogUtil.verbose(str, "getRightSlideDoorPercentR=" + rightSlideDoorPercentR);
            return rightSlideDoorPercentR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRightSlideDoorState() {
        String str = C3;
        LogUtil.verbose(str, "Service getRightSlideDoorState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862984, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightSlideDoorState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightStrutMorotRunningState() {
        String str = C3;
        LogUtil.verbose(str, "Service getRightStrutMorotRunningState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862979, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightStrutMorotRunningStateR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRoadSurfaceMode() {
        String str = C3;
        LogUtil.verbose(str, "getRoadSurfaceMode");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            int roadSurfaceMode = carVendorEnergyManager.getRoadSurfaceMode();
            LogUtil.verbose(str, "getRoadSurfaceMode=" + roadSurfaceMode);
            return roadSurfaceMode;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRoadSurfaceModeNormal() {
        int roadSurfaceModeNormalR;
        String str = C3;
        LogUtil.verbose(str, " getRoadSurfaceModeNormal.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                roadSurfaceModeNormalR = carVendorEnergyManager.getRoadSurfaceModeNormalR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                roadSurfaceModeNormalR = -1;
            }
            LogUtil.verbose(str, " getRoadSurfaceModeNormal.out=" + roadSurfaceModeNormalR);
            return roadSurfaceModeNormalR;
        }
        LogUtil.verbose(str, " getRoadSurfaceModeNormal.null == mCarVendorEnergyManager");
        roadSurfaceModeNormalR = -1;
        LogUtil.verbose(str, " getRoadSurfaceModeNormal.out=" + roadSurfaceModeNormalR);
        return roadSurfaceModeNormalR;
    }

    public int getRollBenchModeStatus() {
        int rollBenchModeStatusR;
        String str = C3;
        LogUtil.verbose(str, " getRollBenchModeStatus.in service");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                rollBenchModeStatusR = carVendorEnergyManager.getRollBenchModeStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rollBenchModeStatusR = -1;
            }
            LogUtil.verbose(str, " getRollBenchModeStatus.out service =" + rollBenchModeStatusR);
            return rollBenchModeStatusR;
        }
        LogUtil.verbose(str, " getRollBenchModeStatus.null");
        rollBenchModeStatusR = -1;
        LogUtil.verbose(str, " getRollBenchModeStatus.out service =" + rollBenchModeStatusR);
        return rollBenchModeStatusR;
    }

    public int getSeatLargeBedModeConfig() {
        int seatLargeBedModeConfigR;
        String str = C3;
        LogUtil.verbose(str, " getSeatLargeBedModeConfig.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatLargeBedModeConfigR = carVendorCarbodyManager.getSeatLargeBedModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatLargeBedModeConfigR = -1;
            }
            LogUtil.verbose(str, " getSeatLargeBedModeConfig.out service =" + seatLargeBedModeConfigR);
            return seatLargeBedModeConfigR;
        }
        LogUtil.verbose(str, " getSeatLargeBedModeConfig.null");
        seatLargeBedModeConfigR = -1;
        LogUtil.verbose(str, " getSeatLargeBedModeConfig.out service =" + seatLargeBedModeConfigR);
        return seatLargeBedModeConfigR;
    }

    public int getSeatLumbarSupportConfigCoPilotLeft(int i) {
        int seatLumbarSupportConfigCoPilotLeftR;
        String str = C3;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotLeft.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatLumbarSupportConfigCoPilotLeftR = carVendorCarbodyManager.getSeatLumbarSupportConfigCoPilotLeftR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatLumbarSupportConfigCoPilotLeftR = -1;
            }
            LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotLeft.out=" + seatLumbarSupportConfigCoPilotLeftR);
            return seatLumbarSupportConfigCoPilotLeftR;
        }
        LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotLeft == null");
        seatLumbarSupportConfigCoPilotLeftR = -1;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotLeft.out=" + seatLumbarSupportConfigCoPilotLeftR);
        return seatLumbarSupportConfigCoPilotLeftR;
    }

    public int getSeatLumbarSupportConfigCoPilotRight(int i) {
        int seatLumbarSupportConfigCoPilotRightR;
        String str = C3;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotRight.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatLumbarSupportConfigCoPilotRightR = carVendorCarbodyManager.getSeatLumbarSupportConfigCoPilotRightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatLumbarSupportConfigCoPilotRightR = -1;
            }
            LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotRight.out=" + seatLumbarSupportConfigCoPilotRightR);
            return seatLumbarSupportConfigCoPilotRightR;
        }
        LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotRight == null");
        seatLumbarSupportConfigCoPilotRightR = -1;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigCoPilotRight.out=" + seatLumbarSupportConfigCoPilotRightR);
        return seatLumbarSupportConfigCoPilotRightR;
    }

    public int getSeatLumbarSupportConfigMainLeft(int i) {
        int seatLumbarSupportConfigMainLeftR;
        String str = C3;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigMainLeft.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatLumbarSupportConfigMainLeftR = carVendorCarbodyManager.getSeatLumbarSupportConfigMainLeftR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatLumbarSupportConfigMainLeftR = -1;
            }
            LogUtil.verbose(str, " getSeatLumbarSupportConfigMainLeft.out=" + seatLumbarSupportConfigMainLeftR);
            return seatLumbarSupportConfigMainLeftR;
        }
        LogUtil.verbose(str, " getSeatLumbarSupportConfigMainLeft == null");
        seatLumbarSupportConfigMainLeftR = -1;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigMainLeft.out=" + seatLumbarSupportConfigMainLeftR);
        return seatLumbarSupportConfigMainLeftR;
    }

    public int getSeatLumbarSupportConfigMainRight(int i) {
        int seatLumbarSupportConfigMainRightR;
        String str = C3;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigMainRight.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatLumbarSupportConfigMainRightR = carVendorCarbodyManager.getSeatLumbarSupportConfigMainRightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatLumbarSupportConfigMainRightR = -1;
            }
            LogUtil.verbose(str, " getSeatLumbarSupportConfigMainRight.out=" + seatLumbarSupportConfigMainRightR);
            return seatLumbarSupportConfigMainRightR;
        }
        LogUtil.verbose(str, " getSeatLumbarSupportConfigMainRight == null");
        seatLumbarSupportConfigMainRightR = -1;
        LogUtil.verbose(str, " getSeatLumbarSupportConfigMainRight.out=" + seatLumbarSupportConfigMainRightR);
        return seatLumbarSupportConfigMainRightR;
    }

    public int getSeatModeCurFeedbackStatus() {
        int seatModeCurFeedbackStatusR;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeCurFeedbackStatus.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeCurFeedbackStatusR = carVendorCarbodyManager.getSeatModeCurFeedbackStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeCurFeedbackStatusR = -1;
            }
            LogUtil.verbose(str, " getSeatModeCurFeedbackStatus.out service =" + seatModeCurFeedbackStatusR);
            return seatModeCurFeedbackStatusR;
        }
        LogUtil.verbose(str, " getSeatModeCurFeedbackStatus.null");
        seatModeCurFeedbackStatusR = -1;
        LogUtil.verbose(str, " getSeatModeCurFeedbackStatus.out service =" + seatModeCurFeedbackStatusR);
        return seatModeCurFeedbackStatusR;
    }

    public int getSeatModeCurFeedbackStatus2() {
        int seatModeCurFeedbackStatus2R;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeCurFeedbackStatus2.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeCurFeedbackStatus2R = carVendorCarbodyManager.getSeatModeCurFeedbackStatus2R();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeCurFeedbackStatus2R = -1;
            }
            LogUtil.verbose(str, " getSeatModeCurFeedbackStatus2.out service =" + seatModeCurFeedbackStatus2R);
            return seatModeCurFeedbackStatus2R;
        }
        LogUtil.verbose(str, " getSeatModeCurFeedbackStatus2.null");
        seatModeCurFeedbackStatus2R = -1;
        LogUtil.verbose(str, " getSeatModeCurFeedbackStatus2.out service =" + seatModeCurFeedbackStatus2R);
        return seatModeCurFeedbackStatus2R;
    }

    public int getSeatModeFailFeedbackStatus() {
        int seatModeFailFeedbackStatusR;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeFailFeedbackStatus.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeFailFeedbackStatusR = carVendorCarbodyManager.getSeatModeFailFeedbackStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeFailFeedbackStatusR = -1;
            }
            LogUtil.verbose(str, " getSeatModeFailFeedbackStatus.out service =" + seatModeFailFeedbackStatusR);
            return seatModeFailFeedbackStatusR;
        }
        LogUtil.verbose(str, " getSeatModeFailFeedbackStatus.null");
        seatModeFailFeedbackStatusR = -1;
        LogUtil.verbose(str, " getSeatModeFailFeedbackStatus.out service =" + seatModeFailFeedbackStatusR);
        return seatModeFailFeedbackStatusR;
    }

    public int getSeatModeFailFeedbackStatus2() {
        int seatModeFailFeedbackStatus2R;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeFailFeedbackStatus2.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeFailFeedbackStatus2R = carVendorCarbodyManager.getSeatModeFailFeedbackStatus2R();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeFailFeedbackStatus2R = -1;
            }
            LogUtil.verbose(str, " getSeatModeFailFeedbackStatus2.out service =" + seatModeFailFeedbackStatus2R);
            return seatModeFailFeedbackStatus2R;
        }
        LogUtil.verbose(str, " getSeatModeFailFeedbackStatus2.null");
        seatModeFailFeedbackStatus2R = -1;
        LogUtil.verbose(str, " getSeatModeFailFeedbackStatus2.out service =" + seatModeFailFeedbackStatus2R);
        return seatModeFailFeedbackStatus2R;
    }

    public int getSeatModeSuccessFeedbackStatus() {
        int seatModeSuccessFeedbackStatusR;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeSuccessFeedbackStatusR = carVendorCarbodyManager.getSeatModeSuccessFeedbackStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeSuccessFeedbackStatusR = -1;
            }
            LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus.out service =" + seatModeSuccessFeedbackStatusR);
            return seatModeSuccessFeedbackStatusR;
        }
        LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus.null");
        seatModeSuccessFeedbackStatusR = -1;
        LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus.out service =" + seatModeSuccessFeedbackStatusR);
        return seatModeSuccessFeedbackStatusR;
    }

    public int getSeatModeSuccessFeedbackStatus2() {
        int seatModeSuccessFeedbackStatus2R;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus2.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeSuccessFeedbackStatus2R = carVendorCarbodyManager.getSeatModeSuccessFeedbackStatus2R();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeSuccessFeedbackStatus2R = -1;
            }
            LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus2.out service =" + seatModeSuccessFeedbackStatus2R);
            return seatModeSuccessFeedbackStatus2R;
        }
        LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus2.null");
        seatModeSuccessFeedbackStatus2R = -1;
        LogUtil.verbose(str, " getSeatModeSuccessFeedbackStatus2.out service =" + seatModeSuccessFeedbackStatus2R);
        return seatModeSuccessFeedbackStatus2R;
    }

    public int getSeatModeWakeupFeedbackState() {
        int seatModeWakeupFeedbackStateR;
        String str = C3;
        LogUtil.verbose(str, " getSeatModeWakeupFeedbackState.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                seatModeWakeupFeedbackStateR = carVendorCarbodyManager.getSeatModeWakeupFeedbackStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatModeWakeupFeedbackStateR = -1;
            }
            LogUtil.verbose(str, " getSeatModeWakeupFeedbackState.out=" + seatModeWakeupFeedbackStateR);
            return seatModeWakeupFeedbackStateR;
        }
        LogUtil.verbose(str, " getSeatModeWakeupFeedbackState == null");
        seatModeWakeupFeedbackStateR = -1;
        LogUtil.verbose(str, " getSeatModeWakeupFeedbackState.out=" + seatModeWakeupFeedbackStateR);
        return seatModeWakeupFeedbackStateR;
    }

    public int getShiftMode() {
        int shiftModeR;
        String str = C3;
        LogUtil.verbose(str, " getShiftMode.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                shiftModeR = carVendorCarbodyManager.getShiftModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                shiftModeR = -1;
            }
            LogUtil.verbose(str, " getShiftMode.out=" + shiftModeR);
            return shiftModeR;
        }
        LogUtil.verbose(str, " getShiftMode == null");
        shiftModeR = -1;
        LogUtil.verbose(str, " getShiftMode.out=" + shiftModeR);
        return shiftModeR;
    }

    public int getSituPowerGenerationState() {
        String str = C3;
        LogUtil.verbose(str, "getSituPowerGenerationState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856266, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSituPowerGenerationState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSituPowerGenerationSupport() {
        String str = C3;
        LogUtil.verbose(str, "getSituPowerGenerationSupport is execute");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int situPowerGenerationSupportR = carVendorChargingManager.getSituPowerGenerationSupportR();
            LogUtil.verbose(str, "getSituPowerGenerationSupportR=" + situPowerGenerationSupportR);
            return situPowerGenerationSupportR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSlideRailMotorElecPercent() {
        int slideRailMotorElecPercentR;
        String str = C3;
        LogUtil.verbose(str, " getSlideRailMotorElecPercent.in");
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager != null) {
            try {
                slideRailMotorElecPercentR = carVendorEngineManager.getSlideRailMotorElecPercentR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                slideRailMotorElecPercentR = -1;
            }
            LogUtil.verbose(str, " getSlideRailMotorElecPercent.out=" + slideRailMotorElecPercentR);
            return slideRailMotorElecPercentR;
        }
        LogUtil.verbose(str, " getSlideRailMotorElecPercent.null == mCarVendorEngineManager");
        slideRailMotorElecPercentR = -1;
        LogUtil.verbose(str, " getSlideRailMotorElecPercent.out=" + slideRailMotorElecPercentR);
        return slideRailMotorElecPercentR;
    }

    public int getSmartVoiceLimit() {
        int smartVoiceLimit;
        String str = C3;
        LogUtil.verbose(str, " getSmartVoiceLimit.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                smartVoiceLimit = carVendorCarbodyManager.getSmartVoiceLimit();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                smartVoiceLimit = -1;
            }
        } else {
            smartVoiceLimit = -1;
        }
        LogUtil.verbose(str, " getSmartVoiceLimit.out = " + smartVoiceLimit);
        return smartVoiceLimit;
    }

    public int getSocTarget() {
        return 0;
    }

    public float getSteeringWheelValue(int i) {
        int steeringWheelValueAngel;
        float f;
        String str = C3;
        LogUtil.verbose(str, "getSteeringWheelValue");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            if (i == 1) {
                steeringWheelValueAngel = carVendorCarbodyManager.getSteeringWheelValueAngel();
            } else {
                if (i != 2) {
                    f = -1.0f;
                    LogUtil.verbose(str, "getSteeringWheelValue=" + f);
                    return f;
                }
                steeringWheelValueAngel = carVendorCarbodyManager.getSteeringWheelValueSpeed();
            }
            f = steeringWheelValueAngel;
            LogUtil.verbose(str, "getSteeringWheelValue=" + f);
            return f;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSteeringWheelY() {
        String str = C3;
        LogUtil.verbose(str, "getSteeringWheelY");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            float steeringWheelY = carVendorCarbodyManager.getSteeringWheelY();
            LogUtil.verbose(str, "getSteeringWheelY=" + steeringWheelY);
            return steeringWheelY;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSteeringWheelZ() {
        String str = C3;
        LogUtil.verbose(str, "getSteeringWheelZ");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0.0f;
        }
        try {
            float steeringWheelZ = carVendorCarbodyManager.getSteeringWheelZ();
            LogUtil.verbose(str, "getSteeringWheelZ=" + steeringWheelZ);
            return steeringWheelZ;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getStopLightState() {
        String str = C3;
        LogUtil.verbose(str, "Service getStopLightState");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int stopLightStateR = carVendorLightManager.getStopLightStateR();
            LogUtil.verbose(str, "getStopLightStateR=" + stopLightStateR);
            return stopLightStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSunroofCloseNotice() {
        String str = C3;
        LogUtil.verbose(str, "Service getSunroofCloseNotice");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int sunroofCloseNoticeR = carVendorCarbodyManager.getSunroofCloseNoticeR();
            LogUtil.verbose(str, "getSunroofCloseNoticeR=" + sunroofCloseNoticeR);
            return sunroofCloseNoticeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSunroofInitState() {
        String str = C3;
        LogUtil.verbose(str, "Service getSunroofInitState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int sunroofInitStateR = carVendorCarbodyManager.getSunroofInitStateR();
            LogUtil.verbose(str, "getSunroofInitStateR=" + sunroofInitStateR);
            return sunroofInitStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSunroofPosition() {
        String str = C3;
        LogUtil.verbose(str, "Service getSunroofPosition");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int sunroofPositionR = carVendorCarbodyManager.getSunroofPositionR();
            LogUtil.verbose(str, "getSunroofPositionR=" + sunroofPositionR);
            return sunroofPositionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSunroofState() {
        String str = C3;
        LogUtil.verbose(str, "service getSunroofState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int sunRoofStateR = carVendorCarbodyManager.getSunRoofStateR();
            LogUtil.verbose(str, "getSunRoofStateR=" + sunRoofStateR);
            return sunRoofStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSunroofWindowblindPosition() {
        String str = C3;
        LogUtil.verbose(str, "Service getSunroofWindowblindPosition");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int sunroofWindowblindPositionR = carVendorCarbodyManager.getSunroofWindowblindPositionR();
            LogUtil.verbose(str, "getSunroofWindowblindPositionR=" + sunroofWindowblindPositionR);
            return sunroofWindowblindPositionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportAFS() {
        return 0;
    }

    public int getSupportLampSingnal() {
        return 0;
    }

    public int getSystemConfig() {
        int systemConfigR;
        String str = C3;
        LogUtil.verbose(str, " getSystemConfig.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                systemConfigR = carVendorCarbodyManager.getSystemConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                systemConfigR = -1;
            }
            LogUtil.verbose(str, " getSystemConfig.out=" + systemConfigR);
            return systemConfigR;
        }
        LogUtil.verbose(str, " getSystemConfig.null == mCarVendorCarbodyManager");
        systemConfigR = -1;
        LogUtil.verbose(str, " getSystemConfig.out=" + systemConfigR);
        return systemConfigR;
    }

    public int getSystemUndervoltageStatus() {
        int systemUndervoltageStatusR;
        String str = C3;
        LogUtil.verbose(str, " getSystemUndervoltageStatus.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                systemUndervoltageStatusR = carVendorTyreManager.getSystemUndervoltageStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                systemUndervoltageStatusR = -1;
            }
            LogUtil.verbose(str, " getSystemUndervoltageStatus.out=" + systemUndervoltageStatusR);
            return systemUndervoltageStatusR;
        }
        LogUtil.verbose(str, " getSystemUndervoltageStatus.null == mCarVendorTyreManager");
        systemUndervoltageStatusR = -1;
        LogUtil.verbose(str, " getSystemUndervoltageStatus.out=" + systemUndervoltageStatusR);
        return systemUndervoltageStatusR;
    }

    public int getTailDoorTypeConfig() {
        int tailDoorTypeConfigR;
        String str = C3;
        LogUtil.verbose(str, " getTailDoorTypeConfig.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                tailDoorTypeConfigR = carVendorCarbodyManager.getTailDoorTypeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tailDoorTypeConfigR = -1;
            }
            LogUtil.verbose(str, " getTailDoorTypeConfig.out=" + tailDoorTypeConfigR);
            return tailDoorTypeConfigR;
        }
        LogUtil.verbose(str, " getTailDoorTypeConfig == null");
        tailDoorTypeConfigR = -1;
        LogUtil.verbose(str, " getTailDoorTypeConfig.out=" + tailDoorTypeConfigR);
        return tailDoorTypeConfigR;
    }

    public int getThemeColorFeatureFlag() {
        int themeColorFeatureFlagR;
        String str = C3;
        LogUtil.verbose(str, " getThemeColorFeatureFlag.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                themeColorFeatureFlagR = carVendorLightManager.getThemeColorFeatureFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                themeColorFeatureFlagR = -1;
            }
            LogUtil.verbose(str, " getThemeColorFeatureFlag.out service =" + themeColorFeatureFlagR);
            return themeColorFeatureFlagR;
        }
        LogUtil.verbose(str, " getThemeColorFeatureFlag.null");
        themeColorFeatureFlagR = -1;
        LogUtil.verbose(str, " getThemeColorFeatureFlag.out service =" + themeColorFeatureFlagR);
        return themeColorFeatureFlagR;
    }

    public int getThemeColorFeedBack() {
        int themeColorFeedBackR;
        String str = C3;
        LogUtil.verbose(str, " getThemeColorFeedBack.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                themeColorFeedBackR = carVendorLightManager.getThemeColorFeedBackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                themeColorFeedBackR = -1;
            }
            LogUtil.verbose(str, " getThemeColorFeedBack.out service =" + themeColorFeedBackR);
            return themeColorFeedBackR;
        }
        LogUtil.verbose(str, " getThemeColorFeedBack.null");
        themeColorFeedBackR = -1;
        LogUtil.verbose(str, " getThemeColorFeedBack.out service =" + themeColorFeedBackR);
        return themeColorFeedBackR;
    }

    public int getThemeColorSwitchState() {
        int themeColorSwitchStateR;
        String str = C3;
        LogUtil.verbose(str, " getThemeColorSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                themeColorSwitchStateR = carVendorLightManager.getThemeColorSwitchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                themeColorSwitchStateR = -1;
            }
            LogUtil.verbose(str, " getThemeColorSwitchState.out service =" + themeColorSwitchStateR);
            return themeColorSwitchStateR;
        }
        LogUtil.verbose(str, " getThemeColorSwitchState.null");
        themeColorSwitchStateR = -1;
        LogUtil.verbose(str, " getThemeColorSwitchState.out service =" + themeColorSwitchStateR);
        return themeColorSwitchStateR;
    }

    public int getThirdRowSeatsExecutionResults() {
        int thirdRowSeatsExecutionResultsR;
        String str = C3;
        LogUtil.verbose(str, " getThirdRowSeatsExecutionResults.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                thirdRowSeatsExecutionResultsR = carVendorCarbodyManager.getThirdRowSeatsExecutionResultsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                thirdRowSeatsExecutionResultsR = -1;
            }
            LogUtil.verbose(str, " getThirdRowSeatsExecutionResults.out service =" + thirdRowSeatsExecutionResultsR);
            return thirdRowSeatsExecutionResultsR;
        }
        LogUtil.verbose(str, " getThirdRowSeatsExecutionResults.null");
        thirdRowSeatsExecutionResultsR = -1;
        LogUtil.verbose(str, " getThirdRowSeatsExecutionResults.out service =" + thirdRowSeatsExecutionResultsR);
        return thirdRowSeatsExecutionResultsR;
    }

    public int getTimerCycleFri() {
        return 0;
    }

    public int getTimerCycleMon() {
        return 0;
    }

    public int getTimerCycleSat() {
        return 0;
    }

    public int getTimerCycleSun() {
        return 0;
    }

    public int getTimerCycleThu() {
        return 0;
    }

    public int getTimerCycleTue() {
        return 0;
    }

    public int getTimerCycleWed() {
        return 0;
    }

    public int getTimerSwitch() {
        return 0;
    }

    public int getTimerType() {
        return 0;
    }

    public int getTimerUnitHour() {
        return 0;
    }

    public int getTimerUnitMinite() {
        return 0;
    }

    public int getTravellingSchedulePeriod() {
        return 0;
    }

    public int[] getTravellingSchedulePeriods(int i) {
        return null;
    }

    public int[] getTravellingScheduleTime() {
        return null;
    }

    public int getTripAcPercent() {
        int tripAcPercentR;
        String str = C3;
        LogUtil.verbose(str, " getTripAcPercent.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripAcPercentR = carVendorEnergyManager.getTripAcPercentR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripAcPercentR = -1;
            }
            LogUtil.verbose(str, " getTripAcPercent.out=" + tripAcPercentR);
            return tripAcPercentR;
        }
        LogUtil.verbose(str, " getTripAcPercent.null == mCarVendorEnergyManager");
        tripAcPercentR = -1;
        LogUtil.verbose(str, " getTripAcPercent.out=" + tripAcPercentR);
        return tripAcPercentR;
    }

    public int getTripAcPercent50km() {
        int tripAcPercent50kmR;
        String str = C3;
        LogUtil.verbose(str, " getTripAcPercent50km.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripAcPercent50kmR = carVendorEnergyManager.getTripAcPercent50kmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripAcPercent50kmR = -1;
            }
            LogUtil.verbose(str, " getTripAcPercent50km.out=" + tripAcPercent50kmR);
            return tripAcPercent50kmR;
        }
        LogUtil.verbose(str, " getTripAcPercent50km.null == mCarVendorEnergyManager");
        tripAcPercent50kmR = -1;
        LogUtil.verbose(str, " getTripAcPercent50km.out=" + tripAcPercent50kmR);
        return tripAcPercent50kmR;
    }

    public int getTripDrivePercent() {
        int tripDrivePercentR;
        String str = C3;
        LogUtil.verbose(str, " getTripDrivePercent.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripDrivePercentR = carVendorEnergyManager.getTripDrivePercentR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripDrivePercentR = -1;
            }
            LogUtil.verbose(str, " getTripDrivePercent.out=" + tripDrivePercentR);
            return tripDrivePercentR;
        }
        LogUtil.verbose(str, " getTripDrivePercent.null == mCarVendorEnergyManager");
        tripDrivePercentR = -1;
        LogUtil.verbose(str, " getTripDrivePercent.out=" + tripDrivePercentR);
        return tripDrivePercentR;
    }

    public int getTripDrivePercent50km() {
        int tripDrivePercent50kmR;
        String str = C3;
        LogUtil.verbose(str, " getTripDrivePercent50km.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripDrivePercent50kmR = carVendorEnergyManager.getTripDrivePercent50kmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripDrivePercent50kmR = -1;
            }
            LogUtil.verbose(str, " getTripDrivePercent50km.out=" + tripDrivePercent50kmR);
            return tripDrivePercent50kmR;
        }
        LogUtil.verbose(str, " getTripDrivePercent50km.null == mCarVendorEnergyManager");
        tripDrivePercent50kmR = -1;
        LogUtil.verbose(str, " getTripDrivePercent50km.out=" + tripDrivePercent50kmR);
        return tripDrivePercent50kmR;
    }

    public int getTripElecAppliancePercent() {
        int tripElecAppliancePercentR;
        String str = C3;
        LogUtil.verbose(str, " getTripElecAppliancePercent.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripElecAppliancePercentR = carVendorEnergyManager.getTripElecAppliancePercentR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripElecAppliancePercentR = -1;
            }
            LogUtil.verbose(str, " getTripElecAppliancePercent.out=" + tripElecAppliancePercentR);
            return tripElecAppliancePercentR;
        }
        LogUtil.verbose(str, " getTripElecAppliancePercent.null == mCarVendorEnergyManager");
        tripElecAppliancePercentR = -1;
        LogUtil.verbose(str, " getTripElecAppliancePercent.out=" + tripElecAppliancePercentR);
        return tripElecAppliancePercentR;
    }

    public int getTripElecAppliancePercent50km() {
        int tripElecAppliancePercent50kmR;
        String str = C3;
        LogUtil.verbose(str, " getTripElecAppliancePercent50km.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripElecAppliancePercent50kmR = carVendorEnergyManager.getTripElecAppliancePercent50kmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripElecAppliancePercent50kmR = -1;
            }
            LogUtil.verbose(str, " getTripElecAppliancePercent50km.out=" + tripElecAppliancePercent50kmR);
            return tripElecAppliancePercent50kmR;
        }
        LogUtil.verbose(str, " getTripElecAppliancePercent50km.null");
        tripElecAppliancePercent50kmR = -1;
        LogUtil.verbose(str, " getTripElecAppliancePercent50km.out=" + tripElecAppliancePercent50kmR);
        return tripElecAppliancePercent50kmR;
    }

    public float getTripElecConAvg() {
        float tripElecConAvgR;
        String str = C3;
        LogUtil.verbose(str, " getTripElecConAvg.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripElecConAvgR = carVendorEnergyManager.getTripElecConAvgR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripElecConAvgR = -1.0f;
            }
            LogUtil.verbose(str, " getTripElecConAvg.out=" + tripElecConAvgR);
            return tripElecConAvgR;
        }
        LogUtil.verbose(str, " getTripElecConAvg.null == mCarVendorEnergyManager");
        tripElecConAvgR = -1.0f;
        LogUtil.verbose(str, " getTripElecConAvg.out=" + tripElecConAvgR);
        return tripElecConAvgR;
    }

    public float getTripEnergyConAvg() {
        float tripEnergyConAvgR;
        String str = C3;
        LogUtil.verbose(str, " getTripEnergyConAvg.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripEnergyConAvgR = carVendorEnergyManager.getTripEnergyConAvgR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripEnergyConAvgR = -1.0f;
            }
            LogUtil.verbose(str, " getTripEnergyConAvg.out=" + tripEnergyConAvgR);
            return tripEnergyConAvgR;
        }
        LogUtil.verbose(str, " getTripEnergyConAvg.null == mCarVendorEnergyManager");
        tripEnergyConAvgR = -1.0f;
        LogUtil.verbose(str, " getTripEnergyConAvg.out=" + tripEnergyConAvgR);
        return tripEnergyConAvgR;
    }

    public float getTripLast50kmElectricConAvg() {
        float tripLast50kmElectricConAvgR;
        String str = C3;
        LogUtil.verbose(str, " getTripLast50kmElectricConAvg.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripLast50kmElectricConAvgR = carVendorEnergyManager.getTripLast50kmElectricConAvgR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripLast50kmElectricConAvgR = -1.0f;
            }
            LogUtil.verbose(str, " getTripLast50kmElectricConAvg.out=" + tripLast50kmElectricConAvgR);
            return tripLast50kmElectricConAvgR;
        }
        LogUtil.verbose(str, " getTripLast50kmElectricConAvg.null == mCarVendorEnergyManager");
        tripLast50kmElectricConAvgR = -1.0f;
        LogUtil.verbose(str, " getTripLast50kmElectricConAvg.out=" + tripLast50kmElectricConAvgR);
        return tripLast50kmElectricConAvgR;
    }

    public float getTripLast50kmFuelConAvg() {
        float tripLast50kmFuelConAvgR;
        String str = C3;
        LogUtil.verbose(str, " getTripLast50kmFuelConAvg.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripLast50kmFuelConAvgR = carVendorEnergyManager.getTripLast50kmFuelConAvgR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripLast50kmFuelConAvgR = -1.0f;
            }
            LogUtil.verbose(str, " getTripLast50kmFuelConAvg.out=" + tripLast50kmFuelConAvgR);
            return tripLast50kmFuelConAvgR;
        }
        LogUtil.verbose(str, " getTripLast50kmFuelConAvg.null == mCarVendorEnergyManager");
        tripLast50kmFuelConAvgR = -1.0f;
        LogUtil.verbose(str, " getTripLast50kmFuelConAvg.out=" + tripLast50kmFuelConAvgR);
        return tripLast50kmFuelConAvgR;
    }

    public float getTripLast50kmOilConAvg() {
        float tripLast50kmOilConAvgR;
        String str = C3;
        LogUtil.verbose(str, " getTripLast50kmOilConAvg.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripLast50kmOilConAvgR = carVendorEnergyManager.getTripLast50kmOilConAvgR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripLast50kmOilConAvgR = -1.0f;
            }
            LogUtil.verbose(str, " getTripLast50kmOilConAvg.out=" + tripLast50kmOilConAvgR);
            return tripLast50kmOilConAvgR;
        }
        LogUtil.verbose(str, " getTripLast50kmOilConAvg.null == mCarVendorEnergyManager");
        tripLast50kmOilConAvgR = -1.0f;
        LogUtil.verbose(str, " getTripLast50kmOilConAvg.out=" + tripLast50kmOilConAvgR);
        return tripLast50kmOilConAvgR;
    }

    public float getTripOilConAvg() {
        float tripOilConAvgR;
        String str = C3;
        LogUtil.verbose(str, " getTripOilConAvg.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripOilConAvgR = carVendorEnergyManager.getTripOilConAvgR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripOilConAvgR = -1.0f;
            }
            LogUtil.verbose(str, " getTripOilConAvg.out=" + tripOilConAvgR);
            return tripOilConAvgR;
        }
        LogUtil.verbose(str, " getTripOilConAvg.null == mCarVendorEnergyManager");
        tripOilConAvgR = -1.0f;
        LogUtil.verbose(str, " getTripOilConAvg.out=" + tripOilConAvgR);
        return tripOilConAvgR;
    }

    public int getTripOtherPercent() {
        int tripOtherPercentR;
        String str = C3;
        LogUtil.verbose(str, " getTripOtherPercent.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripOtherPercentR = carVendorEnergyManager.getTripOtherPercentR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripOtherPercentR = -1;
            }
            LogUtil.verbose(str, " getTripOtherPercent.out=" + tripOtherPercentR);
            return tripOtherPercentR;
        }
        LogUtil.verbose(str, " getTripOtherPercent.null == mCarVendorEnergyManager");
        tripOtherPercentR = -1;
        LogUtil.verbose(str, " getTripOtherPercent.out=" + tripOtherPercentR);
        return tripOtherPercentR;
    }

    public int getTripOtherPercent50km() {
        int tripOtherPercent50kmR;
        String str = C3;
        LogUtil.verbose(str, " getTripOtherPercent50km.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripOtherPercent50kmR = carVendorEnergyManager.getTripOtherPercent50kmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripOtherPercent50kmR = -1;
            }
            LogUtil.verbose(str, " getTripOtherPercent50km.out=" + tripOtherPercent50kmR);
            return tripOtherPercent50kmR;
        }
        LogUtil.verbose(str, " getTripOtherPercent50km.null == mCarVendorEnergyManager");
        tripOtherPercent50kmR = -1;
        LogUtil.verbose(str, " getTripOtherPercent50km.out=" + tripOtherPercent50kmR);
        return tripOtherPercent50kmR;
    }

    public int getTripPointFlag() {
        int tripPointFlagR;
        String str = C3;
        LogUtil.verbose(str, " getTripPointFlag.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                tripPointFlagR = carVendorEnergyManager.getTripPointFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tripPointFlagR = -1;
            }
            LogUtil.verbose(str, " getTripPointFlag.out=" + tripPointFlagR);
            return tripPointFlagR;
        }
        LogUtil.verbose(str, " getTripPointFlag.null == mCarVendorEnergyManager");
        tripPointFlagR = -1;
        LogUtil.verbose(str, " getTripPointFlag.out=" + tripPointFlagR);
        return tripPointFlagR;
    }

    public int getTurnLightFlashState() {
        String str = C3;
        LogUtil.verbose(str, "getTurnLightFlashState");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int turnLightFlashStateR = carVendorLightManager.getTurnLightFlashStateR();
            LogUtil.verbose(str, "getTurnLightFlashStateR=" + turnLightFlashStateR);
            return turnLightFlashStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTurnLightState() {
        String str = C3;
        LogUtil.verbose(str, "Service getTurnLightState");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            int turnLightState = carVendorLightManager.getTurnLightState();
            LogUtil.verbose(str, "getTurnLightState=" + turnLightState);
            return turnLightState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTyreAirLeakState(int i) {
        String str = C3;
        LogUtil.verbose(str, "getTyreAirLeakState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyreAirLeakState = carVendorTyreManager.getTyreAirLeakState();
                LogUtil.verbose(str, "getTyreAirLeakState" + tyreAirLeakState);
                return tyreAirLeakState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.b2;
    }

    public int getTyreArea() {
        int tyreAreaR;
        String str = C3;
        LogUtil.verbose(str, " getTyreArea.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreAreaR = carVendorTyreManager.getTyreAreaR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreAreaR = -1;
            }
        } else {
            tyreAreaR = -1;
        }
        LogUtil.verbose(str, " getTyreArea.out = " + tyreAreaR);
        return tyreAreaR;
    }

    public int getTyreBatteryState() {
        String str = C3;
        LogUtil.verbose(str, "getTyreBatteryState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyreBatteryState = carVendorTyreManager.getTyreBatteryState();
                LogUtil.verbose(str, "getTyreBatteryState" + tyreBatteryState);
                return tyreBatteryState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.c2;
    }

    public float getTyreBatteryValue(int i) {
        String str = C3;
        LogUtil.verbose(str, "getTyreBatteryValue");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                float tyreBatteryValue = carVendorTyreManager.getTyreBatteryValue();
                LogUtil.verbose(str, "getTyreBatteryValue=" + tyreBatteryValue);
                return tyreBatteryValue;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.d2;
    }

    public int getTyreBodyType() {
        int tyreBodyTypeR;
        String str = C3;
        LogUtil.verbose(str, " getTyreBodyType.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreBodyTypeR = carVendorTyreManager.getTyreBodyTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreBodyTypeR = -1;
            }
        } else {
            tyreBodyTypeR = -1;
        }
        LogUtil.verbose(str, " getTyreBodyType.out = " + tyreBodyTypeR);
        return tyreBodyTypeR;
    }

    public int getTyreColorByTypeLb() {
        int tyreColorByTypeLbR;
        String str = C3;
        LogUtil.verbose(str, " getTyreColorByTypeLb.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreColorByTypeLbR = carVendorTyreManager.getTyreColorByTypeLbR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreColorByTypeLbR = -1;
            }
            LogUtil.verbose(str, " getTyreColorByTypeLb.out=" + tyreColorByTypeLbR);
            return tyreColorByTypeLbR;
        }
        LogUtil.verbose(str, " getTyreColorByTypeLb.null == mCarVendorTyreManager");
        tyreColorByTypeLbR = -1;
        LogUtil.verbose(str, " getTyreColorByTypeLb.out=" + tyreColorByTypeLbR);
        return tyreColorByTypeLbR;
    }

    public int getTyreColorByTypeLf() {
        int tyreColorByTypeLfR;
        String str = C3;
        LogUtil.verbose(str, " getTyreColorByTypeLf.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreColorByTypeLfR = carVendorTyreManager.getTyreColorByTypeLfR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreColorByTypeLfR = -1;
            }
            LogUtil.verbose(str, " getTyreColorByTypeLf.out=" + tyreColorByTypeLfR);
            return tyreColorByTypeLfR;
        }
        LogUtil.verbose(str, " getTyreColorByTypeLf.null == mCarVendorTyreManager");
        tyreColorByTypeLfR = -1;
        LogUtil.verbose(str, " getTyreColorByTypeLf.out=" + tyreColorByTypeLfR);
        return tyreColorByTypeLfR;
    }

    public int getTyreColorByTypeRb() {
        int tyreColorByTypeRbR;
        String str = C3;
        LogUtil.verbose(str, " getTyreColorByTypeRb.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreColorByTypeRbR = carVendorTyreManager.getTyreColorByTypeRbR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreColorByTypeRbR = -1;
            }
            LogUtil.verbose(str, " getTyreColorByTypeRb.out=" + tyreColorByTypeRbR);
            return tyreColorByTypeRbR;
        }
        LogUtil.verbose(str, " getTyreColorByTypeRb.null == mCarVendorTyreManager");
        tyreColorByTypeRbR = -1;
        LogUtil.verbose(str, " getTyreColorByTypeRb.out=" + tyreColorByTypeRbR);
        return tyreColorByTypeRbR;
    }

    public int getTyreColorByTypeRf() {
        int tyreColorByTypeRfR;
        String str = C3;
        LogUtil.verbose(str, " getTyreColorByTypeRf.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreColorByTypeRfR = carVendorTyreManager.getTyreColorByTypeRfR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreColorByTypeRfR = -1;
            }
            LogUtil.verbose(str, " getTyreColorByTypeRf.out=" + tyreColorByTypeRfR);
            return tyreColorByTypeRfR;
        }
        LogUtil.verbose(str, " getTyreColorByTypeRf.null == mCarVendorTyreManager");
        tyreColorByTypeRfR = -1;
        LogUtil.verbose(str, " getTyreColorByTypeRf.out=" + tyreColorByTypeRfR);
        return tyreColorByTypeRfR;
    }

    public int getTyrePressureState(int i) {
        String str = C3;
        LogUtil.verbose(str, "getTyrePressureState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyrePressureState = carVendorTyreManager.getTyrePressureState();
                LogUtil.verbose(str, "getTyrePressureState" + tyrePressureState);
                return tyrePressureState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.e2;
    }

    public int getTyrePressureSysFailHint() {
        int tyrePressureSysFailHintR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureSysFailHint.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureSysFailHintR = carVendorTyreManager.getTyrePressureSysFailHintR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureSysFailHintR = -1;
            }
        } else {
            tyrePressureSysFailHintR = -1;
        }
        LogUtil.verbose(str, " getTyrePressureSysFailHint.out = " + tyrePressureSysFailHintR);
        return tyrePressureSysFailHintR;
    }

    public int getTyrePressureValue(int i) {
        String str = C3;
        LogUtil.verbose(str, "getTyrePressureValue");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyrePressureValue = carVendorTyreManager.getTyrePressureValue();
                LogUtil.verbose(str, "getTyrePressureValue" + tyrePressureValue);
                return tyrePressureValue;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.f2;
    }

    public float getTyrePressureValueByTypeLb() {
        float tyrePressureValueByTypeLbR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureValueByTypeLb.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureValueByTypeLbR = carVendorTyreManager.getTyrePressureValueByTypeLbR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureValueByTypeLbR = -1.0f;
            }
            LogUtil.verbose(str, " getTyrePressureValueByTypeLb.out=" + tyrePressureValueByTypeLbR);
            return tyrePressureValueByTypeLbR;
        }
        LogUtil.verbose(str, " getTyrePressureValueByTypeLb.null == mCarVendorTyreManager");
        tyrePressureValueByTypeLbR = -1.0f;
        LogUtil.verbose(str, " getTyrePressureValueByTypeLb.out=" + tyrePressureValueByTypeLbR);
        return tyrePressureValueByTypeLbR;
    }

    public float getTyrePressureValueByTypeLf() {
        float tyrePressureValueByTypeLfR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureValueByTypeLf.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureValueByTypeLfR = carVendorTyreManager.getTyrePressureValueByTypeLfR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureValueByTypeLfR = -1.0f;
            }
            LogUtil.verbose(str, " getTyrePressureValueByTypeLf.out=" + tyrePressureValueByTypeLfR);
            return tyrePressureValueByTypeLfR;
        }
        LogUtil.verbose(str, " getTyrePressureValueByTypeLf.null == mCarVendorTyreManager");
        tyrePressureValueByTypeLfR = -1.0f;
        LogUtil.verbose(str, " getTyrePressureValueByTypeLf.out=" + tyrePressureValueByTypeLfR);
        return tyrePressureValueByTypeLfR;
    }

    public float getTyrePressureValueByTypeRb() {
        float tyrePressureValueByTypeRbR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureValueByTypeRb.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureValueByTypeRbR = carVendorTyreManager.getTyrePressureValueByTypeRbR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureValueByTypeRbR = -1.0f;
            }
            LogUtil.verbose(str, " getTyrePressureValueByTypeRb.out=" + tyrePressureValueByTypeRbR);
            return tyrePressureValueByTypeRbR;
        }
        LogUtil.verbose(str, " getTyrePressureValueByTypeRb.null == mCarVendorTyreManager");
        tyrePressureValueByTypeRbR = -1.0f;
        LogUtil.verbose(str, " getTyrePressureValueByTypeRb.out=" + tyrePressureValueByTypeRbR);
        return tyrePressureValueByTypeRbR;
    }

    public float getTyrePressureValueByTypeRf() {
        float tyrePressureValueByTypeRfR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureValueByTypeRf.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureValueByTypeRfR = carVendorTyreManager.getTyrePressureValueByTypeRfR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureValueByTypeRfR = -1.0f;
            }
            LogUtil.verbose(str, " getTyrePressureValueByTypeRf.out=" + tyrePressureValueByTypeRfR);
            return tyrePressureValueByTypeRfR;
        }
        LogUtil.verbose(str, " getTyrePressureValueByTypeRf.null == mCarVendorTyreManager");
        tyrePressureValueByTypeRfR = -1.0f;
        LogUtil.verbose(str, " getTyrePressureValueByTypeRf.out=" + tyrePressureValueByTypeRfR);
        return tyrePressureValueByTypeRfR;
    }

    public int getTyrePressureWarnHint() {
        int tyrePressureWarnHintR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureWarnHint.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureWarnHintR = carVendorTyreManager.getTyrePressureWarnHintR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureWarnHintR = -1;
            }
        } else {
            tyrePressureWarnHintR = -1;
        }
        LogUtil.verbose(str, " getTyrePressureWarnHint.out = " + tyrePressureWarnHintR);
        return tyrePressureWarnHintR;
    }

    public int getTyrePressureWarnLeftFront() {
        int tyrePressureWarnLeftFrontR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureWarnLeftFrontR.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureWarnLeftFrontR = carVendorTyreManager.getTyrePressureWarnLeftFrontR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureWarnLeftFrontR = -1;
            }
        } else {
            tyrePressureWarnLeftFrontR = -1;
        }
        LogUtil.verbose(str, " getTyrePressureWarnLeftFrontR.out = " + tyrePressureWarnLeftFrontR);
        return tyrePressureWarnLeftFrontR;
    }

    public int getTyrePressureWarnLeftRear() {
        int tyrePressureWarnLeftRearR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureWarnLeftRear.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureWarnLeftRearR = carVendorTyreManager.getTyrePressureWarnLeftRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureWarnLeftRearR = -1;
            }
        } else {
            tyrePressureWarnLeftRearR = -1;
        }
        LogUtil.verbose(str, " getTyrePressureWarnLeftRear.out = " + tyrePressureWarnLeftRearR);
        return tyrePressureWarnLeftRearR;
    }

    public int getTyrePressureWarnRightFront() {
        int tyrePressureWarnRightFrontR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureWarnRightFront.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureWarnRightFrontR = carVendorTyreManager.getTyrePressureWarnRightFrontR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureWarnRightFrontR = -1;
            }
        } else {
            tyrePressureWarnRightFrontR = -1;
        }
        LogUtil.verbose(str, " getTyrePressureWarnRightFront.out = " + tyrePressureWarnRightFrontR);
        return tyrePressureWarnRightFrontR;
    }

    public int getTyrePressureWarnRightRear() {
        int tyrePressureWarnRightRearR;
        String str = C3;
        LogUtil.verbose(str, " getTyrePressureWarnRightRear.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyrePressureWarnRightRearR = carVendorTyreManager.getTyrePressureWarnRightRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyrePressureWarnRightRearR = -1;
            }
        } else {
            tyrePressureWarnRightRearR = -1;
        }
        LogUtil.verbose(str, " getTyrePressureWarnRightRear.out = " + tyrePressureWarnRightRearR);
        return tyrePressureWarnRightRearR;
    }

    public int getTyreSignalState(int i) {
        String str = C3;
        LogUtil.verbose(str, "getTyreSignalState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyreSignalState = carVendorTyreManager.getTyreSignalState();
                LogUtil.verbose(str, "getTyreSignalState" + tyreSignalState);
                return tyreSignalState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.g2;
    }

    public int getTyreSystemState() {
        String str = C3;
        LogUtil.verbose(str, "getTyreSystemState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyreSystemState = carVendorTyreManager.getTyreSystemState();
                LogUtil.verbose(str, "getTyreSystemState" + tyreSystemState);
                return tyreSystemState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.h2;
    }

    public int getTyreTemperatureState() {
        String str = C3;
        LogUtil.verbose(str, "getTyreTemperatureState");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                int tyreTemperatureState = carVendorTyreManager.getTyreTemperatureState();
                LogUtil.verbose(str, "getTyreTemperatureState" + tyreTemperatureState);
                return tyreTemperatureState;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.i2;
    }

    public int getTyreTemperatureValue() {
        int tyreTemperatureValueR;
        String str = C3;
        LogUtil.verbose(str, " getTyreTemperatureValue.in");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager != null) {
            try {
                tyreTemperatureValueR = carVendorTyreManager.getTyreTemperatureValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tyreTemperatureValueR = -1;
            }
            LogUtil.verbose(str, " getTyreTemperatureValue.out=" + tyreTemperatureValueR);
            return tyreTemperatureValueR;
        }
        LogUtil.verbose(str, " getTyreTemperatureValue == mCarVendorTyreManager");
        tyreTemperatureValueR = -1;
        LogUtil.verbose(str, " getTyreTemperatureValue.out=" + tyreTemperatureValueR);
        return tyreTemperatureValueR;
    }

    public int getTyreTurnState() {
        String str = C3;
        LogUtil.verbose(str, "getTyreBatteryValue");
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager == null) {
            return 0;
        }
        try {
            int tyreTurnState = carVendorTyreManager.getTyreTurnState();
            LogUtil.verbose(str, "getTyreTurnState=" + tyreTurnState);
            return tyreTurnState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getUsherLightSupport(int i) {
        return 0;
    }

    public int getV2gChargeStatus() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getV2gChargeStatus.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                i = carVendorChargingManager.getv2gChargeStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getV2gChargeStatus.out service =" + i);
            return i;
        }
        LogUtil.verbose(str, " getV2gChargeStatus.null");
        i = -1;
        LogUtil.verbose(str, " getV2gChargeStatus.out service =" + i);
        return i;
    }

    public int getV2gOutputPower() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getV2gOutputPower.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                i = carVendorChargingManager.getv2gOutputPowerR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getV2gOutputPower.out service =" + i);
            return i;
        }
        LogUtil.verbose(str, " getV2gOutputPower.null");
        i = -1;
        LogUtil.verbose(str, " getV2gOutputPower.out service =" + i);
        return i;
    }

    public int getV2gStatus() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getV2gStatus.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                i = carVendorChargingManager.getv2gStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getV2gStatus.out service =" + i);
            return i;
        }
        LogUtil.verbose(str, " getV2gStatus.null");
        i = -1;
        LogUtil.verbose(str, " getV2gStatus.out service =" + i);
        return i;
    }

    public float getVoltageMax() {
        String str = C3;
        LogUtil.verbose(str, "Service getVoltageMax");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0.0f;
        }
        try {
            float voltageMaxR = carVendorChargingManager.getVoltageMaxR();
            LogUtil.verbose(str, "getVoltageMaxR=" + voltageMaxR);
            return voltageMaxR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getVtovChargeBatteryLevelNum() {
        String str = C3;
        LogUtil.verbose(str, "Service getVtovChargeBatteryLevelNum");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int vtovChargeBatteryLevelNumR = carVendorChargingManager.getVtovChargeBatteryLevelNumR();
            LogUtil.verbose(str, "getVtovChargeBatteryLevelNumR=" + vtovChargeBatteryLevelNumR);
            return vtovChargeBatteryLevelNumR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getVtovChargeBatteryLevelNumLimit() {
        String str = C3;
        LogUtil.verbose(str, "Service getVtovChargeBatteryLevelNumLimit");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int vtovChargeBatteryLevelNumLimitR = carVendorChargingManager.getVtovChargeBatteryLevelNumLimitR();
            LogUtil.verbose(str, "getVtovChargeBatteryLevelNumLimitR=" + vtovChargeBatteryLevelNumLimitR);
            return vtovChargeBatteryLevelNumLimitR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getVtovDischargeConnectState() {
        String str = C3;
        LogUtil.verbose(str, "getVtovDischargeConnectState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int vtovDischargeConnectState = carVendorChargingManager.getVtovDischargeConnectState();
            LogUtil.verbose(str, "getVtovDischargeConnectState=" + vtovDischargeConnectState);
            return vtovDischargeConnectState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getVtovDischargeLimitVal() {
        String str = C3;
        LogUtil.verbose(str, "getVtovDischargeLimitVal");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int vtovDischargeLimitValR = carVendorChargingManager.getVtovDischargeLimitValR();
            LogUtil.verbose(str, "getVtovDischargeLimitValR=" + vtovDischargeLimitValR);
            return vtovDischargeLimitValR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getVtovDischargeLowPowerHint() {
        String str = C3;
        LogUtil.verbose(str, "getVtovDischargeLowPowerHint");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int vtovDischargeLowPowerHintR = carVendorChargingManager.getVtovDischargeLowPowerHintR();
                LogUtil.verbose(str, "getVtovDischargeLowPowerHintR=" + vtovDischargeLowPowerHintR);
                return vtovDischargeLowPowerHintR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.P1;
    }

    public int getVtovDischargeLowestVal() {
        String str = C3;
        LogUtil.verbose(str, "Service getVtovDischargeLowestVal");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int vtovDischargeLowestValR = carVendorChargingManager.getVtovDischargeLowestValR();
            LogUtil.verbose(str, "getVtovDischargeLowestValR=" + vtovDischargeLowestValR);
            return vtovDischargeLowestValR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getVtovDischargeQuantity() {
        String str = C3;
        LogUtil.verbose(str, "Service getVtovDischargeQuantity");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0.0f;
        }
        try {
            float vtovDischargeQuantityR = carVendorChargingManager.getVtovDischargeQuantityR();
            LogUtil.verbose(str, "getVtovDischargeQuantityR=" + vtovDischargeQuantityR);
            return vtovDischargeQuantityR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getVtovOutCarDischargeState() {
        return 0;
    }

    public int getWindowAntiPinchConfig() {
        String str = C3;
        LogUtil.verbose(str, "getWindowAntiPinchConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856209, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWindowAntiPinchConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWindowControlState(int i) {
        return 0;
    }

    public int getWindowControlStateLeftFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowControlStateLeftFront");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowControlStateLeftFrontR = carVendorCarbodyManager.getWindowControlStateLeftFrontR();
            LogUtil.verbose(str, "getWindowControlStateLeftFrontR=" + windowControlStateLeftFrontR);
            return windowControlStateLeftFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowControlStateLeftRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowControlStateLeftRear");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowControlStateLeftRearR = carVendorCarbodyManager.getWindowControlStateLeftRearR();
            LogUtil.verbose(str, "getWindowControlStateLeftRearR=" + windowControlStateLeftRearR);
            return windowControlStateLeftRearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowControlStateRightFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowControlStateRightFront");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowControlStateRightFrontR = carVendorCarbodyManager.getWindowControlStateRightFrontR();
            LogUtil.verbose(str, "getWindowControlStateRightFrontR=" + windowControlStateRightFrontR);
            return windowControlStateRightFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowControlStateRightRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowControlStateRightRear");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowControlStateRightRearR = carVendorCarbodyManager.getWindowControlStateRightRearR();
            LogUtil.verbose(str, "getWindowControlStateRightRearR=" + windowControlStateRightRearR);
            return windowControlStateRightRearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowInitStateLeftFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowInitStateLeftFront");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862954, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWindowInitStateLeftFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWindowInitStateLeftRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowInitStateLeftRear");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862955, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWindowInitStateLeftRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWindowInitStateRightFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowInitStateRightFront");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862956, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWindowInitStateRightFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWindowInitStateRightRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowInitStateRightRear");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557862957, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWindowInitStateRightRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWindowLockState(int i) {
        return 0;
    }

    public int getWindowOpenPercenMoonRoof() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercenMoonRoof");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentMoonR = carVendorCarbodyManager.getWindowOpenPercentMoonR();
            LogUtil.verbose(str, "getWindowOpenPercentMoonR=" + windowOpenPercentMoonR);
            return windowOpenPercentMoonR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowOpenPercentLeftFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercentLeftFront");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentLeftFrontR = carVendorCarbodyManager.getWindowOpenPercentLeftFrontR();
            LogUtil.verbose(str, "getWindowOpenPercentLeftFrontR=" + windowOpenPercentLeftFrontR);
            return windowOpenPercentLeftFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowOpenPercentLeftRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercentLeftRear");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentLeftRearR = carVendorCarbodyManager.getWindowOpenPercentLeftRearR();
            LogUtil.verbose(str, "getWindowOpenPercentLeftRearR=" + windowOpenPercentLeftRearR);
            return windowOpenPercentLeftRearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowOpenPercentPanel() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercentPanel");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentSunR = carVendorCarbodyManager.getWindowOpenPercentSunR();
            LogUtil.verbose(str, "getWindowOpenPercentSunR=" + windowOpenPercentSunR);
            return windowOpenPercentSunR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowOpenPercentRearPanel() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercentRearPanel");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentRearPanelR = carVendorCarbodyManager.getWindowOpenPercentRearPanelR();
            LogUtil.verbose(str, "getWindowOpenPercentRearPanelR=" + windowOpenPercentRearPanelR);
            return windowOpenPercentRearPanelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowOpenPercentRightFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercentRightFront");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentRightFrontR = carVendorCarbodyManager.getWindowOpenPercentRightFrontR();
            LogUtil.verbose(str, "getWindowOpenPercentRightFrontR=" + windowOpenPercentRightFrontR);
            return windowOpenPercentRightFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowOpenPercentRightRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowOpenPercentRightRear");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowOpenPercentRightRearR = carVendorCarbodyManager.getWindowOpenPercentRightRearR();
            LogUtil.verbose(str, "getWindowOpenPercentRightRearR=" + windowOpenPercentRightRearR);
            return windowOpenPercentRightRearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowPercentageControlConfig() {
        int windowPercentageControlConfigR;
        String str = C3;
        LogUtil.verbose(str, " getWindowPercentageControlConfig.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                windowPercentageControlConfigR = carVendorCarbodyManager.getWindowPercentageControlConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                windowPercentageControlConfigR = -1;
            }
            LogUtil.verbose(str, " getWindowPercentageControlConfig.out=" + windowPercentageControlConfigR);
            return windowPercentageControlConfigR;
        }
        LogUtil.verbose(str, " getWindowPercentageControlConfig == null");
        windowPercentageControlConfigR = -1;
        LogUtil.verbose(str, " getWindowPercentageControlConfig.out=" + windowPercentageControlConfigR);
        return windowPercentageControlConfigR;
    }

    public int getWindowPermitState() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowPermitState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowPermitStateR = carVendorCarbodyManager.getWindowPermitStateR();
            LogUtil.verbose(str, "getWindowPermitStateR=" + windowPermitStateR);
            return windowPermitStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowStateLeftFront() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int windowStateLeftFront = carVendorCarbodyManager.getWindowStateLeftFront();
                LogUtil.verbose(C3, "getWindowStateLeftFront=" + windowStateLeftFront);
                return windowStateLeftFront;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.F2;
    }

    public int getWindowStateLeftRear() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int windowStateLeftRear = carVendorCarbodyManager.getWindowStateLeftRear();
                LogUtil.verbose(C3, "getWindowStateLeftRear=" + windowStateLeftRear);
                return windowStateLeftRear;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.G2;
    }

    public int getWindowStateMoonRoof() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowStateMoonRoofR = carVendorCarbodyManager.getWindowStateMoonRoofR();
            LogUtil.verbose(C3, "getWindowStateMoonRoofR=" + windowStateMoonRoofR);
            return windowStateMoonRoofR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowStateRightFront() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int windowStateRightFront = carVendorCarbodyManager.getWindowStateRightFront();
                LogUtil.verbose(C3, "getWindowStateRightFront=" + windowStateRightFront);
                return windowStateRightFront;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.H2;
    }

    public int getWindowStateRightRear() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                int windowStateRightRear = carVendorCarbodyManager.getWindowStateRightRear();
                LogUtil.verbose(C3, "getWindowStateRightRear=" + windowStateRightRear);
                return windowStateRightRear;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.I2;
    }

    @Deprecated
    public int getWindowStateSunShade() {
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowStateSunshadeR = carVendorCarbodyManager.getWindowStateSunshadeR();
            LogUtil.verbose(C3, "getWindowStateSunshadeR=" + windowStateSunshadeR);
            return windowStateSunshadeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStateLeftFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateLeftFront");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateFL = carVendorCarbodyManager.getWindowThermalProtectionStateFL();
            LogUtil.verbose(str, "getWindowThermalProtectionStateFL=" + windowThermalProtectionStateFL);
            return windowThermalProtectionStateFL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStateLeftRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateLeftRear");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateRL = carVendorCarbodyManager.getWindowThermalProtectionStateRL();
            LogUtil.verbose(str, "getWindowThermalProtectionStateRL=" + windowThermalProtectionStateRL);
            return windowThermalProtectionStateRL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStateMoonRoof() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateMoonRoof");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateSkylight = carVendorCarbodyManager.getWindowThermalProtectionStateSkylight();
            LogUtil.verbose(str, "getWindowThermalProtectionStateSkylight=" + windowThermalProtectionStateSkylight);
            return windowThermalProtectionStateSkylight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStatePanel() {
        LogUtil.verbose(C3, "Service getWindowThermalProtectionStatePanel");
        return 0;
    }

    public int getWindowThermalProtectionStateRearPanel() {
        LogUtil.verbose(C3, "Service getWindowThermalProtectionStateRearPanel");
        return 0;
    }

    public int getWindowThermalProtectionStateRearSunshadePanel() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateRearSunshadePanel");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateRearSunshade = carVendorCarbodyManager.getWindowThermalProtectionStateRearSunshade();
            LogUtil.verbose(str, "getWindowThermalProtectionStateRearSunshade=" + windowThermalProtectionStateRearSunshade);
            return windowThermalProtectionStateRearSunshade;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStateRightFront() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateRightFront");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateFR = carVendorCarbodyManager.getWindowThermalProtectionStateFR();
            LogUtil.verbose(str, "getWindowThermalProtectionStateFR=" + windowThermalProtectionStateFR);
            return windowThermalProtectionStateFR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStateRightRear() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateRightRear");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateRR = carVendorCarbodyManager.getWindowThermalProtectionStateRR();
            LogUtil.verbose(str, "getWindowThermalProtectionStateRR=" + windowThermalProtectionStateRR);
            return windowThermalProtectionStateRR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowThermalProtectionStateSunshadePanel() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowThermalProtectionStateSunshadePanel");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowThermalProtectionStateFrontSunshade = carVendorCarbodyManager.getWindowThermalProtectionStateFrontSunshade();
            LogUtil.verbose(str, "getWindowThermalProtectionStateFrontSunshade=" + windowThermalProtectionStateFrontSunshade);
            return windowThermalProtectionStateFrontSunshade;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowblindInitState() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowblindInitState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowblindInitStateR = carVendorCarbodyManager.getWindowblindInitStateR();
            LogUtil.verbose(str, "getWindowblindInitStateR=" + windowblindInitStateR);
            return windowblindInitStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWindowblindState() {
        String str = C3;
        LogUtil.verbose(str, "Service getWindowblindState");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            int windowStateSunshadeR = carVendorCarbodyManager.getWindowStateSunshadeR();
            LogUtil.verbose(str, "getWindowStateSunshadeR=" + windowStateSunshadeR);
            return windowStateSunshadeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWirelessChargingFRState() {
        int wirelessChargingFrStateR;
        String str = C3;
        LogUtil.verbose(str, " getWirelessChargingFRState.in");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                wirelessChargingFrStateR = carVendorChargingManager.getWirelessChargingFrStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wirelessChargingFrStateR = -1;
            }
        } else {
            wirelessChargingFrStateR = -1;
        }
        LogUtil.verbose(str, " getWirelessChargingFRState.out = " + wirelessChargingFrStateR);
        return wirelessChargingFrStateR;
    }

    public int getWirelessChargingFRSwitchState() {
        String str = C3;
        LogUtil.verbose(str, " getWirelessChargingFRSwitchState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856326, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWirelessChargingFRSwitchState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWirelessChargingPowerFLState() {
        int wirelessChargingPowerFlStateR;
        String str = C3;
        LogUtil.verbose(str, " getWirelessChargingPowerFLState.in");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                wirelessChargingPowerFlStateR = carVendorChargingManager.getWirelessChargingPowerFlStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wirelessChargingPowerFlStateR = -1;
            }
        } else {
            wirelessChargingPowerFlStateR = -1;
        }
        LogUtil.verbose(str, " getWirelessChargingPowerFLState.out = " + wirelessChargingPowerFlStateR);
        return wirelessChargingPowerFlStateR;
    }

    public int getWirelessChargingPowerFRState() {
        int wirelessChargingPowerFrStateR;
        String str = C3;
        LogUtil.verbose(str, " getWirelessChargingPowerFRState.in");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                wirelessChargingPowerFrStateR = carVendorChargingManager.getWirelessChargingPowerFrStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wirelessChargingPowerFrStateR = -1;
            }
        } else {
            wirelessChargingPowerFrStateR = -1;
        }
        LogUtil.verbose(str, " getWirelessChargingPowerFRState.out = " + wirelessChargingPowerFrStateR);
        return wirelessChargingPowerFrStateR;
    }

    public int getWirelessChargingState() {
        String str = C3;
        LogUtil.verbose(str, "getWirelessChargingState");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                int wirelessChargingStateR = carVendorChargingManager.getWirelessChargingStateR();
                LogUtil.verbose(str, "getWirelessChargingStateR=" + wirelessChargingStateR);
                return wirelessChargingStateR;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return this.i0;
    }

    public int getWirelessChargingSwitchState() {
        String str = C3;
        LogUtil.verbose(str, "getWirelessChargingSwitchState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557855776, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWirelessChargingSwitchState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public float getYEnergyCon() {
        float yEnergyConR;
        String str = C3;
        LogUtil.verbose(str, " getYEnergyCon.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                yEnergyConR = carVendorEnergyManager.getYEnergyConR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                yEnergyConR = -1.0f;
            }
            LogUtil.verbose(str, " getYEnergyCon.out=" + yEnergyConR);
            return yEnergyConR;
        }
        LogUtil.verbose(str, " getYEnergyCon.null == mCarVendorEnergyManager");
        yEnergyConR = -1.0f;
        LogUtil.verbose(str, " getYEnergyCon.out=" + yEnergyConR);
        return yEnergyConR;
    }

    public int getYEnergyConLimit() {
        int yEnergyConLimitR;
        String str = C3;
        LogUtil.verbose(str, " getYEnergyConLimit.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                yEnergyConLimitR = carVendorEnergyManager.getYEnergyConLimitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                yEnergyConLimitR = -1;
            }
            LogUtil.verbose(str, " getYEnergyConLimit.out=" + yEnergyConLimitR);
            return yEnergyConLimitR;
        }
        LogUtil.verbose(str, " getYEnergyConLimit.null == mCarVendorEnergyManager");
        yEnergyConLimitR = -1;
        LogUtil.verbose(str, " getYEnergyConLimit.out=" + yEnergyConLimitR);
        return yEnergyConLimitR;
    }

    public int getgetPortCapStateLeftR() {
        String str = C3;
        LogUtil.verbose(str, "getgetPortCapStateLeftR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int i = carVendorChargingManager.getgetPortCapStateLeftR();
            LogUtil.verbose(str, "getgetPortCapStateLeftR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getgetPortCapStateRightR() {
        String str = C3;
        LogUtil.verbose(str, "getgetPortCapStateRightR");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            int i = carVendorChargingManager.getgetPortCapStateRightR();
            LogUtil.verbose(str, "getgetPortCapStateRightR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getiTacExitTag() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getiTacExitTag.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                i = carVendorEnergyManager.getiTacExitTagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getiTacExitTag.out=" + i);
            return i;
        }
        LogUtil.verbose(str, " getiTacExitTag.null == mCarVendorEnergyManager");
        i = -1;
        LogUtil.verbose(str, " getiTacExitTag.out=" + i);
        return i;
    }

    public int getiTacMode() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getiTacMode.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                i = carVendorEnergyManager.getiTacModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getiTacMode.out=" + i);
            return i;
        }
        LogUtil.verbose(str, " getiTacMode.null == mCarVendorEnergyManager");
        i = -1;
        LogUtil.verbose(str, " getiTacMode.out=" + i);
        return i;
    }

    public int getiTacpAllowTag() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getiTacpAllowTag.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                i = carVendorEnergyManager.getiTacpAllowTagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getiTacpAllowTag.out=" + i);
            return i;
        }
        LogUtil.verbose(str, " getiTacpAllowTag.null == mCarVendorEnergyManager");
        i = -1;
        LogUtil.verbose(str, " getiTacpAllowTag.out=" + i);
        return i;
    }

    public int getiTacpState() {
        int i;
        String str = C3;
        LogUtil.verbose(str, " getiTacpState.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                i = carVendorEnergyManager.getiTacpStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getiTacpState.out=" + i);
            return i;
        }
        LogUtil.verbose(str, " getiTacpState.null == mCarVendorEnergyManager");
        i = -1;
        LogUtil.verbose(str, " getiTacpState.out=" + i);
        return i;
    }

    public int hasMessage(int i) {
        String str = C3;
        LogUtil.verbose(str, " hasMessage.in" + i);
        switch (com.ts.appservice.caradapter.b.a.a(i)) {
            case 85:
                CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557945600, 0);
                if (carPropertyValueA == null) {
                    return 0;
                }
                int[] iArrH = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA.getValue());
                LogUtil.verbose(str, "hasMessage CAN_055 prop value " + Arrays.toString(iArrH));
                return w(iArrH);
            case 213:
                CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557945603, 0);
                if (carPropertyValueA2 == null) {
                    return 0;
                }
                int[] iArrH2 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA2.getValue());
                LogUtil.verbose(str, "hasMessage CAN_0D5 prop value " + Arrays.toString(iArrH2));
                return w(iArrH2);
            case 269:
                CarPropertyValue carPropertyValueA3 = com.ts.appservice.caradapter.e.c.a(557945605, 0);
                if (carPropertyValueA3 == null) {
                    return 0;
                }
                int[] iArrH3 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA3.getValue());
                LogUtil.verbose(str, "hasMessage CAN_10D prop value " + Arrays.toString(iArrH3));
                return w(iArrH3);
            case 289:
                CarPropertyValue carPropertyValueA4 = com.ts.appservice.caradapter.e.c.a(557945606, 0);
                if (carPropertyValueA4 == null) {
                    return 0;
                }
                int[] iArrH4 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA4.getValue());
                LogUtil.verbose(str, "hasMessage CAN_121 prop value " + Arrays.toString(iArrH4));
                return w(iArrH4);
            case 291:
                CarPropertyValue carPropertyValueA5 = com.ts.appservice.caradapter.e.c.a(557945607, 0);
                if (carPropertyValueA5 == null) {
                    return 0;
                }
                int[] iArrH5 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA5.getValue());
                LogUtil.verbose(str, "hasMessage CAN_123 prop value " + Arrays.toString(iArrH5));
                return w(iArrH5);
            case 301:
                CarPropertyValue carPropertyValueA6 = com.ts.appservice.caradapter.e.c.a(557945608, 0);
                if (carPropertyValueA6 == null) {
                    return 0;
                }
                int[] iArrH6 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA6.getValue());
                LogUtil.verbose(str, "hasMessage CAN_12D prop value " + Arrays.toString(iArrH6));
                return w(iArrH6);
            case 303:
                CarPropertyValue carPropertyValueA7 = com.ts.appservice.caradapter.e.c.a(557945609, 0);
                if (carPropertyValueA7 == null) {
                    return 0;
                }
                int[] iArrH7 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA7.getValue());
                LogUtil.verbose(str, "hasMessage CAN_12F prop value " + Arrays.toString(iArrH7));
                return w(iArrH7);
            case 307:
                CarPropertyValue carPropertyValueA8 = com.ts.appservice.caradapter.e.c.a(557945610, 0);
                if (carPropertyValueA8 == null) {
                    return 0;
                }
                int[] iArrH8 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA8.getValue());
                LogUtil.verbose(str, "hasMessage CAN_133 prop value " + Arrays.toString(iArrH8));
                return w(iArrH8);
            case 312:
                CarPropertyValue carPropertyValueA9 = com.ts.appservice.caradapter.e.c.a(557945611, 0);
                if (carPropertyValueA9 == null) {
                    return 0;
                }
                int[] iArrH9 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA9.getValue());
                LogUtil.verbose(str, "hasMessage CAN_138 prop value " + Arrays.toString(iArrH9));
                return w(iArrH9);
            case 319:
                CarPropertyValue carPropertyValueA10 = com.ts.appservice.caradapter.e.c.a(557945612, 0);
                if (carPropertyValueA10 == null) {
                    return 0;
                }
                int[] iArrH10 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA10.getValue());
                LogUtil.verbose(str, "hasMessage CAN_13F prop value " + Arrays.toString(iArrH10));
                return w(iArrH10);
            case 324:
                CarPropertyValue carPropertyValueA11 = com.ts.appservice.caradapter.e.c.a(557945613, 0);
                if (carPropertyValueA11 == null) {
                    return 0;
                }
                int[] iArrH11 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA11.getValue());
                LogUtil.verbose(str, "hasMessage CAN_144 prop value " + Arrays.toString(iArrH11));
                return w(iArrH11);
            case 325:
                CarPropertyValue carPropertyValueA12 = com.ts.appservice.caradapter.e.c.a(557945775, 0);
                if (carPropertyValueA12 == null) {
                    return 0;
                }
                int[] iArrH12 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA12.getValue());
                LogUtil.verbose(str, "hasMessage CAN_145 prop value " + Arrays.toString(iArrH12));
                return w(iArrH12);
            case 337:
                CarPropertyValue carPropertyValueA13 = com.ts.appservice.caradapter.e.c.a(557945614, 0);
                if (carPropertyValueA13 == null) {
                    return 0;
                }
                int[] iArrH13 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA13.getValue());
                LogUtil.verbose(str, "hasMessage CAN_151 prop value " + Arrays.toString(iArrH13));
                return w(iArrH13);
            case 359:
                CarPropertyValue carPropertyValueA14 = com.ts.appservice.caradapter.e.c.a(557945615, 0);
                if (carPropertyValueA14 == null) {
                    return 0;
                }
                int[] iArrH14 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA14.getValue());
                LogUtil.verbose(str, "hasMessage CAN_167 prop value " + Arrays.toString(iArrH14));
                return w(iArrH14);
            case 394:
                CarPropertyValue carPropertyValueA15 = com.ts.appservice.caradapter.e.c.a(557945616, 0);
                if (carPropertyValueA15 == null) {
                    return 0;
                }
                int[] iArrH15 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA15.getValue());
                LogUtil.verbose(str, "hasMessage CAN_18A prop value " + Arrays.toString(iArrH15));
                return w(iArrH15);
            case 415:
                CarPropertyValue carPropertyValueA16 = com.ts.appservice.caradapter.e.c.a(557945617, 0);
                if (carPropertyValueA16 == null) {
                    return 0;
                }
                int[] iArrH16 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA16.getValue());
                LogUtil.verbose(str, "hasMessage CAN_19F prop value " + Arrays.toString(iArrH16));
                return w(iArrH16);
            case 465:
                CarPropertyValue carPropertyValueA17 = com.ts.appservice.caradapter.e.c.a(557945619, 0);
                if (carPropertyValueA17 == null) {
                    return 0;
                }
                int[] iArrH17 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA17.getValue());
                LogUtil.verbose(str, "hasMessage CAN_1D1 prop value " + Arrays.toString(iArrH17));
                return w(iArrH17);
            case 490:
                CarPropertyValue carPropertyValueA18 = com.ts.appservice.caradapter.e.c.a(557945777, 0);
                if (carPropertyValueA18 == null) {
                    return 0;
                }
                int[] iArrH18 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA18.getValue());
                LogUtil.verbose(str, "hasMessage CAN_1EA prop value " + Arrays.toString(iArrH18));
                return w(iArrH18);
            case 536:
                CarPropertyValue carPropertyValueA19 = com.ts.appservice.caradapter.e.c.a(557945620, 0);
                if (carPropertyValueA19 == null) {
                    return 0;
                }
                int[] iArrH19 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA19.getValue());
                LogUtil.verbose(str, "hasMessage CAN_218 prop value " + Arrays.toString(iArrH19));
                return w(iArrH19);
            case 547:
                CarPropertyValue carPropertyValueA20 = com.ts.appservice.caradapter.e.c.a(557945621, 0);
                if (carPropertyValueA20 == null) {
                    return 0;
                }
                int[] iArrH20 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA20.getValue());
                LogUtil.verbose(str, "hasMessage CAN_223 prop value " + Arrays.toString(iArrH20));
                return w(iArrH20);
            case 563:
                CarPropertyValue carPropertyValueA21 = com.ts.appservice.caradapter.e.c.a(557945622, 0);
                if (carPropertyValueA21 == null) {
                    return 0;
                }
                int[] iArrH21 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA21.getValue());
                LogUtil.verbose(str, "hasMessage CAN_233 prop value " + Arrays.toString(iArrH21));
                return w(iArrH21);
            case 565:
                CarPropertyValue carPropertyValueA22 = com.ts.appservice.caradapter.e.c.a(557945623, 0);
                if (carPropertyValueA22 == null) {
                    return 0;
                }
                int[] iArrH22 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA22.getValue());
                LogUtil.verbose(str, "hasMessage CAN_235 prop value " + Arrays.toString(iArrH22));
                return w(iArrH22);
            case 570:
                CarPropertyValue carPropertyValueA23 = com.ts.appservice.caradapter.e.c.a(557945624, 0);
                if (carPropertyValueA23 == null) {
                    return 0;
                }
                int[] iArrH23 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA23.getValue());
                LogUtil.verbose(str, "hasMessage CAN_23A prop value " + Arrays.toString(iArrH23));
                return w(iArrH23);
            case 578:
                CarPropertyValue carPropertyValueA24 = com.ts.appservice.caradapter.e.c.a(557945625, 0);
                if (carPropertyValueA24 == null) {
                    return 0;
                }
                int[] iArrH24 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA24.getValue());
                LogUtil.verbose(str, "hasMessage CAN_242 prop value " + Arrays.toString(iArrH24));
                return w(iArrH24);
            case 581:
                CarPropertyValue carPropertyValueA25 = com.ts.appservice.caradapter.e.c.a(557945626, 0);
                if (carPropertyValueA25 == null) {
                    return 0;
                }
                int[] iArrH25 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA25.getValue());
                LogUtil.verbose(str, "hasMessage CAN_245 prop value " + Arrays.toString(iArrH25));
                return w(iArrH25);
            case 584:
                CarPropertyValue carPropertyValueA26 = com.ts.appservice.caradapter.e.c.a(557945627, 0);
                if (carPropertyValueA26 == null) {
                    return 0;
                }
                int[] iArrH26 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA26.getValue());
                LogUtil.verbose(str, "hasMessage CAN_248 prop value " + Arrays.toString(iArrH26));
                return w(iArrH26);
            case 586:
                CarPropertyValue carPropertyValueA27 = com.ts.appservice.caradapter.e.c.a(557945628, 0);
                if (carPropertyValueA27 == null) {
                    return 0;
                }
                int[] iArrH27 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA27.getValue());
                LogUtil.verbose(str, "hasMessage CAN_24A prop value " + Arrays.toString(iArrH27));
                return w(iArrH27);
            case 588:
                CarPropertyValue carPropertyValueA28 = com.ts.appservice.caradapter.e.c.a(557945629, 0);
                if (carPropertyValueA28 == null) {
                    return 0;
                }
                int[] iArrH28 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA28.getValue());
                LogUtil.verbose(str, "hasMessage CAN_24C prop value " + Arrays.toString(iArrH28));
                return w(iArrH28);
            case 599:
                CarPropertyValue carPropertyValueA29 = com.ts.appservice.caradapter.e.c.a(557945769, 0);
                if (carPropertyValueA29 == null) {
                    return 0;
                }
                int[] iArrH29 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA29.getValue());
                LogUtil.verbose(str, "hasMessage CAN_257 prop value " + Arrays.toString(iArrH29));
                return w(iArrH29);
            case 600:
                CarPropertyValue carPropertyValueA30 = com.ts.appservice.caradapter.e.c.a(557945765, 0);
                if (carPropertyValueA30 == null) {
                    return 0;
                }
                int[] iArrH30 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA30.getValue());
                LogUtil.verbose(str, "hasMessage CAN_258 prop value " + Arrays.toString(iArrH30));
                return w(iArrH30);
            case 605:
                CarPropertyValue carPropertyValueA31 = com.ts.appservice.caradapter.e.c.a(557945630, 0);
                if (carPropertyValueA31 == null) {
                    return 0;
                }
                int[] iArrH31 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA31.getValue());
                LogUtil.verbose(str, "hasMessage CAN_25D prop value " + Arrays.toString(iArrH31));
                return w(iArrH31);
            case 609:
                CarPropertyValue carPropertyValueA32 = com.ts.appservice.caradapter.e.c.a(557945631, 0);
                if (carPropertyValueA32 == null) {
                    return 0;
                }
                int[] iArrH32 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA32.getValue());
                LogUtil.verbose(str, "hasMessage CAN_261 prop value " + Arrays.toString(iArrH32));
                return w(iArrH32);
            case 623:
                CarPropertyValue carPropertyValueA33 = com.ts.appservice.caradapter.e.c.a(557945632, 0);
                if (carPropertyValueA33 == null) {
                    return 0;
                }
                int[] iArrH33 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA33.getValue());
                LogUtil.verbose(str, "hasMessage CAN_26F prop value " + Arrays.toString(iArrH33));
                return w(iArrH33);
            case 625:
                CarPropertyValue carPropertyValueA34 = com.ts.appservice.caradapter.e.c.a(557945633, 0);
                if (carPropertyValueA34 == null) {
                    return 0;
                }
                int[] iArrH34 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA34.getValue());
                LogUtil.verbose(str, "hasMessage CAN_271 prop value " + Arrays.toString(iArrH34));
                return w(iArrH34);
            case 626:
                CarPropertyValue carPropertyValueA35 = com.ts.appservice.caradapter.e.c.a(557945634, 0);
                if (carPropertyValueA35 == null) {
                    return 0;
                }
                int[] iArrH35 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA35.getValue());
                LogUtil.verbose(str, "hasMessage CAN_272 prop value " + Arrays.toString(iArrH35));
                return w(iArrH35);
            case 629:
                CarPropertyValue carPropertyValueA36 = com.ts.appservice.caradapter.e.c.a(557945635, 0);
                if (carPropertyValueA36 == null) {
                    return 0;
                }
                int[] iArrH36 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA36.getValue());
                LogUtil.verbose(str, "hasMessage CAN_275 prop value " + Arrays.toString(iArrH36));
                return w(iArrH36);
            case 634:
                CarPropertyValue carPropertyValueA37 = com.ts.appservice.caradapter.e.c.a(557945636, 0);
                if (carPropertyValueA37 == null) {
                    return 0;
                }
                int[] iArrH37 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA37.getValue());
                LogUtil.verbose(str, "hasMessage CAN_27A prop value " + Arrays.toString(iArrH37));
                return w(iArrH37);
            case 647:
                CarPropertyValue carPropertyValueA38 = com.ts.appservice.caradapter.e.c.a(557945637, 0);
                if (carPropertyValueA38 == null) {
                    return 0;
                }
                int[] iArrH38 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA38.getValue());
                LogUtil.verbose(str, "hasMessage CAN_287 prop value " + Arrays.toString(iArrH38));
                return w(iArrH38);
            case 648:
                CarPropertyValue carPropertyValueA39 = com.ts.appservice.caradapter.e.c.a(557945638, 0);
                if (carPropertyValueA39 == null) {
                    return 0;
                }
                int[] iArrH39 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA39.getValue());
                LogUtil.verbose(str, "hasMessage CAN_288 prop value " + Arrays.toString(iArrH39));
                return w(iArrH39);
            case 655:
                CarPropertyValue carPropertyValueA40 = com.ts.appservice.caradapter.e.c.a(557945639, 0);
                if (carPropertyValueA40 == null) {
                    return 0;
                }
                int[] iArrH40 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA40.getValue());
                LogUtil.verbose(str, "hasMessage CAN_28F prop value " + Arrays.toString(iArrH40));
                return w(iArrH40);
            case 660:
                CarPropertyValue carPropertyValueA41 = com.ts.appservice.caradapter.e.c.a(557945640, 0);
                if (carPropertyValueA41 == null) {
                    return 0;
                }
                int[] iArrH41 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA41.getValue());
                LogUtil.verbose(str, "hasMessage CAN_294 prop value " + Arrays.toString(iArrH41));
                return w(iArrH41);
            case 662:
                CarPropertyValue carPropertyValueA42 = com.ts.appservice.caradapter.e.c.a(557945641, 0);
                if (carPropertyValueA42 == null) {
                    return 0;
                }
                int[] iArrH42 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA42.getValue());
                LogUtil.verbose(str, "hasMessage CAN_296 prop value " + Arrays.toString(iArrH42));
                return w(iArrH42);
            case 668:
                CarPropertyValue carPropertyValueA43 = com.ts.appservice.caradapter.e.c.a(557945642, 0);
                if (carPropertyValueA43 == null) {
                    return 0;
                }
                int[] iArrH43 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA43.getValue());
                LogUtil.verbose(str, "hasMessage CAN_29C prop value " + Arrays.toString(iArrH43));
                return w(iArrH43);
            case 670:
                CarPropertyValue carPropertyValueA44 = com.ts.appservice.caradapter.e.c.a(557945643, 0);
                if (carPropertyValueA44 == null) {
                    return 0;
                }
                int[] iArrH44 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA44.getValue());
                LogUtil.verbose(str, "hasMessage CAN_29E prop value " + Arrays.toString(iArrH44));
                return w(iArrH44);
            case 678:
                CarPropertyValue carPropertyValueA45 = com.ts.appservice.caradapter.e.c.a(557945645, 0);
                if (carPropertyValueA45 == null) {
                    return 0;
                }
                int[] iArrH45 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA45.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2A6 prop value " + Arrays.toString(iArrH45));
                return w(iArrH45);
            case 679:
                CarPropertyValue carPropertyValueA46 = com.ts.appservice.caradapter.e.c.a(557945646, 0);
                if (carPropertyValueA46 == null) {
                    return 0;
                }
                int[] iArrH46 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA46.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2A7 prop value " + Arrays.toString(iArrH46));
                return w(iArrH46);
            case 688:
                CarPropertyValue carPropertyValueA47 = com.ts.appservice.caradapter.e.c.a(557945647, 0);
                if (carPropertyValueA47 == null) {
                    return 0;
                }
                int[] iArrH47 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA47.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2B0 prop value " + Arrays.toString(iArrH47));
                return w(iArrH47);
            case 692:
                CarPropertyValue carPropertyValueA48 = com.ts.appservice.caradapter.e.c.a(557945648, 0);
                if (carPropertyValueA48 == null) {
                    return 0;
                }
                int[] iArrH48 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA48.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2B4 prop value " + Arrays.toString(iArrH48));
                return w(iArrH48);
            case 698:
                CarPropertyValue carPropertyValueA49 = com.ts.appservice.caradapter.e.c.a(557945650, 0);
                if (carPropertyValueA49 == null) {
                    return 0;
                }
                int[] iArrH49 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA49.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2BA prop value " + Arrays.toString(iArrH49));
                return w(iArrH49);
            case 704:
                CarPropertyValue carPropertyValueA50 = com.ts.appservice.caradapter.e.c.a(557945652, 0);
                if (carPropertyValueA50 == null) {
                    return 0;
                }
                int[] iArrH50 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA50.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2C0 prop value " + Arrays.toString(iArrH50));
                return w(iArrH50);
            case 725:
                CarPropertyValue carPropertyValueA51 = com.ts.appservice.caradapter.e.c.a(557945653, 0);
                if (carPropertyValueA51 == null) {
                    return 0;
                }
                int[] iArrH51 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA51.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2D5 prop value " + Arrays.toString(iArrH51));
                return w(iArrH51);
            case 747:
                CarPropertyValue carPropertyValueA52 = com.ts.appservice.caradapter.e.c.a(557945654, 0);
                if (carPropertyValueA52 == null) {
                    return 0;
                }
                int[] iArrH52 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA52.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2EB prop value " + Arrays.toString(iArrH52));
                return w(iArrH52);
            case 752:
                CarPropertyValue carPropertyValueA53 = com.ts.appservice.caradapter.e.c.a(557945655, 0);
                if (carPropertyValueA53 == null) {
                    return 0;
                }
                int[] iArrH53 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA53.getValue());
                LogUtil.verbose(str, "hasMessage CAN_2F0 prop value " + Arrays.toString(iArrH53));
                return w(iArrH53);
            case 781:
                CarPropertyValue carPropertyValueA54 = com.ts.appservice.caradapter.e.c.a(557945656, 0);
                if (carPropertyValueA54 == null) {
                    return 0;
                }
                int[] iArrH54 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA54.getValue());
                LogUtil.verbose(str, "hasMessage CAN_30D prop value " + Arrays.toString(iArrH54));
                return w(iArrH54);
            case 793:
                CarPropertyValue carPropertyValueA55 = com.ts.appservice.caradapter.e.c.a(557945764, 0);
                if (carPropertyValueA55 == null) {
                    return 0;
                }
                int[] iArrH55 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA55.getValue());
                LogUtil.verbose(str, "hasMessage CAN_319 prop value " + Arrays.toString(iArrH55));
                return w(iArrH55);
            case 798:
                CarPropertyValue carPropertyValueA56 = com.ts.appservice.caradapter.e.c.a(557945657, 0);
                if (carPropertyValueA56 == null) {
                    return 0;
                }
                int[] iArrH56 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA56.getValue());
                LogUtil.verbose(str, "hasMessage CAN_31E prop value " + Arrays.toString(iArrH56));
                return w(iArrH56);
            case 801:
                CarPropertyValue carPropertyValueA57 = com.ts.appservice.caradapter.e.c.a(557945658, 0);
                if (carPropertyValueA57 == null) {
                    return 0;
                }
                int[] iArrH57 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA57.getValue());
                LogUtil.verbose(str, "hasMessage CAN_321 prop value " + Arrays.toString(iArrH57));
                return w(iArrH57);
            case 803:
                CarPropertyValue carPropertyValueA58 = com.ts.appservice.caradapter.e.c.a(557945659, 0);
                if (carPropertyValueA58 == null) {
                    return 0;
                }
                int[] iArrH58 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA58.getValue());
                LogUtil.verbose(str, "hasMessage CAN_323 prop value " + Arrays.toString(iArrH58));
                return w(iArrH58);
            case 813:
                CarPropertyValue carPropertyValueA59 = com.ts.appservice.caradapter.e.c.a(557945660, 0);
                if (carPropertyValueA59 == null) {
                    return 0;
                }
                int[] iArrH59 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA59.getValue());
                LogUtil.verbose(str, "hasMessage CAN_32D prop value " + Arrays.toString(iArrH59));
                return w(iArrH59);
            case 820:
                CarPropertyValue carPropertyValueA60 = com.ts.appservice.caradapter.e.c.a(557945661, 0);
                if (carPropertyValueA60 == null) {
                    return 0;
                }
                int[] iArrH60 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA60.getValue());
                LogUtil.verbose(str, "hasMessage CAN_334 prop value " + Arrays.toString(iArrH60));
                return w(iArrH60);
            case 823:
                CarPropertyValue carPropertyValueA61 = com.ts.appservice.caradapter.e.c.a(557945662, 0);
                if (carPropertyValueA61 == null) {
                    return 0;
                }
                int[] iArrH61 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA61.getValue());
                LogUtil.verbose(str, "hasMessage CAN_337 prop value " + Arrays.toString(iArrH61));
                return w(iArrH61);
            case 824:
                CarPropertyValue carPropertyValueA62 = com.ts.appservice.caradapter.e.c.a(557945774, 0);
                if (carPropertyValueA62 == null) {
                    return 0;
                }
                int[] iArrH62 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA62.getValue());
                LogUtil.verbose(str, "hasMessage CAN_338 prop value " + Arrays.toString(iArrH62));
                return w(iArrH62);
            case 833:
                CarPropertyValue carPropertyValueA63 = com.ts.appservice.caradapter.e.c.a(557945663, 0);
                if (carPropertyValueA63 == null) {
                    return 0;
                }
                int[] iArrH63 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA63.getValue());
                LogUtil.verbose(str, "hasMessage CAN_341 prop value " + Arrays.toString(iArrH63));
                return w(iArrH63);
            case 834:
                CarPropertyValue carPropertyValueA64 = com.ts.appservice.caradapter.e.c.a(557945664, 0);
                if (carPropertyValueA64 == null) {
                    return 0;
                }
                int[] iArrH64 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA64.getValue());
                LogUtil.verbose(str, "hasMessage CAN_342 prop value " + Arrays.toString(iArrH64));
                return w(iArrH64);
            case 836:
                CarPropertyValue carPropertyValueA65 = com.ts.appservice.caradapter.e.c.a(557945763, 0);
                if (carPropertyValueA65 == null) {
                    return 0;
                }
                int[] iArrH65 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA65.getValue());
                LogUtil.verbose(str, "hasMessage CAN_344 prop value " + Arrays.toString(iArrH65));
                return w(iArrH65);
            case 838:
                CarPropertyValue carPropertyValueA66 = com.ts.appservice.caradapter.e.c.a(557945665, 0);
                if (carPropertyValueA66 == null) {
                    return 0;
                }
                int[] iArrH66 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA66.getValue());
                LogUtil.verbose(str, "hasMessage CAN_346 prop value " + Arrays.toString(iArrH66));
                return w(iArrH66);
            case 839:
                CarPropertyValue carPropertyValueA67 = com.ts.appservice.caradapter.e.c.a(557945666, 0);
                if (carPropertyValueA67 == null) {
                    return 0;
                }
                int[] iArrH67 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA67.getValue());
                LogUtil.verbose(str, "hasMessage CAN_347 prop value " + Arrays.toString(iArrH67));
                return w(iArrH67);
            case 842:
                CarPropertyValue carPropertyValueA68 = com.ts.appservice.caradapter.e.c.a(557945667, 0);
                if (carPropertyValueA68 == null) {
                    return 0;
                }
                int[] iArrH68 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA68.getValue());
                LogUtil.verbose(str, "hasMessage CAN_34A prop value " + Arrays.toString(iArrH68));
                return w(iArrH68);
            case 847:
                CarPropertyValue carPropertyValueA69 = com.ts.appservice.caradapter.e.c.a(557945668, 0);
                if (carPropertyValueA69 == null) {
                    return 0;
                }
                int[] iArrH69 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA69.getValue());
                LogUtil.verbose(str, "hasMessage CAN_34F prop value " + Arrays.toString(iArrH69));
                return w(iArrH69);
            case 849:
                CarPropertyValue carPropertyValueA70 = com.ts.appservice.caradapter.e.c.a(557945766, 0);
                if (carPropertyValueA70 == null) {
                    return 0;
                }
                int[] iArrH70 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA70.getValue());
                LogUtil.verbose(str, "hasMessage CAN_351 prop value " + Arrays.toString(iArrH70));
                return w(iArrH70);
            case 857:
                CarPropertyValue carPropertyValueA71 = com.ts.appservice.caradapter.e.c.a(557945778, 0);
                if (carPropertyValueA71 == null) {
                    return 0;
                }
                int[] iArrH71 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA71.getValue());
                LogUtil.verbose(str, "hasMessage CAN_359 prop value " + Arrays.toString(iArrH71));
                return w(iArrH71);
            case 875:
                CarPropertyValue carPropertyValueA72 = com.ts.appservice.caradapter.e.c.a(557945669, 0);
                if (carPropertyValueA72 == null) {
                    return 0;
                }
                int[] iArrH72 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA72.getValue());
                LogUtil.verbose(str, "hasMessage CAN_36B prop value " + Arrays.toString(iArrH72));
                return w(iArrH72);
            case 877:
                CarPropertyValue carPropertyValueA73 = com.ts.appservice.caradapter.e.c.a(557945670, 0);
                if (carPropertyValueA73 == null) {
                    return 0;
                }
                int[] iArrH73 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA73.getValue());
                LogUtil.verbose(str, "hasMessage CAN_36D prop value " + Arrays.toString(iArrH73));
                return w(iArrH73);
            case 878:
                CarPropertyValue carPropertyValueA74 = com.ts.appservice.caradapter.e.c.a(557945671, 0);
                if (carPropertyValueA74 == null) {
                    return 0;
                }
                int[] iArrH74 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA74.getValue());
                LogUtil.verbose(str, "hasMessage CAN_36E prop value " + Arrays.toString(iArrH74));
                return w(iArrH74);
            case 895:
                CarPropertyValue carPropertyValueA75 = com.ts.appservice.caradapter.e.c.a(557945672, 0);
                if (carPropertyValueA75 == null) {
                    return 0;
                }
                int[] iArrH75 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA75.getValue());
                LogUtil.verbose(str, "hasMessage CAN_37F prop value " + Arrays.toString(iArrH75));
                return w(iArrH75);
            case 906:
                CarPropertyValue carPropertyValueA76 = com.ts.appservice.caradapter.e.c.a(557945673, 0);
                if (carPropertyValueA76 == null) {
                    return 0;
                }
                int[] iArrH76 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA76.getValue());
                LogUtil.verbose(str, "hasMessage CAN_38A prop value " + Arrays.toString(iArrH76));
                return w(iArrH76);
            case 907:
                CarPropertyValue carPropertyValueA77 = com.ts.appservice.caradapter.e.c.a(557945674, 0);
                if (carPropertyValueA77 == null) {
                    return 0;
                }
                int[] iArrH77 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA77.getValue());
                LogUtil.verbose(str, "hasMessage CAN_38B prop value " + Arrays.toString(iArrH77));
                return w(iArrH77);
            case 911:
                CarPropertyValue carPropertyValueA78 = com.ts.appservice.caradapter.e.c.a(557945772, 0);
                if (carPropertyValueA78 == null) {
                    return 0;
                }
                int[] iArrH78 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA78.getValue());
                LogUtil.verbose(str, "hasMessage CAN_38F prop value " + Arrays.toString(iArrH78));
                return w(iArrH78);
            case 918:
                CarPropertyValue carPropertyValueA79 = com.ts.appservice.caradapter.e.c.a(557945676, 0);
                if (carPropertyValueA79 == null) {
                    return 0;
                }
                int[] iArrH79 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA79.getValue());
                LogUtil.verbose(str, "hasMessage CAN_396 prop value " + Arrays.toString(iArrH79));
                return w(iArrH79);
            case 928:
                CarPropertyValue carPropertyValueA80 = com.ts.appservice.caradapter.e.c.a(557945677, 0);
                if (carPropertyValueA80 == null) {
                    return 0;
                }
                int[] iArrH80 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA80.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3A0 prop value " + Arrays.toString(iArrH80));
                return w(iArrH80);
            case 935:
                CarPropertyValue carPropertyValueA81 = com.ts.appservice.caradapter.e.c.a(557945678, 0);
                if (carPropertyValueA81 == null) {
                    return 0;
                }
                int[] iArrH81 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA81.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3A7 prop value " + Arrays.toString(iArrH81));
                return w(iArrH81);
            case 940:
                CarPropertyValue carPropertyValueA82 = com.ts.appservice.caradapter.e.c.a(557945679, 0);
                if (carPropertyValueA82 == null) {
                    return 0;
                }
                int[] iArrH82 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA82.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3AC prop value " + Arrays.toString(iArrH82));
                return w(iArrH82);
            case 941:
                CarPropertyValue carPropertyValueA83 = com.ts.appservice.caradapter.e.c.a(557945680, 0);
                if (carPropertyValueA83 == null) {
                    return 0;
                }
                int[] iArrH83 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA83.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3AD prop value " + Arrays.toString(iArrH83));
                return w(iArrH83);
            case 944:
                CarPropertyValue carPropertyValueA84 = com.ts.appservice.caradapter.e.c.a(557945681, 0);
                if (carPropertyValueA84 == null) {
                    return 0;
                }
                int[] iArrH84 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA84.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3B0 prop value " + Arrays.toString(iArrH84));
                return w(iArrH84);
            case 947:
                CarPropertyValue carPropertyValueA85 = com.ts.appservice.caradapter.e.c.a(557945682, 0);
                if (carPropertyValueA85 == null) {
                    return 0;
                }
                int[] iArrH85 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA85.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3B3 prop value " + Arrays.toString(iArrH85));
                return w(iArrH85);
            case 948:
                CarPropertyValue carPropertyValueA86 = com.ts.appservice.caradapter.e.c.a(557945683, 0);
                if (carPropertyValueA86 == null) {
                    return 0;
                }
                int[] iArrH86 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA86.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3B4 prop value " + Arrays.toString(iArrH86));
                return w(iArrH86);
            case 955:
                CarPropertyValue carPropertyValueA87 = com.ts.appservice.caradapter.e.c.a(557945684, 0);
                if (carPropertyValueA87 == null) {
                    return 0;
                }
                int[] iArrH87 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA87.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3BB prop value " + Arrays.toString(iArrH87));
                return w(iArrH87);
            case 957:
                CarPropertyValue carPropertyValueA88 = com.ts.appservice.caradapter.e.c.a(557945685, 0);
                if (carPropertyValueA88 == null) {
                    return 0;
                }
                int[] iArrH88 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA88.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3BD prop value " + Arrays.toString(iArrH88));
                return w(iArrH88);
            case 961:
                CarPropertyValue carPropertyValueA89 = com.ts.appservice.caradapter.e.c.a(557945686, 0);
                if (carPropertyValueA89 == null) {
                    return 0;
                }
                int[] iArrH89 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA89.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3C1 prop value " + Arrays.toString(iArrH89));
                return w(iArrH89);
            case 968:
                CarPropertyValue carPropertyValueA90 = com.ts.appservice.caradapter.e.c.a(557945687, 0);
                if (carPropertyValueA90 == null) {
                    return 0;
                }
                int[] iArrH90 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA90.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3C8 prop value " + Arrays.toString(iArrH90));
                return w(iArrH90);
            case 970:
                CarPropertyValue carPropertyValueA91 = com.ts.appservice.caradapter.e.c.a(557945688, 0);
                if (carPropertyValueA91 == null) {
                    return 0;
                }
                int[] iArrH91 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA91.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3CA prop value " + Arrays.toString(iArrH91));
                return w(iArrH91);
            case 973:
                CarPropertyValue carPropertyValueA92 = com.ts.appservice.caradapter.e.c.a(557945689, 0);
                if (carPropertyValueA92 == null) {
                    return 0;
                }
                int[] iArrH92 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA92.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3CD prop value " + Arrays.toString(iArrH92));
                return w(iArrH92);
            case 982:
                CarPropertyValue carPropertyValueA93 = com.ts.appservice.caradapter.e.c.a(557945771, 0);
                if (carPropertyValueA93 == null) {
                    return 0;
                }
                int[] iArrH93 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA93.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3D6 prop value " + Arrays.toString(iArrH93));
                return w(iArrH93);
            case 985:
                CarPropertyValue carPropertyValueA94 = com.ts.appservice.caradapter.e.c.a(557945690, 0);
                if (carPropertyValueA94 == null) {
                    return 0;
                }
                int[] iArrH94 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA94.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3D9 prop value " + Arrays.toString(iArrH94));
                return w(iArrH94);
            case 986:
                CarPropertyValue carPropertyValueA95 = com.ts.appservice.caradapter.e.c.a(557945691, 0);
                if (carPropertyValueA95 == null) {
                    return 0;
                }
                int[] iArrH95 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA95.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3DA prop value " + Arrays.toString(iArrH95));
                return w(iArrH95);
            case 1009:
                CarPropertyValue carPropertyValueA96 = com.ts.appservice.caradapter.e.c.a(557945692, 0);
                if (carPropertyValueA96 == null) {
                    return 0;
                }
                int[] iArrH96 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA96.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3F1 prop value " + Arrays.toString(iArrH96));
                return w(iArrH96);
            case 1011:
                CarPropertyValue carPropertyValueA97 = com.ts.appservice.caradapter.e.c.a(557945693, 0);
                if (carPropertyValueA97 == null) {
                    return 0;
                }
                int[] iArrH97 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA97.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3F3 prop value " + Arrays.toString(iArrH97));
                return w(iArrH97);
            case 1022:
                CarPropertyValue carPropertyValueA98 = com.ts.appservice.caradapter.e.c.a(557945694, 0);
                if (carPropertyValueA98 == null) {
                    return 0;
                }
                int[] iArrH98 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA98.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3FE prop value " + Arrays.toString(iArrH98));
                return w(iArrH98);
            case 1023:
                CarPropertyValue carPropertyValueA99 = com.ts.appservice.caradapter.e.c.a(557945695, 0);
                if (carPropertyValueA99 == null) {
                    return 0;
                }
                int[] iArrH99 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA99.getValue());
                LogUtil.verbose(str, "hasMessage CAN_3FF prop value " + Arrays.toString(iArrH99));
                return w(iArrH99);
            case 1025:
                CarPropertyValue carPropertyValueA100 = com.ts.appservice.caradapter.e.c.a(557945696, 0);
                if (carPropertyValueA100 == null) {
                    return 0;
                }
                int[] iArrH100 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA100.getValue());
                LogUtil.verbose(str, "hasMessage CAN_401 prop value " + Arrays.toString(iArrH100));
                return w(iArrH100);
            case 1026:
                CarPropertyValue carPropertyValueA101 = com.ts.appservice.caradapter.e.c.a(557945697, 0);
                if (carPropertyValueA101 == null) {
                    return 0;
                }
                int[] iArrH101 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA101.getValue());
                LogUtil.verbose(str, "hasMessage CAN_402 prop value " + Arrays.toString(iArrH101));
                return w(iArrH101);
            case 1028:
                CarPropertyValue carPropertyValueA102 = com.ts.appservice.caradapter.e.c.a(557945698, 0);
                if (carPropertyValueA102 == null) {
                    return 0;
                }
                int[] iArrH102 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA102.getValue());
                LogUtil.verbose(str, "hasMessage CAN_404 prop value " + Arrays.toString(iArrH102));
                return w(iArrH102);
            case 1031:
                CarPropertyValue carPropertyValueA103 = com.ts.appservice.caradapter.e.c.a(557945699, 0);
                if (carPropertyValueA103 == null) {
                    return 0;
                }
                int[] iArrH103 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA103.getValue());
                LogUtil.verbose(str, "hasMessage CAN_407 prop value " + Arrays.toString(iArrH103));
                return w(iArrH103);
            case 1037:
                CarPropertyValue carPropertyValueA104 = com.ts.appservice.caradapter.e.c.a(557945700, 0);
                if (carPropertyValueA104 == null) {
                    return 0;
                }
                int[] iArrH104 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA104.getValue());
                LogUtil.verbose(str, "hasMessage CAN_40D prop value " + Arrays.toString(iArrH104));
                return w(iArrH104);
            case 1047:
                CarPropertyValue carPropertyValueA105 = com.ts.appservice.caradapter.e.c.a(557945701, 0);
                if (carPropertyValueA105 == null) {
                    return 0;
                }
                int[] iArrH105 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA105.getValue());
                LogUtil.verbose(str, "hasMessage CAN_417 prop value " + Arrays.toString(iArrH105));
                return w(iArrH105);
            case 1048:
                CarPropertyValue carPropertyValueA106 = com.ts.appservice.caradapter.e.c.a(557945702, 0);
                if (carPropertyValueA106 == null) {
                    return 0;
                }
                int[] iArrH106 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA106.getValue());
                LogUtil.verbose(str, "hasMessage CAN_418 prop value " + Arrays.toString(iArrH106));
                return w(iArrH106);
            case 1050:
                CarPropertyValue carPropertyValueA107 = com.ts.appservice.caradapter.e.c.a(557945703, 0);
                if (carPropertyValueA107 == null) {
                    return 0;
                }
                int[] iArrH107 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA107.getValue());
                LogUtil.verbose(str, "hasMessage CAN_41A prop value " + Arrays.toString(iArrH107));
                return w(iArrH107);
            case 1052:
                CarPropertyValue carPropertyValueA108 = com.ts.appservice.caradapter.e.c.a(557945704, 0);
                if (carPropertyValueA108 == null) {
                    return 0;
                }
                int[] iArrH108 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA108.getValue());
                LogUtil.verbose(str, "hasMessage CAN_41C prop value " + Arrays.toString(iArrH108));
                return w(iArrH108);
            case 1054:
                CarPropertyValue carPropertyValueA109 = com.ts.appservice.caradapter.e.c.a(557945705, 0);
                if (carPropertyValueA109 == null) {
                    return 0;
                }
                int[] iArrH109 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA109.getValue());
                LogUtil.verbose(str, "hasMessage CAN_41E prop value " + Arrays.toString(iArrH109));
                return w(iArrH109);
            case 1055:
                CarPropertyValue carPropertyValueA110 = com.ts.appservice.caradapter.e.c.a(557945706, 0);
                if (carPropertyValueA110 == null) {
                    return 0;
                }
                int[] iArrH110 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA110.getValue());
                LogUtil.verbose(str, "hasMessage CAN_41F prop value " + Arrays.toString(iArrH110));
                return w(iArrH110);
            case 1065:
                CarPropertyValue carPropertyValueA111 = com.ts.appservice.caradapter.e.c.a(557945707, 0);
                if (carPropertyValueA111 == null) {
                    return 0;
                }
                int[] iArrH111 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA111.getValue());
                LogUtil.verbose(str, "hasMessage CAN_429 prop value " + Arrays.toString(iArrH111));
                return w(iArrH111);
            case 1070:
                CarPropertyValue carPropertyValueA112 = com.ts.appservice.caradapter.e.c.a(557945708, 0);
                if (carPropertyValueA112 == null) {
                    return 0;
                }
                int[] iArrH112 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA112.getValue());
                LogUtil.verbose(str, "hasMessage CAN_42E prop value " + Arrays.toString(iArrH112));
                return w(iArrH112);
            case 1071:
                CarPropertyValue carPropertyValueA113 = com.ts.appservice.caradapter.e.c.a(557945709, 0);
                if (carPropertyValueA113 == null) {
                    return 0;
                }
                int[] iArrH113 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA113.getValue());
                LogUtil.verbose(str, "hasMessage CAN_42F prop value " + Arrays.toString(iArrH113));
                return w(iArrH113);
            case 1074:
                CarPropertyValue carPropertyValueA114 = com.ts.appservice.caradapter.e.c.a(557945710, 0);
                if (carPropertyValueA114 == null) {
                    return 0;
                }
                int[] iArrH114 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA114.getValue());
                LogUtil.verbose(str, "hasMessage CAN_432 prop value " + Arrays.toString(iArrH114));
                return w(iArrH114);
            case 1075:
                CarPropertyValue carPropertyValueA115 = com.ts.appservice.caradapter.e.c.a(557945711, 0);
                if (carPropertyValueA115 == null) {
                    return 0;
                }
                int[] iArrH115 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA115.getValue());
                LogUtil.verbose(str, "hasMessage CAN_433 prop value " + Arrays.toString(iArrH115));
                return w(iArrH115);
            case 1076:
                CarPropertyValue carPropertyValueA116 = com.ts.appservice.caradapter.e.c.a(557945712, 0);
                if (carPropertyValueA116 == null) {
                    return 0;
                }
                int[] iArrH116 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA116.getValue());
                LogUtil.verbose(str, "hasMessage CAN_434 prop value " + Arrays.toString(iArrH116));
                return w(iArrH116);
            case 1078:
                CarPropertyValue carPropertyValueA117 = com.ts.appservice.caradapter.e.c.a(557945713, 0);
                if (carPropertyValueA117 == null) {
                    return 0;
                }
                int[] iArrH117 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA117.getValue());
                LogUtil.verbose(str, "hasMessage CAN_436 prop value " + Arrays.toString(iArrH117));
                return w(iArrH117);
            case 1079:
                CarPropertyValue carPropertyValueA118 = com.ts.appservice.caradapter.e.c.a(557945714, 0);
                if (carPropertyValueA118 == null) {
                    return 0;
                }
                int[] iArrH118 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA118.getValue());
                LogUtil.verbose(str, "hasMessage CAN_437 prop value " + Arrays.toString(iArrH118));
                return w(iArrH118);
            case 1080:
                CarPropertyValue carPropertyValueA119 = com.ts.appservice.caradapter.e.c.a(557945715, 0);
                if (carPropertyValueA119 == null) {
                    return 0;
                }
                int[] iArrH119 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA119.getValue());
                LogUtil.verbose(str, "hasMessage CAN_438 prop value " + Arrays.toString(iArrH119));
                return w(iArrH119);
            case 1091:
                CarPropertyValue carPropertyValueA120 = com.ts.appservice.caradapter.e.c.a(557945718, 0);
                if (carPropertyValueA120 == null) {
                    return 0;
                }
                int[] iArrH120 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA120.getValue());
                LogUtil.verbose(str, "hasMessage CAN_443 prop value " + Arrays.toString(iArrH120));
                return w(iArrH120);
            case 1092:
                CarPropertyValue carPropertyValueA121 = com.ts.appservice.caradapter.e.c.a(557945719, 0);
                if (carPropertyValueA121 == null) {
                    return 0;
                }
                int[] iArrH121 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA121.getValue());
                LogUtil.verbose(str, "hasMessage CAN_444 prop value " + Arrays.toString(iArrH121));
                return w(iArrH121);
            case 1095:
                CarPropertyValue carPropertyValueA122 = com.ts.appservice.caradapter.e.c.a(557945720, 0);
                if (carPropertyValueA122 == null) {
                    return 0;
                }
                int[] iArrH122 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA122.getValue());
                LogUtil.verbose(str, "hasMessage CAN_447 prop value " + Arrays.toString(iArrH122));
                return w(iArrH122);
            case 1097:
                CarPropertyValue carPropertyValueA123 = com.ts.appservice.caradapter.e.c.a(557945721, 0);
                if (carPropertyValueA123 == null) {
                    return 0;
                }
                int[] iArrH123 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA123.getValue());
                LogUtil.verbose(str, "hasMessage CAN_449 prop value " + Arrays.toString(iArrH123));
                return w(iArrH123);
            case 1107:
                CarPropertyValue carPropertyValueA124 = com.ts.appservice.caradapter.e.c.a(557945722, 0);
                if (carPropertyValueA124 == null) {
                    return 0;
                }
                int[] iArrH124 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA124.getValue());
                LogUtil.verbose(str, "hasMessage CAN_453 prop value " + Arrays.toString(iArrH124));
                return w(iArrH124);
            case 1108:
                CarPropertyValue carPropertyValueA125 = com.ts.appservice.caradapter.e.c.a(557945723, 0);
                if (carPropertyValueA125 == null) {
                    return 0;
                }
                int[] iArrH125 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA125.getValue());
                LogUtil.verbose(str, "hasMessage CAN_454 prop value " + Arrays.toString(iArrH125));
                return w(iArrH125);
            case 1121:
                CarPropertyValue carPropertyValueA126 = com.ts.appservice.caradapter.e.c.a(557945724, 0);
                if (carPropertyValueA126 == null) {
                    return 0;
                }
                int[] iArrH126 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA126.getValue());
                LogUtil.verbose(str, "hasMessage CAN_461 prop value " + Arrays.toString(iArrH126));
                return w(iArrH126);
            case 1124:
                CarPropertyValue carPropertyValueA127 = com.ts.appservice.caradapter.e.c.a(557945773, 0);
                if (carPropertyValueA127 == null) {
                    return 0;
                }
                int[] iArrH127 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA127.getValue());
                LogUtil.verbose(str, "hasMessage CAN_464 prop value " + Arrays.toString(iArrH127));
                return w(iArrH127);
            case 1132:
                CarPropertyValue carPropertyValueA128 = com.ts.appservice.caradapter.e.c.a(557945725, 0);
                if (carPropertyValueA128 == null) {
                    return 0;
                }
                int[] iArrH128 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA128.getValue());
                LogUtil.verbose(str, "hasMessage CAN_46C prop value " + Arrays.toString(iArrH128));
                return w(iArrH128);
            case 1136:
                CarPropertyValue carPropertyValueA129 = com.ts.appservice.caradapter.e.c.a(557945726, 0);
                if (carPropertyValueA129 == null) {
                    return 0;
                }
                int[] iArrH129 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA129.getValue());
                LogUtil.verbose(str, "hasMessage CAN_470 prop value " + Arrays.toString(iArrH129));
                return w(iArrH129);
            case 1141:
                CarPropertyValue carPropertyValueA130 = com.ts.appservice.caradapter.e.c.a(557945727, 0);
                if (carPropertyValueA130 == null) {
                    return 0;
                }
                int[] iArrH130 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA130.getValue());
                LogUtil.verbose(str, "hasMessage CAN_475 prop value " + Arrays.toString(iArrH130));
                return w(iArrH130);
            case 1152:
                CarPropertyValue carPropertyValueA131 = com.ts.appservice.caradapter.e.c.a(557945728, 0);
                if (carPropertyValueA131 == null) {
                    return 0;
                }
                int[] iArrH131 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA131.getValue());
                LogUtil.verbose(str, "hasMessage CAN_480 prop value " + Arrays.toString(iArrH131));
                return w(iArrH131);
            case 1153:
                CarPropertyValue carPropertyValueA132 = com.ts.appservice.caradapter.e.c.a(557945729, 0);
                if (carPropertyValueA132 == null) {
                    return 0;
                }
                int[] iArrH132 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA132.getValue());
                LogUtil.verbose(str, "hasMessage CAN_481 prop value " + Arrays.toString(iArrH132));
                return w(iArrH132);
            case 1158:
                CarPropertyValue carPropertyValueA133 = com.ts.appservice.caradapter.e.c.a(557945730, 0);
                if (carPropertyValueA133 == null) {
                    return 0;
                }
                int[] iArrH133 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA133.getValue());
                LogUtil.verbose(str, "hasMessage CAN_486 prop value " + Arrays.toString(iArrH133));
                return w(iArrH133);
            case 1159:
                CarPropertyValue carPropertyValueA134 = com.ts.appservice.caradapter.e.c.a(557945731, 0);
                if (carPropertyValueA134 == null) {
                    return 0;
                }
                int[] iArrH134 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA134.getValue());
                LogUtil.verbose(str, "hasMessage CAN_487 prop value " + Arrays.toString(iArrH134));
                return w(iArrH134);
            case 1160:
                CarPropertyValue carPropertyValueA135 = com.ts.appservice.caradapter.e.c.a(557945732, 0);
                if (carPropertyValueA135 == null) {
                    return 0;
                }
                int[] iArrH135 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA135.getValue());
                LogUtil.verbose(str, "hasMessage CAN_488 prop value " + Arrays.toString(iArrH135));
                return w(iArrH135);
            case 1161:
                CarPropertyValue carPropertyValueA136 = com.ts.appservice.caradapter.e.c.a(557945733, 0);
                if (carPropertyValueA136 == null) {
                    return 0;
                }
                int[] iArrH136 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA136.getValue());
                LogUtil.verbose(str, "hasMessage CAN_489 prop value " + Arrays.toString(iArrH136));
                return w(iArrH136);
            case 1162:
                CarPropertyValue carPropertyValueA137 = com.ts.appservice.caradapter.e.c.a(557945734, 0);
                if (carPropertyValueA137 == null) {
                    return 0;
                }
                int[] iArrH137 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA137.getValue());
                LogUtil.verbose(str, "hasMessage CAN_48A prop value " + Arrays.toString(iArrH137));
                return w(iArrH137);
            case 1164:
                CarPropertyValue carPropertyValueA138 = com.ts.appservice.caradapter.e.c.a(557945770, 0);
                if (carPropertyValueA138 == null) {
                    return 0;
                }
                int[] iArrH138 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA138.getValue());
                LogUtil.verbose(str, "hasMessage CAN_48C prop value " + Arrays.toString(iArrH138));
                return w(iArrH138);
            case 1166:
                CarPropertyValue carPropertyValueA139 = com.ts.appservice.caradapter.e.c.a(557945735, 0);
                if (carPropertyValueA139 == null) {
                    return 0;
                }
                int[] iArrH139 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA139.getValue());
                LogUtil.verbose(str, "hasMessage CAN_48E prop value " + Arrays.toString(iArrH139));
                return w(iArrH139);
            case 1167:
                CarPropertyValue carPropertyValueA140 = com.ts.appservice.caradapter.e.c.a(557945736, 0);
                if (carPropertyValueA140 == null) {
                    return 0;
                }
                int[] iArrH140 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA140.getValue());
                LogUtil.verbose(str, "hasMessage CAN_48F prop value " + Arrays.toString(iArrH140));
                return w(iArrH140);
            case 1172:
                CarPropertyValue carPropertyValueA141 = com.ts.appservice.caradapter.e.c.a(557945737, 0);
                if (carPropertyValueA141 == null) {
                    return 0;
                }
                int[] iArrH141 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA141.getValue());
                LogUtil.verbose(str, "hasMessage CAN_494 prop value " + Arrays.toString(iArrH141));
                return w(iArrH141);
            case 1180:
                CarPropertyValue carPropertyValueA142 = com.ts.appservice.caradapter.e.c.a(557945738, 0);
                if (carPropertyValueA142 == null) {
                    return 0;
                }
                int[] iArrH142 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA142.getValue());
                LogUtil.verbose(str, "hasMessage CAN_49C prop value " + Arrays.toString(iArrH142));
                return w(iArrH142);
            case 1185:
                CarPropertyValue carPropertyValueA143 = com.ts.appservice.caradapter.e.c.a(557945739, 0);
                if (carPropertyValueA143 == null) {
                    return 0;
                }
                int[] iArrH143 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA143.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4A1 prop value " + Arrays.toString(iArrH143));
                return w(iArrH143);
            case 1188:
                CarPropertyValue carPropertyValueA144 = com.ts.appservice.caradapter.e.c.a(557945740, 0);
                if (carPropertyValueA144 == null) {
                    return 0;
                }
                int[] iArrH144 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA144.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4A4 prop value " + Arrays.toString(iArrH144));
                return w(iArrH144);
            case 1189:
                CarPropertyValue carPropertyValueA145 = com.ts.appservice.caradapter.e.c.a(557945741, 0);
                if (carPropertyValueA145 == null) {
                    return 0;
                }
                int[] iArrH145 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA145.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4A5 prop value " + Arrays.toString(iArrH145));
                return w(iArrH145);
            case 1191:
                CarPropertyValue carPropertyValueA146 = com.ts.appservice.caradapter.e.c.a(557945742, 0);
                if (carPropertyValueA146 == null) {
                    return 0;
                }
                int[] iArrH146 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA146.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4A7 prop value " + Arrays.toString(iArrH146));
                return w(iArrH146);
            case 1201:
                CarPropertyValue carPropertyValueA147 = com.ts.appservice.caradapter.e.c.a(557945743, 0);
                if (carPropertyValueA147 == null) {
                    return 0;
                }
                int[] iArrH147 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA147.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4B1 prop value " + Arrays.toString(iArrH147));
                return w(iArrH147);
            case 1202:
                CarPropertyValue carPropertyValueA148 = com.ts.appservice.caradapter.e.c.a(557945744, 0);
                if (carPropertyValueA148 == null) {
                    return 0;
                }
                int[] iArrH148 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA148.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4B2 prop value " + Arrays.toString(iArrH148));
                return w(iArrH148);
            case 1203:
                CarPropertyValue carPropertyValueA149 = com.ts.appservice.caradapter.e.c.a(557945745, 0);
                if (carPropertyValueA149 == null) {
                    return 0;
                }
                int[] iArrH149 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA149.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4B3 prop value " + Arrays.toString(iArrH149));
                return w(iArrH149);
            case 1206:
                CarPropertyValue carPropertyValueA150 = com.ts.appservice.caradapter.e.c.a(557945746, 0);
                if (carPropertyValueA150 == null) {
                    return 0;
                }
                int[] iArrH150 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA150.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4B6 prop value " + Arrays.toString(iArrH150));
                return w(iArrH150);
            case 1211:
                CarPropertyValue carPropertyValueA151 = com.ts.appservice.caradapter.e.c.a(557945747, 0);
                if (carPropertyValueA151 == null) {
                    return 0;
                }
                int[] iArrH151 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA151.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4BB prop value " + Arrays.toString(iArrH151));
                return w(iArrH151);
            case 1215:
                CarPropertyValue carPropertyValueA152 = com.ts.appservice.caradapter.e.c.a(557945749, 0);
                if (carPropertyValueA152 == null) {
                    return 0;
                }
                int[] iArrH152 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA152.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4BF prop value " + Arrays.toString(iArrH152));
                return w(iArrH152);
            case 1241:
                CarPropertyValue carPropertyValueA153 = com.ts.appservice.caradapter.e.c.a(557945752, 0);
                if (carPropertyValueA153 == null) {
                    return 0;
                }
                int[] iArrH153 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA153.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4D9 prop value " + Arrays.toString(iArrH153));
                return w(iArrH153);
            case 1254:
                CarPropertyValue carPropertyValueA154 = com.ts.appservice.caradapter.e.c.a(557945754, 0);
                if (carPropertyValueA154 == null) {
                    return 0;
                }
                int[] iArrH154 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA154.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4E6 prop value " + Arrays.toString(iArrH154));
                return w(iArrH154);
            case 1256:
                CarPropertyValue carPropertyValueA155 = com.ts.appservice.caradapter.e.c.a(557945767, 0);
                if (carPropertyValueA155 == null) {
                    return 0;
                }
                int[] iArrH155 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA155.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4E8 prop value " + Arrays.toString(iArrH155));
                return w(iArrH155);
            case 1257:
                CarPropertyValue carPropertyValueA156 = com.ts.appservice.caradapter.e.c.a(557945768, 0);
                if (carPropertyValueA156 == null) {
                    return 0;
                }
                int[] iArrH156 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA156.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4E9 prop value " + Arrays.toString(iArrH156));
                return w(iArrH156);
            case 1266:
                CarPropertyValue carPropertyValueA157 = com.ts.appservice.caradapter.e.c.a(557945755, 0);
                if (carPropertyValueA157 == null) {
                    return 0;
                }
                int[] iArrH157 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA157.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4F2 prop value " + Arrays.toString(iArrH157));
                return w(iArrH157);
            case 1268:
                CarPropertyValue carPropertyValueA158 = com.ts.appservice.caradapter.e.c.a(557945756, 0);
                if (carPropertyValueA158 == null) {
                    return 0;
                }
                int[] iArrH158 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA158.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4F4 prop value " + Arrays.toString(iArrH158));
                return w(iArrH158);
            case 1270:
                CarPropertyValue carPropertyValueA159 = com.ts.appservice.caradapter.e.c.a(557945757, 0);
                if (carPropertyValueA159 == null) {
                    return 0;
                }
                int[] iArrH159 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA159.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4F6 prop value " + Arrays.toString(iArrH159));
                return w(iArrH159);
            case 1271:
                CarPropertyValue carPropertyValueA160 = com.ts.appservice.caradapter.e.c.a(557945758, 0);
                if (carPropertyValueA160 == null) {
                    return 0;
                }
                int[] iArrH160 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA160.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4F7 prop value " + Arrays.toString(iArrH160));
                return w(iArrH160);
            case 1274:
                CarPropertyValue carPropertyValueA161 = com.ts.appservice.caradapter.e.c.a(557945759, 0);
                if (carPropertyValueA161 == null) {
                    return 0;
                }
                int[] iArrH161 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA161.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4FA prop value " + Arrays.toString(iArrH161));
                return w(iArrH161);
            case 1277:
                CarPropertyValue carPropertyValueA162 = com.ts.appservice.caradapter.e.c.a(557945761, 0);
                if (carPropertyValueA162 == null) {
                    return 0;
                }
                int[] iArrH162 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA162.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4FD prop value " + Arrays.toString(iArrH162));
                return w(iArrH162);
            case 1278:
                CarPropertyValue carPropertyValueA163 = com.ts.appservice.caradapter.e.c.a(557945762, 0);
                if (carPropertyValueA163 == null) {
                    return 0;
                }
                int[] iArrH163 = com.ts.appservice.caradapter.e.c.h((Integer[]) carPropertyValueA163.getValue());
                LogUtil.verbose(str, "hasMessage CAN_4FE prop value " + Arrays.toString(iArrH163));
                return w(iArrH163);
            default:
                return 0;
        }
    }

    @Override // com.ts.appservice.caradapter.d.o
    public void i(LightEventChangeValue lightEventChangeValue) {
        String str = C3;
        if (lightEventChangeValue == null) {
            LogUtil.error(str, "onLightChangeEvent:value=null");
            return;
        }
        int propertyId = lightEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onLightChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 11;
        messageObtain.arg1 = propertyId;
        messageObtain.obj = lightEventChangeValue;
        this.j.sendMessage(messageObtain);
    }

    public int isSupportDischarge() {
        return 0;
    }

    @Override // com.ts.appservice.caradapter.d.InterfaceC0010b
    public void l(ChargingEventChangeValue chargingEventChangeValue) {
        String str = C3;
        if (chargingEventChangeValue == null) {
            LogUtil.error(str, "onChargingChangeEvent:value=null");
            return;
        }
        int propertyId = chargingEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onChargingChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 8;
        messageObtain.arg1 = propertyId;
        messageObtain.obj = chargingEventChangeValue;
        this.j.sendMessage(messageObtain);
    }

    public void reSetAmbientParams(int i) {
        LogUtil.verbose(C3, "reSetAmbientParams");
    }

    public void remoteWhistle() {
    }

    public int resetCurveData(int i) {
        LogUtil.verbose(C3, "resetCurveData" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setResetCurveDataS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void resetDelayLightParams() {
        LogUtil.verbose(C3, "resetDelayLightParams");
    }

    public int setAFSSwitchSate(int i) {
        LogUtil.verbose(C3, "setAFSSwitchSate" + i);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setAFSSwitchSate(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcChargingLimit(int i) {
        String str = C3;
        LogUtil.verbose(str, " setAcChargingLimit.in");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            LogUtil.verbose(str, " setAcChargingLimit.null == mCarVendorChargingManager");
            return -1;
        }
        try {
            carVendorChargingManager.setAcChargingLimitS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setAllDoorsLock(int i) {
        LogUtil.verbose(C3, "Service setAllDoorsLock" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setAllDoorsLock(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAllDoorsLockScene(int i) {
        String str = C3;
        LogUtil.verbose(str, " setAllDoorsLockScene.in" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setAllDoorsLockSceneS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.verbose(str, " setAllDoorsLockScene.out = -1");
        return -1;
    }

    public int setAllWeatherLightSwitchState(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setAllWeatherLightSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setAllWeatherLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAllWeatherLightSwitchState.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAllWeatherLightSwitchState.out service =" + i2);
        return i2;
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        LogUtil.verbose(C3, "setAmbientMulticolorMode" + i + "," + i2);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setAmbientMulticolorModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMulticolorState(int i, int i2) {
        LogUtil.verbose(C3, "setAmbientMulticolorState" + i + "," + i2);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setAmbientMulticolorStateS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMusicMode(int i, int i2) {
        LogUtil.verbose(C3, "setAmbientMusicMode" + i + "," + i2);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setAmbientMusicModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMusicModeState(int i, int i2) {
        LogUtil.verbose(C3, "setAmbientMusicModeState" + i + "," + i2);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setAmbientMusicModeStateS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMusicRhym(int i, int i2) {
        return 0;
    }

    public int setAmbientState(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setAmbientState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setAmbientStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAmbientState.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAmbientState.out service =" + i2);
        return i2;
    }

    public int setAntiTheftStatus(int i) {
        LogUtil.verbose(C3, "Service setAntiTheftStatus");
        return 0;
    }

    public int setAtmospereLightNightWeakenMode(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setAtmospereLightNightWeakenMode.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setAtmospereLightNightWeakenModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAtmospereLightNightWeakenMode.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAtmospereLightNightWeakenMode.out service =" + i2);
        return i2;
    }

    public int setAutoExternalRearMirrorFoldState(int i) {
        LogUtil.verbose(C3, "Service setAutoExternalRearMirrorFoldState" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setAutoExternalRearMirrorFoldStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBackDoorHeightPosition(int i) {
        LogUtil.verbose(C3, "Service setBackDoorHeightPosition" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setBackDoorHeightPositionR(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBackDoorHeightPositionMyCar(int i) {
        LogUtil.verbose(C3, "Service setBackDoorHeightPositionMyCar" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setBackDoorHeightPositionMyCarS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBackDoorOpenedHeight(int i) {
        LogUtil.verbose(C3, "setBackDoorOpenedHeight" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setBackDoorOpenedHeightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBrightness(int i, int i2) {
        return 0;
    }

    public int setCarPlan(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.f65a != null) {
            try {
                int[] iArr = {i, i2, i3, i4, i5, i6, i7, i8};
                LogUtil.verbose(C3, "setCarPlan is execute" + Arrays.toString(iArr));
                this.f65a.setNewCarPlanR(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setCarPlanState(int i) {
        LogUtil.verbose(C3, "setCarPlanState is execute" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setCarPlanStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCargoLightSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setCargoLightSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setCargoLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setCargoLightSwitchState == null");
        }
        LogUtil.verbose(str, " setCargoLightSwitchState.out=0");
        return 0;
    }

    public int setChargeLimit(float f) {
        return 0;
    }

    public int setChargePlanState(int i) {
        LogUtil.verbose(C3, "Service setChargePlanState" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setChargePlanStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setChargePortCoverHint(int i) {
        String str = C3;
        LogUtil.verbose(str, " setChargePortCoverHint.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                carVendorEnergyManager.setChargePortCoverHintS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setChargePortCoverHint.null == mCarVendorEnergyManager");
        }
        LogUtil.verbose(str, " setChargePortCoverHint.out=0");
        return 0;
    }

    public int setChargePreheatState(int i) {
        LogUtil.verbose(C3, "setChargePreheatState is execute" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setChargePreheatStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setChargeStopCapacityState(int i) {
        LogUtil.verbose(C3, "setChargeStopCapacityState" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setChargeStopCapacityStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setChargeStopCapacityStateNotLi(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setChargeStopCapacityStateNotLi.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            i2 = 1;
            try {
                carVendorChargingManager.setChargeStopCapacityStateNotLiS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setChargeStopCapacityStateNotLi.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setChargeStopCapacityStateNotLi.out service =" + i2);
        return i2;
    }

    public int setChargeTempCtlState(int i) {
        LogUtil.verbose(C3, "setChargeTempCtlState" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setChargeTempCtlStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setChargeToggleGuard(int i) {
        LogUtil.verbose(C3, "setChargeToggleGuard" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setChargeToggleGuardS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setChargingNow(int i) {
    }

    public int setChargingSchedulePeriod(int i, int[] iArr) {
        return 0;
    }

    public int setChargingStartAndEndTime(long j, long j2) {
        return 0;
    }

    public int setConfirmChargePortCover(int i) {
        String str = C3;
        LogUtil.verbose(str, " setConfirmChargePortCover.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                carVendorEnergyManager.setConfirmChargePortCoverS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setConfirmChargePortCover.null == mCarVendorEnergyManager");
        }
        LogUtil.verbose(str, " setConfirmChargePortCover.out=0");
        return 0;
    }

    public int setDayTimeLightState(int i) {
        LogUtil.verbose(C3, "setDayTimeLightState" + i);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setDayTimeLightStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDischargeLimit(int i) {
        LogUtil.verbose(C3, "setDischargeLimit" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setDischargeLimitS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDischargeTime(int[] iArr) {
        LogUtil.verbose(C3, "setDischargeToggle" + Arrays.toString(iArr));
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                carVendorChargingManager.setDischargeTimeHourS(iArr[0]);
                this.f65a.setDischargeTimeMinuteS(iArr[1]);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setDischargeToggle(int i) {
        LogUtil.verbose(C3, "setDischargeToggle" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setDischargeToggleS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDoorLightState(int i) {
        LogUtil.verbose(C3, "setDoorLightState" + i);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setDoorLightStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDriveDistanceShowMode(int i) {
        LogUtil.verbose(C3, "setDriveDistanceShowMode" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setDriveDistanceShowModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDriveDistanceShowStandard(int i) {
        LogUtil.verbose(C3, "setDriveDistanceShowStandard" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setDriveDistanceShowStandardS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setEPedalState(int i) {
        LogUtil.verbose(C3, "setEPedalState" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setEPedalStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setElecBackDoorControlState(int i) {
        LogUtil.verbose(C3, "Service setElecBackDoorControlState" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setElecBackDoorControlStateR(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setElecSlideDoorState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setElecSlideDoorState.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            LogUtil.verbose(str, " setElecSlideDoorState == null");
            return -1;
        }
        try {
            carVendorCarbodyManager.setElecSlideDoorStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setElectricShowMode(int i) {
        LogUtil.verbose(C3, "setElectricShowMode" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setElectricShowModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setElectricityPriceLevel(byte[] bArr) {
        String str = C3;
        LogUtil.verbose(str, "setElectricityPriceLevel is execute");
        if (this.f65a != null) {
            try {
                byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, 7);
                byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, 7, 14);
                byte[] bArrCopyOfRange3 = Arrays.copyOfRange(bArr, 14, 21);
                byte[] bArrCopyOfRange4 = Arrays.copyOfRange(bArr, 21, 24);
                int[] iArr = {bArrCopyOfRange[0] & 15, (bArrCopyOfRange[0] & 240) >> 4, bArrCopyOfRange[1] & 15, (bArrCopyOfRange[1] & 240) >> 4, bArrCopyOfRange[2] & 15, (bArrCopyOfRange[2] & 240) >> 4, bArrCopyOfRange[3] & 15, (bArrCopyOfRange[3] & 240) >> 4, bArrCopyOfRange[4] & 15, (bArrCopyOfRange[4] & 240) >> 4, bArrCopyOfRange[5] & 15, (bArrCopyOfRange[5] & 240) >> 4, bArrCopyOfRange[6] & 15, (bArrCopyOfRange[6] & 240) >> 4};
                int[] iArr2 = {bArrCopyOfRange2[0] & 15, (bArrCopyOfRange2[0] & 240) >> 4, bArrCopyOfRange2[1] & 15, (bArrCopyOfRange2[1] & 240) >> 4, bArrCopyOfRange2[2] & 15, (bArrCopyOfRange2[2] & 240) >> 4, bArrCopyOfRange2[3] & 15, (bArrCopyOfRange2[3] & 240) >> 4, bArrCopyOfRange2[4] & 15, (bArrCopyOfRange2[4] & 240) >> 4, bArrCopyOfRange2[5] & 15, (bArrCopyOfRange2[5] & 240) >> 4, bArrCopyOfRange2[6] & 15, (bArrCopyOfRange2[6] & 240) >> 4};
                int[] iArr3 = {bArrCopyOfRange3[0] & 15, (bArrCopyOfRange3[0] & 240) >> 4, bArrCopyOfRange3[1] & 15, (bArrCopyOfRange3[1] & 240) >> 4, bArrCopyOfRange3[2] & 15, (bArrCopyOfRange3[2] & 240) >> 4, bArrCopyOfRange3[3] & 15, (bArrCopyOfRange3[3] & 240) >> 4, bArrCopyOfRange3[4] & 15, (bArrCopyOfRange3[4] & 240) >> 4, bArrCopyOfRange3[5] & 15, (bArrCopyOfRange3[5] & 240) >> 4, bArrCopyOfRange3[6] & 15, (bArrCopyOfRange3[6] & 240) >> 4};
                int[] iArr4 = {bArrCopyOfRange4[0] & 15, (bArrCopyOfRange4[0] & 240) >> 4, bArrCopyOfRange4[1] & 15, (bArrCopyOfRange4[1] & 240) >> 4, bArrCopyOfRange4[2] & 15, (bArrCopyOfRange4[2] & 240) >> 4};
                LogUtil.verbose(str, "newSix:" + Arrays.toString(iArr));
                LogUtil.verbose(str, "newSeven:" + Arrays.toString(iArr2));
                LogUtil.verbose(str, "newEight:" + Arrays.toString(iArr3));
                LogUtil.verbose(str, "newNine:" + Arrays.toString(iArr4));
                this.f65a.setElectricityPriceLeveSixS(iArr);
                this.f65a.setElectricityPriceLevelSevenS(iArr2);
                this.f65a.setElectricityPriceLevelEightS(iArr3);
                this.f65a.setElectricityPriceLevelNineS(iArr4);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setEnergyFeedback(int i) {
        return 0;
    }

    public int setEngineCondition(int i) {
        LogUtil.verbose(C3, "setEngineCondition" + i);
        CarVendorEngineManager carVendorEngineManager = this.d;
        if (carVendorEngineManager == null) {
            return 0;
        }
        try {
            carVendorEngineManager.setEngineConditionS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setEvChargePlanEndTime(int i, int i2, int i3, int i4, int i5) {
        String str = C3;
        LogUtil.verbose(str, "setEvChargePlanEndTime is execute" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5);
        if (this.f65a != null) {
            try {
                int[] iArr = {i, i2, i3, i4, i5};
                LogUtil.debug(str, "setEvChargePlanEndTime is execute" + Arrays.toString(iArr));
                this.f65a.setNewEvChargePlanEndTimeMinuteS(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setEvChargePlanStartTime(int i, int i2, int i3, int i4, int i5) {
        String str = C3;
        LogUtil.verbose(str, "setEvChargePlanStartTime is execute" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5);
        if (this.f65a != null) {
            try {
                int[] iArr = {i, i2, i3, i4, i5};
                LogUtil.debug(str, "setEvChargePlanStartTime is execute" + Arrays.toString(iArr));
                this.f65a.setNewEvChargePlanStartTimeMinuteS(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setFrontFogLightSwitchState(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setFrontFogLightSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setFrontFogLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setFrontFogLightSwitchState.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setFrontFogLightSwitchState.out service =" + i2);
        return i2;
    }

    public int setFseLargeBedMode(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setFseLargeBedMode.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            i2 = 1;
            try {
                carVendorCarbodyManager.setFseLargeBedModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setFseLargeBedMode.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setFseLargeBedMode.out service =" + i2);
        return i2;
    }

    public int setHeadlightControlMode(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setHeadlightControlMode.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setHeadlightControlModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setHeadlightControlMode.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setHeadlightControlMode.out service =" + i2);
        return i2;
    }

    public int setHevChargePlanEndTime(int i, int i2, int i3, int i4, int i5) {
        String str = C3;
        LogUtil.verbose(str, "setHevChargePlanEndTime is execute" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5);
        if (this.f65a != null) {
            try {
                int[] iArr = {i, i2, i3, i4, i5};
                LogUtil.debug(str, "setHevChargePlanEndTime is execute" + Arrays.toString(iArr));
                this.f65a.setNewHevChargePlanEndTimeMinuteS(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setHevChargePlanStartTime(int i, int i2, int i3, int i4, int i5) {
        String str = C3;
        LogUtil.verbose(str, "setHevChargePlanStartTime is execute" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5);
        if (this.f65a != null) {
            try {
                int[] iArr = {i, i2, i3, i4, i5};
                LogUtil.debug(str, "setHevChargePlanStartTime is execute" + Arrays.toString(iArr));
                this.f65a.setNewHevChargePlanStartTimeMinuteS(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setHevChargingType(int i) {
        LogUtil.verbose(C3, "setHevChargingType" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setHevChargingType(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHighlightPosition(int i) {
        String str = C3;
        LogUtil.verbose(str, " setHighlightPosition.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setHighlightPositionS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setHighlightPosition == null");
        }
        LogUtil.verbose(str, " setHighlightPosition.out=0");
        return 0;
    }

    public int setIalBrightness(int i, int i2, int i3) {
        String str = C3;
        LogUtil.verbose(str, "setIalBrightness" + i + "," + i2 + "," + i3);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose(str, "setIalBrightness +in");
            this.e.setIalBrightnessS(i2);
            this.e.setIalAreaDifferentWays(i3);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIalColor(int i, int i2, int i3) {
        LogUtil.verbose(C3, "setIalColor" + i + "," + i2 + "," + i3);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setIalColorS(i2);
            this.e.setIalAreaDifferentWays(i3);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIndirectTyreConfirm(int i) {
        LogUtil.verbose(C3, "setIndirectTyreConfirm" + i);
        CarVendorTyreManager carVendorTyreManager = this.f;
        if (carVendorTyreManager == null) {
            return 0;
        }
        try {
            carVendorTyreManager.setIndirectTyreConfirm(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIntelligentKeepTemMode(int i) {
        return 0;
    }

    public int setIntelligentOrCoerceKeepBattery(int i) {
        return 0;
    }

    public int setKtvMode(int i, int i2) {
        LogUtil.verbose(C3, "setKtvMode " + i + ", " + i2);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setKtvModeColorS(i);
            this.e.setKtvModeBrightnessS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLargeBedMode(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setLargeBedMode.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            i2 = 1;
            try {
                carVendorCarbodyManager.setLargeBedModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setLargeBedMode.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setLargeBedMode.out service =" + i2);
        return i2;
    }

    public int setLeftFrontReadingLampSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setLeftFrontReadingLampSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setLeftFrontReadingLampSwitchStateS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setLeftFrontReadingLampSwitchState == null");
        }
        LogUtil.verbose(str, " setLeftFrontReadingLampSwitchState.out=0");
        return 0;
    }

    public int setLeftMiddleRowRLightRotaryState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setLeftMiddleRowRLightRotaryState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setLeftMiddleRowRLightRotaryStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setLeftMiddleRowRLightRotaryState == null");
        }
        LogUtil.verbose(str, " setLeftMiddleRowRLightRotaryState.out=0");
        return 0;
    }

    public int setLeftMiddleRowSideRLightSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setLeftMiddleRowSideRLightSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setLeftMiddleRowSideRLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setLeftMiddleRowSideRLightSwitchState == null");
        }
        LogUtil.verbose(str, " setLeftMiddleRowSideRLightSwitchState.out=0");
        return 0;
    }

    public int setLeftRearRLightSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setLeftRearRLightSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setLeftRearRLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setLeftRearRLightSwitchState == null");
        }
        LogUtil.verbose(str, " setLeftRearRLightSwitchState.out=0");
        return 0;
    }

    public int setLeftSlideDoorVoiceControlState(int i) {
        LogUtil.verbose(C3, "Service setLeftSlideDoorVoiceControlState" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setLeftSlideDoorVoiceControlStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLightDelayTime(int i, int i2) {
        return 0;
    }

    public int setMandatoryElectricState(int i) {
        LogUtil.verbose(C3, "setMandatoryElectricState" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setMandatoryElectricStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMemoryElectricState(int i) {
        LogUtil.verbose(C3, "setMemoryElectricState" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setMemoryElectricStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMemoryMaxEvState(int i) {
        LogUtil.verbose(C3, "setMemoryMaxEvState" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setMemoryMaxEvStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMemorySportState(int i) {
        LogUtil.verbose(C3, "setMemorySportState" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setMemorySportStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setModeCommand(int[] iArr) {
        String str = C3;
        LogUtil.verbose(str, " setModeCommand.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setModeCommandS(iArr);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setModeCommand.null == mCarVendorEnergyManager");
        }
        LogUtil.verbose(str, " setModeCommand.out=0");
        return 0;
    }

    public int setModeCommandB(int[] iArr) {
        int i;
        String str = C3;
        LogUtil.verbose(str, " setModeCommandB.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            i = 1;
            try {
                carVendorCarbodyManager.setModeCommandBs(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setModeCommandB.null");
            i = 0;
        }
        LogUtil.verbose(str, " setModeCommandB.out service =" + i);
        return i;
    }

    public int setModeCommandC(int[] iArr) {
        int i;
        String str = C3;
        LogUtil.verbose(str, " setModeCommandC.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            i = 1;
            try {
                carVendorCarbodyManager.setModeCommandCs(iArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setModeCommandC.null");
            i = 0;
        }
        LogUtil.verbose(str, " setModeCommandC.out service =" + i);
        return i;
    }

    public int setMoonRoofType(int i) {
        return 0;
    }

    public int setMovePowerStationToggle(int i) {
        LogUtil.verbose(C3, "setMovePowerStationToggle" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setMovePowerStationToggleS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMusicModeState(int i, int i2) {
        return 0;
    }

    public int setNoSenseChargeState(int i) {
        LogUtil.verbose(C3, "setNoSenseChargeState is execute" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setNoSenseChargeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setOnSceneMode(int i) {
        String str = C3;
        LogUtil.verbose(str, "setOnSceneMode" + i);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose(str, "setOnSceneMode +in");
            this.e.setOnSceneModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setOperationMode(int i) {
        String str = C3;
        LogUtil.verbose(str, " setOperationMode.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                carVendorEnergyManager.setOperationModeS(i);
                return 0;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setOperationMode.null == mCarVendorEnergyManager");
        }
        LogUtil.verbose(str, " setOperationMode.out=-1");
        return -1;
    }

    public int setPorducePowerWhenLowPower(int i) {
        LogUtil.verbose(C3, "setPorducePowerWhenLowPower" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setProducePowerWhenLowPowerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPositionLightDisplay(int i) {
        String str = C3;
        LogUtil.verbose(str, " setPositionLightDisplay.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setPositionLightDisplayS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setPositionLightDisplay == null");
        }
        LogUtil.verbose(str, " setPositionLightDisplay.out=0");
        return 0;
    }

    public int setPreSaleModelState(int i) {
        LogUtil.verbose(C3, "setPreSaleModelState" + i);
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager == null) {
            return 0;
        }
        try {
            carVendorEnergyManager.setPreSaleModelStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPreferentialtariffSection(int i, int i2, int i3) {
        return 0;
    }

    public int setRearFogLightSwitchState(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setRearFogLightSwitchState.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setRearFogLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRearFogLightSwitchState.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setRearFogLightSwitchState.out service =" + i2);
        return i2;
    }

    public int setRightFrontReadingLampSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setRightFrontReadingLampSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setRightFrontReadingLampSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRightFrontReadingLampSwitchState == null");
        }
        LogUtil.verbose(str, " setRightFrontReadingLampSwitchState.out=0");
        return 0;
    }

    public int setRightMiddleRowRLightRotaryState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setRightMiddleRowRLightRotaryState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setRightMiddleRowRLightRotaryStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRightMiddleRowRLightRotaryState == null");
        }
        LogUtil.verbose(str, " setRightMiddleRowRLightRotaryState.out=0");
        return 0;
    }

    public int setRightMiddleRowRLightSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setRightMiddleRowRLightSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setRightMiddleRowRLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRightMiddleRowRLightSwitchState == null");
        }
        LogUtil.verbose(str, " setRightMiddleRowRLightSwitchState.out=0");
        return 0;
    }

    public int setRightRearRLightSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setRightRearRLightSwitchState.in");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            try {
                carVendorLightManager.setRightRearRLightSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRightRearRLightSwitchState == null");
        }
        LogUtil.verbose(str, " setRightRearRLightSwitchState.out=0");
        return 0;
    }

    public int setRightSlideDoorVoiceControlState(int i) {
        LogUtil.verbose(C3, "Service setRightSlideDoorVoiceControlState" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setRightSlideDoorVoiceControlStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatModeControl(int i) {
        String str = C3;
        LogUtil.verbose(str, " setSeatModeControl.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setSeatModeControlS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setSeatModeControl == null");
        }
        LogUtil.verbose(str, " setSeatModeControl.out=0");
        return 0;
    }

    public int setSeatRow3OnekeyEnterPit(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setSeatRow3OnekeyEnterPit.in service");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            i2 = 1;
            try {
                carVendorCarbodyManager.setSeatRow3OnekeyEnterPitS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setSeatRow3OnekeyEnterPit.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setSeatRow3OnekeyEnterPit.out service =" + i2);
        return i2;
    }

    public int setSideWindowShadeState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setSideWindowShadeState.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setSideWindowShadeStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setSideWindowShadeState.null == mCarVendorCarbodyManager");
        }
        LogUtil.verbose(str, " setSideWindowShadeState.out=0");
        return 0;
    }

    public int setSituPowerGenerationState(int i) {
        LogUtil.verbose(C3, "setSituPowerGenerationState is execute" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setSituPowerGenerationStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSocTarget(int i) {
        return 0;
    }

    public int setSunroofAndSunshade(int i) {
        String str = C3;
        LogUtil.verbose(str, " setSunroofAndSunshade.in" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setSunroofAndSunshadeS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.verbose(str, " setSunroofAndSunshade.out = -1");
        return -1;
    }

    public int setSyncTime(int i, int i2, int i3, int i4, int i5) {
        LogUtil.verbose(C3, "setSyncTime is " + i + "," + i2 + "," + i3 + "," + i4);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setSyncTimeYearS(i);
            this.f65a.setSyncTimeMonthS(i2);
            this.f65a.setSyncTimeDayS(i3);
            this.f65a.setSyncTimeHourS(i4);
            this.f65a.setSyncTimeMinuteS(i5);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setThemeLinkedAmbienceLightColor(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setThemeLinkedAmbienceLightColor.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setThemeLinkedAmbienceLightColorS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setThemeLinkedAmbienceLightColor.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setThemeLinkedAmbienceLightColor.out service =" + i2);
        return i2;
    }

    public int setThemeLinkedAtmosphereLampSwitch(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setThemeLinkedAtmosphereLampSwitch.in service");
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager != null) {
            i2 = 1;
            try {
                carVendorLightManager.setThemeLinkedAtmosphereLampSwitchS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setThemeLinkedAtmosphereLampSwitch.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setThemeLinkedAtmosphereLampSwitch.out service =" + i2);
        return i2;
    }

    public int setTimerCycleFri(int i) {
        return 0;
    }

    public int setTimerCycleMon(int i) {
        return 0;
    }

    public int setTimerCycleSat(int i) {
        return 0;
    }

    public int setTimerCycleSun(int i) {
        return 0;
    }

    public int setTimerCycleThu(int i) {
        return 0;
    }

    public int setTimerCycleTue(int i) {
        return 0;
    }

    public int setTimerCycleWed(int i) {
        return 0;
    }

    public int setTimerSwitch(int i) {
        return 0;
    }

    public int setTimerType(int i) {
        return 0;
    }

    public int setTimerUnitHour(int i) {
        return 0;
    }

    public int setTimerUnitMinite(int i) {
        return 0;
    }

    public int setTravellingSchedulePeriod(int i, int[] iArr) {
        return 0;
    }

    public int setTravellingScheduleTime(int i, int i2) {
        return 0;
    }

    public int setTurnLightsFlashState(int i) {
        LogUtil.verbose(C3, "setTurnLightsFlashState" + i);
        CarVendorLightManager carVendorLightManager = this.e;
        if (carVendorLightManager == null) {
            return 0;
        }
        try {
            carVendorLightManager.setTurnLightFlashStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setV2gCmd(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setV2gCmd.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            i2 = 1;
            try {
                carVendorChargingManager.setv2gCmdS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setV2gCmd.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setV2gCmd.out service =" + i2);
        return i2;
    }

    public int setV2gPower(float f) {
        int i;
        String str = C3;
        LogUtil.verbose(str, " setV2gPower.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            i = 1;
            try {
                carVendorChargingManager.setv2gPowerS(f);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setV2gPower.null");
            i = 0;
        }
        LogUtil.verbose(str, " setV2gPower.out service =" + i);
        return i;
    }

    public int setV2gStatus(int i) {
        int i2;
        String str = C3;
        LogUtil.verbose(str, " setV2gStatus.in service");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            i2 = 1;
            try {
                carVendorChargingManager.setv2gStatusS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setV2gStatus.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setV2gStatus.out service =" + i2);
        return i2;
    }

    public int setVoiceCtlMoonRoofS(int i) {
        LogUtil.verbose(C3, "setVoiceCtlMoonRoofS" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setVoiceCtlMoonRoofS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setVoiceCtlSunshadePanelS(int i) {
        LogUtil.verbose(C3, "setVoiceCtlSunshadePanelS" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setVoiceCtlSunshadePanelS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setVtoVOutCarDischargeState(int i) {
        return 0;
    }

    public int setWaistSupportAdjustmentCoPilot(int i, int i2) {
        String str = C3;
        LogUtil.verbose(str, " setWaistSupportAdjustmentCoPilot.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setWaistSupportAdjustmentCoPilotS(i2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setWaistSupportAdjustmentCoPilot == null");
        }
        LogUtil.verbose(str, " setWaistSupportAdjustmentCoPilot.out=0");
        return 0;
    }

    public int setWaistSupportAdjustmentMain(int i, int i2) {
        String str = C3;
        LogUtil.verbose(str, " setWaistSupportAdjustmentMain.in");
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setWaistSupportAdjustmentMainS(i2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setWaistSupportAdjustmentMain == null");
        }
        LogUtil.verbose(str, " setWaistSupportAdjustmentMain.out=0");
        return 0;
    }

    public int setWindowControlSend(int i, int i2) {
        LogUtil.verbose(C3, "setWindowControlState");
        if (this.f65a == null) {
            return 0;
        }
        try {
            if (i == 1) {
                this.f66b.setWindowControlStateLeftFrontS(i2);
            } else if (i == 2) {
                this.f66b.setWindowControlStateRightFrontS(i2);
            } else if (i == 3) {
                this.f66b.setWindowControlStateLeftRearS(i2);
            } else {
                if (i != 4) {
                    return 0;
                }
                this.f66b.setWindowControlStateRightRearS(i2);
            }
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowControlState(int[] iArr) {
        if (this.f66b == null) {
            return 0;
        }
        LogUtil.verbose(C3, "setWindowControlState=" + Arrays.toString(iArr));
        try {
            this.f66b.setWindowControlState(iArr);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowLockState(int i, int i2) {
        return 0;
    }

    public int setWindowOpenPercentLeftFront(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentLeftFront" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentLeftFrontS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowOpenPercentLeftRear(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentLeftRear" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentLeftRearS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowOpenPercentMoonRoof(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentMoonRoof" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentMoonS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowOpenPercentPanel(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentPanel" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentSunS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowOpenPercentRearPanel(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentRearPanel" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentRearPanelS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowOpenPercentRightFront(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentRightFront" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentRightFrontS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowOpenPercentRightRear(int i) {
        LogUtil.verbose(C3, "Service setWindowOpenPercentRightRear" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager == null) {
            return 0;
        }
        try {
            carVendorCarbodyManager.setWindowOpenPercentRightRearS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWindowsMode(int i) {
        String str = C3;
        LogUtil.verbose(str, " setWindowsMode.in" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setWindowsModeS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.verbose(str, " setWindowsMode.out = -1");
        return -1;
    }

    public int setWirelessChargingFrSwitchState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setWirelessChargingFrSwitchState.in");
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager != null) {
            try {
                carVendorChargingManager.setWirelessChargingFrSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.verbose(str, " setWirelessChargingFrSwitchState.out = " + i);
        return -1;
    }

    public int setWirelessChargingSwitchState(int i) {
        LogUtil.verbose(C3, "setWirelessChargingSwitchState" + i);
        CarVendorChargingManager carVendorChargingManager = this.f65a;
        if (carVendorChargingManager == null) {
            return 0;
        }
        try {
            carVendorChargingManager.setWirelessChargingSwitchStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setiTacMode(int i) {
        String str = C3;
        LogUtil.verbose(str, " setiTacMode.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                carVendorEnergyManager.setiTacModeS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setiTacMode.null == mCarVendorEnergyManager");
        }
        LogUtil.verbose(str, " setiTacMode.out=0");
        return 0;
    }

    public int setiTacpState(int i) {
        String str = C3;
        LogUtil.verbose(str, " setiTacpState.in");
        CarVendorEnergyManager carVendorEnergyManager = this.c;
        if (carVendorEnergyManager != null) {
            try {
                carVendorEnergyManager.setiTacpStateS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setiTacpState.null == mCarVendorEnergyManager");
        }
        LogUtil.verbose(str, " setiTacpState.out=0");
        return 0;
    }

    public void settingLuggage(int i) {
        LogUtil.verbose(C3, "Service settingLuggage" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setSettingLuggageS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean subscribeCarBodyCallback(ICarBodyCallback iCarBodyCallback) {
        if (this.g == null || iCarBodyCallback == null) {
            return false;
        }
        LogUtil.verbose(C3, "into subscribe: " + iCarBodyCallback.toString());
        this.g.h(iCarBodyCallback);
        if (!this.I) {
            return true;
        }
        this.g.l();
        return true;
    }

    public void turnRearviewMirror(int i) {
        LogUtil.verbose(C3, "turnRearviewMirror" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setturnRearviewMirror(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSteeringWheel(int i) {
        LogUtil.verbose(C3, "turnSteeringWheel" + i);
        CarVendorCarbodyManager carVendorCarbodyManager = this.f66b;
        if (carVendorCarbodyManager != null) {
            try {
                carVendorCarbodyManager.setturnSteeringWheel(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void u() {
        com.ts.appservice.caradapter.a.b bVar = this.g;
        if (bVar != null) {
            this.I = false;
            bVar.g();
        }
        E();
    }

    public boolean unsubscribeCarBodyCallback(ICarBodyCallback iCarBodyCallback) {
        if (this.g == null || iCarBodyCallback == null) {
            return false;
        }
        LogUtil.verbose(C3, "into unsubscribe: " + iCarBodyCallback.toString());
        this.g.i(iCarBodyCallback);
        return true;
    }

    public void x(CarVendorChargingManager carVendorChargingManager, CarVendorCarbodyManager carVendorCarbodyManager, CarVendorEnergyManager carVendorEnergyManager, CarVendorEngineManager carVendorEngineManager, CarVendorTyreManager carVendorTyreManager, CarVendorLightManager carVendorLightManager) {
        this.c = carVendorEnergyManager;
        this.f65a = carVendorChargingManager;
        this.f66b = carVendorCarbodyManager;
        this.d = carVendorEngineManager;
        this.f = carVendorTyreManager;
        this.e = carVendorLightManager;
        try {
            if (this.j == null) {
                this.j = new a(this.i.getLooper());
                LogUtil.info(C3, "initBodyManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.b bVar = this.g;
        if (bVar != null) {
            this.I = true;
            bVar.l();
        }
    }
}
