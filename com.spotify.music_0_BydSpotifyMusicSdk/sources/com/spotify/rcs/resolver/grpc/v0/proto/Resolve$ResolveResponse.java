package com.spotify.rcs.resolver.grpc.v0.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import p.ds1;
import p.i01;
import p.jd0;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Resolve$ResolveResponse extends com.google.protobuf.i implements yj1 {
    public static final int CONFIGURATION_FIELD_NUMBER = 1;
    private static final Resolve$ResolveResponse DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;
    private Resolve$Configuration configuration_;

    static {
        Resolve$ResolveResponse resolve$ResolveResponse = new Resolve$ResolveResponse();
        DEFAULT_INSTANCE = resolve$ResolveResponse;
        com.google.protobuf.i.registerDefaultInstance(Resolve$ResolveResponse.class, resolve$ResolveResponse);
    }

    private Resolve$ResolveResponse() {
    }

    public static Resolve$ResolveResponse g() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        jd0 jd0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"configuration_"});
            case 3:
                return new Resolve$ResolveResponse();
            case 4:
                return new qd0(jd0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Resolve$ResolveResponse.class) {
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

    public final Resolve$Configuration f() {
        Resolve$Configuration resolve$Configuration = this.configuration_;
        return resolve$Configuration == null ? Resolve$Configuration.e() : resolve$Configuration;
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
