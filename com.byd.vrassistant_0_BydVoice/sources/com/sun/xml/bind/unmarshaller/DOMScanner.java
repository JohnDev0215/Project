package com.sun.xml.bind.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx;
import java.util.Enumeration;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.helpers.ValidationEventLocatorImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;

/* JADX INFO: loaded from: classes3.dex */
public class DOMScanner implements LocatorEx, InfosetScanner {
    private Node currentNode = null;
    private final AttributesImpl atts = new AttributesImpl();
    private ContentHandler receiver = null;
    private Locator locator = this;

    @Override // org.xml.sax.Locator
    public int getColumnNumber() {
        return -1;
    }

    @Override // org.xml.sax.Locator
    public int getLineNumber() {
        return -1;
    }

    @Override // com.sun.xml.bind.unmarshaller.InfosetScanner
    public LocatorEx getLocator() {
        return this;
    }

    @Override // org.xml.sax.Locator
    public String getPublicId() {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getSystemId() {
        return null;
    }

    public void setLocator(Locator locator) {
        this.locator = locator;
    }

    @Override // com.sun.xml.bind.unmarshaller.InfosetScanner
    public void scan(Object obj) throws SAXException {
        if (obj instanceof Document) {
            scan((Document) obj);
        } else {
            scan((Element) obj);
        }
    }

    public void scan(Document document) throws SAXException {
        scan(document.getDocumentElement());
    }

    public void scan(Element element) throws SAXException {
        setCurrentLocation(element);
        this.receiver.setDocumentLocator(this.locator);
        this.receiver.startDocument();
        NamespaceSupport namespaceSupport = new NamespaceSupport();
        buildNamespaceSupport(namespaceSupport, element.getParentNode());
        Enumeration prefixes = namespaceSupport.getPrefixes();
        while (prefixes.hasMoreElements()) {
            String str = (String) prefixes.nextElement();
            this.receiver.startPrefixMapping(str, namespaceSupport.getURI(str));
        }
        visit(element);
        Enumeration prefixes2 = namespaceSupport.getPrefixes();
        while (prefixes2.hasMoreElements()) {
            this.receiver.endPrefixMapping((String) prefixes2.nextElement());
        }
        setCurrentLocation(element);
        this.receiver.endDocument();
    }

    public void parse(Element element, ContentHandler contentHandler) throws SAXException {
        this.receiver = contentHandler;
        setCurrentLocation(element);
        this.receiver.startDocument();
        this.receiver.setDocumentLocator(this.locator);
        visit(element);
        setCurrentLocation(element);
        this.receiver.endDocument();
    }

    public void parseWithContext(Element element, ContentHandler contentHandler) throws SAXException {
        setContentHandler(contentHandler);
        scan(element);
    }

    private void buildNamespaceSupport(NamespaceSupport namespaceSupport, Node node) {
        if (node == null || node.getNodeType() != 1) {
            return;
        }
        buildNamespaceSupport(namespaceSupport, node.getParentNode());
        namespaceSupport.pushContext();
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            if ("xmlns".equals(attr.getPrefix())) {
                namespaceSupport.declarePrefix(attr.getLocalName(), attr.getValue());
            } else if ("xmlns".equals(attr.getName())) {
                namespaceSupport.declarePrefix("", attr.getValue());
            }
        }
    }

    public void visit(Element element) throws SAXException {
        setCurrentLocation(element);
        NamedNodeMap attributes = element.getAttributes();
        this.atts.clear();
        int length = (attributes == null ? 0 : attributes.getLength()) - 1;
        for (int i = length; i >= 0; i--) {
            Attr attr = (Attr) attributes.item(i);
            String name = attr.getName();
            if (name.startsWith("xmlns")) {
                if (name.length() == 5) {
                    this.receiver.startPrefixMapping("", attr.getValue());
                } else {
                    String localName = attr.getLocalName();
                    if (localName == null) {
                        localName = name.substring(6);
                    }
                    this.receiver.startPrefixMapping(localName, attr.getValue());
                }
            } else {
                String namespaceURI = attr.getNamespaceURI();
                String str = namespaceURI == null ? "" : namespaceURI;
                String localName2 = attr.getLocalName();
                if (localName2 == null) {
                    localName2 = attr.getName();
                }
                this.atts.addAttribute(str, localName2, attr.getName(), "CDATA", attr.getValue());
            }
        }
        String namespaceURI2 = element.getNamespaceURI();
        if (namespaceURI2 == null) {
            namespaceURI2 = "";
        }
        String localName3 = element.getLocalName();
        String tagName = element.getTagName();
        if (localName3 == null) {
            localName3 = tagName;
        }
        this.receiver.startElement(namespaceURI2, localName3, tagName, this.atts);
        NodeList childNodes = element.getChildNodes();
        int length2 = childNodes.getLength();
        for (int i2 = 0; i2 < length2; i2++) {
            visit(childNodes.item(i2));
        }
        setCurrentLocation(element);
        this.receiver.endElement(namespaceURI2, localName3, tagName);
        while (length >= 0) {
            Attr attr2 = (Attr) attributes.item(length);
            String name2 = attr2.getName();
            if (name2.startsWith("xmlns")) {
                if (name2.length() == 5) {
                    this.receiver.endPrefixMapping("");
                } else {
                    this.receiver.endPrefixMapping(attr2.getLocalName());
                }
            }
            length--;
        }
    }

    private void visit(Node node) throws SAXException {
        setCurrentLocation(node);
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            visit((Element) node);
            return;
        }
        if (nodeType == 7) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) node;
            this.receiver.processingInstruction(processingInstruction.getTarget(), processingInstruction.getData());
        } else if (nodeType == 3 || nodeType == 4) {
            String nodeValue = node.getNodeValue();
            this.receiver.characters(nodeValue.toCharArray(), 0, nodeValue.length());
        } else {
            if (nodeType != 5) {
                return;
            }
            this.receiver.skippedEntity(node.getNodeName());
        }
    }

    private void setCurrentLocation(Node node) {
        this.currentNode = node;
    }

    public Node getCurrentLocation() {
        return this.currentNode;
    }

    @Override // com.sun.xml.bind.unmarshaller.InfosetScanner
    public Object getCurrentElement() {
        return this.currentNode;
    }

    @Override // com.sun.xml.bind.unmarshaller.InfosetScanner
    public void setContentHandler(ContentHandler contentHandler) {
        this.receiver = contentHandler;
    }

    @Override // com.sun.xml.bind.unmarshaller.InfosetScanner
    public ContentHandler getContentHandler() {
        return this.receiver;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx
    public ValidationEventLocator getLocation() {
        return new ValidationEventLocatorImpl(getCurrentLocation());
    }
}
