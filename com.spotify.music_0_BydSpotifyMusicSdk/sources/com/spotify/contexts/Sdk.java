package com.spotify.contexts;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.r72;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Sdk extends i implements yj1 {
    private static final Sdk DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 2;
    public static final int VERSION_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private String versionName_ = "";
    private String type_ = "";

    static {
        Sdk sdk = new Sdk();
        DEFAULT_INSTANCE = sdk;
        i.registerDefaultInstance(Sdk.class, sdk);
    }

    private Sdk() {
    }

    public static void e(Sdk sdk, String str) {
        sdk.getClass();
        str.getClass();
        sdk.bitField0_ |= 1;
        sdk.versionName_ = str;
    }

    public static void f(Sdk sdk) {
        sdk.getClass();
        sdk.bitField0_ |= 2;
        sdk.type_ = "java-android";
    }

    public static r72 g() {
        return (r72) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "versionName_", "type_"});
            case 3:
                return new Sdk();
            case 4:
                return new r72(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Sdk.class) {
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
