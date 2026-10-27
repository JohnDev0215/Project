package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.n51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsResponseWithReasons$ResponseWithReasons extends i implements yj1 {
    private static final EsResponseWithReasons$ResponseWithReasons DEFAULT_INSTANCE;
    public static final int ERROR_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int REASONS_FIELD_NUMBER = 2;
    private int error_;
    private String reasons_ = "";

    public enum Error implements n51 {
        OK(0),
        FORBIDDEN(1),
        NOT_FOUND(2),
        CONFLICT(3),
        UNRECOGNIZED(-1);

        private final int value;

        Error(int i) {
            this.value = i;
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    static {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = new EsResponseWithReasons$ResponseWithReasons();
        DEFAULT_INSTANCE = esResponseWithReasons$ResponseWithReasons;
        i.registerDefaultInstance(EsResponseWithReasons$ResponseWithReasons.class, esResponseWithReasons$ResponseWithReasons);
    }

    private EsResponseWithReasons$ResponseWithReasons() {
    }

    public static EsResponseWithReasons$ResponseWithReasons g(byte[] bArr) {
        return (EsResponseWithReasons$ResponseWithReasons) i.parseFrom(DEFAULT_INSTANCE, bArr);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002Ȉ", new Object[]{"error_", "reasons_"});
            case 3:
                return new EsResponseWithReasons$ResponseWithReasons();
            case 4:
                return new f(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsResponseWithReasons$ResponseWithReasons.class) {
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

    public final Error e() {
        int i = this.error_;
        Error error = i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : Error.CONFLICT : Error.NOT_FOUND : Error.FORBIDDEN : Error.OK;
        return error == null ? Error.UNRECOGNIZED : error;
    }

    public final String f() {
        return this.reasons_;
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
