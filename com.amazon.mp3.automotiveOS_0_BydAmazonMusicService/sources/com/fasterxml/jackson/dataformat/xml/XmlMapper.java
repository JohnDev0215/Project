package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.cfg.MutableCoercionConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.deser.XmlDeserializationContext;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.XmlSerializerProvider;
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter;
import com.fasterxml.jackson.dataformat.xml.util.XmlRootNameLookup;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes6.dex */
public class XmlMapper extends ObjectMapper {
    protected static final JacksonXmlModule DEFAULT_XML_MODULE = new JacksonXmlModule();
    protected static final DefaultXmlPrettyPrinter DEFAULT_XML_PRETTY_PRINTER = new DefaultXmlPrettyPrinter();
    private static final long serialVersionUID = 1;
    protected final JacksonXmlModule _xmlModule;

    public static class Builder extends MapperBuilder<XmlMapper, Builder> {
        public Builder(XmlMapper xmlMapper) {
            super(xmlMapper);
        }

        public Builder enable(FromXmlParser.Feature... featureArr) {
            for (FromXmlParser.Feature feature : featureArr) {
                ((XmlMapper) this._mapper).enable(feature);
            }
            return this;
        }

        public Builder disable(FromXmlParser.Feature... featureArr) {
            for (FromXmlParser.Feature feature : featureArr) {
                ((XmlMapper) this._mapper).disable(feature);
            }
            return this;
        }

        public Builder configure(FromXmlParser.Feature feature, boolean z) {
            if (z) {
                ((XmlMapper) this._mapper).enable(feature);
            } else {
                ((XmlMapper) this._mapper).disable(feature);
            }
            return this;
        }

        public Builder enable(ToXmlGenerator.Feature... featureArr) {
            for (ToXmlGenerator.Feature feature : featureArr) {
                ((XmlMapper) this._mapper).enable(feature);
            }
            return this;
        }

        public Builder disable(ToXmlGenerator.Feature... featureArr) {
            for (ToXmlGenerator.Feature feature : featureArr) {
                ((XmlMapper) this._mapper).disable(feature);
            }
            return this;
        }

        public Builder configure(ToXmlGenerator.Feature feature, boolean z) {
            if (z) {
                ((XmlMapper) this._mapper).enable(feature);
            } else {
                ((XmlMapper) this._mapper).disable(feature);
            }
            return this;
        }

        public Builder nameForTextElement(String str) {
            ((XmlMapper) this._mapper).setXMLTextElementName(str);
            return this;
        }

        public Builder defaultUseWrapper(boolean z) {
            ((XmlMapper) this._mapper).setDefaultUseWrapper(z);
            return this;
        }
    }

    public XmlMapper() {
        this(new XmlFactory());
    }

    public XmlMapper(XMLInputFactory xMLInputFactory, XMLOutputFactory xMLOutputFactory) {
        this(new XmlFactory(xMLInputFactory, xMLOutputFactory));
    }

    public XmlMapper(XMLInputFactory xMLInputFactory) {
        this(new XmlFactory(xMLInputFactory));
    }

    public XmlMapper(XmlFactory xmlFactory) {
        this(xmlFactory, DEFAULT_XML_MODULE);
    }

    public XmlMapper(JacksonXmlModule jacksonXmlModule) {
        this(new XmlFactory(), jacksonXmlModule);
    }

    public XmlMapper(XmlFactory xmlFactory, JacksonXmlModule jacksonXmlModule) {
        super(xmlFactory, new XmlSerializerProvider(new XmlRootNameLookup()), new XmlDeserializationContext(BeanDeserializerFactory.instance));
        this._xmlModule = jacksonXmlModule;
        if (jacksonXmlModule != null) {
            registerModule(jacksonXmlModule);
        }
        this._serializationConfig = this._serializationConfig.withDefaultPrettyPrinter(DEFAULT_XML_PRETTY_PRINTER);
        enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        setBase64Variant(Base64Variants.MIME);
        MutableCoercionConfig acceptBlankAsEmpty = coercionConfigDefaults().setAcceptBlankAsEmpty(Boolean.TRUE);
        CoercionInputShape coercionInputShape = CoercionInputShape.EmptyString;
        acceptBlankAsEmpty.setCoercion(coercionInputShape, CoercionAction.AsEmpty);
        MutableCoercionConfig mutableCoercionConfigCoercionConfigFor = coercionConfigFor(LogicalType.Integer);
        CoercionAction coercionAction = CoercionAction.AsNull;
        mutableCoercionConfigCoercionConfigFor.setCoercion(coercionInputShape, coercionAction);
        coercionConfigFor(LogicalType.Float).setCoercion(coercionInputShape, coercionAction);
        coercionConfigFor(LogicalType.Boolean).setCoercion(coercionInputShape, coercionAction);
    }

