package com.spotify.remoteconfig.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.qg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsRemoteConfig$EnumResponse extends i implements yj1 {
    private static final EsRemoteConfig$EnumResponse DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private int bitField0_;
    private String value_ = "";

    static {
        EsRemoteConfig$EnumResponse esRemoteConfig$EnumResponse = new EsRemoteConfig$EnumResponse();
        DEFAULT_INSTANCE = esRemoteConfig$EnumResponse;
        i.registerDefaultInstance(EsRemoteConfig$EnumResponse.class, esRemoteConfig$EnumResponse);
    }

    private EsRemoteConfig$EnumResponse() {
    }

    public static void e(EsRemoteConfig$EnumResponse esRemoteConfig$EnumResponse, String str) {
        esRemoteConfig$EnumResponse.getClass();
        esRemoteConfig$EnumResponse.bitField0_ |= 1;
        esRemoteConfig$EnumResponse.value_ = str;
    }

    public static qg0 f() {
        return (qg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ለ\u0000", new Object[]{"bitField0_", "value_"});
            case 3:
                return new EsRemoteConfig$EnumResponse();
            case 4:
                return new qg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsRemoteConfig$EnumResponse.class) {
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
