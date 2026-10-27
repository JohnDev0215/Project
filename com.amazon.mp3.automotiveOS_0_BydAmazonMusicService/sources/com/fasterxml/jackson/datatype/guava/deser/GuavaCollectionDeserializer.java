package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;
import java.util.Collection;

/* JADX INFO: loaded from: classes6.dex */
public abstract class GuavaCollectionDeserializer<T> extends ContainerDeserializerBase<T> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<?> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    protected abstract T _createEmpty(DeserializationContext deserializationContext);

    protected abstract T _createWithSingleElement(DeserializationContext deserializationContext, Object obj);

    protected abstract T _deserializeContents(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public abstract AccessPattern getEmptyAccessPattern();

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public abstract Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException;

    public abstract GuavaCollectionDeserializer<T> withResolved(JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool);

    protected GuavaCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, nullValueProvider, bool);
        this._valueTypeDeserializer = typeDeserializer;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Boolean boolFindFormatFeature = findFormatFeature(deserializationContext, beanProperty, Collection.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonDeserializer<?> jsonDeserializerFindContextualValueDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializerForProperty = this._valueTypeDeserializer;
        if (jsonDeserializerFindContextualValueDeserializer == null) {
            jsonDeserializerFindContextualValueDeserializer = deserializationContext.findContextualValueDeserializer(this._containerType.getContentType(), beanProperty);
        }
        if (typeDeserializerForProperty != null) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        NullValueProvider nullValueProviderFindContentNullProvider = findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerFindContextualValueDeserializer);
        return (boolFindFormatFeature == this._unwrapSingle && nullValueProviderFindContentNullProvider == this._nullProvider && jsonDeserializerFindContextualValueDeserializer == this._valueDeserializer && typeDeserializerForProperty == this._valueTypeDeserializer) ? this : withResolved(jsonDeserializerFindContextualValueDeserializer, typeDeserializerForProperty, nullValueProviderFindContentNullProvider, boolFindFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Collection;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedStartArrayToken()) {
            return _deserializeContents(jsonParser, deserializationContext);
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return _deserializeFromSingleValue(jsonParser, deserializationContext);
        }
        return (T) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
    }

    protected T _deserializeFromSingleValue(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object objDeserializeWithType;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            if (this._skipNullValues) {
                return _createEmpty(deserializationContext);
            }
            objDeserializeWithType = this._nullProvider.getNullValue(deserializationContext);
        } else if (typeDeserializer == null) {
            objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
        } else {
            objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return _createWithSingleElement(deserializationContext, objDeserializeWithType);
    }
}
