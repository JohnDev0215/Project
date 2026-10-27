package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoTyreDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static r f1297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoTyreDevice f1298b;

    public static synchronized r b() {
        if (f1297a == null) {
            f1297a = new r();
        }
        return f1297a;
    }

    public int a() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getIndirectTyreSystemState();
        }
        return 0;
    }

    public int c(int i) {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreAirLeakState(i);
        }
        return 0;
    }

    public int d() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreBatteryState();
        }
        return 0;
    }

    public double e(int i) {
        if (this.f1298b != null) {
            return r0.getTyreBatteryValue(i);
        }
        return 0.0d;
    }

    public int f() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreBodyType();
        }
        return 0;
    }

    public int g(int i) {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyrePressureState(i);
        }
        return 0;
    }

    public int h() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyrePressureSysFailHint();
        }
        return 0;
    }

    public int i(int i) {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyrePressureValue(i);
        }
        return 0;
    }

    public int j(int i) {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyrePressureWarn(i);
        }
        return 0;
    }

    public int k() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyrePressureWarnHint();
        }
        return 0;
    }

    public int l(int i) {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreSignalState(i);
        }
        return 0;
    }

    public int m() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreSystemState();
        }
        return 0;
    }

    public int n() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreTemperatureState();
        }
        return 0;
    }

    public int o() {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.getTyreTurnState();
        }
        return 0;
    }

    public void p(Context context) {
        this.f1298b = BYDAutoTyreDevice.getInstance(context);
    }

    public void q(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        if (this.f1298b == null) {
            return;
        }
        String simpleName = r.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydTyreListener start listener == null : ");
        sb.append(absBYDAutoTyreListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1298b.registerListener(absBYDAutoTyreListener);
    }

    public int r(int i) {
        BYDAutoTyreDevice bYDAutoTyreDevice = this.f1298b;
        if (bYDAutoTyreDevice != null) {
            return bYDAutoTyreDevice.setIndirectTyreConfirm(i);
        }
        return 0;
    }

    public void s(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        if (this.f1298b == null) {
            return;
        }
        String simpleName = r.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydTyreListener start listener == null : ");
        sb.append(absBYDAutoTyreListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1298b.unregisterListener(absBYDAutoTyreListener);
    }
}
