package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.Coordinator;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class AdaptedLister<BeanT, PropT, InMemItemT, OnWireItemT, PackT> extends Lister<BeanT, PropT, OnWireItemT, PackT> {
    private final Class<? extends XmlAdapter<OnWireItemT, InMemItemT>> adapter;
    private final Lister<BeanT, PropT, InMemItemT, PackT> core;

    AdaptedLister(Lister<BeanT, PropT, InMemItemT, PackT> lister, Class<? extends XmlAdapter<OnWireItemT, InMemItemT>> cls) {
        this.core = lister;
        this.adapter = cls;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public XmlAdapter<OnWireItemT, InMemItemT> getAdapter() {
        return Coordinator._getInstance().getAdapter(this.adapter);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<OnWireItemT> iterator(PropT propt, XMLSerializer xMLSerializer) {
        return new ListIteratorImpl(this.core.iterator(propt, xMLSerializer), xMLSerializer);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public PackT startPacking(BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException {
        return this.core.startPacking(beant, accessor);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(PackT packt, OnWireItemT onwireitemt) throws AccessorException {
        try {
            this.core.addToPack(packt, getAdapter().unmarshal(onwireitemt));
        } catch (Exception e) {
            throw new AccessorException(e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void endPacking(PackT packt, BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException {
        this.core.endPacking(packt, beant, accessor);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException {
        this.core.reset(beant, accessor);
    }

    private final class ListIteratorImpl implements ListIterator<OnWireItemT> {
        private final ListIterator<InMemItemT> core;
        private final XMLSerializer serializer;

        public ListIteratorImpl(ListIterator<InMemItemT> listIterator, XMLSerializer xMLSerializer) {
            this.core = listIterator;
            this.serializer = xMLSerializer;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
        public boolean hasNext() {
            return this.core.hasNext();
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
        public OnWireItemT next() throws SAXException, JAXBException {
            try {
                return (OnWireItemT) AdaptedLister.this.getAdapter().marshal(this.core.next());
            } catch (Exception e) {
                this.serializer.reportError(null, e);
                return null;
            }
        }
    }
}
