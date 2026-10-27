package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.ed0;
import p.i01;
import p.qd0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsQueue$Queue extends i implements yj1 {
    private static final EsQueue$Queue DEFAULT_INSTANCE;
    public static final int NEXT_TRACKS_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int PREV_TRACKS_FIELD_NUMBER = 4;
    public static final int QUEUE_REVISION_FIELD_NUMBER = 1;
    public static final int TRACK_FIELD_NUMBER = 2;
    private v51 nextTracks_ = i.emptyProtobufList();
    private v51 prevTracks_ = i.emptyProtobufList();
    private long queueRevision_;
    private EsProvidedTrack$ProvidedTrack track_;

    static {
        EsQueue$Queue esQueue$Queue = new EsQueue$Queue();
        DEFAULT_INSTANCE = esQueue$Queue;
        i.registerDefaultInstance(EsQueue$Queue.class, esQueue$Queue);
    }

    private EsQueue$Queue() {
    }

    public static EsQueue$Queue m(byte[] bArr) {
        return (EsQueue$Queue) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        ed0 ed0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u0003\u0002\t\u0003\u001b\u0004\u001b", new Object[]{"queueRevision_", "track_", "nextTracks_", EsProvidedTrack$ProvidedTrack.class, "prevTracks_", EsProvidedTrack$ProvidedTrack.class});
            case 3:
                return new EsQueue$Queue();
            case 4:
                return new qd0(ed0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsQueue$Queue.class) {
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

    public final int f() {
        return this.nextTracks_.size();
    }

    public final v51 g() {
        return this.nextTracks_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final int h() {
        return this.prevTracks_.size();
    }

    public final v51 i() {
        return this.prevTracks_;
    }

    public final long j() {
        return this.queueRevision_;
    }

    public final EsProvidedTrack$ProvidedTrack k() {
        EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrack = this.track_;
        return esProvidedTrack$ProvidedTrack == null ? EsProvidedTrack$ProvidedTrack.h() : esProvidedTrack$ProvidedTrack;
    }

    public final boolean l() {
        return this.track_ != null;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
