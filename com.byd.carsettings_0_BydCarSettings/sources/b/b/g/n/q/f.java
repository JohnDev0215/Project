package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoEngineDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static f f1272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoEngineDevice f1273b;

    public static synchronized f g() {
        if (f1272a == null) {
            f1272a = new f();
        }
        return f1272a;
    }

    public String a() {
        BYDAutoEngineDevice bYDAutoEngineDevice = this.f1273b;
        if (bYDAutoEngineDevice != null) {
            return bYDAutoEngineDevice.getEngineCode();
        }
        return null;
    }

    public int b() {
        BYDAutoEngineDevice bYDAutoEngineDevice = this.f1273b;
        if (bYDAutoEngineDevice != null) {
            return bYDAutoEngineDevice.getEngineCondition();
        }
        return 0;
    }

    public int c() {
        BYDAutoEngineDevice bYDAutoEngineDevice = this.f1273b;
        if (bYDAutoEngineDevice != null) {
            return bYDAutoEngineDevice.getEngineCoolantLevel();
        }
        return 0;
    }

    public double d() {
        if (this.f1273b != null) {
            return r0.getEngineDisplacement();
        }
        return 0.0d;
    }

    public int e() {
        BYDAutoEngineDevice bYDAutoEngineDevice = this.f1273b;
        if (bYDAutoEngineDevice != null) {
            return bYDAutoEngineDevice.getEnginePower();
        }
        return 0;
    }

    public int f() {
        BYDAutoEngineDevice bYDAutoEngineDevice = this.f1273b;
        if (bYDAutoEngineDevice != null) {
            return bYDAutoEngineDevice.getEngineSpeed();
        }
        return 0;
    }

    public int h() {
        BYDAutoEngineDevice bYDAutoEngineDevice = this.f1273b;
        if (bYDAutoEngineDevice != null) {
            return bYDAutoEngineDevice.getOilLevel();
        }
        return 0;
    }

    public void i(Context context) {
        this.f1273b = BYDAutoEngineDevice.getInstance(context);
    }

    public void j(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (this.f1273b == null) {
            return;
        }
        String simpleName = f.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydEngineListener start listener == null : ");
        sb.append(absBYDAutoEngineListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1273b.registerListener(absBYDAutoEngineListener);
    }

    public void k(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (this.f1273b == null) {
            return;
        }
        String simpleName = f.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydEngineListener start listener == null : ");
        sb.append(absBYDAutoEngineListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1273b.unregisterListener(absBYDAutoEngineListener);
    }
}
