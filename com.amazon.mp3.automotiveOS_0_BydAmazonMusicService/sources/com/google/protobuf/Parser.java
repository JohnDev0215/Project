package com.google.protobuf;

/* JADX INFO: loaded from: classes7.dex */
public interface Parser<MessageType> {
    MessageType parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

    MessageType parseFrom(byte[] data) throws InvalidProtocolBufferException;

    MessageType parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

    MessageType parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;
}
