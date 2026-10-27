package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.ClassFactory;
import java.util.HashMap;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.helpers.ValidationEventImpl;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Coordinator implements ErrorHandler, ValidationEventHandler {
    private static final ThreadLocal<Coordinator> activeTable = new ThreadLocal<>();
    private final HashMap<Class<? extends XmlAdapter>, XmlAdapter> adapters = new HashMap<>();
    private Coordinator old;

    protected abstract ValidationEventLocator getLocation();

    public final XmlAdapter putAdapter(Class<? extends XmlAdapter> cls, XmlAdapter xmlAdapter) {
        if (xmlAdapter == null) {
            return this.adapters.remove(cls);
        }
        return this.adapters.put(cls, xmlAdapter);
    }

    public final <T extends XmlAdapter> T getAdapter(Class<T> cls) {
        T tCast = cls.cast(this.adapters.get(cls));
        if (tCast != null) {
            return tCast;
        }
        T t = (T) ClassFactory.create(cls);
        putAdapter(cls, t);
        return t;
    }

    public <T extends XmlAdapter> boolean containsAdapter(Class<T> cls) {
        return this.adapters.containsKey(cls);
    }

    protected final void pushCoordinator() {
        ThreadLocal<Coordinator> threadLocal = activeTable;
        this.old = threadLocal.get();
        threadLocal.set(this);
    }

    protected final void popCoordinator() {
        Coordinator coordinator = this.old;
        if (coordinator != null) {
            activeTable.set(coordinator);
        } else {
            activeTable.remove();
        }
        this.old = null;
    }

    public static Coordinator _getInstance() {
        return activeTable.get();
    }

    @Override // org.xml.sax.ErrorHandler
    public final void error(SAXParseException sAXParseException) throws SAXException {
        propagateEvent(1, sAXParseException);
    }

    @Override // org.xml.sax.ErrorHandler
    public final void warning(SAXParseException sAXParseException) throws SAXException {
        propagateEvent(0, sAXParseException);
    }

    @Override // org.xml.sax.ErrorHandler
    public final void fatalError(SAXParseException sAXParseException) throws SAXException {
        propagateEvent(2, sAXParseException);
    }

    private void propagateEvent(int i, SAXParseException sAXParseException) throws SAXException {
        ValidationEventImpl validationEventImpl = new ValidationEventImpl(i, sAXParseException.getMessage(), getLocation());
        Exception exception = sAXParseException.getException();
        if (exception != null) {
            validationEventImpl.setLinkedException(exception);
        } else {
            validationEventImpl.setLinkedException(sAXParseException);
        }
        if (!handleEvent(validationEventImpl)) {
            throw sAXParseException;
        }
    }
}
