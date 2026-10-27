package com.spotify.eventsender.gabo;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import java.util.ArrayList;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;
import p.zy1;

/* JADX INFO: loaded from: classes.dex */
public final class PublishEventsRequest extends i implements yj1 {
    private static final PublishEventsRequest DEFAULT_INSTANCE;
    public static final int EVENT_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int SUPPRESS_PERSIST_FIELD_NUMBER = 2;
    private v51 event_ = i.emptyProtobufList();
    private boolean suppressPersist_;

    static {
        PublishEventsRequest publishEventsRequest = new PublishEventsRequest();
        DEFAULT_INSTANCE = publishEventsRequest;
        i.registerDefaultInstance(PublishEventsRequest.class, publishEventsRequest);
    }

    private PublishEventsRequest() {
    }

    public static void e(PublishEventsRequest publishEventsRequest, EventEnvelope eventEnvelope) {
        publishEventsRequest.getClass();
        v51 v51Var = publishEventsRequest.event_;
        if (!((a2) v51Var).c) {
            publishEventsRequest.event_ = i.mutableCopy(v51Var);
        }
        publishEventsRequest.event_.add(eventEnvelope);
    }

    public static void f(PublishEventsRequest publishEventsRequest, ArrayList arrayList) {
        v51 v51Var = publishEventsRequest.event_;
        if (!((a2) v51Var).c) {
            publishEventsRequest.event_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) publishEventsRequest.event_);
    }

    public static zy1 g() {
        return (zy1) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0007", new Object[]{"event_", EventEnvelope.class, "suppressPersist_"});
            case 3:
                return new PublishEventsRequest();
            case 4:
                return new zy1(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (PublishEventsRequest.class) {
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
