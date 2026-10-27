package A2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.view.Display;

/* JADX INFO: loaded from: classes.dex */
public final class e implements SensorEventListener {
    public final float[] l = new float[16];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float[] f313m = new float[16];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float[] f314n = new float[16];
    public final float[] o = new float[3];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Display f315p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final d[] f316q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f317r;

    public e(Display display, d... dVarArr) {
        this.f315p = display;
        this.f316q = dVarArr;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i7) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        int i7;
        float[] fArr = sensorEvent.values;
        float[] fArr2 = this.l;
        SensorManager.getRotationMatrixFromVector(fArr2, fArr);
        int rotation = this.f315p.getRotation();
        float[] fArr3 = this.f313m;
        if (rotation != 0) {
            int i10 = 129;
            if (rotation != 1) {
                i7 = 130;
                if (rotation != 2) {
                    if (rotation != 3) {
                        throw new IllegalStateException();
                    }
                    i10 = 130;
                    i7 = 1;
                }
            } else {
                i7 = 129;
                i10 = 2;
            }
            System.arraycopy(fArr2, 0, fArr3, 0, fArr3.length);
            SensorManager.remapCoordinateSystem(fArr3, i10, i7, fArr2);
        }
        SensorManager.remapCoordinateSystem(fArr2, 1, 131, fArr3);
        float[] fArr4 = this.o;
        SensorManager.getOrientation(fArr3, fArr4);
        float f10 = fArr4[2];
        Matrix.rotateM(fArr2, 0, 90.0f, 1.0f, 0.0f, 0.0f);
        boolean z6 = this.f317r;
        float[] fArr5 = this.f314n;
        if (!z6) {
            c.e(fArr5, fArr2);
            this.f317r = true;
        }
        System.arraycopy(fArr2, 0, fArr3, 0, fArr3.length);
        Matrix.multiplyMM(fArr2, 0, fArr3, 0, fArr5, 0);
        d[] dVarArr = this.f316q;
        for (int i11 = 0; i11 < 2; i11++) {
            dVarArr[i11].a(fArr2, f10);
        }
    }
}
