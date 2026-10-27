package a.h.k;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class l implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f821a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewTreeObserver f822b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Runnable f823c;

    public l(View view, Runnable runnable) {
        this.f821a = view;
        this.f822b = view.getViewTreeObserver();
        this.f823c = runnable;
    }

    public static l a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        l lVar = new l(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(lVar);
        view.addOnAttachStateChangeListener(lVar);
        return lVar;
    }

    public void b() {
        (this.f822b.isAlive() ? this.f822b : this.f821a.getViewTreeObserver()).removeOnPreDrawListener(this);
        this.f821a.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f823c.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f822b = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
