package com.spotify.rcs.admin.grpc.v0.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Publish$BackendContext extends i implements yj1 {
    private static final Publish$BackendContext DEFAULT_INSTANCE;
    public static final int DYNAMIC_CONTEXT_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int SERVICE_NAME_FIELD_NUMBER = 2;
    public static final int STATIC_CONTEXT_FIELD_NUMBER = 3;
    public static final int SURFACE_METADATA_FIELD_NUMBER = 10;
    public static final int SYSTEM_FIELD_NUMBER = 1;
    private Publish$DynamicContext dynamicContext_;
    private StaticContext staticContext_;
    private SurfaceMetadata surfaceMetadata_;
    private String system_ = "";
    private String serviceName_ = "";

    public static final class StaticContext extends i implements yj1 {
        private static final StaticContext DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int SERVICE_NAME_FIELD_NUMBER = 2;
        public static final int SYSTEM_FIELD_NUMBER = 1;
        private String system_ = "";
        private String serviceName_ = "";

        static {
            StaticContext staticContext = new StaticContext();
            DEFAULT_INSTANCE = staticContext;
            i.registerDefaultInstance(StaticContext.class, staticContext);
        }

        private StaticContext() {
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"system_", "serviceName_"});
                case 3:
                    return new StaticContext();
                case 4:
                    return new b(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (StaticContext.class) {
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

    public static final class SurfaceMetadata extends i implements yj1 {
        public static final int BACKEND_SDK_VERSION_FIELD_NUMBER = 1;
        private static final SurfaceMetadata DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private String backendSdkVersion_ = "";

        static {
            SurfaceMetadata surfaceMetadata = new SurfaceMetadata();
            DEFAULT_INSTANCE = surfaceMetadata;
            i.registerDefaultInstance(SurfaceMetadata.class, surfaceMetadata);
        }

        private SurfaceMetadata() {
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"backendSdkVersion_"});
                case 3:
                    return new SurfaceMetadata();
                case 4:
                    return new c(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (SurfaceMetadata.class) {
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
        Publish$BackendContext publish$BackendContext = new Publish$BackendContext();
        DEFAULT_INSTANCE = publish$BackendContext;
        i.registerDefaultInstance(Publish$BackendContext.class, publish$BackendContext);
    }

    private Publish$BackendContext() {
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\n\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\t\u0004\t\n\t", new Object[]{"system_", "serviceName_", "staticContext_", "dynamicContext_", "surfaceMetadata_"});
            case 3:
                return new Publish$BackendContext();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Publish$BackendContext.class) {
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
