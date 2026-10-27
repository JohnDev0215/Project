package l0;

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
    public WeakReference<View> f3673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f3674b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f3675c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3676d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ y f3677a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f3678b;

        public a(y yVar, View view) {
            this.f3677a = yVar;
            this.f3678b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3677a.c(this.f3678b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3677a.a(this.f3678b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f3677a.b(this.f3678b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a0 f3680a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f3681b;

        public b(a0 a0Var, View view) {
            this.f3680a = a0Var;
            this.f3681b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f3680a.a(this.f3681b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public x f3683a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3684b;

        public c(x xVar) {
            this.f3683a = xVar;
        }

        @Override // l0.y
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i3 = this.f3683a.f3676d;
            if (i3 > -1) {
                view.setLayerType(i3, null);
                this.f3683a.f3676d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f3684b) {
                x xVar = this.f3683a;
                Runnable runnable = xVar.f3675c;
                if (runnable != null) {
                    xVar.f3675c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f3684b = true;
            }
        }

        @Override // l0.y
        public void b(View view) {
            this.f3684b = false;
            if (this.f3683a.f3676d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f3683a;
            Runnable runnable = xVar.f3674b;
            if (runnable != null) {
                xVar.f3674b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.b(view);
            }
        }

        @Override // l0.y
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    public x(View view) {
        this.f3673a = new WeakReference<>(view);
    }

    public x a(float f3) {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().alpha(f3);
        }
        return this;
    }

    public void b() {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f3673a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j3) {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().setDuration(j3);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f3673a.get();
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

    public x h(long j3) {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().setStartDelay(j3);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f3673a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f3) {
        View view = this.f3673a.get();
        if (view != null) {
            view.animate().translationY(f3);
        }
        return this;
    }
}
