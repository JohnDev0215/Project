package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.gn;
import p.hn;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class BootstrapRequestNonAuth extends i implements yj1 {
    private static final BootstrapRequestNonAuth DEFAULT_INSTANCE;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 5;
    public static final int ERROR_REASON_FIELD_NUMBER = 4;
    public static final int FETCHED_REASON_FIELD_NUMBER = 8;
    public static final int IDENTIFIERS_FIELD_NUMBER = 9;
    public static final int INSTALLATION_ID_FIELD_NUMBER = 6;
    public static final int LATENCY_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PAYLOAD_SIZE_FIELD_NUMBER = 2;
    public static final int RESPONSE_PAYLOAD_FIELD_NUMBER = 7;
    public static final int STATUS_CODE_FIELD_NUMBER = 3;
    private int bitField0_;
    private long latency_;
    private long payloadSize_;
    private int statusCode_;
    private MapFieldLite<String, String> identifiers_ = MapFieldLite.c;
    private String errorReason_ = "";
    private String errorMessage_ = "";
    private String installationId_ = "";
    private String responsePayload_ = "";
    private String fetchedReason_ = "";

    static {
        BootstrapRequestNonAuth bootstrapRequestNonAuth = new BootstrapRequestNonAuth();
        DEFAULT_INSTANCE = bootstrapRequestNonAuth;
        i.registerDefaultInstance(BootstrapRequestNonAuth.class, bootstrapRequestNonAuth);
    }

    private BootstrapRequestNonAuth() {
    }

    public static void e(BootstrapRequestNonAuth bootstrapRequestNonAuth, long j) {
        bootstrapRequestNonAuth.bitField0_ |= 1;
        bootstrapRequestNonAuth.latency_ = j;
    }

    public static void f(BootstrapRequestNonAuth bootstrapRequestNonAuth, String str) {
        bootstrapRequestNonAuth.getClass();
        str.getClass();
        bootstrapRequestNonAuth.bitField0_ |= 16;
        bootstrapRequestNonAuth.errorMessage_ = str;
    }

    public static void g(BootstrapRequestNonAuth bootstrapRequestNonAuth, String str) {
        bootstrapRequestNonAuth.getClass();
        str.getClass();
        bootstrapRequestNonAuth.bitField0_ |= 64;
        bootstrapRequestNonAuth.responsePayload_ = str;
    }

    public static void h(BootstrapRequestNonAuth bootstrapRequestNonAuth, String str) {
        bootstrapRequestNonAuth.getClass();
        bootstrapRequestNonAuth.bitField0_ |= 128;
        bootstrapRequestNonAuth.fetchedReason_ = str;
    }

    public static MapFieldLite i(BootstrapRequestNonAuth bootstrapRequestNonAuth) {
        if (!bootstrapRequestNonAuth.identifiers_.e()) {
            bootstrapRequestNonAuth.identifiers_ = bootstrapRequestNonAuth.identifiers_.g();
        }
        return bootstrapRequestNonAuth.identifiers_;
    }

    public static void j(BootstrapRequestNonAuth bootstrapRequestNonAuth, long j) {
        bootstrapRequestNonAuth.bitField0_ |= 2;
        bootstrapRequestNonAuth.payloadSize_ = j;
    }

    public static void k(BootstrapRequestNonAuth bootstrapRequestNonAuth, int i) {
        bootstrapRequestNonAuth.bitField0_ |= 4;
        bootstrapRequestNonAuth.statusCode_ = i;
    }

    public static void l(BootstrapRequestNonAuth bootstrapRequestNonAuth, String str) {
        bootstrapRequestNonAuth.getClass();
        bootstrapRequestNonAuth.bitField0_ |= 8;
        bootstrapRequestNonAuth.errorReason_ = str;
    }

    public static gn m() {
        return (gn) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\t\t\u0001\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003င\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\t2", new Object[]{"bitField0_", "latency_", "payloadSize_", "statusCode_", "errorReason_", "errorMessage_", "installationId_", "responsePayload_", "fetchedReason_", "identifiers_", hn.a});
            case 3:
                return new BootstrapRequestNonAuth();
            case 4:
                return new gn(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (BootstrapRequestNonAuth.class) {
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
