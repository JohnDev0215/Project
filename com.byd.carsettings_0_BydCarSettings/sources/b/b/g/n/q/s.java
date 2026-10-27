package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoVehicleHealthDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static s f1299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoVehicleHealthDevice f1300b;

    public static synchronized s g() {
        if (f1299a == null) {
            f1299a = new s();
        }
        return f1299a;
    }

    public int a() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getBatteryHealthStatus();
        }
        return 0;
    }

    public int b() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getEPBConfig();
        }
        return 0;
    }

    public int c() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getEPBHint();
        }
        return 0;
    }

    public int d() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getEPBState();
        }
        return 0;
    }

    public int e() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getEPBType();
        }
        return 0;
    }

    public int f() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getFrontWiperCheckState();
        }
        return 0;
    }

    public int h() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getMileageValid();
        }
        return 0;
    }

    public int i() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getOilHint();
        }
        return 0;
    }

    public int j() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getOilLevel();
        }
        return 0;
    }

    public int k(int i) {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getOilLifetime(i);
        }
        return 0;
    }

    public int l() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getOilUpdateState();
        }
        return 0;
    }

    public int m() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getRearWiperCheckState();
        }
        return 0;
    }

    public int n() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getTransportMode();
        }
        return 0;
    }

    public int o() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getTransportModeStateHint();
        }
        return 0;
    }

    public int p() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.getTyreFaultLocation();
        }
        return 0;
    }

    public void q(Context context) {
        this.f1300b = BYDAutoVehicleHealthDevice.getInstance(context);
    }

    public void r(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        if (this.f1300b == null) {
            return;
        }
        String simpleName = s.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydVehicleHealthListener start listener == null : ");
        sb.append(absBYDAutoVehicleHealthListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1300b.registerListener(absBYDAutoVehicleHealthListener);
    }

    public int s(int i) {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.resetOilLifetime(i);
        }
        return 0;
    }

    public int t(int i, int i2) {
        b.b.g.r.e.k(200006, i == 1 ? 2 : 1, i2);
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.setEPBState(i);
        }
        return 0;
    }

    public int u(int i) {
        b.b.g.r.e.j(200000, i == 1 ? 2 : 1);
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.setFrontWiperCheckState(i);
        }
        return 0;
    }

    public int v(int i) {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.setOilUpdateTag(i);
        }
        return 0;
    }

    public int w(int i) {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.setPowerOffChargedState(i);
        }
        return 0;
    }

    public int x(int i) {
        b.b.g.r.e.j(200001, i == 1 ? 2 : 1);
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.setRearWiperCheckState(i);
        }
        return 0;
    }

    public int y(int i) {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = this.f1300b;
        if (bYDAutoVehicleHealthDevice != null) {
            return bYDAutoVehicleHealthDevice.setTransportMode(i);
        }
        return 0;
    }

    public void z(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        if (this.f1300b == null) {
            return;
        }
        String simpleName = s.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydVehicleHealthListener start listener == null : ");
        sb.append(absBYDAutoVehicleHealthListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1300b.unregisterListener(absBYDAutoVehicleHealthListener);
    }
}
