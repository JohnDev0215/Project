package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes6.dex */
public class GuavaOptionalSerializer extends ReferenceTypeSerializer<Optional<?>> {
    private static final long serialVersionUID = 1;

    public GuavaOptionalSerializer(ReferenceType referenceType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(referenceType, z, typeSerializer, jsonSerializer);
    }

    public GuavaOptionalSerializer(GuavaOptionalSerializer guavaOptionalSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, Object obj, boolean z) {
        super(guavaOptionalSerializer, beanProperty, typeSerializer, jsonSerializer, nameTransformer, obj, z);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    protected ReferenceTypeSerializer<Optional<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer) {
        return (this._property == beanProperty && this._valueTypeSerializer == typeSerializer && this._valueSerializer == jsonSerializer && this._unwrapper == nameTransformer) ? this : new GuavaOptionalSerializer(this, beanProperty, typeSerializer, jsonSerializer, nameTransformer, this._suppressableValue, this._suppressNulls);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public ReferenceTypeSerializer<Optional<?>> withContentInclusion(Object obj, boolean z) {
        return new GuavaOptionalSerializer(this, this._property, this._valueTypeSerializer, this._valueSerializer, this._unwrapper, obj, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public boolean _isValuePresent(Optional<?> optional) {
        return optional.isPresent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public Object _getReferenced(Optional<?> optional) {
        return optional.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public Object _getReferencedIfPresent(Optional<?> optional) {
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
