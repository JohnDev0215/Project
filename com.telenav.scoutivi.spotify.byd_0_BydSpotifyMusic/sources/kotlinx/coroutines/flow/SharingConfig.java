package kotlinx.coroutines.flow;

import k5.g;
import kotlinx.coroutines.channels.k;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
final class SharingConfig<T> {
    public final g context;
    public final int extraBufferCapacity;
    public final k onBufferOverflow;
    public final Flow<T> upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public SharingConfig(Flow<? extends T> flow, int i7, k kVar, g gVar) {
        this.upstream = flow;
        this.extraBufferCapacity = i7;
        this.onBufferOverflow = kVar;
        this.context = gVar;
    }
}
