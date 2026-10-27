package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.qd0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsRestrictions$ModeValueRestrictions extends i implements yj1 {
    private static final EsRestrictions$ModeValueRestrictions DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int REASONS_FIELD_NUMBER = 2;
    public static final int VALUE_FIELD_NUMBER = 1;
    private String value_ = "";
    private v51 reasons_ = i.emptyProtobufList();

    static {
        EsRestrictions$ModeValueRestrictions esRestrictions$ModeValueRestrictions = new EsRestrictions$ModeValueRestrictions();
        DEFAULT_INSTANCE = esRestrictions$ModeValueRestrictions;
        i.registerDefaultInstance(EsRestrictions$ModeValueRestrictions.class, esRestrictions$ModeValueRestrictions);
    }

    private EsRestrictions$ModeValueRestrictions() {
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        Object obj3 = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002Ț", new Object[]{"value_", "reasons_"});
            case 3:
                return new EsRestrictions$ModeValueRestrictions();
            case 4:
                return new qd0(12, obj3);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsRestrictions$ModeValueRestrictions.class) {
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