    protected XmlMapper(XmlMapper xmlMapper) {
        super(xmlMapper);
        this._xmlModule = xmlMapper._xmlModule;
    }

    @Override // com.fasterxml.jackson.databind.ObjectMapper
    public XmlMapper copy() {
        _checkInvalidCopy(XmlMapper.class);
        return new XmlMapper(this);
    }

    public static Builder xmlBuilder() {
        return new Builder(new XmlMapper());
    }

    public static Builder builder() {
        return new Builder(new XmlMapper());
    }

    public static Builder builder(XmlFactory xmlFactory) {
        return new Builder(new XmlMapper(xmlFactory));
    }

    @Override // com.fasterxml.jackson.databind.ObjectMapper, com.fasterxml.jackson.core.ObjectCodec, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.databind.ObjectMapper
    protected TypeResolverBuilder<?> _constructDefaultTypeResolverBuilder(ObjectMapper.DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
        return new DefaultingXmlTypeResolverBuilder(defaultTyping, polymorphicTypeValidator);
    }

    @Deprecated
    protected void setXMLTextElementName(String str) {
        ((XmlFactory) this._jsonFactory).setXMLTextElementName(str);
    }

    @Deprecated
    public XmlMapper setDefaultUseWrapper(boolean z) {
        for (Versioned versioned : getDeserializationConfig().getAnnotationIntrospector().allIntrospectors()) {
            if (versioned instanceof XmlAnnotationIntrospector) {
                ((XmlAnnotationIntrospector) versioned).setDefaultUseWrapper(z);
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.ObjectMapper, com.fasterxml.jackson.core.ObjectCodec
    public XmlFactory getFactory() {
        return (XmlFactory) this._jsonFactory;
    }

    public ObjectMapper configure(ToXmlGenerator.Feature feature, boolean z) {
        ((XmlFactory) this._jsonFactory).configure(feature, z);
        return this;
    }

    public ObjectMapper configure(FromXmlParser.Feature feature, boolean z) {
        ((XmlFactory) this._jsonFactory).configure(feature, z);
        return this;
    }

    public ObjectMapper enable(ToXmlGenerator.Feature feature) {
        ((XmlFactory) this._jsonFactory).enable(feature);
        return this;
    }

    public ObjectMapper enable(FromXmlParser.Feature feature) {
        ((XmlFactory) this._jsonFactory).enable(feature);
        return this;
    }

    public ObjectMapper disable(ToXmlGenerator.Feature feature) {
        ((XmlFactory) this._jsonFactory).disable(feature);
        return this;
    }

    public ObjectMapper disable(FromXmlParser.Feature feature) {
        ((XmlFactory) this._jsonFactory).disable(feature);
        return this;
    }

    public <T> T readValue(XMLStreamReader xMLStreamReader, Class<T> cls) throws IOException {
        return (T) readValue(xMLStreamReader, this._typeFactory.constructType(cls));
    }

    public <T> T readValue(XMLStreamReader xMLStreamReader, TypeReference<T> typeReference) throws IOException {
        return (T) readValue(xMLStreamReader, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(XMLStreamReader xMLStreamReader, JavaType javaType) throws IOException {
        return (T) super.readValue((JsonParser) getFactory().createParser(xMLStreamReader), javaType);
    }

    public void writeValue(XMLStreamWriter xMLStreamWriter, Object obj) throws IOException {
        super.writeValue(getFactory().createGenerator(xMLStreamWriter), obj);
    }
}
