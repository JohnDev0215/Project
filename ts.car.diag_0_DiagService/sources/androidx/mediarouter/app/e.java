package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.RestrictTo;
import androidx.mediarouter.media.t;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class e extends androidx.fragment.app.c {
    private boolean q0 = false;
    private Dialog r0;
    private t s0;

    public e() {
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

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        Dialog dialog = this.r0;
        if (dialog == null || this.q0) {
            return;
        }
        ((d) dialog).n(false);
    }

    public d D1(Context context, Bundle bundle) {
        return new d(context);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public i E1(Context context) {
        return new i(context);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void F1(t tVar) {
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
        if (dialog == null || !this.q0) {
            return;
        }
        ((i) dialog).q(tVar);
    }

    void G1(boolean z) {
        if (this.r0 != null) {
            throw new IllegalStateException("This must be called before creating dialog");
        }
        this.q0 = z;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = this.r0;
        if (dialog != null) {
            if (this.q0) {
                ((i) dialog).s();
            } else {
                ((d) dialog).J();
            }
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog w1(Bundle bundle) {
        if (this.q0) {
            i iVarE1 = E1(r());
            this.r0 = iVarE1;
            iVarE1.q(this.s0);
        } else {
            this.r0 = D1(r(), bundle);
        }
        return this.r0;
    }
}
