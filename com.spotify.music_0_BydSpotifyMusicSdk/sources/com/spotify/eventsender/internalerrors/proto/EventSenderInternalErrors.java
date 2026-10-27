package com.spotify.eventsender.internalerrors.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import java.util.Collections;
import java.util.Map;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EventSenderInternalErrors extends i implements yj1 {
    private static final EventSenderInternalErrors DEFAULT_INSTANCE;
    public static final int ERROR_TO_COUNTS_MAP_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private MapFieldLite<String, Counts> errorToCountsMap_ = MapFieldLite.c;

    public static final class Counts extends i implements yj1 {
        private static final Counts DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int REPORTEDCOUNT_FIELD_NUMBER = 2;
        public static final int TOTALCOUNT_FIELD_NUMBER = 1;
        private long reportedCount_;
        private long totalCount_;

        static {
            Counts counts = new Counts();
            DEFAULT_INSTANCE = counts;
            i.registerDefaultInstance(Counts.class, counts);
        }

        private Counts() {
        }

        public static void e(Counts counts, long j) {
            counts.totalCount_ = j;
        }

        public static void f(Counts counts, long j) {
            counts.reportedCount_ = j;
        }

        public static Counts g() {
            return DEFAULT_INSTANCE;
        }

        public static b j() {
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0002", new Object[]{"totalCount_", "reportedCount_"});
                case 3:
                    return new Counts();
                case 4:
                    return new b(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (Counts.class) {
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

        public final long h() {
            return this.reportedCount_;
        }

        public final long i() {
            return this.totalCount_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    static {
        EventSenderInternalErrors eventSenderInternalErrors = new EventSenderInternalErrors();
        DEFAULT_INSTANCE = eventSenderInternalErrors;
        i.registerDefaultInstance(EventSenderInternalErrors.class, eventSenderInternalErrors);
    }

    private EventSenderInternalErrors() {
    }

    public static MapFieldLite e(EventSenderInternalErrors eventSenderInternalErrors) {
        if (!eventSenderInternalErrors.errorToCountsMap_.e()) {
            eventSenderInternalErrors.errorToCountsMap_ = eventSenderInternalErrors.errorToCountsMap_.g();
        }
        return eventSenderInternalErrors.errorToCountsMap_;
    }

    public static a g() {
        return (a) DEFAULT_INSTANCE.createBuilder();
    }

    public static EventSenderInternalErrors h(byte[] bArr) {
        return (EventSenderInternalErrors) i.parseFrom(DEFAULT_INSTANCE, bArr);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"errorToCountsMap_", c.a});
            case 3:
                return new EventSenderInternalErrors();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EventSenderInternalErrors.class) {
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

    public final Map f() {
        return Collections.unmodifiableMap(this.errorToCountsMap_);
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
