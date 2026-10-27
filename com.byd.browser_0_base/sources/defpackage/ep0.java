package defpackage;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ep0 implements wx {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ep0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.wx
    public final void accept(Object obj) {
        int i = this.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                ((FragmentActivity) obj2).w.a();
                break;
            default:
                FragmentManager fragmentManager = (FragmentManager) obj2;
                sj1 sj1Var = (sj1) obj;
                if (fragmentManager.I()) {
                    fragmentManager.k(sj1Var.a, false);
                }
                break;
        }
    }
}
