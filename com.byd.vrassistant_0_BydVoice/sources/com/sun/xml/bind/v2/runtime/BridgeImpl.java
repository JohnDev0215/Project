package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.bind.marshaller.SAX2DOMEx;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
final class BridgeImpl<T> extends InternalBridge<T> {
    private final JaxBeanInfo<T> bi;
    private final Name tagName;
    private final TypeReference typeRef;

    public BridgeImpl(JAXBContextImpl jAXBContextImpl, Name name, JaxBeanInfo<T> jaxBeanInfo, TypeReference typeReference) {
        super(jAXBContextImpl);
        this.tagName = name;
        this.bi = jaxBeanInfo;
        this.typeRef = typeReference;
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, T t, XMLStreamWriter xMLStreamWriter) throws JAXBException {
        MarshallerImpl marshallerImpl = (MarshallerImpl) marshaller;
        marshallerImpl.write(this.tagName, this.bi, t, XMLStreamWriterOutput.create(xMLStreamWriter, this.context, marshallerImpl.getEscapeHandler()), new StAXPostInitAction(xMLStreamWriter, marshallerImpl.serializer));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, T t, OutputStream outputStream, NamespaceContext namespaceContext) throws JAXBException {
        MarshallerImpl marshallerImpl = (MarshallerImpl) marshaller;
        marshallerImpl.write(this.tagName, this.bi, t, marshallerImpl.createWriter(outputStream), namespaceContext != null ? new StAXPostInitAction(namespaceContext, marshallerImpl.serializer) : null);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, T t, Node node) throws JAXBException {
        MarshallerImpl marshallerImpl = (MarshallerImpl) marshaller;
        marshallerImpl.write(this.tagName, this.bi, t, new SAXOutput(new SAX2DOMEx(node)), new DomPostInitAction(node, marshallerImpl.serializer));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, T t, ContentHandler contentHandler) throws JAXBException {
        ((MarshallerImpl) marshaller).write(this.tagName, this.bi, t, new SAXOutput(contentHandler), null);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public void marshal(Marshaller marshaller, T t, Result result) throws JAXBException {
        MarshallerImpl marshallerImpl = (MarshallerImpl) marshaller;
        marshallerImpl.write(this.tagName, this.bi, t, marshallerImpl.createXmlOutput(result), marshallerImpl.createPostInitAction(result));
    }

    @Override // com.sun.xml.bind.api.Bridge
    public T unmarshal(Unmarshaller unmarshaller, XMLStreamReader xMLStreamReader) throws JAXBException {
        return (T) ((JAXBElement) ((UnmarshallerImpl) unmarshaller).unmarshal0(xMLStreamReader, this.bi)).getValue();
    }

    @Override // com.sun.xml.bind.api.Bridge
    public T unmarshal(Unmarshaller unmarshaller, Source source) throws JAXBException {
        return (T) ((JAXBElement) ((UnmarshallerImpl) unmarshaller).unmarshal0(source, this.bi)).getValue();
    }

    @Override // com.sun.xml.bind.api.Bridge
    public T unmarshal(Unmarshaller unmarshaller, InputStream inputStream) throws JAXBException {
        return (T) ((JAXBElement) ((UnmarshallerImpl) unmarshaller).unmarshal0(inputStream, this.bi)).getValue();
    }

    @Override // com.sun.xml.bind.api.Bridge
    public T unmarshal(Unmarshaller unmarshaller, Node node) throws JAXBException {
        return (T) ((JAXBElement) ((UnmarshallerImpl) unmarshaller).unmarshal0(node, this.bi)).getValue();
    }

    @Override // com.sun.xml.bind.api.Bridge
    public TypeReference getTypeReference() {
        return this.typeRef;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.InternalBridge
    public void marshal(T t, XMLSerializer xMLSerializer) throws XMLStreamException, SAXException, IOException {
        xMLSerializer.startElement(this.tagName, null);
        if (t == null) {
            xMLSerializer.writeXsiNilTrue();
        } else {
            xMLSerializer.childAsXsiType(t, null, this.bi, false);
        }
        xMLSerializer.endElement();
    }
}
