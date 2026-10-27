package p;

import java.lang.reflect.Array;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class qr1 extends ux2 {
    public final /* synthetic */ int g;
    public final /* synthetic */ ux2 h;

    public /* synthetic */ qr1(ux2 ux2Var, int i) {
        this.g = i;
        this.h = ux2Var;
    }

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        int i = this.g;
        ux2 ux2Var = this.h;
        switch (i) {
            case 0:
                Iterable iterable = (Iterable) obj;
                if (iterable != null) {
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        ux2Var.c(n32Var, it.next());
                    }
                    break;
                }
                break;
            default:
                if (obj != null) {
                    int length = Array.getLength(obj);
                    for (int i2 = 0; i2 < length; i2++) {
                        ux2Var.c(n32Var, Array.get(obj, i2));
                    }
                    break;
                }
                break;
        }
    }
}
