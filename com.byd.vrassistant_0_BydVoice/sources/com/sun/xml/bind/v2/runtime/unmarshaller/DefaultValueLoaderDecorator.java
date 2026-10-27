package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultValueLoaderDecorator extends Loader {
    private final String defaultValue;
    private final Loader l;

    public DefaultValueLoaderDecorator(Loader loader, String str) {
        this.l = loader;
        this.defaultValue = str;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        if (state.getElementDefaultValue() == null) {
            state.setElementDefaultValue(this.defaultValue);
        }
        state.setLoader(this.l);
        this.l.startElement(state, tagName);
    }
}
