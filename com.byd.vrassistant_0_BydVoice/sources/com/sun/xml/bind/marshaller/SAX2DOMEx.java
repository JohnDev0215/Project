package com.sun.xml.bind.marshaller;

import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.util.Which;
import com.sun.xml.bind.v2.util.XmlFactory;
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

/* JADX INFO: loaded from: classes3.dex */
public class SAX2DOMEx implements ContentHandler {
    protected final Document document;
    private boolean isConsolidate;
    private Node node;
    protected final Stack<Node> nodeStack;
    private final FinalArrayList<String> unprocessedNamespaces;

    @Override // org.xml.sax.ContentHandler
    public void endDocument() {
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

    @Override // org.xml.sax.ContentHandler
    public void startDocument() {
    }

    public SAX2DOMEx(Node node) {
        this(node, false);
    }

    public SAX2DOMEx(Node node, boolean z) {
        this.node = null;
        Stack<Node> stack = new Stack<>();
        this.nodeStack = stack;
        this.unprocessedNamespaces = new FinalArrayList<>();
        this.node = node;
        this.isConsolidate = z;
        stack.push(node);
        if (node instanceof Document) {
            this.document = (Document) node;
        } else {
            this.document = node.getOwnerDocument();
        }
    }

    public SAX2DOMEx(DocumentBuilderFactory documentBuilderFactory) throws ParserConfigurationException {
        this.node = null;
        Stack<Node> stack = new Stack<>();
        this.nodeStack = stack;
        this.unprocessedNamespaces = new FinalArrayList<>();
        documentBuilderFactory.setValidating(false);
        Document documentNewDocument = documentBuilderFactory.newDocumentBuilder().newDocument();
        this.document = documentNewDocument;
        this.node = documentNewDocument;
        stack.push(documentNewDocument);
    }

    public SAX2DOMEx() throws ParserConfigurationException {
        this.node = null;
        Stack<Node> stack = new Stack<>();
        this.nodeStack = stack;
        this.unprocessedNamespaces = new FinalArrayList<>();
        DocumentBuilderFactory documentBuilderFactoryCreateDocumentBuilderFactory = XmlFactory.createDocumentBuilderFactory(false);
        documentBuilderFactoryCreateDocumentBuilderFactory.setValidating(false);
        Document documentNewDocument = documentBuilderFactoryCreateDocumentBuilderFactory.newDocumentBuilder().newDocument();
        this.document = documentNewDocument;
        this.node = documentNewDocument;
        stack.push(documentNewDocument);
    }

    public final Element getCurrentElement() {
        return (Element) this.nodeStack.peek();
    }

    public Node getDOM() {
        return this.node;
    }

    protected void namespace(Element element, String str, String str2) {
        String str3 = ("".equals(str) || str == null) ? "xmlns" : "xmlns:" + str;
        if (element.hasAttributeNS("http://www.w3.org/2000/xmlns/", str3)) {
            element.removeAttributeNS("http://www.w3.org/2000/xmlns/", str3);
        }
        element.setAttributeNS("http://www.w3.org/2000/xmlns/", str3, str2);
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        Node nodePeek = this.nodeStack.peek();
        Element elementCreateElementNS = this.document.createElementNS(str, str3);
        if (elementCreateElementNS == null) {
            throw new AssertionError(Messages.format(Messages.DOM_IMPL_DOESNT_SUPPORT_CREATELEMENTNS, this.document.getClass().getName(), Which.which(this.document.getClass())));
        }
        for (int i = 0; i < this.unprocessedNamespaces.size(); i += 2) {
            namespace(elementCreateElementNS, this.unprocessedNamespaces.get(i), this.unprocessedNamespaces.get(i + 1));
        }
        this.unprocessedNamespaces.clear();
        if (attributes != null) {
            int length = attributes.getLength();
            for (int i2 = 0; i2 < length; i2++) {
                elementCreateElementNS.setAttributeNS(attributes.getURI(i2), attributes.getQName(i2), attributes.getValue(i2));
            }
        }
        nodePeek.appendChild(elementCreateElementNS);
        this.nodeStack.push(elementCreateElementNS);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        this.nodeStack.pop();
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        characters(new String(cArr, i, i2));
    }

    protected Text characters(String str) {
        Node nodePeek = this.nodeStack.peek();
        Node lastChild = nodePeek.getLastChild();
        if (this.isConsolidate && lastChild != null && lastChild.getNodeType() == 3) {
            Text text = (Text) lastChild;
            text.appendData(str);
            return text;
        }
        Text textCreateTextNode = this.document.createTextNode(str);
        nodePeek.appendChild(textCreateTextNode);
        return textCreateTextNode;
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        this.nodeStack.peek().appendChild(this.document.createProcessingInstruction(str, str2));
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        this.unprocessedNamespaces.add(str);
        this.unprocessedNamespaces.add(str2);
    }
}
