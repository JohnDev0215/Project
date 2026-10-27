package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.core.PrettyPrinter;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.XMLStreamWriter2;
import org.codehaus.stax2.typed.Base64Variant;

/* JADX INFO: loaded from: classes6.dex */
public interface XmlPrettyPrinter extends PrettyPrinter {
    void writeEndElement(XMLStreamWriter2 xMLStreamWriter2, int i) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, double d) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, float f) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, int i) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, long j) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, String str3, boolean z) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, BigDecimal bigDecimal) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, BigInteger bigInteger) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, Base64Variant base64Variant, byte[] bArr, int i, int i2) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, boolean z) throws XMLStreamException;

    void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, char[] cArr, int i, int i2, boolean z) throws XMLStreamException;

    void writeLeafNullElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2) throws XMLStreamException;

    void writePrologLinefeed(XMLStreamWriter2 xMLStreamWriter2) throws XMLStreamException;

    void writeStartElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2) throws XMLStreamException;
}
