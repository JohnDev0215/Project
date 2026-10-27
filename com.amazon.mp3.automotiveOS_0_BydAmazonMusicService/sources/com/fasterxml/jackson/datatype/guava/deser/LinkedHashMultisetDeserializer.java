package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.google.common.collect.LinkedHashMultiset;

/* JADX INFO: loaded from: classes6.dex */
public class LinkedHashMultisetDeserializer extends GuavaMultisetDeserializer<LinkedHashMultiset<Object>> {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer, com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ AccessPattern getEmptyAccessPattern() {
        return super.getEmptyAccessPattern();
    }

    public LinkedHashMultisetDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public LinkedHashMultiset<Object> createMultiset() {
        return LinkedHashMultiset.create();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public GuavaCollectionDeserializer<LinkedHashMultiset<Object>> withResolved(JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        return new LinkedHashMultisetDeserializer(this._containerType, jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }
}
