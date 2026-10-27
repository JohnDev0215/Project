package com.sun.xml.bind;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class AccessorFactoryImpl implements InternalAccessorFactory {
    private static AccessorFactoryImpl instance = new AccessorFactoryImpl();

    private AccessorFactoryImpl() {
    }

    public static AccessorFactoryImpl getInstance() {
        return instance;
    }

    @Override // com.sun.xml.bind.AccessorFactory
    public Accessor createFieldAccessor(Class cls, Field field, boolean z) {
        return z ? new Accessor.ReadOnlyFieldReflection(field) : new Accessor.FieldReflection(field);
    }

    @Override // com.sun.xml.bind.InternalAccessorFactory
    public Accessor createFieldAccessor(Class cls, Field field, boolean z, boolean z2) {
        return z ? new Accessor.ReadOnlyFieldReflection(field, z2) : new Accessor.FieldReflection(field, z2);
    }

    @Override // com.sun.xml.bind.AccessorFactory
    public Accessor createPropertyAccessor(Class cls, Method method, Method method2) {
        if (method == null) {
            return new Accessor.SetterOnlyReflection(method2);
        }
        if (method2 == null) {
            return new Accessor.GetterOnlyReflection(method);
        }
        return new Accessor.GetterSetterReflection(method, method2);
    }
}
