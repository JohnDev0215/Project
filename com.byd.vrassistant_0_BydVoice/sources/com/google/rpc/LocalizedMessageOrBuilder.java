package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface LocalizedMessageOrBuilder extends MessageOrBuilder {
    String getLocale();

    ByteString getLocaleBytes();

    String getMessage();

    ByteString getMessageBytes();
}
