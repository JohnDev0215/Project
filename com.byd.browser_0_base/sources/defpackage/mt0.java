package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: GroupsProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mt0 implements c0.b {
    public static final mt0 a = new mt0();

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
