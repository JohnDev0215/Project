package b.a.a.i.o;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import b.a.a.i.o.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f extends d.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ValueAnimator f945a = new ValueAnimator();

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d.g.b f946a;

        public a(d.g.b bVar) {
            this.f946a = bVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f946a.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d.g.a f948a;

        public b(d.g.a aVar) {
            this.f948a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f948a.onAnimationCancel();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f948a.onAnimationEnd();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f948a.a();
        }
    }

    @Override // b.a.a.i.o.d.g
    public void a() {
        this.f945a.cancel();
    }

    @Override // b.a.a.i.o.d.g
    public float b() {
        return this.f945a.getAnimatedFraction();
    }

    @Override // b.a.a.i.o.d.g
    public int c() {
        return ((Integer) this.f945a.getAnimatedValue()).intValue();
    }

    @Override // b.a.a.i.o.d.g
    public long d() {
        return this.f945a.getDuration();
    }

    @Override // b.a.a.i.o.d.g
    public boolean e() {
        return this.f945a.isRunning();
    }

    @Override // b.a.a.i.o.d.g
    public void f(int i) {
        this.f945a.setDuration(i);
    }

    @Override // b.a.a.i.o.d.g
    public void g(float f, float f2) {
        this.f945a.setFloatValues(f, f2);
    }

    @Override // b.a.a.i.o.d.g
    public void h(int i, int i2) {
        this.f945a.setIntValues(i, i2);
    }

    @Override // b.a.a.i.o.d.g
    public void i(Interpolator interpolator) {
        this.f945a.setInterpolator(interpolator);
    }

    @Override // b.a.a.i.o.d.g
    public void j(d.g.a aVar) {
        this.f945a.addListener(new b(aVar));
    }

    @Override // b.a.a.i.o.d.g
    public void k(d.g.b bVar) {
        this.f945a.addUpdateListener(new a(bVar));
    }

    @Override // b.a.a.i.o.d.g
    public void l() {
        this.f945a.start();
    }
}
