package io.sentry.android.core.internal.util;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class f implements ViewTreeObserver.OnDrawListener {
    public final Handler l = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicReference f16217m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Runnable f16218n;

    public f(View view, Runnable runnable) {
        this.f16217m = new AtomicReference(view);
        this.f16218n = runnable;
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        final View view = (View) this.f16217m.getAndSet(null);
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: io.sentry.android.core.internal.util.e
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                f fVar = this.l;
                fVar.getClass();
                view.getViewTreeObserver().removeOnDrawListener(fVar);
            }
        });
        this.l.postAtFrontOfQueue(this.f16218n);
    }
}
