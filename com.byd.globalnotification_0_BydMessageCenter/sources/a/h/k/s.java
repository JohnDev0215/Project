package a.h.k;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<View> f835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f836b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f837c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f838d = -1;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t f839a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f840b;

        public a(s sVar, t tVar, View view) {
            this.f839a = tVar;
            this.f840b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f839a.c(this.f840b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f839a.a(this.f840b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f839a.b(this.f840b);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ v f841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f842b;

        public b(s sVar, v vVar, View view) {
            this.f841a = vVar;
            this.f842b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ((View) a.b.k.v.this.f76d.getParent()).invalidate();
        }
    }

    public s(View view) {
        this.f835a = new WeakReference<>(view);
    }

    public s a(float f2) {
        View view = this.f835a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public void b() {
        View view = this.f835a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public s c(long j) {
        View view = this.f835a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public s d(t tVar) {
        View view = this.f835a.get();
        if (view != null) {
            e(view, tVar);
        }
        return this;
    }

    public final void e(View view, t tVar) {
        if (tVar != null) {
            view.animate().setListener(new a(this, tVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public s f(v vVar) {
        View view = this.f835a.get();
        if (view != null) {
            view.animate().setUpdateListener(vVar != null ? new b(this, vVar, view) : null);
        }
        return this;
    }

    public s g(float f2) {
        View view = this.f835a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }
}
