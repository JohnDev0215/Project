package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;

/* JADX INFO: compiled from: MaterialProgressDrawable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qc1 extends Drawable implements Animatable {
    public static final LinearInterpolator o = new LinearInterpolator();
    public static final jk0 p = new jk0();
    public final ArrayList<Animation> e = new ArrayList<>();
    public final b f;
    public float g;
    public final Resources h;
    public final View i;
    public oc1 j;
    public float k;
    public double l;
    public double m;
    public boolean n;

    /* JADX INFO: compiled from: MaterialProgressDrawable.java */
    public class a implements Drawable.Callback {
        public a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public final void invalidateDrawable(Drawable drawable) {
            qc1.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            qc1.this.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            qc1.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: compiled from: MaterialProgressDrawable.java */
    public static class b {
        public final RectF a = new RectF();
        public final Paint b;
        public final Paint c;
        public final Drawable.Callback d;
        public float e;
        public float f;
        public float g;
        public float h;
        public float i;
        public int[] j;
        public int k;
        public float l;
        public float m;
        public float n;
        public boolean o;
        public Path p;
        public float q;
        public double r;
        public int s;
        public int t;
        public int u;
        public final Paint v;
        public int w;
        public int x;

        public b(a aVar) {
            Paint paint = new Paint();
            this.b = paint;
            Paint paint2 = new Paint();
            this.c = paint2;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 5.0f;
            this.i = 2.5f;
            this.v = new Paint();
            this.d = aVar;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
        }

        public final void a() {
            this.d.invalidateDrawable(null);
        }

        public final void b(int i) {
            this.k = i;
            this.x = this.j[i];
        }
    }

    public qc1(Context context, View view) {
        a aVar = new a();
        this.i = view;
        this.h = context.getResources();
        b bVar = new b(aVar);
        this.f = bVar;
        bVar.j = new int[]{-16777216};
        bVar.b(0);
        d(1);
        oc1 oc1Var = new oc1(this, bVar);
        oc1Var.setRepeatCount(-1);
        oc1Var.setRepeatMode(1);
        oc1Var.setInterpolator(o);
        oc1Var.setAnimationListener(new pc1(this, bVar));
        this.j = oc1Var;
    }

    public static void c(float f, b bVar) {
        if (f > 0.75f) {
            float f2 = (f - 0.75f) / 0.25f;
            int[] iArr = bVar.j;
            int i = bVar.k;
            int i2 = iArr[i];
            int i3 = iArr[(i + 1) % iArr.length];
            int iIntValue = Integer.valueOf(i2).intValue();
            int i4 = (iIntValue >> 24) & 255;
            int i5 = (iIntValue >> 16) & 255;
            int i6 = (iIntValue >> 8) & 255;
            int iIntValue2 = Integer.valueOf(i3).intValue();
            bVar.x = ((iIntValue & 255) + ((int) (f2 * ((iIntValue2 & 255) - r1)))) | ((i4 + ((int) ((((iIntValue2 >> 24) & 255) - i4) * f2))) << 24) | ((i5 + ((int) ((((iIntValue2 >> 16) & 255) - i5) * f2))) << 16) | ((i6 + ((int) ((((iIntValue2 >> 8) & 255) - i6) * f2))) << 8);
        }
    }

    public final void b(double d, double d2, double d3, double d4, float f, float f2) {
        float f3 = this.h.getDisplayMetrics().density;
        double d5 = f3;
        this.l = d * d5;
        this.m = d2 * d5;
        float f4 = ((float) d4) * f3;
        b bVar = this.f;
        bVar.h = f4;
        bVar.b.setStrokeWidth(f4);
        bVar.a();
        bVar.r = d3 * d5;
        bVar.b(0);
        bVar.s = (int) (f * f3);
        bVar.t = (int) (f2 * f3);
        float fMin = Math.min((int) this.l, (int) this.m);
        double d6 = bVar.r;
        bVar.i = (float) ((d6 <= 0.0d || fMin < 0.0f) ? Math.ceil(bVar.h / 2.0f) : ((double) (fMin / 2.0f)) - d6);
    }

    public final void d(int i) {
        if (i == 0) {
            b(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            b(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        boolean z = this.i.getLayoutDirection() == 1;
        Rect bounds = getBounds();
        int iSave = canvas.save();
        if (z) {
            canvas.scale(-1.0f, 1.0f, bounds.exactCenterX(), bounds.exactCenterY());
        }
        canvas.rotate(this.g, bounds.exactCenterX(), bounds.exactCenterY());
        b bVar = this.f;
        RectF rectF = bVar.a;
        rectF.set(bounds);
        float f = bVar.i;
        rectF.inset(f, f);
        float f2 = bVar.e;
        float f3 = bVar.g;
        float f4 = (f2 + f3) * 360.0f;
        float f5 = ((bVar.f + f3) * 360.0f) - f4;
        Paint paint = bVar.b;
        paint.setColor(bVar.x);
        canvas.drawArc(rectF, f4, f5, false, paint);
        if (bVar.o) {
            Path path = bVar.p;
            if (path == null) {
                Path path2 = new Path();
                bVar.p = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            float f6 = (((int) bVar.i) / 2.0f) * bVar.q;
            float fExactCenterX = (float) (((double) bounds.exactCenterX()) + (Math.cos(0.0d) * bVar.r));
            float fSin = (float) ((Math.sin(0.0d) * bVar.r) + ((double) bounds.exactCenterY()));
            bVar.p.moveTo(0.0f, 0.0f);
            bVar.p.lineTo(bVar.s * bVar.q, 0.0f);
            Path path3 = bVar.p;
            float f7 = bVar.s;
            float f8 = bVar.q;
            path3.lineTo((f7 * f8) / 2.0f, bVar.t * f8);
            bVar.p.offset(fExactCenterX - f6, fSin);
            bVar.p.close();
            Paint paint2 = bVar.c;
            paint2.setColor(bVar.x);
            canvas.rotate((f4 + f5) - 5.0f, bounds.exactCenterX(), bounds.exactCenterY());
            canvas.drawPath(bVar.p, paint2);
        }
        if (bVar.u < 255) {
            Paint paint3 = bVar.v;
            paint3.setColor(bVar.w);
            paint3.setAlpha(255 - bVar.u);
            canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), bounds.width() / 2.0f, paint3);
        }
        if (z) {
            canvas.scale(-1.0f, 1.0f, bounds.exactCenterX(), bounds.exactCenterY());
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f.u;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.m;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return (int) this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        ArrayList<Animation> arrayList = this.e;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animation animation = arrayList.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        b bVar = this.f;
        if (bVar.u != i) {
            bVar.u = i;
            bVar.a();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        b bVar = this.f;
        bVar.b.setColorFilter(colorFilter);
        bVar.a();
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.j.reset();
        b bVar = this.f;
        float f = bVar.e;
        bVar.l = f;
        float f2 = bVar.f;
        bVar.m = f2;
        bVar.n = bVar.g;
        View view = this.i;
        if (f2 != f) {
            this.n = true;
            this.j.setDuration(666L);
            view.startAnimation(this.j);
            return;
        }
        bVar.b(0);
        bVar.l = 0.0f;
        bVar.m = 0.0f;
        bVar.n = 0.0f;
        bVar.e = 0.0f;
        bVar.a();
        bVar.f = 0.0f;
        bVar.a();
        bVar.g = 0.0f;
        bVar.a();
        this.j.setDuration(1332L);
        view.startAnimation(this.j);
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.i.clearAnimation();
        this.g = 0.0f;
        invalidateSelf();
        b bVar = this.f;
        if (bVar.o) {
            bVar.o = false;
            bVar.a();
        }
        bVar.b(0);
        bVar.l = 0.0f;
        bVar.m = 0.0f;
        bVar.n = 0.0f;
        bVar.e = 0.0f;
        bVar.a();
        bVar.f = 0.0f;
        bVar.a();
        bVar.g = 0.0f;
        bVar.a();
    }
}
