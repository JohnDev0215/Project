package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoInstrumentDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static h f1276a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoInstrumentDevice f1277b;

    public static synchronized h m() {
        if (f1276a == null) {
            f1276a = new h();
        }
        return f1276a;
    }

    public int A() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getSportModeState();
        }
        return 0;
    }

    public double B() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return ((double) bYDAutoInstrumentDevice.getTotalMileage()) * 0.1d;
        }
        return 0.0d;
    }

    public int C(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getUnit(i);
        }
        return 0;
    }

    public void D(Context context) {
        this.f1277b = BYDAutoInstrumentDevice.getInstance(context);
    }

    public int E(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.notifyMapState(i);
        }
        return 0;
    }

    public int F(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.notifyScreenStatus(i);
        }
        return 0;
    }

    public void G(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (this.f1277b == null) {
            return;
        }
        String simpleName = h.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydInstrumentListener start listener == null : ");
        sb.append(absBYDAutoInstrumentListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1277b.registerListener(absBYDAutoInstrumentListener);
    }

    public int H(String str, String str2, int i, int i2) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.sendCallInfo(str, str2, i, i2);
        }
        return 0;
    }

    public int I(String str, String str2, int i, int i2, String str3) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.sendMusicInfoUrl(str, str2, i, i2, str3);
        }
        return 0;
    }

    public int J(int i, int i2) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.sendMusicProgress(i, i2);
        }
        return 0;
    }

    public int K(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.sendMusicState(i);
        }
        return 0;
    }

    public int L(int i, int i2) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.sendNaviPresentationState(i, i2);
        }
        return 0;
    }

    public int M(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setAreaInfo(i);
        }
        return 0;
    }

    public int N(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setBacklightBrightness(i);
        }
        return 0;
    }

    public int O(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setDayNightMode(i);
        }
        return 0;
    }

    public int P(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setINSAdasCenterDisplay(i);
        }
        return 0;
    }

    public int Q(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setINSCenterDisplay(i);
        }
        return 0;
    }

    public int R(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setInsNaviDisplayMode(i);
        }
        return 0;
    }

    public int S(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setInstrumentAutoDimmState(i);
        }
        return 0;
    }

    public int T(int i, int i2) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setMaintenanceInfo(i, i2);
        }
        return 0;
    }

    public int U(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setMilesType(i);
        }
        return 0;
    }

    public int V(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setSpeedUpCountState(i);
        }
        return 0;
    }

    public int W(int i, int i2) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.setUnit(i, i2);
        }
        return 0;
    }

    public void X(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (this.f1277b == null) {
            return;
        }
        String simpleName = h.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydInstrumentListener start listener == null : ");
        sb.append(absBYDAutoInstrumentListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1277b.unregisterListener(absBYDAutoInstrumentListener);
    }

    public int a(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.clearDistanceMilesType(i);
        }
        return 0;
    }

    public int b() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getAlarmBuzzleState();
        }
        return 0;
    }

    public int c() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getAreaInfo();
        }
        return 0;
    }

    public int d() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getBacklightBrightness();
        }
        return 0;
    }

    public int e() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getDayNightMode();
        }
        return 0;
    }

    public int f() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getDistanceMileageUnit();
        }
        return 0;
    }

    public double g() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getExternalChargePower();
        }
        return 0.0d;
    }

    public double h() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getExternalChargingPower();
        }
        return 0.0d;
    }

    public int i() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getFocusMapType();
        }
        return 0;
    }

    public int j() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getINSAdasCenterDisplay();
        }
        return 0;
    }

    public int k() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getINSCenterDisplay();
        }
        return 0;
    }

    public int l() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getInsNaviDisplayMode();
        }
        return 0;
    }

    public int n() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getInstrumentAutoDimmState();
        }
        return 0;
    }

    public int o() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getInstrumentConfigure();
        }
        return 0;
    }

    public int p() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getInstrumentScreenType();
        }
        return 0;
    }

    public int q() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getLowFuelWarnLightColor();
        }
        return 0;
    }

    public int r() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getLowPowerBatteryWarnLightColor();
        }
        return 0;
    }

    public int s(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getMaintenanceInfo(i);
        }
        return 0;
    }

    public int t(int i) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getMalfunctionInfo(i);
        }
        return 0;
    }

    public int[] u() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        return bYDAutoInstrumentDevice != null ? bYDAutoInstrumentDevice.getMalfunctionInfos() : new int[0];
    }

    public int v() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getMileageUnit();
        }
        return 0;
    }

    public int w() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getMilesType();
        }
        return 0;
    }

    public int x() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getSpeedUnit();
        }
        return 0;
    }

    public int y() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getSpeedUpCountState();
        }
        return 0;
    }

    public int z() {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.f1277b;
        if (bYDAutoInstrumentDevice != null) {
            return bYDAutoInstrumentDevice.getSpeedUpFormIns();
        }
        return 0;
    }
}
