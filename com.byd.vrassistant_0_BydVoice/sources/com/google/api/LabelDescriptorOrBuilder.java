package com.google.api;

import com.google.api.LabelDescriptor;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface LabelDescriptorOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getKey();

    ByteString getKeyBytes();

    LabelDescriptor.ValueType getValueType();

    int getValueTypeValue();
}
