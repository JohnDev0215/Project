package com.spotify.rcs.resolver.grpc.v0.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import p.ds1;
import p.i01;
import p.n51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Resolve$Fetch extends com.google.protobuf.i implements yj1 {
    private static final Resolve$Fetch DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;

    public enum Type implements n51 {
        BLOCKING(0),
        /* JADX INFO: Fake field, exist only in values array */
        BACKGROUND_SYNC(1),
        /* JADX INFO: Fake field, exist only in values array */
        ASYNC(2),
        UNRECOGNIZED(-1);

        private final int value;

        Type(int i) {
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
        Resolve$Fetch resolve$Fetch = new Resolve$Fetch();
        DEFAULT_INSTANCE = resolve$Fetch;
        com.google.protobuf.i.registerDefaultInstance(Resolve$Fetch.class, resolve$Fetch);
    }

    private Resolve$Fetch() {
    }

    public static void e(Resolve$Fetch resolve$Fetch) {
        Type type = Type.BLOCKING;
        resolve$Fetch.getClass();
        resolve$Fetch.type_ = type.getNumber();
    }

    public static i f() {
        return (i) DEFAULT_INSTANCE.createBuilder();
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
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"type_"});
            case 3:
                return new Resolve$Fetch();
            case 4:
                return new i(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Resolve$Fetch.class) {
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
