package defpackage;

import defpackage.ij0;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: compiled from: ExternalNavigationHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gj0 implements PermissionCallback {
    public final /* synthetic */ ij0 e;
    public final /* synthetic */ hj0 f;

    public gj0(hj0 hj0Var, ij0 ij0Var) {
        this.f = hj0Var;
        this.e = ij0Var;
    }

    @Override // org.chromium.ui.permissions.PermissionCallback
    public final void s(int[] iArr, String[] strArr) {
        int length = iArr.length;
        ij0 ij0Var = this.e;
        if (length > 0) {
            boolean z = false;
            if (iArr[0] == 0) {
                cj0 cj0Var = (cj0) this.f.a;
                if (cj0Var.a != null && !cj0Var.c) {
                    z = true;
                }
                if (z) {
                    lb2<ij0.a> lb2Var = ij0Var.s;
                    if (lb2Var != null) {
                        lb2Var.onResult(new ij0.a(ij0Var.a, ij0Var));
                        return;
                    }
                    return;
                }
            }
        }
        lb2<ij0.a> lb2Var2 = ij0Var.s;
        if (lb2Var2 != null) {
            lb2Var2.onResult(new ij0.a());
        }
    }
}
