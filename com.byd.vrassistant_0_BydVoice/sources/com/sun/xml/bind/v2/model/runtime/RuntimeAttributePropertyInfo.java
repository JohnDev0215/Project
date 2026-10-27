package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.AttributePropertyInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeAttributePropertyInfo extends AttributePropertyInfo<Type, Class>, RuntimePropertyInfo, RuntimeNonElementRef {
    @Override // com.sun.xml.bind.v2.model.core.AttributePropertyInfo, com.sun.xml.bind.v2.model.core.NonElementRef
    NonElement<Type, Class> getTarget();
}
