package spi;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public final class us extends ts {
    public final /* synthetic */ Function0 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public us(Function0 function0, String str, boolean z, String str2, boolean z2) {
        super(str2, z2);
        this.e = function0;
    }

    @Override // spi.ts
    public final long a() {
        this.e.invoke();
        return -1L;
    }
}
