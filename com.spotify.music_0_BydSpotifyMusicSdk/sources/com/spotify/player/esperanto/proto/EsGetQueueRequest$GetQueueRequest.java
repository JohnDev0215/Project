package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.dd0;
import p.ds1;
import p.i01;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsGetQueueRequest$GetQueueRequest extends i implements yj1 {
    private static final EsGetQueueRequest$GetQueueRequest DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;

    static {
        EsGetQueueRequest$GetQueueRequest esGetQueueRequest$GetQueueRequest = new EsGetQueueRequest$GetQueueRequest();
        DEFAULT_INSTANCE = esGetQueueRequest$GetQueueRequest;
        i.registerDefaultInstance(EsGetQueueRequest$GetQueueRequest.class, esGetQueueRequest$GetQueueRequest);
    }

    private EsGetQueueRequest$GetQueueRequest() {
    }

    public static EsGetQueueRequest$GetQueueRequest f() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        dd0 dd0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
            case 3:
                return new EsGetQueueRequest$GetQueueRequest();
            case 4:
                return new qd0(dd0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsGetQueueRequest$GetQueueRequest.class) {
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
