package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;

/* JADX INFO: loaded from: classes3.dex */
public final class Discarder extends Loader {
    public static final Loader INSTANCE = new Discarder();

    private Discarder() {
        super(false);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void childElement(UnmarshallingContext.State state, TagName tagName) {
        state.setTarget(null);
        state.setLoader(this);
    }
}
