package com.google.zxing.n.r.g;

import java.util.List;

/* JADX INFO: compiled from: BitArrayBuilder.java */
/* JADX INFO: loaded from: classes.dex */
final class a {
    static com.google.zxing.common.a a(List<b> list) {
        int size = (list.size() << 1) - 1;
        if (list.get(list.size() - 1).d() == null) {
            size--;
        }
        com.google.zxing.common.a aVar = new com.google.zxing.common.a(size * 12);
        int i = 0;
        int iB = list.get(0).d().b();
        for (int i2 = 11; i2 >= 0; i2--) {
            if (((1 << i2) & iB) != 0) {
                aVar.q(i);
            }
            i++;
        }
        for (int i3 = 1; i3 < list.size(); i3++) {
            b bVar = list.get(i3);
            int iB2 = bVar.c().b();
            for (int i4 = 11; i4 >= 0; i4--) {
                if (((1 << i4) & iB2) != 0) {
                    aVar.q(i);
                }
                i++;
            }
            if (bVar.d() != null) {
                int iB3 = bVar.d().b();
                for (int i5 = 11; i5 >= 0; i5--) {
                    if (((1 << i5) & iB3) != 0) {
                        aVar.q(i);
                    }
                    i++;
                }
            }
        }
        return aVar;
    }
}
