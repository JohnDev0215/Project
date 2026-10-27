package org.codehaus.stax2.typed;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes7.dex */
public interface TypedXMLStreamWriter extends XMLStreamWriter {
    void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws XMLStreamException;

    void writeBinaryAttribute(Base64Variant base64Variant, String str, String str2, String str3, byte[] bArr) throws XMLStreamException;

    void writeBoolean(boolean z) throws XMLStreamException;

    void writeBooleanAttribute(String str, String str2, String str3, boolean z) throws XMLStreamException;

    void writeDecimal(BigDecimal bigDecimal) throws XMLStreamException;

    void writeDecimalAttribute(String str, String str2, String str3, BigDecimal bigDecimal) throws XMLStreamException;

    void writeDouble(double d) throws XMLStreamException;

    void writeDoubleAttribute(String str, String str2, String str3, double d) throws XMLStreamException;

    void writeFloat(float f) throws XMLStreamException;

    void writeFloatAttribute(String str, String str2, String str3, float f) throws XMLStreamException;

    void writeInt(int i) throws XMLStreamException;

    void writeIntAttribute(String str, String str2, String str3, int i) throws XMLStreamException;

    void writeInteger(BigInteger bigInteger) throws XMLStreamException;

    void writeIntegerAttribute(String str, String str2, String str3, BigInteger bigInteger) throws XMLStreamException;

    void writeLong(long j) throws XMLStreamException;

    void writeLongAttribute(String str, String str2, String str3, long j) throws XMLStreamException;
}
