package com.spotify.rcs.resolver.grpc.v0;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import p.ds1;
import p.gd0;
import p.i01;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ResolveResponse extends com.google.protobuf.i implements yj1 {
    public static final int CONFIGURATION_FIELD_NUMBER = 1;
    private static final ResolveResponse DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;
    private Configuration configuration_;

    static {
        ResolveResponse resolveResponse = new ResolveResponse();
        DEFAULT_INSTANCE = resolveResponse;
        com.google.protobuf.i.registerDefaultInstance(ResolveResponse.class, resolveResponse);
    }

    private ResolveResponse() {
    }

    public static ResolveResponse g() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        gd0 gd0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"configuration_"});
            case 3:
                return new ResolveResponse();
            case 4:
                return new qd0(gd0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ResolveResponse.class) {
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

    public final Configuration f() {
        Configuration configuration = this.configuration_;
        return configuration == null ? Configuration.g() : configuration;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final boolean h() {
        return this.configuration_ != null;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
