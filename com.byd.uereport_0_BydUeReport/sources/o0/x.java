package o0;

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
    public WeakReference<View> f6721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f6722b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f6723c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6724d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ y f6725d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f6726e;

        public a(y yVar, View view) {
            this.f6725d = yVar;
            this.f6726e = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f6725d.a(this.f6726e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f6725d.b(this.f6726e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f6725d.c(this.f6726e);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ a0 f6728d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f6729e;

        public b(a0 a0Var, View view) {
            this.f6728d = a0Var;
            this.f6729e = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f6728d.a(this.f6729e);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public x f6731a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f6732b;

        public c(x xVar) {
            this.f6731a = xVar;
        }

        @Override // o0.y
        public void a(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.a(view);
            }
        }

        @Override // o0.y
        @SuppressLint({"WrongConstant"})
        public void b(View view) {
            int i8 = this.f6731a.f6724d;
            if (i8 > -1) {
                view.setLayerType(i8, null);
                this.f6731a.f6724d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f6732b) {
                x xVar = this.f6731a;
                Runnable runnable = xVar.f6723c;
                if (runnable != null) {
                    xVar.f6723c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.b(view);
                }
                this.f6732b = true;
            }
        }

        @Override // o0.y
        public void c(View view) {
            this.f6732b = false;
            if (this.f6731a.f6724d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f6731a;
            Runnable runnable = xVar.f6722b;
            if (runnable != null) {
                xVar.f6722b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    public x(View view) {
        this.f6721a = new WeakReference<>(view);
    }

    public x a(float f8) {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().alpha(f8);
        }
        return this;
    }

    public void b() {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f6721a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j8) {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().setDuration(j8);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f6721a.get();
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

    public x h(long j8) {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().setStartDelay(j8);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f6721a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f8) {
        View view = this.f6721a.get();
        if (view != null) {
            view.animate().translationY(f8);
        }
        return this;
    }
}
