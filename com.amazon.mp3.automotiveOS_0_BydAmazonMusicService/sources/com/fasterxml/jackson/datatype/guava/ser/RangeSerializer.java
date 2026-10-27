package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.guava.deser.util.RangeHelper;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class RangeSerializer extends StdSerializer<Range<?>> implements ContextualSerializer {
    protected final JsonSerializer<Object> _endpointSerializer;
    protected final RangeHelper.RangeProperties _fieldNames;
    protected final JavaType _rangeType;

    public RangeSerializer(JavaType javaType) {
        this(javaType, null);
    }

    @Deprecated
    public RangeSerializer(JavaType javaType, JsonSerializer<?> jsonSerializer) {
        this(javaType, jsonSerializer, RangeHelper.standardNames());
    }

    public RangeSerializer(JavaType javaType, JsonSerializer<?> jsonSerializer, RangeHelper.RangeProperties rangeProperties) {
        super(javaType);
        this._rangeType = javaType;
        this._endpointSerializer = jsonSerializer;
        this._fieldNames = rangeProperties;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Range<?> range) {
        return range.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        ?? CreateContextual;
        RangeHelper.RangeProperties propertyNames = RangeHelper.getPropertyNames(serializerProvider.getConfig(), serializerProvider.getConfig().getPropertyNamingStrategy());
        JsonFormatVisitable jsonFormatVisitable = this._endpointSerializer;
        if (jsonFormatVisitable == null) {
            JavaType javaTypeContainedTypeOrUnknown = this._rangeType.containedTypeOrUnknown(0);
            CreateContextual = jsonFormatVisitable;
            if (javaTypeContainedTypeOrUnknown != null) {
                CreateContextual = jsonFormatVisitable;
                if (!javaTypeContainedTypeOrUnknown.hasRawClass(Object.class)) {
                    return new RangeSerializer(this._rangeType, serializerProvider.findValueSerializer(javaTypeContainedTypeOrUnknown, beanProperty), propertyNames);
                }
            }
        } else {
            boolean z = jsonFormatVisitable instanceof ContextualSerializer;
            CreateContextual = jsonFormatVisitable;
            if (z) {
                CreateContextual = ((ContextualSerializer) jsonFormatVisitable).createContextual(serializerProvider, beanProperty);
            }
        }
        return (CreateContextual == this._endpointSerializer && propertyNames == null) ? this : new RangeSerializer(this._rangeType, CreateContextual, propertyNames);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Range<?> range, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject(range);
        _writeContents(range, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Range<?> range, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.setCurrentValue(range);
        WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(range, JsonToken.START_OBJECT));
        _writeContents(range, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
    }

    private void _writeContents(Range<?> range, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (range.hasLowerBound()) {
            String str = this._fieldNames.lowerEndpoint;
            if (this._endpointSerializer != null) {
                jsonGenerator.writeFieldName(str);
                this._endpointSerializer.serialize(range.lowerEndpoint(), jsonGenerator, serializerProvider);
            } else {
                serializerProvider.defaultSerializeField(str, range.lowerEndpoint(), jsonGenerator);
            }
            jsonGenerator.writeStringField(this._fieldNames.lowerBoundType, range.lowerBoundType().name());
        }
        if (range.hasUpperBound()) {
            String str2 = this._fieldNames.upperEndpoint;
            if (this._endpointSerializer != null) {
                jsonGenerator.writeFieldName(str2);
                this._endpointSerializer.serialize(range.upperEndpoint(), jsonGenerator, serializerProvider);
            } else {
                serializerProvider.defaultSerializeField(str2, range.upperEndpoint(), jsonGenerator);
            }
            jsonGenerator.writeStringField(this._fieldNames.upperBoundType, range.upperBoundType().name());
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor jsonObjectFormatVisitorExpectObjectFormat;
        if (jsonFormatVisitorWrapper == null || (jsonObjectFormatVisitorExpectObjectFormat = jsonFormatVisitorWrapper.expectObjectFormat(javaType)) == null || this._endpointSerializer == null) {
            return;
        }
        JavaType javaTypeContainedType = this._rangeType.containedType(0);
        JavaType javaTypeConstructType = jsonFormatVisitorWrapper.getProvider().constructType(BoundType.class);
        JsonSerializer<Object> jsonSerializerFindValueSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(javaTypeConstructType, (BeanProperty) null);
        jsonObjectFormatVisitorExpectObjectFormat.property(this._fieldNames.lowerEndpoint, this._endpointSerializer, javaTypeContainedType);
        jsonObjectFormatVisitorExpectObjectFormat.property(this._fieldNames.lowerBoundType, jsonSerializerFindValueSerializer, javaTypeConstructType);
        jsonObjectFormatVisitorExpectObjectFormat.property(this._fieldNames.upperEndpoint, this._endpointSerializer, javaTypeContainedType);
        jsonObjectFormatVisitorExpectObjectFormat.property(this._fieldNames.upperBoundType, jsonSerializerFindValueSerializer, javaTypeConstructType);
    }
}
