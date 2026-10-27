package defpackage;

import com.google.protobuf.c0;
import com.google.protobuf.y0;
import com.google.protobuf.z;

/* JADX INFO: compiled from: VariationsSeedOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bl3 extends z<bl3, Object> implements bh1 {
    public static final int COUNTRY_CODE_FIELD_NUMBER = 3;
    private static final bl3 DEFAULT_INSTANCE;
    public static final int LAYERS_FIELD_NUMBER = 6;
    private static volatile wv1<bl3> PARSER = null;
    public static final int SERIAL_NUMBER_FIELD_NUMBER = 1;
    public static final int STUDY_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 4;
    private int bitField0_;
    private String countryCode_;
    private c0.e<v51> layers_;
    private byte memoizedIsInitialized = 2;
    private String serialNumber_ = "";
    private c0.e<mv2> study_;
    private String version_;

    static {
        bl3 bl3Var = new bl3();
        DEFAULT_INSTANCE = bl3Var;
        z.M(bl3.class, bl3Var);
    }

    public bl3() {
        y0<Object> y0Var = y0.h;
        this.study_ = y0Var;
        this.countryCode_ = "";
        this.version_ = "";
        this.layers_ = y0Var;
    }

    public static bl3 O(byte[] bArr) {
        return (bl3) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0002\u0001\u0001ဈ\u0000\u0002Л\u0003ဈ\u0001\u0004ဈ\u0002\u0006\u001b", new Object[]{"bitField0_", "serialNumber_", "study_", mv2.class, "countryCode_", "version_", "layers_", v51.class});
            case NEW_MUTABLE_INSTANCE:
                return new bl3();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<bl3> bVar = PARSER;
                if (bVar == null) {
                    synchronized (bl3.class) {
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
