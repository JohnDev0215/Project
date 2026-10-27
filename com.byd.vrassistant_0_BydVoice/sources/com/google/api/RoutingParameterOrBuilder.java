package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface RoutingParameterOrBuilder extends MessageOrBuilder {
    String getField();

    ByteString getFieldBytes();

    String getPathTemplate();

    ByteString getPathTemplateBytes();
}
