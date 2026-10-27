package b.v.a;

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
import b.h.l.g;

/* JADX INFO: compiled from: CircularProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Drawable implements Animatable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f3179b = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Interpolator f3180c = new b.m.a.a.b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f3181d = {-16777216};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f3182e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3183f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Resources f3184g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Animator f3185h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f3186i;
    public boolean j;

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f3187a;

        public a(c cVar) {
            this.f3187a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            b.this.n(fFloatValue, this.f3187a);
            b.this.b(fFloatValue, this.f3187a, false);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: renamed from: b.v.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public class C0063b implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f3189a;

        public C0063b(c cVar) {
            this.f3189a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            b.this.b(1.0f, this.f3189a, true);
            this.f3189a.A();
            this.f3189a.l();
            b bVar = b.this;
            if (!bVar.j) {
                bVar.f3186i += 1.0f;
                return;
            }
            bVar.j = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.f3189a.x(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.f3186i = 0.0f;
        }
    }

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final RectF f3191a = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Paint f3192b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Paint f3193c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Paint f3194d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f3195e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f3196f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f3197g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f3198h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int[] f3199i;
        public int j;
        public float k;
        public float l;
        public float m;
        public boolean n;
        public Path o;
        public float p;
        public float q;
        public int r;
        public int s;
        public int t;
        public int u;

        public c() {
            Paint paint = new Paint();
            this.f3192b = paint;
            Paint paint2 = new Paint();
            this.f3193c = paint2;
            Paint paint3 = new Paint();
            this.f3194d = paint3;
            this.f3195e = 0.0f;
            this.f3196f = 0.0f;
            this.f3197g = 0.0f;
            this.f3198h = 5.0f;
            this.p = 1.0f;
            this.t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        public void A() {
            this.k = this.f3195e;
            this.l = this.f3196f;
            this.m = this.f3197g;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f3191a;
            float f2 = this.q;
            float fMin = (this.f3198h / 2.0f) + f2;
            if (f2 <= 0.0f) {
                fMin = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.f3198h / 2.0f);
            }
            rectF.set(rect.centerX() - fMin, rect.centerY() - fMin, rect.centerX() + fMin, rect.centerY() + fMin);
            float f3 = this.f3195e;
            float f4 = this.f3197g;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.f3196f + f4) * 360.0f) - f5;
            this.f3192b.setColor(this.u);
            this.f3192b.setAlpha(this.t);
            float f7 = this.f3198h / 2.0f;
            rectF.inset(f7, f7);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f3194d);
            float f8 = -f7;
            rectF.inset(f8, f8);
            canvas.drawArc(rectF, f5, f6, false, this.f3192b);
            b(canvas, f5, f6, rectF);
        }

        public void b(Canvas canvas, float f2, float f3, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    Path path2 = new Path();
                    this.o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float fMin = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f4 = (this.r * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path3 = this.o;
                float f5 = this.r;
                float f6 = this.p;
                path3.lineTo((f5 * f6) / 2.0f, this.s * f6);
                this.o.offset((fMin + rectF.centerX()) - f4, rectF.centerY() + (this.f3198h / 2.0f));
                this.o.close();
                this.f3193c.setColor(this.u);
                this.f3193c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.f3193c);
                canvas.restore();
            }
        }

        public int c() {
            return this.t;
        }

        public float d() {
            return this.f3196f;
        }

        public int e() {
            return this.f3199i[f()];
        }

        public int f() {
            return (this.j + 1) % this.f3199i.length;
        }

        public float g() {
            return this.f3195e;
        }

        public int h() {
            return this.f3199i[this.j];
        }

        public float i() {
            return this.l;
        }

        public float j() {
            return this.m;
        }

        public float k() {
            return this.k;
        }

        public void l() {
            t(f());
        }

        public void m() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            y(0.0f);
            v(0.0f);
            w(0.0f);
        }

        public void n(int i2) {
            this.t = i2;
        }

        public void o(float f2, float f3) {
            this.r = (int) f2;
            this.s = (int) f3;
        }

        public void p(float f2) {
            if (f2 != this.p) {
                this.p = f2;
            }
        }

        public void q(float f2) {
            this.q = f2;
        }

        public void r(int i2) {
            this.u = i2;
        }

        public void s(ColorFilter colorFilter) {
            this.f3192b.setColorFilter(colorFilter);
        }

        public void t(int i2) {
            this.j = i2;
            this.u = this.f3199i[i2];
        }

        public void u(int[] iArr) {
            this.f3199i = iArr;
            t(0);
        }

        public void v(float f2) {
            this.f3196f = f2;
        }

        public void w(float f2) {
            this.f3197g = f2;
        }

        public void x(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        public void y(float f2) {
            this.f3195e = f2;
        }

        public void z(float f2) {
            this.f3198h = f2;
            this.f3192b.setStrokeWidth(f2);
        }
    }

    public b(Context context) {
        this.f3184g = ((Context) g.c(context)).getResources();
        c cVar = new c();
        this.f3182e = cVar;
        cVar.u(f3181d);
        k(2.5f);
        m();
    }

    public final void a(float f2, c cVar) {
        n(f2, cVar);
        float fFloor = (float) (Math.floor(cVar.j() / 0.8f) + 1.0d);
        cVar.y(cVar.k() + (((cVar.i() - 0.01f) - cVar.k()) * f2));
        cVar.v(cVar.i());
        cVar.w(cVar.j() + ((fFloor - cVar.j()) * f2));
    }

    public void b(float f2, c cVar, boolean z) {
        float interpolation;
        float interpolation2;
        if (this.j) {
            a(f2, cVar);
            return;
        }
        if (f2 != 1.0f || z) {
            float fJ = cVar.j();
            if (f2 < 0.5f) {
                interpolation = cVar.k();
                interpolation2 = (f3180c.getInterpolation(f2 / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float fK = cVar.k() + 0.79f;
                interpolation = fK - (((1.0f - f3180c.getInterpolation((f2 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = fK;
            }
            float f3 = fJ + (0.20999998f * f2);
            float f4 = (f2 + this.f3186i) * 216.0f;
            cVar.y(interpolation);
            cVar.v(interpolation2);
            cVar.w(f3);
            h(f4);
        }
    }

    public final int c(float f2, int i2, int i3) {
        return ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r0) * f2))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r1) * f2))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r2) * f2))) << 8) | ((i2 & 255) + ((int) (f2 * ((i3 & 255) - r8))));
    }

    public void d(boolean z) {
        this.f3182e.x(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f3183f, bounds.exactCenterX(), bounds.exactCenterY());
        this.f3182e.a(canvas, bounds);
        canvas.restore();
    }

    public void e(float f2) {
        this.f3182e.p(f2);
        invalidateSelf();
    }

    public void f(int... iArr) {
        this.f3182e.u(iArr);
        this.f3182e.t(0);
        invalidateSelf();
    }

    public void g(float f2) {
        this.f3182e.w(f2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f3182e.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final void h(float f2) {
        this.f3183f = f2;
    }

    public final void i(float f2, float f3, float f4, float f5) {
        c cVar = this.f3182e;
        float f6 = this.f3184g.getDisplayMetrics().density;
        cVar.z(f3 * f6);
        cVar.q(f2 * f6);
        cVar.t(0);
        cVar.o(f4 * f6, f5 * f6);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f3185h.isRunning();
    }

    public void j(float f2, float f3) {
        this.f3182e.y(f2);
        this.f3182e.v(f3);
        invalidateSelf();
    }

    public void k(float f2) {
        this.f3182e.z(f2);
        invalidateSelf();
    }

    public void l(int i2) {
        if (i2 == 0) {
            i(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            i(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public final void m() {
        c cVar = this.f3182e;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new a(cVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(f3179b);
        valueAnimatorOfFloat.addListener(new C0063b(cVar));
        this.f3185h = valueAnimatorOfFloat;
    }

    public void n(float f2, c cVar) {
        if (f2 > 0.75f) {
            cVar.r(c((f2 - 0.75f) / 0.25f, cVar.h(), cVar.e()));
        } else {
            cVar.r(cVar.h());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f3182e.n(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3182e.s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f3185h.cancel();
        this.f3182e.A();
        if (this.f3182e.d() != this.f3182e.g()) {
            this.j = true;
            this.f3185h.setDuration(666L);
            this.f3185h.start();
        } else {
            this.f3182e.t(0);
            this.f3182e.m();
            this.f3185h.setDuration(1332L);
            this.f3185h.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f3185h.cancel();
        h(0.0f);
        this.f3182e.x(false);
        this.f3182e.t(0);
        this.f3182e.m();
        invalidateSelf();
    }
}
