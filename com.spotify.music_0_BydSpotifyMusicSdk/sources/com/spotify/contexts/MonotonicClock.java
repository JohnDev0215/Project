package com.spotify.contexts;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;
import p.yk1;

/* JADX INFO: loaded from: classes.dex */
public final class MonotonicClock extends i implements yj1 {
    private static final MonotonicClock DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int bitField0_;
    private long id_;
    private long value_;

    static {
        MonotonicClock monotonicClock = new MonotonicClock();
        DEFAULT_INSTANCE = monotonicClock;
        i.registerDefaultInstance(MonotonicClock.class, monotonicClock);
    }

    private MonotonicClock() {
    }

    public static void e(MonotonicClock monotonicClock, long j) {
        monotonicClock.bitField0_ |= 1;
        monotonicClock.id_ = j;
    }

    public static void f(MonotonicClock monotonicClock, long j) {
        monotonicClock.bitField0_ |= 2;
        monotonicClock.value_ = j;
    }

    public static yk1 g() {
        return (yk1) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001", new Object[]{"bitField0_", "id_", "value_"});
            case 3:
                return new MonotonicClock();
            case 4:
                return new yk1(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (MonotonicClock.class) {
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
