package org.chromium.device.sensors;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import defpackage.xz;
import java.util.HashSet;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class PlatformSensorProvider {
    public SensorManager a;
    public HandlerThread b;
    public Handler c;
    public final HashSet d = new HashSet();

    public PlatformSensorProvider(Context context) {
        this.a = (SensorManager) context.getSystemService("sensor");
    }

    @CalledByNative
    public static PlatformSensorProvider create() {
        return new PlatformSensorProvider(xz.a);
    }

    public final void a(PlatformSensor platformSensor) {
        synchronized (this.d) {
            if (this.d.isEmpty() && this.b == null) {
                HandlerThread handlerThread = new HandlerThread("SensorsHandlerThread");
                this.b = handlerThread;
                handlerThread.start();
                this.c = new Handler(this.b.getLooper());
            }
            this.d.add(platformSensor);
        }
    }

    @CalledByNative
    public boolean hasSensorType(int i) {
        if (this.a == null) {
            return false;
        }
        int i2 = 5;
        if (i != 0) {
            int i3 = 11;
            if (i == 9) {
                i2 = i3;
            } else if (i != 11) {
                i3 = 2;
                if (i == 2) {
                    i2 = 1;
                } else if (i == 3) {
                    i2 = 10;
                } else if (i == 4) {
                    i2 = 9;
                } else if (i != 5) {
                    if (i != 6) {
                        return false;
                    }
                    i2 = i3;
                } else {
                    i2 = 4;
                }
            } else {
                i2 = 15;
            }
        }
        return !r6.getSensorList(i2).isEmpty();
    }

    @CalledByNative
    public void setSensorManagerToNullForTesting() {
        this.a = null;
    }
}
