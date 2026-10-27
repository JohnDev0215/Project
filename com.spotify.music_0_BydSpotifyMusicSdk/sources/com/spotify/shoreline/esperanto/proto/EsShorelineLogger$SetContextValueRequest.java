package com.spotify.shoreline.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.qd0;
import p.u4;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsShorelineLogger$SetContextValueRequest extends i implements yj1 {
    private static final EsShorelineLogger$SetContextValueRequest DEFAULT_INSTANCE;
    public static final int DUMMY_CONTEXT_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_IDS_FIELD_NUMBER = 2;
    public static final int PLAYBACK_STATUS_FIELD_NUMBER = 3;
    public static final int REPORTED_TIMESTAMP_FIELD_NUMBER = 1;
    private int bitField0_;
    private int clientContextCase_ = 0;
    private Object clientContext_;
    private long reportedTimestamp_;

    static {
        EsShorelineLogger$SetContextValueRequest esShorelineLogger$SetContextValueRequest = new EsShorelineLogger$SetContextValueRequest();
        DEFAULT_INSTANCE = esShorelineLogger$SetContextValueRequest;
        i.registerDefaultInstance(EsShorelineLogger$SetContextValueRequest.class, esShorelineLogger$SetContextValueRequest);
    }

    private EsShorelineLogger$SetContextValueRequest() {
    }

    public static EsShorelineLogger$SetContextValueRequest k(byte[] bArr) {
        return (EsShorelineLogger$SetContextValueRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        u4 u4Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဂ\u0000\u0002<\u0000\u0003Ȼ\u0000\u0004:\u0000", new Object[]{"clientContext_", "clientContextCase_", "bitField0_", "reportedTimestamp_", EsShorelineLogger$PlaybackIds.class});
            case 3:
                return new EsShorelineLogger$SetContextValueRequest();
            case 4:
                return new qd0(20, u4Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsShorelineLogger$SetContextValueRequest.class) {
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

    public final EsShorelineLogger$PlaybackIds f() {
        return this.clientContextCase_ == 2 ? (EsShorelineLogger$PlaybackIds) this.clientContext_ : EsShorelineLogger$PlaybackIds.f();
    }

    public final String g() {
        return this.clientContextCase_ == 3 ? (String) this.clientContext_ : "";
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final boolean h() {
        return this.clientContextCase_ == 4;
    }

    public final boolean i() {
        return this.clientContextCase_ == 2;
    }

    public final boolean j() {
        return this.clientContextCase_ == 3;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
