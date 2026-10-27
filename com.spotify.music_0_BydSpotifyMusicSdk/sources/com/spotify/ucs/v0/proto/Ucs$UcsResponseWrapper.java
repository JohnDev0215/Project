package com.spotify.ucs.v0.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$ResolveResponse;
import com.spotify.useraccount.v1.proto.Useraccount$AccountAttribute;
import java.util.Collections;
import java.util.Map;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Ucs$UcsResponseWrapper extends i implements yj1 {
    private static final Ucs$UcsResponseWrapper DEFAULT_INSTANCE;
    public static final int ERROR_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SUCCESS_FIELD_NUMBER = 1;
    private int resultCase_ = 0;
    private Object result_;

    public static final class AccountAttributesResponse extends i implements yj1 {
        public static final int ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 1;
        private static final AccountAttributesResponse DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private MapFieldLite<String, Useraccount$AccountAttribute> accountAttributes_ = MapFieldLite.c;

        static {
            AccountAttributesResponse accountAttributesResponse = new AccountAttributesResponse();
            DEFAULT_INSTANCE = accountAttributesResponse;
            i.registerDefaultInstance(AccountAttributesResponse.class, accountAttributesResponse);
        }

        private AccountAttributesResponse() {
        }

        public static AccountAttributesResponse f() {
            return DEFAULT_INSTANCE;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"accountAttributes_", d.a});
                case 3:
                    return new AccountAttributesResponse();
                case 4:
                    return new e(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AccountAttributesResponse.class) {
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

        public final Map e() {
            return Collections.unmodifiableMap(this.accountAttributes_);
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

    public static final class Error extends i implements yj1 {
        private static final Error DEFAULT_INSTANCE;
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER;
        private int errorCode_;
        private String errorMessage_ = "";

        static {
            Error error = new Error();
            DEFAULT_INSTANCE = error;
            i.registerDefaultInstance(Error.class, error);
        }

        private Error() {
        }

        public static Error e() {
            return DEFAULT_INSTANCE;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002Ȉ", new Object[]{"errorCode_", "errorMessage_"});
                case 3:
                    return new Error();
                case 4:
                    return new g(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (Error.class) {
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

        public final String getErrorMessage() {
            return this.errorMessage_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    public static final class UcsResponse extends i implements yj1 {
        public static final int ACCOUNT_ATTRIBUTES_ERROR_FIELD_NUMBER = 4;
        public static final int ACCOUNT_ATTRIBUTES_SUCCESS_FIELD_NUMBER = 3;
        private static final UcsResponse DEFAULT_INSTANCE;
        public static final int FETCH_TIME_MILLIS_FIELD_NUMBER = 5;
        private static volatile ds1 PARSER = null;
        public static final int RESOLVE_ERROR_FIELD_NUMBER = 2;
        public static final int RESOLVE_SUCCESS_FIELD_NUMBER = 1;
        private Object accountAttributesResult_;
        private long fetchTimeMillis_;
        private Object resolveResult_;
        private int resolveResultCase_ = 0;
        private int accountAttributesResultCase_ = 0;

        static {
            UcsResponse ucsResponse = new UcsResponse();
            DEFAULT_INSTANCE = ucsResponse;
            i.registerDefaultInstance(UcsResponse.class, ucsResponse);
        }

        private UcsResponse() {
        }

        public static UcsResponse f() {
            return DEFAULT_INSTANCE;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0002\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0001\u0004<\u0001\u0005\u0002", new Object[]{"resolveResult_", "resolveResultCase_", "accountAttributesResult_", "accountAttributesResultCase_", Resolve$ResolveResponse.class, Error.class, AccountAttributesResponse.class, Error.class, "fetchTimeMillis_"});
                case 3:
                    return new UcsResponse();
                case 4:
                    return new h(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (UcsResponse.class) {
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

        public final AccountAttributesResponse e() {
            return this.accountAttributesResultCase_ == 3 ? (AccountAttributesResponse) this.accountAttributesResult_ : AccountAttributesResponse.f();
        }

        public final Resolve$ResolveResponse g() {
            return this.resolveResultCase_ == 1 ? (Resolve$ResolveResponse) this.resolveResult_ : Resolve$ResolveResponse.g();
        }

        @Override // com.google.protobuf.i, p.yj1
        public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        public final boolean h() {
            return this.accountAttributesResultCase_ == 3;
        }

        public final boolean i() {
            return this.resolveResultCase_ == 1;
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    static {
        Ucs$UcsResponseWrapper ucs$UcsResponseWrapper = new Ucs$UcsResponseWrapper();
        DEFAULT_INSTANCE = ucs$UcsResponseWrapper;
        i.registerDefaultInstance(Ucs$UcsResponseWrapper.class, ucs$UcsResponseWrapper);
    }

    private Ucs$UcsResponseWrapper() {
    }

    public static Ucs$UcsResponseWrapper e() {
        return DEFAULT_INSTANCE;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"result_", "resultCase_", UcsResponse.class, Error.class});
            case 3:
                return new Ucs$UcsResponseWrapper();
            case 4:
                return new f(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Ucs$UcsResponseWrapper.class) {
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

    public final Error f() {
        return this.resultCase_ == 2 ? (Error) this.result_ : Error.e();
    }

    public final UcsResponse g() {
        return this.resultCase_ == 1 ? (UcsResponse) this.result_ : UcsResponse.f();
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final boolean hasError() {
        return this.resultCase_ == 2;
    }

    public final boolean hasSuccess() {
        return this.resultCase_ == 1;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
