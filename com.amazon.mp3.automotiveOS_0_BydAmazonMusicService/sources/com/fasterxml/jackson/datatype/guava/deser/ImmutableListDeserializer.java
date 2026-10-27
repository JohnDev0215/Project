package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.google.common.collect.ImmutableList;

/* JADX INFO: loaded from: classes6.dex */
public class ImmutableListDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableList<Object>> {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer, com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ AccessPattern getEmptyAccessPattern() {
        return super.getEmptyAccessPattern();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return super.supportsUpdate(deserializationConfig);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public /* bridge */ /* synthetic */ GuavaCollectionDeserializer withResolved(JsonDeserializer jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        return withResolved((JsonDeserializer<?>) jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    public ImmutableListDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public ImmutableListDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        return new ImmutableListDeserializer(this._containerType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public ImmutableList.Builder<Object> createBuilder() {
        return ImmutableList.builder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public ImmutableList<Object> _createEmpty(DeserializationContext deserializationContext) {
        return ImmutableList.of();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public ImmutableList<Object> _createWithSingleElement(DeserializationContext deserializationContext, Object obj) {
        return ImmutableList.of(obj);
    }
}
