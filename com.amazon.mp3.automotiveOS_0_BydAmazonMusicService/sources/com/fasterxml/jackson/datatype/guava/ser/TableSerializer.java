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
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Table;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class TableSerializer extends ContainerSerializer<Table<?, ?, ?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    private final JsonSerializer<Object> _columnSerializer;
    private final BeanProperty _property;
    private final MapSerializer _rowMapSerializer;
    private final JsonSerializer<Object> _rowSerializer;
    private final JavaType _type;
    private final JsonSerializer<Object> _valueSerializer;
    private final TypeSerializer _valueTypeSerializer;

    public TableSerializer(JavaType javaType) {
        super(javaType);
        this._type = javaType;
        this._property = null;
        this._rowSerializer = null;
        this._columnSerializer = null;
        this._valueTypeSerializer = null;
        this._valueSerializer = null;
        this._rowMapSerializer = null;
    }

    protected TableSerializer(TableSerializer tableSerializer, BeanProperty beanProperty, TypeFactory typeFactory, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer3) {
        super(tableSerializer);
        JavaType javaType = tableSerializer._type;
        this._type = javaType;
        this._property = beanProperty;
        this._rowSerializer = jsonSerializer;
        this._columnSerializer = jsonSerializer2;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer3;
        MapType mapTypeConstructMapType = typeFactory.constructMapType(Map.class, javaType.containedTypeOrUnknown(1), javaType.containedTypeOrUnknown(2));
        this._rowMapSerializer = MapSerializer.construct((Set<String>) null, (JavaType) typeFactory.constructMapType(Map.class, javaType.containedTypeOrUnknown(0), mapTypeConstructMapType), false, (TypeSerializer) null, (JsonSerializer<Object>) jsonSerializer, (JsonSerializer<Object>) MapSerializer.construct((Set<String>) null, (JavaType) mapTypeConstructMapType, false, typeSerializer, (JsonSerializer<Object>) jsonSerializer2, (JsonSerializer<Object>) jsonSerializer3, (Object) null), (Object) null);
    }

    protected TableSerializer(TableSerializer tableSerializer, TypeSerializer typeSerializer) {
        super(tableSerializer);
        this._type = tableSerializer._type;
        this._property = tableSerializer._property;
        this._rowSerializer = tableSerializer._rowSerializer;
        this._columnSerializer = tableSerializer._columnSerializer;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = tableSerializer._valueSerializer;
        this._rowMapSerializer = tableSerializer._rowMapSerializer;
    }

    protected TableSerializer withResolved(BeanProperty beanProperty, TypeFactory typeFactory, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer3) {
        return new TableSerializer(this, beanProperty, typeFactory, jsonSerializer, jsonSerializer2, typeSerializer, jsonSerializer3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    protected ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new TableSerializer(this, typeSerializer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.fasterxml.jackson.datatype.guava.ser.TableSerializer] */
    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormatVisitable jsonFormatVisitableCreateContextual = this._valueSerializer;
        if (jsonFormatVisitableCreateContextual == null) {
            JavaType javaTypeContainedTypeOrUnknown = this._type.containedTypeOrUnknown(2);
            if (javaTypeContainedTypeOrUnknown.isFinal()) {
                jsonFormatVisitableCreateContextual = serializerProvider.findValueSerializer(javaTypeContainedTypeOrUnknown, beanProperty);
            }
        } else if (jsonFormatVisitableCreateContextual instanceof ContextualSerializer) {
            jsonFormatVisitableCreateContextual = ((ContextualSerializer) jsonFormatVisitableCreateContextual).createContextual(serializerProvider, beanProperty);
        }
        ?? r7 = jsonFormatVisitableCreateContextual;
        JsonFormatVisitable jsonFormatVisitableCreateContextual2 = this._rowSerializer;
        if (jsonFormatVisitableCreateContextual2 == null) {
            jsonFormatVisitableCreateContextual2 = serializerProvider.findKeySerializer(this._type.containedTypeOrUnknown(0), beanProperty);
        } else if (jsonFormatVisitableCreateContextual2 instanceof ContextualSerializer) {
            jsonFormatVisitableCreateContextual2 = ((ContextualSerializer) jsonFormatVisitableCreateContextual2).createContextual(serializerProvider, beanProperty);
        }
        ?? r4 = jsonFormatVisitableCreateContextual2;
        JsonFormatVisitable jsonFormatVisitableCreateContextual3 = this._columnSerializer;
        if (jsonFormatVisitableCreateContextual3 == null) {
            jsonFormatVisitableCreateContextual3 = serializerProvider.findKeySerializer(this._type.containedTypeOrUnknown(1), beanProperty);
        } else if (jsonFormatVisitableCreateContextual3 instanceof ContextualSerializer) {
            jsonFormatVisitableCreateContextual3 = ((ContextualSerializer) jsonFormatVisitableCreateContextual3).createContextual(serializerProvider, beanProperty);
        }
        ?? r5 = jsonFormatVisitableCreateContextual3;
        TypeSerializer typeSerializerForProperty = this._valueTypeSerializer;
        if (typeSerializerForProperty != null) {
            typeSerializerForProperty = typeSerializerForProperty.forProperty(beanProperty);
        }
        return withResolved(beanProperty, serializerProvider.getTypeFactory(), r4, r5, typeSerializerForProperty, r7);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JavaType getContentType() {
        return this._type.getContentType();
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Table<?, ?, ?> table) {
        return table.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public boolean hasSingleElement(Table<?, ?, ?> table) {
        return table.size() == 1;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Table<?, ?, ?> table, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject(table);
        if (!table.isEmpty()) {
            serializeFields(table, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Table<?, ?, ?> table, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.setCurrentValue(table);
        WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(table, JsonToken.START_OBJECT));
        serializeFields(table, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
    }

    private final void serializeFields(Table<?, ?, ?> table, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        this._rowMapSerializer.serializeFields(table.rowMap(), jsonGenerator, serializerProvider);
    }
}
