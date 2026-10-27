package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.util.AttributesImpl;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: classes3.dex */
public class SAXOutput extends XmlOutputAbstractImpl {
    private String elementLocalName;
    private String elementNsUri;
    private String elementQName;
    protected final ContentHandler out;
    private char[] buf = new char[256];
    private final AttributesImpl atts = new AttributesImpl();

    public SAXOutput(ContentHandler contentHandler) {
        this.out = contentHandler;
        contentHandler.setDocumentLocator(new LocatorImpl());
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        super.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
        if (z) {
            return;
        }
        this.out.startDocument();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        if (!z) {
            this.out.endDocument();
        }
        super.endDocument(z);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) {
        this.elementNsUri = this.nsContext.getNamespaceURI(i);
        this.elementLocalName = str;
        this.elementQName = getQName(i, str);
        this.atts.clear();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) {
        String str3;
        String str4;
        if (i == -1) {
            str4 = "";
            str3 = str;
        } else {
            String namespaceURI = this.nsContext.getNamespaceURI(i);
            String prefix = this.nsContext.getPrefix(i);
            str3 = prefix.length() == 0 ? str : prefix + ':' + str;
            str4 = namespaceURI;
        }
        this.atts.addAttribute(str4, str, str3, "CDATA", str2);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws SAXException {
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        if (current != null) {
            int iCount = current.count();
            for (int i = 0; i < iCount; i++) {
                String prefix = current.getPrefix(i);
                String nsUri = current.getNsUri(i);
                if (nsUri.length() != 0 || current.getBase() != 1) {
                    this.out.startPrefixMapping(prefix, nsUri);
                }
            }
        }
        this.out.startElement(this.elementNsUri, this.elementLocalName, this.elementQName, this.atts);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws SAXException {
        this.out.endElement(this.nsContext.getNamespaceURI(i), str, getQName(i, str));
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        if (current != null) {
            for (int iCount = current.count() - 1; iCount >= 0; iCount--) {
                String prefix = current.getPrefix(iCount);
                if (current.getNsUri(iCount).length() != 0 || current.getBase() != 1) {
                    this.out.endPrefixMapping(prefix);
                }
            }
        }
    }

    private String getQName(int i, String str) {
        String prefix = this.nsContext.getPrefix(i);
        return prefix.length() == 0 ? str : prefix + ':' + str;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws SAXException, XMLStreamException, IOException {
        int length = str.length();
        char[] cArr = this.buf;
        if (cArr.length <= length) {
            this.buf = new char[Math.max(cArr.length * 2, length + 1)];
        }
        if (z) {
            str.getChars(0, length, this.buf, 1);
            this.buf[0] = ' ';
        } else {
            str.getChars(0, length, this.buf, 0);
        }
        this.out.characters(this.buf, 0, length + (z ? 1 : 0));
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws SAXException, XMLStreamException, IOException {
        int length = pcdata.length();
        char[] cArr = this.buf;
        if (cArr.length <= length) {
            this.buf = new char[Math.max(cArr.length * 2, length + 1)];
        }
        if (z) {
            pcdata.writeTo(this.buf, 1);
            this.buf[0] = ' ';
        } else {
            pcdata.writeTo(this.buf, 0);
        }
        this.out.characters(this.buf, 0, length + (z ? 1 : 0));
    }
}
