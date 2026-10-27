package com.fasterxml.jackson.datatype.guava.deser.multimap.set;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.datatype.guava.deser.multimap.GuavaMultimapDeserializer;
import com.google.common.collect.HashMultimap;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes6.dex */
public class HashMultimapDeserializer extends GuavaMultimapDeserializer<HashMultimap<Object, Object>> {
    private static final long serialVersionUID = 1;

    public HashMultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
    }

    public HashMultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method, NullValueProvider nullValueProvider) {
        super(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer, method, nullValueProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.multimap.GuavaMultimapDeserializer
    public HashMultimap<Object, Object> createMultimap() {
        return HashMultimap.create();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.multimap.GuavaMultimapDeserializer
    protected JsonDeserializer<?> _createContextual(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method, NullValueProvider nullValueProvider) {
        return new HashMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer, method, nullValueProvider);
    }
}
