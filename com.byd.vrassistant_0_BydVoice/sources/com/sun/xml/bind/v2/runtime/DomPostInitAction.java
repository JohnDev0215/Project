package com.sun.xml.bind.v2.runtime;

import java.util.HashSet;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: classes3.dex */
final class DomPostInitAction implements Runnable {
    private final Node node;
    private final XMLSerializer serializer;

    DomPostInitAction(Node node, XMLSerializer xMLSerializer) {
        this.node = node;
        this.serializer = xMLSerializer;
    }

    @Override // java.lang.Runnable
    public void run() {
        String localName;
        HashSet hashSet = new HashSet();
        for (Node parentNode = this.node; parentNode != null && parentNode.getNodeType() == 1; parentNode = parentNode.getParentNode()) {
            NamedNodeMap attributes = parentNode.getAttributes();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Attr attr = (Attr) attributes.item(i);
                    String namespaceURI = attr.getNamespaceURI();
                    if (namespaceURI != null && namespaceURI.equals("http://www.w3.org/2000/xmlns/") && (localName = attr.getLocalName()) != null) {
                        if (localName.equals("xmlns")) {
                            localName = "";
                        }
                        String value = attr.getValue();
                        if (value != null && hashSet.add(localName)) {
                            this.serializer.addInscopeBinding(value, localName);
                        }
                    }
                }
            }
        }
    }
}
