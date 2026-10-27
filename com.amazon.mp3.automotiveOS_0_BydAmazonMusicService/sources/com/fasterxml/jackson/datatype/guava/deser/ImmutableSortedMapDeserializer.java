package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

/* JADX INFO: loaded from: classes6.dex */
public class ImmutableSortedMapDeserializer extends GuavaImmutableMapDeserializer<ImmutableSortedMap<Object, Object>> {
    private static final long serialVersionUID = 2;

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableMapDeserializer, com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ AccessPattern getEmptyAccessPattern() {
        return super.getEmptyAccessPattern();
    }

    public ImmutableSortedMapDeserializer(JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(javaType, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return ImmutableSortedMap.of();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableMapDeserializer
    protected ImmutableMap.Builder<Object, Object> createBuilder() {
        return ImmutableSortedMap.naturalOrder();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public GuavaMapDeserializer<ImmutableSortedMap<Object, Object>> withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        return new ImmutableSortedMapDeserializer(this._containerType, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider);
    }
}
