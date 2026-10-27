package org.apache.shiro.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.util.UnknownClassException;

/* JADX INFO: loaded from: classes.dex */
public class ClassResolvingObjectInputStream extends ObjectInputStream {
    public ClassResolvingObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException, IOException {
        try {
            return ClassUtils.forName(objectStreamClass.getName());
        } catch (UnknownClassException e) {
            throw new ClassNotFoundException("Unable to load ObjectStreamClass [" + objectStreamClass + "]: ", e);
        }
    }
}
