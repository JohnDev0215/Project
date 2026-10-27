package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class SerializationUtils {
    public static <T extends Serializable> T clone(T t) throws Throwable {
        ClassLoaderAwareObjectInputStream classLoaderAwareObjectInputStream = null;
        if (t == null) {
            return null;
        }
        try {
            try {
                ClassLoaderAwareObjectInputStream classLoaderAwareObjectInputStream2 = new ClassLoaderAwareObjectInputStream(new ByteArrayInputStream(serialize(t)), t.getClass().getClassLoader());
                try {
                    T t2 = (T) classLoaderAwareObjectInputStream2.readObject();
                    try {
                        classLoaderAwareObjectInputStream2.close();
                        return t2;
                    } catch (IOException e) {
                        throw new SerializationException("IOException on closing cloned object data InputStream.", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    throw new SerializationException("IOException while reading cloned object data", e);
                } catch (ClassNotFoundException e3) {
                    e = e3;
                    throw new SerializationException("ClassNotFoundException while reading cloned object data", e);
                } catch (Throwable th) {
                    th = th;
                    classLoaderAwareObjectInputStream = classLoaderAwareObjectInputStream2;
                    if (classLoaderAwareObjectInputStream != null) {
                        try {
                            classLoaderAwareObjectInputStream.close();
                        } catch (IOException e4) {
                            throw new SerializationException("IOException on closing cloned object data InputStream.", e4);
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (ClassNotFoundException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static <T extends Serializable> T roundtrip(T t) {
        return (T) deserialize(serialize(t));
    }

    public static void serialize(Serializable serializable, OutputStream outputStream) throws Throwable {
        if (outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(outputStream);
                try {
                    objectOutputStream2.writeObject(serializable);
                    try {
                        objectOutputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (IOException e) {
                    e = e;
                    objectOutputStream = objectOutputStream2;
                    throw new SerializationException(e);
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static byte[] serialize(Serializable serializable) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        serialize(serializable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T deserialize(InputStream inputStream) throws Throwable {
        if (inputStream == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        }
        ObjectInputStream objectInputStream = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
                try {
                    T t = (T) objectInputStream2.readObject();
                    try {
                        objectInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return t;
                } catch (IOException e) {
                    e = e;
                    throw new SerializationException(e);
                } catch (ClassNotFoundException e2) {
                    e = e2;
                    throw new SerializationException(e);
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (ClassNotFoundException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static <T> T deserialize(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The byte[] must not be null");
        }
        return (T) deserialize(new ByteArrayInputStream(bArr));
    }

    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes;
        private final ClassLoader classLoader;

        static {
            HashMap map = new HashMap();
            primitiveTypes = map;
            map.put("byte", Byte.TYPE);
            map.put("short", Short.TYPE);
            map.put("int", Integer.TYPE);
            map.put("long", Long.TYPE);
            map.put("float", Float.TYPE);
            map.put("double", Double.TYPE);
            map.put("boolean", Boolean.TYPE);
            map.put("char", Character.TYPE);
            map.put("void", Void.TYPE);
        }

        public ClassLoaderAwareObjectInputStream(InputStream inputStream, ClassLoader classLoader) throws IOException {
            super(inputStream);
            this.classLoader = classLoader;
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException, IOException {
            String name = objectStreamClass.getName();
            try {
                try {
                    return Class.forName(name, false, this.classLoader);
                } catch (ClassNotFoundException unused) {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                }
            } catch (ClassNotFoundException e) {
                Class<?> cls = primitiveTypes.get(name);
                if (cls != null) {
                    return cls;
                }
                throw e;
            }
        }
    }
}
