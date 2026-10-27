package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: UiStyleConfigService.java */
/* JADX INFO: loaded from: classes2.dex */
public class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile u f1303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoStudy f1304b;

    /* JADX INFO: compiled from: UiStyleConfigService.java */
    public class a extends BYDAutoStudyListener {
        public a() {
        }

        @Override // android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener, a.b.d
        public void isConnected(boolean z) {
            BydLogHelper.logD("isConnected:" + z);
        }
    }

    public static u I() {
        if (f1303a == null) {
            synchronized (u.class) {
                if (f1303a == null) {
                    f1303a = new u();
                }
            }
        }
        return f1303a;
    }

    public boolean A() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        return bYDAutoStudy != null && bYDAutoStudy.getDynamicSuspensionModeByCan() == 1;
    }

    public boolean B() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        return bYDAutoStudy != null && bYDAutoStudy.getDynamicSuspensionModeByCanFD() == 1;
    }

    public int C() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getEPedalStateSupport();
        }
        return 0;
    }

    public int D() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getEnergyFeedbackSupport();
        }
        return 0;
    }

    public int E() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getEnergyShowSupport();
        }
        return 0;
    }

    public int F() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getEngineSoundSupport();
        }
        return 1;
    }

    public int G() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getHeadlampLevelConfig();
        }
        return 0;
    }

    public int H() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getHeadlampSupport();
        }
        return 0;
    }

    public int J() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getInstrumentHeightSupport();
        }
        BydLogHelper.logE("getInstrumentHeightSupport", " mUiStyleConfig : is null");
        return 1;
    }

    public int K() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getIntelligentAcAreaSupport();
        }
        return 0;
    }

    public int L() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getIntelligentKeepTemSupport();
        }
        return 1;
    }

    public int M() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy == null) {
            return 0;
        }
        int leftPlatformConfigs = bYDAutoStudy.getLeftPlatformConfigs();
        BydLogHelper.logD("getLeftPlatformConfigs result " + leftPlatformConfigs);
        return leftPlatformConfigs;
    }

    public int N() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getMainDriveElectricHeadrestAdjustmentByGCtrl();
        }
        return 0;
    }

    public int O() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
        }
        return 0;
    }

    public int P() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
        }
        return 0;
    }

    public int Q() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy == null) {
            return 0;
        }
        int mileageResetConfig = bYDAutoStudy.getMileageResetConfig();
        BydLogHelper.logD(u.class.getSimpleName(), " getMileageResetConfig  : " + mileageResetConfig);
        return mileageResetConfig;
    }

    public int R() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getNoSenseChargeSupport();
        }
        return 0;
    }

    public int S() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getOBCType();
        }
        return 1;
    }

    public int T() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getPassengerSeatHeadrestConfigurationByECU();
        }
        return 0;
    }

    public int U() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getPassengerSeatHeadrestConfigurationByGCtrl();
        }
        return 0;
    }

    public int V() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
        }
        return 0;
    }

    public int W() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getPreSaleModelSupport();
        }
        return 1;
    }

    public int X() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
        }
        return 0;
    }

    public int Y() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getRearRightHeadrestLevelAdjustmentConfigurationByECU();
        }
        return 0;
    }

    public int Z() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getRefrigeratorModeSupport();
        }
        return 0;
    }

    public int a() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getAmbientAreaSupport();
        }
        return 1;
    }

    public int a0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSandModeAbsConfig();
        }
        return 0;
    }

    public int b() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getAmbientLightColorSupport();
        }
        return 1;
    }

    public int b0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSteerAssisSupport();
        }
        return 0;
    }

    public int c() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getAmbientLightSupport();
        }
        return 1;
    }

    public int c0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSteeringWheelSupport();
        }
        return 0;
    }

    public int d() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getAutoWiperSupport();
        }
        return 0;
    }

    public int d0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportAcChargingLimit();
        }
        return 0;
    }

    public int e() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getBatteryHealthConfig();
        }
        return 0;
    }

    public int e0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportAFS();
        }
        return 0;
    }

    public int f() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getBrakePedalModeSupport();
        }
        return 0;
    }

    public int f0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportExtreme();
        }
        return 0;
    }

    public int g() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getCabinMovementMonitoringFunction();
        }
        return 0;
    }

    public int g0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportLampSingnal();
        }
        return 0;
    }

    public int h() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getCarPlanSupport();
        }
        return 0;
    }

    public int h0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportPM25Mode();
        }
        return 0;
    }

    public int i() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getCarType();
        }
        return 1;
    }

    public int i0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportPanoramaImage();
        }
        return 1;
    }

    public int j() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getChargeLimitSupport();
        }
        return 1;
    }

    public int j0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy == null) {
            return 0;
        }
        int supportPredictCrashAlertMode = bYDAutoStudy.getSupportPredictCrashAlertMode();
        BydLogHelper.logD("getSupportPredictCrashAlertMode result " + supportPredictCrashAlertMode);
        return supportPredictCrashAlertMode;
    }

    public int k() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getChargeLimitSupportNotLi();
        }
        return 0;
    }

    public int k0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportSmrm();
        }
        return 0;
    }

    public int l() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getChargePreheatSupport();
        }
        return 0;
    }

    public int l0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportSmartChargingPHEV();
        }
        return 0;
    }

    public int m() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getChargeToaggleGuardSupport();
        }
        return 1;
    }

    public int m0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportiTac();
        }
        return 1;
    }

    public boolean n() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        return bYDAutoStudy != null && bYDAutoStudy.getComfortableParkingBy37F() == 1;
    }

    public int n0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getSupportiTacConfig();
        }
        return 1;
    }

    public int o() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getComfortableParkingSupport();
        }
        return 0;
    }

    public boolean o0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        return bYDAutoStudy != null && bYDAutoStudy.getTrailerModeConfig() == 2;
    }

    public int p() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getCpdSwMemStatus();
        }
        return 0;
    }

    public int p0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getUsherLightSupport();
        }
        return 0;
    }

    public int q() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getCuraAssembleStatusFunction();
        }
        return 0;
    }

    public int q0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getWadingModeSetFlag();
        }
        return 1;
    }

    public int r() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDMType();
        }
        return 1;
    }

    public boolean r0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        return bYDAutoStudy != null && bYDAutoStudy.getIntelligentSpeedLimitMapVersionDisplay() == 1;
    }

    public int s() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDiDynaConfig();
        }
        return 0;
    }

    public void s0(Context context) {
        BYDAutoStudy bYDAutoStudy = BYDAutoStudy.getInstance(context);
        this.f1304b = bYDAutoStudy;
        bYDAutoStudy.registerConnectListener(new a());
    }

    public int t() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDiSusShowStateByCan();
        }
        return 0;
    }

    public boolean t0() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        return bYDAutoStudy != null && bYDAutoStudy.getCpdSupport() == 1;
    }

    public int u() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDiSusShowStateByCanFD();
        }
        return 0;
    }

    public int v() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDigtalKeySupport();
        }
        return 0;
    }

    public int w() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDischargeSupport();
        }
        return 0;
    }

    public int x() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDriveConfig();
        }
        return 1;
    }

    public int y() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDriveDistanceShowModeSupport();
        }
        return 0;
    }

    public int z() {
        BYDAutoStudy bYDAutoStudy = this.f1304b;
        if (bYDAutoStudy != null) {
            return bYDAutoStudy.getDriveDistanceShowStandardSupport();
        }
        return 0;
    }
}
