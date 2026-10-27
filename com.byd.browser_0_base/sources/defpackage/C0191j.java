package defpackage;

import com.google.protobuf.z;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: j, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0191j extends z<C0191j, Object> implements bh1 {
    public static final int COUNT_FIELD_NUMBER = 1;
    private static final C0191j DEFAULT_INSTANCE;
    private static volatile wv1<C0191j> PARSER = null;
    public static final int PRECISION_FIELD_NUMBER = 3;
    public static final int UNIT_FIELD_NUMBER = 2;
    private int bitField0_;
    private int count_;
    private int precision_;
    private int unit_;

    static {
        C0191j c0191j = new C0191j();
        DEFAULT_INSTANCE = c0191j;
        z.M(C0191j.class, c0191j);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ဌ\u0001\u0003ဌ\u0002", new Object[]{"bitField0_", "count_", "unit_", C0183f.a, "precision_", C0181e.a});
            case NEW_MUTABLE_INSTANCE:
                return new C0191j();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<C0191j> bVar = PARSER;
                if (bVar == null) {
                    synchronized (C0191j.class) {
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
}
