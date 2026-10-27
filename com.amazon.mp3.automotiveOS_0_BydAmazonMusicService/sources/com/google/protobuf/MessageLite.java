package com.google.protobuf;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        MessageLite build();

        MessageLite buildPartial();

        Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

        Builder mergeFrom(MessageLite other);
    }

    Parser<? extends MessageLite> getParserForType();

    int getSerializedSize();

    Builder newBuilderForType();

    Builder toBuilder();

    ByteString toByteString();

    void writeTo(CodedOutputStream output) throws IOException;
}
