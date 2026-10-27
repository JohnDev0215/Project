package com.spotify.player.esperanto.proto;

import com.google.common.collect.ImmutableSet;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.n51;
import p.ng0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPreparePlayOptions$PreparePlayOptions extends i implements yj1 {
    public static final int ALWAYS_PLAY_SOMETHING_FIELD_NUMBER = 2;
    public static final int AUDIO_STREAM_FIELD_NUMBER = 10;
    public static final int CONFIGURATION_OVERRIDE_FIELD_NUMBER = 13;
    private static final EsPreparePlayOptions$PreparePlayOptions DEFAULT_INSTANCE;
    public static final int INITIALLY_PAUSED_FIELD_NUMBER = 5;
    public static final int LICENSE_FIELD_NUMBER = 12;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_ID_FIELD_NUMBER = 1;
    public static final int PLAYER_OPTIONS_OVERRIDE_FIELD_NUMBER = 7;
    public static final int PREFETCH_LEVEL_FIELD_NUMBER = 9;
    public static final int SEEK_TO_FIELD_NUMBER = 4;
    public static final int SESSION_ID_FIELD_NUMBER = 11;
    public static final int SKIP_TO_FIELD_NUMBER = 3;
    public static final int SUPPRESSIONS_FIELD_NUMBER = 8;
    public static final int SYSTEM_INITIATED_FIELD_NUMBER = 6;
    private boolean alwaysPlaySomething_;
    private int audioStream_;
    private boolean initiallyPaused_;
    private EsContextPlayerOptions$ContextPlayerOptionOverrides playerOptionsOverride_;
    private int prefetchLevel_;
    private EsOptional$OptionalInt64 seekTo_;
    private EsSkipToTrack$SkipToTrack skipTo_;
    private boolean systemInitiated_;
    private MapFieldLite<String, String> configurationOverride_ = MapFieldLite.c;
    private ByteString playbackId_ = ByteString.c;
    private v51 suppressions_ = i.emptyProtobufList();
    private String sessionId_ = "";
    private String license_ = "";

    public enum AudioStream implements n51 {
        DEFAULT(0),
        ALARM(1),
        UNRECOGNIZED(-1);

        private final int value;

        AudioStream(int i) {
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

    public enum PrefetchLevel implements n51 {
        NONE(0),
        MEDIA(1),
        UNRECOGNIZED(-1);

        private final int value;

        PrefetchLevel(int i) {
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
        EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions = new EsPreparePlayOptions$PreparePlayOptions();
        DEFAULT_INSTANCE = esPreparePlayOptions$PreparePlayOptions;
        i.registerDefaultInstance(EsPreparePlayOptions$PreparePlayOptions.class, esPreparePlayOptions$PreparePlayOptions);
    }

    private EsPreparePlayOptions$PreparePlayOptions() {
    }

    public static void e(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, ByteString byteString) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        esPreparePlayOptions$PreparePlayOptions.playbackId_ = byteString;
    }

    public static void f(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, boolean z) {
        esPreparePlayOptions$PreparePlayOptions.initiallyPaused_ = z;
    }

    public static void g(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, boolean z) {
        esPreparePlayOptions$PreparePlayOptions.systemInitiated_ = z;
    }

    public static void h(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, EsContextPlayerOptions$ContextPlayerOptionOverrides esContextPlayerOptions$ContextPlayerOptionOverrides) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        esPreparePlayOptions$PreparePlayOptions.playerOptionsOverride_ = esContextPlayerOptions$ContextPlayerOptionOverrides;
    }

    public static void i(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, ImmutableSet immutableSet) {
        v51 v51Var = esPreparePlayOptions$PreparePlayOptions.suppressions_;
        if (!((a2) v51Var).c) {
            esPreparePlayOptions$PreparePlayOptions.suppressions_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esPreparePlayOptions$PreparePlayOptions.suppressions_);
    }

    public static void j(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, PrefetchLevel prefetchLevel) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        esPreparePlayOptions$PreparePlayOptions.prefetchLevel_ = prefetchLevel.getNumber();
    }

    public static void k(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, AudioStream audioStream) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        esPreparePlayOptions$PreparePlayOptions.audioStream_ = audioStream.getNumber();
    }

    public static void l(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, String str) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        str.getClass();
        esPreparePlayOptions$PreparePlayOptions.sessionId_ = str;
    }

    public static void m(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, boolean z) {
        esPreparePlayOptions$PreparePlayOptions.alwaysPlaySomething_ = z;
    }

    public static void n(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, String str) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        str.getClass();
        esPreparePlayOptions$PreparePlayOptions.license_ = str;
    }

    public static MapFieldLite o(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions) {
        if (!esPreparePlayOptions$PreparePlayOptions.configurationOverride_.e()) {
            esPreparePlayOptions$PreparePlayOptions.configurationOverride_ = esPreparePlayOptions$PreparePlayOptions.configurationOverride_.g();
        }
        return esPreparePlayOptions$PreparePlayOptions.configurationOverride_;
    }

    public static void p(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        esPreparePlayOptions$PreparePlayOptions.skipTo_ = esSkipToTrack$SkipToTrack;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions, EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        esPreparePlayOptions$PreparePlayOptions.getClass();
        esOptional$OptionalInt64.getClass();
        esPreparePlayOptions$PreparePlayOptions.seekTo_ = esOptional$OptionalInt64;
    }

    public static e r() {
        return (e) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\r\u0000\u0000\u0001\r\r\u0001\u0001\u0000\u0001\n\u0002\u0007\u0003\t\u0004\t\u0005\u0007\u0006\u0007\u0007\t\bȚ\t\f\n\f\u000bȈ\fȈ\r2", new Object[]{"playbackId_", "alwaysPlaySomething_", "skipTo_", "seekTo_", "initiallyPaused_", "systemInitiated_", "playerOptionsOverride_", "suppressions_", "prefetchLevel_", "audioStream_", "sessionId_", "license_", "configurationOverride_", ng0.a});
            case 3:
                return new EsPreparePlayOptions$PreparePlayOptions();
            case 4:
                return new e(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPreparePlayOptions$PreparePlayOptions.class) {
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
