package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.runtime.Coordinator;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/* JADX INFO: loaded from: classes3.dex */
final class AdaptedAccessor<BeanT, InMemValueT, OnWireValueT> extends Accessor<BeanT, OnWireValueT> {
    private final Class<? extends XmlAdapter<OnWireValueT, InMemValueT>> adapter;
    private final Accessor<BeanT, InMemValueT> core;
    private XmlAdapter<OnWireValueT, InMemValueT> staticAdapter;

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public boolean isAdapted() {
        return true;
    }

    AdaptedAccessor(Class<OnWireValueT> cls, Accessor<BeanT, InMemValueT> accessor, Class<? extends XmlAdapter<OnWireValueT, InMemValueT>> cls2) {
        super(cls);
        this.core = accessor;
        this.adapter = cls2;
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public OnWireValueT get(BeanT beant) throws AccessorException {
        try {
            return getAdapter().marshal(this.core.get(beant));
        } catch (Exception e) {
            throw new AccessorException(e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(BeanT beant, OnWireValueT onwirevaluet) throws AccessorException {
        try {
            this.core.set(beant, onwirevaluet == null ? null : getAdapter().unmarshal(onwirevaluet));
        } catch (Exception e) {
            throw new AccessorException(e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object getUnadapted(BeanT beant) throws AccessorException {
        return this.core.getUnadapted(beant);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void setUnadapted(BeanT beant, Object obj) throws AccessorException {
        this.core.setUnadapted(beant, obj);
    }

    private XmlAdapter<OnWireValueT, InMemValueT> getAdapter() {
        Coordinator coordinator_getInstance = Coordinator._getInstance();
        if (coordinator_getInstance != null) {
            return coordinator_getInstance.getAdapter(this.adapter);
        }
        synchronized (this) {
            if (this.staticAdapter == null) {
                this.staticAdapter = (XmlAdapter) ClassFactory.create(this.adapter);
            }
        }
        return this.staticAdapter;
    }
}
