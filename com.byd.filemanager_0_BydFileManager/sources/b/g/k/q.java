package b.g.k;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View f1996b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ViewTreeObserver f1997c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Runnable f1998d;

    private q(View view, Runnable runnable) {
        this.f1996b = view;
        this.f1997c = view.getViewTreeObserver();
        this.f1998d = runnable;
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
        if (this.f1997c.isAlive()) {
            this.f1997c.removeOnPreDrawListener(this);
        } else {
            this.f1996b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f1996b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f1998d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f1997c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
