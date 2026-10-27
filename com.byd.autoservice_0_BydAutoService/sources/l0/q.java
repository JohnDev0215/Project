package l0;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f3647b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ViewTreeObserver f3648c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Runnable f3649d;

    public q(View view, Runnable runnable) {
        this.f3647b = view;
        this.f3648c = view.getViewTreeObserver();
        this.f3649d = runnable;
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
        if (this.f3648c.isAlive()) {
            this.f3648c.removeOnPreDrawListener(this);
        } else {
            this.f3647b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f3647b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f3649d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f3648c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
