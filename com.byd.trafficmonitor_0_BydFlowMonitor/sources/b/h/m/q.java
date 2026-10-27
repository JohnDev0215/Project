package b.h.m;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f2640b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ViewTreeObserver f2641c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Runnable f2642d;

    public q(View view, Runnable runnable) {
        this.f2640b = view;
        this.f2641c = view.getViewTreeObserver();
        this.f2642d = runnable;
    }

    public static q a(View view, Runnable runnable) {
        Objects.requireNonNull(view, "view == null");
        Objects.requireNonNull(runnable, "runnable == null");
        q qVar = new q(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(qVar);
        view.addOnAttachStateChangeListener(qVar);
        return qVar;
    }

    public void b() {
        if (this.f2641c.isAlive()) {
            this.f2641c.removeOnPreDrawListener(this);
        } else {
            this.f2640b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f2640b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f2642d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f2641c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
