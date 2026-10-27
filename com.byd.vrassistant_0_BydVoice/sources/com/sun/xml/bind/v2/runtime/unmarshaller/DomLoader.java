package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Result;
import javax.xml.transform.sax.TransformerHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class DomLoader<ResultT extends Result> extends Loader {
    private final DomHandler<?, ResultT> dom;

    private final class State {
        int depth = 1;
        private TransformerHandler handler;
        private final ResultT result;

        public State(UnmarshallingContext unmarshallingContext) throws SAXException {
            this.handler = null;
            this.handler = JAXBContextImpl.createTransformerHandler(unmarshallingContext.getJAXBContext().disableSecurityProcessing);
            ResultT resultt = (ResultT) DomLoader.this.dom.createUnmarshaller(unmarshallingContext);
            this.result = resultt;
            this.handler.setResult(resultt);
            try {
                this.handler.setDocumentLocator(unmarshallingContext.getLocator());
                this.handler.startDocument();
                declarePrefixes(unmarshallingContext, unmarshallingContext.getAllDeclaredPrefixes());
            } catch (SAXException e) {
                unmarshallingContext.handleError(e);
                throw e;
            }
        }

        public Object getElement() {
            return DomLoader.this.dom.getElement(this.result);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void declarePrefixes(UnmarshallingContext unmarshallingContext, String[] strArr) throws SAXException {
            for (int length = strArr.length - 1; length >= 0; length--) {
                String namespaceURI = unmarshallingContext.getNamespaceURI(strArr[length]);
                if (namespaceURI == null) {
                    throw new IllegalStateException("prefix '" + strArr[length] + "' isn't bound");
                }
                this.handler.startPrefixMapping(strArr[length], namespaceURI);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void undeclarePrefixes(String[] strArr) throws SAXException {
            for (int length = strArr.length - 1; length >= 0; length--) {
                this.handler.endPrefixMapping(strArr[length]);
            }
        }
    }

    public DomLoader(DomHandler<?, ResultT> domHandler) {
        super(true);
        this.dom = domHandler;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        UnmarshallingContext context = state.getContext();
        if (state.getTarget() == null) {
            state.setTarget(new State(context));
        }
        State state2 = (State) state.getTarget();
        try {
            state2.declarePrefixes(context, context.getNewlyDeclaredPrefixes());
            state2.handler.startElement(tagName.uri, tagName.local, tagName.getQname(), tagName.atts);
        } catch (SAXException e) {
            context.handleError(e);
            throw e;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        state.setLoader(this);
        State state2 = (State) state.getPrev().getTarget();
        state2.depth++;
        state.setTarget(state2);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
        if (charSequence.length() == 0) {
            return;
        }
        try {
            ((State) state.getTarget()).handler.characters(charSequence.toString().toCharArray(), 0, charSequence.length());
        } catch (SAXException e) {
            state.getContext().handleError(e);
            throw e;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void leaveElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        State state2 = (State) state.getTarget();
        UnmarshallingContext context = state.getContext();
        try {
            state2.handler.endElement(tagName.uri, tagName.local, tagName.getQname());
            state2.undeclarePrefixes(context.getNewlyDeclaredPrefixes());
            int i = state2.depth - 1;
            state2.depth = i;
            if (i == 0) {
                try {
                    state2.undeclarePrefixes(context.getAllDeclaredPrefixes());
                    state2.handler.endDocument();
                    state.setTarget(state2.getElement());
                } catch (SAXException e) {
                    context.handleError(e);
                    throw e;
                }
            }
        } catch (SAXException e2) {
            context.handleError(e2);
            throw e2;
        }
    }
}
