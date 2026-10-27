package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.util.Collection;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class XsiNilLoader extends ProxyLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Loader defaultLoader;

    protected void onNil(UnmarshallingContext.State state) throws SAXException {
    }

    public XsiNilLoader(Loader loader) {
        this.defaultLoader = loader;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.ProxyLoader
    protected Loader selectLoader(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        Boolean bool_parseBoolean;
        int index = tagName.atts.getIndex(WellKnownNamespace.XML_SCHEMA_INSTANCE, "nil");
        if (index != -1 && (bool_parseBoolean = DatatypeConverterImpl._parseBoolean(tagName.atts.getValue(index))) != null && bool_parseBoolean.booleanValue()) {
            onNil(state);
            if (!(tagName.atts.getLength() - 1 > 0) || !(state.getPrev().getTarget() instanceof JAXBElement)) {
                return Discarder.INSTANCE;
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

    public static final class Single extends XsiNilLoader {
        private final Accessor acc;

        public Single(Loader loader, Accessor accessor) {
            super(loader);
            this.acc = accessor;
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader
        protected void onNil(UnmarshallingContext.State state) throws SAXException {
            try {
                this.acc.set(state.getPrev().getTarget(), null);
                state.getPrev().setNil(true);
            } catch (AccessorException e) {
                handleGenericException(e, true);
            }
        }
    }

    public static final class Array extends XsiNilLoader {
        public Array(Loader loader) {
            super(loader);
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader
        protected void onNil(UnmarshallingContext.State state) {
            state.setTarget(null);
        }
    }
}
