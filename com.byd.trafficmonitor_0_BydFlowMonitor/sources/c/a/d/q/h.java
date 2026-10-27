package c.a.d.q;

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
public abstract class h implements c.a.d.q.b, View.OnTouchListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.a.d.q.c f3638c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f3639d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f3640e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f3641f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f3642g;
    public float j;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f3637b = new f();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.d.q.d f3643h = new c.a.d.q.f();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c.a.d.q.e f3644i = new c.a.d.q.g();

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Property<View, Float> f3645a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3646b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3647c;

        public abstract void a(View view);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class b implements c, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Interpolator f3648a = new DecelerateInterpolator();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f3649b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f3650c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f3651d;

        public b(float f2) {
            this.f3649b = f2;
            this.f3650c = f2 * 2.0f;
            this.f3651d = h.this.b();
        }

        @Override // c.a.d.q.h.c
        public boolean a(MotionEvent motionEvent) {
            return true;
        }

        @Override // c.a.d.q.h.c
        public void b(c cVar) {
            h hVar = h.this;
            hVar.f3643h.a(hVar, cVar.c(), c());
            Animator animatorE = e();
            animatorE.addListener(this);
            animatorE.start();
        }

        @Override // c.a.d.q.h.c
        public int c() {
            return 3;
        }

        @Override // c.a.d.q.h.c
        public boolean d(MotionEvent motionEvent) {
            return true;
        }

        public Animator e() {
            View viewC = h.this.f3638c.c();
            this.f3651d.a(viewC);
            h hVar = h.this;
            float f2 = hVar.j;
            if (f2 == 0.0f || ((f2 < 0.0f && hVar.f3637b.f3660c) || (f2 > 0.0f && !hVar.f3637b.f3660c))) {
                return f(this.f3651d.f3646b);
            }
            float f3 = (-f2) / this.f3649b;
            float f4 = f3 >= 0.0f ? f3 : 0.0f;
            float f5 = this.f3651d.f3646b + (((-f2) * f2) / this.f3650c);
            ObjectAnimator objectAnimatorG = g(viewC, (int) f4, f5);
            ObjectAnimator objectAnimatorF = f(f5);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorG, objectAnimatorF);
            return animatorSet;
        }

        public ObjectAnimator f(float f2) {
            View viewC = h.this.f3638c.c();
            float fAbs = Math.abs(f2);
            a aVar = this.f3651d;
            float f3 = (fAbs / aVar.f3647c) * 800.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewC, aVar.f3645a, h.this.f3637b.f3659b);
            objectAnimatorOfFloat.setDuration(Math.max((int) f3, 200));
            objectAnimatorOfFloat.setInterpolator(this.f3648a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        public ObjectAnimator g(View view, int i2, float f2) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.f3651d.f3645a, f2);
            objectAnimatorOfFloat.setDuration(i2);
            objectAnimatorOfFloat.setInterpolator(this.f3648a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            hVar.e(hVar.f3639d);
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
            hVar.f3644i.a(hVar, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public interface c {
        boolean a(MotionEvent motionEvent);

        void b(c cVar);

        int c();

        boolean d(MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class d implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final e f3653a;

        public d() {
            this.f3653a = h.this.c();
        }

        @Override // c.a.d.q.h.c
        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        @Override // c.a.d.q.h.c
        public void b(c cVar) {
            h hVar = h.this;
            hVar.f3643h.a(hVar, cVar.c(), c());
        }

        @Override // c.a.d.q.h.c
        public int c() {
            return 0;
        }

        @Override // c.a.d.q.h.c
        public boolean d(MotionEvent motionEvent) {
            if (!this.f3653a.a(h.this.f3638c.c(), motionEvent)) {
                return false;
            }
            if (!(h.this.f3638c.b() && this.f3653a.f3657c) && (!h.this.f3638c.a() || this.f3653a.f3657c)) {
                return false;
            }
            h.this.f3637b.f3658a = motionEvent.getPointerId(0);
            h hVar = h.this;
            f fVar = hVar.f3637b;
            e eVar = this.f3653a;
            fVar.f3659b = eVar.f3655a;
            fVar.f3660c = eVar.f3657c;
            hVar.e(hVar.f3640e);
            return h.this.f3640e.d(motionEvent);
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f3655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3656b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3657c;

        public abstract boolean a(View view, MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3658a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3659b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3660c;
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class g implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f3661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f3662b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final e f3663c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3664d;

        public g(float f2, float f3) {
            this.f3663c = h.this.c();
            this.f3661a = f2;
            this.f3662b = f3;
        }

        @Override // c.a.d.q.h.c
        public boolean a(MotionEvent motionEvent) {
            h hVar = h.this;
            hVar.e(hVar.f3641f);
            return false;
        }

        @Override // c.a.d.q.h.c
        public void b(c cVar) {
            h hVar = h.this;
            this.f3664d = hVar.f3637b.f3660c ? 1 : 2;
            hVar.f3643h.a(hVar, cVar.c(), c());
        }

        @Override // c.a.d.q.h.c
        public int c() {
            return this.f3664d;
        }

        @Override // c.a.d.q.h.c
        public boolean d(MotionEvent motionEvent) {
            if (h.this.f3637b.f3658a != motionEvent.getPointerId(0)) {
                h hVar = h.this;
                hVar.e(hVar.f3641f);
                return true;
            }
            View viewC = h.this.f3638c.c();
            if (!this.f3663c.a(viewC, motionEvent)) {
                return true;
            }
            e eVar = this.f3663c;
            float f2 = eVar.f3656b;
            boolean z = eVar.f3657c;
            h hVar2 = h.this;
            f fVar = hVar2.f3637b;
            boolean z2 = fVar.f3660c;
            float f3 = f2 / (z == z2 ? this.f3661a : this.f3662b);
            float f4 = eVar.f3655a + f3;
            if ((z2 && !z && f4 <= fVar.f3659b) || (!z2 && z && f4 >= fVar.f3659b)) {
                hVar2.g(viewC, fVar.f3659b, motionEvent);
                h hVar3 = h.this;
                hVar3.f3644i.a(hVar3, this.f3664d, 0.0f);
                h hVar4 = h.this;
                hVar4.e(hVar4.f3639d);
                return true;
            }
            if (viewC.getParent() != null) {
                viewC.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
            if (eventTime > 0) {
                h.this.j = f3 / eventTime;
            }
            h.this.f(viewC, f4);
            h hVar5 = h.this;
            hVar5.f3644i.a(hVar5, this.f3664d, f4);
            return true;
        }
    }

    public h(c.a.d.q.c cVar, float f2, float f3, float f4) {
        this.f3638c = cVar;
        this.f3641f = new b(f2);
        this.f3640e = new g(f3, f4);
        d dVar = new d();
        this.f3639d = dVar;
        this.f3642g = dVar;
        a();
    }

    public final void a() {
        d().setOnTouchListener(this);
        d().setOverScrollMode(2);
    }

    public abstract a b();

    public abstract e c();

    public View d() {
        return this.f3638c.c();
    }

    public void e(c cVar) {
        c cVar2 = this.f3642g;
        this.f3642g = cVar;
        cVar.b(cVar2);
    }

    public abstract void f(View view, float f2);

    public abstract void g(View view, float f2, MotionEvent motionEvent);

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                return this.f3642g.d(motionEvent);
            }
            if (action != 3) {
                return false;
            }
        }
        return this.f3642g.a(motionEvent);
    }
}
