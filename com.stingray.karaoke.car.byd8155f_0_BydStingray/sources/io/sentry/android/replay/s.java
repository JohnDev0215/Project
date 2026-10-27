package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewTreeObserver;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class s implements ViewTreeObserver.OnDrawListener {
    public final u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final F1 f16424m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e7.f f16425n;
    public final ReplayIntegration o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final m7.n f16426p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public WeakReference f16427q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Object f16428r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Object f16429s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f16430t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f16431u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final AtomicBoolean f16432v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final AtomicBoolean f16433w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Bitmap f16434x;

    public s(u uVar, F1 f12, e7.f fVar, ReplayIntegration replayIntegration) {
        A7.m.f(uVar, "config");
        A7.m.f(fVar, "mainLooperHandler");
        this.l = uVar;
        this.f16424m = f12;
        this.f16425n = fVar;
        this.o = replayIntegration;
        this.f16426p = h6.r.o(C1450a.f16331r);
        m7.g gVar = m7.g.f19257m;
        this.f16428r = h6.r.n(gVar, C1450a.f16330q);
        this.f16429s = h6.r.n(gVar, C1450a.f16332s);
        this.f16430t = h6.r.n(gVar, new r(this, 1));
        this.f16431u = h6.r.n(gVar, new r(this, 0));
        this.f16432v = new AtomicBoolean(false);
        this.f16433w = new AtomicBoolean(true);
    }

    public final void a(View view) {
        A7.m.f(view, "root");
        WeakReference weakReference = this.f16427q;
        b(weakReference != null ? (View) weakReference.get() : null);
        WeakReference weakReference2 = this.f16427q;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.f16427q = new WeakReference(view);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnDrawListener(this);
        }
        this.f16432v.set(true);
    }

    public final void b(View view) {
        ViewTreeObserver viewTreeObserver;
        if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnDrawListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        WeakReference weakReference = this.f16427q;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            this.f16424m.getLogger().k(EnumC1495p1.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
        } else {
            this.f16432v.set(true);
        }
    }
}
