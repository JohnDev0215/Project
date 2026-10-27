package com.google.protobuf;

import com.google.protobuf.Descriptors;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public interface MessageOrBuilder extends MessageLiteOrBuilder {
    Map<Descriptors.FieldDescriptor, Object> getAllFields();

    Message getDefaultInstanceForType();

    Descriptors.Descriptor getDescriptorForType();

    Object getField(Descriptors.FieldDescriptor field);

    UnknownFieldSet getUnknownFields();

    boolean hasField(Descriptors.FieldDescriptor field);
}
