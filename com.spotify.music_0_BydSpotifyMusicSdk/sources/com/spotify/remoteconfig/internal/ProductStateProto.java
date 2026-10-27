package com.spotify.remoteconfig.internal;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ay1;
import p.by1;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateProto extends i implements yj1 {
    private static final ProductStateProto DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int VALUES_FIELD_NUMBER = 1;
    private MapFieldLite<String, AccountAttribute> values_ = MapFieldLite.c;

    static {
        ProductStateProto productStateProto = new ProductStateProto();
        DEFAULT_INSTANCE = productStateProto;
        i.registerDefaultInstance(ProductStateProto.class, productStateProto);
    }

    private ProductStateProto() {
    }

    public static MapFieldLite e(ProductStateProto productStateProto) {
        if (!productStateProto.values_.e()) {
            productStateProto.values_ = productStateProto.values_.g();
        }
        return productStateProto.values_;
    }

    public static ay1 f() {
        return (ay1) DEFAULT_INSTANCE.createBuilder();
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"values_", by1.a});
            case 3:
                return new ProductStateProto();
            case 4:
                return new ay1(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ProductStateProto.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
