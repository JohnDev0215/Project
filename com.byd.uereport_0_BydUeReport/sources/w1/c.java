package w1;

import a2.h;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.model.layer.Layer;
import o1.l;
import r1.p;

/* JADX INFO: compiled from: ImageLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    public r1.a<ColorFilter, ColorFilter> A;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Paint f8700x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Rect f8701y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Rect f8702z;

    public c(o1.g gVar, Layer layer) {
        super(gVar, layer);
        this.f8700x = new p1.a(3);
        this.f8701y = new Rect();
        this.f8702z = new Rect();
    }

    public final Bitmap L() {
        return this.f8683n.q(this.f8684o.k());
    }

    @Override // w1.a, q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        super.b(rectF, matrix, z7);
        if (L() != null) {
            rectF.set(0.0f, 0.0f, r3.getWidth() * h.e(), r3.getHeight() * h.e());
            this.f8682m.mapRect(rectF);
        }
    }

    @Override // w1.a, t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        super.i(t7, cVar);
        if (t7 == l.C) {
            if (cVar == null) {
                this.A = null;
            } else {
                this.A = new p(cVar);
            }
        }
    }

    @Override // w1.a
    public void v(Canvas canvas, Matrix matrix, int i8) {
        Bitmap bitmapL = L();
        if (bitmapL == null || bitmapL.isRecycled()) {
            return;
        }
        float fE = h.e();
        this.f8700x.setAlpha(i8);
        r1.a<ColorFilter, ColorFilter> aVar = this.A;
        if (aVar != null) {
            this.f8700x.setColorFilter(aVar.h());
        }
        canvas.save();
        canvas.concat(matrix);
        this.f8701y.set(0, 0, bitmapL.getWidth(), bitmapL.getHeight());
        this.f8702z.set(0, 0, (int) (bitmapL.getWidth() * fE), (int) (bitmapL.getHeight() * fE));
        canvas.drawBitmap(bitmapL, this.f8701y, this.f8702z, this.f8700x);
        canvas.restore();
    }
}
