package com.google.protobuf;

import p.ds1;
import p.i01;
import p.qd0;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Empty extends i implements yj1 {
    private static final Empty DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;

    static {
        Empty empty = new Empty();
        DEFAULT_INSTANCE = empty;
        i.registerDefaultInstance(Empty.class, empty);
    }

    private Empty() {
    }

    public static Empty f() {
        return DEFAULT_INSTANCE;
    }

    public static Empty g(byte[] bArr) {
        return (Empty) i.parseFrom(DEFAULT_INSTANCE, bArr);
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
                return new Empty();
            case 4:
                return new qd0();
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Empty.class) {
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
}
