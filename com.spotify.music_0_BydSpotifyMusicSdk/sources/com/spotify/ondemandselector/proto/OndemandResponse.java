package com.spotify.ondemandselector.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.qd0;
import p.u4;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class OndemandResponse extends i implements yj1 {
    private static final OndemandResponse DEFAULT_INSTANCE;
    public static final int EXPIRETIMESTAMPMILLIS_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int URIS_FIELD_NUMBER = 1;
    private long expireTimestampMillis_;
    private v51 uris_ = i.emptyProtobufList();

    static {
        OndemandResponse ondemandResponse = new OndemandResponse();
        DEFAULT_INSTANCE = ondemandResponse;
        i.registerDefaultInstance(OndemandResponse.class, ondemandResponse);
    }

    private OndemandResponse() {
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ț\u0002\u0002", new Object[]{"uris_", "expireTimestampMillis_"});
            case 3:
                return new OndemandResponse();
            case 4:
                return new qd0(u4Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (OndemandResponse.class) {
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

    public final long f() {
        return this.expireTimestampMillis_;
    }

    public final v51 g() {
        return this.uris_;
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
