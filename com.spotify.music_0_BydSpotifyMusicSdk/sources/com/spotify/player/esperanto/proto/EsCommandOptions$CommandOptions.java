package com.spotify.player.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.mf0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsCommandOptions$CommandOptions extends i implements yj1 {
    private static final EsCommandOptions$CommandOptions DEFAULT_INSTANCE;
    public static final int ONLY_FOR_LOCAL_DEVICE_FIELD_NUMBER = 2;
    public static final int ONLY_FOR_PLAYBACK_ID_FIELD_NUMBER = 4;
    public static final int OVERRIDE_RESTRICTIONS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int SYSTEM_INITIATED_FIELD_NUMBER = 3;
    private boolean onlyForLocalDevice_;
    private ByteString onlyForPlaybackId_ = ByteString.c;
    private boolean overrideRestrictions_;
    private boolean systemInitiated_;

    static {
        EsCommandOptions$CommandOptions esCommandOptions$CommandOptions = new EsCommandOptions$CommandOptions();
        DEFAULT_INSTANCE = esCommandOptions$CommandOptions;
        i.registerDefaultInstance(EsCommandOptions$CommandOptions.class, esCommandOptions$CommandOptions);
    }

    private EsCommandOptions$CommandOptions() {
    }

    public static void e(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions, boolean z) {
        esCommandOptions$CommandOptions.overrideRestrictions_ = z;
    }

    public static void f(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions, boolean z) {
        esCommandOptions$CommandOptions.onlyForLocalDevice_ = z;
    }

    public static void g(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions, boolean z) {
        esCommandOptions$CommandOptions.systemInitiated_ = z;
    }

    public static void h(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions, ByteString byteString) {
        esCommandOptions$CommandOptions.getClass();
        esCommandOptions$CommandOptions.onlyForPlaybackId_ = byteString;
    }

    public static mf0 i() {
        return (mf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\n", new Object[]{"overrideRestrictions_", "onlyForLocalDevice_", "systemInitiated_", "onlyForPlaybackId_"});
            case 3:
                return new EsCommandOptions$CommandOptions();
            case 4:
                return new mf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsCommandOptions$CommandOptions.class) {
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
