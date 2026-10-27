package com.spotify.contexts;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.bb;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ApplicationAndroid extends i implements yj1 {
    private static final ApplicationAndroid DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int VERSION_CODE_FIELD_NUMBER = 2;
    public static final int VERSION_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private long versionCode_;
    private String versionName_ = "";

    static {
        ApplicationAndroid applicationAndroid = new ApplicationAndroid();
        DEFAULT_INSTANCE = applicationAndroid;
        i.registerDefaultInstance(ApplicationAndroid.class, applicationAndroid);
    }

    private ApplicationAndroid() {
    }

    public static void e(ApplicationAndroid applicationAndroid, String str) {
        applicationAndroid.getClass();
        str.getClass();
        applicationAndroid.bitField0_ |= 1;
        applicationAndroid.versionName_ = str;
    }

    public static void f(ApplicationAndroid applicationAndroid, long j) {
        applicationAndroid.bitField0_ |= 2;
        applicationAndroid.versionCode_ = j;
    }

    public static bb g() {
        return (bb) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"bitField0_", "versionName_", "versionCode_"});
            case 3:
                return new ApplicationAndroid();
            case 4:
                return new bb(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ApplicationAndroid.class) {
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
