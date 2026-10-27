package defpackage;

import com.google.protobuf.c0;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: f, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0183f implements c0.b {
    public static final C0183f a = new C0183f();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != 0) {
            c = 2;
            if (i != 1) {
                if (i != 2) {
                    c = 4;
                    if (i != 3) {
                        c = i != 4 ? (char) 0 : (char) 5;
                    }
                } else {
                    c = 3;
                }
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
