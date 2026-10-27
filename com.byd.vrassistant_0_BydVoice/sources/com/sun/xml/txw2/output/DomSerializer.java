package com.sun.xml.txw2.output;

import com.sun.xml.txw2.TxwException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: classes3.dex */
public class DomSerializer implements XmlSerializer {
    private final SaxSerializer serializer;

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void flush() {
    }

    public DomSerializer(Node node) {
        Dom2SaxAdapter dom2SaxAdapter = new Dom2SaxAdapter(node);
        this.serializer = new SaxSerializer(dom2SaxAdapter, dom2SaxAdapter, false);
    }

    public DomSerializer(DOMResult dOMResult) {
        Node node = dOMResult.getNode();
        if (node == null) {
            try {
                DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
                documentBuilderFactoryNewInstance.setNamespaceAware(true);
                Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
                dOMResult.setNode(documentNewDocument);
                this.serializer = new SaxSerializer(new Dom2SaxAdapter(documentNewDocument), null, false);
                return;
            } catch (ParserConfigurationException e) {
                throw new TxwException(e);
            }
        }
        this.serializer = new SaxSerializer(new Dom2SaxAdapter(node), null, false);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void startDocument() {
        this.serializer.startDocument();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void beginStartTag(String str, String str2, String str3) {
        this.serializer.beginStartTag(str, str2, str3);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeAttribute(String str, String str2, String str3, StringBuilder sb) {
        this.serializer.writeAttribute(str, str2, str3, sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeXmlns(String str, String str2) {
        this.serializer.writeXmlns(str, str2);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endStartTag(String str, String str2, String str3) {
        this.serializer.endStartTag(str, str2, str3);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endTag() {
        this.serializer.endTag();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void text(StringBuilder sb) {
        this.serializer.text(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void cdata(StringBuilder sb) {
        this.serializer.cdata(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void comment(StringBuilder sb) {
        this.serializer.comment(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endDocument() {
        this.serializer.endDocument();
    }
}
