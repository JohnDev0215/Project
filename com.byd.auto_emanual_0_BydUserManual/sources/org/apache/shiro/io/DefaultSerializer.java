package org.apache.shiro.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSerializer<T> implements Serializer<T> {
    @Override // org.apache.shiro.io.Serializer
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            throw new IllegalArgumentException("argument cannot be null.");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Unable to serialize object [" + t + "].  In order for the DefaultSerializer to serialize this object, the [" + t.getClass().getName() + "] class must implement java.io.Serializable.", e);
        }
    }

    @Override // org.apache.shiro.io.Serializer
    public T deserialize(byte[] bArr) throws SerializationException {
        if (bArr == null) {
            throw new IllegalArgumentException("argument cannot be null.");
        }
        try {
            ClassResolvingObjectInputStream classResolvingObjectInputStream = new ClassResolvingObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bArr)));
            T t = (T) classResolvingObjectInputStream.readObject();
            classResolvingObjectInputStream.close();
            return t;
        } catch (Exception e) {
            throw new SerializationException("Unable to deserialize argument byte array.", e);
        }
    }
}
