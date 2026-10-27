package defpackage;

import defpackage.f81;
import defpackage.n60;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: PrefetchListPaginator.java */
/* JADX INFO: loaded from: classes.dex */
public final class r32 implements n60.a {
    public b81 a;
    public int b;

    @Override // defpackage.b81
    public final b81 a(b81 b81Var) {
        this.a = b81Var;
        return b81Var;
    }

    @Override // defpackage.b81
    public final void b(ArrayList arrayList) {
        b81 b81Var = this.a;
        if (b81Var == null) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            f81 f81Var = (f81) it.next();
            if ((arrayList2.size() >= (this.b + 1) * 25) && !z2) {
                z = true;
                break;
            }
            z2 |= f81Var instanceof f81.d;
            if ((f81Var instanceof f81.b) && ((f81.b) f81Var).d == 3) {
                z2 = false;
            }
            arrayList2.add(f81Var);
        }
        if (z) {
            arrayList2.add(new f81.g());
        }
        b81Var.b(arrayList2);
    }

    @Override // n60.a
    public final void c() {
        this.b++;
    }

    @Override // n60.a
    public final void reset() {
        this.b = 0;
    }
}
