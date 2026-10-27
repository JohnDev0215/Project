package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.dh0;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSkipToTrack$SkipToTrack extends i implements yj1 {
    private static final EsSkipToTrack$SkipToTrack DEFAULT_INSTANCE;
    public static final int PAGE_INDEX_FIELD_NUMBER = 2;
    public static final int PAGE_URL_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int TRACK_INDEX_FIELD_NUMBER = 5;
    public static final int TRACK_UID_FIELD_NUMBER = 3;
    public static final int TRACK_URI_FIELD_NUMBER = 4;
    private EsOptional$OptionalInt64 pageIndex_;
    private EsOptional$OptionalInt64 trackIndex_;
    private String pageUrl_ = "";
    private String trackUid_ = "";
    private String trackUri_ = "";

    static {
        EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack = new EsSkipToTrack$SkipToTrack();
        DEFAULT_INSTANCE = esSkipToTrack$SkipToTrack;
        i.registerDefaultInstance(EsSkipToTrack$SkipToTrack.class, esSkipToTrack$SkipToTrack);
    }

    private EsSkipToTrack$SkipToTrack() {
    }

    public static void e(EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack, String str) {
        esSkipToTrack$SkipToTrack.getClass();
        str.getClass();
        esSkipToTrack$SkipToTrack.pageUrl_ = str;
    }

    public static void f(EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack, String str) {
        esSkipToTrack$SkipToTrack.getClass();
        str.getClass();
        esSkipToTrack$SkipToTrack.trackUri_ = str;
    }

    public static void g(EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack, EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        esSkipToTrack$SkipToTrack.getClass();
        esOptional$OptionalInt64.getClass();
        esSkipToTrack$SkipToTrack.trackIndex_ = esOptional$OptionalInt64;
    }

    public static void h(EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack, EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        esSkipToTrack$SkipToTrack.getClass();
        esOptional$OptionalInt64.getClass();
        esSkipToTrack$SkipToTrack.pageIndex_ = esOptional$OptionalInt64;
    }

    public static void i(EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack, String str) {
        esSkipToTrack$SkipToTrack.getClass();
        str.getClass();
        esSkipToTrack$SkipToTrack.trackUid_ = str;
    }

    public static dh0 j() {
        return (dh0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003Ȉ\u0004Ȉ\u0005\t", new Object[]{"pageUrl_", "pageIndex_", "trackUid_", "trackUri_", "trackIndex_"});
            case 3:
                return new EsSkipToTrack$SkipToTrack();
            case 4:
                return new dh0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsSkipToTrack$SkipToTrack.class) {
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
