package com.spotify.eventsender;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.vw0;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Fragment extends i implements yj1 {
    public static final int DATA_FIELD_NUMBER = 2;
    private static final Fragment DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private String name_ = "";
    private ByteString data_ = ByteString.c;

    static {
        Fragment fragment = new Fragment();
        DEFAULT_INSTANCE = fragment;
        i.registerDefaultInstance(Fragment.class, fragment);
    }

    private Fragment() {
    }

    public static void e(Fragment fragment, String str) {
        fragment.getClass();
        fragment.bitField0_ |= 1;
        fragment.name_ = str;
    }

    public static void f(Fragment fragment, ByteString byteString) {
        fragment.getClass();
        fragment.bitField0_ |= 2;
        fragment.data_ = byteString;
    }

    public static vw0 g() {
        return (vw0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001", new Object[]{"bitField0_", "name_", "data_"});
            case 3:
                return new Fragment();
            case 4:
                return new vw0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Fragment.class) {
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

    public final ByteString getData() {
        return this.data_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final String getName() {
        return this.name_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
