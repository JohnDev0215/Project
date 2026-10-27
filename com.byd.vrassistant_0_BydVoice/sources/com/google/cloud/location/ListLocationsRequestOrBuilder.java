package com.google.cloud.location;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface ListLocationsRequestOrBuilder extends MessageOrBuilder {
    String getFilter();

    ByteString getFilterBytes();

    String getName();

    ByteString getNameBytes();

    int getPageSize();

    String getPageToken();

    ByteString getPageTokenBytes();
}
