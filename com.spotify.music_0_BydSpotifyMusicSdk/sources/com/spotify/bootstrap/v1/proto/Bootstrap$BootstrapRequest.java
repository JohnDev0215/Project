package com.spotify.bootstrap.v1.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import com.spotify.ucs.v0.proto.Ucs$UcsRequest;
import p.ds1;
import p.i01;
import p.om;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Bootstrap$BootstrapRequest extends i implements yj1 {
    private static final Bootstrap$BootstrapRequest DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int TRIALS_FACADE_REQUEST_V1_FIELD_NUMBER = 3;
    public static final int UCS_REQUEST_V0_FIELD_NUMBER = 2;
    private Object trialsFacadeRequest_;
    private Object ucsRequest_;
    private int ucsRequestCase_ = 0;
    private int trialsFacadeRequestCase_ = 0;

    static {
        Bootstrap$BootstrapRequest bootstrap$BootstrapRequest = new Bootstrap$BootstrapRequest();
        DEFAULT_INSTANCE = bootstrap$BootstrapRequest;
        i.registerDefaultInstance(Bootstrap$BootstrapRequest.class, bootstrap$BootstrapRequest);
    }

    private Bootstrap$BootstrapRequest() {
    }

    public static void e(Bootstrap$BootstrapRequest bootstrap$BootstrapRequest, Ucs$UcsRequest ucs$UcsRequest) {
        bootstrap$BootstrapRequest.getClass();
        ucs$UcsRequest.getClass();
        bootstrap$BootstrapRequest.ucsRequest_ = ucs$UcsRequest;
        bootstrap$BootstrapRequest.ucsRequestCase_ = 2;
    }

    public static void f(Bootstrap$BootstrapRequest bootstrap$BootstrapRequest, Bootstrap$TrialsFacadeRequestV1 bootstrap$TrialsFacadeRequestV1) {
        bootstrap$BootstrapRequest.getClass();
        bootstrap$TrialsFacadeRequestV1.getClass();
        bootstrap$BootstrapRequest.trialsFacadeRequest_ = bootstrap$TrialsFacadeRequestV1;
        bootstrap$BootstrapRequest.trialsFacadeRequestCase_ = 3;
    }

    public static om g() {
        return (om) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0002\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002<\u0000\u0003<\u0001", new Object[]{"ucsRequest_", "ucsRequestCase_", "trialsFacadeRequest_", "trialsFacadeRequestCase_", Ucs$UcsRequest.class, Bootstrap$TrialsFacadeRequestV1.class});
            case 3:
                return new Bootstrap$BootstrapRequest();
            case 4:
                return new om(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Bootstrap$BootstrapRequest.class) {
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
