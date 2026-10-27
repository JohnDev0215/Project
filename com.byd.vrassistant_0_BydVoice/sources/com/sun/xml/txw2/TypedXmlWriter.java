package com.sun.xml.txw2;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface TypedXmlWriter {
    void _attribute(String str, Object obj);

    void _attribute(String str, String str2, Object obj);

    void _attribute(QName qName, Object obj);

    <T extends TypedXmlWriter> T _cast(Class<T> cls);

    void _cdata(Object obj);

    void _comment(Object obj) throws UnsupportedOperationException;

    <T extends TypedXmlWriter> T _element(Class<T> cls);

    <T extends TypedXmlWriter> T _element(String str, Class<T> cls);

    <T extends TypedXmlWriter> T _element(String str, String str2, Class<T> cls);

    <T extends TypedXmlWriter> T _element(QName qName, Class<T> cls);

    void _namespace(String str);

    void _namespace(String str, String str2);

    void _namespace(String str, boolean z);

    void _pcdata(Object obj);

    void block();

    void commit();

    void commit(boolean z);

    Document getDocument();
}
