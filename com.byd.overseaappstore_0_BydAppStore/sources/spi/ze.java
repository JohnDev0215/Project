package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class ze extends ts {
    public final /* synthetic */ re e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ze(String str, boolean z, String str2, boolean z2, re reVar) {
        super(str2, z2);
        this.e = reVar;
    }

    @Override // spi.ts
    public final long a() {
        this.e.I(false, 2, 0);
        return -1L;
    }
}
