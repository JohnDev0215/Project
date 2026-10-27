package com.sun.xml.bind.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface InfosetScanner<XmlNode> {
    ContentHandler getContentHandler();

    XmlNode getCurrentElement();

    LocatorEx getLocator();

    void scan(XmlNode xmlnode) throws SAXException;

    void setContentHandler(ContentHandler contentHandler);
}
