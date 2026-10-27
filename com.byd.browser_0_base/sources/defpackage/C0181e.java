package defpackage;

import com.google.protobuf.c0;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: e, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0181e implements c0.b {
    public static final C0181e a = new C0181e();

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
