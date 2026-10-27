package com.fasterxml.jackson.dataformat.xml.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.dataformat.xml.util.StaxUtil;
import com.fasterxml.jackson.dataformat.xml.util.TypeUtil;
import com.fasterxml.jackson.dataformat.xml.util.XmlRootNameLookup;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: classes6.dex */
public class XmlSerializerProvider extends DefaultSerializerProvider {
    private static final long serialVersionUID = 1;
    protected final XmlRootNameLookup _rootNameLookup;

    public XmlSerializerProvider(XmlRootNameLookup xmlRootNameLookup) {
        this._rootNameLookup = xmlRootNameLookup;
    }

    public XmlSerializerProvider(XmlSerializerProvider xmlSerializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        super(xmlSerializerProvider, serializationConfig, serializerFactory);
        this._rootNameLookup = xmlSerializerProvider._rootNameLookup;
    }

    protected XmlSerializerProvider(XmlSerializerProvider xmlSerializerProvider) {
        super(xmlSerializerProvider);
        this._rootNameLookup = new XmlRootNameLookup();
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public DefaultSerializerProvider copy() {
        return new XmlSerializerProvider(this);
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public DefaultSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new XmlSerializerProvider(this, serializationConfig, serializerFactory);
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public void serializeValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        boolean z;
        this._generator = jsonGenerator;
        if (obj == null) {
            _serializeXmlNull(jsonGenerator);
            return;
        }
        Class<?> cls = obj.getClass();
        ToXmlGenerator toXmlGenerator_asXmlGenerator = _asXmlGenerator(jsonGenerator);
        if (toXmlGenerator_asXmlGenerator == null) {
            z = false;
        } else {
            QName qName_rootNameFromConfig = _rootNameFromConfig();
            if (qName_rootNameFromConfig == null) {
                qName_rootNameFromConfig = this._rootNameLookup.findRootName(cls, this._config);
            }
            _initWithRootName(toXmlGenerator_asXmlGenerator, qName_rootNameFromConfig);
            boolean zIsIndexedType = TypeUtil.isIndexedType(cls);
            if (zIsIndexedType) {
                _startRootArray(toXmlGenerator_asXmlGenerator, qName_rootNameFromConfig);
            }
            z = zIsIndexedType;
        }
        try {
            findTypedValueSerializer(cls, true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (Exception e) {
            throw _wrapAsIOE(jsonGenerator, e);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public void serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) throws IOException {
        serializeValue(jsonGenerator, obj, javaType, null);
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public void serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType, JsonSerializer<Object> jsonSerializer) throws IOException {
        boolean z;
        this._generator = jsonGenerator;
        if (obj == null) {
            _serializeXmlNull(jsonGenerator);
            return;
        }
        if (javaType != null && !javaType.getRawClass().isAssignableFrom(obj.getClass())) {
            _reportIncompatibleRootType(obj, javaType);
        }
        ToXmlGenerator toXmlGenerator_asXmlGenerator = _asXmlGenerator(jsonGenerator);
        if (toXmlGenerator_asXmlGenerator == null) {
            z = false;
        } else {
            QName qName_rootNameFromConfig = _rootNameFromConfig();
            if (qName_rootNameFromConfig == null) {
                qName_rootNameFromConfig = this._rootNameLookup.findRootName(javaType, this._config);
            }
            _initWithRootName(toXmlGenerator_asXmlGenerator, qName_rootNameFromConfig);
            boolean zIsIndexedType = TypeUtil.isIndexedType(javaType);
            if (zIsIndexedType) {
                _startRootArray(toXmlGenerator_asXmlGenerator, qName_rootNameFromConfig);
            }
            z = zIsIndexedType;
        }
        if (jsonSerializer == null) {
            jsonSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
        }
        try {
            jsonSerializer.serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (Exception e) {
            throw _wrapAsIOE(jsonGenerator, e);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public void serializePolymorphic(JsonGenerator jsonGenerator, Object obj, JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer) throws IOException {
        boolean z;
        this._generator = jsonGenerator;
        if (obj == null) {
            _serializeXmlNull(jsonGenerator);
            return;
        }
        if (javaType != null && !javaType.getRawClass().isAssignableFrom(obj.getClass())) {
            _reportIncompatibleRootType(obj, javaType);
        }
        ToXmlGenerator toXmlGenerator_asXmlGenerator = _asXmlGenerator(jsonGenerator);
        if (toXmlGenerator_asXmlGenerator == null) {
            z = false;
        } else {
            QName qName_rootNameFromConfig = _rootNameFromConfig();
            if (qName_rootNameFromConfig == null) {
                qName_rootNameFromConfig = this._rootNameLookup.findRootName(javaType, this._config);
            }
            _initWithRootName(toXmlGenerator_asXmlGenerator, qName_rootNameFromConfig);
            boolean zIsIndexedType = TypeUtil.isIndexedType(javaType);
            if (zIsIndexedType) {
                _startRootArray(toXmlGenerator_asXmlGenerator, qName_rootNameFromConfig);
            }
            z = zIsIndexedType;
        }
        if (jsonSerializer == null) {
            if (javaType != null && javaType.isContainerType()) {
                jsonSerializer = findValueSerializer(javaType, (BeanProperty) null);
            } else {
                jsonSerializer = findValueSerializer(obj.getClass(), (BeanProperty) null);
            }
        }
        try {
            jsonSerializer.serializeWithType(obj, jsonGenerator, this, typeSerializer);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (Exception e) {
            throw _wrapAsIOE(jsonGenerator, e);
        }
    }

    protected void _serializeXmlNull(JsonGenerator jsonGenerator) throws IOException {
        QName qName_rootNameFromConfig = _rootNameFromConfig();
        if (qName_rootNameFromConfig == null) {
            qName_rootNameFromConfig = XmlRootNameLookup.ROOT_NAME_FOR_NULL;
        }
        if (jsonGenerator instanceof ToXmlGenerator) {
            _initWithRootName((ToXmlGenerator) jsonGenerator, qName_rootNameFromConfig);
        }
        super.serializeValue(jsonGenerator, null);
    }

    protected void _startRootArray(ToXmlGenerator toXmlGenerator, QName qName) throws IOException {
        toXmlGenerator.writeStartObject();
        toXmlGenerator.writeFieldName("item");
    }

    protected void _initWithRootName(ToXmlGenerator toXmlGenerator, QName qName) throws IOException {
        if (!toXmlGenerator.setNextNameIfMissing(qName) && toXmlGenerator.inRoot()) {
            toXmlGenerator.setNextName(qName);
        }
        toXmlGenerator.initGenerator();
        String namespaceURI = qName.getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return;
        }
        try {
            toXmlGenerator.getStaxWriter().setDefaultNamespace(namespaceURI);
        } catch (XMLStreamException e) {
            StaxUtil.throwAsGenerationException(e, toXmlGenerator);
        }
    }

    protected QName _rootNameFromConfig() {
        PropertyName fullRootName = this._config.getFullRootName();
        if (fullRootName == null) {
            return null;
        }
        String namespace = fullRootName.getNamespace();
        if (namespace == null || namespace.isEmpty()) {
            return new QName(fullRootName.getSimpleName());
        }
        return new QName(namespace, fullRootName.getSimpleName());
    }

    protected ToXmlGenerator _asXmlGenerator(JsonGenerator jsonGenerator) throws JsonMappingException {
        if (!(jsonGenerator instanceof ToXmlGenerator)) {
            if (jsonGenerator instanceof TokenBuffer) {
                return null;
            }
            throw JsonMappingException.from(jsonGenerator, "XmlMapper does not work with generators of type other than `ToXmlGenerator`; got: `" + jsonGenerator.getClass().getName() + "`");
        }
        return (ToXmlGenerator) jsonGenerator;
    }

    protected IOException _wrapAsIOE(JsonGenerator jsonGenerator, Exception exc) {
        if (exc instanceof IOException) {
            return (IOException) exc;
        }
        String message = exc.getMessage();
        if (message == null) {
            message = "[no message for " + exc.getClass().getName() + "]";
        }
        return new JsonMappingException(jsonGenerator, message, exc);
    }
}
