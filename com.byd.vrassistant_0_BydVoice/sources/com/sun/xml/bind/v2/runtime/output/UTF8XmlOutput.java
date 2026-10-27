package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class UTF8XmlOutput extends XmlOutputAbstractImpl {
    private CharacterEscapeHandler escapeHandler;
    private String header;
    private final Encoded[] localNames;
    protected int octetBufferIndex;
    protected final OutputStream out;
    private int prefixCount;
    private static final byte[] _XMLNS_EQUALS = toBytes(" xmlns=\"");
    private static final byte[] _XMLNS_COLON = toBytes(" xmlns:");
    private static final byte[] _EQUALS = toBytes("=\"");
    private static final byte[] _CLOSE_TAG = toBytes("</");
    private static final byte[] _EMPTY_TAG = toBytes("/>");
    private static final byte[] _XML_DECL = toBytes("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private Encoded[] prefixes = new Encoded[8];
    private final Encoded textBuffer = new Encoded();
    protected final byte[] octetBuffer = new byte[1024];
    protected boolean closeStartTagPending = false;
    private final byte[] XMLNS_EQUALS = (byte[]) _XMLNS_EQUALS.clone();
    private final byte[] XMLNS_COLON = (byte[]) _XMLNS_COLON.clone();
    private final byte[] EQUALS = (byte[]) _EQUALS.clone();
    private final byte[] CLOSE_TAG = (byte[]) _CLOSE_TAG.clone();
    private final byte[] EMPTY_TAG = (byte[]) _EMPTY_TAG.clone();
    private final byte[] XML_DECL = (byte[]) _XML_DECL.clone();

    public UTF8XmlOutput(OutputStream outputStream, Encoded[] encodedArr, CharacterEscapeHandler characterEscapeHandler) {
        int i = 0;
        this.escapeHandler = null;
        this.out = outputStream;
        this.localNames = encodedArr;
        while (true) {
            Encoded[] encodedArr2 = this.prefixes;
            if (i < encodedArr2.length) {
                encodedArr2[i] = new Encoded();
                i++;
            } else {
                this.escapeHandler = characterEscapeHandler;
                return;
            }
        }
    }

    public void setHeader(String str) {
        this.header = str;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        super.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
        this.octetBufferIndex = 0;
        if (!z) {
            write(this.XML_DECL);
        }
        String str = this.header;
        if (str != null) {
            this.textBuffer.set(str);
            this.textBuffer.write(this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        flushBuffer();
        super.endDocument(z);
    }

    protected final void closeStartTag() throws IOException {
        if (this.closeStartTagPending) {
            write(62);
            this.closeStartTagPending = false;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) throws IOException {
        closeStartTag();
        int iPushNsDecls = pushNsDecls();
        write(60);
        writeName(i, str);
        writeNsDecls(iPushNsDecls);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(Name name) throws IOException {
        closeStartTag();
        int iPushNsDecls = pushNsDecls();
        write(60);
        writeName(name);
        writeNsDecls(iPushNsDecls);
    }

    private int pushNsDecls() {
        int iCount = this.nsContext.count();
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        Encoded[] encodedArr = this.prefixes;
        if (iCount > encodedArr.length) {
            int iMax = Math.max(iCount, encodedArr.length * 2);
            Encoded[] encodedArr2 = new Encoded[iMax];
            Encoded[] encodedArr3 = this.prefixes;
            System.arraycopy(encodedArr3, 0, encodedArr2, 0, encodedArr3.length);
            for (int length = this.prefixes.length; length < iMax; length++) {
                encodedArr2[length] = new Encoded();
            }
            this.prefixes = encodedArr2;
        }
        int iMin = Math.min(this.prefixCount, current.getBase());
        int iCount2 = this.nsContext.count();
        for (int i = iMin; i < iCount2; i++) {
            String prefix = this.nsContext.getPrefix(i);
            Encoded encoded = this.prefixes[i];
            if (prefix.length() == 0) {
                encoded.buf = EMPTY_BYTE_ARRAY;
                encoded.len = 0;
            } else {
                encoded.set(prefix);
                encoded.append(':');
            }
        }
        this.prefixCount = iCount2;
        return iMin;
    }

    protected void writeNsDecls(int i) throws IOException {
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        int iCount = this.nsContext.count();
        for (int base = current.getBase(); base < iCount; base++) {
            writeNsDecl(base);
        }
    }

    protected final void writeNsDecl(int i) throws IOException {
        if (this.nsContext.getPrefix(i).length() == 0) {
            if (this.nsContext.getCurrent().isRootElement() && this.nsContext.getNamespaceURI(i).length() == 0) {
                return;
            } else {
                write(this.XMLNS_EQUALS);
            }
        } else {
            Encoded encoded = this.prefixes[i];
            write(this.XMLNS_COLON);
            write(encoded.buf, 0, encoded.len - 1);
            write(this.EQUALS);
        }
        doText(this.nsContext.getNamespaceURI(i), true);
        write(34);
    }

    private void writePrefix(int i) throws IOException {
        this.prefixes[i].write(this);
    }

    private void writeName(Name name) throws IOException {
        writePrefix(this.nsUriIndex2prefixIndex[name.nsUriIndex]);
        this.localNames[name.localNameIndex].write(this);
    }

    private void writeName(int i, String str) throws IOException {
        writePrefix(i);
        this.textBuffer.set(str);
        this.textBuffer.write(this);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(Name name, String str) throws IOException {
        write(32);
        if (name.nsUriIndex == -1) {
            this.localNames[name.localNameIndex].write(this);
        } else {
            writeName(name);
        }
        write(this.EQUALS);
        doText(str, true);
        write(34);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) throws IOException {
        write(32);
        if (i == -1) {
            this.textBuffer.set(str);
            this.textBuffer.write(this);
        } else {
            writeName(i, str);
        }
        write(this.EQUALS);
        doText(str2, true);
        write(34);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws IOException {
        this.closeStartTagPending = true;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(Name name) throws IOException {
        if (this.closeStartTagPending) {
            write(this.EMPTY_TAG);
            this.closeStartTagPending = false;
        } else {
            write(this.CLOSE_TAG);
            writeName(name);
            write(62);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws IOException {
        if (this.closeStartTagPending) {
            write(this.EMPTY_TAG);
            this.closeStartTagPending = false;
        } else {
            write(this.CLOSE_TAG);
            writeName(i, str);
            write(62);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws IOException {
        closeStartTag();
        if (z) {
            write(32);
        }
        doText(str, false);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws IOException {
        closeStartTag();
        if (z) {
            write(32);
        }
        pcdata.writeTo(this);
    }

    private void doText(String str, boolean z) throws IOException {
        if (this.escapeHandler != null) {
            StringWriter stringWriter = new StringWriter();
            this.escapeHandler.escape(str.toCharArray(), 0, str.length(), z, stringWriter);
            this.textBuffer.set(stringWriter.toString());
        } else {
            this.textBuffer.setEscape(str, z);
        }
        this.textBuffer.write(this);
    }

    public final void text(int i) throws IOException {
        closeStartTag();
        boolean z = i < 0;
        int i2 = 11;
        this.textBuffer.ensureSize(11);
        byte[] bArr = this.textBuffer.buf;
        do {
            int i3 = i % 10;
            if (i3 < 0) {
                i3 = -i3;
            }
            i2--;
            bArr[i2] = (byte) (i3 | 48);
            i /= 10;
        } while (i != 0);
        if (z) {
            i2--;
            bArr[i2] = 45;
        }
        write(bArr, i2, 11 - i2);
    }

    public void text(byte[] bArr, int i) throws IOException {
        closeStartTag();
        int i2 = 0;
        while (i > 0) {
            int iMin = Math.min(((this.octetBuffer.length - this.octetBufferIndex) / 4) * 3, i);
            this.octetBufferIndex = DatatypeConverterImpl._printBase64Binary(bArr, i2, iMin, this.octetBuffer, this.octetBufferIndex);
            if (iMin < i) {
                flushBuffer();
            }
            i2 += iMin;
            i -= iMin;
        }
    }

    public final void write(int i) throws IOException {
        int i2 = this.octetBufferIndex;
        byte[] bArr = this.octetBuffer;
        if (i2 < bArr.length) {
            this.octetBufferIndex = i2 + 1;
            bArr[i2] = (byte) i;
        } else {
            this.out.write(bArr);
            this.octetBufferIndex = 1;
            this.octetBuffer[0] = (byte) i;
        }
    }

    protected final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    protected final void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.octetBufferIndex;
        int i4 = i3 + i2;
        byte[] bArr2 = this.octetBuffer;
        if (i4 < bArr2.length) {
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.octetBufferIndex += i2;
        } else {
            this.out.write(bArr2, 0, i3);
            this.out.write(bArr, i, i2);
            this.octetBufferIndex = 0;
        }
    }

    protected final void flushBuffer() throws IOException {
        this.out.write(this.octetBuffer, 0, this.octetBufferIndex);
        this.octetBufferIndex = 0;
    }

    static byte[] toBytes(String str) {
        byte[] bArr = new byte[str.length()];
        for (int length = str.length() - 1; length >= 0; length--) {
            bArr[length] = (byte) str.charAt(length);
        }
        return bArr;
    }
}
