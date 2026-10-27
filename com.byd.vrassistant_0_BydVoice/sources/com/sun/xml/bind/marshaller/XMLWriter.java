package com.sun.xml.bind.marshaller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/* JADX INFO: loaded from: classes3.dex */
public class XMLWriter extends XMLFilterImpl {
    private final Attributes EMPTY_ATTS;
    private int elementLevel;
    private String encoding;
    private final CharacterEscapeHandler escapeHandler;
    private String header;
    private final HashMap<String, String> locallyDeclaredPrefix;
    private Writer output;
    private boolean startTagIsClosed;
    private boolean writeXmlDecl;

    public XMLWriter(Writer writer, String str, CharacterEscapeHandler characterEscapeHandler) {
        this.locallyDeclaredPrefix = new HashMap<>();
        this.EMPTY_ATTS = new AttributesImpl();
        this.elementLevel = 0;
        this.writeXmlDecl = true;
        this.header = null;
        this.startTagIsClosed = true;
        init(writer, str);
        this.escapeHandler = characterEscapeHandler;
    }

    public XMLWriter(Writer writer, String str) {
        this(writer, str, DumbEscapeHandler.theInstance);
    }

    private void init(Writer writer, String str) {
        setOutput(writer, str);
    }

    public void reset() {
        this.elementLevel = 0;
        this.startTagIsClosed = true;
    }

    public void flush() throws IOException {
        this.output.flush();
    }

    public void setOutput(Writer writer, String str) {
        if (writer == null) {
            this.output = new OutputStreamWriter(System.out);
        } else {
            this.output = writer;
        }
        this.encoding = str;
    }

    public void setXmlDecl(boolean z) {
        this.writeXmlDecl = z;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        this.locallyDeclaredPrefix.put(str, str2);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        try {
            reset();
            if (this.writeXmlDecl) {
                writeXmlDecl("<?xml version=\"1.0\"" + (this.encoding != null ? " encoding=\"" + this.encoding + Typography.quote : "") + " standalone=\"yes\"?>");
            }
            String str = this.header;
            if (str != null) {
                write(str);
            }
            super.startDocument();
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    protected void writeXmlDecl(String str) throws IOException {
        write(str);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        try {
            super.endDocument();
            flush();
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        try {
            if (!this.startTagIsClosed) {
                write(">");
            }
            this.elementLevel++;
            write(Typography.less);
            write(str3);
            writeAttributes(attributes);
            if (!this.locallyDeclaredPrefix.isEmpty()) {
                for (Map.Entry<String, String> entry : this.locallyDeclaredPrefix.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (value == null) {
                        value = "";
                    }
                    write(' ');
                    if ("".equals(key)) {
                        write("xmlns=\"");
                    } else {
                        write("xmlns:");
                        write(key);
                        write("=\"");
                    }
                    char[] charArray = value.toCharArray();
                    writeEsc(charArray, 0, charArray.length, true);
                    write(Typography.quote);
                }
                this.locallyDeclaredPrefix.clear();
            }
            super.startElement(str, str2, str3, attributes);
            this.startTagIsClosed = false;
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        try {
            if (this.startTagIsClosed) {
                write("</");
                write(str3);
                write(Typography.greater);
            } else {
                write("/>");
                this.startTagIsClosed = true;
            }
            super.endElement(str, str2, str3);
            this.elementLevel--;
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        try {
            if (!this.startTagIsClosed) {
                write(Typography.greater);
                this.startTagIsClosed = true;
            }
            writeEsc(cArr, i, i2, false);
            super.characters(cArr, i, i2);
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        try {
            writeEsc(cArr, i, i2, false);
            super.ignorableWhitespace(cArr, i, i2);
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        try {
            if (!this.startTagIsClosed) {
                write(Typography.greater);
                this.startTagIsClosed = true;
            }
            write("<?");
            write(str);
            write(' ');
            write(str2);
            write("?>");
            if (this.elementLevel < 1) {
                write('\n');
            }
            super.processingInstruction(str, str2);
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    public void startElement(String str, String str2) throws SAXException {
        startElement(str, str2, "", this.EMPTY_ATTS);
    }

    public void startElement(String str) throws SAXException {
        startElement("", str, "", this.EMPTY_ATTS);
    }

    public void endElement(String str, String str2) throws SAXException {
        endElement(str, str2, "");
    }

    public void endElement(String str) throws SAXException {
        endElement("", str, "");
    }

    public void dataElement(String str, String str2, String str3, Attributes attributes, String str4) throws SAXException {
        startElement(str, str2, str3, attributes);
        characters(str4);
        endElement(str, str2, str3);
    }

    public void dataElement(String str, String str2, String str3) throws SAXException {
        dataElement(str, str2, "", this.EMPTY_ATTS, str3);
    }

    public void dataElement(String str, String str2) throws SAXException {
        dataElement("", str, "", this.EMPTY_ATTS, str2);
    }

    public void characters(String str) throws SAXException {
        try {
            if (!this.startTagIsClosed) {
                write(Typography.greater);
                this.startTagIsClosed = true;
            }
            char[] charArray = str.toCharArray();
            characters(charArray, 0, charArray.length);
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    protected final void write(char c) throws IOException {
        this.output.write(c);
    }

    protected final void write(String str) throws IOException {
        this.output.write(str);
    }

    private void writeAttributes(Attributes attributes) throws IOException {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            char[] charArray = attributes.getValue(i).toCharArray();
            write(' ');
            write(attributes.getQName(i));
            write("=\"");
            writeEsc(charArray, 0, charArray.length, true);
            write(Typography.quote);
        }
    }

    private void writeEsc(char[] cArr, int i, int i2, boolean z) throws IOException {
        this.escapeHandler.escape(cArr, i, i2, z, this.output);
    }
}
