package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class BridgeAdapter<OnWire, InMemory> extends InternalBridge<InMemory> {
    private final Class<? extends XmlAdapter<OnWire, InMemory>> adapter;
    private final InternalBridge<OnWire> core;

    public BridgeAdapter(InternalBridge<OnWire> internalBridge, Class<? extends XmlAdapter<OnWire, InMemory>> cls) {
        super(internalBridge.getContext());
        this.core = internalBridge;
        this.adapter = cls;
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, InMemory inmemory, XMLStreamWriter xMLStreamWriter) throws JAXBException {
        this.core.marshal(marshaller, adaptM(marshaller, inmemory), xMLStreamWriter);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, InMemory inmemory, OutputStream outputStream, NamespaceContext namespaceContext) throws JAXBException {
        this.core.marshal(marshaller, adaptM(marshaller, inmemory), outputStream, namespaceContext);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, InMemory inmemory, Node node) throws JAXBException {
        this.core.marshal(marshaller, adaptM(marshaller, inmemory), node);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, InMemory inmemory, ContentHandler contentHandler) throws JAXBException {
        this.core.marshal(marshaller, adaptM(marshaller, inmemory), contentHandler);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, InMemory inmemory, Result result) throws JAXBException {
        this.core.marshal(marshaller, adaptM(marshaller, inmemory), result);
    }

    private OnWire adaptM(Marshaller marshaller, InMemory inmemory) throws JAXBException {
        XMLSerializer xMLSerializer = ((MarshallerImpl) marshaller).serializer;
        xMLSerializer.pushCoordinator();
        try {
            return _adaptM(xMLSerializer, inmemory);
        } finally {
            xMLSerializer.popCoordinator();
        }
    }

    private OnWire _adaptM(XMLSerializer xMLSerializer, InMemory inmemory) throws MarshalException {
        try {
            return (OnWire) xMLSerializer.getAdapter(this.adapter).marshal(inmemory);
        } catch (Exception e) {
            xMLSerializer.handleError(e, inmemory, null);
            throw new MarshalException(e);
        }
    }

    @Override // com.sun.xml.bind.api.Bridge
    public InMemory unmarshal(Unmarshaller unmarshaller, XMLStreamReader xMLStreamReader) throws JAXBException {
        return adaptU(unmarshaller, this.core.unmarshal(unmarshaller, xMLStreamReader));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public InMemory unmarshal(Unmarshaller unmarshaller, Source source) throws JAXBException {
        return adaptU(unmarshaller, this.core.unmarshal(unmarshaller, source));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public InMemory unmarshal(Unmarshaller unmarshaller, InputStream inputStream) throws JAXBException {
        return adaptU(unmarshaller, this.core.unmarshal(unmarshaller, inputStream));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public InMemory unmarshal(Unmarshaller unmarshaller, Node node) throws JAXBException {
        return adaptU(unmarshaller, this.core.unmarshal(unmarshaller, node));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public TypeReference getTypeReference() {
        return this.core.getTypeReference();
    }

    private InMemory adaptU(Unmarshaller unmarshaller, OnWire onwire) throws JAXBException {
        UnmarshallerImpl unmarshallerImpl = (UnmarshallerImpl) unmarshaller;
        XmlAdapter adapter = unmarshallerImpl.coordinator.getAdapter(this.adapter);
        unmarshallerImpl.coordinator.pushCoordinator();
        try {
            try {
                return (InMemory) adapter.unmarshal(onwire);
            } catch (Exception e) {
                throw new UnmarshalException(e);
            }
        } finally {
            unmarshallerImpl.coordinator.popCoordinator();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.InternalBridge
    void marshal(InMemory inmemory, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        try {
            this.core.marshal(_adaptM(XMLSerializer.getInstance(), inmemory), xMLSerializer);
        } catch (MarshalException unused) {
        }
    }
}
