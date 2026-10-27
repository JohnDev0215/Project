package defpackage;

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
public final class x9 {
    public WeakReference<View> a;
    public Runnable b = null;
    public Runnable c = null;
    public int d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ y9 a;
        public final /* synthetic */ View b;

        public a(y9 y9Var, View view) {
            this.a = y9Var;
            this.b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a.c(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.a(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.a.b(this.b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ aa a;
        public final /* synthetic */ View b;

        public b(aa aaVar, View view) {
            this.a = aaVar;
            this.b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.a(this.b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements y9 {
        public x9 a;
        public boolean b;

        public c(x9 x9Var) {
            this.a = x9Var;
        }

        @Override // defpackage.y9
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i = this.a.d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.a.d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.b) {
                x9 x9Var = this.a;
                Runnable runnable = x9Var.c;
                if (runnable != null) {
                    x9Var.c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y9 y9Var = tag instanceof y9 ? (y9) tag : null;
                if (y9Var != null) {
                    y9Var.a(view);
                }
                this.b = true;
            }
        }

        @Override // defpackage.y9
        public void b(View view) {
            this.b = false;
            if (this.a.d > -1) {
                view.setLayerType(2, null);
            }
            x9 x9Var = this.a;
            Runnable runnable = x9Var.b;
            if (runnable != null) {
                x9Var.b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y9 y9Var = tag instanceof y9 ? (y9) tag : null;
            if (y9Var != null) {
                y9Var.b(view);
            }
        }

        @Override // defpackage.y9
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y9 y9Var = tag instanceof y9 ? (y9) tag : null;
            if (y9Var != null) {
                y9Var.c(view);
            }
        }
    }

    public x9(View view) {
        this.a = new WeakReference<>(view);
    }

    public x9 a(float f) {
        View view = this.a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = this.a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x9 d(long j) {
        View view = this.a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x9 e(Interpolator interpolator) {
        View view = this.a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x9 f(y9 y9Var) {
        View view = this.a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                g(view, y9Var);
            } else {
                view.setTag(2113929216, y9Var);
                g(view, new c(this));
            }
        }
        return this;
    }

    public final void g(View view, y9 y9Var) {
        if (y9Var != null) {
            view.animate().setListener(new a(y9Var, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x9 h(long j) {
        View view = this.a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public x9 i(aa aaVar) {
        View view = this.a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(aaVar != null ? new b(aaVar, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x9 k(float f) {
        View view = this.a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
