package com.spotify.bootstrap.v1.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Bootstrap$TrialsFacadeRequestV1 extends i implements yj1 {
    private static final Bootstrap$TrialsFacadeRequestV1 DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;

    static {
        Bootstrap$TrialsFacadeRequestV1 bootstrap$TrialsFacadeRequestV1 = new Bootstrap$TrialsFacadeRequestV1();
        DEFAULT_INSTANCE = bootstrap$TrialsFacadeRequestV1;
        i.registerDefaultInstance(Bootstrap$TrialsFacadeRequestV1.class, bootstrap$TrialsFacadeRequestV1);
    }

    private Bootstrap$TrialsFacadeRequestV1() {
    }

    public static Bootstrap$TrialsFacadeRequestV1 f() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        Object obj3 = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
            case 3:
                return new Bootstrap$TrialsFacadeRequestV1();
            case 4:
                return new qd0(obj3);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Bootstrap$TrialsFacadeRequestV1.class) {
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
