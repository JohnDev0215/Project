package com.spotify.bootstrap.v1.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Bootstrap$BootstrapResponse extends i implements yj1 {
    private static final Bootstrap$BootstrapResponse DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int TRIALS_FACADE_RESPONSE_V1_FIELD_NUMBER = 3;
    public static final int UCS_RESPONSE_V0_FIELD_NUMBER = 2;
    private Object trialsFacadeResponse_;
    private Object ucsResponse_;
    private int ucsResponseCase_ = 0;
    private int trialsFacadeResponseCase_ = 0;

    public enum UcsResponseCase {
        UCS_RESPONSE_V0(2),
        UCSRESPONSE_NOT_SET(0);

        private final int value;

        UcsResponseCase(int i) {
            this.value = i;
        }
    }

    static {
        Bootstrap$BootstrapResponse bootstrap$BootstrapResponse = new Bootstrap$BootstrapResponse();
        DEFAULT_INSTANCE = bootstrap$BootstrapResponse;
        i.registerDefaultInstance(Bootstrap$BootstrapResponse.class, bootstrap$BootstrapResponse);
    }

    private Bootstrap$BootstrapResponse() {
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0002\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002<\u0000\u0003<\u0001", new Object[]{"ucsResponse_", "ucsResponseCase_", "trialsFacadeResponse_", "trialsFacadeResponseCase_", Bootstrap$UcsResponseWrapperV0.class, Bootstrap$TrialsFacadeResponseWrapperV1.class});
            case 3:
                return new Bootstrap$BootstrapResponse();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Bootstrap$BootstrapResponse.class) {
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

    public final UcsResponseCase e() {
        int i = this.ucsResponseCase_;
        if (i == 0) {
            return UcsResponseCase.UCSRESPONSE_NOT_SET;
        }
        if (i != 2) {
            return null;
        }
        return UcsResponseCase.UCS_RESPONSE_V0;
    }

    public final Bootstrap$UcsResponseWrapperV0 f() {
        return this.ucsResponseCase_ == 2 ? (Bootstrap$UcsResponseWrapperV0) this.ucsResponse_ : Bootstrap$UcsResponseWrapperV0.e();
    }

    public final boolean g() {
        return this.ucsResponseCase_ == 2;
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
