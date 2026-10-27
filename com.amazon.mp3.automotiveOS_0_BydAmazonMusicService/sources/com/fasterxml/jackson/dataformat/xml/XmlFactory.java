package com.fasterxml.jackson.dataformat.xml;

import com.amazon.music.destination.parser.ParserUtil;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.util.StaxUtil;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.io.Stax2ByteArraySource;
import org.codehaus.stax2.io.Stax2CharArraySource;

/* JADX INFO: loaded from: classes6.dex */
public class XmlFactory extends JsonFactory {
    private static final byte BYTE_D = 68;
    private static final byte BYTE_EXCL = 33;
    private static final byte BYTE_HYPHEN = 45;
    private static final byte BYTE_LT = 60;
    private static final byte BYTE_QMARK = 63;
    private static final byte BYTE_l = 108;
    private static final byte BYTE_m = 109;
    private static final byte BYTE_x = 120;
    public static final String FORMAT_NAME_XML = "XML";
    private static final byte UTF8_BOM_1 = -17;
    private static final byte UTF8_BOM_2 = -69;
    private static final byte UTF8_BOM_3 = -65;
    private static final long serialVersionUID = 1;
    protected String _cfgNameForTextElement;
    protected transient String _jdkXmlInFactory;
    protected transient String _jdkXmlOutFactory;
    protected int _xmlGeneratorFeatures;
    protected transient XMLInputFactory _xmlInputFactory;
    protected transient XMLOutputFactory _xmlOutputFactory;
    protected int _xmlParserFeatures;
    static final int DEFAULT_XML_PARSER_FEATURE_FLAGS = FromXmlParser.Feature.collectDefaults();
    static final int DEFAULT_XML_GENERATOR_FEATURE_FLAGS = ToXmlGenerator.Feature.collectDefaults();

