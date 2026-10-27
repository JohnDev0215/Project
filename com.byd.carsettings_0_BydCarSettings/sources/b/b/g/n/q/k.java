package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoRadarDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static k f1282a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoRadarDevice f1283b;

    public static synchronized k c() {
        if (f1282a == null) {
            f1282a = new k();
        }
        return f1282a;
    }

    public int[] a() {
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        return bYDAutoRadarDevice != null ? bYDAutoRadarDevice.getAllRadarDistance() : new int[0];
    }

    public int[] b() {
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        return bYDAutoRadarDevice != null ? bYDAutoRadarDevice.getAllRadarProbeStates() : new int[0];
    }

    public int d(int i) {
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        if (bYDAutoRadarDevice != null) {
            return bYDAutoRadarDevice.getRadarObstacleDistance(i);
        }
        return 0;
    }

    public int e(int i) {
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        if (bYDAutoRadarDevice != null) {
            return bYDAutoRadarDevice.getRadarProbeState(i);
        }
        return 0;
    }

    public int f() {
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        if (bYDAutoRadarDevice != null) {
            return bYDAutoRadarDevice.getReverseRadarKeyState();
        }
        return 0;
    }

    public int g() {
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        if (bYDAutoRadarDevice != null) {
            return bYDAutoRadarDevice.getReverseRadarSwitchState();
        }
        return 0;
    }

    public void h(Context context) {
        this.f1283b = BYDAutoRadarDevice.getInstance(context);
    }

    public void i(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        if (this.f1283b == null) {
            return;
        }
        String simpleName = k.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydRadarListener start listener == null : ");
        sb.append(absBYDAutoRadarListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1283b.registerListener(absBYDAutoRadarListener);
    }

    public int j(int i) {
        b.b.g.r.e.i(160000);
        BYDAutoRadarDevice bYDAutoRadarDevice = this.f1283b;
        if (bYDAutoRadarDevice != null) {
            return bYDAutoRadarDevice.setReverseRadarSwitchState(i);
        }
        return 0;
    }

    public void k(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        if (this.f1283b == null) {
            return;
        }
        String simpleName = k.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydRadarListener start listener == null : ");
        sb.append(absBYDAutoRadarListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1283b.unregisterListener(absBYDAutoRadarListener);
    }
}
