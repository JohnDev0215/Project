package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import javax.xml.namespace.QName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class XsiTypeLoader extends Loader {
    static final QName XsiTypeQNAME = new QName(WellKnownNamespace.XML_SCHEMA_INSTANCE, "type");
    private final JaxBeanInfo defaultBeanInfo;

    public XsiTypeLoader(JaxBeanInfo jaxBeanInfo) {
        super(true);
        this.defaultBeanInfo = jaxBeanInfo;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        JaxBeanInfo xsiType = parseXsiType(state, tagName, this.defaultBeanInfo);
        if (xsiType == null) {
            xsiType = this.defaultBeanInfo;
        }
        Loader loader = xsiType.getLoader(null, false);
        state.setLoader(loader);
        loader.startElement(state, tagName);
    }

    static JaxBeanInfo parseXsiType(UnmarshallingContext.State state, TagName tagName, JaxBeanInfo jaxBeanInfo) throws SAXException {
        UnmarshallingContext context = state.getContext();
        Attributes attributes = tagName.atts;
        int index = attributes.getIndex(WellKnownNamespace.XML_SCHEMA_INSTANCE, "type");
        if (index >= 0) {
            String value = attributes.getValue(index);
            QName qName_parseQName = DatatypeConverterImpl._parseQName(value, context);
            if (qName_parseQName != null) {
                if (jaxBeanInfo != null && jaxBeanInfo.getTypeNames().contains(qName_parseQName)) {
                    return jaxBeanInfo;
                }
                JaxBeanInfo globalType = context.getJAXBContext().getGlobalType(qName_parseQName);
                if (globalType != null || !context.parent.hasEventHandler() || !context.shouldErrorBeReported()) {
                    return globalType;
                }
                String nearestTypeName = context.getJAXBContext().getNearestTypeName(qName_parseQName);
                if (nearestTypeName != null) {
                    reportError(Messages.UNRECOGNIZED_TYPE_NAME_MAYBE.format(qName_parseQName, nearestTypeName), true);
                    return globalType;
                }
                reportError(Messages.UNRECOGNIZED_TYPE_NAME.format(qName_parseQName), true);
                return globalType;
            }
            reportError(Messages.NOT_A_QNAME.format(value), true);
        }
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public Collection<QName> getExpectedAttributes() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(super.getExpectedAttributes());
        hashSet.add(XsiTypeQNAME);
        return Collections.unmodifiableCollection(hashSet);
    }
}
