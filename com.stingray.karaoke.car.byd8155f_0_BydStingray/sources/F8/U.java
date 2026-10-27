package F8;

import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class U implements InterfaceC2782k {
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        if (((C2058c) obj) != null) {
            return Boolean.valueOf(!r1.equals(N7.n.f4866y));
        }
        throw new IllegalArgumentException("Argument for @NotNull parameter 'name' of kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$1.invoke must not be null");
    }
}
