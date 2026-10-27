package com.sun.xml.bind;

import java.util.concurrent.Callable;
import javax.xml.bind.ValidationEventHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IDResolver {
    public abstract void bind(String str, Object obj) throws SAXException;

    public void endDocument() throws SAXException {
    }

    public abstract Callable<?> resolve(String str, Class cls) throws SAXException;

    public void startDocument(ValidationEventHandler validationEventHandler) throws SAXException {
    }
}
