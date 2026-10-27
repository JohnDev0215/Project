package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.google.common.collect.Multiset;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
abstract class GuavaMultisetDeserializer<T extends Multiset<Object>> extends GuavaCollectionDeserializer<T> {
    private static final long serialVersionUID = 1;

    protected abstract T createMultiset();

    GuavaMultisetDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public T getEmptyValue(DeserializationContext deserializationContext) {
        return (T) _createEmpty(deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public T _deserializeContents(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object objDeserializeWithType;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        T t = (T) createMultiset();
        while (true) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == JsonToken.END_ARRAY) {
                return t;
            }
            if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                if (!this._skipNullValues) {
                    objDeserializeWithType = this._nullProvider.getNullValue(deserializationContext);
                }
            } else if (typeDeserializer == null) {
                objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
            t.add(objDeserializeWithType);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public T _createEmpty(DeserializationContext deserializationContext) {
        return (T) createMultiset();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public T _createWithSingleElement(DeserializationContext deserializationContext, Object obj) {
        T t = (T) createMultiset();
        t.add(obj);
        return t;
    }
}
