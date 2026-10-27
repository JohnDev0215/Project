package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.runtime.Transducer;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeNonElement extends NonElement<Type, Class>, RuntimeTypeInfo {
    <V> Transducer<V> getTransducer();
}
