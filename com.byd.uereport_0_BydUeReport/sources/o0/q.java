package o0;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f6695d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewTreeObserver f6696e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runnable f6697f;

    public q(View view, Runnable runnable) {
        this.f6695d = view;
        this.f6696e = view.getViewTreeObserver();
        this.f6697f = runnable;
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
        if (this.f6696e.isAlive()) {
            this.f6696e.removeOnPreDrawListener(this);
        } else {
            this.f6695d.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f6695d.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f6697f.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f6696e = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
