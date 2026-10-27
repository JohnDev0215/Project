package b.g.j;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f1139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ViewTreeObserver f1140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Runnable f1141d;

    public q(View view, Runnable runnable) {
        this.f1139b = view;
        this.f1140c = view.getViewTreeObserver();
        this.f1141d = runnable;
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
        if (this.f1140c.isAlive()) {
            this.f1140c.removeOnPreDrawListener(this);
        } else {
            this.f1139b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f1139b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f1141d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f1140c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
