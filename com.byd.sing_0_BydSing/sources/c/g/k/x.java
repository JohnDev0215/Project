package c.g.k;

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
    private WeakReference<View> f2341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f2342b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f2343c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2344d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ y f2345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2346b;

        a(y yVar, View view) {
            this.f2345a = yVar;
            this.f2346b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2345a.a(this.f2346b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2345a.b(this.f2346b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2345a.c(this.f2346b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f2348a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2349b;

        b(a0 a0Var, View view) {
            this.f2348a = a0Var;
            this.f2349b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f2348a.a(this.f2349b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        x f2351a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2352b;

        c(x xVar) {
            this.f2351a = xVar;
        }

        @Override // c.g.k.y
        public void a(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.a(view);
            }
        }

        @Override // c.g.k.y
        @SuppressLint({"WrongConstant"})
        public void b(View view) {
            int i = this.f2351a.f2344d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f2351a.f2344d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f2352b) {
                x xVar = this.f2351a;
                Runnable runnable = xVar.f2343c;
                if (runnable != null) {
                    xVar.f2343c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.b(view);
                }
                this.f2352b = true;
            }
        }

        @Override // c.g.k.y
        public void c(View view) {
            this.f2352b = false;
            if (this.f2351a.f2344d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f2351a;
            Runnable runnable = xVar.f2342b;
            if (runnable != null) {
                xVar.f2342b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    x(View view) {
        this.f2341a = new WeakReference<>(view);
    }

    private void g(View view, y yVar) {
        if (yVar != null) {
            view.animate().setListener(new a(yVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x a(float f) {
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f2341a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j) {
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f2341a.get();
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
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f2341a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f) {
        View view = this.f2341a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
