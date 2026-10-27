package e7;

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
/* JADX INFO: loaded from: classes2.dex */
public abstract class g implements e7.b, View.OnTouchListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f7.a f4773e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f4774f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0034g f4775g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f4776h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f4777i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f4780l;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f4772d = new f();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public e7.c f4778j = new e7.e();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public e7.d f4779k = new e7.f();

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Property<View, Float> f4781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4782b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4783c;

        public abstract void a(View view);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class b implements c, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Interpolator f4784d = new DecelerateInterpolator();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f4785e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final float f4786f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final a f4787g;

        public b(float f8) {
            this.f4785e = f8;
            this.f4786f = f8 * 2.0f;
            this.f4787g = g.this.b();
        }

        @Override // e7.g.c
        public boolean a(MotionEvent motionEvent) {
            return true;
        }

        @Override // e7.g.c
        public void b(c cVar) {
            g gVar = g.this;
            gVar.f4778j.a(gVar, cVar.c(), c());
            Animator animatorE = e();
            animatorE.addListener(this);
            animatorE.start();
        }

        @Override // e7.g.c
        public int c() {
            return 3;
        }

        @Override // e7.g.c
        public boolean d(MotionEvent motionEvent) {
            return true;
        }

        public Animator e() {
            View viewC = g.this.f4773e.c();
            this.f4787g.a(viewC);
            g gVar = g.this;
            float f8 = gVar.f4780l;
            if (f8 == 0.0f || ((f8 < 0.0f && gVar.f4772d.f4796c) || (f8 > 0.0f && !gVar.f4772d.f4796c))) {
                return f(this.f4787g.f4782b);
            }
            float f9 = (-f8) / this.f4785e;
            float f10 = f9 >= 0.0f ? f9 : 0.0f;
            float f11 = this.f4787g.f4782b + (((-f8) * f8) / this.f4786f);
            ObjectAnimator objectAnimatorG = g(viewC, (int) f10, f11);
            ObjectAnimator objectAnimatorF = f(f11);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorG, objectAnimatorF);
            return animatorSet;
        }

        public ObjectAnimator f(float f8) {
            View viewC = g.this.f4773e.c();
            float fAbs = Math.abs(f8);
            a aVar = this.f4787g;
            float f9 = (fAbs / aVar.f4783c) * 800.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewC, aVar.f4781a, g.this.f4772d.f4795b);
            objectAnimatorOfFloat.setDuration(Math.max((int) f9, 200));
            objectAnimatorOfFloat.setInterpolator(this.f4784d);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        public ObjectAnimator g(View view, int i8, float f8) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.f4787g.f4781a, f8);
            objectAnimatorOfFloat.setDuration(i8);
            objectAnimatorOfFloat.setInterpolator(this.f4784d);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            g gVar = g.this;
            gVar.e(gVar.f4774f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            g gVar = g.this;
            gVar.f4779k.a(gVar, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final e f4789d;

        public d() {
            this.f4789d = g.this.c();
        }

        @Override // e7.g.c
        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        @Override // e7.g.c
        public void b(c cVar) {
            g gVar = g.this;
            gVar.f4778j.a(gVar, cVar.c(), c());
        }

        @Override // e7.g.c
        public int c() {
            return 0;
        }

        @Override // e7.g.c
        public boolean d(MotionEvent motionEvent) {
            if (!this.f4789d.a(g.this.f4773e.c(), motionEvent)) {
                return false;
            }
            if (!(g.this.f4773e.b() && this.f4789d.f4793c) && (!g.this.f4773e.a() || this.f4789d.f4793c)) {
                return false;
            }
            g.this.f4772d.f4794a = motionEvent.getPointerId(0);
            g gVar = g.this;
            f fVar = gVar.f4772d;
            e eVar = this.f4789d;
            fVar.f4795b = eVar.f4791a;
            fVar.f4796c = eVar.f4793c;
            gVar.e(gVar.f4775g);
            return g.this.f4775g.d(motionEvent);
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f4791a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4792b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4793c;

        public abstract boolean a(View view, MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4794a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4795b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4796c;
    }

    /* JADX INFO: renamed from: e7.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class C0034g implements c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f4797d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f4798e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final e f4799f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4800g;

        public C0034g(float f8, float f9) {
            this.f4799f = g.this.c();
            this.f4797d = f8;
            this.f4798e = f9;
        }

        @Override // e7.g.c
        public boolean a(MotionEvent motionEvent) {
            g gVar = g.this;
            gVar.e(gVar.f4776h);
            return false;
        }

        @Override // e7.g.c
        public void b(c cVar) {
            g gVar = g.this;
            this.f4800g = gVar.f4772d.f4796c ? 1 : 2;
            gVar.f4778j.a(gVar, cVar.c(), c());
        }

        @Override // e7.g.c
        public int c() {
            return this.f4800g;
        }

        @Override // e7.g.c
        public boolean d(MotionEvent motionEvent) {
            if (g.this.f4772d.f4794a != motionEvent.getPointerId(0)) {
                g gVar = g.this;
                gVar.e(gVar.f4776h);
                return true;
            }
            View viewC = g.this.f4773e.c();
            if (!this.f4799f.a(viewC, motionEvent)) {
                return true;
            }
            e eVar = this.f4799f;
            float f8 = eVar.f4792b;
            boolean z7 = eVar.f4793c;
            g gVar2 = g.this;
            f fVar = gVar2.f4772d;
            boolean z8 = fVar.f4796c;
            float f9 = f8 / (z7 == z8 ? this.f4797d : this.f4798e);
            float f10 = eVar.f4791a + f9;
            if ((z8 && !z7 && f10 <= fVar.f4795b) || (!z8 && z7 && f10 >= fVar.f4795b)) {
                gVar2.g(viewC, fVar.f4795b, motionEvent);
                g gVar3 = g.this;
                gVar3.f4779k.a(gVar3, this.f4800g, 0.0f);
                g gVar4 = g.this;
                gVar4.e(gVar4.f4774f);
                return true;
            }
            if (viewC.getParent() != null) {
                viewC.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
            if (eventTime > 0) {
                g.this.f4780l = f9 / eventTime;
            }
            g.this.f(viewC, f10);
            g gVar5 = g.this;
            gVar5.f4779k.a(gVar5, this.f4800g, f10);
            return true;
        }
    }

    public g(f7.a aVar, float f8, float f9, float f10) {
        this.f4773e = aVar;
        this.f4776h = new b(f8);
        this.f4775g = new C0034g(f9, f10);
        d dVar = new d();
        this.f4774f = dVar;
        this.f4777i = dVar;
        a();
    }

    public void a() {
        d().setOnTouchListener(this);
        d().setOverScrollMode(2);
    }

    public abstract a b();

    public abstract e c();

    public View d() {
        return this.f4773e.c();
    }

    public void e(c cVar) {
        c cVar2 = this.f4777i;
        this.f4777i = cVar;
        cVar.b(cVar2);
    }

    public abstract void f(View view, float f8);

    public abstract void g(View view, float f8, MotionEvent motionEvent);

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                return this.f4777i.d(motionEvent);
            }
            if (action != 3) {
                return false;
            }
        }
        return this.f4777i.a(motionEvent);
    }
}
