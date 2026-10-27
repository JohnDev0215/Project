package com.byd.filemanager.ui.d;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h implements com.byd.filemanager.ui.d.b, View.OnTouchListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final com.byd.filemanager.ui.d.c f3789c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final d f3790d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final g f3791e;
    protected final b f;
    protected c g;
    protected float j;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final f f3788b = new f();
    protected com.byd.filemanager.ui.d.d h = new com.byd.filemanager.ui.d.f();
    protected com.byd.filemanager.ui.d.e i = new com.byd.filemanager.ui.d.g();

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Property<View, Float> f3792a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3793b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3794c;

        protected a() {
        }

        protected abstract void a(View view);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected class b implements c, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Interpolator f3795a = new DecelerateInterpolator();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final float f3796b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final float f3797c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected final a f3798d;

        public b(float f) {
            this.f3796b = f;
            this.f3797c = f * 2.0f;
            this.f3798d = h.this.b();
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public void a(c cVar) {
            h hVar = h.this;
            hVar.h.a(hVar, cVar.c(), c());
            Animator animatorE = e();
            animatorE.addListener(this);
            animatorE.start();
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public boolean b(MotionEvent motionEvent) {
            return true;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public int c() {
            return 3;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public boolean d(MotionEvent motionEvent) {
            return true;
        }

        protected Animator e() {
            View viewC = h.this.f3789c.c();
            this.f3798d.a(viewC);
            h hVar = h.this;
            float f = hVar.j;
            if (f == 0.0f || ((f < 0.0f && hVar.f3788b.f3807c) || (f > 0.0f && !hVar.f3788b.f3807c))) {
                return f(this.f3798d.f3793b);
            }
            float f2 = (-f) / this.f3796b;
            float f3 = f2 >= 0.0f ? f2 : 0.0f;
            float f4 = this.f3798d.f3793b + (((-f) * f) / this.f3797c);
            ObjectAnimator objectAnimatorG = g(viewC, (int) f3, f4);
            ObjectAnimator objectAnimatorF = f(f4);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorG, objectAnimatorF);
            return animatorSet;
        }

        protected ObjectAnimator f(float f) {
            View viewC = h.this.f3789c.c();
            float fAbs = Math.abs(f);
            a aVar = this.f3798d;
            float f2 = (fAbs / aVar.f3794c) * 800.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewC, aVar.f3792a, h.this.f3788b.f3806b);
            objectAnimatorOfFloat.setDuration(Math.max((int) f2, 200));
            objectAnimatorOfFloat.setInterpolator(this.f3795a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        protected ObjectAnimator g(View view, int i, float f) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.f3798d.f3792a, f);
            objectAnimatorOfFloat.setDuration(i);
            objectAnimatorOfFloat.setInterpolator(this.f3795a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            hVar.e(hVar.f3790d);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            h hVar = h.this;
            hVar.i.a(hVar, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected interface c {
        void a(c cVar);

        boolean b(MotionEvent motionEvent);

        int c();

        boolean d(MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected class d implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final e f3800a;

        public d() {
            this.f3800a = h.this.c();
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public void a(c cVar) {
            h hVar = h.this;
            hVar.h.a(hVar, cVar.c(), c());
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public boolean b(MotionEvent motionEvent) {
            return false;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public int c() {
            return 0;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public boolean d(MotionEvent motionEvent) {
            if (!this.f3800a.a(h.this.f3789c.c(), motionEvent)) {
                return false;
            }
            if (!(h.this.f3789c.b() && this.f3800a.f3804c) && (!h.this.f3789c.a() || this.f3800a.f3804c)) {
                return false;
            }
            h.this.f3788b.f3805a = motionEvent.getPointerId(0);
            h hVar = h.this;
            f fVar = hVar.f3788b;
            e eVar = this.f3800a;
            fVar.f3806b = eVar.f3802a;
            fVar.f3807c = eVar.f3804c;
            hVar.e(hVar.f3791e);
            return h.this.f3791e.d(motionEvent);
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f3802a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3803b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3804c;

        protected e() {
        }

        protected abstract boolean a(View view, MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected int f3805a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected float f3806b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected boolean f3807c;

        protected f() {
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    protected class g implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final float f3808a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final float f3809b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final e f3810c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3811d;

        public g(float f, float f2) {
            this.f3810c = h.this.c();
            this.f3808a = f;
            this.f3809b = f2;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public void a(c cVar) {
            h hVar = h.this;
            this.f3811d = hVar.f3788b.f3807c ? 1 : 2;
            hVar.h.a(hVar, cVar.c(), c());
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public boolean b(MotionEvent motionEvent) {
            h hVar = h.this;
            hVar.e(hVar.f);
            return false;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public int c() {
            return this.f3811d;
        }

        @Override // com.byd.filemanager.ui.d.h.c
        public boolean d(MotionEvent motionEvent) {
            if (h.this.f3788b.f3805a != motionEvent.getPointerId(0)) {
                h hVar = h.this;
                hVar.e(hVar.f);
                return true;
            }
            View viewC = h.this.f3789c.c();
            if (!this.f3810c.a(viewC, motionEvent)) {
                return true;
            }
            e eVar = this.f3810c;
            float f = eVar.f3803b;
            boolean z = eVar.f3804c;
            h hVar2 = h.this;
            f fVar = hVar2.f3788b;
            boolean z2 = fVar.f3807c;
            float f2 = f / (z == z2 ? this.f3808a : this.f3809b);
            float f3 = eVar.f3802a + f2;
            if ((z2 && !z && f3 <= fVar.f3806b) || (!z2 && z && f3 >= fVar.f3806b)) {
                hVar2.g(viewC, fVar.f3806b, motionEvent);
                h hVar3 = h.this;
                hVar3.i.a(hVar3, this.f3811d, 0.0f);
                h hVar4 = h.this;
                hVar4.e(hVar4.f3790d);
                return true;
            }
            if (viewC.getParent() != null) {
                viewC.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
            if (eventTime > 0) {
                h.this.j = f2 / eventTime;
            }
            h.this.f(viewC, f3);
            h hVar5 = h.this;
            hVar5.i.a(hVar5, this.f3811d, f3);
            return true;
        }
    }

    public h(com.byd.filemanager.ui.d.c cVar, float f2, float f3, float f4) {
        this.f3789c = cVar;
        this.f = new b(f2);
        this.f3791e = new g(f3, f4);
        d dVar = new d();
        this.f3790d = dVar;
        this.g = dVar;
        a();
    }

    private void a() {
        d().setOnTouchListener(this);
        d().setOverScrollMode(2);
    }

    protected abstract a b();

    protected abstract e c();

    public View d() {
        return this.f3789c.c();
    }

    protected void e(c cVar) {
        c cVar2 = this.g;
        this.g = cVar;
        cVar.a(cVar2);
    }

    protected abstract void f(View view, float f2);

    protected abstract void g(View view, float f2, MotionEvent motionEvent);

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                return this.g.d(motionEvent);
            }
            if (action != 3) {
                return false;
            }
        }
        return this.g.b(motionEvent);
    }
}
