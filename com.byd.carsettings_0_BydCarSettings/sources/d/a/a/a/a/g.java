package d.a.a.a.a;

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
/* JADX INFO: loaded from: classes3.dex */
public abstract class g implements d.a.a.a.a.b, View.OnTouchListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d.a.a.a.a.j.a f3898b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f3899c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0104g f3900d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f3901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f3902f;
    public float i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f3897a = new f();
    public d.a.a.a.a.c g = new d.a.a.a.a.e();
    public d.a.a.a.a.d h = new d.a.a.a.a.f();

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Property<View, Float> f3903a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3904b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3905c;

        public abstract void a(View view);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class b implements c, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Interpolator f3906a = new DecelerateInterpolator();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f3907b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f3908c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f3909d;

        public b(float f2) {
            this.f3907b = f2;
            this.f3908c = f2 * 2.0f;
            this.f3909d = g.this.b();
        }

        @Override // d.a.a.a.a.g.c
        public boolean a(MotionEvent motionEvent) {
            return true;
        }

        @Override // d.a.a.a.a.g.c
        public void b(c cVar) {
            g gVar = g.this;
            gVar.g.a(gVar, cVar.c(), c());
            Animator animatorE = e();
            animatorE.addListener(this);
            animatorE.start();
        }

        @Override // d.a.a.a.a.g.c
        public int c() {
            return 3;
        }

        @Override // d.a.a.a.a.g.c
        public boolean d(MotionEvent motionEvent) {
            return true;
        }

        public Animator e() {
            View viewC = g.this.f3898b.c();
            this.f3909d.a(viewC);
            g gVar = g.this;
            float f2 = gVar.i;
            if (f2 == 0.0f || ((f2 < 0.0f && gVar.f3897a.f3918c) || (f2 > 0.0f && !gVar.f3897a.f3918c))) {
                return f(this.f3909d.f3904b);
            }
            float f3 = (-f2) / this.f3907b;
            float f4 = f3 >= 0.0f ? f3 : 0.0f;
            float f5 = this.f3909d.f3904b + (((-f2) * f2) / this.f3908c);
            ObjectAnimator objectAnimatorG = g(viewC, (int) f4, f5);
            ObjectAnimator objectAnimatorF = f(f5);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorG, objectAnimatorF);
            return animatorSet;
        }

        public ObjectAnimator f(float f2) {
            View viewC = g.this.f3898b.c();
            float fAbs = Math.abs(f2);
            a aVar = this.f3909d;
            float f3 = (fAbs / aVar.f3905c) * 800.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewC, aVar.f3903a, g.this.f3897a.f3917b);
            objectAnimatorOfFloat.setDuration(Math.max((int) f3, 200));
            objectAnimatorOfFloat.setInterpolator(this.f3906a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        public ObjectAnimator g(View view, int i, float f2) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.f3909d.f3903a, f2);
            objectAnimatorOfFloat.setDuration(i);
            objectAnimatorOfFloat.setInterpolator(this.f3906a);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            g gVar = g.this;
            gVar.e(gVar.f3899c);
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
            gVar.h.a(gVar, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
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
        public final e f3911a;

        public d() {
            this.f3911a = g.this.c();
        }

        @Override // d.a.a.a.a.g.c
        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        @Override // d.a.a.a.a.g.c
        public void b(c cVar) {
            g gVar = g.this;
            gVar.g.a(gVar, cVar.c(), c());
        }

        @Override // d.a.a.a.a.g.c
        public int c() {
            return 0;
        }

        @Override // d.a.a.a.a.g.c
        public boolean d(MotionEvent motionEvent) {
            if (!this.f3911a.a(g.this.f3898b.c(), motionEvent)) {
                return false;
            }
            if (!(g.this.f3898b.b() && this.f3911a.f3915c) && (!g.this.f3898b.a() || this.f3911a.f3915c)) {
                return false;
            }
            g.this.f3897a.f3916a = motionEvent.getPointerId(0);
            g gVar = g.this;
            f fVar = gVar.f3897a;
            e eVar = this.f3911a;
            fVar.f3917b = eVar.f3913a;
            fVar.f3918c = eVar.f3915c;
            gVar.e(gVar.f3900d);
            return g.this.f3900d.d(motionEvent);
        }
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f3913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3914b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3915c;

        public abstract boolean a(View view, MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3916a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3917b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3918c;
    }

    /* JADX INFO: renamed from: d.a.a.a.a.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OverScrollBounceEffectDecoratorBase.java */
    public class C0104g implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f3919a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f3920b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final e f3921c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3922d;

        public C0104g(float f2, float f3) {
            this.f3921c = g.this.c();
            this.f3919a = f2;
            this.f3920b = f3;
        }

        @Override // d.a.a.a.a.g.c
        public boolean a(MotionEvent motionEvent) {
            g gVar = g.this;
            gVar.e(gVar.f3901e);
            return false;
        }

        @Override // d.a.a.a.a.g.c
        public void b(c cVar) {
            g gVar = g.this;
            this.f3922d = gVar.f3897a.f3918c ? 1 : 2;
            gVar.g.a(gVar, cVar.c(), c());
        }

        @Override // d.a.a.a.a.g.c
        public int c() {
            return this.f3922d;
        }

        @Override // d.a.a.a.a.g.c
        public boolean d(MotionEvent motionEvent) {
            if (g.this.f3897a.f3916a != motionEvent.getPointerId(0)) {
                g gVar = g.this;
                gVar.e(gVar.f3901e);
                return true;
            }
            View viewC = g.this.f3898b.c();
            if (!this.f3921c.a(viewC, motionEvent)) {
                return true;
            }
            e eVar = this.f3921c;
            float f2 = eVar.f3914b;
            boolean z = eVar.f3915c;
            g gVar2 = g.this;
            f fVar = gVar2.f3897a;
            boolean z2 = fVar.f3918c;
            float f3 = f2 / (z == z2 ? this.f3919a : this.f3920b);
            float f4 = eVar.f3913a + f3;
            if ((z2 && !z && f4 <= fVar.f3917b) || (!z2 && z && f4 >= fVar.f3917b)) {
                gVar2.g(viewC, fVar.f3917b, motionEvent);
                g gVar3 = g.this;
                gVar3.h.a(gVar3, this.f3922d, 0.0f);
                g gVar4 = g.this;
                gVar4.e(gVar4.f3899c);
                return true;
            }
            if (viewC.getParent() != null) {
                viewC.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
            if (eventTime > 0) {
                g.this.i = f3 / eventTime;
            }
            g.this.f(viewC, f4);
            g gVar5 = g.this;
            gVar5.h.a(gVar5, this.f3922d, f4);
            return true;
        }
    }

    public g(d.a.a.a.a.j.a aVar, float f2, float f3, float f4) {
        this.f3898b = aVar;
        this.f3901e = new b(f2);
        this.f3900d = new C0104g(f3, f4);
        d dVar = new d();
        this.f3899c = dVar;
        this.f3902f = dVar;
        a();
    }

    public void a() {
        d().setOnTouchListener(this);
        d().setOverScrollMode(2);
    }

    public abstract a b();

    public abstract e c();

    public View d() {
        return this.f3898b.c();
    }

    public void e(c cVar) {
        c cVar2 = this.f3902f;
        this.f3902f = cVar;
        cVar.b(cVar2);
    }

    public abstract void f(View view, float f2);

    public abstract void g(View view, float f2, MotionEvent motionEvent);

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                return this.f3902f.d(motionEvent);
            }
            if (action != 3) {
                return false;
            }
        }
        return this.f3902f.a(motionEvent);
    }
}
