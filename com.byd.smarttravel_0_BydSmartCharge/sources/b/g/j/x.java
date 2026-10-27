package b.g.j;

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
    public WeakReference<View> f1157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f1158b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f1159c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1160d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ y f1161a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1162b;

        public a(y yVar, View view) {
            this.f1161a = yVar;
            this.f1162b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1161a.c(this.f1162b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1161a.a(this.f1162b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f1161a.b(this.f1162b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a0 f1164a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1165b;

        public b(a0 a0Var, View view) {
            this.f1164a = a0Var;
            this.f1165b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f1164a.a(this.f1165b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public x f1167a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1168b;

        public c(x xVar) {
            this.f1167a = xVar;
        }

        @Override // b.g.j.y
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i = this.f1167a.f1160d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f1167a.f1160d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f1168b) {
                x xVar = this.f1167a;
                Runnable runnable = xVar.f1159c;
                if (runnable != null) {
                    xVar.f1159c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f1168b = true;
            }
        }

        @Override // b.g.j.y
        public void b(View view) {
            this.f1168b = false;
            if (this.f1167a.f1160d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f1167a;
            Runnable runnable = xVar.f1158b;
            if (runnable != null) {
                xVar.f1158b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.b(view);
            }
        }

        @Override // b.g.j.y
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    public x(View view) {
        this.f1157a = new WeakReference<>(view);
    }

    public x a(float f) {
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f1157a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x d(long j) {
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x e(Interpolator interpolator) {
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x f(y yVar) {
        View view = this.f1157a.get();
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
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x i(a0 a0Var) {
        View view = this.f1157a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(a0Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x k(float f) {
        View view = this.f1157a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
