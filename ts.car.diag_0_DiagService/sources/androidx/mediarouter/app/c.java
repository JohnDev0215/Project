package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.RestrictTo;
import androidx.mediarouter.media.t;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class c extends androidx.fragment.app.c {
    private boolean q0 = false;
    private Dialog r0;
    private t s0;

    public c() {
        A1(true);
    }

    private void C1() {
        if (this.s0 == null) {
            Bundle bundleP = p();
            if (bundleP != null) {
                this.s0 = t.d(bundleP.getBundle("selector"));
            }
            if (this.s0 == null) {
                this.s0 = t.f996c;
            }
        }
    }

    public t D1() {
        C1();
        return this.s0;
    }

    public b E1(Context context, Bundle bundle) {
        return new b(context);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public h F1(Context context) {
        return new h(context);
    }

    public void G1(t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        C1();
        if (this.s0.equals(tVar)) {
            return;
        }
        this.s0 = tVar;
        Bundle bundleP = p();
        if (bundleP == null) {
            bundleP = new Bundle();
        }
        bundleP.putBundle("selector", tVar.a());
        k1(bundleP);
        Dialog dialog = this.r0;
        if (dialog != null) {
            if (this.q0) {
                ((h) dialog).k(tVar);
            } else {
                ((b) dialog).k(tVar);
            }
        }
    }

    void H1(boolean z) {
        if (this.r0 != null) {
            throw new IllegalStateException("This must be called before creating dialog");
        }
        this.q0 = z;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = this.r0;
        if (dialog == null) {
            return;
        }
        if (this.q0) {
            ((h) dialog).l();
        } else {
            ((b) dialog).l();
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog w1(Bundle bundle) {
        if (this.q0) {
            h hVarF1 = F1(r());
            this.r0 = hVarF1;
            hVarF1.k(D1());
        } else {
            b bVarE1 = E1(r(), bundle);
            this.r0 = bVarE1;
            bVarE1.k(D1());
        }
        return this.r0;
    }
}
