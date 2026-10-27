package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: StudyOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nv2 implements c0.b {
    public static final nv2 a = new nv2();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != 0) {
            c = 2;
            if (i != 1) {
                c = i != 2 ? i != 3 ? (char) 0 : (char) 4 : (char) 3;
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
