package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.unmarshaller.InfosetScanner;
import com.sun.xml.bind.v2.runtime.AssociationMap;
import com.sun.xml.bind.v2.runtime.output.DOMOutput;
import com.sun.xml.bind.v2.runtime.unmarshaller.InterningXmlVisitor;
import com.sun.xml.bind.v2.runtime.unmarshaller.SAXConnector;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import javax.xml.bind.Binder;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class BinderImpl<XmlNode> extends Binder<XmlNode> {
    private final AssociationMap<XmlNode> assoc = new AssociationMap<>();
    private final JAXBContextImpl context;
    private MarshallerImpl marshaller;
    private final InfosetScanner<XmlNode> scanner;
    private UnmarshallerImpl unmarshaller;

    BinderImpl(JAXBContextImpl jAXBContextImpl, InfosetScanner<XmlNode> infosetScanner) {
        this.context = jAXBContextImpl;
        this.scanner = infosetScanner;
    }

    private UnmarshallerImpl getUnmarshaller() {
        if (this.unmarshaller == null) {
            this.unmarshaller = new UnmarshallerImpl(this.context, this.assoc);
        }
        return this.unmarshaller;
    }

    private MarshallerImpl getMarshaller() {
        if (this.marshaller == null) {
            this.marshaller = new MarshallerImpl(this.context, this.assoc);
        }
        return this.marshaller;
    }

    @Override // javax.xml.bind.Binder
    public void marshal(Object obj, XmlNode xmlnode) throws JAXBException {
        if (xmlnode == null || obj == null) {
            throw new IllegalArgumentException();
        }
        getMarshaller().marshal(obj, createOutput(xmlnode));
    }

    private DOMOutput createOutput(XmlNode xmlnode) {
        return new DOMOutput((Node) xmlnode, this.assoc);
    }

    @Override // javax.xml.bind.Binder
    public Object updateJAXB(XmlNode xmlnode) throws JAXBException {
        return associativeUnmarshal(xmlnode, true, null);
    }

    @Override // javax.xml.bind.Binder
    public Object unmarshal(XmlNode xmlnode) throws JAXBException {
        return associativeUnmarshal(xmlnode, false, null);
    }

    @Override // javax.xml.bind.Binder
    public <T> JAXBElement<T> unmarshal(XmlNode xmlnode, Class<T> cls) throws JAXBException {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        return (JAXBElement) associativeUnmarshal(xmlnode, true, cls);
    }

    @Override // javax.xml.bind.Binder
    public void setSchema(Schema schema) {
        getMarshaller().setSchema(schema);
        getUnmarshaller().setSchema(schema);
    }

    @Override // javax.xml.bind.Binder
    public Schema getSchema() {
        return getUnmarshaller().getSchema();
    }

    private Object associativeUnmarshal(XmlNode xmlnode, boolean z, Class cls) throws JAXBException {
        if (xmlnode == null) {
            throw new IllegalArgumentException();
        }
        InterningXmlVisitor interningXmlVisitor = new InterningXmlVisitor(getUnmarshaller().createUnmarshallerHandler(this.scanner, z, cls != null ? this.context.getBeanInfo(cls, true) : null));
        this.scanner.setContentHandler(new SAXConnector(interningXmlVisitor, this.scanner.getLocator()));
        try {
            this.scanner.scan(xmlnode);
            return interningXmlVisitor.getContext().getResult();
        } catch (SAXException e) {
            throw this.unmarshaller.createUnmarshalException(e);
        }
    }

    @Override // javax.xml.bind.Binder
    public XmlNode getXMLNode(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        AssociationMap.Entry<XmlNode> entryByPeer = this.assoc.byPeer(obj);
        if (entryByPeer == null) {
            return null;
        }
        return entryByPeer.element();
    }

    @Override // javax.xml.bind.Binder
    public Object getJAXBNode(XmlNode xmlnode) {
        if (xmlnode == null) {
            throw new IllegalArgumentException();
        }
        AssociationMap.Entry<XmlNode> entryByElement = this.assoc.byElement(xmlnode);
        if (entryByElement == null) {
            return null;
        }
        return entryByElement.outer() != null ? entryByElement.outer() : entryByElement.inner();
    }

    @Override // javax.xml.bind.Binder
    public XmlNode updateXML(Object obj) throws JAXBException {
        return updateXML(obj, getXMLNode(obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [XmlNode, org.w3c.dom.Node] */
    @Override // javax.xml.bind.Binder
    public XmlNode updateXML(Object obj, XmlNode xmlnode) throws JAXBException {
        if (obj == null || xmlnode == null) {
            throw new IllegalArgumentException();
        }
        Element element = (Element) xmlnode;
        Node nextSibling = element.getNextSibling();
        Node parentNode = element.getParentNode();
        parentNode.removeChild(element);
        JaxBeanInfo beanInfo = this.context.getBeanInfo(obj, true);
        if (!beanInfo.isElement()) {
            obj = new JAXBElement(new QName(element.getNamespaceURI(), element.getLocalName()), beanInfo.jaxbType, obj);
        }
        getMarshaller().marshal(obj, parentNode);
        ?? r7 = (XmlNode) parentNode.getLastChild();
        parentNode.removeChild(r7);
        parentNode.insertBefore(r7, nextSibling);
        return r7;
    }

    @Override // javax.xml.bind.Binder
    public void setEventHandler(ValidationEventHandler validationEventHandler) throws JAXBException {
        getUnmarshaller().setEventHandler(validationEventHandler);
        getMarshaller().setEventHandler(validationEventHandler);
    }

    @Override // javax.xml.bind.Binder
    public ValidationEventHandler getEventHandler() {
        return getUnmarshaller().getEventHandler();
    }

    @Override // javax.xml.bind.Binder
    public Object getProperty(String str) throws PropertyException {
        if (str == null) {
            throw new IllegalArgumentException(Messages.NULL_PROPERTY_NAME.format(new Object[0]));
        }
        if (excludeProperty(str)) {
            throw new PropertyException(str);
        }
        try {
            try {
                return getMarshaller().getProperty(str);
            } catch (PropertyException unused) {
                return getUnmarshaller().getProperty(str);
            }
        } catch (PropertyException e) {
            e.setStackTrace(Thread.currentThread().getStackTrace());
            throw e;
        }
    }

    @Override // javax.xml.bind.Binder
    public void setProperty(String str, Object obj) throws PropertyException {
        if (str == null) {
            throw new IllegalArgumentException(Messages.NULL_PROPERTY_NAME.format(new Object[0]));
        }
        if (excludeProperty(str)) {
            throw new PropertyException(str, obj);
        }
        try {
            try {
                getMarshaller().setProperty(str, obj);
            } catch (PropertyException unused) {
                getUnmarshaller().setProperty(str, obj);
            }
        } catch (PropertyException e) {
            e.setStackTrace(Thread.currentThread().getStackTrace());
            throw e;
        }
    }

    private boolean excludeProperty(String str) {
        return str.equals("com.sun.xml.bind.characterEscapeHandler") || str.equals("com.sun.xml.bind.xmlDeclaration") || str.equals("com.sun.xml.bind.xmlHeaders");
    }
}
