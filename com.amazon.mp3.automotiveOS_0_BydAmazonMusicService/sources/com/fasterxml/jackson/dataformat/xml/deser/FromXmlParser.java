package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.dataformat.xml.PackageVersion;
import com.fasterxml.jackson.dataformat.xml.util.CaseInsensitiveNameSet;
import com.fasterxml.jackson.dataformat.xml.util.StaxUtil;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: classes6.dex */
public class FromXmlParser extends ParserMinimalBase {
    public static final String DEFAULT_UNNAMED_TEXT_PROPERTY = "";
    protected static final JacksonFeatureSet<StreamReadCapability> XML_READ_CAPABILITIES = JsonParser.DEFAULT_READ_CAPABILITIES.with(StreamReadCapability.DUPLICATE_PROPERTIES).with(StreamReadCapability.SCALARS_AS_OBJECTS).with(StreamReadCapability.UNTYPED_SCALARS);
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder;
    protected String _cfgNameForTextElement;
    protected boolean _closed;
    protected String _currText;
    protected int _formatFeatures;
    protected final IOContext _ioContext;
    protected boolean _mayBeLeaf;
    protected JsonToken _nextToken;
    protected int _numTypesValid;
    protected BigInteger _numberBigInt;
    protected int _numberInt;
    protected long _numberLong;
    protected ObjectCodec _objectCodec;
    protected XmlReadContext _parsingContext;
    protected final XmlTokenStream _xmlTokens;

