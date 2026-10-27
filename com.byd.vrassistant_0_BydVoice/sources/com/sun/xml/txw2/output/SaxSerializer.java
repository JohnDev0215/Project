package com.sun.xml.txw2.output;

import com.sun.xml.txw2.TxwException;
import java.util.Stack;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: classes3.dex */
public class SaxSerializer implements XmlSerializer {
    private final AttributesImpl attrs;
    private final Stack<String> elementBindings;
    private final LexicalHandler lexical;
    private final Stack<String> prefixBindings;
    private final ContentHandler writer;

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void flush() {
    }

    public SaxSerializer(ContentHandler contentHandler) {
        this(contentHandler, null, true);
    }

    public SaxSerializer(ContentHandler contentHandler, LexicalHandler lexicalHandler) {
        this(contentHandler, lexicalHandler, true);
    }

    public SaxSerializer(ContentHandler contentHandler, LexicalHandler lexicalHandler, boolean z) {
        this.prefixBindings = new Stack<>();
        this.elementBindings = new Stack<>();
        this.attrs = new AttributesImpl();
        if (!z) {
            this.writer = contentHandler;
            this.lexical = lexicalHandler;
        } else {
            IndentingXMLFilter indentingXMLFilter = new IndentingXMLFilter(contentHandler, lexicalHandler);
            this.writer = indentingXMLFilter;
            this.lexical = indentingXMLFilter;
        }
    }

    public SaxSerializer(SAXResult sAXResult) {
        this(sAXResult.getHandler(), sAXResult.getLexicalHandler());
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void startDocument() {
        try {
            this.writer.startDocument();
        } catch (SAXException e) {
            throw new TxwException(e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeXmlns(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str.equals("xml")) {
            return;
        }
        this.prefixBindings.add(str2);
        this.prefixBindings.add(str);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void beginStartTag(String str, String str2, String str3) {
        this.elementBindings.add(getQName(str3, str2));
        this.elementBindings.add(str2);
        this.elementBindings.add(str);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeAttribute(String str, String str2, String str3, StringBuilder sb) {
        this.attrs.addAttribute(str, str2, getQName(str3, str2), "CDATA", sb.toString());
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endStartTag(String str, String str2, String str3) {
        while (this.prefixBindings.size() != 0) {
            try {
                this.writer.startPrefixMapping(this.prefixBindings.pop(), this.prefixBindings.pop());
            } catch (SAXException e) {
                throw new TxwException(e);
            }
        }
        this.writer.startElement(str, str2, getQName(str3, str2), this.attrs);
        this.attrs.clear();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endTag() {
        try {
            this.writer.endElement(this.elementBindings.pop(), this.elementBindings.pop(), this.elementBindings.pop());
        } catch (SAXException e) {
            throw new TxwException(e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void text(StringBuilder sb) {
        try {
            this.writer.characters(sb.toString().toCharArray(), 0, sb.length());
        } catch (SAXException e) {
            throw new TxwException(e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void cdata(StringBuilder sb) {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler == null) {
            throw new UnsupportedOperationException("LexicalHandler is needed to write PCDATA");
        }
        try {
            lexicalHandler.startCDATA();
            text(sb);
            this.lexical.endCDATA();
        } catch (SAXException e) {
            throw new TxwException(e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void comment(StringBuilder sb) {
        try {
            LexicalHandler lexicalHandler = this.lexical;
            if (lexicalHandler == null) {
                throw new UnsupportedOperationException("LexicalHandler is needed to write comments");
            }
            lexicalHandler.comment(sb.toString().toCharArray(), 0, sb.length());
        } catch (SAXException e) {
            throw new TxwException(e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endDocument() {
        try {
            this.writer.endDocument();
        } catch (SAXException e) {
            throw new TxwException(e);
        }
    }

    private static String getQName(String str, String str2) {
        return (str == null || str.length() == 0) ? str2 : str + ':' + str2;
    }
}
