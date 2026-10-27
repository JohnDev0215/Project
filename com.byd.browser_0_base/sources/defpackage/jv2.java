package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: StudyOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jv2 implements c0.b {
    public static final jv2 a = new jv2();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != -1) {
            c = 2;
            if (i != 0) {
                c = i != 1 ? i != 2 ? i != 3 ? (char) 0 : (char) 5 : (char) 4 : (char) 3;
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
