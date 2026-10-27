package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface EndpointOrBuilder extends MessageOrBuilder {
    @Deprecated
    String getAliases(int i);

    @Deprecated
    ByteString getAliasesBytes(int i);

    @Deprecated
    int getAliasesCount();

    @Deprecated
    List<String> getAliasesList();

    boolean getAllowCors();

    String getName();

    ByteString getNameBytes();

    String getTarget();

    ByteString getTargetBytes();
}