    protected void _releaseBuffers() throws IOException {
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean canReadObjectId() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean canReadTypeId() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() throws IOException {
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() throws IOException {
        return 0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isNaN() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean requiresCustomCodec() {
        return true;
    }

    public enum Feature implements FormatFeature {
        EMPTY_ELEMENT_AS_NULL(false);

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

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public FromXmlParser(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, XMLStreamReader xMLStreamReader) throws IOException {
        super(i);
        this._cfgNameForTextElement = "";
        this._byteArrayBuilder = null;
        this._numTypesValid = 0;
        this._formatFeatures = i2;
        this._ioContext = iOContext;
        this._objectCodec = objectCodec;
        this._parsingContext = XmlReadContext.createRootContext(-1, -1);
        XmlTokenStream xmlTokenStream = new XmlTokenStream(xMLStreamReader, iOContext.getSourceReference(), this._formatFeatures);
        this._xmlTokens = xmlTokenStream;
        try {
            int iInitialize = xmlTokenStream.initialize();
            if (xmlTokenStream.hasXsiNil()) {
                this._nextToken = JsonToken.VALUE_NULL;
                return;
            }
            if (iInitialize == 1 || iInitialize == 6) {
                this._nextToken = JsonToken.START_OBJECT;
                return;
            }
            if (iInitialize == 7) {
                String text = xmlTokenStream.getText();
                this._currText = text;
                if (text == null) {
                    this._nextToken = JsonToken.VALUE_NULL;
                    return;
                } else {
                    this._nextToken = JsonToken.VALUE_STRING;
                    return;
                }
            }
            _reportError("Internal problem: invalid starting state (%s)", xmlTokenStream._currentStateDesc());
        } catch (XMLStreamException e) {
            StaxUtil.throwAsParseException(e, this);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public void setXMLTextElementName(String str) {
        this._cfgNameForTextElement = str;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return XML_READ_CAPABILITIES;
    }

    public FromXmlParser enable(Feature feature) {
        int mask = feature.getMask() | this._formatFeatures;
        this._formatFeatures = mask;
        this._xmlTokens.setFormatFeatures(mask);
        return this;
    }

    public FromXmlParser disable(Feature feature) {
        int i = (~feature.getMask()) & this._formatFeatures;
        this._formatFeatures = i;
        this._xmlTokens.setFormatFeatures(i);
        return this;
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._formatFeatures) != 0;
    }

    public FromXmlParser configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getFormatFeatures() {
        return this._formatFeatures;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser overrideFormatFeatures(int i, int i2) {
        int i3 = (i & i2) | (this._formatFeatures & (~i2));
        this._formatFeatures = i3;
        this._xmlTokens.setFormatFeatures(i3);
        return this;
    }

    public XMLStreamReader getStaxReader() {
        return this._xmlTokens.getXmlReader();
    }

    public void addVirtualWrapping(Set<String> set, boolean z) {
        String localName;
        if (z) {
            set = CaseInsensitiveNameSet.construct(set);
        }
        if (!this._parsingContext.inRoot() && !this._parsingContext.getParent().inRoot() && (localName = this._xmlTokens.getLocalName()) != null && set.contains(localName)) {
            this._xmlTokens.repeatStartElement();
        }
        this._parsingContext.setNamesToWrap(set);
    }

    @Deprecated
    public void addVirtualWrapping(Set<String> set) {
        addVirtualWrapping(set, false);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() throws IOException {
        String currentName;
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            currentName = this._parsingContext.getParent().getCurrentName();
        } else {
            currentName = this._parsingContext.getCurrentName();
        }
        if (currentName != null) {
            return currentName;
        }
        throw new IllegalStateException("Missing name, in state: " + this._currToken);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        XmlReadContext parent = this._parsingContext;
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            parent = parent.getParent();
        }
        parent.setCurrentName(str);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._closed) {
            return;
        }
        this._closed = true;
        try {
            try {
                if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                    this._xmlTokens.closeCompletely();
                } else {
                    this._xmlTokens.close();
                }
            } catch (XMLStreamException e) {
                StaxUtil.throwAsParseException(e, this);
            }
        } finally {
            _releaseBuffers();
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean isClosed() {
        return this._closed;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public XmlReadContext getParsingContext() {
        return this._parsingContext;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this._xmlTokens.getTokenLocation();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this._xmlTokens.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT) {
            return jsonToken == JsonToken.START_ARRAY;
        }
        this._currToken = JsonToken.START_ARRAY;
        this._parsingContext.convertToArray();
        if (this._nextToken == JsonToken.END_OBJECT) {
            this._nextToken = JsonToken.END_ARRAY;
        } else {
            this._nextToken = null;
        }
        this._xmlTokens.skipAttributes();
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedNumberIntToken() {
        String strTrim;
        int i_isIntNumber;
        boolean zInLongRange;
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING || (i_isIntNumber = _isIntNumber((strTrim = this._currText.trim()))) <= 0) {
            return jsonToken == JsonToken.VALUE_NUMBER_INT;
        }
        if (i_isIntNumber <= 9) {
            this._numberInt = NumberInput.parseInt(strTrim);
            this._numTypesValid = 1;
            this._currToken = JsonToken.VALUE_NUMBER_INT;
            return true;
        }
        if (i_isIntNumber <= 18) {
            long j = NumberInput.parseLong(strTrim);
            if (i_isIntNumber == 10) {
                int i = (int) j;
                if (j == i) {
                    this._numberInt = i;
                    this._numTypesValid = 1;
                    this._currToken = JsonToken.VALUE_NUMBER_INT;
                    return true;
                }
            }
            this._numberLong = j;
            this._numTypesValid = 2;
            this._currToken = JsonToken.VALUE_NUMBER_INT;
            return true;
        }
        if (i_isIntNumber == 19) {
            if (strTrim.charAt(0) == '-') {
                zInLongRange = NumberInput.inLongRange(strTrim.substring(1), true);
            } else {
                zInLongRange = NumberInput.inLongRange(strTrim, false);
            }
            if (zInLongRange) {
                this._numberLong = NumberInput.parseLong(strTrim);
                this._numTypesValid = 2;
                this._currToken = JsonToken.VALUE_NUMBER_INT;
                return true;
            }
        }
        this._numberBigInt = new BigInteger(strTrim);
        this._numTypesValid = 4;
        this._currToken = JsonToken.VALUE_NUMBER_INT;
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() throws IOException {
        this._binaryValue = null;
        this._numTypesValid = 0;
        JsonToken jsonToken = this._nextToken;
        if (jsonToken != null) {
            this._currToken = jsonToken;
            this._nextToken = null;
            int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()];
            if (i == 1) {
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            } else if (i == 2) {
                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            } else if (i == 3 || i == 4) {
                this._parsingContext = this._parsingContext.getParent();
            } else if (i == 5) {
                this._parsingContext.setCurrentName(this._xmlTokens.getLocalName());
            } else {
                this._parsingContext.valueStarted();
            }
            return jsonToken;
        }
        int i_nextToken = _nextToken();
        while (i_nextToken == 1) {
            if (this._mayBeLeaf) {
                this._nextToken = JsonToken.FIELD_NAME;
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                JsonToken jsonToken2 = JsonToken.START_OBJECT;
                this._currToken = jsonToken2;
                return jsonToken2;
            }
            if (this._parsingContext.inArray()) {
                i_nextToken = _nextToken();
                this._mayBeLeaf = true;
            } else {
                String localName = this._xmlTokens.getLocalName();
                this._parsingContext.setCurrentName(localName);
                if (this._parsingContext.shouldWrap(localName)) {
                    this._xmlTokens.repeatStartElement();
                }
                this._mayBeLeaf = true;
                JsonToken jsonToken3 = JsonToken.FIELD_NAME;
                this._currToken = jsonToken3;
                return jsonToken3;
            }
        }
        while (i_nextToken != 2) {
            if (i_nextToken == 3) {
                if (this._mayBeLeaf) {
                    this._mayBeLeaf = false;
                    this._nextToken = JsonToken.FIELD_NAME;
                    this._currText = this._xmlTokens.getText();
                    this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                    JsonToken jsonToken4 = JsonToken.START_OBJECT;
                    this._currToken = jsonToken4;
                    return jsonToken4;
                }
                this._parsingContext.setCurrentName(this._xmlTokens.getLocalName());
                JsonToken jsonToken5 = JsonToken.FIELD_NAME;
                this._currToken = jsonToken5;
                return jsonToken5;
            }
            if (i_nextToken == 4) {
                this._currText = this._xmlTokens.getText();
                this._parsingContext.valueStarted();
                JsonToken jsonToken6 = JsonToken.VALUE_STRING;
                this._currToken = jsonToken6;
                return jsonToken6;
            }
            if (i_nextToken != 5) {
                if (i_nextToken == 8) {
                    this._currToken = null;
                    return null;
                }
                return (JsonToken) _internalErrorUnknownToken(Integer.valueOf(i_nextToken));
            }
            this._currText = this._xmlTokens.getText();
            if (this._mayBeLeaf) {
                this._mayBeLeaf = false;
                int i_nextToken2 = _nextToken();
                if (i_nextToken2 == 2) {
                    if (this._parsingContext.inArray() && XmlTokenStream._allWs(this._currText)) {
                        this._nextToken = JsonToken.END_OBJECT;
                        this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                        JsonToken jsonToken7 = JsonToken.START_OBJECT;
                        this._currToken = jsonToken7;
                        return jsonToken7;
                    }
                    JsonToken jsonToken8 = JsonToken.VALUE_STRING;
                    this._currToken = jsonToken8;
                    return jsonToken8;
                }
                if (i_nextToken2 != 1) {
                    throw new JsonParseException(this, String.format("Internal error: Expected END_ELEMENT (%d) or START_ELEMENT (%d), got event of type %d", 2, 1, Integer.valueOf(i_nextToken2)));
                }
                this._xmlTokens.pushbackCurrentToken();
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            }
            if (this._parsingContext.inObject()) {
                if (this._currToken != JsonToken.FIELD_NAME && XmlTokenStream._allWs(this._currText)) {
                    i_nextToken = _nextToken();
                } else {
                    this._parsingContext.setCurrentName(this._cfgNameForTextElement);
                    this._nextToken = JsonToken.VALUE_STRING;
                    JsonToken jsonToken9 = JsonToken.FIELD_NAME;
                    this._currToken = jsonToken9;
                    return jsonToken9;
                }
            } else if (this._parsingContext.inArray() && XmlTokenStream._allWs(this._currText)) {
                i_nextToken = _nextToken();
            } else {
                this._parsingContext.setCurrentName(this._cfgNameForTextElement);
                this._nextToken = JsonToken.VALUE_STRING;
                JsonToken jsonToken92 = JsonToken.FIELD_NAME;
                this._currToken = jsonToken92;
                return jsonToken92;
            }
        }
        if (this._mayBeLeaf) {
            this._mayBeLeaf = false;
            if (this._parsingContext.inArray()) {
                this._nextToken = JsonToken.END_OBJECT;
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                JsonToken jsonToken10 = JsonToken.START_OBJECT;
                this._currToken = jsonToken10;
                return jsonToken10;
            }
            JsonToken jsonToken11 = this._currToken;
            JsonToken jsonToken12 = JsonToken.VALUE_NULL;
            if (jsonToken11 != jsonToken12) {
                this._parsingContext.valueStarted();
                this._currToken = jsonToken12;
                return jsonToken12;
            }
        }
        this._currToken = this._parsingContext.inArray() ? JsonToken.END_ARRAY : JsonToken.END_OBJECT;
        this._parsingContext = this._parsingContext.getParent();
        return this._currToken;
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = iArr;
            try {
                iArr[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() throws IOException {
        this._binaryValue = null;
        JsonToken jsonToken = this._nextToken;
        if (jsonToken != null) {
            this._currToken = jsonToken;
            this._nextToken = null;
            if (jsonToken == JsonToken.VALUE_STRING) {
                this._parsingContext.valueStarted();
                return this._currText;
            }
            _updateState(jsonToken);
            return null;
        }
        int i_nextToken = _nextToken();
        while (i_nextToken == 1) {
            if (this._mayBeLeaf) {
                this._nextToken = JsonToken.FIELD_NAME;
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                this._currToken = JsonToken.START_OBJECT;
                return null;
            }
            if (this._parsingContext.inArray()) {
                i_nextToken = _nextToken();
                this._mayBeLeaf = true;
            } else {
                String localName = this._xmlTokens.getLocalName();
                this._parsingContext.setCurrentName(localName);
                if (this._parsingContext.shouldWrap(localName)) {
                    this._xmlTokens.repeatStartElement();
                }
                this._mayBeLeaf = true;
                this._currToken = JsonToken.FIELD_NAME;
                return null;
            }
        }
        if (i_nextToken != 2) {
            if (i_nextToken != 3) {
                if (i_nextToken == 4) {
                    this._currToken = JsonToken.VALUE_STRING;
                    this._parsingContext.valueStarted();
                    String text = this._xmlTokens.getText();
                    this._currText = text;
                    return text;
                }
                if (i_nextToken == 5) {
                    this._currText = this._xmlTokens.getText();
                    if (this._mayBeLeaf) {
                        this._mayBeLeaf = false;
                        _skipEndElement();
                        this._parsingContext.valueStarted();
                        this._currToken = JsonToken.VALUE_STRING;
                        return this._currText;
                    }
                    this._parsingContext.setCurrentName(this._cfgNameForTextElement);
                    this._nextToken = JsonToken.VALUE_STRING;
                    this._currToken = JsonToken.FIELD_NAME;
                } else {
                    if (i_nextToken == 8) {
                        this._currToken = null;
                    }
                    return (String) _internalErrorUnknownToken(Integer.valueOf(i_nextToken));
                }
            } else if (this._mayBeLeaf) {
                this._mayBeLeaf = false;
                this._nextToken = JsonToken.FIELD_NAME;
                this._currText = this._xmlTokens.getText();
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                this._currToken = JsonToken.START_OBJECT;
            } else {
                this._parsingContext.setCurrentName(this._xmlTokens.getLocalName());
                this._currToken = JsonToken.FIELD_NAME;
            }
        } else {
            if (this._mayBeLeaf) {
                this._mayBeLeaf = false;
                this._currToken = JsonToken.VALUE_STRING;
                this._parsingContext.valueStarted();
                this._currText = "";
                return "";
            }
            this._currToken = this._parsingContext.inArray() ? JsonToken.END_ARRAY : JsonToken.END_OBJECT;
            this._parsingContext = this._parsingContext.getParent();
        }
        return null;
    }

    private void _updateState(JsonToken jsonToken) {
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()];
        if (i == 1) {
            this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            return;
        }
        if (i == 2) {
            this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            return;
        }
        if (i == 3 || i == 4) {
            this._parsingContext = this._parsingContext.getParent();
        } else if (i == 5) {
            this._parsingContext.setCurrentName(this._xmlTokens.getLocalName());
        } else {
            _internalErrorUnknownToken(jsonToken);
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()];
        if (i == 5) {
            return getCurrentName();
        }
        if (i == 6) {
            return this._currText;
        }
        return this._currToken.asString();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        String text = getText();
        if (text == null) {
            return null;
        }
        return text.toCharArray();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        String text = getText();
        if (text == null) {
            return 0;
        }
        return text.length();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) throws IOException {
        String text = getText();
        if (text == null) {
            return 0;
        }
        writer.write(text);
        return text.length();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING && (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._binaryValue == null) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        }
        return this._binaryValue;
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = _getByteArrayBuilder();
        _decodeBase64(getText(), byteArrayBuilder_getByteArrayBuilder, base64Variant);
        return byteArrayBuilder_getByteArrayBuilder.toByteArray();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException {
        if (this._numTypesValid == 0) {
            _checkNumericValue(0);
        }
        int i = this._numTypesValid;
        if ((i & 1) != 0) {
            return JsonParser.NumberType.INT;
        }
        if ((i & 2) != 0) {
            return JsonParser.NumberType.LONG;
        }
        return JsonParser.NumberType.BIG_INTEGER;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() throws IOException {
        if (this._numTypesValid == 0) {
            _checkNumericValue(0);
        }
        int i = this._numTypesValid;
        if ((i & 1) != 0) {
            return Integer.valueOf(this._numberInt);
        }
        if ((i & 2) != 0) {
            return Long.valueOf(this._numberLong);
        }
        if ((i & 4) != 0) {
            return this._numberBigInt;
        }
        _throwInternal();
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getIntValue() throws IOException {
        int i = this._numTypesValid;
        if ((i & 1) == 0) {
            if (i == 0) {
                _checkNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                _convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getLongValue() throws IOException {
        int i = this._numTypesValid;
        if ((i & 2) == 0) {
            if (i == 0) {
                _checkNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                _convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() throws IOException {
        int i = this._numTypesValid;
        if ((i & 4) == 0) {
            if (i == 0) {
                _checkNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                _convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() throws IOException {
        int i = this._numTypesValid;
        if ((i & 32) == 0 && i == 0) {
            _checkNumericValue(32);
        }
        return _convertNumberToFloat();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() throws IOException {
        int i = this._numTypesValid;
        if ((i & 8) == 0 && i == 0) {
            _checkNumericValue(8);
        }
        return _convertNumberToDouble();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() throws IOException {
        int i = this._numTypesValid;
        if ((i & 16) == 0 && i == 0) {
            _checkNumericValue(16);
        }
        return _convertNumberToBigDecimal();
    }

    protected final void _checkNumericValue(int i) throws IOException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            return;
        }
        _reportError("Current token (" + currentToken() + ") not numeric, can not use numeric value accessors");
    }

    protected final int _isIntNumber(String str) {
        int length = str.length();
        if (length <= 0) {
            return 0;
        }
        int i = str.charAt(0) == '-' ? 1 : 0;
        for (int i2 = i; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt > '9' || cCharAt < '0') {
                return -1;
            }
        }
        return length - i;
    }

    protected void _convertNumberToInt() throws IOException {
        int i = this._numTypesValid;
        if ((i & 2) != 0) {
            long j = this._numberLong;
            int i2 = (int) j;
            if (i2 != j) {
                _reportError("Numeric value (" + getText() + ") out of range of int");
            }
            this._numberInt = i2;
        } else if ((i & 4) != 0) {
            if (ParserMinimalBase.BI_MIN_INT.compareTo(this._numberBigInt) > 0 || ParserMinimalBase.BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    protected void _convertNumberToLong() throws IOException {
        int i = this._numTypesValid;
        if ((i & 1) != 0) {
            this._numberLong = this._numberInt;
        } else if ((i & 4) != 0) {
            if (ParserMinimalBase.BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || ParserMinimalBase.BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    protected void _convertNumberToBigInteger() throws IOException {
        int i = this._numTypesValid;
        if ((i & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((i & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    protected float _convertNumberToFloat() throws IOException {
        int i = this._numTypesValid;
        if ((i & 4) != 0) {
            return this._numberBigInt.floatValue();
        }
        if ((i & 2) != 0) {
            return this._numberLong;
        }
        if ((i & 1) != 0) {
            return this._numberInt;
        }
        _throwInternal();
        return 0.0f;
    }

    protected double _convertNumberToDouble() throws IOException {
        int i = this._numTypesValid;
        if ((i & 4) != 0) {
            return this._numberBigInt.doubleValue();
        }
        if ((i & 2) != 0) {
            return this._numberLong;
        }
        if ((i & 1) != 0) {
            return this._numberInt;
        }
        _throwInternal();
        return 0.0d;
    }

    protected BigDecimal _convertNumberToBigDecimal() throws IOException {
        int i = this._numTypesValid;
        if ((i & 4) != 0) {
            return new BigDecimal(this._numberBigInt);
        }
        if ((i & 2) != 0) {
            return BigDecimal.valueOf(this._numberLong);
        }
        if ((i & 1) != 0) {
            return BigDecimal.valueOf(this._numberInt);
        }
        _throwInternal();
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase
    protected void _handleEOF() throws JsonParseException {
        if (this._parsingContext.inRoot()) {
            return;
        }
        _reportInvalidEOF(String.format(": expected close marker for %s (start marker at %s)", this._parsingContext.inArray() ? "Array" : "Object", this._parsingContext.getStartLocation(this._ioContext.getSourceReference())), null);
    }

    protected ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder = this._byteArrayBuilder;
        if (byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    private <T> T _internalErrorUnknownToken(Object obj) {
        throw new IllegalStateException("Internal error: unrecognized XmlTokenStream token: " + obj);
    }

    protected int _nextToken() throws IOException {
        try {
            return this._xmlTokens.next();
        } catch (IllegalStateException e) {
            throw new JsonParseException(this, e.getMessage(), e);
        } catch (XMLStreamException e2) {
            return ((Integer) StaxUtil.throwAsParseException(e2, this)).intValue();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    protected void _skipEndElement() throws IOException {
        try {
            this._xmlTokens.skipEndElement();
        } catch (Exception e) {
            throw new JsonParseException(this, e.getMessage(), e);
        } catch (XMLStreamException e2) {
            StaxUtil.throwAsParseException(e2, this);
        }
    }
}
