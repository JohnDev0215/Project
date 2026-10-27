package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.Lister;

/* JADX INFO: loaded from: classes3.dex */
abstract class ArrayProperty<BeanT, ListT, ItemT> extends PropertyImpl<BeanT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final Accessor<BeanT, ListT> acc;
    protected final Lister<BeanT, ListT, ItemT, Object> lister;

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public final String getIdValue(BeanT beant) {
        return null;
    }

    protected ArrayProperty(JAXBContextImpl jAXBContextImpl, RuntimePropertyInfo runtimePropertyInfo) {
        super(jAXBContextImpl, runtimePropertyInfo);
        this.lister = Lister.create(Utils.REFLECTION_NAVIGATOR.erasure(runtimePropertyInfo.getRawType()), runtimePropertyInfo.id(), runtimePropertyInfo.getAdapter());
        this.acc = runtimePropertyInfo.getAccessor().optimize(jAXBContextImpl);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.lister.reset(beant, this.acc);
    }
}
