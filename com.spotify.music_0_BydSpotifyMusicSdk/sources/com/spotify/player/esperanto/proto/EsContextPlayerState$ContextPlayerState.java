package com.spotify.player.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import com.spotify.player.esperanto.proto.EsPreparePlayOptions$PreparePlayOptions;
import java.util.Collections;
import java.util.Map;
import p.ds1;
import p.i01;
import p.v51;
import p.vf0;
import p.vj1;
import p.wf0;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextPlayerState$ContextPlayerState extends i implements yj1 {
    public static final int AUDIO_STREAM_FIELD_NUMBER = 26;
    public static final int CONTEXT_METADATA_FIELD_NUMBER = 22;
    public static final int CONTEXT_RESTRICTIONS_FIELD_NUMBER = 4;
    public static final int CONTEXT_URI_FIELD_NUMBER = 2;
    public static final int CONTEXT_URL_FIELD_NUMBER = 3;
    private static final EsContextPlayerState$ContextPlayerState DEFAULT_INSTANCE;
    public static final int DURATION_FIELD_NUMBER = 12;
    public static final int INDEX_FIELD_NUMBER = 6;
    public static final int IS_BUFFERING_FIELD_NUMBER = 15;
    public static final int IS_PAUSED_FIELD_NUMBER = 14;
    public static final int IS_PLAYING_FIELD_NUMBER = 13;
    public static final int IS_SYSTEM_INITIATED_FIELD_NUMBER = 16;
    public static final int NEXT_TRACKS_FIELD_NUMBER = 21;
    public static final int OPTIONS_FIELD_NUMBER = 17;
    public static final int PAGE_METADATA_FIELD_NUMBER = 23;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_ID_FIELD_NUMBER = 8;
    public static final int PLAYBACK_QUALITY_FIELD_NUMBER = 9;
    public static final int PLAYBACK_SPEED_FIELD_NUMBER = 10;
    public static final int PLAY_ORIGIN_FIELD_NUMBER = 5;
    public static final int POSITION_AS_OF_TIMESTAMP_FIELD_NUMBER = 11;
    public static final int PREV_TRACKS_FIELD_NUMBER = 20;
    public static final int QUEUE_REVISION_FIELD_NUMBER = 25;
    public static final int RESTRICTIONS_FIELD_NUMBER = 18;
    public static final int SESSION_ID_FIELD_NUMBER = 24;
    public static final int SIGNALS_FIELD_NUMBER = 27;
    public static final int SUPPRESSIONS_FIELD_NUMBER = 19;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    public static final int TRACK_FIELD_NUMBER = 7;
    private int audioStream_;
    private MapFieldLite<String, String> contextMetadata_;
    private EsRestrictions$Restrictions contextRestrictions_;
    private String contextUri_;
    private String contextUrl_;
    private EsOptional$OptionalInt64 duration_;
    private EsContextPlayerState$ContextIndex index_;
    private boolean isBuffering_;
    private boolean isPaused_;
    private boolean isPlaying_;
    private boolean isSystemInitiated_;
    private v51 nextTracks_;
    private EsContextPlayerOptions$ContextPlayerOptions options_;
    private MapFieldLite<String, String> pageMetadata_;
    private EsPlayOrigin$PlayOrigin playOrigin_;
    private ByteString playbackId_;
    private EsContextPlayerState$PlaybackQuality playbackQuality_;
    private EsOptional$OptionalDouble playbackSpeed_;
    private EsOptional$OptionalInt64 positionAsOfTimestamp_;
    private v51 prevTracks_;
    private long queueRevision_;
    private EsRestrictions$Restrictions restrictions_;
    private String sessionId_;
    private v51 signals_;
    private v51 suppressions_;
    private long timestamp_;
    private EsProvidedTrack$ProvidedTrack track_;

    static {
        EsContextPlayerState$ContextPlayerState esContextPlayerState$ContextPlayerState = new EsContextPlayerState$ContextPlayerState();
        DEFAULT_INSTANCE = esContextPlayerState$ContextPlayerState;
        i.registerDefaultInstance(EsContextPlayerState$ContextPlayerState.class, esContextPlayerState$ContextPlayerState);
    }

    private EsContextPlayerState$ContextPlayerState() {
        MapFieldLite<String, String> mapFieldLite = MapFieldLite.c;
        this.contextMetadata_ = mapFieldLite;
        this.pageMetadata_ = mapFieldLite;
        this.contextUri_ = "";
        this.contextUrl_ = "";
        this.playbackId_ = ByteString.c;
        this.suppressions_ = i.emptyProtobufList();
        this.prevTracks_ = i.emptyProtobufList();
        this.nextTracks_ = i.emptyProtobufList();
        this.sessionId_ = "";
        this.signals_ = i.emptyProtobufList();
    }

    public static EsContextPlayerState$ContextPlayerState P(byte[] bArr) {
        return (EsContextPlayerState$ContextPlayerState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public final long A() {
        return this.queueRevision_;
    }

    public final EsRestrictions$Restrictions B() {
        EsRestrictions$Restrictions esRestrictions$Restrictions = this.restrictions_;
        return esRestrictions$Restrictions == null ? EsRestrictions$Restrictions.B() : esRestrictions$Restrictions;
    }

    public final String C() {
        return this.sessionId_;
    }

    public final int D() {
        return this.signals_.size();
    }

    public final v51 E() {
        return this.signals_;
    }

    public final int F() {
        return this.suppressions_.size();
    }

    public final v51 G() {
        return this.suppressions_;
    }

    public final long H() {
        return this.timestamp_;
    }

    public final EsProvidedTrack$ProvidedTrack I() {
        EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrack = this.track_;
        return esProvidedTrack$ProvidedTrack == null ? EsProvidedTrack$ProvidedTrack.h() : esProvidedTrack$ProvidedTrack;
    }

    public final boolean J() {
        return this.duration_ != null;
    }

    public final boolean K() {
        return this.index_ != null;
    }

    public final boolean L() {
        return this.playbackQuality_ != null;
    }

    public final boolean M() {
        return this.playbackSpeed_ != null;
    }

    public final boolean N() {
        return this.positionAsOfTimestamp_ != null;
    }

    public final boolean O() {
        return this.track_ != null;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u001b\u0000\u0000\u0001\u001b\u001b\u0002\u0004\u0000\u0001\u0003\u0002Ȉ\u0003Ȉ\u0004\t\u0005\t\u0006\t\u0007\t\b\n\t\t\n\t\u000b\t\f\t\r\u0007\u000e\u0007\u000f\u0007\u0010\u0007\u0011\t\u0012\t\u0013Ț\u0014\u001b\u0015\u001b\u00162\u00172\u0018Ȉ\u0019\u0003\u001a\f\u001bȚ", new Object[]{"timestamp_", "contextUri_", "contextUrl_", "contextRestrictions_", "playOrigin_", "index_", "track_", "playbackId_", "playbackQuality_", "playbackSpeed_", "positionAsOfTimestamp_", "duration_", "isPlaying_", "isPaused_", "isBuffering_", "isSystemInitiated_", "options_", "restrictions_", "suppressions_", "prevTracks_", EsProvidedTrack$ProvidedTrack.class, "nextTracks_", EsProvidedTrack$ProvidedTrack.class, "contextMetadata_", vf0.a, "pageMetadata_", wf0.a, "sessionId_", "queueRevision_", "audioStream_", "signals_"});
            case 3:
                return new EsContextPlayerState$ContextPlayerState();
            case 4:
                return new b(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPlayerState$ContextPlayerState.class) {
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

    public final EsPreparePlayOptions$PreparePlayOptions.AudioStream e() {
        int i = this.audioStream_;
        EsPreparePlayOptions$PreparePlayOptions.AudioStream audioStream = i != 0 ? i != 1 ? null : EsPreparePlayOptions$PreparePlayOptions.AudioStream.ALARM : EsPreparePlayOptions$PreparePlayOptions.AudioStream.DEFAULT;
        return audioStream == null ? EsPreparePlayOptions$PreparePlayOptions.AudioStream.UNRECOGNIZED : audioStream;
    }

    public final Map f() {
        return Collections.unmodifiableMap(this.contextMetadata_);
    }

    public final EsRestrictions$Restrictions g() {
        EsRestrictions$Restrictions esRestrictions$Restrictions = this.contextRestrictions_;
        return esRestrictions$Restrictions == null ? EsRestrictions$Restrictions.B() : esRestrictions$Restrictions;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final String h() {
        return this.contextUri_;
    }

    public final String i() {
        return this.contextUrl_;
    }

    public final EsOptional$OptionalInt64 j() {
        EsOptional$OptionalInt64 esOptional$OptionalInt64 = this.duration_;
        return esOptional$OptionalInt64 == null ? EsOptional$OptionalInt64.f() : esOptional$OptionalInt64;
    }

    public final EsContextPlayerState$ContextIndex k() {
        EsContextPlayerState$ContextIndex esContextPlayerState$ContextIndex = this.index_;
        return esContextPlayerState$ContextIndex == null ? EsContextPlayerState$ContextIndex.f() : esContextPlayerState$ContextIndex;
    }

    public final boolean l() {
        return this.isBuffering_;
    }

    public final boolean m() {
        return this.isPaused_;
    }

    public final boolean n() {
        return this.isPlaying_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public final boolean o() {
        return this.isSystemInitiated_;
    }

    public final int p() {
        return this.nextTracks_.size();
    }

    public final v51 q() {
        return this.nextTracks_;
    }

    public final EsContextPlayerOptions$ContextPlayerOptions r() {
        EsContextPlayerOptions$ContextPlayerOptions esContextPlayerOptions$ContextPlayerOptions = this.options_;
        return esContextPlayerOptions$ContextPlayerOptions == null ? EsContextPlayerOptions$ContextPlayerOptions.f() : esContextPlayerOptions$ContextPlayerOptions;
    }

    public final Map s() {
        return Collections.unmodifiableMap(this.pageMetadata_);
    }

    public final EsPlayOrigin$PlayOrigin t() {
        EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin = this.playOrigin_;
        return esPlayOrigin$PlayOrigin == null ? EsPlayOrigin$PlayOrigin.m() : esPlayOrigin$PlayOrigin;
    }

    public final ByteString u() {
        return this.playbackId_;
    }

    public final EsContextPlayerState$PlaybackQuality v() {
        EsContextPlayerState$PlaybackQuality esContextPlayerState$PlaybackQuality = this.playbackQuality_;
        return esContextPlayerState$PlaybackQuality == null ? EsContextPlayerState$PlaybackQuality.f() : esContextPlayerState$PlaybackQuality;
    }

    public final EsOptional$OptionalDouble w() {
        EsOptional$OptionalDouble esOptional$OptionalDouble = this.playbackSpeed_;
        return esOptional$OptionalDouble == null ? EsOptional$OptionalDouble.f() : esOptional$OptionalDouble;
    }

    public final EsOptional$OptionalInt64 x() {
        EsOptional$OptionalInt64 esOptional$OptionalInt64 = this.positionAsOfTimestamp_;
        return esOptional$OptionalInt64 == null ? EsOptional$OptionalInt64.f() : esOptional$OptionalInt64;
    }

    public final int y() {
        return this.prevTracks_.size();
    }

    public final v51 z() {
        return this.prevTracks_;
    }
}
