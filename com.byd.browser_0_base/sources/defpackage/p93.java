package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: ThumbnailCacheEntry.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p93 extends z<p93, a> implements bh1 {
    private static final p93 DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    private static volatile wv1<p93> PARSER;
    private int bitField0_;
    private String id_ = "";

    /* JADX INFO: compiled from: ThumbnailCacheEntry.java */
    public static final class a extends z.a<p93, a> implements bh1 {
        public a() {
            super(p93.DEFAULT_INSTANCE);
        }
    }

    static {
        p93 p93Var = new p93();
        DEFAULT_INSTANCE = p93Var;
        z.M(p93.class, p93Var);
    }

    public static void O(p93 p93Var, String str) {
        p93Var.getClass();
        str.getClass();
        p93Var.bitField0_ |= 1;
        p93Var.id_ = str;
    }

    public static p93 Q() {
        return DEFAULT_INSTANCE;
    }

    public static a T() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"bitField0_", "id_"});
            case NEW_MUTABLE_INSTANCE:
                return new p93();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<p93> bVar = PARSER;
                if (bVar == null) {
                    synchronized (p93.class) {
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

    public final String S() {
        return this.id_;
    }
}
