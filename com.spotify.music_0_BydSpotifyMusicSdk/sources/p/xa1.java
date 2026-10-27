package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class xa1 extends ya1 {
    @Override // p.ya1
    public final void a(long j, Object obj) {
        ((a2) ((v51) dr2.c.k(j, obj))).c = false;
    }

    @Override // p.ya1
    public final void b(long j, Object obj, Object obj2) {
        cr2 cr2Var = dr2.c;
        v51 v51VarF = (v51) cr2Var.k(j, obj);
        v51 v51Var = (v51) cr2Var.k(j, obj2);
        int size = v51VarF.size();
        int size2 = v51Var.size();
        if (size > 0 && size2 > 0) {
            if (!((a2) v51VarF).c) {
                v51VarF = v51VarF.f(size2 + size);
            }
            v51VarF.addAll(v51Var);
        }
        if (size > 0) {
            v51Var = v51VarF;
        }
        dr2.v(j, obj, v51Var);
    }

    @Override // p.ya1
    public final List c(long j, Object obj) {
        v51 v51Var = (v51) dr2.c.k(j, obj);
        if (((a2) v51Var).c) {
            return v51Var;
        }
        int size = v51Var.size();
        v51 v51VarF = v51Var.f(size == 0 ? 10 : size * 2);
        dr2.v(j, obj, v51VarF);
        return v51VarF;
    }
}
