package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.hardware.bydauto.sensor.BYDAutoSensorDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoSensorDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static l f1284a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoSensorDevice f1285b;

    public static synchronized l b() {
        if (f1284a == null) {
            f1284a = new l();
        }
        return f1284a;
    }

    public double a() {
        if (this.f1285b != null) {
            return r0.getHumiditySensorValue();
        }
        return 0.0d;
    }

    public int c() {
        BYDAutoSensorDevice bYDAutoSensorDevice = this.f1285b;
        if (bYDAutoSensorDevice != null) {
            return bYDAutoSensorDevice.getLightIntensity();
        }
        return 0;
    }

    public byte[] d() {
        BYDAutoSensorDevice bYDAutoSensorDevice = this.f1285b;
        return bYDAutoSensorDevice != null ? bYDAutoSensorDevice.getSensorAxValue() : new byte[0];
    }

    public int e() {
        BYDAutoSensorDevice bYDAutoSensorDevice = this.f1285b;
        if (bYDAutoSensorDevice != null) {
            return bYDAutoSensorDevice.getSlope();
        }
        return 0;
    }

    public double f() {
        if (this.f1285b != null) {
            return r0.getTemperatureSensorValue();
        }
        return 0.0d;
    }

    public void g(Context context) {
        this.f1285b = BYDAutoSensorDevice.getInstance(context);
    }

    public void h(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        if (this.f1285b == null) {
            return;
        }
        String simpleName = l.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydSensorListener start listener == null : ");
        sb.append(absBYDAutoSensorListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1285b.registerListener(absBYDAutoSensorListener);
    }

    public int i(int i) {
        BYDAutoSensorDevice bYDAutoSensorDevice = this.f1285b;
        if (bYDAutoSensorDevice != null) {
            return bYDAutoSensorDevice.setOrientationAngle(i);
        }
        return 0;
    }

    public void j(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        if (this.f1285b == null) {
            return;
        }
        String simpleName = l.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydSensorListener start listener == null : ");
        sb.append(absBYDAutoSensorListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1285b.unregisterListener(absBYDAutoSensorListener);
    }
}
