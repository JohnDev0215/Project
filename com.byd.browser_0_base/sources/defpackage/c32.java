package defpackage;

import com.google.protobuf.c0;
import com.google.protobuf.y0;
import com.google.protobuf.z;

/* JADX INFO: compiled from: PowerBookmarkMeta.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c32 extends z<c32, Object> implements bh1 {
    private static final c32 DEFAULT_INSTANCE;
    public static final int FALLBACK_IMAGES_FIELD_NUMBER = 2;
    public static final int LEAD_IMAGE_FIELD_NUMBER = 1;
    public static final int OLD_SHOPPING_SPECIFICS_FIELD_NUMBER = 5;
    private static volatile wv1<c32> PARSER = null;
    public static final int SHOPPING_SPECIFICS_FIELD_NUMBER = 6;
    public static final int TAGS_FIELD_NUMBER = 3;
    private int bitField0_;
    private c0.e<a> fallbackImages_;
    private a leadImage_;
    private zl2 shoppingSpecifics_;
    private c0.e<b> tags_;
    private int typeSpecificsCase_ = 0;
    private Object typeSpecifics_;

    /* JADX INFO: compiled from: PowerBookmarkMeta.java */
    public static final class a extends z<a, Object> implements bh1 {
        private static final a DEFAULT_INSTANCE;
        private static volatile wv1<a> PARSER = null;
        public static final int URL_FIELD_NUMBER = 1;
        private int bitField0_;
        private String url_ = "";

        static {
            a aVar = new a();
            DEFAULT_INSTANCE = aVar;
            z.M(a.class, aVar);
        }

        public static a O() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.z
        public final Object D(z.f fVar, Object obj, Object obj2) {
            switch (fVar.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return new a92(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"bitField0_", "url_"});
                case 3:
                    return new a();
                case 4:
                    return new z.a(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    wv1<a> bVar = PARSER;
                    if (bVar == null) {
                        synchronized (a.class) {
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

    /* JADX INFO: compiled from: PowerBookmarkMeta.java */
    public static final class b extends z<b, Object> implements bh1 {
        private static final b DEFAULT_INSTANCE;
        public static final int DISPLAY_NAME_FIELD_NUMBER = 1;
        private static volatile wv1<b> PARSER;
        private int bitField0_;
        private String displayName_ = "";

        static {
            b bVar = new b();
            DEFAULT_INSTANCE = bVar;
            z.M(b.class, bVar);
        }

        @Override // com.google.protobuf.z
        public final Object D(z.f fVar, Object obj, Object obj2) {
            switch (fVar.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return new a92(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"bitField0_", "displayName_"});
                case 3:
                    return new b();
                case 4:
                    return new z.a(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    wv1<b> bVar = PARSER;
                    if (bVar == null) {
                        synchronized (b.class) {
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

    static {
        c32 c32Var = new c32();
        DEFAULT_INSTANCE = c32Var;
        z.M(c32.class, c32Var);
    }

    public c32() {
        y0<Object> y0Var = y0.h;
        this.fallbackImages_ = y0Var;
        this.tags_ = y0Var;
    }

    public static c32 S(byte[] bArr) {
        return (c32) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0001\u0001\u0001\u0006\u0005\u0000\u0002\u0000\u0001ဉ\u0000\u0002\u001b\u0003\u001b\u0005ြ\u0000\u0006ဉ\u0001", new Object[]{"typeSpecifics_", "typeSpecificsCase_", "bitField0_", "leadImage_", "fallbackImages_", a.class, "tags_", b.class, zl2.class, "shoppingSpecifics_"});
            case 3:
                return new c32();
            case 4:
                return new z.a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                wv1<c32> bVar = PARSER;
                if (bVar == null) {
                    synchronized (c32.class) {
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

    public final a O() {
        a aVar = this.leadImage_;
        return aVar == null ? a.O() : aVar;
    }

    public final zl2 P() {
        zl2 zl2Var = this.shoppingSpecifics_;
        return zl2Var == null ? zl2.Q() : zl2Var;
    }

    public final boolean Q() {
        return (this.bitField0_ & 2) != 0;
    }
}
