package j1;

import android.animation.Animator;
import android.animation.ValueAnimator;
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
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import n0.h;

/* JADX INFO: compiled from: CircularProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Drawable implements Animatable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Interpolator f5542d = new LinearInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Interpolator f5543e = new w0.b();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f5544f = {-16777216};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f5545g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f5546h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Resources f5547i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Animator f5548j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5549k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5550l;

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c f5551d;

        public a(c cVar) {
            this.f5551d = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            b.this.n(fFloatValue, this.f5551d);
            b.this.b(fFloatValue, this.f5551d, false);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: renamed from: j1.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public class C0066b implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c f5553d;

        public C0066b(c cVar) {
            this.f5553d = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            b.this.b(1.0f, this.f5553d, true);
            this.f5553d.A();
            this.f5553d.l();
            b bVar = b.this;
            if (!bVar.f5550l) {
                bVar.f5549k += 1.0f;
                return;
            }
            bVar.f5550l = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.f5553d.x(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.f5549k = 0.0f;
        }
    }

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final RectF f5555a = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Paint f5556b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Paint f5557c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Paint f5558d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f5559e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f5560f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f5561g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f5562h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int[] f5563i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f5564j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f5565k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f5566l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f5567m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f5568n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public Path f5569o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f5570p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public float f5571q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f5572r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f5573s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f5574t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f5575u;

        public c() {
            Paint paint = new Paint();
            this.f5556b = paint;
            Paint paint2 = new Paint();
            this.f5557c = paint2;
            Paint paint3 = new Paint();
            this.f5558d = paint3;
            this.f5559e = 0.0f;
            this.f5560f = 0.0f;
            this.f5561g = 0.0f;
            this.f5562h = 5.0f;
            this.f5570p = 1.0f;
            this.f5574t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        public void A() {
            this.f5565k = this.f5559e;
            this.f5566l = this.f5560f;
            this.f5567m = this.f5561g;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f5555a;
            float f8 = this.f5571q;
            float fMin = (this.f5562h / 2.0f) + f8;
            if (f8 <= 0.0f) {
                fMin = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.f5572r * this.f5570p) / 2.0f, this.f5562h / 2.0f);
            }
            rectF.set(rect.centerX() - fMin, rect.centerY() - fMin, rect.centerX() + fMin, rect.centerY() + fMin);
            float f9 = this.f5559e;
            float f10 = this.f5561g;
            float f11 = (f9 + f10) * 360.0f;
            float f12 = ((this.f5560f + f10) * 360.0f) - f11;
            this.f5556b.setColor(this.f5575u);
            this.f5556b.setAlpha(this.f5574t);
            float f13 = this.f5562h / 2.0f;
            rectF.inset(f13, f13);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f5558d);
            float f14 = -f13;
            rectF.inset(f14, f14);
            canvas.drawArc(rectF, f11, f12, false, this.f5556b);
            b(canvas, f11, f12, rectF);
        }

        public void b(Canvas canvas, float f8, float f9, RectF rectF) {
            if (this.f5568n) {
                Path path = this.f5569o;
                if (path == null) {
                    Path path2 = new Path();
                    this.f5569o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float fMin = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f10 = (this.f5572r * this.f5570p) / 2.0f;
                this.f5569o.moveTo(0.0f, 0.0f);
                this.f5569o.lineTo(this.f5572r * this.f5570p, 0.0f);
                Path path3 = this.f5569o;
                float f11 = this.f5572r;
                float f12 = this.f5570p;
                path3.lineTo((f11 * f12) / 2.0f, this.f5573s * f12);
                this.f5569o.offset((fMin + rectF.centerX()) - f10, rectF.centerY() + (this.f5562h / 2.0f));
                this.f5569o.close();
                this.f5557c.setColor(this.f5575u);
                this.f5557c.setAlpha(this.f5574t);
                canvas.save();
                canvas.rotate(f8 + f9, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.f5569o, this.f5557c);
                canvas.restore();
            }
        }

        public int c() {
            return this.f5574t;
        }

        public float d() {
            return this.f5560f;
        }

        public int e() {
            return this.f5563i[f()];
        }

        public int f() {
            return (this.f5564j + 1) % this.f5563i.length;
        }

        public float g() {
            return this.f5559e;
        }

        public int h() {
            return this.f5563i[this.f5564j];
        }

        public float i() {
            return this.f5566l;
        }

        public float j() {
            return this.f5567m;
        }

        public float k() {
            return this.f5565k;
        }

        public void l() {
            t(f());
        }

        public void m() {
            this.f5565k = 0.0f;
            this.f5566l = 0.0f;
            this.f5567m = 0.0f;
            y(0.0f);
            v(0.0f);
            w(0.0f);
        }

        public void n(int i8) {
            this.f5574t = i8;
        }

        public void o(float f8, float f9) {
            this.f5572r = (int) f8;
            this.f5573s = (int) f9;
        }

        public void p(float f8) {
            if (f8 != this.f5570p) {
                this.f5570p = f8;
            }
        }

        public void q(float f8) {
            this.f5571q = f8;
        }

        public void r(int i8) {
            this.f5575u = i8;
        }

        public void s(ColorFilter colorFilter) {
            this.f5556b.setColorFilter(colorFilter);
        }

        public void t(int i8) {
            this.f5564j = i8;
            this.f5575u = this.f5563i[i8];
        }

        public void u(int[] iArr) {
            this.f5563i = iArr;
            t(0);
        }

        public void v(float f8) {
            this.f5560f = f8;
        }

        public void w(float f8) {
            this.f5561g = f8;
        }

        public void x(boolean z7) {
            if (this.f5568n != z7) {
                this.f5568n = z7;
            }
        }

        public void y(float f8) {
            this.f5559e = f8;
        }

        public void z(float f8) {
            this.f5562h = f8;
            this.f5556b.setStrokeWidth(f8);
        }
    }

    public b(Context context) {
        this.f5547i = ((Context) h.e(context)).getResources();
        c cVar = new c();
        this.f5545g = cVar;
        cVar.u(f5544f);
        k(2.5f);
        m();
    }

    public final void a(float f8, c cVar) {
        n(f8, cVar);
        float fFloor = (float) (Math.floor(cVar.j() / 0.8f) + 1.0d);
        cVar.y(cVar.k() + (((cVar.i() - 0.01f) - cVar.k()) * f8));
        cVar.v(cVar.i());
        cVar.w(cVar.j() + ((fFloor - cVar.j()) * f8));
    }

    public void b(float f8, c cVar, boolean z7) {
        float interpolation;
        float interpolation2;
        if (this.f5550l) {
            a(f8, cVar);
            return;
        }
        if (f8 != 1.0f || z7) {
            float fJ = cVar.j();
            if (f8 < 0.5f) {
                interpolation = cVar.k();
                interpolation2 = (f5543e.getInterpolation(f8 / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float fK = cVar.k() + 0.79f;
                interpolation = fK - (((1.0f - f5543e.getInterpolation((f8 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = fK;
            }
            float f9 = fJ + (0.20999998f * f8);
            float f10 = (f8 + this.f5549k) * 216.0f;
            cVar.y(interpolation);
            cVar.v(interpolation2);
            cVar.w(f9);
            h(f10);
        }
    }

    public final int c(float f8, int i8, int i9) {
        return ((((i8 >> 24) & 255) + ((int) ((((i9 >> 24) & 255) - r0) * f8))) << 24) | ((((i8 >> 16) & 255) + ((int) ((((i9 >> 16) & 255) - r1) * f8))) << 16) | ((((i8 >> 8) & 255) + ((int) ((((i9 >> 8) & 255) - r2) * f8))) << 8) | ((i8 & 255) + ((int) (f8 * ((i9 & 255) - r8))));
    }

    public void d(boolean z7) {
        this.f5545g.x(z7);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f5546h, bounds.exactCenterX(), bounds.exactCenterY());
        this.f5545g.a(canvas, bounds);
        canvas.restore();
    }

    public void e(float f8) {
        this.f5545g.p(f8);
        invalidateSelf();
    }

    public void f(int... iArr) {
        this.f5545g.u(iArr);
        this.f5545g.t(0);
        invalidateSelf();
    }

    public void g(float f8) {
        this.f5545g.w(f8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f5545g.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final void h(float f8) {
        this.f5546h = f8;
    }

    public final void i(float f8, float f9, float f10, float f11) {
        c cVar = this.f5545g;
        float f12 = this.f5547i.getDisplayMetrics().density;
        cVar.z(f9 * f12);
        cVar.q(f8 * f12);
        cVar.t(0);
        cVar.o(f10 * f12, f11 * f12);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f5548j.isRunning();
    }

    public void j(float f8, float f9) {
        this.f5545g.y(f8);
        this.f5545g.v(f9);
        invalidateSelf();
    }

    public void k(float f8) {
        this.f5545g.z(f8);
        invalidateSelf();
    }

    public void l(int i8) {
        if (i8 == 0) {
            i(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            i(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public final void m() {
        c cVar = this.f5545g;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new a(cVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(f5542d);
        valueAnimatorOfFloat.addListener(new C0066b(cVar));
        this.f5548j = valueAnimatorOfFloat;
    }

    public void n(float f8, c cVar) {
        if (f8 > 0.75f) {
            cVar.r(c((f8 - 0.75f) / 0.25f, cVar.h(), cVar.e()));
        } else {
            cVar.r(cVar.h());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f5545g.n(i8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f5545g.s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f5548j.cancel();
        this.f5545g.A();
        if (this.f5545g.d() != this.f5545g.g()) {
            this.f5550l = true;
            this.f5548j.setDuration(666L);
            this.f5548j.start();
        } else {
            this.f5545g.t(0);
            this.f5545g.m();
            this.f5548j.setDuration(1332L);
            this.f5548j.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f5548j.cancel();
        h(0.0f);
        this.f5545g.x(false);
        this.f5545g.t(0);
        this.f5545g.m();
        invalidateSelf();
    }
}
