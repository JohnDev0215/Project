package com.spotify.prefs.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.lg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPrefs$Value extends i implements yj1 {
    public static final int BOOL_FIELD_NUMBER = 2;
    private static final EsPrefs$Value DEFAULT_INSTANCE;
    public static final int NUMBER_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int STRING_FIELD_NUMBER = 3;
    private int valueCase_ = 0;
    private Object value_;

    static {
        EsPrefs$Value esPrefs$Value = new EsPrefs$Value();
        DEFAULT_INSTANCE = esPrefs$Value;
        i.registerDefaultInstance(EsPrefs$Value.class, esPrefs$Value);
    }

    private EsPrefs$Value() {
    }

    public static void e(EsPrefs$Value esPrefs$Value, long j) {
        esPrefs$Value.valueCase_ = 1;
        esPrefs$Value.value_ = Long.valueOf(j);
    }

    public static EsPrefs$Value f() {
        return DEFAULT_INSTANCE;
    }

    public static lg0 h() {
        return (lg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u00015\u0000\u0002:\u0000\u0003Ȼ\u0000", new Object[]{"value_", "valueCase_"});
            case 3:
                return new EsPrefs$Value();
            case 4:
                return new lg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPrefs$Value.class) {
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

    public final long g() {
        if (this.valueCase_ == 1) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
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
