package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.og0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsProvidedTrack$ProvidedTrack extends i implements yj1 {
    public static final int BLOCKED_FIELD_NUMBER = 3;
    public static final int CONTEXT_TRACK_FIELD_NUMBER = 1;
    private static final EsProvidedTrack$ProvidedTrack DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int PROVIDER_FIELD_NUMBER = 4;
    public static final int REMOVED_FIELD_NUMBER = 2;
    private EsContextTrack$ContextTrack contextTrack_;
    private v51 removed_ = i.emptyProtobufList();
    private v51 blocked_ = i.emptyProtobufList();
    private String provider_ = "";

    static {
        EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrack = new EsProvidedTrack$ProvidedTrack();
        DEFAULT_INSTANCE = esProvidedTrack$ProvidedTrack;
        i.registerDefaultInstance(EsProvidedTrack$ProvidedTrack.class, esProvidedTrack$ProvidedTrack);
    }

    private EsProvidedTrack$ProvidedTrack() {
    }

    public static void e(EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrack, EsContextTrack$ContextTrack esContextTrack$ContextTrack) {
        esProvidedTrack$ProvidedTrack.getClass();
        esProvidedTrack$ProvidedTrack.contextTrack_ = esContextTrack$ContextTrack;
    }

    public static void f(EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrack, String str) {
        esProvidedTrack$ProvidedTrack.getClass();
        str.getClass();
        esProvidedTrack$ProvidedTrack.provider_ = str;
    }

    public static EsProvidedTrack$ProvidedTrack h() {
        return DEFAULT_INSTANCE;
    }

    public static og0 j() {
        return (og0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\t\u0002Ț\u0003Ț\u0004Ȉ", new Object[]{"contextTrack_", "removed_", "blocked_", "provider_"});
            case 3:
                return new EsProvidedTrack$ProvidedTrack();
            case 4:
                return new og0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsProvidedTrack$ProvidedTrack.class) {
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

    public final EsContextTrack$ContextTrack g() {
        EsContextTrack$ContextTrack esContextTrack$ContextTrack = this.contextTrack_;
        return esContextTrack$ContextTrack == null ? EsContextTrack$ContextTrack.h() : esContextTrack$ContextTrack;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final String i() {
        return this.provider_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
