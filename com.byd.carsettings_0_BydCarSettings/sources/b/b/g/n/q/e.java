package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoEnergyDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e f1270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoEnergyDevice f1271b;

    public static synchronized e j() {
        if (f1270a == null) {
            f1270a = new e();
        }
        return f1270a;
    }

    public int A() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getiTacpAllowTag();
        }
        return 0;
    }

    public int B() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getiTacpState();
        }
        return 0;
    }

    public void C(Context context) {
        this.f1271b = BYDAutoEnergyDevice.getInstance(context);
    }

    public void D(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (this.f1271b == null) {
            return;
        }
        String simpleName = e.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydEnergyListener startlistener == null : ");
        sb.append(absBYDAutoEnergyListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1271b.registerListener(absBYDAutoEnergyListener);
    }

    public int E(int i) {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.resetCurveData(i);
        }
        return 0;
    }

    public int F(int i) {
        b.b.g.r.e.j(150018, i == 1 ? 2 : 1);
        if (this.f1271b == null) {
            return 0;
        }
        BydLogHelper.logI("BYDAutoEnergyDeviceService", "setDriveDistanceShowMode----" + i);
        return this.f1271b.setDriveDistanceShowMode(i);
    }

    public int G(int i) {
        b.b.g.r.e.j(150012, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setDriveDistanceShowStandard(i);
        }
        return 0;
    }

    public int H(int i) {
        b.b.g.r.e.j(150009, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setEPedalState(i);
        }
        return 0;
    }

    public int I(int i) {
        b.b.g.r.e.j(150010, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setElectricShowMode(i);
        }
        return 0;
    }

    public int J(int i) {
        b.b.g.r.e.j(150011, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setMandatoryElectricState(i);
        }
        return 0;
    }

    public int K(int i) {
        b.b.g.r.e.j(150019, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setMemoryElectricState(i);
        }
        return 0;
    }

    public int L(int i) {
        b.b.g.r.e.j(150014, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setMemoryMaxEvState(i);
        }
        return 0;
    }

    public int M(int i) {
        b.b.g.r.e.j(150013, i == 1 ? 2 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setMemorySportState(i);
        }
        return 0;
    }

    public int N(int i) {
        b.b.g.r.e.j(150020, i == 2 ? 0 : 1);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setPreSaleModelState(i);
        }
        return 0;
    }

    public int O(int i) {
        b.b.g.r.e.j(150028, i);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setiTacMode(i);
        }
        return 0;
    }

    public int P(int i) {
        b.b.g.r.e.j(150025, i);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.setiTacpState(i);
        }
        return 0;
    }

    public void Q(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (this.f1271b == null) {
            return;
        }
        String simpleName = e.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydEnergyListener start listener == null : ");
        sb.append(absBYDAutoEnergyListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1271b.unregisterListener(absBYDAutoEnergyListener);
    }

    public int a() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice == null) {
            return 0;
        }
        int driveDistanceShowMode = bYDAutoEnergyDevice.getDriveDistanceShowMode();
        BydLogHelper.logI("BYDAutoEnergyDeviceService", "getDriveDistanceShowMode----" + driveDistanceShowMode);
        return driveDistanceShowMode;
    }

    public int b() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getDriveDistanceShowStandard();
        }
        return 0;
    }

    public int c() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getEPedalState();
        }
        return 0;
    }

    public int d() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getElectricPointFlag();
        }
        return 0;
    }

    public int e() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getElectricShowMode();
        }
        return 0;
    }

    public int f() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getEnergyMode();
        }
        return 0;
    }

    public int g() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getEnergyState();
        }
        return 0;
    }

    public int h() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getHEVElectricPointFlag();
        }
        return 0;
    }

    public float i() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getHEVLast200MElectricCon();
        }
        return 0.0f;
    }

    public float k() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getLast200MElectricCon();
        }
        return 0.0f;
    }

    public float l() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getLast200MOilCon();
        }
        return 0.0f;
    }

    public float m() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getLast50KMElectricConAvg();
        }
        return 0.0f;
    }

    public float n() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getLast50KMOilConAvg();
        }
        return 0.0f;
    }

    public int o() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getMandatoryElectricState();
        }
        return 0;
    }

    public int p() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getMemoryElectricState();
        }
        return 0;
    }

    public int q() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getMemoryMaxEvState();
        }
        return 0;
    }

    public int r() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getMemorySportState();
        }
        return 0;
    }

    public int s() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getOilPointFlag();
        }
        return 0;
    }

    public int t() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getOperationMode();
        }
        return 0;
    }

    public int u() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getPowerGenerationState();
        }
        return 0;
    }

    public int v() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getPowerGenerationValue();
        }
        return 0;
    }

    public int w() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getPreSaleModelState();
        }
        return 0;
    }

    public int x() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getRoadSurfaceMode();
        }
        return 0;
    }

    public int y() {
        if (this.f1271b == null) {
            return 0;
        }
        BydLogHelper.logD(e.class.getSimpleName(), "getRollBenchModeStatus  : " + this.f1271b.getRollBenchModeStatus());
        return this.f1271b.getRollBenchModeStatus();
    }

    public int z() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f1271b;
        if (bYDAutoEnergyDevice != null) {
            return bYDAutoEnergyDevice.getiTacMode();
        }
        return 0;
    }
}
