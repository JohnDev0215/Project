package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class TextLoader extends Loader {
    private final Transducer xducer;

    public TextLoader(Transducer transducer) {
        super(true);
        this.xducer = transducer;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
        try {
            state.setTarget(this.xducer.parse(charSequence));
        } catch (AccessorException e) {
            handleGenericException(e, true);
        } catch (RuntimeException e2) {
            handleParseConversionException(state, e2);
        }
    }
}
