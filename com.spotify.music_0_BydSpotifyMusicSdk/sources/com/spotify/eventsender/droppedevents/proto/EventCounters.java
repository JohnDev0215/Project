package com.spotify.eventsender.droppedevents.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import p.a2;
import p.ds1;
import p.i01;
import p.u51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EventCounters extends i implements yj1 {
    private static final EventCounters DEFAULT_INSTANCE;
    public static final int EVENT_NAME_TO_COUNTERS_MAP_FIELD_NUMBER = 2;
    public static final int FILE_FORMAT_VERSION_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private MapFieldLite<String, Counters> eventNameToCountersMap_ = MapFieldLite.c;
    private int fileFormatVersion_;

    public static final class Counters extends i implements yj1 {
        public static final int COUNTERS_FIELD_NUMBER = 1;
        private static final Counters DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private int countersMemoizedSerializedSize = -1;
        private u51 counters_ = i.emptyLongList();

        static {
            Counters counters = new Counters();
            DEFAULT_INSTANCE = counters;
            i.registerDefaultInstance(Counters.class, counters);
        }

        private Counters() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static void e(Counters counters, ArrayList arrayList) {
            u51 u51Var = counters.counters_;
            if (!((a2) u51Var).c) {
                counters.counters_ = i.mutableCopy(u51Var);
            }
            com.google.protobuf.a.addAll((Iterable) arrayList, (List) counters.counters_);
        }

        public static Counters g() {
            return DEFAULT_INSTANCE;
        }

        public static b h() {
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001%", new Object[]{"counters_"});
                case 3:
                    return new Counters();
                case 4:
                    return new b(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (Counters.class) {
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

        public final u51 f() {
            return this.counters_;
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
        EventCounters eventCounters = new EventCounters();
        DEFAULT_INSTANCE = eventCounters;
        i.registerDefaultInstance(EventCounters.class, eventCounters);
    }

    private EventCounters() {
    }

    public static void e(EventCounters eventCounters) {
        eventCounters.fileFormatVersion_ = 3;
    }

    public static MapFieldLite f(EventCounters eventCounters) {
        if (!eventCounters.eventNameToCountersMap_.e()) {
            eventCounters.eventNameToCountersMap_ = eventCounters.eventNameToCountersMap_.g();
        }
        return eventCounters.eventNameToCountersMap_;
    }

    public static a i() {
        return (a) DEFAULT_INSTANCE.createBuilder();
    }

    public static EventCounters j(byte[] bArr) {
        return (EventCounters) i.parseFrom(DEFAULT_INSTANCE, bArr);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0001\u0004\u00022", new Object[]{"fileFormatVersion_", "eventNameToCountersMap_", c.a});
            case 3:
                return new EventCounters();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EventCounters.class) {
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

    public final Map g() {
        return Collections.unmodifiableMap(this.eventNameToCountersMap_);
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final int h() {
        return this.fileFormatVersion_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
