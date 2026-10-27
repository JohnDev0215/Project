package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeArrayInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.runtime.Transducer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
final class RuntimeArrayInfoImpl extends ArrayInfoImpl<Type, Class, Field, Method> implements RuntimeArrayInfo {
    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeNonElement
    public <V> Transducer<V> getTransducer() {
        return null;
    }

    RuntimeArrayInfoImpl(RuntimeModelBuilder runtimeModelBuilder, Locatable locatable, Class cls) {
        super(runtimeModelBuilder, locatable, cls);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ArrayInfoImpl, com.sun.xml.bind.v2.model.core.TypeInfo
    /* JADX INFO: renamed from: getType, reason: merged with bridge method [inline-methods] */
    public Type getType2() {
        return (Class) super.getType2();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ArrayInfoImpl, com.sun.xml.bind.v2.model.core.ArrayInfo
    public NonElement<Type, Class> getItemType() {
        return (RuntimeNonElement) super.getItemType();
    }
}
