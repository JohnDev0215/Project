package org.apache.shiro.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class XmlSerializer implements Serializer {
    @Override // org.apache.shiro.io.Serializer
    public byte[] serialize(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("argument cannot be null.");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XMLEncoder xMLEncoder = new XMLEncoder(new BufferedOutputStream(byteArrayOutputStream));
        xMLEncoder.writeObject(obj);
        xMLEncoder.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.apache.shiro.io.Serializer
    public Object deserialize(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        XMLDecoder xMLDecoder = new XMLDecoder(new BufferedInputStream(new ByteArrayInputStream(bArr)));
        Object object = xMLDecoder.readObject();
        xMLDecoder.close();
        return object;
    }
}
