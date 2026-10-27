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
public final class EsRestrictions$ModeRestrictions extends i implements yj1 {
    private static final EsRestrictions$ModeRestrictions DEFAULT_INSTANCE;
    public static final int MODE_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int VALUE_REASONS_FIELD_NUMBER = 2;
    private String mode_ = "";
    private v51 valueReasons_ = i.emptyProtobufList();

    static {
        EsRestrictions$ModeRestrictions esRestrictions$ModeRestrictions = new EsRestrictions$ModeRestrictions();
        DEFAULT_INSTANCE = esRestrictions$ModeRestrictions;
        i.registerDefaultInstance(EsRestrictions$ModeRestrictions.class, esRestrictions$ModeRestrictions);
    }

    private EsRestrictions$ModeRestrictions() {
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"mode_", "valueReasons_", EsRestrictions$ModeValueRestrictions.class});
            case 3:
                return new EsRestrictions$ModeRestrictions();
            case 4:
                return new qd0(11, obj3);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsRestrictions$ModeRestrictions.class) {
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
