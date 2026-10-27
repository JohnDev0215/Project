package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.v22;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class RejectedClientEventNonAuth extends i implements yj1 {
    private static final RejectedClientEventNonAuth DEFAULT_INSTANCE;
    public static final int EVENT_NAME_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int REJECT_REASON_FIELD_NUMBER = 1;
    private int bitField0_;
    private String rejectReason_ = "";
    private String eventName_ = "";

    static {
        RejectedClientEventNonAuth rejectedClientEventNonAuth = new RejectedClientEventNonAuth();
        DEFAULT_INSTANCE = rejectedClientEventNonAuth;
        i.registerDefaultInstance(RejectedClientEventNonAuth.class, rejectedClientEventNonAuth);
    }

    private RejectedClientEventNonAuth() {
    }

    public static void e(RejectedClientEventNonAuth rejectedClientEventNonAuth, String str) {
        rejectedClientEventNonAuth.getClass();
        rejectedClientEventNonAuth.bitField0_ |= 1;
        rejectedClientEventNonAuth.rejectReason_ = str;
    }

    public static void f(RejectedClientEventNonAuth rejectedClientEventNonAuth, String str) {
        rejectedClientEventNonAuth.getClass();
        str.getClass();
        rejectedClientEventNonAuth.bitField0_ |= 2;
        rejectedClientEventNonAuth.eventName_ = str;
    }

    public static v22 g() {
        return (v22) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "rejectReason_", "eventName_"});
            case 3:
                return new RejectedClientEventNonAuth();
            case 4:
                return new v22(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (RejectedClientEventNonAuth.class) {
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
