package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class D4 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public final View s;
    public ViewTreeObserver t;
    public final Runnable u;

    public D4(View view, Runnable runnable) {
        this.s = view;
        this.t = view.getViewTreeObserver();
        this.u = runnable;
    }

    public static D4 a(View view, Runnable runnable) {
        D4 d4 = new D4(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(d4);
        view.addOnAttachStateChangeListener(d4);
        return d4;
    }

    public void b() {
        if (this.t.isAlive()) {
            this.t.removeOnPreDrawListener(this);
        } else {
            this.s.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.s.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.u.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.t = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
