package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.k;
import com.airbnb.lottie.r.c.p;

/* JADX INFO: compiled from: SolidLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {
    private final Path A;
    private final Layer B;
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> C;
    private final RectF x;
    private final Paint y;
    private final float[] z;

    f(com.airbnb.lottie.f fVar, Layer layer) {
        super(fVar, layer);
        this.x = new RectF();
        com.airbnb.lottie.r.a aVar = new com.airbnb.lottie.r.a();
        this.y = aVar;
        this.z = new float[8];
        this.A = new Path();
        this.B = layer;
        aVar.setAlpha(0);
        aVar.setStyle(Paint.Style.FILL);
        aVar.setColor(layer.m());
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        this.x.set(0.0f, 0.0f, this.B.o(), this.B.n());
        this.m.mapRect(this.x);
        rectF.set(this.x);
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == k.C) {
            if (cVar == null) {
                this.C = null;
            } else {
                this.C = new p(cVar);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    public void v(Canvas canvas, Matrix matrix, int i) {
        int iAlpha = Color.alpha(this.B.m());
        if (iAlpha == 0) {
            return;
        }
        int iIntValue = (int) ((i / 255.0f) * (((iAlpha / 255.0f) * (this.v.h() == null ? 100 : this.v.h().h().intValue())) / 100.0f) * 255.0f);
        this.y.setAlpha(iIntValue);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.C;
        if (aVar != null) {
            this.y.setColorFilter(aVar.h());
        }
        if (iIntValue > 0) {
            float[] fArr = this.z;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = this.B.o();
            float[] fArr2 = this.z;
            fArr2[3] = 0.0f;
            fArr2[4] = this.B.o();
            this.z[5] = this.B.n();
            float[] fArr3 = this.z;
            fArr3[6] = 0.0f;
            fArr3[7] = this.B.n();
            matrix.mapPoints(this.z);
            this.A.reset();
            Path path = this.A;
            float[] fArr4 = this.z;
            path.moveTo(fArr4[0], fArr4[1]);
            Path path2 = this.A;
            float[] fArr5 = this.z;
            path2.lineTo(fArr5[2], fArr5[3]);
            Path path3 = this.A;
            float[] fArr6 = this.z;
            path3.lineTo(fArr6[4], fArr6[5]);
            Path path4 = this.A;
            float[] fArr7 = this.z;
            path4.lineTo(fArr7[6], fArr7[7]);
            Path path5 = this.A;
            float[] fArr8 = this.z;
            path5.lineTo(fArr8[0], fArr8[1]);
            this.A.close();
            canvas.drawPath(this.A, this.y);
        }
    }
}
