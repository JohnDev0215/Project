package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: CommonTypesProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nv extends z<nv, Object> implements bh1 {
    private static final nv DEFAULT_INSTANCE;
    private static volatile wv1<nv> PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int bitField0_;
    private String typeUrl_ = "";
    private lk value_ = lk.f;

    static {
        nv nvVar = new nv();
        DEFAULT_INSTANCE = nvVar;
        z.M(nv.class, nvVar);
    }

    public static nv P(lk lkVar) {
        return (nv) z.H(DEFAULT_INSTANCE, lkVar);
    }

    public static nv Q(byte[] bArr) {
        return (nv) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001", new Object[]{"bitField0_", "typeUrl_", "value_"});
            case NEW_MUTABLE_INSTANCE:
                return new nv();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<nv> bVar = PARSER;
                if (bVar == null) {
                    synchronized (nv.class) {
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

    public final lk O() {
        return this.value_;
    }
}
