package p;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class h8 implements yu0, nj1 {
    public final /* synthetic */ s8 c;

    public /* synthetic */ h8(s8 s8Var) {
        this.c = s8Var;
    }

    @Override // p.nj1
    public final void b(si1 si1Var, boolean z) {
        r8 r8Var;
        si1 si1VarK = si1Var.k();
        int i = 0;
        boolean z2 = si1VarK != si1Var;
        if (z2) {
            si1Var = si1VarK;
        }
        s8 s8Var = this.c;
        r8[] r8VarArr = s8Var.T;
        int length = r8VarArr != null ? r8VarArr.length : 0;
        while (true) {
            if (i < length) {
                r8Var = r8VarArr[i];
                if (r8Var != null && r8Var.h == si1Var) {
                    break;
                } else {
                    i++;
                }
            } else {
                r8Var = null;
                break;
            }
        }
        if (r8Var != null) {
            if (!z2) {
                s8Var.q(r8Var, z);
            } else {
                s8Var.o(r8Var.a, r8Var, si1VarK);
                s8Var.q(r8Var, true);
            }
        }
    }

    @Override // p.nj1
    public final boolean s(si1 si1Var) {
        Window.Callback callback;
        if (si1Var != si1Var.k()) {
            return true;
        }
        s8 s8Var = this.c;
        if (!s8Var.N || (callback = s8Var.t.getCallback()) == null || s8Var.Y) {
            return true;
        }
        callback.onMenuOpened(108, si1Var);
        return true;
    }
}
