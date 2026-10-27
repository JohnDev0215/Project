package com.sun.xml.bind.v2.model.core;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ClassInfo<T, C> extends MaybeElement<T, C> {
    boolean declaresAttributeWildcard();

    ClassInfo<T, C> getBaseClass();

    C getClazz();

    String getName();

    List<? extends PropertyInfo<T, C>> getProperties();

    PropertyInfo<T, C> getProperty(String str);

    boolean hasAttributeWildcard();

    boolean hasProperties();

    boolean hasSubClasses();

    boolean hasValueProperty();

    boolean inheritsAttributeWildcard();

    boolean isAbstract();

    boolean isFinal();

    boolean isOrdered();
}
