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
public final class EsContextPlayerState$PlaybackQuality extends i implements yj1 {
    public static final int BITRATE_LEVEL_FIELD_NUMBER = 1;
    private static final EsContextPlayerState$PlaybackQuality DEFAULT_INSTANCE;
    public static final int HIFI_STATUS_FIELD_NUMBER = 5;
    private static volatile ds1 PARSER = null;
    public static final int STRATEGY_FIELD_NUMBER = 2;
    public static final int TARGET_BITRATE_AVAILABLE_FIELD_NUMBER = 4;
    public static final int TARGET_BITRATE_LEVEL_FIELD_NUMBER = 3;
    private int bitrateLevel_;
    private int hifiStatus_;
    private int strategy_;
    private boolean targetBitrateAvailable_;
    private int targetBitrateLevel_;

    public enum BitrateLevel implements n51 {
        UNKNOWN(0),
        LOW(1),
        NORMAL(2),
        HIGH(3),
        VERY_HIGH(4),
        HIFI(5),
        HIFI24(6),
        UNRECOGNIZED(-1);

        private final int value;

        BitrateLevel(int i) {
            this.value = i;
        }

        public static BitrateLevel a(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return LOW;
                case 2:
                    return NORMAL;
                case 3:
                    return HIGH;
                case 4:
                    return VERY_HIGH;
                case 5:
                    return HIFI;
                case 6:
                    return HIFI24;
                default:
                    return null;
            }
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public enum BitrateStrategy implements n51 {
        UNKNOWN_STRATEGY(0),
        BEST_MATCHING(1),
        BACKEND_ADVISED(2),
        OFFLINED_FILE(3),
        CACHED_FILE(4),
        LOCAL_FILE(5),
        UNRECOGNIZED(-1);

        private final int value;

        BitrateStrategy(int i) {
            this.value = i;
        }

        public static BitrateStrategy a(int i) {
            if (i == 0) {
                return UNKNOWN_STRATEGY;
            }
            if (i == 1) {
                return BEST_MATCHING;
            }
            if (i == 2) {
                return BACKEND_ADVISED;
            }
            if (i == 3) {
                return OFFLINED_FILE;
            }
            if (i == 4) {
                return CACHED_FILE;
            }
            if (i != 5) {
                return null;
            }
            return LOCAL_FILE;
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public enum HiFiStatus implements n51 {
        NONE(0),
        OFF(1),
        ON(2),
        UNRECOGNIZED(-1);

        private final int value;

        HiFiStatus(int i) {
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
        EsContextPlayerState$PlaybackQuality esContextPlayerState$PlaybackQuality = new EsContextPlayerState$PlaybackQuality();
        DEFAULT_INSTANCE = esContextPlayerState$PlaybackQuality;
        i.registerDefaultInstance(EsContextPlayerState$PlaybackQuality.class, esContextPlayerState$PlaybackQuality);
    }

    private EsContextPlayerState$PlaybackQuality() {
    }

    public static EsContextPlayerState$PlaybackQuality f() {
        return DEFAULT_INSTANCE;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f\u0004\u0007\u0005\f", new Object[]{"bitrateLevel_", "strategy_", "targetBitrateLevel_", "targetBitrateAvailable_", "hifiStatus_"});
            case 3:
                return new EsContextPlayerState$PlaybackQuality();
            case 4:
                return new c(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPlayerState$PlaybackQuality.class) {
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

    public final BitrateLevel e() {
        BitrateLevel bitrateLevelA = BitrateLevel.a(this.bitrateLevel_);
        return bitrateLevelA == null ? BitrateLevel.UNRECOGNIZED : bitrateLevelA;
    }

    public final HiFiStatus g() {
        int i = this.hifiStatus_;
        HiFiStatus hiFiStatus = i != 0 ? i != 1 ? i != 2 ? null : HiFiStatus.ON : HiFiStatus.OFF : HiFiStatus.NONE;
        return hiFiStatus == null ? HiFiStatus.UNRECOGNIZED : hiFiStatus;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final BitrateStrategy h() {
        BitrateStrategy bitrateStrategyA = BitrateStrategy.a(this.strategy_);
        return bitrateStrategyA == null ? BitrateStrategy.UNRECOGNIZED : bitrateStrategyA;
    }

    public final boolean i() {
        return this.targetBitrateAvailable_;
    }

    public final BitrateLevel j() {
        BitrateLevel bitrateLevelA = BitrateLevel.a(this.targetBitrateLevel_);
        return bitrateLevelA == null ? BitrateLevel.UNRECOGNIZED : bitrateLevelA;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
