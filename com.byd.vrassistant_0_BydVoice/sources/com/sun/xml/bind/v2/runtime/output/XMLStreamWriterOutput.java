package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class XMLStreamWriterOutput extends XmlOutputAbstractImpl {
    protected final char[] buf = new char[256];
    private final CharacterEscapeHandler escapeHandler;
    private final XMLStreamWriter out;
    private final XmlStreamOutWriterAdapter writerWrapper;
    private static final Class FI_STAX_WRITER_CLASS = initFIStAXWriterClass();
    private static final Constructor<? extends XmlOutput> FI_OUTPUT_CTOR = initFastInfosetOutputClass();
    private static final Class STAXEX_WRITER_CLASS = initStAXExWriterClass();
    private static final Constructor<? extends XmlOutput> STAXEX_OUTPUT_CTOR = initStAXExOutputClass();

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws SAXException, IOException {
    }

    public static XmlOutput create(XMLStreamWriter xMLStreamWriter, JAXBContextImpl jAXBContextImpl, CharacterEscapeHandler characterEscapeHandler) {
        Class<?> cls = xMLStreamWriter.getClass();
        if (cls == FI_STAX_WRITER_CLASS) {
            try {
                return FI_OUTPUT_CTOR.newInstance(xMLStreamWriter, jAXBContextImpl);
            } catch (Exception unused) {
            }
        }
        Class cls2 = STAXEX_WRITER_CLASS;
        if (cls2 != null && cls2.isAssignableFrom(cls)) {
            try {
                return STAXEX_OUTPUT_CTOR.newInstance(xMLStreamWriter);
            } catch (Exception unused2) {
            }
        }
        if (characterEscapeHandler == null) {
            characterEscapeHandler = NewLineEscapeHandler.theInstance;
        }
        return new XMLStreamWriterOutput(xMLStreamWriter, characterEscapeHandler);
    }

    protected XMLStreamWriterOutput(XMLStreamWriter xMLStreamWriter, CharacterEscapeHandler characterEscapeHandler) {
        this.out = xMLStreamWriter;
        this.escapeHandler = characterEscapeHandler;
        this.writerWrapper = new XmlStreamOutWriterAdapter(xMLStreamWriter);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        super.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
        if (z) {
            return;
        }
        this.out.writeStartDocument();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        if (!z) {
            this.out.writeEndDocument();
            this.out.flush();
        }
        super.endDocument(z);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) throws XMLStreamException, IOException {
        this.out.writeStartElement(this.nsContext.getPrefix(i), str, this.nsContext.getNamespaceURI(i));
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        if (current.count() > 0) {
            for (int iCount = current.count() - 1; iCount >= 0; iCount--) {
                String nsUri = current.getNsUri(iCount);
                if (nsUri.length() != 0 || current.getBase() != 1) {
                    this.out.writeNamespace(current.getPrefix(iCount), nsUri);
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) throws XMLStreamException, IOException {
        if (i == -1) {
            this.out.writeAttribute(str, str2);
        } else {
            this.out.writeAttribute(this.nsContext.getPrefix(i), this.nsContext.getNamespaceURI(i), str, str2);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws SAXException, XMLStreamException, IOException {
        this.out.writeEndElement();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws SAXException, XMLStreamException, IOException {
        if (z) {
            this.out.writeCharacters(" ");
        }
        this.escapeHandler.escape(str.toCharArray(), 0, str.length(), false, this.writerWrapper);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws SAXException, XMLStreamException, IOException {
        if (z) {
            this.out.writeCharacters(" ");
        }
        int length = pcdata.length();
        char[] cArr = this.buf;
        if (length < cArr.length) {
            pcdata.writeTo(cArr, 0);
            this.out.writeCharacters(this.buf, 0, length);
        } else {
            this.out.writeCharacters(pcdata.toString());
        }
    }

    private static Class initFIStAXWriterClass() {
        Class<?> cls;
        Class<?> cls2;
        try {
            cls = Class.forName("org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter");
            cls2 = Class.forName("com.sun.xml.fastinfoset.stax.StAXDocumentSerializer");
        } catch (Throwable unused) {
        }
        if (cls.isAssignableFrom(cls2)) {
            return cls2;
        }
        return null;
    }

    private static Constructor<? extends XmlOutput> initFastInfosetOutputClass() {
        try {
            Class<?> cls = FI_STAX_WRITER_CLASS;
            if (cls == null) {
                return null;
            }
            return Class.forName("com.sun.xml.bind.v2.runtime.output.FastInfosetStreamWriterOutput").getConstructor(cls, JAXBContextImpl.class);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class initStAXExWriterClass() {
        try {
            return Class.forName("org.jvnet.staxex.XMLStreamWriterEx");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Constructor<? extends XmlOutput> initStAXExOutputClass() {
        try {
            return Class.forName("com.sun.xml.bind.v2.runtime.output.StAXExStreamWriterOutput").getConstructor(STAXEX_WRITER_CLASS);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static class NewLineEscapeHandler implements CharacterEscapeHandler {
        public static final NewLineEscapeHandler theInstance = new NewLineEscapeHandler();

        private NewLineEscapeHandler() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.marshaller.CharacterEscapeHandler
        public void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException {
            int i3 = i + i2;
            int i4 = i;
            while (i < i3) {
                char c = cArr[i];
                if (c == '\r' || c == '\n') {
                    if (i != i4) {
                        writer.write(cArr, i4, i - i4);
                    }
                    i4 = i + 1;
                    if (writer instanceof XmlStreamOutWriterAdapter) {
                        try {
                            ((XmlStreamOutWriterAdapter) writer).writeEntityRef("#x" + Integer.toHexString(c));
                        } catch (XMLStreamException e) {
                            throw new IOException("Error writing xml stream", e);
                        }
                    } else {
                        writer.write("&#x");
                        writer.write(Integer.toHexString(c));
                        writer.write(59);
                    }
                }
                i++;
            }
            if (i4 != i3) {
                writer.write(cArr, i4, i2 - i4);
            }
        }
    }

    private static final class XmlStreamOutWriterAdapter extends Writer {
        private final XMLStreamWriter writer;

        private XmlStreamOutWriterAdapter(XMLStreamWriter xMLStreamWriter) {
            this.writer = xMLStreamWriter;
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            try {
                this.writer.writeCharacters(cArr, i, i2);
            } catch (XMLStreamException e) {
                throw new IOException("Error writing XML stream", e);
            }
        }

        public void writeEntityRef(String str) throws XMLStreamException {
            this.writer.writeEntityRef(str);
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            try {
                this.writer.flush();
            } catch (XMLStreamException e) {
                throw new IOException("Error flushing XML stream", e);
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                this.writer.close();
            } catch (XMLStreamException e) {
                throw new IOException("Error closing XML stream", e);
            }
        }
    }
}
