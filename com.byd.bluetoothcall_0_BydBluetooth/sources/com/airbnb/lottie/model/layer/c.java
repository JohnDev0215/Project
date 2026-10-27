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
    private final Paint w;
    private final Rect x;
    private final Rect y;
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> z;

    c(com.airbnb.lottie.f fVar, Layer layer) {
        super(fVar, layer);
        this.w = new com.airbnb.lottie.r.a(3);
        this.x = new Rect();
        this.y = new Rect();
    }

    private Bitmap J() {
        return this.n.n(this.o.k());
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        if (J() != null) {
            rectF.set(0.0f, 0.0f, r3.getWidth() * h.e(), r3.getHeight() * h.e());
            this.m.mapRect(rectF);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == k.B) {
            if (cVar == null) {
                this.z = null;
            } else {
                this.z = new p(cVar);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    public void t(Canvas canvas, Matrix matrix, int i) {
        Bitmap bitmapJ = J();
        if (bitmapJ == null || bitmapJ.isRecycled()) {
            return;
        }
        float fE = h.e();
        this.w.setAlpha(i);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.z;
        if (aVar != null) {
            this.w.setColorFilter(aVar.h());
        }
        canvas.save();
        canvas.concat(matrix);
        this.x.set(0, 0, bitmapJ.getWidth(), bitmapJ.getHeight());
        this.y.set(0, 0, (int) (bitmapJ.getWidth() * fE), (int) (bitmapJ.getHeight() * fE));
        canvas.drawBitmap(bitmapJ, this.x, this.y, this.w);
        canvas.restore();
    }
}
