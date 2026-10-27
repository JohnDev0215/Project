package com.google.firebase.messaging;

import com.google.firebase.encoders.proto.ProtobufEncoder;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ProtoEncoderDoNotUse {
    private static final ProtobufEncoder ENCODER = ProtobufEncoder.builder().configureWith(AutoProtoEncoderDoNotUseEncoder.CONFIG).build();

    public abstract MessagingClientEventExtension getMessagingClientEventExtension();

    private ProtoEncoderDoNotUse() {
    }

    public static byte[] encode(Object obj) {
        return ENCODER.encode(obj);
    }
}
