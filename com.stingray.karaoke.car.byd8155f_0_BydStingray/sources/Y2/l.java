package Y2;

import d5.C1077F;
import d5.I;

/* JADX INFO: loaded from: classes.dex */
public interface l {
    default void d() {
    }

    void h(byte[] bArr, int i7, int i10, k kVar, h2.g gVar);

    int s();

    default d x(int i7, int i10, byte[] bArr) {
        C1077F c1077fU = I.u();
        h(bArr, 0, i10, k.c, new G2.a(c1077fU, 4));
        return new b(c1077fU.h());
    }
}
