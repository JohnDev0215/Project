package com.sun.xml.txw2.output;

import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ResultFactory {
    private ResultFactory() {
    }

    public static XmlSerializer createSerializer(Result result) {
        if (result instanceof SAXResult) {
            return new SaxSerializer((SAXResult) result);
        }
        if (result instanceof DOMResult) {
            return new DomSerializer((DOMResult) result);
        }
        if (result instanceof StreamResult) {
            return new StreamSerializer((StreamResult) result);
        }
        if (result instanceof TXWResult) {
            return new TXWSerializer(((TXWResult) result).getWriter());
        }
        throw new UnsupportedOperationException("Unsupported Result type: " + result.getClass().getName());
    }
}
