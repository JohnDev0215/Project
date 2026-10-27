package b.g.k;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<View> f2747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f2748b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f2749c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2750d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ y f2751a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2752b;

        a(y yVar, View view) {
            this.f2751a = yVar;
            this.f2752b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2751a.c(this.f2752b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2751a.a(this.f2752b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2751a.b(this.f2752b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f2754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2755b;

        b(a0 a0Var, View view) {
            this.f2754a = a0Var;
            this.f2755b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f2754a.a(this.f2755b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        x f2757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2758b;

        c(x xVar) {
            this.f2757a = xVar;
        }

        @Override // b.g.k.y
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i2 = this.f2757a.f2750d;
            if (i2 > -1) {
                view.setLayerType(i2, null);
                this.f2757a.f2750d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f2758b) {
                x xVar = this.f2757a;
                Runnable runnable = xVar.f2749c;
                if (runnable != null) {
                    xVar.f2749c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f2758b = true;
            }
        }

        @Override // b.g.k.y
        public void b(View view) {
            this.f2758b = false;
            if (this.f2757a.f2750d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f2757a;
            Runnable runnable = xVar.f2748b;
            if (runnable != null) {
                xVar.f2748b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.b(view);
            }
        }

        @Override // b.g.k.y
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    x(View view) {
        this.f2747a = new WeakReference<>(view);
    }

    private void g(View view, y yVar) {
        if (yVar != null) {
            view.animate().setListener(new a(yVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x a(float f2) {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public void b() {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f2747a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j) {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f2747a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                g(view, yVar);
            } else {
                view.setTag(2113929216, yVar);
                g(view, new c(this));
            }
        }
        return this;
    }

    public x h(long j) {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f2747a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f2) {
        View view = this.f2747a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }
}
