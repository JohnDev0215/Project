package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public interface Message extends MessageLite, MessageOrBuilder {

    public interface Builder extends MessageLite.Builder, MessageOrBuilder {
        Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value);

        Message build();

        Message buildPartial();

        @Override // com.google.protobuf.MessageOrBuilder
        Descriptors.Descriptor getDescriptorForType();

        Builder getFieldBuilder(Descriptors.FieldDescriptor field);

        Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException;

        Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

        Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

        Builder mergeFrom(Message other);

        Builder newBuilderForField(Descriptors.FieldDescriptor field);

        Builder setField(Descriptors.FieldDescriptor field, Object value);

        Builder setUnknownFields(UnknownFieldSet unknownFields);
    }

    /* synthetic */ int getSerializedSize();

    Builder newBuilderForType();

    Builder toBuilder();
}
