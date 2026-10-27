package com.spotify.prefs.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.hg0;
import p.i01;
import p.kd0;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPrefs$PrefValues extends i implements yj1 {
    private static final EsPrefs$PrefValues DEFAULT_INSTANCE;
    public static final int ENTRIES_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private MapFieldLite<String, EsPrefs$Value> entries_ = MapFieldLite.c;

    static {
        EsPrefs$PrefValues esPrefs$PrefValues = new EsPrefs$PrefValues();
        DEFAULT_INSTANCE = esPrefs$PrefValues;
        i.registerDefaultInstance(EsPrefs$PrefValues.class, esPrefs$PrefValues);
    }

    private EsPrefs$PrefValues() {
    }

    public static EsPrefs$PrefValues g(byte[] bArr) {
        return (EsPrefs$PrefValues) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        kd0 kd0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"entries_", hg0.a});
            case 3:
                return new EsPrefs$PrefValues();
            case 4:
                return new qd0(kd0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPrefs$PrefValues.class) {
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

    public final EsPrefs$Value f(String str) {
        str.getClass();
        MapFieldLite<String, EsPrefs$Value> mapFieldLite = this.entries_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
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
