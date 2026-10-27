package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes6.dex */
public abstract class JodaDateSerializerBase<T> extends JodaSerializerBase<T> implements ContextualSerializer {
    protected static final int FORMAT_ARRAY = 3;
    protected static final int FORMAT_STRING = 1;
    protected static final int FORMAT_TIMESTAMP = 2;
    private static final long serialVersionUID = 1;
    protected final int _defaultNumericShape;
    protected final SerializationFeature _featureForNumeric;
    protected final JacksonJodaDateFormat _format;
    protected final int _shapeOverride;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, T t) {
        return t == null;
    }

    public abstract JodaDateSerializerBase<T> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat, int i);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public /* bridge */ /* synthetic */ void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        super.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
    }

    protected JodaDateSerializerBase(Class<T> cls, JacksonJodaDateFormat jacksonJodaDateFormat, SerializationFeature serializationFeature, int i, int i2) {
        super(cls);
        this._format = jacksonJodaDateFormat;
        this._featureForNumeric = serializationFeature;
        this._defaultNumericShape = i;
        this._shapeOverride = i2;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        Boolean bool;
        int i;
        JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType());
        if (valueFindFormatOverrides != null) {
            JsonFormat.Shape shape = valueFindFormatOverrides.getShape();
            if (shape.isNumeric()) {
                bool = Boolean.TRUE;
                i = 2;
            } else if (shape == JsonFormat.Shape.STRING) {
                bool = Boolean.FALSE;
                i = 1;
            } else if (shape == JsonFormat.Shape.ARRAY) {
                bool = Boolean.TRUE;
                i = 3;
            } else {
                bool = null;
                i = this._shapeOverride;
            }
            JacksonJodaDateFormat jacksonJodaDateFormatWithUseTimestamp = this._format;
            if (bool != null) {
                jacksonJodaDateFormatWithUseTimestamp = jacksonJodaDateFormatWithUseTimestamp.withUseTimestamp(bool);
            }
            JacksonJodaDateFormat jacksonJodaDateFormatWith = jacksonJodaDateFormatWithUseTimestamp.with(valueFindFormatOverrides);
            if (jacksonJodaDateFormatWith != this._format || i != this._shapeOverride) {
                return withFormat(jacksonJodaDateFormatWith, i);
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        int i_serializationShape = _serializationShape(serializerProvider);
        if (i_serializationShape == 2) {
            return createSchemaNode("array", true);
        }
        if (i_serializationShape == 3) {
            return createSchemaNode("number", true);
        }
        return createSchemaNode("string", true);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        int i_serializationShape = _serializationShape(jsonFormatVisitorWrapper.getProvider());
        if (i_serializationShape == 2) {
            JsonIntegerFormatVisitor jsonIntegerFormatVisitorExpectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (jsonIntegerFormatVisitorExpectIntegerFormat != null) {
                jsonIntegerFormatVisitorExpectIntegerFormat.numberType(JsonParser.NumberType.LONG);
                jsonIntegerFormatVisitorExpectIntegerFormat.format(JsonValueFormat.UTC_MILLISEC);
                return;
            }
            return;
        }
        if (i_serializationShape == 3) {
            JsonArrayFormatVisitor jsonArrayFormatVisitorExpectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
            if (jsonArrayFormatVisitorExpectArrayFormat != null) {
                jsonArrayFormatVisitorExpectArrayFormat.itemsFormat(JsonFormatTypes.INTEGER);
                return;
            }
            return;
        }
        JsonStringFormatVisitor jsonStringFormatVisitorExpectStringFormat = jsonFormatVisitorWrapper.expectStringFormat(javaType);
        if (jsonStringFormatVisitorExpectStringFormat != null) {
            jsonStringFormatVisitorExpectStringFormat.format(JsonValueFormat.DATE_TIME);
        }
    }

    protected boolean writeWithZoneId(SerializerProvider serializerProvider) {
        return this._format.shouldWriteWithZoneId(serializerProvider);
    }

    protected int _serializationShape(SerializerProvider serializerProvider) {
        int i = this._shapeOverride;
        if (i != 0) {
            return i;
        }
        if (this._format.useTimestamp(serializerProvider, this._featureForNumeric)) {
            return this._defaultNumericShape;
        }
        return 1;
    }
}
