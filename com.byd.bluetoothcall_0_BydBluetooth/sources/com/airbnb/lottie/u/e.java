package com.airbnb.lottie.u;

import android.view.Choreographer;

/* JADX INFO: compiled from: LottieValueAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends a implements Choreographer.FrameCallback {
    private com.airbnb.lottie.d k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3187d = 1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3188e = false;
    private long f = 0;
    private float g = 0.0f;
    private int h = 0;
    private float i = -2.1474836E9f;
    private float j = 2.1474836E9f;
    protected boolean l = false;

    private void A() {
        if (this.k == null) {
            return;
        }
        float f = this.g;
        if (f < this.i || f > this.j) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.i), Float.valueOf(this.j), Float.valueOf(this.g)));
        }
    }

    private float i() {
        com.airbnb.lottie.d dVar = this.k;
        if (dVar == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / dVar.h()) / Math.abs(this.f3187d);
    }

    private boolean m() {
        return l() < 0.0f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void cancel() {
        a();
        q();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        p();
        if (this.k == null || !isRunning()) {
            return;
        }
        com.airbnb.lottie.c.a("LottieValueAnimator#doFrame");
        float fI = (this.f != 0 ? j - r1 : 0L) / i();
        float f = this.g;
        if (m()) {
            fI = -fI;
        }
        float f2 = f + fI;
        this.g = f2;
        boolean z = !g.d(f2, k(), j());
        this.g = g.b(this.g, k(), j());
        this.f = j;
        e();
        if (z) {
            if (getRepeatCount() == -1 || this.h < getRepeatCount()) {
                c();
                this.h++;
                if (getRepeatMode() == 2) {
                    this.f3188e = !this.f3188e;
                    t();
                } else {
                    this.g = m() ? j() : k();
                }
                this.f = j;
            } else {
                this.g = this.f3187d < 0.0f ? k() : j();
                q();
                b(m());
            }
        }
        A();
        com.airbnb.lottie.c.b("LottieValueAnimator#doFrame");
    }

    public void endAnimation() {
        q();
        b(m());
    }

    public void f() {
        this.k = null;
        this.i = -2.1474836E9f;
        this.j = 2.1474836E9f;
    }

    public float g() {
        com.airbnb.lottie.d dVar = this.k;
        if (dVar == null) {
            return 0.0f;
        }
        return (this.g - dVar.o()) / (this.k.f() - this.k.o());
    }

    @Override // android.animation.ValueAnimator
    public float getAnimatedFraction() {
        float fK;
        float fJ;
        float fK2;
        if (this.k == null) {
            return 0.0f;
        }
        if (m()) {
            fK = j() - this.g;
            fJ = j();
            fK2 = k();
        } else {
            fK = this.g - k();
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
        com.airbnb.lottie.d dVar = this.k;
        if (dVar == null) {
            return 0L;
        }
        return (long) dVar.d();
    }

    public float h() {
        return this.g;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.l;
    }

    public float j() {
        com.airbnb.lottie.d dVar = this.k;
        if (dVar == null) {
            return 0.0f;
        }
        float f = this.j;
        return f == 2.1474836E9f ? dVar.f() : f;
    }

    public float k() {
        com.airbnb.lottie.d dVar = this.k;
        if (dVar == null) {
            return 0.0f;
        }
        float f = this.i;
        return f == -2.1474836E9f ? dVar.o() : f;
    }

    public float l() {
        return this.f3187d;
    }

    public void n() {
        q();
    }

    public void o() {
        this.l = true;
        d(m());
        v((int) (m() ? j() : k()));
        this.f = 0L;
        this.h = 0;
        p();
    }

    protected void p() {
        if (isRunning()) {
            r(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    protected void q() {
        r(true);
    }

    protected void r(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.l = false;
        }
    }

    public void s() {
        this.l = true;
        p();
        this.f = 0L;
        if (m() && h() == k()) {
            this.g = j();
        } else {
            if (m() || h() != j()) {
                return;
            }
            this.g = k();
        }
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i) {
        super.setRepeatMode(i);
        if (i == 2 || !this.f3188e) {
            return;
        }
        this.f3188e = false;
        t();
    }

    public void t() {
        z(-l());
    }

    public void u(com.airbnb.lottie.d dVar) {
        boolean z = this.k == null;
        this.k = dVar;
        if (z) {
            x((int) Math.max(this.i, dVar.o()), (int) Math.min(this.j, dVar.f()));
        } else {
            x((int) dVar.o(), (int) dVar.f());
        }
        float f = this.g;
        this.g = 0.0f;
        v((int) f);
    }

    public void v(float f) {
        if (this.g == f) {
            return;
        }
        this.g = g.b(f, k(), j());
        this.f = 0L;
        e();
    }

    public void w(float f) {
        x(this.i, f);
    }

    public void x(float f, float f2) {
        if (f > f2) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f), Float.valueOf(f2)));
        }
        com.airbnb.lottie.d dVar = this.k;
        float fO = dVar == null ? -3.4028235E38f : dVar.o();
        com.airbnb.lottie.d dVar2 = this.k;
        float f3 = dVar2 == null ? Float.MAX_VALUE : dVar2.f();
        this.i = g.b(f, fO, f3);
        this.j = g.b(f2, fO, f3);
        v((int) g.b(this.g, f, f2));
    }

    public void y(int i) {
        x(i, (int) this.j);
    }

    public void z(float f) {
        this.f3187d = f;
    }
}
