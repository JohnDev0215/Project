package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.SerializerProvider;

/* JADX INFO: loaded from: classes6.dex */
public interface JsonFormatVisitorWithSerializerProvider {
    SerializerProvider getProvider();

    void setProvider(SerializerProvider serializerProvider);
}
