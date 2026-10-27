package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.ValuePropertyInfo;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeValuePropertyInfo extends ValuePropertyInfo<Type, Class>, RuntimePropertyInfo, RuntimeNonElementRef {
    @Override // com.sun.xml.bind.v2.model.core.NonElementRef
    NonElement<Type, Class> getTarget();
}
