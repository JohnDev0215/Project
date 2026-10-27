package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSortedMultiset;

/* JADX INFO: loaded from: classes6.dex */
public class ImmutableSortedMultisetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSortedMultiset<Object>> {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer, com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ AccessPattern getEmptyAccessPattern() {
        return super.getEmptyAccessPattern();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return super.supportsUpdate(deserializationConfig);
    }

    public ImmutableSortedMultisetDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public GuavaCollectionDeserializer<ImmutableSortedMultiset<Object>> withResolved(JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        return new ImmutableSortedMultisetDeserializer(this._containerType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    protected ImmutableCollection.Builder<Object> createBuilder() {
        return ImmutableSortedMultiset.naturalOrder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public ImmutableSortedMultiset<Object> _createEmpty(DeserializationContext deserializationContext) {
        return ImmutableSortedMultiset.of();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public ImmutableSortedMultiset<Object> _createWithSingleElement(DeserializationContext deserializationContext, Object obj) {
        return (ImmutableSortedMultiset) createBuilder().add(obj).build();
    }
}
