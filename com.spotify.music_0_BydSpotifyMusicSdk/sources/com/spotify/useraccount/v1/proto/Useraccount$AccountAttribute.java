package com.spotify.useraccount.v1.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.id0;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Useraccount$AccountAttribute extends i implements yj1 {
    public static final int BOOL_VALUE_FIELD_NUMBER = 2;
    private static final Useraccount$AccountAttribute DEFAULT_INSTANCE;
    public static final int LONG_VALUE_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 4;
    private int valueCase_ = 0;
    private Object value_;

    static {
        Useraccount$AccountAttribute useraccount$AccountAttribute = new Useraccount$AccountAttribute();
        DEFAULT_INSTANCE = useraccount$AccountAttribute;
        i.registerDefaultInstance(Useraccount$AccountAttribute.class, useraccount$AccountAttribute);
    }

    private Useraccount$AccountAttribute() {
    }

    public static Useraccount$AccountAttribute f() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        id0 id0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0002\u0004\u0003\u0000\u0000\u0000\u0002:\u0000\u00035\u0000\u0004Ȼ\u0000", new Object[]{"value_", "valueCase_"});
            case 3:
                return new Useraccount$AccountAttribute();
            case 4:
                return new qd0(id0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Useraccount$AccountAttribute.class) {
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
