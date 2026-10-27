package com.android.dx.mockito;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.auxiliary.TypeProxy;

/* JADX INFO: loaded from: classes.dex */
abstract class UnsafeAllocator {
    public abstract <T> T newInstance(Class<T> cls) throws Exception;

    UnsafeAllocator() {
    }

    public static UnsafeAllocator create() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            final Object obj = declaredField.get(null);
            final Method method = cls.getMethod("allocateInstance", Class.class);
            return new UnsafeAllocator() { // from class: com.android.dx.mockito.UnsafeAllocator.1
                @Override // com.android.dx.mockito.UnsafeAllocator
                public <T> T newInstance(Class<T> cls2) throws Exception {
                    return (T) method.invoke(obj, cls2);
                }
            };
        } catch (Exception unused) {
            try {
                try {
                    final Method declaredMethod = ObjectInputStream.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, Class.class, Class.class);
                    declaredMethod.setAccessible(true);
                    return new UnsafeAllocator() { // from class: com.android.dx.mockito.UnsafeAllocator.2
                        @Override // com.android.dx.mockito.UnsafeAllocator
                        public <T> T newInstance(Class<T> cls2) throws Exception {
                            return (T) declaredMethod.invoke(null, cls2, Object.class);
                        }
                    };
                } catch (Exception unused2) {
                    return new UnsafeAllocator() { // from class: com.android.dx.mockito.UnsafeAllocator.4
                        @Override // com.android.dx.mockito.UnsafeAllocator
                        public <T> T newInstance(Class<T> cls2) {
                            throw new UnsupportedOperationException("Cannot allocate " + cls2);
                        }
                    };
                }
            } catch (Exception unused3) {
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod2.setAccessible(true);
                final int iIntValue = ((Integer) declaredMethod2.invoke(null, Object.class)).intValue();
                final Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, Class.class, Integer.TYPE);
                declaredMethod3.setAccessible(true);
                return new UnsafeAllocator() { // from class: com.android.dx.mockito.UnsafeAllocator.3
                    @Override // com.android.dx.mockito.UnsafeAllocator
                    public <T> T newInstance(Class<T> cls2) throws Exception {
                        return (T) declaredMethod3.invoke(null, cls2, Integer.valueOf(iIntValue));
                    }
                };
            }
        }
    }
}
