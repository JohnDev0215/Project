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
    private WeakReference<View> f1774a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f1775b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f1776c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1777d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ y f1778a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1779b;

        a(y yVar, View view) {
            this.f1778a = yVar;
            this.f1779b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1778a.c(this.f1779b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1778a.a(this.f1779b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f1778a.b(this.f1779b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f1781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1782b;

        b(a0 a0Var, View view) {
            this.f1781a = a0Var;
            this.f1782b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f1781a.a(this.f1782b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        x f1784a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1785b;

        c(x xVar) {
            this.f1784a = xVar;
        }

        @Override // b.g.k.y
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i = this.f1784a.f1777d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f1784a.f1777d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f1785b) {
                x xVar = this.f1784a;
                Runnable runnable = xVar.f1776c;
                if (runnable != null) {
                    xVar.f1776c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f1785b = true;
            }
        }

        @Override // b.g.k.y
        public void b(View view) {
            this.f1785b = false;
            if (this.f1784a.f1777d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f1784a;
            Runnable runnable = xVar.f1775b;
            if (runnable != null) {
                xVar.f1775b = null;
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
        this.f1774a = new WeakReference<>(view);
    }

    private void g(View view, y yVar) {
        if (yVar != null) {
            view.animate().setListener(new a(yVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x a(float f) {
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f1774a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j) {
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f1774a.get();
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
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f1774a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f) {
        View view = this.f1774a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
