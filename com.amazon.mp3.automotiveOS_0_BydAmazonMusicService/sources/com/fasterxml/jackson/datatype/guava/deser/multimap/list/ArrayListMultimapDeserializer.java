package com.fasterxml.jackson.datatype.guava.deser.multimap.list;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.datatype.guava.deser.multimap.GuavaMultimapDeserializer;
import com.google.common.collect.ArrayListMultimap;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes6.dex */
public class ArrayListMultimapDeserializer extends GuavaMultimapDeserializer<ArrayListMultimap<Object, Object>> {
    private static final long serialVersionUID = 1;

    public ArrayListMultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
    }

    public ArrayListMultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method, NullValueProvider nullValueProvider) {
        super(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer, method, nullValueProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.multimap.GuavaMultimapDeserializer
    public ArrayListMultimap<Object, Object> createMultimap() {
        return ArrayListMultimap.create();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.multimap.GuavaMultimapDeserializer
    protected JsonDeserializer<?> _createContextual(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method, NullValueProvider nullValueProvider) {
        return new ArrayListMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer, method, nullValueProvider);
    }
}
