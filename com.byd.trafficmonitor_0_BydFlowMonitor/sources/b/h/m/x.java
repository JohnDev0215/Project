package b.h.m;

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
    public WeakReference<View> f2663a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f2664b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f2665c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2666d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ y f2667a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2668b;

        public a(y yVar, View view) {
            this.f2667a = yVar;
            this.f2668b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2667a.c(this.f2668b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2667a.a(this.f2668b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2667a.b(this.f2668b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a0 f2670a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2671b;

        public b(a0 a0Var, View view) {
            this.f2670a = a0Var;
            this.f2671b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f2670a.a(this.f2671b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public x f2673a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2674b;

        public c(x xVar) {
            this.f2673a = xVar;
        }

        @Override // b.h.m.y
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i2 = this.f2673a.f2666d;
            if (i2 > -1) {
                view.setLayerType(i2, null);
                this.f2673a.f2666d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f2674b) {
                x xVar = this.f2673a;
                Runnable runnable = xVar.f2665c;
                if (runnable != null) {
                    xVar.f2665c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f2674b = true;
            }
        }

        @Override // b.h.m.y
        public void b(View view) {
            this.f2674b = false;
            if (this.f2673a.f2666d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f2673a;
            Runnable runnable = xVar.f2664b;
            if (runnable != null) {
                xVar.f2664b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.b(view);
            }
        }

        @Override // b.h.m.y
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    public x(View view) {
        this.f2663a = new WeakReference<>(view);
    }

    public x a(float f2) {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public void b() {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f2663a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j) {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f2663a.get();
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

    public final void g(View view, y yVar) {
        if (yVar != null) {
            view.animate().setListener(new a(yVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x h(long j) {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f2663a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f2) {
        View view = this.f2663a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }
}
