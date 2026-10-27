package com.sun.xml.bind.v2.runtime;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes3.dex */
final class StAXPostInitAction implements Runnable {
    private final NamespaceContext nsc;
    private final XMLSerializer serializer;
    private final XMLEventWriter xew;
    private final XMLStreamWriter xsw;

    StAXPostInitAction(XMLStreamWriter xMLStreamWriter, XMLSerializer xMLSerializer) {
        this.xsw = xMLStreamWriter;
        this.xew = null;
        this.nsc = null;
        this.serializer = xMLSerializer;
    }

    StAXPostInitAction(XMLEventWriter xMLEventWriter, XMLSerializer xMLSerializer) {
        this.xsw = null;
        this.xew = xMLEventWriter;
        this.nsc = null;
        this.serializer = xMLSerializer;
    }

    StAXPostInitAction(NamespaceContext namespaceContext, XMLSerializer xMLSerializer) {
        this.xsw = null;
        this.xew = null;
        this.nsc = namespaceContext;
        this.serializer = xMLSerializer;
    }

    @Override // java.lang.Runnable
    public void run() {
        NamespaceContext namespaceContext = this.nsc;
        XMLStreamWriter xMLStreamWriter = this.xsw;
        if (xMLStreamWriter != null) {
            namespaceContext = xMLStreamWriter.getNamespaceContext();
        }
        XMLEventWriter xMLEventWriter = this.xew;
        if (xMLEventWriter != null) {
            namespaceContext = xMLEventWriter.getNamespaceContext();
        }
        if (namespaceContext == null) {
            return;
        }
        for (String str : this.serializer.grammar.nameList.namespaceURIs) {
            String prefix = namespaceContext.getPrefix(str);
            if (prefix != null) {
                this.serializer.addInscopeBinding(str, prefix);
            }
        }
    }
}
