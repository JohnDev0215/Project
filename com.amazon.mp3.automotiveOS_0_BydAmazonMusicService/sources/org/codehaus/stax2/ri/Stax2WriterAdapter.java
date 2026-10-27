package org.codehaus.stax2.ri;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.codehaus.stax2.XMLStreamWriter2;
import org.codehaus.stax2.ri.typed.SimpleValueEncoder;
import org.codehaus.stax2.typed.Base64Variant;
import org.codehaus.stax2.util.StreamWriterDelegate;

/* JADX INFO: loaded from: classes7.dex */
public class Stax2WriterAdapter extends StreamWriterDelegate implements XMLStreamWriter2, XMLStreamConstants {
    protected final boolean mNsRepairing;
    protected SimpleValueEncoder mValueEncoder;

    protected Stax2WriterAdapter(XMLStreamWriter xMLStreamWriter) {
        super(xMLStreamWriter);
        this.mDelegate = xMLStreamWriter;
        Object property = xMLStreamWriter.getProperty("javax.xml.stream.isRepairingNamespaces");
        this.mNsRepairing = (property instanceof Boolean) && ((Boolean) property).booleanValue();
    }

    public static XMLStreamWriter2 wrapIfNecessary(XMLStreamWriter xMLStreamWriter) {
        if (xMLStreamWriter instanceof XMLStreamWriter2) {
            return (XMLStreamWriter2) xMLStreamWriter;
        }
        return new Stax2WriterAdapter(xMLStreamWriter);
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeBoolean(boolean z) throws XMLStreamException {
        this.mDelegate.writeCharacters(z ? "true" : "false");
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeInt(int i) throws XMLStreamException {
        this.mDelegate.writeCharacters(String.valueOf(i));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeLong(long j) throws XMLStreamException {
        this.mDelegate.writeCharacters(String.valueOf(j));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeFloat(float f) throws XMLStreamException {
        this.mDelegate.writeCharacters(String.valueOf(f));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeDouble(double d) throws XMLStreamException {
        this.mDelegate.writeCharacters(String.valueOf(d));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeInteger(BigInteger bigInteger) throws XMLStreamException {
        this.mDelegate.writeCharacters(bigInteger.toString());
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeDecimal(BigDecimal bigDecimal) throws XMLStreamException {
        this.mDelegate.writeCharacters(bigDecimal.toString());
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws XMLStreamException {
        this.mDelegate.writeCharacters(getValueEncoder().encodeAsString(base64Variant, bArr, i, i2));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeBooleanAttribute(String str, String str2, String str3, boolean z) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, z ? "true" : "false");
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeIntAttribute(String str, String str2, String str3, int i) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, String.valueOf(i));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeLongAttribute(String str, String str2, String str3, long j) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, String.valueOf(j));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeFloatAttribute(String str, String str2, String str3, float f) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, String.valueOf(f));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeDoubleAttribute(String str, String str2, String str3, double d) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, String.valueOf(d));
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeIntegerAttribute(String str, String str2, String str3, BigInteger bigInteger) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, bigInteger.toString());
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeDecimalAttribute(String str, String str2, String str3, BigDecimal bigDecimal) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, bigDecimal.toString());
    }

    @Override // org.codehaus.stax2.typed.TypedXMLStreamWriter
    public void writeBinaryAttribute(Base64Variant base64Variant, String str, String str2, String str3, byte[] bArr) throws XMLStreamException {
        this.mDelegate.writeAttribute(str, str2, str3, getValueEncoder().encodeAsString(base64Variant, bArr, 0, bArr.length));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamWriter2
    public void writeCData(char[] cArr, int i, int i2) throws XMLStreamException {
        writeCData(new String(cArr, i, i2));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamWriter2
    public void writeRaw(String str) throws XMLStreamException {
        writeRaw(str, 0, str.length());
    }

    @Override // org.codehaus.stax2.XMLStreamWriter2
    public void writeRaw(String str, int i, int i2) throws XMLStreamException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamWriter2
    public void writeRaw(char[] cArr, int i, int i2) throws XMLStreamException {
        writeRaw(new String(cArr, i, i2));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamWriter2
    public void closeCompletely() throws XMLStreamException {
        close();
    }

    protected SimpleValueEncoder getValueEncoder() {
        if (this.mValueEncoder == null) {
            this.mValueEncoder = new SimpleValueEncoder();
        }
        return this.mValueEncoder;
    }
}
