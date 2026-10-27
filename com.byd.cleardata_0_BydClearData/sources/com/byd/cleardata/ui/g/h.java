package com.byd.cleardata.ui.g;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class h implements com.byd.cleardata.ui.g.b, View.OnTouchListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final com.byd.cleardata.ui.g.c f2887c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final d f2888d;
    protected final g e;
    protected final b f;
    protected c g;
    protected float j;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final f f2886b = new f();
    protected com.byd.cleardata.ui.g.d h = new com.byd.cleardata.ui.g.f();
    protected com.byd.cleardata.ui.g.e i = new com.byd.cleardata.ui.g.g();

    protected static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Property<View, Float> f2889a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2890b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2891c;

        protected a() {
        }

        protected abstract void a(View view);
    }

    protected class b implements c, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Interpolator f2892a = new DecelerateInterpolator();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final float f2893b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final float f2894c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected final a f2895d;

        public b(float f) {
            this.f2893b = f;
            this.f2894c = f * 2.0f;
            this.f2895d = h.this.b();
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public void a(c cVar) {
            h hVar = h.this;
            hVar.h.a(hVar, cVar.c(), c());
            Animator animatorE = e();
            animatorE.addListener(this);
            animatorE.start();
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public boolean b(MotionEvent motionEvent) {
            return true;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public int c() {
            return 3;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public boolean d(MotionEvent motionEvent) {
            return true;
        }

        protected Animator e() {
            View viewC = h.this.f2887c.c();
            this.f2895d.a(viewC);
            h hVar = h.this;
            float f = hVar.j;
            if (f == 0.0f || ((f < 0.0f && hVar.f2886b.f2903c) || (f > 0.0f && !hVar.f2886b.f2903c))) {
                return f(this.f2895d.f2890b);
            }
            float f2 = (-f) / this.f2893b;
            float f3 = f2 >= 0.0f ? f2 : 0.0f;
            float f4 = this.f2895d.f2890b + (((-f) * f) / this.f2894c);
            ObjectAnimator objectAnimatorG = g(viewC, (int) f3, f4);
            ObjectAnimator objectAnimatorF = f(f4);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorG, objectAnimatorF);
            return animatorSet;
        }

        protected ObjectAnimator f(float f) {
            View viewC = h.this.f2887c.c();
            float fAbs = Math.abs(f);
            a aVar = this.f2895d;
            float f2 = (fAbs / aVar.f2891c) * 800.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewC, aVar.f2889a, h.this.f2886b.f2902b);
            objectAnimatorOfFloat.setDuration(Math.max((int) f2, 200));
            objectAnimatorOfFloat.setInterpolator(this.f2892a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        protected ObjectAnimator g(View view, int i, float f) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.f2895d.f2889a, f);
            objectAnimatorOfFloat.setDuration(i);
            objectAnimatorOfFloat.setInterpolator(this.f2892a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            hVar.e(hVar.f2888d);
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

    protected interface c {
        void a(c cVar);

        boolean b(MotionEvent motionEvent);

        int c();

        boolean d(MotionEvent motionEvent);
    }

    protected class d implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final e f2896a;

        public d() {
            this.f2896a = h.this.c();
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public void a(c cVar) {
            h hVar = h.this;
            hVar.h.a(hVar, cVar.c(), c());
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public boolean b(MotionEvent motionEvent) {
            return false;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public int c() {
            return 0;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public boolean d(MotionEvent motionEvent) {
            if (!this.f2896a.a(h.this.f2887c.c(), motionEvent)) {
                return false;
            }
            if (!(h.this.f2887c.b() && this.f2896a.f2900c) && (!h.this.f2887c.a() || this.f2896a.f2900c)) {
                return false;
            }
            h.this.f2886b.f2901a = motionEvent.getPointerId(0);
            h hVar = h.this;
            f fVar = hVar.f2886b;
            e eVar = this.f2896a;
            fVar.f2902b = eVar.f2898a;
            fVar.f2903c = eVar.f2900c;
            hVar.e(hVar.e);
            return h.this.e.d(motionEvent);
        }
    }

    protected static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2898a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2899b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2900c;

        protected e() {
        }

        protected abstract boolean a(View view, MotionEvent motionEvent);
    }

    protected static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected int f2901a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected float f2902b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected boolean f2903c;

        protected f() {
        }
    }

    protected class g implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final float f2904a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final float f2905b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final e f2906c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2907d;

        public g(float f, float f2) {
            this.f2906c = h.this.c();
            this.f2904a = f;
            this.f2905b = f2;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public void a(c cVar) {
            h hVar = h.this;
            this.f2907d = hVar.f2886b.f2903c ? 1 : 2;
            hVar.h.a(hVar, cVar.c(), c());
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public boolean b(MotionEvent motionEvent) {
            h hVar = h.this;
            hVar.e(hVar.f);
            return false;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public int c() {
            return this.f2907d;
        }

        @Override // com.byd.cleardata.ui.g.h.c
        public boolean d(MotionEvent motionEvent) {
            if (h.this.f2886b.f2901a != motionEvent.getPointerId(0)) {
                h hVar = h.this;
                hVar.e(hVar.f);
                return true;
            }
            View viewC = h.this.f2887c.c();
            if (!this.f2906c.a(viewC, motionEvent)) {
                return true;
            }
            e eVar = this.f2906c;
            float f = eVar.f2899b;
            boolean z = eVar.f2900c;
            h hVar2 = h.this;
            f fVar = hVar2.f2886b;
            boolean z2 = fVar.f2903c;
            float f2 = f / (z == z2 ? this.f2904a : this.f2905b);
            float f3 = eVar.f2898a + f2;
            if ((z2 && !z && f3 <= fVar.f2902b) || (!z2 && z && f3 >= fVar.f2902b)) {
                hVar2.g(viewC, fVar.f2902b, motionEvent);
                h hVar3 = h.this;
                hVar3.i.a(hVar3, this.f2907d, 0.0f);
                h hVar4 = h.this;
                hVar4.e(hVar4.f2888d);
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
            hVar5.i.a(hVar5, this.f2907d, f3);
            return true;
        }
    }

    public h(com.byd.cleardata.ui.g.c cVar, float f2, float f3, float f4) {
        this.f2887c = cVar;
        this.f = new b(f2);
        this.e = new g(f3, f4);
        d dVar = new d();
        this.f2888d = dVar;
        this.g = dVar;
        a();
    }

    protected final void a() {
        d().setOnTouchListener(this);
        d().setOverScrollMode(2);
    }

    protected abstract a b();

    protected abstract e c();

    public View d() {
        return this.f2887c.c();
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
