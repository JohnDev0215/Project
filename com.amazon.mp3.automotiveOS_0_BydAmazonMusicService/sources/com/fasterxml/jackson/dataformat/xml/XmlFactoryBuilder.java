package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

/* JADX INFO: loaded from: classes6.dex */
public class XmlFactoryBuilder extends TSFBuilder<XmlFactory, XmlFactoryBuilder> {
    protected int _formatGeneratorFeatures;
    protected int _formatParserFeatures;
    protected String _nameForTextElement;
    protected XMLInputFactory _xmlInputFactory;
    protected XMLOutputFactory _xmlOutputFactory;

    protected XmlFactoryBuilder() {
        this._formatParserFeatures = XmlFactory.DEFAULT_XML_PARSER_FEATURE_FLAGS;
        this._formatGeneratorFeatures = XmlFactory.DEFAULT_XML_GENERATOR_FEATURE_FLAGS;
    }

    public XmlFactoryBuilder(XmlFactory xmlFactory) {
        super(xmlFactory);
        this._formatParserFeatures = xmlFactory._xmlParserFeatures;
        this._formatGeneratorFeatures = xmlFactory._xmlGeneratorFeatures;
        this._xmlInputFactory = xmlFactory._xmlInputFactory;
        this._xmlOutputFactory = xmlFactory._xmlOutputFactory;
        this._nameForTextElement = xmlFactory._cfgNameForTextElement;
    }

    public int formatParserFeaturesMask() {
        return this._formatParserFeatures;
    }

    public int formatGeneratorFeaturesMask() {
        return this._formatGeneratorFeatures;
    }

    public String nameForTextElement() {
        return this._nameForTextElement;
    }

    public XMLInputFactory xmlInputFactory() {
        XMLInputFactory xMLInputFactory = this._xmlInputFactory;
        return xMLInputFactory == null ? defaultInputFactory() : xMLInputFactory;
    }

    protected static XMLInputFactory defaultInputFactory() {
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        Boolean bool = Boolean.FALSE;
        xMLInputFactoryNewInstance.setProperty("javax.xml.stream.isSupportingExternalEntities", bool);
        xMLInputFactoryNewInstance.setProperty("javax.xml.stream.supportDTD", bool);
        return xMLInputFactoryNewInstance;
    }

    public XMLOutputFactory xmlOutputFactory() {
        XMLOutputFactory xMLOutputFactory = this._xmlOutputFactory;
        return xMLOutputFactory == null ? defaultOutputFactory() : xMLOutputFactory;
    }

    protected static XMLOutputFactory defaultOutputFactory() {
        XMLOutputFactory xMLOutputFactoryNewInstance = XMLOutputFactory.newInstance();
        xMLOutputFactoryNewInstance.setProperty("javax.xml.stream.isRepairingNamespaces", Boolean.TRUE);
        return xMLOutputFactoryNewInstance;
    }

    public XmlFactoryBuilder enable(FromXmlParser.Feature feature) {
        this._formatParserFeatures = feature.getMask() | this._formatParserFeatures;
        return _this();
    }

    public XmlFactoryBuilder enable(FromXmlParser.Feature feature, FromXmlParser.Feature... featureArr) {
        this._formatParserFeatures = feature.getMask() | this._formatParserFeatures;
        for (FromXmlParser.Feature feature2 : featureArr) {
            this._formatParserFeatures = feature2.getMask() | this._formatParserFeatures;
        }
        return _this();
    }

    public XmlFactoryBuilder disable(FromXmlParser.Feature feature) {
        this._formatParserFeatures = (~feature.getMask()) & this._formatParserFeatures;
        return _this();
    }

    public XmlFactoryBuilder disable(FromXmlParser.Feature feature, FromXmlParser.Feature... featureArr) {
        this._formatParserFeatures = (~feature.getMask()) & this._formatParserFeatures;
        for (FromXmlParser.Feature feature2 : featureArr) {
            this._formatParserFeatures = (~feature2.getMask()) & this._formatParserFeatures;
        }
        return _this();
    }

    public XmlFactoryBuilder configure(FromXmlParser.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public XmlFactoryBuilder enable(ToXmlGenerator.Feature feature) {
        this._formatGeneratorFeatures = feature.getMask() | this._formatGeneratorFeatures;
        return _this();
    }

    public XmlFactoryBuilder enable(ToXmlGenerator.Feature feature, ToXmlGenerator.Feature... featureArr) {
        this._formatGeneratorFeatures = feature.getMask() | this._formatGeneratorFeatures;
        for (ToXmlGenerator.Feature feature2 : featureArr) {
            this._formatGeneratorFeatures = feature2.getMask() | this._formatGeneratorFeatures;
        }
        return _this();
    }

    public XmlFactoryBuilder disable(ToXmlGenerator.Feature feature) {
        this._formatGeneratorFeatures = (~feature.getMask()) & this._formatGeneratorFeatures;
        return _this();
    }

    public XmlFactoryBuilder disable(ToXmlGenerator.Feature feature, ToXmlGenerator.Feature... featureArr) {
        this._formatGeneratorFeatures = (~feature.getMask()) & this._formatGeneratorFeatures;
        for (ToXmlGenerator.Feature feature2 : featureArr) {
            this._formatGeneratorFeatures = (~feature2.getMask()) & this._formatGeneratorFeatures;
        }
        return _this();
    }

    public XmlFactoryBuilder configure(ToXmlGenerator.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public XmlFactoryBuilder nameForTextElement(String str) {
        this._nameForTextElement = str;
        return _this();
    }

    public XmlFactoryBuilder inputFactory(XMLInputFactory xMLInputFactory) {
        this._xmlInputFactory = xMLInputFactory;
        return _this();
    }

    public XmlFactoryBuilder outputFactory(XMLOutputFactory xMLOutputFactory) {
        this._xmlOutputFactory = xMLOutputFactory;
        return _this();
    }

    @Override // com.fasterxml.jackson.core.TSFBuilder
    public XmlFactory build() {
        return new XmlFactory(this);
    }
}
