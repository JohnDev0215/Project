package defpackage;

import defpackage.f81;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListItemPropertySetter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h81 implements b81 {
    public final wc0 a;
    public b81 b;

    public h81(wc0 wc0Var) {
        this.a = wc0Var;
    }

    @Override // defpackage.b81
    public final b81 a(b81 b81Var) {
        this.b = b81Var;
        return b81Var;
    }

    @Override // defpackage.b81
    public final void b(ArrayList arrayList) {
        if (this.a.d) {
            int i = 0;
            while (i < arrayList.size()) {
                f81 f81Var = (f81) arrayList.get(i);
                if ((f81Var instanceof f81.f) && ((f81.f) f81Var).e.h == 3) {
                    f81 f81Var2 = i == 0 ? null : (f81) arrayList.get(i - 1);
                    f81 f81Var3 = i < arrayList.size() - 1 ? (f81) arrayList.get(i + 1) : null;
                    boolean z = (f81Var2 instanceof f81.f) && ((f81.f) f81Var2).e.h == 3;
                    boolean z2 = (f81Var3 instanceof f81.f) && ((f81.f) f81Var3).e.h == 3;
                    if (!z && !z2) {
                        ((f81.f) f81Var).f = true;
                    }
                }
                i++;
            }
        }
        this.b.b(arrayList);
    }
}
