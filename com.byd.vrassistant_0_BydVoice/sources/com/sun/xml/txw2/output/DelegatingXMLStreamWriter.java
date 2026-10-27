package com.sun.xml.txw2.output;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes3.dex */
abstract class DelegatingXMLStreamWriter implements XMLStreamWriter {
    private final XMLStreamWriter writer;

    public DelegatingXMLStreamWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    public void writeStartElement(String str) throws XMLStreamException {
        this.writer.writeStartElement(str);
    }

    public void writeStartElement(String str, String str2) throws XMLStreamException {
        this.writer.writeStartElement(str, str2);
    }

    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        this.writer.writeStartElement(str, str2, str3);
    }

    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        this.writer.writeEmptyElement(str, str2);
    }

    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        this.writer.writeEmptyElement(str, str2, str3);
    }

    public void writeEmptyElement(String str) throws XMLStreamException {
        this.writer.writeEmptyElement(str);
    }

    public void writeEndElement() throws XMLStreamException {
        this.writer.writeEndElement();
    }

    public void writeEndDocument() throws XMLStreamException {
        this.writer.writeEndDocument();
    }

    public void close() throws XMLStreamException {
        this.writer.close();
    }

    public void flush() throws XMLStreamException {
        this.writer.flush();
    }

    public void writeAttribute(String str, String str2) throws XMLStreamException {
        this.writer.writeAttribute(str, str2);
    }

    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        this.writer.writeAttribute(str, str2, str3, str4);
    }

    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        this.writer.writeAttribute(str, str2, str3);
    }

    public void writeNamespace(String str, String str2) throws XMLStreamException {
        this.writer.writeNamespace(str, str2);
    }

    public void writeDefaultNamespace(String str) throws XMLStreamException {
        this.writer.writeDefaultNamespace(str);
    }

    public void writeComment(String str) throws XMLStreamException {
        this.writer.writeComment(str);
    }

    public void writeProcessingInstruction(String str) throws XMLStreamException {
        this.writer.writeProcessingInstruction(str);
    }

    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        this.writer.writeProcessingInstruction(str, str2);
    }

    public void writeCData(String str) throws XMLStreamException {
        this.writer.writeCData(str);
    }

    public void writeDTD(String str) throws XMLStreamException {
        this.writer.writeDTD(str);
    }

    public void writeEntityRef(String str) throws XMLStreamException {
        this.writer.writeEntityRef(str);
    }

    public void writeStartDocument() throws XMLStreamException {
        this.writer.writeStartDocument();
    }

    public void writeStartDocument(String str) throws XMLStreamException {
        this.writer.writeStartDocument(str);
    }

    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        this.writer.writeStartDocument(str, str2);
    }

    public void writeCharacters(String str) throws XMLStreamException {
        this.writer.writeCharacters(str);
    }

    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        this.writer.writeCharacters(cArr, i, i2);
    }

    public String getPrefix(String str) throws XMLStreamException {
        return this.writer.getPrefix(str);
    }

    public void setPrefix(String str, String str2) throws XMLStreamException {
        this.writer.setPrefix(str, str2);
    }

    public void setDefaultNamespace(String str) throws XMLStreamException {
        this.writer.setDefaultNamespace(str);
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        this.writer.setNamespaceContext(namespaceContext);
    }

    public NamespaceContext getNamespaceContext() {
        return this.writer.getNamespaceContext();
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        return this.writer.getProperty(str);
    }
}
