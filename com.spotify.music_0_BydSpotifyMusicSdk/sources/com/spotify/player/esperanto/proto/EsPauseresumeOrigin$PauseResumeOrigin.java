package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.eg0;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPauseresumeOrigin$PauseResumeOrigin extends i implements yj1 {
    private static final EsPauseresumeOrigin$PauseResumeOrigin DEFAULT_INSTANCE;
    public static final int FEATURE_IDENTIFIER_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private String featureIdentifier_ = "";

    static {
        EsPauseresumeOrigin$PauseResumeOrigin esPauseresumeOrigin$PauseResumeOrigin = new EsPauseresumeOrigin$PauseResumeOrigin();
        DEFAULT_INSTANCE = esPauseresumeOrigin$PauseResumeOrigin;
        i.registerDefaultInstance(EsPauseresumeOrigin$PauseResumeOrigin.class, esPauseresumeOrigin$PauseResumeOrigin);
    }

    private EsPauseresumeOrigin$PauseResumeOrigin() {
    }

    public static void e(EsPauseresumeOrigin$PauseResumeOrigin esPauseresumeOrigin$PauseResumeOrigin, String str) {
        esPauseresumeOrigin$PauseResumeOrigin.getClass();
        str.getClass();
        esPauseresumeOrigin$PauseResumeOrigin.featureIdentifier_ = str;
    }

    public static eg0 f() {
        return (eg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"featureIdentifier_"});
            case 3:
                return new EsPauseresumeOrigin$PauseResumeOrigin();
            case 4:
                return new eg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPauseresumeOrigin$PauseResumeOrigin.class) {
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
