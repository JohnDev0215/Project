package com.spotify.remoteconfig.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.ld0;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsRemoteConfig$LookupRequest extends i implements yj1 {
    private static final EsRemoteConfig$LookupRequest DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SCOPE_FIELD_NUMBER = 1;
    private String scope_ = "";
    private String name_ = "";

    static {
        EsRemoteConfig$LookupRequest esRemoteConfig$LookupRequest = new EsRemoteConfig$LookupRequest();
        DEFAULT_INSTANCE = esRemoteConfig$LookupRequest;
        i.registerDefaultInstance(EsRemoteConfig$LookupRequest.class, esRemoteConfig$LookupRequest);
    }

    private EsRemoteConfig$LookupRequest() {
    }

    public static EsRemoteConfig$LookupRequest g(byte[] bArr) {
        return (EsRemoteConfig$LookupRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        ld0 ld0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"scope_", "name_"});
            case 3:
                return new EsRemoteConfig$LookupRequest();
            case 4:
                return new qd0(ld0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsRemoteConfig$LookupRequest.class) {
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

    public final String f() {
        return this.scope_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final String getName() {
        return this.name_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
