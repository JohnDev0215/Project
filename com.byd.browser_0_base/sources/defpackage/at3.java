package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: WebsiteEventProtos.java */
/* JADX INFO: loaded from: classes2.dex */
public final class at3 implements c0.b {
    public static final at3 a = new at3();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != 0) {
            c = 2;
            if (i != 1) {
                c = i != 2 ? (char) 0 : (char) 3;
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
