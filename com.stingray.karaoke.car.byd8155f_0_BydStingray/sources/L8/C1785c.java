package l8;

import p8.InterfaceC2140o;

/* JADX INFO: renamed from: l8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1785c extends AbstractC1786d {
    public final InterfaceC2140o[] c;

    /* JADX WARN: Illegal instructions before constructor call */
    public C1785c(int i7, InterfaceC2140o[] interfaceC2140oArr) {
        if (interfaceC2140oArr == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'enumEntries' of kotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$EnumLiteFlagField.bitWidth must not be null");
        }
        int i10 = 1;
        int length = interfaceC2140oArr.length - 1;
        if (length != 0) {
            for (int i11 = 31; i11 >= 0; i11--) {
                if (((1 << i11) & length) != 0) {
                    i10 = 1 + i11;
                }
            }
            throw new IllegalStateException("Empty enum: " + interfaceC2140oArr.getClass());
        }
        super(i7, i10);
        this.c = interfaceC2140oArr;
    }

    public final Object c(int i7) {
        int i10 = (1 << this.f18826b) - 1;
        int i11 = this.f18825a;
        int i12 = (i7 & (i10 << i11)) >> i11;
        for (InterfaceC2140o interfaceC2140o : this.c) {
            if (interfaceC2140o.a() == i12) {
                return interfaceC2140o;
            }
        }
        return null;
    }
}