    private static final boolean validXmlNameStartChar(InputAccessor inputAccessor, byte b) throws IOException {
        return (b & 255) >= 65;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public boolean canUseCharArrays() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public String getFormatName() {
        return FORMAT_NAME_XML;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public boolean requiresCustomCodec() {
        return true;
    }

    public XmlFactory() {
        this(null, null, null);
    }

    public XmlFactory(ObjectCodec objectCodec) {
        this(objectCodec, null, null);
    }

    public XmlFactory(XMLInputFactory xMLInputFactory) {
        this(null, xMLInputFactory, null);
    }

    public XmlFactory(XMLInputFactory xMLInputFactory, XMLOutputFactory xMLOutputFactory) {
        this(null, xMLInputFactory, xMLOutputFactory);
    }

    public XmlFactory(ObjectCodec objectCodec, XMLInputFactory xMLInputFactory, XMLOutputFactory xMLOutputFactory) {
        this(objectCodec, DEFAULT_XML_PARSER_FEATURE_FLAGS, DEFAULT_XML_GENERATOR_FEATURE_FLAGS, xMLInputFactory, xMLOutputFactory, null);
    }

    protected XmlFactory(ObjectCodec objectCodec, int i, int i2, XMLInputFactory xMLInputFactory, XMLOutputFactory xMLOutputFactory, String str) {
        super(objectCodec);
        this._xmlParserFeatures = i;
        this._xmlGeneratorFeatures = i2;
        this._cfgNameForTextElement = str;
        if (xMLInputFactory == null) {
            xMLInputFactory = XMLInputFactory.newInstance();
            Boolean bool = Boolean.FALSE;
            xMLInputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", bool);
            xMLInputFactory.setProperty("javax.xml.stream.supportDTD", bool);
        }
        xMLOutputFactory = xMLOutputFactory == null ? XMLOutputFactory.newInstance() : xMLOutputFactory;
        _initFactories(xMLInputFactory, xMLOutputFactory);
        this._xmlInputFactory = xMLInputFactory;
        this._xmlOutputFactory = xMLOutputFactory;
    }

    protected XmlFactory(XmlFactory xmlFactory, ObjectCodec objectCodec) {
        super(xmlFactory, objectCodec);
        this._xmlParserFeatures = xmlFactory._xmlParserFeatures;
        this._xmlGeneratorFeatures = xmlFactory._xmlGeneratorFeatures;
        this._cfgNameForTextElement = xmlFactory._cfgNameForTextElement;
        this._xmlInputFactory = xmlFactory._xmlInputFactory;
        this._xmlOutputFactory = xmlFactory._xmlOutputFactory;
    }

    protected XmlFactory(XmlFactoryBuilder xmlFactoryBuilder) {
        super((TSFBuilder<?, ?>) xmlFactoryBuilder, false);
        this._xmlParserFeatures = xmlFactoryBuilder.formatParserFeaturesMask();
        this._xmlGeneratorFeatures = xmlFactoryBuilder.formatGeneratorFeaturesMask();
        this._cfgNameForTextElement = xmlFactoryBuilder.nameForTextElement();
        this._xmlInputFactory = xmlFactoryBuilder.xmlInputFactory();
        XMLOutputFactory xmlOutputFactory = xmlFactoryBuilder.xmlOutputFactory();
        this._xmlOutputFactory = xmlOutputFactory;
        _initFactories(this._xmlInputFactory, xmlOutputFactory);
    }

    public static XmlFactoryBuilder builder() {
        return new XmlFactoryBuilder();
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public XmlFactoryBuilder rebuild() {
        return new XmlFactoryBuilder(this);
    }

    protected void _initFactories(XMLInputFactory xMLInputFactory, XMLOutputFactory xMLOutputFactory) {
        Boolean bool = Boolean.TRUE;
        xMLOutputFactory.setProperty("javax.xml.stream.isRepairingNamespaces", bool);
        xMLInputFactory.setProperty("javax.xml.stream.isCoalescing", bool);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public XmlFactory copy() {
        _checkInvalidCopy(XmlFactory.class);
        return new XmlFactory(this, (ObjectCodec) null);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    protected Object readResolve() {
        String str = this._jdkXmlInFactory;
        if (str == null) {
            throw new IllegalStateException("No XMLInputFactory class name read during JDK deserialization");
        }
        if (this._jdkXmlOutFactory == null) {
            throw new IllegalStateException("No XMLOutputFactory class name read during JDK deserialization");
        }
        try {
            return new XmlFactory(this._objectCodec, this._xmlParserFeatures, this._xmlGeneratorFeatures, (XMLInputFactory) Class.forName(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), (XMLOutputFactory) Class.forName(this._jdkXmlOutFactory).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), this._cfgNameForTextElement);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this._jdkXmlInFactory = objectInputStream.readUTF();
        this._jdkXmlOutFactory = objectInputStream.readUTF();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this._xmlInputFactory.getClass().getName());
        objectOutputStream.writeUTF(this._xmlOutputFactory.getClass().getName());
    }

    public void setXMLTextElementName(String str) {
        this._cfgNameForTextElement = str;
    }

    public String getXMLTextElementName() {
        return this._cfgNameForTextElement;
    }

    public final XmlFactory configure(FromXmlParser.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public XmlFactory enable(FromXmlParser.Feature feature) {
        this._xmlParserFeatures = feature.getMask() | this._xmlParserFeatures;
        return this;
    }

    public XmlFactory disable(FromXmlParser.Feature feature) {
        this._xmlParserFeatures = (~feature.getMask()) & this._xmlParserFeatures;
        return this;
    }

    public final boolean isEnabled(FromXmlParser.Feature feature) {
        return (feature.getMask() & this._xmlParserFeatures) != 0;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public int getFormatParserFeatures() {
        return this._xmlParserFeatures;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public int getFormatGeneratorFeatures() {
        return this._xmlGeneratorFeatures;
    }

    public final XmlFactory configure(ToXmlGenerator.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public XmlFactory enable(ToXmlGenerator.Feature feature) {
        this._xmlGeneratorFeatures = feature.getMask() | this._xmlGeneratorFeatures;
        return this;
    }

    public XmlFactory disable(ToXmlGenerator.Feature feature) {
        this._xmlGeneratorFeatures = (~feature.getMask()) & this._xmlGeneratorFeatures;
        return this;
    }

    public final boolean isEnabled(ToXmlGenerator.Feature feature) {
        return (feature.getMask() & this._xmlGeneratorFeatures) != 0;
    }

    public XMLInputFactory getXMLInputFactory() {
        return this._xmlInputFactory;
    }

    @Deprecated
    public void setXMLInputFactory(XMLInputFactory xMLInputFactory) {
        this._xmlInputFactory = xMLInputFactory;
    }

    public XMLOutputFactory getXMLOutputFactory() {
        return this._xmlOutputFactory;
    }

    @Deprecated
    public void setXMLOutputFactory(XMLOutputFactory xMLOutputFactory) {
        this._xmlOutputFactory = xMLOutputFactory;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        return hasXMLFormat(inputAccessor);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public Class<FromXmlParser.Feature> getFormatReadFeatureType() {
        return FromXmlParser.Feature.class;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public Class<ToXmlGenerator.Feature> getFormatWriteFeatureType() {
        return ToXmlGenerator.Feature.class;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(String str) throws IOException {
        Reader stringReader = new StringReader(str);
        IOContext iOContext_createContext = _createContext(stringReader, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            stringReader = inputDecorator.decorate(iOContext_createContext, stringReader);
        }
        return _createParser(stringReader, iOContext_createContext);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public ToXmlGenerator createGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public ToXmlGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        IOContext iOContext_createContext = _createContext(outputStream, false);
        iOContext_createContext.setEncoding(jsonEncoding);
        return new ToXmlGenerator(iOContext_createContext, this._generatorFeatures, this._xmlGeneratorFeatures, this._objectCodec, _createXmlWriter(iOContext_createContext, outputStream));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public ToXmlGenerator createGenerator(Writer writer) throws IOException {
        IOContext iOContext_createContext = _createContext(writer, false);
        return new ToXmlGenerator(iOContext_createContext, this._generatorFeatures, this._xmlGeneratorFeatures, this._objectCodec, _createXmlWriter(iOContext_createContext, writer));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public ToXmlGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(file);
        IOContext iOContext_createContext = _createContext(fileOutputStream, true);
        iOContext_createContext.setEncoding(jsonEncoding);
        return new ToXmlGenerator(iOContext_createContext, this._generatorFeatures, this._xmlGeneratorFeatures, this._objectCodec, _createXmlWriter(iOContext_createContext, fileOutputStream));
    }

    public FromXmlParser createParser(XMLStreamReader xMLStreamReader) throws IOException {
        if (xMLStreamReader.getEventType() != 1) {
            xMLStreamReader = _initializeXmlReader(xMLStreamReader);
        }
        XMLStreamReader xMLStreamReader2 = xMLStreamReader;
        FromXmlParser fromXmlParser = new FromXmlParser(_createContext(xMLStreamReader2, false), this._parserFeatures, this._xmlParserFeatures, this._objectCodec, xMLStreamReader2);
        String str = this._cfgNameForTextElement;
        if (str != null) {
            fromXmlParser.setXMLTextElementName(str);
        }
        return fromXmlParser;
    }

    public ToXmlGenerator createGenerator(XMLStreamWriter xMLStreamWriter) throws IOException {
        XMLStreamWriter xMLStreamWriter_initializeXmlWriter = _initializeXmlWriter(xMLStreamWriter);
        return new ToXmlGenerator(_createContext(xMLStreamWriter_initializeXmlWriter, false), this._generatorFeatures, this._xmlGeneratorFeatures, this._objectCodec, xMLStreamWriter_initializeXmlWriter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public FromXmlParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException {
        try {
            FromXmlParser fromXmlParser = new FromXmlParser(iOContext, this._parserFeatures, this._xmlParserFeatures, this._objectCodec, _initializeXmlReader(this._xmlInputFactory.createXMLStreamReader(inputStream)));
            String str = this._cfgNameForTextElement;
            if (str != null) {
                fromXmlParser.setXMLTextElementName(str);
            }
            return fromXmlParser;
        } catch (XMLStreamException e) {
            return (FromXmlParser) StaxUtil.throwAsParseException(e, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public FromXmlParser _createParser(Reader reader, IOContext iOContext) throws IOException {
        try {
            FromXmlParser fromXmlParser = new FromXmlParser(iOContext, this._parserFeatures, this._xmlParserFeatures, this._objectCodec, _initializeXmlReader(this._xmlInputFactory.createXMLStreamReader(reader)));
            String str = this._cfgNameForTextElement;
            if (str != null) {
                fromXmlParser.setXMLTextElementName(str);
            }
            return fromXmlParser;
        } catch (XMLStreamException e) {
            return (FromXmlParser) StaxUtil.throwAsParseException(e, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public FromXmlParser _createParser(char[] cArr, int i, int i2, IOContext iOContext, boolean z) throws IOException {
        XMLStreamReader xMLStreamReaderCreateXMLStreamReader;
        try {
            XMLInputFactory xMLInputFactory = this._xmlInputFactory;
            if (xMLInputFactory instanceof XMLInputFactory2) {
                xMLStreamReaderCreateXMLStreamReader = xMLInputFactory.createXMLStreamReader(new Stax2CharArraySource(cArr, i, i2));
            } else {
                xMLStreamReaderCreateXMLStreamReader = xMLInputFactory.createXMLStreamReader(new CharArrayReader(cArr, i, i2));
            }
            FromXmlParser fromXmlParser = new FromXmlParser(iOContext, this._parserFeatures, this._xmlParserFeatures, this._objectCodec, _initializeXmlReader(xMLStreamReaderCreateXMLStreamReader));
            String str = this._cfgNameForTextElement;
            if (str != null) {
                fromXmlParser.setXMLTextElementName(str);
            }
            return fromXmlParser;
        } catch (XMLStreamException e) {
            return (FromXmlParser) StaxUtil.throwAsParseException(e, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public FromXmlParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException {
        XMLStreamReader xMLStreamReaderCreateXMLStreamReader;
        try {
            XMLInputFactory xMLInputFactory = this._xmlInputFactory;
            if (xMLInputFactory instanceof XMLInputFactory2) {
                xMLStreamReaderCreateXMLStreamReader = xMLInputFactory.createXMLStreamReader(new Stax2ByteArraySource(bArr, i, i2));
            } else {
                xMLStreamReaderCreateXMLStreamReader = xMLInputFactory.createXMLStreamReader(new ByteArrayInputStream(bArr, i, i2));
            }
            FromXmlParser fromXmlParser = new FromXmlParser(iOContext, this._parserFeatures, this._xmlParserFeatures, this._objectCodec, _initializeXmlReader(xMLStreamReaderCreateXMLStreamReader));
            String str = this._cfgNameForTextElement;
            if (str != null) {
                fromXmlParser.setXMLTextElementName(str);
            }
            return fromXmlParser;
        } catch (XMLStreamException e) {
            return (FromXmlParser) StaxUtil.throwAsParseException(e, null);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    protected JsonGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        VersionUtil.throwInternal();
        return null;
    }

    protected XMLStreamWriter _createXmlWriter(IOContext iOContext, OutputStream outputStream) throws IOException {
        try {
            return _initializeXmlWriter(this._xmlOutputFactory.createXMLStreamWriter(_decorate(iOContext, outputStream), ParserUtil.UTF_8));
        } catch (Exception e) {
            throw new JsonGenerationException(e.getMessage(), e, null);
        }
    }

    protected XMLStreamWriter _createXmlWriter(IOContext iOContext, Writer writer) throws IOException {
        try {
            return _initializeXmlWriter(this._xmlOutputFactory.createXMLStreamWriter(_decorate(iOContext, writer)));
        } catch (Exception e) {
            throw new JsonGenerationException(e.getMessage(), e, null);
        }
    }

    protected final XMLStreamWriter _initializeXmlWriter(XMLStreamWriter xMLStreamWriter) throws IOException {
        try {
            xMLStreamWriter.setDefaultNamespace("");
            return xMLStreamWriter;
        } catch (Exception e) {
            throw new JsonGenerationException(e.getMessage(), e, null);
        }
    }

    protected final XMLStreamReader _initializeXmlReader(XMLStreamReader xMLStreamReader) throws IOException {
        do {
            try {
            } catch (Exception e) {
                throw new JsonParseException((JsonParser) null, e.getMessage(), e);
            }
        } while (xMLStreamReader.next() != 1);
        return xMLStreamReader;
    }

    public static MatchStrength hasXMLFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte bNextByte = inputAccessor.nextByte();
        if (bNextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            bNextByte = inputAccessor.nextByte();
        }
        boolean z = bNextByte == 60;
        if (!z) {
            int iSkipSpace = skipSpace(inputAccessor, bNextByte);
            if (iSkipSpace < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (((byte) iSkipSpace) != 60) {
                return MatchStrength.NO_MATCH;
            }
        }
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte bNextByte2 = inputAccessor.nextByte();
        if (bNextByte2 == 63) {
            byte bNextByte3 = inputAccessor.nextByte();
            if (bNextByte3 == 120) {
                if (z && inputAccessor.hasMoreBytes() && inputAccessor.nextByte() == 109 && inputAccessor.hasMoreBytes() && inputAccessor.nextByte() == 108) {
                    return MatchStrength.FULL_MATCH;
                }
                return MatchStrength.SOLID_MATCH;
            }
            if (validXmlNameStartChar(inputAccessor, bNextByte3)) {
                return MatchStrength.SOLID_MATCH;
            }
        } else if (bNextByte2 == 33) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            byte bNextByte4 = inputAccessor.nextByte();
            if (bNextByte4 == 45) {
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputAccessor.nextByte() == 45) {
                    return MatchStrength.SOLID_MATCH;
                }
            } else if (bNextByte4 == 68) {
                return tryMatch(inputAccessor, "OCTYPE", MatchStrength.SOLID_MATCH);
            }
        } else if (validXmlNameStartChar(inputAccessor, bNextByte2)) {
            return MatchStrength.SOLID_MATCH;
        }
        return MatchStrength.NO_MATCH;
    }

    private static final MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    private static final int skipSpace(InputAccessor inputAccessor, byte b) throws IOException {
        while (true) {
            int i = b & 255;
            if (i != 32 && i != 13 && i != 10 && i != 9) {
                return i;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b = inputAccessor.nextByte();
        }
    }

    protected OutputStream _decorate(IOContext iOContext, OutputStream outputStream) throws IOException {
        OutputStream outputStreamDecorate;
        OutputDecorator outputDecorator = this._outputDecorator;
        return (outputDecorator == null || (outputStreamDecorate = outputDecorator.decorate(iOContext, outputStream)) == null) ? outputStream : outputStreamDecorate;
    }

    protected Writer _decorate(IOContext iOContext, Writer writer) throws IOException {
        Writer writerDecorate;
        OutputDecorator outputDecorator = this._outputDecorator;
        return (outputDecorator == null || (writerDecorate = outputDecorator.decorate(iOContext, writer)) == null) ? writer : writerDecorate;
    }
}
