package com.sun.xml.txw2.output;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/* JADX INFO: loaded from: classes3.dex */
public class XMLWriter extends XMLFilterImpl implements LexicalHandler {
    private final Attributes EMPTY_ATTS;
    private int elementLevel;
    private String encoding;
    private final CharacterEscapeHandler escapeHandler;
    private String header;
    private boolean inCDATA;
    private final HashMap locallyDeclaredPrefix;
    private Writer output;
    private boolean startTagIsClosed;
    private boolean writeXmlDecl;

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    public XMLWriter(Writer writer, String str, CharacterEscapeHandler characterEscapeHandler) {
        this.locallyDeclaredPrefix = new HashMap();
        this.EMPTY_ATTS = new AttributesImpl();
        this.inCDATA = false;
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

    public void setEncoding(String str) {
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
                write("<?xml version=\"1.0\"" + (this.encoding != null ? " encoding=\"" + this.encoding + "\"" : "") + " standalone=\"yes\"?>\n");
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

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        try {
            if (!this.startTagIsClosed) {
                write("/>");
                this.startTagIsClosed = true;
            }
            write('\n');
            super.endDocument();
            try {
                flush();
            } catch (IOException e) {
                throw new SAXException(e);
            }
        } catch (IOException e2) {
            throw new SAXException(e2);
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
            writeName(str, str2, str3, true);
            writeAttributes(attributes);
            if (!this.locallyDeclaredPrefix.isEmpty()) {
                for (Map.Entry entry : this.locallyDeclaredPrefix.entrySet()) {
                    String str4 = (String) entry.getKey();
                    String str5 = (String) entry.getValue();
                    if (str5 == null) {
                        str5 = "";
                    }
                    write(' ');
                    if ("".equals(str4)) {
                        write("xmlns=\"");
                    } else {
                        write("xmlns:");
                        write(str4);
                        write("=\"");
                    }
                    char[] charArray = str5.toCharArray();
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
                writeName(str, str2, str3, true);
                write(Typography.greater);
            } else {
                write("/>");
                this.startTagIsClosed = true;
            }
            if (this.elementLevel == 1) {
                write('\n');
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
            if (this.inCDATA) {
                this.output.write(cArr, i, i2);
            } else {
                writeEsc(cArr, i, i2, false);
            }
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

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        try {
            if (!this.startTagIsClosed) {
                write(Typography.greater);
                this.startTagIsClosed = true;
            }
            write("<![CDATA[");
            this.inCDATA = true;
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        try {
            this.inCDATA = false;
            write("]]>");
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        try {
            this.output.write("<!--");
            this.output.write(cArr, i, i2);
            this.output.write("-->");
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    private void write(char c) throws IOException {
        this.output.write(c);
    }

    private void write(String str) throws IOException {
        this.output.write(str);
    }

    private void writeAttributes(Attributes attributes) throws SAXException, IOException {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            char[] charArray = attributes.getValue(i).toCharArray();
            write(' ');
            writeName(attributes.getURI(i), attributes.getLocalName(i), attributes.getQName(i), false);
            write("=\"");
            writeEsc(charArray, 0, charArray.length, true);
            write(Typography.quote);
        }
    }

    private void writeEsc(char[] cArr, int i, int i2, boolean z) throws SAXException, IOException {
        this.escapeHandler.escape(cArr, i, i2, z, this.output);
    }

    private void writeName(String str, String str2, String str3, boolean z) throws IOException {
        write(str3);
    }
}
