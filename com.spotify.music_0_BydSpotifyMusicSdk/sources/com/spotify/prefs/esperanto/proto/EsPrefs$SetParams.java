package com.spotify.prefs.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.ig0;
import p.jg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPrefs$SetParams extends i implements yj1 {
    private static final EsPrefs$SetParams DEFAULT_INSTANCE;
    public static final int ENTRIES_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private MapFieldLite<String, EsPrefs$Value> entries_ = MapFieldLite.c;

    static {
        EsPrefs$SetParams esPrefs$SetParams = new EsPrefs$SetParams();
        DEFAULT_INSTANCE = esPrefs$SetParams;
        i.registerDefaultInstance(EsPrefs$SetParams.class, esPrefs$SetParams);
    }

    private EsPrefs$SetParams() {
    }

    public static MapFieldLite e(EsPrefs$SetParams esPrefs$SetParams) {
        if (!esPrefs$SetParams.entries_.e()) {
            esPrefs$SetParams.entries_ = esPrefs$SetParams.entries_.g();
        }
        return esPrefs$SetParams.entries_;
    }

    public static ig0 f() {
        return (ig0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"entries_", jg0.a});
            case 3:
                return new EsPrefs$SetParams();
            case 4:
                return new ig0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPrefs$SetParams.class) {
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
