package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: UpdateProtos.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lj3 implements c0.b {
    public static final lj3 a = new lj3();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != -1) {
            c = 2;
            if (i != 0) {
                c = i != 1 ? i != 2 ? (char) 0 : (char) 4 : (char) 3;
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
