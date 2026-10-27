package a2;

import android.view.Choreographer;

/* JADX INFO: compiled from: LottieValueAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends a implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public o1.e f65m;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f58f = 1.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f59g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f60h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f61i = 0.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f62j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f63k = -2.1474836E9f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f64l = 2.1474836E9f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f66n = false;

    public final void A() {
        if (this.f65m == null) {
            return;
        }
        float f8 = this.f61i;
        if (f8 < this.f63k || f8 > this.f64l) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.f63k), Float.valueOf(this.f64l), Float.valueOf(this.f61i)));
        }
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void cancel() {
        a();
        q();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j8) {
        p();
        if (this.f65m == null || !isRunning()) {
            return;
        }
        o1.d.a("LottieValueAnimator#doFrame");
        float fI = (this.f60h != 0 ? j8 - r1 : 0L) / i();
        float f8 = this.f61i;
        if (m()) {
            fI = -fI;
        }
        float f9 = f8 + fI;
        this.f61i = f9;
        boolean z7 = !g.d(f9, k(), j());
        this.f61i = g.b(this.f61i, k(), j());
        this.f60h = j8;
        e();
        if (z7) {
            if (getRepeatCount() == -1 || this.f62j < getRepeatCount()) {
                c();
                this.f62j++;
                if (getRepeatMode() == 2) {
                    this.f59g = !this.f59g;
                    t();
                } else {
                    this.f61i = m() ? j() : k();
                }
                this.f60h = j8;
            } else {
                this.f61i = this.f58f < 0.0f ? k() : j();
                q();
                b(m());
            }
        }
        A();
        o1.d.b("LottieValueAnimator#doFrame");
    }

    public void endAnimation() {
        q();
        b(m());
    }

    public void f() {
        this.f65m = null;
        this.f63k = -2.1474836E9f;
        this.f64l = 2.1474836E9f;
    }

    public float g() {
        o1.e eVar = this.f65m;
        if (eVar == null) {
            return 0.0f;
        }
        return (this.f61i - eVar.o()) / (this.f65m.f() - this.f65m.o());
    }

    @Override // android.animation.ValueAnimator
    public float getAnimatedFraction() {
        float fK;
        float fJ;
        float fK2;
        if (this.f65m == null) {
            return 0.0f;
        }
        if (m()) {
            fK = j() - this.f61i;
            fJ = j();
            fK2 = k();
        } else {
            fK = this.f61i - k();
            fJ = j();
            fK2 = k();
        }
        return fK / (fJ - fK2);
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(g());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        o1.e eVar = this.f65m;
        if (eVar == null) {
            return 0L;
        }
        return (long) eVar.d();
    }

    public float h() {
        return this.f61i;
    }

    public final float i() {
        o1.e eVar = this.f65m;
        if (eVar == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / eVar.h()) / Math.abs(this.f58f);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.f66n;
    }

    public float j() {
        o1.e eVar = this.f65m;
        if (eVar == null) {
            return 0.0f;
        }
        float f8 = this.f64l;
        return f8 == 2.1474836E9f ? eVar.f() : f8;
    }

    public float k() {
        o1.e eVar = this.f65m;
        if (eVar == null) {
            return 0.0f;
        }
        float f8 = this.f63k;
        return f8 == -2.1474836E9f ? eVar.o() : f8;
    }

    public float l() {
        return this.f58f;
    }

    public final boolean m() {
        return l() < 0.0f;
    }

    public void n() {
        q();
    }

    public void o() {
        this.f66n = true;
        d(m());
        v((int) (m() ? j() : k()));
        this.f60h = 0L;
        this.f62j = 0;
        p();
    }

    public void p() {
        if (isRunning()) {
            r(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    public void q() {
        r(true);
    }

    public void r(boolean z7) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z7) {
            this.f66n = false;
        }
    }

    public void s() {
        this.f66n = true;
        p();
        this.f60h = 0L;
        if (m() && h() == k()) {
            this.f61i = j();
        } else {
            if (m() || h() != j()) {
                return;
            }
            this.f61i = k();
        }
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i8) {
        super.setRepeatMode(i8);
        if (i8 == 2 || !this.f59g) {
            return;
        }
        this.f59g = false;
        t();
    }

    public void t() {
        z(-l());
    }

    public void u(o1.e eVar) {
        boolean z7 = this.f65m == null;
        this.f65m = eVar;
        if (z7) {
            x((int) Math.max(this.f63k, eVar.o()), (int) Math.min(this.f64l, eVar.f()));
        } else {
            x((int) eVar.o(), (int) eVar.f());
        }
        float f8 = this.f61i;
        this.f61i = 0.0f;
        v((int) f8);
        e();
    }

    public void v(float f8) {
        if (this.f61i == f8) {
            return;
        }
        this.f61i = g.b(f8, k(), j());
        this.f60h = 0L;
        e();
    }

    public void w(float f8) {
        x(this.f63k, f8);
    }

    public void x(float f8, float f9) {
        if (f8 > f9) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f8), Float.valueOf(f9)));
        }
        o1.e eVar = this.f65m;
        float fO = eVar == null ? -3.4028235E38f : eVar.o();
        o1.e eVar2 = this.f65m;
        float f10 = eVar2 == null ? Float.MAX_VALUE : eVar2.f();
        this.f63k = g.b(f8, fO, f10);
        this.f64l = g.b(f9, fO, f10);
        v((int) g.b(this.f61i, f8, f9));
    }

    public void y(int i8) {
        x(i8, (int) this.f64l);
    }

    public void z(float f8) {
        this.f58f = f8;
    }
}
