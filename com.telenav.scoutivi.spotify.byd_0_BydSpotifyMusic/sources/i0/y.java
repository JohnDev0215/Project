package i0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<View> f6900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f6901b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f6902c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f6903d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ z f6904a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f6905b;

        a(z zVar, View view) {
            this.f6904a = zVar;
            this.f6905b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f6904a.c(this.f6905b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f6904a.a(this.f6905b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f6904a.b(this.f6905b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b0 f6907a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f6908b;

        b(b0 b0Var, View view) {
            this.f6907a = b0Var;
            this.f6908b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f6907a.a(this.f6908b);
        }
    }

    y(View view) {
        this.f6900a = new WeakReference<>(view);
    }

    private void g(View view, z zVar) {
        if (zVar != null) {
            view.animate().setListener(new a(zVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public y a(float f7) {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().alpha(f7);
        }
        return this;
    }

    public void b() {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f6900a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public y d(long j7) {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().setDuration(j7);
        }
        return this;
    }

    public y e(Interpolator interpolator) {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public y f(z zVar) {
        View view = this.f6900a.get();
        if (view != null) {
            g(view, zVar);
        }
        return this;
    }

    public y h(long j7) {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().setStartDelay(j7);
        }
        return this;
    }

    public y i(b0 b0Var) {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().setUpdateListener(b0Var != null ? new b(b0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public y k(float f7) {
        View view = this.f6900a.get();
        if (view != null) {
            view.animate().translationY(f7);
        }
        return this;
    }
}
