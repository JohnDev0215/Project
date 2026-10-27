package spi;

import kotlin.jvm.internal.Ref;
import spi.re;

/* JADX INFO: loaded from: classes2.dex */
public final class se extends ts {
    public final /* synthetic */ re.e e;
    public final /* synthetic */ Ref.ObjectRef f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public se(String str, boolean z, String str2, boolean z2, re.e eVar, Ref.ObjectRef objectRef, boolean z3, jp jpVar, Ref.LongRef longRef, Ref.ObjectRef objectRef2) {
        super(str2, z2);
        this.e = eVar;
        this.f = objectRef;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // spi.ts
    public final long a() {
        re reVar = this.e.b;
        reVar.b.a(reVar, (jp) this.f.element);
        return -1L;
    }
}
