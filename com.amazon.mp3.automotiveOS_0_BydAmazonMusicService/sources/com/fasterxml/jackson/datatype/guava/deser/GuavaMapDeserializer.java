package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public abstract class GuavaMapDeserializer<T> extends ContainerDeserializerBase<T> implements ContextualDeserializer {
    private static final long serialVersionUID = 2;
    protected KeyDeserializer _keyDeserializer;
    protected JsonDeserializer<?> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    protected abstract T _deserializeEntries(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public abstract AccessPattern getEmptyAccessPattern();

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public abstract Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException;

    public abstract GuavaMapDeserializer<T> withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider);

    protected GuavaMapDeserializer(JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(javaType, nullValueProvider, (Boolean) null);
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.fasterxml.jackson.databind.KeyDeserializer] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        ?? CreateContextual;
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        Object obj = this._keyDeserializer;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializerForProperty = this._valueTypeDeserializer;
        if (obj == null) {
            CreateContextual = deserializationContext.findKeyDeserializer(this._containerType.getKeyType(), beanProperty);
        } else {
            boolean z = obj instanceof ContextualKeyDeserializer;
            CreateContextual = obj;
            if (z) {
                CreateContextual = ((ContextualKeyDeserializer) obj).createContextual(deserializationContext, beanProperty);
            }
        }
        JavaType contentType = this._containerType.getContentType();
        if (jsonDeserializer == null) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, contentType);
        }
        if (typeDeserializerForProperty != null) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        NullValueProvider nullValueProviderFindContentNullProvider = findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization);
        return (this._keyDeserializer == CreateContextual && this._valueDeserializer == jsonDeserializerHandleSecondaryContextualization && this._valueTypeDeserializer == typeDeserializerForProperty && this._nullProvider == nullValueProviderFindContentNullProvider) ? this : withResolved(CreateContextual, jsonDeserializerHandleSecondaryContextualization, typeDeserializerForProperty, nullValueProviderFindContentNullProvider);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken != JsonToken.FIELD_NAME && currentToken != JsonToken.END_OBJECT) {
            return (T) deserializationContext.handleUnexpectedToken(this._containerType.getRawClass(), jsonParser);
        }
        return _deserializeEntries(jsonParser, deserializationContext);
    }
}
