package p;

import androidx.work.NetworkType;

/* JADX INFO: loaded from: classes.dex */
public final class an1 extends androidx.work.impl.constraints.controllers.a {
    public final int b;

    static {
        v03.i(vc1.d("NetworkNotRoamingCtrlr"), "tagWithPrefix(\"NetworkNotRoamingCtrlr\")");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public an1(qy qyVar) {
        super(qyVar);
        v03.j(qyVar, "tracker");
        this.b = 7;
    }

    @Override // androidx.work.impl.constraints.controllers.a
    public final int a() {
        return this.b;
    }

    @Override // androidx.work.impl.constraints.controllers.a
    public final boolean b(ky2 ky2Var) {
        return ky2Var.j.a == NetworkType.l;
    }

    @Override // androidx.work.impl.constraints.controllers.a
    public final boolean c(Object obj) {
        dn1 dn1Var = (dn1) obj;
        v03.j(dn1Var, "value");
        return (dn1Var.a && dn1Var.d) ? false : true;
    }
}
