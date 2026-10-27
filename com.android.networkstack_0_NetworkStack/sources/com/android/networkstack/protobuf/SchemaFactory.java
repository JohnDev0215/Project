package com.android.networkstack.protobuf;

/* JADX INFO: loaded from: classes.dex */
interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
