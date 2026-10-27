package p;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public final class sc2 extends xc2 {
    public final uc2 b;

    public sc2(uc2 uc2Var) {
        this.b = uc2Var;
    }

    @Override // p.xc2
    public final void a(Matrix matrix, nc2 nc2Var, int i, Canvas canvas) {
        uc2 uc2Var = this.b;
        float f = uc2Var.f;
        float f2 = uc2Var.g;
        RectF rectF = new RectF(uc2Var.b, uc2Var.c, uc2Var.d, uc2Var.e);
        nc2Var.getClass();
        boolean z = f2 < 0.0f;
        Path path = nc2Var.g;
        int[] iArr = nc2.k;
        if (z) {
            iArr[0] = 0;
            iArr[1] = nc2Var.f;
            iArr[2] = nc2Var.e;
            iArr[3] = nc2Var.d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f, f2);
            path.close();
            float f3 = -i;
            rectF.inset(f3, f3);
            iArr[0] = 0;
            iArr[1] = nc2Var.d;
            iArr[2] = nc2Var.e;
            iArr[3] = nc2Var.f;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f4 = 1.0f - (i / fWidth);
        float[] fArr = nc2.l;
        fArr[1] = f4;
        fArr[2] = ((1.0f - f4) / 2.0f) + f4;
        RadialGradient radialGradient = new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, iArr, fArr, Shader.TileMode.CLAMP);
        Paint paint = nc2Var.b;
        paint.setShader(radialGradient);
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, nc2Var.h);
        }
        canvas.drawArc(rectF, f, f2, true, paint);
        canvas.restore();
    }
}
