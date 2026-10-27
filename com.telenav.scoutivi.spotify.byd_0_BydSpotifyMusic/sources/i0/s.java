package i0;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class s implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final View f6881e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ViewTreeObserver f6882g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Runnable f6883h;

    private s(View view, Runnable runnable) {
        this.f6881e = view;
        this.f6882g = view.getViewTreeObserver();
        this.f6883h = runnable;
    }

    public static s a(View view, Runnable runnable) {
        Objects.requireNonNull(view, "view == null");
        Objects.requireNonNull(runnable, "runnable == null");
        s sVar = new s(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(sVar);
        view.addOnAttachStateChangeListener(sVar);
        return sVar;
    }

    public void b() {
        if (this.f6882g.isAlive()) {
            this.f6882g.removeOnPreDrawListener(this);
        } else {
            this.f6881e.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f6881e.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f6883h.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f6882g = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
