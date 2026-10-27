package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.LeafInfo;
import com.sun.xml.bind.v2.runtime.Transducer;
import java.lang.reflect.Type;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeLeafInfo extends LeafInfo<Type, Class>, RuntimeNonElement {
    Class getClazz();

    <V> Transducer<V> getTransducer();

    QName[] getTypeNames();
}
