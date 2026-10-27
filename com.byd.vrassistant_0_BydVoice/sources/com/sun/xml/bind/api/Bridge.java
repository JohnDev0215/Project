package com.sun.xml.bind.api;

import com.sun.xml.bind.v2.runtime.BridgeContextImpl;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Bridge<T> {
    protected final JAXBContextImpl context;

    public abstract TypeReference getTypeReference();

    public abstract void marshal(Marshaller marshaller, T t, OutputStream outputStream, NamespaceContext namespaceContext) throws JAXBException;

    public abstract void marshal(Marshaller marshaller, T t, XMLStreamWriter xMLStreamWriter) throws JAXBException;

    public abstract void marshal(Marshaller marshaller, T t, Result result) throws JAXBException;

    public abstract void marshal(Marshaller marshaller, T t, Node node) throws JAXBException;

    public abstract void marshal(Marshaller marshaller, T t, ContentHandler contentHandler) throws JAXBException;

    public abstract T unmarshal(Unmarshaller unmarshaller, InputStream inputStream) throws JAXBException;

    public abstract T unmarshal(Unmarshaller unmarshaller, XMLStreamReader xMLStreamReader) throws JAXBException;

    public abstract T unmarshal(Unmarshaller unmarshaller, Source source) throws JAXBException;

    public abstract T unmarshal(Unmarshaller unmarshaller, Node node) throws JAXBException;

    protected Bridge(JAXBContextImpl jAXBContextImpl) {
        this.context = jAXBContextImpl;
    }

    public JAXBRIContext getContext() {
        return this.context;
    }

    public final void marshal(T t, XMLStreamWriter xMLStreamWriter) throws JAXBException {
        marshal(t, xMLStreamWriter, (AttachmentMarshaller) null);
    }

    public final void marshal(T t, XMLStreamWriter xMLStreamWriter, AttachmentMarshaller attachmentMarshaller) throws JAXBException {
        Marshaller marshallerTake = this.context.marshallerPool.take();
        marshallerTake.setAttachmentMarshaller(attachmentMarshaller);
        marshal(marshallerTake, t, xMLStreamWriter);
        marshallerTake.setAttachmentMarshaller(null);
        this.context.marshallerPool.recycle(marshallerTake);
    }

    public final void marshal(BridgeContext bridgeContext, T t, XMLStreamWriter xMLStreamWriter) throws JAXBException {
        marshal(((BridgeContextImpl) bridgeContext).marshaller, t, xMLStreamWriter);
    }

    public void marshal(T t, OutputStream outputStream, NamespaceContext namespaceContext) throws JAXBException {
        marshal(t, outputStream, namespaceContext, (AttachmentMarshaller) null);
    }

    public void marshal(T t, OutputStream outputStream, NamespaceContext namespaceContext, AttachmentMarshaller attachmentMarshaller) throws JAXBException {
        Marshaller marshallerTake = this.context.marshallerPool.take();
        marshallerTake.setAttachmentMarshaller(attachmentMarshaller);
        marshal(marshallerTake, t, outputStream, namespaceContext);
        marshallerTake.setAttachmentMarshaller(null);
        this.context.marshallerPool.recycle(marshallerTake);
    }

    public final void marshal(BridgeContext bridgeContext, T t, OutputStream outputStream, NamespaceContext namespaceContext) throws JAXBException {
        marshal(((BridgeContextImpl) bridgeContext).marshaller, t, outputStream, namespaceContext);
    }

    public final void marshal(T t, Node node) throws JAXBException {
        Marshaller marshallerTake = this.context.marshallerPool.take();
        marshal(marshallerTake, t, node);
        this.context.marshallerPool.recycle(marshallerTake);
    }

    public final void marshal(BridgeContext bridgeContext, T t, Node node) throws JAXBException {
        marshal(((BridgeContextImpl) bridgeContext).marshaller, t, node);
    }

    public final void marshal(T t, ContentHandler contentHandler) throws JAXBException {
        marshal(t, contentHandler, (AttachmentMarshaller) null);
    }

    public final void marshal(T t, ContentHandler contentHandler, AttachmentMarshaller attachmentMarshaller) throws JAXBException {
        Marshaller marshallerTake = this.context.marshallerPool.take();
        marshallerTake.setAttachmentMarshaller(attachmentMarshaller);
        marshal(marshallerTake, t, contentHandler);
        marshallerTake.setAttachmentMarshaller(null);
        this.context.marshallerPool.recycle(marshallerTake);
    }

    public final void marshal(BridgeContext bridgeContext, T t, ContentHandler contentHandler) throws JAXBException {
        marshal(((BridgeContextImpl) bridgeContext).marshaller, t, contentHandler);
    }

    public final void marshal(T t, Result result) throws JAXBException {
        Marshaller marshallerTake = this.context.marshallerPool.take();
        marshal(marshallerTake, t, result);
        this.context.marshallerPool.recycle(marshallerTake);
    }

    public final void marshal(BridgeContext bridgeContext, T t, Result result) throws JAXBException {
        marshal(((BridgeContextImpl) bridgeContext).marshaller, t, result);
    }

    private T exit(T t, Unmarshaller unmarshaller) {
        unmarshaller.setAttachmentUnmarshaller(null);
        this.context.unmarshallerPool.recycle(unmarshaller);
        return t;
    }

    public final T unmarshal(XMLStreamReader xMLStreamReader) throws JAXBException {
        return unmarshal(xMLStreamReader, (AttachmentUnmarshaller) null);
    }

    public final T unmarshal(XMLStreamReader xMLStreamReader, AttachmentUnmarshaller attachmentUnmarshaller) throws JAXBException {
        Unmarshaller unmarshallerTake = this.context.unmarshallerPool.take();
        unmarshallerTake.setAttachmentUnmarshaller(attachmentUnmarshaller);
        return exit(unmarshal(unmarshallerTake, xMLStreamReader), unmarshallerTake);
    }

    public final T unmarshal(BridgeContext bridgeContext, XMLStreamReader xMLStreamReader) throws JAXBException {
        return unmarshal(((BridgeContextImpl) bridgeContext).unmarshaller, xMLStreamReader);
    }

    public final T unmarshal(Source source) throws JAXBException {
        return unmarshal(source, (AttachmentUnmarshaller) null);
    }

    public final T unmarshal(Source source, AttachmentUnmarshaller attachmentUnmarshaller) throws JAXBException {
        Unmarshaller unmarshallerTake = this.context.unmarshallerPool.take();
        unmarshallerTake.setAttachmentUnmarshaller(attachmentUnmarshaller);
        return exit(unmarshal(unmarshallerTake, source), unmarshallerTake);
    }

    public final T unmarshal(BridgeContext bridgeContext, Source source) throws JAXBException {
        return unmarshal(((BridgeContextImpl) bridgeContext).unmarshaller, source);
    }

    public final T unmarshal(InputStream inputStream) throws JAXBException {
        Unmarshaller unmarshallerTake = this.context.unmarshallerPool.take();
        return exit(unmarshal(unmarshallerTake, inputStream), unmarshallerTake);
    }

    public final T unmarshal(BridgeContext bridgeContext, InputStream inputStream) throws JAXBException {
        return unmarshal(((BridgeContextImpl) bridgeContext).unmarshaller, inputStream);
    }

    public final T unmarshal(Node node) throws JAXBException {
        return unmarshal(node, (AttachmentUnmarshaller) null);
    }

    public final T unmarshal(Node node, AttachmentUnmarshaller attachmentUnmarshaller) throws JAXBException {
        Unmarshaller unmarshallerTake = this.context.unmarshallerPool.take();
        unmarshallerTake.setAttachmentUnmarshaller(attachmentUnmarshaller);
        return exit(unmarshal(unmarshallerTake, node), unmarshallerTake);
    }

    public final T unmarshal(BridgeContext bridgeContext, Node node) throws JAXBException {
        return unmarshal(((BridgeContextImpl) bridgeContext).unmarshaller, node);
    }
}
