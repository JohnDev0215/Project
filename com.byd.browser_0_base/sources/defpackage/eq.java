package defpackage;

import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.keyboard_accessory.a;

/* JADX INFO: compiled from: ChromeKeyboardVisibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class eq extends vn2 {
    public static final yj1 j = new yj1("AndroidScrollOptimizations", false);
    public final nw2<a> i;

    public eq(WeakReference weakReference, vb1 vb1Var) {
        super(weakReference);
        this.i = vb1Var;
    }

    @Override // defpackage.t41
    public final int b(View view) {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeKeyboardVisibilityDelegate.calculateKeyboardHeight", null);
        try {
            if (!j.a()) {
                int iB = super.b(view);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return iB;
            }
            if (view != null && view.getRootWindowInsets() != null) {
                pt3 pt3VarI = pt3.i(view, view.getRootWindowInsets());
                int i = pt3VarI.a(8).d;
                if (i == 0) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return 0;
                }
                int i2 = i - pt3VarI.a(2).d;
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return i2;
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return 0;
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

    @Override // defpackage.t41
    public final int c(View view) {
        nw2<a> nw2Var = this.i;
        return b(view) + (nw2Var.f() ? nw2Var.get().r0() : 0);
    }

    @Override // defpackage.t41
    public final boolean e(View view) {
        boolean zR;
        nw2<a> nw2Var = this.i;
        if (nw2Var.f()) {
            zR = nw2Var.get().R();
            nw2Var.get().b();
        } else {
            zR = false;
        }
        return t41.d(view) || zR;
    }

    @Override // defpackage.vn2, defpackage.t41
    public final boolean f(Context context, View view) {
        if (!super.f(context, view)) {
            nw2<a> nw2Var = this.i;
            if (!nw2Var.f() || !nw2Var.get().R()) {
                return false;
            }
        }
        return true;
    }
}
