package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.n51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPlayOptions$PlayOptions extends i implements yj1 {
    private static final EsPlayOptions$PlayOptions DEFAULT_INSTANCE;
    public static final int OPERATION_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_SPEED_FIELD_NUMBER = 4;
    public static final int REASON_FIELD_NUMBER = 1;
    public static final int TRIGGER_FIELD_NUMBER = 3;
    private int bitField0_;
    private int operation_;
    private float playbackSpeed_;
    private int reason_;
    private int trigger_;

    public enum Operation implements n51 {
        REPLACE(0),
        ENQUEUE(1),
        PUSH(2),
        UNRECOGNIZED(-1);

        private final int value;

        Operation(int i) {
            this.value = i;
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public enum Trigger implements n51 {
        IMMEDIATELY(0),
        ADVANCED_PAST_TRACK(1),
        ADVANCED_PAST_CONTEXT(2),
        UNRECOGNIZED(-1);

        private final int value;

        Trigger(int i) {
            this.value = i;
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    static {
        EsPlayOptions$PlayOptions esPlayOptions$PlayOptions = new EsPlayOptions$PlayOptions();
        DEFAULT_INSTANCE = esPlayOptions$PlayOptions;
        i.registerDefaultInstance(EsPlayOptions$PlayOptions.class, esPlayOptions$PlayOptions);
    }

    private EsPlayOptions$PlayOptions() {
    }

    public static void e(EsPlayOptions$PlayOptions esPlayOptions$PlayOptions, Operation operation) {
        esPlayOptions$PlayOptions.getClass();
        esPlayOptions$PlayOptions.operation_ = operation.getNumber();
    }

    public static void f(EsPlayOptions$PlayOptions esPlayOptions$PlayOptions, Trigger trigger) {
        esPlayOptions$PlayOptions.getClass();
        esPlayOptions$PlayOptions.trigger_ = trigger.getNumber();
    }

    public static d g() {
        return (d) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f\u0004ခ\u0000", new Object[]{"bitField0_", "reason_", "operation_", "trigger_", "playbackSpeed_"});
            case 3:
                return new EsPlayOptions$PlayOptions();
            case 4:
                return new d(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPlayOptions$PlayOptions.class) {
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
