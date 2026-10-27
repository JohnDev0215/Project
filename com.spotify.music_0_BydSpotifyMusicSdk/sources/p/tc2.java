package p;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public final class tc2 extends xc2 {
    public final vc2 b;
    public final float c;
    public final float d;

    public tc2(vc2 vc2Var, float f, float f2) {
        this.b = vc2Var;
        this.c = f;
        this.d = f2;
    }

    @Override // p.xc2
    public final void a(Matrix matrix, nc2 nc2Var, int i, Canvas canvas) {
        vc2 vc2Var = this.b;
        float f = vc2Var.c;
        float f2 = this.d;
        float f3 = vc2Var.b;
        float f4 = this.c;
        RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(f - f2, f3 - f4), 0.0f);
        Matrix matrix2 = new Matrix(matrix);
        matrix2.preTranslate(f4, f2);
        matrix2.preRotate(b());
        nc2Var.getClass();
        rectF.bottom += i;
        rectF.offset(0.0f, -i);
        int[] iArr = nc2.i;
        iArr[0] = nc2Var.f;
        iArr[1] = nc2Var.e;
        iArr[2] = nc2Var.d;
        Paint paint = nc2Var.c;
        float f5 = rectF.left;
        paint.setShader(new LinearGradient(f5, rectF.top, f5, rectF.bottom, iArr, nc2.j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix2);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }

    public final float b() {
        vc2 vc2Var = this.b;
        return (float) Math.toDegrees(Math.atan((vc2Var.c - this.d) / (vc2Var.b - this.c)));
    }
}
