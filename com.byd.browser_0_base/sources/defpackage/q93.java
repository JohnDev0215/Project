package defpackage;

import com.google.protobuf.z;
import defpackage.lk;

/* JADX INFO: compiled from: ThumbnailCacheEntry.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q93 extends z<q93, a> implements bh1 {
    public static final int COMPRESSED_PNG_FIELD_NUMBER = 3;
    public static final int CONTENT_ID_FIELD_NUMBER = 1;
    private static final q93 DEFAULT_INSTANCE;
    private static volatile wv1<q93> PARSER = null;
    public static final int SIZE_PX_FIELD_NUMBER = 2;
    private int bitField0_;
    private lk compressedPng_ = lk.f;
    private p93 contentId_;
    private int sizePx_;

    /* JADX INFO: compiled from: ThumbnailCacheEntry.java */
    public static final class a extends z.a<q93, a> implements bh1 {
        public a() {
            super(q93.DEFAULT_INSTANCE);
        }
    }

    static {
        q93 q93Var = new q93();
        DEFAULT_INSTANCE = q93Var;
        z.M(q93.class, q93Var);
    }

    public static void O(q93 q93Var, lk.f fVar) {
        q93Var.getClass();
        q93Var.bitField0_ |= 4;
        q93Var.compressedPng_ = fVar;
    }

    public static void P(q93 q93Var, p93 p93Var) {
        q93Var.getClass();
        q93Var.contentId_ = p93Var;
        q93Var.bitField0_ |= 1;
    }

    public static void Q(q93 q93Var, int i) {
        q93Var.bitField0_ |= 2;
        q93Var.sizePx_ = i;
    }

    public static a Z() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    public static q93 a0(byte[] bArr) {
        return (q93) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001\u0003ည\u0002", new Object[]{"bitField0_", "contentId_", "sizePx_", "compressedPng_"});
            case NEW_MUTABLE_INSTANCE:
                return new q93();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<q93> bVar = PARSER;
                if (bVar == null) {
                    synchronized (q93.class) {
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

    public final lk T() {
        return this.compressedPng_;
    }

    public final p93 U() {
        p93 p93Var = this.contentId_;
        return p93Var == null ? p93.Q() : p93Var;
    }

    public final int V() {
        return this.sizePx_;
    }

    public final boolean W() {
        return (this.bitField0_ & 4) != 0;
    }

    public final boolean X() {
        return (this.bitField0_ & 1) != 0;
    }

    public final boolean Y() {
        return (this.bitField0_ & 2) != 0;
    }
}
