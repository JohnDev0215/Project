package com.sun.xml.txw2.output;

import java.util.Stack;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes3.dex */
public class IndentingXMLStreamWriter extends DelegatingXMLStreamWriter {
    private int depth;
    private String indentStep;
    private Object state;
    private Stack<Object> stateStack;
    private static final Object SEEN_NOTHING = new Object();
    private static final Object SEEN_ELEMENT = new Object();
    private static final Object SEEN_DATA = new Object();

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void close() throws XMLStreamException {
        super.close();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void flush() throws XMLStreamException {
        super.flush();
    }

    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ NamespaceContext getNamespaceContext() {
        return super.getNamespaceContext();
    }

    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ String getPrefix(String str) throws XMLStreamException {
        return super.getPrefix(str);
    }

    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ Object getProperty(String str) throws IllegalArgumentException {
        return super.getProperty(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void setDefaultNamespace(String str) throws XMLStreamException {
        super.setDefaultNamespace(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        super.setNamespaceContext(namespaceContext);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void setPrefix(String str, String str2) throws XMLStreamException {
        super.setPrefix(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeAttribute(String str, String str2) throws XMLStreamException {
        super.writeAttribute(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        super.writeAttribute(str, str2, str3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        super.writeAttribute(str, str2, str3, str4);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeComment(String str) throws XMLStreamException {
        super.writeComment(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeDTD(String str) throws XMLStreamException {
        super.writeDTD(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeDefaultNamespace(String str) throws XMLStreamException {
        super.writeDefaultNamespace(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeEndDocument() throws XMLStreamException {
        super.writeEndDocument();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeEntityRef(String str) throws XMLStreamException {
        super.writeEntityRef(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeNamespace(String str, String str2) throws XMLStreamException {
        super.writeNamespace(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeProcessingInstruction(String str) throws XMLStreamException {
        super.writeProcessingInstruction(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public /* bridge */ /* synthetic */ void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        super.writeProcessingInstruction(str, str2);
    }

    public IndentingXMLStreamWriter(XMLStreamWriter xMLStreamWriter) {
        super(xMLStreamWriter);
        this.state = SEEN_NOTHING;
        this.stateStack = new Stack<>();
        this.indentStep = "  ";
        this.depth = 0;
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void onStartElement() throws XMLStreamException {
        this.stateStack.push(SEEN_ELEMENT);
        this.state = SEEN_NOTHING;
        if (this.depth > 0) {
            super.writeCharacters("\n");
        }
        doIndent();
        this.depth++;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void onEndElement() throws XMLStreamException {
        this.depth--;
        if (this.state == SEEN_ELEMENT) {
            super.writeCharacters("\n");
            doIndent();
        }
        this.state = this.stateStack.pop();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void onEmptyElement() throws XMLStreamException {
        this.state = SEEN_ELEMENT;
        if (this.depth > 0) {
            super.writeCharacters("\n");
        }
        doIndent();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void doIndent() throws XMLStreamException {
        if (this.depth > 0) {
            for (int i = 0; i < this.depth; i++) {
                super.writeCharacters(this.indentStep);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeStartDocument() throws XMLStreamException {
        super.writeStartDocument();
        super.writeCharacters("\n");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeStartDocument(String str) throws XMLStreamException {
        super.writeStartDocument(str);
        super.writeCharacters("\n");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        super.writeStartDocument(str, str2);
        super.writeCharacters("\n");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeStartElement(String str) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeStartElement(String str, String str2) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(str, str2, str3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(str, str2, str3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeEmptyElement(String str) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeEndElement() throws XMLStreamException {
        onEndElement();
        super.writeEndElement();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeCharacters(String str) throws XMLStreamException {
        this.state = SEEN_DATA;
        super.writeCharacters(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        this.state = SEEN_DATA;
        super.writeCharacters(cArr, i, i2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.txw2.output.DelegatingXMLStreamWriter
    public void writeCData(String str) throws XMLStreamException {
        this.state = SEEN_DATA;
        super.writeCData(str);
    }
}
