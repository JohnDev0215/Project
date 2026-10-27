package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.ArrayInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeArrayInfo extends ArrayInfo<Type, Class>, RuntimeNonElement {
    @Override // com.sun.xml.bind.v2.model.core.ArrayInfo
    NonElement<Type, Class> getItemType();

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    Type getType();
}
