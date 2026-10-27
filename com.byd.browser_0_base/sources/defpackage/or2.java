package defpackage;

import android.animation.AnimatorSet;
import android.content.Context;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.features.tasks.TasksView;

/* JADX INFO: compiled from: StartSurfaceHomeLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class or2 extends y51 {
    public final lr2 t;
    public boolean u;
    public boolean v;
    public AnimatorSet w;
    public SceneLayer x;

    public or2(Context context, n61 n61Var, j61 j61Var, lr2 lr2Var) {
        super(context, n61Var, j61Var);
        this.t = lr2Var;
        lr2Var.setOnTabSelectingListener(new gc3(this));
    }

    @Override // defpackage.y51
    public final void H(long j, boolean z) {
        lr2 lr2Var = this.t;
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceHomeLayout.Show.StartSurface", null);
        try {
            super.H(j, z);
            lr2Var.o();
            lr2Var.c(z);
            this.u = true;
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

    @Override // defpackage.y51
    public final void I(int i, boolean z) {
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceHomeLayout.Hide.StartSurface", null);
        try {
            super.I(i, z);
            this.u = false;
            this.t.b();
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

    @Override // defpackage.y51
    public final void L() {
        if (this.x != null) {
            return;
        }
        this.x = new SceneLayer();
    }

    @Override // defpackage.y51
    public final void f() {
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceHomeLayout.DoneHiding", null);
        try {
            super.f();
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

    @Override // defpackage.y51
    public final void g() {
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceHomeLayout.DoneShowing", null);
        try {
            super.g();
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

    @Override // defpackage.y51
    public final EventFilter k() {
        return null;
    }

    @Override // defpackage.y51
    public final int m() {
        return 16;
    }

    @Override // defpackage.y51
    public final SceneLayer n() {
        return this.x;
    }

    @Override // defpackage.y51
    public final boolean t() {
        return this.t.a();
    }

    @Override // defpackage.y51
    public final void u() {
        if (this.v) {
            return;
        }
        this.v = true;
        if (this.x == null) {
            this.x = new SceneLayer();
        }
        this.t.d();
    }

    @Override // defpackage.y51
    public final void y(int i, int i2, boolean z, boolean z2, float f, float f2) {
        if (z2 && !z && this.u) {
            TasksView tasksViewV = this.t.v();
            AnimatorSet animatorSet = this.w;
            if (animatorSet != null && animatorSet.isStarted()) {
                this.w.end();
            }
            AnimatorSet animatorSetC = ec.c(this, tasksViewV, f, f2, this.j == 1);
            this.w = animatorSetC;
            animatorSetC.start();
        }
    }

    @Override // defpackage.y51
    public final void d() {
    }
}
