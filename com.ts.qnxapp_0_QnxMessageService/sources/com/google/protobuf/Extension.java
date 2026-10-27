package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;

/* JADX INFO: loaded from: classes.dex */
public abstract class Extension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {

    protected enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public enum MessageType {
        PROTO1,
        PROTO2
    }

    protected abstract Object fromReflectionType(Object obj);

    public abstract Descriptors.FieldDescriptor getDescriptor();

    protected abstract ExtensionType getExtensionType();

    @Override // com.google.protobuf.ExtensionLite
    public abstract Message getMessageDefaultInstance();

    protected abstract Object singularFromReflectionType(Object obj);

    protected abstract Object singularToReflectionType(Object obj);

    protected abstract Object toReflectionType(Object obj);

    @Override // com.google.protobuf.ExtensionLite
    final boolean isLite() {
        return false;
    }

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }
}
