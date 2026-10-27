package com.sun.xml.txw2.output;

import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLFilterImpl;

/* JADX INFO: loaded from: classes3.dex */
public class IndentingXMLFilter extends XMLFilterImpl implements LexicalHandler {
    private LexicalHandler lexical;
    private static final char[] NEWLINE = {'\n'};
    private static final Object SEEN_NOTHING = new Object();
    private static final Object SEEN_ELEMENT = new Object();
    private static final Object SEEN_DATA = new Object();
    private Object state = SEEN_NOTHING;
    private Stack<Object> stateStack = new Stack<>();
    private String indentStep = "";
    private int depth = 0;

    public IndentingXMLFilter() {
    }

    public IndentingXMLFilter(ContentHandler contentHandler) {
        setContentHandler(contentHandler);
    }

    public IndentingXMLFilter(ContentHandler contentHandler, LexicalHandler lexicalHandler) {
        setContentHandler(contentHandler);
        setLexicalHandler(lexicalHandler);
    }

    public LexicalHandler getLexicalHandler() {
        return this.lexical;
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        this.lexical = lexicalHandler;
    }

    public int getIndentStep() {
        return this.indentStep.length();
    }

    public void setIndentStep(int i) {
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            sb.append(' ');
            i--;
        }
        setIndentStep(sb.toString());
    }

    public void setIndentStep(String str) {
        this.indentStep = str;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        this.stateStack.push(SEEN_ELEMENT);
        this.state = SEEN_NOTHING;
        if (this.depth > 0) {
            writeNewLine();
        }
        doIndent();
        super.startElement(str, str2, str3, attributes);
        this.depth++;
    }

    private void writeNewLine() throws SAXException {
        char[] cArr = NEWLINE;
        super.characters(cArr, 0, cArr.length);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        this.depth--;
        if (this.state == SEEN_ELEMENT) {
            writeNewLine();
            doIndent();
        }
        super.endElement(str, str2, str3);
        this.state = this.stateStack.pop();
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this.state = SEEN_DATA;
        super.characters(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.depth > 0) {
            writeNewLine();
        }
        doIndent();
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.comment(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.startDTD(str, str2, str3);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.endDTD();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.startEntity(str);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.endEntity(str);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.startCDATA();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        LexicalHandler lexicalHandler = this.lexical;
        if (lexicalHandler != null) {
            lexicalHandler.endCDATA();
        }
    }

    private void doIndent() throws SAXException {
        if (this.depth > 0) {
            char[] charArray = this.indentStep.toCharArray();
            for (int i = 0; i < this.depth; i++) {
                characters(charArray, 0, charArray.length);
            }
        }
    }
}
