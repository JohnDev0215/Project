package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.ClassBeanInfoImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.util.Collection;
import javax.xml.namespace.QName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class LeafPropertyXsiLoader extends Loader {
    private final Accessor acc;
    private final Loader defaultLoader;
    private final TransducedAccessor xacc;

    public LeafPropertyXsiLoader(Loader loader, TransducedAccessor transducedAccessor, Accessor accessor) {
        this.defaultLoader = loader;
        this.expectText = true;
        this.xacc = transducedAccessor;
        this.acc = accessor;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        Loader loaderSelectLoader = selectLoader(state, tagName);
        state.setLoader(loaderSelectLoader);
        loaderSelectLoader.startElement(state, tagName);
    }

    protected Loader selectLoader(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        UnmarshallingContext context = state.getContext();
        Attributes attributes = tagName.atts;
        int index = attributes.getIndex(WellKnownNamespace.XML_SCHEMA_INSTANCE, "type");
        if (index >= 0) {
            QName qName_parseQName = DatatypeConverterImpl._parseQName(attributes.getValue(index), context);
            if (qName_parseQName == null) {
                return this.defaultLoader;
            }
            JaxBeanInfo globalType = context.getJAXBContext().getGlobalType(qName_parseQName);
            if (globalType == null) {
                return this.defaultLoader;
            }
            try {
                ClassBeanInfoImpl classBeanInfoImpl = (ClassBeanInfoImpl) globalType;
                if (classBeanInfoImpl.getTransducer() == null) {
                    return this.defaultLoader;
                }
                return new LeafPropertyLoader(new TransducedAccessor.CompositeTransducedAccessorImpl(state.getContext().getJAXBContext(), classBeanInfoImpl.getTransducer(), this.acc));
            } catch (ClassCastException unused) {
                return this.defaultLoader;
            }
        }
        return this.defaultLoader;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public Collection<QName> getExpectedChildElements() {
        return this.defaultLoader.getExpectedChildElements();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public Collection<QName> getExpectedAttributes() {
        return this.defaultLoader.getExpectedAttributes();
    }
}
