package com.sun.xml.bind.v2.runtime.reflect;

import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface ListIterator<E> {
    boolean hasNext();

    E next() throws SAXException, JAXBException;
}
