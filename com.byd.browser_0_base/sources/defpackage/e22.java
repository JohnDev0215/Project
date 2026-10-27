package defpackage;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;

/* JADX INFO: compiled from: PlayerFrameViewport.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e22 {
    public boolean e;
    public Size a = new Size(0, 0);
    public final Matrix b = new Matrix();
    public final Rect c = new Rect();
    public final Point d = new Point();
    public Size f = new Size(0, 0);
    public final Rect g = new Rect();
    public final float[] h = new float[9];
    public final Rect i = new Rect();

    public final Rect a() {
        Matrix matrix = this.b;
        float[] fArr = this.h;
        matrix.getValues(fArr);
        int iRound = Math.round(fArr[2]);
        int iRound2 = Math.round(fArr[5]);
        int width = this.a.getWidth() + iRound;
        int height = this.a.getHeight() + iRound2;
        Rect rect = this.g;
        rect.set(iRound, iRound2, width, height);
        return rect;
    }

    public final float b() {
        Matrix matrix = this.b;
        float[] fArr = this.h;
        matrix.getValues(fArr);
        return fArr[0];
    }

    public final float c() {
        Matrix matrix = this.b;
        float[] fArr = this.h;
        matrix.getValues(fArr);
        return fArr[2];
    }

    public final float d() {
        Matrix matrix = this.b;
        float[] fArr = this.h;
        matrix.getValues(fArr);
        return fArr[5];
    }

    public final Rect e(boolean z) {
        if (!z) {
            return a();
        }
        Rect rect = this.c;
        if (rect.isEmpty()) {
            return rect;
        }
        Rect rectA = a();
        int i = rectA.left + rect.left;
        int i2 = rectA.top + rect.top;
        int iWidth = rect.width() + i;
        int iHeight = rect.height() + i2;
        Rect rect2 = this.i;
        rect2.set(i, i2, iWidth, iHeight);
        return rect2;
    }

    public final void f(float f) {
        Matrix matrix = this.b;
        float[] fArr = this.h;
        matrix.getValues(fArr);
        fArr[0] = f;
        fArr[4] = f;
        matrix.setValues(fArr);
    }

    public final void g(float f, float f2) {
        Matrix matrix = this.b;
        float[] fArr = this.h;
        matrix.getValues(fArr);
        fArr[2] = f;
        fArr[5] = f2;
        matrix.setValues(fArr);
    }
}
