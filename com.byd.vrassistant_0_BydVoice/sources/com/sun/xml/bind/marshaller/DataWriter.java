package com.sun.xml.bind.marshaller;

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class DataWriter extends XMLWriter {
    private int depth;
    private String indentStep;
    private Object state;
    private Stack<Object> stateStack;
    private static final Object SEEN_NOTHING = new Object();
    private static final Object SEEN_ELEMENT = new Object();
    private static final Object SEEN_DATA = new Object();

    public DataWriter(Writer writer, String str, CharacterEscapeHandler characterEscapeHandler) {
        super(writer, str, characterEscapeHandler);
        this.state = SEEN_NOTHING;
        this.stateStack = new Stack<>();
        this.indentStep = "";
        this.depth = 0;
    }

    public DataWriter(Writer writer, String str) {
        this(writer, str, DumbEscapeHandler.theInstance);
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

    @Override // com.sun.xml.bind.marshaller.XMLWriter
    public void reset() {
        this.depth = 0;
        this.state = SEEN_NOTHING;
        this.stateStack = new Stack<>();
        super.reset();
    }

    @Override // com.sun.xml.bind.marshaller.XMLWriter
    protected void writeXmlDecl(String str) throws IOException {
        super.writeXmlDecl(str);
        write('\n');
    }

    @Override // com.sun.xml.bind.marshaller.XMLWriter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        this.stateStack.push(SEEN_ELEMENT);
        this.state = SEEN_NOTHING;
        if (this.depth > 0) {
            super.characters("\n");
        }
        doIndent();
        super.startElement(str, str2, str3, attributes);
        this.depth++;
    }

    @Override // com.sun.xml.bind.marshaller.XMLWriter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        this.depth--;
        if (this.state == SEEN_ELEMENT) {
            super.characters("\n");
            doIndent();
        }
        super.endElement(str, str2, str3);
        this.state = this.stateStack.pop();
    }

    @Override // com.sun.xml.bind.marshaller.XMLWriter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        try {
            write('\n');
            super.endDocument();
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // com.sun.xml.bind.marshaller.XMLWriter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this.state = SEEN_DATA;
        super.characters(cArr, i, i2);
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
