package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: NopCollector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NopCollector implements FlowCollector<Object> {
    public static final NopCollector INSTANCE = new NopCollector();

    private NopCollector() {
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, d<? super r> dVar) {
        return r.f6563a;
    }
}
