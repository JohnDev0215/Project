package com.fasterxml.jackson.dataformat.xml.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.XMLStreamWriter2;
import org.codehaus.stax2.typed.Base64Variant;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultXmlPrettyPrinter implements XmlPrettyPrinter, Instantiatable<DefaultXmlPrettyPrinter>, Serializable {
    private static final long serialVersionUID = 1;
    protected Indenter _arrayIndenter;
    protected transient boolean _justHadStartElement;
    protected transient int _nesting;
    protected Indenter _objectIndenter;
    protected boolean _spacesInObjectEntries;

    public interface Indenter {
        boolean isInline();

        void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException;

        void writeIndentation(XMLStreamWriter2 xMLStreamWriter2, int i) throws XMLStreamException;
    }

    protected static class NopIndenter implements Indenter, Serializable {
        private static final long serialVersionUID = 1;

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public boolean isInline() {
            return true;
        }

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i) {
        }

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public void writeIndentation(XMLStreamWriter2 xMLStreamWriter2, int i) {
        }
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException {
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException {
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException {
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException {
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException {
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException {
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeStartArray(JsonGenerator jsonGenerator) throws IOException {
    }

    public DefaultXmlPrettyPrinter() {
        this._arrayIndenter = new FixedSpaceIndenter();
        this._objectIndenter = new Lf2SpacesIndenter();
        this._spacesInObjectEntries = true;
        this._nesting = 0;
    }

    protected DefaultXmlPrettyPrinter(DefaultXmlPrettyPrinter defaultXmlPrettyPrinter) {
        this._arrayIndenter = new FixedSpaceIndenter();
        this._objectIndenter = new Lf2SpacesIndenter();
        this._spacesInObjectEntries = true;
        this._nesting = 0;
        this._arrayIndenter = defaultXmlPrettyPrinter._arrayIndenter;
        this._objectIndenter = defaultXmlPrettyPrinter._objectIndenter;
        this._spacesInObjectEntries = defaultXmlPrettyPrinter._spacesInObjectEntries;
        this._nesting = defaultXmlPrettyPrinter._nesting;
    }

    public void indentArraysWith(Indenter indenter) {
        if (indenter == null) {
            indenter = new NopIndenter();
        }
        this._arrayIndenter = indenter;
    }

    public void indentObjectsWith(Indenter indenter) {
        if (indenter == null) {
            indenter = new NopIndenter();
        }
        this._objectIndenter = indenter;
    }

    public void spacesInObjectEntries(boolean z) {
        this._spacesInObjectEntries = z;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.util.Instantiatable
    public DefaultXmlPrettyPrinter createInstance() {
        return new DefaultXmlPrettyPrinter(this);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw('\n');
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeStartObject(JsonGenerator jsonGenerator) throws IOException {
        if (!this._objectIndenter.isInline()) {
            int i = this._nesting;
            if (i > 0) {
                this._objectIndenter.writeIndentation(jsonGenerator, i);
            }
            this._nesting++;
        }
        this._justHadStartElement = true;
        ((ToXmlGenerator) jsonGenerator)._handleStartObject();
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException {
        if (!this._objectIndenter.isInline()) {
            this._nesting--;
        }
        if (this._justHadStartElement) {
            this._justHadStartElement = false;
        } else {
            this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
        }
        ((ToXmlGenerator) jsonGenerator)._handleEndObject();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeStartElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            if (this._justHadStartElement) {
                this._justHadStartElement = false;
            }
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
            this._nesting++;
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        this._justHadStartElement = true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeEndElement(XMLStreamWriter2 xMLStreamWriter2, int i) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._nesting--;
        }
        if (this._justHadStartElement) {
            this._justHadStartElement = false;
        } else {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeEndElement();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, String str3, boolean z) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        if (z) {
            xMLStreamWriter2.writeCData(str3);
        } else {
            xMLStreamWriter2.writeCharacters(str3);
        }
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, char[] cArr, int i, int i2, boolean z) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        if (z) {
            xMLStreamWriter2.writeCData(cArr, i, i2);
        } else {
            xMLStreamWriter2.writeCharacters(cArr, i, i2);
        }
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, boolean z) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeBoolean(z);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, int i) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeInt(i);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, long j) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeLong(j);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, double d) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeDouble(d);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, float f) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeFloat(f);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, BigInteger bigInteger) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeInteger(bigInteger);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, BigDecimal bigDecimal) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeDecimal(bigDecimal);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2, Base64Variant base64Variant, byte[] bArr, int i, int i2) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeStartElement(str, str2);
        xMLStreamWriter2.writeBinary(base64Variant, bArr, i, i2);
        xMLStreamWriter2.writeEndElement();
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writeLeafNullElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeEmptyElement(str, str2);
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void writeLeafXsiNilElement(XMLStreamWriter2 xMLStreamWriter2, String str, String str2) throws XMLStreamException {
        if (!this._objectIndenter.isInline()) {
            this._objectIndenter.writeIndentation(xMLStreamWriter2, this._nesting);
        }
        xMLStreamWriter2.writeEmptyElement(str, str2);
        xMLStreamWriter2.writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
        this._justHadStartElement = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter
    public void writePrologLinefeed(XMLStreamWriter2 xMLStreamWriter2) throws XMLStreamException {
        xMLStreamWriter2.writeRaw(Lf2SpacesIndenter.SYSTEM_LINE_SEPARATOR);
    }

    protected static class FixedSpaceIndenter implements Indenter, Serializable {
        private static final long serialVersionUID = 1;

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public boolean isInline() {
            return true;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public void writeIndentation(XMLStreamWriter2 xMLStreamWriter2, int i) throws XMLStreamException {
            xMLStreamWriter2.writeRaw(" ");
        }

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException {
            jsonGenerator.writeRaw(' ');
        }
    }

    protected static class Lf2SpacesIndenter implements Indenter, Serializable {
        static final char[] SPACES;
        static final int SPACE_COUNT = 64;
        static final String SYSTEM_LINE_SEPARATOR;
        private static final long serialVersionUID = 1;

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public boolean isInline() {
            return false;
        }

        static {
            String property;
            try {
                property = System.getProperty("line.separator");
            } catch (Throwable unused) {
                property = null;
            }
            if (property == null) {
                property = "\n";
            }
            SYSTEM_LINE_SEPARATOR = property;
            char[] cArr = new char[64];
            SPACES = cArr;
            Arrays.fill(cArr, ' ');
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public void writeIndentation(XMLStreamWriter2 xMLStreamWriter2, int i) throws XMLStreamException {
            xMLStreamWriter2.writeRaw(SYSTEM_LINE_SEPARATOR);
            int length = i + i;
            while (length > 64) {
                char[] cArr = SPACES;
                xMLStreamWriter2.writeRaw(cArr, 0, 64);
                length -= cArr.length;
            }
            xMLStreamWriter2.writeRaw(SPACES, 0, length);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException {
            jsonGenerator.writeRaw(SYSTEM_LINE_SEPARATOR);
            int length = i + i;
            while (length > 64) {
                char[] cArr = SPACES;
                jsonGenerator.writeRaw(cArr, 0, 64);
                length -= cArr.length;
            }
            jsonGenerator.writeRaw(SPACES, 0, length);
        }
    }
}
