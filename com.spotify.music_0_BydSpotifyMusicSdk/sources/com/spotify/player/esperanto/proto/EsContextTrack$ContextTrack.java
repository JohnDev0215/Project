package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import java.util.Collections;
import java.util.Map;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.xf0;
import p.yf0;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextTrack$ContextTrack extends i implements yj1 {
    private static final EsContextTrack$ContextTrack DEFAULT_INSTANCE;
    public static final int METADATA_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int UID_FIELD_NUMBER = 2;
    public static final int URI_FIELD_NUMBER = 1;
    private MapFieldLite<String, String> metadata_ = MapFieldLite.c;
    private String uri_ = "";
    private String uid_ = "";

    static {
        EsContextTrack$ContextTrack esContextTrack$ContextTrack = new EsContextTrack$ContextTrack();
        DEFAULT_INSTANCE = esContextTrack$ContextTrack;
        i.registerDefaultInstance(EsContextTrack$ContextTrack.class, esContextTrack$ContextTrack);
    }

    private EsContextTrack$ContextTrack() {
    }

    public static void e(EsContextTrack$ContextTrack esContextTrack$ContextTrack, String str) {
        esContextTrack$ContextTrack.getClass();
        str.getClass();
        esContextTrack$ContextTrack.uri_ = str;
    }

    public static void f(EsContextTrack$ContextTrack esContextTrack$ContextTrack, String str) {
        esContextTrack$ContextTrack.getClass();
        str.getClass();
        esContextTrack$ContextTrack.uid_ = str;
    }

    public static MapFieldLite g(EsContextTrack$ContextTrack esContextTrack$ContextTrack) {
        if (!esContextTrack$ContextTrack.metadata_.e()) {
            esContextTrack$ContextTrack.metadata_ = esContextTrack$ContextTrack.metadata_.g();
        }
        return esContextTrack$ContextTrack.metadata_;
    }

    public static EsContextTrack$ContextTrack h() {
        return DEFAULT_INSTANCE;
    }

    public static xf0 m() {
        return (xf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0001Ȉ\u0002Ȉ\u00032", new Object[]{"uri_", "uid_", "metadata_", yf0.a});
            case 3:
                return new EsContextTrack$ContextTrack();
            case 4:
                return new xf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextTrack$ContextTrack.class) {
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

    public final int i() {
        return this.metadata_.size();
    }

    public final Map j() {
        return Collections.unmodifiableMap(this.metadata_);
    }

    public final String k() {
        return this.uid_;
    }

    public final String l() {
        return this.uri_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
