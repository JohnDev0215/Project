package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;
import p.zf0;

/* JADX INFO: loaded from: classes.dex */
public final class EsGetStateRequest$GetStateRequest extends i implements yj1 {
    private static final EsGetStateRequest$GetStateRequest DEFAULT_INSTANCE;
    public static final int NEXT_TRACKS_CAP_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PREV_TRACKS_CAP_FIELD_NUMBER = 1;
    private EsOptional$OptionalInt64 nextTracksCap_;
    private EsOptional$OptionalInt64 prevTracksCap_;

    static {
        EsGetStateRequest$GetStateRequest esGetStateRequest$GetStateRequest = new EsGetStateRequest$GetStateRequest();
        DEFAULT_INSTANCE = esGetStateRequest$GetStateRequest;
        i.registerDefaultInstance(EsGetStateRequest$GetStateRequest.class, esGetStateRequest$GetStateRequest);
    }

    private EsGetStateRequest$GetStateRequest() {
    }

    public static void e(EsGetStateRequest$GetStateRequest esGetStateRequest$GetStateRequest, EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        esGetStateRequest$GetStateRequest.getClass();
        esOptional$OptionalInt64.getClass();
        esGetStateRequest$GetStateRequest.prevTracksCap_ = esOptional$OptionalInt64;
    }

    public static void f(EsGetStateRequest$GetStateRequest esGetStateRequest$GetStateRequest, EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        esGetStateRequest$GetStateRequest.getClass();
        esOptional$OptionalInt64.getClass();
        esGetStateRequest$GetStateRequest.nextTracksCap_ = esOptional$OptionalInt64;
    }

    public static zf0 g() {
        return (zf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"prevTracksCap_", "nextTracksCap_"});
            case 3:
                return new EsGetStateRequest$GetStateRequest();
            case 4:
                return new zf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsGetStateRequest$GetStateRequest.class) {
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
