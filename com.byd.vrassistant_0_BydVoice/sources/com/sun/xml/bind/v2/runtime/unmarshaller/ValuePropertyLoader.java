package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import javax.xml.bind.JAXBElement;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class ValuePropertyLoader extends Loader {
    private final TransducedAccessor xacc;

    public ValuePropertyLoader(TransducedAccessor transducedAccessor) {
        super(true);
        this.xacc = transducedAccessor;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
        try {
            this.xacc.parse(state.getTarget(), charSequence);
        } catch (AccessorException e) {
            handleGenericException(e, true);
        } catch (RuntimeException e2) {
            if (state.getPrev() != null) {
                if (state.getPrev().getTarget() instanceof JAXBElement) {
                    return;
                }
                handleParseConversionException(state, e2);
                return;
            }
            handleParseConversionException(state, e2);
        }
    }
}
