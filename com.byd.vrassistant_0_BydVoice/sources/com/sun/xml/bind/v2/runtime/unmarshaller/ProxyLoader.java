package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ProxyLoader extends Loader {
    protected abstract Loader selectLoader(UnmarshallingContext.State state, TagName tagName) throws SAXException;

    public ProxyLoader() {
        super(false);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public final void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        Loader loaderSelectLoader = selectLoader(state, tagName);
        state.setLoader(loaderSelectLoader);
        loaderSelectLoader.startElement(state, tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public final void leaveElement(UnmarshallingContext.State state, TagName tagName) {
        throw new IllegalStateException();
    }
}
