package com.sun.xml.bind.v2.model.nav;

import com.sun.xml.bind.v2.runtime.Location;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public interface Navigator<T, C, F, M> {
    C asDecl(Class cls);

    C asDecl(T t);

    <P> T erasure(T t);

    T getBaseClass(T t, C c);

    Location getClassLocation(C c);

    String getClassName(C c);

    String getClassShortName(C c);

    T getComponentType(T t);

    F getDeclaredField(C c, String str);

    Collection<? extends F> getDeclaredFields(C c);

    Collection<? extends M> getDeclaredMethods(C c);

    C getDeclaringClassForField(F f);

    C getDeclaringClassForMethod(M m);

    F[] getEnumConstants(C c);

    Location getFieldLocation(F f);

    String getFieldName(F f);

    T getFieldType(F f);

    Location getMethodLocation(M m);

    String getMethodName(M m);

    T[] getMethodParameters(M m);

    String getPackageName(C c);

    T getPrimitive(Class cls);

    T getReturnType(M m);

    C getSuperClass(C c);

    T getTypeArgument(T t, int i);

    String getTypeName(T t);

    T getVoidType();

    boolean hasDefaultConstructor(C c);

    boolean isAbstract(C c);

    boolean isArray(T t);

    boolean isArrayButNotByteArray(T t);

    boolean isBridgeMethod(M m);

    boolean isEnum(C c);

    boolean isFinal(C c);

    boolean isFinalMethod(M m);

    boolean isInnerClass(C c);

    boolean isInterface(C c);

    boolean isOverriding(M m, C c);

    boolean isParameterizedType(T t);

    boolean isPrimitive(T t);

    boolean isPublicField(F f);

    boolean isPublicMethod(M m);

    boolean isSameType(T t, T t2);

    boolean isStaticField(F f);

    boolean isStaticMethod(M m);

    boolean isSubClassOf(T t, T t2);

    boolean isTransient(F f);

    C loadObjectFactory(C c, String str);

    T ref(Class cls);

    T use(C c);
}
