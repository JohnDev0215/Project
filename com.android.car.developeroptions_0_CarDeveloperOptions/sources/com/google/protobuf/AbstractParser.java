package com.google.protobuf;

import com.google.protobuf.MessageLite;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private UninitializedMessageException newUninitializedMessageException(MessageType messagetype) {
        if (messagetype instanceof AbstractMessageLite) {
            return ((AbstractMessageLite) messagetype).newUninitializedMessageException();
        }
        return new UninitializedMessageException(messagetype);
    }

    private MessageType checkMessageInitialized(MessageType messagetype) throws InvalidProtocolBufferException {
        if (messagetype == null || messagetype.isInitialized()) {
            return messagetype;
        }
        InvalidProtocolBufferException invalidProtocolBufferExceptionAsInvalidProtocolBufferException = newUninitializedMessageException(messagetype).asInvalidProtocolBufferException();
        invalidProtocolBufferExceptionAsInvalidProtocolBufferException.setUnfinishedMessage(messagetype);
        throw invalidProtocolBufferExceptionAsInvalidProtocolBufferException;
    }

    static {
        ExtensionRegistryLite.getEmptyRegistry();
    }

    public MessageType parsePartialFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
            MessageType partialFrom = parsePartialFrom(codedInputStreamNewCodedInput, extensionRegistryLite);
            try {
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return partialFrom;
            } catch (InvalidProtocolBufferException e) {
                e.setUnfinishedMessage(partialFrom);
                throw e;
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        MessageType messagetype = (MessageType) parsePartialFrom(byteString, extensionRegistryLite);
        checkMessageInitialized(messagetype);
        return messagetype;
    }
}
