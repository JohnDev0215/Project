package a.g.k;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<View> f437a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f438b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f439c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f440d = -1;

    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ y f441a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f442b;

        a(y yVar, View view) {
            this.f441a = yVar;
            this.f442b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f441a.c(this.f442b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f441a.a(this.f442b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f441a.b(this.f442b);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f444a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f445b;

        b(a0 a0Var, View view) {
            this.f444a = a0Var;
            this.f445b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f444a.a(this.f445b);
        }
    }

    static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        x f447a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f448b;

        c(x xVar) {
            this.f447a = xVar;
        }

        @Override // a.g.k.y
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i = this.f447a.f440d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f447a.f440d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f448b) {
                x xVar = this.f447a;
                Runnable runnable = xVar.f439c;
                if (runnable != null) {
                    xVar.f439c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f448b = true;
            }
        }

        @Override // a.g.k.y
        public void b(View view) {
            this.f448b = false;
            if (this.f447a.f440d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f447a;
            Runnable runnable = xVar.f438b;
            if (runnable != null) {
                xVar.f438b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.b(view);
            }
        }

        @Override // a.g.k.y
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    x(View view) {
        this.f437a = new WeakReference<>(view);
    }

    private void g(View view, y yVar) {
        if (yVar != null) {
            view.animate().setListener(new a(yVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x a(float f) {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f437a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j) {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f437a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT < 16) {
                view.setTag(2113929216, yVar);
                yVar = new c(this);
            }
            g(view, yVar);
        }
        return this;
    }

    public x h(long j) {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f437a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f) {
        View view = this.f437a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
