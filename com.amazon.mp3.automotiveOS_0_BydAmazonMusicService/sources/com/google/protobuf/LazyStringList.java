package com.google.protobuf;

import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public interface LazyStringList extends ProtocolStringList {
    void add(ByteString element);

    Object getRaw(int index);

    List<?> getUnderlyingElements();

    LazyStringList getUnmodifiableView();
}
