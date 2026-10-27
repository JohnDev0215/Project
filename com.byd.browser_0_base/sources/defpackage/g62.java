package defpackage;

import org.chromium.base.Callback;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.identitymanager.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class g62 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ h62 f;

    public /* synthetic */ g62(h62 h62Var, int i) {
        this.e = i;
        this.f = h62Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        h62 h62Var = this.f;
        switch (i) {
            case 0:
                h62Var.getClass();
                ((a) obj).d(h62Var);
                break;
            default:
                h62Var.getClass();
                AccountManagerFacadeProvider.getInstance().b().g(new gp(h62Var, 22, (a) obj));
                break;
        }
    }
}
