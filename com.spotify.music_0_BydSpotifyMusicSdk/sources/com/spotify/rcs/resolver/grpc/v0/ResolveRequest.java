package com.spotify.rcs.resolver.grpc.v0;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.y32;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ResolveRequest extends com.google.protobuf.i implements yj1 {
    public static final int CONTEXT_FIELD_NUMBER = 11;
    private static final ResolveRequest DEFAULT_INSTANCE;
    public static final int FETCH_TYPE_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PROPERTY_SET_ID_FIELD_NUMBER = 1;
    private Context context_;
    private Fetch fetchType_;
    private String propertySetId_ = "";

    static {
        ResolveRequest resolveRequest = new ResolveRequest();
        DEFAULT_INSTANCE = resolveRequest;
        com.google.protobuf.i.registerDefaultInstance(ResolveRequest.class, resolveRequest);
    }

    private ResolveRequest() {
    }

    public static void e(ResolveRequest resolveRequest) {
        resolveRequest.getClass();
        resolveRequest.propertySetId_ = "a296f6a0c8d02612625a2cce0a87cd4903b3a030b8d046935f1922a441cd30df";
    }

    public static void f(ResolveRequest resolveRequest, Fetch fetch) {
        resolveRequest.getClass();
        resolveRequest.fetchType_ = fetch;
    }

    public static void g(ResolveRequest resolveRequest, Context context) {
        resolveRequest.getClass();
        context.getClass();
        resolveRequest.context_ = context;
    }

    public static y32 h() {
        return (y32) DEFAULT_INSTANCE.createBuilder();
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
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\t\u000b\t", new Object[]{"propertySetId_", "fetchType_", "context_"});
            case 3:
                return new ResolveRequest();
            case 4:
                return new y32(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ResolveRequest.class) {
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
