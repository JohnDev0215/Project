package a.g.k;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View f419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ViewTreeObserver f420c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Runnable f421d;

    private q(View view, Runnable runnable) {
        this.f419b = view;
        this.f420c = view.getViewTreeObserver();
        this.f421d = runnable;
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
        (this.f420c.isAlive() ? this.f420c : this.f419b.getViewTreeObserver()).removeOnPreDrawListener(this);
        this.f419b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f421d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f420c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
