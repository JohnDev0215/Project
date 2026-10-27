package com.spotify.remoteconfig.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.rg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsRemoteConfig$IntResponse extends i implements yj1 {
    private static final EsRemoteConfig$IntResponse DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private int bitField0_;
    private int value_;

    static {
        EsRemoteConfig$IntResponse esRemoteConfig$IntResponse = new EsRemoteConfig$IntResponse();
        DEFAULT_INSTANCE = esRemoteConfig$IntResponse;
        i.registerDefaultInstance(EsRemoteConfig$IntResponse.class, esRemoteConfig$IntResponse);
    }

    private EsRemoteConfig$IntResponse() {
    }

    public static void e(EsRemoteConfig$IntResponse esRemoteConfig$IntResponse, int i) {
        esRemoteConfig$IntResponse.bitField0_ |= 1;
        esRemoteConfig$IntResponse.value_ = i;
    }

    public static rg0 f() {
        return (rg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"bitField0_", "value_"});
            case 3:
                return new EsRemoteConfig$IntResponse();
            case 4:
                return new rg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsRemoteConfig$IntResponse.class) {
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
