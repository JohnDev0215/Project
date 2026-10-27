package defpackage;

import android.graphics.Rect;
import android.view.View;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;

/* JADX INFO: compiled from: ViewResourceAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public class pn3 implements cf0, View.OnLayoutChangeListener, fm {
    public final View e;
    public final Rect f;
    public final Rect g;
    public final ThreadUtils.a h;
    public final a i;
    public float j;
    public final b<Callback<pb2>> k;

    public pn3(View view, boolean z) {
        Rect rect = new Rect();
        this.f = rect;
        Rect rect2 = new Rect();
        this.g = rect2;
        this.h = new ThreadUtils.a();
        this.j = 1.0f;
        this.k = new b<>();
        this.e = view;
        view.addOnLayoutChangeListener(this);
        rect2.set(0, 0, view.getWidth(), view.getHeight());
        rect.set(rect2);
        if (z && true) {
            this.i = new du0();
        } else {
            this.i = new dq2();
        }
    }

    @Override // defpackage.cf0
    public final void a(Callback<pb2> callback) {
        this.k.a(callback);
    }

    @Override // defpackage.cf0
    public final void b() {
        if (this.k.isEmpty() || !h()) {
            return;
        }
        i();
    }

    @Override // defpackage.cf0
    public final void c(Callback<pb2> callback) {
        this.k.c(callback);
    }

    public long f() {
        return a8.u(null);
    }

    public final void g(Rect rect) {
        Rect rect2 = this.f;
        if (rect != null) {
            rect2.union(rect);
        } else {
            View view = this.e;
            rect2.set(0, 0, view.getWidth(), view.getHeight());
        }
    }

    public boolean h() {
        return !this.f.isEmpty();
    }

    public void i() {
        Rect rect = this.f;
        this.h.getClass();
        TraceEvent traceEventH0 = TraceEvent.H0("ViewResourceAdapter:getBitmap", null);
        try {
            if (this.i.d(this.e, new Rect(rect), this.j, this, new l51(25, this))) {
                rect.setEmpty();
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i3 - i;
        int i10 = i4 - i2;
        int i11 = i8 - i6;
        if (i9 == i7 - i5 && i10 == i11) {
            return;
        }
        this.g.set(0, 0, i9, i10);
        this.f.set(0, 0, i9, i10);
        this.i.c(this.e, this.j);
    }

    /* JADX INFO: compiled from: ViewResourceAdapter.java */
    public interface a {
        boolean a();

        void b();

        boolean d(View view, Rect rect, float f, fm fmVar, l51 l51Var);

        default void c(View view, float f) {
        }
    }
}
