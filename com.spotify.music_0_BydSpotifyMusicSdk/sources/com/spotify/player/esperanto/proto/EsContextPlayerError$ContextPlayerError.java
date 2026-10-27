package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.util.Collections;
import java.util.Map;
import p.ds1;
import p.i01;
import p.n51;
import p.rf0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextPlayerError$ContextPlayerError extends i implements yj1 {
    public static final int CODE_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 3;
    private static final EsContextPlayerError$ContextPlayerError DEFAULT_INSTANCE;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER;
    private int code_;
    private MapFieldLite<String, String> data_ = MapFieldLite.c;
    private String message_ = "";

    public enum ErrorCode implements n51 {
        SUCCESS(0),
        PLAYBACK_STUCK(1),
        PLAYBACK_ERROR(2),
        LICENSE_CHANGE(3),
        PLAY_RESTRICTED(4),
        STOP_RESTRICTED(5),
        UPDATE_RESTRICTED(6),
        PAUSE_RESTRICTED(7),
        RESUME_RESTRICTED(8),
        SKIP_TO_PREV_RESTRICTED(9),
        SKIP_TO_NEXT_RESTRICTED(10),
        SKIP_TO_NON_EXISTENT_TRACK(11),
        SEEK_TO_RESTRICTED(12),
        TOGGLE_REPEAT_CONTEXT_RESTRICTED(13),
        TOGGLE_REPEAT_TRACK_RESTRICTED(14),
        SET_OPTIONS_RESTRICTED(15),
        TOGGLE_SHUFFLE_RESTRICTED(16),
        SET_QUEUE_RESTRICTED(17),
        INTERRUPT_PLAYBACK_RESTRICTED(18),
        ONE_TRACK_UNPLAYABLE(19),
        ONE_TRACK_UNPLAYABLE_AUTO_STOPPED(20),
        ALL_TRACKS_UNPLAYABLE_AUTO_STOPPED(21),
        SKIP_TO_NON_EXISTENT_TRACK_AUTO_STOPPED(22),
        QUEUE_REVISION_MISMATCH(23),
        VIDEO_PLAYBACK_ERROR(24),
        VIDEO_GEOGRAPHICALLY_RESTRICTED(25),
        VIDEO_UNSUPPORTED_PLATFORM_VERSION(26),
        VIDEO_UNSUPPORTED_CLIENT_VERSION(27),
        VIDEO_UNSUPPORTED_KEY_SYSTEM(28),
        VIDEO_MANIFEST_DELETED(29),
        VIDEO_COUNTRY_RESTRICTED(30),
        VIDEO_UNAVAILABLE(31),
        VIDEO_CATALOGUE_RESTRICTED(32),
        INVALID(33),
        TIMEOUT(34),
        PLAYBACK_REPORTING_ERROR(35),
        UNKNOWN(36),
        ADD_TO_QUEUE_RESTRICTED(37),
        PICK_AND_SHUFFLE_CAPPED(38),
        PICK_AND_SHUFFLE_CONNECT_RESTRICTED(39),
        CONTEXT_LOADING_FAILED(40),
        AUDIOBOOK_NOT_PLAYABLE(41),
        UNRECOGNIZED(-1);

        private final int value;

        ErrorCode(int i) {
            this.value = i;
        }

        public static ErrorCode a(int i) {
            switch (i) {
                case 0:
                    return SUCCESS;
                case 1:
                    return PLAYBACK_STUCK;
                case 2:
                    return PLAYBACK_ERROR;
                case 3:
                    return LICENSE_CHANGE;
                case 4:
                    return PLAY_RESTRICTED;
                case 5:
                    return STOP_RESTRICTED;
                case 6:
                    return UPDATE_RESTRICTED;
                case 7:
                    return PAUSE_RESTRICTED;
                case 8:
                    return RESUME_RESTRICTED;
                case 9:
                    return SKIP_TO_PREV_RESTRICTED;
                case 10:
                    return SKIP_TO_NEXT_RESTRICTED;
                case 11:
                    return SKIP_TO_NON_EXISTENT_TRACK;
                case 12:
                    return SEEK_TO_RESTRICTED;
                case 13:
                    return TOGGLE_REPEAT_CONTEXT_RESTRICTED;
                case 14:
                    return TOGGLE_REPEAT_TRACK_RESTRICTED;
                case 15:
                    return SET_OPTIONS_RESTRICTED;
                case 16:
                    return TOGGLE_SHUFFLE_RESTRICTED;
                case 17:
                    return SET_QUEUE_RESTRICTED;
                case 18:
                    return INTERRUPT_PLAYBACK_RESTRICTED;
                case 19:
                    return ONE_TRACK_UNPLAYABLE;
                case 20:
                    return ONE_TRACK_UNPLAYABLE_AUTO_STOPPED;
                case 21:
                    return ALL_TRACKS_UNPLAYABLE_AUTO_STOPPED;
                case 22:
                    return SKIP_TO_NON_EXISTENT_TRACK_AUTO_STOPPED;
                case 23:
                    return QUEUE_REVISION_MISMATCH;
                case 24:
                    return VIDEO_PLAYBACK_ERROR;
                case 25:
                    return VIDEO_GEOGRAPHICALLY_RESTRICTED;
                case 26:
                    return VIDEO_UNSUPPORTED_PLATFORM_VERSION;
                case 27:
                    return VIDEO_UNSUPPORTED_CLIENT_VERSION;
                case 28:
                    return VIDEO_UNSUPPORTED_KEY_SYSTEM;
                case 29:
                    return VIDEO_MANIFEST_DELETED;
                case 30:
                    return VIDEO_COUNTRY_RESTRICTED;
                case 31:
                    return VIDEO_UNAVAILABLE;
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    return VIDEO_CATALOGUE_RESTRICTED;
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    return INVALID;
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    return TIMEOUT;
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    return PLAYBACK_REPORTING_ERROR;
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    return UNKNOWN;
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                    return ADD_TO_QUEUE_RESTRICTED;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                    return PICK_AND_SHUFFLE_CAPPED;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                    return PICK_AND_SHUFFLE_CONNECT_RESTRICTED;
                case 40:
                    return CONTEXT_LOADING_FAILED;
                case 41:
                    return AUDIOBOOK_NOT_PLAYABLE;
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

    static {
        EsContextPlayerError$ContextPlayerError esContextPlayerError$ContextPlayerError = new EsContextPlayerError$ContextPlayerError();
        DEFAULT_INSTANCE = esContextPlayerError$ContextPlayerError;
        i.registerDefaultInstance(EsContextPlayerError$ContextPlayerError.class, esContextPlayerError$ContextPlayerError);
    }

    private EsContextPlayerError$ContextPlayerError() {
    }

    public static EsContextPlayerError$ContextPlayerError g(byte[] bArr) {
        return (EsContextPlayerError$ContextPlayerError) i.parseFrom(DEFAULT_INSTANCE, bArr);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0001\f\u0002Ȉ\u00032", new Object[]{"code_", "message_", "data_", rf0.a});
            case 3:
                return new EsContextPlayerError$ContextPlayerError();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPlayerError$ContextPlayerError.class) {
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

    public final ErrorCode e() {
        ErrorCode errorCodeA = ErrorCode.a(this.code_);
        return errorCodeA == null ? ErrorCode.UNRECOGNIZED : errorCodeA;
    }

    public final Map f() {
        return Collections.unmodifiableMap(this.data_);
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
