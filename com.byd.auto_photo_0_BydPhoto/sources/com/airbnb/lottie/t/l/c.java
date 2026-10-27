package com.airbnb.lottie.t.l;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.k;
import com.airbnb.lottie.r.c.p;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    private final Paint w;
    private final Rect x;
    private final Rect y;

    @Nullable
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> z;

    c(com.airbnb.lottie.f fVar, d dVar) {
        super(fVar, dVar);
        this.w = new com.airbnb.lottie.r.a(3);
        this.x = new Rect();
        this.y = new Rect();
    }

    @Nullable
    private Bitmap H() {
        return this.n.n(this.o.k());
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        if (H() != null) {
            rectF.set(0.0f, 0.0f, r3.getWidth() * com.airbnb.lottie.w.h.e(), r3.getHeight() * com.airbnb.lottie.w.h.e());
            this.m.mapRect(rectF);
        }
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        super.g(t, cVar);
        if (t == k.B) {
            this.z = cVar == null ? null : new p(cVar);
        }
    }

    @Override // com.airbnb.lottie.t.l.a
    public void r(@NonNull Canvas canvas, Matrix matrix, int i) {
        Bitmap bitmapH = H();
        if (bitmapH == null || bitmapH.isRecycled()) {
            return;
        }
        float fE = com.airbnb.lottie.w.h.e();
        this.w.setAlpha(i);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.z;
        if (aVar != null) {
            this.w.setColorFilter(aVar.h());
        }
        canvas.save();
        canvas.concat(matrix);
        this.x.set(0, 0, bitmapH.getWidth(), bitmapH.getHeight());
        this.y.set(0, 0, (int) (bitmapH.getWidth() * fE), (int) (bitmapH.getHeight() * fE));
        canvas.drawBitmap(bitmapH, this.x, this.y, this.w);
        canvas.restore();
    }
}
