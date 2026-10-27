package org.apache.shiro.io;

/* JADX INFO: loaded from: classes.dex */
public interface Serializer<T> {
    T deserialize(byte[] bArr) throws SerializationException;

    byte[] serialize(T t) throws SerializationException;
}
