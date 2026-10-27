package org.objenesis.instantiator.android;

import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/* JADX INFO: loaded from: classes4.dex */
@Instantiator(Typology.SERIALIZATION)
public class AndroidSerializationInstantiator<T> implements ObjectInstantiator<T> {
    private final Method newInstanceMethod = getNewInstanceMethod();
    private final ObjectStreamClass objectStreamClass;
    private final Class<T> type;

    public AndroidSerializationInstantiator(Class<T> cls) {
        this.type = cls;
        try {
            try {
                this.objectStreamClass = (ObjectStreamClass) ObjectStreamClass.class.getMethod("lookupAny", Class.class).invoke(null, cls);
            } catch (IllegalAccessException e) {
                throw new ObjenesisException(e);
            } catch (InvocationTargetException e2) {
                throw new ObjenesisException(e2);
            }
        } catch (NoSuchMethodException e3) {
            throw new ObjenesisException(e3);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            Class<T> cls = this.type;
            return cls.cast(this.newInstanceMethod.invoke(this.objectStreamClass, cls));
        } catch (IllegalAccessException e) {
            throw new ObjenesisException(e);
        } catch (IllegalArgumentException e2) {
            throw new ObjenesisException(e2);
        } catch (InvocationTargetException e3) {
            throw new ObjenesisException(e3);
        }
    }

    private static Method getNewInstanceMethod() {
        try {
            Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, Class.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new ObjenesisException(e);
        } catch (RuntimeException e2) {
            throw new ObjenesisException(e2);
        }
    }
}
