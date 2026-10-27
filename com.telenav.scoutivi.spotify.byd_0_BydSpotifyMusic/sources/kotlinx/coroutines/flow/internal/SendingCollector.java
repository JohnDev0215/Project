package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import kotlinx.coroutines.channels.g0;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: SendingCollector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SendingCollector<T> implements FlowCollector<T> {
    private final g0<T> channel;

    /* JADX WARN: Multi-variable type inference failed */
    public SendingCollector(g0<? super T> g0Var) {
        this.channel = g0Var;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t6, d<? super r> dVar) {
        Object objSend = this.channel.send(t6, dVar);
        return objSend == l5.d.d() ? objSend : r.f6563a;
    }
}
