package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import java.util.ArrayList;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wg0;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSetQueueRequest$SetQueueRequest extends i implements yj1 {
    private static final EsSetQueueRequest$SetQueueRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 5;
    public static final int NEXT_TRACKS_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int PREV_TRACKS_FIELD_NUMBER = 2;
    public static final int QUEUE_REVISION_FIELD_NUMBER = 3;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private long queueRevision_;
    private v51 nextTracks_ = i.emptyProtobufList();
    private v51 prevTracks_ = i.emptyProtobufList();

    static {
        EsSetQueueRequest$SetQueueRequest esSetQueueRequest$SetQueueRequest = new EsSetQueueRequest$SetQueueRequest();
        DEFAULT_INSTANCE = esSetQueueRequest$SetQueueRequest;
        i.registerDefaultInstance(EsSetQueueRequest$SetQueueRequest.class, esSetQueueRequest$SetQueueRequest);
    }

    private EsSetQueueRequest$SetQueueRequest() {
    }

    public static void e(EsSetQueueRequest$SetQueueRequest esSetQueueRequest$SetQueueRequest, ArrayList arrayList) {
        v51 v51Var = esSetQueueRequest$SetQueueRequest.prevTracks_;
        if (!((a2) v51Var).c) {
            esSetQueueRequest$SetQueueRequest.prevTracks_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) esSetQueueRequest$SetQueueRequest.prevTracks_);
    }

    public static void f(EsSetQueueRequest$SetQueueRequest esSetQueueRequest$SetQueueRequest, long j) {
        esSetQueueRequest$SetQueueRequest.queueRevision_ = j;
    }

    public static void g(EsSetQueueRequest$SetQueueRequest esSetQueueRequest$SetQueueRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esSetQueueRequest$SetQueueRequest.getClass();
        esSetQueueRequest$SetQueueRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void h(EsSetQueueRequest$SetQueueRequest esSetQueueRequest$SetQueueRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esSetQueueRequest$SetQueueRequest.getClass();
        esSetQueueRequest$SetQueueRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static void i(EsSetQueueRequest$SetQueueRequest esSetQueueRequest$SetQueueRequest, ArrayList arrayList) {
        v51 v51Var = esSetQueueRequest$SetQueueRequest.nextTracks_;
        if (!((a2) v51Var).c) {
            esSetQueueRequest$SetQueueRequest.nextTracks_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) esSetQueueRequest$SetQueueRequest.nextTracks_);
    }

    public static wg0 j() {
        return (wg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003\u0003\u0004\t\u0005\t", new Object[]{"nextTracks_", EsProvidedTrack$ProvidedTrack.class, "prevTracks_", EsProvidedTrack$ProvidedTrack.class, "queueRevision_", "options_", "loggingParams_"});
            case 3:
                return new EsSetQueueRequest$SetQueueRequest();
            case 4:
                return new wg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsSetQueueRequest$SetQueueRequest.class) {
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
