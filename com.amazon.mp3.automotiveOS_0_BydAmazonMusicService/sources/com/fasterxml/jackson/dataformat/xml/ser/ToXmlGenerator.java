package com.fasterxml.jackson.dataformat.xml.ser;

import com.amazon.music.destination.parser.ParserUtil;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamWriteCapability;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.dataformat.xml.XmlPrettyPrinter;
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter;
import com.fasterxml.jackson.dataformat.xml.util.StaxUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.codehaus.stax2.XMLStreamWriter2;
import org.codehaus.stax2.ri.Stax2WriterAdapter;

/* JADX INFO: loaded from: classes6.dex */
public class ToXmlGenerator extends GeneratorBase {
    protected static final String DEFAULT_UNKNOWN_ELEMENT = "unknown";
    protected LinkedList<QName> _elementNameStack;
    protected int _formatFeatures;
    protected boolean _initialized;
    protected final IOContext _ioContext;
    protected boolean _nextIsAttribute;
    protected boolean _nextIsCData;
    protected boolean _nextIsUnwrapped;
    protected QName _nextName;
    protected final XMLStreamWriter _originalXmlWriter;
    protected final boolean _stax2Emulation;
    protected XmlPrettyPrinter _xmlPrettyPrinter;
    protected final XMLStreamWriter2 _xmlWriter;

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected void _releaseBuffers() {
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteFormattedNumbers() {
        return true;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getOutputBuffered() {
        return -1;
    }

    public enum Feature implements FormatFeature {
        WRITE_XML_DECLARATION(false),
        WRITE_XML_1_1(false),
        WRITE_NULLS_AS_XSI_NIL(false);

        final boolean _defaultState;
        final int _mask = 1 << ordinal();

        public static int collectDefaults() {
            int mask = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    mask |= feature.getMask();
                }
            }
            return mask;
        }

        Feature(boolean z) {
            this._defaultState = z;
        }

        @Override // com.fasterxml.jackson.core.FormatFeature, com.fasterxml.jackson.core.util.JacksonFeature
        public boolean enabledByDefault() {
            return this._defaultState;
        }

        @Override // com.fasterxml.jackson.core.FormatFeature, com.fasterxml.jackson.core.util.JacksonFeature
        public int getMask() {
            return this._mask;
        }

        @Override // com.fasterxml.jackson.core.FormatFeature, com.fasterxml.jackson.core.util.JacksonFeature
        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }
    }

    public ToXmlGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, XMLStreamWriter xMLStreamWriter) {
        super(i, objectCodec);
        this._nextName = null;
        this._nextIsAttribute = false;
        this._nextIsUnwrapped = false;
        this._nextIsCData = false;
        this._elementNameStack = new LinkedList<>();
        this._formatFeatures = i2;
        this._ioContext = iOContext;
        this._originalXmlWriter = xMLStreamWriter;
        XMLStreamWriter2 xMLStreamWriter2WrapIfNecessary = Stax2WriterAdapter.wrapIfNecessary(xMLStreamWriter);
        this._xmlWriter = xMLStreamWriter2WrapIfNecessary;
        this._stax2Emulation = xMLStreamWriter2WrapIfNecessary != xMLStreamWriter;
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        this._xmlPrettyPrinter = prettyPrinter instanceof XmlPrettyPrinter ? (XmlPrettyPrinter) prettyPrinter : null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void initGenerator() throws IOException {
        if (this._initialized) {
            return;
        }
        this._initialized = true;
        try {
            if (Feature.WRITE_XML_1_1.enabledIn(this._formatFeatures)) {
                this._xmlWriter.writeStartDocument(ParserUtil.UTF_8, "1.1");
            } else if (!Feature.WRITE_XML_DECLARATION.enabledIn(this._formatFeatures)) {
                return;
            } else {
                this._xmlWriter.writeStartDocument(ParserUtil.UTF_8, "1.0");
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter == null || this._stax2Emulation) {
                return;
            }
            xmlPrettyPrinter.writePrologLinefeed(this._xmlWriter);
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected PrettyPrinter _constructDefaultPrettyPrinter() {
        return new DefaultXmlPrettyPrinter();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._cfgPrettyPrinter = prettyPrinter;
        this._xmlPrettyPrinter = prettyPrinter instanceof XmlPrettyPrinter ? (XmlPrettyPrinter) prettyPrinter : null;
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getOutputTarget() {
        return this._originalXmlWriter;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getFormatFeatures() {
        return this._formatFeatures;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator overrideFormatFeatures(int i, int i2) {
        int i3 = this._formatFeatures;
        int i4 = (i & i2) | ((~i2) & i3);
        if (i3 != i4) {
            this._formatFeatures = i4;
        }
        return this;
    }

    public ToXmlGenerator enable(Feature feature) {
        this._formatFeatures = feature.getMask() | this._formatFeatures;
        return this;
    }

    public ToXmlGenerator disable(Feature feature) {
        this._formatFeatures = (~feature.getMask()) & this._formatFeatures;
        return this;
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._formatFeatures) != 0;
    }

    public ToXmlGenerator configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JacksonFeatureSet<StreamWriteCapability> getWriteCapabilities() {
        return JsonGenerator.DEFAULT_TEXTUAL_WRITE_CAPABILITIES;
    }

    public boolean inRoot() {
        return this._writeContext.inRoot();
    }

    public XMLStreamWriter getStaxWriter() {
        return this._xmlWriter;
    }

    public void setNextIsAttribute(boolean z) {
        this._nextIsAttribute = z;
    }

    public void setNextIsUnwrapped(boolean z) {
        this._nextIsUnwrapped = z;
    }

    public void setNextIsCData(boolean z) {
        this._nextIsCData = z;
    }

    public final void setNextName(QName qName) {
        this._nextName = qName;
    }

    public final boolean setNextNameIfMissing(QName qName) {
        if (this._nextName != null) {
            return false;
        }
        this._nextName = qName;
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void startWrappedValue(QName qName, QName qName2) throws IOException {
        if (qName != null) {
            try {
                XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
                if (xmlPrettyPrinter != null) {
                    xmlPrettyPrinter.writeStartElement(this._xmlWriter, qName.getNamespaceURI(), qName.getLocalPart());
                } else {
                    this._xmlWriter.writeStartElement(qName.getNamespaceURI(), qName.getLocalPart());
                }
            } catch (XMLStreamException e) {
                StaxUtil.throwAsGenerationException(e, this);
            }
        }
        setNextName(qName2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void finishWrappedValue(QName qName, QName qName2) throws IOException {
        if (qName != null) {
            try {
                XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
                if (xmlPrettyPrinter != null) {
                    xmlPrettyPrinter.writeEndElement(this._xmlWriter, this._writeContext.getEntryCount());
                } else {
                    this._xmlWriter.writeEndElement();
                }
            } catch (XMLStreamException e) {
                StaxUtil.throwAsGenerationException(e, this);
            }
        }
    }

    public void writeRepeatedFieldName() throws IOException {
        if (this._writeContext.writeFieldName(this._nextName.getLocalPart()) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldName(String str) throws IOException {
        if (this._writeContext.writeFieldName(str) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        QName qName = this._nextName;
        setNextName(new QName(qName == null ? "" : qName.getNamespaceURI(), str));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStringField(String str, String str2) throws IOException {
        writeFieldName(str);
        writeString(str2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        }
        this._writeContext = this._writeContext.getParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
        } else {
            _handleStartObject();
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        JsonWriteContext parent = this._writeContext.getParent();
        this._writeContext = parent;
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._nextIsAttribute ? 0 : parent.getEntryCount());
        } else {
            _handleEndObject();
        }
    }

    public final void _handleStartObject() throws IOException {
        if (this._nextName == null) {
            handleMissingName();
        }
        this._elementNameStack.addLast(this._nextName);
        try {
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    public final void _handleEndObject() throws IOException {
        XmlPrettyPrinter xmlPrettyPrinter;
        if (this._elementNameStack.isEmpty()) {
            throw new JsonGenerationException("Can not write END_ELEMENT without open START_ELEMENT", this);
        }
        this._nextName = this._elementNameStack.removeLast();
        try {
            this._nextIsAttribute = false;
            this._xmlWriter.writeEndElement();
            if (!this._elementNameStack.isEmpty() || (xmlPrettyPrinter = this._xmlPrettyPrinter) == null || this._stax2Emulation) {
                return;
            }
            xmlPrettyPrinter.writePrologLinefeed(this._xmlWriter);
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException {
        writeFieldName(serializableString.getValue());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) throws IOException {
        if (str == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write String value");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeAttribute(this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), str);
                return;
            }
            if (checkNextIsUnwrapped()) {
                if (this._nextIsCData) {
                    this._xmlWriter.writeCData(str);
                    return;
                } else {
                    this._xmlWriter.writeCharacters(str);
                    return;
                }
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), str, this._nextIsCData);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            if (this._nextIsCData) {
                this._xmlWriter.writeCData(str);
            } else {
                this._xmlWriter.writeCharacters(str);
            }
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException {
        _verifyValueWrite("write String value");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeAttribute(this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), new String(cArr, i, i2));
                return;
            }
            if (checkNextIsUnwrapped()) {
                if (this._nextIsCData) {
                    this._xmlWriter.writeCData(cArr, i, i2);
                    return;
                } else {
                    this._xmlWriter.writeCharacters(cArr, i, i2);
                    return;
                }
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), cArr, i, i2, this._nextIsCData);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            if (this._nextIsCData) {
                this._xmlWriter.writeCData(cArr, i, i2);
            } else {
                this._xmlWriter.writeCharacters(cArr, i, i2);
            }
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) throws IOException {
        writeString(serializableString.getValue());
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException {
        _reportUnsupportedOperation();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException {
        _reportUnsupportedOperation();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) throws IOException {
        if (this._stax2Emulation) {
            _reportUnimplementedStax2("writeRawValue");
        }
        try {
            _verifyValueWrite("write raw value");
            if (this._nextName == null) {
                handleMissingName();
            }
            if (this._nextIsAttribute) {
                this._xmlWriter.writeAttribute(this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), str);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeRaw(str);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException {
        if (this._stax2Emulation) {
            _reportUnimplementedStax2("writeRawValue");
        }
        try {
            _verifyValueWrite("write raw value");
            if (this._nextName == null) {
                handleMissingName();
            }
            if (this._nextIsAttribute) {
                this._xmlWriter.writeAttribute(this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), str.substring(i, i2 + i));
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeRaw(str, i, i2);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException {
        if (this._stax2Emulation) {
            _reportUnimplementedStax2("writeRawValue");
        }
        _verifyValueWrite("write raw value");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeAttribute(this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), new String(cArr, i, i2));
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeRaw(cArr, i, i2);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) throws IOException {
        _reportUnsupportedOperation();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) throws IOException {
        if (this._stax2Emulation) {
            _reportUnimplementedStax2("writeRaw");
        }
        try {
            this._xmlWriter.writeRaw(str);
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int i, int i2) throws IOException {
        if (this._stax2Emulation) {
            _reportUnimplementedStax2("writeRaw");
        }
        try {
            this._xmlWriter.writeRaw(str, i, i2);
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) throws IOException {
        if (this._stax2Emulation) {
            _reportUnimplementedStax2("writeRaw");
        }
        try {
            this._xmlWriter.writeRaw(cArr, i, i2);
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c) throws IOException {
        writeRaw(String.valueOf(c));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write Binary value");
        if (this._nextName == null) {
            handleMissingName();
        }
        org.codehaus.stax2.typed.Base64Variant stax2Base64Variant = StaxUtil.toStax2Base64Variant(base64Variant);
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeBinaryAttribute(stax2Base64Variant, "", this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), toFullBuffer(bArr, i, i2));
            } else if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeBinary(stax2Base64Variant, bArr, i, i2);
            } else {
                XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
                if (xmlPrettyPrinter != null) {
                    xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), stax2Base64Variant, bArr, i, i2);
                } else {
                    this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
                    this._xmlWriter.writeBinary(stax2Base64Variant, bArr, i, i2);
                    this._xmlWriter.writeEndElement();
                }
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            writeNull();
            return 0;
        }
        _verifyValueWrite("write Binary value");
        if (this._nextName == null) {
            handleMissingName();
        }
        org.codehaus.stax2.typed.Base64Variant stax2Base64Variant = StaxUtil.toStax2Base64Variant(base64Variant);
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeBinaryAttribute(stax2Base64Variant, "", this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), toFullBuffer(inputStream, i));
            } else if (checkNextIsUnwrapped()) {
                writeStreamAsBinary(stax2Base64Variant, inputStream, i);
            } else {
                XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
                if (xmlPrettyPrinter != null) {
                    xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), stax2Base64Variant, toFullBuffer(inputStream, i), 0, i);
                } else {
                    this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
                    writeStreamAsBinary(stax2Base64Variant, inputStream, i);
                    this._xmlWriter.writeEndElement();
                }
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
        return i;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void writeStreamAsBinary(org.codehaus.stax2.typed.Base64Variant base64Variant, InputStream inputStream, int i) throws XMLStreamException, IOException {
        byte[] bArr = new byte[3];
        int i2 = 0;
        do {
            int i3 = inputStream.read(bArr, i2, Math.min(3 - i2, i));
            if (i3 == -1) {
                break;
            }
            i2 += i3;
            i -= i3;
            if (i2 == 3) {
                this._xmlWriter.writeBinary(base64Variant, bArr, 0, 3);
                i2 = 0;
            }
        } while (i != 0);
        if (i2 > 0) {
            this._xmlWriter.writeBinary(base64Variant, bArr, 0, i2);
        }
    }

    private byte[] toFullBuffer(byte[] bArr, int i, int i2) {
        if (i == 0 && i2 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        if (i2 > 0) {
            System.arraycopy(bArr, i, bArr2, 0, i2);
        }
        return bArr2;
    }

    private byte[] toFullBuffer(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i - i2;
            int i4 = inputStream.read(bArr, i2, i3);
            if (i4 < 0) {
                _reportError("Too few bytes available: missing " + i3 + " bytes (out of " + i + ")");
            }
            i2 += i4;
        }
        return bArr;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) throws IOException {
        _verifyValueWrite("write boolean value");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeBooleanAttribute(null, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), z);
                return;
            }
            if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeBoolean(z);
                return;
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), z);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeBoolean(z);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        _verifyValueWrite("write null value");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (!this._nextIsAttribute && !checkNextIsUnwrapped()) {
                boolean zIsEnabled = isEnabled(Feature.WRITE_NULLS_AS_XSI_NIL);
                XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
                if (xmlPrettyPrinter != null) {
                    if (zIsEnabled && (xmlPrettyPrinter instanceof DefaultXmlPrettyPrinter)) {
                        ((DefaultXmlPrettyPrinter) xmlPrettyPrinter).writeLeafXsiNilElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
                        return;
                    } else {
                        xmlPrettyPrinter.writeLeafNullElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
                        return;
                    }
                }
                if (zIsEnabled) {
                    this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
                    this._xmlWriter.writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
                    this._xmlWriter.writeEndElement();
                    return;
                }
                this._xmlWriter.writeEmptyElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) throws IOException {
        _verifyValueWrite("write number");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeIntAttribute(null, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), i);
                return;
            }
            if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeInt(i);
                return;
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), i);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeInt(i);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) throws IOException {
        _verifyValueWrite("write number");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeLongAttribute(null, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), j);
                return;
            }
            if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeLong(j);
                return;
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), j);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeLong(j);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d) throws IOException {
        _verifyValueWrite("write number");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeDoubleAttribute(null, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), d);
                return;
            }
            if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeDouble(d);
                return;
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), d);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeDouble(d);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f) throws IOException {
        _verifyValueWrite("write number");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeFloatAttribute(null, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), f);
                return;
            }
            if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeFloat(f);
                return;
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), f);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeFloat(f);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        if (this._nextName == null) {
            handleMissingName();
        }
        boolean zIsEnabled = isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        try {
            if (this._nextIsAttribute) {
                if (zIsEnabled) {
                    this._xmlWriter.writeAttribute("", this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), bigDecimal.toPlainString());
                    return;
                } else {
                    this._xmlWriter.writeDecimalAttribute("", this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), bigDecimal);
                    return;
                }
            }
            if (checkNextIsUnwrapped()) {
                if (zIsEnabled) {
                    this._xmlWriter.writeCharacters(bigDecimal.toPlainString());
                    return;
                } else {
                    this._xmlWriter.writeDecimal(bigDecimal);
                    return;
                }
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                if (zIsEnabled) {
                    xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), bigDecimal.toPlainString(), false);
                    return;
                } else {
                    xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), bigDecimal);
                    return;
                }
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            if (zIsEnabled) {
                this._xmlWriter.writeCharacters(bigDecimal.toPlainString());
            } else {
                this._xmlWriter.writeDecimal(bigDecimal);
            }
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException {
        if (bigInteger == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        if (this._nextName == null) {
            handleMissingName();
        }
        try {
            if (this._nextIsAttribute) {
                this._xmlWriter.writeIntegerAttribute("", this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), bigInteger);
                return;
            }
            if (checkNextIsUnwrapped()) {
                this._xmlWriter.writeInteger(bigInteger);
                return;
            }
            XmlPrettyPrinter xmlPrettyPrinter = this._xmlPrettyPrinter;
            if (xmlPrettyPrinter != null) {
                xmlPrettyPrinter.writeLeafElement(this._xmlWriter, this._nextName.getNamespaceURI(), this._nextName.getLocalPart(), bigInteger);
                return;
            }
            this._xmlWriter.writeStartElement(this._nextName.getNamespaceURI(), this._nextName.getLocalPart());
            this._xmlWriter.writeInteger(bigInteger);
            this._xmlWriter.writeEndElement();
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) throws UnsupportedOperationException, IOException {
        writeString(str);
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void _verifyValueWrite(String str) throws IOException {
        if (this._writeContext.writeValue() == 5) {
            _reportError("Can not " + str + ", expecting field name");
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            try {
                this._xmlWriter.flush();
            } catch (XMLStreamException e) {
                StaxUtil.throwAsGenerationException(e, this);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        try {
            if (isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
                while (true) {
                    try {
                        JsonWriteContext jsonWriteContext = this._writeContext;
                        if (jsonWriteContext.inArray()) {
                            writeEndArray();
                        } else if (jsonWriteContext.inObject()) {
                            writeEndObject();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new JsonGenerationException(e, this);
                    }
                }
                if (!this._ioContext.isResourceManaged() && !isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                    this._xmlWriter.close();
                    return;
                }
                this._xmlWriter.closeCompletely();
            }
            if (!this._ioContext.isResourceManaged()) {
                this._xmlWriter.close();
                return;
            }
            this._xmlWriter.closeCompletely();
        } catch (XMLStreamException e2) {
            StaxUtil.throwAsGenerationException(e2, this);
        }
    }

    protected boolean checkNextIsUnwrapped() {
        if (!this._nextIsUnwrapped) {
            return false;
        }
        this._nextIsUnwrapped = false;
        return true;
    }

    protected void handleMissingName() {
        throw new IllegalStateException("No element/attribute name specified when trying to output element");
    }

    protected void _reportUnimplementedStax2(String str) throws IOException {
        throw new JsonGenerationException("Underlying Stax XMLStreamWriter (of type " + this._originalXmlWriter.getClass().getName() + ") does not implement Stax2 API natively and is missing method '" + str + "': this breaks functionality such as indentation that relies on it. You need to upgrade to using compliant Stax implementation like Woodstox or Aalto", this);
    }
}
