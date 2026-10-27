package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.DomLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiTypeLoader;
import java.io.IOException;
import javax.xml.bind.annotation.W3CDomHandler;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class AnyTypeBeanInfo extends JaxBeanInfo<Object> implements AttributeAccessor {
    private static final W3CDomHandler domHandler;
    private static final DomLoader domLoader;
    private boolean nilIncluded;
    private final XsiTypeLoader substLoader;

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getId(Object obj, XMLSerializer xMLSerializer) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Transducer<Object> getTransducer() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public boolean reset(Object obj, UnmarshallingContext unmarshallingContext) {
        return false;
    }

    public AnyTypeBeanInfo(JAXBContextImpl jAXBContextImpl, RuntimeTypeInfo runtimeTypeInfo) {
        super(jAXBContextImpl, runtimeTypeInfo, Object.class, new QName(WellKnownNamespace.XML_SCHEMA, "anyType"), false, true, false);
        this.nilIncluded = false;
        this.substLoader = new XsiTypeLoader(this);
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementNamespaceURI(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementLocalName(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Object createInstance(UnmarshallingContext unmarshallingContext) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeBody(Object obj, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        NodeList childNodes = ((Element) obj).getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            short nodeType = nodeItem.getNodeType();
            if (nodeType == 1) {
                xMLSerializer.writeDom((Element) nodeItem, domHandler, null, null);
            } else if (nodeType == 3 || nodeType == 4) {
                xMLSerializer.text(nodeItem.getNodeValue(), (String) null);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeAttributes(Object obj, XMLSerializer xMLSerializer) throws SAXException {
        NamedNodeMap attributes = ((Element) obj).getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Attr attr = (Attr) attributes.item(i);
            String namespaceURI = attr.getNamespaceURI();
            if (namespaceURI == null) {
                namespaceURI = "";
            }
            String localName = attr.getLocalName();
            String name = attr.getName();
            if (localName == null) {
                localName = name;
            }
            if (namespaceURI.equals(WellKnownNamespace.XML_SCHEMA_INSTANCE) && "nil".equals(localName)) {
                this.isNilIncluded = true;
            }
            if (!name.startsWith("xmlns")) {
                xMLSerializer.attribute(namespaceURI, localName, attr.getValue());
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeRoot(Object obj, XMLSerializer xMLSerializer) throws SAXException {
        xMLSerializer.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(obj.getClass().getName()), null, null));
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeURIs(Object obj, XMLSerializer xMLSerializer) {
        NamedNodeMap attributes = ((Element) obj).getAttributes();
        int length = attributes.getLength();
        NamespaceContext2 namespaceContext = xMLSerializer.getNamespaceContext();
        for (int i = 0; i < length; i++) {
            Attr attr = (Attr) attributes.item(i);
            if ("xmlns".equals(attr.getPrefix())) {
                namespaceContext.force(attr.getValue(), attr.getLocalName());
            } else if ("xmlns".equals(attr.getName())) {
                if (obj instanceof Element) {
                    namespaceContext.declareNamespace(attr.getValue(), null, false);
                } else {
                    namespaceContext.force(attr.getValue(), "");
                }
            } else {
                String namespaceURI = attr.getNamespaceURI();
                if (namespaceURI != null && namespaceURI.length() > 0) {
                    namespaceContext.declareNamespace(namespaceURI, attr.getPrefix(), true);
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        if (z) {
            return this.substLoader;
        }
        return domLoader;
    }

    static {
        W3CDomHandler w3CDomHandler = new W3CDomHandler();
        domHandler = w3CDomHandler;
        domLoader = new DomLoader(w3CDomHandler);
    }
}
