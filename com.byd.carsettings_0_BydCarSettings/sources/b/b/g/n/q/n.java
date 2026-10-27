package b.b.g.n.q;

import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoSpeedDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile n f1288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoSpeedDevice f1289b;

    public n() {
        d();
    }

    public static n b() {
        if (f1288a == null) {
            synchronized (n.class) {
                if (f1288a == null) {
                    f1288a = new n();
                }
            }
        }
        return f1288a;
    }

    public double a() {
        BYDAutoSpeedDevice bYDAutoSpeedDevice = this.f1289b;
        if (bYDAutoSpeedDevice != null) {
            return bYDAutoSpeedDevice.getCurrentSpeed();
        }
        return 0.0d;
    }

    public double c() {
        if (this.f1289b != null) {
            return r0.getSpeedValue();
        }
        return 0.0d;
    }

    public void d() {
        if (this.f1289b == null) {
            this.f1289b = BYDAutoSpeedDevice.getInstance(b.b.a.a.a.b().a());
        }
    }

    public void e(AbsBYDAutoSpeedListener absBYDAutoSpeedListener) {
        if (this.f1289b != null) {
            String simpleName = n.class.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("registerListener BydSpeedListener start listener == null : ");
            sb.append(absBYDAutoSpeedListener == null);
            BydLogHelper.logD(simpleName, sb.toString());
            this.f1289b.registerListener(absBYDAutoSpeedListener);
        }
    }

    public void f(AbsBYDAutoSpeedListener absBYDAutoSpeedListener) {
        if (this.f1289b != null) {
            String simpleName = n.class.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("unregisterListener BydSpeedListener start listener == null : ");
            sb.append(absBYDAutoSpeedListener == null);
            BydLogHelper.logD(simpleName, sb.toString());
            this.f1289b.unregisterListener(absBYDAutoSpeedListener);
        }
    }
}
