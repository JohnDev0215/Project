package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.runtime.RuntimeLeafInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.TextLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiTypeLoader;
import java.io.IOException;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class LeafBeanInfoImpl<BeanT> extends JaxBeanInfo<BeanT> {
    private final Loader loader;
    private final Loader loaderWithSubst;
    private final Name tagName;
    private final Transducer<BeanT> xducer;

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final String getId(BeanT beant, XMLSerializer xMLSerializer) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final boolean reset(BeanT beant, UnmarshallingContext unmarshallingContext) {
        return false;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeAttributes(BeanT beant, XMLSerializer xMLSerializer) {
    }

    public LeafBeanInfoImpl(JAXBContextImpl jAXBContextImpl, RuntimeLeafInfo runtimeLeafInfo) {
        super(jAXBContextImpl, (RuntimeTypeInfo) runtimeLeafInfo, runtimeLeafInfo.getClazz(), runtimeLeafInfo.getTypeNames(), runtimeLeafInfo.isElement(), true, false);
        Transducer<BeanT> transducer = runtimeLeafInfo.getTransducer();
        this.xducer = transducer;
        this.loader = new TextLoader(transducer);
        this.loaderWithSubst = new XsiTypeLoader(this);
        if (isElement()) {
            this.tagName = jAXBContextImpl.nameBuilder.createElementName(runtimeLeafInfo.getElementName());
        } else {
            this.tagName = null;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public QName getTypeName(BeanT beant) {
        QName typeName = this.xducer.getTypeName(beant);
        return typeName != null ? typeName : super.getTypeName(beant);
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final String getElementNamespaceURI(BeanT beant) {
        return this.tagName.nsUri;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final String getElementLocalName(BeanT beant) {
        return this.tagName.localName;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public BeanT createInstance(UnmarshallingContext unmarshallingContext) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeBody(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        try {
            this.xducer.writeText(xMLSerializer, beant, null);
        } catch (AccessorException e) {
            xMLSerializer.reportError(null, e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeRoot(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        Name name = this.tagName;
        if (name == null) {
            xMLSerializer.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(beant.getClass().getName()), null, null));
            return;
        }
        xMLSerializer.startElement(name, beant);
        xMLSerializer.childAsSoleContent(beant, null);
        xMLSerializer.endElement();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeURIs(BeanT beant, XMLSerializer xMLSerializer) throws SAXException {
        if (this.xducer.useNamespace()) {
            try {
                this.xducer.declareNamespace(beant, xMLSerializer);
            } catch (AccessorException e) {
                xMLSerializer.reportError(null, e);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        if (z) {
            return this.loaderWithSubst;
        }
        return this.loader;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Transducer<BeanT> getTransducer() {
        return this.xducer;
    }
}
