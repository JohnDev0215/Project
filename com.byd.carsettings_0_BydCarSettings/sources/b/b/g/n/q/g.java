package b.b.g.n.q;

import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoGearboxDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile g f1274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoGearboxDevice f1275b;

    public g() {
        h();
    }

    public static g g() {
        if (f1274a == null) {
            synchronized (g.class) {
                if (f1274a == null) {
                    f1274a = new g();
                }
            }
        }
        return f1274a;
    }

    public int a() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f1275b;
        if (bYDAutoGearboxDevice == null) {
            return 0;
        }
        int ePBState = bYDAutoGearboxDevice.getEPBState();
        BydLogHelper.logD("getEPBState " + ePBState);
        return ePBState;
    }

    public int b() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f1275b;
        if (bYDAutoGearboxDevice != null) {
            return bYDAutoGearboxDevice.getGear();
        }
        return 0;
    }

    public int c() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f1275b;
        if (bYDAutoGearboxDevice != null) {
            return bYDAutoGearboxDevice.getGearboxAutoModeLevel();
        }
        return 0;
    }

    public int d() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f1275b;
        if (bYDAutoGearboxDevice != null) {
            return bYDAutoGearboxDevice.getGearboxAutoModeType();
        }
        return 0;
    }

    public int e() {
        if (this.f1275b == null) {
            return -1;
        }
        boolean zG = b.c().g();
        BydLogHelper.logD("GearListener", " engineType = " + zG);
        return !zG ? this.f1275b.getGearboxAutoModeLevel() : this.f1275b.getGear();
    }

    public int f() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f1275b;
        if (bYDAutoGearboxDevice != null) {
            return bYDAutoGearboxDevice.getGearboxType();
        }
        return 0;
    }

    public final void h() {
        if (this.f1275b == null) {
            this.f1275b = BYDAutoGearboxDevice.getInstance(b.b.a.a.a.b().a());
        }
    }

    public void i(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (this.f1275b == null) {
            return;
        }
        String simpleName = g.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydGearboxListener start listener == null : ");
        sb.append(absBYDAutoGearboxListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1275b.registerListener(absBYDAutoGearboxListener);
    }

    public int j(int i) {
        BydLogHelper.logD("setEPBState " + i);
        b.b.g.r.e.j(220004, 0);
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f1275b;
        if (bYDAutoGearboxDevice == null) {
            return 0;
        }
        int ePBState = bYDAutoGearboxDevice.setEPBState(i);
        BydLogHelper.logD("setEPBState result " + ePBState);
        return ePBState;
    }

    public void k(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (this.f1275b == null) {
            return;
        }
        String simpleName = g.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydGearboxListener start listener == null : ");
        sb.append(absBYDAutoGearboxListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1275b.unregisterListener(absBYDAutoGearboxListener);
    }
}
