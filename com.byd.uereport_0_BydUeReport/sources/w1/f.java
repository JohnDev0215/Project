package w1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.model.layer.Layer;
import o1.l;
import r1.p;

/* JADX INFO: compiled from: SolidLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {
    public final Path A;
    public final Layer B;
    public r1.a<ColorFilter, ColorFilter> C;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final RectF f8704x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Paint f8705y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final float[] f8706z;

    public f(o1.g gVar, Layer layer) {
        super(gVar, layer);
        this.f8704x = new RectF();
        p1.a aVar = new p1.a();
        this.f8705y = aVar;
        this.f8706z = new float[8];
        this.A = new Path();
        this.B = layer;
        aVar.setAlpha(0);
        aVar.setStyle(Paint.Style.FILL);
        aVar.setColor(layer.m());
    }

    @Override // w1.a, q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        super.b(rectF, matrix, z7);
        this.f8704x.set(0.0f, 0.0f, this.B.o(), this.B.n());
        this.f8682m.mapRect(this.f8704x);
        rectF.set(this.f8704x);
    }

    @Override // w1.a, t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        super.i(t7, cVar);
        if (t7 == l.C) {
            if (cVar == null) {
                this.C = null;
            } else {
                this.C = new p(cVar);
            }
        }
    }

    @Override // w1.a
    public void v(Canvas canvas, Matrix matrix, int i8) {
        int iAlpha = Color.alpha(this.B.m());
        if (iAlpha == 0) {
            return;
        }
        int iIntValue = (int) ((i8 / 255.0f) * (((iAlpha / 255.0f) * (this.f8691v.h() == null ? 100 : this.f8691v.h().h().intValue())) / 100.0f) * 255.0f);
        this.f8705y.setAlpha(iIntValue);
        r1.a<ColorFilter, ColorFilter> aVar = this.C;
        if (aVar != null) {
            this.f8705y.setColorFilter(aVar.h());
        }
        if (iIntValue > 0) {
            float[] fArr = this.f8706z;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = this.B.o();
            float[] fArr2 = this.f8706z;
            fArr2[3] = 0.0f;
            fArr2[4] = this.B.o();
            this.f8706z[5] = this.B.n();
            float[] fArr3 = this.f8706z;
            fArr3[6] = 0.0f;
            fArr3[7] = this.B.n();
            matrix.mapPoints(this.f8706z);
            this.A.reset();
            Path path = this.A;
            float[] fArr4 = this.f8706z;
            path.moveTo(fArr4[0], fArr4[1]);
            Path path2 = this.A;
            float[] fArr5 = this.f8706z;
            path2.lineTo(fArr5[2], fArr5[3]);
            Path path3 = this.A;
            float[] fArr6 = this.f8706z;
            path3.lineTo(fArr6[4], fArr6[5]);
            Path path4 = this.A;
            float[] fArr7 = this.f8706z;
            path4.lineTo(fArr7[6], fArr7[7]);
            Path path5 = this.A;
            float[] fArr8 = this.f8706z;
            path5.lineTo(fArr8[0], fArr8[1]);
            this.A.close();
            canvas.drawPath(this.A, this.f8705y);
        }
    }
}
