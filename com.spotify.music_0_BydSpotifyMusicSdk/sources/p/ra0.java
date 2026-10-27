package p;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: loaded from: classes.dex */
public final class ra0 implements dv0 {
    public final dv0 c;
    public final ry0 e;
    public final fz0 k;

    public ra0(ny0 ny0Var) {
        ry0 ry0Var = kotlinx.coroutines.flow.e.a;
        fz0 fz0Var = kotlinx.coroutines.flow.e.b;
        this.c = ny0Var;
        this.e = ry0Var;
        this.k = fz0Var;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, p.tz2] */
    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) {
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = qo1.a;
        Object objC = this.c.c(new kotlinx.coroutines.flow.c(this, ref$ObjectRef, fv0Var), c00Var);
        return objC == CoroutineSingletons.c ? objC : lq2.a;
    }
}
