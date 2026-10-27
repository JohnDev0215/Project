package com.spotify.rcs.resolver.grpc.v0.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.spotify.rcs.admin.grpc.v0.proto.Publish$BackendContext;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.x32;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Resolve$ResolveRequest extends com.google.protobuf.i implements yj1 {
    public static final int BACKEND_CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTEXT_FIELD_NUMBER = 11;
    private static final Resolve$ResolveRequest DEFAULT_INSTANCE;
    public static final int FETCH_TYPE_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PROPERTY_SET_ID_FIELD_NUMBER = 1;
    private Resolve$Context context_;
    private Resolve$Fetch fetchType_;
    private Object resolutionContext_;
    private int resolutionContextCase_ = 0;
    private String propertySetId_ = "";

    static {
        Resolve$ResolveRequest resolve$ResolveRequest = new Resolve$ResolveRequest();
        DEFAULT_INSTANCE = resolve$ResolveRequest;
        com.google.protobuf.i.registerDefaultInstance(Resolve$ResolveRequest.class, resolve$ResolveRequest);
    }

    private Resolve$ResolveRequest() {
    }

    public static void e(Resolve$ResolveRequest resolve$ResolveRequest, String str) {
        resolve$ResolveRequest.getClass();
        str.getClass();
        resolve$ResolveRequest.propertySetId_ = str;
    }

    public static void f(Resolve$ResolveRequest resolve$ResolveRequest, Resolve$Fetch resolve$Fetch) {
        resolve$ResolveRequest.getClass();
        resolve$Fetch.getClass();
        resolve$ResolveRequest.fetchType_ = resolve$Fetch;
    }

    public static void g(Resolve$ResolveRequest resolve$ResolveRequest, Resolve$Context resolve$Context) {
        resolve$ResolveRequest.getClass();
        resolve$Context.getClass();
        resolve$ResolveRequest.context_ = resolve$Context;
    }

    public static x32 h() {
        return (x32) DEFAULT_INSTANCE.createBuilder();
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
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\f\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\t\u000b\t\f<\u0000", new Object[]{"resolutionContext_", "resolutionContextCase_", "propertySetId_", "fetchType_", "context_", Publish$BackendContext.class});
            case 3:
                return new Resolve$ResolveRequest();
            case 4:
                return new x32(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Resolve$ResolveRequest.class) {
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
