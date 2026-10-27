package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.MapPropertyInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeMapPropertyInfo extends RuntimePropertyInfo, MapPropertyInfo<Type, Class> {
    @Override // com.sun.xml.bind.v2.model.core.MapPropertyInfo
    NonElement<Type, Class> getKeyType();

    @Override // com.sun.xml.bind.v2.model.core.MapPropertyInfo
    NonElement<Type, Class> getValueType();
}
