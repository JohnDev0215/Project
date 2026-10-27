package com.spotify.ucs.proto.v0;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import com.spotify.rcs.resolver.grpc.v0.ResolveRequest;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class UcsRequest extends i implements yj1 {
    public static final int ACCOUNT_ATTRIBUTES_REQUEST_FIELD_NUMBER = 3;
    public static final int CALLER_INFO_FIELD_NUMBER = 1;
    private static final UcsRequest DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int RESOLVE_REQUEST_FIELD_NUMBER = 2;
    private AccountAttributesRequest accountAttributesRequest_;
    private CallerInfo callerInfo_;
    private ResolveRequest resolveRequest_;

    public static final class AccountAttributesRequest extends i implements yj1 {
        private static final AccountAttributesRequest DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;

        static {
            AccountAttributesRequest accountAttributesRequest = new AccountAttributesRequest();
            DEFAULT_INSTANCE = accountAttributesRequest;
            i.registerDefaultInstance(AccountAttributesRequest.class, accountAttributesRequest);
        }

        private AccountAttributesRequest() {
        }

        public static a e() {
            return (a) DEFAULT_INSTANCE.createBuilder();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case 3:
                    return new AccountAttributesRequest();
                case 4:
                    return new a(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AccountAttributesRequest.class) {
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

    public static final class CallerInfo extends i implements yj1 {
        private static final CallerInfo DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int REASON_FIELD_NUMBER = 3;
        public static final int REQUEST_ORGIN_VERSION_FIELD_NUMBER = 2;
        public static final int REQUEST_ORIGIN_ID_FIELD_NUMBER = 1;
        private String requestOriginId_ = "";
        private String requestOrginVersion_ = "";
        private String reason_ = "";

        static {
            CallerInfo callerInfo = new CallerInfo();
            DEFAULT_INSTANCE = callerInfo;
            i.registerDefaultInstance(CallerInfo.class, callerInfo);
        }

        private CallerInfo() {
        }

        public static void e(CallerInfo callerInfo) {
            callerInfo.getClass();
            callerInfo.requestOriginId_ = "com.spotify.auto";
        }

        public static void f(CallerInfo callerInfo) {
            callerInfo.getClass();
            callerInfo.requestOrginVersion_ = "4.4.1.0";
        }

        public static void g(CallerInfo callerInfo, String str) {
            callerInfo.getClass();
            str.getClass();
            callerInfo.reason_ = str;
        }

        public static c h() {
            return (c) DEFAULT_INSTANCE.createBuilder();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"requestOriginId_", "requestOrginVersion_", "reason_"});
                case 3:
                    return new CallerInfo();
                case 4:
                    return new c(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CallerInfo.class) {
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

    static {
        UcsRequest ucsRequest = new UcsRequest();
        DEFAULT_INSTANCE = ucsRequest;
        i.registerDefaultInstance(UcsRequest.class, ucsRequest);
    }

    private UcsRequest() {
    }

    public static void e(UcsRequest ucsRequest, CallerInfo callerInfo) {
        ucsRequest.getClass();
        callerInfo.getClass();
        ucsRequest.callerInfo_ = callerInfo;
    }

    public static void f(UcsRequest ucsRequest, ResolveRequest resolveRequest) {
        ucsRequest.getClass();
        ucsRequest.resolveRequest_ = resolveRequest;
    }

    public static void g(UcsRequest ucsRequest, AccountAttributesRequest accountAttributesRequest) {
        ucsRequest.getClass();
        accountAttributesRequest.getClass();
        ucsRequest.accountAttributesRequest_ = accountAttributesRequest;
    }

    public static b h() {
        return (b) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t", new Object[]{"callerInfo_", "resolveRequest_", "accountAttributesRequest_"});
            case 3:
                return new UcsRequest();
            case 4:
                return new b(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (UcsRequest.class) {
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
