package defpackage;

import defpackage.f81;
import defpackage.n60;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: DateListPaginator.java */
/* JADX INFO: loaded from: classes.dex */
public final class e60 implements n60.a {
    public b81 a;
    public int b;

    @Override // defpackage.b81
    public final b81 a(b81 b81Var) {
        this.a = b81Var;
        return b81Var;
    }

    @Override // defpackage.b81
    public final void b(ArrayList arrayList) {
        boolean z;
        b81 b81Var = this.a;
        if (b81Var == null) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            f81 f81Var = (f81) it.next();
            if ((f81Var instanceof f81.h) && arrayList2.size() >= (this.b + 1) * 25) {
                z = true;
                break;
            }
            arrayList2.add(f81Var);
        }
        if (z) {
            arrayList2.add(new f81.g());
            this.b = (arrayList2.size() / 25) - 1;
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
