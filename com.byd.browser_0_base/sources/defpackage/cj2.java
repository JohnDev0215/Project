package defpackage;

import defpackage.q12;
import java.util.HashMap;

/* JADX INFO: compiled from: SessionRemovedTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cj2 extends ip1 {
    public cj2(jq0 jq0Var, q12 q12Var) {
        super(jq0Var, q12Var);
    }

    @Override // defpackage.ip1
    public final void s() {
        ip1.q("SessionRemovedTask.removeSession");
        q12 q12Var = this.i;
        HashMap<Long, q12.a> mapA = q12Var.a();
        jq0 jq0Var = this.h;
        q12.a aVarRemove = mapA.remove(Long.valueOf(jq0Var.get(0).a));
        if (aVarRemove == null) {
            return;
        }
        ((m12) vd1.f()).getClass();
        aVarRemove.a.destroy();
        q12.a aVar = jq0Var.size() > 2 ? q12Var.a().get(Long.valueOf(jq0Var.get(1).a)) : q12Var.a;
        if (aVar == null) {
            return;
        }
        ((m12) vd1.f()).getClass();
        aVar.a.notifyViewDisappeared(aVarRemove.b);
    }
}
