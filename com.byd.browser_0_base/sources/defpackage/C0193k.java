package defpackage;

import com.google.protobuf.z;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: k, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0193k extends z<C0193k, Object> implements bh1 {
    private static final C0193k DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int FIRST_SEEN_FIELD_NUMBER = 1;
    public static final int MORE_ABOUT_FIELD_NUMBER = 3;
    private static volatile wv1<C0193k> PARSER;
    private int bitField0_;
    private C0189i description_;
    private C0191j firstSeen_;
    private C0187h moreAbout_;

    static {
        C0193k c0193k = new C0193k();
        DEFAULT_INSTANCE = c0193k;
        z.M(C0193k.class, c0193k);
    }

    public static C0193k T(byte[] bArr) {
        return (C0193k) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "firstSeen_", "description_", "moreAbout_"});
            case NEW_MUTABLE_INSTANCE:
                return new C0193k();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<C0193k> bVar = PARSER;
                if (bVar == null) {
                    synchronized (C0193k.class) {
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

    public final C0189i O() {
        C0189i c0189i = this.description_;
        return c0189i == null ? C0189i.O() : c0189i;
    }

    public final C0187h P() {
        C0187h c0187h = this.moreAbout_;
        return c0187h == null ? C0187h.O() : c0187h;
    }

    public final boolean Q() {
        return (this.bitField0_ & 2) != 0;
    }

    public final boolean S() {
        return (this.bitField0_ & 4) != 0;
    }
}
