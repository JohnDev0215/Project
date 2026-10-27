package p;

import android.os.RemoteException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class ol1 {
    public final String[] a;
    public final /* synthetic */ ql1 b;

    public ol1(ql1 ql1Var, String[] strArr) {
        this.b = ql1Var;
        this.a = strArr;
    }

    public final void a(Set set) {
        v03.j(set, "tables");
        ql1 ql1Var = this.b;
        if (ql1Var.h.get()) {
            return;
        }
        try {
            x21 x21Var = ql1Var.f;
            if (x21Var != null) {
                int i = ql1Var.d;
                Object[] array = set.toArray(new String[0]);
                v03.g(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                x21Var.W(i, (String[]) array);
            }
        } catch (RemoteException unused) {
        }
    }
}
