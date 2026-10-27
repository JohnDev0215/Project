package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoAcDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f1260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoAcDevice f1261b;

    public static synchronized a a() {
        if (f1260a == null) {
            f1260a = new a();
        }
        return f1260a;
    }

    public int b() {
        return this.f1261b.getTemperatureUnit();
    }

    public void c(Context context) {
        this.f1261b = BYDAutoAcDevice.getInstance(context);
    }

    public void d(AbsBYDAutoAcListener absBYDAutoAcListener) {
        if (this.f1261b == null) {
            return;
        }
        String simpleName = a.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydAcListener start listener == null : ");
        sb.append(absBYDAutoAcListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1261b.registerListener(absBYDAutoAcListener);
    }

    public void e(AbsBYDAutoAcListener absBYDAutoAcListener) {
        if (this.f1261b == null) {
            return;
        }
        String simpleName = a.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydAcListener start listener == null : ");
        sb.append(absBYDAutoAcListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1261b.unregisterListener(absBYDAutoAcListener);
    }
}
