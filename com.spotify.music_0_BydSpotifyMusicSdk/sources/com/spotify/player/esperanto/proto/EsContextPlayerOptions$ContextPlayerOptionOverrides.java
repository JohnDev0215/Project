package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.sf0;
import p.tf0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextPlayerOptions$ContextPlayerOptionOverrides extends i implements yj1 {
    private static final EsContextPlayerOptions$ContextPlayerOptionOverrides DEFAULT_INSTANCE;
    public static final int MODES_FIELD_NUMBER = 5;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_SPEED_FIELD_NUMBER = 4;
    public static final int REPEATING_CONTEXT_FIELD_NUMBER = 2;
    public static final int REPEATING_TRACK_FIELD_NUMBER = 3;
    public static final int SHUFFLING_CONTEXT_FIELD_NUMBER = 1;
    private int bitField0_;
    private MapFieldLite<String, String> modes_ = MapFieldLite.c;
    private float playbackSpeed_;
    private EsOptional$OptionalBoolean repeatingContext_;
    private EsOptional$OptionalBoolean repeatingTrack_;
    private EsOptional$OptionalBoolean shufflingContext_;

    static {
        EsContextPlayerOptions$ContextPlayerOptionOverrides esContextPlayerOptions$ContextPlayerOptionOverrides = new EsContextPlayerOptions$ContextPlayerOptionOverrides();
        DEFAULT_INSTANCE = esContextPlayerOptions$ContextPlayerOptionOverrides;
        i.registerDefaultInstance(EsContextPlayerOptions$ContextPlayerOptionOverrides.class, esContextPlayerOptions$ContextPlayerOptionOverrides);
    }

    private EsContextPlayerOptions$ContextPlayerOptionOverrides() {
    }

    public static void e(EsContextPlayerOptions$ContextPlayerOptionOverrides esContextPlayerOptions$ContextPlayerOptionOverrides, EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        esContextPlayerOptions$ContextPlayerOptionOverrides.getClass();
        esOptional$OptionalBoolean.getClass();
        esContextPlayerOptions$ContextPlayerOptionOverrides.shufflingContext_ = esOptional$OptionalBoolean;
    }

    public static void f(EsContextPlayerOptions$ContextPlayerOptionOverrides esContextPlayerOptions$ContextPlayerOptionOverrides, EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        esContextPlayerOptions$ContextPlayerOptionOverrides.getClass();
        esOptional$OptionalBoolean.getClass();
        esContextPlayerOptions$ContextPlayerOptionOverrides.repeatingContext_ = esOptional$OptionalBoolean;
    }

    public static void g(EsContextPlayerOptions$ContextPlayerOptionOverrides esContextPlayerOptions$ContextPlayerOptionOverrides, EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        esContextPlayerOptions$ContextPlayerOptionOverrides.getClass();
        esOptional$OptionalBoolean.getClass();
        esContextPlayerOptions$ContextPlayerOptionOverrides.repeatingTrack_ = esOptional$OptionalBoolean;
    }

    public static sf0 h() {
        return (sf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0001\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004ခ\u0000\u00052", new Object[]{"bitField0_", "shufflingContext_", "repeatingContext_", "repeatingTrack_", "playbackSpeed_", "modes_", tf0.a});
            case 3:
                return new EsContextPlayerOptions$ContextPlayerOptionOverrides();
            case 4:
                return new sf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPlayerOptions$ContextPlayerOptionOverrides.class) {
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
