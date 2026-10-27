package kotlinx.coroutines.flow;

import g5.r;
import k5.d;

/* JADX INFO: compiled from: Flow.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Flow<T> {
    Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar);
}
