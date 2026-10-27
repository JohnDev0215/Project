package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<View> f573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f574b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f575c = null;
    int d = -1;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w f576a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f577b;

        a(v vVar, w wVar, View view) {
            this.f576a = wVar;
            this.f577b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f576a.c(this.f577b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f576a.a(this.f577b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f576a.b(this.f577b);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ y f578a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f579b;

        b(v vVar, y yVar, View view) {
            this.f578a = yVar;
            this.f579b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f578a.a(this.f579b);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c implements w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        v f580a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f581b;

        c(v vVar) {
            this.f580a = vVar;
        }

        @Override // androidx.core.view.w
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i = this.f580a.d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f580a.d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f581b) {
                v vVar = this.f580a;
                Runnable runnable = vVar.f575c;
                if (runnable != null) {
                    vVar.f575c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                w wVar = tag instanceof w ? (w) tag : null;
                if (wVar != null) {
                    wVar.a(view);
                }
                this.f581b = true;
            }
        }

        @Override // androidx.core.view.w
        public void b(View view) {
            this.f581b = false;
            if (this.f580a.d > -1) {
                view.setLayerType(2, null);
            }
            v vVar = this.f580a;
            Runnable runnable = vVar.f574b;
            if (runnable != null) {
                vVar.f574b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            w wVar = tag instanceof w ? (w) tag : null;
            if (wVar != null) {
                wVar.b(view);
            }
        }

        @Override // androidx.core.view.w
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            w wVar = tag instanceof w ? (w) tag : null;
            if (wVar != null) {
                wVar.c(view);
            }
        }
    }

    v(View view) {
        this.f573a = new WeakReference<>(view);
    }

    private void g(View view, w wVar) {
        if (wVar != null) {
            view.animate().setListener(new a(this, wVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public v a(float f) {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f573a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public v d(long j) {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public v e(Interpolator interpolator) {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public v f(w wVar) {
        View view = this.f573a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                g(view, wVar);
            } else {
                view.setTag(2113929216, wVar);
                g(view, new c(this));
            }
        }
        return this;
    }

    public v h(long j) {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public v i(y yVar) {
        View view = this.f573a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(yVar != null ? new b(this, yVar, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public v k(float f) {
        View view = this.f573a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
