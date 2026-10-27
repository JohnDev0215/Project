package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface LocalizedTextOrBuilder extends MessageOrBuilder {
    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getText();

    ByteString getTextBytes();
}
