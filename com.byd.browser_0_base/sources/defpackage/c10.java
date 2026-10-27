package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: CouponPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c10 implements c0.b {
    public static final c10 a = new c10();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != 1) {
            c = 2;
            if (i != 2) {
                c = 3;
                if (i != 3) {
                    c = 0;
                }
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
