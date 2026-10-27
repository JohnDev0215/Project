package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.mg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPreparePlay$PreparePlayRequest extends i implements yj1 {
    public static final int CONTEXT_FIELD_NUMBER = 1;
    private static final EsPreparePlay$PreparePlayRequest DEFAULT_INSTANCE;
    public static final int OPTIONS_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PLAY_ORIGIN_FIELD_NUMBER = 3;
    private EsContext$Context context_;
    private EsPreparePlayOptions$PreparePlayOptions options_;
    private EsPlayOrigin$PlayOrigin playOrigin_;

    static {
        EsPreparePlay$PreparePlayRequest esPreparePlay$PreparePlayRequest = new EsPreparePlay$PreparePlayRequest();
        DEFAULT_INSTANCE = esPreparePlay$PreparePlayRequest;
        i.registerDefaultInstance(EsPreparePlay$PreparePlayRequest.class, esPreparePlay$PreparePlayRequest);
    }

    private EsPreparePlay$PreparePlayRequest() {
    }

    public static void e(EsPreparePlay$PreparePlayRequest esPreparePlay$PreparePlayRequest, EsContext$Context esContext$Context) {
        esPreparePlay$PreparePlayRequest.getClass();
        esPreparePlay$PreparePlayRequest.context_ = esContext$Context;
    }

    public static void f(EsPreparePlay$PreparePlayRequest esPreparePlay$PreparePlayRequest, EsPreparePlayOptions$PreparePlayOptions esPreparePlayOptions$PreparePlayOptions) {
        esPreparePlay$PreparePlayRequest.getClass();
        esPreparePlay$PreparePlayRequest.options_ = esPreparePlayOptions$PreparePlayOptions;
    }

    public static void g(EsPreparePlay$PreparePlayRequest esPreparePlay$PreparePlayRequest, EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin) {
        esPreparePlay$PreparePlayRequest.getClass();
        esPreparePlay$PreparePlayRequest.playOrigin_ = esPlayOrigin$PlayOrigin;
    }

    public static mg0 h() {
        return (mg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t", new Object[]{"context_", "options_", "playOrigin_"});
            case 3:
                return new EsPreparePlay$PreparePlayRequest();
            case 4:
                return new mg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPreparePlay$PreparePlayRequest.class) {
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
