package com.spotify.libs.connect.events.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.ud1;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class LogoutNonAuth extends i implements yj1 {
    private static final LogoutNonAuth DEFAULT_INSTANCE;
    public static final int LOGOUT_REASON_FIELD_NUMBER = 2;
    public static final int OFFLINE_USER_REMOVED_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int PROTOCOL_VERSION_FIELD_NUMBER = 1;
    public static final int STORED_CREDENTIALS_REMOVED_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean offlineUserRemoved_;
    private boolean storedCredentialsRemoved_;
    private String protocolVersion_ = "";
    private String logoutReason_ = "";

    static {
        LogoutNonAuth logoutNonAuth = new LogoutNonAuth();
        DEFAULT_INSTANCE = logoutNonAuth;
        i.registerDefaultInstance(LogoutNonAuth.class, logoutNonAuth);
    }

    private LogoutNonAuth() {
    }

    public static void e(LogoutNonAuth logoutNonAuth, String str) {
        logoutNonAuth.getClass();
        logoutNonAuth.bitField0_ |= 1;
        logoutNonAuth.protocolVersion_ = str;
    }

    public static void f(LogoutNonAuth logoutNonAuth, String str) {
        logoutNonAuth.getClass();
        str.getClass();
        logoutNonAuth.bitField0_ |= 2;
        logoutNonAuth.logoutReason_ = str;
    }

    public static void g(LogoutNonAuth logoutNonAuth, boolean z) {
        logoutNonAuth.bitField0_ |= 4;
        logoutNonAuth.storedCredentialsRemoved_ = z;
    }

    public static void h(LogoutNonAuth logoutNonAuth, boolean z) {
        logoutNonAuth.bitField0_ |= 8;
        logoutNonAuth.offlineUserRemoved_ = z;
    }

    public static ud1 i() {
        return (ud1) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"bitField0_", "protocolVersion_", "logoutReason_", "storedCredentialsRemoved_", "offlineUserRemoved_"});
            case 3:
                return new LogoutNonAuth();
            case 4:
                return new ud1(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (LogoutNonAuth.class) {
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
