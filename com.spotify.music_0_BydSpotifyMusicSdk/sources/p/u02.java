package p;

import kotlinx.coroutines.channels.BufferOverflow;

/* JADX INFO: loaded from: classes.dex */
public final class u02 implements ad2, dv0, rz0 {
    public final /* synthetic */ ad2 c;

    public u02(kotlinx.coroutines.flow.g gVar) {
        this.c = gVar;
    }

    @Override // p.rz0
    public final dv0 a(y10 y10Var, int i, BufferOverflow bufferOverflow) {
        tz2 tz2Var = cd2.a;
        return ((i == 0 || i == -3) && bufferOverflow == BufferOverflow.c) ? this : new ar(this, y10Var, i, bufferOverflow);
    }

    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) {
        return this.c.c(fv0Var, c00Var);
    }
}
