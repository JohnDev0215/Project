package com.airbnb.lottie.t.l;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.k;
import com.airbnb.lottie.r.c.p;

/* JADX INFO: loaded from: classes.dex */
public class g extends a {
    private final d A;

    @Nullable
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> B;
    private final RectF w;
    private final Paint x;
    private final float[] y;
    private final Path z;

    g(com.airbnb.lottie.f fVar, d dVar) {
        super(fVar, dVar);
        this.w = new RectF();
        com.airbnb.lottie.r.a aVar = new com.airbnb.lottie.r.a();
        this.x = aVar;
        this.y = new float[8];
        this.z = new Path();
        this.A = dVar;
        aVar.setAlpha(0);
        aVar.setStyle(Paint.Style.FILL);
        aVar.setColor(dVar.m());
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        this.w.set(0.0f, 0.0f, this.A.o(), this.A.n());
        this.m.mapRect(this.w);
        rectF.set(this.w);
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        super.g(t, cVar);
        if (t == k.B) {
            this.B = cVar == null ? null : new p(cVar);
        }
    }

    @Override // com.airbnb.lottie.t.l.a
    public void r(Canvas canvas, Matrix matrix, int i) {
        int iAlpha = Color.alpha(this.A.m());
        if (iAlpha == 0) {
            return;
        }
        int iIntValue = (int) ((i / 255.0f) * (((iAlpha / 255.0f) * (this.u.h() == null ? 100 : this.u.h().h().intValue())) / 100.0f) * 255.0f);
        this.x.setAlpha(iIntValue);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.B;
        if (aVar != null) {
            this.x.setColorFilter(aVar.h());
        }
        if (iIntValue > 0) {
            float[] fArr = this.y;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = this.A.o();
            float[] fArr2 = this.y;
            fArr2[3] = 0.0f;
            fArr2[4] = this.A.o();
            this.y[5] = this.A.n();
            float[] fArr3 = this.y;
            fArr3[6] = 0.0f;
            fArr3[7] = this.A.n();
            matrix.mapPoints(this.y);
            this.z.reset();
            Path path = this.z;
            float[] fArr4 = this.y;
            path.moveTo(fArr4[0], fArr4[1]);
            Path path2 = this.z;
            float[] fArr5 = this.y;
            path2.lineTo(fArr5[2], fArr5[3]);
            Path path3 = this.z;
            float[] fArr6 = this.y;
            path3.lineTo(fArr6[4], fArr6[5]);
            Path path4 = this.z;
            float[] fArr7 = this.y;
            path4.lineTo(fArr7[6], fArr7[7]);
            Path path5 = this.z;
            float[] fArr8 = this.y;
            path5.lineTo(fArr8[0], fArr8[1]);
            this.z.close();
            canvas.drawPath(this.z, this.x);
        }
    }
}
