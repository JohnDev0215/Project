package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import java.util.ArrayList;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.nf0;
import p.of0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContext$Context extends i implements yj1 {
    private static final EsContext$Context DEFAULT_INSTANCE;
    public static final int IS_LOADING_FIELD_NUMBER = 5;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int PAGES_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int RESTRICTIONS_FIELD_NUMBER = 6;
    public static final int URI_FIELD_NUMBER = 3;
    public static final int URL_FIELD_NUMBER = 4;
    private boolean isLoading_;
    private EsRestrictions$Restrictions restrictions_;
    private MapFieldLite<String, String> metadata_ = MapFieldLite.c;
    private v51 pages_ = i.emptyProtobufList();
    private String uri_ = "";
    private String url_ = "";

    static {
        EsContext$Context esContext$Context = new EsContext$Context();
        DEFAULT_INSTANCE = esContext$Context;
        i.registerDefaultInstance(EsContext$Context.class, esContext$Context);
    }

    private EsContext$Context() {
    }

    public static void e(EsContext$Context esContext$Context, String str) {
        esContext$Context.getClass();
        str.getClass();
        esContext$Context.url_ = str;
    }

    public static void f(EsContext$Context esContext$Context, boolean z) {
        esContext$Context.isLoading_ = z;
    }

    public static void g(EsContext$Context esContext$Context, EsRestrictions$Restrictions esRestrictions$Restrictions) {
        esContext$Context.getClass();
        esContext$Context.restrictions_ = esRestrictions$Restrictions;
    }

    public static void h(EsContext$Context esContext$Context, ArrayList arrayList) {
        v51 v51Var = esContext$Context.pages_;
        if (!((a2) v51Var).c) {
            esContext$Context.pages_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) esContext$Context.pages_);
    }

    public static MapFieldLite i(EsContext$Context esContext$Context) {
        if (!esContext$Context.metadata_.e()) {
            esContext$Context.metadata_ = esContext$Context.metadata_.g();
        }
        return esContext$Context.metadata_;
    }

    public static void j(EsContext$Context esContext$Context, String str) {
        esContext$Context.getClass();
        str.getClass();
        esContext$Context.uri_ = str;
    }

    public static nf0 k() {
        return (nf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0001\u0001\u0000\u0001\u001b\u00022\u0003Ȉ\u0004Ȉ\u0005\u0007\u0006\t", new Object[]{"pages_", EsContextPage$ContextPage.class, "metadata_", of0.a, "uri_", "url_", "isLoading_", "restrictions_"});
            case 3:
                return new EsContext$Context();
            case 4:
                return new nf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContext$Context.class) {
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
