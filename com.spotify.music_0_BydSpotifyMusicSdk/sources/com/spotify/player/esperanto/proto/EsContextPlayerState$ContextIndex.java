package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.hd0;
import p.i01;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsContextPlayerState$ContextIndex extends i implements yj1 {
    private static final EsContextPlayerState$ContextIndex DEFAULT_INSTANCE;
    public static final int PAGE_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int TRACK_FIELD_NUMBER = 2;
    private long page_;
    private long track_;

    static {
        EsContextPlayerState$ContextIndex esContextPlayerState$ContextIndex = new EsContextPlayerState$ContextIndex();
        DEFAULT_INSTANCE = esContextPlayerState$ContextIndex;
        i.registerDefaultInstance(EsContextPlayerState$ContextIndex.class, esContextPlayerState$ContextIndex);
    }

    private EsContextPlayerState$ContextIndex() {
    }

    public static EsContextPlayerState$ContextIndex f() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        hd0 hd0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0003\u0002\u0003", new Object[]{"page_", "track_"});
            case 3:
                return new EsContextPlayerState$ContextIndex();
            case 4:
                return new qd0(hd0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsContextPlayerState$ContextIndex.class) {
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
        return this.page_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final long h() {
        return this.track_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
