package defpackage;

import com.google.protobuf.z;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: g, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0185g extends z<C0185g, Object> implements bh1 {
    private static final C0185g DEFAULT_INSTANCE;
    public static final int LABEL_FIELD_NUMBER = 1;
    private static volatile wv1<C0185g> PARSER = null;
    public static final int URL_FIELD_NUMBER = 2;
    private int bitField0_;
    private String label_ = "";
    private String url_ = "";

    static {
        C0185g c0185g = new C0185g();
        DEFAULT_INSTANCE = c0185g;
        z.M(C0185g.class, c0185g);
    }

    public static C0185g O() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "label_", "url_"});
            case NEW_MUTABLE_INSTANCE:
                return new C0185g();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<C0185g> bVar = PARSER;
                if (bVar == null) {
                    synchronized (C0185g.class) {
                        bVar = PARSER;
                        if (bVar == null) {
                            bVar = new z.b<>(DEFAULT_INSTANCE);
                            PARSER = bVar;
                        }
                        break;
                    }
                }
                return bVar;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final String P() {
        return this.label_;
    }

    public final String Q() {
        return this.url_;
    }
}
