package com.sun.xml.txw2.output;

import com.sun.xml.txw2.TxwException;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: compiled from: DomSerializer.java */
/* JADX INFO: loaded from: classes3.dex */
class Dom2SaxAdapter implements ContentHandler, LexicalHandler {
    private final Document _document;
    private final Node _node;
    private final Stack _nodeStk;
    private boolean inCDATA;
    private ArrayList unprocessedNamespaces;

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) {
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) {
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    public final Element getCurrentElement() {
        return (Element) this._nodeStk.peek();
    }

    public Dom2SaxAdapter(Node node) {
        Stack stack = new Stack();
        this._nodeStk = stack;
        this.unprocessedNamespaces = new ArrayList();
        this._node = node;
        stack.push(node);
        if (node instanceof Document) {
            this._document = (Document) node;
        } else {
            this._document = node.getOwnerDocument();
        }
    }

    public Dom2SaxAdapter() throws ParserConfigurationException {
        Stack stack = new Stack();
        this._nodeStk = stack;
        this.unprocessedNamespaces = new ArrayList();
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(true);
        documentBuilderFactoryNewInstance.setValidating(false);
        Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
        this._document = documentNewDocument;
        this._node = documentNewDocument;
        stack.push(documentNewDocument);
    }

    public Node getDOM() {
        return this._node;
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        Element elementCreateElementNS = this._document.createElementNS(str, str3);
        if (elementCreateElementNS == null) {
            throw new TxwException("Your DOM provider doesn't support the createElementNS method properly");
        }
        for (int i = 0; i < this.unprocessedNamespaces.size(); i += 2) {
            String str4 = (String) this.unprocessedNamespaces.get(i + 0);
            String str5 = (String) this.unprocessedNamespaces.get(i + 1);
            String str6 = ("".equals(str4) || str4 == null) ? "xmlns" : "xmlns:" + str4;
            if (elementCreateElementNS.hasAttributeNS("http://www.w3.org/2000/xmlns/", str6)) {
                elementCreateElementNS.removeAttributeNS("http://www.w3.org/2000/xmlns/", str6);
            }
            elementCreateElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", str6, str5);
        }
        this.unprocessedNamespaces.clear();
        int length = attributes.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            elementCreateElementNS.setAttributeNS(attributes.getURI(i2), attributes.getQName(i2), attributes.getValue(i2));
        }
        getParent().appendChild(elementCreateElementNS);
        this._nodeStk.push(elementCreateElementNS);
    }

    private final Node getParent() {
        return (Node) this._nodeStk.peek();
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        this._nodeStk.pop();
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        Text textCreateTextNode;
        if (this.inCDATA) {
            textCreateTextNode = this._document.createCDATASection(new String(cArr, i, i2));
        } else {
            textCreateTextNode = this._document.createTextNode(new String(cArr, i, i2));
        }
        getParent().appendChild(textCreateTextNode);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        getParent().appendChild(this._document.createComment(new String(cArr, i, i2)));
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        getParent().appendChild(this._document.createProcessingInstruction(str, str2));
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        this.unprocessedNamespaces.add(str);
        this.unprocessedNamespaces.add(str2);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        this.inCDATA = true;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        this.inCDATA = false;
    }
}
