package com.sun.xml.txw2.output;

import com.sun.xml.txw2.TxwException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes3.dex */
public class StaxSerializer implements XmlSerializer {
    private final XMLStreamWriter out;

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endStartTag(String str, String str2, String str3) {
    }

    public StaxSerializer(XMLStreamWriter xMLStreamWriter) {
        this(xMLStreamWriter, true);
    }

    public StaxSerializer(XMLStreamWriter xMLStreamWriter, boolean z) {
        this.out = z ? new IndentingXMLStreamWriter(xMLStreamWriter) : xMLStreamWriter;
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void startDocument() {
        try {
            this.out.writeStartDocument();
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void beginStartTag(String str, String str2, String str3) {
        try {
            this.out.writeStartElement(str3, str2, str);
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeAttribute(String str, String str2, String str3, StringBuilder sb) {
        try {
            this.out.writeAttribute(str3, str, str2, sb.toString());
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeXmlns(String str, String str2) {
        try {
            if (str.length() == 0) {
                this.out.setDefaultNamespace(str2);
            } else {
                this.out.setPrefix(str, str2);
            }
            this.out.writeNamespace(str, str2);
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endTag() {
        try {
            this.out.writeEndElement();
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void text(StringBuilder sb) {
        try {
            this.out.writeCharacters(sb.toString());
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void cdata(StringBuilder sb) {
        try {
            this.out.writeCData(sb.toString());
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void comment(StringBuilder sb) {
        try {
            this.out.writeComment(sb.toString());
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endDocument() {
        try {
            this.out.writeEndDocument();
            this.out.flush();
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void flush() {
        try {
            this.out.flush();
        } catch (XMLStreamException e) {
            throw new TxwException((Throwable) e);
        }
    }
}
