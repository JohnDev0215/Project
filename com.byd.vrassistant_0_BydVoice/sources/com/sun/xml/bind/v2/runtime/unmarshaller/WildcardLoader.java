package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.model.core.WildcardMode;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import javax.xml.bind.annotation.DomHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class WildcardLoader extends ProxyLoader {
    private final DomLoader dom;
    private final WildcardMode mode;

    public WildcardLoader(DomHandler domHandler, WildcardMode wildcardMode) {
        this.dom = new DomLoader(domHandler);
        this.mode = wildcardMode;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.ProxyLoader
    protected Loader selectLoader(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        Loader loaderSelectRootLoader;
        UnmarshallingContext context = state.getContext();
        if (this.mode.allowTypedObject && (loaderSelectRootLoader = context.selectRootLoader(state, tagName)) != null) {
            return loaderSelectRootLoader;
        }
        if (this.mode.allowDom) {
            return this.dom;
        }
        return Discarder.INSTANCE;
    }
}
