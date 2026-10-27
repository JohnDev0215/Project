package defpackage;

import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.MeteringRectangle;
import android.util.Rational;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YH implements Runnable {
    public final XH s;
    public final /* synthetic */ C0542bI t;

    public YH(C0542bI c0542bI, XH xh) {
        this.t = c0542bI;
        this.s = xh;
    }

    @Override // java.lang.Runnable
    public void run() {
        CameraCharacteristics cameraCharacteristicsI = C0542bI.i(this.t.d);
        Rect rect = (Rect) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        double d = this.s.a;
        if (d != 0.0d) {
            float fMax = Math.max(1.0f, Math.min((float) d, this.t.p));
            float f = (fMax - 1.0f) / (fMax * 2.0f);
            float f2 = 1.0f - f;
            this.t.q = new Rect(Math.round(rect.width() * f), Math.round(rect.height() * f), Math.round(rect.width() * f2), Math.round(rect.height() * f2));
            this.t.q.toString();
        }
        int i = this.s.b;
        if (i != 0) {
            this.t.t = i;
        }
        double d2 = this.s.c;
        if (d2 != 0.0d) {
            this.t.u = (float) d2;
        }
        int i2 = this.s.d;
        if (i2 != 0) {
            this.t.v = i2;
        }
        double d3 = this.s.j;
        if (d3 != 0.0d) {
            this.t.w = (long) d3;
        }
        int i3 = this.s.k;
        if (i3 != 0) {
            this.t.z = i3;
        }
        double d4 = this.s.e;
        if (d4 > 0.0d) {
            this.t.r = (int) Math.round(d4);
        }
        double d5 = this.s.f;
        if (d5 > 0.0d) {
            this.t.s = (int) Math.round(d5);
        }
        MeteringRectangle meteringRectangle = this.t.x;
        if (meteringRectangle != null && !meteringRectangle.getRect().isEmpty() && this.s.a > 0.0d) {
            this.t.x = null;
        }
        C0542bI c0542bI = this.t;
        if (c0542bI.t == 1 || c0542bI.v == 1) {
            this.t.x = null;
        }
        if ((((Integer) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0 || ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)).intValue() > 0 || ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB)).intValue() > 0) && this.s.g.length > 0) {
            Rect rect2 = this.t.q.isEmpty() ? rect : this.t.q;
            int iRound = (int) Math.round(this.s.g[0] * ((double) rect2.width()));
            int iRound2 = (int) Math.round(this.s.g[1] * ((double) rect2.height()));
            if (rect2.equals(this.t.q)) {
                iRound += (rect.width() - rect2.width()) / 2;
                iRound2 += (rect.height() - rect2.height()) / 2;
            }
            int iWidth = rect2.width() / 8;
            int iHeight = rect2.height() / 8;
            this.t.x = new MeteringRectangle(Math.max(0, iRound - (iWidth / 2)), Math.max(0, iRound2 - (iHeight / 2)), iWidth, iHeight, 1000);
            double[] dArr = this.s.g;
            double d6 = dArr[0];
            double d7 = dArr[1];
            rect2.toString();
            rect.toString();
            this.t.x.toString();
        }
        XH xh = this.s;
        if (xh.h) {
            this.t.y = (int) Math.round(xh.i / ((double) ((Rational) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP)).floatValue()));
        }
        double d8 = this.s.l;
        if (d8 > 0.0d) {
            this.t.B = (int) Math.round(d8);
        }
        double d9 = this.s.r;
        if (d9 > 0.0d) {
            this.t.A = (int) Math.round(d9);
        }
        XH xh2 = this.s;
        if (xh2.m) {
            this.t.C = xh2.n;
        }
        int i4 = this.s.o;
        if (i4 != 0) {
            this.t.D = i4;
        }
        XH xh3 = this.s;
        if (xh3.p) {
            this.t.E = xh3.q;
        }
        C0542bI c0542bI2 = this.t;
        if (c0542bI2.h != null) {
            c0542bI2.g(c0542bI2.j);
            try {
                this.t.h.setRepeatingRequest(this.t.j.build(), null, null);
            } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException e) {
                Ts.a("VideoCapture", "setRepeatingRequest: ", e);
            }
        }
    }
}
