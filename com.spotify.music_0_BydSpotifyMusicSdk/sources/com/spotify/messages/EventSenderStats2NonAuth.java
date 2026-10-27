package com.spotify.messages;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.util.List;
import java.util.TreeSet;
import p.a2;
import p.ds1;
import p.e51;
import p.i01;
import p.r51;
import p.tj0;
import p.u51;
import p.v51;
import p.vd1;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EventSenderStats2NonAuth extends i implements yj1 {
    private static final EventSenderStats2NonAuth DEFAULT_INSTANCE;
    public static final int DROP_LIST_COUNTS_TOTAL_FIELD_NUMBER = 12;
    public static final int DROP_LIST_COUNTS_UNREPORTED_FIELD_NUMBER = 13;
    public static final int DROP_LIST_EVENT_NAME_INDEX_FIELD_NUMBER = 11;
    public static final int DROP_LIST_NUM_ENTRIES_PER_SEQUENCE_ID_FIELD_NUMBER = 10;
    public static final int EVENT_NAMES_FIELD_NUMBER = 2;
    public static final int LOSS_STATS_EVENT_NAME_INDEX_FIELD_NUMBER = 4;
    public static final int LOSS_STATS_NUM_ENTRIES_PER_SEQUENCE_ID_FIELD_NUMBER = 3;
    public static final int LOSS_STATS_SEQUENCE_NUMBER_MINS_FIELD_NUMBER = 6;
    public static final int LOSS_STATS_SEQUENCE_NUMBER_NEXTS_FIELD_NUMBER = 7;
    public static final int LOSS_STATS_STORAGE_SIZES_FIELD_NUMBER = 5;
    private static volatile ds1 PARSER = null;
    public static final int RATELIMITER_STATS_DROP_COUNT_FIELD_NUMBER = 9;
    public static final int RATELIMITER_STATS_EVENT_NAME_INDEX_FIELD_NUMBER = 8;
    public static final int SEQUENCE_IDS_FIELD_NUMBER = 1;
    public static final int STATES_LIST_DELETED_COUNTS_TOTAL_FIELD_NUMBER = 22;
    public static final int STATES_LIST_DELIVERED_COUNTS_TOTAL_FIELD_NUMBER = 21;
    public static final int STATES_LIST_DROPPED_COUNTS_TOTAL_FIELD_NUMBER = 18;
    public static final int STATES_LIST_ENTERED_COUNTS_TOTAL_FIELD_NUMBER = 16;
    public static final int STATES_LIST_EVENT_NAME_INDEX_FIELD_NUMBER = 15;
    public static final int STATES_LIST_PERSISTED_COUNTS_TOTAL_FIELD_NUMBER = 19;
    public static final int STATES_LIST_REJECTED_BACKEND_COUNTS_TOTAL_FIELD_NUMBER = 20;
    public static final int STATES_LIST_REJECTED_CLIENT_COUNTS_TOTAL_FIELD_NUMBER = 17;
    private v51 sequenceIds_ = i.emptyProtobufList();
    private v51 eventNames_ = i.emptyProtobufList();
    private r51 lossStatsNumEntriesPerSequenceId_ = i.emptyIntList();
    private r51 lossStatsEventNameIndex_ = i.emptyIntList();
    private u51 lossStatsStorageSizes_ = i.emptyLongList();
    private u51 lossStatsSequenceNumberMins_ = i.emptyLongList();
    private u51 lossStatsSequenceNumberNexts_ = i.emptyLongList();
    private r51 ratelimiterStatsEventNameIndex_ = i.emptyIntList();
    private u51 ratelimiterStatsDropCount_ = i.emptyLongList();
    private r51 dropListNumEntriesPerSequenceId_ = i.emptyIntList();
    private r51 dropListEventNameIndex_ = i.emptyIntList();
    private u51 dropListCountsTotal_ = i.emptyLongList();
    private u51 dropListCountsUnreported_ = i.emptyLongList();
    private r51 statesListEventNameIndex_ = i.emptyIntList();
    private u51 statesListEnteredCountsTotal_ = i.emptyLongList();
    private u51 statesListRejectedClientCountsTotal_ = i.emptyLongList();
    private u51 statesListDroppedCountsTotal_ = i.emptyLongList();
    private u51 statesListPersistedCountsTotal_ = i.emptyLongList();
    private u51 statesListRejectedBackendCountsTotal_ = i.emptyLongList();
    private u51 statesListDeliveredCountsTotal_ = i.emptyLongList();
    private u51 statesListDeletedCountsTotal_ = i.emptyLongList();

    static {
        EventSenderStats2NonAuth eventSenderStats2NonAuth = new EventSenderStats2NonAuth();
        DEFAULT_INSTANCE = eventSenderStats2NonAuth;
        i.registerDefaultInstance(EventSenderStats2NonAuth.class, eventSenderStats2NonAuth);
    }

    private EventSenderStats2NonAuth() {
    }

    public static tj0 R() {
        return (tj0) DEFAULT_INSTANCE.createBuilder();
    }

    public static EventSenderStats2NonAuth S(ByteString byteString) {
        return (EventSenderStats2NonAuth) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void e(EventSenderStats2NonAuth eventSenderStats2NonAuth, int i) {
        r51 r51Var = eventSenderStats2NonAuth.lossStatsNumEntriesPerSequenceId_;
        if (!((a2) r51Var).c) {
            eventSenderStats2NonAuth.lossStatsNumEntriesPerSequenceId_ = i.mutableCopy(r51Var);
        }
        ((e51) eventSenderStats2NonAuth.lossStatsNumEntriesPerSequenceId_).c(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void f(EventSenderStats2NonAuth eventSenderStats2NonAuth, int i) {
        r51 r51Var = eventSenderStats2NonAuth.lossStatsEventNameIndex_;
        if (!((a2) r51Var).c) {
            eventSenderStats2NonAuth.lossStatsEventNameIndex_ = i.mutableCopy(r51Var);
        }
        ((e51) eventSenderStats2NonAuth.lossStatsEventNameIndex_).c(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void g(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.lossStatsStorageSizes_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.lossStatsStorageSizes_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.lossStatsStorageSizes_).c(j);
    }

    public static void h(EventSenderStats2NonAuth eventSenderStats2NonAuth, ByteString byteString) {
        eventSenderStats2NonAuth.getClass();
        v51 v51Var = eventSenderStats2NonAuth.sequenceIds_;
        if (!((a2) v51Var).c) {
            eventSenderStats2NonAuth.sequenceIds_ = i.mutableCopy(v51Var);
        }
        eventSenderStats2NonAuth.sequenceIds_.add(byteString);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void i(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.lossStatsSequenceNumberMins_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.lossStatsSequenceNumberMins_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.lossStatsSequenceNumberMins_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void j(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.lossStatsSequenceNumberNexts_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.lossStatsSequenceNumberNexts_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.lossStatsSequenceNumberNexts_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void k(EventSenderStats2NonAuth eventSenderStats2NonAuth, int i) {
        r51 r51Var = eventSenderStats2NonAuth.ratelimiterStatsEventNameIndex_;
        if (!((a2) r51Var).c) {
            eventSenderStats2NonAuth.ratelimiterStatsEventNameIndex_ = i.mutableCopy(r51Var);
        }
        ((e51) eventSenderStats2NonAuth.ratelimiterStatsEventNameIndex_).c(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void l(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.ratelimiterStatsDropCount_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.ratelimiterStatsDropCount_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.ratelimiterStatsDropCount_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void m(EventSenderStats2NonAuth eventSenderStats2NonAuth, int i) {
        r51 r51Var = eventSenderStats2NonAuth.statesListEventNameIndex_;
        if (!((a2) r51Var).c) {
            eventSenderStats2NonAuth.statesListEventNameIndex_ = i.mutableCopy(r51Var);
        }
        ((e51) eventSenderStats2NonAuth.statesListEventNameIndex_).c(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void n(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListEnteredCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListEnteredCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListEnteredCountsTotal_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void o(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListRejectedClientCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListRejectedClientCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListRejectedClientCountsTotal_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void p(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListDroppedCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListDroppedCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListDroppedCountsTotal_).c(j);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(EventSenderStats2NonAuth eventSenderStats2NonAuth, TreeSet treeSet) {
        v51 v51Var = eventSenderStats2NonAuth.eventNames_;
        if (!((a2) v51Var).c) {
            eventSenderStats2NonAuth.eventNames_ = i.mutableCopy(v51Var);
        }
        a.addAll((Iterable) treeSet, (List) eventSenderStats2NonAuth.eventNames_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void r(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListPersistedCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListPersistedCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListPersistedCountsTotal_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void s(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListRejectedBackendCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListRejectedBackendCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListRejectedBackendCountsTotal_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void t(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListDeliveredCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListDeliveredCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListDeliveredCountsTotal_).c(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void u(EventSenderStats2NonAuth eventSenderStats2NonAuth, long j) {
        u51 u51Var = eventSenderStats2NonAuth.statesListDeletedCountsTotal_;
        if (!((a2) u51Var).c) {
            eventSenderStats2NonAuth.statesListDeletedCountsTotal_ = i.mutableCopy(u51Var);
        }
        ((vd1) eventSenderStats2NonAuth.statesListDeletedCountsTotal_).c(j);
    }

    public final u51 A() {
        return this.lossStatsSequenceNumberMins_;
    }

    public final u51 B() {
        return this.lossStatsSequenceNumberNexts_;
    }

    public final u51 C() {
        return this.lossStatsStorageSizes_;
    }

    public final int D() {
        return ((vd1) this.ratelimiterStatsDropCount_).k;
    }

    public final u51 E() {
        return this.ratelimiterStatsDropCount_;
    }

    public final r51 F() {
        return this.ratelimiterStatsEventNameIndex_;
    }

    public final int G() {
        return this.sequenceIds_.size();
    }

    public final v51 H() {
        return this.sequenceIds_;
    }

    public final u51 I() {
        return this.statesListDeletedCountsTotal_;
    }

    public final u51 J() {
        return this.statesListDeliveredCountsTotal_;
    }

    public final u51 K() {
        return this.statesListDroppedCountsTotal_;
    }

    public final u51 L() {
        return this.statesListEnteredCountsTotal_;
    }

    public final int M() {
        return ((e51) this.statesListEventNameIndex_).k;
    }

    public final r51 N() {
        return this.statesListEventNameIndex_;
    }

    public final u51 O() {
        return this.statesListPersistedCountsTotal_;
    }

    public final u51 P() {
        return this.statesListRejectedBackendCountsTotal_;
    }

    public final u51 Q() {
        return this.statesListRejectedClientCountsTotal_;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0015\u0000\u0000\u0001\u0016\u0015\u0000\u0015\u0000\u0001\u001c\u0002\u001a\u0003\u0016\u0004\u0016\u0005\u0014\u0006\u0014\u0007\u0014\b\u0016\t\u0014\n\u0016\u000b\u0016\f\u0014\r\u0014\u000f\u0016\u0010\u0014\u0011\u0014\u0012\u0014\u0013\u0014\u0014\u0014\u0015\u0014\u0016\u0014", new Object[]{"sequenceIds_", "eventNames_", "lossStatsNumEntriesPerSequenceId_", "lossStatsEventNameIndex_", "lossStatsStorageSizes_", "lossStatsSequenceNumberMins_", "lossStatsSequenceNumberNexts_", "ratelimiterStatsEventNameIndex_", "ratelimiterStatsDropCount_", "dropListNumEntriesPerSequenceId_", "dropListEventNameIndex_", "dropListCountsTotal_", "dropListCountsUnreported_", "statesListEventNameIndex_", "statesListEnteredCountsTotal_", "statesListRejectedClientCountsTotal_", "statesListDroppedCountsTotal_", "statesListPersistedCountsTotal_", "statesListRejectedBackendCountsTotal_", "statesListDeliveredCountsTotal_", "statesListDeletedCountsTotal_"});
            case 3:
                return new EventSenderStats2NonAuth();
            case 4:
                return new tj0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EventSenderStats2NonAuth.class) {
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

    public final int v() {
        return ((vd1) this.dropListCountsTotal_).k;
    }

    public final v51 w() {
        return this.eventNames_;
    }

    public final r51 x() {
        return this.lossStatsEventNameIndex_;
    }

    public final int y() {
        return ((e51) this.lossStatsNumEntriesPerSequenceId_).k;
    }

    public final r51 z() {
        return this.lossStatsNumEntriesPerSequenceId_;
    }
}
