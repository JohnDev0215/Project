package com.sun.xml.bind.v2.runtime;

import com.sun.istack.FinalArrayList;
import com.sun.istack.SAXException2;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: classes3.dex */
final class ContentHandlerAdaptor extends DefaultHandler {
    private final XMLSerializer serializer;
    private final FinalArrayList<String> prefixMap = new FinalArrayList<>();
    private final StringBuffer text = new StringBuffer();

    ContentHandlerAdaptor(XMLSerializer xMLSerializer) {
        this.serializer = xMLSerializer;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() {
        this.prefixMap.clear();
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        this.prefixMap.add(str);
        this.prefixMap.add(str2);
    }

    private boolean containsPrefixMapping(String str, String str2) {
        for (int i = 0; i < this.prefixMap.size(); i += 2) {
            if (this.prefixMap.get(i).equals(str) && this.prefixMap.get(i + 1).equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        try {
            flushText();
            int length = attributes.getLength();
            String prefix = getPrefix(str3);
            if (containsPrefixMapping(prefix, str)) {
                this.serializer.startElementForce(str, str2, prefix, null);
            } else {
                this.serializer.startElement(str, str2, prefix, null);
            }
            for (int i = 0; i < this.prefixMap.size(); i += 2) {
                this.serializer.getNamespaceContext().force(this.prefixMap.get(i + 1), this.prefixMap.get(i));
            }
            for (int i2 = 0; i2 < length; i2++) {
                String qName = attributes.getQName(i2);
                if (!qName.startsWith("xmlns") && attributes.getURI(i2).length() != 0) {
                    this.serializer.getNamespaceContext().declareNamespace(attributes.getURI(i2), getPrefix(qName), true);
                }
            }
            this.serializer.endNamespaceDecls(null);
            for (int i3 = 0; i3 < length; i3++) {
                if (!attributes.getQName(i3).startsWith("xmlns")) {
                    this.serializer.attribute(attributes.getURI(i3), attributes.getLocalName(i3), attributes.getValue(i3));
                }
            }
            this.prefixMap.clear();
            this.serializer.endAttributes();
        } catch (XMLStreamException e) {
            throw new SAXException2((Exception) e);
        } catch (IOException e2) {
            throw new SAXException2(e2);
        }
    }

    private String getPrefix(String str) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf == -1 ? "" : str.substring(0, iIndexOf);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        try {
            flushText();
            this.serializer.endElement();
        } catch (XMLStreamException e) {
            throw new SAXException2((Exception) e);
        } catch (IOException e2) {
            throw new SAXException2(e2);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void flushText() throws SAXException, XMLStreamException, IOException {
        if (this.text.length() != 0) {
            this.serializer.text(this.text.toString(), (String) null);
            this.text.setLength(0);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        this.text.append(cArr, i, i2);
    }
}
