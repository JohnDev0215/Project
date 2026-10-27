package defpackage;

import com.google.protobuf.z;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: h, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0187h extends z<C0187h, Object> implements bh1 {
    private static final C0187h DEFAULT_INSTANCE;
    private static volatile wv1<C0187h> PARSER = null;
    public static final int URL_FIELD_NUMBER = 1;
    private int bitField0_;
    private String url_ = "";

    static {
        C0187h c0187h = new C0187h();
        DEFAULT_INSTANCE = c0187h;
        z.M(C0187h.class, c0187h);
    }

    public static C0187h O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"bitField0_", "url_"});
            case NEW_MUTABLE_INSTANCE:
                return new C0187h();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<C0187h> bVar = PARSER;
                if (bVar == null) {
                    synchronized (C0187h.class) {
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
        return this.url_;
    }
}
