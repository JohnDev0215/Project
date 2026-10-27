package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoOtaDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile j f1280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoOtaDevice f1281b;

    public static j a() {
        if (f1280a == null) {
            synchronized (j.class) {
                if (f1280a == null) {
                    f1280a = new j();
                }
            }
        }
        return f1280a;
    }

    public int b() {
        BYDAutoOtaDevice bYDAutoOtaDevice = this.f1281b;
        if (bYDAutoOtaDevice != null) {
            return bYDAutoOtaDevice.getSocValue();
        }
        return 0;
    }

    public void c(Context context) {
        this.f1281b = BYDAutoOtaDevice.getInstance(context);
    }

    public void d(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        if (this.f1281b != null) {
            String simpleName = j.class.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("registerListener BydOtaListener start listener == null : ");
            sb.append(absBYDAutoOtaListener == null);
            BydLogHelper.logD(simpleName, sb.toString());
            this.f1281b.registerListener(absBYDAutoOtaListener);
        }
    }

    public void e(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        if (this.f1281b != null) {
            String simpleName = j.class.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("unregisterListener BydOtaListener start listener == null : ");
            sb.append(absBYDAutoOtaListener == null);
            BydLogHelper.logD(simpleName, sb.toString());
            this.f1281b.unregisterListener(absBYDAutoOtaListener);
        }
    }
}
