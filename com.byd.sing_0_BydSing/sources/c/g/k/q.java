package c.g.k;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View f2323b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ViewTreeObserver f2324c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Runnable f2325d;

    private q(View view, Runnable runnable) {
        this.f2323b = view;
        this.f2324c = view.getViewTreeObserver();
        this.f2325d = runnable;
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
        if (this.f2324c.isAlive()) {
            this.f2324c.removeOnPreDrawListener(this);
        } else {
            this.f2323b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f2323b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f2325d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f2324c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
