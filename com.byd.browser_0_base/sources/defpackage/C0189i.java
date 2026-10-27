package defpackage;

import com.google.protobuf.z;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: i, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AboutThisSiteMetadataProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0189i extends z<C0189i, Object> implements bh1 {
    private static final C0189i DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int LANG_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile wv1<C0189i> PARSER = null;
    public static final int SOURCE_FIELD_NUMBER = 3;
    public static final int SUBTITLE_FIELD_NUMBER = 5;
    private int bitField0_;
    private C0185g source_;
    private String name_ = "";
    private String subtitle_ = "";
    private String description_ = "";
    private String lang_ = "";

    static {
        C0189i c0189i = new C0189i();
        DEFAULT_INSTANCE = c0189i;
        z.M(C0189i.class, c0189i);
    }

    public static C0189i O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0002\u0003ဉ\u0003\u0004ဈ\u0004\u0005ဈ\u0001", new Object[]{"bitField0_", "name_", "description_", "source_", "lang_", "subtitle_"});
            case NEW_MUTABLE_INSTANCE:
                return new C0189i();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<C0189i> bVar = PARSER;
                if (bVar == null) {
                    synchronized (C0189i.class) {
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
        return this.description_;
    }

    public final C0185g Q() {
        C0185g c0185g = this.source_;
        return c0185g == null ? C0185g.O() : c0185g;
    }
}
