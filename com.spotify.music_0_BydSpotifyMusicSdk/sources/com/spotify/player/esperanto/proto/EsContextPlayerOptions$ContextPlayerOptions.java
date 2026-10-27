package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.md0;
import p.qd0;
import p.uf0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextPlayerOptions$ContextPlayerOptions extends i implements yj1 {
    private static final EsContextPlayerOptions$ContextPlayerOptions DEFAULT_INSTANCE;
    public static final int MODES_FIELD_NUMBER = 5;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_SPEED_FIELD_NUMBER = 4;
    public static final int REPEATING_CONTEXT_FIELD_NUMBER = 2;
    public static final int REPEATING_TRACK_FIELD_NUMBER = 3;
    public static final int SHUFFLING_CONTEXT_FIELD_NUMBER = 1;
    private int bitField0_;
    private MapFieldLite<String, String> modes_ = MapFieldLite.c;
    private float playbackSpeed_;
    private boolean repeatingContext_;
    private boolean repeatingTrack_;
    private boolean shufflingContext_;

    static {
        EsContextPlayerOptions$ContextPlayerOptions esContextPlayerOptions$ContextPlayerOptions = new EsContextPlayerOptions$ContextPlayerOptions();
        DEFAULT_INSTANCE = esContextPlayerOptions$ContextPlayerOptions;
        i.registerDefaultInstance(EsContextPlayerOptions$ContextPlayerOptions.class, esContextPlayerOptions$ContextPlayerOptions);
    }

    private EsContextPlayerOptions$ContextPlayerOptions() {
    }

    public static EsContextPlayerOptions$ContextPlayerOptions f() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        md0 md0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0001\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004ခ\u0000\u00052", new Object[]{"bitField0_", "shufflingContext_", "repeatingContext_", "repeatingTrack_", "playbackSpeed_", "modes_", uf0.a});
            case 3:
                return new EsContextPlayerOptions$ContextPlayerOptions();
            case 4:
                return new qd0(md0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPlayerOptions$ContextPlayerOptions.class) {
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

    public final float g() {
        return this.playbackSpeed_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final boolean h() {
        return this.repeatingContext_;
    }

    public final boolean i() {
        return this.repeatingTrack_;
    }

    public final boolean j() {
        return this.shufflingContext_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
