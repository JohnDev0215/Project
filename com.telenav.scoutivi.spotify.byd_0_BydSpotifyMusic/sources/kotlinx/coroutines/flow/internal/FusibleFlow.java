package kotlinx.coroutines.flow.internal;

import k5.g;
import k5.h;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public interface FusibleFlow<T> extends Flow<T> {

    /* JADX INFO: compiled from: ChannelFlow.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Flow fuse$default(FusibleFlow fusibleFlow, g gVar, int i7, k kVar, int i8, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
            }
            if ((i8 & 1) != 0) {
                gVar = h.f7276e;
            }
            if ((i8 & 2) != 0) {
                i7 = -3;
            }
            if ((i8 & 4) != 0) {
                kVar = k.SUSPEND;
            }
            return fusibleFlow.fuse(gVar, i7, kVar);
        }
    }

    Flow<T> fuse(g gVar, int i7, k kVar);
}
