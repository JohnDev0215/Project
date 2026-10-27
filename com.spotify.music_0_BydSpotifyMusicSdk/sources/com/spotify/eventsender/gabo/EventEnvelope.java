package com.spotify.eventsender.gabo;

import com.google.protobuf.ByteString;
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

/* JADX INFO: loaded from: classes.dex */
public final class EventEnvelope extends i implements yj1 {
    private static final EventEnvelope DEFAULT_INSTANCE;
    public static final int EVENT_FRAGMENT_FIELD_NUMBER = 3;
    public static final int EVENT_NAME_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SEQUENCE_ID_FIELD_NUMBER = 4;
    public static final int SEQUENCE_NUMBER_FIELD_NUMBER = 5;
    private long sequenceNumber_;
    private String eventName_ = "";
    private v51 eventFragment_ = i.emptyProtobufList();
    private ByteString sequenceId_ = ByteString.c;

    public static final class EventFragment extends i implements yj1 {
        public static final int DATA_FIELD_NUMBER = 2;
        private static final EventFragment DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private String name_ = "";
        private ByteString data_ = ByteString.c;

        static {
            EventFragment eventFragment = new EventFragment();
            DEFAULT_INSTANCE = eventFragment;
            i.registerDefaultInstance(EventFragment.class, eventFragment);
        }

        private EventFragment() {
        }

        public static void e(EventFragment eventFragment, String str) {
            eventFragment.getClass();
            str.getClass();
            eventFragment.name_ = str;
        }

        public static void f(EventFragment eventFragment, ByteString byteString) {
            eventFragment.getClass();
            byteString.getClass();
            eventFragment.data_ = byteString;
        }

        public static b g() {
            return (b) DEFAULT_INSTANCE.createBuilder();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"name_", "data_"});
                case 3:
                    return new EventFragment();
                case 4:
                    return new b(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (EventFragment.class) {
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

    static {
        EventEnvelope eventEnvelope = new EventEnvelope();
        DEFAULT_INSTANCE = eventEnvelope;
        i.registerDefaultInstance(EventEnvelope.class, eventEnvelope);
    }

    private EventEnvelope() {
    }

    public static void e(EventEnvelope eventEnvelope, ArrayList arrayList) {
        v51 v51Var = eventEnvelope.eventFragment_;
        if (!((a2) v51Var).c) {
            eventEnvelope.eventFragment_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) eventEnvelope.eventFragment_);
    }

    public static void f(EventEnvelope eventEnvelope, ByteString byteString) {
        eventEnvelope.getClass();
        byteString.getClass();
        eventEnvelope.sequenceId_ = byteString;
    }

    public static void g(EventEnvelope eventEnvelope, long j) {
        eventEnvelope.sequenceNumber_ = j;
    }

    public static void h(EventEnvelope eventEnvelope, String str) {
        eventEnvelope.getClass();
        str.getClass();
        eventEnvelope.eventName_ = str;
    }

    public static a i() {
        return (a) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0002\u0005\u0004\u0000\u0001\u0000\u0002Ȉ\u0003\u001b\u0004\n\u0005\u0002", new Object[]{"eventName_", "eventFragment_", EventFragment.class, "sequenceId_", "sequenceNumber_"});
            case 3:
                return new EventEnvelope();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EventEnvelope.class) {
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
