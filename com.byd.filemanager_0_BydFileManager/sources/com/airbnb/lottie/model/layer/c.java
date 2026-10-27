package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.k;
import com.airbnb.lottie.r.c.p;
import com.airbnb.lottie.u.h;

/* JADX INFO: compiled from: ImageLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> A;
    private final Paint x;
    private final Rect y;
    private final Rect z;

    c(com.airbnb.lottie.f fVar, Layer layer) {
        super(fVar, layer);
        this.x = new com.airbnb.lottie.r.a(3);
        this.y = new Rect();
        this.z = new Rect();
    }

    private Bitmap L() {
        return this.n.q(this.o.k());
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        if (L() != null) {
            rectF.set(0.0f, 0.0f, r3.getWidth() * h.e(), r3.getHeight() * h.e());
            this.m.mapRect(rectF);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == k.C) {
            if (cVar == null) {
                this.A = null;
            } else {
                this.A = new p(cVar);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    public void v(Canvas canvas, Matrix matrix, int i) {
        Bitmap bitmapL = L();
        if (bitmapL == null || bitmapL.isRecycled()) {
            return;
        }
        float fE = h.e();
        this.x.setAlpha(i);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.A;
        if (aVar != null) {
            this.x.setColorFilter(aVar.h());
        }
        canvas.save();
        canvas.concat(matrix);
        this.y.set(0, 0, bitmapL.getWidth(), bitmapL.getHeight());
        this.z.set(0, 0, (int) (bitmapL.getWidth() * fE), (int) (bitmapL.getHeight() * fE));
        canvas.drawBitmap(bitmapL, this.y, this.z, this.x);
        canvas.restore();
    }
}
