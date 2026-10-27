package com.google.protobuf;

import com.google.protobuf.MessageLite;

/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    private UninitializedMessageException newUninitializedMessageException(MessageType message) {
        if (message instanceof AbstractMessageLite) {
            return ((AbstractMessageLite) message).newUninitializedMessageException();
        }
        return new UninitializedMessageException(message);
    }

    private MessageType checkMessageInitialized(MessageType message) throws InvalidProtocolBufferException {
        if (message == null || message.isInitialized()) {
            return message;
        }
        throw newUninitializedMessageException(message).asInvalidProtocolBufferException().setUnfinishedMessage(message);
    }

    public MessageType parsePartialFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        try {
            CodedInputStream codedInputStreamNewCodedInput = data.newCodedInput();
            MessageType partialFrom = parsePartialFrom(codedInputStreamNewCodedInput, extensionRegistry);
            try {
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return partialFrom;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(partialFrom);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MessageType) checkMessageInitialized(parsePartialFrom(byteString, extensionRegistryLite));
    }

    public MessageType parsePartialFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        try {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(data, off, len);
            MessageType partialFrom = parsePartialFrom(codedInputStreamNewInstance, extensionRegistry);
            try {
                codedInputStreamNewInstance.checkLastTagWas(0);
                return partialFrom;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(partialFrom);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    public MessageType parseFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MessageType) checkMessageInitialized(parsePartialFrom(bArr, i, i2, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MessageType) parseFrom(bArr, 0, bArr.length, extensionRegistryLite);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MessageType) parseFrom(bArr, EMPTY_REGISTRY);
    }
}
