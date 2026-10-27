package com.spotify.contexts;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.j90;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceAndroid extends i implements yj1 {
    public static final int ANDROID_ID_FIELD_NUMBER = 5;
    private static final DeviceAndroid DEFAULT_INSTANCE;
    public static final int MANUFACTURER_FIELD_NUMBER = 1;
    public static final int MODEL_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int RELEASE_FIELD_NUMBER = 3;
    public static final int SDK_FIELD_NUMBER = 4;
    private int bitField0_;
    private int sdk_;
    private String manufacturer_ = "";
    private String model_ = "";
    private String release_ = "";
    private String androidId_ = "";

    static {
        DeviceAndroid deviceAndroid = new DeviceAndroid();
        DEFAULT_INSTANCE = deviceAndroid;
        i.registerDefaultInstance(DeviceAndroid.class, deviceAndroid);
    }

    private DeviceAndroid() {
    }

    public static void e(DeviceAndroid deviceAndroid, String str) {
        deviceAndroid.getClass();
        str.getClass();
        deviceAndroid.bitField0_ |= 1;
        deviceAndroid.manufacturer_ = str;
    }

    public static void f(DeviceAndroid deviceAndroid, int i) {
        deviceAndroid.bitField0_ |= 8;
        deviceAndroid.sdk_ = i;
    }

    public static void g(DeviceAndroid deviceAndroid, String str) {
        deviceAndroid.getClass();
        str.getClass();
        deviceAndroid.bitField0_ |= 16;
        deviceAndroid.androidId_ = str;
    }

    public static void h(DeviceAndroid deviceAndroid, String str) {
        deviceAndroid.getClass();
        str.getClass();
        deviceAndroid.bitField0_ |= 2;
        deviceAndroid.model_ = str;
    }

    public static void i(DeviceAndroid deviceAndroid, String str) {
        deviceAndroid.getClass();
        str.getClass();
        deviceAndroid.bitField0_ |= 4;
        deviceAndroid.release_ = str;
    }

    public static j90 k() {
        return (j90) DEFAULT_INSTANCE.createBuilder();
    }

    public static DeviceAndroid l(ByteString byteString) {
        return (DeviceAndroid) i.parseFrom(DEFAULT_INSTANCE, byteString);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003\u0005ဈ\u0004", new Object[]{"bitField0_", "manufacturer_", "model_", "release_", "sdk_", "androidId_"});
            case 3:
                return new DeviceAndroid();
            case 4:
                return new j90(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (DeviceAndroid.class) {
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

    public final String j() {
        return this.androidId_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
