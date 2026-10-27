package com.spotify.rcs.resolver.grpc.v0.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import java.util.ArrayList;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Resolve$Context extends com.google.protobuf.i implements yj1 {
    public static final int CONTEXT_FIELD_NUMBER = 1;
    private static final Resolve$Context DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;
    private v51 context_ = com.google.protobuf.i.emptyProtobufList();

    public static final class ContextEntry extends com.google.protobuf.i implements yj1 {
        private static final ContextEntry DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int POLICY_INPUT_NAME_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 10;
        private Object context_;
        private int contextCase_ = 0;
        private String value_ = "";

        static {
            ContextEntry contextEntry = new ContextEntry();
            DEFAULT_INSTANCE = contextEntry;
            com.google.protobuf.i.registerDefaultInstance(ContextEntry.class, contextEntry);
        }

        private ContextEntry() {
        }

        public static void e(ContextEntry contextEntry, String str) {
            contextEntry.getClass();
            str.getClass();
            contextEntry.contextCase_ = 2;
            contextEntry.context_ = str;
        }

        public static void f(ContextEntry contextEntry, String str) {
            contextEntry.getClass();
            str.getClass();
            contextEntry.value_ = str;
        }

        public static h g() {
            return (h) DEFAULT_INSTANCE.createBuilder();
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
                    return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0002\n\u0002\u0000\u0000\u0000\u0002Ȼ\u0000\nȈ", new Object[]{"context_", "contextCase_", "value_"});
                case 3:
                    return new ContextEntry();
                case 4:
                    return new h(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (ContextEntry.class) {
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

    static {
        Resolve$Context resolve$Context = new Resolve$Context();
        DEFAULT_INSTANCE = resolve$Context;
        com.google.protobuf.i.registerDefaultInstance(Resolve$Context.class, resolve$Context);
    }

    private Resolve$Context() {
    }

    public static void e(Resolve$Context resolve$Context, ArrayList arrayList) {
        v51 v51Var = resolve$Context.context_;
        if (!((a2) v51Var).c) {
            resolve$Context.context_ = com.google.protobuf.i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) arrayList, (List) resolve$Context.context_);
    }

    public static g f() {
        return (g) DEFAULT_INSTANCE.createBuilder();
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
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"context_", ContextEntry.class});
            case 3:
                return new Resolve$Context();
            case 4:
                return new g(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Resolve$Context.class) {
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
