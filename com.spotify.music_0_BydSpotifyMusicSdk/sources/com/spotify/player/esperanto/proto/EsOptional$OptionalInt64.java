package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.cg0;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsOptional$OptionalInt64 extends i implements yj1 {
    private static final EsOptional$OptionalInt64 DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private long value_;

    static {
        EsOptional$OptionalInt64 esOptional$OptionalInt64 = new EsOptional$OptionalInt64();
        DEFAULT_INSTANCE = esOptional$OptionalInt64;
        i.registerDefaultInstance(EsOptional$OptionalInt64.class, esOptional$OptionalInt64);
    }

    private EsOptional$OptionalInt64() {
    }

    public static void e(EsOptional$OptionalInt64 esOptional$OptionalInt64, long j) {
        esOptional$OptionalInt64.value_ = j;
    }

    public static EsOptional$OptionalInt64 f() {
        return DEFAULT_INSTANCE;
    }

    public static cg0 h() {
        return (cg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0002", new Object[]{"value_"});
            case 3:
                return new EsOptional$OptionalInt64();
            case 4:
                return new cg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsOptional$OptionalInt64.class) {
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

    public final long g() {
        return this.value_;
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
