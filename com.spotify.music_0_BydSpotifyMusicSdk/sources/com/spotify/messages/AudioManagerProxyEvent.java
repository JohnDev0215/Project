package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.hd;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class AudioManagerProxyEvent extends i implements yj1 {
    public static final int ACCEPTS_DELAYED_FOCUS_GAIN_FIELD_NUMBER = 9;
    public static final int AUDIO_USAGE_FIELD_NUMBER = 8;
    public static final int COMPONENT_IDENTIFIER_FIELD_NUMBER = 1;
    public static final int CONTENT_TYPE_FIELD_NUMBER = 7;
    private static final AudioManagerProxyEvent DEFAULT_INSTANCE;
    public static final int FOCUS_CHANGE_FIELD_NUMBER = 5;
    public static final int FOCUS_GAIN_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int REQUEST_RESULT_FIELD_NUMBER = 3;
    public static final int SEND_REASON_FIELD_NUMBER = 2;
    public static final int STREAM_TYPE_FIELD_NUMBER = 6;
    private boolean acceptsDelayedFocusGain_;
    private int bitField0_;
    private String componentIdentifier_ = "";
    private String sendReason_ = "";
    private String requestResult_ = "";
    private String focusGain_ = "";
    private String focusChange_ = "";
    private String streamType_ = "";
    private String contentType_ = "";
    private String audioUsage_ = "";

    static {
        AudioManagerProxyEvent audioManagerProxyEvent = new AudioManagerProxyEvent();
        DEFAULT_INSTANCE = audioManagerProxyEvent;
        i.registerDefaultInstance(AudioManagerProxyEvent.class, audioManagerProxyEvent);
    }

    private AudioManagerProxyEvent() {
    }

    public static void e(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        str.getClass();
        audioManagerProxyEvent.bitField0_ |= 1;
        audioManagerProxyEvent.componentIdentifier_ = str;
    }

    public static void f(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        str.getClass();
        audioManagerProxyEvent.bitField0_ |= 8;
        audioManagerProxyEvent.focusGain_ = str;
    }

    public static void g(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        str.getClass();
        audioManagerProxyEvent.bitField0_ |= 16;
        audioManagerProxyEvent.focusChange_ = str;
    }

    public static void h(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        str.getClass();
        audioManagerProxyEvent.bitField0_ |= 64;
        audioManagerProxyEvent.contentType_ = str;
    }

    public static void i(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        str.getClass();
        audioManagerProxyEvent.bitField0_ |= 128;
        audioManagerProxyEvent.audioUsage_ = str;
    }

    public static void j(AudioManagerProxyEvent audioManagerProxyEvent, boolean z) {
        audioManagerProxyEvent.bitField0_ |= 256;
        audioManagerProxyEvent.acceptsDelayedFocusGain_ = z;
    }

    public static void k(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        audioManagerProxyEvent.bitField0_ |= 2;
        audioManagerProxyEvent.sendReason_ = str;
    }

    public static void l(AudioManagerProxyEvent audioManagerProxyEvent, String str) {
        audioManagerProxyEvent.getClass();
        str.getClass();
        audioManagerProxyEvent.bitField0_ |= 4;
        audioManagerProxyEvent.requestResult_ = str;
    }

    public static hd m() {
        return (hd) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဇ\b", new Object[]{"bitField0_", "componentIdentifier_", "sendReason_", "requestResult_", "focusGain_", "focusChange_", "streamType_", "contentType_", "audioUsage_", "acceptsDelayedFocusGain_"});
            case 3:
                return new AudioManagerProxyEvent();
            case 4:
                return new hd(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (AudioManagerProxyEvent.class) {
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
