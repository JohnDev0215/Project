package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import java.util.ArrayList;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.pf0;
import p.qf0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextPage$ContextPage extends i implements yj1 {
    private static final EsContextPage$ContextPage DEFAULT_INSTANCE;
    public static final int IS_LOADING_FIELD_NUMBER = 5;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NEXT_PAGE_URL_FIELD_NUMBER = 4;
    public static final int PAGE_URL_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int TRACKS_FIELD_NUMBER = 1;
    private boolean isLoading_;
    private MapFieldLite<String, String> metadata_ = MapFieldLite.c;
    private v51 tracks_ = i.emptyProtobufList();
    private String pageUrl_ = "";
    private String nextPageUrl_ = "";

    static {
        EsContextPage$ContextPage esContextPage$ContextPage = new EsContextPage$ContextPage();
        DEFAULT_INSTANCE = esContextPage$ContextPage;
        i.registerDefaultInstance(EsContextPage$ContextPage.class, esContextPage$ContextPage);
    }

    private EsContextPage$ContextPage() {
    }

    public static void e(EsContextPage$ContextPage esContextPage$ContextPage, String str) {
        esContextPage$ContextPage.getClass();
        str.getClass();
        esContextPage$ContextPage.nextPageUrl_ = str;
    }

    public static void f(EsContextPage$ContextPage esContextPage$ContextPage, boolean z) {
        esContextPage$ContextPage.isLoading_ = z;
    }

    public static void g(EsContextPage$ContextPage esContextPage$ContextPage, ArrayList arrayList) {
        v51 v51Var = esContextPage$ContextPage.tracks_;
        if (!((a2) v51Var).c) {
            esContextPage$ContextPage.tracks_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) esContextPage$ContextPage.tracks_);
    }

    public static MapFieldLite h(EsContextPage$ContextPage esContextPage$ContextPage) {
        if (!esContextPage$ContextPage.metadata_.e()) {
            esContextPage$ContextPage.metadata_ = esContextPage$ContextPage.metadata_.g();
        }
        return esContextPage$ContextPage.metadata_;
    }

    public static void i(EsContextPage$ContextPage esContextPage$ContextPage, String str) {
        esContextPage$ContextPage.getClass();
        str.getClass();
        esContextPage$ContextPage.pageUrl_ = str;
    }

    public static pf0 j() {
        return (pf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0001\u0001\u0000\u0001\u001b\u00022\u0003Ȉ\u0004Ȉ\u0005\u0007", new Object[]{"tracks_", EsContextTrack$ContextTrack.class, "metadata_", qf0.a, "pageUrl_", "nextPageUrl_", "isLoading_"});
            case 3:
                return new EsContextPage$ContextPage();
            case 4:
                return new pf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPage$ContextPage.class) {
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
