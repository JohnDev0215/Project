package p;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;

/* JADX INFO: loaded from: classes.dex */
public final class ym1 implements j7 {
    public final hn1 a;
    public final kotlinx.coroutines.b b;

    public ym1(com.apollographql.apollo3.network.http.c cVar, hn1 hn1Var, kotlinx.coroutines.b bVar) {
        v03.j(bVar, "dispatcher");
        this.a = cVar;
        this.b = bVar;
    }

    @Override // p.j7
    public final dv0 a(m7 m7Var, e70 e70Var) {
        v03.j(m7Var, "request");
        if (!(m7Var.a instanceof fz1)) {
            throw new IllegalStateException("".toString());
        }
        dv0 dv0VarA = this.a.a(m7Var);
        qo qoVar = qo.o;
        kotlinx.coroutines.b bVar = this.b;
        if (bVar.E0(qoVar) != null) {
            throw new IllegalArgumentException(("Flow context cannot contain job in it. Had " + bVar).toString());
        }
        if (v03.b(bVar, EmptyCoroutineContext.c)) {
            return dv0VarA;
        }
        boolean z = dv0VarA instanceof rz0;
        BufferOverflow bufferOverflow = BufferOverflow.c;
        return z ? ((rz0) dv0VarA).a(bVar, -3, bufferOverflow) : new ar(dv0VarA, bVar, -3, bufferOverflow);
    }
}
