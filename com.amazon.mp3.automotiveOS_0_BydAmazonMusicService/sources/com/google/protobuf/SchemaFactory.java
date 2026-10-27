package com.google.protobuf;

/* JADX INFO: loaded from: classes7.dex */
interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> messageType);
}
