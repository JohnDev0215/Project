package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes6.dex */
public class GuavaOptionalDeserializer extends ReferenceTypeDeserializer<Optional<?>> {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public /* bridge */ /* synthetic */ ReferenceTypeDeserializer<Optional<?>> withResolved(TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        return withResolved2(typeDeserializer, (JsonDeserializer<?>) jsonDeserializer);
    }

    public GuavaOptionalDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(javaType, valueInstantiator, typeDeserializer, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    /* JADX INFO: renamed from: withResolved, reason: avoid collision after fix types in other method */
    public ReferenceTypeDeserializer<Optional<?>> withResolved2(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new GuavaOptionalDeserializer(this._fullType, this._valueInstantiator, typeDeserializer, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer, com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
    public Optional<?> getNullValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return Optional.fromNullable(this._valueDeserializer.getNullValue(deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return getEmptyValue(deserializationContext);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public Optional<?> referenceValue(Object obj) {
        return Optional.fromNullable(obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public Object getReferenced(Optional<?> optional) {
        return optional.get();
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public Optional<?> updateReference(Optional<?> optional, Object obj) {
        return Optional.fromNullable(obj);
    }
}
