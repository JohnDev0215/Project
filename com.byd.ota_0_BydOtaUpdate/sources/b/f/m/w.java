package b.f.m;

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
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<View> f1809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f1810b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f1811c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1812d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ x f1813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1814b;

        public a(x xVar, View view) {
            this.f1813a = xVar;
            this.f1814b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1813a.c(this.f1814b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1813a.a(this.f1814b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f1813a.b(this.f1814b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ z f1816a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1817b;

        public b(z zVar, View view) {
            this.f1816a = zVar;
            this.f1817b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f1816a.a(this.f1817b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w f1819a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1820b;

        public c(w wVar) {
            this.f1819a = wVar;
        }

        @Override // b.f.m.x
        @SuppressLint({"WrongConstant"})
        public void a(View view) {
            int i2 = this.f1819a.f1812d;
            if (i2 > -1) {
                view.setLayerType(i2, null);
                this.f1819a.f1812d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f1820b) {
                w wVar = this.f1819a;
                Runnable runnable = wVar.f1811c;
                if (runnable != null) {
                    wVar.f1811c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                x xVar = tag instanceof x ? (x) tag : null;
                if (xVar != null) {
                    xVar.a(view);
                }
                this.f1820b = true;
            }
        }

        @Override // b.f.m.x
        public void b(View view) {
            this.f1820b = false;
            if (this.f1819a.f1812d > -1) {
                view.setLayerType(2, null);
            }
            w wVar = this.f1819a;
            Runnable runnable = wVar.f1810b;
            if (runnable != null) {
                wVar.f1810b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            x xVar = tag instanceof x ? (x) tag : null;
            if (xVar != null) {
                xVar.b(view);
            }
        }

        @Override // b.f.m.x
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            x xVar = tag instanceof x ? (x) tag : null;
            if (xVar != null) {
                xVar.c(view);
            }
        }
    }

    public w(View view) {
        this.f1809a = new WeakReference<>(view);
    }

    public w a(float f2) {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public void b() {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = this.f1809a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public w d(long j) {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public w e(Interpolator interpolator) {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public w f(x xVar) {
        View view = this.f1809a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                g(view, xVar);
            } else {
                view.setTag(2113929216, xVar);
                g(view, new c(this));
            }
        }
        return this;
    }

    public final void g(View view, x xVar) {
        if (xVar != null) {
            view.animate().setListener(new a(xVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public w h(long j) {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public w i(z zVar) {
        View view = this.f1809a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(zVar != null ? new b(zVar, view) : null);
        }
        return this;
    }

    public void j() {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public w k(float f2) {
        View view = this.f1809a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }
}
