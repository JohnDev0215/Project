package com.sun.xml.txw2;

import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlCDATA;
import com.sun.xml.txw2.annotation.XmlElement;
import com.sun.xml.txw2.annotation.XmlNamespace;
import com.sun.xml.txw2.annotation.XmlValue;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
final class ContainerElement implements InvocationHandler, TypedXmlWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean blocked;
    final Document document;
    final EndTag endTag = new EndTag();
    private ContainerElement lastOpenChild;
    private ContainerElement nextOpen;
    private final String nsUri;
    private final ContainerElement parent;
    private ContainerElement prevOpen;
    StartTag startTag;
    private Content tail;

    public ContainerElement(Document document, ContainerElement containerElement, String str, String str2) {
        this.parent = containerElement;
        this.document = document;
        this.nsUri = str;
        StartTag startTag = new StartTag(this, str, str2);
        this.startTag = startTag;
        this.tail = startTag;
        if (isRoot()) {
            document.setFirstContent(this.startTag);
        }
    }

    private boolean isRoot() {
        return this.parent == null;
    }

    private boolean isCommitted() {
        return this.tail == null;
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public Document getDocument() {
        return this.document;
    }

    boolean isBlocked() {
        return this.blocked && !isCommitted();
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void block() {
        this.blocked = true;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.getDeclaringClass() == TypedXmlWriter.class || method.getDeclaringClass() == Object.class) {
            try {
                return method.invoke(this, objArr);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }
        XmlAttribute xmlAttribute = (XmlAttribute) method.getAnnotation(XmlAttribute.class);
        XmlValue xmlValue = (XmlValue) method.getAnnotation(XmlValue.class);
        XmlElement xmlElement = (XmlElement) method.getAnnotation(XmlElement.class);
        if (xmlAttribute != null) {
            if (xmlValue != null || xmlElement != null) {
                throw new IllegalAnnotationException(method.toString());
            }
            addAttribute(xmlAttribute, method, objArr);
            return obj;
        }
        if (xmlValue == null) {
            return addElement(xmlElement, method, objArr);
        }
        if (xmlElement != null) {
            throw new IllegalAnnotationException(method.toString());
        }
        _pcdata(objArr);
        return obj;
    }

    private void addAttribute(XmlAttribute xmlAttribute, Method method, Object[] objArr) {
        checkStartTag();
        String strValue = xmlAttribute.value();
        if (xmlAttribute.value().length() == 0) {
            strValue = method.getName();
        }
        _attribute(xmlAttribute.ns(), strValue, objArr);
    }

    private void checkStartTag() {
        if (this.startTag == null) {
            throw new IllegalStateException("start tag has already been written");
        }
    }

    private Object addElement(XmlElement xmlElement, Method method, Object[] objArr) {
        String namespace;
        Content pcdata;
        Class<?> returnType = method.getReturnType();
        String name = method.getName();
        if (xmlElement != null) {
            if (xmlElement.value().length() != 0) {
                name = xmlElement.value();
            }
            namespace = xmlElement.ns();
        } else {
            namespace = "##default";
        }
        if (namespace.equals("##default")) {
            Class<?> declaringClass = method.getDeclaringClass();
            XmlElement xmlElement2 = (XmlElement) declaringClass.getAnnotation(XmlElement.class);
            if (xmlElement2 != null) {
                namespace = xmlElement2.ns();
            }
            if (namespace.equals("##default")) {
                namespace = getNamespace(declaringClass.getPackage());
            }
        }
        if (returnType == Void.TYPE) {
            boolean z = method.getAnnotation(XmlCDATA.class) != null;
            StartTag startTag = new StartTag(this.document, namespace, name);
            addChild(startTag);
            for (Object obj : objArr) {
                if (z) {
                    pcdata = new Cdata(this.document, startTag, obj);
                } else {
                    pcdata = new Pcdata(this.document, startTag, obj);
                }
                addChild(pcdata);
            }
            addChild(new EndTag());
            return null;
        }
        if (TypedXmlWriter.class.isAssignableFrom(returnType)) {
            return _element(namespace, name, returnType);
        }
        throw new IllegalSignatureException("Illegal return type: " + returnType);
    }

    private String getNamespace(Package r3) {
        XmlNamespace xmlNamespace;
        return (r3 == null || (xmlNamespace = (XmlNamespace) r3.getAnnotation(XmlNamespace.class)) == null) ? "" : xmlNamespace.value();
    }

    private void addChild(Content content) {
        this.tail.setNext(this.document, content);
        this.tail = content;
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void commit() {
        commit(true);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void commit(boolean z) {
        _commit(z);
        this.document.flush();
    }

    private void _commit(boolean z) {
        if (isCommitted()) {
            return;
        }
        addChild(this.endTag);
        if (isRoot()) {
            addChild(new EndDocument());
        }
        this.tail = null;
        if (z) {
            for (ContainerElement containerElement = this; containerElement != null; containerElement = containerElement.parent) {
                while (true) {
                    ContainerElement containerElement2 = containerElement.prevOpen;
                    if (containerElement2 != null) {
                        containerElement2._commit(false);
                    }
                }
            }
        }
        while (true) {
            ContainerElement containerElement3 = this.lastOpenChild;
            if (containerElement3 == null) {
                break;
            } else {
                containerElement3._commit(false);
            }
        }
        ContainerElement containerElement4 = this.parent;
        if (containerElement4 != null) {
            if (containerElement4.lastOpenChild == this) {
                containerElement4.lastOpenChild = this.prevOpen;
            } else {
                this.nextOpen.prevOpen = this.prevOpen;
            }
            ContainerElement containerElement5 = this.prevOpen;
            if (containerElement5 != null) {
                containerElement5.nextOpen = this.nextOpen;
            }
        }
        this.nextOpen = null;
        this.prevOpen = null;
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _attribute(String str, Object obj) {
        _attribute("", str, obj);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _attribute(String str, String str2, Object obj) {
        checkStartTag();
        this.startTag.addAttribute(str, str2, obj);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _attribute(QName qName, Object obj) {
        _attribute(qName.getNamespaceURI(), qName.getLocalPart(), obj);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _namespace(String str) {
        _namespace(str, false);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _namespace(String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException();
        }
        checkStartTag();
        this.startTag.addNamespaceDecl(str, str2, false);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _namespace(String str, boolean z) {
        checkStartTag();
        this.startTag.addNamespaceDecl(str, null, z);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _pcdata(Object obj) {
        addChild(new Pcdata(this.document, this.startTag, obj));
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _cdata(Object obj) {
        addChild(new Cdata(this.document, this.startTag, obj));
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public void _comment(Object obj) throws UnsupportedOperationException {
        addChild(new Comment(this.document, this.startTag, obj));
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public <T extends TypedXmlWriter> T _element(String str, Class<T> cls) {
        return (T) _element(this.nsUri, str, cls);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public <T extends TypedXmlWriter> T _element(QName qName, Class<T> cls) {
        return (T) _element(qName.getNamespaceURI(), qName.getLocalPart(), cls);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public <T extends TypedXmlWriter> T _element(Class<T> cls) {
        return (T) _element(TXW.getTagName(cls), cls);
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public <T extends TypedXmlWriter> T _cast(Class<T> cls) {
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, this));
    }

    @Override // com.sun.xml.txw2.TypedXmlWriter
    public <T extends TypedXmlWriter> T _element(String str, String str2, Class<T> cls) {
        ContainerElement containerElement = new ContainerElement(this.document, this, str, str2);
        addChild(containerElement.startTag);
        this.tail = containerElement.endTag;
        ContainerElement containerElement2 = this.lastOpenChild;
        if (containerElement2 != null) {
            containerElement.prevOpen = containerElement2;
            containerElement2.nextOpen = containerElement;
        }
        this.lastOpenChild = containerElement;
        return (T) containerElement._cast(cls);
    }
}
