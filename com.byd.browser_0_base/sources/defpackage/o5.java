package defpackage;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.ThumbnailUtils;
import android.util.FloatProperty;
import android.view.View;
import android.view.ViewGroup;
import defpackage.od0;
import org.chromium.ui.R$color;
import org.chromium.ui.R$dimen;

/* JADX INFO: compiled from: AnimatedImageDragShadowBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class o5 extends View.DragShadowBuilder {
    public static final /* synthetic */ int q = 0;
    public final a a = new a();
    public final float b;
    public final float c;
    public final Paint d;
    public final Paint e;
    public final RectF f;
    public final Matrix g;
    public final View h;
    public final int i;
    public final boolean j;
    public final RectF k;
    public final RectF l;
    public final RectF m;
    public final RectF n;
    public final RectF o;
    public float p;

    /* JADX INFO: compiled from: AnimatedImageDragShadowBuilder.java */
    public class a extends FloatProperty<o5> {
        public a() {
            super("progress");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((o5) obj).p);
        }

        @Override // android.util.FloatProperty
        public final void setValue(o5 o5Var, float f) {
            o5 o5Var2 = o5Var;
            o5Var2.p = f;
            o5Var2.h.updateDragShadow(o5Var2);
        }
    }

    public o5(ViewGroup viewGroup, Bitmap bitmap, float f, float f2, od0.a aVar) {
        RectF rectF = new RectF();
        this.k = rectF;
        RectF rectF2 = new RectF();
        this.l = rectF2;
        this.m = new RectF();
        this.n = new RectF();
        RectF rectF3 = new RectF();
        this.o = rectF3;
        this.h = viewGroup;
        this.j = aVar.e;
        int i = aVar.a;
        int i2 = aVar.b;
        Bitmap bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, i, i2, 2);
        float f3 = aVar.c;
        float f4 = i;
        Resources resources = viewGroup.getResources();
        this.p = 0.0f;
        rectF.set(0.0f, 0.0f, f4, i2);
        rectF2.set(0.0f, 0.0f, f3, aVar.d);
        rectF2.offset(f - rectF2.centerX(), f2 - rectF2.centerY());
        rectF3.set(rectF);
        rectF3.union(rectF2);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.drag_shadow_border_size);
        this.i = dimensionPixelSize;
        float f5 = dimensionPixelSize;
        float f6 = rectF3.left - f5;
        rectF3.left = f6;
        float f7 = rectF3.top - f5;
        rectF3.top = f7;
        rectF3.right += f5;
        rectF3.bottom += f5;
        float f8 = -f6;
        float f9 = -f7;
        rectF3.offset(f8, f9);
        rectF.offset(f8, f9);
        rectF2.offset(f8, f9);
        float dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.drag_shadow_border_corner_radius);
        this.b = dimensionPixelSize2;
        this.c = dimensionPixelSize2 / (f3 / f4);
        this.f = new RectF(0.0f, 0.0f, bitmapExtractThumbnail.getWidth(), bitmapExtractThumbnail.getHeight());
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmapExtractThumbnail, tileMode, tileMode);
        Paint paint = new Paint();
        this.d = paint;
        paint.setShader(bitmapShader);
        Paint paint2 = new Paint();
        this.e = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(f5);
        paint2.setColor(resources.getColor(R$color.baseline_neutral_variant_100_alpha_20));
        this.g = new Matrix();
    }

    @Override // android.view.View.DragShadowBuilder
    public final void onDrawShadow(Canvas canvas) {
        RectF rectF = this.m;
        RectF rectF2 = this.k;
        float f = rectF2.left;
        RectF rectF3 = this.l;
        float f2 = rectF3.left;
        float f3 = this.p;
        float f4 = C0173a.f(f2, f, f3, f);
        float f5 = rectF2.top;
        float f6 = C0173a.f(rectF3.top, f5, f3, f5);
        float f7 = rectF2.right;
        float f8 = C0173a.f(rectF3.right, f7, f3, f7);
        float f9 = rectF2.bottom;
        rectF.set(f4, f6, f8, ((rectF3.bottom - f9) * f3) + f9);
        Matrix matrix = this.g;
        matrix.setRectToRect(this.f, rectF, Matrix.ScaleToFit.CENTER);
        Paint paint = this.d;
        paint.getShader().setLocalMatrix(matrix);
        float f10 = this.p;
        float f11 = this.b;
        float f12 = this.c;
        float f13 = C0173a.f(f11, f12, f10, f12);
        paint.setAlpha(Math.round((1.0f - (f10 * 0.39999998f)) * 255.0f));
        canvas.drawRoundRect(rectF, f13, f13, paint);
        RectF rectF4 = this.n;
        float f14 = rectF.left;
        int i = this.i;
        rectF4.set(f14 - (i / 2.0f), rectF.top - (i / 2.0f), (i / 2.0f) + rectF.right, (i / 2.0f) + rectF.bottom);
        canvas.drawRoundRect(rectF4, f13, f13, this.e);
    }

    @Override // android.view.View.DragShadowBuilder
    public final void onProvideShadowMetrics(Point point, Point point2) {
        RectF rectF = this.o;
        point.set(Math.round(rectF.width()), Math.round(rectF.height()));
        RectF rectF2 = this.l;
        point2.set(Math.round(rectF2.centerX()), Math.round(rectF2.centerY()));
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.a, 1.0f);
        objectAnimatorOfFloat.setAutoCancel(true);
        objectAnimatorOfFloat.setDuration(this.j ? 0L : 300L);
        objectAnimatorOfFloat.start();
    }
}
