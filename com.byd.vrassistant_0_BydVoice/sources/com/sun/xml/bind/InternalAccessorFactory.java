package com.sun.xml.bind;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import javax.xml.bind.JAXBException;

/* JADX INFO: loaded from: classes3.dex */
public interface InternalAccessorFactory extends AccessorFactory {
    Accessor createFieldAccessor(Class cls, Field field, boolean z, boolean z2) throws JAXBException;
}
