package androidx.swiperefreshlayout.widget;

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
import b.g.j.h;

/* JADX INFO: compiled from: CircularProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Drawable implements Animatable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Interpolator f1871b = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Interpolator f1872c = new b.j.a.a.b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f1873d = {-16777216};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c f1874e;
    private float f;
    private Resources g;
    private Animator h;
    float i;
    boolean j;

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f1875a;

        a(c cVar) {
            this.f1875a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            b.this.n(fFloatValue, this.f1875a);
            b.this.b(fFloatValue, this.f1875a, false);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: renamed from: androidx.swiperefreshlayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    class C0035b implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f1877a;

        C0035b(c cVar) {
            this.f1877a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            b.this.b(1.0f, this.f1877a, true);
            this.f1877a.A();
            this.f1877a.l();
            b bVar = b.this;
            if (!bVar.j) {
                bVar.i += 1.0f;
                return;
            }
            bVar.j = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.f1877a.x(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.i = 0.0f;
        }
    }

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final RectF f1879a = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Paint f1880b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Paint f1881c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Paint f1882d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f1883e;
        float f;
        float g;
        float h;
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p;
        float q;
        int r;
        int s;
        int t;
        int u;

        c() {
            Paint paint = new Paint();
            this.f1880b = paint;
            Paint paint2 = new Paint();
            this.f1881c = paint2;
            Paint paint3 = new Paint();
            this.f1882d = paint3;
            this.f1883e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 5.0f;
            this.p = 1.0f;
            this.t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        void A() {
            this.k = this.f1883e;
            this.l = this.f;
            this.m = this.g;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f1879a;
            float f = this.q;
            float fMin = (this.h / 2.0f) + f;
            if (f <= 0.0f) {
                fMin = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - fMin, rect.centerY() - fMin, rect.centerX() + fMin, rect.centerY() + fMin);
            float f2 = this.f1883e;
            float f3 = this.g;
            float f4 = (f2 + f3) * 360.0f;
            float f5 = ((this.f + f3) * 360.0f) - f4;
            this.f1880b.setColor(this.u);
            this.f1880b.setAlpha(this.t);
            float f6 = this.h / 2.0f;
            rectF.inset(f6, f6);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f1882d);
            float f7 = -f6;
            rectF.inset(f7, f7);
            canvas.drawArc(rectF, f4, f5, false, this.f1880b);
            b(canvas, f4, f5, rectF);
        }

        void b(Canvas canvas, float f, float f2, RectF rectF) {
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
                float f3 = (this.r * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path3 = this.o;
                float f4 = this.r;
                float f5 = this.p;
                path3.lineTo((f4 * f5) / 2.0f, this.s * f5);
                this.o.offset((fMin + rectF.centerX()) - f3, rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.f1881c.setColor(this.u);
                this.f1881c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.f1881c);
                canvas.restore();
            }
        }

        int c() {
            return this.t;
        }

        float d() {
            return this.f;
        }

        int e() {
            return this.i[f()];
        }

        int f() {
            return (this.j + 1) % this.i.length;
        }

        float g() {
            return this.f1883e;
        }

        int h() {
            return this.i[this.j];
        }

        float i() {
            return this.l;
        }

        float j() {
            return this.m;
        }

        float k() {
            return this.k;
        }

        void l() {
            t(f());
        }

        void m() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            y(0.0f);
            v(0.0f);
            w(0.0f);
        }

        void n(int i) {
            this.t = i;
        }

        void o(float f, float f2) {
            this.r = (int) f;
            this.s = (int) f2;
        }

        void p(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }

        void q(float f) {
            this.q = f;
        }

        void r(int i) {
            this.u = i;
        }

        void s(ColorFilter colorFilter) {
            this.f1880b.setColorFilter(colorFilter);
        }

        void t(int i) {
            this.j = i;
            this.u = this.i[i];
        }

        void u(int[] iArr) {
            this.i = iArr;
            t(0);
        }

        void v(float f) {
            this.f = f;
        }

        void w(float f) {
            this.g = f;
        }

        void x(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        void y(float f) {
            this.f1883e = f;
        }

        void z(float f) {
            this.h = f;
            this.f1880b.setStrokeWidth(f);
        }
    }

    public b(Context context) {
        this.g = ((Context) h.c(context)).getResources();
        c cVar = new c();
        this.f1874e = cVar;
        cVar.u(f1873d);
        k(2.5f);
        m();
    }

    private void a(float f, c cVar) {
        n(f, cVar);
        float fFloor = (float) (Math.floor(cVar.j() / 0.8f) + 1.0d);
        cVar.y(cVar.k() + (((cVar.i() - 0.01f) - cVar.k()) * f));
        cVar.v(cVar.i());
        cVar.w(cVar.j() + ((fFloor - cVar.j()) * f));
    }

    private int c(float f, int i, int i2) {
        return ((((i >> 24) & 255) + ((int) ((((i2 >> 24) & 255) - r0) * f))) << 24) | ((((i >> 16) & 255) + ((int) ((((i2 >> 16) & 255) - r1) * f))) << 16) | ((((i >> 8) & 255) + ((int) ((((i2 >> 8) & 255) - r2) * f))) << 8) | ((i & 255) + ((int) (f * ((i2 & 255) - r8))));
    }

    private void h(float f) {
        this.f = f;
    }

    private void i(float f, float f2, float f3, float f4) {
        c cVar = this.f1874e;
        float f5 = this.g.getDisplayMetrics().density;
        cVar.z(f2 * f5);
        cVar.q(f * f5);
        cVar.t(0);
        cVar.o(f3 * f5, f4 * f5);
    }

    private void m() {
        c cVar = this.f1874e;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new a(cVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(f1871b);
        valueAnimatorOfFloat.addListener(new C0035b(cVar));
        this.h = valueAnimatorOfFloat;
    }

    void b(float f, c cVar, boolean z) {
        float interpolation;
        float interpolation2;
        if (this.j) {
            a(f, cVar);
            return;
        }
        if (f != 1.0f || z) {
            float fJ = cVar.j();
            if (f < 0.5f) {
                interpolation = cVar.k();
                interpolation2 = (f1872c.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float fK = cVar.k() + 0.79f;
                interpolation = fK - (((1.0f - f1872c.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = fK;
            }
            float f2 = fJ + (0.20999998f * f);
            float f3 = (f + this.i) * 216.0f;
            cVar.y(interpolation);
            cVar.v(interpolation2);
            cVar.w(f2);
            h(f3);
        }
    }

    public void d(boolean z) {
        this.f1874e.x(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f, bounds.exactCenterX(), bounds.exactCenterY());
        this.f1874e.a(canvas, bounds);
        canvas.restore();
    }

    public void e(float f) {
        this.f1874e.p(f);
        invalidateSelf();
    }

    public void f(int... iArr) {
        this.f1874e.u(iArr);
        this.f1874e.t(0);
        invalidateSelf();
    }

    public void g(float f) {
        this.f1874e.w(f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f1874e.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.h.isRunning();
    }

    public void j(float f, float f2) {
        this.f1874e.y(f);
        this.f1874e.v(f2);
        invalidateSelf();
    }

    public void k(float f) {
        this.f1874e.z(f);
        invalidateSelf();
    }

    public void l(int i) {
        if (i == 0) {
            i(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            i(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    void n(float f, c cVar) {
        if (f > 0.75f) {
            cVar.r(c((f - 0.75f) / 0.25f, cVar.h(), cVar.e()));
        } else {
            cVar.r(cVar.h());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f1874e.n(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1874e.s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.h.cancel();
        this.f1874e.A();
        if (this.f1874e.d() != this.f1874e.g()) {
            this.j = true;
            this.h.setDuration(666L);
            this.h.start();
        } else {
            this.f1874e.t(0);
            this.f1874e.m();
            this.h.setDuration(1332L);
            this.h.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.h.cancel();
        h(0.0f);
        this.f1874e.x(false);
        this.f1874e.t(0);
        this.f1874e.m();
        invalidateSelf();
    }
}
